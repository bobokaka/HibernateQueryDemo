package com.edp.hibernate.demo1;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @Title: HibernateDemo1.java
 * @Package com.edp.hibernate.demo1
 * @author EdPeng
 * @version ����ʱ�� 2020��2��7������9:01:14
 * @Description HQL��ѯ��ʽ�Ĳ�����
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
	// ��ʼ�����ݿ�
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ����һ���ͻ�
		Customer customer = new Customer();
		customer.setCust_name("����");

		for (int i = 0; i <= 10; i++) {
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("����֮��" + i);
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
	 * @Description: HQL�ļ򵥲�ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer");

		// sql��֧��*�ŵ�д����select * from cst_customer;������HQL�в�֧��*�ŵ�д��
		// Query query = session.createQuery("select * from Customer");��д������
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
	 * @Description: HQL�ı�����ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ȡ������bieMing
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
	 * @Description: HQL�������ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ����Ĳ�ѯ
		// Ĭ�����������,��ʹ��asc
// List<Customer> list = session.createQuery("from Customer order by
// cust_id").list();
// ���ý�������
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
	 * @Description: HQL��������ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// �����Ĳ�ѯ
		// 1.��λ�ð�
// Query query= session.createQuery("from Customer where cust_name = ?0");
// query.setParameter(0, "���");
// List<Customer> list = query.list();

// Query query = session.createQuery("from Customer cust where cust.cust_source
// = ?0 and cust.cust_name like ?1");
// query.setParameter(0, "�����ƹ�");
// query.setParameter(1, "��%");
// List<Customer> list = query.list();

		// 2.�����ư�
		// ������
// Query query = session
// .createQuery("from Customer cust where cust.cust_source = :aaa and
// cust.cust_name like :bbb");
// query.setParameter("aaa", "�����ƹ�");
// query.setParameter("bbb", "��%");
// List<Customer> list = query.list();
// ��������
		Query query = session.createQuery("from Customer where cust_source = :aaa and cust_name like :bbb");
		query.setParameter("aaa", "�����ƹ�");
		query.setParameter("bbb", "��%");
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
	 * @Description: HQL��ͶӰ��ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ͶӰ��ѯ
		// ��������
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
// ��ѯ������ԣ����������װ��������
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
	 * @Description: HQL�ķ�ҳ��ѯ
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
	 * @Description: HQL�ķ���ͳ�Ʋ�ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ͳ�Ʊ����ܵļ�¼��Ŀ,uniqueResult()����Ψһ���
		// �ۺϺ�����ʹ�ã�count(),max(),min(),avg(),sum()��HQL�ж�������
// Object object = session.createQuery("select count(*) from
// Customer").uniqueResult();
// System.out.println(object);
// ����ͳ��
// ���ݿͻ�����Դͳ�ƿͻ��ĸ���
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
	 * @Description: HQL�Ķ���ѯ
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// ��ʾ������,�������ã��Զ�����
		// SELECT * FROM cst_customer JOIN cst_linkman ON cst_customer.cust_id =
		// cst_linkman.lkm_cust_id;
// List<Object[]> list = session.createQuery("from Customer c inner join
// c.linkMans").list();
// for (Object[] objects : list) {
// System.out.println(Arrays.toString(objects));
// }

		// ���������ӣ�����ͨ��������inner join�����һ���ؼ���fetch
		// fetch:֪ͨHibernate����һ����������ݷ�װ��������
		//// һ�����ݣ�����������1����ϵ��
// List<Customer> list = session.createQuery("from Customer c inner join fetch
// c.linkMans").list();
// �������ݣ�����������10����ϵ��
		List<Customer> list = session.createQuery("select distinct c from Customer c inner join fetch c.linkMans")
				.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}

}
