package com.edp.hibernate.demo1;



/**
 * 
 * @Title: HibernateDemo2.java
 * @Package com.edp.hibernate.demo1
 * @author EdPeng
 * @version ����ʱ�� 2020��2��9������6:42:17
 * @Description QBC��ѯ
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
	 * @Description: �򵥵Ĳ�ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// �򵥲�ѯ
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. ��ȡCriteriaQuery����
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// 2. ָ��������
		createQuery.from(Customer.class);
		// 3. ͨ��sessionִ�в�ѯ
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
	 * @Description: �����ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// �����ѯ
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. ��ȡCriteriaQuery����
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// 2. ָ��������,���ʵ��Ĳ�ѯ������
		Root<Customer> root = createQuery.from(Customer.class);
		// ��������
		createQuery.select(root);
		// ��ѯ�����id��������
// Order order = criteriaBuilder.desc(from.get("cust_id"));
// createQuery.orderBy(order);
		createQuery.orderBy(criteriaBuilder.desc(root.get("cust_id")));
		// 4. ����ѯ�������õ�where������
		// 5. ͨ��sessionִ�в�ѯ
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
	 * @Description: ��ҳ��ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// �����ѯ
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. ��ȡCriteriaQuery����
		CriteriaQuery<LinkMan> createQuery = criteriaBuilder.createQuery(LinkMan.class);

		// ����1��
// int pageIndex = 11;
// int pageSize = 10;
// List<Customer> list =
// session.createQuery(createQuery).setFirstResult(pageIndex).setMaxResults(pageSize).list();

// ����2
// Ҫ��ѯ�����ʵ�壬�������Ϊ��Ҫ��ѯ�ı�
		Root<LinkMan> root = createQuery.from(LinkMan.class);

		TypedQuery<LinkMan> typedQuery = session.createQuery(createQuery);
		int pageIndex = 11;
		int pageSize = 10;
		// ���÷�ҳ
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
	 * @Description: ������ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ������ѯ
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. ��ȡCriteriaQuery����
		CriteriaQuery<Customer> createQuery = criteriaBuilder.createQuery(Customer.class);
		// ��ѯ�ͻ�����Դ��С����
		Root<Customer> root = createQuery.from(Customer.class);

		/**
		 * = eq
		 * > gt
		 * >= ge
		 * < lt
		 * <= le
		 * <> notEqual(������)
		 * ���� like
		 * where���治ͬ��ȡֵ in
		 * �� and
		 * �� or
		 */
		Predicate predicate1 = criteriaBuilder.equal(root.get("cust_source"), "С���");
// Predicate predicate2 = criteriaBuilder.gt(root.get("cust_id"), 2);
// Predicate predicate3 = criteriaBuilder.ge(root.get("cust_id"), 2);
// Predicate predicate4 = criteriaBuilder.lt(root.get("cust_id"), 2);
// Predicate predicate5 = criteriaBuilder.le(root.get("cust_id"), 2);
// Predicate predicate6 = criteriaBuilder.notEqual(root.get("cust_id"), 2);
// Predicate predicate7 = criteriaBuilder.like(root.get("cust_source"), "С%");
// Path<Object> path = root.get("cust_id");
// Predicate predicate8 = criteriaBuilder.in(path);
// Predicate predicate9 = criteriaBuilder.and(predicate1);
// Predicate predicate10 = criteriaBuilder.or(predicate1);

		// ����ѯ�������õ�where������,�������ö������
		// �������������䣺criteria.where(criteriaBuilder.or(criteriaBuilder.and(predicate1,
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
	 * @Description: ͳ�Ʋ�ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// ͳ�Ʋ�ѯϰ����ʹ��HQL����ϰ��QBC��ʽ
		// ͳ�Ʋ�ѯ����ѯ�����ж���������
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		// 1. ��ȡCriteriaQuery����
		CriteriaQuery<Long> createQuery = criteriaBuilder.createQuery(Long.class);
		Root<LinkMan> root = createQuery.from(LinkMan.class);
		/**
		 * add ����ͨ��������where���������
		 * addOrder ������
		 * setProject ���ۺϺ�����group by having
		 * 
		 */
		createQuery.select(criteriaBuilder.count(root));
		// Ψһ�Ľ��uniqueResult()����
		Long count = session.createQuery(createQuery).uniqueResult();
		System.out.println("����Ϊ��" + count);
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo6
	 * @Description: ����������ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo6() {
		// web��
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("cust_name", "��%"));

		// DAO��
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ���һ����ִ�е�Criteria��getExecutableCriteria()
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
}
