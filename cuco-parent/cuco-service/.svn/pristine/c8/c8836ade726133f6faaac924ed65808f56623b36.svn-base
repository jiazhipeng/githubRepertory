package cn.cuco.enums;

/**
* @ClassName: OperateSettingTypeEnum 
* @Description: 参数设置类型枚举
* @author huanghua
* @date 2017年2月23日 下午2:51:43
 */
public enum OperateSettingTypeEnum {
	//1：费用管理；2：参数管理
	COST(1,"费用管理"),
	PARAMETER(2,"参数管理")
    ;

    private Integer value;
    private String display;

    OperateSettingTypeEnum(Integer value, String display) {
        this.value = value;
        this.display = display;
    }

    public static OperateSettingTypeEnum[] getAll(){
        return OperateSettingTypeEnum.values();
    }

    public static OperateSettingTypeEnum fromValue(Integer value){
        OperateSettingTypeEnum[] all = getAll();
        for(OperateSettingTypeEnum temp : all){
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
