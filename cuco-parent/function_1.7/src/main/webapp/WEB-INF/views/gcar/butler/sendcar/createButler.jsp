<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>

<html >
	<head>
	<meta charset="utf-8">
	<%--<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">--%>
        <%--<meta content="yes" name="apple-mobile-web-app-capable"/>--%>
        <%--<meta content="yes" name="apple-touch-fullscreen"/>--%>
        <%--<meta content="telephone=no" name="format-detection"/>--%>
    <%--<meta content="black" name="apple-mobile-web-app-status-bar-style">--%>
    <title>送车-创建任务</title>
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/dateUtils.js?v=<%=new Date().getTime()%>"></script>

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
                    会员名称：
                </span>
                <span class="info-val">
                   ${member.name!=null?member.name:'极车会员' }
                </span>
            </li>
            <li class="list-row">
                <span class="info-name">
                    联系电话：
                </span>
                <span class="info-val">
                    ${member.mobile!=null?member.mobile:''  }
                </span>
            </li>

        </ul>
        <ul class="charge-info-list marginBottom">
            <li>
                <a href="javascript:;">
                    <span class="info-name">
                        用车时间
                    </span>
                    <span class="info-val page-current" id="page-datetime-picker">
                        <input type="text" placeholder="" id="datetime-picker" readonly="" onchange="setGetCarTime()">

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
                <a href="javascript:showSendCarAddress();">
                    <span class="info-name">
                        用车地点：
                    </span>
                    <span class="info-val" id="sendCarAddress_span">
                        
                    </span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <span class="info-name">
                        还车时间
                    </span>
                    <span class="info-val page-current" id="page-datetime-picker-2">
                        <input type="text" placeholder="" id="datetime-picker-2" readonly="" disabled="disabled">
                    </span>
                </a>
            </li>
            <script>
			       $(document).on("pageInit", "#page-datetime-picker-2", function(e) {
			    	
			        $("#datetime-picker-2").datetimePicker({
			          toolbarTemplate: '<header class="bar bar-nav">\
			          <button class="button button-link pull-right close-picker">确定</button>\
			          <h1 class="title">选择时间</h1>\
			          </header>',
			         
			        });
			      });
			
			      $.init();
			  </script>
            <li>
                <a id="getCarAddress">
                    <span class="info-name">
                        还车地点：
                    </span>
                    <span class="info-val" id="getCarAddress_span">
                        
                    </span>
                </a>
            </li>
            <li>
                <a href="javascript:;" onclick="selectCarType();">
                    <span class="info-name">
                        启用车型
                    </span>
                    <span class="info-val" id="carTypeId_div">
                        
                    </span>
                    <input type="hidden" id="carTypeId_input" value=""/>
                </a>
            </li>
        </ul>
        <div class="btns">
            <a class="button cencle" onclick='location.href = document.referrer;' id="cencleButton">取消</a>
            <a class="button" onclick="submitCrateUsedCar()" id="submitCrateUsedCarButton">确定</a>
        </div>
    </div>
    
    <form action="${ctx}/wechat/butler/task/sendCar/createButlerTaskBySendCar" id = "myForm" style="display:none" method="post">
		<input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
		<input type="hidden" name= "planTime" id = "planTime" />
		<input type="hidden" name= "completeTime" id = "completeTime" />
		<input type="hidden" name= "taskGetCarAddress" id = "taskGetCarAddress"/>
		<input type="hidden" name= "taskSendCarAddress" id = "taskSendCarAddress"/>
		<input type="hidden" name= "carOperateId" id = "carOperateId"/>
	</form>
	
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
        		<div class="button" onclick='getCarAddressComplete();' id="">确定</div>
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
                <div class="button cencle" onclick='location.href = document.referrer;'>取消</div>
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
	<div class="car-type-page" style="display:none;" id="cartype_div">
        <ul class="car-type-list">
	        <c:forEach items="${cartypes }" var="model">
				    <li class="list-row">
                		<span class="info-name">
                			
                	    </span>
		                <span class="info-val">
		                	
		                </span>
		            </li>
	        </c:forEach>
        </ul>

        <a href="" class="button fixBtn">确定</a>
    </div>
     <%@ include file="edit_carType.jsp"%>

   </body>
   
<script type="text/javascript">
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
//变更取车地点
function showSendCarAddress(){
	$("#main_div").hide();
	$("#sendCar_address_div").css("display","block");
}
//变更取车地点取消
function sendCarAddressCancel(){
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}
//变更取车地点确定
function sendCarAddressComplete(){
	$("#sendCarAddress_span").empty();
	$("#sendCarAddress_span").html($("#sendCar_input").val());
	$("#taskSendCarAddress").val($("#sendCar_input").val());
    $("#getCarAddress").attr("onclick","showGetCarAddress();");
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}
function sendCar_cancel(){
	$("#sendCar_input").val("");
	$("#sendCar_address_div").hide();
	$("#main_div").css("display","block");
}

function buttonHide(){
    $("#cencleButton").hide();
    $("#submitCrateUsedCarButton").hide();
    $("#submitCrateUsedCarButton").attr("onclick","return false");
    $.showPreloader("正在努力为您创建任务");
}

function buttonShow(){
    $("#cencleButton").show();
    $("#submitCrateUsedCarButton").show();
    $("#submitCrateUsedCarButton").attr("onclick","submitCrateUsedCar(this)");
    $.hidePreloader();
}


function submitCrateUsedCar(){
    buttonHide();
    //会员id
    var memberId = '${member.id}';
    //管家id
    var operaterId = '${loginId}';
    //用车时间
    var planTime = $("#datetime-picker").val();
    //用车地点
    var taskSendCarAddress = trim($("#sendCarAddress_span").html());
    //还车时间
    var completeTime = $("#datetime-picker-2").val();
    //还车地点
    var taskGetCarAddress = trim($("#getCarAddress_span").html());
    //启用车型
    var carTypeId = $("#carTypeId_input").val();

    if(!planTime){
        alert("请选择用车时间");
        buttonShow();
        return;
    }

    if(!planTime){
        alert("请选择预计用车时间");
        buttonShow();
        return;
    }
    planTime = planTime.replace(/-/g,"/");
    planTime = new Date(planTime);
    var  butlerUsecarAdvance  = '${basicThreshold.butlerUsecarAdvance}';
    var minDate = DateUtil.dateAdd('h',parseInt(butlerUsecarAdvance),new Date());
  /*   if(planTime.getTime() <minDate.getTime()){
        alert("用车时间只能选择当前时间 "+ butlerUsecarAdvance +" 小时之后");
        $(obj).attr("onclick","submitCrateUsedCar(this)");
        return;
    } */

    var serviceTimeStart = '${basicThreshold.serviceTimeStart}';
    var serviceTimeEnd =  '${basicThreshold.serviceTimeEnd}';

    var array = DateUtil.toArray(planTime);
    var hours = array[3];
    var minutes = array[4];
    hours = parseInt(hours);
    minutes = parseInt(minutes);

    if(serviceTimeStart!=null && serviceTimeEnd!=null){
        var starthour = serviceTimeStart.substring(0,2);
        var startminute = serviceTimeStart.substring(3,5);
        var endhour = serviceTimeEnd.substring(0,2);
        var endminute = serviceTimeEnd.substring(3,5);
        var start= parseInt(starthour)*60*60+ parseInt(startminute)*60;
        var end= parseInt(endhour)*60*60+ parseInt(endminute)*60;
        var plan = parseInt(hours)*60*60+ parseInt(minutes)*60;
        console.log("start="+start+"-end="+end+"-plan="+plan);
        if(!(plan<=end && plan>=start)){
            alert("用车时间不在服务时间内，请选择服务时间内的时间，服务时间"+starthour+":"+startminute+"-"+endhour+":"+endminute);
            buttonShow();
            return ;
        }
    }


    if(!taskSendCarAddress){
        alert("请选择送车地点");
        buttonShow();
        return;
    }
    if(!completeTime){
        alert("请选择还车时间");
        buttonShow();
        return;
    }
    completeTime = completeTime.replace(/-/g,"/");
    completeTime = new Date(completeTime);
    array = DateUtil.toArray(completeTime);
    hours = array[3];
    minutes = array[4];
    hours = parseInt(hours);
    minutes = parseInt(minutes);



    if(serviceTimeStart!=null && serviceTimeEnd!=null){
        var starthour = serviceTimeStart.substring(0,2);
        var startminute = serviceTimeStart.substring(3,5);
        var endhour = serviceTimeEnd.substring(0,2);
        var endminute = serviceTimeEnd.substring(3,5);
        var start= parseInt(starthour)*60*60+ parseInt(startminute)*60;
        var end= parseInt(endhour)*60*60+ parseInt(endminute)*60;
        var plan = parseInt(hours)*60*60+ parseInt(minutes)*60;
        console.log("start="+start+"-end="+end+"-plan="+plan);
        if(!(plan<=end && plan>=start)){
            alert("还车时间不在服务时间内，请选择服务时间内的时间，服务时间"+starthour+":"+startminute+"-"+endhour+":"+endminute);
            buttonShow();
            return ;
        }
    }


    if(!taskGetCarAddress){
        alert("请选择还车地点");
        buttonShow();
        return;
    }

    if(!carTypeId){
        alert("请选择车型");
        buttonShow();
        return;
    }
    if(completeTime.getTime() <= planTime.getTime()){
        alert("还车时间必须大于用车时间");
        buttonShow();
        return;
    }



    var data = {};
    data["memberId"] = memberId;
    data["operaterId"] = operaterId;
    data["planTime"] = planTime;
    data["taskSendCarAddress"] = taskSendCarAddress;
    data["completeTime"] = completeTime;
    data["taskGetCarAddress"] = taskGetCarAddress;
    data["carTypeId"] = carTypeId;
    data["operaterId"] = operaterId;

    console.log(data);
    $.ajax({
        type: 'POST',
        url: "${ctx}/wechat/butler/task/sendCar/createButlerTaskBySendCar",
//        async:false,
        data:data,
        //  返回数据处理
        success: function(data){
            if(data.errcode == "ok"){
                alert(data.errmsg);
                location.href = "${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId=" + data.result.id;
            }else{
                alert(data.errmsg);
                buttonShow();
            }
        }
    });
}

function formCancel() {
    //location.href = "${ctx}/wechat/butler/task/sendCar/toSearchMemberList?loginId=${loginId}";
    history.go(-1);
    location.reload();

}

function trim(testStr){
    if(!testStr){
        return testStr;
    }
    var resultStr = testStr.replace(/\ +/g,"");//去掉空格
    resultStr = resultStr.replace(/[ ]/g,"");    //去掉空格
    resultStr = resultStr.replace(/[\r\n]/g,"");//去掉回车换行
    return resultStr;
}

function setGetCarTime(){
    var planTime = $("#datetime-picker").val();
    if(planTime){
        $("#datetime-picker-2").removeAttr("disabled");
        planTime = planTime.replace(/-/g,"/");
        planTime = new Date(planTime);
        var getCarTime = DateUtil.dateAdd('d',1,planTime);
        getCarTime = getCarTime.format('yyyy-MM-dd hh:mm');
        $("#datetime-picker-2").val(getCarTime);
    }
}
</script>
</html>