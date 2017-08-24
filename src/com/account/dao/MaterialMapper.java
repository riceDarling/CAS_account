package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.entity.AccountReject;
import com.account.entity.Material;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.MaterialPage;

public interface MaterialMapper {
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
    int insertSelective(Material record);

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
    
    /**
     * 获取全部物资
     * @return
     */
    List<Material> findAllList();
    
    public List<Material> selectView(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("paging") PageUtil paging,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
	
	
	
	public int selectViewCount(
			@Param("title") String title ,
			@Param("checker") String checker ,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime
	);
  
}