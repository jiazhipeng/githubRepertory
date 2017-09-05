package cn.cuco.service.basic.dictionary;


import cn.cuco.entity.Insurance;
import cn.cuco.page.PageResult;

/**
* @ClassName: InsuranceService 
* @Description: 保险公司接口
* @author huanghua
* @date 2017年2月21日 下午2:41:53
 */
public interface InsuranceService {
	
    /**
    * @Title: getInsuranceById 
    * @Description:  取保险公司详情
    * @author huanghua
    * @param id
    * @return Insurance
     */
    public Insurance getInsuranceById(Long id);

    /**
    * @Title: getInsuranceList 
    * @Description: 分页
    * @author huanghua
    * @param insurance
    * @return PageResult<Insurance>
     */
    public PageResult<Insurance> getInsuranceList(Insurance insurance);


}
