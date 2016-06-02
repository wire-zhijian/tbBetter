package com.zhijian.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
		public void setId(Integer id) {
			this.id = id;
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
		
		@Override
		public String toString() {
			StringBuilder extraCond = new StringBuilder();
			if(this.id != null){
				extraCond.append(" AND id = " + id);
			}
			
			if(this.author != null && !this.author.isEmpty()){
				extraCond.append(" AND author = " + author);
			}
			
			if(this.commentAmount != null){
				extraCond.append(" AND comment_amount = " + commentAmount);
			}
			
			if(this.content != null && !this.content.isEmpty()){
				extraCond.append(" AND content = " + this.content);
			}
			
			if(this.createTime != null){
				extraCond.append(" AND ceate_time = " + this.createTime);
			}
			
			if(this.title != null){
				extraCond.append(" AND title = " + this.title);
			}
			
			return extraCond.toString();
		
		}
	}
	
	
	public int insert(Article.InsertBuilder builder)throws SQLException, Exception{
		String sql;
		sql = " INSERT INTO zhijian_blog.article(title, author, content, comment_amount,create_time) VALUES("
			+ "'" + builder.getTitle() + "', " 
			+ "'" + builder.getAuthor() + "', "
			+ "'" + builder.getContent() + "', "
			+ 0 + "," 
			+ "'" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(builder.getCreateTime())  + "'); ";
		
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
		String sql = " SELECT * FROM zhijian_blog.article " + 
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
				article.setCommentAmount(dbCon.rs.getInt("comment_amount"));
				article.setContent(dbCon.rs.getString("content"));
				article.setId(dbCon.rs.getInt("id"));
				article.setTitle(dbCon.rs.getString("title"));
				article.setCreateTime(dbCon.rs.getTimestamp("create_time"));
				result.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteById(int id) throws Exception{
		String sql = " DELETE FROM zhijian_blog.article WHERE " + 
					 " id = " + id;
		DBCon dbCon = new DBCon();
		int count = 0;
		try {
			dbCon.connect();
			dbCon.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			dbCon.rs = dbCon.stmt.getGeneratedKeys();
			if(dbCon.rs.next()){
				count = dbCon.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
