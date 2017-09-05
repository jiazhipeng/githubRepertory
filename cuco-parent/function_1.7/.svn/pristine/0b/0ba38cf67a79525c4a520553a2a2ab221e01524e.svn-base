package com.hy.gcar.service.carOperte.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.DateUtils;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.dao.CarOperateLogMapper;
import com.hy.gcar.entity.BasicRestriction;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.member.MemberService;

@Service(value = "tdCarOperateLogService")
public class CarOperateLogServiceImpl implements CarOperateLogService {

    @Autowired
    private CarOperateLogMapper<CarOperateLog>tdCarOperateLogMapper;
    
    @Autowired
    private CarOperateService carOperateService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private CarTypeService carTypeService;
    
    @Autowired
    private BasicRestrictionService  basicRestrictionService;
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    @Override
    public Integer insertSelective(CarOperateLog tdCarOperateLog) throws Exception {
           return this.tdCarOperateLogMapper.insertSelective(tdCarOperateLog);
        }

    @Override
    public Integer insertBatch(List<CarOperateLog> tdCarOperateLog) throws Exception {
           return this.tdCarOperateLogMapper.insertBatch(tdCarOperateLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCarOperateLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCarOperateLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CarOperateLog tdCarOperateLog) {
           return this.tdCarOperateLogMapper.updateByPrimaryKeySelective(tdCarOperateLog);
    }

    @Override
    public CarOperateLog findById(Object id) {
           return (CarOperateLog) this.tdCarOperateLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarOperateLog> selectByCondition(CarOperateLog tdCarOperateLog) {
           return (List<CarOperateLog>) this.tdCarOperateLogMapper.selectByCondition(tdCarOperateLog);
    }

    @Override
    public Integer selectCountByCondition(CarOperateLog tdCarOperateLog) {
           return  this.tdCarOperateLogMapper.selectCountByCondition(tdCarOperateLog);
    }
    
    /**
	 * 根据用车记录 自动插入会员的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午4:48:51   
	* @return String
	 */
    @Override
	public void autoCreateUseCarLog(PowerUsed powerUses,ButlerTask butlerTask){
		
		Calendar start = Calendar.getInstance();  
	    start.setTime(butlerTask.getCompleteTime());
	   // start.set(2014, 6, 11);
	    Long startTIme = start.getTimeInMillis();  
	  
	    Calendar end = Calendar.getInstance();  
	    end.setTime(powerUses.getCarReturnTime());  
	    //end.set(2014, 7, 11);  
	    Long endTime = end.getTimeInMillis();  
	    
	    Calendar nowCalendar = Calendar.getInstance();  
	    nowCalendar.setTime(new Date());  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;
	    Long twelveHours = 1000 * 60 * 60 * 26l;  
	    
	    Long time = startTIme;  
	    int i = 1;
	    int status = powerUses.getUsedStatus();
	    while (time <= endTime) {  
	        Date d = new Date(time);  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(df.format(d));  
	        //log
	        insertCarLog(powerUses, butlerTask, i,d);
	        if(i == 1){
	        	 time += twelveHours;
	        }else{
	        	  time += oneDay;  
	        }
	        Date now = new Date(time);
	        Date endDate = new Date(endTime); 
	        Date nowDate = new Date();
	        i++;
	        if(time > nowCalendar.getTimeInMillis()){
	        	break;
	        }
	        if((endTime - time) < 0 && Math.abs(endTime - time) < oneDay){
	        	if(status == 6){
	        		i = -1;
	        	}
	        	Date dd = new Date(endTime);  
	        	 //log
		        insertCarLog(powerUses, butlerTask, i,dd);
	        }
	       /* if(now.getYear() == endDate.getYear() && now.getMonth() == endDate.getMonth()
	        		&& now.getDate() == endDate.getDate()){
	        	if(time > endTime){
	        		time = endTime;
	        	}
	        }*/
	        if(now.getYear() == nowDate.getYear() && now.getMonth() == nowDate.getMonth()
	        		&& now.getDate() == (nowDate.getDate()+1)){
	        	if(status == 3){
	        		break;
	        	}
	        }
	        if(time == endTime){
	        	if(status == 6){
	        		i = -1;
	        	}
	        }
	    }  
		
	}
	
	/**
	 * 插入车辆 会员使用中的 日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:38:39   
	* @return String
	 */
	private void insertCarLog(PowerUsed powerUses,ButlerTask butlerTask,int i,Date d){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		//运营记录
		carOperateLog.setMemberId(powerUses.getMemberId());
		Member member = null;
		try {
			member = memberService.findMemberByID(powerUses.getMemberId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(member != null){
			carOperateLog.setMemberName(member.getName());
		}
		carOperateLog.setOperateId(butlerTask.getOperaterId());
		carOperateLog.setOperateName(butlerTask.getOperaterName());
		CarOperate car = carOperateService.findById(powerUses.getCaroperateId());
		boolean flag = checkLimitNumber(d, car);
		if(i == 1){
			carOperateLog.setTakein(new BigDecimal(1199));
			carOperateLog.setRemark("数据自动导入管家送车完成,修改车辆状态为会员使用中！");
			carOperateLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
			if(flag){
				carOperateLog.setRemark("数据自动导入管家送车完成,修改车辆状态为会员使用中(限行)！");
				carOperateLog.setTakein(new BigDecimal(0));
			}
		}else if(i == -1){
			carOperateLog.setTakein(new BigDecimal(0));
			carOperateLog.setRemark("数据自动导入会员使用完毕,修改车辆状态为整备中！");
			carOperateLog.setType(CarOperateEnum.status.READINESSING.getValue());
			if(flag){
				carOperateLog.setRemark("数据自动导入会员使用完毕,修改车辆状态为整备中(限行)！");
				carOperateLog.setTakein(new BigDecimal(0));
			}
		}else{
			carOperateLog.setTakein(new BigDecimal(1199));
			carOperateLog.setRemark("数据自动导入会员使用中！");
			carOperateLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
			if(flag){
				carOperateLog.setRemark("数据自动导入会员使用中(限行)！");
				carOperateLog.setTakein(new BigDecimal(0));
			}
		}
		CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
		carOperateLog.setOperateNum(carOperate.getOperateNum());
		carOperateLog.setCreated(d);
		try {
			this.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--powerUsesId"+powerUses.getId());
		}
	}
	
	 private boolean checkLimitNumber(Date date, CarOperate carOperate) {
	        //验证是否限号
	        BasicRestriction basicRestriction = new BasicRestriction();
	        basicRestriction.setCityId(2);
	        basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(date,"yyyy-MM-dd")));
	        List<BasicRestriction> basicRestrictions = basicRestrictionService.selectByCondition(basicRestriction);
	        //根据车型判断车辆是否限号
	        List<CarType> carTypes = carTypeService.selectCarTypeByIds(Arrays.asList(carOperate.getCarTypeId()));
	        if(CollectionUtils.isNotEmpty(carTypes)){
	           CarType carType = carTypes.get(0);
	            if(carType.getIsRestriction().intValue() == 0){
	                return false;
	            }
	        }

	        if(CollectionUtils.isNotEmpty(basicRestrictions)){
	            basicRestriction = basicRestrictions.get(0);
	            String restrictions = basicRestriction.getRestrictions();
	            List<String> restrictionList = Arrays.asList(restrictions.split(","));
	            String carPlateNum = null;
	            String last = null;
	            carPlateNum = carOperate.getCarPlateNum();
	            last = carPlateNum.substring(carPlateNum.length()-1);
	            if(!isInteger(last)){
	                last = "0";
	            }
	            if(restrictionList.contains(last)){
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    /*
	     * 判断是否为整数
	     * @param str 传入的字符串
	     * @return 是整数返回true,否则返回false
	   */
	   public static boolean isInteger(String str) {
	       Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	       return pattern.matcher(str).matches();
	   }

	
	
	/**
	 * 插入自动整备完成的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:34:11   
	* @return String
	 */
	private void insertReadinessingCarLog(ButlerTask butlerTask,Date d){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		carOperateLog.setRemark("数据自动导入系统自动整备完成！");
		carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
		CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
		carOperateLog.setOperateNum(carOperate.getOperateNum());
		carOperateLog.setCreated(d);
		try {
			this.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--butlerTaskId"+butlerTask.getId());
		}
	}

	@Override
	public CarOperateLog selectByOperateNumCreate(CarOperateLog log) {
		return tdCarOperateLogMapper.selectByOperateNumCreate(log);
	}

}
