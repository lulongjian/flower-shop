package com.example.mall.domain.form;

import lombok.Data;

import java.util.Date;

@Data
public class ProductTypeForm {
    /**
     * id
     */
    private Integer id;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 名称
     */
    private String name;
}
