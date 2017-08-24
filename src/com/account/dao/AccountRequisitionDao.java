package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountRequisition;
import com.account.utils.PageUtil;

/**
 * 申请单DAO接口
 */
public interface AccountRequisitionDao  {
	AccountRequisition getDataByProcId(String procInsId);
	AccountRequisition selectRequisitionByOrdernum(String orderNumber);
	
	AccountRequisition get(String id);
	
	List<AccountRequisition> findList(AccountRequisition entity);
	List<AccountRequisition> findAllList(AccountRequisition entity);
	
	void insert(AccountRequisition entity);
	void update(AccountRequisition entity);
	
	void delete(String id);
	
	public List<AccountRequisition> getAccountPurchaseTitle();
	
	
	public List<AccountRequisition> selectView(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("procInsId") String procInsId ,
			@Param("paging") PageUtil paging,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
	
	
	
	public int selectViewCount(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("procInsId") String procInsId ,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
}