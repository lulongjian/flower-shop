package com.example.admin.service;

import java.util.Date;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.NoticeDOMapper;
import com.example.mall.domain.NoticeDO;
import com.example.mall.domain.form.NoticeForm;
import com.example.mall.domain.vo.RecommendVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminNoticeService {

    @Autowired
    private NoticeDOMapper noticeDOMapper;

    /**
     * 查询系统公告通知列表
     *
     * @param page
     * @param number
     * @param noticeForm
     * @return
     */
    public JsonModel getNoticeList(Integer page, Integer number, NoticeForm noticeForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<NoticeDO> voPage = (Page<NoticeDO>) noticeDOMapper.getNoticeList(noticeForm);
        PageInfo<NoticeDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 新增系统通知
     *
     * @param noticeForm
     * @return
     */
    public void addNotice(NoticeForm noticeForm) {
        NoticeDO noticeDO = new NoticeDO();
        noticeDO.setTitle(noticeForm.getTitle());
        noticeDO.setCreateTime(new Date());
        noticeDO.setContent(noticeForm.getContent());
        noticeDOMapper.insertSelective(noticeDO);
    }

    /**
     * 更新系统通知
     *
     * @param noticeForm
     */
    public void updateNotice(NoticeForm noticeForm) {
        NoticeDO noticeDO = noticeDOMapper.selectByPrimaryKey(noticeForm.getId());
        noticeDO.setTitle(noticeForm.getTitle());
        noticeDO.setUpdateTime(new Date());
        noticeDO.setContent(noticeForm.getContent());
        noticeDOMapper.updateByPrimaryKeySelective(noticeDO);
    }

    /**
     * 删除
     * @param noticeForm
     */
    public void deleteNotice(NoticeForm noticeForm) {
        noticeDOMapper.deleteByPrimaryKey(noticeForm.getId());
    }
}
