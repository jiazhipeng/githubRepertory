package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-27 16:30:17
 */
public class CarUsedLog implements Serializable {

    private static final long serialVersionUID = 3104247493529451L;

    private Long id;//车辆日志ID

    private Long carId;//车辆ID

    private String carPlateNum;//车牌号

    private Integer type;//车辆运营类型（0：闲置；1：会员使用中；2：非会员使用中；3：维修中；4：整备中；5：失联中；6：其他）

    private String driver;//行驶人

    private BigDecimal drivingMileage;//行驶公里数（km）

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private Date created;//创建时间
    
    private String queryDateStr;//用于查询，传入要查询的天，格式是yyyy-MM-dd

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public BigDecimal getDrivingMileage() {
        return this.drivingMileage;
    }

    public void setDrivingMileage(BigDecimal drivingMileage) {
        this.drivingMileage = drivingMileage;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public String getQueryDateStr() {
		return queryDateStr;
	}

	public void setQueryDateStr(String queryDateStr) {
		this.queryDateStr = queryDateStr;
	}

}
