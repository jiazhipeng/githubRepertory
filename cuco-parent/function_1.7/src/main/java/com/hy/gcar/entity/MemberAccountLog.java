package com.hy.gcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hy.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-23 16:56:20
 */
public class MemberAccountLog implements Serializable {

    private static final long serialVersionUID = 7248292940882391L;

    private Long id;//

    private Long memberItemId;//会员权益ID

    private String memberItemName;//权益名称

    private String modifierId;//操作人ID

    private String modifier;//操作人

    private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金

    private Integer transformType;//变动类型：1，客户续费；2，后台人工续费；3，后台人工扣费；4，用车扣费；5，购买(订单支付成功) 6 、用车加急，7燃油费 8、取消用车违约费9紧急救援费用、10替换启用车型费 

    private Integer memberShareType;//共用人状态：0，不是；1，普通共用人；2，主共用人

    private BigDecimal balance;//余额

    private BigDecimal deposit;//押金

    private Date created;//创建时间

    private String remark;//备注

    private Long memberItemOwnerId;//会员权益拥有者ID

    private BigDecimal total;//变化的总金额(余额+押金）

    private Long balanceReasonId;//余额扣费事由ID

    private String balanceReason;//余额扣费事由

    private Long depositReasonId;//押金扣费事由ID

    private String depositReason;//押金扣费事由

    private BigDecimal preBalance;//修改前余额

    private BigDecimal preDeposit;//修改前押金

    private Date lasttimeModify;//最后操作时间

    private Long powerUsedId;//会员用车ID

    /**
     * 金额变动范围查询标示
     * balance,余额 account_type in (1,3)
     * deposit,押金 account_type in (2,3)
     */
    @JsonIgnore
    private String searchAccountType;

    /**
     * 排序标示
     * 1，order by lasttime_modify desc
     */
    @JsonIgnore
    private Integer orderByType;

    @JsonIgnore
    private PowerUsed powerUsed;

    /**
     * 根据transform类型判断是否显示最后修改时间
     * 用车时，会多次修改最后时间，展示时需展示时间段
     * @return
     */
    public boolean getShowLasttimeModify(){
        if(Arrays.asList(4).contains(this.getTransformType())){//用车扣费
            return true;
        }
        return false;
    }

    public String getTransformReason(){
        switch (this.getTransformType()){
            case 1 : return "用户续费";
            case 2 : return "系统续费";
            case 3 : return StringUtils.fillNull(this.getBalanceReason());
            case 4 : return this.getPowerUsedCarInfo()+"使用费";//TODO
            case 5 : return "充值";
            case 6 : return "用车加急费";
            case 7 : return "油费";
            case 8 : return "违约费";
            case 9 : return "紧急救援费用";
            case 10 : return "更换车型费";
            case 11 : return "充电";
            case 12 : return "故障换车";
            default : return "";
        }
    }

    public String getPowerUsedCarInfo(){
        if(powerUsed!=null){
            return StringUtils.fillNull(powerUsed.getBrand()+" "+powerUsed.getCarType());
        }
        return "";
    }

    public PowerUsed getPowerUsed() {
        return powerUsed;
    }

    public void setPowerUsed(PowerUsed powerUsed) {
        this.powerUsed = powerUsed;
    }

    public String getSearchAccountType() {
        return searchAccountType;
    }

    public void setSearchAccountType(String searchAccountType) {
        this.searchAccountType = searchAccountType;
    }

    public Integer getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(Integer orderByType) {
        this.orderByType = orderByType;
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

    public String getMemberItemName() {
        return this.memberItemName;
    }

    public void setMemberItemName(String memberItemName) {
        this.memberItemName = memberItemName;
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

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getTransformType() {
        return this.transformType;
    }

    public void setTransformType(Integer transformType) {
        this.transformType = transformType;
    }

    public Integer getMemberShareType() {
        return this.memberShareType;
    }

    public void setMemberShareType(Integer memberShareType) {
        this.memberShareType = memberShareType;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
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

    public Long getMemberItemOwnerId() {
        return this.memberItemOwnerId;
    }

    public void setMemberItemOwnerId(Long memberItemOwnerId) {
        this.memberItemOwnerId = memberItemOwnerId;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getBalanceReasonId() {
        return this.balanceReasonId;
    }

    public void setBalanceReasonId(Long balanceReasonId) {
        this.balanceReasonId = balanceReasonId;
    }

    public String getBalanceReason() {
        return this.balanceReason;
    }

    public void setBalanceReason(String balanceReason) {
        this.balanceReason = balanceReason;
    }

    public Long getDepositReasonId() {
        return this.depositReasonId;
    }

    public void setDepositReasonId(Long depositReasonId) {
        this.depositReasonId = depositReasonId;
    }

    public String getDepositReason() {
        return this.depositReason;
    }

    public void setDepositReason(String depositReason) {
        this.depositReason = depositReason;
    }

    public BigDecimal getPreBalance() {
        return this.preBalance;
    }

    public void setPreBalance(BigDecimal preBalance) {
        this.preBalance = preBalance;
    }

    public BigDecimal getPreDeposit() {
        return this.preDeposit;
    }

    public void setPreDeposit(BigDecimal preDeposit) {
        this.preDeposit = preDeposit;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public Long getPowerUsedId() {
        return this.powerUsedId;
    }

    public void setPowerUsedId(Long powerUsedId) {
        this.powerUsedId = powerUsedId;
    }

}
