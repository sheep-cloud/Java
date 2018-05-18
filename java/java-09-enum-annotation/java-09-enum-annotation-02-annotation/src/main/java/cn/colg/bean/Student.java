package cn.colg.bean;

import cn.colg.annotation.MyAnnotation;
import cn.hutool.core.lang.Console;

/**
 * Student 实体
 *
 * @author colg
 */
@MyAnnotation(value = "colg")
@Deprecated
public class Student extends Person {

    @Override
    public void wali() {
        Console.log("学生走路");
    }

    @Override
    public void eat() {
        Console.log("学生吃饭");
    }

}
