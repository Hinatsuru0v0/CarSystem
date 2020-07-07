package com.gzzz.test;

import com.gzzz.dao.BrandDAO;
import com.gzzz.entity.Brand;

import org.junit.Test;

import java.util.List;

public class BrandDAOTest {
    @Test
    public void testListBrands() {
        List<Brand> brands = BrandDAO.listBrands();
        if (brands.size() > 0) {
            for (Brand brand : brands) {
                System.out.println("品牌名:" + brand.getBrand_name());
            }
        } else {
            System.out.println("数据库未找到品牌信息！");
        }
    }

    @Test
    public void testGetBrand() {
        Brand brand = BrandDAO.getBrand(1001);
        if (brand != null) {
            System.out.println("品牌:" + brand.getBrand_name());
        } else {
            System.out.println("数据库未找到品牌信息！");
        }
    }

    @Test
    public void testInsertBrand() {
        int modifyCount = BrandDAO.insertBrand(1004, "奔驰", "");
        System.out.println("添加的品牌总数:" + modifyCount);
    }

    @Test
    public void testUpdatedBrandId() {
        int brand_id = BrandDAO.updatedBrandId();
        System.out.println("最新的品牌编号:" + brand_id);
    }
}
