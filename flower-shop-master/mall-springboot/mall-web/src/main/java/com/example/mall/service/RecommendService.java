package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.AccountMapper;
import com.example.mall.dao.RecommendMapper;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.domain.vo.RecommendVO;
import com.example.mall.utils.Base64Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;

    /**
     * 管理员获取每日推荐
     *
     * @param page
     * @param number
     * @param recommendForm
     * @return
     */
    public JsonModel getRecommendList(Integer page, Integer number, RecommendForm recommendForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<RecommendVO> voPage = (Page<RecommendVO>) recommendMapper.getRecommendList(recommendForm);
        PageInfo<RecommendVO> pageInfo = voPage.toPageInfo();
        // 补充base64
        List<RecommendVO> list = pageInfo.getList();
        list.stream().forEach(productVO -> {
            productVO.setId(productVO.getProductId());
            String imageStr = Base64Utils.GetImageStr(productVO.getImgUrl());
            productVO.setImgUrlBase64(imageStr);
        });
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(list);
        return jsonModel;
    }
}
