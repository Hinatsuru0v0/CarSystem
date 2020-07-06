package com.gzzz.dao;

import com.gzzz.entity.Brand;
import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


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

    public static int insertBrand(int brand_id,String brand_name,String remark) {
        String sql = "INSERT INTO brand VALUES(?, ?, ?)";
        try {
            return runner.update(sql, brand_id, brand_name, remark);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
