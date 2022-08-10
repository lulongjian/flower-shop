package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.UserOrderDO;
import com.example.mall.domain.UserOrderDetailsDO;
import com.example.mall.domain.vo.UserOrderDetailsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderDetailsDOMapper extends TkMapper<UserOrderDetailsDO> {

    /**
     * 获取商品详情信息
     *
     * @param id
     * @return
     */
    List<UserOrderDetailsVO> getOrderDetailsVOList(Integer id);
}