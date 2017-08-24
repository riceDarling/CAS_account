package com.account.service;

import java.util.List;

import com.account.entity.ParaInfo;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.ParaInfoPage;

public interface ParaInfoService {	
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

	List<ParaInfo> selectView(String title, String checker, PageUtil paging, String startTime, String endTime);

	int selectViewCount(String title, String checker, String startTime, String endTime);
    
}
