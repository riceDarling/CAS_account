package com.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountInquiryDao;
import com.account.dao.AccountInquiryDetailDao;
import com.account.entity.AccountInquiry;
import com.account.entity.AccountInquiryDetail;
import com.account.entity.Admin;
import com.account.service.AccountInquiryDetailService;

@Service
public class AccountInquiryDetailServiceImpl implements AccountInquiryDetailService {

	@Autowired
	private AccountInquiryDetailDao accountInquiryDetailDao;
	
	@Autowired
	private AccountInquiryDao accountInquiryDao;
	
	@Override
	public List<AccountInquiryDetail> selectInquiryDetailByOrdernum(String accountInquiryOrdernum) {
		
		return accountInquiryDetailDao.selectInquiryDetailByOrdernum(accountInquiryOrdernum);
	}

	public void save(AccountInquiryDetail entity) {
		entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		accountInquiryDetailDao.insert(entity);
	}

	@Override
	public List<AccountInquiryDetail> selectInquiryDetailEndByOrdernum(String accountInquiryOrdernum) {
		return accountInquiryDetailDao.selectInquiryDetailEndByOrdernum(accountInquiryOrdernum);
	}

	@Override
	public List<Map<String, Object>> selectAllmaterial(String accountInquiryOrdernum) {
		return accountInquiryDetailDao.selectAllmaterial(accountInquiryOrdernum);
	}

	@Override
	public List<AccountInquiryDetail> selectDetail(AccountInquiryDetail entity) {
		// TODO Auto-generated method stub
		return accountInquiryDetailDao.selectDetail(entity);
	}

	@Override
	public void saveDetail(AccountInquiry accountInquiry) {
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		accountInquiry.setStatus("examine");
		accountInquiry.setUpdate(new Date());
		accountInquiry.setUpdateBy(loginAdmin.getId().toString());
		accountInquiryDao.update(accountInquiry);
		List<AccountInquiryDetail> list=accountInquiry.getDetailList();
		for (AccountInquiryDetail entity : list) {
			accountInquiryDetailDao.update(entity);
		}
	}

	@Override
	public List<AccountInquiryDetail> getAccountSupplierByPurchasenum(AccountInquiryDetail accountInquiryDetail) {
		return accountInquiryDetailDao.getAccountSupplierByPurchasenum(accountInquiryDetail);
	}

	@Override
	public List<AccountInquiryDetail> getAccountSupplierByPurchasenumtitle(String purchasenumtitle) {
		return accountInquiryDetailDao.getAccountSupplierByPurchasenumtitle(purchasenumtitle);
	}

}
