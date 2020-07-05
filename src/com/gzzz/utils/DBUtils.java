package com.gzzz.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * Database Utils
 * @author GZZZ
 * @version 1.0.0
 */
public class DBUtils {
    public final static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public final static QueryRunner runner = new QueryRunner(dataSource);
}
