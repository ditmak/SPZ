package com.csl.util.DAO;

import java.io.Serializable;
import java.util.Collection;


public interface BaseDAO<T> {
	Serializable saveEntry(T entry);
	void updateEntry(T entry);
	void deleteEntry(Serializable entryId);
	T findEntry(Serializable entryId);
        T findEntryByName(String name);
	Collection<T> findAllEntry();
	Collection<T> findEntries(Serializable ...ids);
}
