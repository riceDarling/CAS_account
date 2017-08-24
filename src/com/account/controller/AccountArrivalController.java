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

import com.account.entity.AccountArrival;
import com.account.entity.AccountArrivalDetail;
import com.account.service.AccountArrivalService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;

/**
 * 到货单Controller
 */
@Controller
@RequestMapping("/accountArrivalController")
public class AccountArrivalController {
	@Autowired
	private AccountArrivalService accountArrivalService;

	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody AccountArrival accountArrival) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			accountArrivalService.save(accountArrival);
			rm.isSuccessMsg("", "提交成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	@ResponseBody
	@RequestMapping(value = "findList")
	public ResponseModel<List<AccountArrival>> findList(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", req.getParameter("supplier"));
		map.put("arrivalstatus", req.getParameter("arrivalstatus"));
		map.put("beginDate", req.getParameter("beginDate"));
		map.put("endDate", req.getParameter("endDate"));
		ResponseModel<List<AccountArrival>> rm = new ResponseModel<List<AccountArrival>>();
		try {
			List<AccountArrival> li = accountArrivalService.findList(map);
			rm.isSuccessMsg(li, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询到货主表详细信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public ResponseModel<AccountArrival> getById(HttpServletRequest req) {
		ResponseModel<AccountArrival> rm = new ResponseModel<AccountArrival>();
		try {
			String id = req.getParameter("id");
			AccountArrival accountArrival = accountArrivalService.getById(id);
			rm.isSuccessMsg(accountArrival, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询到货子表物资信息
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getByArrivalId")
	public ResponseModel<List<AccountArrivalDetail>> getByArrivalId(HttpServletRequest req) {
		ResponseModel<List<AccountArrivalDetail>> rm = new ResponseModel<List<AccountArrivalDetail>>();
		try {
			String id = req.getParameter("id");
			List<AccountArrivalDetail> accountArrivalDetail = accountArrivalService.getByArrivalId(id);
			rm.isSuccessMsg(accountArrivalDetail, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}

	/**
	 * 查询所有到货单号及标题
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findAllorderNum")
	public ResponseModel<List<AccountArrival>> findAllorderNum() {
		ResponseModel<List<AccountArrival>> rm = new ResponseModel<List<AccountArrival>>();
		try {
			List<AccountArrival> accountArrival = accountArrivalService.findAllorderNum();
			rm.isSuccessMsg(accountArrival, "成功");
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
			accountArrivalService.delete(id);
			rm.isSuccessMsg("", "删除成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String supplier,  String arrivalstatus, PageUtil paging , String beginDate, String endDate) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountArrival> list= accountArrivalService.selectView( supplier, arrivalstatus, paging, beginDate, endDate );
			int total = accountArrivalService.selectViewCount( supplier, arrivalstatus, beginDate, endDate );
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
