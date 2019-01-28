package cn.colg.entity;

import cn.colg.core.BaseEntity;
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
}
