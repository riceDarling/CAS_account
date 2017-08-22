package com.account.entity;

/**
 * 到货单子表
 */
public class AccountArrivalDetail {
	
	private String id;
	private String parent_Id;		// 主表ID 父类
	private String materialcode;//物资编号
	private String materialname;		// 物资名称
	private String size;			//规格型号
	private Double unitprice;		// 单价
	private Double money;		// 总价
	private int quantity;		// 订货数量
	private int num;		// 到货数量
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
	 * @return the parent_Id
	 */
	public String getParent_Id() {
		return parent_Id;
	}
	/**
	 * @param parent_Id the parent_Id to set
	 */
	public void setParent_Id(String parent_Id) {
		this.parent_Id = parent_Id;
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
	
}