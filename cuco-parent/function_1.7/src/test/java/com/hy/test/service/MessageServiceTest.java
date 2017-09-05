package com.hy.test.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;

/**
 * 微信消息推送测试类
 * @author q.p.x
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath*:/spring-junit-config/applicationContext-test.xml"}) //加载配置文件  
public class MessageServiceTest {
	@Autowired
	CarTypeService carTypeService;
	@Autowired
	ItemService itemService;
	@Autowired
	MemberService memberService;
	@Autowired
	MemberItemService memberItemService;
	
	@Test
	public void carTypeInsert() throws Exception{
		CarType carType = new CarType();
		carType.setBrand("特斯拉");
		carType.setCartypeParam("轮毂");
		carType.setCarType("MODE X 90D");
		carType.setCartypeColor("red");
		carType.setCartypeIntroduce("特斯拉牛奔的车");
		carType.setCartypePower(1);
		carType.setCharging(1);
		carType.setCreated(new Date());
		carType.setImageUrl("www.baidu.com");
		carType.setRoadRescue(1);
		carType.setVidioUrl("www.baidu.com");
		
		carTypeService.insertSelective(carType);
		
	}
	@Test
	public void itemInsert() throws Exception{
		for (int i = 0; i < 5; i++) {
			
			Item item = new Item();
			BigDecimal changePrice = new BigDecimal(500);
			item.setChangePrice(changePrice);
			item.setCreated(new Date());
			BigDecimal driver = new BigDecimal(200);
			item.setDriver(driver);
			item.setEnableCount(5);
			item.setLasttimeModify(new Date());
			item.setLevel(1);
			item.setLevelName("套餐"+i);
			item.setLevelUrl("www.baidu.com");
			item.setModifier("zhangsan");
			item.setModifierId("45s5d5sd");
			BigDecimal price = new BigDecimal(500);
			
			item.setPrice(price);
			item.setStatus(1);
			itemService.insertSelective(item);
		}
		
	}
	@Test
	public void memberInsert() throws Exception{
		for (int i = 0; i < 10; i++) {
			
			Member m = new Member();
			m.setCityCode("110000");
			m.setCityId("2");
			m.setCityName("北京");
			m.setCreated(new Date());
			m.setFocusType(1);
			m.setFromMember(1);
			m.setImageQrcode("www.baidu.com");
			m.setImageUrl("www.baidu.com");
			m.setInfoAudit(1);
			m.setIsButler(1);
			m.setIsSelles(1);
			m.setIsTestDriver(0);
			m.setLasttimeModify(m.getCreated());
			m.setMarketingId(1L);
			m.setMarketingName("A活动");
			m.setMobile("1302108017"+i);
			m.setModifier("zhangsan");
			m.setModifierId("sdsd4s4d5sdsd");
			m.setName("极车会员"); 
			m.setOpenid("sdsdsdsdasd1243213123e"+i);
			m.setRecommendId(545454L);
			m.setRecommendName("lisi");
			m.setSex(1);
			m.setStatuts(1);
			m.setSysuserId("asd4asd54a4sdasd");
			m.setSysuserName("AAAAAAAAAA");
			m.setType(0);
			memberService.createPersonalMember(m);
		}
		
	}
	
	@Test
	public void findCartypeList(){
		List<CarType> carTypes = carTypeService.selectCarTypes("");
		for (CarType carType : carTypes) {
			System.out.println(carType.getDayPrice());
			System.out.println(carType.toString());
		}
	}
	
	@Test
	public void findCartypeDetail(){
		CarType carType = new CarType();
		carType.setId(3L);
		carType.setMemberId(14L);
		CarType carType2 = carTypeService.selectCarTypeDetail(carType);
		System.out.println(carType2.toString());
	}
	
	
	@Test
	public void findMyPower(){
		MemberItem item = new MemberItem();
		item.setMemberId(14L);
		MemberItem memberItem = memberItemService.selectMyPowerList(item);
		System.out.println();
	}
	@Test
	public void findPowerDetail(){
		MemberItem item = new MemberItem();
		item.setMemberId(14L);
		item.setItemId(1L);
		MemberItem memberItem = memberItemService.selectByMemberItemId(item);
		
		System.out.println();
	}

}
