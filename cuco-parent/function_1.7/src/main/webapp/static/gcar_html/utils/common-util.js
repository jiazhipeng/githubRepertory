/*
* 公用js
*/
//////////////////////////////////////////////////////////////////////////////
//功能说明：3种trim实现
//////////////////////////////////////////////////////////////////////////////
String.prototype.trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
String.prototype.ltrim = function() { 
	return this.replace(/(^\s*)/g, ""); 
} 
String.prototype.rtrim = function() { 
	return this.replace(/(\s*$)/g, ""); 
}
//added by William 2011-12-23 扩展String类，将字符转换为数字
String.prototype.toNumber = function() {
	if(this==""){
		return 0;
	}
	return Number(this); 
}
//added by William 2011-12-28 判断参数是否为空
function isNotBlank(param) {
	if(param==null){
		return false;
	}
	//数值类型的先转换成字符串
	param+="";
	if(param.trim()==""){
		return false;
	}
	return true;
}
//added by William 2012-01-15 判断参数是不是数字
function isNumber(param){
	if(!param||param==""){
		return false;
	}
	return !isNaN(param);
}
//四舍五入,参数可以是保留小数的位数,返回数字或者空字符--added by William 2012-02-10
String.prototype.toFixed = function (fractionDigits){
	if(this){
		var param = this+"";
		if(param==""){
			return "";
		}
		return Number(param).toFixed(fractionDigits);
	}else{
		return "";
	}
}
//////////////////////////////////////////////////////////////////////////////
//功能说明：获取给定字符串的长度,包括回车换行符
//////////////////////////////////////////////////////////////////////////////
String.prototype.getLengthrn = function() {  
	var length = this.length;
	if(this.indexOf("\n") != -1){
		var nrl = this.match(new RegExp("\n","g")).length;
		length +=  nrl;
	}
	return length;   
}
//////////////////////////////////////////////////////////////////////////////
//功能说明：截取字符串，长度去掉回车换行
//////////////////////////////////////////////////////////////////////////////
String.prototype.substrrn = function(length) { 
	var nrl = 0;
	if(this.indexOf("\n") != -1){
		nrl = this.match(new RegExp("\n","g")).length;
	}
	return this.substr(0, length-nrl);
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 判断给定的字是否为中文
//参数定义： word unicode字符
//////////////////////////////////////////////////////////////////////////////
function isChinese(word) {
	var lst = /[u00-uFF]/;
	return !lst.test(word);
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 判断给定的节点对象是否为空
//参数定义： word unicode字符
//////////////////////////////////////////////////////////////////////////////
function isNull(field){
	var Text=""+field.value;
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++)
		if(Text.charAt(i)!=" ")
		break;
		if(i>=Text.length){
		    return true;
		} else { 
		    return false;
		}
	}else
		return true;
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 判断给定的值是否为int型
//参数定义： word unicode字符
//////////////////////////////////////////////////////////////////////////////
var intnumber = /^\d+$/;
function isInt(intValue){
	if(intnumber.test(intValue)){
		return true;
	}else{
		return false;
	}
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 判断给定的值是否为Email,格式正确返回true,否则返回false
//参数定义：给定的字符串
//////////////////////////////////////////////////////////////////////////////
function validateEmail(val){
	var regExp = new RegExp("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
	if(regExp.test(val)){
		return true;
	}
	return false;
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 判断给定的值是否为手机号码,目前支持号码段为13，14，15，18，格式正确返回true,否则返回false
//参数定义：给定的字符串
//////////////////////////////////////////////////////////////////////////////
function validateCellPhone(val){
	var regExp = /^1[3|4|5|8][0-9]\d{8}$/;
	if(regExp.test(val)){
		return true;
	}
	return false;
}
//////////////////////////////////////////////////
//功能说明：密码验证
//参数定义：给定字符串
function checkPassword(password){
	//只能输入6-20个以字母开头、数字的字串
	var regExp = new RegExp("[a-zA-Z0-9]{6,18}");
	if(regExp.test(password)){
		return true;
	}
	return false;
	
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 定义JS中用于高效拼接字符串的StringBuiler
//////////////////////////////////////////////////////////////////////////////
function StringBuilder(){
	this.__string__ = new Array();
}
//////////////////////////////////////////////////////////////////////////////
//功能说明： 定义StringBuiler的append方法
//////////////////////////////////////////////////////////////////////////////
StringBuilder.prototype.append = function(str){
	this.__string__.push(str);
};
//////////////////////////////////////////////////////////////////////////////
//功能说明： 定义StringBuiler的toString方法
//////////////////////////////////////////////////////////////////////////////
StringBuilder.prototype.toString = function(){
	return this.__string__.join("");
};
//////////////////////////////////////////////////////////////////////////////
//功能说明： 验证字符串长度，如果验证通过返回true，未通过返回false
//参数定义：str:需要验证的字符串，length:验证不超过的长度
//////////////////////////////////////////////////////////////////////////////
function lengthValidate(str,length){
	str.length<=length ? flag=true : flag=false;
	return flag;
}
//////////////////////////////////////////////////////////////////////////////
//功能说明：字段截取，返回截取的从0个到n个的字符串，中文为2个字节长度，其他为1个字节
//参数定义：n：需要截取最大长度
//////////////////////////////////////////////////////////////////////////////
String.prototype.sub = function(n)
{    
	var r = /[^\x00-\xff]/g;    
	if(this.replace(r, "mm").length <= n) return this;     
	var m = Math.floor(n/2);    
	for(var i=m; i<this.length; i++) {    
	if(this.substr(0, i).replace(r, "mm").length>=n) {    
	   return this.substr(0, i) ; }    
	} 
	return this;   
 };
//////////////////////////////////////////////////////////////////////////////
//功能说明：如果字符串大于num个字符截取字符串前num个字符，后面省略显示
//参数定义：str:需要省略的字符串
//////////////////////////////////////////////////////////////////////////////
function shortDesc(str,num){
	if(str==null || str=='')
		return str;
	if(str.match(/[^\x00-\xff]/ig)){
		var cArr = str.match(/[^\x00-\xff]/ig);
		if((str.length+cArr.length)<=num){
			return str;
		}else{
			return str.sub(num)+"...";
		}
	}else{
		if(str.length<=num){
			return str;
		}else{
			return str.sub(num)+"...";
		}
	}
}
//////////////////////////////////////////////////////////////////////////////
//功能说明：后台拿到前台的DATETIME需要转换，提供日期date转换方法
//参数定义：datetime:需要转换的datetime/ new Date(datetime).format("yyyy-MM-dd");
//////////////////////////////////////////////////////////////////////////////
Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), // cond
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
//////////////////////////////////////////////////////////////////////
///功能说明：html转义，增加从数据库中取数据，数据里有\n等回车转义符不能识别
/////////////////////////////////////////////////////////////////////
function htmlEncode(s) {
	if(s != null){
	    s = s.replace(new RegExp("&","g"), "&amp;");
	    s = s.replace(new RegExp("<","g"), "&lt;");
	    s = s.replace(new RegExp(">","g"), "&gt;");
	    s = s.replace(new RegExp("\"","g"), "&quot;");
	    s = s.replace(new RegExp("\'","g"), "&#34;");
	    s = s.replace(new RegExp(" ","g"), "&nbsp;");
	    s = s.replace(new RegExp("\n","g"), "<br/>");
	}
    return s;
}

//////////////////////////////////////////////////////////////////////
///功能说明：校验日期
//1900-01-01 through 2099-12-31
//Matches invalid dates such as February 31st
/////////////////////////////////////////////////////////////////////
function validateYMDDate(d){
	return /(19|20)[0-9]{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])/.test(d);
}
//////////////////////////////////////////////////////////////////////
///功能说明：等比例缩放图片
//img 图片Id
//width,height 限定的最大高度和宽度
/////////////////////////////////////////////////////////////////////
function setPopimage(img,width,height){
	var image = document.getElementById(img);
	if (image.width > image.height){
	   if(image.width>width){
	    image.width=width;
	    image.height=width/image.width*image.height;
	   }
	}else{
	   if(image.height>height){
	    image.height=height;
	    image.width=height/image.height*image.width;
	   }
	}
}
//////////////////////////////////////////////////////////////////////
///功能说明：判断当前浏览器类型
/////////////////////////////////////////////////////////////////////
    function getClientOs(){
		var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		var s;
		var nowClient="";
		(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
		(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
		if (Sys.ie) nowClient='IE' + Sys.ie;
		if (Sys.firefox) nowClient='Firefox';
		if (Sys.chrome) nowClient='Chrome';
		if (Sys.opera) nowClient='Opera';
		if (Sys.safari) nowClient='Safari';
		return nowClient;
	}

//////////////////////////////////////////////////////////////////////
///功能说明：格式化数据货币，以千分符分割，例如1,000,000,000
///参数定义：number 需要格式化的金额字符串,d 可选参数，指定精度
///updated by William 2012-04-16
///@see String.propotype.toFixed
/////////////////////////////////////////////////////////////////////	
function outputDollars(number,d){
	//0是false
	if(!number&&Number(number)!=0){
		return "";
	}
	number = number+"";
	if(number==""){
		return "";
	}
	if(d&&d>0){
		number = number.toFixed(d);
	}
	var flag = "";
	if(number.indexOf("-")!=-1){
		flag = "-";
		number = number.replace("-","");
	}
	var numArr = number.split(".");
	number = numArr[0];
	if (number.length<= 3)
		return flag + (number == '' ? '0' : number+'.'+numArr[1]);
	else{
		 var mod = number.length%3;
		 var output = (mod == 0 ? '' : (number.substring(0,mod)));
		 for (i=0 ; i< Math.floor(number.length/3) ; i++)
		 {
		   if ((mod ==0) && (i ==0))
		   output+= number.substring(mod+3*i,mod+3*i+3);
		   else
		   output+= ',' + number.substring(mod+3*i,mod+3*i+3);
		 }
		 return (flag + output+'.'+numArr[1]);
	}
}

//////////////////////////////////////////////////////////////////////
///功能说明：年龄校验
///参数定义：age 待校验年龄,minAge 最小年龄,maxAge 最大年龄
/////////////////////////////////////////////////////////////////////	
function ageValid(age,minAge,maxAge){
	return age>=minAge&&age<=maxAge ? true : false;
}

//////////////////////////////////////////////////////////////////////
///功能说明：计算年龄
///参数定义：birthDate 出身日期,格式是yyyy-mm-dd的字符串,或者是Date类型
/////////////////////////////////////////////////////////////////////	
function calcAge(date, type){
	var d1 = new Date(Date.parse(date.replace(/-/g, "/")));//2012-01-01
	var d2 = new Date();
	var year1 = d1.getFullYear();
	var year2 = d2.getFullYear();
	var month1 = d1.getMonth();
	var month2 = d2.getMonth();
	var diff = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
	var age = d2.getFullYear() - d1.getFullYear() - ((d2.getMonth() < d1.getMonth() || d2.getMonth() == d1.getMonth() && d2.getDate() < d1.getDate()) ? 1 : 0);
	var result = ((diff / 365) * 100).toString().split(".");
	if(type == "0"){//驾龄计算
		return result[0] / 100;
	}else{
		return age;
	}
}

//////////////////////////////////////////////////////////////////////
///功能说明：验证身份证
///参数定义：value 身份证号码
/////////////////////////////////////////////////////////////////////
function validateCard(value) {
	/*IPARTNER-2033 
	 * 将身份证校验改成跟核心一样调用存储过程校验，屏蔽之前的方法
	 * 修改人：dhtan
	 * 时间：2013年4月19日15:03:07
	 * 
 	*/
 	value=value.trim();
 	var Y,JYM;  
    var S,M;  
    var idcard_array = new Array();  
    idcard_array = value.split("");
 	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
	if(value!=""&&value.length!=15&&value.length!=18){
		return false;
	}
 	if(value!=""&&area[parseInt(value.substr(0,2))]==null){
 		return false;
 	}		 	
 	switch(value.length){
 	//15位身份证号校验
 	case 15: 
 		if ((parseInt(value.substr(6,2))+1900) % 400 == 0 || ((parseInt(value.substr(6,2))+1900) % 100 != 0 && (parseInt(value.substr(6,2))+1900) % 4 == 0 )){ 
 			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; 
 		} else { 
 			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; 
 		}
 		if(!ereg.test(value)){
 			return false;	
 		}
 	break;
 	//18位身份证号校验
 	case 18: 
 		if ( parseInt(value.substr(6,4)) % 400 == 0 || (parseInt(value.substr(6,4)) % 100 != 0 && parseInt(value.substr(6,4))%4 == 0 )){ 
 			//闰年出生日期的合法性正则表达式 
 			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9xX]{1}$/;
 		} else { 
 			//平年出生日期的合法性正则表达式 
 			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9xX]{1}$/;
 		}
 		if(ereg.test(value)){//测试出生日期的合法性  
 		     //计算校验位  
 		     S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7  
 		     + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9  
 		     + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10  
 		     + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5  
 		     + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8  
 		     + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4  
 		     + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2  
 		     + parseInt(idcard_array[7]) * 1   
 		     + parseInt(idcard_array[8]) * 6  
 		     + parseInt(idcard_array[9]) * 3 ;  
 		     Y = S % 11;  
 		     M = "F";  
 		     JYM = "10X98765432";  
 		     M = JYM.substr(Y,1);/*判断校验位*/
 		    if(M == idcard_array[17].toUpperCase()){  
 		       return true; /*检测ID的校验位false;*/  
 		    }  
 		    else {  
 		       return false;  
 		    }
 		}
 		else {
 		     return false;  
 		}  
 	break;
 	default:  
 	     return true;
 	}
 	
	/*dhtan 修改的后台校验身份证
	if(JsHashMap.prototype.get(value) == null) {
		var params = {cardNo:value};
		$.get(contextPath+'/card/validate.json',params,function(response){
			var result = response.result;
			if("1" == result) {
				JsHashMap.prototype.put(value,true);
				return true;
			} else {
				JsHashMap.prototype.put(value,false);
				return false;
			}
		},"json");
	} else {
		return JsHashMap.prototype.get(value);
	}
	*/
}



//////////////////////////////////////////////////////////////////////
///功能说明：外部调用通用验证身份证
///参数定义：O对象；n提示信息
/////////////////////////////////////////////////////////////////////
function checkIDCard(o,n){
	if(o.val()==""){
		return true;
	}
	var cardNo = o.val();
	var boolean = validateCard(cardNo);
	if(!boolean){
		$.msg.alert("系统提示",n+"输入不合法！",function(){
		o.focus();
		});
	}
	return boolean;
}
/*dhtan 之前的调后台的方法
	function checkIDCard(o,n){
		if(o.val()==""){
			return true;
		}
		var boolen = false;
		if(JsHashMap.prototype.get(o.val()) == null) {
			var params = {cardNo:o.val()};
			$.get(contextPath+'/card/validate.json',params,function(response){
				var result = response.result;
				if("1" == result) {
					JsHashMap.prototype.put(o.val(),true);
					o.blur();
					boolen = true;
				} else {
					$.msg.alert("系统提示",n+"输入不合法！",function(){
						o.focus();
					});
					JsHashMap.prototype.put(o.val(),false);
					boolen = false;
				}
			},"json");
		}else if(!JsHashMap.prototype.get(o.val())){
			$.msg.alert("系统提示",n+"输入不合法！",function(){
				o.focus();
			});
			boolen = false;
		}else {
			boolen = true;
		}
		return boolen;
	}
*/
//////////////////////////////////////////////////////////////////////
///功能说明：比较两个日期相差天数
///参数定义：startDate endDate 以 yyyy-MM-dd格式
/////////////////////////////////////////////////////////////////////
function dateDiff(startDate,endDate){
	times = startDate.split("-");
	date1 = new Date(times[0],times[1]-1,times[2]);
	times = endDate.split("-");
	date2 = new Date(times[0],times[1]-1,times[2]);
	var diffValue = date2.getTime() - date1.getTime();
	return parseInt(diffValue/86400000);    
}
//////////////////////////////////////////////////////////////////////
///功能说明：根据身份证号获取出生日期、性别、年龄
///参数定义：params ,如{id:"420625********664X",format:"yyyy-MM-dd"}
///参见：format()方法
///返回：返回对象result,如result = {birthDay:"1987-02-09",sex:"F",age:25}
///@author William @since 2012-02-06
/////////////////////////////////////////////////////////////////////
function resolveIDCard(params){
	params.id=$.trim(params.id);
	var result = {};
	var _birthDate;
	switch(params.id.length){
	case 18: _birthDate = {year : params.id.substr(6, 4),
			month : params.id.substr(10, 2),
			date : params.id.substr(12, 2)};
		result['sex'] = parseInt(params.id.substr(16, 1))%2==0 ? "F":"M";//第17位
		break;
	case 15:
		_birthDate = {year : "19" + params.id.substr(6, 2),
				month : params.id.substr(8, 2),
				date : params.id.substr(10, 2)};
		result['sex'] = parseInt(params.id.substr(14, 1))%2==0 ? "F":"M";//第15位
		break;
	}
	var birthDate = new Date(Number(_birthDate.year),Number(_birthDate.month) - 1,Number(_birthDate.date));
	result['birthDay'] = birthDate.format(params.format);
	var _currDate = new Date();
	if(Number(_birthDate.month) - 1 < _currDate.getMonth()){
		result['age'] = _currDate.getFullYear() - birthDate.getFullYear();
	}else if(Number(_birthDate.month) - 1 == _currDate.getMonth()){
		if(Number(_birthDate.date)<=_currDate.getDate()){
			result['age'] = _currDate.getFullYear() - birthDate.getFullYear();
		}else{
			result['age'] = _currDate.getFullYear() - birthDate.getFullYear() - 1;
		}
	}else{
		result['age'] = _currDate.getFullYear() - birthDate.getFullYear() - 1;
	}
	return result;
}
//////////////////////////////////////////////////////////////////////
///功能说明：根据日期字符串转换为Date类型
///参数定义：date
///返回：Date
///@author William @since 2012-03-02
/////////////////////////////////////////////////////////////////////
function resolveCharacter2Date(date){
	var arr = date.split("-");
	return new Date(Number(arr[0]),Number(arr[1]) - 1,Number(arr[2]));
}

function resolveCharacterDate(date,param){
	if(!date){
		return "";
	}
	var dateTime = date +"";
	dateTime = dateTime.replace("-","/").replace("-","/");
	dateTime = dateTime.replace("T"," ");
	dateTime = dateTime.substring(0, 19); 
	//alert(dateTime);
	console.log("---"+ dateTime);
	return new Date(dateTime).format(param);
}

function resolveStringDate(date){
	if(!date){
		return "";
	}
	var dateTime = date +"";
	dateTime = dateTime.replace("-","/").replace("-","/");
	dateTime = dateTime.replace("T"," ");
	dateTime = dateTime.substring(0, 19);
	//alert(dateTime);
	console.log("---"+ dateTime);
	return new Date(dateTime)
}

/***
 * @author William
 * @category 数组的简单排序，通过快速排序算法实现
 * @since 2012-03-13
 * ***/
Array.prototype.sortAsc = function(){
	_sort = function(arr,left,right){
		var i = left,j=right,keyValue=arr[left],temp;
		while (i <= j) {
			while (arr[i] < keyValue) {
				i++;
			}
			while (arr[j] > keyValue) {
				j--;
			}
			if (i <= j) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		if (left < j) {
			_sort(arr, left, j);
		}
		if (i < right) {
			_sort(arr, i, right);
		}
	}
	_sort(this,0,this.length-1);
}
/**
 * @author William
 * @category 获取dom对象
 * @param id1,id2,id3......
 * @since 2012-03-29
 **/
function $O() {
  var elements = new Array();
  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == 'string')
      element = document.getElementById(element);
    if (arguments.length == 1)
      return element;
    elements.push(element);
  }
  return elements;
}

function $G(id){
	return $("#" + id.replace(/\./g, "\\.").replace("[","\\[").replace("]","\\]"));
};

/**
 * @author wslun
 * 日期合法性验证：判断dataStr是否符合formatStr指定的日期格式
 * 示例：
 * (1)alert(Date.isValiDate('2008-02-29','yyyy-MM-dd'));//true
 * (2)alert(Date.isValiDate('aaaa-58-29','yyyy-MM-dd'));//false
 * @param dateStr：必选，日期字符串
 * @param formatStr：可选，格式字符串，可选格式有：(1)yyyy-MM-dd(默认格式)或YYYY-MM-DD (2)yyyy/MM/dd或YYYY/MM/DD (3)MM-dd-yyyy或MM-DD-YYYY (4)MM/dd/yyyy或MM/DD/YYYY
 * @since 2012-11-26 
 */
function dateIsValiDate (dateStr, formatStr)
{
    if(!dateStr){
        return false;
    }
    if(!formatStr){
        formatStr = "yyyy-MM-dd";//默认格式：yyyy-MM-dd
    }
    if(dateStr.length!=formatStr.length){
        return false;
    }else{
        if(formatStr=="yyyy-MM-dd"||formatStr=="YYYY-MM-DD"){
            var r1=/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
        	return r1.test(dateStr);
        }else if(formatStr=="yyyy/MM/dd"||formatStr=="YYYY/MM/DD"){
            var r2=/((^((1[8-9]\d{2})|([2-9]\d{3}))(\/)(10|12|0?[13578])(\/)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(\/)(11|0?[469])(\/)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(\/)(0?2)(\/)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(\/)(29)$)|(^([3579][26]00)(\/)(0?2)(\/)(29)$)|(^([1][89][0][48])(\/)(0?2)(\/)(29)$)|(^([2-9][0-9][0][48])(\/)(0?2)(\/)(29)$)|(^([1][89][2468][048])(\/)(0?2)(\/)(29)$)|(^([2-9][0-9][2468][048])(\/)(0?2)(\/)(29)$)|(^([1][89][13579][26])(\/)(0?2)(\/)(29)$)|(^([2-9][0-9][13579][26])(\/)(0?2)(\/)(29)$))/;
        	return r2.test(dateStr);
        }else{
            return false;
        }
    }
    return false;
}

/**
 * @author wlsun
 * 日期和时间合法性验证
 * 格式：yyyy-MM-dd hh:mm:ss
 * @param dateTimeStr 必选，日期字符串
 * @since 2012-11-26 
 */
function dateIsValiDateTime (dateTimeStr)
{
    //var dateTimeReg=/^(((((([02468][048])|([13579][26]))(00))|(\d{2}(([02468][48])|([13579][26]))))\-((((0[13578])|(1[02]))\-(([0-2][0-9])|(3[01])))|(((0[469])|(11))\-(([0-2][0-9])|(30)))|(02\-([0-2][0-9]))))|(\d{2}(([02468][1235679])|([13579][01345789]))\-((((0[13578])|(1[02]))\-(([0-2][0-9])|(3[01])))|(((0[469])|(11))\-(([0-2][0-9])|(30)))|(02\-(([0-1][0-9])|(2[0-8]))))))(\s{1}(([0-1][0-9])|(2[0-3]))\:([0-5][0-9])\:([0-5][0-9]))?$/;
    var dateTimeReg=/^(?:(?:(?:(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(\/|-)(?:0?2\1(?:29)))|(?:(?:(?:1[6-9]|[2-9]\d)?\d{2})(\/|-)(?:(?:(?:0?[13578]|1[02])\2(?:31))|(?:(?:0?[1,3-9]|1[0-2])\2(29|30))|(?:(?:0?[1-9])|(?:1[0-2]))\2(?:0?[1-9]|1\d|2[0-8])))))\s(?:([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d)$/;
	return dateTimeReg.test(dateTimeStr);
}

function sharedMethod(obj,value){
	var flag=true;
	if($.trim(value)!=''){
		if(value.length<=10){
			if(!dateIsValiDate(value,""))
			{
				$.msg.alert("系统提示","请重新填写正确日期!</br>默认格式如下: yyyy-MM-dd",function(){
					obj.focus();
				});
				flag=false;
			}
		}else{
			if(!dateIsValiDateTime(value))
			{
				$.msg.alert("系统提示","请重新填写正确日期!</br>默认格式如下: yyyy-MM-dd hh:mm:ss",function(){
					obj.focus();
				});
				flag=false;
			}
		}
	}
	return flag;
}

function date2Day(newDate, oldDate){
	if(newDate == "" || oldDate == "") return 0;
	var nd = newDate.split("-"), n_year = nd[0], n_month = nd[1], n_day = nd[2].split(" ")[0];
	var od = oldDate.split("-"),o_year = od[0], o_month = od[1], o_day = od[2].split(" ")[0];
	var re_day = (new Date(n_year,n_month,n_day).getTime() - new Date(o_year,o_month,o_day).getTime()) / (1 * 24 * 60 * 60 * 1000);
	return re_day;
}

function validationDateDiff(beginDateId, endDateId){
	if(!sharedMethod($O(beginDateId), $O(beginDateId).value)) return false;
	if(!sharedMethod($O(endDateId), $O(endDateId).value)) return false;
	var beginDateVal = $("#" + beginDateId).attr("value");
	var endDateVal = $("#" + endDateId).attr("value");
	if(isNotBlank(beginDateVal) && isNotBlank(endDateVal)){
		var diffDay = date2Day(endDateVal, beginDateVal) / 365;
		if(diffDay < 0){
			$.msg.alert("系统提示","查询起期不能大于查询止期!");
			return false;
		}
	}
//	else{
//		$.msg.alert("系统提示","查询起止日期必须输入或全为空!");
//		return false;
//	}
	return true;
}

function dateAddYear(date,interval){
	var year = parseInt(date.format("yyyy"));
	var target = year + interval;
	var newDateStr = date.format("yyyy-MM-dd hh:mm:ss");
	newDateStr = "" + target + date.format("yyyy-MM-dd hh:mm:ss").substring(4);
	return Str2Date(newDateStr);
}
//yyyy-MM-dd hh:mm:ss格式的日期字符串的分钟处理为半点、整点
function getRoundDateStr(dateStr0){
	var date = Str2Date(dateStr0);
	var dateStr = date.format("yyyy-MM-dd hh:mm:ss");
	var chooseTime = date.getTime();
	var minute = parseInt(dateStr.substring(14,16));
	if(0 < minute && minute <=30){
		minute = 30 - minute;
	}else if(minute > 30){
		minute = 60 - minute;
	}
	chooseTime = chooseTime + 1000 * 60 * minute;
	date = new Date(chooseTime);
	return date.format("yyyy-MM-dd hh:mm:ss");
}

/**
 * 管家任务时间校验
 * 1.usertime格式：yyyy-MM-dd hh:mm:ss字符串
 * 2.usertime不能为空
 * 3.usertime必须大于当前时间
 * 4.usertime所选时间不能大于两年
 * 5.usertime必须在8:00-20:00之间
 * showErrInfoId:展示错误信息的区域id
 * 
 */
function validateDateForButler(usetime,showErrInfoId){
	if($.trim(usetime) ==""){
		//alert("请选择时间");
		$("#"+showErrInfoId).html("请选择时间");
		return false;
	}
	var newDate = Str2Date(usetime);
	var chooseTime = newDate.getTime();
	var currDate = new Date();
	var max = dateAddYear(currDate,2).getTime();
	if(chooseTime<currDate.getTime()){
		//alert("计划时间不能小于当前时间");//所选时间必须大于当前时间
		$("#"+showErrInfoId).html("计划时间不能小于当前时间");
		return false;
	}
	//判断 时间必须在8:00-20:00之间
	if(!validateTime(newDate,showErrInfoId)){
		return false;
	}
	
	if(chooseTime >= max){
		//alert("计划时间不能大于两年");//计划时间不能小于当前时间
		$("#"+showErrInfoId).html("计划时间不能大于两年");
		return false;
	}
	return true;
}


/**
 * 判断 时间必须在8:00-20:00之间
 * usetime:date类型
 * showErrInfoId：展示错误信息标签的id
 */
function validateTime(usetime,showErrInfoId){
	var hours = usetime.getHours();
	var minutes = usetime.getMinutes();
	var min = 8 * 60 * 60 * 1000;
	var max = 20 * 60 * 60 * 1000;
	var curr = (hours + minutes/60) * 60 * 60 * 1000;
	if((min > curr) || (max < curr)){
		$("#"+showErrInfoId).html("所选时间必须在管家工作时间（8:00-20:00）内");
		return false;
	}
	return true;
}

function validateDate(usetime,showErrInfoId){
	if($.trim(usetime) ==""){
		//alert("请选择时间");
		$("#"+showErrInfoId).html("请选择时间");
		return false;
	}
	var newDate = Str2Date(usetime);
	var chooseTime = newDate.getTime();
	var currDate = new Date();
	if(chooseTime<currDate.getTime()){
		//alert("计划时间不能小于当前时间");//所选时间必须大于当前时间
		$("#"+showErrInfoId).html("计划时间不能小于当前时间");
		return false;
	}
	return true;
}

/**
 * 用户发起用车、取车、充电时时间校验
 * 1.usertime格式：yyyy-MM-dd hh:mm:ss字符串
 * 2.usertime不能为空
 * 3.usertime必须大于当前时间
 * 4.usertime所选时间不能大于两年
 * 5.usertime必须在8:00-20:00之间
 * showErrInfoId:展示错误信息的区域id
 * 
 */
function validateDateForUser(usetime,showErrInfoId){
	if($.trim(usetime) ==""){
		//alert("请选择时间");
		$("#"+showErrInfoId).html("请选择时间");
		return false;
	}
	var newDate = Str2Date(usetime);
	var chooseTime = newDate.getTime();
	var currDate = new Date();
	if(chooseTime<currDate.getTime()){
		//alert("计划时间不能小于当前时间");//所选时间必须大于当前时间
		$("#"+showErrInfoId).html("计划时间不能小于当前时间");
		return false;
	}
	//判断 时间必须在8:00-20:00之间
	var hours = newDate.getHours();
	var minutes = newDate.getMinutes();
	var min1 = 8 * 60 * 60 * 1000;
	var max1 = 20 * 60 * 60 * 1000;
	var curr1 = (hours + minutes/60) * 60 * 60 * 1000;
	
	if((min1 > curr1) || (max1 < curr1)){//时间在8:00-20:00内
		$("#"+showErrInfoId).html("服务时间仅支持8:00-20:00，且需提前两小时预约");
		return false;
	}
	
	var chooseDateStr = newDate.format("yyyy-MM-dd");
	var currDateStr = currDate.format("yyyy-MM-dd");
	var interval = chooseTime - currDate.getTime();
	//必须至少提前两个小时
	if(chooseDateStr == currDateStr && interval < 7200000 ){
		$("#"+showErrInfoId).html("服务时间仅支持8:00-20:00，且需提前两小时预约");
		return false;
	}
	var max = dateAddYear(currDate,2).getTime();
	if(chooseTime >= max){
		//alert("计划时间不能大于两年");//计划时间不能小于当前时间
		$("#"+showErrInfoId).html("计划时间不能大于两年");
		return false;
	}
	return true;
}

function Str2Date(dateStr){
	var regEx = new RegExp("\\-","gi");
	dateStr=dateStr.replace(regEx,"/");
	return new Date(dateStr);
}

//获取最近的整点/半点
function getRoundDateTime(){
	var date = new Date();
	var dateStr = date.format("yyyy-MM-dd hh:mm:ss");
	var chooseTime = date.getTime();
	var minute = parseInt(dateStr.substring(14,16));
	if(0 < minute && minute <30){
		minute = 30 - minute;
	}else if(minute > 30){
		minute = 60 - minute;
	}
	chooseTime = chooseTime + 1000 * 60 * minute;
	return new Date(chooseTime);
}
//为 input datetime-local设置最大最小时间
function setMinMaxTime(id){
	var min = getRoundDateTime();
	var max = dateAddYear(min,2);
	var hours = min.getHours();
	if(hours > 20){
		var minMilliseconds = min.getTime() - ((min.getHours() + min.getMinutes()/60) * 60 * 60 * 1000) + 8 * 60 * 60 * 1000;
		min = new Date(minMilliseconds);
	}
	var minStr = min.format("yyyy-MM-dd hh:mm:ss");
	minStr = minStr.replace(" ","T").substring(0,16);
	var maxStr = max.format("yyyy-MM-dd");
	maxStr = maxStr + "T20:00:00"
	$("#"+id).attr('min',minStr);
	$("#"+id).attr('max',maxStr);
}

function addDate(date,days){ 
	date.setDate(date.getDate()+days); 
	return date;
}

function addDate2Str(date,i){
	return addDate(date,i).format("yyyy-MM-dd");
}

function getAllDateForUser(){
	var data = "";
	var currDate = getRoundDateTime();
	var maxDate = dateAddYear(currDate,2);
	
	//判断 时间必须在8:00-20:00之间
	var hours = currDate.getHours();
	var minutes = currDate.getMinutes();
	var min1 = 8 * 60 * 60 * 1000;
	var max1 = 20 * 60 * 60 * 1000;
	var curTime = hours + minutes/60;
	if(curTime > 18){
		//currDate = addDate(currDate,1);//如果当前时间大于18点，按照提前两个小时规则，只能选择次日时间
	}else/*  if(curTime >= 6) */{
		//设置当天可显示的剩余时间
		data += "<li><span>"+currDate.format("yyyy-MM-dd")+"</span><ul>";
		var temp = "";
		if(curTime >= 6){
			hours = hours + 2;
			curTime = curTime +2;
			if(curTime > hours){//半点
    			temp = "<li>"+hours+":30</li>"; 
    			hours++;
    		}
		}else{
			hours = 8;
		}
		data += temp;
		for(var i = hours;i < 20;i++){
			data += "<li>"+i+":00</li><li>"+i+":30</li>";
		}
		data += "<li>20:00</li></ul></li>";
		//currDate = addDate(currDate,1);
	}
	var interval = dateDiff(currDate.format("yyyy-MM-dd"),maxDate.format("yyyy-MM-dd"));
	var timeList = "<ul>";
	for(var i = 8;i < 20;i++){
		timeList += "<li>"+i+":00</li> <li>"+i+":30</li>";
	}
	timeList += "<li>20:00</li></ul>";
	//currDate = Str2Date(currDate.format("yyyy-MM-dd"));
	//var dateList = "";
	//alert(Str2Date(currDate.format("yyyy-MM-dd"))+"\n"+data+".....");
	for(var i = 0;i < interval;i++){
		var tempDateTime = "<li><span>";
		//var tempDate = addDate2Str(currDate,i);
		var tempDateStr = addDate2Str(currDate,1);
		tempDateTime += tempDateStr +"</span>"+timeList+"</li>";
		data += tempDateTime;
		//dateList += tempDateStr + "<br>";
	}
	//$("#test").html(dateList);
	return data;
}

function getAllDateForButler(){
	var data = "";
	var currDate = getRoundDateTime();
	var maxDate = dateAddYear(currDate,2);
	
	//判断 时间必须在8:00-20:00之间
	var hours = currDate.getHours();
	var minutes = currDate.getMinutes();
	var min1 = 8 * 60 * 60 * 1000;
	var max1 = 20 * 60 * 60 * 1000;
	var curTime = hours + minutes/60;
	if(curTime >= 20){
		//currDate = addDate(currDate,1);//如果当前时间大于18点，按照提前两个小时规则，只能选择次日时间
	}else{
		//设置当天可显示的剩余时间
		data += "<li><span>"+currDate.format("yyyy-MM-dd")+"</span><ul>";
		var temp = "";
		if(curTime >= 8){
			if(curTime > hours){//半点
    			temp = "<li>"+hours+":30</li>"; 
    			hours++;
    		}
		}else{
			hours = 8;
		}
		data += temp;
		for(var i = hours;i < 20;i++){
			data += "<li>"+i+":00</li><li>"+i+":30</li>";
		}
		data += "<li>20:00</li></ul></li>";
		//currDate = addDate(currDate,1);
	}
	var interval = dateDiff(currDate.format("yyyy-MM-dd"),maxDate.format("yyyy-MM-dd"));
	var timeList = "<ul>";
	for(var i = 8;i < 20;i++){
		timeList += "<li>"+i+":00</li> <li>"+i+":30</li>";
	}
	timeList += "<li>20:00</li></ul>";
	//currDate = Str2Date(currDate.format("yyyy-MM-dd"));
	//var dateList = "";
	//alert(Str2Date(currDate.format("yyyy-MM-dd"))+"\n"+data+".....");
	for(var i = 0;i < interval;i++){
		var tempDateTime = "<li><span>";
		//var tempDate = addDate2Str(currDate,i);
		var tempDateStr = addDate2Str(currDate,1);
		tempDateTime += tempDateStr +"</span>"+timeList+"</li>";
		data += tempDateTime;
		//dateList += tempDateStr + "<br>";
	}
	//$("#test").html(dateList);
	return data;
}
