package com.gzzz.dao;

import com.gzzz.entity.Brand;
import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.SQLException;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

/**
 * Brand Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class BrandDAO {
    public static List<Brand> listBrands() {
        String sql = "SELECT * FROM brand";
        try {
            return runner.query(sql, new BeanListHandler<>(Brand.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Brand getBrand(int brand_id) {
        String sql = "SELECT * FROM brand WHERE brand_id=?";
        try {
            return runner.query(sql, new BeanHandler<>(Brand.class), brand_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int insertBrand(int brand_id, String brand_name, String remark) {
        String sql = "INSERT INTO brand VALUES(?, ?, ?)";
        try {
            return runner.update(sql, brand_id, brand_name, remark);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static int updatedBrandId() {
        String sql = "SELECT brand_id FROM brand ORDER BY brand_id DESC LIMIT 1";
        try {
            return runner.query(sql, new ScalarHandler<Integer>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1001;
    }
}
