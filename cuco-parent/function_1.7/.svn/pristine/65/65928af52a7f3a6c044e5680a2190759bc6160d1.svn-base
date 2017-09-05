package com.hy.gcar.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.service.nation.NationService;

/**
 * 
 * @author q.p.x
 * 省市关联的工具类
 *
 */
@Service
public class ProvincesUtils {
	
	
	 Logger logger = Logger.getLogger(ProvincesUtils.class);
	 
	 @Autowired
	 NationService nationService;
	
	//private final static NationService nationService = ContextLoader.getCurrentWebApplicationContext().getBean("nationServiceImpl",NationServiceImpl.class);

	/**
	 * 省集合
	 */
	private static List<Nation>  province = null;
	
	
	
	private ProvincesUtils(){
		//TODO
		
	}
	
	/**
	 * 获取省集合
	* @Title: getProvince 
	* @Description: TODO
	* @author q.p.x
	* @param @return    
	* @return List<Nation>    
	* @date 2016年4月22日 下午5:17:21 
	* @throws
	 */
	public  synchronized List<Nation> getProvince(){
		
		if(province != null && province.size()>0){
			return province;
		}
		province = Lists.newArrayList();
		List<String> parentList = Arrays.asList("1");
		Nation nation = new Nation();
		nation.setParentList(parentList);
		province =  nationService.getNationByParentID(nation);	
		logger.debug(" 获取省集合 province>>>>>>>>" + JSONObject.toJSONString(province));
		return province;    
	}
	
	/**
	 * 获取城市的集合
	* @Title: cityMap 
	* @Description: TODO
	* @author q.p.x
	* @param @return    
	* @return Map<String,List<Nation>>    
	* @date 2016年4月22日 下午6:12:43 
	* @throws
	 */
	private static final List<String> lists = Arrays.asList("110000","120000","310000","500000");
	public  synchronized List<Nation> getCityMap(){
		

		List<Nation> provinces =  getProvince();
		List<String> parentList = Lists.newArrayList();
		for(Nation province : provinces){
			parentList.add(province.getId()+"");
		}
		
		
		Nation nation = new Nation();
		nation.setParentList(parentList);
		List<Nation>  nations =  nationService.getNationByParentID(nation);	
		
		logger.debug("获取城市的集合......"+JSONObject.toJSONString(nations));
		for(Nation province : provinces){
			List<Nation> cityList = Lists.newArrayList();
			for(Nation n : nations){
				if(province.getId().intValue() == Integer.valueOf(n.getParent()).intValue()){
					cityList.add(n);
				}
			}
			
//			if(!lists.contains(province.getCode())){
//				province.setSub(cityList);
//
//			}
			province.setSub(cityList);
		}
		logger.debug("获取城市......"+JSONObject.toJSONString(province));
		return provinces;
		
	}
	
	
	public static void main(String[] args) {
		
//		provincesUtils.getProvince();
//		getCityMap();
//		getDistrictMap();
	}

}
