package com.edp.hibernate.utils;

import org.hibernate.Session;

/**
 * 
 * @author EdPeng
 * @version 创建时间 2020年1月23日上午12:39:24
 * 类说明 Hibernate工具类
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	public static final Configuration cfg;
	public static final SessionFactory sf;

	static {
		cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}

	public static Session openSession() {
		return sf.openSession();
	}

	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}
}
