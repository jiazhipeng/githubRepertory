(function ($, window) {
    var deal_tooltip = {
        init: function () {
            var $template = $(this.template)
            $(document.documentElement).append($template);
            
            $('.popup').get(0).addEventListener('touchmove',this.fScrollFix,false);
        },
        fScrollFix:function (e){
    	　　e.preventDefault();
    	　　e.stopPropagation();
    		//$(window.body).css({overflow:'hidden'});
    	},
        template: [
            '<div class="popup" style="display:none" >',
            '   <div class="popup-con">',
            '       <div class="popup-head"><h3>提示</h3></div>',
            '       <div class="popup-body"><p></p></div>',
            '       <div class="popup-foot"><a href="javascript:">确认</a></div>',
            '   </div>',
            '</div>'
        ].join('')
    };

    var tooltip = function () {
        if ($('.popup').length <= 0) {
            deal_tooltip.init();
        }
        return tooltip;
    };
    tooltip.show = function (title, body, callback,btn_text) {
        $('.popup')
            .show()
            .find('.popup-foot>a')
            .unbind('click').click(function (e) { $('.popup').hide(); callback && callback.call(this, e);})
            .html(btn_text||'确认')
            .end()
            .find('.popup-head>h3')
            .html(title || '提示')
            .end()
            .find('.popup-body>p').html(body || '什么？');
        return tooltip;
    }

     window.tooltip = window.tooltip || tooltip;

     var deal_confirm = {
        init: function () {
            var $template = $(this.template)
            $(document.documentElement).append($template);
            $('.confirm').get(0).addEventListener('touchmove',this.fScrollFix,false);
        },
        fScrollFix:function (e){
      	　　e.preventDefault();
      	　　e.stopPropagation();
      	},
        template: [
            '<div class="confirm" style="display:none" >',
            '   <div class="confirm-con">',
            '       <div class="confirm-head"><h3>提示</h3></div>',
            '       <div class="confirm-body"><p></p></div>',
            '       <div class="confirm-foot">',
            '           <a href="javascript:" class="sure">确认</a>',
            '           <a href="javascript:" class="cancel">取消</a>',
            '       </div>',
            '   </div>',
            '</div>'
        ].join('')
    };

    var xconfirm = function () {
        if ($('.confirm').length <= 0) {
            deal_confirm.init();
        }
        return  xconfirm;
    };
    xconfirm.show = function (title, body, sure_callback,btn_sure_text,cancel_callback,btn_cancel_text) {
        $('.confirm')
            .show()
            .find('.confirm-foot>a.sure')
            .unbind('click')
            .click(function (e) { $('.confirm').hide(); sure_callback && sure_callback.call(this, e); })
            .html(btn_sure_text||'确认')
            .end()
            .find('.confirm-foot>a.cancel')
            .unbind('click')
            .click(function (e) { $('.confirm').hide();cancel_callback && cancel_callback.call(this, e); })
            .html(btn_cancel_text||'取消')
            .end()
            .find('.confirm-head>h3')
            .html(title || '提示')
            .end()
            .find('.confirm-body>p').html(body || '什么？');
    }
    window.myconfirm = window.myconfirm || xconfirm;

})(jQuery, window);