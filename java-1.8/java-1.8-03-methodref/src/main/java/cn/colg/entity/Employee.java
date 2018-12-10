package cn.colg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工Entity
 *
 * @author colg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Employee {

    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 工资 */
    private Double salary;
}
