package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountInputDao;
import com.account.dao.AccountInputDetailDao;
import com.account.entity.AccountInput;
import com.account.entity.AccountInputDetail;
import com.account.service.AccountInputService;
import com.account.utils.PageUtil;

@Service
@Transactional
public class AccountInputServiceImpl implements AccountInputService {
	@Autowired
	private AccountInputDao accountInputDao;
	@Autowired
	private AccountInputDetailDao accountInputDetailDao;

	@Override
	public void save(AccountInput accountInput) {
		AccountInputDetail accountInputDetail = new AccountInputDetail();
		int size = accountInput.getAccountInputDetail().size();
		if (accountInput.getId() != null && accountInput.getId().trim().length() > 0) {
			accountInputDao.update(accountInput);
			accountInputDetailDao.delete(accountInput.getId());
			for (int i = 0; i < size; i++) {
				accountInputDetail = accountInput.getAccountInputDetail().get(i);
				accountInputDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountInputDetail.setParent_id(accountInput.getId());
				accountInputDetailDao.save(accountInputDetail);
			}
		} else {
			accountInput.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			if (accountInput.getInputdate() == null || accountInput.getInputdate().trim().length() <= 0) {
				accountInput.setInputdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			if (accountInput.getMakeDate() == null || accountInput.getMakeDate().trim().length() <= 0) {
				accountInput.setMakeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			accountInputDao.save(accountInput);
			for (int i = 0; i < size; i++) {
				accountInputDetail = accountInput.getAccountInputDetail().get(i);
				accountInputDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountInputDetail.setParent_id(accountInput.getId());
				accountInputDetailDao.save(accountInputDetail);
			}
		}
	}

	@Override
	public void delete(String id) {
		accountInputDao.delete(id);
	}

	@Override
	public void update(AccountInput accountInput) {
		accountInputDao.update(accountInput);
	}

	@Override
	public List<AccountInput> findList(Map<String, Object> map) {
		return accountInputDao.findList(map);
	}

	@Override
	public AccountInput getById(String id) {
		return accountInputDao.getById(id);
	}

	@Override
	public List<AccountInput> selectView(String supplier, PageUtil paging, String beginDate, String endDate) {
		return accountInputDao.selectView(supplier, paging, beginDate, endDate);
	}

	@Override
	public int selectViewCount(String supplier, String beginDate, String endDate) {
		return accountInputDao.selectViewCount(supplier, beginDate, endDate);
	}
	@Override
	public List<AccountInputDetail> getByInputId(String id) {
		return accountInputDetailDao.getByInputId(id);
	}
}
