package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.ProductTypeDO;
import com.example.mall.domain.form.ProductTypeForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeDOMapper extends TkMapper<ProductTypeDO> {

    List<ProductTypeDO> getProductType(ProductTypeForm productTypeForm);
}