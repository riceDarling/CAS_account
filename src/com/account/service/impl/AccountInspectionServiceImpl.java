package com.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountInspectionDao;
import com.account.dao.AccountInspectionDetailDao;
import com.account.entity.AccountInspection;
import com.account.entity.AccountInspectionDetail;
import com.account.service.AccountInspectionService;

@Service
@Transactional
public class AccountInspectionServiceImpl implements AccountInspectionService {
	@Autowired
	private AccountInspectionDao accountInspectionDao;
	@Autowired
	private AccountInspectionDetailDao accountInspectionDetailDao;

	@Override
	public void save(AccountInspection accountInspection) {
		int size = accountInspection.getAccountInspectionDetail().size();
		AccountInspectionDetail accountInspectionDetail = new AccountInspectionDetail();
		if (accountInspection.getId() != null && accountInspection.getId().trim().length() > 0) {
			accountInspectionDao.update(accountInspection);
			accountInspectionDetailDao.delete(accountInspection.getId());
			for (int i = 0; i < size; i++) {
				accountInspectionDetail = accountInspection.getAccountInspectionDetail().get(i);
				if (!accountInspectionDetail.getStatus().equals("0")&&accountInspectionDetail.getStatus().length()>0) {
					accountInspectionDao.upSatus(accountInspection.getId());
				}
				accountInspectionDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountInspectionDetail.setParent_Id(accountInspection.getId());
				accountInspectionDetailDao.add(accountInspectionDetail);
			}
		} else {
			accountInspection.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			accountInspectionDao.save(accountInspection);
			for (int i = 0; i < size; i++) {
				accountInspectionDetail = accountInspection.getAccountInspectionDetail().get(i);
				if (!accountInspectionDetail.getStatus().equals("0")&&accountInspectionDetail.getStatus().length()>0) {
					accountInspectionDao.upSatus(accountInspection.getId());
				}
				accountInspectionDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountInspectionDetail.setParent_Id(accountInspection.getId());
				accountInspectionDetailDao.add(accountInspectionDetail);
			}

		}
		
	
	

	}

	@Override
	public void delete(String id) {
		accountInspectionDao.delete(id);
	}

	@Override
	public void update(AccountInspection accountInspection) {
		accountInspectionDao.update(accountInspection);
	}


	@Override
	public AccountInspection getById(String id) {
		return accountInspectionDao.getById(id);
	}

	@Override
	public List<AccountInspection> findList(Map<String, Object> map) {
		return accountInspectionDao.findList(map);
	}

	@Override
	public List<Map<String, Object>> getArrivalNum() {
		return accountInspectionDao.getArrivalNum();
	}

	@Override
	public List<AccountInspectionDetail> getArrivalDetail(String pid) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("parent_Id", pid);
		map.put("status", "1");
		return accountInspectionDetailDao.findList(map);
	}

	@Override
	public List<AccountInspectionDetail> getByInspectionId(String id) {
		return accountInspectionDetailDao.getByInspectionId(id);
	}
}
