package com.account.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.AccountRequisitionAct;
import com.account.entity.Admin;
import com.account.service.AccountRequisitionActService;
import com.account.utils.MD5Util;
import com.account.utils.PageUtil;
import com.account.utils.ResponseModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
@RequestMapping("/act")
public class ActTaskController {
	
	@Autowired
	private AccountRequisitionActService accountRequisitionActService;
	
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@ResponseBody
	@RequestMapping("todo")
	public ResponseModel<PageUtil<Map<String,Object>>> todoList(AccountRequisitionAct entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseModel<PageUtil<Map<String,Object>>> rm = new ResponseModel<PageUtil<Map<String,Object>>>();
		try {
			Subject subject = SecurityUtils.getSubject();
			Admin loginAdmin =(Admin) subject.getSession().getAttribute("loginAdmin");
			entity.setCheckerName(loginAdmin.getId().toString());
			entity.setPage(new PageUtil<Map<String,Object>>());
			entity.setState(0);
			accountRequisitionActService.findPage(entity);
			rm.isSuccessMsg(entity.getPage(),"获取待办任务成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取待办任务失败");
		}
		return rm;
	}
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "historic")
	public ResponseModel<PageUtil<Map<String,Object>>> historicList(AccountRequisitionAct entity,  HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResponseModel<PageUtil<Map<String,Object>>> rm = new ResponseModel<PageUtil<Map<String,Object>>>();
		try {
			Subject subject = SecurityUtils.getSubject();
			Admin loginAdmin =(Admin) subject.getSession().getAttribute("loginAdmin");
			entity.setCheckerName(loginAdmin.getId().toString());
			entity.setPage(new PageUtil<Map<String,Object>>());
			entity.setState(1);
			accountRequisitionActService.findPage(entity);
			rm.isSuccessMsg(entity.getPage(),"获取已办任务成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取已办任务失败");
		}
		return rm;
	}
	/**
	 * 撤回操作
	 * @param requisitionid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "revoke")
	public ResponseModel<String> revoke(String requisitionid) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try { 
			rm.isSuccessMsg(accountRequisitionActService.revoke(requisitionid),"处理成功");
		} catch (Exception e) {
			rm.isErrorMsg("撤回失败");
		
		}
		return rm;
	
	}
}
