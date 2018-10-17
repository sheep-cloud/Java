package cn.colg.enums;

import cn.colg.enumsinterface.Info;
import cn.hutool.core.lang.Console;

/**
 * 季节枚举类
 *
 * @author colg
 */
public enum SeasonEnum implements Info {

    /*
     * colg [枚举类]
     *  1. 如何自定义枚举类
     *  2. 如何使用enmu关键字定义枚举类
     *      > 常用的方法: values(); valueOf(String str)
     *      > 如何让枚举类实现接口： 可以让不同的枚举类的对象调用被重写的抽象方法，执行的效果不同。（相当于让每个对象重写抽象方法）
     */

    /** 春季 */
    SPRING("sprign", "春暖花开") {
        @Override
        public void show() {
            Console.log("春天在哪里？");
        }
    },
    /** 夏季 */
    SUMMER("summer", "夏日炎炎") {
        @Override
        public void show() {
            Console.log("生如夏花");
        }
    },
    /** 秋季 */
    FALL("fall", "秋高气爽") {
        @Override
        public void show() {
            Console.log("秋天是用来分手的季节");
        }
    },
    /** 冬季 */
    WINTER("winter", "白雪皑皑") {
        @Override
        public void show() {
            Console.log("冬天里的一把火");
        }
    };

    /** 季节名称 */
    private String seasonName;
    /** 季节描述 */
    private String seasonDesc;

    private SeasonEnum(String seasonName, String seasonDesc) {
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

    @Override
    public void show() {
        Console.log("重写抽象方法");
    }

}
