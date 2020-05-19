package cn.itcast.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.Dict;
import cn.itcast.entity.PageBean;
import cn.itcast.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
private Customer customer;
 
private Integer currentPage;



	public Integer getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}



	public void setCustomer(Customer customer) {
	this.customer = customer;
}



	private CustomerService customerService;

	

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}



	public String list() {
		List<Customer> list=customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
			return "list";
		}
	public String toAddPage() {
		List<Dict> list=customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toAddPage";
	}
	public String add() {
		customerService.add(customer);
		return "add";
	}
	public String delete() {
		int cid=customer.getCid();
	Customer c= customerService.findOne(cid);
	if(c!=null) {
	customerService.delete(c);	
	}
	return "delete";
	}


	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	public String showCustomer() {
		int id=customer.getCid();
		Customer c=customerService.findOne(id);
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}
	public String update() {
		customerService.update(customer);
		return "update";
	}
	public String listPage() {
		PageBean pageBean=customerService.listPage(currentPage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listPage";
	}
	public String listCondition() {
		if(customer.getCustName()!=null&&!"".equals(customer.getCustName())) {
	List<Customer> list=customerService.findCondition(customer);
	ServletActionContext.getRequest().setAttribute("list", list);
	
		}
		else {
			list();
		}
		return "list";
	}
	public String moreCondition() {
		List<Customer> list=customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	public String toSelectPage() {
		return "toSelectPage";
	}
	public String countSource() {
		List list=customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
public String countLevel() {
	List list=customerService.findCountLevel();
	ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
	
}
