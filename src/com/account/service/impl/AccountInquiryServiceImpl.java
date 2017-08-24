package com.account.service.impl;

import java.util.Date;
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
import com.account.dao.AccountPurchaseDao;
import com.account.dao.AccountRequisitionActDao;
import com.account.entity.AccountInquiry;
import com.account.entity.AccountInquiryDetail;
import com.account.entity.AccountPurchase;
import com.account.entity.AccountRequisition;
import com.account.entity.AccountRequisitionAct;
import com.account.entity.Admin;
import com.account.service.AccountInquiryService;
import com.account.utils.FormatDateUtils;
import com.account.utils.PageUtil;
import com.account.utils.RandomUtils;

@Service
public class AccountInquiryServiceImpl implements AccountInquiryService {

	@Autowired
	private AccountInquiryDao accountInquiryDao;

	@Autowired
	private AccountInquiryDetailDao accountInquiryDetailDao;

	@Autowired
	private AccountRequisitionActDao accountRequisitionActDao;
	
	@Autowired
	private AccountPurchaseDao accountPurchaseDao;

	@Override
	public AccountInquiry getById(String id) {
		return accountInquiryDao.get(id);
	}

	@Override
	public void findPage(AccountInquiry accountInquiry) {
		accountInquiry.getPage().setList(accountInquiryDao.findList(accountInquiry));
	}

	public void commit1(String ordernum) {
		accountInquiryDao.commit1(ordernum);
	}

	@Override
	public void detailupdata(String[] inquirydetailId,String checker) {
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		AccountInquiryDetail etail = accountInquiryDetailDao.get(inquirydetailId[0]);
		
		//流程当前节点
		AccountRequisitionAct now_act = new AccountRequisitionAct();
		now_act.setActindex(1);
		now_act.setCheckerName(loginAdmin.getId().toString());
		now_act.setRequisitionId(etail.getInquirydetailnum());
		now_act = accountRequisitionActDao.getbyRequisitionIdAndChecknameAndState(now_act);
		
		now_act.setState(1);
		now_act.setConclusion(1);
		now_act.setRemarks("提交询价申请");
		now_act.setEndTime(new Date());
		now_act.setActindex(1);
		accountRequisitionActDao.update(now_act);
		//将全部的字表状态恢复到默认值0
		accountInquiryDetailDao.updateByOrdernum(etail.getOrdernum());
		
		accountInquiryDao.setInquiryStatusById(etail.getInquirydetailnum(), "1");
		for (String id : inquirydetailId) {
			accountInquiryDetailDao.setInquiryDetailStateById(id, "1");
		}
		//下一节点
		AccountRequisitionAct next_act = new AccountRequisitionAct();
		next_act.setActindex(1);
		next_act.setRequisitionId(etail.getInquirydetailnum());
		next_act.setRequisitionName(loginAdmin.getId().toString());
		next_act.setStartTime(new Date());
		next_act.setStep(1);
		next_act.setState(0);
		next_act.setCheckerName(checker);
		accountRequisitionActDao.insert(next_act);
	}

	@Override
	public void saveAudit(AccountInquiry entity) {
		AccountRequisitionAct now_act = new AccountRequisitionAct();
		
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		now_act.setCheckerName(loginAdmin.getId().toString());
		now_act.setRequisitionId(entity.getId());
		now_act = accountRequisitionActDao.getbyRequisitionIdAndChecknameAndState(now_act);

		now_act.setState(1);
		now_act.setConclusion("yes".equals(entity.getConclusion()) || "end".equals(entity.getConclusion()) ? 1 : 0);
		now_act.setRemarks(entity.getComment());
		now_act.setEndTime(new Date());
		entity.setUpdate(new Date());
		entity.setUpdateBy(loginAdmin.getId().toString());
		now_act.setActindex(1);
		accountRequisitionActDao.update(now_act);
		if ("yes".equals(entity.getConclusion())) {
			
			// 如果同意则，进入下一个节点
			AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(1);
			next_act.setRequisitionId(entity.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(now_act.getStep() + 1);
			next_act.setState(0);
			next_act.setCheckerName(entity.getChecker());
			accountRequisitionActDao.insert(next_act);
		} else if ("end".equals(entity.getConclusion())) {
			String checker=entity.getChecker();
			// 结束标识
			entity.setStatus("end");
			accountInquiryDao.setInquiryStatusById(entity.getId(), "2");
			entity =accountInquiryDao.get(entity.getId());
			AccountPurchase accountPurchase=new AccountPurchase();
			// 生成17位单据编号 04-20170203-00001
			String ordernum = "06-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();
			accountPurchase.setOrdernum(ordernum);
						
			accountPurchase.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountPurchase.setTitle(entity.getTitle()+"订货单");
			accountPurchase.setMaker(loginAdmin.getId().toString());
			accountPurchase.setProcInsId("start");
			//创建人，订货办理人
			accountPurchase.setCreateBy(checker);
			accountPurchase.setInquirynum(entity.getOrdernum());
			accountPurchase.setCreateDate(new Date());
			accountPurchaseDao.insert(accountPurchase);
			//添加订货任务
			AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(2);
			next_act.setRequisitionId(accountPurchase.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(0);
			next_act.setState(0);
			next_act.setCheckerName(checker);
			accountRequisitionActDao.insert(next_act);
		} else {
			accountInquiryDao.setInquiryStatusById(entity.getId(), "0");
			
			// 返回上一个节点驳回目标人为上一节点（逐级驳回）
			//此处bug,驳回的处理人当前任务的为申请人
			AccountRequisitionAct next_act = new AccountRequisitionAct();
			next_act.setActindex(1);
			next_act.setRequisitionId(entity.getId());
			next_act.setRequisitionName(loginAdmin.getId().toString());
			next_act.setStartTime(new Date());
			next_act.setStep(now_act.getStep() - 1);
			next_act.setState(0);
			next_act.setCheckerName(now_act.getRequisitionName());
			accountRequisitionActDao.insert(next_act);
		}
		
	}

	@Override
	public List<Map<String, Object>> selectAllTitle() {
		// TODO Auto-generated method stub
		return accountInquiryDao.selectAllTitle();
	}

	@Override
	public List<AccountInquiry> selectView(String title, String checker, String procInsId, PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountInquiryDao.selectView(title, checker, procInsId, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String checker, String procInsId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountInquiryDao.selectViewCount(title, checker, procInsId, startTime, endTime);
	}

	@Override
	public void saveEnd(String accountInquiryid) {
		AccountInquiry accountInquiry=new AccountInquiry();
		
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		accountInquiry.setId(accountInquiryid);
		accountInquiry.setStatus("end");
		accountInquiry.setUpdate(new Date());
		accountInquiry.setUpdateBy(loginAdmin.getId().toString());
		accountInquiryDao.update(accountInquiry);
		
	}




}
