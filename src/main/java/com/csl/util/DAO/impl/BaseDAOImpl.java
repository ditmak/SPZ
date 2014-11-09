package com.csl.util.DAO.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.csl.util.DAO.BaseDAO;


public class BaseDAOImpl<T> extends HibernateDaoSupport implements BaseDAO<T> {
	private Class clazz ;
	public BaseDAOImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		clazz =(Class) parameterizedType.getActualTypeArguments()[0];
	}
	
	public void deleteEntry(Serializable id) {
		Object object = getSession().get(clazz, id);
		getHibernateTemplate().delete(object);
	}
	public T findEntry(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}
	public Serializable saveEntry(T t) {
		return getHibernateTemplate().save(t);
	}
	public void updateEntry(T t) {
		getHibernateTemplate().update(t);
	}
	public Collection<T> findAllEntry() {
		return (Collection<T>) getSession().createQuery("from "+clazz.getName()).list();
	}
	public Set<T> findEntries(Serializable[] ids) {
		StringBuilder idsString= new StringBuilder();
		for(Serializable s : ids){
			idsString.append(s).append(",");
		}
		String idss =idsString.substring(0, idsString.lastIndexOf(","));
		
		String idName = getSession().getSessionFactory().getClassMetadata(clazz).getIdentifierPropertyName();
		return new HashSet<T>(getSession().createQuery("from "+clazz.getName()+"  where "+idName+" in ("+idss+")").list());
	}
}
