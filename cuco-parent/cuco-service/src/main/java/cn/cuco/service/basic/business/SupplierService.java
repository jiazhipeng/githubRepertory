package cn.cuco.service.basic.business;


import cn.cuco.entity.Supplier;
import cn.cuco.page.PageResult;

/**
* @ClassName: SupplierService 
* @Description: 供应商接口
* @author huanghua
* @date 2017年2月21日 下午2:32:06
 */
public interface SupplierService {
   
	/**
	* @Title: createSupplier 
	* @Description: 创建供应商
	* @param supplier
	* @return Supplier
	 */
    public Supplier createSupplier(Supplier supplier);

    /**
    * @Title: updateSupplier 
    * @Description: 修改供应商信息
    * @param supplier
    * @return Supplier
     */
    public Supplier updateSupplier(Supplier supplier);

    /**
    * @Title: getSupplierById 
    * @Description: 取供应商详情
    * @author huanghua
    * @param id
    * @return Supplier
     */
    public Supplier getSupplierById(Long id);

    /**
    * @Title: getSupplierByPage 
    * @Description: 分页取供应商
    * @author huanghua
    * @param supplier
    * @return
    * @return PageResult<Supplier>
     */
    public PageResult<Supplier> getSupplierByPage(Supplier supplier);


}
