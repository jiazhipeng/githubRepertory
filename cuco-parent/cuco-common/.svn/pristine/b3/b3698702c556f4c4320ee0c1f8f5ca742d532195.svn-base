package cn.cuco.enums;

/**
 * Created by WangShuai on 2016/7/26.
 */
public enum ServerStatus {
    SUCCESS("0000","成功"),

    UNKNOW_ERROR("1000","服务器繁忙"),
    USER_DEFINITION("1002",""),
    USER_REGISTER("1003","用户未注册"),
    MENU_NAME_REPEAT("1004","菜单名称重复"),
    QRCODE_INVALID("1005","二维码失效"),
    QRCODE_CHECKING("1006","扫描完成，正在处理"),
    USER_TOKEN_FAILURE("1007","用户token失效"),
    ADD_RECORD_EXIST("1008","记录已经存在，无法重复添加"),
    /*2xxx通用code*/
    PARAM_NOT_NULL("2001","参数不能为空"),
    RESOURCE_NOT_EXISTS("2002","资源不存在"),
    PARAM_NOT_EMPTY("2003","参数不能为空"),
    ACCESS_TOKEN_INVALID("2004","无效的AccessToken"),
    RESOURCE_EXISTS("2005","资源不可重复"),
    PARAM_LIMIT_LENGTH("2006","参数不能超出长度")
    ;

    private String code;

    private String message;

    ServerStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
