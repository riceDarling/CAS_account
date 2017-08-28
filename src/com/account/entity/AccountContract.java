package com.account.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;


/**
 * 合同
 */
public class AccountContract {
	
	private String id;
	private String contractnum;		// 合同编号
	private String papercontract;//纸质合同编号
	private String title;		// 申购标题
	private String supplier;		// 供应商名称
	private String contracttitle;		// 合同名称
	private String purchasenum;		// 采购单号
	private String supplierNum;	//供应商编码
	private String supplieraccount;	//供应商账号
	private String openbank;	//开户行
	private String phone;	//联系电话
	private String address;	//地址
	private String maker; // 制单人
	private String createdate;//制单日期
	private String caigouname;		// 乙方
	private String file;//附件
	private Double summoney;//总金额
	private String status="0";		// 合同状态(0:未完成  1:完成)
	private String remarks;//备注信息
	private String beginDate;//开始日期
	private String endDate;//结束日期
	private String delFlag="0";//是否删除
	List<AccountContractDetail> accountContractDetail;
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

	public AccountContract() {
		super();
	}


	@Length(min=1, max=37, message="合同编号长度必须介于 1 和 37 之间")
	public String getContractnum() {
		return contractnum;
	}

	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}
	
	@Length(min=1, max=255, message="合同名称长度必须介于 1 和 255 之间")
	public String getContracttitle() {
		return contracttitle;
	}

	public void setContracttitle(String contracttitle) {
		this.contracttitle = contracttitle;
	}
	
	@Length(min=1, max=255, message="采购单号长度必须介于 1 和 255 之间")
	public String getPurchasenum() {
		return purchasenum;
	}

	public void setPurchasenum(String purchasenum) {
		this.purchasenum = purchasenum;
	}
	
	@Length(min=0, max=64, message="乙方长度必须介于 0 和 64 之间")
	public String getCaigouname() {
		return caigouname;
	}

	public void setCaigouname(String caigouname) {
		this.caigouname = caigouname;
	}
	
	@Length(min=0, max=1, message="合同状态(0:未完成  1:完成)长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
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
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the papercontract
	 */
	public String getPapercontract() {
		return papercontract;
	}

	/**
	 * @param papercontract the papercontract to set
	 */
	public void setPapercontract(String papercontract) {
		this.papercontract = papercontract;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the accountContractDetail
	 */
	public List<AccountContractDetail> getAccountContractDetail() {
		return accountContractDetail;
	}

	/**
	 * @param accountContractDetail the accountContractDetail to set
	 */
	public void setAccountContractDetail(List<AccountContractDetail> accountContractDetail) {
		this.accountContractDetail = accountContractDetail;
	}

	/**
	 * @return the summoney
	 */
	public Double getSummoney() {
		return summoney;
	}

	/**
	 * @param summoney the summoney to set
	 */
	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}

	/**
	 * @return the supplieraccount
	 */
	public String getSupplieraccount() {
		return supplieraccount;
	}

	/**
	 * @param supplieraccount the supplieraccount to set
	 */
	public void setSupplieraccount(String supplieraccount) {
		this.supplieraccount = supplieraccount;
	}

	/**
	 * @return the openbank
	 */
	public String getOpenbank() {
		return openbank;
	}

	/**
	 * @param openbank the openbank to set
	 */
	public void setOpenbank(String openbank) {
		this.openbank = openbank;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	
}