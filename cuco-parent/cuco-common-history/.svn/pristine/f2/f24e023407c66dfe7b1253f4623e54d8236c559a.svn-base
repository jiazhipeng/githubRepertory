package com.hy.enums;

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
    USER_TOKEN_FAILURE("1007","无效的token"),
    ADD_RECORD_EXIST("1008","记录已经存在，无法重复添加"),
    SYSTEM_TOKEN_ISNULL("1018","系统登录token为空"),
    SYSTEM_TOKEN_FAILURE("1009","系统登录token失效"),
    USER_TOKEN_ISNULL("1010","用户token为空"),
    USER_SECURITY_ISEMPTY("1011","用户权限为空"),
    USER_LACKOF_SECURITY("1012","用户权限不足"),
    SYSTEM_OPER_SUCCESS("1013","操作成功"),
    USER_NO_ANY_SYSTEM_ACCESS("1014","用户无任何系统访问权限"),
    USER_NO_SYSTEM_ACCESS("1015","用户无系统访问权限"),
    USER_NOT_FOUND("1016","系统中没有该用户"),
    USER_LOGIN_TIMEOUT_FOUND("1017","登录超时，请重新登录"),
    SYSTEM_TIME_STAMP_ERROR("1020","系统时间戳错误"),
    SYSTEM_TOKEN_SIGN_ERROR("1021","系统签名错误"),
    /*2xxx通用code*/
    PARAM_NOT_NULL("2001","参数不能为空"),
    RESOURCE_NOT_EXISTS("2002","资源不存在"),
    PARAM_NOT_EMPTY("2003","参数不能为空"),
    ACCESS_TOKEN_INVALID("2004","无效的AccessToken"),
    RESOURCE_EXISTS("2005","资源不可重复"),
    PARAM_LIMIT_LENGTH("2006","参数不能超出长度"),
    /**************************app使用************************/
    APP_BALANCE_NOT_ENOUGH("8001","您余额不足，请及时续费"),
    APP_DEPOSIT_NOT_ENOUGH("8002","您的押金不满xx元，请及时续费"),
    APP_BALANCE_DEPOSIT_NOT_ENOUGH("8003","您的余额不足且押金不满XX元，请及时续费"),
    APP_USECAR_COMPLETED("8004","本次用车已结束"),
    APP_USER_ALREADY_FROZEN("8005","您已被冻结，请联系客服400-0520-952"),
    APP_USECAR_ALREADY_CONFIRM("8006","该用车信息已确认，如需修改请联系客服 400-0520-952"),
    APP_INFORMATION_IS_NOT_PERFECT("8007","信息未完善"),
    APP_STORE_OF_CARTYPE_NOT_ENOUGH("8008","当前选择的车型库存不足，请重新选择"),
    APP_PARKING_IS_USED("8009","该车位已发起用车"),
    APP_CAR_IS_LAUNCH_CHARGE("8010","已发起充电任务"),
    APP_CAN_NOT_USE_THE_CAR("1000","只有发起用车的人才可以进行还车"),
    APP_NOT_OPEN_THIS_CITY("1000","当前城市暂未开通，敬请期待"),
    APP_NOT_SUPPORT_USED_CAR_SIMULTANEOUSLY("1000","您不支持与发起人同时使用车辆"),
    APP_NOT_USE_CAR_WITHDRAWALS("1000","您正在提现中，不可发起用车"),
    APP_DELETE_PUBLIC_IDENTITY("1000","您已被删除共用人身份"),
    APP_NO_PARKING_SPACES("1000","您的车位已用完，还车后方可发起"),
    APP_INFORMATION_AUDIT("1000","您的信息正在审核中，审核通过后即可用车"),
    APP_CAN_NOT_CANCEL_THE_CAR("1000","只有发起用车的人才可以取消用车"),
    APP_CAPTCHA_INVALID("1000","验证码失效"),
    APP_CAPTCHA_ERROR("1000","验证码错误"),
    APP_MEMBER_NOT_EXISTED("1000","用户不存在"),
    APP_GETMONEY_PROCESSING("8101","已经发起提现，进入提现流程页"),
    APP_GETMONEY_ONLYMAIN("8102","只有主账号可发起提现"),
    APP_GETMONEY_NOTMONEY("8103","您没有可以提现的押金"),
    APP_GETMONEY_ONEDAY("8104","只有余额不足支付1天的使用费时，才可发起提现。"),
    APP_GETMONEY_AFTERTHIRTY("8105","押金提现需在用车完成后30天发起"),
    APP_GETMONEY_PLEASEADDCARD("8106","请添加银行卡"),
    
    APP_WECHATPAY_ERROR("1000",""),
    /**************************app使用************************/
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
