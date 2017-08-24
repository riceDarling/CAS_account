package com.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountRejectDao;
import com.account.dao.AccountRejectDetailDao;
import com.account.entity.AccountReject;
import com.account.entity.AccountRejectDetail;
import com.account.entity.Admin;
import com.account.service.AccountRejectService;
import com.account.utils.FormatDateUtils;
import com.account.utils.PageUtil;
import com.account.utils.RandomUtils;
@Service
@Transactional
public class AccountRejectServiceImpl implements AccountRejectService {

	@Autowired
	private AccountRejectDao accountRejectDao;
	
	@Autowired
	private AccountRejectDetailDao accountRejectDetailDao;
	
	@Override
	public void save(AccountReject accountReject) {
		Subject subject = SecurityUtils.getSubject();
		Admin loginAdmin = (Admin) subject.getSession().getAttribute("loginAdmin");
		if(accountReject.getId()==null){
			accountReject.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			// 生成17位单据编号 09-20170203-00001
			String ordernum = "09-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();
			accountReject.setOrdernum(ordernum);
			accountReject.setCreateBy(loginAdmin.getId().toString());
			accountReject.setCreateDate(new Date());
			accountRejectDao.insertSelective(accountReject);
		}else{
			accountReject.setCreateBy(loginAdmin.getId().toString());
			accountReject.setUpdate(new Date());
			accountRejectDao.updateByPrimaryKeySelective(accountReject);
		}
		List<AccountRejectDetail> detailList = accountReject.getDetailList();
		for (AccountRejectDetail detail : detailList) {
			if(detail.getId()==null){
				detail.setParentId(accountReject.getId());
				detail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				detail.setCreateBy(loginAdmin.getId().toString());
				detail.setCreateDate(new Date());
				accountRejectDetailDao.insertSelective(detail);
			}else{
				detail.setCreateBy(loginAdmin.getId().toString());
				detail.setUpdate(new Date());
				accountRejectDetailDao.updateByPrimaryKeySelective(detail);
			}
		}
	}

	@Override
	public  List<AccountReject> list(AccountReject entity) {
		return accountRejectDao.list(entity);
	}

	@Override
	public AccountReject get(String accountRejectId) {
		AccountReject entity=accountRejectDao.selectByPrimaryKey(accountRejectId);
		List<AccountRejectDetail> detailList = accountRejectDetailDao.getByPid(accountRejectId);
		entity.setDetailList(detailList);
		return entity;
	}

	@Override
	public void delete(String accountRejectId) {
		accountRejectDao.delete(accountRejectId);
		
	}

	@Override
	public List<AccountReject> selectView(String title, String supplier,  PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountRejectDao.selectView(title, supplier, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String supplier,  String startTime, String endTime) {
		// TODO Auto-generated method stub
		return accountRejectDao.selectViewCount(title, supplier,  startTime, endTime);
	}

}
