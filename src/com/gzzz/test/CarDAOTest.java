package com.gzzz.test;

import com.gzzz.dao.CarDAO;
import com.gzzz.entity.Car;
import com.gzzz.entity.Model;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static com.gzzz.utils.DBUtils.runner;

public class CarDAOTest {
    @Test
    public void testListUpdatedCars() {
        List<Car> cars = CarDAO.listUpdatedCars();
        if (cars != null) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testGetCar() {
        Car car = CarDAO.getCar(1);
        if (car != null) {
            System.out.println(car);
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testListCarsByPrice() {
        List<Car> cars = CarDAO.listCarsByPrice(200000, 300000);
        if (cars != null) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testInsertCar() {
        int modifyCount = CarDAO.insertCar(1001,100102,"2.0T",100000,195800,"自动","2019-08-01");
        if (modifyCount != 0) {
            System.out.println(modifyCount);
        } else {
            System.out.println("数据未添加或已存在！");
        }
    }
}
