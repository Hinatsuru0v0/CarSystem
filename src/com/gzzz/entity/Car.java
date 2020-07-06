package com.gzzz.entity;

import java.util.Date;

import static com.gzzz.utils.DBUtils.simpleDateFormat;

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
    private boolean is_sold;

    public Car() {}

    public Car(int car_id, int brand_id, int model_id, String exhaust, int milage, int price, String clutch, Date issue_time, Date publish_time, boolean is_sold) {
        this.car_id = car_id;
        this.brand_id = brand_id;
        this.model_id = model_id;
        this.exhaust = exhaust;
        this.milage = milage;
        this.price = price;
        this.clutch = clutch;
        this.issue_time = issue_time;
        this.publish_time = publish_time;
        this.is_sold = is_sold;
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

    public boolean isIs_sold() {
        return is_sold;
    }

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }

    @Override
    public String toString() {
        return "[二手车编号:" + car_id + ", 品牌编号:" + brand_id + "车型编号:" + model_id + ", 排量:" + exhaust
                + ", 里程:" + milage + ", 价格:" + price + ", 离合器类型:" + clutch
                + ", 上牌时间:" + simpleDateFormat.format(issue_time) + ", 发布时间:" + simpleDateFormat.format(publish_time) + "]";
    }
}
