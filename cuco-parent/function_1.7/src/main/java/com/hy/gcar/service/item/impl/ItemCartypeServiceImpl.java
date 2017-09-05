package com.hy.gcar.service.item.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.ItemCartypeMapper;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.service.item.ItemCartypeService;

@Service(value = "itemCartypeService")
public class ItemCartypeServiceImpl implements ItemCartypeService {

    @Autowired
    private ItemCartypeMapper<ItemCartype>itemCartypeMapper;
    
    @Override
    public Integer insertSelective(ItemCartype itemCartype) throws Exception {
           return this.itemCartypeMapper.insertSelective(itemCartype);
        }

    @Override
    public Integer insertBatch(List<ItemCartype> itemCartype) throws Exception {
           return this.itemCartypeMapper.insertBatch(itemCartype) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.itemCartypeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.itemCartypeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ItemCartype itemCartype) {
           return this.itemCartypeMapper.updateByPrimaryKeySelective(itemCartype);
    }

    @Override
    public ItemCartype findById(Object id) {
           return (ItemCartype) this.itemCartypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemCartype> selectByCondition(ItemCartype itemCartype) {
           return (List<ItemCartype>) this.itemCartypeMapper.selectByCondition(itemCartype);
    }

    @Override
    public Integer selectCountByCondition(ItemCartype tdItemCartype) {
        return  this.itemCartypeMapper.selectCountByCondition(tdItemCartype);
    }

    @Override
	public List<ItemCartype> selectByCarTypeId(Long carTypeId) {
		// TODO Auto-generated method stub
		return itemCartypeMapper.selectByCarTypeId(carTypeId);
	}

	@Override
	public BigDecimal selectMinimumPrice(ItemCartype itemCartype) {
		Assert.notNull(itemCartype, "根据套餐ID查询当前套餐下的最低日使用费,套餐车型对象不能为空！");
		Assert.notNull(itemCartype.getItemId(), "根据套餐ID查询当前套餐下的最低日使用费,套餐ID不能为空！");
		List<ItemCartype>  itemCartypeList= this.itemCartypeMapper.selectByCondition(itemCartype);
		BigDecimal minimumPrice= new BigDecimal("0");
		if(CollectionUtils.isNotEmpty(itemCartypeList)){
			minimumPrice = itemCartypeList.get(0).getDayPrice();
			for(ItemCartype ItemCart:itemCartypeList){
				if(1==minimumPrice.compareTo(ItemCart.getDayPrice())){
					minimumPrice = ItemCart.getDayPrice();
				}
			}
		}
		return minimumPrice;
	}

}
