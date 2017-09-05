//package com.hy.gcar.common.controller;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hy.gcar.entity.Marketing;
//import com.hy.gcar.entity.Member;
//import com.hy.gcar.entity.Nation;
//import com.hy.gcar.service.marketing.MarketingService;
//import com.hy.gcar.service.member.MemberService;
//import com.hy.gcar.service.nation.NationService;
//import com.hy.weixin.entity.WeiXinCallBack;
//import com.techstar.common.utils.AccessTokenUtils;
//import com.techstar.common.utils.MapUtils;
//import com.techstar.common.utils.PropertiesLoader;
//import com.techstar.common.utils.StringUtils;
//import com.techstar.weixin.entity.Share;
//import com.techstar.weixin.entity.W3AccessToken;
//import com.techstar.weixin.entity.WeChatReceiveMsg;
//import com.techstar.weixin.entity.WxArticlesBack;
//import com.techstar.weixin.entity.WxBtnMsgBack;
//import com.techstar.weixin.entity.WxLinkBack;
//import com.techstar.weixin.entity.WxNewsBack;
//import com.techstar.weixin.utils.WeiXinUtils;
//import com.thoughtworks.xstream.XStream;
//
//@Controller
//public class CallBackControllerBack {
//
//	private final String SUBSCRIBE_EVENT = "subscribe";// 订阅事件
//	private final String UNSUBSCRIBE_EVENT = "unsubscribe";// 取消订阅事件
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private MemberService memberService;
//	@Autowired
//	private MarketingService marketingService;
//	@Autowired
//	private NationService nationService;
//
//	@RequestMapping("index")
//	public String index(@RequestParam(value = "url") String url, String callbackparam) {
//		try {
//			String newurl = URLEncoder.encode(url, "UTF-8");
//			logger.info("------------- " + url + " -----------");
//			String redirectPath = WeiXinUtils.getUrlBySnsapibase(newurl);
//			return "redirect:" + redirectPath;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//
//	}
//
//
//
//	@RequestMapping(value = "wxshare", method = RequestMethod.POST)
//	public @ResponseBody Map<String, String> getWxShare(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(value = "url") String url) throws Exception {
//		String s = null;
//		try {
//			s = new String(request.getParameter("url").getBytes("ISO8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		String ticket = AccessTokenUtils.getTicket();
//		Map<String, String> ret = Share.sign(ticket.toString(), s);
//		logger.info("============index========jsapi_ticket=========>>" + ret.get("jsapi_ticket"));
//		logger.info("============index========appId=========>>" + ret.get("appId"));
//		logger.info("============index========url=========>>" + s);
//		logger.info("============index========nonceStr=========>>" + ret.get("nonceStr"));
//		logger.info("============index========timestamp=========>>" + ret.get("timestamp"));
//		logger.info("============index========signature=========>>" + ret.get("sign"));
//		logger.info("============index========link=========>>" + ret.get("link"));
//		//openResponse.setUserdata(ret);
//		// mv.addAllObjects(ret);
//		return ret;
//	}
//
//	/**
//	 * look
//	 * 
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/toshare", method = RequestMethod.GET)
//	public String toshare(@RequestParam(value = "code") String code) throws Exception {
//		W3AccessToken acctoken = WeiXinUtils.getAccToken(code);
//		String oppid = acctoken.getOpenid();
//		logger.info("================================" + code);
//
//		logger.info("================================" + oppid);
//		return "/gcar/share/share";
//	}
//
//	/**
//	 * 验证token
//	 * 
//	 * @param signature
//	 * @param timestamp
//	 * @param nonce
//	 * @param echostr
//	 * @return
//	 * @throws NoSuchAlgorithmException
//	 */
//	@RequestMapping("/weixin")
//	public void weixin(WeiXinCallBack weiXinCallBack,HttpServletRequest request,HttpServletResponse response) {
//		
//		logger.info("weixin callback begin......");
//		PropertiesLoader load = new PropertiesLoader("weixin.properties");
//		String token = load.getProperty("token");
//		
//		String signature = weiXinCallBack.getSignature();// 微信加密签名
//		String timestamp = weiXinCallBack.getTimestamp(); //时间戳
//		String nonce = weiXinCallBack.getNonce();//随机数
//		
//		String tmpStr = "";
//		String returnMessage = null;
//		try {
//			tmpStr = getSHA1(token, timestamp, nonce);
//			if(!tmpStr.equals(signature)){
//				return;
//			}
//			String method = request.getMethod();
//			logger.info("@@@@@@@@@@@@method:"+method);
//			if(!method.equals("POST")){
//				this.returnMessage(weiXinCallBack.getEchostr(), response);
//			}
//			
//			WeChatReceiveMsg msg = getMsg(request.getInputStream());
//			this.logger.info("微信回掉消息为>>>>>>>>>>>:" + JSONObject.toJSONString(msg));
//			String msgType = msg.getMsgType();
//			
//			if (msgType.equals("event")) {//接收事件消息
//				String event = msg.getEvent();
//				if(SUBSCRIBE_EVENT.equalsIgnoreCase(event) || UNSUBSCRIBE_EVENT.equalsIgnoreCase(event)){
//					returnMessage = this.dingyue(msg);
//				}else if(event.equalsIgnoreCase("click")){
//					returnMessage = this.click(msg);
//				}else if(event.equalsIgnoreCase("LOCATION")){
//					returnMessage = this.getGeography(msg);
//				}
//			}else {//接收普通消息
//				msg.setEventKey("but_lianxikefu");
//				returnMessage = this.click(msg);
//			}
//			this.returnMessage(returnMessage, response);
//			
//		} catch (Exception e1) {
//			logger.error("callback validate exception {}",e1);
//		}
//		
//		
//		logger.info("weixin callback end......");
//	}
//
//
//
//	private void returnMessage(String message, HttpServletResponse response) throws IOException {
//		PrintWriter write = response.getWriter();
//		write.print(message);
//		write.flush();
//		write.close();
//	}
//
//	/**
//	 * 点击事件
//	* @Title: click 
//	* @Description: TODO
//	* @author q.p.x
//	* @param @param msg
//	* @param @param request
//	* @param @param response
//	* @param @param echostr    
//	* @return void    
//	* @date 2016年4月26日 下午4:48:42 
//	* @throws
//	 */
//	private String click(WeChatReceiveMsg msg) {
//		//https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543&token=&lang=zh_CN
//		
//		//https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141016&token=&lang=zh_CN
//		
//			String eventKey = msg.getEventKey();
//			String message = "";
//			if (!StringUtils.isBlank(eventKey)) {
//				if (eventKey.startsWith("but_tiyanshijia")) {//体验试驾
//					message = "终于等到你~~\n预约体验试驾，拨打服务热线：400-6091-510（工作日9:00-18:00)";
//				} else if (eventKey.startsWith("but_lianxikefu")) {//联系客服 
//					message = "我是社员甲，有问题尽管问我！\n点击左下角键盘直接给我留言；你也可以拨打官方服务热线 : 400-6091-510（工作日9:00-18:00)";
//				}else if(eventKey.startsWith("btn_daolujiuyuan")){//||道路救援
//					message = "拨打官方服务热线400-6091-510（工作日9:00—18:00），若满足救援条件管家2小时内免费救援。";
//				}else if (eventKey.startsWith("btn_yuyuechongdian")) {// 预约充电
//					message = "拨打官方服务热线400-6091-510（工作日9:00—18:00），专人管家为您取车充电。";
//				}
//			}
//			//发送 回复文本消息  
//			/**
//			 * <xml>
//				<ToUserName><![CDATA[toUser]]></ToUserName>
//				<FromUserName><![CDATA[fromUser]]></FromUserName>
//				<CreateTime>12345678</CreateTime>
//				<MsgType><![CDATA[text]]></MsgType>
//				<Content><![CDATA[你好]]></Content>
//				</xml>
//			*/
//			
////			Map<String,String> param = Maps.newHashMap();
////			param.put("ToUserName", msg.getToUserName());
////			param.put("FromUserName", msg.getFromUserName());
////			param.put("CreateTime", System.currentTimeMillis()/1000/60+"");
////			param.put("MsgType", "text");
////			param.put("Content", message);
//
//			WxBtnMsgBack wxmsg = new WxBtnMsgBack();
//			wxmsg.setToUserName(msg.getFromUserName());
//			wxmsg.setFromUserName(msg.getToUserName());
//			wxmsg.setCreateTime(System.currentTimeMillis()/1000/60/60+"");
//			wxmsg.setMsgType("text");
//	        wxmsg.setContent(message);
//			//hwInfo.setDiskInfos(diskInfos);
//			
//			XStream xStream = new XStream();  
//	        xStream.autodetectAnnotations(true);  
//	        String xml = xStream.toXML(wxmsg); 
//	        this.logger.info("发送的报文为>>>>>>>：" + xml);
//	        return xml; 
//	       
//	}
//	
//	/**
//	 * 关注/取消关注
//	 * @param msg
//	 * @param request
//	 * @param response
//	 * @param echostr
//	 * @throws Exception
//	 */
//	public String dingyue(WeChatReceiveMsg msg) throws Exception{
//		// 如果是事件推送信息
//		String event = msg.getEvent();
//		Member member = memberService.findMemberByOpenID(msg.getFromUserName());
//		W3AccessToken info = null;
//		try{
//			info = WeiXinUtils.getNickname(null,msg.getFromUserName());
//		}catch(Exception e){
//			info = new W3AccessToken();
//		}
//		
//		if(null == info)
//			info = new W3AccessToken();
//		if(member == null){//添加
//			
//			logger.info("execute {} add {} begin .....",event,msg.getFromUserName());
//			
//			int from = 2;// 自主关注 '用户来源 0:线下；1:活动；2:自主关注；3:推荐；',
//			long id = 0L;
//			String eventKey = msg.getEventKey();
//			if (!StringUtils.isBlank(eventKey)) {
//				if (eventKey.startsWith("qrscene_member_")) {// 推荐
//					from = 3;
//					id = Long.parseLong(eventKey.replace("qrscene_member_", ""));
//				} else if (eventKey.startsWith("qrscene_marketing_")) {// 活动
//					from = 1;
//					id = Long.parseLong(eventKey.replace("qrscene_marketing_", ""));
//				}
//			}
//			Member m = new Member();
//			m.setOpenid(msg.getFromUserName());
//			if (event.equals(SUBSCRIBE_EVENT)) {//关注
//				m.setFocusType(1);
//				
//			} else if (event.equals(UNSUBSCRIBE_EVENT)) {//取消关注
//				m.setFocusType(0);
//			}
//			
//			m.setName(info.getNicknamereturn());
//			m.setImageUrl(info.getHeadimgurlreturn());
//			m.setStatuts(1);
//			m.setFromMember(from);
//			if (from == 1) {
//				m.setMarketingId(id);
//				Marketing marketing = new Marketing();
//				marketing.setId(id);
//				List<Marketing> marketingList = marketingService.getMarketingList(marketing);
//				if (!CollectionUtils.isEmpty(marketingList)) {
//					marketing = marketingList.get(0);
//					m.setMarketingName(marketing.getName());
//				}
//			} else if (from == 3) {
//				m.setRecommendId(id);
//				Member member1 = new Member();
//				List<Member> memberList = memberService.selectByCondition(member1);
//				if (!CollectionUtils.isEmpty(memberList)) {
//					member1 = memberList.get(0);
//					m.setRecommendName(member1.getName());
//				}
//			}
//			m.setCreated(new Date());
//			m.setLasttimeModify(new Date());
//			//member.setImageQrcode(request.getServletContext().getRealPath("/"));
//			m.setType(0);
//			memberService.createPersonalMember(m);
//			
//			logger.info("execute {} add {} begin .....",event,msg.getFromUserName());
//		}else{//修改
//			logger.info("execute {} update {} begin .....",event,msg.getFromUserName());
//			Member member0 = new Member();
//			member0.setId(member.getId());
//			if (event.equals(SUBSCRIBE_EVENT)) {
//				member0.setFocusType(1);//关注
//			}else if (event.equals(UNSUBSCRIBE_EVENT)) {//取消关注
//				member0.setFocusType(0);
//			}
//			if(StringUtils.isBlank(member.getImageUrl()))
//				member0.setImageUrl(info.getHeadimgurlreturn());
//			//member0.setStatuts(1);
//			//member0.setOpenid(msg.getFromUserName());
//			member0.setLasttimeModify(new Date());
//			memberService.updatePersonalMember(member0);
//			logger.info("execute {} update {} end .....",event,msg.getFromUserName());
//		}
//		String xml = "ok";
//		if(event.equals(SUBSCRIBE_EVENT)) {
//			this.logger.info("关注的时候发送链接消息。。。。。。。。。。。。。。");
//			WxLinkBack x = new WxLinkBack();
//			x.setToUserName(msg.getFromUserName());
//			x.setFromUserName(msg.getToUserName());
//			x.setCreateTime(System.currentTimeMillis()/1000/60/60+"");
//			x.setMsgType("news");
//			x.setArticleCount("1");
//			
//			WxNewsBack news = new WxNewsBack();
//			news.setDescription("一个给聪明人看的h5");
//			news.setTitle("一个给聪明人看的h5");
//			news.setPicUrl("http://1mobility.cn/newV1/images/page.jpg");
//			news.setUrl("http://1mobility.cn/newV1/index.html");
//			
//			WxArticlesBack wxArticlesBack= new WxArticlesBack();
//			wxArticlesBack.setArticles(Arrays.asList(news));
//			
//			x.setArticles(wxArticlesBack);
//			
//			XStream xStream = new XStream();  
//	        xStream.autodetectAnnotations(true);  
//	        xml = xStream.toXML(x);
//	        logger.info("xml>>>>>>>>>>>>>>>>>>>>>" + xml);  
//		}
//		return xml;
//			
//	}
//
//	/**
//	 * @throws Exception 
//	 * 获取并且更新用户的地理位置
//	* @Title: getGeography 
//	* @Description: TODO
//	* @author q.p.x
//	* @param @param msg
//	* @param @param member    
//	* @return void    
//	* @date 2016年5月10日 下午2:22:36 
//	* @throws
//	 */
//	private String getGeography(WeChatReceiveMsg msg) throws Exception {
//		logger.info("获取地理位置开始......................");
////		try {
////			TimeUnit.SECONDS.sleep(0);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//		Member member = memberService.findMemberByOpenID(msg.getFromUserName());
//		Member member0 = new Member();
//		member0.setId(member.getId());
//		
//		String location = msg.getLongitude()+","+msg.getLatitude();
//		Map<String,Object> resultMap = MapUtils.getCityByLocation(location);
//		Object cityObj = resultMap.get("cityName");
//		if(cityObj != null){
//			//查询数据库获取cityID
//			Nation nation = new Nation();
//			nation.setCity(cityObj.toString());
//			List<Nation> nations = nationService.getNationByCity(nation);
//			if(CollectionUtils.isNotEmpty(nations)){
//				Nation n = nations.get(0);
//				member0.setCityCode(n.getCode());
//				member0.setCityId(n.getId()+"");
//				member0.setCityName(n.getCity());
//			}
//			member0.setLasttimeModify(new Date());
//			memberService.updatePersonalMember(member0);
//			logger.info("更新地理位置城市成功....参数为：" +  new JSONObject().toJSONString(member0));
//		}
//		return "ok";
//		
//		
//	}
//
//	public String weixin0(String signature, String timestamp, String nonce, String echostr)
//			throws NoSuchAlgorithmException {
//		String token = "weixin";
//		String tmpStr = getSHA1(token, timestamp, nonce);
//
//		if (tmpStr.equals(signature)) {
//			return echostr;
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 用SHA1算法生成安全签名
//	 * 
//	 * @param token
//	 *            票据
//	 * @param timestamp
//	 *            时间戳
//	 * @param nonce
//	 *            随机字符串
//	 * @param encrypt
//	 *            密文
//	 * @return 安全签名
//	 * @throws NoSuchAlgorithmException
//	 * @throws AesException
//	 */
//	public String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
//		String[] array = new String[] { token, timestamp, nonce };
//		StringBuffer sb = new StringBuffer();
//		// 字符串排序
//		Arrays.sort(array);
//		for (int i = 0; i < 3; i++) {
//			sb.append(array[i]);
//		}
//		String str = sb.toString();
//		// SHA1签名生成
//		MessageDigest md = MessageDigest.getInstance("SHA-1");
//		md.update(str.getBytes());
//		byte[] digest = md.digest();
//
//		StringBuffer hexstr = new StringBuffer();
//		String shaHex = "";
//		for (int i = 0; i < digest.length; i++) {
//			shaHex = Integer.toHexString(digest[i] & 0xFF);
//			if (shaHex.length() < 2) {
//				hexstr.append(0);
//			}
//			hexstr.append(shaHex);
//		}
//		return hexstr.toString();
//	}
//
//
//	private WeChatReceiveMsg getMsg(InputStream request) throws Exception {
//
//		WeChatReceiveMsg receiveMsg = new WeChatReceiveMsg();
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document doc = builder.parse(request);
//
//		Element root = doc.getDocumentElement();// 获得根节点
//		NodeList msgs = root.getChildNodes();
//		for (int i = 0; i < msgs.getLength(); i++) {// 遍历元素
//			Node msg = msgs.item(i);
//			if (msg.getNodeType() == Node.ELEMENT_NODE) {// 如果是元素节点
//
//				String nodeName = msg.getNodeName().trim();// 获得元素的名
//				String nodeValue = msg.getTextContent();
//				if (nodeName.equals("ToUserName")) {
//					receiveMsg.setToUserName(nodeValue);
//				} else if (nodeName.equals("FromUserName")) {
//					receiveMsg.setFromUserName(nodeValue);
//				} else if (nodeName.equals("CreateTime")) {
//					receiveMsg.setCreateTime(nodeValue);
//				} else if (nodeName.equals("MsgType")) {
//					receiveMsg.setMsgType(nodeValue);
//				} else if (nodeName.equals("Ticket")) {
//					receiveMsg.setTicket(nodeValue);
//				} else if (nodeName.equals("Event")) {
//					receiveMsg.setEvent(nodeValue);
//				} else if (nodeName.equals("EventKey")) {
//					receiveMsg.setEventKey(nodeValue);
//				} else if (nodeName.equals("Content")) {
//					receiveMsg.setContent(nodeValue);
//				}else if(nodeName.equals("Latitude")){//获取维度
//					receiveMsg.setLatitude(nodeValue);
//				}else if(nodeName.equals("Longitude")){//获取经度
//					receiveMsg.setLongitude(nodeValue);
//				}else if(nodeName.equals("Precision")){//获取精度
//					receiveMsg.setPrecision(nodeValue);
//				}
//			}
//		}
//		return receiveMsg;
//	}
//
//	public static void main(String[] args) {
//		// TokenThread tt = new TokenThread();
//		// String ticket = tt.ticket;
//	}
//}
