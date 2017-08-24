package com.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountInquiryDao;
import com.account.dao.AccountPurchaseDao;
import com.account.dao.AccountRequisitionActDao;
import com.account.dao.AccountRequisitionDao;
import com.account.entity.AccountPurchase;
import com.account.entity.AccountRequisition;
import com.account.entity.AccountRequisitionAct;
import com.account.entity.Admin;
import com.account.service.AccountRequisitionActService;


@Service
@Transactional
public class AccountRequisitionActServiceImpl implements AccountRequisitionActService {
	
	@Autowired
	private	AccountRequisitionActDao dao;
	
	@Autowired
	private AccountInquiryDao accountInquiryDao;
	
	@Autowired
	private AccountRequisitionDao accountRequisitionDao;
	
	
	@Autowired
	private AccountPurchaseDao accountPurchaseDao;

	
	public void insert(AccountRequisitionAct entity) {
		dao.insert(entity);
	};
	
	public void update(AccountRequisitionAct entity) {
		dao.update(entity);
	};
	
	public List<Map<String,Object>> getbyRequisitionId(String requisitionId){
		return dao.getbyRequisitionId(requisitionId);
	};
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public AccountRequisitionAct findPage(AccountRequisitionAct entity) {
		entity.getPage().setList(dao.findList(entity));
		return entity;
	}

	public String revoke(String requisitionId) {
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin =(Admin) subject.getSession().getAttribute("loginAdmin");
		AccountRequisitionAct now_act=dao.getbyRequisitionIdAndState(requisitionId);
		String now_RequisitionName=now_act.getRequisitionName();
		if(now_RequisitionName.equals(loginAdmin.getId().toString())){
			//如果申请人是当前登录人，可以执行撤销操作(当前节点申请人是自己，办理人也改为自己)
			now_act.setCheckerName(loginAdmin.getId().toString());
			/*//如果是询价流程，则要改变询价主表状态，方便添加询价记录
			if(now_act.getActindex()==1){
				accountInquiryDao.setInquiryStatusById(now_act.getRequisitionId(), "0");
			}*/
			if(now_act.getActindex()==0){
				
				AccountRequisition accountRequisition=accountRequisitionDao.get(requisitionId);
				//AccountRequisition accountRequisition=new AccountRequisition();
				//accountRequisition.setId(requisitionId);
				if("rejected".equals(accountRequisition.getProcInsId())){
					return "当前处于驳回状态，无法撤销";
				}else{	
					//如果是申购，则要修改申购主表办理人
					//办理人修改为，上一步办理人
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("requisitionId", now_act.getRequisitionId());
					map.put("step",now_act.getStep()-1);
					AccountRequisitionAct 	previous_act=dao.getbyRequisitionIdAndStep(map);
					accountRequisition.setAct_checker(previous_act.getCheckerName());
					accountRequisitionDao.update(accountRequisition);
				}
				
			}else if(now_act.getActindex()==2){
				AccountPurchase accountPurchase=accountPurchaseDao.get(requisitionId);
				//AccountPurchase accountPurchase=new AccountPurchase();
				//accountPurchase.setId(requisitionId);
				if("rejected".equals(accountPurchase.getProcInsId())){
					return "当前处于驳回状态，无法撤销";
				}else{
					//办理人修改为，上一步办理人
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("requisitionId", now_act.getRequisitionId());
					map.put("step",now_act.getStep()-1);
					AccountRequisitionAct 	previous_act=dao.getbyRequisitionIdAndStep(map);
					accountPurchase.setAct_checker(previous_act.getCheckerName());
					accountPurchaseDao.update(accountPurchase);
				}	
			}
			
			//步数-1
			int step=now_act.getStep();
			if(step>0){
				now_act.setStep(step-1);
			}	
			dao.revoke(now_act);
			return "撤销成功";
		}else{
			return "下一节点已处理，无法越级撤销";
		}
		
	}
	
}
