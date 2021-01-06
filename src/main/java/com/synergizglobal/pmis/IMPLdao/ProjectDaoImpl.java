package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

import net.engio.mbassy.common.ISetEntry;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Project> getProjectList() throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT project_id, project_name, plan_head_number, pink_book_item_number, remarks, project_status, attachment FROM project";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
	}
		return objsList;
 }
	
	
	@Override
	public Project editProject(String projectId, Project projects)throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Project project = null;
		try {
			connection = dataSource.getConnection();
			String qry ="SELECT project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_status,attachment FROM project"
					+ " where project_id = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, projectId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				project = new Project();
				project.setProject_id(resultSet.getString("project_id"));
				project.setProject_name(resultSet.getString("project_name"));
				project.setPlan_head_number(resultSet.getString("plan_head_number"));
				project.setPink_book_item_number(resultSet.getString("pink_book_item_number"));
				project.setRemarks(resultSet.getString("remarks"));
				project.setProject_status(resultSet.getString("project_status"));
				project.setAttachment(resultSet.getString("attachment"));
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return project;
	}
	
	
	@Override
	public boolean updateProject(Project project)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			String qry = "update project set project_name = ?,plan_head_number = ?,pink_book_item_number =?,remarks = ?,project_status=?,attachment=?" + 
					 " where project_id =?";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,project.getProject_name());
			stmt.setString(2,project.getPlan_head_number());
			stmt.setString(3,project.getPink_book_item_number());
			stmt.setString(4,project.getRemarks());
			stmt.setString(5,project.getProject_status());
			stmt.setString(6,project.getAttachment());
			stmt.setString(7,project.getProject_id());
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

	@Override
	public boolean addProject(Project project)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			
			String projectId = getProjectId(con);
			
			String qry ="INSERT into project (project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_status,attachment)" + 
					 " VALUES(?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			
			stmt.setString(1,projectId); 
			stmt.setString(2,project.getProject_name()); 
			stmt.setString(3,project.getPlan_head_number()); 
			stmt.setString(4,project.getPink_book_item_number()); 
			stmt.setString(5,project.getRemarks()); 
			stmt.setString(6,project.getProject_status());
			stmt.setString(7,project.getAttachment());
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
	private String getProjectId(Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String projectId = null;;
		try{
			String maxIdQry = "SELECT CONCAT(SUBSTRING(project_id, 1, LENGTH(project_id)-2),LPAD(MAX(SUBSTRING(project_id, 2, LENGTH(project_id)))+1,2,'0') ) AS maxId FROM project";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				projectId = rs.getString("maxId");
				if(StringUtils.isEmpty(projectId)) {
					projectId = "P01";
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return projectId;
	}


	@Override
	public boolean deleteProject(String projectId, Project project)throws Exception{
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try{  
			con = dataSource.getConnection();
			String qry = "delete from project where project_id = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, projectId);
			int c = stmt.executeUpdate();  
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

}
	
	
	
	
	
	
	
	


