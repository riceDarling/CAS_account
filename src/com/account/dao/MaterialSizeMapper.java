package com.account.dao;

import com.account.entity.MaterialSize;

public interface MaterialSizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialSize record);

    int insertSelective(MaterialSize record);

    MaterialSize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialSize record);

    int updateByPrimaryKey(MaterialSize record);
}