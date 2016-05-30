package com.zhijian.action;

import java.util.Date;
import java.util.List;

import com.zhijian.dao.ArticleDao;
import com.zhijian.model.Article;

public class OperateArtcleAction {
	private Integer id;
	private String title;
	private String author;
	private String createTime;
	private String content;
	private Integer commentAmount;
	private List<Article> result;
	
	
	public List<Article> getResult() {
		return result;
	}

	public void setResult(List<Article> result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getCommentAmount() {
		return commentAmount;
	}
	
	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}
	
	public String insert(){
		try {
			Article.InsertBuilder builder = new Article.InsertBuilder().setCreateTime(new Date());
			if(title != null){
				builder.setTitle(title);
			}
			
			if(author != null){
				builder.setAuthor(author);
			}
			
			if(content != null){
				builder.setContent(content);
			}
			
			new ArticleDao().insert(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "insert";
	}
	
	
	public String getByCond() throws Exception{
		ArticleDao.ExtraCond extraCond = new ArticleDao.ExtraCond();
		if(this.id != null){
			extraCond.setId(id);
		}
		
		if(this.title != null){
			extraCond.setTitle(title);
		}
		
		if(this.commentAmount != null){
			extraCond.setCommentAmount(commentAmount);
		}
		
		if(this.author != null){
			extraCond.setAuthor(author);
		}
		
		if(this.content != null){
			extraCond.setContent(content);
		}
		
		if(this.createTime != null){
			extraCond.setCreateTime(createTime);
		}
		
		result = new ArticleDao().getByCond(extraCond);
		
		return "getByCond";
	}
}
