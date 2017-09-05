package com.hy.httpservice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class ResponseBody {

    private String code;

    private String message;

    private Object data;

    public ResponseBody() {
    }

    public ResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBody(String code, String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Map toMap(){
        Map map = new HashMap();
        map.put("code",this.getCode());
        map.put("message",this.getMessage());
        map.put("data",this.getData());

        return map;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
