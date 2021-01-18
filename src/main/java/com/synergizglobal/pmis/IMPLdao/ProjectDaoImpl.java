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
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Project;

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
	public Project getProject(String projectId, Project projects)throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Project project = null;
		try {
			connection = dataSource.getConnection();
			String qry ="SELECT project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_status,attachment,benefits FROM project"
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
				project.setBenefits(resultSet.getString("benefits"));
				project.setGalleryFileNames(getGalleryFileNames(project.getProject_id(),connection));
				project.setProjectGallery(getProjectGalleryFiles(project.getProject_id(),connection));
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
	
	
	private List<Project> getProjectGalleryFiles(String project_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Project> filesObj = new ArrayList<Project>();
		try{
			String qry = "select id,file_name,project_id_fk,created_date,created_by from project_gallery where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				Project obj = new Project();
				obj.setId(rs.getString("id"));
				obj.setFile_name(rs.getString("file_name"));
				obj.setProject_id_fk(rs.getString("project_id_fk"));
				filesObj.add(obj);
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return filesObj;
	}


	private String getGalleryFileNames(String project_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String gallery_file_names = null;;
		try{
			String qry = "select group_concat(file_name) as gallery_file_names from project_gallery where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				gallery_file_names = rs.getString("gallery_file_names");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return gallery_file_names;
	}


	@Override
	public boolean updateProject(Project project)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			String qry = "update project set project_name = ?,plan_head_number = ?,pink_book_item_number =?,remarks = ?,project_status=?,attachment=?,benefits = ?" + 
					 " where project_id =?";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,project.getProject_name());
			stmt.setString(2,project.getPlan_head_number());
			stmt.setString(3,project.getPink_book_item_number());
			stmt.setString(4,project.getRemarks());
			stmt.setString(5,project.getProject_status());
			stmt.setString(6,project.getAttachment());
			stmt.setString(7,project.getBenefits());
			stmt.setString(8,project.getProject_id());
			count = stmt.executeUpdate();
			if(count > 0 ){
				flag = true;
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			if(flag) {				
				List<MultipartFile> galleryFiles = project.getProjectGalleryFiles();
				if(!StringUtils.isEmpty(galleryFiles) && galleryFiles.size() > 0 && !galleryFiles.get(0).isEmpty()) {					
					String deleteQry ="delete from project_gallery where project_id_fk = ? ";
					stmt = con.prepareStatement(deleteQry); 
					stmt.setString(1, project.getProject_id());
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					String galleryQry ="INSERT into project_gallery (file_name,project_id_fk,created_by)VALUES(?,?,?)";
					stmt = con.prepareStatement(galleryQry); 
					
					for (MultipartFile multipartFile : galleryFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.PROJECT_GALLERY_FILE_SAVING_PATH + project.getProject_id() + "/";
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							stmt.setString(1,fileName); 
							stmt.setString(2,project.getProject_id()); 
							stmt.setString(3,project.getCreated_by());
							stmt.addBatch();
						}
					}
					stmt.executeBatch();
				}				
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
			con.setAutoCommit(false);
			String qry ="INSERT into project (project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_status,attachment,benefits)" + 
					 " VALUES(?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			
			stmt.setString(1,projectId); 
			stmt.setString(2,project.getProject_name()); 
			stmt.setString(3,project.getPlan_head_number()); 
			stmt.setString(4,project.getPink_book_item_number()); 
			stmt.setString(5,project.getRemarks()); 
			stmt.setString(6,project.getProject_status());
			stmt.setString(7,project.getAttachment());
			stmt.setString(8,project.getBenefits());
			count = stmt.executeUpdate();
			if(count > 0 ){
				flag = true; 
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			if(flag) {
				String galleryQry ="INSERT into project_gallery (file_name,project_id_fk,created_by)VALUES(?,?,?)";
				stmt = con.prepareStatement(galleryQry); 
				if(!StringUtils.isEmpty(project.getProjectGalleryFiles()) && project.getProjectGalleryFiles().size() > 0) {
					List<MultipartFile> galleryFiles = project.getProjectGalleryFiles();
					for (MultipartFile multipartFile : galleryFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.PROJECT_GALLERY_FILE_SAVING_PATH + projectId + "/";
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							stmt.setString(1,fileName); 
							stmt.setString(2,projectId); 
							stmt.setString(3,project.getCreated_by());
							stmt.addBatch();
						}
					}
					stmt.executeBatch();
				}				
			}
			con.commit();
		}catch(Exception e){ 
			e.printStackTrace();
			con.rollback();
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
	
	
	
	
	
	
	
	


