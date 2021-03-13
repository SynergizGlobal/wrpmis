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
import com.synergizglobal.pmis.common.CommonMethods;
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
	public List<Work> getWorkList(Work obj) throws Exception{
		List<Work> objsList = null;
		try {
			String qry ="SELECT DISTINCT work_id,work_name,work_short_name,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost,"
					+ "(SELECT GROUP_CONCAT(`work_railway`.`railway_id_fk` SEPARATOR ',') FROM `work_railway` WHERE (`work_railway`.`work_id_fk` = `w`.`work_id`)) AS `railway`," 
					+ "(SELECT GROUP_CONCAT(`work_railway`.`executed_by_id_fk` SEPARATOR ',') FROM `work_railway` WHERE (`work_railway`.`work_id_fk` = `w`.`work_id`)) AS `executed_by`,"
					+ "completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" 
					+ ",w.remarks,w.attachment,DATE_FORMAT(w.projected_completion,'%d-%m-%Y') AS projected_completion "
					+ "FROM work w "  
					+"LEFT JOIN project p ON w.project_id_fk = p.project_id ";
		
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
			String qry ="SELECT work_id,work_name,work_short_name,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost," 
					+ "completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" 
					+ ",w.remarks,w.attachment,DATE_FORMAT(w.projected_completion,'%d-%m-%Y') AS projected_completion "
					+ "FROM work w " 
					+ "LEFT JOIN project p ON w.project_id_fk = p.project_id " 
				    + "where work_id = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, workId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				work = new Work();
				work.setWork_id(resultSet.getString("work_id"));
				work.setWork_name(resultSet.getString("work_name"));
				work.setWork_short_name(resultSet.getString("work_short_name"));
				work.setProject_id_fk(resultSet.getString("project_id_fk"));
				work.setProject_name(resultSet.getString("project_name"));
				work.setSanctioned_year_fk(resultSet.getString("sanctioned_year_fk"));
				work.setSanctioned_estimated_cost(resultSet.getString("sanctioned_estimated_cost"));
				work.setSanctioned_completion_cost(resultSet.getString("sanctioned_completion_cost"));
				work.setCompleteion_period_months(resultSet.getString("completeion_period_months"));
				work.setAnticipated_cost(resultSet.getString("anticipated_cost"));
				work.setYear_of_completion(resultSet.getString("year_of_completion"));
				work.setCompletion_cost(resultSet.getString("completion_cost"));
				work.setRemarks(resultSet.getString("remarks"));
				work.setAttachment(resultSet.getString("attachment"));
				work.setProjected_completion(resultSet.getString("projected_completion"));
				work.setWorkRevisions(getWorkRevisions(work.getWork_id(),connection));	
				work.setRailwayAgencyList(getRailwayAgencyList(work.getWork_id(),connection));
				work.setExecutedByList(getExecutedByList(work.getWork_id(),connection));
				
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
	
	private List<Work> getExecutedByList(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> objsList = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT executed_by_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON executed_by_id_fk = railway_id "
					+ "where work_id_fk = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, work_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setExecuted_by_id_fk(resultSet.getString("executed_by_id_fk"));
				obj.setRailway_name(resultSet.getString("railway_name"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
	}


	private List<Work> getRailwayAgencyList(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> objsList = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT railway_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON railway_id_fk = railway_id "
					+ "where work_id_fk = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, work_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setRailway_id_fk(resultSet.getString("railway_id_fk"));
				obj.setRailway_name(resultSet.getString("railway_name"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
		
	}


	private List<Work> getWorkRevisions(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> workRevisions = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT financial_year,latest_revised_cost, year_of_revision,revision_number"
					+ " from work_yearly_sanction where work_id_fk = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, work_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setFinancial_year(resultSet.getString("financial_year"));
				obj.setLatest_revised_cost(resultSet.getString("latest_revised_cost"));
				obj.setYear_of_revision(resultSet.getString("year_of_revision"));
				obj.setRevision_number(resultSet.getString("revision_number"));
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
			con.setAutoCommit(false);
			String qry = "update work set work_name = ?,project_id_fk = ?,sanctioned_year_fk=?,sanctioned_estimated_cost = ?," + 
						 "completeion_period_months = ?,sanctioned_completion_cost = ?,anticipated_cost = ?,year_of_completion = ?,"
						 + "completion_cost = ?,remarks = ?,attachment = ?,projected_completion = ?,work_short_name = ? "+
						 "where work_id =?";
		
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,work.getWork_name());
			stmt.setString(2,work.getProject_id_fk());
			stmt.setString(3,work.getSanctioned_year_fk());
			stmt.setString(4,work.getSanctioned_estimated_cost());
			stmt.setString(5,work.getCompleteion_period_months());
			stmt.setString(6,work.getSanctioned_completion_cost());
			stmt.setString(7,work.getAnticipated_cost());
			stmt.setString(8,work.getYear_of_completion());
			stmt.setString(9,work.getCompletion_cost());
			stmt.setString(10,work.getRemarks());
			stmt.setString(11,work.getAttachment());
			stmt.setString(12,work.getProjected_completion());
			stmt.setString(13,work.getWork_short_name());
			stmt.setString(14,work.getWork_id());
			int count = stmt.executeUpdate();
			if(count > 0){
				flag = true; 
			}
			if(stmt != null){stmt.close();}
			
			String deleteWorkRailwayQry = "delete from work_railway where work_id_fk = ?";
			stmt = con.prepareStatement(deleteWorkRailwayQry); 
			stmt.setString(1,work.getWork_id());
			count = stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			if(!StringUtils.isEmpty(work.getRailway_id_fk())) {
				String qry3 = "INSERT into work_railway (work_id_fk,railway_id_fk) VALUES (?,?)";
				stmt = con.prepareStatement(qry3); 
				if(work.getRailway_id_fk().contains(",")) {
					String[] ids = work.getRailway_id_fk().split(",");					
					for (int i = 0; i < ids.length; i++) {
						stmt.setString(1,work.getWork_id());
						stmt.setString(2,!StringUtils.isEmpty(ids[i])?ids[i]:null);
						stmt.addBatch(); 
					}					
					stmt.executeBatch();
				}else {
					stmt.setString(1,work.getWork_id());
					stmt.setString(2,work.getRailway_id_fk());
					stmt.executeUpdate(); 
				}				
				if(stmt != null){stmt.close();}
			}

			if(!StringUtils.isEmpty(work.getExecuted_by_id_fk())) {
				String qry3 = "INSERT into work_railway (work_id_fk,executed_by_id_fk) VALUES (?,?)";
				stmt = con.prepareStatement(qry3); 
				if(work.getExecuted_by_id_fk().contains(",")) {
					String[] ids = work.getExecuted_by_id_fk().split(",");					
					for (int i = 0; i < ids.length; i++) {
						stmt.setString(1,work.getWork_id());
						stmt.setString(2,!StringUtils.isEmpty(ids[i])?ids[i]:null);
						stmt.addBatch(); 
					}					
					stmt.executeBatch();
				}else {
					stmt.setString(1,work.getWork_id());
					stmt.setString(2,work.getExecuted_by_id_fk());
					stmt.executeUpdate(); 
				}				
				if(stmt != null){stmt.close();}
			}
			
			String qryDelete = "delete from work_yearly_sanction where work_id_fk = ?";
			stmt = con.prepareStatement(qryDelete); 			
			stmt.setString(1,work.getWork_id());
			count = stmt.executeUpdate();			
			if(stmt != null){stmt.close();}	
			
			if(flag) {			
				String qry4 = "INSERT into  work_yearly_sanction (financial_year,latest_revised_cost,"
						 +"year_of_revision,revision_number,work_id_fk) "
						 +"VALUES (?,?,?,?,?)";
				stmt = con.prepareStatement(qry4); 
				
				int arraySize = 0;
				
				if(!StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {
					work.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(work.getFinancial_years()));
					if(arraySize < work.getFinancial_years().length) {
						arraySize = work.getFinancial_years().length;
					}
				}
				if(!StringUtils.isEmpty(work.getLatest_revised_costs()) && work.getLatest_revised_costs().length > 0) {
					work.setLatest_revised_costs(CommonMethods.replaceEmptyByNullInSringArray(work.getLatest_revised_costs()));
					if(arraySize < work.getLatest_revised_costs().length) {
						arraySize = work.getLatest_revised_costs().length;
					}
				}
				if(!StringUtils.isEmpty(work.getYear_of_revisions()) && work.getYear_of_revisions().length > 0) {
					work.setYear_of_revisions(CommonMethods.replaceEmptyByNullInSringArray(work.getYear_of_revisions()));
					if(arraySize < work.getYear_of_revisions().length) {
						arraySize = work.getYear_of_revisions().length;
					}
				}
				if(!StringUtils.isEmpty(work.getRevision_numbers()) && work.getRevision_numbers().length > 0) {
					work.setRevision_numbers(CommonMethods.replaceEmptyByNullInSringArray(work.getRevision_numbers()));
					if(arraySize < work.getRevision_numbers().length) {
						arraySize = work.getRevision_numbers().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					int p = 1;
					stmt.setString(p++,(work.getFinancial_years().length > 0)?work.getFinancial_years()[i]:null);
					stmt.setString(p++,(work.getLatest_revised_costs().length > 0)?work.getLatest_revised_costs()[i]:null);
					stmt.setString(p++,(work.getYear_of_revisions().length > 0)?work.getYear_of_revisions()[i]:null);						
					stmt.setString(p++,(work.getRevision_numbers().length > 0)?work.getRevision_numbers()[i]:null);
					stmt.setString(p++,work.getWork_id());
					stmt.addBatch();
				}
				int[] c = stmt.executeBatch();
			}
			
			con.commit();
		}catch(Exception e){ 
			con.rollback();
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
		try{
			con = dataSource.getConnection();
			String workId = getWorkId(work.getProject_id_fk(),con);
			con.setAutoCommit(false);
			String qry ="INSERT into work (work_id,work_name,project_id_fk,sanctioned_year_fk,sanctioned_estimated_cost," + 
						"completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,"
						+ "remarks,attachment,projected_completion,work_short_name)"+
						" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,workId ); 
			stmt.setString(2,work.getWork_name()); 
			stmt.setString(3,work.getProject_id_fk());
			stmt.setString(4,work.getSanctioned_year_fk());
			stmt.setString(5,work.getSanctioned_estimated_cost());
			stmt.setString(6,work.getCompleteion_period_months());
			stmt.setString(7,work.getSanctioned_completion_cost());
			stmt.setString(8,work.getAnticipated_cost());
			stmt.setString(9,work.getYear_of_completion());
			stmt.setString(10,work.getCompletion_cost());
			stmt.setString(11,work.getRemarks());
			stmt.setString(12,work.getAttachment());
			stmt.setString(13,work.getProjected_completion());
			stmt.setString(14,work.getWork_short_name());
			count = stmt.executeUpdate();
			if(count > 0){
				flag = true; 
			}
			if(stmt != null){stmt.close();}
			
			if(!StringUtils.isEmpty(work.getRailway_id_fk())) {
				String qry3 = "INSERT into  work_railway (work_id_fk,railway_id_fk) VALUES (?,?)";
				stmt = con.prepareStatement(qry3); 
				if(work.getRailway_id_fk().contains(",")) {
					String[] ids = work.getRailway_id_fk().split(",");					
					for (int i = 0; i < ids.length; i++) {
						stmt.setString(1,workId);
						stmt.setString(2,!StringUtils.isEmpty(ids[i])?ids[i]:null);
						stmt.addBatch(); 
					}					
					stmt.executeBatch();
				}else {
					stmt.setString(1,workId);
					stmt.setString(2,work.getRailway_id_fk());
					stmt.executeUpdate(); 
				}				
				if(stmt != null){stmt.close();}
			}

			if(!StringUtils.isEmpty(work.getExecuted_by_id_fk())) {
				String qry3 = "INSERT into  work_railway (work_id_fk,executed_by_id_fk) VALUES (?,?)";
				stmt = con.prepareStatement(qry3); 
				if(work.getExecuted_by_id_fk().contains(",")) {
					String[] ids = work.getExecuted_by_id_fk().split(",");					
					for (int i = 0; i < ids.length; i++) {
						stmt.setString(1,workId);
						stmt.setString(2,!StringUtils.isEmpty(ids[i])?ids[i]:null);
						stmt.addBatch(); 
					}					
					stmt.executeBatch();
				}else {
					stmt.setString(1,workId);
					stmt.setString(2,work.getExecuted_by_id_fk());
					stmt.executeUpdate(); 
				}				
				if(stmt != null){stmt.close();}
			}
			
			
			String qry4 = "INSERT into  work_yearly_sanction (financial_year,latest_revised_cost,"
						 +"year_of_revision,revision_number,work_id_fk) "
						 +"VALUES (?,?,?,?,?)";
			stmt = con.prepareStatement(qry4); 
			if(flag) {		
				int arraySize = 0;
				if(!StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {
					work.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(work.getFinancial_years()));
					if(arraySize < work.getFinancial_years().length) {
						arraySize = work.getFinancial_years().length;
					}
				}
				if(!StringUtils.isEmpty(work.getLatest_revised_costs()) && work.getLatest_revised_costs().length > 0) {
					work.setLatest_revised_costs(CommonMethods.replaceEmptyByNullInSringArray(work.getLatest_revised_costs()));
					if(arraySize < work.getLatest_revised_costs().length) {
						arraySize = work.getLatest_revised_costs().length;
					}
				}
				if(!StringUtils.isEmpty(work.getYear_of_revisions()) && work.getYear_of_revisions().length > 0) {
					work.setYear_of_revisions(CommonMethods.replaceEmptyByNullInSringArray(work.getYear_of_revisions()));
					if(arraySize < work.getYear_of_revisions().length) {
						arraySize = work.getYear_of_revisions().length;
					}
				}
				if(!StringUtils.isEmpty(work.getRevision_numbers()) && work.getRevision_numbers().length > 0) {
					work.setRevision_numbers(CommonMethods.replaceEmptyByNullInSringArray(work.getRevision_numbers()));
					if(arraySize < work.getRevision_numbers().length) {
						arraySize = work.getRevision_numbers().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					int p = 1;
					stmt.setString(p++,(work.getFinancial_years().length > 0)?work.getFinancial_years()[i]:null);
					stmt.setString(p++,(work.getLatest_revised_costs().length > 0)?work.getLatest_revised_costs()[i]:null);
					stmt.setString(p++,(work.getYear_of_revisions().length > 0)?work.getYear_of_revisions()[i]:null);						
					stmt.setString(p++,(work.getRevision_numbers().length > 0)?work.getRevision_numbers()[i]:null);
					stmt.setString(p++,work.getWork_id());
					stmt.addBatch();
				}
				int[] c = stmt.executeBatch();
			}
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;
	}
	
	
	
	private String getWorkId(String projectId,Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String workId = null;
		try{			
			String maxIdQry = "SELECT CONCAT(SUBSTRING(work_id, 1, LENGTH(work_id)-3),'W',LPAD(MAX(SUBSTRING(work_id, 5, LENGTH(work_id)))+1,2,'0') ) AS maxId FROM work WHERE work_id LIKE ?";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1, projectId+"%");
			rs = stmt.executeQuery();  
			if(rs.next()) {
				workId = rs.getString("maxId");
				if(StringUtils.isEmpty(workId)) {
					workId = projectId+"W01";
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return workId;
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
	
}