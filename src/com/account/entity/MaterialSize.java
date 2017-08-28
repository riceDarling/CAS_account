package com.account.entity;

public class MaterialSize {
    private Integer id;

    private String materialnum;

    private String size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialnum() {
        return materialnum;
    }

    public void setMaterialnum(String materialnum) {
        this.materialnum = materialnum == null ? null : materialnum.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }
}