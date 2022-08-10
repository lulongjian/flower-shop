package com.example.admin.controller;

import com.example.admin.service.AdminNoticeService;
import com.example.admin.service.AdminProductTypeService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.NoticeForm;
import com.example.mall.domain.form.ProductTypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/produce/type")
public class adminProductTyeController {

    @Autowired
    private AdminProductTypeService adminProductTypeService;

    /**
     * 查询类别列表
     *
     * @return
     */
    @RequestMapping(value = "getProductTypeList", method = RequestMethod.POST)
    public JsonModel getProductTypeList(Integer page, Integer number, ProductTypeForm productTypeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminProductTypeService.getProductType(page, number, productTypeForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 新增类别
     *
     * @param productTypeForm
     * @return
     */
    @RequestMapping(value = "addProduceType", method = RequestMethod.POST)
    public JsonModel addProduceType(ProductTypeForm productTypeForm) {
        return adminProductTypeService.addProductType(productTypeForm);
    }

    /**
     * 更新类别
     *
     * @param productTypeForm
     * @return
     */
    @RequestMapping(value = "updateProductType", method = RequestMethod.POST)
    public JsonModel updateProductType(ProductTypeForm productTypeForm) {
        return adminProductTypeService.updateProductType(productTypeForm);
    }

    /**
     * 删除类别
     *
     * @param productTypeForm
     * @return
     */
    @RequestMapping(value = "deleteProductType", method = RequestMethod.POST)
    public JsonModel deleteProductType(ProductTypeForm productTypeForm) {
        return adminProductTypeService.deleteProductType(productTypeForm);
    }

    /**
     * 获取全部
     *
     * @param productTypeForm
     * @return
     */
    @RequestMapping(value = "getProductTypeAllList", method = RequestMethod.POST)
    public JsonModel getProductTypeAllList(ProductTypeForm productTypeForm) {
        return adminProductTypeService.getProductTypeAllList(productTypeForm);
    }
}
