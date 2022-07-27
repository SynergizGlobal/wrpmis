package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.reference.Idao.RrResponsibleExecutivesDao;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Repository
public class RrResponsibleExecutivesDaoImpl implements RrResponsibleExecutivesDao{

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
			String qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(DISTINCT (u.user_name) SEPARATOR ',') user_name,STRING_AGG(DISTINCT (u.user_id) SEPARATOR ',') user_id FROM rr_executives re "
					+ "left join [user] u on re.executive_user_id_fk = u.user_id "
					+ "left join work w on re.work_id_fk = w.work_id "
					+ "GROUP BY work_id_fk;";
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public boolean addRRExecutives(TrainingType obj) throws Exception {
		int count = 0;
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry3 = "INSERT into rr_executives (work_id_fk,executive_user_id_fk) "
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
	public boolean updateRRExecutives(TrainingType obj) throws Exception {
		int count = 0;
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			if(!StringUtils.isEmpty(obj.getExecutive_user_id_fks()) && obj.getExecutive_user_id_fks().length > 0) {
					
					String conDeleteQry = "DELETE from rr_executives where work_id_fk = :work_id_fk_old";		 
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(conDeleteQry, paramSource);
			}
			String qry3 = "INSERT into rr_executives (work_id_fk,executive_user_id_fk) "
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
	public List<TrainingType> executivesList(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name SEPARATOR ', ') user_name FROM rr_executives re "
					+ "left join [user] u on re.executive_user_id_fk = u.user_id "
					+ "left join work w on re.work_id_fk = w.work_id "
					+ "where  work_id_fk = ?";
			Object[] pValues = new Object[1];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT  r.work_id as work_id_fk, w.work_short_name FROM rr r "
					+ "left join work w on r.work_id = w.work_id group by r.work_id  ";
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<TrainingType> getUsersDetails(TrainingType obj) throws Exception {
		List<TrainingType> objList = null;
		try {
			String qry = "SELECT user_id,user_name,designation FROM [user] u where user_id is not null and user_type_fk <> '' and u.user_type_fk not in('Management')";
			qry = qry + " and user_name not like '%user%' and pmis_key_fk not like '%SGS%'";// and department_fk in('Engg','Elec','S&T') 
			
			qry = qry + " ORDER BY FIELD(user_type_fk,'HOD','DYHOD','Officers ( Jr./Sr. Scale )','Others'),"
					+ "FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','AGM Civil'," 
					+ " 'DyCPM Civil','DyCPM III','DyCPM V','DyCE EE','DyCE Badlapur','DyCPM Pune','DyCE Proj','DyCEE I','DyCEE Projects','DyCEE PSI','DyCSTE I','DyCSTE IT','DyCSTE Projects','XEN Consultant'," 
					+ " 'AEN Adhoc','AEN Project','AEN P-Way','AEN','Sr Manager Signal','Manager Signal','Manager Civil','Manager OHE','Manager GS','Manager Finance','Planning Manager'," 
					+ " 'Manager Project','Manager','SSE','SSE Project','SSE Works','SSE Drg','SSE BR','SSE P-Way','SSE OHE','SPE','PE','JE','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";
			
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
	
}
