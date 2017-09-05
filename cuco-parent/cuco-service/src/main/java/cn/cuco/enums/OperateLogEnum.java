package cn.cuco.enums;

/**
* @ClassName: Validate 
* @Description: 是否有效枚举
* @author huanghua
* @date 2017年2月21日 下午4:18:44
 */
public enum OperateLogEnum {
	USER(1,"用户"),
	ORDER_RENEWAL(2,"续费订单"),
	TASK(3,"任务"),
	CARPORT(4,"车库"),
	CAR_ACCIDENT(5,"出险"),
	CAR_TYPE(6,"车型"),
	REPAIR(7,"维修"),
	PARKING(8,"停车场"),
	CAR(9,"车辆"),
	INSURANCE(10,"保险"),
	OPERATE_SETTING(11,"参数"),
	MEMBER_CARPORT(12,"用户车库"),
	ORDER_CARPORT(13,"解锁订单"),
	PAYMENT(14,"支付"),
	PRE_AUTHORIZED_DEBIT(15,"预授权"),
	CAR_VIOLATION(16,"车辆违章"),
	ORDER_MEMBER_USE_CAR(17,"用户用车订单"),
	MEMBER_USE_CAR(18,"用户用车")//订单里的用车状态
    ;
	
    private Integer value;
    private String display;

    OperateLogEnum(Integer value, String display) {
        this.value = value;
        this.display = display;
    }

    public static OperateLogEnum[] getAll(){
        return OperateLogEnum.values();
    }

    public static OperateLogEnum fromValue(Integer value){
        OperateLogEnum[] all = getAll();
        for(OperateLogEnum temp : all){
            if(temp.getValue().equals(value)){
                return temp;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }
}
