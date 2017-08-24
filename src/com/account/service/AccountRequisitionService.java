package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountRequisition;
import com.account.utils.PageUtil;

public interface AccountRequisitionService {
	public AccountRequisition get(String id);
	public void save(AccountRequisition accountRequisition);
	/**
	 * 获取字表详细数据（关联物资，计量单位）
	 * @param parentid
	 * @return
	 */
	List<Map<String,Object>> getDetailMapByparentid(String parentid);
	
	public void saveAudit(AccountRequisition accountRequisition);
	
	void delete(String requisitionid);
	
	void findPage(AccountRequisition entity);
	public List<AccountRequisition> getAccountPurchaseTitle();
	
	public List<AccountRequisition> selectView( String title, String checker, String procInsId, PageUtil paging , String startTime, String endTime );
	
	public int selectViewCount( String title, String checker, String procInsId , String startTime, String endTime );

}
