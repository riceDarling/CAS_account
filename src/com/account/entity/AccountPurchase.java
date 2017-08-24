package com.account.entity;

import org.hibernate.validator.constraints.Length;

import com.account.utils.PageUtil;

import java.util.List;

/**
 * 订货单Entity
 * 
 * @author admin
 * @version 2017-07-26
 */
public class AccountPurchase extends BaseModel {

	private String id;
	private String title; // 标题
	private String ordernum; // 单据编号
	private String inquirynum; // 合同单号
	private String maker; // 制单人
	private String checker; // 审核人
	private String procInsId; // 流程节点状态
	private List<AccountPurchaseDetail> accountPurchaseDetailList; // 子表列表

	private List<AccountPurchaseSupplier> supplierList;// 供应商列表

	private String comment;// 流程过程中的意见

	private String conclusion;// 流程同意是否

	private String act_checker;// 当前节点办理人

	private PageUtil<AccountPurchase> page;

	private String supplierNum;// 供应商编号，查询使用

	private String supplier;// 供应商名称，查询使用

	private String contracttitle;// 合同名称

	public String getContracttitle() {
		return contracttitle;
	}

	public void setContracttitle(String contracttitle) {
		this.contracttitle = contracttitle;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public PageUtil<AccountPurchase> getPage() {
		return page;
	}

	public void setPage(PageUtil<AccountPurchase> page) {
		this.page = page;
	}

	public String getAct_checker() {
		return act_checker;
	}

	public void setAct_checker(String act_checker) {
		this.act_checker = act_checker;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Length(min = 1, max = 60, message = "意见长度必须介于 1 和 60 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public List<AccountPurchaseSupplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<AccountPurchaseSupplier> supplierList) {
		this.supplierList = supplierList;
	}

	@Length(min = 0, max = 255, message = "标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 1, max = 17, message = "单据编号长度必须介于 1 和 17 之间")
	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	@Length(min = 1, max = 37, message = "询价单号长度必须介于 1 和 37 之间")
	public String getInquirynum() {
		return inquirynum;
	}

	public void setInquirynum(String inquirynum) {
		this.inquirynum = inquirynum;
	}

	@Length(min = 1, max = 35, message = "制单人长度必须介于 1 和 35 之间")
	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Length(min = 1, max = 35, message = "审核人长度必须介于 1 和 35 之间")
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	@Length(min = 0, max = 64, message = "流程节点状态长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public List<AccountPurchaseDetail> getAccountPurchaseDetailList() {
		return accountPurchaseDetailList;
	}

	public void setAccountPurchaseDetailList(List<AccountPurchaseDetail> accountPurchaseDetailList) {
		this.accountPurchaseDetailList = accountPurchaseDetailList;
	}

}