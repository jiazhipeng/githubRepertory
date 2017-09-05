package cn.cuco.service.basic.dictionary;


import cn.cuco.entity.InsuranceSubjects;
import cn.cuco.page.PageResult;

/**
* @ClassName: InsuranceSubjectsService 
* @Description: 保险科目接口
* @author huanghua
* @date 2017年2月21日 下午3:01:48
 */
public interface InsuranceSubjectsService {

    /**
    * @Title: getInsuranceSubjectsById 
    * @Description:  取保险科目详情
    * @author huanghua
    * @param id
    * @return InsuranceSubjects
     */
    public InsuranceSubjects getInsuranceSubjectsById(Long id);

    /**
    * @Title: getInsuranceSubjectsList 
    * @Description: 分页
    * @author huanghua
    * @param insuranceSubjects
    * @return
    * @return PageResult<InsuranceSubjects>
     */
    public PageResult<InsuranceSubjects> getInsuranceSubjectsList(InsuranceSubjects insuranceSubjects);

}
