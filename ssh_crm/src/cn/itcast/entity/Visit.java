package cn.itcast.entity;

public class Visit {
private Integer vid;
private String visitContent;
public String getVisitContent() {
	return visitContent;
}
public void setVisitContent(String visitContent) {
	this.visitContent = visitContent;
}
public String getVisitAddress() {
	return visitAddress;
}
public void setVisitAddress(String visitAddress) {
	this.visitAddress = visitAddress;
}
private String visitAddress;
private User user;
private Customer customer;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public Integer getVid() {
	return vid;
}
public void setVid(Integer vid) {
	this.vid = vid;
}



}
