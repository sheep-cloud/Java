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
public class BnInterestItemEntity extends BaseEntity implements Serializable {
    /**
     * 
     */
    private Integer institutionId;

    /**
     * 
     */
    private Integer billId;

    /**
     * 
     */
    private Integer otDays;

    /**
     * 
     */
    private BigDecimal interest;

    /**
     * 0:未结清   1：已结清
     */
    private Integer status;

    /**
     * 
     */
    private Date interestCreateTime;

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

    /**
     * 获取
     *
     * @return OT_DAYS - 
     */
    public Integer getOtDays() {
        return otDays;
    }

    /**
    /* 设置otDays
     *
     * @param otDays 
     */
    public void setOtDays(Integer otDays) {
        this.otDays = otDays;
    }

    /**
     * 获取
     *
     * @return INTEREST - 
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
    /* 设置interest
     *
     * @param interest 
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取0:未结清   1：已结清
     *
     * @return STATUS - 0:未结清   1：已结清
     */
    public Integer getStatus() {
        return status;
    }

    /**
    /* 设置status
     *
     * @param status 0:未结清   1：已结清
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取
     *
     * @return INTEREST_CREATE_TIME - 
     */
    public Date getInterestCreateTime() {
        return interestCreateTime;
    }

    /**
    /* 设置interestCreateTime
     *
     * @param interestCreateTime 
     */
    public void setInterestCreateTime(Date interestCreateTime) {
        this.interestCreateTime = interestCreateTime;
    }
}