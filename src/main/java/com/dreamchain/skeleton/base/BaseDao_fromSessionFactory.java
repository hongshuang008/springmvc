package com.dreamchain.skeleton.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dreamchain.skeleton.base.baseInterface.DaoInterface;

@Repository
public abstract class BaseDao_fromSessionFactory<ENTITY, ID extends java.io.Serializable>implements DaoInterface<ENTITY, ID> {

	// 日志输出类

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	// 泛型反射类

	private Class<ENTITY> entityClass;

	// 通过反射获取子类确定的泛型类

	@SuppressWarnings("unchecked")
	public BaseDao_fromSessionFactory() {

		entityClass = (Class<ENTITY>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * 
	 * 注入sessionFactory
	 */

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {

		// 事务必须是开启的(Required)，否则获取不到

		return sessionFactory.getCurrentSession();

	}

	/*
	 * 
	 * 保存PO
	 */

	@SuppressWarnings("unchecked")
	public ID save(ENTITY entity) {

		return (ID) getSession().save(entity);

	}

	/*
	 * 
	 * 保存或更新PO
	 */

	public void saveOrUpdate(ENTITY entity) {

		getSession().saveOrUpdate(entity);

	}

	/*
	 * 
	 * 更新PO
	 */

	public void update(ENTITY entity) {

		getSession().update(entity);

	}

	/*
	 * 
	 * 合并PO
	 */

	@SuppressWarnings("unchecked")
	public ENTITY merge(ENTITY entity) {

		return (ENTITY)getSession().merge(entity);

	}


	/*
	 * 
	 * 根据id删除PO
	 */

	public void delete(ID id) {

		getSession().delete(this.findById(id));

	}

	/*
	 * 
	 * 删除PO
	 */

	public void delete(ENTITY entity) {
		getSession().delete(entity);

	}

	/*
	 * 
	 * 根据id判断PO是否存在
	 */

	public boolean exists(ID id) {

		return findById(id) != null;

	}

	/*
	 * 
	 * 根据id加载PO
	 */

	@SuppressWarnings("unchecked")
	public ENTITY load(ID id) {

		return (ENTITY) getSession().load(this.entityClass, id);

	}

	/*
	 * 
	 * 根据id获取PO
	 */

	@SuppressWarnings("unchecked")
	public ENTITY findById(ID id) {

		return (ENTITY) getSession().get(this.entityClass, id);

	}
	/*
	 * 
	 * 获取PO总数(默认为entityClass)
	 */

	public int countAll() {

		Criteria criteria = createCriteria();

		return Integer.valueOf(criteria.setProjection(Projections.rowCount())

		.uniqueResult().toString());

	}

	/*
	 * 
	 * 根据Criteria查询条件，获取PO总数
	 */

	public int countAll(Criteria criteria) {

		return Integer.valueOf(criteria.setProjection(Projections.rowCount())

		.uniqueResult().toString());

	}

	/*
	 * 
	 * 删除所有
	 */

	public void deleteAll(Collection<?> entities) {

		if (entities == null)

			return;

		for (Object entity : entities) {

			getSession().delete(entity);

		}

	}

	/*
	 * 
	 * 获取全部对象
	 */

	@SuppressWarnings("unchecked")
	public List<ENTITY> findAll() {
		return createCriteria().list();

	}
	/*
	 * 
	 * 获取对象列表根据Criteria
	 */

	@SuppressWarnings("unchecked")
	public List<ENTITY> list(Criteria criteria) {

		return criteria.list();

	}

	/*
	 * 
	 * 离线查询
	 */

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> list(DetachedCriteria criteria) {

		return (List<T>) list(criteria.getExecutableCriteria(getSession()));

	}

	/*
	 * 
	 * 获取全部对象，支持排序
	 * 
	 * 
	 * 
	 * @param orderBy
	 * 
	 * 
	 * 
	 * @param isAsc
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<ENTITY> list(String orderBy, boolean isAsc) {

		Criteria criteria = createCriteria();

		if (isAsc) {

			criteria.addOrder(Order.asc(orderBy));

		} else {

			criteria.addOrder(Order.desc(orderBy));

		}

		return criteria.list();

	}

	/*
	 * 
	 * 按属性查找对象列表，匹配方式为相等
	 * 
	 * 
	 * 
	 * @param propertyName
	 * 
	 * 
	 * 
	 * @param value
	 * 
	 * 
	 * 
	 * @return
	 */

	public List<ENTITY> list(String propertyName, Object value) {

		Criterion criterion = Restrictions

		.like(propertyName, "%" + value + "%");

		return list(criterion);

	}

	/*
	 * 
	 * 根据查询条件获取数据列表
	 */

	@SuppressWarnings("unchecked")
	private List<ENTITY> list(Criterion criterion) {

		Criteria criteria = createCriteria();

		criteria.add(criterion);

		return criteria.list();

	}

	/*
	 * 
	 * 按Criteria查询对象列表
	 * 
	 * 
	 * 
	 * @param criterions数量可变的Criterion
	 * 
	 * 
	 * 
	 * @param criterions
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<ENTITY> list(Criterion... criterions) {

		return createCriteria(criterions).list();

	}

	/*
	 * 
	 * 按属性查找唯一对象，匹配方式为相等
	 * 
	 * 
	 * 
	 * @param propertyName
	 * 
	 * 
	 * 
	 * @param value
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public ENTITY uniqueResult(String propertyName, Object value) {

		Criterion criterion = Restrictions.eq(propertyName, value);

		return (ENTITY) createCriteria(criterion).uniqueResult();

	}

	/*
	 * 
	 * 按Criteria查询唯一对象
	 * 
	 * 
	 * 
	 * @param criterions数量可变的Criterion
	 * 
	 * 
	 * 
	 * @param criterions
	 * 
	 * 
	 * 
	 * @return
	 */

	public ENTITY uniqueResult(Criterion... criterions) {

		Criteria criteria = createCriteria(criterions);

		return uniqueResult(criteria);

	}

	/*
	 * 
	 * 按Criteria查询唯一对象
	 * 
	 * 
	 * 
	 * @param criterions
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public ENTITY uniqueResult(Criteria criteria) {

		return (ENTITY) criteria.uniqueResult();

	}

	/*
	 * 
	 * 为Criteria添加distinct transformer
	 * 
	 * 
	 * 
	 * @param criteria
	 * 
	 * 
	 * 
	 * @return
	 */

	// 认为没用

	public Criteria distinct(Criteria criteria) {

		// 将结果集进行一次封装，封装成DISTINCT_ROOT_ENTITY对象，方便service层代码使用

		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return criteria;

	}

	/*
	 * 
	 * 强制清空session
	 */

	public void flush() {

		getSession().flush();

	}

	/*
	 * 
	 * 清空session
	 */

	public void clear() {

		getSession().clear();

	}

	/*
	 * 
	 * 创建Criteria实例
	 */

	public Criteria createCriteria() {

		return getSession().createCriteria(entityClass);

	}

	/*
	 * 
	 * 根据Criterion条件创建Criteria
	 * 
	 * 
	 * 
	 * @param criterions数量可变的Criterion
	 */

	public Criteria createCriteria(Criterion... criterions) {

		Criteria criteria = createCriteria();

		for (Criterion c : criterions) {

			criteria.add(c);

		}

		return criteria;

	}

	/*
	 * 
	 * 分页查询Criteria
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @return
	 */

	public List<ENTITY> findPage(Criteria criteria, int pageNo, int pageSize) {

		// 设置起始结果数

		criteria.setFirstResult((pageNo - 1) * pageSize);

		// 返回的最大结果集

		criteria.setMaxResults(pageSize);

		return list(criteria);

	}

	

	

	

	/*
	 * 
	 * 分页查询Criteria
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @return
	 */

//	public Page<T> pagedQuery(Criteria criteria, int pageNo, int pageSize) {
//
//		Assert.isTrue(pageNo >= 1, "pageNO should start from 1");
//
//		// 返回查询结果集
//
//		List<T> list = findPage(criteria, pageNo, pageSize);
//
//		/*
//		 * 
//		 * 
//		 * 注：因为finaPage方法改变了查询条件导致countALL方法查询为空， 所以必须重新设置setFirstResult为0
//		 */
//
//		criteria.setFirstResult(0);
//
//		// count查询
//
//		// 获得查询总数
//
//		long totalCount = countAll(criteria);
//
//		if (totalCount < 1) {
//
//			return new Page<T>();
//
//		}
//
//		// 实际查询返回分页对象
//
//		int startIndex = Page.getStartOfPage(pageNo, pageSize);
//
//		return new Page<T>(startIndex, totalCount, pageSize, list);
//
//	}
//
//	/*
//	 * 
//	 * 分页查询Criteria
//	 * 
//	 * 
//	 * 
//	 * @param
//	 * 
//	 * 
//	 * 
//	 * @return
//	 */
//
//	public Page<T> pagedQuery(ConditionQuery conditionQuery, OrderBy orderBy,
//
//	int pageNo, int pageSize) {
//
//		Assert.isTrue(pageNo >= 1, "pageNO should start from 1");
//
//		Criteria criteria = createCriteria();
//
//		// 构造查询条件和排序
//
//		conditionQuery.build(criteria);
//
//		orderBy.build(criteria);
//
//		// count查询
//
//		// 获得查询总数
//
//		long totalCount = countAll(criteria);
//
//		if (totalCount < 1) {
//
//			return new Page<T>();
//
//		}
//
//		// 实际查询返回分页对象
//
//		int startIndex = Page.getStartOfPage(pageNo, pageSize);
//
//		// 返回查询结果集
//
//		List<T> list = findPage(criteria, pageSize, pageNo);
//
//		return new Page<T>(startIndex, totalCount, pageSize, list);
//
//	}

}
