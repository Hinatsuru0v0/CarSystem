package com.gzzz.test;

import com.gzzz.dao.CarDAO;
import com.gzzz.dao.ModelDAO;
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
        if (!cars.isEmpty()) {
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
        if (!cars.isEmpty()) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testListCarsByMaxPrice() {
        List<Car> cars = CarDAO.listCarsByMaxPrice(300000);
        if (!cars.isEmpty()) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testListCarsByModel() {
        List<Car> cars = CarDAO.listCarsByModel(100101);
        if (!cars.isEmpty()) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到品牌二手车信息！");
        }
    }

    @Test
    public void testInsertCar() {
        int modifyCount = CarDAO.insertCar(1001, 100102, "2.0T", 100000, 195800, "自动", "2019-08-01");
        System.out.println("添加二手车的数量:" + modifyCount);

    }

    @Test
    public void testListCarByTime() {
        String start = "2018-07-01" ;
        String end = "2020-07-07";
        List<Car> cars = CarDAO.listCarsByTime(start, end);
        if (!cars.isEmpty()) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }

    @Test
    public void testUpdateSoldCar() {
        int modifyCount = CarDAO.updateSoldCar(2);
        System.out.println("已修改售出二手车的数量:" + modifyCount);
    }
}
