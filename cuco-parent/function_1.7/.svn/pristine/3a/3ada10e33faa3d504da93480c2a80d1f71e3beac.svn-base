package com.hy.gcar.service.suggest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.SuggestMapper;
import com.hy.gcar.entity.Suggest;
import com.hy.gcar.service.suggest.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService{
	
	@Autowired
	private SuggestMapper<Suggest> suggestMapper;

	@Override
	public Suggest createSuggest(Suggest suggest){
		Assert.notNull(suggest, "意见建议对象为空");
		this.suggestMapper.insertSelective(suggest);
		return (Suggest) suggestMapper.selectByPrimaryKey(suggest.getId());
	}

}
