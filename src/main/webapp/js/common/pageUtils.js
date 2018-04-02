pageUtils={
	toPage:function(uri){
		var toPageNum=document.getElementById("toPageNum").value;
		if (toPageNum.trim().length == 0 || !toPageNum.isNumber() || (toPageNum<1)) {
			alert("输入的页码不正确！请重新输入！");
			return false;
		}
		window.location.href=(uri+"currentPageNum="+toPageNum);
	},
	/**
	 * 回车跳转到某页.
	 */
	enterToPage: function(uri,evt) {
		var keynum=0;
		if(window.event) // IE
		{
			keynum = evt.keyCode;
		}
		else if(evt.which) // Netscape/Firefox/Opera
		{
			keynum = evt.which;
		}
		if (keynum == 13) {
			var toPageNum=document.getElementById("toPageNum").value;
			if (toPageNum.trim().length == 0 || !toPageNum.isNumber()) {
				alert("输入的页码不正确！请重新输入！");
				return false;
			}
			window.location.href=(uri+"currentPageNum="+toPageNum);
		}
		
		var e = evt ? evt : window.event;
		if (window.event) {
//			由于HTML中的对象都是层次结构，比如一个Table包含了多个TR，一个TR包含了多个TD 
//			Bubble就是一个事件可以从子节点向父节点传递，比如鼠标点击了一个TD，当前的event.srcElement就是这个TD，但是这种冒 泡机制使你可以从TR或者Table处截获这个点击事件，但是如果你event.cancelBubble，则就不能上传事件。
			e.cancelBubble=true;
		} else {
//			该方法将停止事件的传播，阻止它被分派到其他 Document 节点。在事件传播的任何阶段都可以调用它。注意，虽然该方法不能阻止同一个 Document 节点上的其他事件句柄被调用，但是它可以阻止把事件分派到其他节点。
			e.stopPropagation();
		}
	}

};

String.prototype.isNumber = function() {
	if (this !== "") {
		 return /^[1-9][0-9]*$/.test(this);
	}
};

String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
};