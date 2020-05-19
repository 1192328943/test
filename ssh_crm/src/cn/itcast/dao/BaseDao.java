package cn.itcast.dao;

import java.util.List;

public interface BaseDao<T> {
void delete(T t);
void add(T t);
void update(T t);
T findOne(int id);
List<T> findAll();
}
