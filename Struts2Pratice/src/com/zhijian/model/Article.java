package com.zhijian.model;

import java.util.Date;
import java.util.List;

public class Article {

	private int id;
	private String author;
	private String title;
	private String content;
	private Date createTime;
	private int commentAmount;
	private List<String> comments;
	
	public Article(){
		
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
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
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
		private Date createTime;
		
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
		
		public Date getCreateTime() {
			return createTime;
		}
		
		public InsertBuilder setCreateTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}
		
		public Article build(){
			return new Article(this);
		}
	}
}
