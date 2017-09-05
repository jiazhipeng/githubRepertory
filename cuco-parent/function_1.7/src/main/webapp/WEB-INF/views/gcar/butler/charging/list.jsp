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
	<title>充电列表</title>
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
                    <a href="javascript:setStatus(2,this)">待取车</a>
                    <a href="javascript:setStatus(3,this)">取车中</a>
                    <a href="javascript:setStatus(5,this)">充电中</a>
                    <a href="javascript:setStatus(1,this)">送车中</a>
                    <a href="javascript:setStatus(7,this)">已完成</a>
                    <a href="javascript:setStatus(8,this)">已取消</a>
                </p>
            </li>
            <li class="down" id="butler_li" onclick="showBulter(this)">
                <span>车管家</span>
                <p class="car-manager">
                    <a href="javascript:setButler('all',this);">全部</a>
                   	<c:forEach items="${butlers }" var="model">
			        	<a href="javascript:setButler('${model.sysuserId}','${model.sysuserName}',this);">${ model.sysuserName}</a>
			        </c:forEach>
                </p>
            </li>
            <li>时间</li>
        </ul>
        <ul class="charge-list" id="charging_list">
            <c:forEach items="${butlerTaskList}" var="model">
			    <li>
			    <a href="${ctx}/wechat/butlerTask/charging/detail?id=${model.id}&loginId=${loginId}">
		            <p class="flex">${model.memberName}<br>${model.memberMobile}</p>
		            <p class="flex">${model.statusInfo}</p>
		            <p class="flex">${model.operaterName}</p>
		            <p class="flex"><fmt:formatDate value="${model.planTime}" pattern="MM-dd HH:mm"/></p>
			    </a>
			    </li>
			 </c:forEach>
        </ul>
    </div>
    
    <form action = "" id = "myForm" style="display:none">
		<input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
		<input type="hidden" name = "operaterId" id="operaterId"/>
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
        $("#charging_list").show();
    }else {
        $(obj).attr("class","up");
        $("#butler_li").attr("class","down");
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
		url: "${ctx}/wechat/butlerTask/charging/butlerTaskList",
		async:false,
		data:$("#myForm").serialize(),
		//  返回数据处理
		success: function(data){
			$("#charging_list").empty();
			var bulterStr="";
			var loginId= $("#loginId").val();
			if(null!=data.butlerTaskList){
				for(var i=0;i<data.butlerTaskList.length;i++){
					var mobile = "";
					if(null!=data.butlerTaskList[i].memberMobile && ""!=data.butlerTaskList[i].memberMobile){
						mobile = data.butlerTaskList[i].memberMobile;
					}
					bulterStr +=("<li>");
					bulterStr +=("<a href='${ctx}/wechat/butlerTask/charging/detail?id="+data.butlerTaskList[i].id+"&loginId="+$("#loginId").val()+"'>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].memberName+"<br/>"+mobile+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].statusInfo+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].operaterName+"</p>");
					bulterStr +=("<p class='flex'>"+resolveCharacterDate(data.butlerTaskList[i].created,'MM-dd hh:mm')+"</p>");
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
	        $("#charging_list").show();
	    }else{
	        $(obj).attr("class","up");
	        $("#status_li").attr("class","down");
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
		url: "${ctx}/wechat/butlerTask/charging/butlerTaskList",
		async:false,
		data:$("#myForm").serialize(),
		//  返回数据处理
		success: function(data){
			$("#charging_list").empty();
			var bulterStr="";
			var loginId= $("#loginId").val();
			if(null!=data.butlerTaskList){
				for(var i=0;i<data.butlerTaskList.length;i++){
					var mobile = "";
					if(null!=data.butlerTaskList[i].memberMobile && ""!=data.butlerTaskList[i].memberMobile){
						mobile = data.butlerTaskList[i].memberMobile;
					}
					bulterStr +=("<li>");
					bulterStr +=("<a href='${ctx}/wechat/butlerTask/charging/detail?id="+data.butlerTaskList[i].id+"&loginId="+$("#loginId").val()+"'>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].memberName+"<br/>"+mobile+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].statusInfo+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTaskList[i].operaterName+"</p>");
					bulterStr +=("<p class='flex'>"+resolveCharacterDate(data.butlerTaskList[i].created,'MM-dd hh:mm')+"</p>");
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