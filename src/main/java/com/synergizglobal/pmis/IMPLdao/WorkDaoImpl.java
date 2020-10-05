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
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.WorkDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

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
			String qry ="SELECT DISTINCT work_id,work_name,project_id_fk,wr.executed_by_id_fk,railway_id_fk,r.railway_name,p.project_name,YEAR(sanctioned_year) as sanctioned_year,sanctioned_estimated_cost," + 
						"completeion_period_months,sanctioned_completion_cost,anticipated_cost,YEAR(year_of_completion) as year_of_completion,completion_cost" + 
						",w.remarks FROM work w " + 
						"LEFT JOIN project p ON w.project_id_fk = p.project_id " + 
						"LEFT JOIN work_railway wr ON w.work_id COLLATE utf8mb4_unicode_ci  = wr.work_id_fk " + 
						"LEFT JOIN railway r ON wr.railway_id_fk = r.railway_id ";
		
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		
		return objsList;
    }

	
	@Override
	public Work getWork(String workId,Work works)throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Work work = null;
		try {
			connection = dataSource.getConnection();
			String qry ="SELECT DISTINCT work_id,work_name,project_id_fk,wr.executed_by_id_fk,railway_id_fk,r.railway_name,p.project_name,YEAR(sanctioned_year) as sanctioned_year,sanctioned_estimated_cost," + 
					"completeion_period_months,sanctioned_completion_cost,anticipated_cost,YEAR(year_of_completion) as year_of_completion,completion_cost" + 
					",w.remarks FROM work w " + 
					"LEFT JOIN project p ON w.project_id_fk = p.project_id " + 
					"LEFT JOIN work_railway wr ON w.work_id COLLATE utf8mb4_unicode_ci  = wr.work_id_fk " + 
					"LEFT JOIN railway r ON wr.railway_id_fk = r.railway_id "
				    + "where work_id_fk = ?";
		
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
				work.setRemarks(resultSet.getString("remarks"));
				work.setRailway_id_fk(resultSet.getString("railway_id_fk"));
				work.setExecuted_by_id_fk(resultSet.getString("executed_by_id_fk"));
				work.setWorkRevisions(getWorkRevisions(work.getWork_id(),connection));				
				
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
	
	private List<Work> getWorkRevisions(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> workRevisions = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT financial_year,pink_book_item_number,latest_revised_cost, year_of_revision,revision_number"
					+ ",remarks from work_yearly_sanction where work_id_fk = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, work_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setFinancial_year(resultSet.getString("financial_year"));
				obj.setPink_book_item_number(resultSet.getString("pink_book_item_number"));
				obj.setLatest_revised_cost(resultSet.getString("latest_revised_cost"));
				obj.setYear_of_revision(resultSet.getString("year_of_revision"));
				obj.setRevision_number(resultSet.getString("revision_number"));
				obj.setRemarks(resultSet.getString("remarks"));
				workRevisions.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return workRevisions;
	}


	@Override
	public boolean updateWork(Work work)throws Exception{
		
		Connection con = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			String qry = "update work set work_name = ?,project_id_fk = ?,sanctioned_year=?,sanctioned_estimated_cost = ?," + 
						 "completeion_period_months = ?,sanctioned_completion_cost = ?,anticipated_cost = ?,year_of_completion = ?,completion_cost = ?,remarks = ? "+
						 "where work_id =?";
		
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,work.getWork_name());
			stmt.setString(2,work.getProject_id_fk());
			stmt.setString(3,work.getSanctioned_year()+"-00-00");
			stmt.setString(4,work.getSanctioned_estimated_cost());
			stmt.setString(5,work.getCompleteion_period_months());
			stmt.setString(6,work.getSanctioned_completion_cost());
			stmt.setString(7,work.getAnticipated_cost());
			stmt.setString(8,work.getYear_of_completion()+"-00-00");
			stmt.setString(9,work.getCompletion_cost());
			stmt.setString(10,work.getRemarks());
			stmt.setString(11,work.getWork_id());
			int count = stmt.executeUpdate();
			
			if(stmt != null){stmt.close();}
		
			String qry3 = "update work_railway set railway_id_fk =?,executed_by_id_fk = ?,remarks =? where work_id_fk = ?";
			stmt = con.prepareStatement(qry3); 
			stmt.setString(1,work.getRailway_id_fk());
			stmt.setString(2,work.getExecuted_by_id_fk());
			stmt.setString(3,work.getRemarks());
			stmt.setString(4,work.getWork_id());
			count = stmt.executeUpdate();
			if(!StringUtils.isEmpty(work) && !StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {

			String qryDelete = "delete from work_yearly_sanction where work_id_fk = ?";
			stmt = con.prepareStatement(qryDelete); 
		
			stmt.setString(1,work.getWork_id());}
			count = stmt.executeUpdate();
			
			if(stmt != null){stmt.close();}	
			String qry4 = "INSERT into  work_yearly_sanction (financial_year,pink_book_item_number,latest_revised_cost,"
					 +"year_of_revision,revision_number,remarks,work_id_fk) "
					 +"VALUES (?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry4); 
			if(!StringUtils.isEmpty(work) && !StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {
				for (int i = 0; i < work.getFinancial_years().length; i++) {
					if(!StringUtils.isEmpty(work.getFinancial_years()[i])){
						int p = 1;
						stmt.setString(p++,work.getFinancial_years()[i]);
						if(!StringUtils.isEmpty(work.getPink_book_item_numbers()) && work.getPink_book_item_numbers().length > 0) {
							stmt.setString(p++,work.getPink_book_item_numbers()[i]);
						}else {
							stmt.setString(p++,null);
						}	
						
						if(!StringUtils.isEmpty(work.getLatest_revised_costs()) && work.getLatest_revised_costs().length > 0) {
							stmt.setString(p++,work.getLatest_revised_costs()[i]);
						}else {
							stmt.setString(p++,null);
						}	
						if(!StringUtils.isEmpty(work.getYear_of_revisions()) && work.getYear_of_revisions().length > 0) {
							stmt.setString(p++,work.getYear_of_revisions()[i]);
						}else {
							stmt.setString(p++,null);
						}	
						
						if(!StringUtils.isEmpty(work.getRevision_numbers()) && work.getRevision_numbers().length > 0) {
							stmt.setString(p++,work.getRevision_numbers()[i]);
						}else {
							stmt.setString(p++,null);
						}
						
						if(!StringUtils.isEmpty(work.getRemarkss()) && work.getRemarkss().length > 0) {
							stmt.setString(p++,work.getRemarkss()[i]);
						}else {
							stmt.setString(p++,null);
						}
						if(!StringUtils.isEmpty(work.getWork_id()) ) {
							stmt.setString(p++,work.getWork_id());
						} else {
							stmt.setString(p++,null);
						}
						
	
						stmt.addBatch();
					}
				}
			}
			int[] c = stmt.executeBatch();
			if(c.length > 0 || count > 0){
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
	
	@Override
	public boolean addWork(Work work)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		int[] c = {};
		try{
			con = dataSource.getConnection();
			String workId = getWorkId(con);
			String wID = work.getProject_id_fk()+workId;
			
			String qry ="INSERT into work (work_id,work_name,project_id_fk,sanctioned_year,sanctioned_estimated_cost," + 
						"completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,remarks)"+
						" VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,wID ); 
			stmt.setString(2,work.getWork_name()); 
			stmt.setString(3,work.getProject_id_fk());
			stmt.setString(4,work.getSanctioned_year()+"-00-00");
			stmt.setString(5,work.getSanctioned_estimated_cost());
			stmt.setString(6,work.getCompleteion_period_months());
			stmt.setString(7,work.getSanctioned_completion_cost());
			stmt.setString(8,work.getAnticipated_cost());
			stmt.setString(9,work.getYear_of_completion()+"-00-00");
			stmt.setString(10,work.getCompletion_cost());
			stmt.setString(11,work.getRemarks());
			count = stmt.executeUpdate();
			
			if(stmt != null){stmt.close();}

			String qry3 = "INSERT into  work_railway (work_id_fk,railway_id_fk,executed_by_id_fk,remarks) VALUES (?,?,?,?)";
			stmt = con.prepareStatement(qry3); 
			stmt.setString(1,wID);
			stmt.setString(2,work.getRailway_id_fk());
			stmt.setString(3,work.getExecuted_by_id_fk());
			stmt.setString(4,work.getRemarks());
			count = stmt.executeUpdate(); 
			
			if(stmt != null){stmt.close();}
			String qry4 = "INSERT into  work_yearly_sanction (financial_year,pink_book_item_number,latest_revised_cost,"
						 +"year_of_revision,revision_number,remarks,work_id_fk) "
						 +"VALUES (?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry4); 
			if(!StringUtils.isEmpty(work) && !StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {
				for (int i = 0; i < work.getFinancial_years().length; i++) {
					if(!StringUtils.isEmpty(work.getFinancial_years()[i])){
						stmt.setString(1,work.getFinancial_years()[i]+"-00-00");
						stmt.setString(2,work.getPink_book_item_numbers()[i]);
						stmt.setString(3,work.getLatest_revised_costs()[i]);
						stmt.setString(4,work.getYear_of_revisions()[i]+"-00-00");
						stmt.setString(5,work.getRevision_numbers()[i]);
						stmt.setString(6,work.getRemarkss()[i]);
						stmt.setString(7,wID);
	
						stmt.addBatch();
					}
				}
			}
			c = stmt.executeBatch();
			if(c.length > 0 || count > 0){
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
	
	
	
	private String getWorkId(Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String workId = null;;
		String WID = null;
		try{
			String maxIdQry = "SELECT CONCAT(SUBSTRING(work_id, 1, LENGTH(work_id)-2),LPAD(MAX(SUBSTRING(work_id, 5, LENGTH(work_id)))+1,2,'0') ) AS maxId FROM work";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				workId = rs.getString("maxId");
				if(workId == null) {
					workId = "W01";
				}
				if(workId.length()>3) {
					  String[] arrOfStr = workId.split("W"); 
				        for (String a : arrOfStr) 
				        WID = "W"+arrOfStr[1];
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return WID;
	}

	@Override
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
	
	@Override
	public List<Railway> getExcecuteList()throws Exception{
		List<Railway> objsList = null;
		try {
			String qry = "SELECT DISTINCT executed_by_id_fk FROM `work_railway` WHERE executed_by_id_fk IS NOT NULL";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Railway>(Railway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public boolean deleteRow(String workId, Work work)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{  
			con = dataSource.getConnection();
			
			String qry = "delete from work where work_id = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, workId);
			int c = stmt.executeUpdate();  
			if(stmt != null){stmt.close();}
			
			String qry3 = "delete from work_railway where work_id_fk =?";
			stmt = con.prepareStatement(qry3);
			stmt.setString(1, workId);
			 c = stmt.executeUpdate();  
			 
			 if(stmt != null){stmt.close();}
			 
			 String qry4 = "delete from work_yearly_sanction where work_id_fk =?";
				stmt = con.prepareStatement(qry4);
				stmt.setString(1, workId);
				 c = stmt.executeUpdate(); 
			if(c > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}
	
	@Override
	public List<Year> getYearList()throws Exception{
		List<Year> objsList = null;
		try {
			String qry = "SELECT financial_year FROM financial_year";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Year>(Year.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Work> getWorkList(Work work)throws Exception{
		List<Work> objsList = null;
		
      try {
		   String qry = "SELECT DISTINCT work_id,work_name,project_id_fk,wr.executed_by_id_fk,railway_id_fk,r.railway_name,p.project_name,YEAR(sanctioned_year) as sanctioned_year,sanctioned_estimated_cost," + 
					    "completeion_period_months,sanctioned_completion_cost,anticipated_cost,YEAR(year_of_completion) as year_of_completion,completion_cost" + 
						",w.remarks FROM work w " + 
						"LEFT JOIN project p ON w.project_id_fk = p.project_id " + 
						"LEFT JOIN work_railway wr ON w.work_id COLLATE utf8mb4_unicode_ci  = wr.work_id_fk " + 
						"LEFT JOIN railway r ON wr.railway_id_fk = r.railway_id ";

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		
		return objsList;
	
	}

	
	
	
	
	
	
	
	
	
}