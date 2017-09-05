<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>东北电网调度运行策划安全风险管控系统</title>

<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->        
    
    <title>treetest</title>
     
  
   
	<link href="${rc.getContextPath()}/static/css/style.css" rel="stylesheet" />
	<link href="${rc.getContextPath()}/static/css/style_responsive.css" rel="stylesheet" />

	<link type="text/css" href="${rc.getContextPath()}/static/jquery-ui-bootstrap/assets/css/font-awesome.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" media="screen" href="${rc.getContextPath()}/static/timepicker/jquery-ui-timepicker-addon.css" />
	<!--[if IE 7]>
	<link rel="stylesheet" href="${rc.getContextPath()}/static/jquery-ui-bootstrap/assets/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/static/jquery-ui-bootstrap/css/custom-theme/jquery.ui.1.9.2.ie.css"/>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/static/jqgrid/css/ui.jqgrid.css" />
	  <link rel="stylesheet" href="${rc.getContextPath()}/static/jquery.zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="${rc.getContextPath()}/static/assets/uniform/themes/agent/css/uniform.agent.min.css" rel="stylesheet" />	
    <link type="text/css" href="${rc.getContextPath()}/static/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.9.2.custom.css" rel="stylesheet" />
	<link href="${rc.getContextPath()}/static/assets/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet"></link>
	<link href="${rc.getContextPath()}/static/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
	<link type="text/css" href="${rc.getContextPath()}/static/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.9.2.custom.css" rel="stylesheet" />
	<link href="${rc.getContextPath()}/static/css/mystyle.css" rel="stylesheet"  rel="stylesheet"  />
	 <link href="${rc.getContextPath()}/static/css/jqGrid.bootstrap.css" rel="stylesheet" />
	 <!-- 
	 <link href="${rc.getContextPath()}/static/jquery-ui-bootstrap/assets/css/bootstrap.min.css" rel="stylesheet">
	
    zTree树控件 -->
    <script src="${rc.getContextPath()}/static/jquery-ui-bootstrap/assets/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${rc.getContextPath()}/static/jquery.zTree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script src="${rc.getContextPath()}/static/jquery-ui-bootstrap/js/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
	<script src="${rc.getContextPath()}/static/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
	<script src="${rc.getContextPath()}/static/timepicker/jquery.ui.datepicker-zh-CN.js"	type="text/javascript"></script> 
	<script src="${rc.getContextPath()}/static/jquery-form/jquery.form.js" type="text/javascript"></script>
	<script src="${rc.getContextPath()}/static/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"> </script>
     <script type="text/javascript">
		var ctx = '${rc.getContextPath()}';
		$.jgrid.no_legacy_api = true;
		$.jgrid.useJSON = true;
	 </script>
	 <script src="${rc.getContextPath()}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="${rc.getContextPath()}/static/jqgrid/jqGrid.extend.js" type="text/javascript"></script>
    
   <style>
     .pos{width:10px;height:19px;background:#ccc;border-bottom:solid 0px black;}
     #div_pos{position:fixed;}
     dl{width:98%;border-bottom:solid 0px #ccc;height:100%}
     
     input[type="text"], input[type="password"], .ui-autocomplete-input, textarea, .uneditable-input{
	    border-radius: 3px;
	    color: #808080;
	    display: inline-block;
	    font-size: 13px;
	    line-height: 15px;
	    padding: 4px;
	}
	.ui-jqdialog{
		z-index: 1004 !important;
	}
	
	.btn-success {
    background-color: #5BB75B;
    background-image: linear-gradient(to bottom, #62C462, #51A351);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
    color: #FFFFFF;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
   }
   
	 .btn {
	    background: none repeat scroll 0 0 #009E86;
	}
	
	select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input {
	    margin-bottom: 10px;
	    vertical-align: middle;
	}
	
	.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button { font-family: "微软雅黑"; font-size: 1em; }
	
	.toplink{color:#FFF; font-size:17px; cursor:pointer;  float:right; margin-right:20px}


	.ui-jqgrid .ui-jqgrid-view{
		font-size:14px;
	}
	
		body,html{  
            margin:0;  
           padding: 0;  
            height: 100%;  
        } 


    </style>
    <script type="text/javascript">
    var divPos,dtPos=[],curPos;
    window.onload=function(){
        divPos=document.getElementById("treeDemo").getElementsByTagName("li");
        var dts=document.getElementsByTagName("dt");
        for(var i=0;i<dts.length;i++){
            dtPos.push(offsetTop(dts[i]));
        }
        if(Math.max(document.body.scrollTop,document.documentElement.scrollTop)==0)
            setPosition(); 
    }
    window.onscroll=function(){
        setPosition();
    }
    function setPosition(){
        var pos=Math.max(document.body.scrollTop,document.documentElement.scrollTop)+100;
        var temp=curPos; 
        if(pos>=dtPos[dtPos.length-1]){
            curPos=divPos[divPos.length-1];
        }else{
            for(var i=0;i<dtPos.length-1;i++){
                if((pos>=dtPos[i] && pos<dtPos[i+1])){
                    curPos=divPos[i];
                    break;
                }
               }
        }
           if(temp!=curPos){
               if(temp)temp.style.backgroundColor="#fff";
               //curPos.style.backgroundColor="#fff";
               $("#treeDemo a").attr('class','level2')
               var treeDemoid=$(curPos).attr('id');
               var treemid= $("#"+treeDemoid+" a").attr('id');
	           $("#"+treemid).attr('class','level2 curSelectedNode')
              
        }
    }
    function offsetTop(elements){ 
        var top = elements.offsetTop; 
        var parent = elements.offsetParent; 
        while( parent != null ){ 
            top += parent.offsetTop; 
            parent = parent.offsetParent; 
        }; 
        return top; 
    }
    
    var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
				
				}
			},
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick:zTreeOnCheck
			},
			view : {
				dblClickExpand : false,
			    showIcon: true
			}
		};

		function zTreeOnCheck(event, treeId, treeNode) {
			  $("#treeDemo a").attr('class','level2')
		   document.getElementById(treeNode.id).scrollIntoView();
		};

		var zNodes =[
			   <#if list?exists>
			     <#list list as item>
			     <#if !item_has_next>
			    	<#if item.icon?exists>
			    	 { id:'${item.titileid}', pId: '${item.parentId}' , name:'${item.name}',icon:'${item.icon}' ,open:true}
			    	<#else>
			    	 { id:'${item.titileid}', pId: '${item.parentId}' , name:'${item.name}' ,open:true}
		
			    	</#if>
			    
			     <#else>
				     <#if item.icon?exists>
				     
				      { id:'${item.titileid}', pId: '${item.parentId}' , name:'${item.name}',icon:'${item.icon}',open:true},
			     
				     <#else>
				      { id:'${item.titileid}', pId: '${item.parentId}' , name:'${item.name}' ,open:true},
			     
				     </#if>
			     
			     </#if>
			     
			  </#list>
	       </#if>
		
		];
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			 var wh=window.screen.width-405;
			 var tdwh=wh/2
		     $("#id1").attr('width',tdwh);
			
		 	$("#id7").attr('width',tdwh+350);
		 	
		 	
		 	
		 	
		 	var scrollw= document.body.offsetWidth;
		 	var scrollvh= document.body.offsetHeight;
		
		 	$("#divzetree").height(scrollvh+900);
		 	
		 	
		 	
		 
		 	
		 	<#if list?exists>
			  <#list list as item>
				  var w=window.screen.width;
			 	$("#table2${item.titileid}").width(w*0.78-70);
			 </#list>
		  </#if>
		 	
	 	 <#if list?exists>
		  <#list list as item>
	 	
		 	var id='${item.id}';
	    	var remark=$("#text${item.id}").val();
	    
	   
		
		
		
		var  x=$("#remarktitle${item.id}").html();

		 $.ajax({
			type: 'POST',
			data:{id:id,remark:x},
			url: '${rc.getContextPath()}/safetyplan/editortobr',
			success: function(data) {
		
		      $("#remarktitle${item.id}").html(data.msg);
       
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		});
		
				 	
		  </#list>
		  </#if>
		 	
		 
					   
		 	
		 <#if list?exists>
			  <#list list as item>
			   <#if item.titlename?exists>
			     <#if item.titlename=="risk_safetyplan_addendumone">
				     $("#jqGridTable${item.id}").jqGrid({        
					   	url:'${rc.getContextPath()}/addendumOne/search?idtitle=${item.id}',
						datatype: "json",	
						mtype:'POST',
					    	colNames:['id','事故类别','计划实施时间','责任单位','责任人','配合单位','责任人','风险因素','部门','高危时段','重点部位','管控措施内容'],
					    	colModel:[
					   	        {name: "id",index:'id',width:2,hidden:true,sortable:false,search:false},
						   		{name: "accidentstype",index:'accidentstype',editable:true,editoptions:{maxlength:"100"}, width:135,editrules:{required:true},formoptions:{ rowpos:1,colpos:1,elmsuffix:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img>"}},
						   		{name:"datebegin",index:"datebegin",width:200,editable:true,formatter:"date",formatoptions: {srcformat:'Y-m-d',newformat:'Y-m-d'},searchoptions:{dataInit:function (elem) {$(elem).datepicker();}},editoptions:{dataInit:function (elem) {$(elem).datepicker({dateFormat : 'yy-mm-dd',showButtonPanel : true,changeYear:true,changeMonth:true,minDate:new Date()});}},formoptions:{ rowpos:1,colpos:2}},
							   	{name: "iddept",index:'iddept',editable:true,editoptions:{maxlength:"100"},editrules:{required:false},width:150,formoptions:{ rowpos:2,colpos:1}},
							   	{name: "iduser",index:'iduser',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:120,formoptions:{ rowpos:2,colpos:2}},
							   	{name: "idcdndept",index:'idcdndept',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:140,formoptions:{ rowpos:3,colpos:1}},
							   	{name: "idcdnuser",index:'idcdnuser',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:120,formoptions:{ rowpos:3,colpos:2}},
							   	{name: "riskfactor",index:'riskfactor',editable:true, editrules:{required:false},editoptions:{maxlength:"100"}, width:180,formoptions:{ rowpos:4,colpos:1}},
						   		
						   	    {name:"inputdeptname",index:"inputdeptname",sorttype:"string",width:180,editable:true,edittype:'select',align:"center",editoptions:{maxlength:"50",dataUrl:'${rc.getContextPath()}/zd/sysdicitem/type3/StdOrganization'},formoptions:{ rowpos:4,colpos:2} },
			
						   		{name: "riskperiod",index:'riskperiod',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:180},
						   		{name: "keyparts",index:'keyparts',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:180},
						   		{name: "controlmss",index:'controlmss',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:215}
					   	],
					  
					   	pager: '#pager13${item.id}',
					    viewrecords: true,
					    rowNum:5,
			   	        rowList:[5,10,20,30],
					    shrinkToFit:true,
					    rownumbers:true,
					    forceFit:true,
					    autoScroll: false,  
						editurl: "${rc.getContextPath()}/addendumOne/edit?idtitle=${item.id}&htmlType=1",
						autowidth:true,
						beforeShowForm: function () {
						
						}
						
					});
					
			       var default_options = {add:true,edit:false,del:false};
					
					var default_editoptions = {
							reloadAfterSubmit:true,
							recreateForm:true,
							closeAfterEdit:true,
					         closeAfterAdd:true,
							width:800,
							viewPagerButtons:false,
							beforeShowForm: function ($form) {
							$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							
					         $("#datebegin").attr("readonly","true");
							
							},
							afterShowForm:function($form){
								$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},	
							bottominfo:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img> 必须填写",
							afterSubmit : function(response, postdata)
						   	{
						   	
						   
						   		var res =$.parseJSON(response.responseText);
						   		return [res.success,res.message,res.userdata.id];
						   	}
					};
					var default_addoptions = $.extend({},default_editoptions);
					
					jQuery("#jqGridTable${item.id}").jqGrid('navGrid',"#pager13${item.id}",
							default_options, //options
							default_editoptions,// edit options		
							default_addoptions, // add options
							{
								reloadAfterSubmit:true,
								url:"${rc.getContextPath()}/addendumOne/delete"
							}, // del options
							{}, // search options
							{navkeys: [true,38,40], height:250,jqModal:false,closeOnEscape:true} // view options
							);
							
							 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22',
						 		caption:"复制",
						 		title:'复制所选记录',
						 		buttonicon : "ui-icon-key",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
				 						 if(id){
				 						 		//复制内容
				 						 		 $.ajax({
														type: 'POST',
														data:{id:id},
														url: '${rc.getContextPath()}/addendumOne/replication',
														success: function(data) {
															var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
															if(data.msg=='y'){
																
						                                        $.jgrid.info_dialog('注意','复制成功！','',{left:positions[0],top:positions[1]});
																//刷新页面
															 jQuery("#jqGridTable${item.id}").jqGrid('setGridParam',{url:"${rc.getContextPath()}/addendumOne/search?idtitle=${item.id}",page:1}).trigger("reloadGrid"); 
		
															}else {
																 $.jgrid.info_dialog('注意','复制失败！','',{left:positions[0],top:positions[1]});
															}
														},
														error: function(XMLHttpRequest, textStatus, errorThrown) {
															
														}
													});
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						   
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22w',
						 		caption:"编辑",
						 		title:'编辑所选记录',
						 		buttonicon : "ui-icon-pencil",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 		 var vids = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selarrrow');
												 if(vids.length>1){
													
														$.jgrid.info_dialog('注意','请选择一条记录','',{left:positions[0],top:positions[1]});
												 }else{
												 var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													 if( gr != null ) {
														
														 var positionsx = $.jgrid.htmlPoaitions(800,500,'${item.titileid}');
														jQuery("#jqGridTable${item.id}").jqGrid('editGridRow',gr,{
															reloadAfterSubmit:false,
															left:positionsx[0],
															top:positionsx[1],
															closeAfterEdit:true,
														
															width:800, 
															beforeShowForm: function () {
													        },
															beforeSubmit:function(postdata, formid){  
									 
																return[true,''];// 提交
															},
															afterSubmit : function(response, postdata)
														   	{
															
																
																$.jgrid.info_dialog('注意','修改成功!','',{left:positions[0],top:positions[1]});
														   		var res =$.parseJSON(response.responseText);
														   		return [res.success,res.message,res.userdata.id];
														   	} 
														});
													 }else {
														
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													};
												 }
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22wd',
						 		caption:"删除",
						 		title:'删除所选记录',
						 		buttonicon : "ui-icon-trash",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 			var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													if( gr != null && gr!="" ) {
														jQuery("#jqGridTable${item.id}").jqGrid('delGridRow',gr,{url:"${rc.getContextPath()}/addendumOne/delete",reloadAfterSubmit:false});
													}else {
											
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													}
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						     $("#search_jqGridTable${item.id}").hide();
					<#elseif item.titlename=="risk_safetyplan_addendumtwo">
					$("#jqGridTable${item.id}").jqGrid({        
					   	url:'${rc.getContextPath()}/addendumTwo/search?idtitle=${item.id}',
						datatype: "json",	
					   	 	colNames:['id','事故类别','计划实施时间','责任单位','责任人','配合单位','责任人','风险因素','部门','高危时段','重点部位','管控措施内容'],
					    	colModel:[
					   	        {name: "id",index:'id',width:2,hidden:true,sortable:false,search:false},
						   		{name: "accidentstype",index:'accidentstype',editable:true,editoptions:{maxlength:"100"}, width:135,editrules:{required:true},formoptions:{ rowpos:1,colpos:1,elmsuffix:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img>"}},
						   		{name:"datebegin",index:"datebegin",width:200,editable:true,formatter:"date",formatoptions: {srcformat:'Y-m-d',newformat:'Y-m-d'},searchoptions:{dataInit:function (elem) {$(elem).datepicker();}},editoptions:{dataInit:function (elem) {$(elem).datepicker({dateFormat : 'yy-mm-dd',showButtonPanel : true,changeYear:true,changeMonth:true,minDate:new Date()});}},formoptions:{ rowpos:1,colpos:2}},
							   	{name: "iddept",index:'iddept',editable:true,editoptions:{maxlength:"100"},editrules:{required:false},width:150,formoptions:{ rowpos:2,colpos:1}},
							   	{name: "iduser",index:'iduser',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:120,formoptions:{ rowpos:2,colpos:2}},
							   	{name: "idcdndept",index:'idcdndept',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:140,formoptions:{ rowpos:3,colpos:1}},
							   	{name: "idcdnuser",index:'idcdnuser',editable:true,editoptions:{maxlength:"100"},editrules:{required:false}, width:120,formoptions:{ rowpos:3,colpos:2}},
							   	{name: "riskfactor",index:'riskfactor',editable:true, editrules:{required:false},editoptions:{maxlength:"100"}, width:180,formoptions:{ rowpos:4,colpos:1}},
						   		
						   	    {name:"inputdeptname",index:"inputdeptname",sorttype:"string",width:180,editable:true,edittype:'select',align:"center",editoptions:{maxlength:"50",dataUrl:'${rc.getContextPath()}/zd/sysdicitem/type3/StdOrganization'},formoptions:{ rowpos:4,colpos:2} },
			
						   		{name: "riskperiod",index:'riskperiod',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:180},
						   		{name: "keyparts",index:'keyparts',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:180},
						   		{name: "controlmss",index:'controlmss',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:215}
					   	],
					  
					   	pager: '#pager13${item.id}',
					    viewrecords: true,
					    rowNum:5,
			   	        rowList:[5,10,20,30],
					    shrinkToFit:true,
					    forceFit:true,
					    rownumbers:true,  
						editurl: "${rc.getContextPath()}/addendumTwo/edit?idtitle=${item.id}&htmlType=1",
						autowidth:true
						//hidegrid:false//设置grid的隐藏/显示按钮是否可用
						//shrinkToFit: false,
						//caption: "用户列表"	
					});
					
			       var default_options = {add:true,edit:false,del:false};
					
					var default_editoptions = {
							reloadAfterSubmit:true,
							recreateForm:true,
							closeAfterEdit:true,
					         closeAfterAdd:true,
							width:800,
							viewPagerButtons:false,
							beforeShowForm: function ($form) {
							$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							
							 $("#datebegin").attr("readonly","true");
								        
							},
							afterShowForm:function($form){
								$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},	
							bottominfo:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img> 必须填写",
							afterSubmit : function(response, postdata)
						   	{
						   		var res =$.parseJSON(response.responseText);
						   		return [res.success,res.message,res.userdata.id];
						   	}
					};
					var default_addoptions = $.extend({},default_editoptions);
					
					jQuery("#jqGridTable${item.id}").jqGrid('navGrid',"#pager13${item.id}",
							default_options, //options
							default_editoptions,// edit options		
							default_addoptions, // add options
							{
								reloadAfterSubmit:true,
								url:"${rc.getContextPath()}/addendumTwo/delete"
							}, // del options
							{}, // search options
							{navkeys: [true,38,40], height:250,jqModal:false,closeOnEscape:true} // view options
							);
							 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22',
						 		caption:"复制",
						 		title:'复制所选记录',
						 		buttonicon : "ui-icon-key",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
				 						 if(id){
				 						 		//复制内容
				 						 		 $.ajax({
														type: 'POST',
														data:{id:id},
														url: '${rc.getContextPath()}/addendumTwo/replication',
														success: function(data) {
														var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
															if(data.msg=='y'){
						                                        $.jgrid.info_dialog('注意','复制成功！','',{left:positions[0],top:positions[1]});
																//刷新页面
															 jQuery("#jqGridTable${item.id}").jqGrid('setGridParam',{url:"${rc.getContextPath()}/addendumTwo/search?idtitle=${item.id}",page:1}).trigger("reloadGrid"); 
		
															}else {
															
																 $.jgrid.info_dialog('注意','复制失败！','',{left:positions[0],top:positions[1]});
															}
														},
														error: function(XMLHttpRequest, textStatus, errorThrown) {
															
														}
													});
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22w',
						 		caption:"编辑",
						 		title:'编辑所选记录',
						 		buttonicon : "ui-icon-pencil",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 		 var vids = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selarrrow');
												 if(vids.length>1){
													
														$.jgrid.info_dialog('注意','请选择一条记录','',{left:positions[0],top:positions[1]});
												 }else{
												 var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													 if( gr != null ) {
														
														 var positionsx = $.jgrid.htmlPoaitions(800,500,'${item.titileid}');
														jQuery("#jqGridTable${item.id}").jqGrid('editGridRow',gr,{
															reloadAfterSubmit:false,
															left:positionsx[0],
															top:positionsx[1],
															closeAfterEdit:true,
															width:800, 
															beforeShowForm: function () {
													        },
															beforeSubmit:function(postdata, formid){  
									 
																return[true,''];// 提交
															},
															afterSubmit : function(response, postdata)
														   	{
															
																$.jgrid.info_dialog('注意','修改成功!','',{left:positions[0],top:positions[1]});
														   		var res =$.parseJSON(response.responseText);
														   		return [res.success,res.message,res.userdata.id];
														   	} 
														});
													 }else {
														
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													};
												 }
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22wd',
						 		caption:"删除",
						 		title:'删除所选记录',
						 		buttonicon : "ui-icon-trash",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 			var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													if( gr != null && gr!="" ) {
														jQuery("#jqGridTable${item.id}").jqGrid('delGridRow',gr,{url:"${rc.getContextPath()}/addendumTwo/delete",reloadAfterSubmit:false});
													}else {
											
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													}
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						  $("#search_jqGridTable${item.id}").hide();
					<#elseif item.titlename=="risk_safetyplan_addendumthree">
					$("#jqGridTable${item.id}").jqGrid({        
					   	url:'${rc.getContextPath()}/addendumThree/search?idtitle=${item.id}',
						datatype: "json",	
					   	 	colNames:['id','名称','类型','时间及工期','实施责任单位','配合单位','内容及工作量'],
						   	colModel:[
						   	    {name: "id",index:'id',width:0,hidden:true,sortable:false,search:false},
						   		{name: "riskname",index:'riskname',editoptions:{maxlength:"100"}, editable:true,width:100,editrules:{required:true},formoptions:{ rowpos:1,colpos:1,elmsuffix:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img>"}},
						   		{name: "idtype",index:'idtype',editoptions:{maxlength:"20"},editable:true,width:100,formoptions:{ rowpos:1,colpos:2},editrules:{required:false}},
						   		{name: "time",index:'time',editable:true,width:100,editoptions:{maxlength:"100"},formoptions:{ rowpos:2,colpos:1},editrules:{required:false}},
						   		{name: "idimpdept",index:'idimpdept',editoptions:{maxlength:"200"},editable:true,width:100,formoptions:{ rowpos:2,colpos:2},editrules:{required:false}},
						   		{name: "idcdndept",index:'idcdndept',editoptions:{maxlength:"200"},editable:true,width:100,formoptions:{ rowpos:4,colpos:1}},

						   		{name: "content",index:'content',editable:true, edittype:'textarea',editrules:{required:false},editoptions:{maxlength:"1000",rows:"2",cols:"50"}, width:215}
		
						   	],
					  
					   	pager: '#pager13${item.id}',
					    viewrecords: true,
					    rowNum:5,
			   	        rowList:[5,10,20,30],
			   	        forceFit:true,
					     shrinkToFit:true,
					    rownumbers:true,  
						editurl: "${rc.getContextPath()}/addendumThree/edit?idtitle=${item.id}&htmlType=1",
						autowidth:true
						//hidegrid:false//设置grid的隐藏/显示按钮是否可用
						//shrinkToFit: false,
						//caption: "用户列表"	
					});
					
			       var default_options = {add:true,edit:false,del:false};
					
					var default_editoptions = {
							reloadAfterSubmit:true,
							recreateForm:true,
							closeAfterEdit:true,
					         closeAfterAdd:true,
							width:800,
							viewPagerButtons:false,
							beforeShowForm: function ($form) {
							$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
						        
							},
							afterShowForm:function($form){
								$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},	
							bottominfo:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img> 必须填写",
							afterSubmit : function(response, postdata)
						   	{
						   		var res =$.parseJSON(response.responseText);
						   		return [res.success,res.message,res.userdata.id];
						   	}
					};
					var default_addoptions = $.extend({},default_editoptions);
					
					jQuery("#jqGridTable${item.id}").jqGrid('navGrid',"#pager13${item.id}",
							default_options, //options
							default_editoptions,// edit options		
							default_addoptions, // add options
							{
								reloadAfterSubmit:true,
								url:"${rc.getContextPath()}/addendumThree/delete"
							}, // del options
							{}, // search options
							{navkeys: [true,38,40], height:250,jqModal:false,closeOnEscape:true} // view options
							);
							 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22',
						 		caption:"复制",
						 		title:'复制所选记录',
						 		buttonicon : "ui-icon-key",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
				 						 if(id){
				 						 		//复制内容
				 						 		 $.ajax({
														type: 'POST',
														data:{id:id},
														url: '${rc.getContextPath()}/addendumThree/replication',
														success: function(data) {
															var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
															if(data.msg=='y'){
						                                        $.jgrid.info_dialog('注意','复制成功！','',{left:positions[0],top:positions[1]});
																//刷新页面
															 jQuery("#jqGridTable${item.id}").jqGrid('setGridParam',{url:"${rc.getContextPath()}/addendumThree/search?idtitle=${item.id}",page:1}).trigger("reloadGrid"); 
		
															}else {
																
																   $.jgrid.info_dialog('注意','复制失败！','',{left:positions[0],top:positions[1]});
															}
														},
														error: function(XMLHttpRequest, textStatus, errorThrown) {
															
														}
													});
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						   
						   jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22w',
						 		caption:"编辑",
						 		title:'编辑所选记录',
						 		buttonicon : "ui-icon-pencil",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 		 var vids = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selarrrow');
												 if(vids.length>1){
													
														$.jgrid.info_dialog('注意','请选择一条记录','',{left:positions[0],top:positions[1]});
												 }else{
												 var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													 if( gr != null ) {
														
														 var positionsx = $.jgrid.htmlPoaitions(800,500,'${item.titileid}');
														jQuery("#jqGridTable${item.id}").jqGrid('editGridRow',gr,{
															reloadAfterSubmit:false,
															left:positionsx[0],
															top:positionsx[1],
															closeAfterEdit:true,
															width:800, 
															beforeShowForm: function () {
													        },
															beforeSubmit:function(postdata, formid){  
									 
																return[true,''];// 提交
															},
															afterSubmit : function(response, postdata)
														   	{
															
																$.jgrid.info_dialog('注意','修改成功!','',{left:positions[0],top:positions[1]});
														   		var res =$.parseJSON(response.responseText);
														   		return [res.success,res.message,res.userdata.id];
														   	} 
														});
													 }else {
														
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													};
												 }
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22wd',
						 		caption:"删除",
						 		title:'删除所选记录',
						 		buttonicon : "ui-icon-trash",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 			var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													if( gr != null && gr!="" ) {
														jQuery("#jqGridTable${item.id}").jqGrid('delGridRow',gr,{url:"${rc.getContextPath()}/addendumThree/delete",reloadAfterSubmit:false});
													}else {
											
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													}
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
							 $("#search_jqGridTable${item.id}").hide();
					<#elseif item.titlename=="risk_safetyplan_addendumfour">
					$("#jqGridTable${item.id}").jqGrid({        
					   	url:'${rc.getContextPath()}/addendumFour/search?idtitle=${item.id}',
						datatype: "json",	
					   		colNames:['id','考评项目','标准分','考评分','版本','考评内容','考评方法及评分标准'],
						   	colModel:[
						   	     {name: "id",index:'id',width:0,hidden:true,sortable:false,search:false},
						   		 {name: "riskname",index:'riskname',editable:true,editoptions:{maxlength:"100"}, width:150,formoptions:{ rowpos:1,colpos:1,elmsuffix:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img>"} ,editrules:{required:true}},
						   		 {name: "standardscore",index:'standardscore',editable:true,editoptions:{maxlength:"20"},width:150,formoptions:{ rowpos:1,colpos:2},editrules:{required:false}},
						   		 {name: "finalscore",index:'finalscore',editable:true,width:150,editoptions:{maxlength:"20"},formoptions:{ rowpos:2,colpos:1},editrules:{required:false}},
						   		 {name: "version",index:'version',editable:true,editoptions:{maxlength:"20"}, width:150,formoptions:{ rowpos:2,colpos:2},editrules:{required:false}},
						   		 {name: "controlmss",index:'controlmss',edittype:'textarea',editoptions:{maxlength:"1000",rows:"2",cols:"50"}, editable:true,width:396},
						   		 {name: "criterion",index:'criterion',edittype:'textarea',editoptions:{maxlength:"1000",rows:"2",cols:"50"}, editable:true,width:396}
						   			   		 
						   	],
					  
					   	pager: '#pager13${item.id}',
					    viewrecords: true,
					    rowNum:5,
					    forceFit:true,
			   	        rowList:[5,10,20,30],
					    shrinkToFit:true,
					    rownumbers:true,  
						editurl: "${rc.getContextPath()}/addendumFour/edit?idtitle=${item.id}&htmlType=1",
						autowidth:true
						//hidegrid:false//设置grid的隐藏/显示按钮是否可用
						//shrinkToFit: false,
						//caption: "用户列表"	
					});
					
			       var default_options = {add:true,edit:false,del:false};
					
					var default_editoptions = {
							reloadAfterSubmit:true,
							recreateForm:true,
							closeAfterEdit:true,
					         closeAfterAdd:true,
							width:800,
							viewPagerButtons:false,
							beforeShowForm: function ($form) {
							$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},	
						afterShowForm:function($form){
								$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},
							bottominfo:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img> 必须填写",
							afterSubmit : function(response, postdata)
						   	{
						   		var res =$.parseJSON(response.responseText);
						   		return [res.success,res.message,res.userdata.id];
						   	}
					};
					var default_addoptions = $.extend({},default_editoptions);
					
					jQuery("#jqGridTable${item.id}").jqGrid('navGrid',"#pager13${item.id}",
							default_options, //options
							default_editoptions,// edit options		
							default_addoptions, // add options
							{
								reloadAfterSubmit:true,
								url:"${rc.getContextPath()}/addendumFour/delete"
							}, // del options
							{}, // search options
							{navkeys: [true,38,40], height:250,jqModal:false,closeOnEscape:true} // view options
							);
							 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22',
						 		caption:"复制",
						 		title:'复制所选记录',
						 		buttonicon : "ui-icon-key",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
				 						 if(id){
				 						 		//复制内容
				 						 		 $.ajax({
														type: 'POST',
														data:{id:id},
														url: '${rc.getContextPath()}/addendumFour/replication',
														success: function(data) {
																var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
															if(data.msg=='y'){
						                                        $.jgrid.info_dialog('注意','复制成功！','',{left:positions[0],top:positions[1]});
																//刷新页面
															 jQuery("#jqGridTable${item.id}").jqGrid('setGridParam',{url:"${rc.getContextPath()}/addendumFour/search?idtitle=${item.id}",page:1}).trigger("reloadGrid"); 
		
															}else {
																
																 $.jgrid.info_dialog('注意','复制失败！','',{left:positions[0],top:positions[1]});
															}
														},
														error: function(XMLHttpRequest, textStatus, errorThrown) {
															
														}
													});
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						   
						   
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22w',
						 		caption:"编辑",
						 		title:'编辑所选记录',
						 		buttonicon : "ui-icon-pencil",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 		 var vids = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selarrrow');
												 if(vids.length>1){
													
														$.jgrid.info_dialog('注意','请选择一条记录','',{left:positions[0],top:positions[1]});
												 }else{
												 var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													 if( gr != null ) {
														
														 var positionsx = $.jgrid.htmlPoaitions(800,500,'${item.titileid}');
														jQuery("#jqGridTable${item.id}").jqGrid('editGridRow',gr,{
															reloadAfterSubmit:false,
															left:positionsx[0],
															top:positionsx[1],
															closeAfterEdit:true,
															width:800, 
															beforeShowForm: function () {
													        },
															beforeSubmit:function(postdata, formid){  
									 
																return[true,''];// 提交
															},
															afterSubmit : function(response, postdata)
														   	{
															
																$.jgrid.info_dialog('注意','修改成功!','',{left:positions[0],top:positions[1]});
														   		var res =$.parseJSON(response.responseText);
														   		return [res.success,res.message,res.userdata.id];
														   	} 
														});
													 }else {
														
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													};
												 }
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22wd',
						 		caption:"删除",
						 		title:'删除所选记录',
						 		buttonicon : "ui-icon-trash",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 			var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													if( gr != null && gr!="" ) {
														jQuery("#jqGridTable${item.id}").jqGrid('delGridRow',gr,{url:"${rc.getContextPath()}/addendumFour/delete",reloadAfterSubmit:false});
													}else {
											
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													}
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						   $("#search_jqGridTable${item.id}").hide();  
					<#elseif item.titlename=="risk_safetyplan_addendumfive">
					
					$("#jqGridTable${item.id}").jqGrid({        
					   	url:'${rc.getContextPath()}/addendumFive/search?idtitle=${item.id}',
						datatype: "json",	
					    
						   colNames:['id','问题和建议','整改情况'],
						   	colModel:[
						   	    {name: "id",index:'id',width:4,hidden:true,sortable:false,search:false},
						   		 {name: "question",index:'question',editable:true,editoptions:{maxlength:"100"}, width:100,formoptions:{ rowpos:1,colpos:1,elmsuffix:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img>"} ,editrules:{required:true}},

						   		{name: "controlmss",index:'controlmss',edittype:'textarea',editable:true,editoptions:{maxlength:"1000",rows:"2",cols:"50"},width:100},
						   		         
						   	],
					  
					   	pager: '#pager13${item.id}',
					    viewrecords: true,
					    rowNum:5,
					    forceFit:true,
			   	        rowList:[5,10,20,30],
					    shrinkToFit:true,
					    rownumbers:true,  
						editurl: "${rc.getContextPath()}/addendumFive/edit?idtitle=${item.id}&htmlType=1",
						autowidth:true
						//hidegrid:false//设置grid的隐藏/显示按钮是否可用
						//shrinkToFit: false,
						//caption: "用户列表"	
					});
					
			       var default_options = {add:true,edit:false,del:false};
					
					var default_editoptions = {
							reloadAfterSubmit:true,
							recreateForm:true,
							closeAfterEdit:true,
					         closeAfterAdd:true,
							width:450,
							viewPagerButtons:false,
					        afterShowForm:function($form){
					       
								$form.closest(".ui-jqdialog").position({
									my:'center', at:'center', of:window, collision: 'fit'
						        });
							},
							bottominfo:"<img  src='${rc.getContextPath()}/static/css/img/required.png'  class='requiredtag'></img> 必须填写",
							afterSubmit : function(response, postdata)
						   	{
						   		var res =$.parseJSON(response.responseText);
						   		return [res.success,res.message,res.userdata.id];
						   	}
					};
					var default_addoptions = $.extend({},default_editoptions);
					
					jQuery("#jqGridTable${item.id}").jqGrid('navGrid',"#pager13${item.id}",
							default_options, //options
							default_editoptions,// edit options		
							default_addoptions, // add options
							{
								reloadAfterSubmit:true,
								url:"${rc.getContextPath()}/addendumFive/delete"
							}, // del options
							{}, // search options
							{navkeys: [true,38,40], height:250,jqModal:false,closeOnEscape:true} // view options
							);
							
							 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22',
						 		caption:"复制",
						 		title:'复制所选记录', 
						 		buttonicon : "ui-icon-key",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
				 						 if(id){
				 						 		//复制内容
				 						 		 $.ajax({
														type: 'POST',
														data:{id:id},
														url: '${rc.getContextPath()}/addendumFive/replication',
														success: function(data) {
															var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
															if(data.msg=='y'){
						                                        $.jgrid.info_dialog('注意','复制成功！','',{left:positions[0],top:positions[1]});
																//刷新页面
															 jQuery("#jqGridTable${item.id}").jqGrid('setGridParam',{url:"${rc.getContextPath()}/addendumFive/search?idtitle=${item.id}",page:1}).trigger("reloadGrid"); 
		
															}else {
																
														       $.jgrid.info_dialog('注意','复制失败！','',{left:positions[0],top:positions[1]});
															}
														},
														error: function(XMLHttpRequest, textStatus, errorThrown) {
															
														}
													});
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						
						 jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22w',
						 		caption:"编辑",
						 		title:'编辑所选记录',
						 		buttonicon : "ui-icon-pencil",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 		 var vids = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selarrrow');
												 if(vids.length>1){
													
														$.jgrid.info_dialog('注意','请选择一条记录','',{left:positions[0],top:positions[1]});
												 }else{
												 var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													 if( gr != null ) {
														
														 var positionsx = $.jgrid.htmlPoaitions(800,500,'${item.titileid}');
														jQuery("#jqGridTable${item.id}").jqGrid('editGridRow',gr,{
															reloadAfterSubmit:false,
															left:positionsx[0],
															top:positionsx[1],
															closeAfterEdit:true,
															width:800, 
															beforeShowForm: function () {
													        },
															beforeSubmit:function(postdata, formid){  
									 
																return[true,''];// 提交
															},
															afterSubmit : function(response, postdata)
														   	{
															
																$.jgrid.info_dialog('注意','修改成功!','',{left:positions[0],top:positions[1]});
														   		var res =$.parseJSON(response.responseText);
														   		return [res.success,res.message,res.userdata.id];
														   	} 
														});
													 }else {
														
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													};
												 }
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						    
						    
						     jQuery("#jqGridTable${item.id}").jqGrid('navButtonAdd','#pager13${item.id}',{
						 		id:'aa_exceptable22wd',
						 		caption:"删除",
						 		title:'删除所选记录',
						 		buttonicon : "ui-icon-trash",
						 		position : "last",
						 		onClickButton:function(){	
						 				 var id = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
						 				 
						 				 var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
						 				 
				 						 if(id){
				 						 			var gr = jQuery("#jqGridTable${item.id}").jqGrid('getGridParam','selrow');
													if( gr != null && gr!="" ) {
														jQuery("#jqGridTable${item.id}").jqGrid('delGridRow',gr,{url:"${rc.getContextPath()}/addendumFive/delete",reloadAfterSubmit:false});
													}else {
											
														$.jgrid.info_dialog('注意','请选择记录','',{left:positions[0],top:positions[1]});
													}
											 						 	
				 						  }else{
											var positions = $.jgrid.htmlPoaitions(290,50,'${item.titileid}');
											$.jgrid.info_dialog2('注意',"请选择记录",{left:positions[0],top:positions[1]});
										 }
						 		  } 
						    });
						     $("#search_jqGridTable${item.id}").hide();
			       </#if>
			       
			      </#if>
			      $("#content${item.id}").on('click', function(){
				        $.jgrid.form_dialog_forOnBeforeClose("content${item.id}",
	          			{	
	                	title:'查看标题原内容',
	                	width:620,
	      				height:400
	      				},{
	          				url: "${rc.getContextPath()}/historycontent?idtitle=${item.id}"
	          			});	
          	         });
          			
			  </#list>
	       </#if>
		 	
		 
		});
		 function beforeClickTree (treeId, treeNode) {
			 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			 var id = zTree.getSelectedNodes()[0].id;
		
		 }
		 
		 var  pagetype=1;
		function edittitle(id){
			if(pagetype==1){
			    pagetype=2;
			   $("#text"+id).show();
			   $("#zhanshidiv"+id).hide();
			   $("#save"+id).show();
			   $("#quxiao"+id).show();
			   $("#content"+id).show();
			   $("#text"+id).attr('style','resize: none;width:100%;height:150px;font-size:15px;font-family:微软雅黑;line-height:inherit;color:333333;')
			   document.getElementById("text"+id).readOnly = false; 
			}else {
	            var positions = $.jgrid.findPositions(290,50);
                $.jgrid.info_dialog('注意','请保存其他的编辑框！','',{left:positions[0],top:positions[1]});
			}
		   
		}
		
		//取消按钮
		function quxiao(id){
		       pagetype=1;
			   $("#save"+id).hide();
			   $("#quxiao"+id).hide();
			  
			   $("#text"+id).attr('style','width:100%;height:150px;font-size:15px;font-family:微软雅黑;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;resize: none;line-height:inherit;color:333333;')
			   document.getElementById("text"+id).readOnly = true; 
		} 
		
	   function contentSave(id){
	   	    var remark=$("#text"+id).val();
	   	    var titlename=$("#titlename"+id).html();
           $.ajax({
				type: 'POST',
				data:{id:id,remark:remark,titlename:titlename},
				url: '${rc.getContextPath()}/safetyplan/contentSave',
				success: function(data) {
					if(data.msg=='y'){
						pagetype=1;
						// var positions = $.jgrid.findPositions(290,50);
						var positions = $.jgrid.htmlPoaitions(290,50,'save'+id+'');
                        $.jgrid.info_dialog('注意','保存成功！','',{left:positions[0],top:positions[1]});
					   $("#save"+id).hide();
					   $("#quxiao"+id).hide();
					 
					    document.getElementById("text"+id).readOnly = true; 
						
					}else {
						
						var positions = $.jgrid.findPositions(290,50);
                        $.jgrid.info_dialog('注意','保存失败！','',{left:positions[0],top:positions[1]});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					
				}
			});
	   
	   } 
	   
	   function queryDialog(id){
			
	   } 
      
      
      function getScrollTop()
		{
		    var scrollTop=0;
		    if(document.documentElement&&document.documentElement.scrollTop)
		    {
		        scrollTop=document.documentElement.scrollTop;
		    }
		    else if(document.body)
		    {
		        scrollTop=document.body.scrollTop;
		    }
		    return scrollTop;
		} 
		
		
    </script>
  </head>
  <body >
  <div  style="padding-top:0px;padding-bottom:0px;padding-bottom:0px;background:#f3f7f8;overflow:hidden;"  id="divid"  >
   <div style="padding-top:0px;padding-bottom:0px;padding-bottom:0px;">
   		<table id="tableid" border="0" style="background:#204f4b;color:#FFFFFF;margin-left: 0px; position: fixed;z-index:2000;" height="50" >
   			<tr>
   			 <#if modellist?exists>
			     <#list modellist as modelitem>
   					<td  width="10"></td><td id="id1"  style="color:#FFF; font-size:17px;">${modelitem.namescheme?if_exists}</td><td ></td><td  ></td><td ></td><td  ></td><td  ></td>
   					 <td id="id7" >
	   					 <div class="toplink" >${(modelitem.dateuseredit?string("yyyy-MM-dd HH:mm:ss"))!}</div><div  class="toplink">录入时间</div>
					   <div class="toplink" >${modelitem.useredit?if_exists}</div> <div  class="toplink">录入人</div>
					   <div class="toplink" >${modelitem.deptname?if_exists}</div> <div  class="toplink">部门</div>
				    </td>
   			  </#list>
	        </#if>
   			</tr>
   		</table>
   </div>
   <div style="padding:50px 0px 0px 0px;">
         <div id="divzetree" style="margin-left:20px; width: 20%;background-color:#ffffff;position:fixed;border:1px solid #d1d4d5;">
		  	<div class="zTreeDemoBackground " style="float:left;width: 20%;position:fixed; ">
		  		<div  style="width: 98%; overflow:hidden;">
		  		<ul id="treeDemo" class="ztree"></ul>
		  		</div>
		   		
		    </div>
		  </div>
	    <div  style="float:right;width: 78%;">
	    	<div style=" float:right;width: 97%;border:#d1d4d5 1px solid;  margin-top:10px; float:left; margin-left:10px; background:#f8f8f8; line-height:23px;">
			   <#if list?exists>
			       <#list list as item>
			       <div >
				         <#if item.titlename?exists>
				        
				          
				              <dl id="${item.titileid}">
				                
				                    <div style="height:35px;" ></div>
					           	    <div style="color:#333333; font-size:18px;  font-weight:bold; text-align:left;">&nbsp;&nbsp;&nbsp;${item.name?if_exists}</div>
					           	     <dt id="remarktitle${item.id}" >${item.remarktitle?if_exists}</dt>
								
									  <div class="row-fluid" style="padding-left:15px">
											<!--jqGridTable开始-->
											<table id="jqGridTable${item.id}">
											</table>
											<div id="pager13${item.id}"></div>
									   </div>
				          
							  </dl>
						 <#else>
						       
								   <dl id="${item.titileid}">
								   <table border="0" id="table2${item.titileid}" >
							        <tr><td style="width:98%;">
							         <#if item_index = 0>
				           				 <div  align="center" style="padding-top:0px;padding-bottom:0px;color:#333333; font-size:20px;  font-weight:bold" ><span id="titlename${item.id}" >${item.name?if_exists}</span></div>
				            	     <#else>
				            	     
					            	    <!-- <div style="height:50px;" ></div> -->
					            	     <div  style="margin-top:0px;padding-top:43px;padding-bottom:0px;color:#333333; font-size:18px;  font-weight:bold; text-align:left;BORDER: 0px solid #00FFFF" >&nbsp;&nbsp;&nbsp;<span id="titlename${item.id}" >${item.name?if_exists}</span></div>
				                     </#if>
								    </td></tr>
								     <tr><td>
						               <dt style="padding-left:40px;" id="remarktitle${item.id}" >${item.remarktitle?if_exists}</dt>
						             </td></tr>
						               <tr><td>
								            <div style="float:right;padding-top:0px;padding-bottom:0px;BORDER: 0px solid red"  >     
												<button id='save${item.id}' style=" display:none;cursor:pointer!important;"  class="btn btn-success btnnew " type="button" onclick="contentSave('${item.id}')" > 保存</button>
												<button id="quxiao${item.id}" style=" display:none;cursor:pointer!important;"  class="btn btn-success btnnew" type="button" onclick="quxiao('${item.id}')" > 取消</button>
												<#if item.alloweditorflag?exists>
												   <#if item.alloweditorflag=="1">
												   	 <button id="content${item.id}"  style="cursor:pointer!important;"  class="btn btn-success btnnew" type="button"> 查看原内容</button>
												   	 <button id="edittitle${item.id}"   class="btn btn-success btnnew" style="cursor:pointer!important;" type="button"  onclick="edittitle('${item.id}')" > 编辑</button>
												   </#if>
											     </#if>
										     </div>
									    </td></tr>  
									    <tr style="padding-top:0px;padding-bottom:0px;BORDER: 0px;"><td style="padding-top:0px;padding-bottom:0px;BORDER: 0px;"> 
										   <#if item.alloweditorflag?exists>
										     <#if item.alloweditorflag=="1">
										  	  <dd style="padding-top:0px;padding-bottom:0px;">
											  	  <textarea id="text${item.id}" cols=""  readonly="true" rows=""  style="display:none;color:333333;line-height:2;width:100%;height:150px;font-family:微软雅黑;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;resize: none; width:100%;font-size:15px;">${item.remark?if_exists}</textarea>
											  	  <div id="zhanshidiv${item.id}"  style="margin-bottom:0px;padding-top:0px;padding-bottom:0px;line-height:2;color:333333;font-size:15px" >${item.remark?if_exists}</div>
										  	  </dd>
										     </#if>
										   </#if>
									     </td></tr>  
									   </table>
						           </dl>
				         </#if>
			         </div>
				  </#list>
			   </#if>
			 </div>
	   </div>
	 </div>
   </div>
  </body>
</html>
  <script>
   jQuery().ready(function (){
	$("#divid").height($("#divid").height()+1000);
   	  
		    
   	});
  
  </script>
