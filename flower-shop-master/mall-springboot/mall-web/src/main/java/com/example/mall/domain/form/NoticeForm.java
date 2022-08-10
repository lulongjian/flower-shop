package com.example.mall.domain.form;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class NoticeForm {
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;
}
