package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:41
 */
public class InsuranceSubjects extends PageQuery implements Serializable {

    private static final long serialVersionUID = 4376052521674725L;

    private Long id;//

    private String subjectNum;//科目编号

    private String subjectName;//科目名称

    private BigDecimal subjectPrice;//科目金额

    private Integer type;//科目所属类型（0：交强险；1：商业险）

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectNum() {
        return this.subjectNum;
    }

    public void setSubjectNum(String subjectNum) {
        this.subjectNum = subjectNum;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public BigDecimal getSubjectPrice() {
        return this.subjectPrice;
    }

    public void setSubjectPrice(BigDecimal subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
