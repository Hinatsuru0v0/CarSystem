package com.gzzz.entity;

import java.util.Date;

/**
 * User Class
 * @author GZZZ
 * @version 1.0.0
 */
public class Car {
    private int car_id;
    private int brand_id;
    private int model_id;
    private String exhaust;
    private int milage;
    private int price;
    private String clutch;
    private Date issue_time;
    private Date publish_time;

    public Car() {}

    public Car(int car_id, int brand_id, int model_id, String exhaust, int milage, int price, String clutch, Date issue_time, Date publish_time) {
        this.car_id = car_id;
        this.brand_id = brand_id;
        this.model_id = model_id;
        this.exhaust = exhaust;
        this.milage = milage;
        this.price = price;
        this.clutch = clutch;
        this.issue_time = issue_time;
        this.publish_time = publish_time;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getExhaust() {
        return exhaust;
    }

    public void setExhaust(String exhaust) {
        this.exhaust = exhaust;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getClutch() {
        return clutch;
    }

    public void setClutch(String clutch) {
        this.clutch = clutch;
    }

    public Date getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(Date issue_time) {
        this.issue_time = issue_time;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }
}
