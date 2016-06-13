package com.dreamchain.skeleton.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dreamchain.skeleton.base.baseInterface.DaoInterface;

/**
 * 抽象的Hibernate DataAccessObject
 * 
 * @param <ENTITY>
 *            实体的类型
 * @param <ID>
 *            主键的类型
 * 这里必须的   abstract 啊，不然会报错ParameterizedType转换异常  
 */
@Repository
public abstract class BaseDao_fromHibernateDaoSupport<ENTITY, ID extends Serializable>
		extends HibernateDaoSupport implements DaoInterface<ENTITY, ID>{
	
	
	// 日志输出类
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)   
    {   
        super.setSessionFactory(sessionFactory);   
    }  
	/**
	 * 定义T的实际类型
	 */
	protected Class<ENTITY> entityClass;

	/**
	 * 构造器，通过反射获取T的实际类型，以供其它方法使用
	 */
	@SuppressWarnings("unchecked")
	public BaseDao_fromHibernateDaoSupport() {
		entityClass = (Class<ENTITY>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(ENTITY entity) {
		log.debug("执行删除");
		getHibernateTemplate().delete(entity);
	}

	public void delete(ID id){
		log.debug("执行根据ID删除");
		getHibernateTemplate().delete(findById(id));
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findByExample(ENTITY entity) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity));
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findByExample(ENTITY entity, int firstResult,
			int maxResults, MatchMode matchMode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity).enableLike(matchMode));
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findByProperty(String propertyName, Object value) {
		String queryString = "from " + entityClass.getName()
				+ " as model where model." + propertyName + "= ?";
		return (List<ENTITY>) getHibernateTemplate().find(queryString, value);
	}

	/**
	 * {@inheritDoc}
	 */
	// @Override
	public List<ENTITY> findByProperties(Map<String, Object> properties) {
		return findByProperties(properties, -1, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findByProperties(Map<String, Object> properties,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (Entry<String, Object> entry : properties.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findbyPageCriteriaOrder(Map<String, Object> properties,
			int firstResult, int maxResults, String columnname, String orderType) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (Entry<String, Object> entry : properties.entrySet()) {
			if ("com.doer.baseInfoManage.bean.Dictionary".equals(entityClass
					.getName()) && "CDictionaryType".equals(entry.getKey())) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			} else {
				criteria.add(Restrictions.like(entry.getKey(),
						"%" + entry.getValue() + "%"));
			}

		}
		if ("".equals(columnname) || columnname == null) {

		} else {
			if ("asc".equals(orderType)) {
				criteria.addOrder(Order.asc(columnname));
			} else {
				criteria.addOrder(Order.desc(columnname));
			}
		}

		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	// @Override
	public void saveOrUpdate(ENTITY entity) {
		log.debug("执行保存或更新");
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findAll() {
		log.debug("执行查找");
		String queryString = "from " + entityClass.getName();
		return (List<ENTITY>) getHibernateTemplate().find(queryString);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public List<ENTITY> findAll(int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public ENTITY findById(ID id, LockMode lockMode) {
		return (ENTITY) getHibernateTemplate().load(entityClass, id, lockMode);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	// @Override
	public ENTITY findById(ID id) {
		log.debug("执行根据ID查找");
		return (ENTITY) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ENTITY entity) {
		log.debug("执行更新");
		getHibernateTemplate().update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public ENTITY merge(ENTITY entity) {
		return (ENTITY) getHibernateTemplate().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ID save(ENTITY entity) {
		log.debug("执行保存");
		return (ID) getHibernateTemplate().save(entity);
	}

	@SuppressWarnings("unchecked")
	public void batchSaveENTITY(final List<ENTITY> entityList) {
         	getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {               
            	for (int i = 0; i < entityList.size(); i++) {
                    session.save(entityList.get(i));
                    if (i % 20 == 0) {
                        session.flush();
                        session.clear();
                    }
                }               
                return null;
            }
        });
    }
	public void batchSaveENTITY2(final List<ENTITY> entityList) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();  
		for (int i = 0; i < entityList.size(); i++) {
            session.save(entityList.get(i));
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }    
		tx.commit();  
		session.close(); 
	}
	/**
	 * {@inheritDoc}
	 */
	public void refresh(ENTITY entity) {
		getHibernateTemplate().refresh(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer count() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.setProjection(Projections.rowCount());
		Long a = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);
		return a.intValue();
	}
	public Long count2() {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.setProjection(Projections.rowCount());
		return (Long) getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByExampleLikeAnyWhere(ENTITY entity) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass).add(
				Example.create(entity).enableLike(MatchMode.ANYWHERE));
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer countByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq(propertyName, value));
		return (Integer) getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	/**
	 * 实用两级类型 分页模糊查询
	 * 
	 * @param properties
	 * @param firstResult
	 * @param maxResults
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<ENTITY> findbyPageCriteria(Map<String, Object> properties,
			int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		for (String key : properties.keySet()) {
			if (properties.get(key) != null && properties.get(key) != "") {
				/**
				 * 如果属性的值是String类型
				 */
				if (String.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.ilike(key,
							"%" + properties.get(key) + "%", MatchMode.ANYWHERE));
				} else if (Long.class.equals(properties.get(key).getClass())
						|| Integer.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.eq(key, properties.get(key)));
				} else if (Date.class.equals(properties.get(key).getClass())) {
					/**
					 * 时间模糊查询具体实现
					 */
				} else { // 如果不是
					String[] longClass = key.split("\\.");
					Class<?> class1 = properties.get(key).getClass();
					DetachedCriteria criteria2 = criteria
							.createCriteria(longClass[0]);
					Method method;
					try {
						/**
						 * 获得属性的get方法
						 */
						String str = longClass[1].substring(0, 1);
						str = str.toUpperCase()
								+ longClass[1].substring(1,
										longClass[1].length());
						method = class1.getMethod("get" + str);
						/**
						 * 调用方法
						 */
						Object returnValue = method.invoke(properties.get(key));
						if (returnValue != null && returnValue != "") {
							if (Long.class.equals(returnValue.getClass())) {
								criteria2.add(Restrictions.eq(longClass[1],
										returnValue));
							} else {
								criteria2.add(Restrictions.ilike(longClass[1],
										"%" + returnValue + "%",
										MatchMode.ANYWHERE));
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return (List<ENTITY>) this.getHibernateTemplate().findByCriteria(criteria,
				(firstResult - 1) * maxResults, maxResults);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer countByCriteria(Map<String, Object> properties) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		for (String key : properties.keySet()) {
			if (properties.get(key) != null && properties.get(key) != "") {
				/**
				 * 如果属性的值是String类型
				 */
				if (String.class.equals(properties.get(key).getClass())) {
					System.out.println(entityClass.getName());
					if ("com.doer.baseInfoManage.bean.Dictionary"
							.equals(entityClass.getName())
							&& "CDictionaryType".equals(key)) {
						criteria.add(Restrictions.eq(key, properties.get(key)));
					} else {
						criteria.add(Restrictions.ilike(key,
								"%" + properties.get(key) + "%"));
					}

				} else if (Long.class.equals(properties.get(key).getClass())
						|| Integer.class.equals(properties.get(key).getClass())) {
					criteria.add(Restrictions.eq(key, properties.get(key)));
				} else if (Date.class.equals(properties.get(key).getClass())) {
					/**
					 * 时间模糊查询具体实现
					 */
				} else { // 如果不是
					String[] longClass = key.split("\\.");
					Class<?> class1 = properties.get(key).getClass();
					DetachedCriteria criteria2 = criteria
							.createCriteria(longClass[0]);
					Method method;
					try {
						/**
						 * 获得属性的get方法
						 */
						String str = longClass[1].substring(0, 1);
						str = str.toUpperCase()
								+ longClass[1].substring(1,
										longClass[1].length());
						method = class1.getMethod("get" + str);
						/**
						 * 调用方法
						 */
						Object returnValue = method.invoke(properties.get(key));
						if (returnValue != null && returnValue != "") {
							if (Long.class.equals(returnValue.getClass())) {
								criteria2.add(Restrictions.eq(longClass[1],
										returnValue));
							} else {
								criteria2.add(Restrictions.ilike(longClass[1],
										"%" + returnValue + "%"));
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		criteria.setProjection((Projections.rowCount()));

		return (Integer) this.getHibernateTemplate().findByCriteria(criteria)
				.get(0);
	}

	/**
	 * 通过给定的一个对象和一个需要排序的列，查找与其匹配的对象并按照指定列进行排序。
	 * 
	 * @param entity
	 *            实体
	 * @return 实体集合
	 */
	public List<ENTITY> findByExampleByOrder(ENTITY entity, String property) {

		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass)
				.add(Example.create(entity)).addOrder(Order.asc(property));
		return (List<ENTITY>) getHibernateTemplate().findByCriteria(criteria);
	}

	
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
		public void excuteHql(final String hql) {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query query = session.createQuery(hql);
					return query.executeUpdate();
				}
			});
		}
		
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void excuteSql(final String sql) {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query query = session.createSQLQuery(sql);
					return query.executeUpdate();
				}
			});
		}
	    
	    @SuppressWarnings("unchecked")
		public List findBySql(final String sql){
	    	List list = getHibernateTemplate().execute( new HibernateCallback() {
	            public Object doInHibernate(Session session)
	            throws HibernateException {
	            	   	Query query = session.createSQLQuery(sql);
	            	   	//结果集为map
	            	   	query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	                    return query.list();
	             }});
			return list;
	    }
	    
	    @SuppressWarnings("unchecked")
		public List findByHql(final String hql){
	    	List list = getHibernateTemplate().execute( new HibernateCallback() {
	            public Object doInHibernate(Session session)
	            throws HibernateException {
	            	   	Query query = session.createQuery(hql);
	                    return query.list();
	             }});
			return list;
	    }
}
