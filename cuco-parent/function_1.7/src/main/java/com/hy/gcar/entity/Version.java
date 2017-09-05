package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 版本基础信息
 */
public class Version implements Serializable {
    private Long id; //主键ID
    private String name="";//版本名称
    private   String  force;//是否强迫
    private  String   code;//版本代码（版本号）
    private  String  address; //版本返回地址
    private  String  describe;//版本描述
    private  Date updatetime;//更新时间
    private String forceCode;//强制更新线(强制更新版本号) 
    private Integer flag;//0 安卓 ,1 ios
    
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getForceCode() {
		return forceCode;
	}
	public void setForceCode(String forceCode) {
		this.forceCode = forceCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getForce() {
		return force;
	}
	public void setForce(String force) {
		this.force = force;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	


    

}