package com.edp.hibernate.demo2;

import java.util.List;

/**
 * 
 * @Title: HibernateDemo3.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version 创建时间 2020年2月11日下午5:04:35
 * @Description 批量抓取
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
	 * @Description: 获取客户的时候，批量抓取联系人
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 以下代码会发送5条SQL语句
		List<Customer> list = session.createQuery("from Customer").list();
		for (Customer customer : list) {
			System.out.println(customer.getCust_name());
			for (LinkMan linkMan : customer.getLinkMans()) {
				System.out.println(linkMan.getLkm_name());
			}
		}
		// 使用批量抓取
		// 在Customer.hbm.xml中设置<set name="linkMans" batch-size="4">
		// 只发送2条sql语句
		transaction.commit();
	}

	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description: 获取联系人的时候，批量抓取客户
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 以下代码会发送4条SQL语句
		List<LinkMan> list = session.createQuery("from LinkMan").list();
		for (LinkMan linkMan : list) {
			System.out.println(linkMan.getLkm_name());
			System.out.println(linkMan.getCustomer().getCust_name());
		}
		// 使用批量抓取
		// 在Customer.hbm.xml中设置<set name="linkMans" batch-size="4">
		// 只发送2条sql语句
		transaction.commit();
	}
}
