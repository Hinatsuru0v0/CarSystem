package com.gzzz.dao;

import com.gzzz.entity.Car;
import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

/**
 * Car Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class CarDAO {
    public static List<Car> listUpdatedCars() {
        String sql = "SELECT * FROM car WHERE is_sold=false ORDER BY publish_time DESC LIMIT 10";
        try {
            return runner.query(sql, new BeanListHandler<>(Car.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List<Car> listCarsByModel(int model_id){
        String sql = "SELECT * FROM car WHERE model_id=? AND is_sold=false";
        try {
            return runner.query(sql, new BeanListHandler<>(Car.class), model_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static Car getCar(int car_id) {
        String sql = "SELECT * FROM car WHERE car_id=?";
        try {
            return runner.query(sql, new BeanHandler<>(Car.class), car_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public  static List<Car> listCarsByPrice(double minprice, double maxprice){
        String sql = "SELECT * FROM car WHERE price>=? AND price<? AND is_sold=false";
        try{
            return runner.query(sql, new BeanListHandler<>(Car.class), minprice, maxprice);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public  static List<Car> listCarsByMaxPrice(double maxprice){
        String sql = "SELECT * FROM car WHERE price>=? AND is_sold=false";
        try{
            return runner.query(sql, new BeanListHandler<>(Car.class), maxprice);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insertCar(int brand_id,int moder_id,String exhaust,int milage,int price,String clutch,String issue_time){
        String sql = "INSERT INTO car(brand_id, model_id, exhaust, milage, price, clutch, issue_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            return runner.update(sql, brand_id, moder_id, exhaust, milage, price, clutch, issue_time);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static List<Car> listCarsByTime(String start_time,String end_time){
        String sql = "SELECT * FROM car WHERE ?<=issue_time AND issue_time<=? AND is_sold=false";
        try{
            return runner.query(sql, new BeanListHandler<>(Car.class), start_time, end_time);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static int updateSoldCar(int car_id) {
        String sql = "UPDATE car SET is_sold=true WHERE car_id=?";
        try {
            return runner.update(sql, car_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
