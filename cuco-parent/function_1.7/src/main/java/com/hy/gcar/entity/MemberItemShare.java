package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-27 13:00:04
 */
public class MemberItemShare implements Serializable {

    private static final long serialVersionUID = 8834879343317120L;

    private Long id;//

    private Long memberItemId;//权益ID

    private Long memberId;//会员ID

    private Integer isMain;//是否为主共用人：0:不是；1是

    private Integer isShare;//是否共用：0:不共用；1共用

    private Long parentMemberId;//共享的谁的权益

    private Integer parentMemberType;//所属权益会员的类型：0:个人；1:企业

    private Date created;//创建时间

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间

    private Member member;
    
    private String memberMobile;
    
    private Integer memberType;//会员类型
    
    public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberItemId() {
        return this.memberItemId;
    }

    public void setMemberItemId(Long memberItemId) {
        this.memberItemId = memberItemId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getIsMain() {
        return this.isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public Integer getIsShare() {
        return this.isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public Long getParentMemberId() {
        return this.parentMemberId;
    }

    public void setParentMemberId(Long parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public Integer getParentMemberType() {
        return this.parentMemberType;
    }

    public void setParentMemberType(Integer parentMemberType) {
        this.parentMemberType = parentMemberType;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

}
