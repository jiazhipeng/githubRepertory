package com.hy.gcar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class CarType implements Serializable {

    private static final long serialVersionUID = 5154766196768276L;

    private Long id;//

    private String brand;//品牌名称

    private String carType;//车型名称

    private String imageUrl;//车型剪影图片地址

    private Integer cartypePower;//车型动力

    private String cartypeColor;//车型颜色

    private String vidioUrl;//车型视频地址

    private Integer roadRescue;//道路救援  0:未勾选；1:勾选；默认0；（临时处理方案）

    private Integer charging;//代充电服务 0:未勾选；1:勾选；默认0；（临时处理方案）

    private Date created;//创建时间

    private String cartypeIntroduce;//车型介绍

    private String cartypeParam;//车型参数
    
    private BigDecimal dayPrice;
    
    private  Long memberId;
    private  Long memberItemId;
    private Integer flag;
    private List<String> itemNames;
    private List<String> serviceNames;
    
    private Integer scene; //适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；
    
    private boolean isStore;//是否有库存 true 有  false 无
    
    private String cartypeId;//ios id关键字
    /**
     * 无限库存 0:不是；1:是
     */
    private Integer isInfiniteStock;
    
    /**
     * 车型是否限号 0:不限；1限号
     */
    private Integer isRestriction;
    

    
	public Long getMemberItemId() {
		return memberItemId;
	}

	public void setMemberItemId(Long memberItemId) {
		this.memberItemId = memberItemId;
	}

	public String getCartypeId() {
		return cartypeId;
	}

	public void setCartypeId(String cartypeId) {
		this.cartypeId = cartypeId;
	}

	public Integer getIsInfiniteStock(){
    	return isInfiniteStock;
    }
    
    public void setIsInfiniteStock(Integer isInfiniteStock){
    	this.isInfiniteStock = isInfiniteStock;
    }
    
    public Integer getIsRestriction(){
    	return isRestriction;
    }
    
    public void setIsRestriction(Integer isRestriction){
    	this.isRestriction = isRestriction;
    }

	public boolean isStore() {
		return isStore;
	}

	public void setStore(boolean isStore) {
		this.isStore = isStore;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public List<String> getServiceNames() {
		return serviceNames;
	}

	public void setServiceNames(List<String> serviceNames) {
		this.serviceNames = serviceNames;
	}

	public List<String> getItemNames() {
		return itemNames;
	}

	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(BigDecimal dayPrice) {
		this.dayPrice = dayPrice;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCartypePower() {
        return this.cartypePower;
    }

    public void setCartypePower(Integer cartypePower) {
        this.cartypePower = cartypePower;
    }

    public String getCartypeColor() {
        return this.cartypeColor;
    }

    public void setCartypeColor(String cartypeColor) {
        this.cartypeColor = cartypeColor;
    }

    public String getVidioUrl() {
        return this.vidioUrl;
    }

    public void setVidioUrl(String vidioUrl) {
        this.vidioUrl = vidioUrl;
    }

    public Integer getRoadRescue() {
        return this.roadRescue;
    }

    public void setRoadRescue(Integer roadRescue) {
        this.roadRescue = roadRescue;
    }

    public Integer getCharging() {
        return this.charging;
    }

    public void setCharging(Integer charging) {
        this.charging = charging;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCartypeIntroduce() {
        return this.cartypeIntroduce;
    }

    public void setCartypeIntroduce(String cartypeIntroduce) {
        this.cartypeIntroduce = cartypeIntroduce;
    }

	public String getCartypeParam() {
		return cartypeParam;
	}

	public void setCartypeParam(String cartypeParam) {
		this.cartypeParam = cartypeParam;
	}

	@Override
	public String toString() {
		return "CarType [id=" + id + ", brand=" + brand + ", carType=" + carType + ", imageUrl=" + imageUrl
				+ ", cartypePower=" + cartypePower + ", cartypeColor=" + cartypeColor + ", vidioUrl=" + vidioUrl
				+ ", roadRescue=" + roadRescue + ", charging=" + charging + ", created=" + created
				+ ", cartypeIntroduce=" + cartypeIntroduce + ", cartypeParam=" + cartypeParam + ", dayPrice=" + dayPrice
				+ ", memberId=" + memberId + ", flag=" + flag + ", itemNames=" + itemNames + "]";
	}

  

}
