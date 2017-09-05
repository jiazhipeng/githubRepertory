package cn.cuco.controller.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import cn.cuco.entity.MemberInfo;
import cn.cuco.page.PageQuery;

/**
 * 
 * @author jiaxiaoxian
 * @since 1.0,2017-02-27 10:41:17
 */
public class MemberVO extends PageQuery<MemberVO> implements Serializable {

    private static final long serialVersionUID = 5530697938816824L;

    private Long id;//用户ID

    private String name;//用户昵称&姓名

    private String mobile;//手机号

    private Integer sex;//性别 -1未知;0:男;1:女;

    private Integer source;//用户来源 0:线下;1:App-Android;2:App-IOS;3:自主关注(微信);4:市场活动;5:渠道;6:推荐

    private Long sourceId;//来源ID

    private Integer statuts;//用户状态 0:冻结;1:正常;默认1

    private Long cityId;//城市id

    private String cityCode;//城市编码

    private String cityName;//城市名称

    private String riskAuditNum;//风控审核编号

    private Integer riskAuditStatus;//风控审核状态 0:未提交资料；1:系统审核通过；2：系统审核拒绝;3:等待人工审核;4:系统审核失败;5:人工审核通过;6:人工审核拒绝;默认0；

    private Date riskAuditTime;//风控审核时间
    
    private String nationality;//国籍

    private Long nationalityId;//国籍ID

    private Date created;//创建时间

    private Long modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间
    
    private MemberInfo memberInfo;//用户证件信息
    
    private String remark;//备注
    
    private Date lastUseCarBeginTime;//用车开始时间
    
    private Date lastUseCarEndTime;//用车结束时间
    
    private Date createdBegin;//注册开始时间
    
    private Date createdEnd;//注册结束时间
    
    private Date lastUseCarTime;//最后用车时间
    
    private Map<String,Integer> carports;//用户车库列表
    
    private BigDecimal balance;//余额
    
    private BigDecimal consumptionAmount;//累计消费金额
    
    private Integer useCarCount;//用车次数
    
    private Integer payCount;//充值次数
    
    private Integer preAuthorization;//预授权状态
    
    private String idcardFront;//身份证正面

    private String idcardBack;//身份证反面

    private String drivingLicenseOriginal;//驾驶证正本

    private String drivingLicenseCopy;//驾驶证副本

    private String creditCard;//信用卡(或储蓄卡)
    
    private BigDecimal deposit;//押金
    

    public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSource() {
        return this.source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getStatuts() {
        return this.statuts;
    }

    public void setStatuts(Integer statuts) {
        this.statuts = statuts;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRiskAuditNum() {
        return this.riskAuditNum;
    }

    public void setRiskAuditNum(String riskAuditNum) {
        this.riskAuditNum = riskAuditNum;
    }

    public Integer getRiskAuditStatus() {
        return this.riskAuditStatus;
    }

    public void setRiskAuditStatus(Integer riskAuditStatus) {
        this.riskAuditStatus = riskAuditStatus;
    }

    public Date getRiskAuditTime() {
		return riskAuditTime;
	}

	public void setRiskAuditTime(Date riskAuditTime) {
		this.riskAuditTime = riskAuditTime;
	}

	public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getNationalityId() {
        return this.nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getLastUseCarBeginTime() {
		return lastUseCarBeginTime;
	}

	public void setLastUseCarBeginTime(Date lastUseCarBeginTime) {
		this.lastUseCarBeginTime = lastUseCarBeginTime;
	}

	public Date getLastUseCarEndTime() {
		return lastUseCarEndTime;
	}

	public void setLastUseCarEndTime(Date lastUseCarEndTime) {
		this.lastUseCarEndTime = lastUseCarEndTime;
	}

	public Date getCreatedBegin() {
		return createdBegin;
	}

	public void setCreatedBegin(Date createdBegin) {
		this.createdBegin = createdBegin;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}

	public Date getLastUseCarTime() {
		return lastUseCarTime;
	}

	public void setLastUseCarTime(Date lastUseCarTime) {
		this.lastUseCarTime = lastUseCarTime;
	}

	public Map<String, Integer> getCarports() {
		return carports;
	}

	public void setCarports(Map<String, Integer> carports) {
		this.carports = carports;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getConsumptionAmount() {
		return consumptionAmount;
	}

	public void setConsumptionAmount(BigDecimal consumptionAmount) {
		this.consumptionAmount = consumptionAmount;
	}

	public Integer getUseCarCount() {
		return useCarCount;
	}

	public void setUseCarCount(Integer useCarCount) {
		this.useCarCount = useCarCount;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public Integer getPreAuthorization() {
		return preAuthorization;
	}

	public void setPreAuthorization(Integer preAuthorization) {
		this.preAuthorization = preAuthorization;
	}

	public String getIdcardFront() {
		return idcardFront;
	}

	public void setIdcardFront(String idcardFront) {
		this.idcardFront = idcardFront;
	}

	public String getIdcardBack() {
		return idcardBack;
	}

	public void setIdcardBack(String idcardBack) {
		this.idcardBack = idcardBack;
	}

	public String getDrivingLicenseOriginal() {
		return drivingLicenseOriginal;
	}

	public void setDrivingLicenseOriginal(String drivingLicenseOriginal) {
		this.drivingLicenseOriginal = drivingLicenseOriginal;
	}

	public String getDrivingLicenseCopy() {
		return drivingLicenseCopy;
	}

	public void setDrivingLicenseCopy(String drivingLicenseCopy) {
		this.drivingLicenseCopy = drivingLicenseCopy;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

}
