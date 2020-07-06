package com.gzzz.test;

import com.gzzz.dao.CarDAO;
import com.gzzz.entity.Car;
import org.junit.Test;

import java.util.List;

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
}
