$(function () {
	pageInit();
});

function pageInit(){

	  'use strict';
	  var loadStatus = true;
	  var beginPage = $("#beginPage").val()*1;
	  //最多可加载的条目
	  //var maxItems;
	  //无限滚动
	  $(document).on("pageInit", "#page-infinite-scroll-bottom", function(e, id, page) {
		if(!loadStatus){
			return;	
		}
		
	    var loading = false;
	    // 每次加载添加多少条目
	    var itemsPerLoad = 15;
	    // 最多可加载的条目
	    var maxItems = $("#count").val()*1;
	    var lastIndex = $('.list-container li').length;
	    function addItems(number, lastIndex) {
	      // 生成新条目的HTML
	      var html = '';
	      
	      beginPage +=1;
		  $("#beginPage").val(beginPage);
		  
	  	$.ajax({
			type: 'POST',
			url : basePath+"/wechat/personalcentre/butler/listOver",
			async:false,
			data:$("#myForm").serialize(),
			//  返回数据处理
			success: function(data){
				//$("#butler_list").empty();
				for(var i=0;i<data.butlerTasks.length;i++){
					var bulterStr="";
					bulterStr +=("<li>");
					bulterStr +=("<a href='${ctx}/wechat/personalcentre/butler/check?taskType="+data.butlerTasks[i].type+"&taskId="+data.butlerTasks[i].id+"&curMemberId="+$("#curMemberId").val()+"'>");
					bulterStr +=("<p class='flex'>"+data.butlerTasks[i].memberName+"<br/>"+data.butlerTasks[i].memberMobile+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTasks[i].statusInfo+"<br/>"+data.butlerTasks[i].typeInfo+"</p>");
					bulterStr +=("<p class='flex'>"+data.butlerTasks[i].operaterName+"</p>");
					bulterStr +=("<p class='flex'>"+new Date(data.butlerTasks[i].created).format('MM-dd hh:mm')+"</p>");
					bulterStr +=("</a>");
					bulterStr +=("</li>");
					$('.infinite-scroll .list-container').append(bulterStr);
				}
			}
		});
	      // 添加新条目
	      //$('.infinite-scroll .list-container').append(html);
	    }
	    $(page).on('infinite', function() {
//	    	alert("go");
	      // 如果正在加载，则退出
	      if (loading) return;
	      // 设置flag
	      loading = true;
	      // 模拟1s的加载过程
//	      setTimeout(function() {
//	        // 重置加载flag
//	        loading = false;
	        if (lastIndex >= maxItems) {
	        loadStatus = false;
	          // 加载完毕，则注销无限加载事件，以防不必要的加载
	          $.detachInfiniteScroll($('.infinite-scroll'));
	          // 删除加载提示符
	          $('.infinite-scroll-preloader').remove();
	          return;
	        }
	        addItems(itemsPerLoad,lastIndex);
	        // 更新最后加载的序号
	        lastIndex = $('.list-container li').length;
	        $.refreshScroller();
//	      }, 1000);
	    });
	  });
	  $.init();
}