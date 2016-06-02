package com.zhijian.dao;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhijian.model.User;
import com.zhijian.model.User.InsertBuilder;
import com.zhijian.model.User.Status;
import com.zhijian.util.DBCon;

public class UserDao {
	public static class ExtraCond{
		private int id;
		private String username;
		private String password;
		private String email;
		private long brithDay;
		private int sex;
		private int age;
		private Status status;
		public int getId() {
			return id;
		}
		public ExtraCond setId(int id) {
			this.id = id;
			return this;
		}
		public String getUsername() {
			return username;
		}
		public ExtraCond setUsername(String username) {
			this.username = username;
			return this;
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
		public long getBrithDay() {
			return brithDay;
		}
		public void setBrithDay(long brithDay) {
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
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
		@Override
		public String toString() {
			StringBuilder extraCond = new StringBuilder();
			
			if(this.id != 0){
				extraCond.append(" AND id = " + this.id);
			}
			
			if(this.age != 0){
				extraCond.append(" AND age = " + this.age);
			}
			
			if(this.brithDay != 0){
				String date = new SimpleDateFormat("yyyy-MM-hh").format(new Date(this.brithDay));
				extraCond.append(" AND birthday = '" + date + "' ");
			}
			
			if(this.email != null){
				extraCond.append(" AND email = '" + this.email + "' ");
			}
			
			if(this.password != null){
				extraCond.append(" AND password = '" + this.password + "' ");
			}
			
			if(this.sex != 0){
				extraCond.append(" AND sex = " + this.sex);
			}
			
			if(this.status != null){
				extraCond.append(" AND status = " + this.status.getVal());
			}
			
			return extraCond.toString();
		}
	}
	
	/**
	 * insert the user by builder
	 * @param builder
	 * @return
	 * @throws Exception
	 */
	public int insert(InsertBuilder builder)throws Exception{
		String sql = " INSERT INTO zhijian_blog.user(name,password,email,birthday,sex,age,status)VALUES( " +
					 builder.getUsername() + ", " + 
					 builder.getPassword() + ", " + 
					 builder.getEmail() + ", " + 
					 builder.getBrithDay() + ", " + 
					 builder.getSex() + ", " + 
					 builder.getAge() + ", " + 
					 builder.getStatus().getVal() + 
					 ")";
		DBCon dbCon = new DBCon();
		int id = 0;
		try {
			dbCon.connect();
			dbCon.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			dbCon.rs = dbCon.stmt.getGeneratedKeys();
			if(dbCon.rs.next()){
				id = dbCon.rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	/**
	 * get the users by extraCond
	 * @param extraCond
	 * @return
	 * @throws Exception
	 */
	public List<User> getByCond(ExtraCond extraCond)throws Exception{
		String sql = " SELECT * FROM zhijian_blog.user " + 
					 " WHERE 1 = 1 " + 
					 extraCond.toString();
		
		DBCon dbCon = new DBCon();
		List<User> users = new ArrayList<User>();
		try {
			dbCon.connect();
			dbCon.rs = dbCon.stmt.executeQuery(sql);
			while(dbCon.rs.next()){
				User user = new User();
				user.setId(dbCon.rs.getInt("id"));
				user.setAge(dbCon.rs.getInt("age"));
				user.setBrithDay(dbCon.rs.getDate("birthday").getTime());
				user.setEmail(dbCon.rs.getString("email"));
				user.setSex(dbCon.rs.getInt("sex"));
				user.setPassword(dbCon.rs.getString("password"));
				user.setStatus(User.Status.valueOf(dbCon.rs.getInt("status")));
				user.setUsername(dbCon.rs.getString("name"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
}

