package com.account.service;

import java.util.List;

import com.account.entity.AccountReject;
import com.account.utils.PageUtil;

public interface AccountRejectService {

	void save(AccountReject accountReject);

	 List<AccountReject> list(AccountReject entity);

	AccountReject get(String accountRejectId);

	void delete(String accountRejectId);

	List<AccountReject> selectView(String title, String supplier,  PageUtil paging, String startTime, String endTime);

	int selectViewCount(String title, String supplier, String startTime, String endTime);

}
