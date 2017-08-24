package com.account.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.dao.AccountContractDao;
import com.account.dao.AccountContractDetailDao;
import com.account.entity.AccountContract;
import com.account.entity.AccountContractDetail;
import com.account.service.AccountContractService;
import com.account.utils.PageUtil;

@Service
public class AccountContractServiceImpl implements AccountContractService {
	@Autowired
	private AccountContractDao accountContractDao;
	@Autowired
	private AccountContractDetailDao accountContractDetailDao;

	@Override
	public void save(AccountContract accountContract) {
		AccountContractDetail accountContractDetail = new AccountContractDetail();
		int size = accountContract.getAccountContractDetail().size();
		if (accountContract.getId() != null && accountContract.getId().trim().length() > 0) {
			accountContractDao.update(accountContract);
			accountContractDetailDao.delete(accountContract.getId());
			for (int i = 0; i < size; i++) {
				accountContractDetail = accountContract.getAccountContractDetail().get(i);
				accountContractDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountContractDetail.setParent_Id(accountContract.getId());
				accountContractDetailDao.save(accountContractDetail);
			}
		} else {
			accountContract.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountContractDao.save(accountContract);
			for (int i = 0; i < size; i++) {
				accountContractDetail = accountContract.getAccountContractDetail().get(i);
				accountContractDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountContractDetail.setParent_Id(accountContract.getId());
				accountContractDetailDao.save(accountContractDetail);
			}

		}

	}

	@Override
	public void delete(String id) {
		accountContractDao.delete(id);
	}

	@Override
	public List<AccountContract> findList(Map<String, Object> map) {
		return accountContractDao.findList(map);
	}

	@Override
	public void updateContractStatus(String id) {
		accountContractDao.updateContractStatus(id);
	}

	@Override
	public void updateContractStatustwo(String id) {
		accountContractDao.updateContractStatustwo(id);
	}

	@Override
	public List<AccountContract> getAllAccountContractInfo() {
		return accountContractDao.getAllAccountContractInfo();
	}

	@Override
	public AccountContract getById(String id) {
		return accountContractDao.getById(id);
	}

	@Override
	public void update(AccountContract accountContract) {
		accountContractDao.update(accountContract);
	}
	@Override
	public List<AccountContractDetail> getByContractId(String id) {
		return accountContractDetailDao.getByContractId(id);
	}

	@Override
	public List<Map<String, Object>> findAllTitle() {
		return accountContractDao.findAllTitle();
	}

	@Override
	public List<AccountContract> selectView(String title, String checker, String procInsId, PageUtil paging, String beginDate, String endDate) {
		return accountContractDao.selectView(title, checker, procInsId, paging, beginDate, endDate);
	}

	@Override
	public int selectViewCount(String title, String checker, String procInsId, String beginDate, String endDate) {
		return accountContractDao.selectViewCount(title, checker, procInsId, beginDate, endDate);
	}

}
