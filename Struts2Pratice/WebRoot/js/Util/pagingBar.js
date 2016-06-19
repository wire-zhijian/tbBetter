(function(){
	function CreatePaggingBar(param){
		var _param = param || {
			borderColor : null,
			barWidth : null,
			totalSize : null,
			renderTo : null,
			length : null,
			afterClick : null
		};
		var pages;
		if(_param.totalSize){
			pages = Math.floor(_param.totalSize / _param.length);
			if(_param.totalSize % _param.length){
				pages += 1;
			}
		}else{
			alert('读取不到数据总条数');
			return;
		}
		
		var temp = [];
		temp.push("<ul>");
		for(var i = 0; i < pages; i++){
			temp.push('<li ' + (i == 0 ? "class=\'choosenPagingBar\'" : "") + 'data-type="pagingCompeng" style="cursor:pointer;margin-right:5px;width:38px;height:38px;border:2px solid #504C4C;line-height:38px;text-align:center;color:#504C4C;float:left;" data-value="' + i + '">' + (i + 1) + '</li>');
		}
		temp.push("</ul>");
		
		$(_param.renderTo).html(temp.join(''));

		$('[data-type=pagingCompeng]').click(function(){
//			$(this).css('background', '#292727').siblings().css('background', 'transparent');
			$(this).addClass('choosenPagingBar').siblings().removeClass('choosenPagingBar');
//			alert(this.getAttribute('data-value'));
			if(_param.afterClick && typeof _param.afterClick == 'function'){
				_param.afterClick();
			}
		});
	}
	
	PagingBar = {
		newInstance : function(param){
			return new CreatePaggingBar(param);
		}
	};
})();