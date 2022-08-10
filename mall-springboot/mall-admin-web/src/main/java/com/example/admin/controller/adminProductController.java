package com.example.admin.controller;

import com.example.admin.service.AdminProductService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.ProductForm;
import com.example.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/product")
public class adminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @Autowired
    private ProductService productService;

    /**
     * 添加商品
     *
     * @param productForm
     * @return
     */
    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public JsonModel addProduct(ProductForm productForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            //校验参数
            productService.addProduct(productForm);
            jsonModel.setCode(0);
            jsonModel.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("系统异常，请联系管理员！");
        }
        return jsonModel;
    }

    /**
     * 更新商品
     *
     * @param productForm
     * @return
     */
    @RequestMapping(value = "updateProduct", method = RequestMethod.POST)
    public JsonModel updateProduct(ProductForm productForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            //校验参数
            productService.updateProduct(productForm);
            jsonModel.setCode(0);
            jsonModel.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("系统异常，请联系管理员！");
        }
        return jsonModel;
    }

    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping(value = "getProduct", method = RequestMethod.POST)
    public JsonModel getProduct(Integer page, Integer number, ProductForm productForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminProductService.getProduct(page, number, productForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 查询列表(下拉选择)
     *
     * @return
     */
    @RequestMapping(value = "selectProductList", method = RequestMethod.GET)
    public JsonModel selectProductList() {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminProductService.selectProductList();
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 上架商品
     *
     * @return
     */
    @RequestMapping(value = "upProduct/{id}", method = RequestMethod.GET)
    public JsonModel upProduct(@PathVariable("id") String id) {
        //更新商品状态---上架
        JsonModel jsonMode = new JsonModel();
        try {
            adminProductService.upProduct(id);
            jsonMode.setMsg("上架成功");
        } catch (Exception e) {
            jsonMode.setCode(1);
            jsonMode.setMsg("网络错误！请刷新重试");
        }
        return jsonMode;
    }

    /**
     * 下架商品
     *
     * @return
     */
    @RequestMapping(value = "deProduct/{id}", method = RequestMethod.GET)
    public JsonModel deProduct(@PathVariable("id") String id) {
        //更新商品状态---上架
        JsonModel jsonMode = new JsonModel();
        try {
            adminProductService.deProduct(id);
            jsonMode.setMsg("下架成功");
        } catch (Exception e) {
            jsonMode.setCode(1);
            jsonMode.setMsg("网络错误！请刷新重试");
        }
        return jsonMode;
    }

    /**
     * 批量下架商品
     *
     * @return
     */
    @RequestMapping(value = "deProducts/{productIds}", method = RequestMethod.DELETE)
    public JsonModel deProducts(@PathVariable("productIds") String productIds) {
        //更新商品状态---上架
        JsonModel jsonMode = new JsonModel();
        String[] idArrays = productIds.split(",");
        try {
            for (int i = 0; i < idArrays.length; i++) {
                adminProductService.deProduct(idArrays[i]);
            }
            jsonMode.setMsg("下架成功");
        } catch (Exception e) {
            jsonMode.setCode(1);
            jsonMode.setMsg("网络错误！请刷新重试");
        }
        return jsonMode;
    }

    /**
     * 批量上架商品
     *
     * @return
     */
    @RequestMapping(value = "upProducts/{productIds}", method = RequestMethod.GET)
    public JsonModel upProducts(@PathVariable("productIds") String productIds) {
        //更新商品状态---上架
        JsonModel jsonMode = new JsonModel();
        String[] idArrays = productIds.split(",");
        try {
            for (int i = 0; i < idArrays.length; i++) {
                adminProductService.upProduct(idArrays[i]);
            }
            jsonMode.setMsg("上架成功");
        } catch (Exception e) {
            jsonMode.setCode(1);
            jsonMode.setMsg("网络错误！请刷新重试");
        }
        return jsonMode;
    }
}
