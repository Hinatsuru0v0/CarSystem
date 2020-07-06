package com.gzzz.dao;

import com.gzzz.entity.Car;
import com.gzzz.entity.Model;
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
    public static List<Model> listModelsByBrand(String brand_name){
        String sql = "SELECT model_name FROM model,brand WHERE brand.brand_id=model.brand_id AND brand_name =?;";
        try {
            return runner.query(sql, new BeanListHandler<>(Model.class),brand_name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
