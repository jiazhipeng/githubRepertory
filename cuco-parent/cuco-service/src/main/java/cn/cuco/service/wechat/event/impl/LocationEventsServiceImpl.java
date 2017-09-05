package cn.cuco.service.wechat.event.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import cn.cuco.common.utils.map.MapUtils;
import cn.cuco.entity.City;
import cn.cuco.entity.Member;
import cn.cuco.service.basic.dictionary.CityService;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.wechat.event.EventsService;
import cn.cuco.wechat.entity.WeChatReceiveMsg;

@Service
public class LocationEventsServiceImpl implements EventsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CityService cityService;
	
	@Override
	public String response(WeChatReceiveMsg msg) throws Exception {
		logger.info("获取地理位置开始......................");
//		try {
//			TimeUnit.SECONDS.sleep(0);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		
		String location = msg.getLongitude()+","+msg.getLatitude();
		Map<String,Object> resultMap = MapUtils.getCityByLocation(location);
		if(resultMap == null){
			return "success";
		}
		Object adcode = resultMap.get("adcode");
		if(adcode == null){
			return "success";
		}
		//查询数据库获取cityID
		List<City> cityList = cityService.getCityByCityCode(adcode.toString());
		if(cityList == null){
			return "success";
		}
		City city = cityList.get(0);
		Member getMember = memberService.getMemberByOpenId(msg.getFromUserName());
		if(getMember == null){
			return "success";
		}
		
		if(StringUtils.isNotBlank(getMember.getCityCode())){
			return "success";
		}
		Member member = new Member();
		member.setId(getMember.getId());
		member.setCityCode(city.getCode());
		member.setCityId(city.getId());
		member.setCityName(city.getName());
		member.setLasttimeModify(new Date());
		memberService.updateMember(member);
		logger.info("更新地理位置城市成功....参数为：" +  JSONObject.toJSONString(member));
		return "success";
	}

}
