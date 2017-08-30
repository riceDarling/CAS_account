package com.account.entity;


import java.util.List;

import org.hibernate.validator.constraints.Length;

/**
 * 入库单主表
 */
public class AccountInput {
	
	private String id;
	private String inputnum;		// 入库单号
	private String arrivalnum;// 到货单号
	private String supplier;  //供应商名称
	private String suppliernum;  //供应商编码
	private String inputdate;		// 入库日期
	private String makeDate;//制单日期
	private String category;		// 入库类别
	private String maker;//制单人
	private String sumquantity;// 到货数量(总) 查询使用
	private String sumnum;// 入库数量(总) 查询使用
	private String summoney;// 总价(总) 查询使用
	private String beginDate;//开始日期
	private String endDate;//结束日期
	private String remarks;//备注信息
	private String delFlag="0";//是否删除
	List<AccountInputDetail> accountInputDetail;
	public AccountInput() {
		super();
	}

	

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



	@Length(min=1, max=37, message="入库单号长度必须介于 1 和 37 之间")
	public String getInputnum() {
		return inputnum;
	}

	public void setInputnum(String inputnum) {
		this.inputnum = inputnum;
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



	@Length(min=0, max=255, message="入库类别长度必须介于 0 和 255 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
	 * @return the makeDate
	 */
	public String getMakeDate() {
		return makeDate;
	}



	/**
	 * @param makeDate the makeDate to set
	 */
	public void setMakeDate(String makeDate) {
		this.makeDate = makeDate;
	}



	/**
	 * @return the maker
	 */
	public String getMaker() {
		return maker;
	}



	/**
	 * @param maker the maker to set
	 */
	public void setMaker(String maker) {
		this.maker = maker;
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
	 * @return the accountInputDetail
	 */
	public List<AccountInputDetail> getAccountInputDetail() {
		return accountInputDetail;
	}



	/**
	 * @param accountInputDetail the accountInputDetail to set
	 */
	public void setAccountInputDetail(List<AccountInputDetail> accountInputDetail) {
		this.accountInputDetail = accountInputDetail;
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
	 * @return the sumquantity
	 */
	public String getSumquantity() {
		return sumquantity;
	}



	/**
	 * @param sumquantity the sumquantity to set
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
	 * @param sumnum the sumnum to set
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
	 * @param summoney the summoney to set
	 */
	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}

	
}