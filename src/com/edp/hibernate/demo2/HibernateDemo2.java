package com.edp.hibernate.demo2;

import java.util.Iterator;

/**
 * 
 * @Title: HibernateDemo2.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version 创建时间 2020年2月9日下午6:42:17
 * @Description 在<set>上的fetch和lazy
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
	 * @Description:默认情况
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);//发送一条查询客户的SQL
		System.out.println(customer.getCust_name());
		// 查看1号客户的每个联系人的信息
		for (LinkMan linkMan : customer.getLinkMans()) {//发送一条根据客户ID查询联系人的SQL
			System.out.println(linkMan.getLkm_name());
		}
		System.out.println(customer);

		transaction.commit();
	}
	
	@Test
	/**
	 * 
	 * @Title: demo2
	 * @Description:<set>属性
	 * lazy="true" fetch="select"
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// 查询1号客户
		Customer customer = session.get(Customer.class, 1l);//发送一条查询客户的SQL
		System.out.println(customer.getCust_name());
		// 查看1号客户的每个联系人的信息
		for (LinkMan linkMan : customer.getLinkMans()) {//发送一条根据客户ID查询联系人的SQL
			System.out.println(linkMan.getLkm_name());
		}
		System.out.println(customer);

		transaction.commit();
	}
}
