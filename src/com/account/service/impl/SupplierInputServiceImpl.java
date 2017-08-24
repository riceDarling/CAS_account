package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.SupplierInputMapper;
import com.account.entity.SupplierInput;
import com.account.service.SupplierInputService;
import com.account.utils.GetUUID;
import com.account.utils.pagebean.SupplierInputPage;

@Service
@Transactional
public class SupplierInputServiceImpl implements SupplierInputService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SupplierInputMapper siDao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return siDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SupplierInput record) {
		if (record.getId() != null && !record.getId().equals("")) {
			record.setDelFlag("0");
			return siDao.updateByPrimaryKeySelective(record);
		} else { 
			record.setId(GetUUID.getOneUUID());
			record.setCreateDate(new Date());
			record.setDelFlag("0");
			return siDao.insertSelective(record);
		}
	}

	@Override
	public SupplierInput selectByPrimaryKey(String id) {
		return siDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SupplierInput record) {
		return siDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SupplierInput> findList(SupplierInputPage siPage) {
		List<SupplierInput> list = siDao.findList(siPage);
		for (SupplierInput si : list) {
			si.setBeginTime(sdf.format(si.getBeginDate()));
			si.setCreateTime(sdf.format(si.getCreateDate()));
		}
		return list;
	}

	@Override
	public int findListCount(SupplierInputPage siPage) {
		return siDao.findListCount(siPage);
	}

}
