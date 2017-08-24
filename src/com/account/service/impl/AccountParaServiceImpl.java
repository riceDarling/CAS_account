package com.account.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountParaMapper;
import com.account.entity.AccountPara;
import com.account.service.AccountParaService;

@Service
@Transactional
public class AccountParaServiceImpl implements AccountParaService {

	@Autowired
	private AccountParaMapper apDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return apDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AccountPara record) {
		return apDao.insertSelective(record);
	}

	@Override
	public AccountPara selectByPrimaryKey(Integer id) {
		return apDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountPara record) {
		return apDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AccountPara> findList() {
		return apDao.findList();
	}
}
