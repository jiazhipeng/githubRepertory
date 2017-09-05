package com.hy.gcar.service.marketing.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.MarketingMapper;
import com.hy.gcar.entity.Marketing;
import com.hy.gcar.service.marketing.MarketingService;

/**
 * 活动service
 *
 */
@Service
public class MarketingServiceImpl implements MarketingService {

	@Autowired
	private MarketingMapper marketingMapper;
	
	@Override
	public Marketing createMarketing(Marketing marketing) throws Exception {
		marketingMapper.insertSelective(marketing);
		long marketingid = marketing.getId();
		return marketingMapper.selectByPrimaryKey(marketingid);
	}

	@Override
	public List<Marketing> getMarketingList(Marketing marketing) throws Exception {
		List<Marketing> marketinglist = marketingMapper.getMarketingList(marketing);
		return marketinglist;
	}

	@Override
	public String getWechatQrCode(String json) {
		
		return null;
	}

	@Override
	public Marketing updateMarketing(Marketing marketing) throws Exception {
		marketingMapper.updateByPrimaryKeySelective(marketing);
		long marketingid = marketing.getId();
		return marketingMapper.selectByPrimaryKey(marketingid);
	}

	@Override
	public String getMarketingStatis(Marketing marketing) throws Exception {
		StringBuffer json=new StringBuffer("[");
		List<Marketing> marketinglist = new ArrayList<Marketing>();//marketingMapper.getMarketingStatis(marketing);
		for(long i = 0;i < 5;i++){
			marketing = new Marketing();
			marketing.setMemberType(i);
			marketing.setTypeCount((i+1)*10L);
			marketinglist.add(marketing);
		}
		if(!CollectionUtils.isEmpty(marketinglist)){
			Long total = 0L;
			for(Marketing market : marketinglist){
				total += market.getTypeCount();
			}
			for(Marketing market:marketinglist){
				json.append("['"+market.getMemberType()+"',"+market.getTypeCount()/total.doubleValue()+"],");
			}
			json = new StringBuffer(json.substring(0, json.lastIndexOf(",")));
		}		
		json.append("]");
		//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+json);
		return json.toString();
	}

}
