package com.edp.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @Title: Customer.java
 * @Package com.edp.hibernate.domain
 * @author EdPeng
 * @version ����ʱ�� 2020��2��4������1:03:39
 * @Description �ͻ���ʵ��
 * @version V1.0
 *          CREATE TABLE `cst_customer` (
 *          `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '�ͻ����(����)',
 *          `cust_name` varchar(32) NOT NULL COMMENT '�ͻ�����(��˾����)',
 *          `cust_source` varchar(32) DEFAULT NULL COMMENT '�ͻ���Ϣ��Դ',
 *          `cust_industry` varchar(32) DEFAULT NULL COMMENT '�ͻ�������ҵ',
 *          `cust_level` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
 *          `cust_phone` varchar(64) DEFAULT NULL COMMENT '�̶��绰',
 *          `cust_mobile` varchar(16) DEFAULT NULL COMMENT '�ƶ��绰',
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

	private Long cust_id;// �ͻ�id
	private String cust_name;// �ͻ�������
	private String cust_source;// �ͻ�����Դ
	private String cust_industry;// �ͻ�������ҵ
	private String cust_level;// �ͻ��ļ���
	private String cust_phone;// �ͻ��Ĺ̶��绰
	private String cust_mobile;// �ͻ����ƶ��绰
	// ͨ��ORM��ʽ��ʾ��һ���ͻ���Ӧ�����ϵ��
	// ���ö��һ���ļ��ϡ�HibernateĬ��ʹ�õ���Set����
	// ��ΪHibernate����Ҫ��list�����ݽ����������У��ͻ������ݿ��жཨһ���������򣬱��оͻ�����һ��
	// ���һ�������ʹ��Set����
	// �������˫����������ͻ�����ϵ���ܲ飬��ϵ�˲�ͻ�Ҳ�ܲ�
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