package com.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.entity.Admin;
import com.account.service.AdminService;
import com.account.utils.ResponseModel;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	/**
	 * 新增账号
	 * @param ia
	 */
	@ResponseBody
	@RequestMapping("insert")
	public ResponseModel<String> insertSelective(Admin admin, HttpServletRequest req, HttpServletResponse resp) {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			admin.setPassword(admin.getPassword());
			adminService.insertSelective(admin);
			rm.isSuccessMsg("","新增账号成功");
		} catch (Exception e) {
			rm.isErrorMsg("新增账号失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseModel<Admin> login(Admin admin,HttpServletRequest request,HttpServletResponse response){
		ResponseModel<Admin> rm=new ResponseModel<Admin>();
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				// 获取验证通过的账户信息
				admin= adminService.getAdminByName(admin.getUsername());
				// 管理员登陆成功后，信息保存在session中
				subject.getSession().setAttribute("loginAdmin", admin);
				rm.isSuccessMsg(admin, "登陆成功");
			} else {
				rm.isErrorMsg("账号和密码不匹配");
			}
		} catch (IncorrectCredentialsException e) {
			rm.isErrorMsg("账号和密码不匹配");
		} catch (ExcessiveAttemptsException e) {
			rm.isErrorMsg("登录失败次数过多,请10分钟后重试");
		} catch (LockedAccountException e) {
			rm.isErrorMsg("账号已被锁定");
		} catch (DisabledAccountException e) {
			rm.isErrorMsg("账号已被冻结");
		} catch (ExpiredCredentialsException e) {
			rm.isErrorMsg("账号已被冻结");
		} catch (UnknownAccountException e) {
			rm.isErrorMsg("账号和密码不匹配");
		} catch (UnauthorizedException e) {
			rm.isErrorMsg("账号和密码不匹配");
		} catch (AuthenticationException e) {
			rm.isErrorMsg("账号和密码不匹配");
		}catch (NullPointerException e) {
			rm.isErrorMsg("账号和密码不匹配");
		}
		return rm;
	}
	
	/**
	 * 退出登录
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exit")
	public ResponseModel<String> exit(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResponseModel<String> rm = new ResponseModel<String>();
		try {
			// 销毁session
			request.getSession().invalidate();
			rm.setSuccessMessage("退出成功", null);
		} catch (Exception e) {
			rm.setErrorMessage("操作失败", null);
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping("getLoginAdmin")
	public ResponseModel<Admin> getLoginAdmin(HttpServletRequest req, HttpServletResponse resp) {
		ResponseModel<Admin> rm = new ResponseModel<Admin>();
		try {
			Subject subject = SecurityUtils.getSubject();
			Admin loginAdmin =(Admin) subject.getSession().getAttribute("loginAdmin");
			//System.out.println(loginAdmin.getUsername());
			if(loginAdmin!=null){
				rm.isSuccessMsg(loginAdmin, "成功");
			}else{
				rm.isErrorMsg("失败");
			}
			
		} catch (Exception e) {
			rm.isErrorMsg("失败");
		}
		return rm;
	}
	
	@ResponseBody
	@RequestMapping("getAdminList")
	public ResponseModel<List<Admin>> getAdminList(HttpServletRequest req, HttpServletResponse resp) {
		ResponseModel<List<Admin>> rm = new ResponseModel<List<Admin>>();
		try {	
			List<Admin> list=adminService.findList();
			rm.isSuccessMsg(list, "获取用户列表成功");
		} catch (Exception e) {
			rm.isErrorMsg("获取用户列表成功");
		}
		return rm;
	}
	
	
}
