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

import com.account.entity.AccountInput;
import com.account.entity.AccountInputDetail;
import com.account.service.AccountInputService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;

/**
 * 入库单Controller
 */
@Controller
@RequestMapping("/accountInputController")
public class AccountInputController {
	@Autowired
	private AccountInputService accountInputService;

	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody AccountInput accountInput) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountInputService.save(accountInput);
			rm.isSuccessMsg("", "提交成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "findList")
	public ResponseModel<List<AccountInput>> findList(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", req.getParameter("supplier"));
		map.put("beginDate", req.getParameter("beginDate"));
		map.put("endDate", req.getParameter("endDate"));
		ResponseModel<List<AccountInput>> rm = new ResponseModel<List<AccountInput>>();
		try {
			List<AccountInput> li = accountInputService.findList(map);
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询入库主表详细信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public ResponseModel<AccountInput> getById(HttpServletRequest req) {
		ResponseModel<AccountInput> rm = new ResponseModel<AccountInput>();
		try {
			String id = req.getParameter("id");
			AccountInput accountInput = accountInputService.getById(id);
			rm.isSuccessMsg(accountInput, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询入库子表物资信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getByInputId")
	public ResponseModel<List<AccountInputDetail>> getByInputId(HttpServletRequest req) {
		ResponseModel<List<AccountInputDetail>> rm = new ResponseModel<List<AccountInputDetail>>();
		try {
			String id = req.getParameter("id");
			List<AccountInputDetail> accountInputDetail = accountInputService.getByInputId(id);
			rm.isSuccessMsg(accountInputDetail, "成功");
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
			accountInputService.delete(id);
			rm.isSuccessMsg("", "删除成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String supplier, PageUtil paging, String beginDate, String endDate) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try {
			List<AccountInput> list = accountInputService.selectView(supplier, paging, beginDate, endDate);
			int total = accountInputService.selectViewCount(supplier, beginDate, endDate);
			paging.setList(list);
			paging.setTotalCount(total);
			rm.setSuccessMessage("操作成功", paging);
		} catch (Exception e) {
			e.printStackTrace();
			rm.isErrorMsg("查询失败");
		}
		return rm;
	}

}
