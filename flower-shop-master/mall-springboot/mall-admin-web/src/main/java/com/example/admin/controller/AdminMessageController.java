package com.example.admin.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/message")
@Slf4j
public class AdminMessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 管理员查询所有留言
     */
    @RequestMapping(value = "getMessageList", method = RequestMethod.POST)
    public JsonModel getMessageList(Integer page, Integer number, String text) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = messageService.getMessageList(page, number, text);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.GET)
    public JsonModel deleteMessage(@PathVariable Integer id) {
        return messageService.deleteMessage(id);
    }
}
