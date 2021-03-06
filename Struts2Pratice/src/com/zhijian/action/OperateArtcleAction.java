package com.zhijian.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhijian.core.CoreUtil;
import com.zhijian.dao.ArticleDao;
import com.zhijian.dao.UserDao;
import com.zhijian.model.Article;
import com.zhijian.model.User;

public class OperateArtcleAction {
	private Integer id;
	private String title;
	private String author;
	private String createTime;
	private String content;
	private Integer commentAmount;
	private List<Article> result;
	private Integer start;
	private Integer limit;
	private Integer totalSize;
	private String isSelf;
	private Integer visitId;
	
	

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

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
			Article.InsertBuilder builder = new Article.InsertBuilder().setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			if(title != null){
				builder.setTitle(title);
			}
			
			if(content != null){
				builder.setContent(content);
			}
			
			String userId = String.valueOf(ServletActionContext.getRequest().getSession().getAttribute("userId"));
			
			if(userId != null && userId != "null"){
				List<User> result = new UserDao().getByCond(new UserDao.ExtraCond().setId(Integer.valueOf(userId)));
				if(result.size() > 0){
					builder.setAuthor(result.get(0).getUsername());
					builder.setAuthorId(Integer.parseInt(userId));
				}
			}
			
			new ArticleDao().insert(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "insert";
	}
	
	
	public String getByCond() throws Exception{
		ArticleDao.ExtraCond extraCond = new ArticleDao.ExtraCond();
		
		if(Boolean.parseBoolean(this.isSelf) && visitId == null){
			extraCond.setIsSelf(Boolean.parseBoolean(this.isSelf));
		}
		
		if(this.visitId != null){
			extraCond.setAuthorId(visitId);;
		}
		
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
		
		totalSize = result.size();
		
		if(start != null && !start.equals("") && limit != null && !limit.equals("")){
			result = CoreUtil.DatePaging(result, true, start, limit);
		}
		
		return "getByCond";
	}
	
	
	public String delete(){
		if(id != null){
			try {
				new ArticleDao().deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "delete";
	}
}
