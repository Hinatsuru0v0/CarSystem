package com.gzzz.dao;

import com.gzzz.entity.User;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

import static com.gzzz.utils.DBUtils.runner;

/**
 * User Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class UserDAO {
    // 获取用户
    public static User getUser(String username) {
        String sql = "SELECT * FROM user WHERE username=?";
        try {
            return runner.query(sql, new BeanHandler<>(User.class), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    // 注册用户
    public static int registerUser(String username, String password) {
        String sql = "INSERT INTO user(username, password) VALUES (?, ?)";
        try {
            return runner.update(sql, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static int updateBalance(String username, int balance) {
        String sql = "UPDATE user SET balance=balance+? WHERE username=?";
        try {
            return runner.update(sql, balance, username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
