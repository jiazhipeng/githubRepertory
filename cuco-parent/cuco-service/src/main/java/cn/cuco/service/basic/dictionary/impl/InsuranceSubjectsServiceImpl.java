package cn.cuco.service.basic.dictionary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.InsuranceSubjectsMapper;
import cn.cuco.entity.InsuranceSubjects;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.dictionary.InsuranceSubjectsService;

@Service(value = "insuranceSubjectsService")
public class InsuranceSubjectsServiceImpl implements InsuranceSubjectsService {

    @Autowired
    private InsuranceSubjectsMapper<InsuranceSubjects> insuranceSubjectsMapper;

    /**
     * 取保险公司科目详情
     */
	@Override
	public InsuranceSubjects getInsuranceSubjectsById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询保险科目，ID不能为空");
		return this.insuranceSubjectsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页
	 */
	@Override
	public PageResult<InsuranceSubjects> getInsuranceSubjectsList(InsuranceSubjects insuranceSubjects) {
		Integer page = insuranceSubjects.getPage();
	    Integer pageSize = insuranceSubjects.getPageSize();
	     /*搜索条件*/
	    InsuranceSubjects insuranceSubjectsSearch = new InsuranceSubjects();
	    List<InsuranceSubjects> insuranceSubjectss = null;
	    /*总记录数*/
	    Integer totalSize = this.insuranceSubjectsMapper.selectCountByConditionByPage(insuranceSubjectsSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    insuranceSubjectss = this.insuranceSubjectsMapper.selectByConditionByPage(insuranceSubjectsSearch);
	    
        PageResult<InsuranceSubjects> pageResult = new PageResult<InsuranceSubjects>(page,pageSize,totalSize,insuranceSubjectss);
		return pageResult;
	}
    
   

}
