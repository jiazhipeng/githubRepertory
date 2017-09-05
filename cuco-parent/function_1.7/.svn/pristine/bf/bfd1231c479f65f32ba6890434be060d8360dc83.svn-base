<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="yes" name="apple-touch-fullscreen" />
    <meta content="telephone=no" name="format-detection" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <title>共用人</title>
    <link href="${ctx}/static/gcar_html/css/css.css" rel="stylesheet" type="text/css" />
     <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
     <link href="${ctx}/static/gcar_html/css/popup.css" rel="stylesheet">
      <script src="${ctx}/static/gcar_html/js/popup.js"></script>
    <style>
        .com-people-list li {
            padding: 2% 0;
            font-size: 1.4rem;
            border-bottom: 1px solid #ccc;
            display: -webkit-box;
            -webkit-box-align: center;
            box-sizing: border-box;
        }
        
        .com-people-list .flex {
            -webkit-box-flex: 1;
            -webkit-box-align: center;
            padding:0 2%;
        }
        
        .com-people-list .flex:first-child {
            min-width: 25%;
            max-width: 25%;
        }
        
        .com-people-list .flex:last-child {
            text-align: center;
            min-width: 20%;
            max-width: 20%;
        }
        
        .com-people-list li a {
            color: rgb(23, 128, 219);
        }
        
        .list-dia {
            background: rgba(0, 0, 0, 0.8);
            display: _block;
            bottom: 0;
        }
        
        .list-dia .bottom {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
        }
        
        .list-dia a {
            border-bottom: 1px solid #ccc;
            line-height: 3.5rem;
            text-align: center;
            display: block;
            color: #333;
            font-size: 1.5rem;
            background: #fff;
        }
        
        .list-dia a span {
            padding: 0 26px;
        }
        
        .list-dia .select span {
            background: url("${ctx}/static/gcar_html/images/right.png") no-repeat right center;
            background-size: 14px;
        }
        
        .list-dia .cencel {
            margin-top: 3px;
        }
        
        .button {
            position: fixed;
            bottom: 0;
        }

        .com-people-tel{
            font-size:1.4rem;
            padding:4% 3%;
            position:relative;
        }
        .com-people-tel input{
            height:100%;
            width:100%;
            position:absolute;
            top:0;
            bottom:0;
            left:0;
            font-size:1.4rem;
            border-bottom:1px solid #ccc;
            background:none;
            padding-left:116px;
        }

    </style>
    
    <script type="text/javascript">
    	
	    function addShareMember(){
	    	var mobile = $("#mobile").val();
			if($.trim(mobile) =="" || !(/^1[3|4|5|7|8]\d{9}$/.test(mobile))){
				alert("请输入正确手机号码");
				return;
			}
			 var myc = myconfirm();
        	 myc.show('提示', '共用人是否支持与其他人共同操作同一辆车', function() {
        		 add('1',mobile);
             },'是',function(){
            	add('0',mobile);
             },'否'); 
	    	
	    }
	    function add(isShare,mobile){
	    	$("#addButton").addClass("greyBtn");
	    	$("#addButton").removeAttr("onclick");
		    	$.ajax({
					type: 'POST',
					url: "${ctx}/wechat/mycars/addShareMember",
					data:{"memberId":'${parentMemberId}',"memberItemId":'${memberItemId}',"memberMobile":mobile,"isShare":isShare},
					//  返回数据处理
					success: function(data){
			        	if(data.result){
			        		$(".com-people-list").show();
			    	    	$(".add-com-people").hide();
			        		location.reload();
			        	}else{
			        		alert(data.msg);
			        	}
			          
					},error:function(data){
						alert('添加失败');
					}
				}); 
	    }
	    
	    
	    /* 点击添加共有人按钮 */
	    function addShare(){
	    	//首先判断该用户有没有被冻结
	    	 $.ajax({
     				type: 'POST',
     				url: "${ctx}/wechat/mycars/checkMemberStatuts",
     				data:{"memberId":$("#memberId").val()},
     				//  返回数据处理
     				success: function(data){
     		        	if(!data){
     		        		if(3>'${fn:length(shareList)}'){
     		   		    	$(".com-people-list").hide();
     		   		    	$(".add-com-people").show();
	     		   	    	}else{
	     		   	    		alert("共用人最多为3人");
	     		   	    	}
     		        	}else{
     		        		  var myt=  tooltip();
     		 				  myt.show('提示','<a href="tel:400-9029-858">您已被冻结，请联系客服<span class="tel">400-9029-858</span></a>',function(){})

     		        	}
     		          
     				},error:function(data){
     					alert('校验失败');
     				}
     			});
	    	
	    }
	    
	    function closeModel(){
	    	$("#shareId").val('');
    		$("#isShare").val('');
	    	$(".diaBox").hide();
	    }
    
    	function editModelShow(isShare,shareId){
//     		alert(memberId);

    		$("#shareId").val(shareId);
    		$("#isShare").val(isShare);
    		if(isShare=='1'){
    			$("#select").addClass("select");
    		}else{
    			$("#select").removeClass("select");
    		}
    		
    		$('.diaBox').show();
    	}
    	function deleteShare(){
    		
    		 var myc = myconfirm();
        	 myc.show('提示', '您即将删除当前共用人', function() {
        		 $.ajax({
     				type: 'POST',
     				url: "${ctx}/wechat/mycars/deleteShare",
     				data:{"id":$("#shareId").val()},
     				//  返回数据处理
     				success: function(data){
     		        	if(data.isSuccess){
     		        		$(".diaBox").hide();
     		        		$("#shareId").val('');
     		        		$("#isShare").val('');
     		        		location.reload();
     		        	}
     		          
     				},error:function(data){
     					alert('删除失败');
     				}
     			});
             },'确定'); 
        	 
    		
    	}
    	
    	
    	$(function(){
    		$("#mobile").focus(function(){
//     			$("#addButton").addClass("button");
    			$("#addButton").removeClass("greyBtn");
				$("#addButton").attr("onclick","addShareMember()");
    		});
    		
    		$("#select").click(function(){
    			var isShare =$("#isShare").val();
        		if('1'==isShare){
        			isShare='0';
        		}else{
        			isShare='1';
        		}
    			$.ajax({
    				type: 'POST',
    				url: "${ctx}/wechat/mycars/updateShareMemberIsShare",
    				data:{"id":$("#shareId").val(),"isShare":isShare},
    				//  返回数据处理
    				success: function(data){
    		        	if(data.isSuccess){
    		        		$(".diaBox").hide();
    		        		$("#shareId").val('');
    		        		$("#isShare").val('');
    		        		location.reload();
    		        	}
    		          
    				},error:function(data){
    					alert('修改失败');
    				}
    			});
    		});
    		
    	})
    	
    </script>
</head>

<body>
	<input type="hidden" id="memberId" value="${memberId }">
	<input type="hidden" id="isShare" >
	<input type="hidden" id="shareId" >
    <!--共用人列表-->
    <div class="com-people-list" style="display:block;">
        <ul>
        <c:forEach items="${shareList }" var="item">
            <li>
                <div class="flex">
                   <c:choose>
                   	<c:when test="${item.member.name ==''}">极车会员</c:when>
                   	<c:when test="${item.member.name ==null}">极车会员</c:when>
                   	<c:otherwise>${item.member.name }</c:otherwise>
                   </c:choose>
                    <br> ${item.member.mobile }
                </div>
                <div class="flex">
	                <c:if test="${item.isShare=='1'}"> 可同时操作同一辆车</c:if>
	                <c:if test="${item.isShare=='0'}"> 不可同时操作同一辆车</c:if>
                </div>
                <div class="flex">
                    <c:if test="${item.isMain=='1'}">主账号</c:if>
	                <c:if test="${item.isMain=='0'}"> <a href="javascript:;" onclick="editModelShow(${item.isShare},${item.id })">编辑</a></c:if>
                </div>
            </li>
           </c:forEach>
        </ul>

        <div class="marginBottom"></div>
        <a href="javascript:;" class="button" onclick="addShare()">添加共用人</a>
    </div>

    <!--输入手机号-->
    <div class="add-com-people" style="display:none;">
        <div class="com-people-tel">
            <input type="tel" id="mobile" placeholder="请输入手机号" style="padding-left:4%;">
        </div>
        <div class="marginBottom"></div>
        <a href="javascript:;" class="button" id="addButton" onclick="addShareMember()">确定</a>
    </div>



    <!--公用人操作-->
    <div class="diaBox list-dia">
        <div class="bottom">
            <a href="javascript:;" id="select" onclick="updateIsShare()"><span>同时操作同一辆车</span></a>
            <a href="javascript:;" onclick="deleteShare()"><span>删除共用人</span></a>
            <a href="javascript:;" class="cencel" onclick="closeModel()">取消</a>
        </div>
    </div>
</body>

</html>