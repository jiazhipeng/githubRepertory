package com.hy.gcar.service.nation.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.NationMapper;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.service.nation.NationService;


@Service
public class NationServiceImpl implements NationService{

	@Autowired
	private NationMapper nationMapper;

	/**
	 * 根据pid 获取市或者县
	 */
	@Override
	public List<Nation> getNationByParentID(Nation nation) {
		return nationMapper.getNationByParentID(nation);
	}

	@Override
	public List<Nation> getNationByCity(Nation nation) {
		return nationMapper.getNationByCity(nation);
	}

	@Override
	public List<Nation> selectByParent(Integer id) {
		return nationMapper.selectByParent(id);
	}

	@Override
	public Nation getNationByCityCode(String code) {
		List<Nation> nation = nationMapper.getNationByCityCode(code);
		if(CollectionUtils.isNotEmpty(nation)){
			return nation.get(0);
		}
		return null;
	}

	@Override
	public Nation getParentNationByCityeCodeOrId(Nation nation) {
		
		List<Nation> nations = nationMapper.getParentNationByCityeCodeOrId(nation);
		if(CollectionUtils.isNotEmpty(nations)){
			return nations.get(0);
		}
		return null;
	}




}
