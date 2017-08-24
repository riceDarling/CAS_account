package com.account.dao;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountInquiryDetail;

/**
 * 询价单子表DAO接口
 */
public interface AccountInquiryDetailDao {
	
	AccountInquiryDetail get(String inquirydetailId);
	
	List<AccountInquiryDetail> selectInquiryDetailByOrdernum(String ordernum);
	
	void insert(AccountInquiryDetail entity);
	
	void setInquiryDetailStateById(String id,String state);
	
	public List<AccountInquiryDetail> selectInquiryDetailEndByOrdernum(String ordernum);
	
	void updateByOrdernum(String ordernum);

	List<Map<String, Object>> selectAllmaterial(String accountInquiryOrdernum);

	List<AccountInquiryDetail> selectDetail(AccountInquiryDetail entity);

	void update(AccountInquiryDetail entity);
	public List<AccountInquiryDetail> getAccountSupplierByPurchasenum(AccountInquiryDetail accountInquiryDetail);

	public List<AccountInquiryDetail> getAccountSupplierByPurchasenumtitle(String purchasenumtitle);

}