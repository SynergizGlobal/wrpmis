package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AlertConditionsDao;
import com.synergizglobal.pmis.model.AlertConditions;

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
			String qry = "SELECT alert_condition_id,alert_type_fk,alert_level_fk,first_condition,first_condition_value,second_condition,second_condition_value "
					+ "FROM alert_conditions "
					+ "WHERE alert_type_fk IS NOT NULL";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and alert_type_fk = ? ";
				arrSize++;
			}
			
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

	@Override
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
	}

	@Override
	public boolean updateAlertCondition(AlertConditions obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String updateQry = "UPDATE alert_conditions set "
					+ "first_condition_value= :first_condition_value,second_condition_value= :second_condition_value "
					+ "where alert_condition_id = :alert_condition_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
}
