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
public class BnSysPayItemEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer broUserId;

    /**
     * 
     */
    private Date payTime;

    /**
     * 
     */
    private BigDecimal payCount;

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
     * @return PAY_TIME - 
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
    /* 设置payTime
     *
     * @param payTime 
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取
     *
     * @return PAY_COUNT - 
     */
    public BigDecimal getPayCount() {
        return payCount;
    }

    /**
    /* 设置payCount
     *
     * @param payCount 
     */
    public void setPayCount(BigDecimal payCount) {
        this.payCount = payCount;
    }
}