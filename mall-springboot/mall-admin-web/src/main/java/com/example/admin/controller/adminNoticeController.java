package com.example.admin.controller;

import com.example.admin.service.AdminNoticeService;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.NoticeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/notice")
public class adminNoticeController {

    @Autowired
    private AdminNoticeService adminNoticeService;

    /**
     * 查询系统公告通知列表
     *
     * @return
     */
    @RequestMapping(value = "getNoticeList", method = RequestMethod.POST)
    public JsonModel getNoticeList(Integer page, Integer number, NoticeForm noticeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = adminNoticeService.getNoticeList(page, number, noticeForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 新增系统通知
     *
     * @param noticeForm
     * @return
     */
    @RequestMapping(value = "addNotice", method = RequestMethod.POST)
    public JsonModel addNotice(NoticeForm noticeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            adminNoticeService.addNotice(noticeForm);
            jsonModel.setMsg("新增成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }


    /**
     * 更新系统通知
     *
     * @param noticeForm
     * @return
     */
    @RequestMapping(value = "updateNotice", method = RequestMethod.POST)
    public JsonModel updateNotice(NoticeForm noticeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            adminNoticeService.updateNotice(noticeForm);
            jsonModel.setMsg("更新成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 删除通知
     *
     * @param noticeForm
     * @return
     */
    @RequestMapping(value = "deleteNotice", method = RequestMethod.POST)
    public JsonModel deleteNotice(NoticeForm noticeForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            adminNoticeService.deleteNotice(noticeForm);
            jsonModel.setMsg("删除成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }
}
