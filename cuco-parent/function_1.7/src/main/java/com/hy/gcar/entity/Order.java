package com.hy.gcar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:08
 */
public class Order implements Serializable {
	
	public enum OrderStatus{
		
		OrderStatus_1("待处理",1),
		OrderStatus_2("跟进中",2),
		OrderStatus_3("有效线索",3),
		OrderStatus_4("意向线索",4),
		OrderStatus_5("超时线索",5),
		OrderStatus_6("无效线索",6),
		OrderStatus_7("待审核",7),
		OrderStatus_8("待签约",8),
		OrderStatus_9("待付款",9),
		OrderStatus_10("待确认",10),
		OrderStatus_11("已付款",11),
		OrderStatus_12("已取消",12);
		
		private String name;

		private int index;

		OrderStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public int getIndex() {
			return index;
		}
		
		public static String getNames(Integer index) {
			if(index == null){
				return "";
			}
			for (OrderStatus status : OrderStatus.values()) {

				if (status.getIndex() == index) {
					return status.getName();
				}

			}
			return "";

		}

	}

    private static final long serialVersionUID = 1739807430873158L;

    private Long id;//主键

    private String orderNum;//订单编号(产品代码＋年月日＋4位随机数)(加唯一索引，代码中cach异常，捕获后重新调用生成编号，再插入)

    private Long memberId;//会员ID

    private String memberName;//会员姓名

    private String memberMobile;//会员手机号

    private Long itemId;//会员成长ID

    private String carType;//启用车型 json

    private Date created;//订单创建时间

    private Integer status;//订单状态 0:已取消；1:入会申请；2:待签约；3:待付款；4:待结款；5:已结款；6:升级申请；
    
    private String statusInfo;

    public String getStatusInfo() {
    	return OrderStatus.getNames(this.status);
	}

	private String modifierId;//订单负责人ID

    private String modifier;//订单负责人姓名

    private Date lasttimeModify;//最后修改时间

    private String remark;//备注

    private String operateNum;//车辆运营编号(类似订单号，平台内部使用)

    private BigDecimal total;//会员成长(套餐)金额

    private BigDecimal payment;//已付金额

    private String cityId;//服务城市ID

    private String cityName;//服务城市名称

    private Integer orderFrom;//订单来源 1:微信；2:安卓App；3:iOS App；4:线下；5:渠道；6:名片'

    private String carBrand;//车辆品牌

    private String applyIntention;//申请意向

    private Integer trialStatus;//审核结果 0:未通过；1:通过；默认0

    private BigDecimal paymentDeposit;//已付押金
    
    private  String  memberSex;
    
    private String loginId;
    
    private BigDecimal refundPrice;
    
    private Long couponId;
    
    private String couponName;
    
    private BigDecimal couponPrice;
    
    private String emergencyContact;//紧急联系人姓名
    
    private String relationship;//关系
    
    private String emergencyMobile;//紧急联系人电话
    
    private String address;//住址
    
    private Integer familyStatus;//家庭状况 0:有子女；1:无子女
    
    private Integer maritalStatus;//婚姻状况 0:未婚；1:已婚
    
    private String corporateName;//公司名称
    
    private String wechatNum;//微信号
    
    private String position;//职位
    
    private String qqcode;//QQ号码
    
    private Integer clueType;//线索类型 0:购买；1:升级；
    
    private Integer monthlyIncome;//月收入 0:<=10k;1:10k-30k;2:30k-50k;3:50k-100k;4:>=100;
    
    private Integer invalidReason;//无效原因 0:城市不匹配；1:对产品无兴趣；2:多次联系未果；3:接听拒访；4:空号/停机；5:人群不匹配；6:未接通/听；
    
    private Date communicationTime;//沟通时间
    
    private Date nextCommunicationTime;//下次沟通时间
    
    private Integer paymentType;//付款方式 0:现金；1:刷卡；2:转账；3:支票；4:支付宝；
    
    private Date paymentDate;//到账时间
    
    private Long orderFromId;//订单来源ID（渠道ID、名片ID）
    
    private String itemName;//套餐名称
    
    private String sureName;
    
    private Integer source;
    
    
    public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

    public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

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

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateNum() {
        return this.operateNum;
    }

    public void setOperateNum(String operateNum) {
        this.operateNum = operateNum;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
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

    public Integer getOrderFrom() {
        return this.orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getApplyIntention() {
        return this.applyIntention;
    }

    public void setApplyIntention(String applyIntention) {
        this.applyIntention = applyIntention;
    }

    public Integer getTrialStatus() {
        return this.trialStatus;
    }

    public void setTrialStatus(Integer trialStatus) {
        this.trialStatus = trialStatus;
    }

    public BigDecimal getPaymentDeposit() {
        return this.paymentDeposit;
    }

    public void setPaymentDeposit(BigDecimal paymentDeposit) {
        this.paymentDeposit = paymentDeposit;
    }

	public BigDecimal getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(Integer familyStatus) {
		this.familyStatus = familyStatus;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getQqcode() {
		return qqcode;
	}

	public void setQqcode(String qqcode) {
		this.qqcode = qqcode;
	}

	public Integer getClueType() {
		return clueType;
	}

	public void setClueType(Integer clueType) {
		this.clueType = clueType;
	}

	public Integer getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Integer monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(Integer invalidReason) {
		this.invalidReason = invalidReason;
	}

	public Date getCommunicationTime() {
		return communicationTime;
	}

	public void setCommunicationTime(Date communicationTime) {
		this.communicationTime = communicationTime;
	}

	public Date getNextCommunicationTime() {
		return nextCommunicationTime;
	}

	public void setNextCommunicationTime(Date nextCommunicationTime) {
		this.nextCommunicationTime = nextCommunicationTime;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getOrderFromId() {
		return orderFromId;
	}

	public void setOrderFromId(Long orderFromId) {
		this.orderFromId = orderFromId;
	}

}
