package com.hy.gcar.service.item.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ItemMapper;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;

@Service(value = "tdItemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper<Item>itemMapper;
    @Autowired
    private ItemCartypeService itemCartypeService;
    @Autowired
    private CarTypeService carTypeService;
    
    @Override
    public Integer insertSelective(Item item) throws Exception {
           return this.itemMapper.insertSelective(item);
        }

    @Override
    public Integer insertBatch(List<Item> item) throws Exception {
           return this.itemMapper.insertBatch(item) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.itemMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.itemMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Item item) {
           return this.itemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public Item findById(Object id) {
           return (Item) this.itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Item> selectByCondition(Item item) {
           return (List<Item>) this.itemMapper.selectByCondition(item);
    }


	@Override
	public Integer selectMaxEnableCount() {
		
		return itemMapper.selectMaxEnableCount();
	}

	@Override
	public List<Item> selectCarTypeList(Item item) {
		List <Item> itemList = this.selectByCondition(item);
		//根据套餐Id查询所有的车型
		for(Item itemModel : itemList){
			ItemCartype itemCartype = new ItemCartype();
			itemCartype.setItemId(itemModel.getId());
			List<ItemCartype> itemCartypeList = this.itemCartypeService.selectByCondition(itemCartype);
			
			if(CollectionUtils.isNotEmpty(itemCartypeList)){
				List<CarType> carTypeList = new ArrayList<CarType>();
				BigDecimal dayPrice_row = itemCartypeList.get(0).getDayPrice();
				for(ItemCartype itemCarty : itemCartypeList){
					BigDecimal dayPrice = itemCarty.getDayPrice();
					if(1==dayPrice_row.compareTo(dayPrice)){
						dayPrice_row = dayPrice;
					}
					CarType carType = new CarType();
					carType = this.carTypeService.findById(itemCarty.getCartypeId());
					carTypeList.add(carType);
				}
				itemModel.setDayPriceMinimum(dayPrice_row);
				itemModel.setCarTypes(carTypeList);
			}
			
		}
		return itemList;
	}

}
