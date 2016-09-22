package com.zhijian.model;


public class Comment {
	private int id;
	private Integer authorId;
	private User author;
	private Integer referrId;
	private User referr;
	private String content;
	private String createTime;
	private Integer articleId;
	private Article article;
	private Status status;
	
	public Comment(){}
	public Comment(InsertBuilder builder){
		this.articleId = builder.articleId;
		this.authorId = builder.authorId;
		this.referrId = builder.referrId;
		this.content = builder.content;
		this.status = Status.valueOf(builder.status);
		this.createTime = builder.createTime;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Integer getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}



	public User getAuthor() {
		return author;
	}



	public void setAuthor(User author) {
		this.author = author;
	}



	public Integer getReferrId() {
		return referrId;
	}



	public void setReferrId(Integer referrId) {
		this.referrId = referrId;
	}



	public User getReferr() {
		return referr;
	}



	public void setReferr(User referr) {
		this.referr = referr;
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



	public Integer getArticleId() {
		return articleId;
	}



	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}



	public Article getArticle() {
		return article;
	}



	public void setArticle(Article article) {
		this.article = article;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public static enum Status{
		NORMAL(1, "正常"),
		DELETE(2, "删除");
		
		private int val;
		private String desc;
		
		Status(int val, String desc){
			this.val = val;
			this.desc = desc;
		}
		
		@Override
		public String toString() {
			return "the Status (" + 
				   "val = " + this.val + ", " + 
				   "desc = " + this.desc + ")";
		}
		
		public static Status valueOf(int val){
			for(Status status : values()){
				if(status.val == val){
					return status;
				}
			}
			throw new IllegalArgumentException("the value = (" + val + ") is invaild");
		}
		
		public int getVal(){
			return this.val;
		}
		
		public String getDesc(){
			return this.desc;
		}
	}
	
	public static class InsertBuilder{
		private Integer authorId;
		private Integer referrId;
		private String content;
		private String createTime;
		private Integer articleId;
		private Integer status;
		
		public Integer getAuthorId() {
			return authorId;
		}
		
		public InsertBuilder setAuthorId(Integer authorId) {
			this.authorId = authorId;
			return this;
		}
		
		public Integer getReferrId() {
			return referrId;
		}
		
		public InsertBuilder setReferrId(Integer referrId) {
			this.referrId = referrId;
			return this;
		}
		
		public String getContent() {
			return content;
		}
		
		public InsertBuilder setContent(String content) {
			this.content = content;
			return this;
		}
		
		public String getCreateTime() {
			return createTime;
		}
		
		public InsertBuilder setCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}
		
		public Integer getArticleId() {
			return articleId;
		}
		
		public InsertBuilder setArticleId(Integer articleId) {
			this.articleId = articleId;
			return this;
		}
		
		public Integer getStatus() {
			return status;
		}
		
		public InsertBuilder setStatus(Integer status) {
			this.status = status;
			return this;
		}

		public Comment builder(){
			return new Comment(this);
		}
		
	}
}
