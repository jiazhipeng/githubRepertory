package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 14:44:02
 */
public class CarportCartype implements Serializable {

    private static final long serialVersionUID = 5099522274666173L;

    private Long id;//主键

    private Long carportId;//车库ID

    private Long carTypeId;//车型ID

    private String brand;//车辆品牌

    private String carType;//车辆型号

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人姓名

    private Long modifierId;//操作人ID

    private Integer valid;//是否删除 0：删除；1：未删除

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getCarTypeId() {
        return this.carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

}
