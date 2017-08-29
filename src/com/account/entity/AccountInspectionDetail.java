package com.account.entity;

import org.hibernate.validator.constraints.Length;

/**
 * 送检单子表
 */
public class AccountInspectionDetail {
	
	private String id;
	private String parent_Id;		// 主表ID 父类
	private String materialcode;//物资编码
	private String materialname;		// 物资名称
	private String size;			//规格型号
	private String ingredient;		// 成分含量
	private String granularity;		// 粒度
	private String appearance;		// 外观
	private String status;			//检验类型（1.免检 2.必检）
	private String inspectionmode;		// 检验方式
	private String result;//检验结果（1不合格 2.合格）
	private String remarks;	
	private String delFlag="0";//是否删除
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public AccountInspectionDetail() {
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

	@Length(min=1, max=64, message="物资名称长度必须介于 1 和 64 之间")
	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	
	@Length(min=1, max=11, message="检验方式长度必须介于 1 和 11 之间")
	public String getInspectionmode() {
		return inspectionmode;
	}

	public void setInspectionmode(String inspectionmode) {
		this.inspectionmode = inspectionmode;
	}
	
	@Length(min=1, max=64, message="成分含量长度必须介于 1 和 64 之间")
	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
	
	@Length(min=1, max=64, message="外观长度必须介于 1 和 64 之间")
	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
}