

	

	
	//class="active"
	
	//alert($('#menuId').children().length);
	
//	alert($("#sidebar ul").children() );
	
/*	alert($("#sidebar ul li").html());
 * 
$("#sidebar ul li").each(function(i, v) {
	alert(i);
		var menuDiv = $(v);
		aler(menuDiv);
		//menuDiv.click(ShowPage(menuDiv.attr('id').replace(/Menu$/g, "")));
	});*/
	

	
/*	$('#menuId').children().each(function(i, v) {
		$(v).click(function (){
			//var cl=$(v).attr("class");
			//alert(cl);
			var ch=$(v).children();
			//有子标签
			if(ch.length>1){
				$(v).children().each(function(i, v) {	
					$(v).click(function (){
						alert("ddd");
					});
				});
				
			}else {//没有子标签
				
			}
			
		});
		
		//var cl=$(v).attr("class");
		//alert(cl);
		//$(domthis).attr("class",'active  '+cl);
		
	});*/
	


/**
 * 查找菜单对象
 */
/*function findMenu(id) {
	for(var i = 0; i < menus.length; i++) {
		if(menus[i].id == id) {
			return menus[i];
		} else if(menus[i].subMenus) {
			for(var j = 0; j < menus[i].subMenus.length; j++) {
				if(menus[i].subMenus[j].id == id) {
					return menus[i].subMenus[j];
				}
			}
		}
	}
	return null;
}*/



function ShowPage(menuId) {
	return function(){
		var menu = findMenu(menuId);
		var parentMenu = findParentMenu(menuId);
		//点击一级菜单的处理，无二级菜单
		if(menu.level == 1 && typeof menu.subMenus == 'undefined') {
			selectMenu(menu.id);

			//当前tabs的id
			var curTabsId = "tabs_" + menu.id;	

			if(lastShowTab != undefined && lastShowTab != curTabsId) {
				$("#" + lastShowTab).css("display", "none");
			}
			
			//动态创建一级菜单的frame
			if($("#" + curTabsId).length == 0) {						
				var vh = $('#centerPane').innerHeight()-$(".ui-tabs-nav",'#tabs').outerHeight()-10;
				var newHtml = "<iframe id='" + curTabsId + "' src='" + menu.url + "' width='100%' height='" +vh+ "' frameborder='0' scrolling='no' style='margin-top:10px;'>";
				
				$("#tabs").append(newHtml);
				
			} else {
				$("#" + curTabsId).css("display", "block");
			}

			//改变菜单的样式
			changeMenuStyle(curTabsId);
			lastShowTab = curTabsId;

			
		} else if(menu.level == 1 && typeof menu.subMenus != 'undefined') { //一级菜单下有二级菜单
			selectMenu(menu.id);
			//子菜单处于打开状态，则直接显示打开的内容，否则不做任何操作
			var menuName = $(".tab_num" + menu.order).html();
			if(menuName != undefined && menuName != "") {
				var curTabsId = "tabs_" + menu.id;	
				changeMenuStyle(curTabsId);

				if(lastShowTab != undefined && lastShowTab != curTabsId) {
					$("#" + lastShowTab).css("display", "none");
					$("#" + curTabsId).css("display", "block");
				}
				
				var lastmenuid=lastShowTab.substring(5);
				for(var i = 0; i < menus.length; i++) {
					var lastMenu = menus[i].lastMenu;
					if(menus[i].id == lastmenuid){
						if(""!=lastMenu){
							lastFrameid=lastMenu;
						}
						break;
					}
				}
				for(var i = 0; i < menus.length; i++) {
					var lastMenu = menus[i].lastMenu;
					if(menus[i].id == menu.id){
						if(""!=lastMenu){
							startInterval(lastMenu,lastFrameid);
							lastFrameid=lastMenu;
						}
						break;
					}
				}
				lastShowTab = curTabsId;
			}
		}else {
			// 二级菜单，直接找到对应的容器打开
			addTab(menu, parentMenu.id);
		}
	};
}

/*function domclass(domthis){
	var cl=$(domthis).attr("class");
	alert(cl);
	$(domthis).attr("class",'active  '+cl);

}*/