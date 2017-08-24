package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.dao.WarehouseMapper;
import com.account.entity.Warehouse;
import com.account.service.WarehouseService;
import com.account.utils.GetUUID;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.WarehousePage;

@Service
public class WarehouseServiceImpl implements WarehouseService{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private WarehouseMapper wDao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return wDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Warehouse record) {
		if (record.getId() != null && !record.getId().equals("")) {
			record.setDelFlag("0");
			return wDao.updateByPrimaryKeySelective(record);
		} else {
			record.setId(GetUUID.getOneUUID());
			record.setCreateDate(new Date());
			record.setDelFlag("0");
			return wDao.insertSelective(record);
		}
	}

	@Override
	public Warehouse selectByPrimaryKey(String id) {
		return wDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Warehouse record) {
		return wDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Warehouse> findList(WarehousePage wPage) {
		List<Warehouse> list = wDao.findList(wPage);
		for (Warehouse wh : list) {
			wh.setCreateTime(sdf.format(wh.getCreateDate()));
		}
		return list;
	}

	@Override
	public int findListCount(WarehousePage wPage) {
		return wDao.findListCount(wPage);
	}

	@Override
	public List<Warehouse> selectView(String title, String checker, PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return wDao.selectView(title, checker, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String checker, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return wDao.selectViewCount(title, checker, startTime, endTime);
	}
	
}
