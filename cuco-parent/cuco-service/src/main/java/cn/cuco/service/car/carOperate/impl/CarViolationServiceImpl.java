package cn.cuco.service.car.carOperate.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CarViolationMapper;
import cn.cuco.entity.CarAttachment;
import cn.cuco.entity.CarViolation;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.enums.CarEnum;
import cn.cuco.enums.CarViolationEnum;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.car.attachment.CarAttachmentService;
import cn.cuco.service.car.carOperate.CarViolationService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.order.OrderMemberCarUsedService;

/** 
* @ClassName: CarViolationServiceImpl 
* @Description: 车辆违章service实现
* @author gongbw
* @date 2017年3月6日 下午1:53:49  
*/
@Service
public class CarViolationServiceImpl implements CarViolationService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CarViolationMapper<CarViolation> carViolationMapper;
	@Autowired
	private CarAttachmentService carAttachmentService;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private OrderMemberCarUsedService memberCarUsedService;
	    
	@Override
	public PageResult<CarViolation> getCarViolationByPage(CarViolation carViolation) {
		//设置参数类型
		Integer page = carViolation.getPage();
	    Integer pageSize = carViolation.getPageSize();
	    Integer totalSize = this.carViolationMapper.selectCountOrderByCreatedDesc(carViolation);
		List<CarViolation> carViolationList = this.carViolationMapper.selectByConditionOrderByCreatedDesc(carViolation);
		/*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    PageResult<CarViolation> pageResult = new PageResult<CarViolation>(page,pageSize,totalSize,carViolationList);
		return pageResult;
	}

	private CarViolation insertCarViolation(CarViolation carViolation){
		carViolation.setCreated(new Date());
		carViolation.setLasttimeModify(carViolation.getCreated());
		this.carViolationMapper.insertSelective(carViolation);
		return this.getCarViolationById(carViolation.getId());
	}
	
	@Override
	public CarViolation createCarViolation(CarViolation carViolation) {
		ParamVerifyUtils.paramNotNull(carViolation,"创建车辆违章，车辆违章对象不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getCarPlateNum(),"创建车辆违章，车牌号不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getViolationTime()+"","创建车辆违章，违章时间不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getViolationAddress(),"创建车辆违章，违章地点不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getDealType()+"","创建车辆违章，违章行为不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getFine()+"","创建车辆违章，罚款不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getPointPenalty()+"","创建车辆违章，扣分不能为空");
		ParamVerifyUtils.paramNotEmpty(carViolation.getDescription()+"","创建车辆违章，违章描述不能为空");
		//自动补全车辆违章的其他信息
		carViolation = this.supplementCarViolation(carViolation);
		//写入车辆违章记录
		this.insertCarViolation(carViolation);
		//插入车辆违章操作日志
		this.operateLogService.createLogForCarViolation(carViolation);
		return this.carViolationMapper.selectByPrimaryKey(carViolation.getId());
	}
	
	private CarViolation supplementCarViolation(CarViolation carViolation){
		//根据车辆违章时间查询属于哪次用车
		OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
		memberCarUsed.setCaroperateId(carViolation.getCarId());
		memberCarUsed.setStartDate(carViolation.getViolationTime());
		List<OrderMemberCarUsed> memberCarUsedList = this.memberCarUsedService.getOrderMemberCarUsedListByCarIdAndDate(memberCarUsed);
		if(CollectionUtils.isNotEmpty(memberCarUsedList)){
			if(memberCarUsedList.size()>1){
				throw new RuntimeExceptionWarn("创建车辆违规，找到的用户用车记录异常");
			}else{
				memberCarUsed = memberCarUsedList.get(0);
				carViolation.setMemberId(memberCarUsed.getMemberId());
				carViolation.setMemberName(memberCarUsed.getMemberName());
				carViolation.setMemberMobile(memberCarUsed.getMemberMobile());
				carViolation.setCarUsedId(memberCarUsed.getId());
				carViolation.setStatus(CarViolationEnum.status.WAITECONFIRM.getIndex());
				carViolation.setType(0);
			}
		}else{
			carViolation.setStatus(CarViolationEnum.status.PROCESSING.getIndex());
			carViolation.setDealType(CarViolationEnum.dealType.DEALWITHCUCO.getIndex());
		}
		return carViolation;
	}

	@Override
	public CarViolation getCarViolationById(Long id) {
		ParamVerifyUtils.paramNotNull(id,"根据ID查询车辆违章详情，ID不能为空");
		CarViolation carViolation = this.carViolationMapper.selectByPrimaryKey(id);
		if(null!=carViolation){
			carViolation = this.getCarAttachment(carViolation);
		}
		return carViolation;
	}

	/**   
	 * @Title: getCarAttachment   
	 * @Description: 查询得到违章处罚凭证
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: CarViolation       
	 */
	private CarViolation getCarAttachment(CarViolation carViolation){
		//查询得到违章处罚凭证
		CarAttachment search = new CarAttachment();
		search.setParentId(carViolation.getId());
		search.setType(CarEnum.CarAttachmentType.VIOLATION_URL.getIndex());
		List<CarAttachment> carAttachmentList = this.carAttachmentService.getCarAttachmentList(search);
		carViolation.setCarAttachmentList(carAttachmentList);
		return carViolation;
	}

	@Override
	public PageResult<OperateLog> getCarViolationLogByPage(OperateLog log) {
		ParamVerifyUtils.paramNotNull(log,"查询车辆违章操作日志，日志对象不能为空");
		ParamVerifyUtils.paramNotNull(log.getOperationId(),"查询车辆违章操作日志，操作ID不能为空");
		log.setType(OperateLogEnum.CAR_VIOLATION.getValue());
		return this.operateLogService.getOperateLogByPage(log);
	}

	@Override
	public CarViolation updateCarViolation2processing(CarViolation carViolation) {
		ParamVerifyUtils.paramNotNull(carViolation,"修改车辆违规状态为待处理，车辆违章对象不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getId(),"修改车辆违规状态为待处理，车辆违章对象ID不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getModifierId(),"修改车辆违规状态为待处理，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getModifier(),"修改车辆违规状态为待处理，操作人不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getDealType(),"修改车辆违规状态为待处理，没有选择是自己处理还是cuco代处理");
		carViolation.setStatus(CarViolationEnum.status.PROCESSING.getIndex());
		return this.updateCarViolation(carViolation);
	}

	@Override
	public CarViolation updateCarViolation2processed(CarViolation carViolation) {
		ParamVerifyUtils.paramNotNull(carViolation,"修改车辆违规状态为已处理，车辆违章对象不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getId(),"修改车辆违规状态为已处理，车辆违章对象ID不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getModifierId(),"修改车辆违规状态为已处理，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getModifier(),"修改车辆违规状态为已处理，操作人不能为空");
		if(null != carViolation.getUploadImages()){
			for(int i=0;i<carViolation.getUploadImages().length;i++){
				CarAttachment newcar = new CarAttachment();
				newcar.setParentId(carViolation.getId());
				newcar.setUrl(carViolation.getUploadImages()[i]);
				//先删除
				this.carAttachmentService.deleteAttachment(newcar);
				//后添加
				this.carAttachmentService.createCarViolationAttachment(newcar);
			}
		}
		//判断是否存在附件
		CarAttachment carAttachment = new CarAttachment();
		carAttachment.setParentId(carViolation.getId());
		carAttachment.setType(CarEnum.CarAttachmentType.VIOLATION_URL.getIndex());
		List<CarAttachment> carAttachmentList = this.carAttachmentService.getCarAttachmentList(carAttachment);
		if(CollectionUtils.isEmpty(carAttachmentList)){
			throw new RuntimeExceptionWarn("未上传任何凭证，不能修改状态为已处理");
		}
		carViolation.setStatus(CarViolationEnum.status.PROCESSED.getIndex());
		return this.updateCarViolation(carViolation);
	}
	
	private CarViolation updateCarViolation(CarViolation carViolation){
		carViolation.setLasttimeModify(new Date());
		this.carViolationMapper.updateByPrimaryKeySelective(carViolation);
		return this.carViolationMapper.selectByPrimaryKey(carViolation.getId());
	}

	@Override
	public BigDecimal calculatePriceDealWithBycucuo(CarViolation carViolation) {
		ParamVerifyUtils.paramNotNull(carViolation,"用户请求cuco代为处理，车辆违约对象不能为空");
		ParamVerifyUtils.paramNotNull(carViolation.getId(),"用户请求cuco代为处理，车辆违约ID不能为空");
		carViolation = this.carViolationMapper.selectByPrimaryKey(carViolation.getId());
		if(null == carViolation){
			throw new RuntimeExceptionWarn("没有找到该违章记录");
		}
		BigDecimal price = new BigDecimal("100");
		BigDecimal fine = carViolation.getFine();
		BigDecimal point_price = new BigDecimal(carViolation.getPointPenalty()*200);
		BigDecimal total = (fine.add(point_price)).multiply(new BigDecimal(1.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(price.doubleValue()<=total.doubleValue()){
			price = total;
		}
		return price;
	}

	@Override
	public void updateCarViolation2processingBysystem(CarViolation carViolation) {
		carViolation.setStatus(CarViolationEnum.status.PROCESSING.getIndex());
		carViolation.setDealType(CarViolationEnum.dealType.DEALWITHCUCO.getIndex());
		this.updateCarViolation(carViolation);
		//生成一条系统自动生成扭转的日志
		OperateLog operateLog = new OperateLog();
		operateLog.setType(OperateLogEnum.CAR_VIOLATION.getValue());
		operateLog.setOperationId(carViolation.getId());
		operateLog.setModifierId(Long.parseLong("0"));
		operateLog.setModifier("系统");
		operateLog.setRemark("系统自动将车辆违章变为未处理");
		this.operateLogService.createOperateLog(operateLog);
	}
}
