package com.gzzz.test;

import com.gzzz.dao.UserDAO;
import com.gzzz.entity.User;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void testGetUser() {
        User user = UserDAO.getUser("admin");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("数据库未找到用户信息！");
        }
    }

    @Test
    public void testRegisterUser() {
        int modifyCount = UserDAO.registerUser("test", "123456");
        System.out.println("注册的用户总数:" + modifyCount);
    }
}
