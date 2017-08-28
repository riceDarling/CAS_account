package com.account.entity;

/**
 * 发票
 */
public class AccountReceipt {

	private String id;
	private String suppliernum;// 供应商编码
	private String supplier; // 供应商名称
	private String billingdate; // 开票日期
	private String materialcode; // 物资编码
	private String materialname; // 物资名称
	private String size; // 规格型号
	private Double pricefee; // 含税单价 自动生成（保留两位）
	private Double pricenotfee; // 不含税单价 自动生成（保留两位）
	private int quantity; // 数量
	private Double moneyfee; // 含税金额
	private Double moneynotfee; // 不含税金额
	private Double taxrte; // 税率
	private Double tax; // 税款
	private String receiptnum; // 发票号码
	private String category; // 发票类别
	private String ordernum; // 单据编号(17位编码区分普票和专票)
	private String arrivalnum; // 到货单号
	private String maker; // 制单人
	private String auditor; // 审核人
	private String remarks;// 备注
	private String delFlag = "0";// 是否删除
	private String beginDate;// 开始日期
	private String endDate;// 结束日期

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
	 * @return the billingdate
	 */
	public String getBillingdate() {
		return billingdate;
	}

	/**
	 * @param billingdate
	 *            the billingdate to set
	 */
	public void setBillingdate(String billingdate) {
		this.billingdate = billingdate;
	}

	/**
	 * @return the materialcode
	 */
	public String getMaterialcode() {
		return materialcode;
	}

	/**
	 * @param materialcode the materialcode to set
	 */
	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	/**
	 * @return the materialname
	 */
	public String getMaterialname() {
		return materialname;
	}

	/**
	 * @param materialname
	 *            the materialname to set
	 */
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the pricefee
	 */
	public Double getPricefee() {
		return pricefee;
	}

	/**
	 * @param pricefee
	 *            the pricefee to set
	 */
	public void setPricefee(Double pricefee) {
		this.pricefee = pricefee;
	}

	/**
	 * @return the pricenotfee
	 */
	public Double getPricenotfee() {
		return pricenotfee;
	}

	/**
	 * @param pricenotfee
	 *            the pricenotfee to set
	 */
	public void setPricenotfee(Double pricenotfee) {
		this.pricenotfee = pricenotfee;
	}

	

	/**
	 * @return the moneyfee
	 */
	public Double getMoneyfee() {
		return moneyfee;
	}

	/**
	 * @param moneyfee
	 *            the moneyfee to set
	 */
	public void setMoneyfee(Double moneyfee) {
		this.moneyfee = moneyfee;
	}

	/**
	 * @return the moneynotfee
	 */
	public Double getMoneynotfee() {
		return moneynotfee;
	}

	/**
	 * @param moneynotfee
	 *            the moneynotfee to set
	 */
	public void setMoneynotfee(Double moneynotfee) {
		this.moneynotfee = moneynotfee;
	}

	/**
	 * @return the taxrte
	 */
	public Double getTaxrte() {
		return taxrte;
	}

	/**
	 * @param taxrte
	 *            the taxrte to set
	 */
	public void setTaxrte(Double taxrte) {
		this.taxrte = taxrte;
	}

	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}

	/**
	 * @return the receiptnum
	 */
	public String getReceiptnum() {
		return receiptnum;
	}

	/**
	 * @param receiptnum
	 *            the receiptnum to set
	 */
	public void setReceiptnum(String receiptnum) {
		this.receiptnum = receiptnum;
	}

	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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

	/**
	 * @return the auditor
	 */
	public String getAuditor() {
		return auditor;
	}

	/**
	 * @param auditor the auditor to set
	 */
	public void setAuditor(String auditor) {
		this.auditor = auditor;
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

}