package com.hy.gcar.service.transferList;


import com.hy.gcar.entity.TransferList;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <TransferList>
 * @since 1.0,2016-09-12 10:36:30
 */
public interface TransferListService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(TransferList tdTransferList) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<TransferList> tdTransferList) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(TransferList tdTransferList);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdTransferList
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public TransferList findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdTransferList
    *@返回值 List<TransferList> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public List<TransferList> selectByCondition(TransferList tdTransferList);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdTransferList
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(TransferList tdTransferList);


    /**
     * 上传交接单
     * @param transferList
     * @return
     */
    public TransferList uploadTransferPicture(TransferList transferList) throws Exception;


    /**
     * 上传车辆图片
     * @param transferList
     * @return
     */
    public TransferList uploadCarPicture(TransferList transferList) throws Exception;

    /**
     * @Title: downloadMediaFromWx
     * @Description: 下载微信 图片到本地
     * @param @return
     * @param @throws Exception
     * @return String
     * @author q.p.x
     * @date 2016年8月16日 下午8:01:36
     * @throws
     */
    public String downloadMediaFromWx(String mediaId) throws Exception;

    /**
     * 裁剪图片
     * @param fileSavePathUrl
     * @return
     */
    public String cutCenterImage(String fileSavePathUrl) throws IOException;

    /**
     * 根据任务id删除照片
     * @param transferList
     */
    public Integer deleteByTaskID(TransferList transferList);
}
