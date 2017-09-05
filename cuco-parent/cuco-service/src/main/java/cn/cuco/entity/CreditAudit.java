package cn.cuco.entity;

import java.io.Serializable;

/** 
* @ClassName: CreditAudit 
* @Description: 用户风控审核实体类
* @author zc.du
* @date 2017年2月23日 上午11:29:10  
*/
public class CreditAudit implements Serializable {
	
	private static final long serialVersionUID = -7770750943796206396L;

	private String auditNum;//审核单号
	
	private Long memberId;//用户Id
	
	private String mobile;//用户手机号
	
	private String name;//用户姓名
	
	private String idCard;//身份证号码
	
	private String bankCardNumber;//银行卡号
	
	private String idcardFront;//身份证正面

    private String idcardBack;//身份证反面

    private String drivingLicenseOriginal;//驾驶证正本

    private String drivingLicenseCopy;//驾驶证副本

    private String creditCard;//信用卡(或储蓄卡)
    
    private Integer auditStatus;//审核结果 1:系统审核通过；2：系统审核拒绝;3:等待人工审核;4:系统审核失败;5:人工审核通过;6:人工审核拒绝;
    
    public CreditAudit(){}

	public String getAuditNum() {
		return auditNum;
	}

	public void setAuditNum(String auditNum) {
		this.auditNum = auditNum;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
    
}
