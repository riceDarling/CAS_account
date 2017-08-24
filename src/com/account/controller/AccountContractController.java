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

import com.account.entity.AccountContract;
import com.account.entity.AccountContractDetail;
import com.account.service.AccountContractService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;

/**
 * 合同Controller
 */
@Controller
@RequestMapping("/accountContractController")
public class AccountContractController {

	@Autowired
	private AccountContractService accountContractService;

	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody AccountContract accountContract) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountContractService.save(accountContract);
			rm.isSuccessMsg("", "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "findList")
	public ResponseModel<List<AccountContract>> findList(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchasenumtitle", req.getParameter("purchasenumtitle"));
		map.put("contracttitle", req.getParameter("contracttitle"));
		map.put("status", req.getParameter("status"));
		map.put("beginDate", req.getParameter("beginDate"));
		map.put("endDate", req.getParameter("endDate"));
		ResponseModel<List<AccountContract>> rm = new ResponseModel<List<AccountContract>>();
		try {
			List<AccountContract> li = accountContractService.findList(map);
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	/**
	 * 查询合同主表信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public ResponseModel<AccountContract> getById(HttpServletRequest req) {
		ResponseModel<AccountContract> rm = new ResponseModel<AccountContract>();
		try {
			String id = req.getParameter("id");
			AccountContract accountContract = accountContractService.getById(id);
			rm.isSuccessMsg(accountContract, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	/**
	 * 查询子表物资信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getByContractId")
	public ResponseModel<List<AccountContractDetail>> getByContractId(HttpServletRequest req) {
		ResponseModel<List<AccountContractDetail>> rm = new ResponseModel<List<AccountContractDetail>>();
		try {
			String id = req.getParameter("id");
			List<AccountContractDetail> accountContractDetail = accountContractService.getByContractId(id);
			rm.isSuccessMsg(accountContractDetail, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public ResponseModel<String> delete(String id) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountContractService.delete(id);
			rm.isSuccessMsg("", "删除成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	/**
	 * 获取合同标题列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findAllTitle")
	public ResponseModel<List<Map<String,Object>>> findAllTitle() {
		ResponseModel<List<Map<String,Object>>> rm = new ResponseModel<List<Map<String,Object>>>();
		try {
			List<Map<String,Object>> li = accountContractService.findAllTitle();
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String purchasenumtitle, String contracttitle, String status, PageUtil paging , String beginDate, String endDate) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountContract> list= accountContractService.selectView( purchasenumtitle, contracttitle, status, paging, beginDate, endDate );
			int total = accountContractService.selectViewCount( purchasenumtitle, contracttitle, status, beginDate, endDate );
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