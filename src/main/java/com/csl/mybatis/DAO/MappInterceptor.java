package com.csl.mybatis.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.csl.mybatis.exception.MybatisDAOException;
import com.csl.mybatis.utils.MapperSqlHelper;
import com.csl.mybatis.utils.ReflectionUtils;
import com.csl.mybatis.utils.ResultMapsUtil;

@Intercepts({
	@Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }),
		@Signature(type = Executor.class, method = "update", args = {
			MappedStatement.class, Object.class }) })
public class MappInterceptor implements Interceptor {
	static {

	}
	private final static String _sql_regex = ".*MapperGD.*";
	private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(
			0);


	private void processIntercept(final Object[] queryArgs) {
		final MappedStatement ms = (MappedStatement) queryArgs[0];
		final Object parameter = queryArgs[1];
		String mapperSQL = ms.getBoundSql(parameter).getSql();
		boolean interceptor = mapperSQL.matches(_sql_regex);
		if (!interceptor) {
			return;
		}
		String className = ms.getResource();
		if (className.contains(".java")) {
			className = className.substring(0, className.indexOf(".java"));
			className = className.replaceAll("/", ".");
			Class<?> clazz = null;
			try {
				clazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}
			Class<?> entityClazz = ReflectionUtils.getSuperInterfaceGenricType(
					clazz, 0);
			String new_sql = MapperSqlHelper.getExecuSQL(entityClazz,
					mapperSQL, parameter);
			if (StringUtils.isBlank(new_sql)) {
				throw new MybatisDAOException("没有匹配的方法");
			}
			SqlSourceBuilder builder = new SqlSourceBuilder(
					ms.getConfiguration());
			SqlSource ss = builder.parse(new_sql, entityClazz,
					new HashMap<String, Object>(0));
			MappedStatement new_ms = copyFromMappedStatement(ms, ss,
					entityClazz, new_sql.contains("count"));
			queryArgs[0] = new_ms;
		}

	}
	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource, Class<?> clazz, boolean isType) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		// builder.keyProperty(ms.getKeyProperties());
		if (ms.getKeyProperties() != null
				&& ms.getKeyProperties().length != 0) {
			StringBuilder keyProperties = new StringBuilder();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1,
					keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		// setStatementTimeout()
		builder.timeout(ms.getTimeout());
		// setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		// setStatementResultMap()
		if (!isType) {
			builder.resultMaps(ResultMapsUtil.getResultMaps(ms, clazz));
		} else {
			List<ResultMap> resultMaps = new ArrayList<ResultMap>();
			ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(),
					ms.getId(), int.class, EMPTY_RESULTMAPPING).build();
			resultMaps.add(resultMap);
			builder.resultMaps(resultMaps);
		}
		builder.resultSetType(ms.getResultSetType());
		// setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}
}
