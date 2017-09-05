package cn.cuco.controller;

import cn.cuco.common.utils.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Tracker
 * WangShuai created at 2017/03/11
 */
@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        LogUtils.getLogger().warn("Returning HTTP 400 Bad Request", e);
        throw e;
    }
}