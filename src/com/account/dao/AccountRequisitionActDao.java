package com.account.dao;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountRequisitionAct;

public interface AccountRequisitionActDao {

	public void insert(AccountRequisitionAct entity);
	
	public void update(AccountRequisitionAct entity);
	
	public List<Map<String,Object>> getbyRequisitionId(String requisitionId);
	
	public AccountRequisitionAct getbyRequisitionIdAndChecknameAndState(AccountRequisitionAct entity);
	
	public List<AccountRequisitionAct> getbyRequisitionIdAndChecknameAndStates(AccountRequisitionAct entity);
	
	/**
	 * 待办(完成)
	 * @param checkname
	 * @return
	 */
	public List<Map<String,Object>> findList(AccountRequisitionAct entity);
	
	public void deleteByRequisitionId(String requisitionId);
	
	/**
	 * 获取最新的节点
	 */
	public AccountRequisitionAct getbyRequisitionIdAndState(String requisitionId);
	
	public void revoke(AccountRequisitionAct entity);
	
	/**
	 * 根据requisitionId和步数查询一条记录
	 */
	public AccountRequisitionAct getbyRequisitionIdAndStep(Map<String,Object> map);
}
