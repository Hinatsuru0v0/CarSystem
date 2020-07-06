package com.gzzz.test;

import com.gzzz.dao.UserDAO;
import com.gzzz.entity.User;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void testGetUser() {
        User user = UserDAO.getUser("admin");
        System.out.println(user);
    }

    @Test
    public void testRegisterUser() {
        int modifyCount = UserDAO.registerUser("test", "123456");
        System.out.println("添加的车型总数:" + modifyCount);
    }
}
