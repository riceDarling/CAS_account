package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountInspection;
import com.account.utils.PageUtil;

/**
 * 送检单主表DAO接口
 */
public interface AccountInspectionDao {
	AccountInspection getById(String id);

	void save(AccountInspection accountInspection);

	void delete(String id);

	void update(AccountInspection accountInspection);
	void upSatus(String id);

	List<AccountInspection> findList(Map<String, Object> map);
	
	List<Map<String,Object>> getArrivalNum();
	List<Map<String, Object>> findAllTitle();
	  public List<AccountInspection> selectView(
				@Param("supplier") String supplier ,
				@Param("status") String status ,
				@Param("paging") PageUtil paging,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
		
		
		
		public int selectViewCount(
				@Param("supplier") String supplier ,
				@Param("status") String status ,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
}