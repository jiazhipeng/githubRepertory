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
    
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />
    <!--时间插件end  -->
	<!--高德地图插件  -->
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b861063582ce96fb14c1e8d61a800608&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <!--高德地图插件end  -->
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
   </head>
   <body class="greyBg">
    <div class="charge-page" id="main_div">
        <ul class="charge-info-list">
            <li class="list-row">
                <span class="info-name">
                   	 用车会员：
                </span>
                <span class="info-val">
                    ${butlerTask.memberName!=null?butlerTask.memberName:'极车会员' }<c:if test="${not empty butlerTask.memberMobile}"><a href="tel:${butlerTask.memberMobile}" class="tel"> ( ${butlerTask.memberMobile} ) </a></c:if>
                </span>
            </li>
            <c:if test="${butlerTask.status==2}">
            	<li>
                <a href="javascript:;">
                    <span class="info-name">
                        	取车时间：
                    </span>
                    <span class="info-val page-current" id="page-datetime-picker">
    					<input type="text" placeholder="" id="datetime-picker" readonly="" value="<fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>">
                    </span>
                </a>
            	</li>
            	<script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/js/date/sm.js"></script>
            	<script>
			       $(document).on("pageInit", "#page-datetime-picker", function(e) {
			    	
			        $("#datetime-picker").datetimePicker({
			          toolbarTemplate: '<header class="bar bar-nav">\
			          <button class="button button-link pull-right close-picker">确定</button>\
			          <h1 class="title">选择时间</h1>\
			          </header>',
			          
			        });
			      });
			
			      $.init();
			    </script>
	            <li>
	                <a href="javascript:showGetCarAddress();">
	                    <span class="info-name">
	                        	取车地点：
	                    </span>
	                    <span class="info-val" id="getCarAddress_span">
	                        	${butlerTask.taskGetCarAddress }
	                    </span>
	                </a>
	            </li>
            </c:if>
            <c:if test="${butlerTask.status !=2}">
            	 <li class="list-row">
                    <span class="info-name">
                        	取车时间：
                    </span>
                    <span class="info-val">
                        <fmt:formatDate value="${butlerTask.planTime }" pattern="yyyy-MM-dd HH:mm"/>
                    </span>
            	</li>
	            <li class="list-row">
                    <span class="info-name">
                        	取车地点：
                    </span>
                    <span class="info-val">
                        	${butlerTask.taskGetCarAddress }
                    </span>
	            </li>
            </c:if>
           <c:if test="${butlerTask.status==2 || butlerTask.status==3}">
            <li>
                <a href="javascript:showSendCarAddress();">
                    <span class="info-name">
                        	送车地点：
                    </span>
                    <span class="info-val" id="sendCarAddress_span">
                       	${butlerTask.taskSendCarAddress }
                    </span>
                </a>
            </li>
            </c:if>
            <c:if test="${butlerTask.status!=2 && butlerTask.status!=3}">
            <li class="list-row">
                 <span class="info-name">
                     	送车地点：
                 </span>
                 <span class="info-val">
                    	${butlerTask.taskSendCarAddress }
                 </span>
            </li>
            </c:if>
	            <li class="list-row">
	                <span class="info-name">
	                    	使用车辆：
	                </span>
	                <span class="info-val">
	                    	${carOperateMessage }
	                </span>
	            </li>
        </ul>
        <ul class="charge-info-list">
        	<c:if test="${butlerTask.status==2}">
            <li>
                <a href="javascript:showChoseButler();;">
                    <span class="info-name">
                      	  负责管家：
                    </span>
                    <span class="info-val" id="modifier_span">
                        ${butlerTask.operaterName }
                    </span>
                </a>
            </li>
            </c:if>
            <c:if test="${butlerTask.status != 2}">
	            <li class="list-row">
	                <span class="info-name">
	                  	  负责管家：
	                </span>
	                <span class="info-val">
	                    ${butlerTask.operaterName }
	                </span>
	            </li>
            </c:if>
            <li class="list-row">
                   <span class="info-name">
                       	任务处理：
                   </span>
                   <span class="info-val">
                       <span class="task-state radioRoup">
                           <c:if test="${butlerTask.status == 2}">
	                           <em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="3" id='rd2'>
	                               <label for='rd2'>出发取车</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="8" id='rd3'>
	                              <label for='rd3'>取消任务</label>
	                           </em>
                           </c:if>
                           <c:if test="${butlerTask.status == 3}">
                           		<em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
                           		</em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="5" id='rd2'>
	                               <label for='rd2'>开始充电</label>
	                           </em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="8" id='rd3'>
	                              <label for='rd3'>取消任务</label>
	                           </em>
                           </c:if>
                           <c:if test="${butlerTask.status == 5}">
                           		<em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
                           		</em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="1" id='rd2'>
	                               <label for='rd2'>出发送车</label>
	                           </em>
                           </c:if>
                           <c:if test="${butlerTask.status == 1}">
                           		<em>
	                               <input type="radio" name="butlerStatus" value="100" id='rd1'>
	                               <label for='rd1'>跟进中</label>
                           		</em>
	                           <em>
	                               <input type="radio" name="butlerStatus" value="7" id='rd2'>
	                               <label for='rd2'>确认送达</label>
	                           </em>
                           </c:if>
                       </span>
                   </span>
            </li>
            <li data-val="aaa">
                <a href="javascript:showRemarks();">
                    <span class="info-name">
                       	 任务备注：
                    </span>
                    <span class="info-val" id="remarks" maxlength="250">
                                                                         
                    </span>
                </a>
            </li>
        </ul>
        <div class="charge-task-t">
           	 任务记录
        </div>
        <ul class="charge-task-list">
        	<c:forEach items="${logList}" var="model">
	        	<li>
		        	<div class="row">
		                <p>${model.statusInfo }</p>
		                <p>${model.modifier }</p>
		                <p><fmt:formatDate value="${model.created }" pattern="yyyy-MM-dd HH:mm"/> </p>
		            </div>
	                <div class="row">
	                	${model.remark }
	                </div>
	            </li>
        	</c:forEach>
        </ul>
        <div class="btns">
            <div class="button cencle" onclick='formCancel();'>取消</div>
        	<div class="button" onclick='formSubmit(this);' id="formSubmit">确定</div>
        </div>
    </div>
    
     <!--填写备注-->
    <div class="charge-page charge-dia remark" style="display:none;" id="remark_div">
        <textarea class="textarea" id="textarea" placeholder="请输入备注" maxlength="250"></textarea>
        <div class="btns">
            <div class="button cencle" onclick='remarkCancel();'>取消</div>
        	<div class="button" onclick='remarkComplete();'>确定</div>
        </div>
    </div>

    <!--管家列表-->
    <div class="charge-page charge-dia" style="display:none;" id="choseBulter_div">
        <div class="charge-tasker">
            <div class="tasker-t">当前负责管家:<span id="chooseButler">${butlerTask.operaterName!=null?butlerTask.operaterName:'暂未选择' }</span></div>
            <ul id="choseButler_ul">
               	<c:forEach items="${butlers }" var="model">
			        	<li onclick="choseButler('${model.sysuserId}','${model.sysuserName}',this)" <c:if test="${model.sysuserId == butlerTask.operaterId}">class='on'</c:if>> ${ model.sysuserName} </li>
			        </c:forEach>
            </ul>
        </div>
        <div class="btns">
            <div class="button cencle" onclick='choseBulterCancel();'>取消</div>
        	<div class="button" onclick='choseBulterComplete();' id="submitButton">确定</div>
        </div>
    </div>

    <!--地址选择-->
    <div class="charge-page charge-dia" style="display:none;" id="getCar_address_div">
        <div class="searc-sec searc-sec-nop">
            <div class="searchBox searc-sec-nop">
                <a href="javascript:;" class="searchBtn"></a>
                <input type="search" placeholder="请输入地点" id="getCar_input">
                <a href="javascript:getCar_cancel();" class="cencle-search">取消</a>
            </div>
            <!-- <div id="container"></div> -->
            <div class="btns">
                <div class="button cencle" onclick='getCarAddressCancel();'>取消</div>
        		<div class="button" onclick='getCarAddressComplete();' id="submitButton">确定</div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
	    //地图加载
	    var map = new AMap.Map("container", {
	        resizeEnable: true
	    });
	    //输入提示
	    var autoOptions = {
	        input: "getCar_input"
	    };
	    var auto = new AMap.Autocomplete(autoOptions);
	    var placeSearch = new AMap.PlaceSearch({
	        map: map
	    });  //构造地点查询类
	    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
	    function select(e) {
	        placeSearch.setCity(e.poi.adcode);
	        placeSearch.search(e.poi.name);  //关键字查询查询
	    }
	</script>
    <!--地址选择-->
    <div class="charge-page charge-dia" style="display:none;" id="sendCar_address_div">
        <div class="searc-sec searc-sec-nop">
            <div class="searchBox searc-sec-nop">
                <a href="javascript:;" class="searchBtn"></a>
                <input type="search" placeholder="请输入地点" id="sendCar_input">
                <a href="javascript:sendCar_cancel();" class="cencle-search">取消</a>
            </div>
            <!-- <div id="container"></div> -->
            <div class="btns">
                <div class="button cencle" onclick='sendCarAddressCancel();'>取消</div>
        		<div class="button" onclick='sendCarAddressComplete();' id="submitButton">确定</div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
	    //地图加载
	    var map = new AMap.Map("container", {
	        resizeEnable: true
	    });
	    //输入提示
	    var autoOptions = {
	        input: "sendCar_input"
	    };
	    var auto = new AMap.Autocomplete(autoOptions);
	    var placeSearch = new AMap.PlaceSearch({
	        map: map
	    });  //构造地点查询类
	    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
	    function select(e) {
	        placeSearch.setCity(e.poi.adcode);
	        placeSearch.search(e.poi.name);  //关键字查询查询
	    }
	</script>
    <form action="${ctx}/wechat/butlerTask/charging/edit" id = "myForm" style="display:none" method="post">
		<input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
		<input type="hidden" name= "status" id = "status" />
		<input type="hidden" name= "planTime" id = "planTime" value="<fmt:formatDate value="${butlerTask.planTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
		<input type="hidden" name= "taskGetCarAddress" id = "taskGetCarAddress"/>
		<input type="hidden" name= "taskSendCarAddress" id = "taskSendCarAddress"/>
		<input type="hidden" name= "remark" id = "remark" />
		<input type="hidden" name = "operaterId" id="operaterId" />
		<input type="hidden" name = "operaterName" id="operaterName" />
		<input type="hidden" name="id" value="${butlerTask.id}"/>
	</form>
	<!--以前的数据作对比-->
	<input type="hidden" id="oldPlanTime" value="${butlerTask.planTime}"/>
	<input type="hidden" id = "oldStatus" value="${butlerTask.status}"/>
	<input type="hidden" id = "oldTaskGetCarAddress" value="${butlerTask.taskGetCarAddress }"/>
	<input type="hidden" id = "oldTaskSendCarAddress" value="${butlerTask.taskSendCarAddress }"/>
	<input type="hidden" id="serviceTimeStart" value="${basicThreshold.serviceTimeStart}"/>
	<input type="hidden" id="serviceTimeEnd" value="${basicThreshold.serviceTimeEnd}"/>
</body>
<script>
//显示备注div
function showRemarks(){
	$("#main_div").hide();
	$("#remark_div").css("display","block");
}
//备注取消操作
function remarkCancel(){
	$("#textarea").val("");
	$("#remark_div").hide();
	$("#main_div").css("display","block");
}
//备注完成点击确定操作
function remarkComplete(){
	$("#remarks").html($("#textarea").val());
	$("#remark").val($("#textarea").val());
	$("#remark_div").hide();
	$("#main_div").css("display","block");
}
//选择管家切换样式
function choseButler(operaterId,operaterName,obj){
	//交换样式
	$("#choseButler_ul li").removeClass("on");
	$(obj).addClass("on");
	$("#chooseButler").html("当前负责管家："+operaterName);
	$("#operaterId").val(operaterId);
	$("#operaterName").val(operaterName);
}
function showChoseButler(){
	$("#main_div").hide();
	//直接显示
	$("#choseBulter_div").css('display','block');
}
//选择管家点击确定
function choseBulterComplete(){
	$("#modifier_span").empty();
	$("#modifier_span").html($("#operaterName").val());
	//负责管家重新赋值
	$("#choseBulter_div").hide();
	$("#main_div").css("display","block");
}
//选择管家点击取消
function choseBulterCancel(){
	$("#choseBulter_div").hide();
	$("#main_div").css("display","block");
}
//选择取车地点
function showGetCarAddress(){
	$("#main_div").hide();
	$("#getCar_address_div").css("display","block");
}
//取车取消
function getCarAddressCancel(){
	$("#getCar_address_div").hide();
	$("#main_div").css("display","block");
}
//取车完成
function getCarAddressComplete(){
	$("#getCarAddress_span").empty();
	$("#getCarAddress_span").html($("#getCar_input").val());
	$("#taskGetCarAddress").val($("#getCar_input").val());
	$("#getCar_address_div").hide();
	$("#main_div").css("display","block");
}
function getCar_cancel(){
	$("#getCar_input").val("");
	$("#getCar_address_div").hide();
	$("#main_div").css("display","block");
}
//选择送车地点
function showSendCarAddress(){
	$("#main_div").hide();
	$("#sendCar_address_div").css("display","block");
}
//送车取消
function sendCarAddressCancel(){
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}
//送车取消
function sendCarAddressComplete(){
	$("#sendCarAddress_span").empty();
	$("#sendCarAddress_span").html($("#sendCar_input").val());
	$("#taskSendCarAddress").val($("#sendCar_input").val());
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}
function sendCar_cancel(){
	$("#sendCar_input").val("");
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}
//取消
function formCancel(){
	window.location.href="${ctx}/wechat/butlerTask/charging/toButlerTaskList?loginId=${loginId}";
}
//提交
function formSubmit(obj){
	$(obj).attr("onclick","return false");
	var status = $("input[name='butlerStatus']:checked").val();
	var remark = $("#remark").val();
	var operaterId = $("#operaterId").val();
	var planTime = $("#datetime-picker").val();
	var oldPlanTime = $("#oldPlanTime").val()
	var oldStatus = $("#oldStatus").val();
	var taskGetCarAddress = $("#taskGetCarAddress").val();
	var oldTaskGetCarAddress = $("#oldTaskGetCarAddress").val();
	var taskSendCarAddress = $("#taskSendCarAddress").val();
	var oldTaskSendCarAddress = $("#oldTaskSendCarAddress").val();
	var serviceTimeStart = $("#serviceTimeStart").val();
	var serviceTimeEnd = $("#serviceTimeEnd").val();
	
	if(null!=planTime){
		planTime = planTime.replace(/-/g,"/")
		$("#planTime").val(new Date(planTime));
	}
	if(3==status){
		
		if(null==planTime || ""==planTime){
			if(null==oldPlanTime || ""==oldPlanTime){
				alert("请选择取车时间");
				$(obj).attr("onclick","formSubmit(this)");
				return false;
			}
		}else{
			var sDate = new Date(Date.parse(planTime.replace(/-/g, "/")));
			var currdate = new Date();
			console.log("sDate="+sDate.getTime()+"-currdate="+currdate.getTime());
			if(sDate.getTime() < currdate.getTime()){
				alert("请选择当前时间之后的时间");
				$(obj).attr("onclick","formSubmit(this)");
				return false;
			}
			if(serviceTimeStart!=null && serviceTimeEnd!=null){
				var starthour = serviceTimeStart.substring(0,2);
				var startminute = serviceTimeStart.substring(3,5);
				var endhour = serviceTimeEnd.substring(0,2);
				var endminute = serviceTimeEnd.substring(3,5);
				var hour = planTime.substring(11,13); 
				var minute = planTime.substring(14,16); //分
				var start= parseInt(starthour)*60*60+ parseInt(startminute)*60;
				var end= parseInt(endhour)*60*60+ parseInt(endminute)*60;
				var plan = parseInt(hour)*60*60+ parseInt(minute)*60;
				console.log("start="+start+"-end="+end+"-plan="+plan);
				if(!(plan<=end && plan>=start)){
					alert("当前所选时间不在服务时间内，请选择服务时间内的时间，服务时间"+starthour+":"+startminute+"-"+endhour+":"+endminute);
					$(obj).attr("onclick","formSubmit(this)");
					return false;
				}
			}
			planTime = planTime.replace(/-/g,"/")
			$("#planTime").val(new Date(planTime));
		}
		
		if((null==taskGetCarAddress || ""==taskGetCarAddress) && (null==oldTaskGetCarAddress || ""==oldTaskGetCarAddress)){
			alert("请选择取车地点");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
	}
	if(3==status || 5==status || 1==status){
		if((null==taskSendCarAddress || ""==taskSendCarAddress) && (null==oldTaskSendCarAddress || ""==oldTaskSendCarAddress)){
			alert("请选择送车地点");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
	}
	
	if(null==status || ""==status){
		alert("请选择任务处理方式");
		$(obj).attr("onclick","formSubmit(this)");
		return false;
	}
	//取消时，必须填写备注
	if(8==status){
		if(null==remark || ""==remark){
			alert("请输入备注信息");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
	}
	//变更管家只能在跟进中的判断
	if(null!=operaterId && ""!=operaterId){
		//表示变更了管家
		if(100 != status){
			alert("只能在选择跟进中时变更管家");
			$(obj).attr("onclick","formSubmit(this)");
			return false;
		}
	}
	//提交操作
	if(100 != status){
		//给状态赋值
		$("#status").val(status)
	}
	
	//提交表单
	planTime = $("#planTime").val();
	planTime = planTime.replace(/-/g,"/")
	$("#planTime").val(new Date(planTime));
	$.ajax({
        type: 'POST',
        url: "${ctx}/wechat/butlerTask/charging/edit",
        async:false,
        data:$("#myForm").serialize(),
        //  返回数据处理
        success: function(data){
            if(true==data.result){
            	window.location.href="${ctx}/wechat/butlerTask/charging/detail?id="+data.butlerTaskId+"&loginId="+data.loginId;
            }else{
            	 alert(data.msg);
            	 $(obj).attr("onclick","formSubmit(this)");
            }
        }
    });
	//$("#myForm").submit();
	
}
</script>
</html>