package cn.cuco.service.basic.dictionary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.InsuranceMapper;
import cn.cuco.entity.Insurance;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.dictionary.InsuranceService;

@Service(value = "insuranceService")
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceMapper<Insurance> insuranceMapper;

    /**
     * 取保险公司详情
     */
	@Override
	public Insurance getInsuranceById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询保险公司，ID不能为空");
		return this.insuranceMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页
	 */
	@Override
	public PageResult<Insurance> getInsuranceList(Insurance insurance) {
		Integer page = insurance.getPage();
	    Integer pageSize = insurance.getPageSize();
	     /*搜索条件*/
	    Insurance insuranceSearch = new Insurance();
	    List<Insurance> insurances = null;
	    /*总记录数*/
	    Integer totalSize = this.insuranceMapper.selectCountByConditionByPage(insuranceSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    insurances = this.insuranceMapper.selectByConditionByPage(insuranceSearch);
	    
        PageResult<Insurance> pageResult = new PageResult<Insurance>(page,pageSize,totalSize,insurances);
		return pageResult;
	}
    
  
}
