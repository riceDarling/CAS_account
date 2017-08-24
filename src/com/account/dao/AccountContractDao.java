package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountContract;
import com.account.utils.PageUtil;

/**
 * 合同DAO接口
 */
public interface AccountContractDao {

	void save(AccountContract accountContract);

	void delete(String id);

	void update(AccountContract accountContract);

	void updateContractStatus(String id);

	void updateContractStatustwo(String id);

	List<AccountContract> findList(Map<String, Object> map);

	AccountContract getById(String id);

	List<AccountContract> getAllAccountContractInfo();

	List<Map<String, Object>> findAllTitle();
	  public List<AccountContract> selectView(
				@Param("purchasenumtitle") String purchasenumtitle ,
				@Param("contracttitle") String contracttitle ,
				@Param("status") String status ,
				@Param("paging") PageUtil paging,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
		
		
		
		public int selectViewCount(
				@Param("purchasenumtitle") String purchasenumtitle ,
				@Param("contracttitle") String contracttitle ,
				@Param("status") String status ,
				@Param("beginDate") String beginDate,
				@Param("endDate") String endDate
		);
}