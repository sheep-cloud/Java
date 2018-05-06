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
public class BnBroSettingEntity extends BaseEntity implements Serializable {
    /**
     * 金融机构编码
     */
    private Integer institutionId;

    /**
     * 额度
     */
    private Integer limitCount;

    /**
     * 
     */
    private String createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String updateUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 当机构改变额度的时候，修改成0，然后新增一条新的记录，这样能保证旧的账单能用旧的规则设置
     */
    private Integer status;

    /**
     * 
     */
    private Date limitStartTime;

    /**
     * 
     */
    private Date limitEndTime;

    /**
     * 
     */
    private BigDecimal perOper;

    /**
     * 
     */
    private Integer limitDaysCount;

    /**
     * 
     */
    private BigDecimal otPerOper;

    /**
     * 
     */
    private BigDecimal firstOper;

    /**
     * 
     */
    private Integer firstLimitDays;

    private static final long serialVersionUID = 1L;

    /**
     * 获取金融机构编码
     *
     * @return INSTITUTION_ID - 金融机构编码
     */
    public Integer getInstitutionId() {
        return institutionId;
    }

    /**
    /* 设置institutionId
     *
     * @param institutionId 金融机构编码
     */
    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * 获取额度
     *
     * @return LIMIT_COUNT - 额度
     */
    public Integer getLimitCount() {
        return limitCount;
    }

    /**
    /* 设置limitCount
     *
     * @param limitCount 额度
     */
    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    /**
     * 获取
     *
     * @return CREATE_USER_ID - 
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
    /* 设置createUserId
     *
     * @param createUserId 
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * 获取
     *
     * @return CREATE_TIME - 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
    /* 设置createTime
     *
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     *
     * @return UPDATE_USER_ID - 
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
    /* 设置updateUserId
     *
     * @param updateUserId 
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    /**
     * 获取
     *
     * @return UPDATE_TIME - 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
    /* 设置updateTime
     *
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取当机构改变额度的时候，修改成0，然后新增一条新的记录，这样能保证旧的账单能用旧的规则设置
     *
     * @return STATUS - 当机构改变额度的时候，修改成0，然后新增一条新的记录，这样能保证旧的账单能用旧的规则设置
     */
    public Integer getStatus() {
        return status;
    }

    /**
    /* 设置status
     *
     * @param status 当机构改变额度的时候，修改成0，然后新增一条新的记录，这样能保证旧的账单能用旧的规则设置
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取
     *
     * @return LIMIT_START_TIME - 
     */
    public Date getLimitStartTime() {
        return limitStartTime;
    }

    /**
    /* 设置limitStartTime
     *
     * @param limitStartTime 
     */
    public void setLimitStartTime(Date limitStartTime) {
        this.limitStartTime = limitStartTime;
    }

    /**
     * 获取
     *
     * @return LIMIT_END_TIME - 
     */
    public Date getLimitEndTime() {
        return limitEndTime;
    }

    /**
    /* 设置limitEndTime
     *
     * @param limitEndTime 
     */
    public void setLimitEndTime(Date limitEndTime) {
        this.limitEndTime = limitEndTime;
    }

    /**
     * 获取
     *
     * @return PER_OPER - 
     */
    public BigDecimal getPerOper() {
        return perOper;
    }

    /**
    /* 设置perOper
     *
     * @param perOper 
     */
    public void setPerOper(BigDecimal perOper) {
        this.perOper = perOper;
    }

    /**
     * 获取
     *
     * @return LIMIT_DAYS_COUNT - 
     */
    public Integer getLimitDaysCount() {
        return limitDaysCount;
    }

    /**
    /* 设置limitDaysCount
     *
     * @param limitDaysCount 
     */
    public void setLimitDaysCount(Integer limitDaysCount) {
        this.limitDaysCount = limitDaysCount;
    }

    /**
     * 获取
     *
     * @return OT_PER_OPER - 
     */
    public BigDecimal getOtPerOper() {
        return otPerOper;
    }

    /**
    /* 设置otPerOper
     *
     * @param otPerOper 
     */
    public void setOtPerOper(BigDecimal otPerOper) {
        this.otPerOper = otPerOper;
    }

    /**
     * 获取
     *
     * @return FIRST_OPER - 
     */
    public BigDecimal getFirstOper() {
        return firstOper;
    }

    /**
    /* 设置firstOper
     *
     * @param firstOper 
     */
    public void setFirstOper(BigDecimal firstOper) {
        this.firstOper = firstOper;
    }

    /**
     * 获取
     *
     * @return FIRST_LIMIT_DAYS - 
     */
    public Integer getFirstLimitDays() {
        return firstLimitDays;
    }

    /**
    /* 设置firstLimitDays
     *
     * @param firstLimitDays 
     */
    public void setFirstLimitDays(Integer firstLimitDays) {
        this.firstLimitDays = firstLimitDays;
    }
}