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
public class BnSysSettingEntity extends BaseEntity implements Serializable {
    /**
     * 设置编码
            PF_INS_PERSENT ：平台与金融机构分配比例设置
            USER_LIMIT_INIT  ：用户额度设置（当前统一设置）
            TRADE_RULE_DES  ：交易规则描述
            LIMIT_COUT_DES    ： 额度说明描述
            FRIST_TRADE_INTEREST  ： 首笔交易利息设置
            INTEREST_RULE_DES  ：利息政策描述
            BACK_DAYS ：账单还清账期天数设置
            
     */
    private String settingKey;

    /**
     * 设置值
     */
    private String settingValue;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取设置编码
            PF_INS_PERSENT ：平台与金融机构分配比例设置
            USER_LIMIT_INIT  ：用户额度设置（当前统一设置）
            TRADE_RULE_DES  ：交易规则描述
            LIMIT_COUT_DES    ： 额度说明描述
            FRIST_TRADE_INTEREST  ： 首笔交易利息设置
            INTEREST_RULE_DES  ：利息政策描述
            BACK_DAYS ：账单还清账期天数设置
            
     *
     * @return SETTING_KEY - 设置编码
            PF_INS_PERSENT ：平台与金融机构分配比例设置
            USER_LIMIT_INIT  ：用户额度设置（当前统一设置）
            TRADE_RULE_DES  ：交易规则描述
            LIMIT_COUT_DES    ： 额度说明描述
            FRIST_TRADE_INTEREST  ： 首笔交易利息设置
            INTEREST_RULE_DES  ：利息政策描述
            BACK_DAYS ：账单还清账期天数设置
            
     */
    public String getSettingKey() {
        return settingKey;
    }

    /**
    /* 设置settingKey
     *
     * @param settingKey 设置编码
            PF_INS_PERSENT ：平台与金融机构分配比例设置
            USER_LIMIT_INIT  ：用户额度设置（当前统一设置）
            TRADE_RULE_DES  ：交易规则描述
            LIMIT_COUT_DES    ： 额度说明描述
            FRIST_TRADE_INTEREST  ： 首笔交易利息设置
            INTEREST_RULE_DES  ：利息政策描述
            BACK_DAYS ：账单还清账期天数设置
            
     */
    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey == null ? null : settingKey.trim();
    }

    /**
     * 获取设置值
     *
     * @return SETTING_VALUE - 设置值
     */
    public String getSettingValue() {
        return settingValue;
    }

    /**
    /* 设置settingValue
     *
     * @param settingValue 设置值
     */
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue == null ? null : settingValue.trim();
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
    /* 设置remark
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}