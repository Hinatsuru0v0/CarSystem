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
    public void testGetCar(int car_id) {
        Car cars = CarDAO.getCar(car_id);
        if (cars != null) {
            System.out.println(cars);
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }
}
