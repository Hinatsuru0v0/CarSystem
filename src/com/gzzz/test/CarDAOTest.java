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
    public void listCarsByModel() {
        List<Car> cars = CarDAO.listCarsByModel(100101);
        if (cars != null) {
            for (Car car : cars) {
                System.out.println("车辆编号：" + car.getCar_id() +
                        "，品牌编号：" + car.getModel_id() +
                        "，排量：" + car.getExhaust() +
                        "，里程数："+car.getMilage()+
                        "，价格：" +car.getPrice()+
                        "，离合器："+car.getClutch()+
                        "，发行时间:"+car.getIssue_time()+
                        "，发布时间:"+car.getPublish_time());
            }
        } else {
            System.out.println("数据库未找到品牌车型信息！");
        }
    }
}
