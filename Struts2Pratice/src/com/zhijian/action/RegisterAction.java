package com.zhijian.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhijian.dao.UserDao;
import com.zhijian.model.User;
import com.zhijian.model.User.Status;

public class RegisterAction {
	private int id;
	private String username;
	private String password;
	private String email;
	private int sex;
	private int age;
	private Status status;
	private User user;
	private String brithday;
	
	
	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String execute(){
		List<User> data = new ArrayList<User>();
		try {
			if(username != null && !username.isEmpty()){
				data.addAll(new UserDao().getByCond(new UserDao.ExtraCond().setUsername(username).setStatus(User.Status.NORMAL)));
			}
			
			if(data.size() > 0){
				throw new Exception("用户已经存在");
			}
			
			
			int id = new UserDao().insert(new User.InsertBuilder().setAge(age)
														 .setBrithDay(brithday)
														 .setEmail(email)
														 .setUsername(username)
														 .setPassword(password)
														 .setSex(sex)
														 .setStatus(User.Status.NORMAL));
			
			if(id > 0){
				user = new UserDao().getByCond(new UserDao.ExtraCond().setId(id)).get(0);
			}
			
			ServletActionContext.getRequest().getSession().setAttribute("userId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "register";
	}
}
