package com.gzzz.test;

import com.gzzz.dao.CarByTimeDao;
import com.gzzz.dao.CarDAO;
import com.gzzz.entity.Car;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class CarByTimeTest {
    @Test
    public void testCarByTime() {
        String x = "2020-07-01" ;
        String y = "2020-07-07";
        List<Car> cars = CarByTimeDao.listCarsByTime(x,y);
        if (cars != null) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("数据库未找到二手车信息！");
        }
    }
}
