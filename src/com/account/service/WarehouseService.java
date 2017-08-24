package com.account.service;

import java.util.List;

import com.account.entity.Warehouse;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.WarehousePage;

public interface WarehouseService {

	/**
	 * 根据主键删除仓库
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 新增仓库
     * @param record
     * @return
     */
    int insertSelective(Warehouse record);

    /**
     * 根据主键查询仓库
     * @param id
     * @return
     */
    Warehouse selectByPrimaryKey(String id);

    /**
     * 修改仓库信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Warehouse record);

    /**
     * 分页查询全部仓库
     * @return
     */
    List<Warehouse> findList(WarehousePage wPage);
    
    /**
     * 仓库分页总记录数
     * @param wPage
     * @return
     */
    int findListCount(WarehousePage wPage);

	List<Warehouse> selectView(String title, String checker, PageUtil paging, String startTime, String endTime);

	int selectViewCount(String title, String checker, String startTime, String endTime);
}
