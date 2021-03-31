package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.reference.Idao.RiskWorkHodDao;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Repository
public class RiskWorkHodDaoImpl implements RiskWorkHodDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getRiskWorkHODDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select risk_work_hod_id, work_id_fk, hod_user_id_fk,u.designation,w.work_short_name from risk_work_hod wh "
					+ "left join work w on wh.work_id_fk = w.work_id "
					+ "left join user u on wh.hod_user_id_fk = u.user_id ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,work_name,work_short_name from work ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getHODDetails(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select user_id as hod_user_id_fk,user_name,designation from user where user_type_fk = 'HOD'";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addRiskWorkHOD(TrainingType obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO risk_work_hod"
					+ "( work_id_fk, hod_user_id_fk) VALUES (:work_id_fk, :hod_user_id_fk)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateRiskWorkHOD(TrainingType obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE risk_work_hod set "
					+ "work_id_fk= :work_id_fk_new, hod_user_id_fk= :hod_user_id_fk_new where risk_work_hod_id = :risk_work_hod_id";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteRiskWorkHOD(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE from risk_work_hod WHERE `risk_work_hod_id`= :risk_work_hod_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			 count = namedParamJdbcTemplate.update(deleteQry, paramSource);
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return flag;
	}

}
