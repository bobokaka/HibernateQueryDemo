package com.edp.hibernate.demo1;



/**
 * 
 * @Title: HibernateDemo2.java
 * @Package com.edp.hibernate.demo1
 * @author EdPeng
 * @version 创建时间 2020年2月9日下午6:42:17
 * @Description QBC查询
 * @version V1.0
 */

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.domain.LinkMan;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo2 {
	@Test
	/**
	 * @Title: demo1
	 * @Description: 简单的查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 简单查询
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. 获取CriteriaQuery对象
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// 2. 指定根条件
		createQuery.from(Customer.class);
		// 3. 通过session执行查询
		List<Customer> list = session.createQuery(createQuery).list();
		for (Customer customer : list) {
			System.out.println(customer);
		}

		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description: 排序查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 排序查询
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. 获取CriteriaQuery对象
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// 2. 指定根条件,获得实体的查询根对象
		Root<Customer> root = createQuery.from(Customer.class);
		// 降序排序
		createQuery.select(root);
		// 查询结果按id降序排列
// Order order = criteriaBuilder.desc(from.get("cust_id"));
// createQuery.orderBy(order);
		createQuery.orderBy(criteriaBuilder.desc(root.get("cust_id")));
		// 4. 将查询条件设置到where方法中
		// 5. 通过session执行查询
		List<Customer> list = session.createQuery(createQuery).list();
		for (Customer customer : list) {
			System.out.println(customer);
		}

		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo3
	 * @Description: 分页查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 排序查询
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. 获取CriteriaQuery对象
		CriteriaQuery<LinkMan> createQuery = criteriaBuilder.createQuery(LinkMan.class);

		// 方法1：
// int pageIndex = 11;
// int pageSize = 10;
// List<Customer> list =
// session.createQuery(createQuery).setFirstResult(pageIndex).setMaxResults(pageSize).list();

// 方法2
// 要查询对象的实体，可以理解为需要查询的表
		Root<LinkMan> root = createQuery.from(LinkMan.class);

		TypedQuery<LinkMan> typedQuery = session.createQuery(createQuery);
		int pageIndex = 11;
		int pageSize = 10;
		// 设置分页
		typedQuery.setFirstResult(pageIndex);
		typedQuery.setMaxResults(pageSize);

		List<LinkMan> list = typedQuery.getResultList();
		for (LinkMan linkMan : list) {
			System.out.println(linkMan);
		}

		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo4
	 * @Description: 条件查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 条件查询
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. 获取CriteriaQuery对象
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// 查询客户的来源是小广告的
		Root<Customer> root = createQuery.from(Customer.class);

		/**
		 * = eq
		 * > gt
		 * >= ge
		 * < lt
		 * <= le
		 * <> notEqual(不等于)
		 * 相似 like
		 * where后面不同的取值 in
		 * 和 and
		 * 或 or
		 */
		Predicate predicate1 = criteriaBuilder.equal(root.get("cust_source"), "小广告");
// Predicate predicate2 = criteriaBuilder.gt(root.get("cust_id"), 2);
// Predicate predicate3 = criteriaBuilder.ge(root.get("cust_id"), 2);
// Predicate predicate4 = criteriaBuilder.lt(root.get("cust_id"), 2);
// Predicate predicate5 = criteriaBuilder.le(root.get("cust_id"), 2);
// Predicate predicate6 = criteriaBuilder.notEqual(root.get("cust_id"), 2);
// Predicate predicate7 = criteriaBuilder.like(root.get("cust_source"), "小%");
// Path<Object> path = root.get("cust_id");
// Predicate predicate8 = criteriaBuilder.in(path);
// Predicate predicate9 = criteriaBuilder.and(predicate1);
// Predicate predicate10 = criteriaBuilder.or(predicate1);

		// 将查询条件设置到where方法中,可以设置多个条件
		// 还可以这样搭配：criteria.where(criteriaBuilder.or(criteriaBuilder.and(predicate1,
		// predicate2), predicate3));
		// criteria.where(criteriaBuilder.and(predicate1, predicate2));

		createQuery.where(criteriaBuilder.and(predicate1));
		List<Customer> list = session.createQuery(createQuery).list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo5
	 * @Description: 统计查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 统计查询习惯性使用HQL，不习惯QBC方式
		// 统计查询，查询表中有多少条数据
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. 获取CriteriaQuery对象
		CriteriaQuery<Long> createQuery = criteriaBuilder.createQuery(Long.class);
		Root<LinkMan> root = createQuery.from(LinkMan.class);
		/**
		 * add ：普通的条件，where后面的条件
		 * addOrder ：排序
		 * setProject ：聚合函数和group by having
		 * 
		 */
		createQuery.select(criteriaBuilder.count(root));
		// 唯一的结果uniqueResult()方法
		Long count = session.createQuery(createQuery).uniqueResult();
		System.out.println("总数为：" + count);
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo6
	 * @Description: 离线条件查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo6() {
		// web层
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("cust_name", "李%"));

		// DAO中
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 获得一个可执行的Criteria：getExecutableCriteria()
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
}
