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
public class BnInsCapitalItemEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer institutionId;

    /**
     * 
     */
    private BigDecimal capital;

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
     * @return CAPITAL - 
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
    /* 设置capital
     *
     * @param capital 
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }
}