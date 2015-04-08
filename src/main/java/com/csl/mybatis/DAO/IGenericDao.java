package com.csl.mybatis.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.csl.mybatis.utils.Conditions;

public interface IGenericDao<T, PK> {

	@Select("MapperGD.find.entityById")
	public T findEntityById(PK id);

	@Select("MapperGD.find.entitys")
	public List<T> findEntity(Conditions con);

	@Insert("MapperGD.insert.entity.null")
	public int insertEntity(T t);

	@Insert("MapperGD.insert.entity.notNull")
	public int insertEntityWithoutNull(T t);

	@Update("MapperGD.update.entity.notNull")
	public int updateEntityByIdWithoutNull(T entity);

	@Update("MapperGD.update.entity.null")
	public int updateEntityById(T entity);

	@Delete("MapperGD.delete.id")
	public int deleteById(PK id);

	@Delete("MapperGD.delete.condition")
	public int deleteByCondition(Conditions con);

	@Select("MapperGD.count.condition")
	public int countByCondition(Conditions con);

	@Select("MapperGD.find.entity.queryByVo")
	public List<T> queryByVo(T t);

}
