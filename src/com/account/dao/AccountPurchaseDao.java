package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountPurchase;
import com.account.entity.AccountRequisition;
import com.account.utils.PageUtil;

/**
 * 订货单DAO接口
 * 
 */
public interface AccountPurchaseDao {

	public AccountPurchase get(String id);

	public List<AccountPurchase> findList(AccountPurchase accountPurchase);

	public List<AccountPurchase> findAllList(AccountPurchase accountPurchase);

	public void insert(AccountPurchase accountPurchase);

	public void update(AccountPurchase accountPurchase);

	public void delete(String id);
	public List<AccountPurchase> findAllorderNum();
	public List<AccountPurchase> selectView(
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