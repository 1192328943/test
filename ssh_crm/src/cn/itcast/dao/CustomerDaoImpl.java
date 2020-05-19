package cn.itcast.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.org.apache.xml.internal.security.transforms.Transforms;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;
import cn.itcast.entity.LinkMan;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

//	@Override
//	public void add(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//		
//	}
//private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("all")
//	@Override
//	public List<Customer> findAll() {
//		// TODO Auto-generated method stub
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}
//
//	@Override
//	public Customer findOne(int cid) {
//		
//		return this.getHibernateTemplate().get(Customer.class, cid);
//	}
//
//	@Override
//	public void delete(Customer c) {
//		this.getHibernateTemplate().delete(c);
//		
//	}
//
//	@Override
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//		
//	}

	@Override
	public List<Customer> findPage(int begin, int pageSize) {
		//1使用hibernate底层代码实现
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session =sessionFactory.getCurrentSession();
//		Query query=session.createQuery("from Customer");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		List<Customer> list=query.list();
		//2 使用离线对象和hibernatetemplate的方法实现
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
//		criteria.setProjection(Projections.rowCount());
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public int findCount() {
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().find("select count(*) from Customer");
		if(list!=null&&list.size()!=0) {
			Object obj=list.get(0);
			Long lobj= (Long) obj;
			int count=lobj.intValue();
			return count;
			
		}
		return 0;
	}

	@Override
	public List<Customer> findCondition(Customer customer) {
		//1 传统
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session =sessionFactory.getCurrentSession();
//		Query query=session.createQuery("from Customer where custName like ?");
//		query.setParameter(0, "%"+customer.getCustName()+"%");
//		List<Customer> list= query.list();
		//2  find方法
//		List<Customer> list=(List<Customer>) this.getHibernateTemplate().
//		find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		//3 detachedcriteria对象 
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		
		return list;
	}

	@Override
	public List<Customer> findMoreCondition(Customer customer) {
    String hql="from Customer where 1=1";
    List<Object> list=new ArrayList<Object>();
//    if(customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())) {
//    	hql+=" and custLevel=?";
//    	list.add(customer.getCustLevel());
//    }
//    if(customer.getCustName()!=null&&!"".equals(customer.getCustName())) {
//    	hql+=" and custName=?";
//    	list.add(customer.getCustName());
//    }
//    if(customer.getCustSource()!=null&&!"".equals(customer.getCustSource())) {
//    	hql+=" and custSource=?";
//    	list.add(customer.getCustSource());
//    }
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
//		  if(customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())) {
//		    	criteria.add(Restrictions.like("custLevel", "%"+customer.getCustLevel()+"%"));
//		    }
		    if(customer.getCustName()!=null&&!"".equals(customer.getCustName())) {
		    	criteria.add(Restrictions.like("custName",  "%"+customer.getCustName()+"%"));
		    }
		    if(customer.getCustSource()!=null&&!"".equals(customer.getCustSource())) {
		    	criteria.add(Restrictions.like("custSource",  "%"+customer.getCustSource()+"%"));
		    }
    
		return  (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("all")
	@Override
	public List<Dict> findAllDictLevel() {
		
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	@Override
	public List findCountSource() {
		Session session=this.getSessionFactory().getCurrentSession();
		
		SQLQuery query=session.createSQLQuery("select count(*) as num ,custSource from s_customer group by custSource");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list=query.list();
		return list;
	}

	@Override
	public List findCountLevel() {
		Session session=this.getSessionFactory().getCurrentSession();
		SQLQuery query=session.createSQLQuery("SELECT c.num,d.dname FROM \r\n" + 
				"(SELECT COUNT(*) AS num,custLevel FROM s_customer GROUP BY custLevel) c,\r\n" + 
				"s_dict d WHERE c.custLevel=d.did");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list=query.list();
		return list;
	}

	




}
