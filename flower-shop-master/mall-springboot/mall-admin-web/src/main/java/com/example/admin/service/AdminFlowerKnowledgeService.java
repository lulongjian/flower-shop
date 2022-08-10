package com.example.admin.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.FlowerKnowledgeDOMapper;
import com.example.mall.domain.FlowerKnowledgeDO;
import com.example.mall.domain.FlowerLanguageDO;
import com.example.mall.domain.form.FlowerKnowledgeForm;
import com.example.mall.utils.DateFormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AdminFlowerKnowledgeService {

    @Autowired
    private FlowerKnowledgeDOMapper flowerKnowledgeDOMapper;

    /**
     * 管理员获取养花知识
     *
     * @param page
     * @param number
     * @param flowerKnowledgeForm
     * @return
     */
    public JsonModel getFlowerKnowledgeList(Integer page, Integer number, FlowerKnowledgeForm flowerKnowledgeForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<FlowerKnowledgeDO> voPage = (Page<FlowerKnowledgeDO>) flowerKnowledgeDOMapper.getFlowerLanguageList(flowerKnowledgeForm);
        PageInfo<FlowerKnowledgeDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 新增养花知识
     *
     * @param flowerKnowledgeForm
     * @return
     */
    public JsonModel addFlowerKnowledge(FlowerKnowledgeForm flowerKnowledgeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            FlowerKnowledgeDO flowerKnowledgeDO = new FlowerKnowledgeDO();
            flowerKnowledgeDO.setTitle(flowerKnowledgeForm.getTitle());
            flowerKnowledgeDO.setContent(flowerKnowledgeForm.getContent());
            flowerKnowledgeDO.setCreateTime(new Date());
            flowerKnowledgeDO.setUpdateTime(new Date());
            flowerKnowledgeDOMapper.insertSelective(flowerKnowledgeDO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 更新养花知识
     *
     * @param flowerKnowledgeForm
     * @return
     */
    public JsonModel updateFlowerKnowledge(FlowerKnowledgeForm flowerKnowledgeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            FlowerKnowledgeDO flowerKnowledgeDO = flowerKnowledgeDOMapper.selectByPrimaryKey(flowerKnowledgeForm.getId());
            flowerKnowledgeDO.setTitle(flowerKnowledgeForm.getTitle());
            flowerKnowledgeDO.setContent(flowerKnowledgeForm.getContent());
            flowerKnowledgeDO.setUpdateTime(new Date());
            flowerKnowledgeDOMapper.updateByPrimaryKeySelective(flowerKnowledgeDO);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 删除养花知识
     *
     * @param id
     * @return
     */
    public JsonModel deleteFlowerKnowledge(Integer id) {
        JsonModel jsonModel = new JsonModel();
        try {
            flowerKnowledgeDOMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }
}
