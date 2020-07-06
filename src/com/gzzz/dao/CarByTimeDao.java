package com.gzzz.dao;

import java.util.List;
import java.util.Date;
import com.gzzz.entity.Car;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import static com.gzzz.utils.DBUtils.runner;


public class CarByTimeDao {
    public static List<Car> listCarsByTime(String starttime,String endtime){
        String sql = "select * from car where ?<=publish_time  and publish_time<=?";
        try{
            return runner.query(sql, new BeanListHandler<>(Car.class), starttime,endtime);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
