package cn.cuco.entity;

import java.io.Serializable;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:40
 */
public class Insurance extends PageQuery implements Serializable {

    private static final long serialVersionUID = 7864959275389954L;

    private Long id;//

    private String insuranceNumber;//保险公司编号

    private String insuranceName;//保险公司名称

    private String insuranceAddress;//保险公司地址

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceAddress() {
        return this.insuranceAddress;
    }

    public void setInsuranceAddress(String insuranceAddress) {
        this.insuranceAddress = insuranceAddress;
    }

}
