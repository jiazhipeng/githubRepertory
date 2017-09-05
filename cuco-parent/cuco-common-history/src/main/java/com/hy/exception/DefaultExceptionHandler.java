package com.hy.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.hy.common.utils.LogUtil;
import com.hy.enums.ServerStatus;
import com.hy.httpservice.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by WangShuai on 2016/7/27.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);

        ResponseBody responseBody = null;
        if(e instanceof RuntimeExceptionWarn){
            responseBody = ((RuntimeExceptionWarn) e).getResponseBody();
            jsonView.setAttributesMap(responseBody.toMap());

            return modelAndView;
        }

        LogUtil.getLogger().error("服务器异常：",e);
        responseBody = new ResponseBody(ServerStatus.UNKNOW_ERROR.getCode(),ServerStatus.UNKNOW_ERROR.getMessage());
        jsonView.setAttributesMap(responseBody.toMap());
        return modelAndView;
    }
}
