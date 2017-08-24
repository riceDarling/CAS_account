package com.account.dao;

import java.util.List;

import com.account.entity.SupplierInput;
import com.account.utils.pagebean.SupplierInputPage;

public interface SupplierInputMapper {
	/**
	 * 根据主键删除供应商期初余额录入
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 新增供应商期初余额录入
     * @param record
     * @return
     */
    int insertSelective(SupplierInput record);

    /**
     * 根据主键查询供应商期初余额录入
     * @param id
     * @return
     */
    SupplierInput selectByPrimaryKey(String id);

    /**
     * 修改供应商期初余额录入信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SupplierInput record);

    /**
     * 分页查询全部供应商期初余额录入
     * @return
     */
    List<SupplierInput> findList(SupplierInputPage siPage);
    
    /**
     * 分页查询总记录数
     * @param sPage
     * @return
     */
    int findListCount(SupplierInputPage siPage);
}