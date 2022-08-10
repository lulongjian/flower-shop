package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.form.ProductTypeForm;
import com.example.mall.service.OrderService;
import com.example.mall.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product/type")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 获取全部
     *
     * @param productTypeForm
     * @return
     */
    @RequestMapping(value = "getProductTypeAllList", method = RequestMethod.POST)
    public JsonModel getProductTypeAllList(ProductTypeForm productTypeForm) {
        return productTypeService.getProductTypeAllList(productTypeForm);
    }
}
