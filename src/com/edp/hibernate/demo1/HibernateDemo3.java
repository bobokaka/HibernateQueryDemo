package com.edp.hibernate.demo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.Test;

import com.edp.hibernate.domain.Customer;
import com.edp.hibernate.utils.HibernateUtils;

public class HibernateDemo3 {
	@Test
	/**
	 * 
	 * @Title: demo1
	 * @Description: SQL查询
	 * @param
	 * @return void
	 * @throws
	 *
	 */
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();

//		NativeQuery nativeQuery = session
//				.createSQLQuery("SELECT * FROM cst_customer c INNER JOIN cst_linkman l ON c.cust_id = l.lkm_cust_id;");
//
//		List<Object[]> list2 = nativeQuery.list();
//		for (Object[] objects : list2) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		//封装到对象中
		NativeQuery nativeQuery = session
				.createSQLQuery("SELECT * FROM cst_customer;");
		nativeQuery.addEntity(Customer.class);
		List<Customer> list = nativeQuery.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
}
