package cn.colg.enums;

import java.util.Calendar;

import cn.colg.enumsinterface.Info;
import cn.hutool.core.lang.Console;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 星期枚举
 *
 * @author colg
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WeekEnum implements Info {
    /** 周日 */
    SUNDAY(0, "星期日") {
        @Override
        public void show() {
            Console.log("今天是星期日");
        }
    },
    /** 周一 */
    MONDAY(1, "星期一"),
    /** 周二 */
    TUESDAY(2, "星期二"),
    /** 周三 */
    WEDNESDAY(3, "星期三"),
    /** 周四 */
    THURSDAY(4, "星期四"),
    /** 周五 */
    FRIDAY(5, "星期五"),
    /** 周六 */
    SATURDAY(6, "星期六");

    /** 星期对应{@link Calendar} 中的Week值 */
    private int value;
    /** 星期的中文名 */
    private String chinese;

    @Override
    public void show() {}

}
