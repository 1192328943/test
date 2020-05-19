package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.User;
import cn.itcast.entity.Visit;
import cn.itcast.service.CustomerService;
import cn.itcast.service.UserService;
import cn.itcast.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private Visit visit=new Visit();
	private VisitService visitService;
	private UserService userService;
	private CustomerService customerService;
public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
public String toAddPage() {
	List<Customer> listCustomer=customerService.findAll();
	List<User> listUser=userService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	ServletActionContext.getRequest().setAttribute("listUser", listUser);
	return "toAddPage";
}
public String add() {
	visitService.add(visit);
	return "add";
}
public String list() {
	List<Visit> list=visitService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "list";
	
}

@Override
public Visit getModel() {
	// TODO Auto-generated method stub
	return visit;
}
public String update() {
	List<Customer> listCustomer=customerService.findAll();
	List<User> listUser=userService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	ServletActionContext.getRequest().setAttribute("listUser", listUser);
	Visit v=visitService.findOne(visit.getVid());
	ServletActionContext.getRequest().setAttribute("visit", v);
	return "update";
}
public String updateVisit() {
	visitService.update(visit);
	return "updateVisit";
}

public String delete() {
	visitService.delete(visit);
	return "delete";
}
public String toSelectPage() {
	List<Customer> listCustomer=customerService.findAll();
	List<User> listUser=userService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	ServletActionContext.getRequest().setAttribute("listUser", listUser);
	return "toSelectPage";
}
public String moreCondition() {
	List<Visit> list=visitService.findMoreCondition(visit);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
}
}
