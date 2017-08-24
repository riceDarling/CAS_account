
package com.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountInquiry;
import com.account.entity.AccountRequisition;
import com.account.utils.PageUtil;


/**
 * 询价单DAO接口
 */
public interface AccountInquiryDao {
	public void commit1(String ordernum);

	public List<AccountInquiry> selectInquiryByDateAndStatus(AccountInquiry accountInquiry);
	
	public void setAccountInquiryStatus(AccountInquiry entity);
	
	public String getRequisitionId(String ordernum);
	
	AccountInquiry get(String id);
	
	List<AccountInquiry> findList(AccountInquiry entity);
	List<AccountInquiry> findAllList(AccountInquiry entity);
	
	void insert(AccountInquiry entity);
	void update(AccountInquiry entity);
	
	void delete(String id);
	
	void setInquiryStatusById(String id,String status);
	
	
	List<Map<String,Object>> selectAllTitle();
	
	public List<AccountInquiry> selectView(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("procInsId") String procInsId ,
			@Param("paging") PageUtil paging,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
	
	
	
	public int selectViewCount(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("procInsId") String procInsId ,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
}