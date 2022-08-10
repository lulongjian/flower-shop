package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.service.FlowerLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flower/language")
public class FlowerLanguageController {

    @Autowired
    private FlowerLanguageService flowerLanguageService;

    /**
     * 获取每日花语
     *
     * @return
     */
    @RequestMapping(value = "getFlowerLanguage", method = RequestMethod.POST)
    public JsonModel getFlowerLanguageList() {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = flowerLanguageService.getFlowerLanguageList();
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }
}
