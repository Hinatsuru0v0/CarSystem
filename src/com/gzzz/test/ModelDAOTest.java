package com.gzzz.test;

import com.gzzz.dao.ModelDAO;
import com.gzzz.entity.Model;
import org.junit.Test;

import java.util.List;

public class ModelDAOTest {
    @Test
    public void testListModelsByBrand() {
        List<Model> models = ModelDAO.listModelsByBrand("奥迪");
        if (models != null) {
            for (Model model : models) {
                System.out.println("车型:" + model.getModel_name());
            }
        } else {
            System.out.println("数据库未找到品牌车型信息！");
        }
    }
}
