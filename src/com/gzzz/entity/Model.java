package com.gzzz.entity;

/**
 * Model Class
 * @author GZZZ
 * @version 1.0.0
 */
public class Model {
    private int model_id;
    private int brand_id;
    private String model_name;

    public Model() {}

    public Model(int model_id, int brand_id, String model_name) {
        this.model_id = model_id;
        this.brand_id = brand_id;
        this.model_name = model_name;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }
}
