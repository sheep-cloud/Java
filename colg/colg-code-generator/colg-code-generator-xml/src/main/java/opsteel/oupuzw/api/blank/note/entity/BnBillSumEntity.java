package opsteel.oupuzw.api.blank.note.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import opsteel.oupuzw.common.entity.BaseEntity;

/**
 *  - entity
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class BnBillSumEntity extends BaseEntity implements Serializable {
    /**
     * 金融机构编码，外部系统
     */
    private Integer institutionId;

    /**
     * 
     */
    private BigDecimal broCountSum;

    /**
     * 
     */
    private Date sumTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取金融机构编码，外部系统
     *
     * @return INSTITUTION_ID - 金融机构编码，外部系统
     */
    public Integer getInstitutionId() {
        return institutionId;
    }

    /**
    /* 设置institutionId
     *
     * @param institutionId 金融机构编码，外部系统
     */
    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * 获取
     *
     * @return BRO_COUNT_SUM - 
     */
    public BigDecimal getBroCountSum() {
        return broCountSum;
    }

    /**
    /* 设置broCountSum
     *
     * @param broCountSum 
     */
    public void setBroCountSum(BigDecimal broCountSum) {
        this.broCountSum = broCountSum;
    }

    /**
     * 获取
     *
     * @return SUM_TIME - 
     */
    public Date getSumTime() {
        return sumTime;
    }

    /**
    /* 设置sumTime
     *
     * @param sumTime 
     */
    public void setSumTime(Date sumTime) {
        this.sumTime = sumTime;
    }
}