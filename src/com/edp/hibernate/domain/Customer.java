package com.edp.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @Title: Customer.java
 * @Package com.edp.hibernate.domain
 * @author EdPeng
 * @version 创建时间 2020年2月4日上午1:03:39
 * @Description 客户的实体
 * @version V1.0
 *          CREATE TABLE `cst_customer` (
 *          `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
 *          `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
 *          `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
 *          `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
 *          `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
 *          `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
 *          `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
 *          PRIMARY KEY (`cust_id`)
 *          ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 *
 */
public class Customer {
	
	
	
	public Customer() {
		super();
	}
	
	public Customer(String cust_name, String cust_source) {
		super();
		this.cust_name = cust_name;
		this.cust_source = cust_source;
	}

	private Long cust_id;// 客户id
	private String cust_name;// 客户的名称
	private String cust_source;// 客户的来源
	private String cust_industry;// 客户所属行业
	private String cust_level;// 客户的级别
	private String cust_phone;// 客户的固定电话
	private String cust_mobile;// 客户的移动电话
	// 通过ORM方式表示：一个客户对应多个联系人
	// 放置多的一方的集合。Hibernate默认使用的是Set集合
	// 因为Hibernate中需要对list的数据进行有序排列，就会在数据库中多建一列用来排序，表中就会多出来一列
	// 因此一般情况下使用Set集合
	// 这里采用双向关联，即客户查联系人能查，联系人查客户也能查
	private Set<LinkMan> linkMans = new HashSet<LinkMan>();

	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}


	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_source() {
		return cust_source;
	}

	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}

	public String getCust_industry() {
		return cust_industry;
	}

	public void setCust_industry(String cust_industry) {
		this.cust_industry = cust_industry;
	}

	public String getCust_level() {
		return cust_level;
	}

	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_source=" + cust_source
				+ ", cust_industry=" + cust_industry + ", cust_level=" + cust_level + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + "]";
	}

}
