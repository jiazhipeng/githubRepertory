package com.hy.gcar.service.card.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.CardMapper;
import com.hy.gcar.entity.Card;
import com.hy.gcar.service.card.CardService;

@Service(value = "tdCardService")
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper<Card>tdCardMapper;
    
    @Override
    public Integer insertSelective(Card tdCard) throws Exception {
           return this.tdCardMapper.insertSelective(tdCard);
        }

    @Override
    public Integer insertBatch(List<Card> tdCard) throws Exception {
           return this.tdCardMapper.insertBatch(tdCard) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCardMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCardMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Card tdCard) {
           return this.tdCardMapper.updateByPrimaryKeySelective(tdCard);
    }

    @Override
    public Card findById(Object id) {
           return (Card) this.tdCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Card> selectByCondition(Card tdCard) {
           return (List<Card>) this.tdCardMapper.selectByCondition(tdCard);
    }

    @Override
    public Integer selectCountByCondition(Card tdCard) {
           return  this.tdCardMapper.selectCountByCondition(tdCard);
    }

}
