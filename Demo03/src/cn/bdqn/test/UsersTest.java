package cn.bdqn.test;

import cn.bdqn.entity.District;
import cn.bdqn.entity.Street;
import cn.bdqn.entity.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.bdqn.dao.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class UsersTest {


	public List<Users> test1(){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Users where password like :index";
		Query query = session.createQuery(hql);
		query.setParameter("index","%"+"a"+"%");
		List<Users> usersList = query.list();
		return usersList;
	}
	public Iterator<Users> tesst2(){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Users where password like :index";
		Query query = session.createQuery(hql);
		query.setParameter("index","%"+"a"+"%");
		Iterator<Users> usersIterator = query.iterate();
		return usersIterator;
	}
	public List<Users> test3(Users users){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Users where 1=1";
		if (users.getName()!=null){
			hql+="and name like :name";
		}
		if (users.getPassword()!=null){
			hql+="  and password like :password";
		}
		Query query = session.createQuery(hql);
		query.setProperties(users);
		List<Users> usersList = query.list();
		return usersList;
	}
	public List<Users> test4(int currentPageNo,int pageSize){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Users u order by u.id";
		Query query = session.createQuery(hql);
		int startIndex = (currentPageNo-1)*pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List<Users> usersList = query.list();
		return usersList;
	}
	public List<String> test5(){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select name from Users";
		Query query = session.createQuery(hql);
		List<String> list = query.list();
		return list;
	}
	public List<Object[]> test6(){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select name,password from Users";
		Query query = session.createQuery(hql);
		List<Object[]> objects = query.list();
		return objects;
	}
	public List<Users> test7(){
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select new Users(id,name,password) from Users order by id";
		Query query = session.createQuery(hql);
		List<Users> usersList = query.list();
		return usersList;
	}
	public static void main(String[] args) {
		UsersTest usersTest = new UsersTest();
/*		List<Users> usersList = usersTest.test1();
		for (Users users : usersList) {
			System.out.println(users.getName()+">>>>>>>>>>>>>"+users.getPassword());
		}*/
/*		Users users = null;
		Iterator<Users> usersIterator = usersTest.tesst2();
		while (usersIterator.hasNext()){
			users = usersIterator.next();
			System.out.println(users.getName()+">>>>>>>>>>>>"+users.getPassword());
		}*/
/*		Users users = new Users("%a%","%s%");
		System.out.println(">>>>>>>>>>>>>>>>>"+users.getName());
		System.out.println(">>>>>>>>>>>>>>>>>"+users.getPassword());
		List<Users> usersList = usersTest.test3(users);
		for (Users users1 : usersList) {
			System.out.println(users1.getName()+">>>>>>>>>>>"+users1.getPassword());
		}*/
/*		int currentPageNo = 2;
		int pageSize = 3;
		List<Users> usersList = usersTest.test4(currentPageNo,pageSize);
		for (Users users : usersList) {
			System.out.println(users.getName()+">>>>>>>>>>>>>"+users.getPassword());
		}*/
/*		List<String> list = usersTest.test5();
		for (String s : list) {
			System.out.println(s);
		}*/
/*		List<Object[]> nameList = usersTest.test6();
		for (Object[] objects : nameList) {
			System.out.println(objects[0]+"-----------"+objects[1]);
		}*/
/*		List<Users> usersList = usersTest.test7();
		for (Users users : usersList) {
			System.out.println(users.getId()+">>>>>>>>"+users.getName()+">>>>>>>>"+users.getPassword());
		}*/
		Session session = HibernateUtil.currentSession();
		Transaction transaction = session.beginTransaction();
/*		District district = (District) session.get(District.class,9);
		Street street = (Street) session.get(Street.class,4);
		district.getStreets().remove(street);
		street.setDistrict(null);*/
/*		Street street1 = new Street();
		Street street2 = new Street();
		District district = new District();
		street1.setSname("光华路");
		street2.setSname("红庙坡");
		district.setDname("新城区");
		district.getStreets().add(street1);
		district.getStreets().add(street2);
		street1.setDistrict(district);
		street2.setDistrict(district);
		session.save(district);*/
		transaction.commit();
	}
}
