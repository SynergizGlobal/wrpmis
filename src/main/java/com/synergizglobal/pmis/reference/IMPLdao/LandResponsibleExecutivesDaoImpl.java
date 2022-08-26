package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.reference.Idao.LandResponsibleExecutivesDao;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class LandResponsibleExecutivesDaoImpl implements LandResponsibleExecutivesDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Override
	public List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM land_executives re "
					+ "LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id "
					+ "left join work w on re.work_id_fk = w.work_id "
					+ "GROUP BY work_id_fk,work_short_name;";
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public boolean addLandAcquisitionExecutives(TrainingType obj) throws Exception {
		int count = 0;
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry3 = "INSERT into land_executives (work_id_fk,executive_user_id_fk) "
					+ "VALUES (:work_id_fk,:executive_user_id_fk)";

			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int executivesArrSize = 0,workSize = 0;;
			int len = obj.getWork_id_fks().length;
			if(!StringUtils.isEmpty(obj.getWork_id_fks()) && obj.getWork_id_fks().length > 0) {
				obj.setWork_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getWork_id_fks()));
				if(executivesArrSize < obj.getWork_id_fks().length) {
					executivesArrSize = obj.getWork_id_fks().length;
				}
			}
			if(executivesArrSize == 1 ) {
	    		String joined = String.join(",", obj.getExecutive_user_id_fks());
	    		String[] strArray = new String[] {joined};
	    		obj.setExecutive_user_id_fks(strArray);
	    	}
			if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()) && obj.getExecutive_user_id_fks().length > 0) {
				obj.setExecutive_user_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getExecutive_user_id_fks()));
				if(executivesArrSize < obj.getExecutive_user_id_fks().length) {
					executivesArrSize = obj.getExecutive_user_id_fks().length;
				}
			}
			for (int i = 0; i < executivesArrSize; i++){
				List<String> executives = null;
				if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()[i]) && !StringUtils.isEmpty(obj.getWork_id_fks()[i])){
					if(obj.getExecutive_user_id_fks()[i].contains(",")) {
						executives = new ArrayList<String>(Arrays.asList(obj.getExecutive_user_id_fks()[i].split(",")));
					}else {
						executives = new ArrayList<String>(Arrays.asList(obj.getExecutive_user_id_fks()[i]));
					}
					for(String eObj : executives) {
						if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getWork_id_fks()) &&  !StringUtils.isEmpty(eObj)) {
							TrainingType fileObj = new TrainingType();
							fileObj.setWork_id_fk(obj.getWork_id_fks()[i]);
							fileObj.setExecutive_user_id_fk(eObj);
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							 count = namedParamJdbcTemplate.update(qry3, paramSource);
						}
					}
					if(count > 0) {
						flag = true;
					}
				}
			
			}		
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		} 
		return flag;
	}

	@Override
	public boolean updateLandAcquisitionExecutives(TrainingType obj) throws Exception {
		int count = 0;
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()) && obj.getExecutive_user_id_fks().length > 0) {
					
					String conDeleteQry = "DELETE from land_executives where work_id_fk = :work_id_fk_old";		 
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(conDeleteQry, paramSource);
			}
			String qry3 = "INSERT into land_executives (work_id_fk,executive_user_id_fk) "
					+ "VALUES (:work_id_fk,:executive_user_id_fk)";

			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int executivesArrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fks()) && obj.getWork_id_fks().length > 0) {
				obj.setWork_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getWork_id_fks()));
				if(executivesArrSize < obj.getWork_id_fks().length) {
					executivesArrSize = obj.getWork_id_fks().length;
				}
			}
			if(executivesArrSize == 1 ) {
	    		String joined = String.join(",", obj.getExecutive_user_id_fks());
	    		String[] strArray = new String[] {joined};
	    		obj.setExecutive_user_id_fks(strArray);
	    	}
			if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()) && obj.getExecutive_user_id_fks().length > 0) {
				obj.setExecutive_user_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getExecutive_user_id_fks()));
				if(executivesArrSize < obj.getExecutive_user_id_fks().length) {
					executivesArrSize = obj.getExecutive_user_id_fks().length;
				}
			}
			for (int i = 0; i < executivesArrSize; i++){
				List<String> executives = null;
				if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()[i])){
					if(obj.getExecutive_user_id_fks()[i].contains(",")) {
						executives = new ArrayList<String>(Arrays.asList(obj.getExecutive_user_id_fks()[i].split(",")));
					}else {
						executives = new ArrayList<String>(Arrays.asList(obj.getExecutive_user_id_fks()[i]));
					}
					for(String eObj : executives) {
						if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getWork_id_fks()[i]) &&  !StringUtils.isEmpty(eObj)) {
							TrainingType fileObj = new TrainingType();
							fileObj.setWork_id_fk(obj.getWork_id_fks()[i]);
							fileObj.setExecutive_user_id_fk(eObj);
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							 count = namedParamJdbcTemplate.update(qry3, paramSource);
						}
					}
					if(count > 0) {
						flag = true;
					}
				}
			
			}		
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		} 
		return flag;
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT  work_id_fk, w.work_short_name FROM la_land_identification la "
					+ "left join  work w on la.work_id_fk = w.work_id group by work_id_fk,w.work_short_name ";
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
}
