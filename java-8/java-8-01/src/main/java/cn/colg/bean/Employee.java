package cn.colg.bean;

/**
 * 员工实体
 *
 * @author colg
 */
public class Employee {

    private String name;
    private Integer age;
    private Double salary;

    public Employee() {}

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"age\":\"" + age + "\",\"salary\":\"" + salary + "\"}";
    }

}
