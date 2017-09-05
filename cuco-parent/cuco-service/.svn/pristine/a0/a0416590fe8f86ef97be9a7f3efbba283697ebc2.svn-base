package cn.cuco.service.basic.business.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CarMapper;
import cn.cuco.dao.SupplierMapper;
import cn.cuco.entity.Car;
import cn.cuco.entity.Supplier;
import cn.cuco.enums.Valid;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.business.SupplierService;

@Service(value = "supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper<Supplier> supplierMapper;
    
    @Autowired
    private CarMapper<Car> carMapper;

    /**
     * 创建供应商
     */
	@Override
	public Supplier createSupplier(Supplier supplier) {
		ParamVerifyUtils.paramNotNull(supplier);
		ParamVerifyUtils.paramNotNull(supplier.getSupplierName(), "创建供应商，名称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSupplierReferred(), "创建供应商，简称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getFirstContactMobile(), "创建供应商，第一联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getFirstContactName(), "创建供应商，第一联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSecondContactMobile(), "创建供应商，第二联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSecondContactName(), "创建供应商，第二联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getEmail(), "创建供应商，邮箱不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getInsuranceId(), "创建供应商，保险公司ID不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getInsuranceName(), "创建供应商，保险公司名称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getOutDangerMobile(), "创建供应商，出险联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getOutDangerName(), "创建供应商，出险联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getRepairShops(), "创建供应商，合作维修厂不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getRepairShopsMobile(), "创建供应商，合作维修厂电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getModifierId(), "创建供应商，创建人ID不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getModifier(), "创建供应商，创建人不能为空");
		//验证名称是否重复
		Supplier searchSupplier = new Supplier();
		searchSupplier.setSupplierName(supplier.getSupplierName());
		List<Supplier> list = this.supplierMapper.selectByCondition(searchSupplier);
		if(!list.isEmpty()){
			 throw new RuntimeExceptionWarn("供应商名称重复");
		}
		supplier.setCreated(new Date());
		supplier.setLasttimeModify(new Date());
		supplier.setValid(Valid.UP.getValue());
		this.supplierMapper.insertSelective(supplier);
		return supplier;
	}

	/**
	 * 修改供应商
	 */
	@Override
	public Supplier updateSupplier(Supplier supplier) {
		ParamVerifyUtils.paramNotNull(supplier);
		ParamVerifyUtils.paramNotNull(supplier.getId(), "修改供应商，ID不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSupplierName(), "修改供应商，名称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSupplierReferred(), "修改供应商，简称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getFirstContactMobile(), "修改供应商，第一联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getFirstContactName(), "修改供应商，第一联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSecondContactMobile(), "修改供应商，第二联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getSecondContactName(), "修改供应商，第二联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getEmail(), "修改供应商，邮箱不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getInsuranceId(), "修改供应商，保险公司ID不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getInsuranceName(), "修改供应商，保险公司名称不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getOutDangerMobile(), "修改供应商，出险联系人电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getOutDangerName(), "修改供应商，出险联系人姓名不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getRepairShops(), "修改供应商，合作维修厂不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getRepairShopsMobile(), "修改供应商，合作维修厂电话不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getModifierId(), "修改供应商，修改人ID不能为空");
		ParamVerifyUtils.paramNotNull(supplier.getModifier(), "修改供应商，修改人不能为空");
		//验证名称是否重复
		Supplier searchSupplier = new Supplier();
		searchSupplier.setSupplierName(supplier.getSupplierName());
		List<Supplier> list = this.supplierMapper.selectByCondition(searchSupplier);
		for (Supplier supplierView : list) {
			if(supplierView.getId() != supplier.getId()){
				throw new RuntimeExceptionWarn("供应商名称重复");
			}
		}
		supplier.setCreated(new Date());
		supplier.setLasttimeModify(new Date());
		this.supplierMapper.updateByPrimaryKeySelective(supplier);
		return supplier;
	}

	/**
	 * 查询供应商详情
	 */
	@Override
	public Supplier getSupplierById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询供应商，ID不能为空");
		return this.supplierMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页供应商
	 */
	@Override
	public PageResult<Supplier> getSupplierByPage(Supplier supplier) {
		Integer page = supplier.getPage();
	    Integer pageSize = supplier.getPageSize();
	     /*搜索条件*/
	    Supplier parkingSearch = new Supplier();
	    parkingSearch.setValid(Valid.UP.getValue());
        if(StringUtils.isNotBlank(supplier.getSupplierName())){
        	parkingSearch.setSupplierName(supplier.getSupplierName());
        }
	    List<Supplier> suppliers = null;
	    /*总记录数*/
	    Integer totalSize = this.supplierMapper.selectCountByConditionByPage(parkingSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    suppliers = this.supplierMapper.selectByConditionByPage(parkingSearch);
	    if(CollectionUtils.isNotEmpty(suppliers)){
	    	for (Supplier supplierView : suppliers) {
	    		Car car = new Car();
	    		car.setCarSupplierId(supplierView.getId());
	    		supplierView.setCarCount(carMapper.selectCountByCondition(car));
	    	}
	    }
        PageResult<Supplier> pageResult = new PageResult<Supplier>(page,pageSize,totalSize,suppliers);
		return pageResult;
	}


}
