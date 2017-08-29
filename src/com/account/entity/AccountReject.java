package com.account.entity;

import java.util.List;

/**
 * 退货单
 * 
 */
public class AccountReject extends BaseModel {
	private String id;

	private String ordernum;

	private String title;

	private String suppliernum;// 供应商编号

	private String supplier;// 供应商名称,查询

	// private String contractnum;//合同编码,查询

	// private String office;//采购部门,查询

	private String rejectDate;// 退货时间

	private String maker;// 制单人

	private String arrivalnum;// 到货编码

	private String remarks;

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(String rejectDate) {
		this.rejectDate = rejectDate;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	private List<AccountRejectDetail> detailList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AccountRejectDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<AccountRejectDetail> detailList) {
		this.detailList = detailList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getSuppliernum() {
		return suppliernum;
	}

	public void setSuppliernum(String suppliernum) {
		this.suppliernum = suppliernum;
	}

	public String getArrivalnum() {
		return arrivalnum;
	}

	public void setArrivalnum(String arrivalnum) {
		this.arrivalnum = arrivalnum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}