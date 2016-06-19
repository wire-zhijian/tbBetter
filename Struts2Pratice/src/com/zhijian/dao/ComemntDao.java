package com.zhijian.dao;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.zhijian.model.Comment;
import com.zhijian.model.Comment.InsertBuilder;
import com.zhijian.util.DBCon;

public class ComemntDao {
	/**
	 * extraCond of Comment for search
	 * @author Administrator
	 */
	public static class ExtraCond{
		private Integer articleId;
		public ExtraCond setArticleId(int articleId){
			this.articleId = articleId;
			return this;
		}
		
		@Override
		public String toString() {
			StringBuilder extraCond = new StringBuilder();
			if(articleId != null){
				extraCond.append(" AND article_id = " + this.articleId);
			}
			return extraCond.toString();
		}
	}
	
	/**
	 * insert comment by builder
	 * @param builder
	 * @return
	 * @throws Exception
	 */
	public int insert(InsertBuilder builder)throws Exception{
		String sql;
		sql = " INSERT INTO zhijian_blog.comment(`author_id`, `referr_id`, `content`, `create_time`, `status`, `article_id`) VALUES(" +
			  builder.getAuthorId() + ", "+
			  builder.getReferrId() + ", "+ 
			  "'" + builder.getContent() + "'" + ", " +
			  "'" + builder.getCreateTime() + "'" + ", " + 
			  builder.getStatus() + 
			  builder.getArticleId() +
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
		}finally{
			dbCon.disconnest();
		}
		
		return id;
	}
	
	
	/**
	 * get comments by extraCond
	 * @param extraCond
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getByCond(ExtraCond extraCond)throws Exception{
		String sql;
		sql = " SELECT * FROM zhijian_blog.comemnt " + 
			  " WHERE 1 = 1 " + 
			  extraCond.toString();
		DBCon dbCon = new DBCon();
		List<Comment> result = new ArrayList<Comment>();
		try {
			dbCon.rs = dbCon.stmt.executeQuery(sql);
			while(dbCon.rs.next()){
				Comment comment = new Comment();
				comment.setArticleId(dbCon.rs.getInt("article_id"));
				comment.setAuthorId(dbCon.rs.getInt("author_id"));
				comment.setReferrId(dbCon.rs.getInt("referr_id"));
				comment.setId(dbCon.rs.getInt("id"));
				comment.setContent(dbCon.rs.getString("content"));
				comment.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dbCon.rs.getTimestamp("create_time")));
				comment.setStatus(Comment.Status.valueOf(dbCon.rs.getInt("status")));
				result.add(comment);
			}
		}finally{
			dbCon.disconnest();
		}
		
		return result;
	}
	
	
	/**
	 * delete Comment by id
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id)throws Exception{
		String sql;
		sql = " UPDATE zhijian_blog SET status = " + Comment.Status.DELETE + 
			  " WHERE id = " + id;
		DBCon dbCon = new DBCon();
		try {
			dbCon.connect();
			dbCon.stmt.executeUpdate(sql);
		}finally{
			dbCon.disconnest();
		}
	}
}
