package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.ProductTypeDOMapper;
import com.example.mall.domain.ProductTypeDO;
import com.example.mall.domain.form.ProductTypeForm;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductTypeService {

    @Autowired
    private ProductTypeDOMapper productTypeDOMapper;

    public JsonModel getProductTypeAllList(ProductTypeForm productTypeForm) {
        List<ProductTypeDO> productTypeDOList = productTypeDOMapper.getProductType(productTypeForm);
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setData(productTypeDOList);
        return jsonModel;
    }
}
