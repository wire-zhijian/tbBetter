$(function(){
//	$('#username_input_selfMes')
	var keyCodes = {
		ENTER : {val : 13 , desc : 'enter'}	
	};
	$('#usernameToEdit_a_selfMsg').click(function(){
		$('#usernameToEdit_a_selfMsg').hide();
		$('#usernameEdit_span_selfMsg').hide();
		$('#username_input_selfMsg').show();
		$('#username_input_selfMsg').focus();
		$('#username_input_selfMsg').val($('#usernameEdit_span_selfMsg').html());
	});
	
	$('#username_input_selfMsg').blur(function(){
		$('#username_input_selfMsg').hide();
		$('#usernameToEdit_a_selfMsg').show();
		$('#usernameEdit_span_selfMsg').show();
		$('#usernameEdit_span_selfMsg').html($('#username_input_selfMsg').val());
	});
	
	$('#username_input_selfMsg').keyup(function(e){
		if(e.keyCode == keyCodes.ENTER.val){
			$('#username_input_selfMsg').blur();
		}
	});
	
	$('#passwordToEdit_a_selfMsg').click(function(){
		$('#passwordToEdit_a_selfMsg').hide();
		$('#passwordEdit_span_selfMsg').hide();
		$('#password_input_selfMsg').show();
		$('#password_input_selfMsg').focus();
		$('#password_input_selfMsg').val($('#passwordEdit_span_selfMsg').html());
	});
	
	$('#password_input_selfMsg').blur(function(){
		$('#password_input_selfMsg').hide();
		$('#passwordToEdit_a_selfMsg').show();
		$('#passwordEdit_span_selfMsg').show();
		$('#passwordEdit_span_selfMsg').html($('#password_input_selfMsg').val());
	});
	
	$('#password_input_selfMsg').keyup(function(e){
		if(e.keyCode == keyCodes.ENTER.val){
			$('#password_input_selfMsg').blur();
		}
	});

	$('#emailToEdit_a_selfMsg').click(function(){
		$('#emailToEdit_a_selfMsg').hide();
		$('#emailEdit_span_selfMsg').hide();
		$('#email_input_selfMsg').show();
		$('#email_input_selfMsg').focus();
		$('#email_input_selfMsg').val($('#emailEdit_span_selfMsg').html());
	});
	
	$('#email_input_selfMsg').blur(function(){
		$('#email_input_selfMsg').hide();
		$('#emailToEdit_a_selfMsg').show();
		$('#emailEdit_span_selfMsg').show();
		$('#emailEdit_span_selfMsg').html($('#email_input_selfMsg').val());
	});
	
	$('#email_input_selfMsg').keyup(function(e){
		if(e.keyCode == keyCodes.ENTER.val){
			$('#email_input_selfMsg').blur();
		}
	});

	$('#birthdayToEdit_a_selfMsg').click(function(){
		$('#birthdayToEdit_a_selfMsg').hide();
		$('#birthdayEdit_span_selfMsg').hide();
		$('#birthday_input_selfMsg').show();
		$('#birthday_input_selfMsg').focus();
		$('#birthday_input_selfMsg').val($('#birthdayEdit_span_selfMsg').html());
	});
	
	$('#birthday_input_selfMsg').blur(function(){
		$('#birthday_input_selfMsg').hide();
		$('#birthdayToEdit_a_selfMsg').show();
		$('#birthdayEdit_span_selfMsg').show();
		$('#birthdayEdit_span_selfMsg').html($('#birthday_input_selfMsg').val());
	});
	
	$('#selfDescToEdit_a_selfMsg').click(function(){
		$('#selfDescToEdit_a_selfMsg').hide();
		$('#selfDescEdit_span_selfMsg').hide();
		$('#selfDesc_input_selfMsg').show();
		$('#selfDesc_input_selfMsg').focus();
		$('#selfDesc_input_selfMsg').val($('#selfDescEdit_span_selfMsg').html());
	});
	
	$('#selfDesc_input_selfMsg').blur(function(){
		$('#selfDesc_input_selfMsg').hide();
		$('#selfDescToEdit_a_selfMsg').show();
		$('#selfDescEdit_span_selfMsg').show();
		$('#selfDescEdit_span_selfMsg').html($('#selfDesc_input_selfMsg').val());
	});
	
	$('#birthday_input_selfMsg').keyup(function(e){
		if(e.keyCode == keyCodes.ENTER.val){
			$('#birthday_input_selfMsg').blur();
		}
	});
	
	$('#backBtn_input_selfMsg').click(function(){
		window.location.href = 'index.html';
	});
	
	$('#saveBtn_input_selfMsg').click(function(){
		updateMsg();
	});
	
	initMsg();
	function initMsg(){
		$.ajax({
			url : '../json/user/getById',
			type : 'post',
			data: {
				 
			},
			dataType : 'json',
			success : function(data){
				if(data.user){
					$('#usernameEdit_span_selfMsg').html(data.user.username);
					$('#passwordEdit_span_selfMsg').html(data.user.password);
					$('#emailEdit_span_selfMsg').html(data.user.email);
					$('#birthdayEdit_span_selfMsg').html(data.user.brithDay);
					$('#selfDescEdit_span_selfMsg').html(data.user.selfDesc);
					$('[name=sex]').each(function(index, el){
						if(el.value == data.user.sex){
							el.checked = 'checked';
						}
					});
				}
			},
			error : function(){
				
			}
		});
	}
	
	
	function updateMsg(){
		var username = $('#usernameEdit_span_selfMsg').html();
		var password = $('#passwordEdit_span_selfMsg').html();
		var email = $('#emailEdit_span_selfMsg').html();
		var birthday = $('#birthdayEdit_span_selfMsg').html();
		var selfDesc = $('#selfDescEdit_span_selfMsg').html();
		var sex;
		$('[name=sex]').each(function(index, el){
			if(el.checked == true){
				sex = el.value;
			}
		});
		
		$.ajax({
			url : '../json/user/update',
			type : 'post',
			data : {
				username : username,
				password : password,
				email : email,
				brithDay : birthday,
				sex : sex,
				selfDesc : selfDesc
			},
			dataType : 'json',
			success : function(data){
				if(data.success){
					alert('保存成功');
				}
			},
			error : function(){
				
			}
		});
	}
});