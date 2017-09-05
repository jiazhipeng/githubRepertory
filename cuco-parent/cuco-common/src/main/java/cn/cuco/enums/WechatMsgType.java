package cn.cuco.enums;

/**
 * 
* @ClassName: WechatMsgType 
* @Description: wechat消息模板type
* @author jiaxiaoxian
* @date 2017年2月21日 下午1:58:57
 */
public enum WechatMsgType {

	  	AUCTIONSUCCESS("1","竞拍成功通知"),
	  	SENDINCARSUCCESS("2","送车中成功通知"),
	  	SENDARRIVEDSUCCESS("3","送车已到达成功通知"),
	  	SENDCOMPLETEDSUCCESS("4","送车已完成成功通知"),
	  	GETTAKECARSUCCESS("5","取车取车中成功通知"),
	  	GETARRIVEDSUCCESS("6","取车已到达成功通知"),
	  	GETPENDINGSTORAGESUCCESS("7","取车待入库成功通知"),
	  	RESTRICTIONSUCCESS("8","限号成功通知"),
		SENDCUSTOMERSUCCESS("9","创建送车任务客服成功通知"),
		GETCUSTOMERSUCCESS("10","创建取车任务客服成功通知"),
		SENDCUSTOMERTOOPERATERSUCCESS("11","客服分配送车任务给管家成功通知"),
		GETCUSTOMERTOOPERATERSUCCESS("12","客服分配取车任务给管家成功通知"),
		ORDERTAKINGTOCARSERVICESUCCESS("13","管家接单给车务成功通知"),
		OPERATERGETTOCARSERVICESUCCESS("13","管家接车完成送车入库给车务成功通知");
	    private String type;

	    private String message;

	    WechatMsgType(String type, String message) {
	        this.type = type;
	        this.message = message;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getMessage() {
	        return message;
	    }
	
}
