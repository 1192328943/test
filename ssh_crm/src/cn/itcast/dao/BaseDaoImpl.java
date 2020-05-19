package cn.itcast.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
private Class pclass;
	public BaseDaoImpl() {
		//第一步 得到当前运行类Class
		Class clazz=this.getClass();
		//第二部 得到运行类的父类的参数化类型BaseDaoImpl<Customer>
		// Type getGenericSuperclass
		
		Type type=clazz.getGenericSuperclass();
		//使用Type子接口 ParameterizedType
		ParameterizedType ptype=(ParameterizedType) type;
		//第三步 得到实际类型参数<Customer> 里面的customer
		//Type[] getActualTypeArguments
	Type[] types=ptype.getActualTypeArguments();
	Class tclass=(Class) types[0];
	this.pclass=tclass;
	}
	@SuppressWarnings("all")
	@Override
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(pclass, id);	
	}

	@Override
	public List<T> findAll() {
		
		return (List<T>) this.getHibernateTemplate().find("from "+pclass.getSimpleName());
	}
	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	

}
