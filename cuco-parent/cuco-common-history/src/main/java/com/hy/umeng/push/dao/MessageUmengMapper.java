package com.hy.umeng.push.dao;


import java.util.List;

import com.hy.umeng.push.entity.MessageUmeng;



public interface MessageUmengMapper {
	
	
	public List<MessageUmeng> selectByList(MessageUmeng message);
	
	
	public int selectByListCount(MessageUmeng message);
	
	
	public void insertSelective(MessageUmeng message);
	
	
	public void updateByPrimaryKeySelective(MessageUmeng message);


	public MessageUmeng selectByPrimaryKey(MessageUmeng message);

	
	public void deleteByPrimaryKey(int messageID);


}
