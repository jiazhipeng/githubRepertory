package cn.cuco.httpservice.runtime;

import java.text.SimpleDateFormat;

import cn.cuco.constant.DateConstant;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class HttpServiceDateFormat extends SimpleDateFormat {

    public HttpServiceDateFormat() {
         super(DateConstant.DEFAULT_DATE_FORMAT_PATTERN);
    }

}
