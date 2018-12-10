package cn.colg.strategy;

import cn.colg.entity.Employee;

/**
 * 根据工资过滤员工
 *
 * @author colg
 */
public class FilterEmployeeBySalary implements MyStrategy<Employee> {

    /**
     * 返回工作大于5000的员工
     *
     * @param t
     * @return
     */
    @Override
    public boolean compartor(Employee t) {
        return t.getSalary() > 5000;
    }
}
