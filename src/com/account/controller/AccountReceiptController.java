package com.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.AccountReceipt;
import com.account.service.AccountReceiptService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;

/**
 * 发票Controller
 * 
 */
@Controller
@RequestMapping("/accountReceiptController")
public class AccountReceiptController {
	@Autowired
	private AccountReceiptService accountReceiptService;

	@ResponseBody
	@RequestMapping(value = "findList")
	public ResponseModel<List<AccountReceipt>> findList(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", req.getParameter("supplier"));
		map.put("beginDate", req.getParameter("beginDate"));
		map.put("endDate", req.getParameter("endDate"));
		ResponseModel<List<AccountReceipt>> rm = new ResponseModel<List<AccountReceipt>>();
		try {
			List<AccountReceipt> li = accountReceiptService.findList(map);
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody AccountReceipt accountReceipt) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountReceiptService.save(accountReceipt);
			rm.isSuccessMsg("", "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "getById")
	public ResponseModel<AccountReceipt> getById(HttpServletRequest req) {
		ResponseModel<AccountReceipt> rm = new ResponseModel<AccountReceipt>();
		try {
			String id = req.getParameter("id");
			AccountReceipt accountReceipt = accountReceiptService.getById(id);
			rm.isSuccessMsg(accountReceipt, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public ResponseModel<String> delete(HttpServletRequest req) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			String id = req.getParameter("id");
			accountReceiptService.delete(id);
			rm.isSuccessMsg("", "删除成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String supplier, PageUtil paging , String beginDate, String endDate) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountReceipt> list= accountReceiptService.selectView( supplier, paging, beginDate, endDate );
			int total = accountReceiptService.selectViewCount( supplier, beginDate, endDate );
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
