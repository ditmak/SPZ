package com.csl.mybatis.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.csl.demo.entities.Persion;

@Repository("persionDAO")
public interface PersionDAO extends IGenericDao<Persion, Integer> {
	List<Persion> queryPersion();

	List<Persion> queryPersions(Map<String, Object> map);

	void batchInsert(List<Persion> list);
}
