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
public class BnRestLimitEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer broUserId;

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
}