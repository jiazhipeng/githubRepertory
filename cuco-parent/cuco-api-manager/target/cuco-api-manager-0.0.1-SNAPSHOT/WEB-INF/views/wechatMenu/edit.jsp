<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="yes" name="apple-touch-fullscreen" />
<meta content="telephone=no" name="format-detection" />
<meta content="black" name="apple-mobile-web-app-status-bar-style">


<title>微信菜单</title>

<style type="text/css">
		* {
			padding: 0;
			margin: 0;
		}
		
		body {
			background: #eee;
		}
		
		.clearFix {
			overflow: hidden;
		}
		
		.fl {
			float: left;
		}
		
		.section {
			margin-bottom: 30px;
			background: #fff;
			padding: 30px 50px;
			width: 1000px;
			margin: auto;
			border-bottom: 1px solid #ccc;
		}
		
		.secTitle {
			font-weight: bold;
			margin-bottom: 20px;
			text-align: center;
		}
		
		textarea {
			width: 300px;
			height: 100px;
			resize: none;
			display: none;
			padding: 8px;
		}
		
		.subMenu {
			margin-top: 20px;
			padding: 20px;
			background: #eee;
			border-radius: 4px;
		}
		
		.subMenu .sub,.subMenu .sub {
			padding-top: 10px;
			margin-bottom: 10px;
			border-bottom: 1px dotted #aaa;
		}
		
		.reply {
			padding-top: 10px;
			margin-bottom: 15px;
		}
		
		.name {
			width: 100px;
			text-align: right;
			display: inline-block;
			padding: 0 6px;
		}
		
	.button{width: 100px; height: 40px; line-height: 40px; font-size:14px; font-weight:bold; background: #34b4ba; border:0; outline:none;cursor:pointer; color:#fff; display: block; margin: 30px auto; text-align: center; border-radius: 4px;}
</style>

<script type="text/javascript" src="/static/js/jquery-1.8.3.min.js">   </script>
	<script type="text/javascript">

	$(function(){
		$("input[type='radio']").change(function(){
			var curId = $(this).attr('id');
			var curVal = $(this).val();
			var prefix = curId.substring(0,curId.indexOf('_'));
			if(curId.indexOf('showSon') != -1){
				//是否显示子菜单 radio change 事件
				if("son"==curVal){
					//显示子菜单
					$("#"+prefix+"MainDiv").hide();
					$("#"+prefix+"SonDiv").show();
				}else{
					//显示主菜单
					$("#"+prefix+"MainDiv").show();
					$("#"+prefix+"SonDiv").hide();
				}
			}else{
				//子菜单radio change 事件
				if("click"==curVal){
					//自动回复
					$("#"+prefix+"_message").show();
					$("#"+prefix+"_url").hide();
				}else{
					//自动回复
					$("#"+prefix+"_message").hide();
					$("#"+prefix+"_url").show();
				}
			}
		});
	})
	
	function update(){
		var mainArray = [];
		for(var i = 1; i <= 3; i++){//主菜单
			//1.判断是否有主菜单
			var curMainId = "mainMenu0"+i;
			var curMainName = $.trim($("#"+curMainId).val());
			//alert($("#"+curMainId).val()+"\n"+$.trim($("#"+curMainId).val()));
			if(null == curMainName || ""==curMainName){
				//alert("主菜单"+i+"没有填写");
				continue;
			}else if(curMainName.replace(/[^\x00-\xff]/g, "**").length > 8){//.replace(/[^\x00-\xff]/g, "**").length;
				alert("主菜单"+i+"名称过长(最多4个汉字[8个字母])");
				return;
			}
			var curMainMenu = {name:curMainName};
			//2.判断是否有子菜单
			var checkSonName = curMainId+"_showSon";
			var menuType = $("input:radio[name="+checkSonName+"]:checked").val();
			if("main"==menuType){//没有子菜单
				curMainMenu.hasSubmenu = "0";
				var menuTypeId = curMainId+"_type";
				var menuType = $("input:radio[name="+menuTypeId+"]:checked").val();
				curMainMenu.type = menuType;
				//alert("curType:"+curType);
				if("view"==menuType){
					//1.view
					var menuUrl = $.trim($("#"+curMainId+"_url").val());
					//判断url是否为空
					if(null == menuUrl || ""==menuUrl || "http://"==menuUrl){
						//alert("请填写主菜单"+i+"对应的跳转路径");
						return;
					}
					curMainMenu.url = menuUrl;
				}else{
					//2.click
					var menuMsg = $.trim($("#"+curMainId+"_message").val());
					//判断message是否为空
					if(null == menuMsg || ""==menuMsg){
						alert("请填写主菜单"+i+"对应的回复内容");
						return;
					}
					curMainMenu.message = menuMsg;
					curMainMenu.msgKey = "key" + i;
				}
			}else{//存在子菜单
				curMainMenu.hasSubmenu = "1";
				//处理子菜单
				var sonArray = [];
				var chooseNum = 0;
				for(var j = 1; j <= 5; j++){//子菜单
					var curSonPrefix = "subMenu0"+i+"0"+j;
					var curSonName = $("#"+curSonPrefix+"_name").val();
					if(null == curSonName || ""==curSonName){
						//alert("主菜单"+i+"的子菜单"+j+"没有填写");
						continue;
					}else if(curSonName.replace(/[^\x00-\xff]/g, "**").length > 14){
						alert("主菜单"+i+"的子菜单"+j+"名称过长(最多7个汉字[14个字母])");
						return;
					}
					var curSonMenu = {name:curSonName};
					var curTypeId = curSonPrefix+"_type";
					var curType = $("input:radio[name="+curTypeId+"]:checked").val();
					curSonMenu.type = curType;
					//alert("curType:"+curType);
					if("view"==curType){
						//1.view
						var curUrl = $.trim($("#"+curSonPrefix+"_url").val());
						//判断url是否为空
						if(null == curUrl || ""==curUrl || "http://"==curUrl){
							alert("请填写主菜单"+i+"的子菜单"+j+"对应的跳转路径");
							return;
						}
						curSonMenu.url = curUrl;
					}else{
						//2.click
						var curMsg = $.trim($("#"+curSonPrefix+"_message").val());
						//判断message是否为空
						if(null == curMsg || ""==curMsg){
							alert("请填写主菜单"+i+"的子菜单"+j+"对应的回复内容");
							return;
						}
						curSonMenu.message = curMsg;
						curSonMenu.msgKey = "key" + i + j;
					}
					chooseNum++;
					sonArray.push(curSonMenu);
				}
				if(chooseNum == 0){
					alert("请填写主菜单"+i+"对应的子菜单");
					return;
				}
				curMainMenu.subMenu = sonArray;
			}
			mainArray.push(curMainMenu);
		}
		if(mainArray.length==0){
			alert("您尚未填写任何菜单！");
			return;
		}
		var data = JSON.stringify(mainArray);
		//alert(data);
		if(confirm("确定更新微信菜单？")){
			//alert("更新开始.....");
			$.ajax({
			     type: 'POST',
			     url: '${ctx}/wechat/menu/save',
			     dataType: "json",
			     data: {
			    	 "data":data		 
			     },
			     success: function(data){
			    	 alert(data.data.msg);
			    } 
			});
		}
	}
</script>

</head>

<body>
	<div class="section">
		<p class="secTitle">主菜单一</p>
		<div>
			<div class="menu">
				主菜单名称:<input type="text" id="mainMenu01"> 是否需要子菜单：
				<input type="radio" name="mainMenu01_showSon" id="mainMenu01_showSon" value="son" checked="checked">是
				<input type="radio" name="mainMenu01_showSon" id="mainMenu01_showSon" value="main">否
				<div id="mainMenu01MainDiv" style="display:none;">
					<br>
					回复方式:
					<input type="radio" name="mainMenu01_type" id="mainMenu01_type" value="view" checked="checked">跳转url
					<input type="radio" name="mainMenu01_type" id="mainMenu01_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl">回复内容:</span>
						<span class="fl">
							<input type="text" id="mainMenu01_url" value="http://">
							<textarea id="mainMenu01_message" value="" placeholder="请输入自动回复内容"></textarea>
						</span>
						<!-- <input type="text" id="mainMenu01_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					</div>
				</div>
			</div>
			<div class="subMenu" id="mainMenu01SonDiv">
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0101_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0101_type" id="subMenu0101_type" value="view" checked="checked">跳转url
					<input type="radio" name="subMenu0101_type" id="subMenu0101_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0101_url" value="http://">
					<textarea id="subMenu0101_message" value="" placeholder="请输入自动回复内容"></textarea>
						</span>
					</div>
					<!-- <input type="text" id="subMenu0101_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0102_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0102_type" id="subMenu0102_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0102_type" id="subMenu0102_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0102_url" value="http://">
					<textarea id="subMenu0102_message" value="" placeholder="请输入自动回复内容"></textarea>
						</span>
					</div>
					<!-- <input type="text" id="subMenu0102_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0103_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0103_type" id="subMenu0103_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0103_type" id="subMenu0103_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0103_url" value="http://">
					<textarea id="subMenu0103_message" value="" placeholder="请输入自动回复内容"></textarea>
</span>
					</div>
					<!-- <input type="text" id="subMenu0103_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0104_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0104_type" id="subMenu0104_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0104_type" id="subMenu0104_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0104_url" value="http://">
					<!-- <input type="text" id="subMenu0104_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0104_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0105_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0105_type" id="subMenu0105_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0105_type" id="subMenu0105_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0105_url" value="http://">
					<!-- <input type="text" id="subMenu0105_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0105_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<p class="secTitle">主菜单二</p>
		<div>
			<div class="menu">
				主菜单名称:<input type="text" id="mainMenu02"> 是否需要子菜单：
				<input type="radio" name="mainMenu02_showSon" id="mainMenu02_showSon" value="son" checked="checked">是
				<input type="radio" name="mainMenu02_showSon" id="mainMenu02_showSon" value="main">否
				<div id="mainMenu02MainDiv" style="display:none">
					<br>
					回复方式：<input type="radio" name="mainMenu02_type" id="mainMenu02_type" value="view" checked="checked">跳转url
					   	   <input type="radio" name="mainMenu02_type" id="mainMenu02_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl">回复内容:</span>
						<span class="fl">
							<input type="text" id="mainMenu02_url" value="http://">
							<textarea id="mainMenu02_message" value="" placeholder="请输入自动回复内容" ></textarea>
						  <!-- <input type="text" id="mainMenu02_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
						</span>
					</div>
				</div>
			</div>
			<div class="subMenu" id="mainMenu02SonDiv">
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0201_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0201_type" id="subMenu0201_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0201_type" id="subMenu0201_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0201_url" value="http://">
					<!-- <input type="text" id="subMenu0201_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0201_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0202_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0202_type" id="subMenu0202_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0202_type" id="subMenu0202_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0202_url" value="http://">
					<!-- <input type="text" id="subMenu0202_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0202_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0203_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0203_type" id="subMenu0203_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0203_type" id="subMenu0203_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0203_url" value="http://">
					<!-- <input type="text" id="subMenu0203_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0203_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0204_name"> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0204_type" id="subMenu0204_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0204_type" id="subMenu0204_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0204_url" value="http://">
					<!-- <input type="text" id="subMenu0204_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0204_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0205_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0205_type" id="subMenu0205_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0205_type" id="subMenu0205_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0205_url" value="http://">
					<!-- <input type="text" id="subMenu0205_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0205_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section">
		<p class="secTitle">主菜单三</p>
		<div>
			<div class="menu">
				主菜单名称:<input type="text" id="mainMenu03"> 是否需要子菜单：
				<input type="radio" name="mainMenu03_showSon" id="mainMenu03_showSon" value="son" checked="checked">是
				<input type="radio" name="mainMenu03_showSon" id="mainMenu03_showSon" value="main">否
				<div id="mainMenu03MainDiv" style="display:none">
					<br>
					回复方式：<input type="radio" name="mainMenu03_type" id="mainMenu03_type" value="view" checked="checked">跳转url
					   	   <input type="radio" name="mainMenu03_type" id="mainMenu03_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl">回复内容:</span>
						<span class="fl">
							<input type="text" id="mainMenu03_url" value="http://">
						  	<!-- <input type="text" id="mainMenu03_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
						  	<textarea id="mainMenu03_message" value="" placeholder="请输入自动回复内容" ></textarea>
						</span>
					</div>
				</div>
			</div>
			<div class="subMenu" id="mainMenu03SonDiv">
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0301_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0301_type" id="subMenu0301_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0301_type" id="subMenu0301_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0301_url" value="http://">
					<!-- <input type="text" id="subMenu0301_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0301_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0302_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0302_type" id="subMenu0302_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0302_type" id="subMenu0302_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0302_url" value="http://">
					<!-- <input type="text" id="subMenu0302_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0302_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0303_name"> <br> <br><span class="name">回复方式:</span>
					<input type="radio" name="subMenu0303_type" id="subMenu0303_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0303_type" id="subMenu0303_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0303_url" value="http://">
					<!-- <input type="text" id="subMenu0303_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0303_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0304_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0304_type" id="subMenu0304_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0304_type" id="subMenu0304_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0304_url" value="http://">
					<!-- <input type="text" id="subMenu0304_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0304_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
				<div class="sub">
					<span class="name">子菜单名称:</span><input type="text" id="subMenu0305_name"><br> <br> <span class="name">回复方式:</span>
					<input type="radio" name="subMenu0305_type" id="subMenu0305_type" value="view" checked>跳转url
					<input type="radio" name="subMenu0305_type" id="subMenu0305_type" value="click">自动回复
					<div class="reply clearFix">
						<span class="fl name">回复内容:</span>
						<span class="fl">
					<input type="text" id="subMenu0305_url" value="http://">
					<!-- <input type="text" id="subMenu0305_message" value="" placeholder="请输入自动回复内容" style="display:none"> -->
					<textarea id="subMenu0305_message" value="" placeholder="请输入自动回复内容"></textarea>
					</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br/>
		<button onclick="update();" class="button">更新菜单</button>
</body>

</html>
