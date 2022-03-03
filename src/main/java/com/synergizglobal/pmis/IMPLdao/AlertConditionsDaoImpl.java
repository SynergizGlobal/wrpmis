package com.synergizglobal.pmis.IMPLdao;

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
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AlertConditionsDao;
import com.synergizglobal.pmis.model.AlertConditions;
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.ModulePermission;

@Repository
public class AlertConditionsDaoImpl implements AlertConditionsDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<AlertConditions> getAlertTypes() throws Exception {
		List<AlertConditions> objsList = null;
		try {
			String qry = "SELECT alert_type_fk "
					+ "from alert_conditions "
					+ "GROUP BY alert_type_fk ORDER BY alert_type_fk";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<AlertConditions>(AlertConditions.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<AlertConditions> getAlertConditions(AlertConditions obj) throws Exception {
		List<AlertConditions> objsList = null;
		try {
			String qry = "SELECT alert_type_fk,condition_info,first_condition,GROUP_CONCAT(first_condition_value) AS first_condition_value,second_condition,GROUP_CONCAT(second_condition_value) AS second_condition_value "
					+ "FROM alert_conditions "
					+ "WHERE alert_type_fk IS NOT NULL";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and alert_type_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY alert_type_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<AlertConditions>(AlertConditions.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	public List<AlertConditions> getAlertCondition(AlertConditions obj) throws Exception {
		List<AlertConditions> objsList = null;
		try {
			String qry = "SELECT alert_condition_id,alert_type_fk,condition_info,alert_level_fk,first_condition,first_condition_value,second_condition,second_condition_value "
					+ "FROM alert_conditions "
					+ "WHERE alert_type_fk = ?";
			
			objsList = jdbcTemplate.query( qry,new Object[]{obj.getAlert_type_fk()}, new BeanPropertyRowMapper<AlertConditions>(AlertConditions.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	/*@Override
	public AlertConditions getAlertCondition(AlertConditions obj) throws Exception {
		AlertConditions aObj = null;
		try {
			String qry = "SELECT alert_condition_id,alert_type_fk,alert_level_fk,first_condition,first_condition_value,second_condition,second_condition_value "
					+ "FROM alert_conditions "
					+ "WHERE alert_condition_id = ?";
			
			List<AlertConditions> objsList = jdbcTemplate.query( qry,new Object[]{obj.getAlert_condition_id()}, new BeanPropertyRowMapper<AlertConditions>(AlertConditions.class));
			for (AlertConditions alertConditions : objsList) {
				aObj = alertConditions;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return aObj;
	}*/

	@Override
	public boolean updateAlertCondition(AlertConditions obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getAlert_condition_ids()) && obj.getAlert_condition_ids().size() > 0) {
				if(arraySize < obj.getAlert_condition_ids().size()) {
					arraySize = obj.getAlert_condition_ids().size();
				}
			}
			if(!StringUtils.isEmpty(obj.getFirst_condition_values()) && obj.getFirst_condition_values().size() > 0) {
				if(arraySize < obj.getFirst_condition_values().size()) {
					arraySize = obj.getFirst_condition_values().size();
				}
			}
			if(!StringUtils.isEmpty(obj.getSecond_condition_values()) && obj.getSecond_condition_values().size() > 0) {
				if(arraySize < obj.getSecond_condition_values().size()) {
					arraySize = obj.getSecond_condition_values().size();
				}
			}
			
			if(arraySize > 0) {		
				String updateQry = "UPDATE alert_conditions set "
						+ "first_condition_value= :first_condition_value,second_condition_value= :second_condition_value "
						+ "where alert_condition_id = :alert_condition_id";
				SqlParameterSource[] source = new SqlParameterSource[arraySize];
				
				for (int i = 0; i < arraySize; i++) {
					AlertConditions pObj = new AlertConditions();
					if(!StringUtils.isEmpty(obj.getAlert_condition_ids()) && obj.getAlert_condition_ids().size() > 0 && !StringUtils.isEmpty(obj.getAlert_condition_ids().get(i))) {
						pObj.setAlert_condition_id(obj.getAlert_condition_ids().get(i));
					}
					if(!StringUtils.isEmpty(obj.getFirst_condition_values()) && obj.getFirst_condition_values().size() > 0 && !StringUtils.isEmpty(obj.getFirst_condition_values().get(i))) {
						pObj.setFirst_condition_value(obj.getFirst_condition_values().get(i));
						if(i < (arraySize-1)) {
							String second_condition_value = obj.getFirst_condition_values().get(i+1);
							pObj.setSecond_condition_value(second_condition_value);
						}
					}
					
					/*if(!StringUtils.isEmpty(obj.getSecond_condition_values()) && obj.getSecond_condition_values().size() > 0 && !StringUtils.isEmpty(obj.getSecond_condition_values().get(i))) {
						pObj.setSecond_condition_value(obj.getSecond_condition_values().get(i));
					}*/
					source[i] = new BeanPropertySqlParameterSource(pObj);
				}
				
				int[] count = namedParamJdbcTemplate.batchUpdate(updateQry, source);
				if(count.length > 0) {
					flag = true;
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
}
