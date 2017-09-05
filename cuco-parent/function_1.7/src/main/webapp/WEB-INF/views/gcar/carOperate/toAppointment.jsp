<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
        <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/4v.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    if(${code == 500}){
    	  alert("${msg}");	
    	  window.location.href= "javascript:history.back(-1);"; 
    }
    $(function() {
	    $('#submit').click(function submitForm() {
	    	$('#submit').unbind("click");
	    	var beginTime = $('#datetime-picker').val();
	 		var beginTimeDate = new Date(beginTime).getTime();
			var nowDate = new Date().getTime();
			var twoHours = 60 * 60 * 48 * 1000;
			if(nowDate > beginTimeDate){
				alert('用车时间不能早于当前时间');
	 			$('#submit').bind("click",submitForm);
	 			return;
			}
			if(beginTimeDate - nowDate > twoHours){
				alert('签约预约只能选择距当前时间48小时之内的送车时间');
	 			$('#submit').bind("click",submitForm);
	 			return;
			}
			var address = $('#address').val();
			if(address == ''){
				alert('请填写送车地点');
	 			$('#submit').bind("click",submitForm);
	 			return;
			}
			var t = $('#operateForm').serializeArray();	
 			    $.ajax({
	             type: "GET",
	             url: "appointment",
	             data:t,
	             dataType: "json",
	             success: function(data){
	                var obj = eval("("+data+")"); 
                    alert(obj["msg"]);
                    if(obj["code"] === 200){
                    	window.location.href="${ctx}/wechat/carOperate/toCar?carPlateNum=${carPlateNum}&loginId=${loginId}";
                    	//window.location.href = "getById?id=${carId}&type=${type}&dateStr=${dateStr}&started=${started}&loginId=${loginId}";
                    }else{
                    	$('#submit').bind("click",submitForm);
                    }
				 }
 				})
	    })
	 })   
  </script>
     <style type="text/css">
     	 .right{
           position: absolute;
           left: 40;
        }
        
        .bottom{
           width: 60%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
           position: absolute;
           bottom: 0px;
           right: 0px;
        }
        
        .left{
           width: 30%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
           position: absolute;
           bottom: 0px;
           left: 0px;
        }
     </style>
   
<title>签约</title>
</head>
  
<body>
	<form id="operateForm" action="appointment">
    <div class="fault-page">
                <span class="info-name info-name-time">
                    预计送车时间：
                </span>
              <span class="info-val page-current" id="page-datetime-picker">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="border:1px solid  #000" placeholder="" id="datetime-picker"  placeholder="开始时间"   value="" name="sendCarTime">
            </span>
           <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
        	<script>
		       $(document).on("pageInit", "#page-datetime-picker", function(e) {
		    	var oDate = new Date();
		    	var year = oDate.getFullYear();   //获取系统的年；
		    	var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
		    	month=month<10?('0'+month):month;
		    	var date = oDate.getDate(); // 获取系统日，
		    	var hour = oDate.getHours(); //获取系统时，
		    	var minute = oDate.getMinutes(); //分
		    	if(minute < 10){
		    	  	minute = '0'+minute;
		    	}
		        $("#datetime-picker").datetimePicker({
		          toolbarTemplate: '<header class="bar bar-nav">\
		          <button class="button button-link pull-right close-picker">确定</button>\
		          <h1 class="title"></h1>\
		          </header>',
		           value: [year, month, date, hour, minute]
		        });
		       });
		        
		      $.init();
		     </script>
		     <span>
		      <input type="hidden" name="loginId" value="${loginId}" id="loginId">
		   <input type="hidden" name="carId" value="${carId}" id="carId">
                   &nbsp; 预计送车地点：
                   <input type="text" name="address" value="" id="address" style="border:1px solid  #000">
             </span>       
    </div>
    <div class="btns">
       <input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >
       <input class="bottom" type="button" id="submit" value="确定">
    </div>
    </form>
</body>
</html>