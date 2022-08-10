package com.example.mall.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class RecommendForm {

	private Integer id; // id

	private Integer productId; // 商品id

	private Integer sort; // 排序

	private String reTime; //推荐日期
}
