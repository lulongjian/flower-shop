package com.example.mall.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_notice")
public class UserNoticeDO {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 公告id
     */
    private Integer notice;

    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private Integer isRead;
}