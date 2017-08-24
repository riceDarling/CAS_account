package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountReceipt;
import com.account.utils.PageUtil;

/**
 * 发票DAO接口
 */
public interface AccountReceiptDao {
	void save(AccountReceipt accountReceipt);

	void delete(String id);

	void update(AccountReceipt accountReceipt);

	List<AccountReceipt> findList(Map<String, Object> map);

	AccountReceipt getById(String id);
	List<Map<String, Object>> findAllTitle();
	  public List<AccountReceipt> selectView(
				@Param("supplier") String supplier ,
				@Param("paging") PageUtil paging,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
		
		
		
		public int selectViewCount(
				@Param("supplier") String supplier ,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
}