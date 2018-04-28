package cn.colg.strategy;

import cn.colg.entity.Employee;

/**
 * 根据年龄过滤员工
 *
 * @author colg
 */
public class FilterEmployeeByAge implements MyStrategy<Employee> {

    /**
     * 返回年龄大于35的员工
     *
     * @param t
     * @return
     */
    @Override
    public boolean compartor(Employee t) {
        return t.getAge() > 35;
    }

}
