/*!
 * =====================================================
 * SUI Mobile - http://m.sui.taobao.org/
 *
 * =====================================================
 */
// jshint ignore: start
+function($){

$.smConfig.rawCitiesData = rawCitiesData;
}(Zepto);
// jshint ignore: end

/* jshint unused:false*/

+ function($) {
    "use strict";
    var format = function(data) {
        var result = [];
        for(var i=0;i<data.length;i++) {
            var d = data[i];
            if(d.name === "请选择") continue;
            result.push(d.name);
        }
        if(result.length) return result;
        return [""];
    };

    var sub = function(data) {
        if(!data.sub) return [""];
        return format(data.sub);
    };

    var getCities = function(d) {
        for(var i=0;i< raw.length;i++) {
            if(raw[i].name === d) return sub(raw[i]);
        }
        return [""];
    };

    var getDistricts = function(p, c) {
        for(var i=0;i< raw.length;i++) {
            if(raw[i].name === p) {
                for(var j=0;j< raw[i].sub.length;j++) {
                    if(raw[i].sub[j].name === c) {
                        return sub(raw[i].sub[j]);
                    }
                }
            }
        }
        return [""];
    };

    var raw = $.smConfig.rawCitiesData;
    var provinces = raw.map(function(d) {
        return d.name;
    });
    var initCities = sub(raw[0]);
    var initDistricts = [""];

    var currentProvince = provinces[0];
    var currentCity = initCities[0];
    var currentDistrict = initDistricts[0];

    var t;
    var defaults = {

        cssClass: "city-picker",
        rotateEffect: false,  //为了性能

        onChange: function (picker, values, displayValues) {
            var newProvince = picker.cols[0].value;
            var newCity;
            var nowIndex = picker.cols[0].activeIndex;
            console.log('省' + rawCitiesData[nowIndex].code);
            
            var nowIndexCity = picker.cols[1].activeIndex;
            if(rawCitiesData[nowIndex].sub[nowIndexCity] == null){
            	$("#cityId").val(rawCitiesData[nowIndex].id);
                $("#cityCode").val(rawCitiesData[nowIndex].code);
                $("#cityName").val(rawCitiesData[nowIndex].name);
                console.log('省' + rawCitiesData[nowIndexCity].code);
            }else{
                $("#cityId").val(rawCitiesData[nowIndex].sub[nowIndexCity].id);
                $("#cityCode").val(rawCitiesData[nowIndex].sub[nowIndexCity].code);
                $("#cityName").val(rawCitiesData[nowIndex].sub[nowIndexCity].name);
                console.log('市' + rawCitiesData[nowIndex].sub[nowIndexCity].code);

            }


            if(newProvince !== currentProvince) {
                // 如果Province变化，节流以提高reRender性能
                clearTimeout(t);

                t = setTimeout(function(){
                    var newCities = getCities(newProvince);
                    newCity = newCities[0];
                    var newDistricts = getDistricts(newProvince, newCity);
                    picker.cols[1].replaceValues(newCities);
                    picker.cols[2].replaceValues(newDistricts);
                    currentProvince = newProvince;
                    currentCity = newCity;
                    picker.updateValue();
                }, 200);
                return;
            }
            newCity = picker.cols[1].value;
            
            if(newCity !== currentCity) {
                picker.cols[2].replaceValues(getDistricts(newProvince, newCity));
                currentCity = newCity;
                picker.updateValue();
            }
        },

        cols: [
        {
            textAlign: 'center',
            values: provinces,
            cssClass: "col-province"
        },
        {
            textAlign: 'center',
            values: initCities,
            cssClass: "col-city"
        },
        {
            textAlign: 'center',
            values: initDistricts,
            cssClass: "col-district"
        }
        ]
    };

    $.fn.cityPicker = function(params) {
        return this.each(function() {
            if(!this) return;
            var p = $.extend(defaults, params);
            //计算value
            if (p.value) {
                $(this).val(p.value.join(' '));
            } else {
                var val = $(this).val();
                val && (p.value = val.split(' '));
            }

            if (p.value) {
                //p.value = val.split(" ");
                if(p.value[0]) {
                    currentProvince = p.value[0];
                    p.cols[1].values = getCities(p.value[0]);
                }
                if(p.value[1]) {
                    currentCity = p.value[1];
                    p.cols[2].values = getDistricts(p.value[0], p.value[1]);
                } else {
                    p.cols[2].values = getDistricts(p.value[0], p.cols[1].values[0]);
                }
                !p.value[2] && (p.value[2] = '');
                currentDistrict = p.value[2];
            }
            $(this).picker(p);
        });
    };

    //订单创建会员使用
    var defaults_person = {

            cssClass: "city-picker-person",
            rotateEffect: false,  //为了性能

            onChange: function (picker, values, displayValues) {
                var newProvince = picker.cols[0].value;
                var newCity;
                var nowIndex = picker.cols[0].activeIndex;
                console.log('省' + rawCitiesData[nowIndex].code);
                
                var nowIndexCity = picker.cols[1].activeIndex;
                if(rawCitiesData[nowIndex].sub[nowIndexCity] == null){
                	$("#cityId").val(rawCitiesData[nowIndex].id);
                    $("#cityCode").val(rawCitiesData[nowIndex].code);
                    $("#cityName").val(rawCitiesData[nowIndex].name);
                    console.log('市' + rawCitiesData[nowIndexCity].code);
                }else{
                    $("#cityId").val(rawCitiesData[nowIndex].sub[nowIndexCity].id);
                    $("#cityCode").val(rawCitiesData[nowIndex].sub[nowIndexCity].code);
                    $("#cityName").val(rawCitiesData[nowIndex].sub[nowIndexCity].name);
                    console.log('市' + rawCitiesData[nowIndex].sub[nowIndexCity].code);
                }


                if(newProvince !== currentProvince) {
                    // 如果Province变化，节流以提高reRender性能
                    clearTimeout(t);

                    t = setTimeout(function(){
                        var newCities = getCities(newProvince);
                        newCity = newCities[0];
                        var newDistricts = getDistricts(newProvince, newCity);
                        picker.cols[1].replaceValues(newCities);
                        picker.cols[2].replaceValues(newDistricts);
                        currentProvince = newProvince;
                        currentCity = newCity;
                        picker.updateValue();
                    }, 200);
                    return;
                }
                newCity = picker.cols[1].value;
                
                if(newCity !== currentCity) {
                    picker.cols[2].replaceValues(getDistricts(newProvince, newCity));
                    currentCity = newCity;
                    picker.updateValue();
                }
            },

            cols: [
            {
                textAlign: 'center',
                values: provinces,
                cssClass: "col-province"
            },
            {
                textAlign: 'center',
                values: initCities,
                cssClass: "col-city"
            },
            {
                textAlign: 'center',
                values: initDistricts,
                cssClass: "col-district"
            }
            ]
        };

        $.fn.cityPicker_person = function(params) {
            return this.each(function() {
                if(!this) return;
                var p = $.extend(defaults_person, params);
                //计算value
                if (p.value) {
                    $(this).val(p.value.join(' '));
                } else {
                    var val = $(this).val();
                    val && (p.value = val.split(' '));
                }

                if (p.value) {
                    //p.value = val.split(" ");
                    if(p.value[0]) {
                        currentProvince = p.value[0];
                        p.cols[1].values = getCities(p.value[0]);
                    }
                    if(p.value[1]) {
                        currentCity = p.value[1];
                        p.cols[2].values = getDistricts(p.value[0], p.value[1]);
                    } else {
                        p.cols[2].values = getDistricts(p.value[0], p.cols[1].values[0]);
                    }
                    !p.value[2] && (p.value[2] = '');
                    currentDistrict = p.value[2];
                }
                $(this).picker(p);
            });
        };
        
        var defaults_company = {

                cssClass: "city-picker-company",
                rotateEffect: false,  //为了性能

                onChange: function (picker, values, displayValues) {
                    var newProvince = picker.cols[0].value;
                    var newCity;
                    var nowIndex = picker.cols[0].activeIndex;
                    console.log('省' + rawCitiesData[nowIndex].code);
                    
                    var nowIndexCity = picker.cols[1].activeIndex;
                    console.log('市' + rawCitiesData[nowIndexCity].code);
                    if(rawCitiesData[nowIndex].sub[nowIndexCity] == null){
                    	$("#cityId").val(rawCitiesData[nowIndex].id);
                        $("#cityCode").val(rawCitiesData[nowIndex].code);
                        $("#cityName").val(rawCitiesData[nowIndex].name);
                        console.log('市' + rawCitiesData[nowIndex].code);
                    }else{
                        $("#cityId").val(rawCitiesData[nowIndex].sub[nowIndexCity].id);
                        $("#cityCode").val(rawCitiesData[nowIndex].sub[nowIndexCity].code);
                        $("#cityName").val(rawCitiesData[nowIndex].sub[nowIndexCity].name);
                        console.log('市' + rawCitiesData[nowIndex].sub[nowIndexCity].code);
                    }


                    if(newProvince !== currentProvince) {
                        // 如果Province变化，节流以提高reRender性能
                        clearTimeout(t);

                        t = setTimeout(function(){
                            var newCities = getCities(newProvince);
                            newCity = newCities[0];
                            var newDistricts = getDistricts(newProvince, newCity);
                            picker.cols[1].replaceValues(newCities);
                            picker.cols[2].replaceValues(newDistricts);
                            currentProvince = newProvince;
                            currentCity = newCity;
                            picker.updateValue();
                        }, 200);
                        return;
                    }
                    newCity = picker.cols[1].value;
                    
                    if(newCity !== currentCity) {
                        picker.cols[2].replaceValues(getDistricts(newProvince, newCity));
                        currentCity = newCity;
                        picker.updateValue();
                    }
                },

                cols: [
                {
                    textAlign: 'center',
                    values: provinces,
                    cssClass: "col-province"
                },
                {
                    textAlign: 'center',
                    values: initCities,
                    cssClass: "col-city"
                },
                {
                    textAlign: 'center',
                    values: initDistricts,
                    cssClass: "col-district"
                }
                ]
            };

            $.fn.cityPicker_company = function(params) {
                return this.each(function() {
                    if(!this) return;
                    var p = $.extend(defaults_company, params);
                    //计算value
                    if (p.value) {
                        $(this).val(p.value.join(' '));
                    } else {
                        var val = $(this).val();
                        val && (p.value = val.split(' '));
                    }

                    if (p.value) {
                        //p.value = val.split(" ");
                        if(p.value[0]) {
                            currentProvince = p.value[0];
                            p.cols[1].values = getCities(p.value[0]);
                        }
                        if(p.value[1]) {
                            currentCity = p.value[1];
                            p.cols[2].values = getDistricts(p.value[0], p.value[1]);
                        } else {
                            p.cols[2].values = getDistricts(p.value[0], p.cols[1].values[0]);
                        }
                        !p.value[2] && (p.value[2] = '');
                        currentDistrict = p.value[2];
                    }
                    $(this).picker(p);
                });
            };
}(Zepto);
