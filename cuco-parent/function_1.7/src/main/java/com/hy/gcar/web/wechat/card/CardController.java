package com.hy.gcar.web.wechat.card;

import java.util.*;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.hy.common.utils.StringUtils;
import com.hy.constant.Constant;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Card;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.card.CardService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.order.OrderService;
import com.hy.gcar.utils.ResultUtils;

@Controller
@RequestMapping("/wechat/card")
public class CardController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CardService cardService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberService memberService;

	
	/**
	 * 跳转销售名片详情
	 * @param 
	 * @return 
	 * @throws  
	 * @throws Exception 
	 */
	@RequestMapping(value="/toCardDetail")
	public String toCardDetail(String memberId,ModelMap map) throws Exception{
		this.logger.info("跳转销售名片详情---登录人ID为-"+memberId);
		
		Member m = this.memberService.findMemberByID(Long.parseLong(memberId));
		Card card = new Card();
		card.setCardMobile(m.getMobile());
		card.setStatus(0);
		List<Card> cardList = this.cardService.selectByCondition(card);
		if(CollectionUtils.isNotEmpty(cardList)){
			//销售名片
			card = cardList.get(0);
		}else{
			this.logger.error("没有名片---");
			return null;
		}
		
		//查询该名片产生的销售线索
		Order order = new Order();
		order.setOrderFrom(6);//代表名片
		order.setOrderFromId(card.getId());//销售名片Id
		List<Order> orderList = this.orderService.getOrderList(order);
		for(Order ord : orderList){
			//查询套餐
			Item item = this.itemService.findById(ord.getItemId());
			if(null!=item){
				ord.setItemName(item.getLevelName());
			}else{
				ord.setItemName("-");
			}
			if(null==ord.getSureName()){
				ord.setSureName(ord.getMemberName());
			}
		}
		//获取前端h5链接地址
		String url = Constant.load.getProperty("h5_url");
		//前端返值
		map.put("url", url);
		map.put("card", card);
		map.put("orderList", orderList);
		return "gcar/card/cardDetail";
	}
	
	 /**
	  * 跳转进入销售名片提交订单页面
	 * @param card
	 * @return
	 */
	@RequestMapping("/toCreateOrder")
	 public  @ResponseBody Map<String,Object> toCreateOrder(Card card){
		 if(StringUtils.isEmpty(card.getId()+"")){
	    		return ResultUtils.fail("销售名片ID为空---");
	    }
		//查询名片状态
		 Card card_data = this.cardService.findById(card.getId());
		 if(null==card_data){
			 //表示名片被删除了
			 return ResultUtils.fail("100001","销售名片被停止，跳转官网");
		 }
		 if(1==card_data.getStatus()){
			 //表示名片是停止
			 return ResultUtils.fail("100001","销售名片被停止，跳转官网");
		 }
		 Map<String,Object> resultMap = Maps.newHashMap();
		 //根据ID查询销售名片详情
		 card = this.cardService.findById(card.getId());
		 //查询所有的已上架的套餐
		 Item item = new Item();
		 item.setStatus(1);
		 List<Item> itemList = this.itemService.selectCarTypeList(item);
		 //组装数据--名片信息
		 resultMap.put("cardId", card.getId());//名片ID
		 resultMap.put("cardName", card.getCardName());//名片名称
		 resultMap.put("imgUrl", card.getImgUrl());//名片图片地址
		 resultMap.put("cardMobile", card.getCardMobile());//名片手机号
		 //组装数据--套餐信息
		 List<Map<String, Object>> returnList = new ArrayList<>();
		 for(Item model : itemList){
			 Map<String, Object> map =  new LinkedHashMap();
			 map.put("price", model.getPrice());
			 map.put("dayPriceMinimum", model.getDayPriceMinimum());
			 map.put("itemName", model.getLevelName());
			 List<CarType> carTypes1 = new ArrayList<>();
			 List<CarType> carTypes2 = new ArrayList<>();
			 List<CarType> carTypes3 = new ArrayList<>();
			 List<CarType> carTypes4 = new ArrayList<>();
			 List<CarType> carTypes5 = new ArrayList<>();
			 List<CarType> carTypes6 = new ArrayList<>();
			
			 for(CarType cart : model.getCarTypes()){
//				    private Integer scene; //适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；
				 if(0==cart.getScene()){
					 carTypes1.add(cart);
//					 map.put("新能源count", carTypes1.size());
				 }
				 if(1==cart.getScene()){
					 carTypes2.add(cart);
//					 map.put("商务轿车count", carTypes2.size());
				 }
				 if(2==cart.getScene()){
					 carTypes3.add(cart);
//					 map.put("SUVcount", carTypes3.size());
				 }
				 if(3==cart.getScene()){
					 carTypes4.add(cart);
//					 map.put("MPVcount", carTypes4.size());
				 }
				 if(4==cart.getScene()){
					 carTypes5.add(cart);
//					 map.put("炫目轿跑count", carTypes5.size());
				 }
				 if(5==cart.getScene()){
					 carTypes6.add(cart);
//					 map.put("性能跑车count", carTypes6.size());
				 }
			 }
			 map.put("限行无忧", carTypes1);
			 map.put("尊贵出行", carTypes2);
			 map.put("畅享旅途", carTypes3);
			 map.put("豪华商务", carTypes4);
			 map.put("彰显个性", carTypes5);
			 map.put("速度激情", carTypes6);
			 returnList.add(map);
		 }
		 resultMap.put("itemList", returnList);
		 return ResultUtils.success(resultMap);
	 }

	/**
	 * 跳转进入销售名片提交订单页面
	 * @param
	 * @return
	 */
	@RequestMapping("/toProductDetails")
	public  @ResponseBody Map<String,Object> toProductDetails(){
		Map<String,Object> resultMap = Maps.newHashMap();
		//查询所有的已上架的套餐
		Item item = new Item();
		item.setStatus(1);
		List<Item> itemList = this.itemService.selectCarTypeList(item);

		//查询所有可用车型
//		 List<CarType> carTypes = carTypeService.selectByCondition(new CarType());
		//Set<CarType> carTypeSet = new HashSet<CarType>();
		Set<CarType> carTypeSet = new LinkedHashSet<CarType>();
		for(Item model : itemList){
			for(CarType cart : model.getCarTypes()){
				carTypeSet.add(cart);
			}
		}


		//组装数据--套餐信息
		List<Map<String, Object>> returnList = new ArrayList<>();
		for(Item model : itemList){
			Map<String, Object> map =  new LinkedHashMap();
			map.put("price", model.getPrice());
			map.put("dayPriceMinimum", model.getDayPriceMinimum());
			map.put("itemName", model.getLevelName());
			List<Map<String,Object>> carTypeList = Lists.newLinkedList();

			for(CarType carType:carTypeSet){
				Map<String,Object> carTypeMap = Maps.newConcurrentMap();
				carTypeMap.put("carTypeName", carType.getBrand()+ " " +carType.getCarType());
				carTypeMap.put("isContain", false);
				for(CarType cart : model.getCarTypes()){
					if(cart.getId().longValue() == carType.getId().longValue()){
						carTypeMap.put("isContain", true);
						break;
					}
				}

				carTypeList.add(carTypeMap);

			}

			map.put("carTypes", carTypeList);
			returnList.add(map);
		}
		resultMap.put("itemList", returnList);
		return ResultUtils.success(resultMap);
	}


	/**
	  * 转发成功之后，card表上的转发次数加一
	 * @param card
	 * @return
	 */
	@RequestMapping("/addForwardCount")
	public  @ResponseBody Map<String,Object> addForwardCount(Card card){
		 if(StringUtils.isEmpty(card.getId()+"")){
	    		return ResultUtils.fail("销售名片ID为空---");
	    }
		card = this.cardService.findById(card.getId());
		if(null!=card){
			int forwardCount = 0;
			if(null!=card.getForwardCount()){
				forwardCount = card.getForwardCount();
			}
			Card card_new = new Card();
			card_new.setId(card.getId());
			card_new.setForwardCount(forwardCount+1);
			this.cardService.updateByPrimaryKeySelective(card_new);
		}
		return ResultUtils.success(null);
	}
}
