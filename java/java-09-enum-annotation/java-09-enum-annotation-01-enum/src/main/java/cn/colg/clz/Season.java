package cn.colg.clz;

import cn.colg.core.BaseEntity;

/**
 * 季节枚举类 - 自定义实现
 *
 * @author colg
 */
public class Season extends BaseEntity {

    /*
     * colg [自定义实现枚举类]
     *  1. 提供类的属性，声明为private final
     *  2. 声明为final的属性，在构造器中初始化
     *  3. 通过公共的方法来调用属性
     *  4. 创建枚举类的对象
     */

    /** 季节名称 */
    private final String seasonName;
    /** 季节描述 */
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    /// ----------------------------------------------------------------------------------------------------

    // 创建枚举类的对象
    /** 春季 */
    public static final Season SPRING = new Season("sprign", "春暖花开");
    /** 夏季 */
    public static final Season SUMMER = new Season("summer", "夏日炎炎");
    /** 秋季 */
    public static final Season FALL = new Season("fall", "秋高气爽");
    /** 冬季 */
    public static final Season WINTER = new Season("winter", "白雪皑皑");
}
