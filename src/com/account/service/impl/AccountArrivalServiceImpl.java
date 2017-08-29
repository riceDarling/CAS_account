package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountArrivalDao;
import com.account.dao.AccountArrivalDetailDao;
import com.account.entity.AccountArrival;
import com.account.entity.AccountArrivalDetail;
import com.account.service.AccountArrivalService;
import com.account.utils.PageUtil;

@Service
@Transactional
public class AccountArrivalServiceImpl implements AccountArrivalService {
	@Autowired
	private AccountArrivalDao accountArrivalDao;
	@Autowired
	private AccountArrivalDetailDao accountArrivalDetailDao;

	@Override
	public void save(AccountArrival accountArrival) {
		AccountArrivalDetail accountArrivalDetail = new AccountArrivalDetail();
		int size = accountArrival.getAccountArrivalDetail().size();
		if (accountArrival.getId() != null && accountArrival.getId().trim().length() > 0) {
			accountArrivalDao.update(accountArrival);
			accountArrivalDetailDao.delete(accountArrival.getId());
			//int sumquantity = 0;
			//int sumnum = 0;
			for (int i = 0; i < size; i++) {
				accountArrivalDetail = accountArrival.getAccountArrivalDetail().get(i);
				accountArrivalDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountArrivalDetail.setParent_Id(accountArrival.getId());
				//sumquantity += accountArrivalDetail.getQuantity();
				//sumnum += accountArrivalDetail.getNum();
				accountArrivalDetailDao.save(accountArrivalDetail);
			}
			/*if (sumnum == sumquantity) {
				accountArrivalDao.updateStatus(accountArrival.getId());
			}*/
		} else {
			accountArrival.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			if(accountArrival.getArrivalDate()==null||accountArrival.getArrivalDate().trim().length()<=0)
			{
				accountArrival.setArrivalDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			if(accountArrival.getMakeDate()==null||accountArrival.getMakeDate().trim().length()<=0)
			{
				accountArrival.setMakeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			accountArrivalDao.save(accountArrival);
		/*	int sumquantity = 0;
			int sumnum = 0;*/
			for (int i = 0; i < size; i++) {
				accountArrivalDetail = accountArrival.getAccountArrivalDetail().get(i);
				accountArrivalDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				accountArrivalDetail.setParent_Id(accountArrival.getId());
				//sumquantity += accountArrivalDetail.getQuantity();
				//sumnum += accountArrivalDetail.getNum();
				accountArrivalDetailDao.save(accountArrivalDetail);
			}
		/*	if (sumnum == sumquantity) {
				accountArrivalDao.updateStatus(accountArrival.getId());
			}*/
		}
	}

	@Override
	public void delete(String id) {
		accountArrivalDao.delete(id);

	}

	@Override
	public void update(AccountArrival accountArrival) {
		accountArrivalDao.update(accountArrival);
	}

	@Override
	public List<AccountArrival> findList(Map<String, Object> map) {
		return accountArrivalDao.findList(map);
	}

	@Override
	public AccountArrival getById(String id) {
		return accountArrivalDao.getById(id);
	}

	@Override
	public List<AccountArrivalDetail> getByArrivalId(String id) {
		return accountArrivalDetailDao.getByArrivalId(id);
	}

	@Override
	public List<AccountArrival> findAllorderNum() {
		return accountArrivalDao.findAllorderNum();
	}

	@Override
	public List<AccountArrival> selectView(String supplier, String arrivalstatus, PageUtil paging, String beginDate, String endDate) {
		return accountArrivalDao.selectView(supplier, arrivalstatus, paging, beginDate, endDate);
	}
	@Override
	public int selectViewCount(String supplier, String arrivalstatus, String beginDate, String endDate) {
		return accountArrivalDao.selectViewCount(supplier, arrivalstatus, beginDate, endDate);
	}
	@Override
	public List<AccountArrival> findorderNum() {
		return accountArrivalDao.findorderNum();
	}

}
