package com.account.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountPaymentDao;
import com.account.entity.AccountPayment;
import com.account.service.AccountPaymentService;

@Service
@Transactional
public class AccountPaymentServiceImpl implements AccountPaymentService {
	@Autowired
	private AccountPaymentDao accountPaymentDao;

	@Override
	public void save(AccountPayment accountPayment) {
		if (accountPayment.getId() != null&&accountPayment.getId().trim().length()>0) {
			accountPaymentDao.update(accountPayment);
		} else {
			accountPayment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountPaymentDao.save(accountPayment);
		}
	}

	@Override
	public void delete(String id) {
		accountPaymentDao.delete(id);
	}

	@Override
	public void update(AccountPayment accountPayment) {
		accountPaymentDao.update(accountPayment);
	}

	@Override
	public List<AccountPayment> findList(Map<String, Object> map) {
		return accountPaymentDao.findList(map);
	}

	@Override
	public AccountPayment getById(String id) {
		return accountPaymentDao.getById(id);
	}
}
