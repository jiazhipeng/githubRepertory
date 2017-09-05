package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 10:41:17
 */
public class Member extends PageQuery<Member> implements Serializable {

    private static final long serialVersionUID = 5530697938816824L;

    private Long id;//用户ID

    private String name;//用户昵称&姓名

    private String mobile;//手机号

    private String openid;//微信openid

    private String unionid;//unionid来区分用户的唯一性

    private Integer sex;//性别 -1未知;0:男;1:女;

    private Integer source;//用户来源 0:线下;1:App-Android;2:App-IOS;3:自主关注(微信);4:市场活动;5:渠道;6:推荐

    private Long sourceId;//来源ID

    private String sourceName;//活动名称

    private Integer statuts;//用户状态 0:冻结;1:正常;默认1

    private Integer type;//用户类型 0:非会员;1:会员;默认0
    
    private Date addTime;//入会时间

    private Integer focusStatus;//关注状态 0:未关注;1:已关注;2:取消关注;默认0

    private Long cityId;//城市id

    private String cityCode;//城市编码

    private String cityName;//城市名称

    private String imageUrl;//用户头像地址

    private String imageQrcode;//用户二维码URL

    private String riskAuditNum;//风控审核编号

    private Integer riskAuditStatus;//风控审核状态 0:未提交资料；1:系统审核通过；2：系统审核拒绝;3:等待人工审核;4:系统审核失败;5:人工审核通过;6:人工审核拒绝;默认0；

    private Date riskAuditTime;//风控审核时间
    
    private String nationality;//国籍

    private Long nationalityId;//国籍ID

    private String idcard;//身份证/护照号码

    private Date birthday;//生日

    private Date created;//创建时间

    private Long modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间
    
    private MemberInfo memberInfo;//用户证件信息
    
    private String remark;//备注
    
    private Date useCarStartDate;//用车开始时间
    
    private Date useCarEndDate;//用车结束时间
    
    private Date registerStartDate;//注册开始时间
    
    private Date registerEndDate;//注册结束时间
    
    private Date lastUseCarDate;//最后用车时间
    
    private List<MemberCarport> carportList;//用户车库列表
    
    private BigDecimal balane;//用户余额
    
    private BigDecimal deposit;//押金
    
    private Integer renewalCount = 0;//充值次数

    private String carportName;
    
    public BigDecimal getBalane() {
		return balane;
	}
    
	public void setBalane(BigDecimal balane) {
		this.balane = balane;
	}

	public Integer getRenewalCount() {
		return renewalCount;
	}

	public void setRenewalCount(Integer renewalCount) {
		this.renewalCount = renewalCount;
	}

	public BigDecimal getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	public Integer getUseCarCount() {
		return useCarCount;
	}

	public void setUseCarCount(Integer useCarCount) {
		this.useCarCount = useCarCount;
	}

	private BigDecimal costTotal;//消费总额
    
    private Integer useCarCount = 0;//累计用车次数

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

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return this.unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
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

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getStatuts() {
        return this.statuts;
    }

    public void setStatuts(Integer statuts) {
        this.statuts = statuts;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFocusStatus() {
        return this.focusStatus;
    }

    public void setFocusStatus(Integer focusStatus) {
        this.focusStatus = focusStatus;
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

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageQrcode() {
        return this.imageQrcode;
    }

    public void setImageQrcode(String imageQrcode) {
        this.imageQrcode = imageQrcode;
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

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

	public Date getUseCarStartDate() {
		return useCarStartDate;
	}

	public void setUseCarStartDate(Date useCarStartDate) {
		this.useCarStartDate = useCarStartDate;
	}

	public Date getUseCarEndDate() {
		return useCarEndDate;
	}

	public void setUseCarEndDate(Date useCarEndDate) {
		this.useCarEndDate = useCarEndDate;
	}

	public Date getRegisterStartDate() {
		return registerStartDate;
	}

	public void setRegisterStartDate(Date registerStartDate) {
		this.registerStartDate = registerStartDate;
	}

	public Date getRegisterEndDate() {
		return registerEndDate;
	}

	public void setRegisterEndDate(Date registerEndDate) {
		this.registerEndDate = registerEndDate;
	}

	public Date getLastUseCarDate() {
		return lastUseCarDate;
	}

	public void setLastUseCarDate(Date lastUseCarDate) {
		this.lastUseCarDate = lastUseCarDate;
	}

	public List<MemberCarport> getCarportList() {
		return carportList;
	}

	public void setCarportList(List<MemberCarport> carportList) {
		this.carportList = carportList;
	}

	public String getCarportName() {
		return carportName;
	}

	public void setCarportName(String carportName) {
		this.carportName = carportName;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
}
