package com.example.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "flower_language")
public class FlowerLanguageDO {
    @Id
    private Integer id;

    /**
     * 展示日期
     */
    @Column(name = "re_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reTime;

    /**
     * 中文标语
     */
    private String title1;

    /**
     * 英文标语
     */
    private String title2;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}