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
public class BnSysPaySumEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer broUserId;

    /**
     * 
     */
    private BigDecimal paySum;

    private static final long serialVersionUID = 1L;

    /**
     * 获取
     *
     * @return BRO_USER_ID - 
     */
    public Integer getBroUserId() {
        return broUserId;
    }

    /**
    /* 设置broUserId
     *
     * @param broUserId 
     */
    public void setBroUserId(Integer broUserId) {
        this.broUserId = broUserId;
    }

    /**
     * 获取
     *
     * @return PAY_SUM - 
     */
    public BigDecimal getPaySum() {
        return paySum;
    }

    /**
    /* 设置paySum
     *
     * @param paySum 
     */
    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }
}