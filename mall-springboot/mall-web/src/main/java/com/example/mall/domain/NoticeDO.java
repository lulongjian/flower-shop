package com.example.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "notice")
public class NoticeDO {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 标题
     */
    private String title;

//    /**
//     * 类型
//     */
//    private String type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 内容
     */
    private String content;
}