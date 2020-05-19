package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao{

	@Override
	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);
		
	}

	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
	}

	@Override
	public Visit findOne(Integer vid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Visit.class, vid);
	}

	@Override
	public void update(Visit visit) {
		this.getHibernateTemplate().update(visit);
		
	}

	@Override
	public void delete(Visit visit) {
		this.getHibernateTemplate().delete(visit);
		
	}

	@Override
	public List<Visit> findMoreCondition(Visit visit) {
//		String hql="from Visit where 1=1";
//		List<Object> list=new ArrayList<Object>();
//		if(visit.getCustomer().getCid()!=null&&visit.getCustomer().getCid()>0) {
//			hql+=" and customer.cid=?";
//			list.add(visit.getCustomer().getCid());
//		}
//		if(visit.getUser().getUid()!=null&&visit.getUser().getUid()>0) {
//			hql+=" and user.uid=?";
//			list.add(visit.getUser().getUid());
//		}
//		
//		return (List<Visit>) this.getHibernateTemplate().find(hql, list.toArray());
  DetachedCriteria criteria=DetachedCriteria.forClass(Visit.class);
  if(visit.getCustomer().getCid()!=null&&visit.getCustomer().getCid()>0) {
		criteria.add(Restrictions.eq("customer.cid", visit.getCustomer().getCid()));
	}
	if(visit.getUser().getUid()!=null&&visit.getUser().getUid()>0) {
		criteria.add(Restrictions.eq("user.uid", visit.getUser().getUid()));
	}
	return (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
