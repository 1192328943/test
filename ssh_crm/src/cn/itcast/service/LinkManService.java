package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;
@Transactional
public class LinkManService {
private LinkManDao linkManDao;

public void setLinkManDao(LinkManDao linkManDao) {
	this.linkManDao = linkManDao;
}


public void addLinkMan(LinkMan linkMan) {
	linkManDao.addLinkMan(linkMan);
	
}


public List<LinkMan> listLinkMan() {
	// TODO Auto-generated method stub
	return linkManDao.listLinkMan();
}


public LinkMan findOne(Integer linkid) {
	return linkManDao.findOne(linkid);
	
}


public void updateLinkMan(LinkMan linkMan) {
	linkManDao.updateLinkMan(linkMan);
	
}


public void delete(LinkMan linkMan) {
	linkManDao.delete(linkMan);
	
}


public List<LinkMan> findMoreCondition(LinkMan linkMan) {
	// TODO Auto-generated method stub
	return linkManDao.findMoreCondition(linkMan);
}
}
