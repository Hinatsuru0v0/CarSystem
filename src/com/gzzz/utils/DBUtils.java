package com.gzzz.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.text.SimpleDateFormat;

/**
 * Database Utils
 * @author GZZZ
 * @version 1.0.0
 */
public class DBUtils {
    public static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static final QueryRunner runner = new QueryRunner(dataSource);

    public static final  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
