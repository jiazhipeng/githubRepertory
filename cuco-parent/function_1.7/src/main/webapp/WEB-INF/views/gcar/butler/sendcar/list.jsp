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
    <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>

        <title>送车任务列表</title>
        <style type="text/css">
        .charge-page {
            font-size: 1.5rem;
        }

        .charge-page .charge-t {
            width: 100%;
            height: 45px;
            line-height: 45px;
            background: #1e1e1e;
            color: #fff;
            padding: 0 2%;
        }

        .charge-t li {
            float: left;
            width: 23%;
            box-sizing: border-box;
            position: relative;
        }

        .charge-t .up span,
        .charge-t .down span {
            padding-right: 20px;
            background: url(/static/gcar_html/images/arrow_up.png) no-repeat right center;
            background-size: 12px;
        }

        .charge-t .down span{
            background-image: url(/static/gcar_html/images/arrow_down.png);
        }
        .charge-t .car-manager{
            height: 100%;
            padding-bottom: 60px;
            overflow: scroll;
            -webkit-overflow-scrolling: touch;
            box-sizing: border-box;
        }
        .charge-t .down .state-list,.charge-t .down .car-manager {
            display: none;
        }
        /* .charge-t .state-list {
            background: #1e1e1e;
            position: absolute;
            left: -15px;
            top: 44px;
            width: 100%;
        } */
        .charge-t .car-manager,.charge-t .state-list{
            background: rgba(0,0,0,0.8);
            position:fixed;
            width:100%;
            height:100%;
            left:0;
            top:44px;
            z-index:99;
        }
        .charge-t .car-manager a,.charge-t .state-list a{
            background:#fff;
            text-align:center;
            border-bottom:1px solid #ccc;
            line-height:40px;
            display:block;
        }
        .charge-t .car-manager .on,.charge-t .state-list .on{
            color:red;
        }
        /* .charge-t .state-list a {
            width: 100%;
            height: 30px;
            line-height: 30px;
            color: #fff;
            display: block;
            padding-left: 15px;
            box-sizing: border-box;
        } */

        .charge-list li {
            border-bottom: 1px solid #ccc;
            padding: 2%;
        }

        .charge-list a {
            display: -webkit-box;
            background: url(/static/gcar_html/images/arrow_right_grey.png) no-repeat right center;
            background-size: 9px;
            padding-right: 14px;
            -webkit-box-align: center;
        }

        .charge-list a p {
            -webkit-box-flex: 1;
            -webkit-box-align: center;
            min-width: 23%;
            max-width: 23%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .charge-t li:first-child,
        .charge-list a p:first-child {
            min-width: 31%;
            max-width: 31%;
        }

         @media screen and (max-width:330px) {
            .charge-page {
                font-size: 1.1rem;
            }
            .charge-list a {
                background-size: 8px;
            }
            .charge-list a p{
            	max-width:25%;
            	min-width:25%;
            }
        }
        @media screen and (min-width:330px) and (max-width:380px) {
            .charge-page {
                font-size: 1.3rem;
            }
        }
    </style>
	
	</head>
	<body>
    <div class="charge-page">
        <ul class="charge-t">
            <li>联系人</li>
            <li class="down" id="status_li" onclick="showStatus(this)">
                <span>状态</span>
                <p class="state-list">
                	<a href="javascript:setStatus('all',this)">全部</a>
                    <a href="javascript:setStatus(10,this)">待接单</a>
                    <a href="javascript:setStatus(0,this)">待送车</a>
                    <a href="javascript:setStatus(1,this)">送车中</a>
                    <a href="javascript:setStatus(4,this)">已到达</a>
                    <a href="javascript:setStatus(7,this)">已完成</a>
                    <a href="javascript:setStatus(8,this)">已取消</a>
                </p>
            </li>
            <li class="down" id="butler_li" onclick="showBulter(this)">
                <span>车管家</span>
                <p class="car-manager" style="background: #fff">
                    <a href="javascript:setButler('all',this);">全部</a>
                   	<c:forEach items="${butlers }" var="model">
			        	<a href="javascript:setButler('${model.sysuserId}','${model.sysuserName}',this);">${ model.sysuserName}</a>
			        </c:forEach>
                </p>
            </li>
            <li>时间</li>
        </ul>
        <ul class="charge-list" id="charging_list">
            <c:if test="${empty butlerTasks}">
                <span class="main">今日无送车任务</span>
            </c:if>
            <c:forEach items="${butlerTasks}" var="model">
                <c:if test="${model.status !=9}">
                    <li>
                        <a href="${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId=${model.id}">
                            <p class="flex">${model.memberName}<br>${model.memberMobile}</p>
                            <p class="flex">${model.statusInfo}</p>
                            <p class="flex">${model.operaterName}</p>
                            <p class="flex"><fmt:formatDate value="${model.planTime}" pattern="MM-dd HH:mm"/></p>
                        </a>
                    </li>
                </c:if>
			 </c:forEach>
        </ul>
    </div>
    
    <form action = "" id = "myForm" style="display:none">
    	<input type="hidden" name = "queryTime" value="${queryTime}" id="queryTime"/>
        <input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
        <input type="hidden" name = "operaterId" id="operaterId" value="${butlerSysuserId }"/>
		<input type="hidden" name = "operaterName" id="operaterName"/>
		<input type="hidden" name= "status" id = "status" />
	</form>
</body>
<script>
//显示状态列表
function showStatus(obj){
    if($(obj).attr("class") == 'up'){
        //交换样式
        $("#status_li").attr("class","down");
        $("#charging_list").css('display','block');
    }else {
        $(obj).attr("class","up");
        $("#butler_li").attr("class","down");
        $("#charging_list").css('display','none');
    }

}
//切换状态
function setStatus(status,obj){
	if("all"==status){
		$("#status").val("");
	}else{
		$("#status").val(status);
	}
	//交换样式
	$("#status_li").attr("class","down");
	$("#charging_list").show();
	//ajax请求数据
	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/butler/task/sendCar/taskSendCarList",
		async:false,
		data:$("#myForm").serialize(),
		//  返回数据处理
		success: function(data){
			$("#charging_list").empty();
			var bulterStr="";
            if(null != data.result){
                var result = data.result
                var model = null;
                for(var i=0;i<result.length;i++){
                    model = result[i];
                    if(model.status == 9){
                    	continue;
                    }
                    var mobile = "";
                    if(null!=model.memberMobile && ""!=model.memberMobile){
                        mobile = model.memberMobile;
                    }
                    bulterStr +=("<li>"); 
                    bulterStr +=("<a href='${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId="+model.id + "'>");
                    bulterStr +=("<p class='flex'>"+model.memberName+"<br/>"+mobile+"</p>");
                    bulterStr +=("<p class='flex'>"+model.statusInfo+"</p>");
                    if(model.status == 9){
                        bulterStr +=("<p class='flex'>"+model.customerName+"</p>");
                    }else{
                        bulterStr +=("<p class='flex'>"+model.operaterName+"</p>");
                    }
                    bulterStr +=("<p class='flex'>"+resolveCharacterDate(model.planTime,'MM-dd hh:mm')+"</p>");
                    bulterStr +=("</a>");
                    bulterStr +=("</li>");
                }
            }
			$("#charging_list").append(bulterStr);
		}
	});
}

//显示管家列表
function showBulter(obj){
    if($(obj).attr("class") == 'up'){
        //交换样式
        $("#butler_li").attr("class","down");
        $("#charging_list").css('display','block');
    }else{
        $(obj).attr("class","up");
        $("#status_li").attr("class","down");
        $("#charging_list").css('display','none');
    }

}
//切换管家负责人
function setButler(memberId,memberName,obj){
	if("all"!=memberId){
		$("#operaterId").val(memberId);
		$("#operaterName").val(memberName);
	}else{
		$("#operaterId").val("");
		$("#operaterName").val("");
	}
	//交换样式
	$("#butler_li").attr("class","down");
	$("#charging_list").show();
	//ajax请求数据
	$.ajax({
		type: 'POST',
        url: "${ctx}/wechat/butler/task/sendCar/taskSendCarList",
		async:false,
		data:$("#myForm").serialize(),
		//  返回数据处理
		success: function(data){
			$("#charging_list").empty();
			var bulterStr="";
			if(null != data.result){
			    var result = data.result
                var model = null;
                for(var i=0;i<result.length;i++){
			        model = result[i];
			        if(model.status == 9){
                    	continue;
                    }
					var mobile = "";
					if(null!=model.memberMobile && ""!=model.memberMobile){
						mobile = model.memberMobile;
					}
					bulterStr +=("<li>");
					bulterStr +=("<a href='${ctx}/wechat/butler/task/sendCar/toEditSendCarTask?taskId="+model.id + "'>");
					bulterStr +=("<p class='flex'>"+model.memberName+"<br/>"+mobile+"</p>");
					bulterStr +=("<p class='flex'>"+model.statusInfo+"</p>");
                    if(model.status == 9){
                        bulterStr +=("<p class='flex'>"+model.customerName+"</p>");
                    }else{
                        bulterStr +=("<p class='flex'>"+model.operaterName+"</p>");
                    }
					bulterStr +=("<p class='flex'>"+resolveCharacterDate(model.planTime,'MM-dd hh:mm')+"</p>");
					bulterStr +=("</a>");
					bulterStr +=("</li>");
				}
			}
			$("#charging_list").append(bulterStr);
		}
	});
}
</script>
</html>