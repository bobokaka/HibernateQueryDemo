package com.edp.hibernate.demo2;

import java.util.Iterator;

/**
 * 
 * @Title: HibernateDemo2.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version ����ʱ�� 2020��2��9������6:42:17
 * @Description ��<set>�ϵ�fetch��lazy
 * @version V1.0
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.domain.LinkMan;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo2 {
	@Test
	/**
	 * 
	 * @Title: demo1
	 * @Description:Ĭ�����
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);//����һ����ѯ�ͻ���SQL
		System.out.println(customer.getCust_name());
		// �鿴1�ſͻ���ÿ����ϵ�˵���Ϣ
		for (LinkMan linkMan : customer.getLinkMans()) {//����һ�����ݿͻ�ID��ѯ��ϵ�˵�SQL
			System.out.println(linkMan.getLkm_name());
		}
		System.out.println(customer);

		transaction.commit();
	}
	
	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description:<set>����
	 * lazy="true" fetch="select"
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// ��ѯ1�ſͻ�
		Customer customer = session.get(Customer.class, 1l);//����һ����ѯ�ͻ���SQL
		System.out.println(customer.getCust_name());
		// �鿴1�ſͻ���ÿ����ϵ�˵���Ϣ
		for (LinkMan linkMan : customer.getLinkMans()) {//����һ�����ݿͻ�ID��ѯ��ϵ�˵�SQL
			System.out.println(linkMan.getLkm_name());
		}
		System.out.println(customer);

		transaction.commit();
	}
}
