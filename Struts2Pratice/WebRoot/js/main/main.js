$(function(){
	var _pagingSize = 5;
	var _visitId;
	function DateUtil(strDate){
		var date = new Date(Date.parse(strDate));
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(); 
	}
	
	
	if(getUrlSearchData('visit')){
		_visitId = getUrlSearchData('visit');
		$('#indexBtn_index').hide();
		$('#editBtn_index').hide();
		$('#mood_index').hide();
		$('#selfMes_index').hide();
		$('#visitIndexBtn_index').show();
		$('#return_index').show();
	}
	
	loadArticle();
	loadUserMes();
	
	var isProcessing = false;
	var container = document.getElementById('userMsgContainer_div_index');
	var containerTop = container.offsetTop;
	var containerLeft = container.offsetLeft;
	window.onscroll = function(){
//		console.log(window.scrollY);
//		console.log(document.getElementById('userMsgContainer_div_index').offsetTop);
//		console.log(document.getElementById('userMsgContainer_div_index').clientTop);
		if(window.scrollY > containerTop && !isProcessing){
			isProcessing = true;
			container.style.position = 'fixed';
			container.style.top = 0 + 'px';
			container.style.left = containerLeft + 'px';
		}
		
		if(window.scrollY < containerTop && isProcessing){
			isProcessing = false;
			container.style.position = 'relative';
			container.style.top = 0 + 'px';
			container.style.left = 0 + 'px';
		}
	};
	
	function getUrlSearchData(key){
		var search = window.location.search;
		if(search){
			search = search.substring(1, search.length);
		}
		var value;
		search.split('&').forEach(function(el, index){
			if(el && el.split('=').length > 0 && el.split('=')[0] == key){
				value = el.split('=')[1];
			}
		});
		
		return value;
	}
	
	function loadArticle(){
		var start = $('.choosenPagingBar').length > 0 ? $('.choosenPagingBar')[0].getAttribute('data-value') * _pagingSize : 0;
		var limit = start + _pagingSize;
		$.ajax({
			url : '../json/article/getByCond',
			data : {
				start : start,
				limit : limit,
				isSelf : 'true',
				visitId : _visitId
			},
			type : 'post',
			dataType : 'json',
			success : function(data){
				if(data.result){
					$('#artcleContainer_div_index').html('');
					data.result.forEach(function(el, index){
						var artcle = ArticleContainer.newInstance({
							id : el.id,
							title : el.title,
							author : el.author,
							commentAmount : el.commentAmount,
							createTime : DateUtil(el.createTime),
							content : el.content
						});
						artcle.append("artcleContainer_div_index", true);
					});
					
					if(!$('.choosenPagingBar').length > 0){
						PagingBar.newInstance({
							totalSize : data.totalSize,
							length : _pagingSize,
							renderTo : $('#paging_div_index'),
							afterClick : loadArticle
						});
					}
				}
			},
			error : function(req, status, request){
				alert('文章读取失败  请检查网络是否通畅');
			}
		});
	}
	
	function loadUserMes(){
		$.ajax({
			url : '../json/user/getById',
			type : 'post',
			data: {
				visitId :  _visitId
			},
			dataType : 'json',
			success : function(data){
				if(data.user){
					$('#username_span_index').text(data.user.username);
					$('#birthday_span_index').text(data.user.brithDay);
					$('#selfDesc_span_index').text(data.user.selfDesc);
				}
			},
			error : function(){
				
			}
		});
		
	}
	
});