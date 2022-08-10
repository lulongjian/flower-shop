package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.UserNoticeDO;
import com.example.mall.domain.vo.UserNoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserNoticeDOMapper extends TkMapper<UserNoticeDO> {

    List<UserNoticeVO> getUserNoticeList(@Param("userId") String userId, @Param("isRead") Integer isRead);
}