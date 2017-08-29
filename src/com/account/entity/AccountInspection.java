package com.account.entity;

import java.util.List;

/**
 * 送检单主表
 */
public class AccountInspection {
	
	private String id;
	private String ordernum;		// 送检单号
	private String arrivalnum;		// 到货单编号
	private String supplier;  //供应商名称
	private String suppliernum;  //供应商编码
	private String inspectionman;		// 送检人
	private String checker;		// 检验部门
	private String inspectiondate;		// 送检日期
	private String examinationdate;		// 检验日期
	private String beginDate;//开始日期 查询使用
	private String endDate;//结束日期 查询使用
	private String status="0";	//入库状态 0:未入库 1:已入库
	private String remarks;//备注
	private String delFlag="0";//是否删除
	List<AccountInspectionDetail> accountInspectionDetail;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the ordernum
	 */
	public String getOrdernum() {
		return ordernum;
	}
	/**
	 * @param ordernum the ordernum to set
	 */
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	/**
	 * @return the arrivalnum
	 */
	public String getArrivalnum() {
		return arrivalnum;
	}
	/**
	 * @param arrivalnum the arrivalnum to set
	 */
	public void setArrivalnum(String arrivalnum) {
		this.arrivalnum = arrivalnum;
	}
	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	/**
	 * @return the suppliernum
	 */
	public String getSuppliernum() {
		return suppliernum;
	}
	/**
	 * @param suppliernum the suppliernum to set
	 */
	public void setSuppliernum(String suppliernum) {
		this.suppliernum = suppliernum;
	}
	/**
	 * @return the inspectionman
	 */
	public String getInspectionman() {
		return inspectionman;
	}
	/**
	 * @param inspectionman the inspectionman to set
	 */
	public void setInspectionman(String inspectionman) {
		this.inspectionman = inspectionman;
	}
	/**
	 * @return the checker
	 */
	public String getChecker() {
		return checker;
	}
	/**
	 * @param checker the checker to set
	 */
	public void setChecker(String checker) {
		this.checker = checker;
	}
	/**
	 * @return the inspectiondate
	 */
	public String getInspectiondate() {
		return inspectiondate;
	}
	/**
	 * @param inspectiondate the inspectiondate to set
	 */
	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}
	
	/**
	 * @return the examinationdate
	 */
	public String getExaminationdate() {
		return examinationdate;
	}
	/**
	 * @param examinationdate the examinationdate to set
	 */
	public void setExaminationdate(String examinationdate) {
		this.examinationdate = examinationdate;
	}
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		if(beginDate!=null&&!beginDate.equals("")) {
		beginDate=beginDate+" 00:00:00";}
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if(endDate!=null&&!endDate.equals("")) {
			endDate=endDate+" 23:59:59";}
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * @return the accountInspectionDetail
	 */
	public List<AccountInspectionDetail> getAccountInspectionDetail() {
		return accountInspectionDetail;
	}
	/**
	 * @param accountInspectionDetail the accountInspectionDetail to set
	 */
	public void setAccountInspectionDetail(List<AccountInspectionDetail> accountInspectionDetail) {
		this.accountInspectionDetail = accountInspectionDetail;
	}

}