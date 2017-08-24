package com.account.service;

import java.util.List;

import com.account.entity.AccountReject;
import com.account.entity.Material;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.MaterialPage;

public interface MaterialService {

	/**
	 * 根据主键删除物资
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 新增物资
     * @param record
     * @return
     */
    void insertSelective(Material record);

    /**
     * 根据主键查询物资
     * @param id
     * @return
     */
    Material selectByPrimaryKey(String id);

    /**
     * 修改物资信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Material record);

    /**
     * 分页查询全部物资
     * @return
     */
    List<Material> findList(MaterialPage mPage);
    
    /**
     * 分页总记录数
     * @param mPage
     * @return
     */
    int findListCount(MaterialPage mPage);
    
    public List<Material> findAllList();
    
    List<Material> selectView(String title, String checker,PageUtil paging, String startTime, String endTime);

	int selectViewCount(String title, String checker, String startTime, String endTime);
}
