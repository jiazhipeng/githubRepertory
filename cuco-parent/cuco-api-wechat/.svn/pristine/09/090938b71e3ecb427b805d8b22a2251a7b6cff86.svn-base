package cn.cuco.controller.entity;

import cn.cuco.controller.enums.StatisticsCycleEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

/**
 * @ClassName:
 * @Description：
 * @author：WS
 * @date：2017年03月02 14:47:00
 */
public class CarStatisticsVO {

    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    private Date endTime;

    private StatisticsCycleEnum cycle;

    private Long carportId;

    private Long carSupplierId;

    private Long carTypeId;


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

    public StatisticsCycleEnum getCycle() {
        return cycle;
    }

    public void setCycle(StatisticsCycleEnum cycle) {
        this.cycle = cycle;
    }

    public Long getCarportId() {
        return carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getCarSupplierId() {
        return carSupplierId;
    }

    public void setCarSupplierId(Long carSupplierId) {
        this.carSupplierId = carSupplierId;
    }

    public Long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }
}
