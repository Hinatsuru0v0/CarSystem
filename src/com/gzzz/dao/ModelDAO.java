package com.gzzz.dao;

import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

/**
 * Model Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class ModelDAO {
    public static List<Model> listModelsByBrand(int brand_id){
        String sql = "SELECT * FROM model WHERE brand_id=?";
        try {
            return runner.query(sql, new BeanListHandler<>(Model.class), brand_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Model getModel(int model_id) {
        String sql = "SELECT * FROM model WHERE model_id=?";
        try {
            return runner.query(sql, new BeanHandler<>(Model.class), model_id);
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

    public static int insertModel(int model_id, int brand_id, String model_name, String remark) {
        String sql = "INSERT INTO model VALUES (?, ?, ?, ?); ";
        try {
            return runner.update(sql, model_id, brand_id, model_name, remark);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static int updatedModelId(int brand_id) {
        String sql = "SELECT model_id FROM model WHERE brand_id=? ORDER BY model_id DESC limit 1";
        try {
            return runner.query(sql, new ScalarHandler<Integer>(), brand_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}

