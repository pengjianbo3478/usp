var courseCategoryLevel = {
	courseCategoryJson : "",

	init : function(tagId){
		//加载初始化表单信息
		var content = '<span class="select-list margin-r-5 float-l select-3">'
					+ '<div id="1thList_Text" class="select-list-now" style="background:white">选择分类</div>'
					+ '<i onClick="courseCategoryLevel.showSelect(\'1thList\')" class="btn-select"></i>'
					+ '<ul id="1thList" class="select-item-1 btn-select-scroll-1"  style="z-index:10000" >'
					+ '</ul>'
					+ '</span>'
					+ '<span class="select-list margin-r-5 float-l select-3">'
					+ '<div id="2thList_Text" class="select-list-now" style="background:white;">二级分类</div>'
					+ '<i onClick="courseCategoryLevel.showSelect(\'2thList\')" class="btn-select"></i>'
					+ '<ul id="2thList" class="select-item-1 btn-select-scroll-1" style="z-index:10000;margin-left: 182px;" >'
					+ '</ul>'
					+ '</span>';
					//+ '<span class="select-list margin-r-5 float-l select-3">'
					//+ '<div id="3thList_Text" class="select-list-now" style="background:white">三级分类</div>'
					//+ '<i onClick="courseCategoryLevel.showSelect(\'3thList\')" class="btn-select"></i>'
					//+ '<ul id="3thList" class="select-item-1 btn-select-scroll-1">'
					//+ '</ul>'
					//+ '</span>';
		$('#'+tagId).append(content);
		
		var url = $('#_CONTEXT_PATH').val()+'/gd/musiccategory/searchex03.do';
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				courseCategoryLevel.courseCategoryJson = data;
				courseCategoryLevel.loadCategoryL1();
			}
		});
	},
	
	loadCategoryL1 : function(){
		var index = 0;
		var html = '';
		$.each(courseCategoryLevel.courseCategoryJson, function(i,element) {
			if($.trim(element.parentId)=='0'){
				var categoryL1 = $('[name=categoryL1]').val();
				if ($.trim(categoryL1)=='' && index == 0) {
					$("#1thList_Text").text(element.name);
					$("#categoryL1").val(element.id);
					courseCategoryLevel.loadCategoryL2(element.id);
				}
				if ($.trim(categoryL1)!='' && categoryL1 == element.id ){
					$("#1thList_Text").text(element.name);
					$("#categoryL1").val(element.id);
					courseCategoryLevel.loadCategoryL2(element.id);
				}
				html += '<li onclick="courseCategoryLevel.categoryL1Change(this)" value="'+ element.id +'" style="background:white" >' + element.name + '</li>';
				index++;
			}
		});
		$("#1thList").html(html);
	},
	
	loadCategoryL2 : function(parentId){
		var index = 0;
		var html = '';
		$.each(courseCategoryLevel.courseCategoryJson, function(i,element) {
			if($.trim(element.parentId)==$.trim(parentId)){
				var categoryL1 = $('[name=categoryL2]').val();
				if ($.trim(categoryL1)=='' && index == 0) {
					$("#2thList_Text").text(element.name);
					$("#categoryL2").val(element.id);
					//courseCategoryLevel.loadCategoryL3(element.id);
				}
				if ($.trim(categoryL1)!='' && categoryL1 == element.id ){
					$("#2thList_Text").text(element.name);
					$("#categoryL2").val(element.id);
					//courseCategoryLevel.loadCategoryL3(element.id);
				}
				html += '<li onclick="courseCategoryLevel.categoryL2Change(this)" value="'+ element.id +'" style="background:white" >' + element.name + '</li>';
				index++;
			}
		});
		$("#2thList").html(html);
	},
	
	loadCategoryL3 : function(parentId){
		var index = 0;
		var html = '';
		$.each(courseCategoryLevel.courseCategoryJson, function(i,element) {
			if($.trim(element.parentId)==$.trim(parentId)){
				var categoryL1 = $('[name=categoryL3]').val();
				if ($.trim(categoryL1)=='' && index == 0) {
					$("#3thList_Text").text(element.name);
					$("#categoryL3").val(element.id);
				}
				if ($.trim(categoryL1)!='' && categoryL1 == element.id ){
					$("#3thList_Text").text(element.name);
					$("#categoryL3").val(element.id);
				}
				html += '<li onclick="courseCategoryLevel.categoryL3Change(this)" value="'+ element.id +'" style="background:white" >' + element.name + '</li>';
				index++;
			}
		});
		$("#3thList").html(html);
	},
	
	categoryL1Change : function(obj){
		var categoryL1Id = $(obj).val();
		$("#categoryL1").val(categoryL1Id);
		$("#categoryL2").val('');
		$("#categoryL3").val('');
		courseCategoryLevel.loadCategoryL2(categoryL1Id);
	},
	
	categoryL2Change : function(obj){
		var categoryL1Id = $(obj).val();
		$("#categoryL2").val(categoryL1Id);
		$("#categoryL3").val('');
		//courseCategoryLevel.loadCategoryL3(categoryL1Id);
	},
	
	categoryL3Change : function(obj){
		var categoryL1Id = $(obj).val();
		$("#categoryL3").val(categoryL1Id);
	},
	
	showSelect : function(listId){
		var list=document.getElementById(listId);
		var inputId="#categoryL"+listId.substring(0,1);
		var divId="#"+listId+"_Text";
		if(list.style.display!="block"){
			list.style.display="block";
			if(list.getBoundingClientRect().top>410){
				list.style.marginTop='-200px';
			};
			$("#"+listId+" li").click(function(){
				var nowLi=$(this);
				$(inputId).val(nowLi.val());
				$(divId).text(nowLi.text());
				list.style.display="none";
			}
			);
		}else{
			list.style.display="none";
		}
	}
		
}