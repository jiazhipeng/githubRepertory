package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-12 10:36:30
 */
public class CarOperateLog implements Serializable {

    private static final long serialVersionUID = 6765361174708751L;

    private Long id;//主键

    private String operateNum;//车辆运营编号(类似订单号，平台内部使用)

    private Date created;//创建时间

    private Long memberId;//会员ID

    private String memberName;//会员姓名

    private Integer type;//用车类型  -1:已排产；0:已落地未启用；1:已分配待使用；2:会员使用中；3:暂停中；4:待运营；5:平台运营中；6:闲置中；

    private BigDecimal takein;//当日运营收益

    private String operateId;//车管家ID

    private String operateName;//车管家姓名

    private String operateTypeId;//平台运营类型id

    private String operateTypeName;//平台运营类型名称(如:专车、婚庆、短租等)

    private String remark;//备注

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateNum() {
        return this.operateNum;
    }

    public void setOperateNum(String operateNum) {
        this.operateNum = operateNum;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getTakein() {
        return this.takein;
    }

    public void setTakein(BigDecimal takein) {
        this.takein = takein;
    }

    public String getOperateId() {
        return this.operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getOperateName() {
        return this.operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateTypeId() {
        return this.operateTypeId;
    }

    public void setOperateTypeId(String operateTypeId) {
        this.operateTypeId = operateTypeId;
    }

    public String getOperateTypeName() {
        return this.operateTypeName;
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
