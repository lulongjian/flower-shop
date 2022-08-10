package com.example.mall.service;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.MessageMapper;
import com.example.mall.dao.UserMapper;
import com.example.mall.domain.MessageDO;
import com.example.mall.domain.UserDO;
import com.example.mall.domain.vo.MessageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据商品id查询所有留言
     */
    public List<MessageVO> findMessageByProductId(int productId) {
        return messageMapper.findMessageByProductId(productId);
    }

    /**
     * 添加留言
     */
    public void addMessage(int productId, String text, String name) {
        //根据用户名查询用户id
        UserDO userDO = new UserDO();
        userDO.setName(name);
        UserDO userDO1 = userMapper.selectOne(userDO);
        messageMapper.addMessage(productId, text, userDO1.getId(), new Date());
    }

    /**
     * 管理员查询所有留言
     *
     * @param page
     * @param number
     * @param text
     * @return
     */
    public JsonModel getMessageList(Integer page, Integer number, String text) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<MessageDO> voPage = (Page<MessageDO>) messageMapper.getMessageList(text);
        PageInfo<MessageDO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 删除
     *
     * @param id
     */
    public JsonModel deleteMessage(int id) {
        JsonModel jsonModel = new JsonModel();
        try {
            messageMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(5000);
            jsonModel.setMsg("操作失败");
        }
        return jsonModel;
    }
}
