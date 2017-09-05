package com.hy.gcar.service.carType.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.dao.CarTypeMapper;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;

@Service(value = "tcCarTypeService")
public class CarTypeServiceImpl implements CarTypeService {

    @Autowired
    private CarTypeMapper<CarType>carTypeMapper;
    
    @Autowired
    ItemService itemService;
    
    @Autowired
    MemberItemShareService itemShareService;
    @Autowired
    ItemCartypeService itemCartypeService;
    @Autowired
    MemberItemService memeberItemService;
    @Autowired
    MemberItemCartypeService memberItemCartypeService;
    @Autowired
    MemberItemService memberItemService;
    @Autowired
    CarOperatePlanService carOperatePlanService;
    
    
    @Override
    public Integer insertSelective(CarType tcCarType) throws Exception {
           return this.carTypeMapper.insertSelective(tcCarType);
        }

    @Override
    public Integer insertBatch(List<CarType> tcCarType) throws Exception {
           return this.carTypeMapper.insertBatch(tcCarType) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.carTypeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.carTypeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CarType tcCarType) {
           return this.carTypeMapper.updateByPrimaryKeySelective(tcCarType);
    }

    @Override
    public CarType findById(Object id) {
           return (CarType) this.carTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarType> selectByCondition(CarType tcCarType) {
           return (List<CarType>) this.carTypeMapper.selectByCondition(tcCarType);
    }

	@Override
	public List<CarType> selectByItemId(Long itemId) {
		return carTypeMapper.selectByItemId(itemId);
	}

	

	@Override
	public List<CarType> selectCarTypes(String carType) {
		return carTypeMapper.selectCarTypes(carType);
	}

	@Override
	public CarType selectCarTypeDetail(CarType carType) {
		if(carType!=null&&carType.getId()!=null){
			if(carType.getMemberId()==null){
				CarType type = selectPower(carType.getId());
				type.setFlag(0);
				return type;
			}else{
				CarType type = selectPower(carType.getId());
				
				MemberItem tdMemeberItem = new MemberItem();
				tdMemeberItem.setMemberId(carType.getMemberId());
				List<MemberItem> list = memeberItemService.selectByCondition(tdMemeberItem);
				Long memberItemId=null;
				//如果会员已登录 且没有购买套餐  
				if(list==null || list.size()==0){//自己没有购买权益
					MemberItemShare trMemberItemShare = new MemberItemShare();
					trMemberItemShare.setMemberId(carType.getMemberId());
					List<MemberItemShare> shareList = itemShareService.selectByCondition(trMemberItemShare);
					if(CollectionUtils.isEmpty(shareList)){//不是别人的共用人
						type.setFlag(0);
						return type;
					}
					memberItemId=shareList.get(0).getMemberItemId();
				}else{
					memberItemId=list.get(0).getId();
				}
				if(null!=memberItemId){//查询套餐下所有车型
					MemberItem memberItem = memeberItemService.findById(memberItemId);
					ItemCartype itemCartype = new ItemCartype();
					itemCartype.setItemId(memberItem.getItemId());
					List<ItemCartype> itemcartypes = itemCartypeService.selectByCondition(itemCartype);
					for (ItemCartype itemCartype2 : itemcartypes) {
						if((itemCartype2.getCartypeId().longValue()==carType.getId().longValue())){
							//会员拥有该权益 可以用车
							type.setFlag(2);
							type.setDayPrice(itemCartype2.getDayPrice());
							type.setMemberItemId(memberItemId);
							return type;
						}
					}
				}
				//没有改车型权益 显示升级套餐
				type.setFlag(1);
				return type;
			}
		}
		
		return null;
	}
	
	public CarType selectPower(Long carTypeId){
		List<ItemCartype> items =itemCartypeService.selectByCarTypeId(carTypeId);
		List<String> itemName = new ArrayList<>();
		List<String> serviceName = new ArrayList<>();
		for (ItemCartype itemCartype : items) {
			itemName.add(itemCartype.getItemName());
		}
		CarType type = carTypeMapper.selectByPrimaryKey(carTypeId);
		if(type.getRoadRescue()==1){
			serviceName.add("道路救援");
		}
		if(type.getCharging()==1){
			serviceName.add("代充电");
		}
		type.setServiceNames(serviceName);
		type.setItemNames(itemName);
		return type;
	}

	/**
	 * 查询非启用车型
	 */
	public List<CarType> selectMyNotEnablecarTypes(Long memberId) {
		MemberItem tdMemeberItem = new MemberItem();
		tdMemeberItem.setMemberId(memberId);
		List<MemberItem> list = memeberItemService.selectByCondition(tdMemeberItem);
		Long memberItemId=null;
		List<CarType> myNotEnableCatTypes = new ArrayList<>();
		//如果会员已登录 且没有购买套餐  
		if(list==null || list.size()==0){//自己没有购买权益
			MemberItemShare trMemberItemShare = new MemberItemShare();
			trMemberItemShare.setMemberId(memberId);
			List<MemberItemShare> shareList = itemShareService.selectByCondition(trMemberItemShare);
			if(CollectionUtils.isEmpty(shareList)){//不是别人的共用人
				return null;
			}
			memberItemId=shareList.get(0).getMemberItemId();
		}else{
			memberItemId=list.get(0).getId();
		}
		if(null!=memberItemId){
			MemberItem memberItem = memeberItemService.findById(memberItemId);
			ItemCartype itemCartype = new ItemCartype();
			itemCartype.setItemId(memberItem.getItemId());
			//查询套餐下所有车型
			List<ItemCartype> allItemCartypes = itemCartypeService.selectByCondition(itemCartype);
			allItemCartypes = new CopyOnWriteArrayList<>(allItemCartypes);
			MemberItemCartype memberItemCartype = new MemberItemCartype();
			memberItemCartype.setMemberItemId(memberItemId);
			//查询我的启用车型
			List<MemberItemCartype> carTypeList = memberItemCartypeService.selectByCondition(memberItemCartype);
			
			
			for (MemberItemCartype ownCarType : carTypeList) {
				for (ItemCartype itemCartype2 : allItemCartypes) {
					if(ownCarType.getCarTypeId().longValue() == itemCartype2.getCartypeId().longValue()){
						allItemCartypes.remove(itemCartype2);
						break;
					}
				}
			}
			//所有非启用车型
			for (ItemCartype itemCartype3 : allItemCartypes) {
				CarType carType = this.findById(itemCartype3.getCartypeId());
				carType.setDayPrice(itemCartype3.getDayPrice());
				myNotEnableCatTypes.add(carType);
			}
		}
		return myNotEnableCatTypes;
	}

	@Override
	public Map<String, Object> getStoreOfCartype(String memberItemId, String useCarStartTime, String useCarEndTime) {
		
		Map<String, Object> returnMap = Maps.newHashMap();
		MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
		//查询权益下的所有车型
		ItemCartype itemCartype = new ItemCartype();
		itemCartype.setItemId(memberItem.getItemId());
		List<ItemCartype> itemCartypeList = itemCartypeService.selectByCondition(itemCartype);
		List<Long> ownCartypeIds = new ArrayList<>();
		for (ItemCartype itemCar : itemCartypeList) {
			ownCartypeIds.add(itemCar.getCartypeId().longValue());
		}
		
		
		//'适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；',
		JSONArray array = getSceneCartype(ownCartypeIds,useCarStartTime,useCarEndTime,memberItem.getItemId());
		returnMap.put("ownList", array);
		//查询所有的已上架的套餐
		 Item item = new Item();
		 item.setStatus(1);
		 List<Item> itemList = this.itemService.selectCarTypeList(item);
		 List<Long> notOwnCartypeIds = new ArrayList<>();
		 //查询非权益下的其他车型
		 for(Item model : itemList){
			 for(CarType cartype : model.getCarTypes()){
				 if(!ownCartypeIds.contains(cartype.getId().longValue())&&!notOwnCartypeIds.contains(cartype.getId().longValue())){
					notOwnCartypeIds.add(cartype.getId());
				 }
			 }
		}
		//封装非权益车型
		 JSONArray notOwnList = getSceneCartype(notOwnCartypeIds,null,null,memberItem.getItemId());
		 returnMap.put("notOwnList", notOwnList);
		 return returnMap;
	}

	@Override
	public List<CarType> selectCarTypeByIds(List<Long> carTypeIds) {

		return this.carTypeMapper.selectCarTypeByIds(carTypeIds);
	}

	private JSONArray getSceneCartype(List<Long> notOwnCartypeIds,String useCarStartTime,String useCarEndTime,Long itemId) {
//		List<Map<String, Object>> ownList = new ArrayList<>();
//		 Map<String, Object> map =  new LinkedHashMap<String, Object>();
		JSONArray arr = new JSONArray();
		 List<CarType> carTypes0 = null;
		List<CarType> carTypes1 = null;
		List<CarType> carTypes2 = null;
		List<CarType> carTypes3 = null;
		List<CarType> carTypes4 = null;
		List<CarType> carTypes5 = null;
		JSONObject json0 = null;
		JSONObject json1 = null;
		JSONObject json2 = null;
		JSONObject json3 = null;
		JSONObject json4 = null;
		JSONObject json5 = null;
		for (Long cartypeId : notOwnCartypeIds) {
			//查询是否有库存
			CarType cart = this.findById(cartypeId);
			cart.setCartypeId(cart.getId()+"");
			ItemCartype itemCartype = new ItemCartype();
			itemCartype.setCartypeId(cartypeId);
			itemCartype.setItemId(itemId);
			List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(itemCartype );
			if(CollectionUtils.isNotEmpty(itemCartypes)){
				cart.setDayPrice(itemCartypes.get(0).getDayPrice());
			}
			if(StringUtils.isNotBlank(useCarStartTime)&&StringUtils.isNotBlank(useCarEndTime)){
				Date start = DateUtils.parseDate(useCarStartTime);
				Date end = DateUtils.parseDate(useCarEndTime);
				boolean isStore = carOperatePlanService.isStore(cartypeId, start, end);
				cart.setStore(isStore);
			}
			
//			 '适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；',
			if(0==cart.getScene()){
				 carTypes0 = addCarType(carTypes0, cart);
				 json0 = jsonPut(carTypes0, json0,"限行无忧");
//				 map.put("0", carTypes0);
			 }
			 if(1==cart.getScene()){
				 carTypes1 = addCarType(carTypes1, cart);
				 json1 = jsonPut(carTypes1, json1,"尊贵出行");
//				 map.put("1", carTypes1);
			 }
			 if(2==cart.getScene()){
				 carTypes2 = addCarType(carTypes2, cart);
				 json2 = jsonPut(carTypes2, json2,"畅享旅途");
//				 map.put("2", carTypes2);
			 }
			 if(3==cart.getScene()){
				 carTypes3 = addCarType(carTypes3, cart);
				 json3 = jsonPut(carTypes3, json3,"豪华商务");
//				 map.put("3", carTypes3);
			 }
			 if(4==cart.getScene()){
				 carTypes4 = addCarType(carTypes4, cart);
				 json4 = jsonPut(carTypes4, json4,"彰显个性");
//				 map.put("4", carTypes4);
			 }
			 if(5==cart.getScene()){
				 carTypes5 = addCarType(carTypes5, cart);
				 json5 = jsonPut(carTypes5, json5,"速度激情");
//				 map.put("5", carTypes5);
			 }
		}
		jsonArrayAdd(arr, json0);
		jsonArrayAdd(arr, json1);
		jsonArrayAdd(arr, json2);
		jsonArrayAdd(arr, json3);
		jsonArrayAdd(arr, json4);
		jsonArrayAdd(arr, json5);
//		ownList.add(map);
		return arr;
	}
	private void jsonArrayAdd(JSONArray arr, JSONObject json) {
		if(json!=null){
			arr.add(json);
		}
	}
	private JSONObject jsonPut(List<CarType> carTypes, JSONObject json,String type) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (CarType cartype : carTypes) {
			Map<String, Object> map =  new HashMap<String, Object>();
			map.put("brand", cartype.getBrand());
			map.put("carType", cartype.getCarType());
			map.put("cartypeId", cartype.getId()+"");
			map.put("dayPrice", cartype.getDayPrice());
			map.put("store", cartype.isStore());
			list.add(map);
		}
		if(json==null){
			 json= new JSONObject();
			 json.put("type", type);
			 json.put("data", list);
		 }else{
			 json.put("data", list);
		 }
		return json;
	}
	private List<CarType> addCarType(List<CarType> carTypes, CarType cart) {
		if(carTypes==null){
			 carTypes=new ArrayList<>();
		 }
		 carTypes.add(cart);
		return carTypes;
	}

}
