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
    <title>取车任务查询</title>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/gcar_html/css/gjd/jcgs_two.css" rel="stylesheet" type="text/css" />
   </head>
   <body class="greyBg">
        
    <!--单车-->
        <div class="searc-sec">
            <div class="searchBox_black">
                <div class="searc-in-sec">
                    <a href="javascript:searchButlerByMember();" class="searchBtn"></a>
                    <input type="search" placeholder="请输入会员姓名或电话" id="search_condition" maxlength="11">
                </div>
                <a href="javascript:searchButlerByMemberCancel();" class="cencle-search">取消</a>
            </div>
            <ul class="search-list s-car-list" id="memberList_ul">
            
            </ul>

		<input type="hidden" name = "loginId" value="${loginId}" id="loginId"/>
        <div class="button fixed-btn" onclick='javascript:searchButlerByMember();'>确定</div>
        </div>
</body>
<script>
//搜索取消
function searchButlerByMemberCancel(){
	window.location.href="${ctx}/wechat/butlerTask/getCar/tobutlerCalendar?loginId=${loginId}";
}
//输入搜索条件之后进行搜索
function searchButlerByMember(){
	//得到搜索条件
	var search_condition = $("#search_condition").val();
	if(null==search_condition || ""==search_condition){
		alert("请输入检索条件");
		return false;
	}
	$.ajax({
		type: 'POST',
		url: "${ctx}/wechat/butlerTask/getCar/memberList",
		async:false,
		data:{"name":search_condition,"mobile":search_condition},
		//  返回数据处理
		success: function(data){
			$("#memberList_ul").empty();
			var memberStr="";
			var loginId= $("#loginId").val();
			if(null!=data.memberList){
				if(0==data.memberList.length){
					alert("查询不到该会员，请检查后重新输入");
				}
				for(var i=0;i<data.memberList.length;i++){
					memberStr +=("<li onclick='tobutler("+data.memberList[i].id+")'>");
					memberStr +=("<a href='Javascript:;'>");
					memberStr += (data.memberList[i].name);
					memberStr +=("</li>");
				}
			}
			$("#memberList_ul").append(memberStr);
		}
	});
}
function tobutler(memberId){
	
	window.location.href="${ctx}/wechat/butlerTask/getCar/toButlerTaskListByMember?memberId="+memberId+"&loginId="+$("#loginId").val();
}
</script>
</html>