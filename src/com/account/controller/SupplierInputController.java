package com.account.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.SupplierInput;
import com.account.service.SupplierInputService;
import com.account.utils.PageBean;
import com.account.utils.ResponseModel;
import com.account.utils.pagebean.SupplierInputPage;

/**
 * 供应商期初余额录入Controller
 */
@Controller
@RequestMapping("/supplierInput")
public class SupplierInputController  {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private SupplierInputService siService;
	
	/**
	 * 分页查询供应商期初余额录入信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="findList")
	public ResponseModel<SupplierInputPage> findList(SupplierInputPage siPage, HttpServletRequest req) {
		ResponseModel<SupplierInputPage> rm = new ResponseModel<SupplierInputPage>();
		try {
			List<SupplierInput> list = siService.findList(siPage);
			siPage.setCurrentPage(PageBean.getCurrentPage(req));
			siPage.setUrl(PageBean.getUrl(req));
			siPage.setPageTotal(PageBean.getPageCount(req));
			siPage.setTotalCount(siService.findListCount(siPage));
			siPage.setList(list);
			rm.setSuccessMessage("操作成功", siPage);
		} catch (Exception e) {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 新增供应商期初余额录入信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="insert")
	public ResponseModel<String> insert(SupplierInput SupplierInput) {
		ResponseModel<String> rm = new ResponseModel<String>();
		 
		if (siService.insertSelective(SupplierInput) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键删除供应商期初余额录入信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="delete")
	public ResponseModel<String> delete(String id) {
		ResponseModel<String> rm = new ResponseModel<String>();

		if (siService.deleteByPrimaryKey(id) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键查询供应商期初余额录入信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="selectById")
	public ResponseModel<SupplierInput> selectById(String id) {
		ResponseModel<SupplierInput> rm = new ResponseModel<SupplierInput>();

		SupplierInput mt = siService.selectByPrimaryKey(id);
		if (mt != null) {
			mt.setBeginTime(sdf.format(mt.getBeginDate()));
			rm.setSuccessMessage("操作成功", mt);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
}