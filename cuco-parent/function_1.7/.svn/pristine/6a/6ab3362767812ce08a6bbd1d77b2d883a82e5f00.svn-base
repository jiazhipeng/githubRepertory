package com.hy.gcar.service.marketing;

import java.util.List;

import com.hy.gcar.entity.Marketing;


/**
 * 活动接口
 * @author leic
 *
 */
public interface MarketingService {

	/**
	 * 创建活动
	 * @param marketing
	 */
	public Marketing createMarketing(Marketing marketing) throws Exception;
	
	/**
	 * 分页查询活动列表
	 * @param marketing
	 * @return
	 * @throws Exception
	 */
	public List<Marketing> getMarketingList(Marketing marketing) throws Exception;
	
	/**
	 * 获取微信带参数二维码
	 * @param json
	 * @return
	 */
	public String getWechatQrCode(String json);
	
	/**
	 * 修改活动
	 * @param marketing
	 */
	public Marketing updateMarketing(Marketing marketing) throws Exception;
	
	/**
	 * 获取活动效果统计数据（指定活动带来游客、用户、会员、VIP等数量）
	 * @param marketing
	 * @return json数据
	 */
	public String getMarketingStatis(Marketing marketing) throws Exception;
	
}
