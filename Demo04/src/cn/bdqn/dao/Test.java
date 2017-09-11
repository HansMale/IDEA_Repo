package cn.bdqn.dao;

import cn.bdqn.entity.Employee;
import cn.bdqn.entity.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class Test {
    public void test1(){
        Session session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class,1);
        Set<Employee> employees = project.getEmployees();
        for (Employee employee : employees) {
            System.out.println(employee.getEname()+">>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }
    public void test2(){
        Session session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class,1);
        Set<Project> projects = employee.getProjects();
        for (Project project : projects) {
            System.out.println(project.getPname()+">>>>>>>>>>>>>>>>>>>>>>");
        }
    }
    public void test3(){
        Session session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class,1);
        Employee employee_1 = (Employee) session.get(Employee.class,1);
        Employee employee_2 = (Employee) session.get(Employee.class,2);
        Employee employee_3 = (Employee) session.get(Employee.class,3);
        employee_1.getProjects().remove(project);
        employee_2.getProjects().remove(project);
        employee_3.getProjects().remove(project);
        project.getEmployees().removeAll(project.getEmployees());
        transaction.commit();
    }
    public void test4(){
        Session session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class,4);
        Employee employee = new Employee();
        employee.setEname("ff");
        project.setPname("网站");
        project.getEmployees().add(employee);
        session.save(employee);
        transaction.commit();
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test4();
    }
}
