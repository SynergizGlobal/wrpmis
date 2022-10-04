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
			String qry = "SELECT  work_id_fk, work_short_name, STRING_AGG(u.user_name , ',') user_name,STRING_AGG(u.user_id , ',') user_id FROM rr_executives re "
					+ "left join [user] u on re.executive_user_id_fk = u.user_id "
					+ "left join work w on re.work_id_fk = w.work_id "
					+ "GROUP BY work_id_fk,work_short_name";
			
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
			String qry = "select id,title_fk,work_id_fk,dashboard_url,soft_delete_status_fk,title_fk as title,work_id,work_short_name \r\n" + 
					"					from work_details \r\n" + 
					"					LEFT JOIN work ON work_id_fk = work_id 		\r\n" + 
					"					WHERE title_fk IS NOT NULL  ";
			
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
			
			qry = qry + "ORDER BY \r\n" + 
					"					case when user_type_fk='HOD' then 1\r\n" + 
					"					when user_type_fk='DYHOD' then 2\r\n" + 
					"					when user_type_fk='Officers ( Jr./Sr. Scale )' then 3\r\n" + 
					"					when user_type_fk='Others' then 4\r\n" + 
					"					end asc ,\r\n" + 
					"case when u.designation='ED Civil' then 1 \r\n" + 
					"   when u.designation='CPM I' then 2 \r\n" + 
					"   when u.designation='CPM II' then 3\r\n" + 
					"   when u.designation='CPM III' then 4 \r\n" + 
					"   when u.designation='CPM V' then 5\r\n" + 
					"   when u.designation='CE' then 6 \r\n" + 
					"   when u.designation='ED S&T' then 7 \r\n" + 
					"   when u.designation='CSTE' then 8\r\n" + 
					"   when u.designation='GM Electrical' then 9\r\n" + 
					"   when u.designation='CEE Project I' then 10\r\n" + 
					"   when u.designation='CEE Project II' then 11\r\n" + 
					"   when u.designation='ED Finance & Planning' then 12\r\n" + 
					"   when u.designation='AGM Civil' then 13\r\n" + 
					"   when u.designation='DyCPM Civil' then 14\r\n" + 
					"   when u.designation='DyCPM III' then 15\r\n" + 
					"   when u.designation='DyCPM V' then 16\r\n" + 
					"   when u.designation='DyCE EE' then 17\r\n" + 
					"   when u.designation='DyCE Badlapur' then 18\r\n" + 
					"   when u.designation='DyCPM Pune' then 19\r\n" + 
					"   when u.designation='DyCE Proj' then 20\r\n" + 
					"   when u.designation='DyCEE I' then 21\r\n" + 
					"   when u.designation='DyCEE Projects' then 22\r\n" + 
					"   when u.designation='DyCEE PSI' then 23\r\n" + 
					"   when u.designation='DyCSTE I' then 24\r\n" + 
					"   when u.designation='DyCSTE IT' then 25\r\n" + 
					"   when u.designation='DyCSTE Projects' then 26\r\n" + 
					"   when u.designation='XEN Consultant' then 27\r\n" + 
					"   when u.designation='AEN Adhoc' then 28\r\n" + 
					"   when u.designation='AEN Project' then 29\r\n" + 
					"   when u.designation='AEN P-Way' then 30\r\n" + 
					"   when u.designation='AEN' then 31\r\n" + 
					"   when u.designation='Sr Manager Signal' then 32 \r\n" + 
					"   when u.designation='Manager Signal' then 33\r\n" + 
					"   when u.designation='Manager Civil' then 34 \r\n" + 
					"   when u.designation='Manager OHE' then 35\r\n" + 
					"   when u.designation='Manager GS' then 36\r\n" + 
					"   when u.designation='Manager Finance' then 37\r\n" + 
					"   when u.designation='Planning Manager' then 38\r\n" + 
					"   when u.designation='Manager Project' then 39\r\n" + 
					"   when u.designation='Manager' then 40 \r\n" + 
					"   when u.designation='SSE' then 41\r\n" + 
					"   when u.designation='SSE Project' then 42\r\n" + 
					"   when u.designation='SSE Works' then 43\r\n" + 
					"   when u.designation='SSE Drg' then 44\r\n" + 
					"   when u.designation='SSE BR' then 45\r\n" + 
					"   when u.designation='SSE P-Way' then 46\r\n" + 
					"   when u.designation='SSE OHE' then 47\r\n" + 
					"   when u.designation='SPE' then 48\r\n" + 
					"   when u.designation='PE' then 49\r\n" + 
					"   when u.designation='JE' then 50\r\n" + 
					"   when u.designation='Demo-HOD-Elec' then 51\r\n" + 
					"   when u.designation='Demo-HOD-Engg' then 52\r\n" + 
					"   when u.designation='Demo-HOD-S&T' then 53\r\n" + 
					"\r\n" + 
					"   end asc";
			
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
	
}
