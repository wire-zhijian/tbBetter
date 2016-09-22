package com.zhijian.action;

import org.apache.struts2.ServletActionContext;

import com.zhijian.dao.UserDao;
import com.zhijian.model.User;

public class OperateUserAction {

	private int id;
	private String username;
	private String password;
	private String email;
	private String brithDay;
	private int sex;
	private int age;
	private int status;
	private String selfDesc;
	private User user;
	private boolean success;
	private int visitId;
	
	
	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public String getSelfDesc() {
		return selfDesc;
	}

	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

	public String getBrithDay() {
		return brithDay;
	}

	public void setBrithDay(String brithDay) {
		this.brithDay = brithDay;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public String getById(){
		int id = (Integer)ServletActionContext.getRequest().getSession().getAttribute("userId");
		try {
			if(visitId == 0){
				user = new UserDao().getByCond(new UserDao.ExtraCond().setId(id)).get(0);
			}else{
				user = new UserDao().getByCond(new UserDao.ExtraCond().setId(visitId)).get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user";
	}
	
	public String update()throws Exception{
		User.UpdateBuilder builder = new User.UpdateBuilder((Integer)ServletActionContext.getRequest().getSession().getAttribute("userId"));
		if(this.age != 0){
			builder.setAge(age);
		}
		
		if(this.brithDay != null){
			builder.setBrithDay(brithDay);
		}
		
		if(this.email != null){
			builder.setEmail(email);
		}
		
		if(this.password != null){
			builder.setPassword(password);
		}
		
		if(this.sex != 0){
			builder.setSex(sex);
		}
		
		if(this.status != 0){
			builder.setStatus(User.Status.valueOf(status));
		}
		
		if(this.username != null){
			builder.setUsername(username);
		}
		
		if(this.selfDesc != null){
			builder.setSelfDesc(selfDesc);
		}
		
		try {
			new UserDao().update(builder);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user";
	}
}
