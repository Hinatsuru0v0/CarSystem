package com.gzzz.test;

import com.gzzz.dao.BrandDAO;
import com.gzzz.entity.Brand;

import org.junit.Test;

import java.util.List;

public class BrandDAOTest {
    @Test
    public void testGetBrands() {
        List<Brand> brands = BrandDAO.listGetBrands();
        if (brands != null) {
            for (Brand brand : brands) {
                System.out.println("品牌编号：" + brand.getBrand_id() + "，品牌名：" + brand.getBrand_name() + "，备注：" + brand.getRemark());
            }
        } else {
            System.out.println("数据库未找到品牌信息！");
        }
    }
}
