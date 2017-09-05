package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:08
 */
public class OrderLog implements Serializable {

    private static final long serialVersionUID = 8759872635156821L;

    private Long id;//主键

    private String orderNum;//订单编号(产品代码＋年月日＋4位随机数)(加唯一索引，代码中cach异常，捕获后重新调用生成编号，再插入)

    private Integer status;//订单状态 0:已取消；1:入会申请；2:待签约；3:待付款；4:待结款；5:已结款；

    private String modifierId;//操作人ID

    private String modifier;//操作人姓名

    private Date created;//操作时间

    private String remark;//备注信息

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
