package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.LinkMan;

public interface LinkManDao{

	void addLinkMan(LinkMan linkMan);

	List<LinkMan> listLinkMan();

	LinkMan findOne(Integer linkid);

	void updateLinkMan(LinkMan linkMan);

	void delete(LinkMan linkMan);

	List<LinkMan> findMoreCondition(LinkMan linkMan);

	

	

}
