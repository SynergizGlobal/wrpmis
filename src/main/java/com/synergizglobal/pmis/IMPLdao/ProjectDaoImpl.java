package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Year;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<Project> getProjectList(Project project) throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT project_id, project_name, plan_head_number, remarks, project_status, benefits ,"
					+ "(select financial_year_fk from project_pinkbook where project_id_fk = project_id order by project_pinkbook_id asc limit 1) as financial_year_fk,"
					+ "(select pb_item_no from project_pinkbook where project_id_fk = project_id order by project_pinkbook_id asc limit 1) as pb_item_no "
					+ "FROM project";
					
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e);
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
			String qry ="SELECT project_id,project_name,plan_head_number,remarks,project_status,benefits,"
					+ "(select financial_year_fk from project_pinkbook where project_id_fk = ? order by project_pinkbook_id asc limit 1) as financial_year_fk, "
					+ "(select pb_item_no from project_pinkbook where project_id_fk = ? order by project_pinkbook_id asc limit 1) as pb_item_no "
					+ "FROM project "
					+ "where project_id = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, projectId);
			stmt.setString(2, projectId);
			stmt.setString(3, projectId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				project = new Project();
				project.setProject_id(resultSet.getString("project_id"));
				project.setProject_name(resultSet.getString("project_name")); 
				project.setPlan_head_number(resultSet.getString("plan_head_number"));
				project.setRemarks(resultSet.getString("remarks"));
				project.setProject_status(resultSet.getString("project_status"));
				//project.setAttachment(resultSet.getString("attachment"));
				project.setBenefits(resultSet.getString("benefits"));
				
				if(!StringUtils.isEmpty(resultSet.getString("financial_year_fk"))) {
					project.setFinancial_year_fk(resultSet.getString("financial_year_fk"));
				}
				String pbItemNo = resultSet.getString("pb_item_no");
				
				if(!StringUtils.isEmpty(pbItemNo)) {
					String railway = null;
					String pb_item_no = null;
					if(pbItemNo.contains("-")) {
						String[] pb_item_no_temp = pbItemNo.split("-");
						if(pb_item_no_temp.length > 0) {
							railway = pb_item_no_temp[0].trim();
						}
						if(pb_item_no_temp.length > 1) {
							pb_item_no = pb_item_no_temp[1].trim();
						}
					}else if(pbItemNo.equals("CR") || pbItemNo.equals("WR")) {
						railway = pbItemNo;
					}
					
					if(!StringUtils.isEmpty(railway)) {
						project.setRailway(railway);
					}
					if(!StringUtils.isEmpty(pb_item_no)) {
						project.setPb_item_no(pb_item_no);
					}
				}
				
				
				project.setProjectGalleryFilesList(getProjectGalleryFiles(project.getProject_id(),connection));
				project.setProjectPinkBooks(getProjectPinkBooks(project.getProject_id(),connection));
				project.setProjectFilesList(getProjectFiles(project.getProject_id(),connection));
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
			String qry = "select id as project_file_id,project_id_fk,attachment,project_file_type_fk from project_files where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			while(rs.next()) {
				Project obj = new Project();
				obj.setProject_file_id(rs.getString("project_file_id"));
				obj.setProject_id_fk(rs.getString("project_id_fk"));
				obj.setAttachment(rs.getString("attachment"));
				obj.setProject_file_type_fk(rs.getString("project_file_type_fk"));
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
			String qry = "select project_pinkbook_id,project_id_fk,financial_year_fk,pb_item_no "
					+ "from project_pinkbook "
					+ "where project_id_fk = ? "
					+ "and project_pinkbook_id NOT IN (select min(project_pinkbook_id) from project_pinkbook where project_id_fk = ?)";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			stmt.setString(2,project_id);
			rs = stmt.executeQuery();  
			while(rs.next()) {
				Project obj = new Project();
				obj.setProject_pinkbook_id(rs.getString("project_pinkbook_id"));
				obj.setProject_id_fk(rs.getString("project_id_fk"));
				obj.setFinancial_year_fk(rs.getString("financial_year_fk"));
				obj.setPb_item_no(rs.getString("pb_item_no"));
				String railway = null;
				String pb_item_no = null;
				if(!StringUtils.isEmpty(obj.getPb_item_no())) {
					pb_item_no = obj.getPb_item_no();
					if(pb_item_no.contains("-")) {
						String[] pb_item_no_temp = pb_item_no.split("-");
						if(pb_item_no_temp.length > 0) {
							railway = pb_item_no_temp[0].trim();
						}
						if(pb_item_no_temp.length > 1) {
							pb_item_no = pb_item_no_temp[1].trim();
						}
					}else if(pb_item_no.equals("CR") || pb_item_no.equals("WR")) {
						railway = pb_item_no;
					}
				}
				obj.setRailway(railway);
				obj.setPb_item_no(pb_item_no);
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
			String qry = "select id,file_name,project_id_fk,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,created_by from project_gallery where project_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,project_id);
			rs = stmt.executeQuery();  
			while(rs.next()) {
				Project obj = new Project();
				obj.setId(rs.getString("id"));
				obj.setFile_name(rs.getString("file_name"));
				obj.setCreated_date(rs.getString("created_date"));
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


	@Override
	public boolean updateProject(Project project)throws Exception{
		int count = 0;
		boolean flag = false;
		try{
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			
			String qry = "update project set project_name = :project_name,plan_head_number = :plan_head_number,remarks = :remarks,project_status=:project_status,benefits = :benefits "
					 + "where project_id = :project_id";			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(project);
			count = template.update(qry, paramSource);
			if(count > 0 ){
				flag = true;
			}			
			if(flag) {	
				int arraySize = 0;
				if(!StringUtils.isEmpty(project.getProjectGalleryFileNames()) && project.getProjectGalleryFileNames().length > 0 ) {
					project.setProjectGalleryFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectGalleryFileNames()));
					if(arraySize < project.getProjectGalleryFileNames().length) {
						arraySize = project.getProjectGalleryFileNames().length;
					}
				}
				
				String deleteQry ="delete from project_gallery where project_id_fk = :project_id ";
				Project dObj = new Project();
				dObj.setProject_id(project.getProject_id());
				paramSource = new BeanPropertySqlParameterSource(dObj);
				template.update(deleteQry, paramSource);

				String galleryQry ="INSERT into project_gallery (file_name,project_id_fk,created_by,created_date)VALUES(:file_name,:project_id,:created_by,:created_date)";
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = project.getProjectGalleryFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())
							|| !StringUtils.isEmpty(project.getProjectGalleryFileNames()[i])) {
						String saveDirectory = CommonConstants2.PROJECT_GALLERY_FILE_SAVING_PATH + project.getProject_id() + File.separator;
						String fileName = project.getProjectGalleryFileNames()[i];
						String date = project.getCreated_dates()[i];
						project.setCreated_date(DateParser.parse(date));
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Project imgObj = new Project();
						imgObj.setFile_name(fileName);
						imgObj.setProject_id(project.getProject_id());
						imgObj.setCreated_by(project.getCreated_by());
						imgObj.setCreated_date(project.getCreated_date());
						paramSource = new BeanPropertySqlParameterSource(imgObj);
						template.update(galleryQry, paramSource);
					}
				}
				
				/************************************************************************************************************************************/
				
				
				
				arraySize = 0;
				if (!StringUtils.isEmpty(project.getProjectFileNames()) && project.getProjectFileNames().length > 0) {
					project.setProjectFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectFileNames()));
					if (arraySize < project.getProjectFileNames().length) {
						arraySize = project.getProjectFileNames().length;
					}
				}

				if (!StringUtils.isEmpty(project.getProject_file_types()) && project.getProject_file_types().length > 0) {
					project.setProject_file_types(CommonMethods.replaceEmptyByNullInSringArray(project.getProject_file_types()));
					if (arraySize < project.getProject_file_types().length) {
						arraySize = project.getProject_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(project.getProject_file_ids()) && project.getProject_file_ids().length > 0) {
					project.setProject_file_ids(CommonMethods.replaceEmptyByNullInSringArray(project.getProject_file_ids()));
					if (arraySize < project.getProject_file_ids().length) {
						arraySize = project.getProject_file_ids().length;
					}
				}
				
				String file_ids = "";
				for (int i = 0; i < arraySize; i++) {
					if(!StringUtils.isEmpty(project.getProject_file_ids()) && project.getProject_file_ids().length > 0 && !StringUtils.isEmpty(project.getProject_file_ids()[i])) {
						file_ids = file_ids + project.getProject_file_ids()[i] + ",";
					}
				}
				
				if (!StringUtils.isEmpty(file_ids)) {			
					file_ids = org.apache.commons.lang3.StringUtils.chop(file_ids);

					String deleteFilesQry = "delete from project_files where id not in("+file_ids+") and project_id_fk = :project_id";
					Project fileObj = new Project();
					fileObj.setProject_id(project.getProject_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					template.update(deleteFilesQry, paramSource);
				}

				String insertFileQry = "INSERT INTO project_files (attachment,project_id_fk,project_file_type_fk,created_date)VALUES(:attachment,:project_id,:project_file_type_fk,CURRENT_TIMESTAMP)";
				String updateFileQry = "UPDATE project_files set attachment=:attachment,project_id_fk=:project_id,project_file_type_fk=:project_file_type_fk WHERE id=:project_file_id";
				
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = project.getProjectFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())
							|| !StringUtils.isEmpty(project.getProjectFileNames()) && project.getProjectFileNames().length > 0 && !StringUtils.isEmpty(project.getProjectFileNames()[i])) {
						String saveDirectory = CommonConstants.PROJECT_FILE_SAVING_PATH + project.getProject_id() + File.separator;
						String fileName = project.getProjectFileNames()[i];
						String file_id = project.getProject_file_ids()[i];
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Project fObj = new Project();
						fObj.setAttachment(fileName);
						fObj.setProject_file_type_fk(project.getProject_file_types()[i]);
						fObj.setProject_file_id(file_id);
						fObj.setProject_id(project.getProject_id());
						paramSource = new BeanPropertySqlParameterSource(fObj);
						if(!StringUtils.isEmpty(file_id)) {
							template.update(updateFileQry, paramSource);
						}else {
							template.update(insertFileQry, paramSource);
						}
					}
				}
				
				/********************************************************************************************************************************************/
				
				arraySize = 0;
				if(!StringUtils.isEmpty(project.getFinancial_years()) && project.getFinancial_years().length > 0 ) {
					project.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(project.getFinancial_years()));
					if(arraySize < project.getFinancial_years().length) {
						arraySize = project.getFinancial_years().length;
					}
				}
				if(!StringUtils.isEmpty(project.getRailways()) && project.getRailways().length > 0 ) {
					project.setRailways(CommonMethods.replaceEmptyByNullInSringArray(project.getRailways()));
					if(arraySize < project.getRailways().length) {
						arraySize = project.getRailways().length;
					}
				}
				if(!StringUtils.isEmpty(project.getPink_book_item_numbers()) && project.getPink_book_item_numbers().length > 0 ) {
					project.setPink_book_item_numbers(CommonMethods.replaceEmptyByNullInSringArray(project.getPink_book_item_numbers()));
					if(arraySize < project.getPink_book_item_numbers().length) {
						arraySize = project.getPink_book_item_numbers().length;
					}
				}
				
				if (arraySize > 0) {
					String pinkbookDeleteQry ="delete from project_pinkbook where project_id_fk = :project_id ";
					Project obj = new Project();
					obj.setProject_id(project.getProject_id());
					paramSource = new BeanPropertySqlParameterSource(obj);
					template.update(pinkbookDeleteQry, paramSource);
				}
				
				String pinkbookQry ="INSERT into project_pinkbook (project_id_fk,financial_year_fk,pb_item_no)VALUES(:project_id,:financial_year_fk,:pb_item_no)";
				if(arraySize > 0) {
					for (int i = 0; i < arraySize; i++) {
						if(!StringUtils.isEmpty(project.getFinancial_years()[i]) || !StringUtils.isEmpty(project.getPink_book_item_numbers()[i])) {
							String pb_item_no = project.getPink_book_item_numbers()[i];
							if(!StringUtils.isEmpty(project.getRailways()[i]) && !StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getRailways()[i] + "-" + project.getPink_book_item_numbers()[i];
							}else if(!StringUtils.isEmpty(project.getRailways()[i]) && StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getRailways()[i] + "-";
							}else if(StringUtils.isEmpty(project.getRailways()[i]) && !StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getPink_book_item_numbers()[i];
							}
							
							Project obj = new Project();
							obj.setProject_id(project.getProject_id());
							obj.setFinancial_year_fk(project.getFinancial_years()[i]);
							obj.setPb_item_no(pb_item_no);
							paramSource = new BeanPropertySqlParameterSource(obj);
							template.update(pinkbookQry, paramSource);
						}
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(project.getCreated_by_user_id_fk());
				formHistory.setUser(project.getDesignation()+" - "+project.getUser_name());
				formHistory.setModule_name("Project");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Project "+project.getProject_name() + " updated");
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}	
		return flag;	
	}

	@Override
	public boolean addProject(Project project)throws Exception{
		int count = 0;
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String projectId = null;			
			String maxIdQry = "SELECT CONCAT(SUBSTRING(project_id, 1, LENGTH(project_id)-2),LPAD(MAX(SUBSTRING(project_id, 2, LENGTH(project_id)))+1,2,'0') ) AS project_id FROM project";
			Project pId = template.queryForObject(maxIdQry,new MapSqlParameterSource(), BeanPropertyRowMapper.newInstance(Project.class));
			if(StringUtils.isEmpty(pId)) {
				projectId = "P01";
			}else {
				projectId = pId.getProject_id();
			}
			project.setProject_id(projectId);
			String qry ="INSERT into project (project_id,project_name,plan_head_number,remarks,project_status,benefits)" + 
					 " VALUES(:project_id,:project_name,:plan_head_number,:remarks,:project_status,:benefits)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(project);
			count = template.update(qry, paramSource);
			if(count > 0 ){
				flag = true; 
			}
			if(flag) {
				int arraySize = 0;
				if(!StringUtils.isEmpty(project.getProjectGalleryFileNames()) && project.getProjectGalleryFileNames().length > 0 ) {
					project.setProjectGalleryFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectGalleryFileNames()));
					if(arraySize < project.getProjectGalleryFileNames().length) {
						arraySize = project.getProjectGalleryFileNames().length;
					}
				}
				
				if(!StringUtils.isEmpty(project.getCreated_dates()) && project.getCreated_dates().length > 0 ) {
					project.setCreated_dates(CommonMethods.replaceEmptyByNullInSringArray(project.getCreated_dates()));
					if(arraySize < project.getCreated_dates().length) {
						arraySize = project.getCreated_dates().length;
					}
				}

				String galleryQry ="INSERT into project_gallery (file_name,project_id_fk,created_by,created_date)VALUES(:file_name,:project_id,:created_by,:created_date)";
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = project.getProjectGalleryFiles()[i];
					if (null != multipartFile && !multipartFile.isEmpty()) {
						String saveDirectory = CommonConstants2.PROJECT_GALLERY_FILE_SAVING_PATH + project.getProject_id() + File.separator;
						String fileName = project.getProjectGalleryFileNames()[i];
						String date = project.getCreated_dates()[i];
						project.setCreated_date(DateParser.parse(date));
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Project imgObj = new Project();
						imgObj.setFile_name(fileName);
						imgObj.setProject_id(project.getProject_id());
						imgObj.setCreated_by(project.getCreated_by());
						imgObj.setCreated_date(project.getCreated_date());
						paramSource = new BeanPropertySqlParameterSource(imgObj);
						template.update(galleryQry, paramSource);
					}
				}
				
				/************************************************************************************************************************************/
				
				
				
				arraySize = 0;
				if (!StringUtils.isEmpty(project.getProjectFileNames()) && project.getProjectFileNames().length > 0) {
					project.setProjectFileNames(CommonMethods.replaceEmptyByNullInSringArray(project.getProjectFileNames()));
					if (arraySize < project.getProjectFileNames().length) {
						arraySize = project.getProjectFileNames().length;
					}
				}

				if (!StringUtils.isEmpty(project.getProject_file_types()) && project.getProject_file_types().length > 0) {
					project.setProject_file_types(CommonMethods.replaceEmptyByNullInSringArray(project.getProject_file_types()));
					if (arraySize < project.getProject_file_types().length) {
						arraySize = project.getProject_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(project.getProject_file_ids()) && project.getProject_file_ids().length > 0) {
					project.setProject_file_ids(CommonMethods.replaceEmptyByNullInSringArray(project.getProject_file_ids()));
					if (arraySize < project.getProject_file_ids().length) {
						arraySize = project.getProject_file_ids().length;
					}
				}

				String insertFileQry = "INSERT INTO project_files (attachment,project_id_fk,project_file_type_fk,created_date)VALUES(:attachment,:project_id,:project_file_type_fk,CURRENT_TIMESTAMP)";
				
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = project.getProjectFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())) {
						String saveDirectory = CommonConstants.PROJECT_FILE_SAVING_PATH + projectId + File.separator;
						String fileName = project.getProjectFileNames()[i];
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Project fileObj = new Project();
						fileObj.setAttachment(fileName);
						fileObj.setProject_file_type_fk((project.getProject_file_types().length > 0)?project.getProject_file_types()[i]:null);
						fileObj.setProject_id(projectId);
						paramSource = new BeanPropertySqlParameterSource(fileObj);
						template.update(insertFileQry, paramSource);
					}
				}
				
				/********************************************************************************************************************************************/
				
				
				arraySize = 0;
				if(!StringUtils.isEmpty(project.getFinancial_years()) && project.getFinancial_years().length > 0 ) {
					project.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(project.getFinancial_years()));
					if(arraySize < project.getFinancial_years().length) {
						arraySize = project.getFinancial_years().length;
					}
				}
				
				if(!StringUtils.isEmpty(project.getRailways()) && project.getRailways().length > 0 ) {
					project.setRailways(CommonMethods.replaceEmptyByNullInSringArray(project.getRailways()));
					if(arraySize < project.getRailways().length) {
						arraySize = project.getRailways().length;
					}
				}
				
				if(!StringUtils.isEmpty(project.getPink_book_item_numbers()) && project.getPink_book_item_numbers().length > 0 ) {
					project.setPink_book_item_numbers(CommonMethods.replaceEmptyByNullInSringArray(project.getPink_book_item_numbers()));
					if(arraySize < project.getPink_book_item_numbers().length) {
						arraySize = project.getPink_book_item_numbers().length;
					}
				}
				
				String pinkbookQry ="INSERT into project_pinkbook (project_id_fk,financial_year_fk,pb_item_no)VALUES(:project_id,:financial_year_fk,:pb_item_no)";
				if(arraySize > 0) {
					for (int i = 0; i < arraySize; i++) {
						if(!StringUtils.isEmpty(project.getFinancial_years()[i]) || !StringUtils.isEmpty(project.getPink_book_item_numbers()[i])) {
							String pb_item_no = project.getPink_book_item_numbers()[i];
							if(!StringUtils.isEmpty(project.getRailways()[i]) && !StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getRailways()[i] + "-" + project.getPink_book_item_numbers()[i];
							}else if(!StringUtils.isEmpty(project.getRailways()[i]) && StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getRailways()[i] + "-";
							}else if(StringUtils.isEmpty(project.getRailways()[i]) && !StringUtils.isEmpty(project.getPink_book_item_numbers()[i]) ) {
								pb_item_no = project.getPink_book_item_numbers()[i];
							}
							Project obj = new Project();
							obj.setProject_id(project.getProject_id());
							obj.setFinancial_year_fk(project.getFinancial_years()[i]);
							obj.setPb_item_no(pb_item_no);
							paramSource = new BeanPropertySqlParameterSource(obj);
							template.update(pinkbookQry, paramSource);
						}
					}
				}
				
				/********************************************************************************/
				
				String qryUsers ="SELECT incharge_user_id_fk as user_id FROM `module` where module_name = 'Works' ";
				List<String> users = jdbcTemplate.queryForList( qryUsers, String.class);	
				if(!StringUtils.isEmpty(users) && users.size() > 0) {
					String userIds[]  = new String[users.size()];	
					userIds = users.toArray(userIds);
					String messageType = "Project";
					String redirect_url = null;
					String message = "New project "+project.getProject_name()+" is added on PMIS";
					 
					Messages msgObj = new Messages();
					msgObj.setUser_ids(userIds);
					msgObj.setMessage_type(messageType);
					msgObj.setRedirect_url(redirect_url);
					msgObj.setMessage(message);
					messagesDao.addMessages(msgObj,template);
				}
				/********************************************************************************/
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(project.getCreated_by_user_id_fk());
				formHistory.setUser(project.getDesignation()+" - "+project.getUser_name());
				formHistory.setModule_name("Project");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Project "+project.getProject_name() + " created");
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
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
		throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Project> getProjectPinkBookList() throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT project_pinkbook_id, project_id_fk, financial_year_fk, pb_item_no FROM project_pinkbook pb "
					+ "LEFT JOIN project p on pb.project_id_fk = p.project_id order by project_id_fk ASC,financial_year_fk DESC,pb_item_no DESC";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Project> getProjectFileTypes() throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="SELECT project_file_type FROM project_file_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}
	*/
}
	
	
	
	
	
	
	
	


