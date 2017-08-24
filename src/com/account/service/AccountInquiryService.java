package com.account.service;

import java.util.List;
import java.util.Map;

import com.account.entity.AccountInquiry;
import com.account.entity.AccountRequisition;
import com.account.utils.PageUtil;

public interface AccountInquiryService {

	AccountInquiry getById(String id);

	void findPage(AccountInquiry accountInquiry);
	
	public void commit1(String ordernum);
	
	void detailupdata(String [] inquirydetailId,String checker);
	
	void saveAudit(AccountInquiry entity);

	List<Map<String, Object>> selectAllTitle();
	
	public List<AccountInquiry> selectView( String title, String checker, String procInsId, PageUtil paging , String startTime, String endTime );
	
	public int selectViewCount( String title, String checker, String procInsId , String startTime, String endTime );

	void saveEnd(String accountInquiryid);


}
