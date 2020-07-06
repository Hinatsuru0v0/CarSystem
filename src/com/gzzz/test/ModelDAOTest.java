package com.gzzz.test;

import com.gzzz.dao.ModelDAO;
import com.gzzz.dao.UserDAO;
import com.gzzz.entity.Model;
import com.gzzz.entity.User;
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
    @Test
    public void removeModel() {
        int model =ModelDAO.removeModel(100101);
        System.out.println(model);
    }
}
