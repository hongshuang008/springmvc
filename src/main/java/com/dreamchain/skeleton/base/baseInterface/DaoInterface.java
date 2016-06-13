package com.dreamchain.skeleton.base.baseInterface;

import java.io.Serializable;
import java.util.List;

public interface DaoInterface<ENTITY, ID extends Serializable> {
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(ENTITY entity);
	public void delete(ID id);
	public List<ENTITY> findAll();
//	public List<K> findAll(int firstResult, int maxResults);
	public ENTITY findById(ID id);
	public void saveOrUpdate(ENTITY entity);
	public void update(ENTITY entity);
	public ENTITY merge(ENTITY entity);
	public ID save(ENTITY entity);
	
	
//	public List<K> findByExample(K entity);
//	public List<K> findByExample(K entity, int firstResult,
//			int maxResults, MatchMode matchMode);
//	
//	public List<K> findByProperty(String propertyName, Object value);
//	public List<K> findByProperties(Map<String, Object> properties) ;
//	public List<K> findByProperties(Map<String, Object> properties,
//			int firstResult, int maxResults);
	
}
