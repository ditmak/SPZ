package com.csl.mybatis.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Conditions extends HashMap<String, Object> {

	private static final long serialVersionUID = 2832989175146235236L;

	private List<String> conditions = new LinkedList<String>();
	private String orderBy = null;
	private int count = 0;

	public String getOrderBy() {
		return orderBy;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public Conditions equal(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " = #{args" + count++ + "}");
		return this;
	}

	public Conditions notEqual(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " != #{args" + count++ + "}");
		return this;
	}

	public Conditions gte(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " >= #{args" + count++ + "}");
		return this;
	}

	public Conditions gt(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " > #{args" + count++ + "}");
		return this;
	}

	public Conditions lte(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " <= #{args" + count++ + "}");
		return this;
	}

	public Conditions lt(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " < #{args" + count++ + "}");
		return this;
	}

	public Conditions islike(String column, Object value) {
		this.put("args" + count, value);
		conditions.add(column + " like concat('%',#{args" + count++ + "},'%')");
		return this;
	}

	public Conditions isNull(String column) {
		conditions.add(column + "  is null");
		return this;
	}

	public Conditions isNotNull(String column, Object value) {
		conditions.add(column + " is not null");
		return this;
	}

	public Conditions orderBy(String column, boolean sortDESC) {
		orderBy = column;
		if (sortDESC) {
			orderBy += "  desc";
		} else {
			orderBy += "  asc";
		}
		return this;
	}

	public Conditions orderBy(String column) {
		orderBy(column, false);
		return this;
	}

}
