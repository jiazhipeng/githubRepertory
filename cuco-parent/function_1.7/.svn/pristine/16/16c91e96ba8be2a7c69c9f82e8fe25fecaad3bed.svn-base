package com.hy.gcar.entity;



import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.hy.gcar.dao.JsonMapper;


public class OpenResponse implements Serializable {

    private static final long serialVersionUID = -398819668346187784L;
    private String isSuccess;
    private String errorMessage;
    private Object response;
    private String  code;
    
    public OpenResponse(){}
    
    public OpenResponse(String isSuccess,String errorMessage,Object response,String code){
    	if (response == null) {
    		response = "";
    	}    
    	//生成 空response
        if (errorMessage == null){
        	 errorMessage = "";    //生成 空errorMessage
        }
        String result = JsonMapper.getInstance().toJson(response);
        result = result.replaceAll(":\"null\"", ":\"\"");
        result = result.replaceAll(":null", ":\"\"");
        response = JSON.parse(result);
    	this.isSuccess = isSuccess;
    	this.errorMessage = errorMessage;
    	this.response = response;
    	this.code = code;
    }
    
    public String toJson() {
        setEmpty();
        String result = JsonMapper.getInstance().toJson(this);
        result = result.replaceAll("\"null\"", "\"\"");
        result = result.replaceAll("null", "\"\"");
        return result;
    }

       /**
     * 用于保持json xml输出空置时 内容一致
     */
    private void setEmpty() {
        if (response == null) response = "";    //生成 空response
        if (errorMessage == null) errorMessage = "";    //生成 空errorMessage
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
    
    public static void main(String[] args) {
		System.out.println(new Date().getTime());
		System.out.println("2015");
	}

}
