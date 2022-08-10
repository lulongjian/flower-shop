package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.FlowerKnowledgeDO;
import com.example.mall.domain.form.FlowerKnowledgeForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlowerKnowledgeDOMapper extends TkMapper<FlowerKnowledgeDO> {
    /**
     * 管理员获取养花知识
     *
     * @param flowerKnowledgeForm
     * @return
     */
    List<FlowerKnowledgeDO> getFlowerLanguageList(FlowerKnowledgeForm flowerKnowledgeForm);
}