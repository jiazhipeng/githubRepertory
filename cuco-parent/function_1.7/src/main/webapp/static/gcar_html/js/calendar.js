(function ($) {
    var Plugin,
        privateMethod,
        state_cfg = [{ class: 'yong', text: '用', id: 1 }, { class: 'zhang', text: '障', id: 2 }, { class: 'zheng', text: '整', id: 3 }, { class: 'yu', text: '预', id: 4 }, { class: 'shi', text: '失', id: 5 },{ class: 'qian', text: '签', id: 6 }],
        html_dattepicker = ' <div class="datepicker"></div>',
        html_title = ' <div class="datepicker-title"><span>日</span><span>一</span><span>二</span><span>三</span><span>四</span><span>五</span><span>六</span></div>',
        html_body = ' <div class="datepicker-body"></div',
        html_r = '<div class="datepicker-r"></div>';
    Plugin = (function () {
        function Plugin(element, options) {
            this.settings = $.extend({}, $.fn.plugin.defaults, options);
            this.$element = $(element);
            //初始化调用一下
            this.init();
        }

        Plugin.prototype = {

            init: function () {
                var _this = this;
                this.$picker = $(html_dattepicker);
                this.$picker_head = $(html_title);
                this.$picker_body = $(html_body);

                this.$picker.append(this.$picker_head);

                var $picker_rs = this._loopMonth(this.settings.year, this.settings.month);
                this.$picker_body.html($picker_rs);
                this.$picker.append(this.$picker_body);

                this.$element.html(this.$picker);
                this.$picker_body.on('click', 'span.cell', function () {
                    _this.settings.onCellClick && _this.settings.onCellClick.call(this, $(this).data('val'))
                });

            },
            prev: function (year, month) {
                var $picker_rs = this._loopMonth(year, month);
                this.$picker_body.html($picker_rs);
            },
            nxt: function (year, month) {
                var $picker_rs = this._loopMonth(year, month);
                this.$picker_body.html($picker_rs);
            },
            _loopMonth: function (year, month) {
                var days = privateMethod.getMonthDays(year, month);
                var html_cell = $('<div></div>');
                var weeks = privateMethod.getMonthWeek(year, month, days);

                for (var index = 0; index < weeks; index++) {
                    var cells = '',
                    	c_html;
                    for (var index_w = 0; index_w < 7; index_w++) {
                        var day_crrt = index_w + index * 7;
                        var start = new Date([year, month, 1].join('/')).getDay();

                        if ((index === 0 && day_crrt < start) || (index === (weeks - 1) && (day_crrt - start + 1) > days)) {
                            cells += '<span></span>';
                        }
                        else {
                            if (this.settings.type === 'car_state') {
                                 c_html = '<em></em>';
                            } 
                            else if(this.settings.type === 'fetch_car'){
                            	 c_html = '<i>0项</i>';
                            }
                            else {
                                 c_html = '<i>0辆</i>';
                            }
                            cells += '<span class="cell" data-val="' + new Date([year, month, day_crrt - start + 1].join('/')).Format('yyyy/MM/dd') + '">' + (day_crrt - start + 1) +  c_html + '</span>';
                        }
                    }
                    var r = $(html_r);
                    r.append(cells);
                    html_cell.append(r);
                }
                return html_cell;
            },
            setCars: function (arrs) {
                for (var index = 0; index < arrs.length; index++) {
                    var val = arrs[index];
                    this.$picker_body.find('span[data-val="' + val.date + '"]').find('i').html(val.count + '辆');
                }
            },
            fetchCars:function(cars){
            	for (var index = 0; index < cars.length; index++) {
                    var val = cars[index];
                    this.$picker_body.find('span[data-val="' + val.date + '"]').find('i').html(val.count + '项');
                }
            },
            setState: function (arrs) {
                //state_cfg
                for (var index = 0; index < arrs.length; index++) {
                    var crrt = arrs[index];
                    var state = privateMethod.getStateById(crrt.count);
                    if (state === null) return;
                    this.$picker_body.find('span[data-val="' + crrt.date + '"]').addClass(state.class).find('em').html(state.text);

                }

                // for (var index = 0; index < arrs.length; index++) {
                //     var val = arrs[index];
                //     
                // }
            }
        };

        return Plugin;

    })();

    /**
     * 私有方法
     */
    privateMethod = {
        //判断年份是否为润年
        isLeapYear: function (year) {
            return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        },
        //获取某一年份的某一月份的天数
        getMonthDays: function (year, month) {
            month--;
            return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (privateMethod.isLeapYear(year) ? 29 : 28);
        },
        getMonthWeek: function (a, b, c) {
            var date = new Date(a, parseInt(b) - 1, c), w = date.getDay(), d = date.getDate();
            return Math.ceil(
                (d + 6 - w) / 7
            );
        },
        //根据typeid获取state对象
        getStateById:function(id) {
            for (var index = 0; index < state_cfg.length; index++) {
                var crrt = state_cfg[index];
                if (crrt.id === id) {
                    return crrt;
                }
            }
            return null;
        }
    };

    $.fn.plugin = function (options) {
        return this.each(function () {
            var $this = $(this),
                instance = $.fn.plugin.lookup[$this.data('plugin')];
            if (!instance) {
                //zepto的data方法只能保存字符串，所以用此方法解决一下
                $.fn.plugin.lookup[++$.fn.plugin.lookup.i] = new Plugin(this, options);
                $this.data('plugin', $.fn.plugin.lookup.i);
                instance = $.fn.plugin.lookup[$this.data('plugin')];
            }

            if (typeof options === 'string') instance[options]();
        })
    };
    $.fn.plugin.lookup = { i: 0 };

    /**
     * 插件的默认值
     */
    $.fn.plugin.defaults = {
        year: new Date().getFullYear(),
        month: new Date().getMonth() + 1,
        type: 'carmgr',
        onCellClick: null
    };

    /**
     * 优雅处： 通过data-xxx 的方式 实例化插件。
     * 这样的话 在页面上就不需要显示调用了。
     * 可以查看bootstrap 里面的JS插件写法
     */
    // $(function () {
    //     return new Plugin($('[data-plugin]'));
    // });
})(Zepto||$);
