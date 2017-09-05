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
		  
 			$(function() {
 				$('#submit').click(function submitForm() {
 					$('#submit').unbind("click");
 					var handle = $("input[name='handle']:checked").val();
 					console.log(handle);
 					if(handle == 1){
		    			if(window.confirm('是否取消签约？')){
	    				    $.ajax({
				             type: "GET",
				             url: "cacleAppointment",
				             data: {
				             	carId:${carId},
				             	loginId:${loginId}
				             },
				             dataType: "json",
				             success: function(data){
				                var obj = eval("("+data+")"); 
			                    alert(obj["msg"]);
			                    if(obj["code"] === 200){
    								 window.location.href="${ctx}/wechat/carOperate/toCar?carPlateNum=${carPlateNum}&loginId=${loginId}";
			                    }else{
			                    	$('#submit').bind("click",submitForm);
			                    }
							 }
 							})
		    				return;
		    			}
		    		}else{
		    				var mobile = $('#mobile').val();
		    				console.log(mobile);
		    				if(mobile == ''){
			 					alert('请填写用车人手机号码');
			 					$('#submit').bind("click",submitForm);
			 					return;
	 						}
 						   if(!(/^1[34578]\d{9}$/.test(mobile))){ 
					          alert("该手机号码不符合规则，请输入正确的手机号码");  
					          $('#submit').bind("click",submitForm);
					          return; 
					       } 
	 						var beginTime = $('#datetime-picker').val();
	 						console.log(beginTime);
					 		var beginTimeDate = new Date(beginTime).getTime();
					 		console.log(beginTimeDate);
							var nowDate = new Date().getTime();
							var twoDay = 60 * 60 * 48 * 1000;
							if(beginTimeDate - nowDate > twoDay){
								alert('签约预约只能选择距当前时间48小时之内的送车时间');
					 			$('#submit').bind("click",submitForm);
					 			return;
							}
							var endTime = $('#datetime-picker-end').val();
							console.log(endTime);
					 		var endTimeDate = new Date(endTime).getTime();
					 		console.log(endTimeDate);
					 		if(nowDate > endTimeDate){
								alert('还车时间不能早于当前时间');
					 			$('#submit').bind("click",submitForm);
					 			return;
							}
							if(beginTimeDate - endTimeDate >= 0){
								alert('还车时间需晚于送车时间');
					 			$('#submit').bind("click",submitForm);
					 			return;
							}
							var sendAddress = $('#sendAddress').val();
							if(sendAddress == ''){
								alert('请填写送车地点');
					 			$('#submit').bind("click",submitForm);
					 			return;
							}
	 						var getAddress = $('#getAddress').val();
							if(getAddress == ''){
								alert('请填写取车地点');
					 			$('#submit').bind("click",submitForm);
					 			return;
							}
		    				
		    				$.ajax({
				             type: "GET",
				             url: "launchAppointment",
				             data: {
				             	carId:${carId},
				             	mobile:mobile,
				             	loginId:${loginId},
				             	beginTime:beginTime,
				             	endTime:endTime,
				             	sendAddress:sendAddress,
				             	getAddress:getAddress
				             },
				             dataType: "json",
				             success: function(data){
				                var obj = eval("("+data+")"); 
			                    alert(obj["msg"]);
			                    var carPlateNum = obj["carPlateNum"];
			                    if(obj["code"] === 200){
    								 window.location.href="${ctx}/wechat/carOperate/toCar?carPlateNum="+carPlateNum+"&loginId=${loginId}";
			                    }else{
			                    	$('#submit').bind("click",submitForm);
			                    }
							 }
 							})
		    				return;
		    			}
		    		});
		    	});	
		    	
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
        .fault-page li{
           border:0px solid;
        }
     </style>
   
<title>签约</title>
</head>
  

<body>
    <div class="fault-page sign-page">
        <ul>
            <li>
                <span class="info-name">
                    预约状态：
                </span>
                <span class="info-val" style="color:#000">
                    <input name="handle" id="use" type="radio" value="1">取消预约
                    <input name="handle" id="handle" type="radio" value="2" checked>开始用车</label>
                </span>
            </li>
            <li>
                <span class="info-name">
                    用车人手机号码：
                </span>
                <span class="info-val" style="padding-top:10px;">
                    <input type="tel" id="mobile" style="border:1px solid  #000;width:175px;" value="" name="phone" size="11" maxlength="11">
                </span>
            </li>
            <li>
                <span class="info-name">
                    预计送车时间：
                </span>
            <span class="info-val page-current" id="page-datetime-picker" style="padding-top:10px;">
				<input type="text" style="border:1px solid  #000"  placeholder="" id="datetime-picker"  placeholder="预计送车时间" disabled="disabled" name="beginTime" readonly="" value="<fmt:formatDate value="${sendCarDate }" pattern="yyyy-MM-dd HH:mm"/>">
            </span>
               
            </li>
            <li>
                <span class="info-name">
                    预计送车地点：
                </span>
                <span class="info-val" style="padding-top:10px;">
                    <input type="text" id="sendAddress" disabled="disabled" value="${address }" style="border:1px solid  #000">
                </span>
            </li>
            <li>
                <span class="info-name">
                    预计取车时间：
                </span>
             <span class="info-val page-current" id="page-datetime-picker-end" style="padding-top:10px;">
				<input type="text" style="border:1px solid  #000" placeholder="" id="datetime-picker-end" placeholder="结束时间" readonly="" value="" name="endTime">
            </span>
            </li>
            <li>
                <span class="info-name">
                    预计取车地点：
                </span>
                <span class="info-val" style="padding-top:10px;">
                    <input type="text" id="getAddress" style="border:1px solid  #000">
                </span>
            </li>
        </ul>
    </div>
    <div class="btns">
         <input type="button" class="left" onclick="javascript:history.back(-1);" value="返回">
       <input class="bottom" type="button" id="submit" value="确认" >
    </div>
    
        <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
        	<script type="text/javascript">
		      $(document).on("pageInit", "#page-datetime-picker", function(e) {
		        $("#datetime-picker").datetimePicker({
		          toolbarTemplate: '<header class="bar bar-nav">\
		          <button class="button button-link pull-right close-picker">确定</button>\
		          <h1 class="title">选择时间</h1>\
		          </header>',
		        });
		       });
		        
		     $(document).on("pageInit", "#page-datetime-picker-end", function(e) {
				var oDate = new Date();
		    	var year = oDate.getFullYear();   //获取系统的年；
		    	var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
		    	month=month<10?('0'+month):month;
		    	var date = oDate.getDate()+1; // 获取系统日，
		    	var hour = oDate.getHours(); //获取系统时，
		    	var minute = oDate.getMinutes(); //分
		    	if(minute < 10){
		    	  	minute = '0'+minute;
		    	}
		        $("#datetime-picker-end").datetimePicker({
		          toolbarTemplate: '<header class="bar bar-nav">\
		          <button class="button button-link pull-right close-picker">确定</button>\
		          <h1 class="title"></h1>\
		          </header>',
		          value: [year, month, date, hour, minute]
		        });
		      });
		
		      $.init();
		       </script>
		       
</body>
</html>