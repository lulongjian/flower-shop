package com.example.admin.service;
import java.util.Date;
import java.util.List;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.ProductTypeDOMapper;
import com.example.mall.domain.ProductDO;
import com.example.mall.domain.ProductTypeDO;
import com.example.mall.domain.form.ProductForm;
import com.example.mall.domain.form.ProductTypeForm;
import com.example.mall.domain.vo.ProductVO;
import com.example.mall.utils.Base64Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminProductTypeService {

    @Autowired
    private ProductTypeDOMapper productTypeDOMapper;

    /**
     * 管理员获取商品类别信息
     *
     * @param page
     * @param number
     * @param productTypeForm
     * @return
     */
    public JsonModel getProductType(Integer page, Integer number, ProductTypeForm productTypeForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<ProductTypeDO> voPage = (Page<ProductTypeDO>) productTypeDOMapper.getProductType(productTypeForm);
        PageInfo<ProductTypeDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    public JsonModel addProductType(ProductTypeForm productTypeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            ProductTypeDO productTypeDO = new ProductTypeDO();
            productTypeDO.setSort(productTypeForm.getSort());
            productTypeDO.setName(productTypeForm.getName());
            productTypeDO.setCreateTime(new Date());

            productTypeDOMapper.insertSelective(productTypeDO);
            jsonModel.setMsg("新增成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    public JsonModel updateProductType(ProductTypeForm productTypeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            ProductTypeDO productTypeDO = new ProductTypeDO();
            productTypeDO.setId(productTypeForm.getId());
            productTypeDO.setSort(productTypeForm.getSort());
            productTypeDO.setName(productTypeForm.getName());
            productTypeDO.setCreateTime(new Date());

            productTypeDOMapper.updateByPrimaryKeySelective(productTypeDO);
            jsonModel.setMsg("更新成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    public JsonModel deleteProductType(ProductTypeForm productTypeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            productTypeDOMapper.deleteByPrimaryKey(productTypeForm.getId());
            jsonModel.setMsg("删除成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    public JsonModel getProductTypeAllList(ProductTypeForm productTypeForm) {
        List<ProductTypeDO> productTypeDOList = productTypeDOMapper.getProductType(productTypeForm);
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setData(productTypeDOList);
        return jsonModel;
    }
}
