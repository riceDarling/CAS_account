package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountContract;
import com.account.entity.AccountContractDetail;
import com.account.utils.PageUtil;

public interface AccountContractService {
	void save(AccountContract accountContract);

	void delete(String id);

	void update(AccountContract accountContract);

	void updateContractStatus(String id);

	void updateContractStatustwo(String id);

	List<AccountContract> findList(Map<String, Object> map);

	AccountContract getById(String id);

	List<AccountContract> getAllAccountContractInfo();
	List<AccountContractDetail> getByContractId(String id);

	List<Map<String, Object>> findAllTitle();

	
	List<AccountContract> selectView(String purchasenumtitle, String contracttitle, String status, PageUtil paging, String beginDate, String endDate);

	
	int selectViewCount(String purchasenumtitle, String contracttitle, String status, String beginDate, String endDate);
}
