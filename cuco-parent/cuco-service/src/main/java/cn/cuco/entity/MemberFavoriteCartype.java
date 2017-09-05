package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-27 14:53:47
 */
public class MemberFavoriteCartype implements Serializable {

    private static final long serialVersionUID = 3816261734101563L;

    private Long id;//主键

    private Long memberId;//用户ID

    private Long carportId;//车库ID

    private Long cartypeId;//车型ID

    private Date created;//创建时间

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getCartypeId() {
        return this.cartypeId;
    }

    public void setCartypeId(Long cartypeId) {
        this.cartypeId = cartypeId;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
