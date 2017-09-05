package com.techstar.gcar.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hy.common.utils.JsonUtil;
import com.hy.common.utils.Reflections;
import com.hy.constant.Constant;
import com.hy.security.entity.Api;

import java.util.Arrays;

/**
 * Created by WangShuai on 2016/7/29.
 */
public class UtilTest {

    @org.junit.Test
    public void testPrePersist() throws JsonProcessingException {
        Api api = new Api();
    //    PrePersistUtil.onCreate(api);

//        Object object = Reflections.getFieldValue(api,"xxx");

        System.out.println(
                JsonUtil.getObjectMapper().writeValueAsString(api)
        );

        System.out.println(Arrays.asList(Constant.SYSTEM_TYPE_MAIN,Constant.SYSTEM_TYPE_SUB).contains(null));
    }
}
