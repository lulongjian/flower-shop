package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.service.FlowerKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flower/knowledge")
public class FlowerKnowledgeController {

    @Autowired
    private FlowerKnowledgeService flowerKnowledgeService;

    /**
     * 获取养花知识
     *
     * @return
     */
    @RequestMapping(value = "getFlowerKnowledge", method = RequestMethod.POST)
    public JsonModel getFlowerKnowledge(Integer page, Integer number) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = flowerKnowledgeService.getFlowerKnowledge(page, number);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }
}
