package cn.cuco.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 16:57:14
 */
public class TransferList implements Serializable {

    private static final long serialVersionUID = 9302761225938842L;

    private Long id;//

    private Long taskId;//任务ID

    private Long carUsedId;//用车记录ID

    private Integer type;//附件类型 0:交接单照片  1:车辆照片

    private String imageUrl;//图片地址

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCarUsedId() {
        return this.carUsedId;
    }

    public void setCarUsedId(Long carUsedId) {
        this.carUsedId = carUsedId;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
