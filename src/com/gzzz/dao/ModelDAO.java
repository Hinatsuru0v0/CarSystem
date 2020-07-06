package com.gzzz.dao;

import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

/**
 * Model Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class ModelDAO {
    public static List<Model> listModelsByBrand(String brand_name){
        String sql = "SELECT model_name FROM model, brand WHERE brand.brand_id=model.brand_id AND brand_name=?";
        try {
            return runner.query(sql, new BeanListHandler<>(Model.class), brand_name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int removeModel(int model_id) {
        String sql = "DELETE FROM model WHERE model_id=?";
        try {
            return runner.update(sql, model_id) ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}

