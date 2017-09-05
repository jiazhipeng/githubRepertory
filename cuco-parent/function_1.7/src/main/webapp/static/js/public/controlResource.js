/*******************************************************************************
 * 判断当前用户是否有操作对象objectRoles的权限
 * 
 * @param objects
 * @param tagerts
 * @returns {Boolean}
 */
function  hasRoles(objects,tagerts){
	var hasRoles = false;
	var arrObjects = objects.split(",");
	for(var i=0;i<arrObjectRoles.length;i++){
		if(tagerts.contains(arrObjects[i])){
			hasRoles = true;
			break;
		}
	}
	return hasRoles;
}

/*******************************************************************************
 * 设置详情表单是为只读
 * 
 * @param readOnly
 */
function readOnlyForm(readOnly){
	if(readOnly){
		$('input').attr("readonly",true);
		$('input').attr("disabled",true);
		$('textarea').attr("readonly",true);
		$("select").attr("disabled", true);
		$("button").hide();
	}else{
		$('input').attr("readonly",false);
		$('input').attr("disabled",false);
		$('textarea').attr("readonly",false);
		$("select").attr("disabled", false);
		$("button").show();
	}
}


function readOnlyFormv_id(readOnly,v_id){ 
	if(readOnly){
		$('input',v_id).attr("readonly",true);
		$('input',v_id).attr("disabled",true);
		$('textarea',v_id).attr("readonly",true);
		$("select",v_id).attr("disabled", true); 
	}else{
		$('input',v_id).attr("readonly",false);
		$('input',v_id).attr("disabled",false);
		$('textarea',v_id).attr("readonly",false);
		$("select",v_id).attr("disabled", false);
	}
}

// jQuery解除超链接<a>的click事件
function aClickEnable(id,fun){
	$("#"+id).attr("class"," icon-btn icon-btn-imgs span6 ");
	$("#"+id).click(function (event){  
	    event.preventDefault();  
	});
//	$("#"+id).attr("disabled",false);
	$("#"+id).attr("onclick",fun);
}

// jQuery禁止超链接<a>的click事件
function aClickDisable(id){
	$("#"+id).attr("class"," iconbutton span6 ");
	$("#"+id).click(function (event){  
	    return false;  
	}); 
	$("#"+id).unbind('mouseover');
//	$("#"+id).attr("disabled",true);
	$("#"+id).attr("onclick","javascript:void(0);");
	
}