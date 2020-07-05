package com.gzzz.entity;

/**
 * Brand Class
 * @author GZZZ
 * @version 1.0.0
 */
public class Brand {
    private int brand_id;
    private String brand_name;
    private String remark;

    public Brand() {}

    public Brand(int brand_id, String brand_name, String remark) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.remark = remark;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
