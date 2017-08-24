package com.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.account.entity.ParaInfo;
import com.account.entity.Warehouse;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.ParaInfoPage;

public interface ParaInfoMapper {
	/**
	 * 根据主键删除单位
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
	 * 新增单位
	 * @param id
	 * @return
	 */
    int insertSelective(ParaInfo record);

    /**
	 * 根据主键查询单位
	 * @param id
	 * @return
	 */
    ParaInfo selectByPrimaryKey(Integer id);

    /**
	 * 根据主键修改单位
	 * @param id
	 * @return
	 */
    int updateByPrimaryKeySelective(ParaInfo record);

    /**
     * 分页查询全部单位
     * @return
     */
    List<ParaInfo> findList(ParaInfoPage piPage);
    
    /**
     * 分页查询总记录数
     * @param piPage
     * @return
     */
    int findListCount(ParaInfoPage piPage);
    
    public List<ParaInfo> getParaInfoList(int pId);
    
    public List<ParaInfo> selectView(
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