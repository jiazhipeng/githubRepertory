package cn.cuco.service.basic.dictionary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CountriesMapper;
import cn.cuco.entity.Countries;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.dictionary.CountriesService;

@Service(value = "countriesService")
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    private CountriesMapper<Countries> countriesMapper;

    /**
     * 分页
     */
	@Override
	public PageResult<Countries> getCountriesPage(Countries countries){
		Integer page = countries.getPage();
	    Integer pageSize = countries.getPageSize();
	     /*搜索条件*/
	    Countries parkingSearch = new Countries();
	    List<Countries> countriess = null;
	    /*总记录数*/
	    Integer totalSize = this.countriesMapper.selectCountByConditionByPage(parkingSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    countriess = this.countriesMapper.selectByConditionByPage(parkingSearch);
	    
        PageResult<Countries> pageResult = new PageResult<Countries>(page,pageSize,totalSize,countriess);
		return pageResult;
	}

	/**
	 * 查询国家
	 */
	@Override
	public Countries getCountriesById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询国家，ID不能为空");
		return this.countriesMapper.selectByPrimaryKey(id);
	}
    
   
}
