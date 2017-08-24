package com.account.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.AccountRequisition;
import com.account.service.AccountRequisitionActService;
import com.account.service.AccountRequisitionService;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;
import com.alibaba.fastjson.JSON;


/**
 * 申请单Controller
 * @author admin
 * @version 2017-07-24
 */
@Controller
@RequestMapping("/accountRequisition")
public class AccountRequisitionController {

	@Autowired
	private AccountRequisitionService accountRequisitionService;
	
	@Autowired
	private AccountRequisitionActService accountRequisitionActService;
	
	@ResponseBody
	@RequestMapping(value = "save")
	public ResponseModel<String> save(@RequestBody String data){
		ResponseModel<String> rm = new ResponseModel<String>();
		try { 
			AccountRequisition accountRequisition= JSON.parseObject(data, AccountRequisition.class);
			if("no".equals(accountRequisition.getConclusion())) {
				////删除申请单(修改页面单独处理，所有此处没有删除逻辑)
				rm.isSuccessMsg("","删除成功");
			}else {
				//保存申请单
				accountRequisitionService.save(accountRequisition);
				rm.isSuccessMsg("","提交成功");
			}
		} catch (Exception e) {
			rm.isErrorMsg("失败");
	
		}
		
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "formView")
	public ResponseModel<Map<String,Object>> formView(String requisitionid) {
		ResponseModel<Map<String,Object>> rm = new ResponseModel<Map<String,Object>>();
		try { 
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("accountRequisition", accountRequisitionService.get(requisitionid));
			map.put("detailMapList",accountRequisitionService.getDetailMapByparentid(requisitionid));
			map.put("actList",accountRequisitionActService.getbyRequisitionId(requisitionid));
			rm.isSuccessMsg(map,"获取申请单信息成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取申请单信息失败");
		
		}
		return rm;
	
	}
	
	@ResponseBody
	@RequestMapping(value = "saveAudit")
	public ResponseModel<String> saveAudit(AccountRequisition accountRequisition) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try { 
			accountRequisitionService.saveAudit(accountRequisition);
			//提交申请单
			rm.isSuccessMsg("","提交成功");
			
		} catch (Exception e) {
			rm.isErrorMsg("提交失败");
		}
		
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public ResponseModel<String> delete(String requisitionid) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try { 
			accountRequisitionService.delete(requisitionid);
			//提交申请单
			rm.isSuccessMsg("","删除成功");
			
		} catch (Exception e) {
			rm.isErrorMsg("删除失败");		
		}
		
		return rm;
	}
	
	@ResponseBody
	@RequestMapping("list")
	public ResponseModel<AccountRequisition> list(AccountRequisition entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel<AccountRequisition> rm = new ResponseModel<AccountRequisition>();
		try {
			entity.setPage(new PageUtil<AccountRequisition>());
			accountRequisitionService.findPage(entity);
			rm.isSuccessMsg(entity,"获取申购单列表成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取申购单列表失败");
		}
		return rm;
	}
	/**
	 * 获取采购标题
	 */
	@ResponseBody
	@RequestMapping(value = "getAccountPurchaseTitle")
	public ResponseModel<List<AccountRequisition>> getAccountPurchaseTitle() {
		ResponseModel<List<AccountRequisition>> rm = new ResponseModel<List<AccountRequisition>>();
		try {
			List<AccountRequisition> accountRequisition = accountRequisitionService.getAccountPurchaseTitle();
			rm.isSuccessMsg(accountRequisition, "成功");
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value = "selectView")
	public ResponseModel<PageUtil> selectView(String title, String checker, String procInsId, PageUtil paging , String startTime, String endTime) {
		ResponseModel<PageUtil> rm = new ResponseModel<PageUtil>();
		try{
			List<AccountRequisition> list= accountRequisitionService.selectView( title, checker, procInsId, paging, startTime, endTime );
			int total = accountRequisitionService.selectViewCount( title, checker, procInsId, startTime, endTime );
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