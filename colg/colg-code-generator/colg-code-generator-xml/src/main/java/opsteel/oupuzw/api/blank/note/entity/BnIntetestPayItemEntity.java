package opsteel.oupuzw.api.blank.note.entity;

import java.io.Serializable;
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
public class BnIntetestPayItemEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer interestItemId;

    /**
     * 
     */
    private Integer payItemId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取
     *
     * @return INTEREST_ITEM_ID - 
     */
    public Integer getInterestItemId() {
        return interestItemId;
    }

    /**
    /* 设置interestItemId
     *
     * @param interestItemId 
     */
    public void setInterestItemId(Integer interestItemId) {
        this.interestItemId = interestItemId;
    }

    /**
     * 获取
     *
     * @return PAY_ITEM_ID - 
     */
    public Integer getPayItemId() {
        return payItemId;
    }

    /**
    /* 设置payItemId
     *
     * @param payItemId 
     */
    public void setPayItemId(Integer payItemId) {
        this.payItemId = payItemId;
    }
}