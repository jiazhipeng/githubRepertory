package com.hy.gcar.web.wechat.memberInfo;

import java.util.Map;

import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.utils.ResultUtils;

@Controller
@RequestMapping("/wechat/memberInfo")
public class MemberInfoWechatController {
	
	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private MemberService memberService;
	/**
	 * @Title: findMemberInfoByMemberID  
	 * @Description: 上传身份证、驾驶证等信息
	 * @param:@param memberID
	 * @param:@return    
	 * @return:MemberInfo 
	 * @createTime:2016年8月13日 下午1:17:00
	 * @throws
	 */
	@RequestMapping("/uploadMemberInfo")
	public  @ResponseBody Map<String,Object> uploadMemberInfo(MemberInfo memberInfo) {
		
		try {
			memberInfoService.uploadMemberInfo(memberInfo);
			return ResultUtils.success("上传成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.fail("error", "上传失败!");
		}
	}
	
	/**
	 * 
	 * @Title: editMemberInfo 
	 * @Description: 编辑会员资料信息
	 * @param @param memberInfo
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 下午1:36:53 
	 * @throws
	 */
	@RequestMapping("/editMemberInfo")
	public String editMemberInfo(MemberInfo memberInfo,ModelMap map){
		try {
			MemberInfo mInfo = memberInfoService.findMemberInfoByMemberID(memberInfo.getMemberId());
			Member member = memberService.findMemberByID(memberInfo.getMemberId());
			map.put("member", member);
			boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(mInfo);
			map.put("memberId", memberInfo.getMemberId());

			if(mInfo != null){
				map.put("memberInfo", mInfo);
			}
			if(isPerfect == false || member.getUseCarApproved().intValue()== 1 || member.getUseCarApproved().intValue() == 3){
				map.put("isPerfect", "未完善");
				return "gcar/member/editMemberInfo";
			}
			return "gcar/member/seeMemberInfo";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
