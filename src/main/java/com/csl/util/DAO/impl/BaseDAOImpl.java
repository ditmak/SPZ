package com.csl.util.DAO.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.csl.util.DAO.BaseDAO;

public class BaseDAOImpl<T> extends HibernateDaoSupport implements BaseDAO<T> {
    private Class clazz;

    public BaseDAOImpl() {
	ParameterizedType parameterizedType = (ParameterizedType) getClass()
		.getGenericSuperclass();
	this.clazz = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void deleteEntry(Serializable id) {
	Object object = getSession().get(this.clazz, id);
	getHibernateTemplate().delete(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findEntry(Serializable id) {
	return (T) getHibernateTemplate().get(this.clazz, id);
    }

    @Override
    public T findEntryByName(String name) {
	return (T) getSession()
		.createQuery(
			"from " + this.clazz.getName() + " t where t.name=?")
			.setString(0, name).uniqueResult();
    }

    @Override
    public Serializable saveEntry(T t) {
	return getHibernateTemplate().save(t);
    }

    @Override
    public void updateEntry(T t) {
	getHibernateTemplate().update(t);
    }

    @Override
    public Collection<T> findAllEntry() {
	return getSession().createQuery("from " + this.clazz.getName()).list();
    }

    @Override
    public Set<T> findEntries(Serializable... ids) {
	StringBuilder idsString = new StringBuilder();
	for (Serializable s : ids) {
	    idsString.append(s).append(",");
	}
	String idss = idsString.substring(0, idsString.lastIndexOf(","));

	String idName = getSession().getSessionFactory()
		.getClassMetadata(this.clazz).getIdentifierPropertyName();
	return new HashSet<T>(getSession().createQuery(
		"from " + this.clazz.getName() + "  where " + idName + " in ("
			+ idss + ")").list());
    }
}
