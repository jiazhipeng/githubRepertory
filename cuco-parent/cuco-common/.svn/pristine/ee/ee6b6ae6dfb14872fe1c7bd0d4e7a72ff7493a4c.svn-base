package cn.cuco.common.utils.map;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import cn.cuco.constant.Constant;

/**
 * 地图工具类
 * @author qpx
 *
 */
public class MapUtils {
	private static Logger logger = Logger.getLogger(MapUtils.class);

	
	/**
	 * 高德地图根据 经纬度获取城市国家......
	* @Title: 
	* @Description: TODO
	* @author q.p.x
	* @param @param Location getCityByLocation  经纬度
	* @param @return   
	* @return Map<String,Object>    
	* @date 2016年5月10日 上午11:15:04 
	* @throws
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public static Map<String,Object> getCityByLocation(String location){
		String url  = Constant.GEOCODEREGEO_URL;
		logger.info("高德地图接口 - 逆地理编码 url>>>>>>>>>>>>>>>" + url);
		url = url.replace("{key}",Constant.GEOCODEREGEO_KEY);
		url = url.replace("{location}", location);
		
		
		/* 3.根据规则生成签名： 签名=MD5(请求参数（参数名的hash值升序排序）+（+号无需输入）私钥)； 例如：请求服务为testservice，请求参数分别为a=23，b=12，d=48，f=8，c=67；签名为bbbbb 则数字签名：sig=MD5(a=23&b=12&c=67&d=48&f=8bbbbb) */
		String sigStr = "key="+ Constant.GEOCODEREGEO_KEY + "&location="+location+Constant.GEOCODEREGEO_PRIVATE_KEY;
		logger.info("加密之前的参数::::::::>>>>>>>" + sigStr);
		String sig = RelyMD5Utils.MD5Encode(sigStr);
		logger.info("加密之后的参数::::::::>>>>>>>" + sig);
		url = url.replace("{sig}", sig);
		
		
		String result = "";
		try {
			result = RelyHttpClientUtils.sendGet(url, null, "utf-8");
		} catch (Exception e) {
			logger.error("发送消息异常.....",e);
		}
		logger.info("返回的参数  result>>>>>>>>>>" + result);
		String province = null;
		String cityName = null;
		Map<String,Object> resultMap = new JSONObject().parseObject(result, Map.class);
		if("1".equals(resultMap.get("status").toString())){
			Map<String,Object> regeocode = (Map<String,Object>)resultMap.get("regeocode");
			Map<String,Object> addressComponent = (Map<String,Object>)regeocode.get("addressComponent");
			province = addressComponent.get("province").toString();
			if(addressComponent.get("city") instanceof String){
				cityName = addressComponent.get("city").toString();
			}else{
				if(addressComponent.get("city") != null){
					List<Object> city = (List<Object>)addressComponent.get("city");
					if(CollectionUtils.isNotEmpty(city)){
						if(city.get(0) == null){
							cityName = province;
						}else{
							cityName = city.get(0).toString();
						}
					}else{
						cityName = province;
					}
				}
				
			}
			
			
			logger.info("cityName >>>>>>>>>>" + cityName);	
			Map<String,Object> resultObj = Maps.newHashMap();
			resultObj.put("cityName", cityName);
			resultObj.put("adcode", addressComponent.get("adcode"));
			return resultObj;
		}
		return null;
		
	}
	
	
	public static void main(String[] args) {
		Object a = null;
		if(a instanceof String){
			System.out.println("1111");	
		}
	}
	
}
