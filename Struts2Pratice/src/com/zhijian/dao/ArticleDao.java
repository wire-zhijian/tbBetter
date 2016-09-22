package com.zhijian.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhijian.model.Article;
import com.zhijian.util.DBCon;

public class ArticleDao {

	public static class ExtraCond{
		private Integer id;
		private String title;
		private String author;
		private String createTime;
		private String content;
		private Integer commentAmount;
		private Integer status;
		private boolean isSelf;
		private Integer authorId;
		
		public void setIsSelf(boolean isSelf){
			this.isSelf = isSelf;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public void setAuthorId(Integer authorId){
			this.authorId = authorId;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public void setCommentAmount(Integer commentAmount) {
			this.commentAmount = commentAmount;
		}
		public void setStatus(Integer status){
			this.status = status;
		}
		
		@Override
		public String toString() {
			StringBuilder extraCond = new StringBuilder();
			if(this.isSelf){
				extraCond.append(" AND us.id = " + ServletActionContext.getRequest().getSession().getAttribute("userId"));
			}
			
			if(this.id != null){
				extraCond.append(" AND art.id = " + id);
			}
			
			if(this.author != null && !this.author.isEmpty()){
				extraCond.append(" AND art.author = " + author);
			}
			
			if(this.authorId != null){
				extraCond.append(" AND art.author_id = " + this.authorId);
			}
			
			if(this.commentAmount != null){
				extraCond.append(" AND art.comment_amount = " + commentAmount);
			}
			
			if(this.content != null && !this.content.isEmpty()){
				extraCond.append(" AND art.content = " + this.content);
			}
			
			if(this.createTime != null){
				extraCond.append(" AND art.ceate_time = " + this.createTime);
			}
			
			if(this.title != null){
				extraCond.append(" AND art.title = " + this.title);
			}
			
			if(this.status != null){
				extraCond.append(" AND art.status = " + this.status);
			}else{
				extraCond.append(" AND art.status = " + Article.Status.NORMAL.getValue());
			}
			
			return extraCond.toString();
		
		}
	}
	
	
	public int insert(Article.InsertBuilder builder)throws SQLException, Exception{
		String sql;
		sql = " INSERT INTO zhijian_blog.article(title, author, author_id, content, comment_amount,create_time) VALUES("
			+ "'" + builder.getTitle() + "', " 
			+ "'" + builder.getAuthor() + "', "
			+ builder.getAuthorId() + ", "
			+ "'" + builder.getContent() + "', "
			+ 0 + "," 
			+ "'" + builder.getCreateTime()  + "'); ";
		
		DBCon dbCon = new DBCon();
		int id = 0;
		try {
			dbCon.connect();
			dbCon.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			dbCon.rs = dbCon.stmt.getGeneratedKeys();
			if(dbCon.rs.next()){
				id = dbCon.rs.getInt(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			dbCon.disconnest();
		}
		
		return id;
	}
	
	
	
	public List<Article> getByCond(ExtraCond extraCond) throws Exception{
		String sql = " SELECT art.id, art.author, art.author_id, art.title, art.content, art.status, art.create_time, art.comment_amount FROM zhijian_blog.article art" + 
					 " JOIN zhijian_blog.user us ON art.author = us.name " + 
					 " WHERE 1 = 1 " + 
					 extraCond.toString() + 
					 " ORDER BY id DESC ";
		DBCon dbCon = new DBCon();
		List<Article> result = new ArrayList<Article>();
		try {
			dbCon.connect();
			dbCon.rs = dbCon.stmt.executeQuery(sql);
			while(dbCon.rs.next()){
				Article article = new Article();
				article.setAuthor(dbCon.rs.getString("author"));
				article.setAuthorId(dbCon.rs.getInt("author_id"));
				article.setCommentAmount(dbCon.rs.getInt("comment_amount"));
				article.setContent(dbCon.rs.getString("content"));
				article.setId(dbCon.rs.getInt("id"));
				article.setTitle(dbCon.rs.getString("title"));
				article.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dbCon.rs.getTimestamp("create_time")));
				result.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteById(int id) throws Exception{
		String sql = " UPDATE zhijian_blog.article SET" + 
					 " status = " + Article.Status.DELETE.getValue() +
				     " WHERE 1 = 1 " + 
					 " AND id = " + id;
		DBCon dbCon = new DBCon();
		int count = 0;
		try {
			dbCon.connect();
			dbCon.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			dbCon.rs = dbCon.stmt.getGeneratedKeys();
			if(dbCon.rs.next()){
				count = dbCon.rs.getInt(1);
			}
		} finally{
			dbCon.disconnest();
		}
		
		return count;
	}
}
