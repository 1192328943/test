package cn.itcast.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
private LinkManService linkManService;
private CustomerService customerService;
private LinkMan linkMan=new LinkMan();
public void setCustomerService(CustomerService customerService) {
	this.customerService = customerService;
}
public void setLinkManService(LinkManService linkManService) {
	this.linkManService = linkManService;
}
public String toAddPage() {
	 List<Customer> listCustomer=customerService.findAll();
	 ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	return "toAddPage";
}
/*
 * 需要上传文件（流）
 * 需要上传文件名称
 * （1）在action里面成员变量位置定义变量（命名规范）
 * 一个表示上传文件
 * 一个表示文件名称
 * （2）
 * 生成变量的get和set方法
 */
//1 上传文件
//变量的名称需要是表单里面文件上传项的name值
private File upload;
public File getUpload() {
	return upload;
}
public void setUpload(File upload) {
	this.upload = upload;
}
public String getUploadFileName() {
	return uploadFileName;
}
public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
}
//2 上传文件名称
//变量的名称需要是表单里面文件上传项的name值+FileName
private String uploadFileName;
public String addLinkMan() throws IOException {
	/*
	 * 可以封装联系人基本信息
	 * 但是有cid是客户id值，不能直接封装的
	 * 把cid封装LinkMan实体类里面customer对象里面
	 **/
	//1 原始方式实现
//	String scid=ServletActionContext.getRequest().getParameter("cid");
//	int cid=Integer.parseInt(scid);
//	Customer c=new Customer();
//	c.setCid(cid);
//	linkMan.setCustomer(c);
	if(upload!=null) {
		//写上传代码
		//在服务器文件夹里面创建文件
		File serverFile=new File("E:\\sshfileupload"+"/"+uploadFileName);
		
		//把上传的文件复制到服务器文件里面
		FileUtils.copyFile(upload, serverFile);
	}
	linkManService.addLinkMan(linkMan);
	return "addLinkMan";
}
public String listLinkMan() {
	List<LinkMan> list=linkManService.listLinkMan();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "listLinkMan";
}
public String update() {
	List<Customer> listCustomer=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	LinkMan linkMan1=linkManService.findOne(linkMan.getLinkid());
	ServletActionContext.getRequest().setAttribute("linkman", linkMan1);
	return "update";
}
public String updateLinkMan() {
	linkManService.updateLinkMan(linkMan);
	return "updateLinkMan";
	
}
public String delete() {
	linkManService.delete(linkMan);
	return "delete";
}
@Override
public LinkMan getModel() {
	// TODO Auto-generated method stub
	return linkMan;
}
public String toSelectPage() {
	List<Customer> list=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "toSelectPage";
}
public String moreCondition() {
	List<LinkMan> list=linkManService.findMoreCondition(linkMan);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
}
}
