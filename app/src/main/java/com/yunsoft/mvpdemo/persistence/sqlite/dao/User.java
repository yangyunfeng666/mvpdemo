package com.yunsoft.mvpdemo.persistence.sqlite.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by yyf on 2018-04-13 11:31.
 */

@Entity //声明这个是一个表单
public class User {
@Id
private Long id;//定义数据存储id 必须是long Long

@NotNull //不能为空
private String text;

@Property(nameInDb = "COMMENT")//定义表字段名称
private String comment;

@Index(unique = true)//添加索引 唯一索引
private java.util.Date date;
 //如果是更新可以为空
private String num;

private String name;

@Generated(hash = 2141060647)
public User(Long id, @NotNull String text, String comment, java.util.Date date,
		String num, String name) {
	this.id = id;
	this.text = text;
	this.comment = comment;
	this.date = date;
	this.num = num;
	this.name = name;
}


@Generated(hash = 586692638)
public User() {
}


	@Override
	public String toString() {
		return "{id:"+id+",comment:"+comment+"num"+num+":name"+name+"}";
	}


	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return this.text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getComment() {
		return this.comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public java.util.Date getDate() {
		return this.date;
	}


	public void setDate(java.util.Date date) {
		this.date = date;
	}


	public String getNum() {
		return this.num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}




}
