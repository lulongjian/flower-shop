package com.example.admin.service;

import java.util.Date;
import java.util.List;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.RecommendMapper;
import com.example.mall.domain.RecommendDO;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.domain.vo.RecommendVO;
import com.example.mall.domain.vo.UserVO;
import com.example.mall.utils.Base64Utils;
import com.example.mall.utils.DateFormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminRecommendService {

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
//            productVO.setId(productVO.getProductId());
            String imageStr = Base64Utils.GetImageStr(productVO.getImgUrl());
            productVO.setImgUrlBase64(imageStr);
        });
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 新增每日推荐
     *
     * @param recommendForm
     * @return
     */
    public JsonModel addRecommendRequest(RecommendForm recommendForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            RecommendDO recommendDO = new RecommendDO();
            recommendDO.setProductId(recommendForm.getProductId());
            recommendDO.setSort(recommendForm.getSort());
            recommendDO.setReTime(DateFormatUtils.StrToDate(recommendForm.getReTime()));
            recommendDO.setCreateTime(new Date());
            recommendMapper.insertSelective(recommendDO);
        } catch (Exception e) {
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 更新每日推荐
     *
     * @param recommendForm
     * @return
     */
    public JsonModel updateRecommendRequest(RecommendForm recommendForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            RecommendDO recommendDO = new RecommendDO();
            recommendDO.setId(recommendForm.getId());
            recommendDO.setProductId(recommendForm.getProductId());
            recommendDO.setSort(recommendForm.getSort());
            recommendDO.setReTime(DateFormatUtils.StrToDate(recommendForm.getReTime()));
            recommendDO.setCreateTime(new Date());
            recommendMapper.updateByPrimaryKeySelective(recommendDO);
        } catch (Exception e) {
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }

    /**
     * 删除每日推荐
     *
     * @param id
     * @return
     */
    public JsonModel deleteRecommend(Integer id) {
        JsonModel jsonModel = new JsonModel();
        try {
            recommendMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }
}
