$(function(){
	$('#registerBtn_input_register').click(function(){
		var username =  $('#username_input_register').val();
		var password =  $('#password_input_register').val();
		var email =  $('#email_input_register').val();
		var brithday =  $('#birthday_input_register').val();
		var sexRadio = $('[name=sex]'); 
		var sex;
		sexRadio.each(function(index, el){
			if($(el).attr('checked') == 'checked'){
				sex = $(el).val();
			}
		});
		
		if(!username){
			alert('请输入用户名');
			return;
		}
		
		if(!password){
			alert('请输入密码');
			return;
		}
		
		if(!sex){
			alert('还没选择性别');
			return;
		}
		
		if(!email){
			alert('请输入邮箱');
			return;
		}
		
		if(!brithday){
			alert('请输入生日');
			return;
		}
		
		$.ajax({
			url : 'json/register',
			type : 'post',
			data : {
				username : username,
				password : password,
				email : email,
				brithday : brithday,
				sex : sex
			},
			dataType : 'json',
			success : function(data){
				if(data.user){
					window.location.href = "pages/index.html";
				}
			},
			error : function(){
				
			}
		});
	});
});