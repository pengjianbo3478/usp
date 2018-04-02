// JavaScript Document
$(function(){
	$("#izl_rmenu").each(function(){
		$(this).find(".scroll-wx").mouseenter(function(){
			$(this).find(".pic").fadeIn("fast");
		});
		$(this).find(".scroll-wx").mouseleave(function(){
			$(this).find(".pic").fadeOut("fast");
		});
		
		$(this).find(".scroll-top-top").click(function(){
			$("html, body").animate({
				"scroll-top":0
			},"fast");
		});
	});
	var lastRmenuStatus=false;
	$(window).scroll(function(){//bug
		var _top=$(window).scrollTop();
		if(_top>200){
			$("#izl_rmenu").data("expanded",true);
		}else{
			$("#izl_rmenu").data("expanded",false);
		}
		if($("#izl_rmenu").data("expanded")!=lastRmenuStatus){
			lastRmenuStatus=$("#izl_rmenu").data("expanded");
			if(lastRmenuStatus){
				$("#izl_rmenu .scroll-top-top").slideDown();
			}else{
				$("#izl_rmenu .scroll-top-top").slideUp();
			}
		}
	});
});

$(document).ready(function(e) {
	/*头部搜索框*/
	$(".select-top").focus(function(){
		$(".header-select").animate({width:'249px'})
		$(this).css("width","214px");
		});
    $(".select-top").blur(function(){
		$(".header-select").animate({width:'162px'})
		$(this).css("width","125px");
		})
		
	
	/*头部个人中心下啦*/
	$(".icon-pic , .center-slide").hover(function(){
		$(".center-slide").toggle();
		});
});
