package com.gzzz.dao;

import java.sql.SQLException;

import static com.gzzz.utils.DBUtils.runner;

/**
 * User Data Access Object
 * @author GZZZ
 * @version 1.0.0
 */
public class UserDAO {
    // 用户注册
    public static int registerUser(String username, String password) {
        String sql = "INSERT INTO user(username, password) VALUES (?, ?)";
        try {
            return runner.update(sql, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
