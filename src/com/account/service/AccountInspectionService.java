package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountInspection;
import com.account.entity.AccountInspectionDetail;

public interface AccountInspectionService {

	void save(AccountInspection accountInspection);

	void delete(String id);

	void update(AccountInspection accountInspection);

	AccountInspection getById(String id);

	List<AccountInspection> findList(Map<String, Object> map);

	List<Map<String,Object>> getArrivalNum();
	
	List<AccountInspectionDetail> getArrivalDetail(String pid);
	List<AccountInspectionDetail> getByInspectionId(String id);

}
