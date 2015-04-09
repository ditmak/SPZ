package com.csl.mybatis.utils;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.csl.mybatis.annotation.MyBatisColumn;
import com.csl.mybatis.annotation.MybatisTable;
import com.csl.mybatis.exception.MybatisDAOException;

public class MapperSqlHelper {
	private MapperSqlHelper() {
	};

	private static MapperSqlHelper helper = new MapperSqlHelper();
	public static final Class<? extends Annotation> MYBATISTABLE = MybatisTable.class;
	public static final Class<? extends Annotation> MYBATISCOLUMN = MyBatisColumn.class;
	public static Map<String, String> IDSqls = new HashMap<String, String>();

	/**
	 * 传入mapper接口class
	 * 
	 * @param mapperclazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private String insertEntityWithNull(Class<?> clazz, Object param) {
		return insertEntity(clazz, param, Boolean.FALSE);
	}

	private String insertEntity(Class<?> clazz, Object param) {
		return insertEntity(clazz, param, Boolean.TRUE);
	}

	private String insertEntity(Class<?> clazz, Object param,
			Boolean withoutNull) {
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			INSERT_INTO(antable.value());
		else
			INSERT_INTO(clazz.getSimpleName());
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (withoutNull && field.get(param) == null) {
					continue;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new MybatisDAOException("参数"
						+ param.getClass().getSimpleName() + "与接口定义"
						+ clazz.getSimpleName() + "不同");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new MybatisDAOException("field  " + field.getName()
						+ "不允许访问异常---应该不会出现");
			}
			if (field.isAnnotationPresent(MYBATISCOLUMN)) {
				MyBatisColumn anColumn = (MyBatisColumn) field
						.getAnnotation(MYBATISCOLUMN);
				// 如果字段是忽略字段
				if (anColumn.isInsertIngore()) {
					continue;
				}
				if (StringUtils.isBlank(anColumn.name())) {
					VALUES(field.getName(), "#{" + field.getName() + "}");
				} else {
					VALUES(anColumn.name(), "#{" + field.getName() + "}");
				}
			} else {
				VALUES(field.getName(), "#{" + field.getName() + "}");
			}
		}
		return SQL();
	}

	/**
	 * 传入mapper接口class
	 * 
	 * @param mapperclazz
	 * @return
	 */
	private String updateEntityById(Class<?> clazz, Object param,
			Boolean withoutNull) {
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			UPDATE(antable.value());
		else
			UPDATE(clazz.getSimpleName());
		Field[] fields = clazz.getDeclaredFields();
		boolean isSetID = false;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (withoutNull && field.get(param) == null) {
					continue;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new MybatisDAOException("参数"
						+ param.getClass().getSimpleName() + "与接口定义"
						+ clazz.getSimpleName() + "不同");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new MybatisDAOException("field  " + field.getName()
						+ "不允许访问异常---应该不会出现");
			}
			String fieldName = field.getName();
			MyBatisColumn anColumn = (MyBatisColumn) field
					.getAnnotation(MYBATISCOLUMN);
			if (anColumn != null && StringUtils.isNotBlank(anColumn.name()))
				fieldName = anColumn.name();
			if (anColumn != null && anColumn.isID()) {
				WHERE(fieldName + " = #{" + field.getName() + "}");
			} else {
				SET(fieldName + " = #{" + field.getName() + "}");
				isSetID = true;
			}
		}
		if (!isSetID) {
			throw new RuntimeException("实体变量没有设置ID字段值");
		}
		return SQL();
	}

	private String findEntityByConditions(Class<?> clazz, Conditions con) {
		setSelectSql(clazz);
		List<String> conditions = con.getConditions();
		for (String condition : conditions) {
			WHERE(condition);
		}
		if (con.getOrderBy() != null) {
			ORDER_BY(con.getOrderBy());
		}

		return SQL();
	}

	private String findEntityById(Class<?> clazz) {
		String sql = IDSqls.get("findID." + clazz.getName());
		if (sql != null)
			return sql;
		setSelectSql(clazz);
		if (!setIDCon(clazz)) {
			throw new RuntimeException("没有查询到ID属性");
		}
		sql = SQL();
		IDSqls.put("findID." + clazz.getName(), sql);
		return sql;
	}

	private void setSelectSql(Class<?> clazz) {
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		SELECT("*");
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			FROM(antable.value());
		else
			FROM(clazz.getSimpleName());
	}

	private boolean setIDCon(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		boolean falg = false;
		for (Field file : fields) {
			file.setAccessible(true);
			if (file.isAnnotationPresent(MyBatisColumn.class)) {
				MyBatisColumn anColumn = (MyBatisColumn) file
						.getAnnotation(MYBATISCOLUMN);
				if (anColumn.isID()) {// 判断字段不为主键
					falg = true;
					if (StringUtils.isNotBlank(anColumn.name())) {
						WHERE(anColumn.name() + "= #{0}");
					} else {
						WHERE(file.getName() + "= #{0}");
					}
				}
			}
		}
		return falg;
	}

	public String deleteById(Class<?> clazz) {
		String sql = IDSqls.get("deleteID." + clazz.getName());
		if (sql != null)
			return sql;
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			DELETE_FROM(antable.value());
		else
			DELETE_FROM(clazz.getSimpleName());
		if (!setIDCon(clazz)) {
			throw new RuntimeException("没有查询到ID属性");
		}
		sql = SQL();
		IDSqls.put("deleteID." + clazz.getName(), sql);
		return sql;
	}

	public String queryByVo(Class<?> clazz, Object args) {
		setSelectSql(clazz);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(args) == null) {
					continue;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new MybatisDAOException("参数"
						+ args.getClass().getSimpleName() + "与接口定义"
						+ clazz.getSimpleName() + "不同");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new MybatisDAOException("field  " + field.getName()
						+ "不允许访问异常---应该不会出现");
			}
			String fieldName = field.getName();
			MyBatisColumn anColumn = (MyBatisColumn) field
					.getAnnotation(MYBATISCOLUMN);
			if (anColumn != null && StringUtils.isNotBlank(anColumn.name()))
				fieldName = anColumn.name();
			WHERE(fieldName + " = #{" + field.getName() + "}");

		}
		return SQL();
	}

	public String count(Class<?> clazz, Conditions con) {
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		SELECT(" count(1)");
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			FROM(antable.value());
		else
			FROM(clazz.getSimpleName());
		List<String> conditions = con.getConditions();
		for (String condition : conditions) {
			WHERE(condition);
		}
		return SQL();
	}

	public String deleteByCondition(Class<?> clazz, Conditions con) {
		MybatisTable antable = (MybatisTable) clazz.getAnnotation(MYBATISTABLE);
		BEGIN();
		if (antable != null && StringUtils.isNotBlank(antable.value()))
			DELETE_FROM(antable.value());
		else
			DELETE_FROM(clazz.getSimpleName());
		List<String> conditions = con.getConditions();
		if (conditions.size() == 0) {
			throw new RuntimeException("没有设置条件");
		}
		for (String condition : conditions) {
			WHERE(condition);
		}
		return SQL();
	}

	public static String getExecuSQL(Class<?> clazz, String mapperDBsql,
			Object param) {
		if (mapperDBsql.equals("MapperGD.find.entitys")) {
			return helper.findEntityByConditions(clazz, (Conditions) param);// 条件查询实体列表
		} else if (mapperDBsql.equals("MapperGD.find.entityById")) {
			return helper.findEntityById(clazz);// id查询实体
		} else if (mapperDBsql.equals("MapperGD.insert.entity.null")) {
			return helper.insertEntity(clazz, param);// 保存单一实体
		} else if (mapperDBsql.equals("MapperGD.insert.entity.notNull")) {
			return helper.insertEntityWithNull(clazz, param);// 保存单一实体
		} else if (mapperDBsql.equals("MapperGD.update.entity.notNull")) {
			return helper.updateEntityById(clazz, param, Boolean.TRUE);// 保存单一实体
		} else if (mapperDBsql.equals("MapperGD.update.entity.null")) {
			return helper.updateEntityById(clazz, param, Boolean.FALSE);// 保存单一实体
		} else if (mapperDBsql.equals("MapperGD.delete.id")) {
			return helper.deleteById(clazz);
		} else if (mapperDBsql.equals("MapperGD.delete.condition")) {
			return helper.deleteByCondition(clazz, (Conditions) param);
		} else if (mapperDBsql.equals("MapperGD.count.condition")) {
			return helper.count(clazz, (Conditions) param);
		} else if (mapperDBsql.equals("MapperGD.find.entity.queryByVo")) {
			return helper.queryByVo(clazz, param);
		}
		return null;
	}

}
