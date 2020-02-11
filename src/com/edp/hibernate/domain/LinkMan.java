package com.edp.hibernate.domain;

/**
 * 
 * @Title: LinkMan.java
 * @Package com.edp.hibernate.domain
 * @author EdPeng
 * @version 创建时间 2020年2月4日上午1:04:07
 * @Description 联系人的实体
 * @version V1.0
 *          CREATE TABLE `cst_linkman` (
 *          `lkm_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
 *          `lkm_name` varchar(16) DEFAULT NULL COMMENT '联系人姓名',
 *          `lkm_cust_id` bigint(32) DEFAULT NULL COMMENT '客户id',
 *          `lkm_gender` char(1) DEFAULT NULL COMMENT '联系人性别',
 *          `lkm_phone` varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
 *          `lkm_mobile` varchar(16) DEFAULT NULL COMMENT '联系人手机',
 *          `lkm_email` varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
 *          `lkm_qq` varchar(16) DEFAULT NULL COMMENT '联系人qq',
 *          `lkm_position` varchar(16) DEFAULT NULL COMMENT '联系人职位',
 *          `lkm_memo` varchar(512) DEFAULT NULL COMMENT '联系人备注',
 *          PRIMARY KEY (`lkm_id`),
 *          KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
 *          CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`)
 *          REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE
 *          NO ACTION
 *          ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 * 
 */

public class LinkMan {
	private Long lkm_id;
	private String lkm_name;
// private String lkm_cust_id;不要写此外键的名称
	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;
	private String lkm_email;
	private String lkm_qq;
	private String lkm_position;
	private String lkm_memo;

	// 通过ORM方式表示：一个联系人只能属于某一个客户
	private Customer customer;

	// 见到外键不要写相应外键的名称，把外键改成一的一方的对象
	// 并写相应对象的get和set方法
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public String getLkm_email() {
		return lkm_email;
	}

	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}

	public String getLkm_qq() {
		return lkm_qq;
	}

	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}

	public String getLkm_position() {
		return lkm_position;
	}

	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}

	public String getLkm_memo() {
		return lkm_memo;
	}

	public void setLkm_memo(String lkm_memo) {
		this.lkm_memo = lkm_memo;
	}

	@Override
	public String toString() {
		return "LinkMan [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + ", lkm_gender=" + lkm_gender + ", lkm_phone="
				+ lkm_phone + ", lkm_mobile=" + lkm_mobile + ", lkm_email=" + lkm_email + ", lkm_qq=" + lkm_qq
				+ ", lkm_position=" + lkm_position + ", lkm_memo=" + lkm_memo + "]";
	}

}
