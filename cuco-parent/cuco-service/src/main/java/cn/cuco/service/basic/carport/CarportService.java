package cn.cuco.service.basic.carport;


import cn.cuco.entity.Carport;
import cn.cuco.page.PageResult;

/**
* @ClassName: CarportService 
* @Description: TODO(车库接口) 
* @author huanghua
* @date 2017年2月21日 上午11:46:15
 */
public interface CarportService {
	
    /**
    * @Title: createCarport 
    * @Description: 新建车库
    * @param Carport
    * @return Carport    返回类型 
     */
    public Carport createCarport(Carport carport);

    /**
    * @Title: getCarportById 
    * @Description: 根据ID查询车库详情
    * @author huanghua
    * @param id
    * @return
    * @return Carport
     */
    public Carport getCarportById(Long id);
    
    /**
    * @Title: getCarportByPage 
    * @Description: 分页车库
    * @author huanghua
    * @param carport
    * @return
    * @return PageResult<Carport>
     */
    public PageResult<Carport> getCarportByPage(Carport carport);
    
    /**
    * @Title: updateCarport 
    * @Description: TODO(修改车库) 
    * @param carport
    * @return Carport    返回类型 
     */
    public Carport updateCarport(Carport carport);

    /**
    * @Title: updateCarportShelves 
    * @Description: TODO(车库上架) 
    * @param carport
    * @return void    返回类型 
     */
    public void updateCarportShelves(Carport carport);

    /**
    * @Title: updateCarportTheShelves 
    * @Description: TODO(车库下架) 
    * @param carport
    * @return void    返回类型 
     */
    public void updateCarportTheShelves(Carport carport);
    
}