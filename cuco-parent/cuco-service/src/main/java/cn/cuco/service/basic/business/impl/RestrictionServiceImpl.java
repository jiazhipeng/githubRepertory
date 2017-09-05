package cn.cuco.service.basic.business.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.RestrictionMapper;
import cn.cuco.entity.City;
import cn.cuco.entity.Restriction;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.service.basic.business.RestrictionService;
import cn.cuco.service.basic.dictionary.CityService;

@Service(value = "restrictionService")
public class RestrictionServiceImpl implements RestrictionService {

    @Autowired
    private RestrictionMapper<Restriction> restrictionMapper;
    @Autowired
    private CityService cityService;
    
    /**
     * 创建-修改修改限号
     */
	@Override
	public Restriction createRestriction(Restriction restriction) {
		ParamVerifyUtils.paramNotNull(restriction);
		ParamVerifyUtils.paramNotNull(restriction.getCityId(),"城市id号不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getRestrictionsDate(),"限行日期不能为空");
//		ParamVerifyUtils.paramNotNull(restriction.getRestrictions(),"限行数字不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getBeginDate(),"限行开始时间不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getEndDate(),"限行结束时间不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getModifier(),"操作人不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getModifierId(),"操作人ID不能为空");
		//查询该城市的该日期限号记录
		Restriction selectRestriction = new Restriction();
		selectRestriction.setCityId(restriction.getCityId());
		selectRestriction.setRestrictionsDate(restriction.getRestrictionsDate());
		List<Restriction> list = restrictionMapper.selectByDate(selectRestriction);
		
		String restrictions = restriction.getRestrictions();		
		if(restrictions.indexOf("-1") == -1){//需要限号			
			if(list.isEmpty()){//当前日期未设置限号				
				//增加限号数字
				City city = cityService.getCityById(restriction.getCityId());
				if(city==null){
					 throw new RuntimeExceptionWarn("限号城市不存在");
				}
				restriction.setCityId(city.getId());
				restriction.setCityCode(city.getCode());
				restriction.setCityName(city.getName());
				restrictionMapper.insertSelective(restriction);				
				return restrictionMapper.selectByPrimaryKey(restriction.getId());
			}else{//当前日期已设置限号				
				//修改限号数字
				Restriction updateRestriction = list.get(0);
				restriction.setId(updateRestriction.getId());
				restrictionMapper.updateByPrimaryKeySelective(restriction);				
				//修改城市服务中的操作人及操作时间
				return  restrictionMapper.selectByPrimaryKey(restriction.getId());
			}			
		}else{//不需要限号
			if(!list.isEmpty()){
				restrictionMapper.deleteByPrimaryKey(list.get(0).getId());
			}
			return null;
		}
	}

	/**
	 * 按时间查询限号记录
	 */
	@Override
	public List<Restriction> getMouthRestriction(Restriction restriction) {
		ParamVerifyUtils.paramNotNull(restriction);
		ParamVerifyUtils.paramNotNull(restriction.getCityId(),"城市id不能为空");
		ParamVerifyUtils.paramNotNull(restriction.getSearchDate(),"开始时间不能为空");
		try {
			restriction.setSearchBeginDate(this.getMiniDate(restriction.getSearchDate(), true).getTime());
			restriction.setSearchEndDate(this.getMaxDate(restriction.getSearchDate(), true).getTime());
		} catch (ParseException e) {
			throw new RuntimeExceptionWarn("查询失败！");
		}
		return restrictionMapper.selectByRestrictionsDate(restriction);
	}
	
	/**
	 * 根据年月日-城市查询限号
	 */
	@Override
	public Restriction getRestrictionByDate(Restriction restriction) {
		Restriction selectRestriction = new Restriction();
		selectRestriction.setCityId(restriction.getCityId());
		selectRestriction.setRestrictionsDate(restriction.getRestrictionsDate());
		List<Restriction> list = restrictionMapper.selectByDate(selectRestriction);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	/**
	 * 获取本月最小的日期  例：2016-8-01 00:00:00
	 * flag true设置date天为最小， false不设置天
	* @Description:TODO      
	* @return String
	 */
	private static Calendar getMiniDate(Date date,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最小日期
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		return calendar;
	}
	
	/**
	 * 获取本月最大的日期  例：2016-8-31 23:59:59
	 *  flag true设置date天为最大， false不设置天
	* @Description:TODO      
	* @return String
	 */
	private static Calendar getMaxDate(Date date,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最大日期
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		return calendar;
	}
}
