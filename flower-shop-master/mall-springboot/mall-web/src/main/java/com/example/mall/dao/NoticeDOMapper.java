package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.NoticeDO;
import com.example.mall.domain.form.NoticeForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDOMapper extends TkMapper<NoticeDO> {

    /**
     * 查询系统公告通知列表
     *
     * @param noticeForm
     * @return
     */
    List<NoticeDO> getNoticeList(NoticeForm noticeForm);
}