package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao{

	@Override
	public void addLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		
	}

	@Override
	public List<LinkMan> listLinkMan() {
		List<LinkMan> list =(List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
		return list;
	}

	@Override
	public LinkMan findOne(Integer linkid) {
		LinkMan linkMan=this.getHibernateTemplate().get(LinkMan.class, linkid);
		return linkMan;
	}

	@Override
	public void updateLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
		
	}

	@Override
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
		
	}

	@Override
	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
//		String hql="from LinkMan where 1=1";
//		List<Object> list=new ArrayList<Object>();
//		if(linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())) {
//			hql+=" and lkmName=?";
//			list.add(linkMan.getLkmName());
//		}
//		if(linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0) {
//			hql+=" and customer.cid=?";
//			list.add(linkMan.getCustomer().getCid());
//		}
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		
		if(linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())) {
			
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		if(linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0) {
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
