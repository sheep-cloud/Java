package opsteel.oupuzw.api.blank.note.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class BnInterestSumEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer institutionId;

    /**
     * 
     */
    private BigDecimal interestSum;

    /**
     * 
     */
    private BigDecimal interestOk;

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
     * @return INTEREST_SUM - 
     */
    public BigDecimal getInterestSum() {
        return interestSum;
    }

    /**
    /* 设置interestSum
     *
     * @param interestSum 
     */
    public void setInterestSum(BigDecimal interestSum) {
        this.interestSum = interestSum;
    }

    /**
     * 获取
     *
     * @return INTEREST_OK - 
     */
    public BigDecimal getInterestOk() {
        return interestOk;
    }

    /**
    /* 设置interestOk
     *
     * @param interestOk 
     */
    public void setInterestOk(BigDecimal interestOk) {
        this.interestOk = interestOk;
    }
}