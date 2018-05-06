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
public class BnInsProfitSumEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer institutionId;

    /**
     * 
     */
    private BigDecimal profitSum;

    /**
     * 
     */
    private Date profitTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取
     *
     * @return INSTITUTION_ID - 
     */
    public Integer getInstitutionId() {
        return institutionId;
    }

    /**
    /* 设置institutionId
     *
     * @param institutionId 
     */
    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * 获取
     *
     * @return PROFIT_SUM - 
     */
    public BigDecimal getProfitSum() {
        return profitSum;
    }

    /**
    /* 设置profitSum
     *
     * @param profitSum 
     */
    public void setProfitSum(BigDecimal profitSum) {
        this.profitSum = profitSum;
    }

    /**
     * 获取
     *
     * @return PROFIT_TIME - 
     */
    public Date getProfitTime() {
        return profitTime;
    }

    /**
    /* 设置profitTime
     *
     * @param profitTime 
     */
    public void setProfitTime(Date profitTime) {
        this.profitTime = profitTime;
    }
}