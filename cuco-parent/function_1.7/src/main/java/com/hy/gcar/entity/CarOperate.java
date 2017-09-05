package com.hy.gcar.entity;

import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-12 10:36:30
 */
public class CarOperate implements Serializable {

    private static final long serialVersionUID = 3694154312600639L;

    private Long id;//

    private String carColor;//车辆颜色

    private String imageUrl;//车辆图片地址

    private String carPlateNum;//车牌号码

    private String carVin;//车架号

    private String cityId;//车辆所属城市ID

    private String cityName;//车辆所属城市名称

    private String operateNum;//车辆运营编号(类似订单号，平台内部使用)

    private Integer status;//车辆运营状态 1:待分配； 2:会员待使用；3：平台待使用；4:会员使用中；5：平台使用中；6：维修中；7：整备中',

    private String statusInfo;
    
    private Date useCarStartTime;
    
    private Integer readyHour;

    /**
     * 运营状态  --供查询使用
     */
    private Integer operatePlanStatus;


    private Long operateTypeId;//平台运营类型id

    private String operateTypeName;//平台运营类型名称(如:专车、婚庆、短租等)

    private String carBrand;//车辆品牌

    private Integer valid;//数据有效性 0:无效；1:有效；

    private String carType;//车型

    private String drivingKm;//行驶公里

    private Date manufactureTime;//出厂日期

    private String carQrcodeUrl;//车辆二维码URL

    private String engineNum;//发动机号

    private Date created;//
    private Long carTypeId;//车型id
    
    /**
     * 开始整备时间
     */
    private Date readyTime;
    
    /**
     * 故障时间 
     */
    private Date failureTime;

    /**
     * 运营状态
     */
    private String operatePlanStatusInfo;


    /**
     * 使用方 --供查询使用
     */
    private String operateTo;
    
    /**
     * 预约用车 开始时间 --供查询使用
     */
    private Date started;
    
    /**
     * 开始时间 --String
     */
    private String startedStr;

    /**
     * 结束时间 供返回使用
     */
    private Date endTime;
    
    /**
     * 管家姓名  --供查询使用
     */
    private String butlerName;
    
    /**
     * 会员姓名 --共查询使用
     */
    private String memberName;


    private String carPlanStatusInfo;// td_car_operate_plan 运营状态 0:待执行；1:执行中；2:已完成；3:已取消；4:已终止；
    /**
     * td_car_operate_plan 车辆运营状态 1:待分配； 2:会员待使用；3：平台待使用；4:会员使用中；5：平台使用中；6：维修中；7：整备中',
     */
    private String operateStatusInfo;

    /**
     * 限号  号码
     */
    private List<String> limitNumber;
    
    /**
     * 车辆归属id（某租赁公司）
     */
    private String carIgnedtoId;

    //是否限行 true 限行  false 不限行
    private boolean isLimitLine = false;
    /**
     * 车辆归属名称（某租赁公司）
     */
    private String carIgnedtoName;
    
    public String getCarIgnedtoId(){
    	return carIgnedtoId;
    }
    
    public void setCarIgnedtoId(String carIgnedtoId){
    	this.carIgnedtoId = carIgnedtoId;
    }
    
    public String getCarIgnedtoName(){
    	return carIgnedtoName;
    }
    
    public void setCarIgnedtoName(String carIgnedtoName){
    	this.carIgnedtoName = carIgnedtoName;
    }
    
    public List<String> getLimitNumber(){
    	return limitNumber;
    }
    
    public void setLimitNumber(List<String> limitNumber){
    	this.limitNumber = limitNumber;
    }
    
    public Integer getReadyHour() {
		return readyHour;
	}

	public void setReadyHour(Integer readyHour) {
		this.readyHour = readyHour;
	}

	public Date getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Date readyTime) {
		this.readyTime = readyTime;
	}
    
    public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
    
    public String getButlerName() {
		return butlerName;
	}

	public void setButlerName(String butlerName) {
		this.butlerName = butlerName;
	}
    
    public String getStartedStr() {
        if(started == null){
            return "";
        }
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	return sdf.format(this.started);
	}

	public void setStartedStr(String startedStr) {
		this.startedStr = startedStr;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

	public String getOperateTo() {
		return operateTo;
	}

	public void setOperateTo(String operateTo) {
		this.operateTo = operateTo;
	}

	public Integer getOperatePlanStatus() {
		return operatePlanStatus;
	}

	public void setOperatePlanStatus(Integer operatePlanStatus) {
		this.operatePlanStatus = operatePlanStatus;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarColor() {
        return this.carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public String getCarVin() {
        return this.carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOperateNum() {
        return this.operateNum;
    }

    public void setOperateNum(String operateNum) {
        this.operateNum = operateNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOperateTypeId() {
        return this.operateTypeId;
    }

    public void setOperateTypeId(Long operateTypeId) {
        this.operateTypeId = operateTypeId;
    }

    public String getOperateTypeName() {
        return this.operateTypeName;
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDrivingKm() {
        return this.drivingKm;
    }

    public void setDrivingKm(String drivingKm) {
        this.drivingKm = drivingKm;
    }

    public Date getManufactureTime() {
        return this.manufactureTime;
    }

    public void setManufactureTime(Date manufactureTime) {
        this.manufactureTime = manufactureTime;
    }

    public String getCarQrcodeUrl() {
        return this.carQrcodeUrl;
    }

    public void setCarQrcodeUrl(String carQrcodeUrl) {
        this.carQrcodeUrl = carQrcodeUrl;
    }

    public String getEngineNum() {
        return this.engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public String getStatusInfo() {
        return CarOperateEnum.status.getKey(this.status);
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getOperatePlanStatusInfo() {
        if(this.operatePlanStatus == null){
            return "";
        }
        return CarOperateEnum.status.getKey(this.operatePlanStatus);
    }

    public void setOperatePlanStatusInfo(String operatePlanStatusInfo) {
        this.operatePlanStatusInfo = operatePlanStatusInfo;
    }

    public String getCarPlanStatusInfo() {
        return carPlanStatusInfo;
    }

    public void setCarPlanStatusInfo(String carPlanStatusInfo) {
        this.carPlanStatusInfo = carPlanStatusInfo;
    }

    public String getOperateStatusInfo() {
        return operateStatusInfo;
    }

    public void setOperateStatusInfo(String operateStatusInfo) {
        this.operateStatusInfo = operateStatusInfo;
    }

	public Date getUseCarStartTime() {
		return useCarStartTime;
	}

	public void setUseCarStartTime(Date useCarStartTime) {
		this.useCarStartTime = useCarStartTime;
	}

    public boolean getIsLimitLine() {
        return isLimitLine;
    }

    public void setIsLimitLine(boolean limitLine) {
        isLimitLine = limitLine;
    }
}
