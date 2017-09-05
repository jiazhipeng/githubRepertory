package com.hy.weixin.utils;



import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.hy.common.utils.FilterStr;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.constant.Constant;
import com.hy.weixin.entity.AccessToken;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.entity.WeChatReceiveMsg;

public class WeiXinUtils {
	
	private static Logger log = LoggerFactory.getLogger(WeiXinUtils.class);
	

	/**
	 * 得到权限验证的token
	 * 
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws Exception
	 */
	public final static String ACC_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
	public static W3AccessToken getAccToken(String code) throws Exception {
		String tokenUrl = ACC_TOKEN_URL;
		tokenUrl = tokenUrl.replace("{appid}", Constant.APPID);
		tokenUrl = tokenUrl.replace("{secret}", Constant.APPSECRET);
		tokenUrl = tokenUrl.replace("{code}", code);
		
		JSONObject json = HttpClientUtils.sendSSL(tokenUrl, "GET", null);
		String access_token = json.getString("access_token");
//		String expires_in = json.getString("expires_in");
		String refresh_token = json.getString("refresh_token");
		String openid = json.getString("openid");
		// getNickname(access_token,openid);
		W3AccessToken accesstoken = new W3AccessToken();
		accesstoken.setAccess_token(access_token);
		// accesstoken.setExpires_in(Integer.parseInt(expires_in));
		accesstoken.setRefresh_token(refresh_token);
		accesstoken.setOpenid(openid);
		return accesstoken;
	}

	/**
	 * 得到权限验证的token
	 * 
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws Exception
	 */
	public final static String NICK_NAME_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN";;
	public static W3AccessToken getNickname(String accesstokenpar, String openid) throws Exception {
		log.info("WWWAccessToken getAccToken  into  ");
		String tokenUrl = NICK_NAME_URL;
		tokenUrl = tokenUrl.replace("{access_token}", AccessTokenUtils.getAccessToken());
		tokenUrl = tokenUrl.replace("{openid}", openid);

		log.info("tokenUrl>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + tokenUrl);
		JSONObject  json = HttpClientUtils.sendSSL(tokenUrl, "GET", null);
		String headimgurlreturn = json.getString("headimgurl");
		String nicknamereturn = json.getString("nickname");
		String openidreturn = json.getString("openid");
		log.info("getAccToken===openid=试试===============================[" + openid + "]");
		log.info("getAccToken====headimgurlreturn==得到=headimgurl============================[" + headimgurlreturn + "]");
		log.info("getAccToken===nicknamereturn================================[" + nicknamereturn + "]");
		log.info("getAccToken====openidreturn================================[" + openidreturn + "]");

		if(StringUtils.isNotEmpty(nicknamereturn)){
			nicknamereturn = FilterStr.filter(nicknamereturn);
		}

		W3AccessToken accesstoken3w = new W3AccessToken();
		accesstoken3w.setNicknamereturn(nicknamereturn);
		accesstoken3w.setOpenid(openid);
		accesstoken3w.setHeadimgurlreturn(headimgurlreturn);
		return accesstoken3w;
	}
	
	/**
	 * 获取token
	 */
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
	public static AccessToken getToken(String appid, String appsecret) throws Exception {
//		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
		String tokenUrl = TOKEN_URL;
		tokenUrl = tokenUrl.replace("{appid}",appid);
		tokenUrl = tokenUrl.replace("{secret}",appsecret);
		JSONObject json = HttpClientUtils.sendSSL(tokenUrl, "GET", null);
		String access_token = json.getString("access_token");
		// String expires_in= json.getString("expires_in");
		AccessToken accesstoken = new AccessToken();
		// accesstoken.setExpiresIn(Integer.parseInt(expires_in));
		accesstoken.setToken(access_token);
		return accesstoken;
	}
	
	public static void main(String[] args) throws Exception {
		getToken(Constant.APPID,Constant.APPSECRET);
	}
	
	/**
	 * 静默授权
	 */
	public final static String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={appid}&redirect_uri={redirect_uri}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	public static String getUrlBySnsapibase(String redirecturi) throws Exception {
		String weixinurl = AUTHORIZE_URL;
		weixinurl = weixinurl.replace("{appid}", Constant.APPID);
		weixinurl = weixinurl.replace("{redirect_uri}", redirecturi);
		log.info("[weixin]:==================" + weixinurl);
		return weixinurl;
	}
	
	/**
	 * 自定义分享和屏蔽的时候调用
	 * @param ACCESS_TOKEN
	 * @return
	 * @throws Exception
	 */
	public static String getTokenAPI(String ACCESS_TOKEN) throws Exception {
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + ACCESS_TOKEN + "&type=jsapi";
		// 建立连接
		log.info("ACCESS_TOKEN + =====================  " + ACCESS_TOKEN);

		JSONObject json = HttpClientUtils.sendSSL(tokenUrl, "GET", null);
		String ticket = json.getString("ticket");
		log.info("ticket=====================================================" + ticket);
		return ticket;
	}
	
	/**
	 * 手动授权
	 */
	public final static String GETURL_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={appid}&redirect_uri={redirect_uri}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public static String getUrl(String redirecturi) throws Exception {
		String weixinurl = GETURL_URL;
		weixinurl = weixinurl.replace("{appid}", Constant.APPID);
		weixinurl = weixinurl.replace("{redirect_uri}", redirecturi);
		log.info("[weixin]:==================" + weixinurl);
		return weixinurl;
	}
	
	/**
	 * 用SHA1算法生成安全签名
	 * 
	 * @param token
	 *            票据
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机字符串
	 * @param encrypt
	 *            密文
	 * @return 安全签名
	 * @throws NoSuchAlgorithmException
	 * @throws AesException
	 */
	public static String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
		String[] array = new String[] { token, timestamp, nonce };
		StringBuffer sb = new StringBuffer();
		// 字符串排序
		Arrays.sort(array);
		for (int i = 0; i < 3; i++) {
			sb.append(array[i]);
		}
		String str = sb.toString();
		// SHA1签名生成
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(str.getBytes());
		byte[] digest = md.digest();

		StringBuffer hexstr = new StringBuffer();
		String shaHex = "";
		for (int i = 0; i < digest.length; i++) {
			shaHex = Integer.toHexString(digest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexstr.append(0);
			}
			hexstr.append(shaHex);
		}
		return hexstr.toString();
	}

	
	public static WeChatReceiveMsg getMsg(InputStream request) throws Exception {

		WeChatReceiveMsg receiveMsg = new WeChatReceiveMsg();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(request);

		Element root = doc.getDocumentElement();// 获得根节点
		NodeList msgs = root.getChildNodes();
		for (int i = 0; i < msgs.getLength(); i++) {// 遍历元素
			Node msg = msgs.item(i);
			if (msg.getNodeType() == Node.ELEMENT_NODE) {// 如果是元素节点

				String nodeName = msg.getNodeName().trim();// 获得元素的名
				String nodeValue = msg.getTextContent();
				if (nodeName.equals("ToUserName")) {
					receiveMsg.setToUserName(nodeValue);
				} else if (nodeName.equals("FromUserName")) {
					receiveMsg.setFromUserName(nodeValue);
				} else if (nodeName.equals("CreateTime")) {
					receiveMsg.setCreateTime(nodeValue);
				} else if (nodeName.equals("MsgType")) {
					receiveMsg.setMsgType(nodeValue);
				} else if (nodeName.equals("Ticket")) {
					receiveMsg.setTicket(nodeValue);
				} else if (nodeName.equals("Event")) {
					receiveMsg.setEvent(nodeValue);
				} else if (nodeName.equals("EventKey")) {
					receiveMsg.setEventKey(nodeValue);
				} else if (nodeName.equals("Content")) {
					receiveMsg.setContent(nodeValue);
				}else if(nodeName.equals("Latitude")){//获取维度
					receiveMsg.setLatitude(nodeValue);
				}else if(nodeName.equals("Longitude")){//获取经度
					receiveMsg.setLongitude(nodeValue);
				}else if(nodeName.equals("Precision")){//获取精度
					receiveMsg.setPrecision(nodeValue);
				}
			}
		}
		return receiveMsg;
	}
}
