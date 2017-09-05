package com.hy.httpservice;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by WangShuai on 2016/8/2.
 */
public class ResponseBodyView<E> {
    private String code;

    private String message;

    private E data;

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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
