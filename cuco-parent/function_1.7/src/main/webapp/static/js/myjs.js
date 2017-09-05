/**
 * 
 * this is a javascript file for public use.
 * 
 */
// setting height of the div
jQuery.extend({
	    handleError: function( s, xhr, status, e ) {
	        // If a local callback was specified, fire it
	        if ( s.error )
	            s.error( xhr, status, e );
	        // If we have some XML response text (e.g. from an AJAX call) then log it in the console
	        else if(xhr.responseText)
	            console.log(xhr.responseText);
	    }
	});

function settingDialog() {
	$("div:visible").each(function() {
		if ($(this).attr("class") == "ui-widget-overlay") {
			tmpIndex = $(this).css("z-index");
		}
	});
	tmpIndex += 10;
	$("#info_dialog").css("z-index", tmpIndex);
	$("div:visible").each(
			function() {
				if ($(this).attr("class") == "ui-widget-overlay"
						&& $(this).css("z-index") == "999") {
					$(this).css("z-index", tmpIndex - 1);
				}
			});
}

// redraw dialog
function info_dialog_autoset(caption, content, p) {
	var positions = $.jgrid.findPositions(290, 50);
	p = $.extend({
		left : positions[0],
		top : positions[1]
	}, p || {});
	$.jgrid.info_dialog(caption, content, '', p);
	settingDialog();
}


//redraw confirm dialog
function jqgrid_confirm(caption,callback,c_b, modalopt){
	var positions = $.jgrid.findPositions(290,50);
	modalopt =  $.extend({left:positions[0],top:positions[1]}	, modalopt || {});
	var $align = modalopt.align || "center";
	var ret = '<div id="redrawConfirmDialog" align="'+$align+'" class="ui-widget" >';
	ret += '<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">';
	ret += '<p style="margin-top:10px;"><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>';    	
	ret += caption;
	ret += '</p>';
	ret += '</div>';
	ret += '</div>';
	ret += '<div style="margin-top:10px;">';
	ret += "<button id=\'custom_confirm_ok\' >确定</button>&nbsp;&nbsp;";
	ret += '<button id=\'custom_confirm_cancel\' >取消</button>';
	ret += '</div>';
	bindCustomConfirmEvent(callback);
	info_dialog_autoset("提示", ret,c_b, modalopt); 
} 

function bindCustomConfirmEvent(callback){
	$("#custom_confirm_ok").live("click",function(){
		hideOverTopDiv(parseInt($("#info_dialog").css("z-index"))-1);
		$("#info_dialog").remove();
		callback(); 
		$("#custom_confirm_ok").die("click");
	});
	$("#custom_confirm_cancel").live("click",function(){
		hideOverTopDiv(parseInt($("#info_dialog").css("z-index"))-1);
		$("#info_dialog").remove();
		$(".ui-front").show();
		hideOverTopDiv();
		$("#custom_confirm_ok").die("click");
	});
} 
 
// object confirm call back
function confirmCallback(callback){
	this.callback = callback;
} 

// hide div of the over top 
function hideOverTopDiv(num){
	$("div:visible").each(function(){
		if($(this).attr("class")=="ui-widget-overlay"){
			tmpIndex = $(this).css("z-index");
		}
	});
	$("div:visible").each(function(){
		if($(this).attr("class")=="ui-widget-overlay" && $(this).css("z-index")==num){
			$(this).hide();
		}
	});
}
Date.prototype.Format = function (fmt) {  
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//initialize date
function  initializeDatePlug(dateId,setDefault){
	$('#'+dateId).datetimepicker({
		changeYear:true,
		changeMonth:true,
		minDateTime:new Date()
	});
	if(setDefault==false){
	}else{
		if($('#'+dateId).val()==""){
			var defaultDate = new Date().Format("yyyy-MM-dd hh:mm");
			$('#'+dateId).val(defaultDate);
		}
	}
}

//custom error message
function $customValidator(formid,errorid,isCompareDate){
	var validateResult = false;
	var form = "#"+formid;
	validateResult = jqueryValidate(form,errorid);
	if(validateResult && isCompareDate==true){
		var startTime = $("#"+formid+" .startTime").val();
		var startLable = $("#"+formid+" .startTime").attr("error");
		var endTime = $("#"+formid+" .endTime").val();
		var endLable = $("#"+formid+" .endTime").attr("error");
		if(endTime!=""){
			if(startTime==""){
				validateResult = false;
				showErrorMsg(errorid,startLable+"不能为空!");
			}else{
				validateResult = compareDate(startTime,endTime); 
				if(!validateResult){
					showErrorMsg(errorid,startLable+"不能大于或等于"+endLable+"!");
				}
			}
		}
	}
	return validateResult;
}

// validate of the jquery basic
function jqueryValidate(formid,errorid){
	return $(formid).validate({ 
		errorPlacement : function(error, element) { 
			var tagType = $(element).prop("tagName");
			var errormsg = $(error).html();
			if($(element).attr("type")){
				if(tagType.toUpperCase()=="SELECT"||$(element).attr("type").toUpperCase()=="checkbox"){
					errormsg = '必选字段';
				}
			}
			var errorStr = "'"+$(element).attr("error")+"'字段为"+errormsg;
			showErrorMsg(errorid,errorStr);
		},
		showErrors : function(error,errorList){
			if(errorList.length){
				var firstErrorMsg = errorList.shift();
				var newErrorList = [];
				newErrorList.push(firstErrorMsg);
				this.errorList = newErrorList;
			}
			this.defaultShowErrors();
		}
	}).form(); 
}


// compare date
function compareDate(startTime,endTime){
	var s = new Date(startTime.replace("-", "/").replace("-", "/"));
	var e = new Date(endTime.replace("-", "/").replace("-", "/"));
	return s<e;
}

// show erro message
function showErrorMsg(erid,msg){
	$("#"+erid).css("marginBottom","0px");
	$("#"+erid).css("display","block");
	$("#"+erid).html("&nbsp;&nbsp;"+msg);
}

function verifyFileType(fileName){
	var standard = "doc,docx,xls,xlsx,ppt,pptx,png,pdf,jpg,gif,bmp";
	if(standard.indexOf(fileName.toLowerCase())>-1){
		return true;
	}else{
		return false;
	} 
}