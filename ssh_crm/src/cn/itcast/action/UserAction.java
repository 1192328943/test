package cn.itcast.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

public class UserAction extends ActionSupport {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String login() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User userExist = userService.login(user);
		if (userExist != null) {
			HttpServletRequest request=ServletActionContext.getRequest();
			request.getSession().setAttribute("user", userExist);
			return "loginSuccess";
		} else {
			return "login";
		}
	}
}
