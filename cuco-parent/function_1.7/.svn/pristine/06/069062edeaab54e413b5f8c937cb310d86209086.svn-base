
//显示备注界面
function showInputRemarkDiv(){
	$("#newRemark").val($("#remark0").val());
	$("#mainDiv").hide();
	$("#inputRemarkDiv").show();
}

//取消修改备注
function cancleInputRemark(){
	$("#inputRemarkDiv").hide();
	$("#newRemark").val("");
	$("#mainDiv").show();
}

//确认修改备注
function comfirmInputRemark(){
	var newRemark = $.trim($("#newRemark").val());
	/*if(newRemark == ""){
		alert("请输入备注");
		return;
	}*/
	$("#remark").html("<span>任务备注：</span>"+newRemark);
	$("#remark0").val(newRemark);
	cancleInputRemark();
}

//显示用车地点界面
function showInputAddressDiv(){
	$("#mainDiv").hide();
	$("#currentAddress").html($("#taskAddress").val());
	$("#newAddress").val("");
	$("#inputAddressDiv").show();
}

//取消修改用车地点
function cancleInputAddress(){
	$("#inputAddressDiv").hide();
	$("#newAddress").val("");
	$("#mainDiv").show();
}

//确认修改用车地点
function confirmInputAddress(){
	var newAddress = $.trim($("#newAddress").val());
	if(newAddress == ""){
		alert("请输入变更地址");
		return;
	}
	$("#address").html(newAddress);
	$("#taskAddress").val(newAddress);
	cancleInputAddress();
}

//显示选择车辆界面
function showChooseCarDiv(){
	//String operateNum,Long powerId,Long curMemberId
	var operateNum = $("#operateNum").val();
	$("#tempOperateNum").val(operateNum);
	var powerId = $("#memberPowerId").val();
	$.ajax({
	     type: 'POST',
	     url: '/wechat/personalcentre/butler/getCarList',
	     data: {
	    	 "powerId":powerId		 
	     },
	     dataType: "json",
	     success: function(data){
	    	var carInfo = "暂未选择";
	    	var carNum = "";
	    	
    		var carListInfo = "";
    		
    		if(data.carList != null){
    			$.each(data.carList, function(i, item){ 
    				var classInfo = "";
				    if(operateNum != null && operateNum == item.operateNum){
				    	classInfo = "class = 'on'";
				    	carInfo = item.carBrand + "  " + item.carType;
				    	carNum = item.carPlateNum;
				    }
				    var privateInfo = "";
				    if(item.isLife == 1){
				    	privateInfo = "(专)";
				    }
    			    carListInfo += "<dd " + classInfo 
    			    	+ " onclick=\"chooseCar(this,'"+item.carBrand+"','"+item.carType+"','"+item.carColor+"','"+item.carPlateNum+"','"+item.operateNum+"');\">"
    			    	+ "<p>" + item.carBrand + " " + item.carType
    			        + "&nbsp;&nbsp;&nbsp;&nbsp;" + item.carColor + privateInfo + "</p><p><span>" + item.carPlateNum
    			        + "</span>" + "<span class='fRight'>" + item.statusName +"</span></p></dd>";    
    			});
    		}
    		if(carNum == ""){
    			carInfo = $("#carInfo").html();
    		}
    		$("#chooseCar_1").html(carInfo);
    		$("#chooseCar_2").html(carNum);
    		$("#carListInfo").html(carListInfo);
	    	$("#mainDiv").hide();
	    	$("#chooseCarDiv").show();
	    } 
	});
}

//选择车辆
function chooseCar(obj,brand,type,color,carNum,num){
	if(!$(obj).hasClass("on")){
		$("dd").each(function(){
            $(this).removeClass('on');
        });
		$(obj).addClass("on");
		
		$("#chooseCar_1").html(brand + "   " + type);
		$("#chooseCar_2").html(carNum);
	}
	$("#tempOperateNum").val(num);
	$("#tempCarInfo").val(brand + "  " + type + "   " +  color + "   (" + carNum + ")");
}

//取消选择车辆
function cancleChooseCar(){
	$("#chooseCarDiv").hide();
	$("#mainDiv").show();
}

//确认车辆
function confirmChooseCar(){
	var num = $("#tempOperateNum").val();
	if(num==""){
		alert("请选择车辆");
		return;
	}
	var carInfo = $("#tempCarInfo").val();
	$("#carInfo").html(carInfo);
	$("#operateNum").val(num);
	$("#chooseCarDiv").hide();
	$("#mainDiv").show();
}

//显示选择管家界面
function showChooseButlerDiv(){
	var butler = "暂未选择";
	var butler0 = $("#butlerName").html();
	if(butler0!=""){
		butler = butler0;
	}
	$("#tempButlerId").val($("#operaterId").val());
	$("#tempButlerName").val($("#operaterName").val());
	var butlerListInfo = "<li>当前负责管家："+butler+"</li>";
	$.ajax({
	     type: 'GET',
	     url: '/wechat/personalcentre/butler/getButlerList',
	     dataType: "json",
	     success: function(data){
	   		if(data.butlerList != null){
	   			var curButlerId = $("#operaterId").val();
	   			$.each(data.butlerList, function(i, item){ 
	   				var classInfo = "";
	   				if(item.sysuserId==curButlerId){
	   					classInfo = "class='on'";
	   				}
	   				butlerListInfo += "<li "+classInfo+" onclick=\"chooseButler(this,'"+item.sysuserId+"','"+item.sysuserName+"');\">"+item.sysuserName+"</li>"; 
	   			});
	   		}
	   		
	   		$("#butlerListInfo").html(butlerListInfo);
	    	$("#mainDiv").hide();
	    	$("#chooseButlerDiv").show();
	    } 
	});
}

//选择管家
function chooseButler(obj,id,name){
	if(!$(obj).hasClass("on")){
		$("#butlerListInfo li").each(function(){
            $(this).removeClass('on');
        });
		$(obj).addClass("on");
		/*$("#operateNum").val(num);
		$("#hasChoosedInfo").html("");
		$("#hasChoosedInfo").append("    已&nbsp;&nbsp;&nbsp;&nbsp;选：<span>"+brand+"   "+type+"</span>");
		$("#hasChoosedInfo").append("<span class='fRight'>"+carNum+"</span>");
		$("#errInfo").html("");*/
	}
	$("#tempButlerId").val(id);
	$("#tempButlerName").val(name);
}

//取消选择管家
function cancleChooseButler(){
	$("#chooseButlerDiv").hide();
	$("#mainDiv").show();
}

//确认选择管家
function confirmChooseButler(){
	var newButlerName = $("#tempButlerName").val();;
	var newButlerId = $("#tempButlerId").val();;
	$("#butlerName").html(newButlerName);
	$("#operaterName").val(newButlerName);
	$("#operaterId").val(newButlerId);
	cancleChooseButler();
}
		