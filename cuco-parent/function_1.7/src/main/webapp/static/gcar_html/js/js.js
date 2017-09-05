(function($){
	var _href = window.location.href.split('#')[0];
	if(_href.indexOf('&')!=-1){
		_href = _href.replace(/&/g,'%26');
		window.location = _href;
	}
	
	var channel = geturlparam('channel');
	//var act = geturlparam('activity');
	var code = geturlparam('code');
	var state = geturlparam('state');
	//var _url = 'http://192.168.1.207:8080/service/service/customer/';
	var _url = 'http://1mobility.cn/service/service/customer/';
	var oWrap = document.querySelector('.swiper-wrapper');
	var aPage = document.querySelectorAll('.page');
	var nPage = aPage.length;//页数
	
	var oAudio = document.getElementById('audio');
	document.addEventListener('touchstart',function(){
		oAudio.play();
	},false);
	var iNow = 6;
	var oH = document.documentElement.clientHeight;
	
	var oErr = $('.err');//预约错误提示
	var index = {
		init : function(){
			index.loading();
			
			$('.button').on('tap',function(){
				index.toBooking();
			});
			
			index.swichPage();
			
		},
		toBooking : function(){
			var name = $('#name').val(),telNum = $('#tel').val();
			
			
			//用户名检测
			name = name.replace(/(^\s*)|(\s*$)/g,'').replace(/\s+/g,' ')
			
			if(name == ''){
				index.showErr('用户名不能为空');
				return;
			}else if(name.length<2 || name.length>20){
				index.showErr('姓名格式不正确');
				return;
			}
			//手机号检测
			if(telNum == ''){
				index.showErr('手机号码不能为空');
				return;
			}else if(!mobile(telNum)){
				index.showErr('手机号码格式不正确');
				return;
			}
			
 			$.ajax({   
				type: "get",     
				url: _url + 'register',     
				dataType: "json", 
				data: {
					//name: encodeURI(name),
					name: name,
					mobile: telNum,
					cityflag: '',
					source: '01',
					state: '',
					code: code,
					channel: channel,
					activity: 'party'
				},
				success: function (data) {
					console.log(data);
					if(data.isSuccess == 'true'){//预约成功
						$('.err').html('');
						index.showDia();
					}
					else if(data.isSuccess == 'false'){
						index.showErr(data.errorMessage);
					}
				}   
			}); 
			
		},
		swichPage : function(){
			//按钮闪动切换
			$('.page5 .btn').on('tap',function(){
				isClick = $(this).index();
//				
				$('.page5 .btn').removeClass('btnMove');
				$('.page6 div').hide().removeClass('Dmove');
			
			$('.page6 div').eq($(this).index()-1).show().addClass('Dmove');
//				oWrap.style.webkitTransition='none';
//				oWrap.style.webkitTransform='translateY(-'+iNow/nPage*100+'%)';
				
				if($(this).index()!=3){
					$(this).next().addClass('btnMove');
				}else{
					$('.page5 .btn').eq(0).addClass('btnMove');
				}
				oWrap.style.webkitTransition='.3s ease-in-out all';
				oWrap.style.webkitTransform='translateY(-'+iNow*oH+'px)';
				
				
//				oWrap.addEventListener('transitionend',function(){
//					oWrap.style.webkitTransition='';
//				},false);
			});
			
		},
		loading : function(){
			var aImage = document.querySelectorAll('.loading img');
	    		for(var i=0; i<aImage.length; i++){
	    			(function(index){
	    				aImage[index].style.webkitAnimation = 'loading .3s ease-out '+(0.2*index)+'s 1 forwards';
	    			})(i);
	    		}
	    			
	    	//var aniEnd = 'webkitAnimationEnd' || 'animationend';
	    			
	    	aImage[8].addEventListener('webkitAnimationEnd',function(){
	    		setTimeout(function(){
	    			oWrap.style.webkitTransition='.3s ease-in-out all';
	    			oWrap.style.webkitTransform='translateY(-'+oH+'px)';
	    			var swiper = new Swiper('.swiper-container', {
	    		        pagination: '.swiper-pagination',
	    		        paginationClickable: true,
	    		        direction: 'vertical'
	    		    });
	    		},800);
	    	});
//			$('.loading img').each(function(index,element){
//				$(this).css('animation','loading .3s ease-out '+(0.2*index)+'s 1 forwards');
//			});
//			$('.loading img').eq(8).on('animationend',function(){
//				setTimeout(function(){
//					oWrap.style.webkitTransition='.3s ease-in-out all';
//					oWrap.style.webkitTransform='translateY(-'+oH+'px)';
//					var swiper = new Swiper('.swiper-container', {
//				        pagination: '.swiper-pagination',
//				        paginationClickable: true,
//				        direction: 'vertical'
//				    });
//				},800);
//			})
		},
		showErr : function(sHtml){
			oErr.html('*'+sHtml);
			oErr.show();
		},
		showDia: function(){
			$('.diaBox').show();
			$('.diaBox img').on('tap',function(){
				$('.diaBox').hide();
			});
		}
		
	}
	index.init();
})(Zepto);




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

