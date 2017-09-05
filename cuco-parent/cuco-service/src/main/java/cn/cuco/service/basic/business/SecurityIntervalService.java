package cn.cuco.service.basic.business;


import cn.cuco.entity.SecurityInterval;

/**
* @ClassName: SecurityIntervalService 
* @Description: 安全区间接口
* @author huanghua
* @date 2017年2月21日 下午3:08:27
 */
public interface SecurityIntervalService {

    /**
    * @Title: getSecurityIntervalByCreatedDesc 
    * @Description: 取安全区间信息
    * @author huanghua
    * @return SecurityInterval
     */
    public SecurityInterval getSecurityIntervalByCreatedDesc();

}
