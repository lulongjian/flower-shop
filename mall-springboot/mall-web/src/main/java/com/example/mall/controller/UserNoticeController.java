package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.AddressDO;
import com.example.mall.domain.form.AddressForm;
import com.example.mall.service.AddressService;
import com.example.mall.service.UserNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("notice")
public class UserNoticeController {

    @Autowired
    private UserNoticeService userNoticeService;

    /**
     * 系统公告
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserNoticeList", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel getUserNoticeList(Integer page, Integer number, String userId, Integer isRead) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = userNoticeService.getUserNoticeList(page, number, userId, isRead);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 已读
     *
     * @param userId
     * @param noticeId
     * @return
     */
    @RequestMapping(value = "/readUserNotice", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel readUserNotice(String userId, Integer noticeId) {
        JsonModel jsonModel = new JsonModel();
        try {
            userNoticeService.readUserNotice(userId, noticeId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }
}
