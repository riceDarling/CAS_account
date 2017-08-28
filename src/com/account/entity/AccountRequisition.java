package com.account.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.account.utils.PageUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 申请单Entity
 * 
 * @author admin
 * @version 2017-07-24
 */
public class AccountRequisition extends BaseModel {

	private String id;
	private String ordernum; // 单据编号
	private String title; // 标题
	private Date receivedate; // 期望到货日期
	private String reason; // 申请原因
	private String office; // 申请部门
	private String procInsId; // 流程节点状态
	private String checker; // 审核人
	private List<AccountRequisitionDetail> accountRequisitionDetailList; // 子表列表
	private String requisitionDate;// 申购日期
	private String maker;// 制单人

	private String inquiry;// 询价人

	PageUtil<AccountRequisition> page;

	private String act_checker;// 当前节点办理人

	public String getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getAct_checker() {
		return act_checker;
	}

	public void setAct_checker(String act_checker) {
		this.act_checker = act_checker;
	}

	public PageUtil<AccountRequisition> getPage() {
		return page;
	}

	public void setPage(PageUtil<AccountRequisition> page) {
		this.page = page;
	}

	public String getInquiry() {
		return inquiry;
	}

	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}

	private String comment;// 流程过程中的意见

	private String conclusion;// 流程同意是否

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Length(min = 1, max = 60, message = "意见长度必须介于 1 和 60 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AccountRequisition() {
		super();
	}

	@Length(min = 1, max = 17, message = "单据编号长度必须介于 1 和 17 之间")
	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	@Length(min = 0, max = 60, message = "标题长度必须介于 0 和 60 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "期望到货日期不能为空")
	public Date getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}

	@Length(min = 0, max = 255, message = "申请原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Length(min = 0, max = 64, message = "流程节点状态长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	@Length(min = 0, max = 35, message = "审核人长度必须介于 0 和 35 之间")
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public List<AccountRequisitionDetail> getAccountRequisitionDetailList() {
		return accountRequisitionDetailList;
	}

	public void setAccountRequisitionDetailList(List<AccountRequisitionDetail> accountRequisitionDetailList) {
		this.accountRequisitionDetailList = accountRequisitionDetailList;
	}
}