package cn.cuco.service.basic.carport.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CarportCartypeMapper;
import cn.cuco.dao.CarportMapper;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Carport;
import cn.cuco.entity.CarportCartype;
import cn.cuco.enums.Valid;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.basic.carport.CarportService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.member.carUsed.MemberCarportService;

@Service(value = "carportService")
public class CarportServiceImpl implements CarportService {

    @Autowired
    private CarportMapper<Carport> carportMapper;
    @Autowired
    private CarportCartypeMapper<CarportCartype> carportCartypeMapper;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private OperateLogService operateLogService;
    @Autowired
    private MemberCarportService memberCarportService;
    /**
     * 创建车库
     */
	@Override
	public Carport createCarport(Carport carport) {
		ParamVerifyUtils.paramNotNull(carport);
		ParamVerifyUtils.paramNotNull(carport.getCarportName(), "创建车库，车库名称不能为空");
		ParamVerifyUtils.paramNotNull(carport.getUnlockPrice(), "创建车库，车库解锁价格不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifierId(), "创建车库，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifier(), "创建车库，操作人不能为空");
		//验证车型
		Carport carportSearch = new Carport();
		carportSearch.setCarportName(carport.getCarportName());
		if(!this.carportMapper.selectByCondition(carportSearch).isEmpty()){
			  throw new RuntimeExceptionWarn("车库名称重复");
		}
		//默认值
		carport.setValid(Valid.DOWN.getValue());
		carport.setCreated(new Date());
		carport.setLasttimeModify(new Date());
		//创建车库
		this.carportMapper.insertSelective(carport);
		//创建车型
		this.createCarportCartypeBatch(carport);
		//创建日志
		operateLogService.createOperateLogForCarport(carport);
		return this.getCarportById(carport.getId());
	}

	/**
	 * 修改车库
	 */
	@Override
	public Carport updateCarport(Carport carport) {
		ParamVerifyUtils.paramNotNull(carport);
		ParamVerifyUtils.paramNotNull(carport.getId(), "修改车库，车库ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getCarportName(), "修改车库，车库名称不能为空");
		ParamVerifyUtils.paramNotNull(carport.getUnlockPrice(), "修改车库，车库解锁价格不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifierId(), "修改车库，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifier(), "修改车库，操作人不能为空");
		//验证车型
		Carport carportSearch = new Carport();
		carportSearch.setCarportName(carport.getCarportName());
		List<Carport> lists = this.carportMapper.selectByCondition(carportSearch);
		for (Carport carportView : lists) {
			if(carportView.getId()!= carport.getId()){
				throw new RuntimeExceptionWarn("车库名称重复");
			}
		}
		//默认值
		carport.setLasttimeModify(new Date());
		//修改车库
		this.carportMapper.updateByPrimaryKeySelective(carport);
		//修改车型
		this.updateCarportCartypeBatch(carport);
		return this.getCarportById(carport.getId());
	}
	
	/**
	 * 根据ID查询车库信息
	 */
	@Override
	public Carport getCarportById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询车库，车库ID不能为空");
		Carport carport = this.carportMapper.selectByPrimaryKey(id);
		if(carport!=null){
			CarportCartype carportCartype = new CarportCartype();
			carportCartype.setCarportId(carport.getId());
			List<CarportCartype> lists = this.carportCartypeMapper.selectByCondition(carportCartype);
			if(!lists.isEmpty()){
				List<CarType> carTypelist = new ArrayList<>();
				for (CarportCartype carportCartypeView : lists) {
					carTypelist.add(carTypeService.getCarTypeById(carportCartypeView.getCarTypeId()));
				}
				carport.setCarTypes(carTypelist);
			}
		}
		return carport;
	}

	/**
	 * 分页
	 */
	@Override
	public PageResult<Carport> getCarportByPage(Carport carport) {

		Integer page = carport.getPage();
	    Integer pageSize = carport.getPageSize();
	     /*搜索条件*/
	    Carport carportsSearch = new Carport();
	    List<Carport> carports = null;
	    /*总记录数*/
	    Integer totalSize = this.carportMapper.selectCountByConditionByPage(carportsSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    carports = this.carportMapper.selectByConditionByPage(carportsSearch);

	    PageResult<Carport> pageResult = new PageResult<Carport>(page,pageSize,totalSize,carports);
		return pageResult;
	}

	/**
	 * 上架
	 */
	@Override
	public void updateCarportShelves(Carport carport) {
		ParamVerifyUtils.paramNotNull(carport);
		ParamVerifyUtils.paramNotNull(carport.getId(), "车库上架，ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifierId(), "车库上架，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifier(), "车库上架，操作人不能为空");
		//验证数据是否存在
		Carport carportView = this.getCarportById(carport.getId());
		if(carportView==null){
			throw new RuntimeExceptionWarn("车库不存在");
		}
		if(carportView.getValid() == Valid.UP.getValue().intValue()){
			throw new RuntimeExceptionWarn("为上架状态不可重新变更");
		}
		carport.setValid(Valid.UP.getValue());
		carport.setLasttimeModify(new Date());
		//修改车型状态
		this.carportMapper.updateByPrimaryKeySelective(carport);
		//创建车型日志
		operateLogService.createOperateLogForCarport(carport);
	}

	/**
	 * 下架
	 */
	@Override
	public void updateCarportTheShelves(Carport carport) {
		ParamVerifyUtils.paramNotNull(carport);
		ParamVerifyUtils.paramNotNull(carport.getId(), "车库上架，ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifierId(), "车库上架，操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(carport.getModifier(), "车库上架，操作人不能为空");
		//验证数据是否存在
		Carport carportView = this.getCarportById(carport.getId());
		if(carportView==null){
			throw new RuntimeExceptionWarn("车库不存在");
		}
		
		if(carportView.getValid() == Valid.DOWN.getValue().intValue()){
			throw new RuntimeExceptionWarn("为下架状态不可重新变更");
		}
		carport.setValid(Valid.DOWN.getValue());
		carport.setLasttimeModify(new Date());
		//修改车型状态
		this.carportMapper.updateByPrimaryKeySelective(carport);
		//创建车型日志
		operateLogService.createOperateLogForCarport(carport);
	}
	
	/**
	* @Title: createCarportCartypeBatch 
	* @Description: 创建车库车型关系
	* @param carportCartype
	* @return void
	 */
	private void createCarportCartypeBatch(Carport carport) {
		if(carport.getCarTypes().isEmpty()){
			throw new RuntimeExceptionWarn("创建车库，车型数据不能为空");
		}
		List<CarType> lists = carport.getCarTypes();
		List<CarportCartype> carportCartypes = new ArrayList<>();
		for (CarType carTypeView : lists) {
			ParamVerifyUtils.paramNotNull(carTypeView.getId(), "创建车库，车型ID不能为空");
			//验证车型信息
			CarType carType = carTypeService.getCarTypeById(carTypeView.getId());
			if(carType == null){
				throw new RuntimeExceptionWarn("创建车库，车型不存在");
			}
			CarportCartype carportCartypeView = new CarportCartype();
			carportCartypeView.setCarTypeId(carType.getId());
			carportCartypeView.setCarportId(carport.getId());
			carportCartypeView.setBrand(carType.getBrand());
			carportCartypeView.setCarType(carType.getCarType());
			carportCartypeView.setCreated(carport.getCreated());
			carportCartypeView.setLasttimeModify(carport.getLasttimeModify());
			carportCartypeView.setModifierId(carport.getModifierId());
			carportCartypeView.setModifier(carport.getModifier());
			carportCartypeView.setValid(Valid.UP.getValue());
			carportCartypes.add(carportCartypeView);
		}
		this.carportCartypeMapper.insertBatch(carportCartypes);
	}
	
	/**
	* @Title: updateCarportCartypeBatch 
	* @Description: 修改车库车型关系
	* @param carportCartype
	* @return void
	 */
	private void updateCarportCartypeBatch(Carport carport) {
		if(carport.getCarTypes().isEmpty()){
			throw new RuntimeExceptionWarn("创建车库，车型数据不能为空");
		}
		//删除旧的车库车型
		this.deleteCarportCartypeByCarprotId(carport);
		//添加新的车库车型
		this.createCarportCartypeBatch(carport);
	}
	
	/**
	* @Title: deleteCarportCartypeByCarprotId 
	* @Description: 根据车库ID删除车型关系
	* @param carportCartype
	* @return void
	 */
	private void deleteCarportCartypeByCarprotId(Carport carport) {
		ParamVerifyUtils.paramNotNull(carport);
		ParamVerifyUtils.paramNotNull(carport.getId(), "删除车型，车库ID不能为空");
		CarportCartype carportCartype = new CarportCartype();
		carportCartype.setCarportId(carport.getId());
		this.carportCartypeMapper.deleteCarportCartypeByCarprotId(carportCartype);
	}
}
