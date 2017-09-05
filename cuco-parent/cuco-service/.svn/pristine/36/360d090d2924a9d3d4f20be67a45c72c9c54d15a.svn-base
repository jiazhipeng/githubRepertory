package cn.cuco.enums;

/**
* @ClassName: Validate 
* @Description: 是否有效枚举
* @author huanghua
* @date 2017年2月21日 下午4:18:44
 */
public enum Valid {
	DOWN(0,"下架"),
	UP(1,"上架")
    ;

    private Integer value;
    private String display;

    Valid(Integer value, String display) {
        this.value = value;
        this.display = display;
    }

    public static Valid[] getAll(){
        return Valid.values();
    }

    public static Valid fromValue(Integer value){
        Valid[] all = getAll();
        for(Valid temp : all){
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
