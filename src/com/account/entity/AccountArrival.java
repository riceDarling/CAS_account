package com.account.entity;

import java.util.List;

/**
 * 到货单
 */
public class AccountArrival {

	private String id;
	private String ordernum;// 订货单号
	private String arrivalnum;// 到货单号
	private String supplier; // 供应商名称
	private String suppliernum; // 供应商编号
	private String arrivalDate;// 到货日期
	private String remarks; // 备注信息
	private String arrivalstatus = "1";// 1:未送检 2:已送检
	private String delFlag = "0";// 是否删除
	private String sumquantity;// 订货数量(总)
	private String title;// 标题 查询使用
	private String sumnum;// 到货数量(总) 查询使用
	private String summoney;// 总价(总) 查询使用
	private String beginDate;// 开始日期 查询使用
	private String endDate;// 结束日期 查询使用
	List<AccountArrivalDetail> accountArrivalDetail;// 合同子表信息

	private String maker; // 制单人
	private String makeDate;// 制单时间

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param ordernum
	 *            the ordernum to set
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
	 * @param arrivalnum
	 *            the arrivalnum to set
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
	 * @param supplier
	 *            the supplier to set
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
	 * @param suppliernum
	 *            the suppliernum to set
	 */
	public void setSuppliernum(String suppliernum) {
		this.suppliernum = suppliernum;
	}

	/**
	 * @return the sumquantity
	 */
	public String getSumquantity() {
		return sumquantity;
	}

	/**
	 * @param sumquantity
	 *            the sumquantity to set
	 */
	public void setSumquantity(String sumquantity) {
		this.sumquantity = sumquantity;
	}

	/**
	 * @return the sumnum
	 */
	public String getSumnum() {
		return sumnum;
	}

	/**
	 * @param sumnum
	 *            the sumnum to set
	 */
	public void setSumnum(String sumnum) {
		this.sumnum = sumnum;
	}

	/**
	 * @return the summoney
	 */
	public String getSummoney() {
		return summoney;
	}

	/**
	 * @param summoney
	 *            the summoney to set
	 */
	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}

	/**
	 * @return the arrivalDate
	 */
	public String getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		if (beginDate != null && !beginDate.equals("")) {
			beginDate = beginDate + " 00:00:00";
		}
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate != null && !endDate.equals("")) {
			endDate = endDate + " 23:59:59";
		}
		this.endDate = endDate;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the arrivalstatus
	 */
	public String getArrivalstatus() {
		return arrivalstatus;
	}

	/**
	 * @param arrivalstatus
	 *            the arrivalstatus to set
	 */
	public void setArrivalstatus(String arrivalstatus) {
		this.arrivalstatus = arrivalstatus;
	}

	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag
	 *            the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the accountArrivalDetail
	 */
	public List<AccountArrivalDetail> getAccountArrivalDetail() {
		return accountArrivalDetail;
	}

	/**
	 * @param accountArrivalDetail
	 *            the accountArrivalDetail to set
	 */
	public void setAccountArrivalDetail(List<AccountArrivalDetail> accountArrivalDetail) {
		this.accountArrivalDetail = accountArrivalDetail;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}