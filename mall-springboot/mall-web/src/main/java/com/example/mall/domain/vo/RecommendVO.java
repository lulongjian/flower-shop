package com.example.mall.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class RecommendVO {

    private Integer id; // id

    private Integer productId; // 商品id

    private String title;//商品标题

    private Integer sort; // 排序

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reTime; //推荐日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime; // 创建时间

    private String permission;//商品授权
    private Integer number;//商品数量
    private String des;//商品描述
    private Integer userId;//用户id
    private String imgUrl;//图片地址
    private String imgUrlBase64;//图片base64
    private BigDecimal price;//价格
    private BigDecimal oldPrice;//原价格
    private Integer collectNum;//收藏数
    private Integer sallyNum;//销量
    private String type; // 排序
}
