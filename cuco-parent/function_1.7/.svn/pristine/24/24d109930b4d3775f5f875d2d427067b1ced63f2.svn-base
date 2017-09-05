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
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <!--时间插件  -->
    <link href="${ctx}/static/gcar_html/css/gjd/date/demos.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm.css" rel=s"stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/date/sm-extend.css" rel="stylesheet" type="text/css" />

    <link href="${ctx}/static/gcar_html/css/4v.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css?v=yy" rel="stylesheet" type="text/css" />   
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
    <style type="text/css">
         body {
            background: white;
            overflow:scroll;
        }
        .down{
           display: none;
        }
        
        .right{
           position: absolute;
           left: 40;
        }
        
        .bottom{
           width: 55%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
        }
        
        .left{
           width: 30%;
           height: 50px;
           background: #1e1e1e; 
           color: #fff;
        }
       
        .charge-page select{
        	border:1px solid #002a86;
            height: 25;
            width: 70%;
        }
        
        .charge-page input{
            border:1px solid #002a86;
            height: 25;
            width: 70%;
        }
        .charge-page textarea{
            border:1px solid #002a86;
            width: 70%;
        }
        *{padding: 0; margin: 0}
        .box{
            position: fixed;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.2);
            display: none;
        }
        .box1{
            width: 500px;
            height: 500px;
            position: fixed;left: 50%; top: 25%;
            margin-left: -250px;
            border: 1px solid #000000;
        }
        
      	.charge-page {
            font-size: 1.5rem;
        }
        
        .charge-page-car .charge-t {
            width: 100%;
            height: 45px;
            line-height: 45px;
            background: #1e1e1e;
            color: #fff;
            padding: 0 2%;
            display:flex;
            position: fixed;
		    z-index: 10;
		    left: 0;
		    top: 0;
        }
        
        .charge-page-car .charge-new {
            width: 100%;
            background: white;
            color: black;
            padding: 0 2%;
            padding-bottom: 50px;
    		padding-top: 45px;
        }
        
        .charge-new li {
            float: left;
            width: 26%;
            box-sizing: border-box;
            position: relative;
            line-height:20px;
            flex:1;
            text-align:center;
            
        }
        .charge-new li p {
            margin:0;
        } 
        
        .charge-t li {
            float: left;
            width: 23%;
            box-sizing: border-box;
            position: relative;
            flex:1;
            text-align:center;
        }
        .btns {
        	position: fixed;
		    bottom: 0;
		    /* overflow: hidden; */
		    left: 0;
		    width: 100%;
		    display: flex;
		    border-top: 1px #ccc solid;
		    /* padding-top: 50px; */
        }
        .btns button {
        	display: inline-block;
    		flex: 1;
        }
         .btns left {
    		flex: 1;
        }
        
        .fixBtnBox{
        	width:100%;
        	position:fixed;
        	left:0;
        	bottom:0; 
        	background:#fff;       	
        }
        .fixBtnBox input{
        	font-size:1.5rem;
        }
        .fixBtnBox .left{float:left}
        .fixBtnBox .bottom{float:right}
        input{
        	padding:0 5px;
        }
       
       
    </style>
<title>故障换车</title>
</head>
<body>

      <script>
      		function getByType(){
      			$('#selectCarDiv').css('display','block');
      			$('#repairCar').css('display','none');
      			showUsedCar();
      		}
      		
      		function getByTypeHide(){
      			$('#selectCarDiv').css('display','none');
      			$('#repairCar').css('display','block');
      		}
      		
      		
      		function selectCar(){
      			var carPlanNum = '';
      			var clss = $('#selectCarUl').children("li");
      			for(var i=0;i<clss.length;i++){
      				console.log(clss[i]);
      				var getClass = clss[i].getAttribute("class");
      				if(getClass === 'on'){
      					var carPlanNum = clss[i].getElementsByTagName("div")[0].innerText;
      					break;
      				}
      			}
      			getByTypeUrl(carPlanNum,${status},'${oldCarPlateNum}',${carOperatePlanId},${loginId},${powerUsedId},'${started}');
      		}
      		
      		function getByTypeUrl(newCarPlateNum,status,oldCarPlateNum,carOperatePlanId,loginId,powerUsedId,started){
      			
      			window.location.href = "toFailureChange?newCarPlateNum="+newCarPlateNum+"&status="+status+"&oldCarPlateNum="+oldCarPlateNum+"&carOperatePlanId="+carOperatePlanId+"&loginId="+loginId+"&powerUsedId="+powerUsedId+"&started="+started;
      		}
      		
   			function ajaxCar(){
      			 var t = $('#operateForm').serializeArray();	
      			 $.ajax({
		             type: "GET",
		             url: "replaceCar",
		             data:t,
		             dataType: "json",
		             success: function(data){
		                var obj = eval("("+data+")"); 
	                    alert(obj["msg"]);
	                    if(obj["code"] === 200){
	                    	window.location.href='toCar?carPlateNum=${oldCarPlateNum}&loginId=${loginId}';
	                    }
					 }
	 				});
      		}
      		$(function() {
      			$('#submit').click(function submitForm(){
      				$('#submit').unbind("click");
 					var sel = $('#operateType');
 				 	if(sel.find("option:selected").text() == '请选择车辆'){
 				 		alert('请选择更换车辆');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
 				 	}
 				 	if($('#remark').val() == ''){
 				 		alert('请填写维修原因');
 				 		$('#submit').bind("click",submitForm);
 				 		return;
 				 	}
 				 	console.log('111');
	 			   	ajaxCar();
      			})
      		})
     
      	
      </script>
		
       <form id="operateForm" action="platformOperation">
       <div id = "repairCar">
       <div class="charge-page" id = "main">
          <br/>
           更换车辆   &nbsp; 
            <c:if test="${newCarPlateNum == null}">
           		<input type="text" onclick="getByType()" value="请选择车辆">
           	</c:if>
           <c:if test="${newCarPlateNum != null}">
           		<input type="text" onclick="getByType()" value="${newCarPlateNum}">
           	</c:if>
			<br/><br/>
			<input type="hidden" id="carTypeIdHidden" value="${carOperate.carTypeId}"/>
            <input type="hidden" name="loginId" value="${loginId}" id="loginId">
		   <input type="hidden" name="status" value="${status}" id="status">
		   <input type="hidden" name="carOperatePlanId" value="${carOperatePlanId}" id="carOperatePlanId">
		   <input type="hidden" name="newCarPlateNum" id="newCarPlateNum" value="${newCarPlateNum}">
		   <input type="hidden" name="oldCarPlateNum" id="oldCarPlateNum" value="${oldCarPlateNum}">
            <p>维修原因 &nbsp;<textarea rows="3" cols="20" id="remark" name="remark" maxlength="250"></textarea></p>
         
    </div>
     <div class="fixBtnBox">  
       		<input class="left" type="button" onclick="javascript:history.back(-1);" value="返回" >&nbsp;&nbsp;
       		<input class="bottom" type="button" id="submit" value="更换车辆" >
    	</div>
    </div>
    
    <div id="selectCarDiv" style="display:none" class="distribution">
       
        <div class="moudel-list">
        <ul id="carTypeUL">
            <li class="active">
                车型列表
               <!-- 特斯拉ModelS -->
            </li>

        </ul>
    </div>
    <ul class="car-list" id="selectCarUl">
        <li style="padding-left:30%;">
          车辆列表
         </li> 
        <li> 
            <div class="flex">
            	车辆信息
               <!-- 京A·R5678<br> （限行）-->
            </div>
            <div class="flex">
            	车辆状态
               <!-- <p class="state">待分配</p> -->
            </div>
        </li>
    </ul>
  <div class="fixBtnBox">  
       	<input class="left" type="button"  onclick="getByTypeHide();" value="返回" >
   		<input class="bottom" type="button" onclick="selectCar()" value="确定">
    </div>	
    </div>
    	</form>
    	
    <script>
    
        function getcarTypeList(){
        var list;
        var datas = {oldCarPlateNum:'${oldCarPlateNum}',carOperatePlanId:'${carOperatePlanId}',status:'${status}',loginId:'${loginId}',powerUsedId:'${powerUsedId}',started:'${started}'};
        $.ajax({
            type: 'POST',
            url: "${ctx}/wechat/carOperate/getByType",
            async:false,
            data:datas,
            datatype:"json",
            //  返回数据处理
            success: function(data){
                if(data.errcode == "ok"){
                    list =   data.result;
                }else{
                    alert(data.errmsg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
        return list;
    }
    
        var carTypeLists = null;
    /**
     * 选择车辆
     */
    function showUsedCar() {
        carTypeLists = getcarTypeList();
        var carTypeUL = $("#carTypeUL");
        var li =  "";
        var active = "";
        var carOperates = null;
        var carTypeId = ${car.carTypeId};
        var isActive = false;
        for(var i = 0;i<carTypeLists.length;i++){
            if(carTypeId == carTypeLists[i].carTypeId){
                isActive = true;
                break;
            }
        }


        for(var i = 0;i<carTypeLists.length;i++){
            carOperates = carTypeLists[i].carOperates;
            active = "";
            if(i == 0 && isActive == false){
                active = "active";
            }
            if(carTypeId == carTypeLists[i].carTypeId){
                active = "active";
            }
            if(carOperates.length == 0){
                li +='<li class="'+active+'" onclick=\"getCarOperates('+carTypeLists[i].carTypeId+',this)\">'+carTypeLists[i].carTypeName+' (无)</li>';
            }else{
                li +='<li class="'+active+'" onclick=\"getCarOperates('+carTypeLists[i].carTypeId+',this)\">'+carTypeLists[i].carTypeName+' (有)</li>';
            }
            if(i == 0  || carTypeLists[i].carTypeId == carTypeId){
                setCarOperatesLiHtml(carOperates);
            }
        }
        carTypeUL.html(li);

        $("#selectCarDiv").css("display","block");
    }

    function clickCarLi(isLimitLine,carBrand,car_plate_num,car_type,car_operate_id,obj) {
        console.log(car_plate_num);
        console.log(car_type);
        console.log(car_operate_id);
        console.log(carBrand);
        if(isLimitLine==true){
            alert("此车辆限行，不能选择");
            return;
        }
        $("#carOperateId_copy").val(car_operate_id);
        $("#carOperateName_copy").val(carBrand + "   " + car_type + "   " + car_plate_num);
        $("#usedCar_copy").html($("#carOperateName").val());

        $(obj).siblings().removeClass("on");
        $(obj).addClass("on");

    }

    function getCarOperates(carTypeId,obj){
        if(carTypeLists == null){
                return;
            }
            var carOperates = null;
            for(var i = 0 ;i<carTypeLists.length;i++){
                if(carTypeLists[i].carTypeId == carTypeId){
                    carOperates = carTypeLists[i].carOperates;
                    break;
                }
            }

        setCarOperatesLiHtml(carOperates);
        $(obj).siblings().removeClass("active");
        $(obj).addClass("active");
        $("#carTypeIdHidden").val(carTypeId);

    }


    function setCarOperatesLiHtml(carOperates){

        var selectCarUl = $("#selectCarUl");
        var carOperatesLi = "";
        var carOperate = null;
        var statusInfo = "";
        var endTime = "";
        var isLimitLineInfo = "";
        if(carOperates == null || carOperates.length == 0){
            selectCarUl.html("<center style='margin:100% 0 0 0'>该车型无可用车辆，请选择其他车型</center>");
            return;
        }

        var carOperateId = $("#carOperateId").val();
        var on = "";
        for(var j = 0;j<carOperates.length;j++){
            carOperate = carOperates[j];
            if(carOperate.operatePlanStatusInfo){
                statusInfo = carOperate.operatePlanStatusInfo;
            }else{
                statusInfo = carOperate.statusInfo;
            }
            endTime = "--";
            if(carOperate.endTime){
                endTime = resolveCharacterDate(carOperate.endTime,"MM/dd hh:mm");
            }
            var color = "";
            if(null != carOperate.carColor){
                color = "("+carOperate.carColor+")";
            }
            isLimitLineInfo = "";
            if(carOperate.isLimitLine == true){
                isLimitLineInfo = "(限行)";
            }
            on = "";
            if(carOperateId == carOperate.id){
                on = "on"
            }
            if(carOperate.endTime){
                carOperatesLi+=
                        "<li class='"+on+"' onclick=\"clickCarLi("+carOperate.isLimitLine+",'"+carOperate.carBrand+"','"+carOperate.carPlateNum+"','"+carOperate.carType+"','"+carOperate.id+"',this)\">"+
                        '<div class="flex">'+
                        carOperate.carPlateNum + "  "+isLimitLineInfo+""+
                        '</div>'+
                        '<div class="flex">'+
                        '<p class="state">'+statusInfo+'</p>'+
                        '<p class="small">预计还车时间</p>'+
                        '<span class="time">'+endTime+'</span>'+
                        '</div>'+
                        '</li>';
            }else{
                carOperatesLi+=
                        "<li class='"+on+"' onclick=\"clickCarLi("+carOperate.isLimitLine+",'"+carOperate.carBrand+"','"+carOperate.carPlateNum+"','"+carOperate.carType+"','"+carOperate.id+"',this)\">"+
                        '<div class="flex">'+
                        carOperate.carPlateNum + "  "+isLimitLineInfo+""+
                        '</div>'+
                        '<div class="flex">'+
                        '<p class="state">'+statusInfo+'</p>'+
                        '</div>'+
                        '</li>';
            }
        }
        selectCarUl.html(carOperatesLi);
    }
</script>	
</body>
</html>