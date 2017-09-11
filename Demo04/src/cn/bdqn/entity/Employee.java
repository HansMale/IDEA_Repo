package cn.bdqn.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Employee implements Serializable {
    private int eid;
    private String ename;

    private Set<Project> projects = new HashSet<Project>();

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
