(function(){
    function createArticleContainer(params){
        var param = params || {
                id : null,
        		title : null,            //标题
                content : null,         //内容
                author : null,          //作者
                createTime : null,		//创建时间
//                seeAmount : null,       //浏览次数  暂时不提供
                commentAmount : null  //评论次数
//                forWardAmount : null   //转发次数  暂时不提供
            };

        var container = '<article style="width:100%;margin-top:10px;">'+
                        '<div class="artcleHead">'+
                        '<div style="float:left;background:red;height:60px;width:2px;"></div>'+
                        '<div style="float:left;width:calc(100% - 4px);height:60px;">'+
                        '<h3 class="artcleTitle" style="text-indent: 5px;" data-type="artcle_title">' + param.title + '</h3>'+
                        '<div style="width:95%;border-top:1px dashed #AF3F08;margin:6px auto;"></div>'+
                        '<div style="width:100%;">'+
                        '<div style="float:left;width:20%;padding-left:4px;border-right:1px solid #AF3F08;height:20px;">'+
                        '<span class="artcleFont">作者：</span>'+
                        '<span class="artcleFont" data-type="artcle_author">' + param.author + '</span>'+
                        '</div>'+
                        '<div style="float:left;width:48%;padding-left:4px;border-right:1px solid #AF3F08;height:20px;">'+
                        '<span class="artcleFont">创建时间：</span>'+
                        '<span class="artcleFont" data-type="artcle_createTime">' + param.createTime + '</span>'+
                        '</div>'+
                        '<div style="float:left;width:20%;padding-left:4px;border-right:1px solid #AF3F08;height:20px;">'+
                        '<span class="artcleFont" data-type="showComment_artivleCon" style="cursor:pointer;">评论：</span>'+
                        '<span class="artcleFont">【<span data-type="artcle_commentAmount">' + param.commentAmount + '</span>】</span>'+
                        '</div>'+
                        '<span data-type="showDelete_articleCon" style="margin-left:2%;display:inline-block;width:20px;height:20px;position:relative;background:url(\'../images/delete.png\') no-repeat center;cursor:pointer;">'+
                        '<span data-type="deleteBtn" style="color:#AF3F08;width: 46px;height: 22px;border-radius: 5px;text-align: center;line-height: 22px;display:none;position:absolute;background:#0E0E5D;right:-26px;bottom:-16px;" name=\'' + (param.id ? param.id : '') + '\'>删除</span>'+
                        '</span>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '<div style="background:#171717;width:100%;min-height:100px;max-height:200px;margin:10px 0;">'+
                        '<p style="min-height:100px;max-height:200px;color:#666;text-indent:10px;">' + param.content + '</p>'+
                        '</div>'+
                        '<div data-type="commentContainer_article"style="display:none;background:#171717;width:100%;min-height:60px;max-height:300px;">' +
                        '<input data-type="commentContents_article"　type="text" placeholder="输入评论" style="text-indent:10px;float:left;margin-top:5px;margin-left:5%;width:80%;height:30px;background:#000;color:#ccc;border:none;"/>' +
//						<input type="text" name="title" style="background:#000;border-color:#000;color:#ccc;margin-bottom:16px;width:60%;height:30px;line-height:30px;" placeholder="请输入你的标题"/><br/>
                        '<input data-type="addCommentBtn_article" type="button" value="添加评论" style="cursor:pointer;background:#383131;width:80px;height:30px;float:left;margin-top:5px;border:none;"/>'+
                        '<div style="clear:both;"></div>'+
                        '<p style="margin-left:5%;color:#aaa;padding-top:5px;border-top:1px solid #333;width:80%;display:inline-block;"><span>作者名：</span><span>他说了什么</span></p>' +
                        '</div>'+
                        '</article>;';
        


        var _articleContainerBox;
        init();
        function init(){
            _articleContainerBox = document.createElement('div');
            _articleContainerBox.id = 'ArticleContainer' + new Date().getTime();
            _articleContainerBox.innerHTML = container;
        }

        
        //key:属性名  value:属性值  
        function findNodes(key, value, tagName, deep){
            var childArr = [].slice.call(_articleContainerBox.children[0].children);
            var result = [];
            childArr.forEach(function(el, index){
                if(key != null && value != null && el.getAttribute(key) == value){
                    result.push(el);
                }
                if(tagName && (el.tagName == tagName)){
                    result.push(el);
                }

                if(deep){
                    var secondChildArr = [].slice.call(el.children);
                    if(secondChildArr.length){
                        secondChildArr.forEach(function(el, index){
                            if(key != null && value != null && el.getAttribute(key) == value){
                                result.push(el);
                            }
                            if(tagName && (el.tagName == tagName)){
                                result.push(el);
                            }
                        });
                    }
                }
            });
            return result.length ? result : null;
        }

        //查找功能
        this.find = function(key, value, tagName, deep){
            findNodes(key, value, tagName, deep);
        };

        //添加功能
        this.append = function(id, isBefore){
            if(id){
                if(isBefore){
                    document.getElementById(id).insertBefore(_articleContainerBox, null);
                }else{
                    document.getElementById(id).appendChild(_articleContainerBox);
                }
            }else{
                if(isBefore){
                    document.documentElement.insertBefore(_articleContainerBox, null);
                }else{
                    document.documentElement.appendChild(_articleContainerBox);
                }
            }
            
            $(_articleContainerBox).find('[data-type=showDelete_articleCon]').click(function(e){
            	$(this).find('[data-type=deleteBtn]').fadeIn();
            	e.stopPropagation();
            });
            
            //TODO
            $(_articleContainerBox).find('[data-type=showComment_artivleCon]').click(function(e){
            	$(_articleContainerBox).find('[data-type=commentContainer_article]').slideToggle('normal', function(){
            		if($(_articleContainerBox).find('[data-type=commentContainer_article]').css('display') != 'none'){
            			
            			//添加评论
            			$(_articleContainerBox).find('[data-type=addCommentBtn_article]').each(function(index, el){
            				el.onclick = function(){
//            					var comments = $(_articleContainerBox).find('[data-type=commentContents_article]');
            					alert(0);
            					$.ajax({
            						url : '../json/comment/insert',
            						type : 'post',
            						dataType : 'json',
            						data : {
            							content : comments
            						},
            						success : function(res, status, req){
            							
            						},
            						error : function(req, status, err){
            							
            						}
            					});
            				};
            			});
            		}
            	});
            	e.stopPropagation();
            });

            $(_articleContainerBox).find('[data-type=deleteBtn]').click(function(){
            	if(this.getAttribute('name')){
            		$.ajax({
            			url : '../json/article/delete',
            			data : {
            				id : this.getAttribute('name')
            			},
            			type : 'post',
            			dataType : 'json',
            			success : function(){
            				$(_articleContainerBox).remove();
            				alert('删除成功');
            			}
            		});
            	}else{
            		alert('删除失败');
            	}
            });
            
            document.documentElement.onclick = function(){
            	$('[data-type=deleteBtn]').fadeOut();
            };
            return this;
        };


        //删除功能
        this.remove = function(){
            _articleContainerBox.parentNode.removeChild(_articleContainerBox);
            return this;
        };

        return this;
    }


    ArticleContainer = {
        newInstance : function(param){
            return new createArticleContainer(param);
        }
    };
})();