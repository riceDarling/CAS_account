package com.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AdminDao;
import com.account.entity.Admin;
import com.account.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin getAdminByName(String name) {
		return adminDao.getAdminByName(name);
	}

	@Override
	public void insertSelective(Admin ia) {
		adminDao.insert(ia);
	}

	@Override
	public void update(Admin ia) {
		adminDao.updateByPrimaryKey(ia);
	}

	@Override
	public List<Admin> findList() {
		return adminDao.findList();
	}


}
