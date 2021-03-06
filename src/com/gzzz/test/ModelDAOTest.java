package com.gzzz.test;

import com.gzzz.dao.ModelDAO;
import com.gzzz.entity.Model;
import org.junit.Test;

import java.util.List;

public class ModelDAOTest {
    @Test
    public void testListModelsByBrand() {
        List<Model> models = ModelDAO.listModelsByBrand(1001);
        if (!models.isEmpty()) {
            for (Model model : models) {
                System.out.println("车型:" + model.getModel_name());
            }
        } else {
            System.out.println("数据库未找到品牌车型信息！");
        }
    }

    @Test
    public void testGetModel() {
        Model model = ModelDAO.getModel(100101);
        if (model != null) {
            System.out.println("车型:" + model.getModel_name());
        } else {
            System.out.println("数据库未找到车型信息！");
        }
    }

    @Test
    public void testRemoveModel() {
        int modifyCount = ModelDAO.removeModel(100101);
        System.out.println("删除的车型总数:" + modifyCount);
    }

    @Test
    public void testInsertModel() {
        int modifyCount = ModelDAO.insertModel(100101,1001,"A4L", "");
        System.out.println("添加的车型总数:" + modifyCount);
    }

    @Test
    public void testUpdatedModelId() {
        int model_id = ModelDAO.updatedModelId(1001);
        System.out.println("最新的车型编号:" + model_id);
    }
}
