package com.hy.gcar.service.item.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.MemeberItemMapper;
import com.hy.gcar.entity.BasicGarageImage;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basicGarageImage.BasicGarageImageService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.powerUserd.PowerUsedService;

@Service(value = "tdMemeberItemService")
public class MemberItemServiceImpl implements MemberItemService {

    @Autowired
    private MemeberItemMapper<MemberItem>memeberItemMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private MemberItemCartypeService memberItemCartypeService;
    @Autowired
    private BasicGarageImageService basicGarageImageService;
    @Autowired
    private CarOperateService carOperateService;
    @Autowired
    private MemberItemShareService memberItemShareService;
    @Autowired
    private PowerUsedService powerUsedService;
    
    
    @Override
    public MemberItem insertSelective(MemberItem tdMemeberItem) throws Exception {
    	   this.memeberItemMapper.insertSelective(tdMemeberItem);
           return this.memeberItemMapper.selectByPrimaryKey(tdMemeberItem.getId());
        }

    @Override
    public Integer insertBatch(List<MemberItem> tdMemeberItem) throws Exception {
           return this.memeberItemMapper.insertBatch(tdMemeberItem) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.memeberItemMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.memeberItemMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public MemberItem updateByPrimaryKeySelective(MemberItem tdMemeberItem) {
    	   this.memeberItemMapper.updateByPrimaryKeySelective(tdMemeberItem);
           return this.memeberItemMapper.selectByPrimaryKey(tdMemeberItem.getId());
    }

    @Override
    public MemberItem findById(Object id) {
           return (MemberItem) this.memeberItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberItem> selectByCondition(MemberItem tdMemeberItem) {
           return (List<MemberItem>) this.memeberItemMapper.selectByCondition(tdMemeberItem);
    }

	@Override
	public MemberItem selectMyPowerList(MemberItem memberItem) {
		//入参memberid
		
//		查询会员权益
		List<MemberItem> list = this.memeberItemMapper.selectByCondition(memberItem);
	
		Long memberItemId=null;
		Long itemId=null;
		//会员没有购买套餐
		if(list==null ||list.size()==0){//查询共有人
			MemberItemShare trMemberItemShare = new MemberItemShare();
			trMemberItemShare.setMemberId(memberItem.getMemberId());
			List<MemberItemShare> shareList = memberItemShareService.selectByCondition(trMemberItemShare);
			if(CollectionUtils.isEmpty(shareList)){
				return null;
			}
			memberItemId = shareList.get(0).getMemberItemId();
			MemberItem t = new MemberItem();
			t.setId(memberItemId);
			List<MemberItem> itemList = memeberItemMapper.selectByCondition(t);
			if(CollectionUtils.isNotEmpty(itemList)){
				itemId=itemList.get(0).getItemId();
				memberItem.setItemId(itemList.get(0).getItemId());
				memberItem.setItemName(itemList.get(0).getItemName());
			}
		}else{
			itemId=list.get(0).getItemId();
			memberItemId=list.get(0).getId();
			memberItem.setItemId(list.get(0).getItemId());
			memberItem.setItemName(list.get(0).getItemName());
		}
		memberItem.setId(memberItemId);
		
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setMemberItemId(memberItem.getId());
		//查询使用中的车
		List<PowerUsed> PowerUsedList = powerUsedService.selectPowerUsedOfUseingCars(powerUsed);
		memberItem.setPowerUseds(PowerUsedList);
		
	/*	MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
		tdMemberItemCartype.setMemberItemId(memberItemId);
		List<MemberItemCartype> cartypes = memberItemCartypeService.selectByCondition(tdMemberItemCartype );*/
		Item item = itemService.findById(itemId);
		Integer itemEnableCount = item.getEnableCount();
		memberItem.setEnableCount(itemEnableCount);
		
		/*for (MemberItemCartype memberItemCartype : cartypes) {
			// 待使用
			if(memberItemCartype.getStatus()==1||memberItemCartype.getStatus()==0){
				BasicGarageImage tcBasicGarageImage = new BasicGarageImage();
				tcBasicGarageImage.setType(1);
				List<BasicGarageImage> basic = basicGarageImageService.selectByCondition(tcBasicGarageImage);
				if(CollectionUtils.isNotEmpty(basic)){
					memberItemCartype.setCarTypeImageUrl(basic.get(0).getImage());
				}
			}else{
				CarOperate carOperate = carOperateService.findById(memberItemCartype.getCarOperateId());
				memberItemCartype.setCarTypeImageUrl(carOperate.getImageUrl());
			}
		}
		memberItem.setCartypes(cartypes);*/
		
		
		return  memberItem;
				
	}

	@Override
	public MemberItem selectByMemberItemId(MemberItem memberItem) {
//		MemberItem t = new MemberItem();
//		t.setItemId(itemId);
		/*List<MemberItem> list = memeberItemMapper.selectByCondition(memberItem);
		if(list.size()>0){
			MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
			tdMemberItemCartype.setMemberItemId(list.get(0).getId());
			List<MemberItemCartype> cartypes = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
			memberItem.setCartypes(cartypes);
			memberItem.setBalance(list.get(0).getBalance());
			memberItem.setDeposit(list.get(0).getDeposit());
		}*/
		return  memeberItemMapper.selectByPrimaryKey(memberItem.getId());
	}

	@Override
	public MemberItem selectMemberItem(MemberItem memberItem) {
		List<MemberItem> list = memeberItemMapper.selectByCondition(memberItem);
		/*if(list.size()>0){
			memberItem = list.get(0);
			MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
			tdMemberItemCartype.setMemberItemId(list.get(0).getId());
			List<MemberItemCartype> cartypes = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
			memberItem.setCartypes(cartypes);
			return memberItem;
		}*/
		if(CollectionUtils.isNotEmpty(list)){
			memberItem = list.get(0);
			return memberItem;
		}
		return null;
	}

}
