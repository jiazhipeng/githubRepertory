package com.hy.gcar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-19 16:16:13
 */
public class PowerUsed implements Serializable {

    private static final long serialVersionUID = 7864261579265588L;

    private Long id;//

    private Long memberId;//用车发起人ID

    private Long memberItemId;//权益ID
    
    private String memberItemName;

    private Long caroperateId;//运营车辆ID

    private Integer memberType;//共用人类型 0:共用人；1:主；

    private Date carUsedTime;//用车时间

    private Integer carUsedDay;//用车天数

    private String carUsedAddress;//

    private Date carReturnTime;//预计换车时间

    private String carReturnAddress;//预计还车地点

    private Long carTypeId;//原启用车型的车型ID

    private Long changeCaroperateId;//

    private Integer changeStatus;//是否临时换车：0：无换车；1：有换车

    private Integer usedStatus;//用车的状态0：待送车 1：已出发送车 2 已到达 3送车完成 4已出发取车 5 管家已到达 6取车完成 7：已取消

    private Long changeCarTypeId;//替换后的车型id

    private String cityCode;//用车城市code
    
    private Date created;
    private Date lasttimeModify;
    

    private Long couponInfoId;//优惠券id
    private String flag;//用车来源0:展厅 1车位
    
    private String brand;//
    private String carType;//
    private String carPlateNum;
    private long ItemId;//
    
//    private String clickCartype;//记录点击次数
    
    private PowerUsedCost powerUsedCost;
    
    private CarOperate carOperate;
    
    private CarType carTypeView;
    
    private MemberItem memberItem;
    
    private Member member;
    private BigDecimal price;
   	
    public String getCarPlateNum() {
		return carPlateNum;
	}

	public void setCarPlateNum(String carPlateNum) {
		this.carPlateNum = carPlateNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	private List<TransferList> useList;
    
    private List<TransferList> returnList;
    private int beginPage = 1;
	
   	private int pageSize = 20;
    
    public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<TransferList> getUseList() {
		return useList;
	}

	public void setUseList(List<TransferList> useList) {
		this.useList = useList;
	}

	public List<TransferList> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<TransferList> returnList) {
		this.returnList = returnList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberItem getMemberItem() {
		return memberItem;
	}

	public void setMemberItem(MemberItem memberItem) {
		this.memberItem = memberItem;
	}

	public CarType getCarTypeView() {
		return carTypeView;
	}

	public void setCarTypeView(CarType carTypeView) {
		this.carTypeView = carTypeView;
	}

	public PowerUsedCost getPowerUsedCost() {
		return powerUsedCost;
	}

	public void setPowerUsedCost(PowerUsedCost powerUsedCost) {
		this.powerUsedCost = powerUsedCost;
	}

	public CarOperate getCarOperate() {
		return carOperate;
	}

	public void setCarOperate(CarOperate carOperate) {
		this.carOperate = carOperate;
	}

	public long getItemId() {
		return ItemId;
	}

	public void setItemId(long itemId) {
		ItemId = itemId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}


	public Long getCouponInfoId() {
		return couponInfoId;
	}

	public void setCouponInfoId(Long couponInfoId) {
		this.couponInfoId = couponInfoId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberItemId() {
        return this.memberItemId;
    }

    public void setMemberItemId(Long memberItemId) {
        this.memberItemId = memberItemId;
    }

    public Long getCaroperateId() {
        return this.caroperateId;
    }

    public void setCaroperateId(Long caroperateId) {
        this.caroperateId = caroperateId;
    }

    public Integer getMemberType() {
        return this.memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Date getCarUsedTime() {
        return this.carUsedTime;
    }

    public void setCarUsedTime(Date carUsedTime) {
        this.carUsedTime = carUsedTime;
    }

    public Integer getCarUsedDay() {
        return this.carUsedDay;
    }

    public void setCarUsedDay(Integer carUsedDay) {
        this.carUsedDay = carUsedDay;
    }

    public String getCarUsedAddress() {
        return this.carUsedAddress;
    }

    public void setCarUsedAddress(String carUsedAddress) {
        this.carUsedAddress = carUsedAddress;
    }

    public Date getCarReturnTime() {
        return this.carReturnTime;
    }

    public void setCarReturnTime(Date carReturnTime) {
        this.carReturnTime = carReturnTime;
    }

    public String getCarReturnAddress() {
        return this.carReturnAddress;
    }

    public void setCarReturnAddress(String carReturnAddress) {
        this.carReturnAddress = carReturnAddress;
    }

    public Long getCarTypeId() {
        return this.carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public Long getChangeCaroperateId() {
        return this.changeCaroperateId;
    }

    public void setChangeCaroperateId(Long changeCaroperateId) {
        this.changeCaroperateId = changeCaroperateId;
    }

    public Integer getChangeStatus() {
        return this.changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getUsedStatus() {
        return this.usedStatus;
    }

    public void setUsedStatus(Integer usedStatus) {
        this.usedStatus = usedStatus;
    }

    public Long getChangeCarTypeId() {
        return this.changeCarTypeId;
    }

    public void setChangeCarTypeId(Long changeCarTypeId) {
        this.changeCarTypeId = changeCarTypeId;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

	public String getMemberItemName() {
		return memberItemName;
	}

	public void setMemberItemName(String memberItemName) {
		this.memberItemName = memberItemName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLasttimeModify() {
		return lasttimeModify;
	}

	public void setLasttimeModify(Date lasttimeModify) {
		this.lasttimeModify = lasttimeModify;
	}

	
    
}
