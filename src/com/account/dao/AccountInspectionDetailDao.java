package com.account.dao;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountInspectionDetail;

/**
 * 送检单子表DAO接口
 */
public interface AccountInspectionDetailDao {
	void add(AccountInspectionDetail accountInspectionDetail);

	void delete(String id);

	List<AccountInspectionDetail> findList(Map<String, Object> map);

	List<AccountInspectionDetail> getByInspectionId(String id);


}