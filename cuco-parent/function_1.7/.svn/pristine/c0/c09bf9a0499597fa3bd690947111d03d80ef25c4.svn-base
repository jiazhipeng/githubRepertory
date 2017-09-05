package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

import com.hy.gcar.constant.MemberSex;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class Member implements Serializable {

    private static final long serialVersionUID = 1233354131356180L;

    private Long id;//会员id

    private String name;//会员昵称&姓名(企业名称)

    private String mobile;//手机号

    private String openid;//微信openid

    private Integer sex;//性别 -1未知;1:男;0:女;
    
    @SuppressWarnings("unused")
	private String sexInfo;//性别中文详情

    private Integer fromMember;//用户来源 0:线下；1:活动；2:自主关注；3:推荐；4:安卓；5:iOS；6:极车产品

    private Long marketingId;//活动id

    private String marketingName;//活动名称

    private Long recommendId;//推荐人id

    private String recommendName;//推荐人姓名

    private Date created;//创建时间

    private Integer statuts;//会员状态 0:冻结；1:正常；

    private Integer isSelles;//是否为销售 0:否；1:是；

    private Integer isButler;//是否为车管家 0:否；1:是；

    private Integer type;//会员类型 0:个人会员；1:企业员；

    private Integer focusType;//关注状态 0:取消关注；1:已关注；2:未关注

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间

    private String cityId;//城市id

    private String cityCode;//城市编码

    private String cityName;//城市名称

    private String imageUrl;//会员头像地址

    private Integer isTestDriver;//是否试驾过 0:否；1:是；

    private String imageQrcode;//会员二维码URL

    private String orgId;//组织机构代码（企业会员用）

    private String orgName;//企业联系人姓名（企业会员用）

    private String orgTel;//企业联系电话（企业会员用）

    private String sysuserId;//系统用户ID

    private String sysuserName;//系统用户姓名

    private Integer infoAudit;//信息审核 0:未审核；1:已审核；默认0；
    
    private boolean isSuccess;//登录时用
    
    private String message;//登录时用
    
    private String loginId;
    
    private String mediaId;//微信多媒体id

    private Integer useCarApproved;//'用车审核状态：0：待审核；1：驳回审核；2：通过审核；3未审核（无提交审核记录）',
    
    private Integer buyApproved;//'购买审核(入会申请)状态：0：待审核；1：驳回审核；2：通过审核；3未审核（无提交审核记录）'
    
    private String sureName;
    
    private String nationality;
    
    private Long nationalityId;
    
    private String idcard;
    
    private Date addtime;
    
    private Integer memberType;
    
    private Integer isSalesManager;
    
    private Integer isCustomerManager;
    
    private Integer isCustomer;
    
    private Date birthday;
    
    private Long channelId;
    
    private Long cardId;
    
    public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Long nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Integer getIsSalesManager() {
		return isSalesManager;
	}

	public void setIsSalesManager(Integer isSalesManager) {
		this.isSalesManager = isSalesManager;
	}

	public Integer getIsCustomerManager() {
		return isCustomerManager;
	}

	public void setIsCustomerManager(Integer isCustomerManager) {
		this.isCustomerManager = isCustomerManager;
	}

	public Integer getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Integer isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Integer getIsSalesSupport() {
		return isSalesSupport;
	}

	public void setIsSalesSupport(Integer isSalesSupport) {
		this.isSalesSupport = isSalesSupport;
	}

	public void setSexInfo(String sexInfo) {
		this.sexInfo = sexInfo;
	}

	private Integer isSalesSupport;
    
    public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSexInfo() {
		return MemberSex.getSex(this.sex);
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

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getFromMember() {
        return this.fromMember;
    }

    public void setFromMember(Integer fromMember) {
        this.fromMember = fromMember;
    }

    public Integer getBuyApproved() {
		return buyApproved;
	}

	public void setBuyApproved(Integer buyApproved) {
		this.buyApproved = buyApproved;
	}

	public Long getMarketingId() {
        return this.marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getMarketingName() {
        return this.marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Long getRecommendId() {
        return this.recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public String getRecommendName() {
        return this.recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatuts() {
        return this.statuts;
    }

    public void setStatuts(Integer statuts) {
        this.statuts = statuts;
    }

    public Integer getIsSelles() {
        return this.isSelles;
    }

    public void setIsSelles(Integer isSelles) {
        this.isSelles = isSelles;
    }

    public Integer getIsButler() {
        return this.isButler;
    }

    public void setIsButler(Integer isButler) {
        this.isButler = isButler;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFocusType() {
        return this.focusType;
    }

    public void setFocusType(Integer focusType) {
        this.focusType = focusType;
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

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
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

    public Integer getIsTestDriver() {
        return this.isTestDriver;
    }

    public void setIsTestDriver(Integer isTestDriver) {
        this.isTestDriver = isTestDriver;
    }

    public String getImageQrcode() {
        return this.imageQrcode;
    }

    public void setImageQrcode(String imageQrcode) {
        this.imageQrcode = imageQrcode;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgTel() {
        return this.orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getSysuserId() {
        return this.sysuserId;
    }

    public void setSysuserId(String sysuserId) {
        this.sysuserId = sysuserId;
    }

    public String getSysuserName() {
        return this.sysuserName;
    }

    public void setSysuserName(String sysuserName) {
        this.sysuserName = sysuserName;
    }

    public Integer getInfoAudit() {
        return this.infoAudit;
    }

    public void setInfoAudit(Integer infoAudit) {
        this.infoAudit = infoAudit;
    }

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


    public Integer getUseCarApproved() {
        return useCarApproved;
    }

    public void setUseCarApproved(Integer useCarApproved) {
        this.useCarApproved = useCarApproved;
    }
}
