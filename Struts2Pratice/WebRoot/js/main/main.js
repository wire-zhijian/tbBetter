$(function(){
	
	function DateUtil(strDate){
		var date = new Date(strDate);
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(); 
	}
	
	$.ajax({
		url : '../json/article_getByCond.action',
		data : {
			
		},
		type : 'post',
		dataType : 'json',
		success : function(data){
			if(data.result){
				data.result.forEach(function(el, index){
					var artcle = ArticleContainer.newInstance({
						title : el.title,
						author : el.author,
						commentAmount : el.commentAmount,
						createTime : DateUtil(el.createTime),
						content : el.content
					});
					artcle.append("artcleContainer_div_index", true);
				});
			}
		},
		error : function(req, status, request){
			
		}
	});
});