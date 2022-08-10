package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.FlowerKnowledgeDOMapper;
import com.example.mall.domain.FlowerKnowledgeDO;
import com.example.mall.domain.form.FlowerKnowledgeForm;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlowerKnowledgeService {

    @Autowired
    private FlowerKnowledgeDOMapper flowerKnowledgeDOMapper;


    /**
     * 获取养花知识
     *
     * @param page
     * @param number
     * @return
     */
    public JsonModel getFlowerKnowledge(Integer page, Integer number) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<FlowerKnowledgeDO> voPage = (Page<FlowerKnowledgeDO>) flowerKnowledgeDOMapper.getFlowerLanguageList(new FlowerKnowledgeForm());
        PageInfo<FlowerKnowledgeDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }
}
