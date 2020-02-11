package com.edp.hibernate.demo2;

/**
 * 
 * @Title: HibernateDemo1.java
 * @Package com.edp.hibernate.demo2
 * @author EdPeng
 * @version 创建时间 2020年2月7日下午9:01:14
 * @Description 类级别的延迟加载
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
	 * @Description: 类级别的延迟加载
	 * 在<class>的标签上配置lazy =true
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.load(Customer.class, 1l);
		//该语句使延迟加载失效
//		Hibernate.initialize(customer);
		System.out.println(customer);
		
		transaction.commit();
	}
}
