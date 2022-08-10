package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.UserNoticeDOMapper;
import com.example.mall.domain.UserNoticeDO;
import com.example.mall.domain.vo.UserNoticeVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNoticeService {

    @Autowired
    private UserNoticeDOMapper userNoticeDOMapper;

    /**
     * 查询系统通知
     *
     * @param page
     * @param number
     * @param userId
     * @param isRead
     * @return
     */
    public JsonModel getUserNoticeList(Integer page, Integer number, String userId, Integer isRead) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<UserNoticeVO> voPage = (Page<UserNoticeVO>) userNoticeDOMapper.getUserNoticeList(userId, isRead);
        PageInfo<UserNoticeVO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 已读
     *
     * @param userId
     * @param noticeId
     * @return
     */
    public void readUserNotice(String userId, Integer noticeId) {
        UserNoticeDO userNoticeDO = new UserNoticeDO();
        userNoticeDO.setNotice(noticeId);
        userNoticeDO.setUserId(Integer.parseInt(userId));
        UserNoticeDO userNoticeDO1 = userNoticeDOMapper.selectOne(userNoticeDO);
        if (null == userNoticeDO1) {
            userNoticeDO.setIsRead(1);
            userNoticeDOMapper.insert(userNoticeDO);
        }
    }
}
