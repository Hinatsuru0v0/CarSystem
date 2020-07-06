package com.gzzz.dao;


import java.util.ArrayList;
import java.util.List;
import com.gzzz.entity.Car;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import static com.gzzz.utils.DBUtils.runner;
import java.sql.SQLException;

public class CarByPriceDao {


    public  static List<Car> queryCarsByPrice(double minprice,double maxprice){
        String sql = "select * from car where price>? and price<? ";
        List <Car> list = new ArrayList<>();
        try{
            return runner.query(sql,new BeanListHandler<>(Car.class),minprice,maxprice);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return  null;
    }
    
}
