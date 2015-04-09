package com.csl.demo.entities;

import java.util.Date;

import com.csl.mybatis.annotation.MyBatisColumn;

public class Persion {
	private String name;
	@MyBatisColumn(isID = true)
	private int id;
	@MyBatisColumn(name = "birthday1")
	private Date birthDay;

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Persion [name=" + name + ", id=" + id + ", birthDay="
				+ birthDay + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
