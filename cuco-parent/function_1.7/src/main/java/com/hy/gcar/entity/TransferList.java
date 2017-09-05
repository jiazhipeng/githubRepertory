package com.hy.gcar.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-12 10:36:30
 */
public class TransferList implements Serializable {

    private static final long serialVersionUID = 5121616167125722L;

    private Long id;//

    private Long taskId;//任务ID

    private Long powerUsedId;//用车记录ID

    private Integer type;//附件类型 0:交接单照片  1:车辆照片

    private String imageUrl;//图片地址

    private int uploadCount;//上传图片的个数

    public int getUploadCount() {
        return uploadCount;
    }

    public void setUploadCount(int uploadCount) {
        this.uploadCount = uploadCount;
    }

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

    public Long getPowerUsedId() {
        return this.powerUsedId;
    }

    public void setPowerUsedId(Long powerUsedId) {
        this.powerUsedId = powerUsedId;
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
