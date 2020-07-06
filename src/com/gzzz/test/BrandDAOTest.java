package com.gzzz.test;

import com.gzzz.dao.BrandDAO;
import com.gzzz.entity.Brand;

import org.junit.Test;

import java.util.List;

public class BrandDAOTest {
    @Test
    public void testListBrands() {
        List<Brand> brands = BrandDAO.listBrands();
        if (brands != null) {
            for (Brand brand : brands) {
                System.out.println("品牌名:" + brand.getBrand_name());
            }
        } else {
            System.out.println("数据库未找到品牌信息！");
        }
    }
}
