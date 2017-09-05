(function($){
	//本文件js为调试代码  开发的时候可全部删除
	//隐藏显示菜单
	$('.header .userIcon').on('tap',function(){
		$('.userSlider').show();
	})
	$('.userSlider .close').on('tap',function(){
		$('.userSlider').hide();
	});


	//极车服务

	$('.serviTitle li').on('tap',function(){
		$('.serviTitle li').removeClass('active');
		$(this).addClass('active');
		$('.serviSec').hide()
		$('.serviSec').eq($(this).index()).show()
	});

	//暂无数据

	$('.nodataTest').on('tap',function(){
		$('.serviSec .carInfo').hide();
		$('.serviSec .noown').show();
	});

})(Zepto);

