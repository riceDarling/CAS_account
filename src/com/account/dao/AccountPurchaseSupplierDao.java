package com.account.dao;

import java.util.List;
import com.account.entity.AccountPurchaseSupplier;

/**
 * 订货供应商DAO接口
 * 
 */
public interface AccountPurchaseSupplierDao {
	
	public AccountPurchaseSupplier get(String id);

	public List<AccountPurchaseSupplier> findList(AccountPurchaseSupplier entity);

	public List<AccountPurchaseSupplier> findAllList(AccountPurchaseSupplier entity);

	public void insert(AccountPurchaseSupplier entity);

	public void update(AccountPurchaseSupplier entity);

	public void delete(String id);
	
	List<AccountPurchaseSupplier> getbyParentId(String parentId);

	void deleteByparentId(String parentId);
}