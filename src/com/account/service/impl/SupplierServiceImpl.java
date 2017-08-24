package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.dao.SupplierMapper;
import com.account.entity.AccountSupplier;
import com.account.service.SupplierService;
import com.account.utils.GetUUID;
import com.account.utils.PageUtil;
import com.account.utils.pagebean.SupplierPage;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SupplierMapper sDao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return sDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AccountSupplier record) {
		if (record.getId() != null && !record.getId().equals("")) {
			record.setDelFlag("0");
			return sDao.updateByPrimaryKeySelective(record);
		} else {
			record.setId(GetUUID.getOneUUID());
			record.setCreateDate(new Date());
			record.setDelFlag("0");
			return sDao.insertSelective(record);
		}
	}

	@Override
	public AccountSupplier selectByPrimaryKey(String id) {
		return sDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountSupplier record) {
		return sDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AccountSupplier> findList(SupplierPage sPage) {
		List<AccountSupplier> list = sDao.findList(sPage);
		for (AccountSupplier as : list) {
			as.setCreateTime(sdf.format(as.getCreateDate()));
		}
		return list;
	}
	
	@Override
	public int findListCount(SupplierPage sPage) {
		return sDao.findListCount(sPage);
	}

	@Override
	public List<AccountSupplier> findAllList() {
		// TODO Auto-generated method stub
		return sDao.findAllList();
	}

	@Override
	public List<AccountSupplier> selectView(String title, String checker, PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return sDao.selectView(title, checker, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String checker, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return sDao.selectViewCount(title, checker, startTime, endTime);
	}
	
}
