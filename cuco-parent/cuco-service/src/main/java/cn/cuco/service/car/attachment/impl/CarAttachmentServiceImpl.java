package cn.cuco.service.car.attachment.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CarAttachmentMapper;
import cn.cuco.entity.CarAttachment;
import cn.cuco.enums.CarEnum;
import cn.cuco.service.car.attachment.CarAttachmentService;

@Service
public class CarAttachmentServiceImpl implements CarAttachmentService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CarAttachmentMapper<CarAttachment> carAttachmentMapper;
	
	/**   
	 * @Title: createCarAttachment   
	 * @Description: 创建车辆相关附件基础方法  
	 * @param: @param carAttachment      
	 * @return: void       
	 */
	private void createCarAttachment(CarAttachment carAttachment){
		this.carAttachmentMapper.insertSelective(carAttachment);
	}
	
	@Override
	public void createCarInsuranceAttachment(CarAttachment carAttachment) {
		ParamVerifyUtils.paramNotNull(carAttachment,"创建车辆保险单附件，车辆保险附件不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getParentId(),"创建保险单明细，车辆保险ID不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getUrl(),"创建保险单明细，车辆保险附件url不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getType(),"创建保险单明细，车辆附件类型不能为空");
		this.createCarAttachment(carAttachment);
	}

	@Override
	public void createCarViolationAttachment(CarAttachment carAttachment) {
		ParamVerifyUtils.paramNotNull(carAttachment,"创建车辆违章附件，车辆违章附件不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getParentId(),"创建车辆违章附件，车辆违章ID不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getUrl(),"创建车辆违章附件，车辆违章附件url不能为空");
		carAttachment.setType(CarEnum.CarAttachmentType.VIOLATION_URL.getIndex());
		this.createCarAttachment(carAttachment);
		
	}

	@Override
	public List<CarAttachment> getCarAttachmentList(CarAttachment carAttachment) {
		ParamVerifyUtils.paramNotNull(carAttachment,"查询车辆相关附件，车辆附件对象不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getParentId(),"查询车辆相关附件，主对象ID不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getType(),"查询车辆相关附件，类型不能为空");
		return this.carAttachmentMapper.selectByCondition(carAttachment);
	}

	@Override
	public void createCarInsuranceAttachmenBatch(List<CarAttachment> carAttachmentList) {
		ParamVerifyUtils.paramNotNull(carAttachmentList,"批量创建车辆保险单附件，车辆违章附件列表不能为空");
		this.carAttachmentMapper.insertBatch(carAttachmentList);
	}

	@Override
	public void deleteAttachment(CarAttachment carAttachment) {
		ParamVerifyUtils.paramNotNull(carAttachment,"删除车辆相关附件，车辆附件对象不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getParentId(),"删除相关，车辆附件主表ID对象不能为空");
		ParamVerifyUtils.paramNotNull(carAttachment.getType()+"","删除相关，车辆附件类型对象不能为空");
		this.carAttachmentMapper.deleteByParentId(carAttachment);
	}

}
