package com.zhijian.model;

import java.util.List;

public class Article {

	private int id;
	private String author;
	private String title;
	private String content;
	private String createTime;
	private int commentAmount;
	private List<String> comments;
	private int authorId;
	
	public Article(){
		
	}
	
	
	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public Article(int id){
		this.id = id;
	}
	
	public Article(InsertBuilder builder){
		this.author = builder.author;
		this.content = builder.content;
		this.title = builder.title;
		this.createTime = builder.createTime;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public int getCommentAmount() {
		return commentAmount;
	}
	
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}
	
	public List<String> getComments() {
		return comments;
	}
	
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	public List<String> addComments(String comment){
		this.comments.add(comment);
		return this.comments;
	}
	
	public static class InsertBuilder{
		private String author;
		private String title;
		private String content;
		private String createTime;
		private int authorId;
		
		
		public int getAuthorId() {
			return authorId;
		}

		public void setAuthorId(int authorId) {
			this.authorId = authorId;
		}

		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}
		
		public String getCreateTime() {
			return createTime;
		}
		
		public InsertBuilder setCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}
		
		public Article build(){
			return new Article(this);
		}
	}
	
	public static enum Status{
		NORMAL(1, "正常"),
		DELETE(0, "已删除");
		
		private final int val;
		private final String desc;
		
		Status(int val, String desc){
			this.val = val;
			this.desc = desc;
		}
		
		@Override
		public String toString() {
			return "status = (" + 
				   "val = " + val + 
				   "desc = " + desc + ")";
		}
		
		public Status valueOf(int val){
			for(Status status : values()){
				if(status.val == val){
					return status;
				}
			}
			throw new IllegalArgumentException("the Article (value = " + val + ") is invalid ");
		}
		
		public int getValue(){
			return val;
		}
		
		public String getDesc(){
			return desc;
		}
	}

}
