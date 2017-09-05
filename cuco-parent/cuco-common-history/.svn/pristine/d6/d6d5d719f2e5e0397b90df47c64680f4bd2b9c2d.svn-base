package com.hy.runtime;

import com.hy.httpservice.RequestRouting;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangShuai on 2016/7/30.
 */
public class APIContext {

    public static List<RequestRouting> getAllReqMappings(ApplicationContext applicationContext){
        List<RequestRouting> requestRoutings = new ArrayList<RequestRouting>();
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                    try {
                        HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
                        RequestRouting requestRouting = RequestRouting.getReqRouting(handlerMethod);
                        if(requestRouting!=null){
                            requestRoutings.add(requestRouting);
                        }
                    }catch (Exception e){
                    }
                }
                continue;
            }
        }
        return requestRoutings;
    }
}
