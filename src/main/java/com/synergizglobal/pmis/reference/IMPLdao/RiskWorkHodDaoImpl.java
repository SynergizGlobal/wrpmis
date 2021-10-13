package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.DBConnectionHandler;
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
			String qry ="select risk_work_hod_id, work_id_fk,sub_work, risk_work_completed,hod_user_id_fk,u.designation,w.work_short_name,priority,wh.short_name "
					+ "from risk_work_hod wh "
					+ "left join work w on wh.work_id_fk = w.work_id "
					+ "left join user u on wh.hod_user_id_fk = u.user_id "
					+ "ORDER BY priority ASC";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e);
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
		throw new Exception(e);
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
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addRiskWorkHOD(TrainingType obj) throws Exception {
		boolean flag = false;
		try {
			String riskCompleted = obj.getRisk_work_completed();
			if(StringUtils.isEmpty(riskCompleted)) 
			{
				obj.setRisk_work_completed("No");
			}
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO risk_work_hod"
					+ "( work_id_fk,sub_work, hod_user_id_fk,risk_work_completed,priority,short_name) VALUES (:work_id_fk,:sub_work, :hod_user_id_fk, :risk_work_completed,:priority,:short_name)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateRiskWorkHOD(TrainingType obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String riskCompleted = obj.getRisk_work_completed_new();
			if(StringUtils.isEmpty(riskCompleted)) 
			{
				obj.setRisk_work_completed_new("No");
			}
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE risk_work_hod set "
					+ "work_id_fk= :work_id_fk_new, hod_user_id_fk= :hod_user_id_fk_new,sub_work= :sub_work_new,risk_work_completed= :risk_work_completed_new,priority= :priority,short_name=:short_name where risk_work_hod_id = :risk_work_hod_id";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) 
			{
				flag = true;
				
				con = dataSource.getConnection();
				String qry = "select * from risk r   "
						+ "LEFT JOIN risk_revision rr on r.risk_id_pk=rr.risk_id_pk_fk where sub_work = ?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,obj.getSub_work_new());
				rs = stmt.executeQuery();  
				while(rs.next()) 
				{
				    String riskrevisionid = rs.getString(4);
				    
				    PreparedStatement stmtUpdate = null;
				    
				    String qry2 = "UPDATE risk_revision set owner = ? WHERE risk_revision_id = ?";
				    stmtUpdate = con.prepareStatement(qry2);
				    stmtUpdate.setString(1, getDesignation(obj.getHod_user_id_fk_new()));
				    stmtUpdate.setString(2, riskrevisionid);
					int c = stmtUpdate.executeUpdate();  						
				}				
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}
	
	private String getDesignation(String hod_user_id) throws Exception
	{
		String Designation="";
		try {
			String qry = "select designation from user where user_id = ?";
			Designation = (String) jdbcTemplate.queryForObject(qry, new Object[] { hod_user_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Designation;
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
		throw new Exception(e);
		}
		return flag;
	}

}
