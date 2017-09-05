package com.hy.httpservice;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.io.IOException;

/**
 * Created by WangShuai on 2016/7/28.
 */
public class DefaultMessageConverter extends MappingJackson2HttpMessageConverter {

    /**
     * 重写输出消息处理器，统一响应体结构
     * @param object
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if( !(object instanceof ResponseBody) ){
            object = ResponseUtil.toSuccessBody(object);
        }
        super.writeInternal(object, outputMessage);
    }
}
