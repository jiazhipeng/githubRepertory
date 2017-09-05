package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Map;


/**
 * 
 * @author auto create
 * @since 1.0,2016-01-19 16:05:54
 */
public class Message{
	
	
	/**
	 * 接收设备
	 * @author qpx
	 *
	 */
	public enum ReceivingEquipment{
		IOS("ios",1),
		ANDROID("android",2);
		
		
		ReceivingEquipment(String name,int index){
			this.name = name;
			this.index = index;
		}
		private String name;
		private Integer index;
		public String getName() {
			return name;
		}
		public int getIndex() {
			return index;
		}	
		
		
		public static  String getNameStr(Integer index){
			
			for(ReceivingEquipment receivingEquipment:ReceivingEquipment.values()){
				if(receivingEquipment.getIndex() == index){
					return receivingEquipment.getName();
				}
			}
			return "";
			
			
		}
		
	}
	
	/**
	 * 应用类型
	 * @author qpx
	 *
	 */
	public enum ApplicationType{
		APPLICATION_TYPE_0("极车公社",0),
		APPLICATION_TYPE_1("易人易车",1),
		APPLICATION_TYPE_2("新能源",2);
		
		private String name;
		
		private Integer index;
		
		
		ApplicationType(String name,Integer index){
			this.name = name;
			this.index = index;
		}
		public String getName() {
			return name;
		}


		public int getIndex() {
			return index;
		}
		
		public static  String getNameStr(Integer index){
			
			for(ApplicationType applicationType:ApplicationType.values()){
				if(applicationType.getIndex() == index){
					return applicationType.getName();
				}
				
				
			}
			return "";
			
			
		}
		
		
	}
	

    private static final long serialVersionUID = 8150333688333074L;

    private Integer messageId;//主键

    private Integer applicationType ;//应用类型 0 极车公社 1易人易车 2 新能源
    
    private String applicationTypeStr;//引用类型描述
    
    

    private String messageContent;//消息内容

    private Integer userType;//用户类型 -1:所有人 0:普通用户 1:vip 用户 2:准vip用户
    
    private String userTypeStr;//用户类型 描述

    private Integer transmissionTypes;//发送类型 0 即时 1 定时

    private String beginTime;//定时发送的开始时间
    
    private String createTime;//创建时间
    
    private int status;//状态 0：未成功 1：成功  2：等待发送
        
    
    private String createUser;//创建人
    
    private int receivingEquipment;// 接收设备 0 全部 1:ios 2 安卓  　S
    

    private String returnMessage;//返回信息
    
    private String remark;//备注描述
    
    private String ticker;//通知栏提示文字
      
    private String title;//标题
    
    
    private String alias;//单推字段
    
    private String aliasType;//单推类型
    
    
    private Map<String,Object> jsonMap;
    
    
    private int readStatus;//阅读状态
    
    
    private String extendContent;//json 大字段。
    
    
    
    private String statusMessage;//状态信息
    
    
    private int beginPage = 1;
	
	private int pageSize = 15;

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
    
    

	public String getApplicationTypeStr() {
		return applicationTypeStr;
	}

	public void setApplicationTypeStr(String applicationTypeStr) {
		this.applicationTypeStr = applicationTypeStr;
	}

	public String getUserTypeStr() {
		return userTypeStr;
	}

	public void setUserTypeStr(String userTypeStr) {
		this.userTypeStr = userTypeStr;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public String getExtendContent() {
		return extendContent;
	}

	public void setExtendContent(String extendContent) {
		this.extendContent = extendContent;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAliasType() {
		return aliasType;
	}

	public void setAliasType(String aliasType) {
		this.aliasType = aliasType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public int getReceivingEquipment() {
		return receivingEquipment;
	}

	public void setReceivingEquipment(int receivingEquipment) {
		this.receivingEquipment = receivingEquipment;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getApplicationType() {
        return this.applicationType;
    }

    public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

    public String getMessageContent() {
        return this.messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getTransmissionTypes() {
        return this.transmissionTypes;
    }

    public void setTransmissionTypes(Integer transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        return this.messageId + "^" + this.applicationType + "^" + this.messageContent + "^" + this.userType + "^" + this.transmissionTypes + "^" + this.beginTime;
    }
}
