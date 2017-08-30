package com.account.dao;

import java.util.List;

import com.account.entity.AccountInputDetail;

/**
 * 入库单子表DAO接口
 * 
 */
public interface AccountInputDetailDao {
	void save(AccountInputDetail accountInputDetail);
    void delete(String id);
	List<AccountInputDetail> getByInputId(String id);
}