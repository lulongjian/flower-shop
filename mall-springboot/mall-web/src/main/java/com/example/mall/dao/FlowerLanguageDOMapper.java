package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.FlowerLanguageDO;
import com.example.mall.domain.form.FlowerLanguageForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlowerLanguageDOMapper extends TkMapper<FlowerLanguageDO> {

    /**
     * 管理员获取每日花语
     *
     * @param flowerLanguageForm
     * @return
     */
    List<FlowerLanguageDO> getFlowerLanguageList(FlowerLanguageForm flowerLanguageForm);

    Integer getFlowerLanguageByReTime(String reTime);
}