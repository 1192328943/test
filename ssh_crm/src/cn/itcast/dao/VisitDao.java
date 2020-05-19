package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

	Visit findOne(Integer vid);

	void update(Visit visit);

	void delete(Visit visit);

	List<Visit> findMoreCondition(Visit visit);

}
