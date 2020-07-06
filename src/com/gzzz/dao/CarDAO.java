package com.gzzz.dao;

import com.gzzz.entity.Car;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

/**
 * Car Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class CarDAO {
    public static List<Car> listUpdatedCars() {
        String sql = "SELECT * FROM car ORDER BY publish_time DESC LIMIT 10";
        try {
            return runner.query(sql, new BeanListHandler<>(Car.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Car getCar(int car_id) {
        String sql = "SELECT * FROM car WHERE car_id = ?";
        try {
            return runner.query(sql,new BeanHandler<>(Car.class),car_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
