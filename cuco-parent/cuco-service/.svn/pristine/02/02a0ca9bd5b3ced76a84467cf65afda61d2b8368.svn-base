package cn.cuco.service.basic.dictionary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CityMapper;
import cn.cuco.entity.City;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.dictionary.CityService;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper<City> cityMapper;
	
    /**
     * 分页
     */
	@Override
	public PageResult<City> getCityPage(City city){
		Integer page = city.getPage();
	    Integer pageSize = city.getPageSize();
	     /*搜索条件*/
	    City citySearch = new City();
	    List<City> citys = null;
	    /*总记录数*/
	    Integer totalSize = this.cityMapper.selectCountByCondition(citySearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    citys = this.cityMapper.selectByCondition(citySearch);
	    
        PageResult<City> pageResult = new PageResult<City>(page,pageSize,totalSize,citys);
		return pageResult;
	}

	/**
	 * 根据ID城市
	 */
	@Override
	public City getCityById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询城市，ID不能为空");
		return this.cityMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据父id获取城市信息
	 */
	@Override
	public List<City> getCityByParent(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询城市，父ID不能为空");
		return cityMapper.selectByParent(id);
	}

	/**
	 * 根据code查询城市信息
	 */
	@Override
	public List<City> getCityByCityCode(String code) {
		ParamVerifyUtils.paramNotNull(code, "查询城市，code不能为空");
		return  cityMapper.getCityByCityCode(code);
	}

	/**
	 * 查询父级city
	 */
	@Override
	public City getParentCity(City city) {
		ParamVerifyUtils.paramNotNull(city);
		ParamVerifyUtils.paramNotNull(city.getId(), "查询父级城市，ID不能为空");
		return cityMapper.selectParent(city);
	}

	/**
	 * 根据 pid 的集合获取市和县的集合
	 */
	@Override
	public List<City> getCityByParentID(City city) {
			return cityMapper.getCityByParentID(city);
	}


}
