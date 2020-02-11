# HibernateQueryDemo
一个Hibernate的查询的demo示范案例

# Hibernate版本
hibernate-core-5.4.10

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
