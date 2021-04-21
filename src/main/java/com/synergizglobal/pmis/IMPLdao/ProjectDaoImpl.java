package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Year;

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
			String qry ="SELECT project_id, project_name, plan_head_number, remarks, project_status, attachment FROM project";
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
			String qry ="SELECT project_id,project_name,plan_head_number,remarks,project_status,attachment,benefits FROM project"
					+ " where project_id = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, projectId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				project = new Project();
				project.setProject_id(resultSet.getString("project_id"));
				project.setProject_name(resultSet.getString("project_name")); 
				project.setPlan_head_number(resultSet.getString("plan_head_number"));
				project.setRemarks(resultSet.getString("remarks"));
				project.setProject_status(resultSet.getString("project_status"));
				project.setAttachment(resultSet.getString("attachment"));
				project.setBenefits(resultSet.getString("benefits"));
				project.setGalleryFileNames(getGalleryFileNames(project.getProject_id(),connection));
				project.setProjectGallery(getProjectGalleryFiles(project.getProject_id(),connection));
				project.setProjectPinkBooks(getProjectPinkBooks(project.getProject_id(),connection));
				project.setProjectFiles(getProjectFiles(project.getProject_id(),connection));
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
	
	
	private List<Project> getProjectFiles(String project_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Project> objsList = new ArrayList<Project>();
		try{
			String qry = "select id, project_id_fk, attachment from project_files where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			while(rs.next()) {
				Project obj = new Project();
				obj.setId(rs.getString("id"));
				obj.setProject_id_fk(rs.getString("project_id_fk"));
				obj.setAttachment(rs.getString("attachment"));
				objsList.add(obj);
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return objsList;
	}


	private List<Project> getProjectPinkBooks(String project_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Project> objsList = new ArrayList<Project>();
		try{
			String qry = "select project_pinkbook_id,project_id_fk,financial_year_fk,pb_item_no from project_pinkbook where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			while(rs.next()) {
				Project obj = new Project();
				obj.setProject_pinkbook_id(rs.getString("project_pinkbook_id"));
				obj.setProject_id_fk(rs.getString("project_id_fk"));
				obj.setFinancial_year_fk(rs.getString("financial_year_fk"));
				obj.setPb_item_no(rs.getString("pb_item_no"));
				objsList.add(obj);
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return objsList;
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
			while(rs.next()) {
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
			String qry = "update project set project_name = ?,plan_head_number = ?,remarks = ?,project_status=?,attachment=?,benefits = ?" + 
					 " where project_id =?";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1,project.getProject_name());
			stmt.setString(2,project.getPlan_head_number());
			stmt.setString(3,project.getRemarks());
			stmt.setString(4,project.getProject_status());
			stmt.setString(5,project.getAttachment());
			stmt.setString(6,project.getBenefits());
			stmt.setString(7,project.getProject_id());
			count = stmt.executeUpdate();
			if(count > 0 ){
				flag = true;
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			
			if(flag) {	
				String docFileName = null;
				int arraySize = 0;
				if(!StringUtils.isEmpty(project.getProjectGalleryFileNames()) && project.getProjectGalleryFileNames().length > 0 ) {
					project.setProjectGalleryFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectGalleryFileNames()));
					if(arraySize < project.getProjectGalleryFileNames().length) {
						arraySize = project.getProjectGalleryFileNames().length;
					}
				}
				List<MultipartFile> galleryFiles = project.getProjectGalleryFiles();
				if(galleryFiles.size() > 0 && !galleryFiles.get(0).isEmpty() && !(arraySize > 1)) {arraySize = 1; }
				if(!StringUtils.isEmpty(galleryFiles)) {
					String deleteQry ="delete from project_gallery where project_id_fk = ? ";
					stmt = con.prepareStatement(deleteQry); 
					stmt.setString(1, project.getProject_id());
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					String galleryQry ="INSERT into project_gallery (file_name,project_id_fk,created_by)VALUES(?,?,?)";
					stmt = con.prepareStatement(galleryQry); 
					if(arraySize > 0) {
						for (int i = 0; i < arraySize; i++) {
							docFileName  = (project.getProjectGalleryFileNames().length > 0)?project.getProjectGalleryFileNames()[i]:null;
						    if(docFileName == null) {
						    	docFileName = null;
						    }
						    if(docFileName != null) {
								stmt.setString(1,docFileName); 
								stmt.setString(2,project.getProject_id()); 
								stmt.setString(3,project.getCreated_by());
								stmt.addBatch();
						    }
						}
					}
					if(!StringUtils.isEmpty(galleryFiles) && galleryFiles.size() > 0 && !galleryFiles.get(0).isEmpty()) {		
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
					}
					stmt.executeBatch();
				}
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				 arraySize = 0;
				if(!StringUtils.isEmpty(project.getProjectFileNames()) && project.getProjectFileNames().length > 0 ) {
					project.setProjectFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectFileNames()));
					if(arraySize < project.getProjectFileNames().length) {
						arraySize = project.getProjectFileNames().length;
					}
				}
				List<MultipartFile> projectFiles = project.getProjectFile();
				
				if(!StringUtils.isEmpty(projectFiles)) {
					String deleteQry ="delete from project_files where project_id_fk = ? ";
					stmt = con.prepareStatement(deleteQry); 
					stmt.setString(1, project.getProject_id());
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					String filesQry ="INSERT into project_files (attachment,project_id_fk)VALUES(?,?)";
					stmt = con.prepareStatement(filesQry); 
					if(arraySize > 0) {
						for (int i = 0; i < arraySize; i++) {
							docFileName  = (project.getProjectFileNames().length > 0)?project.getProjectFileNames()[i]:null;
						    if(docFileName == null) {
						    	docFileName = null;
						    }
						    if(docFileName != null) {
								stmt.setString(1,docFileName); 
								stmt.setString(2,project.getProject_id()); 
								stmt.addBatch();
						    }
						}
					}
					if(!StringUtils.isEmpty(projectFiles) && projectFiles.size() > 0 && !projectFiles.get(0).isEmpty()) {		
						for (MultipartFile multipartFile : projectFiles) {
							if (null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants.PROJECT_FILE_SAVING_PATH ;
								String fileName = multipartFile.getOriginalFilename();
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								
								stmt.setString(1,fileName); 
								stmt.setString(2,project.getProject_id()); 
								stmt.addBatch();
							}
						}
					}
					stmt.executeBatch();
				}
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			
				arraySize = 0;
				if(!StringUtils.isEmpty(project.getFinancial_years()) && project.getFinancial_years().length > 0 ) {
					project.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(project.getFinancial_years()));
					if(arraySize < project.getFinancial_years().length) {
						arraySize = project.getFinancial_years().length;
					}
				}
				if(!StringUtils.isEmpty(project.getPink_book_item_numbers()) && project.getPink_book_item_numbers().length > 0 ) {
					project.setPink_book_item_numbers(CommonMethods.replaceEmptyByNullInSringArray(project.getPink_book_item_numbers()));
					if(arraySize < project.getPink_book_item_numbers().length) {
						arraySize = project.getPink_book_item_numbers().length;
					}
				}
				
				String pinkbookDeleteQry ="delete from project_pinkbook where project_id_fk = ? ";
				stmt = con.prepareStatement(pinkbookDeleteQry); 
				stmt.setString(1, project.getProject_id());
				stmt.executeUpdate();
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				String pinkbookQry ="INSERT into project_pinkbook (project_id_fk,financial_year_fk,pb_item_no)VALUES(?,?,?)";
				stmt = con.prepareStatement(pinkbookQry); 
				if(arraySize > 0) {
					for (int i = 0; i < arraySize; i++) {
						stmt.setString(1,project.getProject_id()); 
						stmt.setString(2,(project.getFinancial_years().length > 0)?project.getFinancial_years()[i]:null); 
						stmt.setString(3,(project.getPink_book_item_numbers().length > 0)?project.getPink_book_item_numbers()[i]:null);
						stmt.addBatch();
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
			String qry ="INSERT into project (project_id,project_name,plan_head_number,remarks,project_status,attachment,benefits)" + 
					 " VALUES(?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			
			stmt.setString(1,projectId); 
			stmt.setString(2,project.getProject_name()); 
			stmt.setString(3,project.getPlan_head_number()); 
			stmt.setString(4,project.getRemarks()); 
			stmt.setString(5,project.getProject_status());
			stmt.setString(6,project.getAttachment());
			stmt.setString(7,project.getBenefits());
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
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				String filesQry ="INSERT into project_files (attachment,project_id_fk)VALUES(?,?)";
				stmt = con.prepareStatement(filesQry); 
				if(!StringUtils.isEmpty(project.getProjectFile()) && project.getProjectFile().size() > 0) {
					List<MultipartFile> projectFiles = project.getProjectFile();
					for (MultipartFile multipartFile : projectFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants.PROJECT_FILE_SAVING_PATH;
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							stmt.setString(1,fileName); 
							stmt.setString(2,projectId); 
							stmt.addBatch();
						}
					}
					stmt.executeBatch();
				}	
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(project.getFinancial_years()) && project.getFinancial_years().length > 0 ) {
					project.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(project.getFinancial_years()));
					if(arraySize < project.getFinancial_years().length) {
						arraySize = project.getFinancial_years().length;
					}
				}
				if(!StringUtils.isEmpty(project.getPink_book_item_numbers()) && project.getPink_book_item_numbers().length > 0 ) {
					project.setPink_book_item_numbers(CommonMethods.replaceEmptyByNullInSringArray(project.getPink_book_item_numbers()));
					if(arraySize < project.getPink_book_item_numbers().length) {
						arraySize = project.getPink_book_item_numbers().length;
					}
				}
				
				String pinkbookQry ="INSERT into project_pinkbook (project_id_fk,financial_year_fk,pb_item_no)VALUES(?,?,?)";
				stmt = con.prepareStatement(pinkbookQry); 
				if(arraySize > 0) {
					for (int i = 0; i < arraySize; i++) {
						stmt.setString(1,projectId); 
						stmt.setString(2,(project.getFinancial_years().length > 0)?project.getFinancial_years()[i]:null); 
						stmt.setString(3,(project.getPink_book_item_numbers().length > 0)?project.getPink_book_item_numbers()[i]:null);
						stmt.addBatch();
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


	@Override
	public List<Project> getFileNames(String projectId) throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT id, file_name, project_id_fk, created_date, created_by FROM project_gallery where project_id_fk = ? ";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = projectId;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
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

	/**
	@Override
	public int getTotalRecords(Project obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from project where project_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (project_id like ? or project_name like ? or plan_head_number like ?"
						+ " or remarks like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}


	@Override
	public List<Project> getProjectsList(Project obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT project_id, project_name, plan_head_number, remarks, project_status, attachment FROM project where project_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (project_id like ? or project_name like ? or plan_head_number like ?"
						+ " or remarks like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY project_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Project>(Project.class));
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	*/
}
	
	
	
	
	
	
	
	


