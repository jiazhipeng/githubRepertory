(function($){
	var _href = window.location.href.split('#')[0];
	if(_href.indexOf('&')!=-1){
		_href = _href.replace(/&/g,'%26');
		window.location = _href;
	}
	$("#submit").show();
	var channel = geturlparam('channel');
	var code = geturlparam('code');
	var state = geturlparam('state');
	//var _url = 'http://192.168.1.207:8080/service/service/customer/';
	//var _url = 'http://1mobility.cn/service/service/customer/';
	
	var oH = document.documentElement.clientHeight;
	
	var oErr = $('.errInfo');//预约错误提示
	var index = {
		init : function(){
			$('#submit').on('click',function(){
				index.toBooking();
			});
			
			
		},
		toBooking : function(){
			var name = $('#name').val(),telNum = $('#tel').val();
			
			
			//用户名检测
			name = name.replace(/(^\s*)|(\s*$)/g,'').replace(/\s+/g,' ')
			
			if(name == ''){
				index.showErr('用户名不能为空');
				return;
			}/*else if(name.length<2 || name.length>20){
				index.showErr('姓名格式不正确');
				return;
			}*/
			//手机号检测
			if(telNum == ''){
				index.showErr('手机号码不能为空');
				return;
			}else if(!mobile(telNum)){
				index.showErr('手机号码格式不正确');
				return;
			}
			//区分是企业客户还是个人客户
			var type = $("#membertype option:selected").val();
			if('3'==type){
				$("#type").val('3');
			}
 			$.ajax({   
				type: 'POST',
				url: basePath+"/wechat/personalcentre/sell/create" ,
				async:false,
				data:$("#sellForm").serialize(),
					success: function (data) {
						if(data.reslut=='success'){
							if(data.isMember=='true'){
								$('.errInfo').html('');
								index.showDia();
							}else{
								window.location.href=basePath+"/wechat/personalcentre/sell/attention";
							}
					 		$("#submit").hide();
						}else{
							$('.errInfo').html('预约失败,'+data.reason);
						}
					}   
			}); 
 		//$("#submit").attr("disabled",true);
		},
		showErr : function(sHtml){
			oErr.html('*'+sHtml);
			oErr.show();
		},
		showDia: function(){
			var isShow = true;
			$('.diaBox').show();
			$('.diaBox').on('click',function(){
				$('.diaBox').hide();
				isShow = false;
			});
			if(isShow){
				setTimeout(function(){
					$('.diaBox').hide();
				},3000);
			}
			
		}
		
	}
	index.init();
})(jQuery);




function mobile(val){
	return /^(13|14|15|17|18)\d{9}$/i.test(val);
}

function reName(name){
	return /\s+/g.test(name);
}


//获取地址栏参数
function geturlparam(m){
	var json = {};
	var value = "";
	var _href = window.location.href.replace(/%26/g,"&").split("?");
	if(_href.length>1){
		if(_href[1].indexOf("&")>0){
			var arr = _href[1].split("&");
			for (var i = 0; i < arr.length; i++) {
				var p = arr[i].split("=")[0];
				json[arr[i].split("=")[0]] = arr[i].split("=")[1];
			}
		}else{
			json[ _href[1].split("=")[0]] =  _href[1].split("=")[1];
		}
	}
	return json[m];
} ;

