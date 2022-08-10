package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.RecommendDO;
import com.example.mall.domain.form.RecommendForm;
import com.example.mall.domain.vo.RecommendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper extends TkMapper<RecommendDO> {

    /**
     * 管理员获取每日推荐
     *
     * @param recommendForm
     * @return
     */
    List<RecommendVO> getRecommendList(RecommendForm recommendForm);
}
