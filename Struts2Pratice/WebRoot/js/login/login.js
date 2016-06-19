$(function(){
	var debug = false;
	var isProcessing = false;
	$('#form_login').submit(function(){
		var username = $('#username_input_login').val();
		var password = $('#password_input_login').val();
		
		if(!username || debug){
			alert('请输入账号');
			return false;
		}
		
		if(!password || debug){
			alert('请输入密码');
			return false;
		}
		
		if(isProcessing){
			return false;
		}
		$('#loginBtn_input_login').val('登陆中..');
		isProcessing = true;
		return true;
	});
	
	
	$('#extraLoginBtn_input_login').click(function(){
		window.location.href = 'register.html';
	});
////	var username = $('#username_input_login');
////	var password = $('#password_input_login');
////	var loginBtn = $('#loginBtn_input_login');
////	var extraLoginBtn = $('#extraLoginBtn_input_login');
//	var debug = false;
//	var isProcessing = false;
//	$('#loginBtn_input_login').click(function(){
//		var username = $('#username_input_login').val();
//		var password = $('#password_input_login').val();
//		
//		if(!username || debug){
//			alert('请输入账号');
//			return;
//		}
//		
//		if(!password || debug){
//			alert('请输入密码');
//			return;
//		}
//		
//		if(isProcessing){
//			return;
//		}
//		$('#loginBtn_input_login').val('登陆中..');
//		isProcessing = true;
//		
//		$.ajax({
//			url : 'login/login',
//			type : 'post',
//			dataType : 'json',
//			data : {
//				username : username,
//				password : password
//			},
//			success : function(data){
//				$('#loginBtn_input_login').val('登陆');
//				isProcessing = false;
//				window.location.href = "pages/index.html";
//			},
//			error : function(req, status, err){
//				$('#loginBtn_input_login').val('登陆');
//				isProcessing = false;
//				alert(err);
//			}
//		});
//		
//		
//	});
//	
//	$('#extraLoginBtn_input_login').click(function(){
//		alert('暂时不支持游客访谈');
//	});
});