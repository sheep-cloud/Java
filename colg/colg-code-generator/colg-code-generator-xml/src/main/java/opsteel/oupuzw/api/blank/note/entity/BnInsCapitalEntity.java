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
public class BnInsCapitalEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer institutionId;

    /**
     * 
     */
    private BigDecimal capitalSum;

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
     * @return CAPITAL_SUM - 
     */
    public BigDecimal getCapitalSum() {
        return capitalSum;
    }

    /**
    /* 设置capitalSum
     *
     * @param capitalSum 
     */
    public void setCapitalSum(BigDecimal capitalSum) {
        this.capitalSum = capitalSum;
    }
}