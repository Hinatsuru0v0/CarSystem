package com.gzzz.test;

import com.gzzz.dao.UserDAO;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void testRegisterUser() {
        int modifyCount = UserDAO.registerUser("test", "123456");
        System.out.println("Count of register user: " + modifyCount);
    }
}