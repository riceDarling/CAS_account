package com.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountInquiryDao;
import com.account.dao.AccountInquiryDetailDao;
import com.account.dao.AccountRequisitionActDao;
import com.account.dao.AccountRequisitionDao;
import com.account.dao.AccountRequisitionDetailDao;
import com.account.dao.MaterialSupplierMapper;
import com.account.entity.AccountInquiry;
import com.account.entity.AccountInquiryDetail;
import com.account.entity.AccountRequisition;
import com.account.entity.AccountRequisitionAct;
import com.account.entity.AccountRequisitionDetail;
import com.account.entity.Admin;
import com.account.service.AccountRequisitionService;
import com.account.utils.FormatDateUtils;
import com.account.utils.PageUtil;
import com.account.utils.RandomUtils;

/**
 * 申请单Service
 * 
 * @author admin
 * @version 2017-07-24
 */
@Service
public class AccountRequisitionServiceImpl implements AccountRequisitionService {

	@Autowired
	private AccountRequisitionDao accountRequisitionDao;

	@Autowired
	private AccountRequisitionDetailDao accountRequisitionDetailDao;

	@Autowired
	private AccountRequisitionActDao accountRequisitionActDao;

	@Autowired
	private AccountInquiryDao accountInquiryDao;
	
	@Autowired
	private AccountInquiryDetailDao accountInquiryDetailDao;
	
	@Autowired
	private MaterialSupplierMapper materialSupplierMapper;

	public AccountRequisition get(String id) {
		AccountRequisition accountRequisition = accountRequisitionDao.get(id);
		// accountRequisition.setAccountRequisitionDetailList(accountRequisitionDetailDao.findList(new AccountRequisitionDetail(accountRequisition)));
		return accountRequisition;
	}

	public void save(AccountRequisition accountRequisition) {
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if (accountRequisition.getId() == null) {
			accountRequisition.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			// 生成17位单据编号 04-20170203-00001
			String ordernum = "04-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();
			accountRequisition.setOrdernum(ordernum);
			
			accountRequisition.setOffice("测试");
			accountRequisition.setCreateBy(loginAdmin.getId().toString());
			accountRequisition.setCreateDate(new Date());
			accountRequisition.setProcInsId("start");
			accountRequisition.setAct_checker(accountRequisition.getChecker());
			accountRequisitionDao.insert(accountRequisition);
		} else {
			AccountRequisitionAct now_act = new AccountRequisitionAct();
			now_act.setActindex(0);

			now_act.setCheckerName(loginAdmin.getId().toString());
			now_act.setRequisitionId(accountRequisition.getId());
			now_act = accountRequisitionActDao.getbyRequisitionIdAndChecknameAndState(now_act);
			now_act.setState(1);
			now_act.setConclusion(1);
			now_act.setRemarks("重申");
			now_act.setEndTime(new Date());
			accountRequisitionActDao.update(now_act);
			
			accountRequisition.setOffice("测试");
			accountRequisition.setCreateBy(loginAdmin.getId().toString());
			accountRequisition.setCreateDate(new Date());
			accountRequisition.setProcInsId("start");
			accountRequisition.setAct_checker(accountRequisition.getChecker());
			accountRequisitionDao.update(accountRequisition);
		}
		

		accountRequisitionDetailDao.deleteByparentId(accountRequisition.getId());
		for (AccountRequisitionDetail accountRequisitionDetail : accountRequisition.getAccountRequisitionDetailList()) {
			accountRequisitionDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountRequisitionDetail.setParent(accountRequisition);
			accountRequisitionDetailDao.insert(accountRequisitionDetail);
		}

		AccountRequisitionAct act = new AccountRequisitionAct();
		act.setActindex(0);
		act.setRequisitionId(accountRequisition.getId());

		act.setRequisitionName(loginAdmin.getId().toString());
		act.setStartTime(new Date());
		act.setStep(0);
		act.setState(1);
		act.setCheckerName(loginAdmin.getId().toString());
		accountRequisitionActDao.insert(act);

		act.setConclusion(1);
		act.setRemarks("提交申请");
		act.setEndTime(new Date());
		accountRequisition.setAct_checker(accountRequisition.getChecker());
		accountRequisitionActDao.update(act);

		AccountRequisitionAct next_act = new AccountRequisitionAct();
		next_act.setActindex(0);
		next_act.setRequisitionId(accountRequisition.getId());
		next_act.setRequisitionName(loginAdmin.getId().toString());
		next_act.setStartTime(new Date());
		next_act.setStep(1);
		next_act.setState(0);
		next_act.setCheckerName(accountRequisition.getChecker());
		accountRequisitionActDao.insert(next_act);
	}

	@Override
	public List<Map<String, Object>> getDetailMapByparentid(String parentid) {
		return accountRequisitionDetailDao.getDetailMapByparentid(parentid);
	}

	public void saveAudit(AccountRequisition accountRequisition) {
		AccountRequisitionAct now_act = new AccountRequisitionAct();
		now_act.setActindex(0);
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		now_act.setCheckerName(loginAdmin.getId().toString());
		now_act.setRequisitionId(accountRequisition.getId());
		now_act = accountRequisitionActDao.getbyRequisitionIdAndChecknameAndState(now_act);

		now_act.setState(1);
		now_act.setConclusion("yes".equals(accountRequisition.getConclusion()) || "end".equals(accountRequisition.getConclusion()) ? 1 : 0);
		now_act.setRemarks(accountRequisition.getComment());
		now_act.setEndTime(new Date());
		accountRequisition.setUpdate(new Date());
		accountRequisition.setUpdateBy(loginAdmin.getId().toString());
		accountRequisitionActDao.update(now_act);
		if ("yes".equals(accountRequisition.getConclusion())) {
			// 如果有人进行审核并且通过，修改主表状态为"examine"
			//accountRequisition.setProcInsId("examine");
			accountRequisition.setAct_checker(accountRequisition.getChecker());
			accountRequisitionDao.update(accountRequisition);
			// 如果同意则，进入下一个节点
			AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(0);
			next_act.setRequisitionId(accountRequisition.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(now_act.getStep() + 1);
			next_act.setState(0);
			next_act.setCheckerName(accountRequisition.getChecker());
			accountRequisitionActDao.insert(next_act);
		} else if ("end".equals(accountRequisition.getConclusion())) {
			// 结束标识
			accountRequisition.setProcInsId("end");
			accountRequisitionDao.update(accountRequisition);
			// 添加数据到询价单主表
			AccountInquiry entity = new AccountInquiry();

			// 生成17位单据编号 04-20170203-00001
			String ordernum = "13-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();
			// 生成37位入库单号
			String inquirynum = "xj-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();


			accountRequisition = accountRequisitionDao.get(accountRequisition.getId());
			entity.setOrdernum(ordernum);
			entity.setInquirynum(inquirynum);
			entity.setRequisition(accountRequisition.getId());
			entity.setStatus("start");
			entity.setTitle(accountRequisition.getTitle());

			// 询价人
			entity.setInquiry(accountRequisition.getChecker());
			entity.setCreateDate(new Date());
			
			entity.setValiddate(new Date());
			entity.setMaker(loginAdmin.getUsername());
			//创建人改为任务办理人（流程第一节点）
			entity.setCreateBy(accountRequisition.getChecker());
			entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountInquiryDao.insert(entity);
			//询价子表(申购单中的所有物资，系统中提供物资的供应商)
			//根据物资查询所有提供的企业，存进询价单子表
			List<Map<String, Object>> list=accountRequisitionDetailDao.getDetailMapByparentid(accountRequisition.getId());
			for (Map<String, Object> map : list) {
				String materialNum=(String) map.get("materialNum");
				Integer units=(Integer) map.get("units");
				List<Map<String, Object>> materialSupplier=materialSupplierMapper.selectMapByMaterialNum(materialNum);
				for (Map<String, Object> material : materialSupplier) {
					String ai_supplierNum=(String) material.get("supplierNum");//供应商编号
					String ai_supplier=(String) material.get("supplier");//供应商名称
					String ai_materialNum=(String) material.get("materialNum");//物资编号
					String ai_materialName=(String) material.get("materialName");//物资名称
					String ai_size=(String) material.get("size");//物资型号
					AccountInquiryDetail accountInquiryDetail=new AccountInquiryDetail();
					accountInquiryDetail.setSuppliercode(ai_supplierNum);
					accountInquiryDetail.setSupplier(ai_supplier);
					accountInquiryDetail.setMaterialcode(ai_materialNum);
					accountInquiryDetail.setMaterialname(ai_materialName);
					accountInquiryDetail.setSize(ai_size);
					accountInquiryDetail.setUnit(units);
					
					accountInquiryDetail.setMaker(loginAdmin.getId().toString());
					accountInquiryDetail.setCreateBy(loginAdmin.getId().toString());
					accountInquiryDetail.setCreateDate(new Date());
					accountInquiryDetail.setOrdernum(entity.getOrdernum());
					accountInquiryDetail.setInquirydetailnum(entity.getId());
					accountInquiryDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					accountInquiryDetailDao.insert(accountInquiryDetail);
					
				}
			}
			//询价办理
			/*AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(1);
			next_act.setRequisitionId(entity.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(0);
			next_act.setState(0);
			next_act.setCheckerName(accountRequisition.getChecker());
			accountRequisitionActDao.insert(next_act);*/
		} else {
			//办理人修改为，上一步办理人
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("requisitionId", now_act.getRequisitionId());
			map.put("step",now_act.getStep()-1);
			AccountRequisitionAct 	previous_act=accountRequisitionActDao.getbyRequisitionIdAndStep(map);
			accountRequisition.setAct_checker(previous_act.getCheckerName());
			accountRequisition.setProcInsId("rejected");
			accountRequisitionDao.update(accountRequisition);
			// 返回上一个节点驳回目标人为上一节点（逐级驳回）
			AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(0);
			next_act.setRequisitionId(accountRequisition.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(now_act.getStep() - 1);
			next_act.setState(0);
			next_act.setCheckerName(previous_act.getCheckerName());
			accountRequisitionActDao.insert(next_act);
		}

	}

	public void delete(String requisitionid) {
		accountRequisitionDao.delete(requisitionid);
		accountRequisitionActDao.deleteByRequisitionId(requisitionid);
	}

	@Override
	public void findPage(AccountRequisition entity) {
		List<AccountRequisition> list=accountRequisitionDao.findList(entity);
		entity.getPage().setList(list);
	}
	@Override
	public List<AccountRequisition> getAccountPurchaseTitle() {
		return accountRequisitionDao.getAccountPurchaseTitle();
	}

	@Override
	public List<AccountRequisition> selectView(String title, String checker, String procInsId, PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountRequisitionDao.selectView(title, checker, procInsId, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String checker, String procInsId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountRequisitionDao.selectViewCount(title, checker, procInsId, startTime, endTime);
	}

}