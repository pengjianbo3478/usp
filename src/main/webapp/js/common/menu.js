//function header(){
//	$(document).mousemove(function(event){
//		if(event.pageY > 0 && event.pageY < 30 && event.pageX > 72){
//			$('#header').css('display','block');	
//		}	
//	});
//	$('#header').hover(function(){
//		$('#header').css('display','block');	
//	},function(){
//		$('#header').css('display','none');		
//	});
//}

function tabvariedcolor() {
	$('table.tabList tbody tr:odd').addClass('treven');
	$('table.tabList tbody tr').hover(function() {
		$(this).addClass('trhover');
	}, function() {
		$(this).removeClass('trhover');
	});
}

function leftBtn() {
	var winHeight = $(window).height();
	var navHeight = $('.nav').height();
	var liHeight = $('.nav li').height();
	var num = 6;
	if (navHeight > (winHeight - 70)) {
		$('#cntLeft').prepend("<a href='javascript:void(0)' class='upBtn' id='upBtn'>▲</a>");
		$('#cntLeft').append("<div id='navList'></div>");
		$('.nav').appendTo($('#navList'));
		$('#cntLeft').append("<a href='javascript:void(0)' class='downBtn' id='downBtn'>▼</a>");
		$('#navList').css({
			'height' : liHeight * num,
			'overflow' : 'hidden'
		});
		$('.nav').css('margin-top', 0);
		// 初始化左边选中图标
		if ($('.nav li.lon').index() > 1 && $('.nav li.lon').index() < ($('.nav li').length - num)) {
			$('.nav').css('margin-top', -($('.nav li.lon').index() - 1) * liHeight);
		} else if ($('.nav li.lon').index() >= ($('.nav li').length - num)) {
			$('.nav').css('margin-top', -($('.nav li').length - num) * liHeight);
		}
		// up button click
		$('#upBtn').bind('click', function() {
			if (parseInt($('.nav').css('margin-top')) < 0) {
				$('.nav').animate({
					'margin-top' : (parseInt($('.nav').css('margin-top')) + liHeight)
				}, 100);
			}
		});
		// down button click
		$('#downBtn').bind('click', function() {
			if (parseInt($('.nav').css('margin-top')) > -($('.nav li').length - num) * liHeight) {
				$('.nav').animate({
					'margin-top' : (parseInt($('.nav').css('margin-top')) - liHeight)
				}, 100);
			}
		});
	}
}

function addRightMenu(i_on_num) {
	if (i_on_num == '11') {
		var i11 = '<a href="' + $("#_CONTEXT_PATH").val() + '/usp/index/index.do">首页</a>';
		$('#cRTnav').html(i15);
	} else if (i_on_num == '1') {
		var i1 = '<a href="' + $("#_CONTEXT_PATH").val() + '/account/search.do">基础资料</a>';
		i1 += '<a href="' + $("#_CONTEXT_PATH").val() + '/account/passwordmerge.do">密码修改</a>';
		i1 += '<a href="' + $("#_CONTEXT_PATH").val() + '/account/merge.do">修改资料</a>';
		$('#cRTnav').html(i1);
	} else if (i_on_num == '2') {
		var i2 = '<a href="' + $("#_CONTEXT_PATH").val() + '/finance/search.do">财务信息</a>';
		i2 += '<a href="' + $("#_CONTEXT_PATH").val() + '/adhibition/search.do">应用查询</a>';
		i2 += '<a href="' + $("#_CONTEXT_PATH").val() + '/adhibition/add.do">应用新增</a>';
		i2 += '<a href="' + $("#_CONTEXT_PATH").val() + '/template/search.do">模板列表</a>';
		i2 += '<a href="' + $("#_CONTEXT_PATH").val() + '/template/add.do">模板新增</a>';
		i2 += '<a >模板详情</a>';
		$('#cRTnav').html(i2);
	} else if (i_on_num == '3') {
		var i3 = '<a href="' + $("#_CONTEXT_PATH").val() + '/monthly/search.do">月结账单查询</a>';
		i3 += '<a href="' + $("#_CONTEXT_PATH").val() + '/recharge/search.do">充值查询</a>';
		$('#cRTnav').html(i3);
	} else if (i_on_num == '4') {
		var i4 = '<a href="' + $("#_CONTEXT_PATH").val() + '/consumer/search.do">消费概要</a>';
		 i4 = '<a href="' + $("#_CONTEXT_PATH").val() + '/consumer/masssearch.do">群发查询</a>';
		 i4 = '<a href="' + $("#_CONTEXT_PATH").val() + '/consumer/singlesearch.do">单发查询</a>';
		$('#cRTnav').html(i4);
	
	} else if (i_on_num == '9') {
		var i9 = '<a href="' + $("#_CONTEXT_PATH").val() + '/ea/organization/search.do">机构类别查询</a>';
		i9 += '<a href="' + $("#_CONTEXT_PATH").val() + '/ea/organization/add.do">机构类别新增</a>';
		i9 += '<a>机构类别详情</a>';
		i9 += '<a>机构类别修改</a>';
		$('#cRTnav').html(i9);
	}
}

// 设置导航信息,add by cp 20140515
function setNavMsg(obj) {
	if ($('#cRTnav').html() != "") {
		var cTnavHtml = $('#cTnav').find('a').text();
		$('#cTnav').html('<a href="javascript:void(0);">' + cTnavHtml + '</a>&nbsp;&gt;&nbsp;<span>' + $(obj).text() + '</span>');
	}
}
// add by cp 20140515
function cRTnavBindClick(node) {
	// 右边菜单添加click事件及on样式
	$('#cRTnav').find("a").bind('click', function() {
		$(this).addClass('on').siblings().removeClass('on');
		// $(this).addClass('on');
		// 设置导航信息
		setNavMsg(this);
	});
}
// 点亮menu,add by cp 20140515
function initMenu() {
	var leftMenu = $("#cntLeft .nav").find("a").get(parseInt($("#_MENU_L0").val()));
	$(leftMenu).addClass("on");
	$(leftMenu).parent().addClass("lon");
	var i_class = $(leftMenu).find("i").attr("class");
	var i_on_num = $.trim(i_class).substring(1);
	$(this).find("i").addClass("on" + i_on_num);
	// 右边菜单添加click事件及on样式
	addRightMenu(i_on_num);
	// 添加导航
	var cntLeftText = $(leftMenu).text();
	$('#cTnav').html('<a href="javascript:void(0);">' + cntLeftText + '</a>');
	// $(leftMenu).click();
	var rightMenu = $("#cRTnav").find("a").get(parseInt($("#_MENU_L1").val()));
	// $(rightMenu).click();
	$(rightMenu).addClass('on');
	// 添加右边菜单的click事件
	cRTnavBindClick();
	// 设置导航信息
	setNavMsg(rightMenu);
}

// add by cp 20140515
function leftMenuClick() {
	// 左边菜单添加click事件及样式
	$("#cntLeft .nav").find("a").bind('click', function() {
		$("#cntLeft .nav").find("li").removeClass("lon");
		$("#cntLeft .nav").find("a").removeClass("on");
		$("#cntLeft .nav").find("i").each(function() {

			var i_class = $(this).attr("class");
			var i_on_class = "on" + $.trim(i_class).substring("i");
			$(this).removeClass(i_on_class);
		});
		$(this).addClass("on");
		$(this).parent().addClass("lon");
		var i_class = $(this).find("i").attr("class");
		var i_on_num = $.trim(i_class).substring(1);
		$(this).find("i").addClass("on" + i_on_num);
		// 添加右边菜单
		addRightMenu(i_on_num);
		// 添加导航
		var cntLeftText = $(this).text();
		$('#cTnav').html('<a href="javascript:void(0);">' + cntLeftText + '</a>');
		cRTnavBindClick();
	});
	initMenu();
}

$(function() {
	leftBtn();
	$('.header').on('click', '.userName', function() {
		if ($('.userListCenter').css('display') == 'none') {
			$('.userListCenter').css('display', 'block');
			$('.userName').css('border-radius', '12px 12px 0 0');
		} else {
			$('.userListCenter').css('display', 'none');
			$('.userName').css('border-radius', '12px');
		}

		var offset = $(this).offset();
		$('.userListCenter').css({
			left : offset.left,
			top : offset.top + 20,
			width : $(this).width() + 32
		});
	});
	//add by cp 20140515
	leftMenuClick();

	$('form').each(function() {
		if ($(this).hasClass('validate')) {
			$(this).validate();
		}
	});
});
