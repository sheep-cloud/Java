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
public class BnBillPayItemEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer payItemId;

    /**
     * 
     */
    private Integer billId;

    private static final long serialVersionUID = 1L;

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

    /**
     * 获取
     *
     * @return BILL_ID - 
     */
    public Integer getBillId() {
        return billId;
    }

    /**
    /* 设置billId
     *
     * @param billId 
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }
}