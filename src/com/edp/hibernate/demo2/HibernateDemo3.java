package com.edp.hibernate.demo2;

import java.util.List;

/**
 * 
 * @Title: HibernateDemo3.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version ����ʱ�� 2020��2��11������5:04:35
 * @Description ����ץȡ
 * @version V1.0
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.domain.LinkMan;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo3 {
	@Test
	/**
	 * 
	 * @Title: demo1
	 * @Description: ��ȡ�ͻ���ʱ������ץȡ��ϵ��
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ���´���ᷢ��5��SQL���
		List<Customer> list = session.createQuery("from Customer").list();
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			for (LinkMan linkMan : customer.getLinkMans()) {
				System.out.println(linkMan.getLkm_name());
			}
		}
		// ʹ������ץȡ
		// ��Customer.hbm.xml������<set name="linkMans" batch-size="4">
		// ֻ����2��sql���
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description: ��ȡ��ϵ�˵�ʱ������ץȡ�ͻ�
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ���´���ᷢ��4��SQL���
		List<LinkMan> list = session.createQuery("from LinkMan").list();
		for (LinkMan linkMan : list) {
			System.out.println(linkMan.getLkm_name());
			System.out.println(linkMan.getCustomer().getCust_name());
		}
		// ʹ������ץȡ
		// ��Customer.hbm.xml������<set name="linkMans" batch-size="4">
		// ֻ����2��sql���
		transaction.commit();
	}
}
