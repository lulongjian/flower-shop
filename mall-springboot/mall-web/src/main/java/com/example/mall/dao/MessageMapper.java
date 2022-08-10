package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.MessageDO;
import com.example.mall.domain.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper extends TkMapper<MessageDO> {

    List<MessageVO> findMessageByProductId(int productId);//根据商品id查询所有留言

    void addMessage(@Param("productId") int productId, @Param("text") String text, @Param("userId") int userId, @Param("date") Date date);//添加留言

    /**
     * 管理员查询所有留言
     *
     * @param text
     * @return
     */
    List<MessageDO> getMessageList(@Param("text") String text);
}
