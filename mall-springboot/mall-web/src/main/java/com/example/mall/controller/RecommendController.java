package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.service.RecommendService;
import com.example.mall.utils.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: 12198
 * @Time: 2022/4/25 22:48
 * @Description:
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 管理员获取每日推荐
     *
     * @return
     */
    @RequestMapping(value = "getRecommendList", method = RequestMethod.POST)
    public JsonModel getRecommendList(Integer page, Integer number, RecommendForm recommendForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            if (StringUtils.isEmpty(recommendForm.getReTime())) {
                recommendForm.setReTime(DateFormatUtils.DateToStr(new Date()));
            }
            jsonModel = recommendService.getRecommendList(page, number, recommendForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

}
