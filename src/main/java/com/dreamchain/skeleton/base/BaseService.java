package com.dreamchain.skeleton.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreamchain.skeleton.base.baseInterface.DaoInterface;
import com.dreamchain.skeleton.model.PMIData;

@Service
public abstract class BaseService<ENTITY, ID extends java.io.Serializable> {
	//定义一个全局的记录器，通过LoggerFactory获取
    protected  Logger log = LoggerFactory.getLogger(this.getClass());
    
	protected DaoInterface<ENTITY, ID> baseDAO;
	@Autowired
	public BaseService(DaoInterface<ENTITY, ID> dao){
		baseDAO = dao;
	}
	
	@Transactional
	public void delete(ENTITY entity) {
		baseDAO.delete(entity);
	}
	@Transactional
	public void delete(ID id) {
		baseDAO.delete(id);
	}
	@Transactional
	public List<ENTITY> findAll() {
		return baseDAO.findAll();
	}
	@Transactional
	public ENTITY findById(ID id) {
		return baseDAO.findById(id);
	}
	@Transactional
	public void saveOrUpdate(ENTITY entity) {
		baseDAO.saveOrUpdate(entity);
	}
	@Transactional
	public void update(ENTITY entity) {
		baseDAO.update(entity);
	}
	@Transactional
	public ENTITY merge(ENTITY entity) {
		return baseDAO.merge(entity);
	}
	@Transactional
	public ID save(ENTITY entity) {
		return baseDAO.save(entity);
	}
}
