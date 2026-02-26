package com.synergizglobal.wrpmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.wrpmis.common.CommonMethods;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.Idao.WorkDetailsDao;
import com.synergizglobal.wrpmis.model.WorkFeatures;

@Repository
public class WorkDetailsDaoImpl implements WorkDetailsDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<WorkFeatures> getWorkDetailsList(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select STRING_AGG(title_fk) as title_fk,work_id_fk,work_id,work_short_name "
					+ "from work_details wd "
					+ "LEFT JOIN work ON wd.work_id_fk = work_id "		
					+ "WHERE wd.work_id_fk IS NOT NULL ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "AND wd.work_id_fk = ? ";
			}
			qry = qry + "GROUP BY work_id_fk ORDER BY work_id_fk ASC ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<WorkFeatures> getWorkDetails(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select id,title_fk,work_id_fk,dashboard_url,soft_delete_status_fk,title_fk as title,work_id,work_short_name "
					+ "from work_details "
					+ "LEFT JOIN work ON work_id_fk = work_id "		
					+ "WHERE title_fk IS NOT NULL ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj)) {
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + "AND work_id_fk = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
					qry = qry + "AND soft_delete_status_fk = ? ";
					arrSize++;
				}
			}
			qry = qry + "ORDER BY work_id_fk ASC ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj)) {
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
					pValues[i++] = obj.getSoft_delete_status_fk();
				}
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addWorkDetails(WorkFeatures obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			String insertQry = "INSERT INTO work_details"
					+ "(title_fk,work_id_fk,dashboard_url,soft_delete_status_fk) VALUES (:title_fk,:work_id_fk,:dashboard_url,:soft_delete_status_fk)";
			
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0) {
				obj.setTitle_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getTitle_fks()));
				if(arraySize < obj.getTitle_fks().length) {
					arraySize = obj.getTitle_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getDashboard_urls()) && obj.getDashboard_urls().length > 0) {
				obj.setDashboard_urls(CommonMethods.replaceEmptyByNullInSringArray(obj.getDashboard_urls()));
				if(arraySize < obj.getDashboard_urls().length) {
					arraySize = obj.getDashboard_urls().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0) {
				obj.setStatuses(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatuses()));
				if(arraySize < obj.getStatuses().length) {
					arraySize = obj.getStatuses().length;
				}
			}
			
			int size = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0 && !StringUtils.isEmpty(obj.getTitle_fks()[i])) {
					size++;
				}
			}
			SqlParameterSource[] source = new SqlParameterSource[size];
			int p = 0;
			
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0 && !StringUtils.isEmpty(obj.getTitle_fks()[i])) {
					WorkFeatures insertObj = new WorkFeatures();
					insertObj.setTitle_fk((!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0)?obj.getTitle_fks()[i]:null);
					insertObj.setWork_id_fk(obj.getWork_id_fk());
					insertObj.setDashboard_url((!StringUtils.isEmpty(obj.getDashboard_urls()) && obj.getDashboard_urls().length > 0)?obj.getDashboard_urls()[i]:null);
					insertObj.setSoft_delete_status_fk((!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0 && !StringUtils.isEmpty(obj.getStatuses()[i]))?obj.getStatuses()[i]:CommonConstants.INACTIVE);
					source[p++] = new BeanPropertySqlParameterSource(insertObj);
				}
		    }
			int[] insert_count = namedParamJdbcTemplate.batchUpdate(insertQry, source);		
			if(insert_count.length > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateWorkDetails(WorkFeatures obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			String deleteQry = "DELETE FROM work_details WHERE work_id_fk = :work_id_fk";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(deleteQry, paramSource);
			
			String insertQry = "INSERT INTO work_details"
					+ "(title_fk,work_id_fk,dashboard_url,soft_delete_status_fk) VALUES (:title_fk,:work_id_fk,:dashboard_url,:soft_delete_status_fk)";
			
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0) {
				obj.setTitle_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getTitle_fks()));
				if(arraySize < obj.getTitle_fks().length) {
					arraySize = obj.getTitle_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getDashboard_urls()) && obj.getDashboard_urls().length > 0) {
				obj.setDashboard_urls(CommonMethods.replaceEmptyByNullInSringArray(obj.getDashboard_urls()));
				if(arraySize < obj.getDashboard_urls().length) {
					arraySize = obj.getDashboard_urls().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0) {
				obj.setStatuses(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatuses()));
				if(arraySize < obj.getStatuses().length) {
					arraySize = obj.getStatuses().length;
				}
			}
			int size = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0 && !StringUtils.isEmpty(obj.getTitle_fks()[i])) {
					size++;
				}
			}
			SqlParameterSource[] source = new SqlParameterSource[size];
			int p = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0 && !StringUtils.isEmpty(obj.getTitle_fks()[i])) {
					WorkFeatures insertObj = new WorkFeatures();
					insertObj.setTitle_fk((!StringUtils.isEmpty(obj.getTitle_fks()) && obj.getTitle_fks().length > 0)?obj.getTitle_fks()[i]:null);
					insertObj.setWork_id_fk(obj.getWork_id_fk());
					insertObj.setDashboard_url((!StringUtils.isEmpty(obj.getDashboard_urls()) && obj.getDashboard_urls().length > 0)?obj.getDashboard_urls()[i]:null);
					insertObj.setSoft_delete_status_fk((!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0 && !StringUtils.isEmpty(obj.getStatuses()[i]))?obj.getStatuses()[i]:CommonConstants.INACTIVE);
					source[p++] = new BeanPropertySqlParameterSource(insertObj);
				}
		    }
			int[] insert_count = namedParamJdbcTemplate.batchUpdate(insertQry, source);		
			if(insert_count.length > 0) {
				flag = true;
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<WorkFeatures> getWorksListFilter(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select work_id_fk,work_id,work_short_name "
					+ "from work_details "
					+ "LEFT JOIN work ON work_id_fk = work_id "					
					+ "WHERE work_id_fk IS NOT NULL "
					+ "GROUP BY work_id_fk ORDER BY work_id_fk ASC";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getTitlesList(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select title from work_details_title";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getStatusList(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select soft_delete_status as status from soft_delete_status";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getWorksList(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select work_id,work_short_name "
					+ "from work "				
					+ "WHERE work_id IS NOT NULL "
					+ "GROUP BY work_id ORDER BY work_id ASC";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getWorksListFilterForSalientFeatures(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select work_id_fk,work_id,work_short_name "
					+ "from salient_features "
					+ "LEFT JOIN work ON work_id_fk = work_id "					
					+ "WHERE work_id_fk IS NOT NULL "
					+ "GROUP BY work_id_fk ORDER BY work_id_fk ASC";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getWorkSalientFeatures(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select id,category_fk,work_id_fk,description,soft_delete_status_fk,category_fk as category,work_id,work_short_name "
					+ "from salient_features "
					+ "LEFT JOIN work ON work_id_fk = work_id "		
					+ "WHERE category_fk IS NOT NULL ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj)) {
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + "AND work_id_fk = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
					qry = qry + "AND soft_delete_status_fk = ? ";
					arrSize++;
				}
			}
			qry = qry + "ORDER BY work_id_fk ASC ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj)) {
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
					pValues[i++] = obj.getSoft_delete_status_fk();
				}
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<WorkFeatures> getCategoryList(WorkFeatures obj) throws Exception {
		List<WorkFeatures> objsList = null;
		try {
			String qry ="select category from salient_features_category";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkFeatures>(WorkFeatures.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addWorkSalientFeatures(WorkFeatures obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			String insertQry = "INSERT INTO salient_features"
					+ "(category_fk,work_id_fk,description,soft_delete_status_fk) VALUES (:category_fk,:work_id_fk,:description,:soft_delete_status_fk)";
			
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0) {
				obj.setCategory_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getCategory_fks()));
				if(arraySize < obj.getCategory_fks().length) {
					arraySize = obj.getCategory_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getDescriptions()) && obj.getDescriptions().length > 0) {
				obj.setDescriptions(CommonMethods.replaceEmptyByNullInSringArray(obj.getDescriptions()));
				if(arraySize < obj.getDescriptions().length) {
					arraySize = obj.getDescriptions().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0) {
				obj.setStatuses(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatuses()));
				if(arraySize < obj.getStatuses().length) {
					arraySize = obj.getStatuses().length;
				}
			}
			
			int size = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0 && !StringUtils.isEmpty(obj.getCategory_fks()[i])) {
					size++;
				}
			}
			
			SqlParameterSource[] source = new SqlParameterSource[size];
			int p = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0 && !StringUtils.isEmpty(obj.getCategory_fks()[i])) {
					WorkFeatures insertObj = new WorkFeatures();
					insertObj.setCategory_fk((!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0)?obj.getCategory_fks()[i]:null);
					insertObj.setWork_id_fk(obj.getWork_id_fk());
					insertObj.setDescription((!StringUtils.isEmpty(obj.getDescriptions()) && obj.getDescriptions().length > 0)?obj.getDescriptions()[i]:null);
					insertObj.setSoft_delete_status_fk((!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0 && !StringUtils.isEmpty(obj.getStatuses()[i]))?obj.getStatuses()[i]:CommonConstants.INACTIVE);
					source[p++] = new BeanPropertySqlParameterSource(insertObj);
				}
		    }
			int[] insert_count = namedParamJdbcTemplate.batchUpdate(insertQry, source);		
			if(insert_count.length > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateWorkSalientFeatures(WorkFeatures obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			String deleteQry = "DELETE FROM salient_features WHERE work_id_fk = :work_id_fk";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(deleteQry, paramSource);
			
			String insertQry = "INSERT INTO salient_features"
					+ "(category_fk,work_id_fk,description,soft_delete_status_fk) VALUES (:category_fk,:work_id_fk,:description,:soft_delete_status_fk)";
			
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0) {
				obj.setCategory_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getCategory_fks()));
				if(arraySize < obj.getCategory_fks().length) {
					arraySize = obj.getCategory_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getDescriptions()) && obj.getDescriptions().length > 0) {
				obj.setDescriptions(CommonMethods.replaceEmptyByNullInSringArray(obj.getDescriptions()));
				if(arraySize < obj.getDescriptions().length) {
					arraySize = obj.getDescriptions().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0) {
				obj.setStatuses(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatuses()));
				if(arraySize < obj.getStatuses().length) {
					arraySize = obj.getStatuses().length;
				}
			}
			
			int size = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0 && !StringUtils.isEmpty(obj.getCategory_fks()[i])) {
					size++;
				}
			}
			SqlParameterSource[] source = new SqlParameterSource[size];
			int p = 0;
			for (int i = 0; i < arraySize; i++) {
				if(!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0 && !StringUtils.isEmpty(obj.getCategory_fks()[i])) {
					WorkFeatures insertObj = new WorkFeatures();
					insertObj.setCategory_fk((!StringUtils.isEmpty(obj.getCategory_fks()) && obj.getCategory_fks().length > 0)?obj.getCategory_fks()[i]:null);
					insertObj.setWork_id_fk(obj.getWork_id_fk());
					insertObj.setDescription((!StringUtils.isEmpty(obj.getDescriptions()) && obj.getDescriptions().length > 0)?obj.getDescriptions()[i]:null);
					insertObj.setSoft_delete_status_fk((!StringUtils.isEmpty(obj.getStatuses()) && obj.getStatuses().length > 0 && !StringUtils.isEmpty(obj.getStatuses()[i]))?obj.getStatuses()[i]:CommonConstants.INACTIVE);
					source[p++] = new BeanPropertySqlParameterSource(insertObj);
				}
		    }
			int[] insert_count = namedParamJdbcTemplate.batchUpdate(insertQry, source);		
			if(insert_count.length > 0) {
				flag = true;
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
}
