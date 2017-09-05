package cn.cuco.service.wechat.menu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.dao.WechatMenuMapper;
import cn.cuco.entity.WechatMenu;
import cn.cuco.service.wechat.menu.WechatMenuService;

@Service
public class WechatMenuServiceImpl implements WechatMenuService{
	
	@Autowired
	private WechatMenuMapper<WechatMenu> wechatmenuMapper;

	@Override
	public Integer create(WechatMenu tdWechatMenu) throws Exception {
		return wechatmenuMapper.insertSelective(tdWechatMenu);
	}

	@Override
	public Integer createList(List<WechatMenu> tdWechatMenu) throws Exception {
		return wechatmenuMapper.insertBatch(tdWechatMenu);
	}

	@Override
	public Integer deleteById(Object id) {
		return wechatmenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(WechatMenu tdWechatMenu) {
		return wechatmenuMapper.updateByPrimaryKeySelective(tdWechatMenu);
	}

	@Override
	public WechatMenu getById(Object id) {
		return wechatmenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WechatMenu> getByMenu(WechatMenu tdWechatMenu) {
		return wechatmenuMapper.selectByCondition(tdWechatMenu);
	}

	@Override
	public Integer getCountByMenu(WechatMenu tdWechatMenu) {
		return wechatmenuMapper.selectCountByCondition(tdWechatMenu);
	}

	@Override
	public Integer deleteAll() {
		return wechatmenuMapper.deleteAll();
	}

	
}
