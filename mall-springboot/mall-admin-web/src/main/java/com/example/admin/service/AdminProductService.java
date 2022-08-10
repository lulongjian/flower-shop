package com.example.admin.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.ProductMapper;
import com.example.mall.domain.ProductDO;
import com.example.mall.domain.form.ProductForm;
import com.example.mall.domain.vo.ProductVO;
import com.example.mall.utils.Base64Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 管理员获取商品信息
     *
     * @param page
     * @param number
     * @param productForm
     * @return
     */
    public JsonModel getProduct(Integer page, Integer number, ProductForm productForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<ProductVO> productVOPage = (Page<ProductVO>) productMapper.getProduct(productForm);
        PageInfo<ProductVO> productVOPageInfo = productVOPage.toPageInfo();
        // 补充base64
        productVOPageInfo.getList().stream().forEach(productVO -> {
            String imageStr = Base64Utils.GetImageStr(productVO.getImgUrl());
            productVO.setImgUrlBase64(imageStr);
        });
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(productVOPageInfo.getTotal());
        jsonModel.setData(productVOPageInfo.getList());
        return jsonModel;
    }

    /**
     * 更新商品状态--上架
     *
     * @param id
     */
    public void upProduct(String id) {
        ProductDO productDO = new ProductDO();
        productDO.setId(Integer.parseInt(id));
        productDO.setPermission("1");
        productMapper.updateByPrimaryKeySelective(productDO);
    }

    /**
     * 更新商品状态--下架
     *
     * @param id
     */
    public void deProduct(String id) {
        ProductDO productDO = new ProductDO();
        productDO.setId(Integer.parseInt(id));
        productDO.setPermission("0");
        productMapper.updateByPrimaryKeySelective(productDO);
    }

    /**
     * 查询列表(下拉选择)
     *
     * @return
     */
    public JsonModel selectProductList() {
        ProductDO productDO = new ProductDO();
        productDO.setPermission("1");
        List<ProductDO> productDOList = productMapper.select(productDO);

        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setData(productDOList);
        return jsonModel;
    }
}
