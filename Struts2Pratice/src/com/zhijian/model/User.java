package com.zhijian.model;


public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private long brithDay;
	private int sex;
	private int age;
	private Status status;
	
	public User(){}
	
	public User(int id){
		this.id = id;
	}
	
	public User(InsertBuilder builder){
		this.username = builder.username;
		this.password = builder.password;
		this.email = builder.email;
		this.brithDay = builder.brithDay;
		this.sex = builder.sex;
		this.age = builder.age;
		this.status = builder.status;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
	
	public static enum Sex{
		MAL(1,"男"),
		FEMAL(2,"女");
		private int val;
		private String desc;
		Sex(int val, String desc){
			this.val = val;
			this.desc = desc;
		}
		
		public String toString() {
			return "Sex( " +
				   "val = " + val +
				   ", desc = " + desc;
		}
		
		public Sex valueOf(int val){
			for(Sex sex : values()){
				if(sex.val == val){
					return sex;
				}
			}
			
			throw new IllegalArgumentException(" the val = (" + val + ") is invalid");
		}
		
		public int getVal() {
			return val;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
	public static enum Status{
		NORMAL(1, "正常"),
		STOPPING(2, "停用");
		
		private final int val;
		private final String desc;
		Status(int val, String desc){
			this.val = val;
			this.desc = desc;
		}
		
		@Override
		public String toString() {

			return "status("+
					"val=" + this.val + 
					", desc=" + this.desc + ")";
		}
		
		public static Status valueOf(int val){
			for(Status status : values()){
				if(status.val == val){
					return status;
				}
			}
			throw new IllegalArgumentException("the value = (" + val + ") is invalid");
		}
		
		public int getVal() {
			return val;
		}
		
		public String getDesc() {
			return desc;
		}
	}
	
	
	public class InsertBuilder{
		private String username;
		private String password;
		private String email;
		private long brithDay;
		private int sex;
		private int age;
		private Status status;
		
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
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
		
		public User builder(){
			return new User(this);
		}
	}
	
	public class UpdateBuilder{
		private int id;
		private String username;
		private String password;
		private String email;
		private long brithDay;
		private int sex;
		private int age;
		private Status status;
		
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
		public UpdateBuilder(int id){
			this.id = id;
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
	}
}
