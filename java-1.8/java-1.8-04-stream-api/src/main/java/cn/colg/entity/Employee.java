package cn.colg.entity;

import cn.colg.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工Entity
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Employee {

    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 工资 */
    private Double salary;

    /** 状态（枚举） */
    private Status status;

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}
