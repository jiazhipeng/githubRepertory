package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 14:44:02
 */
public class Parking extends PageQuery  implements Serializable {

    private static final long serialVersionUID = 1808658315455248L;

    private Long id;//

    private String parkingName;//停车场名称

    private String parkingAddress;//停车场地址

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

    public String getParkingName() {
        return this.parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingAddress() {
        return this.parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
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