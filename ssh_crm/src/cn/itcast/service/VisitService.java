package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.VisitDao;
import cn.itcast.entity.Visit;
@Transactional
public class VisitService {
private VisitDao visitDao;

public void setVisitDao(VisitDao visitDao) {
	this.visitDao = visitDao;
}

public void add(Visit visit) {
	visitDao.add(visit);
	
}

public List<Visit> findAll() {
	// TODO Auto-generated method stub
	return visitDao.findAll();
}

public Visit findOne(Integer vid) {
	// TODO Auto-generated method stub
	return visitDao.findOne(vid);
}

public void update(Visit visit) {
	// TODO Auto-generated method stub
	visitDao.update(visit);
}

public void delete(Visit visit) {
	// TODO Auto-generated method stub
	visitDao.delete(visit);
}

public List<Visit> findMoreCondition(Visit visit) {
	// TODO Auto-generated method stub
	return visitDao.findMoreCondition(visit);
}


}
