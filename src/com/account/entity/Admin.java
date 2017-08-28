package com.account.entity;

import java.util.Date;

public class Admin {
	private Integer id;

	private Integer pid;

	private String username;

	private String password;

	private Date starttime;

	private Date endtime;

	private Date logintime;

	private Boolean atype;

	private String userrole;

	private Integer menu_0;// 采购模块，操作权限

	private Integer menu_1;

	private Integer menu_2;

	private Integer menu_3;

	private Department d;// 部门

	public Department getD() {
		return d;
	}

	public void setD(Department d) {
		this.d = d;
	}

	public Integer getMenu_0() {
		return menu_0;
	}

	public void setMenu_0(Integer menu_0) {
		this.menu_0 = menu_0;
	}

	public Integer getMenu_1() {
		return menu_1;
	}

	public void setMenu_1(Integer menu_1) {
		this.menu_1 = menu_1;
	}

	public Integer getMenu_2() {
		return menu_2;
	}

	public void setMenu_2(Integer menu_2) {
		this.menu_2 = menu_2;
	}

	public Integer getMenu_3() {
		return menu_3;
	}

	public void setMenu_3(Integer menu_3) {
		this.menu_3 = menu_3;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Boolean getAtype() {
		return atype;
	}

	public void setAtype(Boolean atype) {
		this.atype = atype;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
}