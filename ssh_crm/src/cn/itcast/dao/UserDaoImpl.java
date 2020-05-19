package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
//private HibernateTemplate hibernateTemplate;

@Override
public User login(User user) {
	HibernateTemplate hibernateTemplate=this.getHibernateTemplate();
	List<User> list=(List<User>) hibernateTemplate.find("from User where username=? and password=?",user.getUsername(),user.getPassword());
	if(list!=null&&list.size()!=0) {
		return list.get(0);
	}else 
	return null;
}

@Override
public List<User> findAll() {
	// TODO Auto-generated method stub
	return (List<User>) this.getHibernateTemplate().find("from User");
}


}
