package cn.cuco.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:40
 */
public class CarBrand implements Serializable {

    private static final long serialVersionUID = 9378743801754971L;

    private Long id;//

    private String brand;//品牌名称

    private String carType;//车型名称

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

}
