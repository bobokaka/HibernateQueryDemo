# HibernateQueryDemo
一个Hibernate的查询的demo示范案例

# Hibernate版本
hibernate-core-5.4.10

# 知识点说明：
[【Java中级】11.0 SSH之Hibernate框架（八）——Hibernate的查询方式之HQL检索](https://www.jianshu.com/p/4cf820738e3a)<br/>
[【Java中级】12.0 SSH之Hibernate框架（九）——Hibernate的查询方式之QBC检索](https://www.jianshu.com/p/eda536fecd1b)<br/>
[【Java中级】13.0 SSH之Hibernate框架（十）——HQL的多表查询、SQL检索和抓取策略](https://www.jianshu.com/p/f44eb7d0a2b7)<br/>

# src代码结构的部分说明
不同的查询方式：

>* com.edp.hibernate.demo1包下
>>* HibernateDemo1.java
>>>* HQL查询方式的测试类
>>* HibernateDemo2.java
>>>* QBC查询
>>* HibernateDemo3.java
>>>* SQL查询

抓取策略优化：
>* com.edp.hibernate.demo1包下
>>* HibernateDemo1.java
>>>* 类级别的延迟加载
>>* HibernateDemo2.java
>>>* 在<set>上的fetch和lazy
>>* HibernateDemo3.java
>>>* 批量抓取


>* com.edp.hibernate.domain包下
>>* Customer.java
>>>* 客户的实体（一的一方）
>>* LinkMan.java
>>>* 联系人的实体（多的一方）
>>* Customer.hbm.xml
>>>* 客户的实体的映射
>>* LinkMan.hbm.xml
>>>* 联系人的实体的映射
