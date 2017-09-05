package com.hy.gcar.web.wechat.butlerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;

/**
 * 取车任务controller
 * @author gongbw
 *
 */
@Controller
@RequestMapping("/wechat/butlerTask")
public class BulterTaskController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/toButlerManager")
	public String toButlerTaskList(ButlerTask butlerTask, ModelMap map) {
		this.logger.info("跳转进入管家主界面---查看用户ID为--"+butlerTask.getMemberId());
		map.put("loginId", butlerTask.getMemberId());
		try {
			Member member = memberService.findMemberByID(Long.decode(butlerTask.getMemberId()));
			map.put("isCustomer", member.getIsCustomer());
		} catch (Exception e) {
			logger.debug("查询member错误");
		}
		return "gcar/butler/manager";
	}
}
