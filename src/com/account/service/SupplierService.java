package com.account.service;

import java.util.List;

import com.account.entity.AccountSupplier;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.SupplierPage;

public interface SupplierService {

	/**
	 * 根据主键删除供应商
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 新增供应商
     * @param record
     * @return
     */
    int insertSelective(AccountSupplier record);

    /**
     * 根据主键查询供应商
     * @param id
     * @return
     */
    AccountSupplier selectByPrimaryKey(String id);

    /**
     * 修改供应商信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AccountSupplier record);

    /**
     * 分页查询全部供应商
     * @return
     */
    List<AccountSupplier> findList(SupplierPage sPage);
    
    /**
     * 分页查询总记录数
     * @param sPage
     * @return
     */
    int findListCount(SupplierPage sPage);
    
    List<AccountSupplier> findAllList();

	List<AccountSupplier> selectView(String title, String checker, PageUtil paging, String startTime, String endTime);

	int selectViewCount(String title, String checker, String startTime, String endTime);
}
