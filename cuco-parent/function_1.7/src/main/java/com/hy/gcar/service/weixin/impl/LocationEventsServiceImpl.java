package com.hy.gcar.service.weixin.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.MapUtils;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.weixin.entity.WeChatReceiveMsg;
@Service
public class LocationEventsServiceImpl implements EventsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
	@Autowired
	private NationService nationService;
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
		Nation nation = nationService.getNationByCityCode(adcode.toString());
		if(nation == null){
			return "success";
		}
		Member member = memberService.findMemberByOpenID(msg.getFromUserName());
		if(member == null){
			return "success";
		}
		
		if(StringUtils.isNotBlank(member.getCityCode())){
			return "success";
		}
		Member member0 = new Member();
		member0.setId(member.getId());
		member0.setCityCode(nation.getCode());
		member0.setCityId(nation.getId()+"");
		member0.setCityName(nation.getName());
		member0.setLasttimeModify(new Date());
		memberService.updatePersonalMember(member0);
		logger.info("更新地理位置城市成功....参数为：" +  JSONObject.toJSONString(member0));
		return "success";
	}

}
