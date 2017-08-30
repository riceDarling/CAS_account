package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountInput;
import com.account.utils.PageUtil;

/**
 * 入库单主表DAO接口
 */
public interface AccountInputDao {

	void save(AccountInput accountInput);

	void delete(String id);

	void update(AccountInput accountInput);

	List<AccountInput> findList(Map<String, Object> map);

	AccountInput getById(String id);
	List<Map<String, Object>> findAllTitle();
	  public List<AccountInput> selectView(
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