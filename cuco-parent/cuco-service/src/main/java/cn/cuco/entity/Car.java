package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 09:57:49
 */
public class Car extends PageQuery<Car> implements Serializable {

    private static final long serialVersionUID = 1754319387357134L;

    private Long id;//车辆ID

    private String carPlateNum;//车牌号码

    private Long carBrandId;//车辆品牌ID

    private String carBrand;//车品牌

    private Long carTypeId;//车型ID

    private String carType;//车型

    private String carVin;//车架号

    private String carEngineNum;//发动机号

    private String carColor;//车辆颜色

    private Integer status;//车辆状态（0：待分配；1:已分配；2：会员使用中；3：非会员使用中；4：维修中；5：待整备；6：整备中；7：已失联）
    
    private Integer logType;//车辆运营类型（0：闲置；1：会员使用中；2：非会员使用中；3：维修中；4：整备中；5：失联中；6：其他）

    private Long cityId;//车辆所属城市ID

    private String cityName;//车辆所属城市名称

    private Long carSupplierId;//车辆所属供应商ID

    private String carSupplierName;//车辆所属供应商名称

    private BigDecimal carSupplyPrice;//车辆供给成本(元/月）

    private Date manufactureTime;//出厂时间

    private BigDecimal drivingMileage;//入库时行驶里程(KM）

    private BigDecimal totalMileage;//车辆当前里程（KM）

    private String travelLicenseUrl;//行驶证url

    private Integer gpsStatus;//车辆安装的GPS行车定位仪的状态（0：未接通；1：已接通）

    private String gpsNum;//GPS设备号

    private Long currentParkingId;//车辆当前停放的停车场ID

    private String currentParkingName;//车辆当前停放的停车场名称

    private String currentParkingSpace;//车辆当前停放的停车位

    private Date inspectionTime;//车辆年检时间

    private Date created;//车辆入库的时间
    
    private Date outTime;//车辆脱表时间
    
    private Date losedTime;//车辆失联时间
    
    private Date losedBackTime;//车辆失联找回时间
    
    private Date repairdStartTime;//车辆最近一次车辆开始维修时间
    
    private Date repairedEndTime;//车辆最近一次车辆维修结束时间

    private Long modifierId;//操作人ID

    private String modifier;//操作人名称

    private Date lasttimeModify;//最后修改时间

	private Integer valid;//数据有效性（ 0:无效；1:有效；）
	
	private Integer allocated;//是否被分配过，分配过不能修改（ 0:没分配过；1:分配过；）
	
	private BigDecimal preAuthorizationMoney;//用车预授权金额（元）
	
    //业务字段
    
    private String compulsoryInsuranceNum;//车辆所投保的交强险保单号

    private Date compulsoryInsuranceStart;//交强险开始时间

    private Date compulsoryInsuranceEnd;//交强险结束时间
    
    private String[] compulsoryInsuranceUrl;//强制险上传图片url

    private String businessInsuranceNum;//车辆所投保的商业险保单号

    private Date businessInsuranceStart;//车辆所投保的商业险保单开始时间

    private Date businessInsuranceEnd;//车辆所投保的商业险保单结束时间
    
    private String[] businessInsuranceUrl;//强制险上传图片url
    
    private Long carPortId;//车库ID
    
    private String restrictions;//车辆限号
    
    private Date startTime;//开始时间，用于创建时间检索
    
    private Date endTime;//结束时间，用于创建时间检索

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public Long getCarBrandId() {
        return this.carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Long getCarTypeId() {
        return this.carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarVin() {
        return this.carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public String getCarEngineNum() {
        return this.carEngineNum;
    }

    public void setCarEngineNum(String carEngineNum) {
        this.carEngineNum = carEngineNum;
    }

    public String getCarColor() {
        return this.carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCarSupplierId() {
        return this.carSupplierId;
    }

    public void setCarSupplierId(Long carSupplierId) {
        this.carSupplierId = carSupplierId;
    }

    public String getCarSupplierName() {
        return this.carSupplierName;
    }

    public void setCarSupplierName(String carSupplierName) {
        this.carSupplierName = carSupplierName;
    }

    public BigDecimal getCarSupplyPrice() {
        return this.carSupplyPrice;
    }

    public void setCarSupplyPrice(BigDecimal carSupplyPrice) {
        this.carSupplyPrice = carSupplyPrice;
    }

    public Date getManufactureTime() {
        return this.manufactureTime;
    }

    public void setManufactureTime(Date manufactureTime) {
        this.manufactureTime = manufactureTime;
    }

    public BigDecimal getDrivingMileage() {
        return this.drivingMileage;
    }

    public void setDrivingMileage(BigDecimal drivingMileage) {
        this.drivingMileage = drivingMileage;
    }

    public BigDecimal getTotalMileage() {
        return this.totalMileage;
    }

    public void setTotalMileage(BigDecimal totalMileage) {
        this.totalMileage = totalMileage;
    }

    public String getTravelLicenseUrl() {
        return this.travelLicenseUrl;
    }

    public void setTravelLicenseUrl(String travelLicenseUrl) {
        this.travelLicenseUrl = travelLicenseUrl;
    }

    public Integer getGpsStatus() {
        return this.gpsStatus;
    }

    public void setGpsStatus(Integer gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public String getGpsNum() {
        return this.gpsNum;
    }

    public void setGpsNum(String gpsNum) {
        this.gpsNum = gpsNum;
    }

    public Long getCurrentParkingId() {
        return this.currentParkingId;
    }

    public void setCurrentParkingId(Long currentParkingId) {
        this.currentParkingId = currentParkingId;
    }

    public String getCurrentParkingName() {
        return this.currentParkingName;
    }

    public void setCurrentParkingName(String currentParkingName) {
        this.currentParkingName = currentParkingName;
    }

    public String getCurrentParkingSpace() {
        return this.currentParkingSpace;
    }

    public void setCurrentParkingSpace(String currentParkingSpace) {
        this.currentParkingSpace = currentParkingSpace;
    }

    public Date getInspectionTime() {
        return this.inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
    
    public String getCompulsoryInsuranceNum() {
		return compulsoryInsuranceNum;
	}

	public void setCompulsoryInsuranceNum(String compulsoryInsuranceNum) {
		this.compulsoryInsuranceNum = compulsoryInsuranceNum;
	}

	public Date getCompulsoryInsuranceStart() {
		return compulsoryInsuranceStart;
	}

	public void setCompulsoryInsuranceStart(Date compulsoryInsuranceStart) {
		this.compulsoryInsuranceStart = compulsoryInsuranceStart;
	}

	public Date getCompulsoryInsuranceEnd() {
		return compulsoryInsuranceEnd;
	}

	public void setCompulsoryInsuranceEnd(Date compulsoryInsuranceEnd) {
		this.compulsoryInsuranceEnd = compulsoryInsuranceEnd;
	}

	public String getBusinessInsuranceNum() {
		return businessInsuranceNum;
	}

	public void setBusinessInsuranceNum(String businessInsuranceNum) {
		this.businessInsuranceNum = businessInsuranceNum;
	}

	public Date getBusinessInsuranceStart() {
		return businessInsuranceStart;
	}

	public void setBusinessInsuranceStart(Date businessInsuranceStart) {
		this.businessInsuranceStart = businessInsuranceStart;
	}

	public Date getBusinessInsuranceEnd() {
		return businessInsuranceEnd;
	}

	public void setBusinessInsuranceEnd(Date businessInsuranceEnd) {
		this.businessInsuranceEnd = businessInsuranceEnd;
	}

	public String[] getCompulsoryInsuranceUrl() {
		return compulsoryInsuranceUrl;
	}

	public void setCompulsoryInsuranceUrl(String[] compulsoryInsuranceUrl) {
		this.compulsoryInsuranceUrl = compulsoryInsuranceUrl;
	}

	public String[] getBusinessInsuranceUrl() {
		return businessInsuranceUrl;
	}

	public void setBusinessInsuranceUrl(String[] businessInsuranceUrl) {
		this.businessInsuranceUrl = businessInsuranceUrl;
	}

	public Integer getAllocated() {
		return allocated;
	}

	public void setAllocated(Integer allocated) {
		this.allocated = allocated;
	}

	public Long getCarPortId() {
		return carPortId;
	}

	public void setCarPortId(Long carPortId) {
		this.carPortId = carPortId;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public BigDecimal getPreAuthorizationMoney() {
		return preAuthorizationMoney;
	}

	public void setPreAuthorizationMoney(BigDecimal preAuthorizationMoney) {
		this.preAuthorizationMoney = preAuthorizationMoney;
	}

	public Date getLosedTime() {
		return losedTime;
	}

	public void setLosedTime(Date losedTime) {
		this.losedTime = losedTime;
	}

	public Date getLosedBackTime() {
		return losedBackTime;
	}

	public void setLosedBackTime(Date losedBackTime) {
		this.losedBackTime = losedBackTime;
	}

	public Date getRepairdStartTime() {
		return repairdStartTime;
	}

	public void setRepairdStartTime(Date repairdStartTime) {
		this.repairdStartTime = repairdStartTime;
	}

	public Date getRepairedEndTime() {
		return repairedEndTime;
	}

	public void setRepairedEndTime(Date repairedEndTime) {
		this.repairedEndTime = repairedEndTime;
	}

}