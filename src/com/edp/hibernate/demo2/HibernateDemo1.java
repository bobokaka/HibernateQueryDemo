package com.edp.hibernate.demo2;

/**
 * 
 * @Title: HibernateDemo1.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version ����ʱ�� 2020��2��7������9:01:14
 * @Description �༶����ӳټ���
 * @version V1.0
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo1 {
	@Test
	/**
	 * 
	 * @Title: demo1
	 * @Description: �༶����ӳټ���
	 * ��<class>�ı�ǩ������lazy =true
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.load(Customer.class, 1l);
		//�����ʹ�ӳټ���ʧЧ
//		Hibernate.initialize(customer);
		System.out.println(customer);
		
		transaction.commit();
	}
}
