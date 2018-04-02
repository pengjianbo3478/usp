// jQuery Alert Dialogs Plugin
//
// Version 1.0
//
// Usage:
//		jAlert( message, [title, closetime, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, texttype, title, callback] )
// 

/*
* @date 2009-12-31
* @author Alvin
* @修改接受输入框，用于支持密码的输入
*/
(function($) {
	
	$.alerts = {
		
		verticalOffset: -75,                // 水平位置
		horizontalOffset: 0,                // 垂直位置
		repositionOnResize: true,           // 是否根据浏览器窗口大小的变化而居中
		overlayOpacity: .01,                // 透明度
		overlayColor: '#FFF',               // 透明层的颜色
		draggable: true,                    // 是否支持拖动
		okButton: '&nbsp;确定&nbsp;',       // 确定按钮
		cancelButton: '&nbsp;取消&nbsp;',   // 取消按钮
		dialogClass: null,                  // 窗口自定义样式
		
		// Public methods
		
		alert: function(message, title, closetime, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, null, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
			if( closetime != null && !isNaN(closetime)){
				setTimeout($.alerts._hide, closetime);
			}
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, texttype, title, callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, value, texttype, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		// Private methods
		
		_show: function(title, msg, value, texttype, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.alerts.msie && parseInt($.alerts.version) <= 6 ) ? 'absolute' : 'fixed';
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					//支持密码框 update by 曾琪 2009-12-31
					switch(texttype)
					{
						case 'password':
							$("#popup_message").append('<br /><input type="password" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
							break;
						default:
							$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
							break;
					}
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
			}
			
			$("#popup_panel input:button").addClass("inp_btn");
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.alerts.msie && parseInt($.alerts.version) <= 6 ) top = top + $(window).scrollTop();

			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', function() {
							$.alerts._reposition();
						});
					break;
					case false:
						$(window).unbind('resize');
					break;
				}
			}
		},
		
		browser: function()
	    {
	      var agent = navigator.userAgent.toLowerCase() ;
	 
	      var regStr_ie = /msie [\d.]+;/gi ;
	      var regStr_ff = /firefox\/[\d.]+/gi
	      var regStr_chrome = /chrome\/[\d.]+/gi ;
	      var regStr_saf = /safari\/[\d.]+/gi ;
	      //IE
	      if(agent.indexOf("msie") > 0)
	      {
	        return agent.match(regStr_ie) ;
	      }
	 
	      //firefox
	      if(agent.indexOf("firefox") > 0)
	      {
	        return agent.match(regStr_ff) ;
	      }
	 
	      //Chrome
	      if(agent.indexOf("chrome") > 0)
	      {
	        return agent.match(regStr_chrome) ;
	      }
	 
	      //Safari
	      if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
	      {
	        return agent.match(regStr_saf) ;
	      }
	      
	    },
	    
	    msie: function(){
	    	return navigator.userAgent.toLowerCase().indexOf("msie") > 0;
	    },
	    
	    version: function(){
	    	return ($.alerts.browser()+'').replace(/[^0-9.]/ig,"");
	    }
		
	};
	
	// =javascript中的alert
	jAlert = function(message, title, closetime, callback) {
		$.alerts.alert(message, title, closetime, callback);
	};
	// =javascript中的confirm
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
	// =javascript中的prompt
	jPrompt = function(message, value, texttype, title, callback) {
		$.alerts.prompt(message, value, texttype, title, callback);
	};
	
})(jQuery);