package com.hy.gcar.service.member;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.OpenResponse;


/**
 * 
 * @author 海易出行
 *
 */
public interface MemberAppService {

	/**
	 * 上传会员头像
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Member upload(HttpServletRequest request) throws Exception;
	
	/**
	 * 保存图片到本地
	 * @param stream
	 * @param path
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String saveFileFromInputStream(InputStream stream, String path,  String filename) throws Exception;

	/**
	 * 验证个人信息
	 * @param mobile
	 * @return
	 */
	public OpenResponse getverificationMain(String mobile);
}
