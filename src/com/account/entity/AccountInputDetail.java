package com.account.entity;

/**
 * 入库单子表
 */
public class AccountInputDetail{
	
	private String id;
	private String parent_id;		// 主表id
	private String warehouse;		// 仓库
	private String location;		// 货位编码
	private String materialcode;//物资编码
	private String materialname;		// 物资名称
	private String size;			//规格型号
	//private String ingredient;		// 成分含量
	private String unitname; // 计量单位名称
	private Double unitprice;		// 单价
	private Double money;		// 总价
	private int quantity;		// 到货数量
	private int num;		// 入库数量
	private String remarks;//备注信息
	private String delFlag="0";//是否删除
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
	 * @return the parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	/**
	 * @return the warehouse
	 */
	public String getWarehouse() {
		return warehouse;
	}
	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @param materialname the materialname to set
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
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the unitname
	 */
	public String getUnitname() {
		return unitname;
	}
	/**
	 * @param unitname the unitname to set
	 */
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	/**
	 * @return the unitprice
	 */
	public Double getUnitprice() {
		return unitprice;
	}
	/**
	 * @param unitprice the unitprice to set
	 */
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	/**
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
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
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
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

	
}