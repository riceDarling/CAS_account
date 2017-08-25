package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountArrival;
import com.account.entity.AccountArrivalDetail;
import com.account.utils.PageUtil;

public interface AccountArrivalService {
	void save(AccountArrival accountArrival);

	void delete(String id);

	void update(AccountArrival accountArrival);

	List<AccountArrival> findList(Map<String, Object> map);

	AccountArrival getById(String id);

	List<AccountArrivalDetail> getByArrivalId(String id);

	public List<AccountArrival> findAllorderNum();
	public List<AccountArrival> findorderNum();
	List<AccountArrival> selectView(String supplier, String arrivalstatus,  PageUtil paging, String beginDate, String endDate);
	
	int selectViewCount(String supplier, String arrivalstatus, String beginDate, String endDate);

}
