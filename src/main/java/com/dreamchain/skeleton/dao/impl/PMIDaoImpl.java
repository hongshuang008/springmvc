package com.dreamchain.skeleton.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.dreamchain.skeleton.base.BaseDao_fromHibernateDaoSupport;
import com.dreamchain.skeleton.model.PMIData;

@Repository
public class PMIDaoImpl extends BaseDao_fromHibernateDaoSupport<PMIData,Long>{

	
//	@SuppressWarnings("unchecked")
//	public List getHistoryPMIData(final Map param)throws Exception {
//		List list = getHibernateTemplate().execute( new HibernateCallback() {
//            public Object doInHibernate(Session session) throws HibernateException {
//            	StringBuffer sql = new StringBuffer(
//            			"	select "+
//            					"	  a.phone, "+
//            					"	  a.province, "+
//            					"	  a.city, "+
//            					"	  a.district, "+
//            					"	  a.humidity, "+
//            					"	  a.pm1, "+
//            					"	  a.pm10, "+
//            					"	  a.pm2_5, "+
//            					"	  a.temp, "+
//            					"	  a.voc, "+
//            					"	  DATE_FORMAT( "+
//            					"	    a.create_time, "+
//            					"	    '%Y-%m-%d %H:%i:%S' "+
//            					"	  ) createTime  "+
//            					"	from "+
//            					"	  pmidata a  "+
//            					"	where a.phone = :phone  "+
//            					"	  and a.create_time >= :begin "+
//            					"	    and a.create_time <= :end "
//            			);
//		    	
//		    	Query query = session.createSQLQuery(sql.toString());
//		    	
//        		if(param.get("date_begin")!=null)
//        			query.setString("begin", param.get("date_begin").toString()+" 00:00:00");
//        		if(param.get("date_end")!=null)
//        			query.setString("end", param.get("date_end").toString()+" 23:59:59");
//        		if(param.get("phone")!=null)
//            	   	query.setString("phone",  param.get("phone").toString() );
//        		//结果集为map
//        	   	query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
//        	   	return query.list();
//            }
//		});  
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public List getHistoryPMIData(final Map param)throws Exception {
		List list = getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				StringBuffer sql = new StringBuffer(
						"	select "+
								"	  a.phone, "+
								"	  a.province, "+
								"	  a.city, "+
								"	  a.district, "+
								"	  a.humidity, "+
								"	  a.pm1, "+
								"	  a.pm10, "+
								"	  a.pm2_5, "+
								"	  a.temp, "+
								"	  a.voc, "+
								"	  DATE_FORMAT( "+
								"	    a.create_time, "+
								"	    '%Y-%m-%d %H:%i:%S' "+
								"	  ) createTime  "+
								"	from "+
								"	  pmidata a  "+
								"	where 1=1 "
						);
				
				if(param.get("phone")!=null){
		    		sql.append(" and a.phone = :phone  ");
		    	}
				if(param.get("date_begin")!=null){
					sql.append(" and a.create_time >= :begin  ");
				}
				if(param.get("date_end")!=null){
					sql.append(" and a.create_time <= :end  ");
				}
				if(param.get("order")!=null){
					sql.append(" ORDER BY "+param.get("order").toString()+" DESC ");
				}
				if(param.get("limit")!=null){
					sql.append(" LIMIT "+param.get("limit").toString());
				}
				
				
				Query query = session.createSQLQuery(sql.toString());
				
				if(param.get("date_begin")!=null)
					query.setString("begin", param.get("date_begin").toString()+" 00:00:00");
				if(param.get("date_end")!=null)
					query.setString("end", param.get("date_end").toString()+" 23:59:59");
				if(param.get("phone")!=null)
					query.setString("phone",  param.get("phone").toString() );
				//结果集为map
				query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});  
		return list;
	}
}
