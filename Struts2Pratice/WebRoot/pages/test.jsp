<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>test</title>
  </head>
  <script type="text/javascript" src="../js/Util/jquery-1.8.2.min.js"></script>
  <body>
    <form action="login_save.action" method="post">
    	用户名：<input type="text" name="username" placeholder="请输入名字"/>
    	<input type="submit" value="提交数据"/>
    </form>
    
    <script type="text/javascript">
    	/*$(function(){
    		$.ajax({
    			url : '/Struts2Pratice/json/login_save.action',
    			type : 'post',
    			dataType : 'json',
    			data : {
    				username : 'zhijian'
    			},
    			success : function(data){
    				console.log(data);
    			},
    			error : function(){
    				
    			}
    		});
    	});*/
    </script>
  </body>
</html>
