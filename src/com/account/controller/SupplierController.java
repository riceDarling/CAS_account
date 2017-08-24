package com.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.AccountSupplier;
import com.account.entity.Material;
import com.account.service.SupplierService;
import com.account.utils.PageBean;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;
import com.account.utils.pagebean.SupplierPage;

/**
 * 供应商Controller
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController  {
	
	@Autowired
	private SupplierService sService;
	
	/**
	 * 分页查询供应商信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="findList")
	public ResponseModel<SupplierPage> findList(SupplierPage sPage, HttpServletRequest req) {
		ResponseModel<SupplierPage> rm = new ResponseModel<SupplierPage>();
		try {
			List<AccountSupplier> list = sService.findList(sPage);
			sPage.setCurrentPage(PageBean.getCurrentPage(req));
			sPage.setUrl(PageBean.getUrl(req));
			sPage.setPageTotal(PageBean.getPageCount(req));
			sPage.setTotalCount(sService.findListCount(sPage));
			sPage.setList(list);
			rm.setSuccessMessage("操作成功", sPage);
		} catch (Exception e) {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 新增供应商信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="insert")
	public ResponseModel<String> insert(AccountSupplier Supplier) {
		ResponseModel<String> rm = new ResponseModel<String>();
		
		if (sService.insertSelective(Supplier) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键删除供应商信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="delete")
	public ResponseModel<String> delete(String id) {
		ResponseModel<String> rm = new ResponseModel<String>();

		if (sService.deleteByPrimaryKey(id) > 0) {
			rm.setSuccessMessage("操作成功", null);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	/**
	 * 根据主键查询供应商信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="selectById")
	public ResponseModel<AccountSupplier> selectById(String id) {
		ResponseModel<AccountSupplier> rm = new ResponseModel<AccountSupplier>();

		AccountSupplier mt = sService.selectByPrimaryKey(id);
		if (mt != null) {
			rm.setSuccessMessage("操作成功", mt);
		} else {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	/**
	 * 获取全部供应商
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="findAllList")
	public ResponseModel<List<AccountSupplier>> findAllList(HttpServletRequest req) {
		ResponseModel<List<AccountSupplier>> rm = new ResponseModel<List<AccountSupplier>>();
		try {
			List<AccountSupplier> li=sService.findAllList();
			rm.setSuccessMessage("操作成功", li);
		} catch (Exception e) {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String title, String checker,PageUtil paging , String startTime, String endTime) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountSupplier> list= sService.selectView( title, checker,  paging, startTime, endTime );
			int total = sService.selectViewCount( title, checker,  startTime, endTime );
			paging.setList( list );
			paging.setTotalCount(total);
			rm.setSuccessMessage("操作成功", paging);
		}catch( Exception e ){
			e.printStackTrace();
			rm.isErrorMsg("查询失败");
		}
		return rm;
	}
}