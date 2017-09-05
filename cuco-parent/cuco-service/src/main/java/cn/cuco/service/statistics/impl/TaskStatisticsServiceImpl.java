package cn.cuco.service.statistics.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.ButlerTaskMapper;
import cn.cuco.entity.ButlerTask;
import cn.cuco.enums.ButlerTaskType;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.service.statistics.TaskStatisticsService;

@Service
public class TaskStatisticsServiceImpl implements TaskStatisticsService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private ButlerTaskMapper<ButlerTask> butlerTaskMapper;

	
	/**
	 * 执行任务统计
	 */
	@Override
	public List<Map<String, Object>> getExexuteTaskStatistics(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"任务统计-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTimeStart(), "任务统计-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTimeEnd(), "任务统计-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getType(), "任务统计-按月或按日不能为空");
		if(butlerTask.getPlanTimeStart()!=null && butlerTask.getPlanTimeEnd()!=null && butlerTask.getPlanTimeStart().getTime()>butlerTask.getPlanTimeEnd().getTime()){
			throw new RuntimeExceptionWarn("查询条件开始时间不能大于结束时间");
		}
		if(butlerTask.getPlanTimeStart()!=null && butlerTask.getPlanTimeEnd()==null){
			throw new RuntimeExceptionWarn("查询条件结束时间不为空");
		}
		if(butlerTask.getPlanTimeStart()==null && butlerTask.getPlanTimeEnd()!=null){
			throw new RuntimeExceptionWarn("查询条件开始时间不为空");
		}
		ButlerTask butlerTaskSearch = new ButlerTask();
		List<Map<String, Object>> lists = new  ArrayList<>();
		try {
			//周期次数
			int num = 0;
			//周
			if(butlerTask.getType()==1){
				num = DateUtils.getCountTwoDayWeek(butlerTask.getPlanTimeStart(),butlerTask.getPlanTimeEnd());
				for (int i = 0; i < num; i++) {
					Map<String, Object> map = new HashMap<>();
					Date before = DateUtils.getMondayByDate(DateUtils.addWeeks(butlerTask.getPlanTimeStart(), i));
					if(before.getTime()<butlerTask.getPlanTimeStart().getTime()){
						before = butlerTask.getPlanTimeStart();
					}
					Date after = DateUtils.getDayMaxDate(DateUtils.getSundayByDate(before));
					//设置查询时间
					butlerTaskSearch.setPlanTimeStart(DateUtils.getDayMiniDate(before));
					if(after.getTime() >butlerTask.getPlanTimeEnd().getTime()){
						butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
					}else{
						butlerTaskSearch.setPlanTimeEnd(after);
					}
					map.put("date", DateUtils.formatDate(before, null));
					//查询送车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
					map.put("send", butlerTaskMapper.getTaskNumByDate(butlerTask));
					
					//查询取车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
					map.put("get", butlerTaskMapper.getTaskNumByDate(butlerTask));
					lists.add(map);
				}
			//月
			}else if(butlerTask.getType()==2){
				num = DateUtils.getCountTwoDayMonth(butlerTask.getPlanTimeStart(),butlerTask.getPlanTimeEnd());
				for (int i = 0; i < num; i++) {
					Map<String, Object> map = new HashMap<>();
					Date before = DateUtils.getFirstOfMonth(DateUtils.addMonths(butlerTask.getPlanTimeStart(), i));
					if(before.getTime()<butlerTask.getPlanTimeStart().getTime()){
						before = butlerTask.getPlanTimeStart();
					}
					Date after = DateUtils.getDayMaxDate(DateUtils.getLastOfMonth(before));
					//设置查询时间
					butlerTaskSearch.setPlanTimeStart(DateUtils.getDayMiniDate(before));
					if(after.getTime() >butlerTask.getPlanTimeEnd().getTime()){
						butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
					}else{
						butlerTaskSearch.setPlanTimeEnd(after);
					}
					map.put("date", DateUtils.formatDate(before, null));
					//查询送车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
					map.put("send", butlerTaskMapper.getTaskNumByDate(butlerTask));
					
					//查询取车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
					map.put("get", butlerTaskMapper.getTaskNumByDate(butlerTask));
					lists.add(map);
				}
			//日
			}else{
				num =  (int) DateUtils.getDistanceOfTwoDate(butlerTask.getPlanTimeStart(), butlerTask.getPlanTimeEnd());
				for (int i = 0; i < num; i++) {
					Map<String, Object> map = new HashMap<>();
					Date before = DateUtils.addDays(butlerTask.getPlanTimeStart(), i);
					Date after = DateUtils.getDayMaxDate(before);
					//设置查询时间
					butlerTaskSearch.setPlanTimeStart(DateUtils.getDayMiniDate(before));
					if(after.getTime() >butlerTask.getPlanTimeEnd().getTime()){
						butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
					}else{
						butlerTaskSearch.setPlanTimeEnd(after);
					}
					map.put("date", DateUtils.formatDate(before, null));
					//查询送车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
					map.put("send", butlerTaskMapper.getTaskNumByDate(butlerTask));
					
					//查询取车数据
					butlerTaskSearch.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
					map.put("get", butlerTaskMapper.getTaskNumByDate(butlerTask));
					lists.add(map);
				}
				
			}
		} catch (Exception e) {
			 throw new RuntimeExceptionWarn("任务统计失败");
		}
		return lists;
	}


	/**
	* @Title: getSundayByDate 
	* @Description: 取周日
	* @author huanghua
	* @param date
	* @throws ParseException
	* @return void
	 */
	private Date getSundayByDate(Date date)throws ParseException {  
        
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Calendar cal = Calendar.getInstance();  
		//        Date date=sdf.parse("2015-9-4 14:22:47");
		cal.setTime(date);  
		System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		if(1 == dayWeek) {  
		   cal.add(Calendar.DAY_OF_MONTH, -1);  
		}  
       cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
       int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
       cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
       cal.add(Calendar.DATE, 6);
       System.out.println("所在周星期日的日期："+sdf.format(cal.getTime()));  
        return cal.getTime();
    }
	
	/**
	* @Title: getMondayByDate 
	* @Description: 取周一
	* @author huanghua
	* @param date
	* @return
	* @throws ParseException
	* @return Date
	 */
	private Date getMondayByDate(Date date)throws ParseException {  
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
//        Date date=sdf.parse("2015-9-4 14:22:47");
        cal.setTime(date);  
        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
        if(1 == dayWeek) {  
           cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
       cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
       int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
       cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
       return cal.getTime();
   }
}
