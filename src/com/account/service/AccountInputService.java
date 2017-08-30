package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountInput;
import com.account.entity.AccountInputDetail;
import com.account.utils.PageUtil;

public interface AccountInputService {
	void save(AccountInput accountInput);

	void delete(String id);

	void update(AccountInput accountInput);

	List<AccountInput> findList(Map<String, Object> map);

	AccountInput getById(String id);
	List<AccountInputDetail> getByInputId(String id);
	List<AccountInput> selectView(String supplier, PageUtil paging, String beginDate, String endDate);

	int selectViewCount(String supplier, String beginDate, String endDate);

}
