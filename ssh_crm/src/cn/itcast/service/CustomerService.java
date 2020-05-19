package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelAttribute;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;
import cn.itcast.entity.PageBean;
@Transactional
public class CustomerService {
private CustomerDao customerDao;

public void setCustomerDao(CustomerDao customerDao) {
	this.customerDao = customerDao;
}

public void add(Customer customer) {
	// TODO Auto-generated method stub
	customerDao.add(customer);
}

public List<Customer> findAll() {
	
	return customerDao.findAll();
}

public Customer findOne(int cid) {
	// TODO Auto-generated method stub
	return customerDao.findOne(cid);
}

public void delete(Customer c) {
	customerDao.delete(c);
	
}

public void update(Customer customer) {
	// TODO Auto-generated method stub
	customerDao.update(customer);
}
//封装分页数据到pageBean对象里面
public PageBean listPage(Integer currentPage) {
	PageBean pageBean=new PageBean();
	pageBean.setCurrentPage(currentPage);
	int totalCount=customerDao.findCount();
	pageBean.setTotalCount(totalCount);
	int pageSize=3;
	pageBean.setPageSize(pageSize);
	int totalPage=0;
	if(totalCount%pageSize==0	) {
		totalPage=totalCount/pageSize;
	}else {
		totalPage=totalCount/pageSize+1;
	}
	pageBean.setTotalPage(totalPage);
	int begin=(currentPage-1)*pageSize;
	pageBean.setBegin(begin);
	List<Customer> list=customerDao.findPage(begin,pageSize);
	pageBean.setList(list);
	return pageBean;
}

public List<Customer> findCondition(Customer customer) {
	// TODO Auto-generated method stub
	return customerDao.findCondition(customer);
}

public List<Customer> findMoreCondition(Customer customer) {
	// TODO Auto-generated method stub
	return customerDao.findMoreCondition(customer);
}

public List<Dict> findAllDictLevel() {
	// TODO Auto-generated method stub
	return customerDao.findAllDictLevel();
}

public List findCountSource() {
	// TODO Auto-generated method stub
	return customerDao.findCountSource();
}

public List findCountLevel() {
	// TODO Auto-generated method stub
	return customerDao.findCountLevel();
}








}
