package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountPurchase;
import com.account.entity.AccountReject;
import com.account.utils.PageUtil;

public interface AccountRejectDao {
    int deleteByPrimaryKey(String id);

    int insert(AccountReject record);

    int insertSelective(AccountReject record);

    AccountReject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountReject record);

    int updateByPrimaryKey(AccountReject record);
    
    List<AccountReject> list(AccountReject entity);
    
   int delete(String id);
   
   public List<AccountReject> selectView(
			@Param("title") String title ,
			@Param("supplier") String supplier ,
			@Param("paging") PageUtil paging,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
	
	
	
	public int selectViewCount(
			@Param("title") String title ,
			@Param("supplier") String supplier ,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
}