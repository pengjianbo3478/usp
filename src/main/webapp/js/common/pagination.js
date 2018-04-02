/**
 * 系统分页模块
 */
Utils.paginationSys = {
		
		pageStyle : {
			firstClass : 'first',				//首页链接样式
			lastClass : 'last',					//末页链接样式
			prevClass : 'prev',					//上一页链接样式
			nextClass : 'next',					//下一页链接样式
			currentClass : 'current',			//当前页链接样式
			nocurrentClass : 'no_current',		//非当前页链接样式
			firstAndLastLink : false				//是否显示首页末页链接
		},
		
		/**
		 * dest : 装载分页容器 $对象
		 * pageCount : 页总记录数
		 * pageNo : 当前页数  第一页下标为 0 , 以此类推
		 * cb : 分页完成后回调函数
		 * pageBar : TRUE OR FLASE 是否需要指定跳转功能
		 * preNum : 中心页前显示页数
		 * allNum ：总显示页数
		 */
		turnPage : function(dest , pageCount , pageNo , cb , pageBar , preNum , allNum){
			if(!preNum)
				preNum = 4;
			if(!allNum)
				allNum = 8;
			
			if(pageCount <= pageNo){
				if(pageCount == 0 || pageNo < 0){
					dest.html('<div>暂无数据</div>');
				}else{
					cb(pageNo - 1);
				}
				return false;
			}
			var page = '';
			var pageNo = pageNo;
			dest.html('');
		    if(pageCount > 1){
		        if(pageNo > 0){
		        	if(Utils.paginationSys.pageStyle.firstAndLastLink){
		        		page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.firstClass+'"><<首页</a>';
		        	}
		            page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.prevClass+'">&lt;上一页</a>';
		        }
		        var _sidx = (pageNo - preNum >= 0 ? (pageNo - preNum) : 0);
		        var _eidx = ((_sidx + allNum) < pageCount ? (_sidx + allNum) : (pageCount-1));
		        if((_eidx - _sidx) < allNum ){
		        	_sidx = _eidx - allNum;
		        	if(_sidx < 0){
		        		_sidx = 0;
		        	}
		        }
		        for(var i= _sidx; i <= _eidx; i++){
		            if(i == pageNo){ 
		                page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.currentClass+'" pageIndex="'+(i+1)+'">'+(i+1)+'</a>';
		            }else{
		                page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.nocurrentClass+'" pageIndex="'+(i+1)+'">'+(i+1)+'</a>';
		            }
		        }
		        if(pageNo < (pageCount-1)){
		            page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.nextClass+'">下一页&gt;</a>';
		            if(Utils.paginationSys.pageStyle.firstAndLastLink){
		        		page += '<a href="javascript:void(0);" class="'+Utils.paginationSys.pageStyle.lastClass+'">末页>></a>';
		        	}    
		        }
		        if(pageBar){
		        	page+='共'+pageCount+'页　到 第 <input type="text"  class="pageBarText" /> 页 <input type="button" value="确定" class="gray2fBtn" />';
		        }
		    }
		    dest.html(page);
		    if(page){
		    	Utils.paginationSys.bindBtn(dest , pageNo , cb , pageCount);
		    }
		},
		
		bindBtn : function(dest , pageNo , cb , pageCount){
			
			dest.find('.first').click(function(){
				cb(0);
			});
			
			dest.find('.prev').click(function(){
				cb(pageNo - 1);
			});
			
			dest.find('.next').click(function(){
				cb(pageNo + 1);
			});
			
			dest.find('.last').click(function(){
				cb(pageCount - 1);
			});
			
			dest.find('.no_current').click(function(e){
				var _cell = $(e.target);
				var value = parseInt(_cell.attr("pageIndex"));
				cb(value - 1);
			});
			
			dest.find('.pageBarText').keyup(function(e){
				$(this).val($(this).val().replace(/\D/g,''));
				if($(this).val()==0){
					$(this).val(1);
				}
				if($(this).val()>pageCount){
					$(this).val(pageCount);
				}
			});
			
			dest.find('input[type="button"]').click(function(){
				var pageBarText = dest.find('.pageBarText');
				if(pageBarText.val()==""){
					
				}else{
					cb(pageBarText.val() - 1);
				}
			});
		}
};