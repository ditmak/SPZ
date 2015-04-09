package com.csl.mybatis.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultMapping.Builder;

import com.csl.mybatis.annotation.MyBatisColumn;

public class ResultMapsUtil {
	private static Map<String, List<ResultMap>> resultMaps = new HashMap<String, List<ResultMap>>();

	public static List<ResultMap> getResultMaps(MappedStatement ms,
			Class<?> clazz) {
		// 缓存中
		List<ResultMap> rml = resultMaps.get(clazz.getName());
		if (rml != null) {
			return rml;
		}
		List<ResultMapping> listMapping = getResultMappings(ms, clazz);
		// 构建resultMap list
		rml = new ArrayList<ResultMap>();
		ResultMap.Builder rmb = new ResultMap.Builder(ms.getConfiguration(),
				ms.getId(), clazz, listMapping);
		rml.add(rmb.build());
		resultMaps.put(clazz.getName(), rml);
		return rml;
	}

	// 构建resutlMapping list
	private static List<ResultMapping> getResultMappings(MappedStatement ms,
			Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<ResultMapping> listMapping = new ArrayList<ResultMapping>();
		for (Field field : fields) {
			field.setAccessible(true);
			if (!ReflectionUtils.isPrimitiveType(field.getType()))
				continue;
			Builder rmb = new Builder(ms.getConfiguration(), field.getName(),
					field.getName(), field.getType());
			MyBatisColumn column = field.getAnnotation(MyBatisColumn.class);
			if (column != null && StringUtils.isNotBlank(column.name())) {
				rmb.column(column.name());
			}
			listMapping.add(rmb.build());
		}
		return listMapping;
	};

}
