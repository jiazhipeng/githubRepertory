	function isNullVal(value){
		value = value.trim();
		if (value.length == 0 || value==""||value==null||typeof(value)=="undefined"){  
        	return true;
    	}
    	return false;
	} 

	//验证是否为空
	function validNullVal(cname,value){
		var message = "";
		if (value.length == 0 ){  
        	message = cname+"不允许为空！\n";
    	}
    	return message;
	} 
	
	//验证内容长度
	function validLength(cname,value,slen){
		var message = "";
    	if(value.replace(/[^\x00-\xff]/g,"**").length>slen){
    		message=cname+"最大长度不允许超过"+slen+"个字符("+slen/2+"个汉字)!";
		}
    	return message;
	}
	
	//验证是否是合法的邮编
	function validPostalCode(fcname,value){
		var message = "";
		if(isNullVal(value)){
			return message;
		}
		if (!isPostalCode(value)){
			message = fcname+"输入不合法！\n";
		}
		return message;
	}
	
	//验证电话传真号码是否有效
	function validTel(fcname,value){ 
		var message = "";
		if(isNullVal(value)){
			return message;
		}
		if (!isPhone(value)&&!isMobile(value)&&!isFax(value)){
			message = fcname+"输入格式不正确！\n";
		}
		return message; 
	}
	
	function validModel(fcname,value){ 
		var message = "";
		if(isNullVal(value)){
			return message;
		}
		if (!isMobile(value)){
			message = fcname+"输入格式不正确！\n";
		}
		return message; 
	}
	
	//验证email邮箱地址
	function validEmail(fcname,value){ 
		var message = "";
		if(isNullVal(value)){
			return message;
		}
		if (!isEmail(value)){
			message = fcname+"输入格式不正确！\n";
		}
		return message; 
	}
	
	//验证身份证号是否合法
	function validIDCard(fcname,value){
		var message = "";
		if(isNullVal(value)){
			return message;
		}
		if (!checkIDCard(value)){
			message = fcname+"输入不合法！\n";
		}
		return message;
	}
	
	function isPostalCode(value){//检测是否为合法邮编
		var patrn  =/^[0-9]{6}$/; 
		return patrn.exec(value);
	}
	
	function isPhone(value){//检测是否为固定电话号码
		var regex = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
   		return regex.test(value);
	}

	function isMobile(value){//检测是否为手机号码
		var regex = /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/;
   		return regex.test(value);
	}
	
	function isFax(value){//检测是否为传真号
		var regex = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
   		return regex.test(value);
	}
	
	function isEmail(value){//验证email邮箱地址	
		var res = /^[0-9a-zA-Z_\-\.]+@[0-9a-zA-Z_\-]+(\.[0-9a-zA-Z_\-]+)*$/; 
		var re = new RegExp(res); 
		return !(value.match(re) == null); 
	}
	
	function  checkIDCard(value){// 验证身份证号    
		//身份证正则表达式(15位)      
	    isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{2}[\0-9xX]{1}$/;    
    	//身份证17位   
    	isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}[0-9xX]{1}$/;   
       
    	if((isIDCard1.test(value)||isIDCard2.test(value))){   
        	return true;   
    	}   
    	return false;   
  	}
	
	//验证数值类型
	function validNumber(fcname,value){
		//验证是浮点数，并且小数点后保留2位数字
		var message = "";
		var st =value;
		if(st.substring(0,1)=="-"){
			st = st.substring(1,st.length);
		}
		if (st ==null || st ==""){
			return false;
		}
		var intVal = st;
		if(st.indexOf(".")!=-1){
			intVal = st.substring(0,st.indexOf("."));
			intVal = Math.abs(intVal);
		}
		if (isNaN(st)){
			message=fcname+"只允许输入数字型数据!";
		}
		return message;
	}
	
	//验证浮点数，并且验证整数与小数据保留位数
	function validFloat(fcname,value,intlen,plen){
		//验证是浮点数，并且小数点后保留2位数字
		var message = "";
		var st =value;
		if(st.substring(0,1)=="-"){
			st = st.substring(1,st.length);
		}
		if (st ==null || st ==""){
			return message;
		}
		var intVal = st;
		if(st.indexOf(".")!=-1){
			intVal = st.substring(0,st.indexOf("."));
			intVal = Math.abs(intVal);
		}
		if (isNaN(st)){
			message=fcname+"只允许输入数字型数据!";
			return message;
		}else if(intVal.length>intlen){
			message=fcname+"整数有效位数不允许超过"+intlen+"位!";
			return message;
		}
		
		var f = true;
		var ss = st.replace(/[^\.]/g,"");//获得小数点
		var sb = st.replace(/(\d*\.?)/,"");//获得小数点后数字
		if (ss.length<2){
			if (sb.length >plen){
				message=fcname+"小数点后只允许要保留"+plen+"位!";
				return message;
			}
		}  
		return message;
	}