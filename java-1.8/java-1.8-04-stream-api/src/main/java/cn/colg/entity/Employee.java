package cn.colg.entity;

import cn.colg.core.BaseEntity;
import cn.colg.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 员工Entity
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Employee extends BaseEntity{

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
