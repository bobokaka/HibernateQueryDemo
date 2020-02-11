package com.edp.hibernate.demo1;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @Title: HibernateDemo1.java
 * @Package com.edp.hibernate.demo1
 * @author EdPeng
 * @version 创建时间 2020年2月7日下午9:01:14
 * @Description HQL查询方式的测试类
 * @version V1.0
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.domain.LinkMan;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo1 {

	@Test
	// 初始化数据库
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 创建一个客户
		Customer customer = new Customer();
		customer.setCust_name("王三");

		for (int i = 0; i <= 10; i++) {
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("五菱之光" + i);
			linkMan.setCustomer(customer);

			customer.getLinkMans().add(linkMan);
			session.save(linkMan);
		}

		session.save(customer);

		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description: HQL的简单查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer");

		// sql中支持*号的写法：select * from cst_customer;但是在HQL中不支持*号的写法
		// Query query = session.createQuery("select * from Customer");该写法错误
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo3
	 * @Description: HQL的别名查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 取别名叫bieMing
// Query query = session.createQuery("from Customer bieMing");
		Query query = session.createQuery("select bieMing from Customer bieMing");
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo4
	 * @Description: HQL的排序查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 排序的查询
		// 默认情况，升序,或使用asc
// List<Customer> list = session.createQuery("from Customer order by
// cust_id").list();
// 设置降序排序
		List<Customer> list = session.createQuery("from Customer order by cust_id desc").list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo5
	 * @Description: HQL的条件查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 条件的查询
		// 1.按位置绑定
// Query query= session.createQuery("from Customer where cust_name = ?0");
// query.setParameter(0, "李二");
// List<Customer> list = query.list();

// Query query = session.createQuery("from Customer cust where cust.cust_source
// = ?0 and cust.cust_name like ?1");
// query.setParameter(0, "网络推广");
// query.setParameter(1, "李%");
// List<Customer> list = query.list();

		// 2.按名称绑定
		// 带别名
// Query query = session
// .createQuery("from Customer cust where cust.cust_source = :aaa and
// cust.cust_name like :bbb");
// query.setParameter("aaa", "网络推广");
// query.setParameter("bbb", "李%");
// List<Customer> list = query.list();
// 不带别名
		Query query = session.createQuery("from Customer where cust_source = :aaa and cust_name like :bbb");
		query.setParameter("aaa", "网络推广");
		query.setParameter("bbb", "李%");
		List<Customer> list = query.list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo6
	 * @Description: HQL的投影查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 投影查询
		// 单个属性
// List<Object> list = session.createQuery("select c.cust_name from Customer
// c").list();
//
// for (Object object : list) {
// System.out.println(object);
// }
// List<Object[]> list = session.createQuery("select c.cust_name,c.cust_source
// from Customer c").list();
// for (Object[] objects : list) {
// System.out.println(Arrays.toString(objects));
// }
// 查询多个属性，但还是想封装到对象中
		List<Customer> list = session.createQuery("select new Customer(cust_name,cust_source) from Customer").list();

		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo7
	 * @Description: HQL的分页查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from LinkMan");
		query.setFirstResult(11);
		query.setMaxResults(10);
		List<LinkMan> list = query.list();

		for (LinkMan linkMan : list) {
			System.out.println(linkMan);
		}

		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo8
	 * @Description: HQL的分组统计查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 统计表中总的记录数目,uniqueResult()返回唯一结果
		// 聚合函数的使用：count(),max(),min(),avg(),sum()在HQL中都可以用
// Object object = session.createQuery("select count(*) from
// Customer").uniqueResult();
// System.out.println(object);
// 分组统计
// 根据客户的来源统计客户的个数
		List<Object[]> list = session
				.createQuery("select cust_source ,count(*) from Customer group by cust_source having count(*) >= 2")
				.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo9
	 * @Description: HQL的多表查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 显示内连接,根据配置，自动关联
		// SELECT * FROM cst_customer JOIN cst_linkman ON cst_customer.cust_id =
		// cst_linkman.lkm_cust_id;
// List<Object[]> list = session.createQuery("from Customer c inner join
// c.linkMans").list();
// for (Object[] objects : list) {
// System.out.println(Arrays.toString(objects));
// }

		// 迫切内连接，在普通的内连接inner join后添加一个关键字fetch
		// fetch:通知Hibernate将另一个对象的数据封装到对象中
		//// 一条数据，数据里面有1个联系人
// List<Customer> list = session.createQuery("from Customer c inner join fetch
// c.linkMans").list();
// 三条数据，数据里面有10条联系人
		List<Customer> list = session.createQuery("select distinct c from Customer c inner join fetch c.linkMans")
				.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

}
