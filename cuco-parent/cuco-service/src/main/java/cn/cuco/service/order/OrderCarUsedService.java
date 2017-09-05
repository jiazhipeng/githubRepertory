package cn.cuco.service.order;

import java.math.BigDecimal;
import java.util.Map;

import cn.cuco.entity.OrderCarUsed;
import cn.cuco.page.PageResult;

/** 
* @ClassName: OrderCarUsedService 
* @Description: 用户用车结算明细相关接口
* @author zc.du
* @date 2017年2月23日 上午10:47:07  
*/
public interface OrderCarUsedService {
    
    /** 
    * @Title: getOrderCarUsedListByPage 
    * @Description: 分页查询用户用车明细列表
    * @author zc.du
    * @param orderCarUsed
    * @return PageResult<OrderCarUsed>
    */
    public PageResult<OrderCarUsed> getOrderCarUsedListByPage(OrderCarUsed orderCarUsed);

    /** 
    * @Title: createSettlementForButtler 
    * @Description: 管家结算
    * @author zc.du
    * @param orderCarUsed
    * @return OrderCarUsed
    */
    public OrderCarUsed createOrderCarUsedForButtler(OrderCarUsed orderCarUsed);
    
    /** 
    * @Title: createSettlementForSystem 
    * @Description: 系统结算
    * @author zc.du
    * @param orderCarUsed
    * @return OrderCarUsed
    */
    public OrderCarUsed createOrderCarUsedForSystem(OrderCarUsed orderCarUsed);
    
    /** 
    * @Title: createOrderCarUsedForAdditionalDeduction 
    * @Description: 补扣结算
    * @author zc.du
    * @param orderCarUsed
    * @return OrderCarUsed
    */
    public OrderCarUsed createOrderCarUsedForAdditionalDeduction(OrderCarUsed orderCarUsed);
    
    /** 
     * @Title: getMemberCarUsedHabit 
     * @Description: 根据用户ID获取用户用车里程统计
     * @author zc.du
     * @param memberId
     * @return Map<String,Integer>
     */
     public Map<String,Integer> getMemberDriveMileageStatistics(Long memberId);
     
     /** 
    * @Title: getOrderCarUsedById 
    * @Description: 根据结算订单ID查询结算订单
    * @author zc.du
    * @param id
    * @return OrderCarUsed
    */
    public OrderCarUsed getOrderCarUsedById(Long id);
    
    /**   
     * @Title: getOrderCarUsedCount   
     * @Description: 获取两个时间之间的结费次数(支持根据车型、车库ID、用车类型[会员用车、非会员用车])  
     * @param: @param orderCarUsed
     * @param: @return      
     * @return: int      
     */
    public int getOrderCarUsedCount(OrderCarUsed orderCarUsed);
    
    /** 
    * @Title: getOrderCarUsedMoney 
    * @Description: 获取两个时间之间的结费金额(支持根据车型、车库ID、用车类型[会员用车、非会员用车])
    * @author zc.du
    * @param orderCarUsed
    * @return BigDecimal
    */
    public BigDecimal getOrderCarUsedMoney(OrderCarUsed orderCarUsed);
    
}
