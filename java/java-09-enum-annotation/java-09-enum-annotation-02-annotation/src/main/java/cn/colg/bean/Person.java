package cn.colg.bean;

import cn.hutool.core.lang.Console;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person 实体
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    private String name;
    private Integer age;

    /// ----------------------------------------------------------------------------------------------------

    public void wali() {
        Console.log("走路");
    }

    public void eat() {
        Console.log("吃饭");
    }
}
