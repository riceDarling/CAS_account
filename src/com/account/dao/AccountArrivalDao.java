package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountArrival;
import com.account.utils.PageUtil;

/**
 * 到货单DAO接口
 */
public interface AccountArrivalDao {
	void save(AccountArrival accountArrival);

	void delete(String id);

	void update(AccountArrival accountArrival);

	void updateStatus(String id);

	List<AccountArrival> findList(Map<String, Object> map);

	AccountArrival getById(String id);

	public List<AccountArrival> findAllorderNum();
	List<Map<String, Object>> findAllTitle();
	  public List<AccountArrival> selectView(
				@Param("supplier") String supplier ,
				@Param("arrivalstatus") String arrivalstatus ,
				@Param("paging") PageUtil paging,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
		
		
		
		public int selectViewCount(
				@Param("supplier") String supplier ,
				@Param("arrivalstatus") String arrivalstatus ,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
}