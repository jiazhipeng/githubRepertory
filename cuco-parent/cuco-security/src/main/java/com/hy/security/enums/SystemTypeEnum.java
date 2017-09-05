package com.hy.security.enums;

/**
 * Created by WangShuai on 2016/7/22.
 */
public enum SystemTypeEnum {

    main("main","权限系统"),
    sub("sub","子系统")
    ;

    private String value;
    private String message;

    private SystemTypeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
