package com.account.entity;


public class AccountInputInfo {
	private String materialname;		// 物资名称
	private String size;		// 规格型号
	private String receivedquantity;		// 到货数量
	private String remarks;  //入库主表备注信息
	private String inputdate;		// 入库日期
	private String warehouse;		// 仓库
	private String location;		// 货位编码
	private String inputnum;		// 入库单号
	private String inspectionnum;		// 送检单号
	private String status;		// 送检状态
	private String quantity;		// 入库数量
	private String inputremarks;		// 入库子表备注
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getReceivedquantity() {
		return receivedquantity;
	}
	public void setReceivedquantity(String receivedquantity) {
		this.receivedquantity = receivedquantity;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getInputnum() {
		return inputnum;
	}
	public void setInputnum(String inputnum) {
		this.inputnum = inputnum;
	}
	
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getInputremarks() {
		return inputremarks;
	}
	public void setInputremarks(String inputremarks) {
		this.inputremarks = inputremarks;
	}
	/**
	 * @return the inputdate
	 */
	public String getInputdate() {
		return inputdate;
	}
	/**
	 * @param inputdate the inputdate to set
	 */
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public String getInspectionnum() {
		return inspectionnum;
	}
	public void setInspectionnum(String inspectionnum) {
		this.inspectionnum = inspectionnum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
