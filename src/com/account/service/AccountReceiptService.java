package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountReceipt;
import com.account.utils.PageUtil;

public interface AccountReceiptService {
	void save(AccountReceipt accountReceipt);

	void delete(String id);

	void update(AccountReceipt accountReceipt);

	List<AccountReceipt> findList(Map<String, Object> map);

	AccountReceipt getById(String id);

	List<AccountReceipt> selectView(String supplier, PageUtil paging, String beginDate, String endDate);

	int selectViewCount(String supplier,String beginDate, String endDate);

}
