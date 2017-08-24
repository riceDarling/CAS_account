package com.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.dao.MaterialMapper;
import com.account.dao.MaterialSupplierMapper;
import com.account.entity.Material;
import com.account.entity.MaterialSupplier;
import com.account.service.MaterialService;
import com.account.utils.FormatDateUtils;
import com.account.utils.GetUUID;
import com.account.utils.PageUtil;
import com.account.utils.RandomUtils;
import com.account.utils.pagebean.MaterialPage;

@Service
public class MaterialServiceImpl implements MaterialService{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private MaterialMapper mDao;
	
	@Autowired
	MaterialSupplierMapper msDao;

	@Override
	public int deleteByPrimaryKey(String id) {
		return mDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insertSelective(Material record) {
		
		if (record.getId() != null && !record.getId().equals("")) {
			record.setDelFlag("0");
			mDao.updateByPrimaryKeySelective(record);
			msDao.deleteByMaterialNum(record.getMaterialNum());
			for (MaterialSupplier element : record.getMaterialSupplier()) {
				element.setMaterial(record.getMaterialNum());
				msDao.insertSelective(element);
			}
		} else {
			String UUID=GetUUID.getOneUUID();
			record.setId(UUID);
			// 生成17位单据编号 04-20170203-00001
			String ordernum = "25-" + FormatDateUtils.dateToString(new Date()) + "-" + RandomUtils.random();
			record.setMaterialNum(ordernum);
			record.setCreateDate(new Date());
			record.setDelFlag("0");
			mDao.insertSelective(record);
			for (MaterialSupplier element : record.getMaterialSupplier()) {
				element.setMaterial(ordernum);
				msDao.insertSelective(element);
			}
		}	
		
	
	}

	@Override
	public Material selectByPrimaryKey(String id) {
		Material material= mDao.selectByPrimaryKey(id);
		material.setMaterialSupplier(msDao.selectMaterialSupplierByMaterialNum(material.getMaterialNum()));
		return material;
	}

	@Override
	public int updateByPrimaryKeySelective(Material record) {
		return mDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Material> findList(MaterialPage mPage) {
		List<Material> list = mDao.findList(mPage);
		for (Material ml : list) {
			ml.setCreateTime(sdf.format(ml.getCreateDate()));
		}
		return list;
	}
	
	@Override
	public int findListCount(MaterialPage mPage) {
		return mDao.findListCount(mPage);
	}
	
	@Override
	public List<Material> findAllList() {
		return mDao.findAllList();
	}

	@Override
	public List<Material> selectView(String title, String checker, PageUtil paging, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return mDao.selectView(title, checker, paging, startTime, endTime);
	}

	@Override
	public int selectViewCount(String title, String checker, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return mDao.selectViewCount(title, checker, startTime, endTime);
	}
}
