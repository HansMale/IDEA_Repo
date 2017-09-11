package cn.bdqn.test;


import org.hibernate.Session;
import org.junit.Test;

import cn.bdqn.dao.BaseDao;
import cn.bdqn.entity.Customer;
import cn.bdqn.entity.Order;

import java.util.Iterator;
import java.util.Set;

public class OneToMany extends BaseDao{
//	测试一对多关系

	@Test
	/**
	 * "增"
	 * 共打印8条语句
	 * 1-3 查找主键（native设置，Oracle 使用序列）
	 * 4-6 保存对象、维护外键
	 * 7-8 维护外键
	 * 解决办法：单纯指定关系由其中一方来维护，另一方不维护（外键维护的放弃只能由非外键所在对象放弃）
	 * inverse(默认为false)
	 */
/*
	<set name="orders" inverse="true">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 	</set>
*/
	public void test1(){
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________
		Customer customer = new Customer();
		customer.setName("Jerry");
		Order order1 = new Order();
		order1.setName("牙膏");
		Order order2 = new Order();
		order2.setName("毛巾");
		//inverse将主键维护交给了Order自身，所以Customer不用再维护
		/*customer.getOrders().add(order1);//维护关系
		customer.getOrders().add(order2);//维护关系*/
		order1.setCustomer(customer);//维护关系
		order2.setCustomer(customer);//维护关系
		session.save(customer);
		session.save(order1);
		session.save(order2);
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "删"
	 * 删除用户时，会先移除Customer引用的外键然后再删除Customer
	 * 维护一方的对象时会自动维护另一方的关系
	 *
	 * 当设置Customer的inverse为true时，customer不负责维护外键关系
	 * 直接删除会报错，引用了无效的ID,维护了主键约束
	 * 当然可以直接删除Order
	 */
/*
	<set name="orders" inverse="true">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 	</set>
*/
	public void test2(){
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Customer customer = (Customer) session.get(Customer.class,7);
//		session.delete(customer);
		//为了删除只有通过Order，即
		Set<Order> orders = customer.getOrders();
		for (Order order : orders) {
			order.setCustomer(null);//设置Order不属于任何Customer===移除外键约束
		}
		session.delete(customer);
//		Order order = (Order) session.get(Order.class,5);
//		session.delete(order);
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "增"
	 *保存Customer时候自动将Order也保存
	 * 解决办法：cascade
	 */
	public void test3(){
/*
		<set name="orders" inverse="true" cascade="save-update">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>
*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Customer customer = new Customer();
		customer.setName("张三");
		Order order1 = new Order();
		order1.setName("苹果");
		Order order2 = new Order();
		order2.setName("香蕉");
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		session.save(customer);
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "增"
	 *保存Customer时候自动将Order也保存
	 * 解决办法：cascade
	 */
	public void test4(){
/*		<set name="orders" inverse="false" cascade="save-update">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Customer customer = new Customer();
		customer.setName("张三");
		Order order1 = new Order();
		order1.setName("苹果");
		Order order2 = new Order();
		order2.setName("香蕉");
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		session.save(customer);
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "增"
	 *修改Order时会将Customer下的所有Order都修改
	 * 解决办法：cascade
	 */
	public void test5(){
/*		<set name="orders" inverse="false" cascade="save-update">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Customer customer = (Customer) session.get(Customer.class, 20);

		Set<Order> orders = customer.getOrders();

		for (Order order : orders) {
			order.setName("橘子");
		}
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "增"
	 *删除Customer时会将Customer下的所有Order都删除
	 * 解决办法：
	 * inverse:false 6条语句
	 * inverse:true  5条语句，不用维护外键
	 */
	public void test6(){
/*		<set name="orders" inverse="true" cascade="delete">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Customer customer = (Customer) session.get(Customer.class, 23);
		session.delete(customer);

		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
	@Test
	/**
	 * "增"
	 *删除Customer时会将Customer下的所有Order都删除
	 * 解决办法：
	 *假如操作双方都设置cascade为delete，任何一方操作都会导致所有的关系数据删除
	 */
	public void test7(){
/*		<set name="orders" inverse="true" cascade="delete">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>
 		<many-to-one name="customer" column="CID" class="Customer"  cascade="delete"/>

*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________

		Order order = (Order) session.get(Order.class,27);
		session.delete(order);
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}

	@Test
	/**
	 *
	 * cascade="delete-orphan"
	 *当没有任何外键引用Order时，Order会被删除
	 *
	 */
	public void test8(){
/*		<set name="orders" inverse="false" cascade="delete-orphan">
				<key column="CID" ></key>
				<one-to-many class="Order" />
 		</set>
 		<many-to-one name="customer" column="CID" class="Customer"/>

*/
		Session session = currentSession();
		//开启事务
		session.beginTransaction();
		//_______________________________________________________________________________________________
		Customer customer = (Customer) session.get(Customer.class,32);
		Iterator<Order> iterator = customer.getOrders().iterator();
		//遍历Customer下的Order，并将Order删除，维护关系
		while (iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		//_______________________________________________________________________________________________
		//提交事务
		session.getTransaction().commit();
	}
}
