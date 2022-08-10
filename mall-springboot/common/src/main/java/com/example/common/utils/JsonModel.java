package com.example.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonModel<T> implements Serializable {
	private Integer code = 0;	//状态码
    private String msg = "操作成功";
    private T data;
    private Long count;
}