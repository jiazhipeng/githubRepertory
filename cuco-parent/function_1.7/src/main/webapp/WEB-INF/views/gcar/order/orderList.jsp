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
    <link rel="stylesheet" href="${ctx}/static/gcar_html/sui_style/sm.css">
    <link href="${ctx}/static/gcar_html/css/bultertask.css" rel="stylesheet" type="text/css" />
     <%-- <link rel="stylesheet" href="${ctx}/static/gcar_html/sui_style/sm.css"> --%>
     <%-- <script src="${ctx}/static/gcar_html/sui_style/zepto.js"></script> --%>
     <script type="text/javascript" charset="utf8" src="${ctx}/static/gcar_html/utils/common-util.js?v=<%=new Date().getTime()%>"></script>
	<title>销售意向</title>
	</head>
	<body class="bulterPage">
		<div class="tab">
		    <p class="flex">联系人</p>
		    <p class="flex"><span class="stadus " onclick="showStatus(this)" id="status_span">状态</p>
		    <p class="flex"><span class="stadus " onclick="showSales(this)" id="sales_span">负责人</p>
		    <p class="flex">时间</p>
		</div>
		<div class="searchBox">
            <div class="searchSec">
                <a href="javascript:searchOrder();" class="searchBtn"></a>
                <input type="search" placeholder="请输入用户名或手机号" id="search_content">
            </div>
        </div>
		 <!--极车管家任务列表结束-->
		 <!--任务状态-->
		<div class="changeTask stadusDia sellDia" style="display: none;" id="status_div">
		    <div class="taskCon">
		        <ul id="status_list">
					<!-- 订单状态 -->
			        <li class='on' onclick='setStatus(100,this)'>全部</li>
					<li onclick='setStatus(1,this)'>入会申请</li>
					<li onclick='setStatus(6,this)'>升级申请</li>
					<li onclick='setStatus(2,this)'>待签约</li>
					<li onclick='setStatus(3,this)'>待付款</li>
					<!-- <li onclick='setStatus(4,this)'>待结款</li> -->
					<li onclick='setStatus(5,this)'>已结款</li>
					<li onclick='setStatus(0,this)'>已取消</li>
		        </ul>
		    </div>
	    </div>
	     <!--任务状态-->
		<div class="changeTask stadusDia sellDia" style="display: none;" id="sales_div">
		    <div class="taskCon">
		        <ul id="sales_list">
					<!-- 选择销售负责人 -->
					<li onclick='setSales("all",this)'>全部</li>
					<c:forEach items="${sales }" var="model">
			        <li onclick='setSales("${model.sysuserId}",this)'>${model.sysuserName}</li>
			        </c:forEach> 
		        </ul>
		    </div>
	    </div>
        <!--订单列表开始-->
	    <ul class="list" id="order_list">
			<c:forEach items="${orderList}" var="model">
			    <li>
			        <a href="${ctx}/wechat/order/detail?id=${model.id}&loginId=${loginId}">
			            <p class="flex">${model.memberName}<br>${model.memberMobile}</p>
			            <p class="flex">${model.statusInfo}</p>
			            <p class="flex">${model.modifier}</p>
			            <p class="flex"><fmt:formatDate value="${model.created}" pattern="MM-dd HH:mm"/></p>
			        </a>
			    </li>
			 </c:forEach>   
		</ul>
	     <!--订单列表结束-->
		 <form action = "" id = "myForm" style="display:none">
			<input type="hidden" name= "status" id = "status" />
			<input type="hidden" name= "modifierId" id = "modifierId" />
			<input type="hidden" name= "loginId" id = "loginId"  value="${loginId}"/>
		</form>
		<div class="fixHeight"></div>
		<div class="fixBottom">
	    <a href="javascript:createOrder()" class="button">创建订单</a>
		</div>
		<section class="poper vipform" style='display:none'>
	        <section class="search">
	        <div id="page-city-picker" class="page page-current val" style="display:none;">
	            	<input placeholder="" type="hidden" id="city-picker" readonly=""/>
	    			<input name="cityId" id="cityId" type="hidden" value="${member.cityId==null?'2':member.cityId}"/>
	    			<input name="cityCode" id="cityCode" type="hidden" value="${member.city == null?'110000':member.city}"/>
	    			<input name="cityName" id="cityName" type="hidden" value="${member.cityName == null?'北京市':member.cityName}"/>
	    			<script>
	            	var rawCitiesData = null;
	            	$.ajax({
		   			     type: 'GET',
		   			  	 async:false,
		   			     url: '${ctx}/wechat/order/getNation',
		   			     dataType: "json",
		   			     success: function(data){
		   			    	rawCitiesData = data;
		   			    } 
	   				});
	            	  var province = "北京市";
	            	  if('${province}'!=null && '${province}'!=''){
	            		  province = '${province}';
	            		  if('${province}' =='中华人民共和国'){
	            			  province = "";
	            		  }
	            	  }
	            	  var city = "";
	            	  if('${member.cityName}'!=null && '${member.cityName}'!=''){
	            		  city = '${member.cityName}';
	            	  }
	            	  
	            	  if('${member.mobile}'!=null && '${member.mobile}'!=''){
	            		  if('${member.cityName}' == null || '${member.cityName}' == ''){
	            			  city = "";
	                		  province = "";
	                		  $("#cityId").val("");
	                		  $("#city").val("");
	                		  $("#cityName").val("");
	            		  }
	            		  
	            		  $("#city-picker").attr("disabled","disabled");
	
	            	  }
           	  
           			</script>  
			    </div>
	            <div class="form personal" >
	                <div class="row">
	                    <span>会员类型</span>
	                    <input type="radio" name="type"  value="0" checked onclick="changeType(0)"><label for="p">个人会员</label>
	                    <input type="radio" name="type"  value="1" onclick="changeType(1)"><label for="c">企业会员</label>
	                </div>
	                <div class='personal' id="personal">
	                	<div class="row clearfix">
		                    <span>姓名</span>
		                    <input type="text" placeholder="请输入姓名" id="memberName" name="name">
		                </div>
		                
		                <div class="row clearfix">
		                    <span>性别</span>
		                    <input type="radio" name="sex" value="1"><label for="m">男</label>
		                    <input type="radio" name="sex" value="0"><label for="f">女</label>
		                </div>
		                <div class="row  clearfix">
		                    <span>手机号码</span>
		                    <input type="tel" placeholder="请输入手机号" name="mobile" id="memberMobile" maxlength="11">
		                </div>
		                <div class="row clearfix page page-current" id="page-city-picker-person" style="position:relative; top:auto; bottom:auto;">
			                <span>城市</span>
			                <input type="text" placeholder="请选择城市" id="city-picker-person" readonly="">
		                </div>
	                </div>
	                
	                <div class='corp' id="corp" style='display:none'>
	                	<div class="row clearfix">
		                    <span>企业名称</span>
		                    <input type="text" placeholder="请输入企业名称" id="orgN">
		                </div>
		                <div class="row  clearfix">
		                    <span>组织机构代码</span>
		                    <input type="phone" placeholder="请输入组织机构代码" id="orgId" maxlength="15">
		                </div>
		                <div class="row  clearfix">
		                    <span>联系人</span>
		                    <input type="text" placeholder="请输入联系人" id="orgName">
		                </div>
		                <div class="row  clearfix">
		                    <span>联系电话</span>
		                    <input type="tel" placeholder="请输入联系电话" name="orgTel" id="orgTel" maxlength="11">
		                </div>
						<div class="row clearfix page page-current" id="page-city-picker-company" style="position:relative; top:auto; bottom:auto;">
			                <span>所属城市</span>
			                <input type="text" placeholder="请选择城市" id="city-picker-company" readonly="">
	                	</div>
	                </div>
	            </div>
	            <section class="btns">
	                <div class="btn cancel" onclick="createVipCancel()">取消</div>
	                <div class="btn sure" onclick="createVipSubmit()">确定</div>
	            </section>
	        </section>
	    </section>
	    <section class="poper searchform" style='display:none'>
	        <section class="search">
	           <div class="input">
	                <i></i>
	                <div class="in-box">
	                	<input type="text" placeholder="请输入会员手机号或组织机构代码" id="search_conditions">
	                </div>
	            </div>
	            <section class="btns">
	                <div class="btn cancel" onclick="searchCancel()">取消</div>
	                <div class="btn sure" onclick="searchSubmit()">确定</div>
	            </section>
	        </section>
	    </section>
	</body>
	
	<script src="${ctx}/static/gcar_html/sui_style/sm.js"></script>
	<script src="${ctx}/static/gcar_html/sui_style/sm-city-picker.js"></script>
	<script src="${ctx}/static/gcar_html/sui_style/demos.js"></script>
	<script>
	$(document).on("click", ".cancel-picker", function() {
		var cityName = $("#city-picker").attr("placeholder");
		$(".cityPicker").remove();
		console.log($("#page-city-picker").html());
		$("#page-city-picker").append('<input type="text" placeholder=" '+ cityName + '" id="city-picker"  class="cityPicker" readonly="" onclick = "cityPicker();"/>');
		console.log($("#page-city-picker").html());
		var pickerToClose = $('.picker-modal.modal-in');
		$.closeModal(pickerToClose);
		cityPicker();
	});
	
	function cityPicker(){
		$("#city-picker").cityPicker({
			//value: ['四川', '内江', '东兴区']
		});
	}
	
		$('.stadusDia').on('click',function(){
			$("#status_span").removeClass("staUp");
			$("#sales_span").removeClass("staUp");
			$(this).hide();
		});
		//显示状态列表
		function showStatus(obj){
			$(obj).addClass("staUp");
			$("#sales_div").hide();
			$("#sales_span").removeClass("staUp");
			$("#status_div").show();
		}
		//切换状态
		function setStatus(status,obj){
			if(100==status){
				$("#status").val("");
			}else{
				$("#status").val(status);
			}
			//交换样式
			$("#status_span").removeClass("staUp");
			$("#status_list li").removeClass("on");
			$(obj).addClass("on");
			$("#order_list").show();
			//ajax请求数据
			$.ajax({
				type: 'POST',
				url: "${ctx}/wechat/order/orderList",
				async:false,
				data:$("#myForm").serialize(),
				//  返回数据处理
				success: function(data){
					$("#order_list").empty();
					var bulterStr="";
					var loginId= $("#loginId").val();
					if(null!=data.orders){
						for(var i=0;i<data.orders.length;i++){
							var mobile = "";
							if(null!=data.orders[i].memberMobile && ""!=data.orders[i].memberMobile){
								mobile = data.orders[i].memberMobile;
							}
							bulterStr +=("<li>");
							bulterStr +=("<a href='${ctx}/wechat/order/detail?id="+data.orders[i].id+"&loginId="+$("#loginId").val()+"'>");
							bulterStr +=("<p class='flex'>"+data.orders[i].memberName+"<br/>"+mobile+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].statusInfo+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].modifier+"</p>");
							bulterStr +=("<p class='flex'>"+resolveCharacterDate(data.orders[i].created,'MM-dd hh:mm')+"</p>");
							bulterStr +=("</a>");
							bulterStr +=("</li>");
						}
					}
					$("#status_div").hide();
					$("#order_list").append(bulterStr);
				}
			});
		}
		function searchOrder(){
			var search_content = $("#search_content").val();
			/* if(null==search_content || ""==search_content){
				alert("请输入搜索条件");
				return false;
			} */
			//ajax请求数据
			$.ajax({
				type: 'POST',
				url: "${ctx}/wechat/order/orderSearch",
				async:false,
				data:{"memberName":search_content,"memberMobile":search_content},
				//  返回数据处理
				success: function(data){
					$("#order_list").empty();
					var bulterStr="";
					var loginId= $("#loginId").val();
					if(null!=data.orders){
						for(var i=0;i<data.orders.length;i++){
							var mobile = "";
							if(null!=data.orders[i].memberMobile && ""!=data.orders[i].memberMobile){
								mobile = data.orders[i].memberMobile;
							}
							bulterStr +=("<li>");
							bulterStr +=("<a href='${ctx}/wechat/order/detail?id="+data.orders[i].id+"&loginId="+$("#loginId").val()+"'>");
							bulterStr +=("<p class='flex'>"+data.orders[i].memberName+"<br/>"+mobile+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].statusInfo+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].modifier+"</p>");
							bulterStr +=("<p class='flex'>"+resolveCharacterDate(data.orders[i].created,'MM-dd hh:mm')+"</p>");
							bulterStr +=("</a>");
							bulterStr +=("</li>");
						}
					}
					$("#status_div").hide();
					$("#order_list").append(bulterStr);
				}
			});
		}
		//显示销售列表
		function showSales(obj){
			$(obj).addClass("staUp");
			$("#status_div").hide();
			$("#status_span").removeClass("staUp");
			$("#sales_div").show();
		}
		//切换负责人
		function setSales(memberId,obj){
			if("all"!=memberId){
				$("#modifierId").val(memberId);
			}else{
				$("#modifierId").val("");
			}
			//交换样式
			$("#sales_span").removeClass("staUp");
			$("#sales_list li").removeClass("on");
			$(obj).addClass("on");
			$("#order_list").show();
			//ajax请求数据
			$.ajax({
				type: 'POST',
				url: "${ctx}/wechat/order/orderList",
				async:false,
				data:$("#myForm").serialize(),
				//  返回数据处理
				success: function(data){
					$("#order_list").empty();
					var bulterStr="";
					var loginId= $("#loginId").val();
					if(null!=data.orders){
						for(var i=0;i<data.orders.length;i++){
							var mobile = "";
							if(null!=data.orders[i].memberMobile && ""!=data.orders[i].memberMobile){
								mobile = data.orders[i].memberMobile;
							}
							bulterStr +=("<li>");
							bulterStr +=("<a href='${ctx}/wechat/order/detail?id="+data.orders[i].id+"&loginId="+$("#loginId").val()+"'>");
							bulterStr +=("<p class='flex'>"+data.orders[i].memberName+"<br/>"+mobile+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].statusInfo+"</p>");
							bulterStr +=("<p class='flex'>"+data.orders[i].modifier+"</p>");
							bulterStr +=("<p class='flex'>"+resolveCharacterDate(data.orders[i].created,'MM-dd hh:mm')+"</p>");
							bulterStr +=("</a>");
							bulterStr +=("</li>");
						}
					}
					$("#sales_div").hide();
					$("#order_list").append(bulterStr);
				}
			});
		}
		//创建订单
		function createOrder(){
			$("#search_conditions").val("");
			$(".searchform").show();
			document.querySelector('.searchform').addEventListener('touchmove',function(e){
				e.stopPropagation();
				e.preventDefault();
			},false);
		}
		//搜索确定
		function searchSubmit(){
			var search_conditions = $("#search_conditions").val().trim();
			if(null==search_conditions||""==search_conditions){
				alert("请输入搜索条件");
				return false;
			}
			var fileData=null;
			if((/^1[3|4|5|7|8]\d{9}$/.test(search_conditions))){
				//手机号格式
				fileData = {"mobile":search_conditions};
			}else{
				//名称
				fileData = {"orgId":search_conditions};
			}
			//ajax请求数据
			$.ajax({
				type: 'POST',
				url: "${ctx}/wechat/order/searchMember",
				async:false,
				data:fileData,
				//  返回数据处理
				success: function(data){
					if(data.result==false){
						//影藏搜索框
						$(".searchform").hide();
						//会员创建页面
						$("#cityId").val("");
						$("#cityCode").val("");
						$("#cityName").val("");
						$("#memberName").val("");
						$("#memberMobile").val("");
						
						$("#orgN").val("");
						$("#orgId").val("");
						$("#orgName").val("");
						$("#orgTel").val("");
						$(".vipform").show();
						
						document.querySelector('.vipform').addEventListener('touchmove',function(e){
							e.stopPropagation();
							e.preventDefault();
						},false);
					}else if(data.result==true){
						//查到了相关信息直接跳转订单创建页面--添加冻结用户判断
						if(0==data.member.statuts){
							//表示该用户被冻结
							alert("该用户已被冻结");
						}else{
							//影藏搜索框
							$(".searchform").hide();
							window.location.href="${ctx}/wechat/order/toCreateOrder?loginId="+$("#loginId").val()+"&id="+data.member.id;						
						}
					}
				}
			});
			
		}
		//搜索取消
		function searchCancel(){
			//影藏搜索框
			$(".searchform").hide();
		}
		//创建会员取消
		function createVipCancel(){
			//影藏搜索框
			$(".vipform").hide();
		}
		//会员类型选择
		function changeType(type){
			$("#cityId").val("");
			$("#cityCode").val("");
			$("#cityName").val("");
			$("#memberName").val("");
			$("#memberMobile").val("");
			/* $("input[name='sex']").removeAttr('checked'); */
			$("#orgN").val("");
			$("#orgId").val("");
			$("#orgName").val("");
			$("#orgTel").val("");
			if(0==type){
				//选中个人会员
				$("#personal").show();
				$("#corp").hide();
			}
			else{
				$("#personal").hide();
				$("#corp").show();
			}
		}
		//创建会员确定
		function createVipSubmit(){
			var loginId = $("#loginId").val();
			var type = $("input[name='type']:checked").val();
			var cityId = $("#cityId").val();
			var cityCode = $("#cityCode").val();
			var cityName = $("#cityName").val();
			if(0==type){
				//代表个人
				var memberName = $("#memberName").val().trim();
				var sex = $("input[name='sex']:checked").val();
				var mobile = $("#memberMobile").val().trim();
				if(null==memberName || ""==memberName){
					alert("请填写会员姓名");
					return false;
				}
				if(!(/^[\u4e00-\u9fa5a-zA-Z ]+$/.test(memberName))){
					alert("名称仅支持中文、英文大小写、空格");
					return false;
				}
				if(null==sex || ""==sex){
					alert("请选择会员性别");
					return false;
				}
				if(null==mobile || ""==mobile){
					alert("请填写会员手机号");
					return false;
				}
				if(null==cityId || ""==cityId){
					alert("请选择会员所属城市");
					return false;
				}
				beforeData={"mobile":mobile};
				dateV={"type":type,"name":memberName,"sex":sex,"mobile":mobile,"loginId":loginId,"cityId":cityId,"cityCode":cityCode,"cityName":cityName};
			}else if(1 == type){
				//代表企业
				var orgN = $("#orgN").val().trim();//名称
				var orgId = $("#orgId").val().trim();//代码
				var orgName = $("#orgName").val().trim();//联系人
				var orgTel = $("#orgTel").val().trim();//联系电话
				if(null==orgN || ""==orgN){
					alert("请填写企业名称");
					return false;
				}
				if(!(/^[\u4e00-\u9fa5a-zA-Z ]+$/.test(orgN))){
					alert("名称仅支持中文、英文大小写、空格");
					return false;
				}
				if(null==orgId || ""==orgId){
					alert("请填写组织机构代码");
					return false;
				}
				if(null==orgName || ""==orgName){
					alert("请填写联系人");
					return false;
				}
				if(null==orgTel || ""==orgTel){
					alert("请填写联系电话");
					return false;
				}
				if(null==cityId || ""==cityId){
					alert("请选择会员所属城市");
					return false;
				}
				beforeData={"orgId":orgId};
				dateV ={"type":type,"name":orgN,"orgId":orgId,"orgName":orgName,"orgTel":orgTel,"loginId":loginId,"cityId":cityId,"cityCode":cityCode,"cityName":cityName};
			}
			//ajax请求数据
			$.ajax({
					url: basePath+"/wechat/order/beforeCreate",
					data: beforeData,
					success: function(data){
						if(data.result == false){
							alert(data.msg);
							return false;
						}else{
							$.ajax({
								type: 'POST',
								url: "${ctx}/wechat/order/createMember",
								data: dateV,
								//  返回数据处理
								success: function(data){
									if(data.result==false){
										alert(data.msg);
									}else if(data.result==true){
										//影藏搜索框
										$(".vipform").hide();
										 window.location.href="${ctx}/wechat/order/toCreateOrder?loginId="+$("#loginId").val()+"&id="+data.memberId;
																
									}
								}
							});
						}
					}
				});
		}
		
		
	</script>
</html>
