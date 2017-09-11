package cn.bdqn.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Project implements Serializable {
    private int pid;
    private String pname;

    private Set<Employee> employees = new HashSet<Employee>();
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
