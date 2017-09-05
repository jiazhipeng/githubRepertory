package cn.cuco.service.member.carUsed;

import cn.cuco.entity.MemberCarport;
import cn.cuco.entity.MemberFavoriteCartype;
import cn.cuco.page.PageResult;

/** 
* @ClassName: MemberCarportService 
* @Description: 用户车库相关接口
* @author zc.du
* @date 2017年2月23日 上午10:33:07  
*/
public interface MemberCarportService {
    /** 
    * @Title: createMemberCarport 
    * @Description: 创建用户车库
    * @author zc.du
    * @param memberCarport
    * @return MemberCarport
    */
    public MemberCarport createMemberCarport(MemberCarport memberCarport);
    
    /** 
    * @Title: getMemberCarportById 
    * @Description: 根据用户车库ID获取用户车库
    * @author zc.du
    * @param id 用户车库ID
    * @return
    * @return MemberCarport
    */
    public MemberCarport getMemberCarportById(Long id);
    
    /** 
    * @Title: getMemberCarport 
    * @Description: 查询用户车库
    * @author zc.du
    * @param memberCarport
    * @return MemberCarport
    */
    public MemberCarport getMemberCarport(MemberCarport memberCarport);
    
    /** 
    * @Title: updateMemberCarportStatusByUnlockin 
    * @Description: 根据用户车库ID修改其状态为"解锁中"
    * @author zc.du
    * @param memberCarportId
    * @return MemberCarport
    */
    public MemberCarport updateMemberCarportStatusByUnlockin(Long memberCarportId);
    
    /** 
     * @Title: updateMemberCarportStatusByUnlocked
     * @Description: 根据用户车库ID修改其状态为"已解锁"
     * @author zc.du
     * @param memberCarportId
     * @return MemberCarport
     */
     public MemberCarport updateMemberCarportStatusByUnlocked(Long memberCarportId);
     
     /** 
      * @Title: updateMemberCarportStatusByDisable
      * @Description: 根据用户车库ID修改其状态为"已停用"
      * @author zc.du
      * @param memberCarportId
      * @return MemberCarport
      */
    public MemberCarport updateMemberCarportStatusByDisable(Long memberCarportId);
    
    /** 
    * @Title: getMemberCarportListByPage 
    * @Description: 根据用户ID分页查询用户车库列表
    * @author zc.du
    * @param memberCarport
    * @return PageResult<MemberCarport>
    */
    public PageResult<MemberCarport> getMemberCarportListByPage(MemberCarport memberCarport);
    
    /** 
    * @Title: createMarkFavoriteCarType 
    * @Description: 标记喜欢车型
    * @author zc.du
    * @param memberFavoriteCartype
    * @return MemberFavoriteCartype
    */
    public MemberFavoriteCartype createMarkFavoriteCarType(MemberFavoriteCartype memberFavoriteCartype); 
    
    /** 
    * @Title: updateCountOrMileage 
    * @Description: 更新"使用次数"、"行驶里程"数量(count、mileage自行设置、操作人、操作人ID)
    * @author zc.du
    * @param memberCarport
    * @return MemberCarport
    */
    public MemberCarport updateCountOrMileage(MemberCarport memberCarport);
}
