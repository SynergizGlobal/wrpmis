package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.WorkDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.Work;

@Repository
public class WorkDaoImpl implements WorkDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	
	@Override
	public List<Work> getworkList() throws Exception{
		List<Work> objsList = null;
		try {
			String qry ="SELECT work_id,work_name,project_id_fk,wr.executed_by_id_fk,railway_id_fk,r.railway_name,p.project_name,sanctioned_year,sanctioned_estimated_cost,\r\n" + 
					"	completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" + 
					"	,weight,w.remarks FROM WORK w " + 
					"	 JOIN project p ON w.project_id_fk = p.project_id" + 
					"	RIGHT JOIN work_railway wr ON wr.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id " + 
					"	JOIN railway r ON r.railway_id = wr.railway_id_fk WHERE work_id IS NOT NULL" + 
					"	";
		
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		
	}catch(Exception e){ 
		throw new Exception(e.getMessage());
	}
	
		return objsList;
 }

	
	@SuppressWarnings("null")
	@Override
	public Work editWork(String workId,Work works)throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Work work = null;
		
		try {
			connection = dataSource.getConnection();
			String qry ="SELECT work_id,work_name,project_id_fk,wr.executed_by_id_fk,railway_id_fk,r.railway_name,p.project_name,sanctioned_year,sanctioned_estimated_cost, " + 
					"completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost " + 
					",weight,w.remarks FROM WORK w " + 
					"JOIN project p ON w.project_id_fk = p.project_id " + 
					"RIGHT JOIN work_railway wr ON wr.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id " + 
					" JOIN railway r ON r.railway_id = wr.railway_id_fk WHERE work_id =?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, workId);
			
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				work = new Work();
				work.setWork_id(resultSet.getString("work_id"));
				work.setWork_name(resultSet.getString("work_name"));
				work.setProject_id_fk(resultSet.getString("project_id_fk"));
				work.setSanctioned_year(resultSet.getString("sanctioned_year"));
				work.setSanctioned_estimated_cost(resultSet.getString("sanctioned_estimated_cost"));
				work.setSanctioned_completion_cost(resultSet.getString("sanctioned_completion_cost"));
				work.setCompleteion_period_months(resultSet.getString("completeion_period_months"));
				work.setAnticipated_cost(resultSet.getString("anticipated_cost"));
				work.setYear_of_completion(resultSet.getString("year_of_completion"));
				work.setCompletion_cost(resultSet.getString("completion_cost"));
				work.setWeight(resultSet.getString("weight"));
				work.setRemarks(resultSet.getString("remarks"));
				work.setRailway_id_fk(resultSet.getString("railway_id_fk"));
				 
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		
		return work;
		
	}
	
	public boolean updateWork(Work work)throws Exception{
		
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			
			String qry = "update work set work_name = ?,project_id_fk = ?,sanctioned_year =?,sanctioned_estimated_cost = ?," + 
					"completeion_period_months = ?,sanctioned_completion_cost = ?,anticipated_cost = ?,year_of_completion = ?,completion_cost = ?"
					+ " where work_id =?";
		
			stmt = con.prepareStatement(qry); 
			  
			 
			stmt.setString(1,work.getWork_name());
			stmt.setString(2,work.getProject_id_fk());
			stmt.setString(3,work.getSanctioned_year());
			stmt.setString(4,work.getSanctioned_estimated_cost());
			stmt.setString(5,work.getCompleteion_period_months());
			stmt.setString(6,work.getSanctioned_completion_cost());
			stmt.setString(7,work.getAnticipated_cost());
			stmt.setString(8,work.getYear_of_completion());
			stmt.setString(9,work.getCompletion_cost());
			stmt.setString(10,work.getWork_id());

		
			count = stmt.executeUpdate();
			
			if(count > 0 ){
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;	
		
	}
	
	public boolean addWork(Work work)throws Exception{
		
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			
			String qry ="INSERT into work (work_id,work_name,project_id_fk,sanctioned_year,sanctioned_estimated_cost," + 
					"completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,work.getWork_id()); 
			stmt.setString(2,work.getWork_name()); 
			stmt.setString(3,work.getProject_id_fk());
			stmt.setString(4,work.getSanctioned_year());
			stmt.setString(5,work.getSanctioned_estimated_cost());
			stmt.setString(6,work.getCompleteion_period_months());
			stmt.setString(7,work.getSanctioned_completion_cost());
			stmt.setString(8,work.getAnticipated_cost());
			stmt.setString(9,work.getYear_of_completion());
			stmt.setString(10,work.getCompletion_cost());
			
			count = stmt.executeUpdate();
			
			if(count > 0 ){
				flag = true; 
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;
		
	}
	
	
	
	public List<Railway> getRailwayList()throws Exception{
		List<Railway> objsList = null;
		try {
			String qry = "select railway_id,railway_name from `railway`";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Railway>(Railway.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
}