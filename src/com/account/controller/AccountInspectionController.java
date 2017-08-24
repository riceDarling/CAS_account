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

import com.account.entity.AccountInspection;
import com.account.entity.AccountInspectionDetail;
import com.account.service.AccountInspectionService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;

/**
 * 送检单Controller
 */
@Controller
@RequestMapping("/accountInspectionController")
public class AccountInspectionController {
	@Autowired
	private AccountInspectionService accountInspectionService;

	@ResponseBody
	@RequestMapping(value = "findList")
	public ResponseModel<List<AccountInspection>> findList(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", req.getParameter("supplier"));
		map.put("status", req.getParameter("status"));
		map.put("beginDate", req.getParameter("beginDate"));
		map.put("endDate", req.getParameter("endDate"));
		ResponseModel<List<AccountInspection>> rm = new ResponseModel<List<AccountInspection>>();
		try {
			List<AccountInspection> li = accountInspectionService.findList(map);
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody AccountInspection accountInspection) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountInspectionService.save(accountInspection);
			rm.isSuccessMsg("", "提交成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	/**
	 * 查询送检主表详细信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public ResponseModel<AccountInspection> getById(HttpServletRequest req) {
		ResponseModel<AccountInspection> rm = new ResponseModel<AccountInspection>();
		try {
			String id = req.getParameter("id");
			AccountInspection accountInspection = accountInspectionService.getById(id);
			rm.isSuccessMsg(accountInspection, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询送检子表物资信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getByInspectionId")
	public ResponseModel<List<AccountInspectionDetail>> getByInspectionId(HttpServletRequest req) {
		ResponseModel<List<AccountInspectionDetail>> rm = new ResponseModel<List<AccountInspectionDetail>>();
		try {
			String id = req.getParameter("id");
			List<AccountInspectionDetail> accountInspectionDetail = accountInspectionService.getByInspectionId(id);
			rm.isSuccessMsg(accountInspectionDetail, "成功");
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
			accountInspectionService.delete(id);
			rm.isSuccessMsg("", "删除成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "getArrivalNum")
	public ResponseModel<List<Map<String, Object>>> getArrivalNum(HttpServletRequest req) {
		ResponseModel<List<Map<String, Object>>> rm = new ResponseModel<List<Map<String, Object>>>();
		try {
			List<Map<String, Object>> li=accountInspectionService.getArrivalNum();
			rm.isSuccessMsg(li, "获取送检单成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取送检单失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "getArrivalDetail")
	public ResponseModel<List<AccountInspectionDetail>> getArrivalDetail(String pid) {
		ResponseModel<List<AccountInspectionDetail>> rm = new ResponseModel<List<AccountInspectionDetail>>();
		try {
			List<AccountInspectionDetail> li=accountInspectionService.getArrivalDetail(pid);
			rm.isSuccessMsg(li, "获取送检单详细信息成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取送检单详细信息失败");
		}
		return rm;
	}
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String supplier, String status, PageUtil paging , String beginDate, String endDate) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountInspection> list= accountInspectionService.selectView( supplier, status, paging, beginDate, endDate );
			int total = accountInspectionService.selectViewCount( supplier,status, beginDate, endDate );
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