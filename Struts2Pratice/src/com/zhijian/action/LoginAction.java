package com.zhijian.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhijian.dao.UserDao;
import com.zhijian.dao.UserDao.ExtraCond;
import com.zhijian.model.User;

public class LoginAction {

	private String username;
	private String password;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String login(){
		ExtraCond extraCond = new UserDao.ExtraCond();
		extraCond.setUsername(username);
		extraCond.setPassword(password);
		try {
			List<User> result = new UserDao().getByCond(extraCond);
			if(result.size() > 0){
				user = result.get(0);
				ServletActionContext.getRequest().getSession().setAttribute("userId", user.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginSuccess";
	}
}
