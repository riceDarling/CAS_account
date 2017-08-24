package com.account.service.impl;

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
import com.account.entity.AccountInputInfo;
import com.account.service.AccountInputService;

@Service
@Transactional
public class AccountInputServiceImpl implements AccountInputService {
	@Autowired
	private AccountInputDao accountInputDao;
	@Autowired
	private AccountInputDetailDao accountInputDetailDao;

	@Override
	public List<AccountInputInfo> getAccountInputInfo() {
		return accountInputDao.getAccountInputInfo();
	}

	@Override
	public List<AccountInputInfo> getAccountInputListInfoBy(String id) {
		return accountInputDao.getAccountInputListInfoBy(id);
	}

	@Override
	public int getAccountInput(String Inspectionnum) {
		return accountInputDao.getAccountInput(Inspectionnum);
	}

	@Override
	public void updateStatus(String ordernum) {
		accountInputDao.updateStatus(ordernum);
	}

	@Override
	public void updateInspectionStatus(String ordernum) {
		accountInputDao.updateInspectionStatus(ordernum);
	}

	@Override
	public void save(AccountInput accountInput) {
		accountInput.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		accountInputDao.save(accountInput);
		accountInputDao.updateStatus(accountInput.getInspectionnum());
		int size = accountInput.getAccountInputDetail().size();
		AccountInputDetail accountInputDetail = new AccountInputDetail();
		for (int i = 0; i < size; i++) {
			accountInputDetail = accountInput.getAccountInputDetail().get(i);
			accountInputDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountInputDetail.setParent_id(accountInput.getInputnum());
			accountInputDetailDao.save(accountInputDetail);
		}
	}

	@Override
	public void delete(String id) {
		accountInputDao.delete(id);
	}

	@Override
	public void update(String id) {
		accountInputDao.update(id);
	}

	@Override
	public List<AccountInput> findList(Map<String, Object> map) {
		return accountInputDao.findList(map);
	}

	@Override
	public AccountInput getById(String id) {
		return accountInputDao.getById(id);
	}

}
