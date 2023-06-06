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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.WorkDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

@Repository
public class WorkDaoImpl implements WorkDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	DataSourceTransactionManager transactionManager;	
	@Autowired
	MessagesDao messagesDao;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<Work> getWorkList(Work obj) throws Exception{
		List<Work> objsList = null;
		try {
			String qry ="SELECT DISTINCT work_id,work_name,work_short_name,work_code,work_code,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost,"
					+ "(SELECT STRING_AGG(work_railway.railway_id_fk , ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS railway," 
					+ "(SELECT STRING_AGG(work_railway.executed_by_id_fk , ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS executed_by,"
					+ "completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" 
					+ ",w.remarks,FORMAT(w.projected_completion,'dd-MM-yyyy') AS projected_completion,"
					+ "FORMAT(w.projected_completion_date,'dd-MM-yyyy') AS projected_completion_date,work_status_fk,work_type_fk "
					+ "FROM work w "  
					+"LEFT JOIN project p ON w.project_id_fk = p.project_id ";
		
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		
		}catch(Exception e){ 
			throw new Exception(e);
		}
		
		return objsList;
    }
	
	
	@Override
	public List<Work> getWorkStatusList(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "SELECT execution_status as work_status_fk from execution_status where execution_status in('Not Started','In Progress','Completed')";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));
		}catch(Exception e){ 
			throw new Exception(e);
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
			String qry ="SELECT work_id,work_name,work_short_name,work_code,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost," 
					+ "completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" 
					+ ",w.remarks,FORMAT(w.projected_completion,'dd-MM-yyyy') AS projected_completion,"
					+ "FORMAT(w.projected_completion_date,'dd-MM-yyyy') AS projected_completion_date,work_status_fk,work_type_fk "
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
				work.setWork_code(resultSet.getString("work_code"));
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
				//work.setAttachment(resultSet.getString("attachment"));
				work.setProjected_completion(resultSet.getString("projected_completion"));
				work.setWork_status_fk(resultSet.getString("work_status_fk"));
				work.setWork_type_fk(resultSet.getString("work_type_fk"));
				work.setProjected_completion_date(resultSet.getString("projected_completion_date"));
				//work.setSanctioned_estimated_cost_unit(resultSet.getString("sanctioned_estimated_cost_unit"));
				//work.setSanctioned_completion_cost_unit(resultSet.getString("sanctioned_completion_cost_unit"));
				//work.setAnticipated_cost_unit(resultSet.getString("anticipated_cost_unit"));
				//work.setCompletion_cost_unit(resultSet.getString("completion_cost_unit"));
				work.setWorkRevisions(getWorkRevisions(work.getWork_id(),connection));	
				work.setRailwayAgencyList(getRailwayAgencyList(work.getWork_id(),connection));
				work.setExecutedByList(getExecutedByList(work.getWork_id(),connection));
				work.setWorkFilesList(getWorkFilesList(work.getWork_id(),connection));
				work.setWorkChainageFilesList(getWorkChainagesFilesList(work.getWork_id(),connection));
				
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
	

	private List<Work> getWorkChainagesFilesList(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> objsList = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT uploaded_file,format(uploaded_on,'d-MM-yyyy') as uploaded_on from work_chainages_upload_data  where work_chainage_id='"+work_id+"'";
			
		
			stmt = connection.prepareStatement(qry);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setUploaded_file(resultSet.getString("uploaded_file"));
				obj.setUploaded_on(resultSet.getString("uploaded_on"));
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
	
	
	private List<Work> getWorkFilesList(String work_id, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Work> objsList = new ArrayList<Work>();
		Work obj = null;
		try {
			String qry ="SELECT id as work_file_id, work_id_fk, attachment,work_file_type_fk from work_files  "
					+ "where work_id_fk = ?";
		
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, work_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Work();
				obj.setWork_file_id(resultSet.getString("work_file_id"));
				obj.setWork_id_fk(resultSet.getString("work_id_fk"));
				obj.setAttachment(resultSet.getString("attachment"));
				obj.setWork_file_type_fk(resultSet.getString("work_file_type_fk"));
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
				//obj.setLatest_revised_cost_unit(resultSet.getString("latest_revised_cost_unit"));
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
						 + "completion_cost = ?,remarks = ?,projected_completion = ?,work_short_name = ?,work_code = ?,projected_completion_date = ? ,work_status_fk = ?,work_type_fk = ? "
						 + "where work_id =?";
		
			stmt = con.prepareStatement(qry); 
			int p = 1;
			stmt.setString(p++,work.getWork_name());
			stmt.setString(p++,work.getProject_id_fk());
			stmt.setString(p++,work.getSanctioned_year_fk());
			stmt.setString(p++,work.getSanctioned_estimated_cost());
			stmt.setString(p++,work.getCompleteion_period_months());
			stmt.setString(p++,work.getSanctioned_completion_cost());
			stmt.setString(p++,work.getAnticipated_cost());
			stmt.setString(p++,work.getYear_of_completion());
			stmt.setString(p++,work.getCompletion_cost());
			stmt.setString(p++,work.getRemarks());
			//stmt.setString(p++,work.getAttachment());
			stmt.setString(p++,work.getProjected_completion());
			stmt.setString(p++,work.getWork_short_name());
			stmt.setString(p++,work.getWork_code().toUpperCase());
			stmt.setString(p++,work.getProjected_completion_date());
			stmt.setString(p++,work.getWork_status_fk());
			stmt.setString(p++,work.getWork_type_fk());
			//stmt.setString(p++,work.getSanctioned_estimated_cost_unit());
			//stmt.setString(p++,work.getSanctioned_completion_cost_unit());
			//stmt.setString(p++,work.getAnticipated_cost_unit());
			//stmt.setString(p++,work.getCompletion_cost_unit());
			stmt.setString(p++,work.getWork_id());
			int count = stmt.executeUpdate();
			if(count > 0){
				flag = true; 
			}
			if(stmt != null){stmt.close();}
			
			if(flag){
				String docFileName = null;
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
				
				/*****************************************************************************************************************/
				
				int arraySize = 0;
				if (!StringUtils.isEmpty(work.getWorkFileNames()) && work.getWorkFileNames().length > 0) {
					work.setWorkFileNames(CommonMethods.replaceEmptyByNullInSringArray(work.getWorkFileNames()));
					if (arraySize < work.getWorkFileNames().length) {
						arraySize = work.getWorkFileNames().length;
					}
				}
	
				if (!StringUtils.isEmpty(work.getWork_file_types()) && work.getWork_file_types().length > 0) {
					work.setWork_file_types(CommonMethods.replaceEmptyByNullInSringArray(work.getWork_file_types()));
					if (arraySize < work.getWork_file_types().length) {
						arraySize = work.getWork_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(work.getWork_file_ids()) && work.getWork_file_ids().length > 0) {
					work.setWork_file_ids(CommonMethods.replaceEmptyByNullInSringArray(work.getWork_file_ids()));
					if (arraySize < work.getWork_file_ids().length) {
						arraySize = work.getWork_file_ids().length;
					}
				}
				
				String file_ids = "";
				if (!StringUtils.isEmpty(work.getWork_file_ids()) && work.getWork_file_ids().length > 0) {
					for (int i = 0; i < arraySize; i++) {
						if(!StringUtils.isEmpty(work.getWork_file_ids()[i])) {
							file_ids = file_ids + work.getWork_file_ids()[i] + ",";
						}
					}
				}
				
				if (!StringUtils.isEmpty(file_ids)) {			
					file_ids = org.apache.commons.lang3.StringUtils.chop(file_ids);
					String deleteQry ="delete from work_files where id not in("+file_ids+") and work_id_fk = ? ";
					stmt = con.prepareStatement(deleteQry); 
					stmt.setString(1, work.getWork_id());
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				}
	
				String insertFileQry = "INSERT INTO work_files (attachment,work_id_fk,work_file_type_fk,created_date)VALUES(?,?,?,CURRENT_TIMESTAMP)";
				String updateFileQry = "UPDATE work_files set attachment=?,work_id_fk=?,work_file_type_fk=? WHERE id=?";
				
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = work.getWorkFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())
							|| !StringUtils.isEmpty(work.getWorkFileNames()[i])) {
						String saveDirectory = CommonConstants.WORK_FILE_SAVING_PATH + work.getWork_id() + File.separator;
						String fileName = work.getWorkFileNames()[i];
						String file_id = null;
						if (!StringUtils.isEmpty(file_ids)) {	
							file_id = work.getWork_file_ids()[i];
						}
						String file_type = null;
						if(!StringUtils.isEmpty(work.getWork_file_types()) && work.getWork_file_types().length > 0) {
							file_type = work.getWork_file_types()[i];
						}
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						if(!StringUtils.isEmpty(file_id)) {
							stmt = con.prepareStatement(updateFileQry); 
							stmt.setString(1,fileName); 
							stmt.setString(2,work.getWork_id()); 
							stmt.setString(3,file_type); 
							stmt.setString(4,file_id); 
							stmt.executeUpdate();
						}else {
							stmt = con.prepareStatement(insertFileQry); 
							stmt.setString(1,fileName); 
							stmt.setString(2,work.getWork_id()); 
							stmt.setString(3,file_type); 
							stmt.executeUpdate();
						}
					}
				}
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				/*****************************************************************************************************************************/
				String qryDelete = "delete from work_yearly_sanction where work_id_fk = ?";
				stmt = con.prepareStatement(qryDelete); 			
				stmt.setString(1,work.getWork_id());
				count = stmt.executeUpdate();			
				if(stmt != null){stmt.close();}	
				
				
				String qry4 = "INSERT into  work_yearly_sanction (financial_year,latest_revised_cost,"
						 +"year_of_revision,revision_number,work_id_fk) "
						 +"VALUES (?,?,?,?,?)";
				stmt = con.prepareStatement(qry4); 
				
				arraySize = 0;
				
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
				if(!StringUtils.isEmpty(work.getLatest_revised_cost_units()) && work.getLatest_revised_cost_units().length > 0) {
					work.setLatest_revised_cost_units(CommonMethods.replaceEmptyByNullInSringArray(work.getLatest_revised_cost_units()));
					if(arraySize < work.getLatest_revised_cost_units().length) {
						arraySize = work.getLatest_revised_cost_units().length;
					}
				}
				if(!StringUtils.isEmpty(work.getRevision_numbers()) && work.getRevision_numbers().length > 0) {
					work.setRevision_numbers(CommonMethods.replaceEmptyByNullInSringArray(work.getRevision_numbers()));
					if(arraySize < work.getRevision_numbers().length) {
						arraySize = work.getRevision_numbers().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					p = 1;
					if((work.getFinancial_years().length > 0 && !StringUtils.isEmpty(work.getFinancial_years()[i])) 
							|| (work.getLatest_revised_costs().length > 0 && !StringUtils.isEmpty(work.getLatest_revised_costs()[i])) 
							|| (work.getYear_of_revisions().length > 0 && !StringUtils.isEmpty(work.getYear_of_revisions()[i])) 
							|| (work.getRevision_numbers().length > 0 && !StringUtils.isEmpty(work.getRevision_numbers()[i])) ) {
						stmt.setString(p++,(work.getFinancial_years().length > 0)?work.getFinancial_years()[i]:null);
						stmt.setString(p++,(work.getLatest_revised_costs().length > 0)?work.getLatest_revised_costs()[i]:null);
						stmt.setString(p++,(work.getYear_of_revisions().length > 0)?work.getYear_of_revisions()[i]:null);						
						stmt.setString(p++,(work.getRevision_numbers().length > 0)?work.getRevision_numbers()[i]:null);
						stmt.setString(p++,work.getWork_id());
						//stmt.setString(p++,(work.getLatest_revised_cost_units().length > 0)?work.getLatest_revised_cost_units()[i]:null);
						stmt.addBatch();
					}
				}
				stmt.executeBatch();
				
				
				/********************************************************************************/
				if("Completed".equals(work.getWork_status_fk()) && !"Completed".equals(work.getExisting_work_status_fk())) {
					String qryUsers ="SELECT incharge_user_id_fk as user_id FROM module where module_name = Works ";
					List<String> users = jdbcTemplate.queryForList( qryUsers, String.class);	
					if(!StringUtils.isEmpty(users) && users.size() > 0) {
						NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
						String userIds[]  = new String[users.size()];	
						userIds = users.toArray(userIds);
						String messageType = "Work";
						String redirect_url = null;
						String message = "Work "+work.getWork_short_name()+" is completed";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,template);
					}
				}
				/********************************************************************************/
			
			}
			con.commit();
			if(flag) {
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(work.getUser_id());
				formHistory.setUser(work.getDesignation()+" - "+work.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Update Work");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Work "+work.getWork_id() + " Updated");
				formHistory.setWork_id_fk(work.getWork_id());
				formHistory.setWork_name(work.getWork_short_name());
				
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
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
						+ "remarks,projected_completion,work_short_name,work_code,projected_completion_date,work_type_fk )"+
						" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(qry); 
			int p = 1;
			stmt.setString(p++,workId ); 
			stmt.setString(p++,work.getWork_name()); 
			stmt.setString(p++,work.getProject_id_fk());
			stmt.setString(p++,work.getSanctioned_year_fk());
			stmt.setString(p++,work.getSanctioned_estimated_cost());
			stmt.setString(p++,work.getCompleteion_period_months());
			stmt.setString(p++,work.getSanctioned_completion_cost());
			stmt.setString(p++,work.getAnticipated_cost());
			stmt.setString(p++,work.getYear_of_completion());
			stmt.setString(p++,work.getCompletion_cost());
			stmt.setString(p++,work.getRemarks());
			//stmt.setString(p++,work.getAttachment());
			stmt.setString(p++,work.getProjected_completion());
			stmt.setString(p++,work.getWork_short_name());
			stmt.setString(p++,work.getWork_code().toUpperCase());
			stmt.setString(p++,work.getProjected_completion_date());
			stmt.setString(p++,work.getWork_type_fk());
			//stmt.setString(p++,work.getSanctioned_estimated_cost_unit());
			//stmt.setString(p++,work.getSanctioned_completion_cost_unit());
			//stmt.setString(p++,work.getAnticipated_cost_unit());
			//stmt.setString(p++,work.getCompletion_cost_unit());
			count = stmt.executeUpdate();
			if(count > 0){
				flag = true; 
			}
			if(stmt != null){stmt.close();}
			if(flag) {
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
				
				/*****************************************************************************************************************/
				
				int arraySize = 0;
				if (!StringUtils.isEmpty(work.getWorkFileNames()) && work.getWorkFileNames().length > 0) {
					work.setWorkFileNames(CommonMethods.replaceEmptyByNullInSringArray(work.getWorkFileNames()));
					if (arraySize < work.getWorkFileNames().length) {
						arraySize = work.getWorkFileNames().length;
					}
				}
	
				if (!StringUtils.isEmpty(work.getWork_file_types()) && work.getWork_file_types().length > 0) {
					work.setWork_file_types(CommonMethods.replaceEmptyByNullInSringArray(work.getWork_file_types()));
					if (arraySize < work.getWork_file_types().length) {
						arraySize = work.getWork_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(work.getWork_file_ids()) && work.getWork_file_ids().length > 0) {
					work.setWork_file_ids(CommonMethods.replaceEmptyByNullInSringArray(work.getWork_file_ids()));
					if (arraySize < work.getWork_file_ids().length) {
						arraySize = work.getWork_file_ids().length;
					}
				}
	
				String insertFileQry = "INSERT INTO work_files (attachment,work_id_fk,work_file_type_fk,created_date)VALUES(?,?,?,CURRENT_TIMESTAMP)";
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = work.getWorkFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())) {
						String saveDirectory = CommonConstants.WORK_FILE_SAVING_PATH + work.getWork_id() + File.separator;
						String fileName = work.getWorkFileNames()[i];
						String file_id = work.getWork_file_ids()[i];
						String file_type = work.getWork_file_types()[i];
						
						FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						
						stmt = con.prepareStatement(insertFileQry); 
						stmt.setString(1,fileName); 
						stmt.setString(2,workId); 
						stmt.setString(3,file_type); 
						stmt.executeUpdate();
					}
				}
				
				String qry4 = "INSERT into  work_yearly_sanction (financial_year,latest_revised_cost,"
							 +"year_of_revision,revision_number,work_id_fk) "
							 +"VALUES (?,?,?,?,?)";
				stmt = con.prepareStatement(qry4); 
				if(flag) {	
					arraySize = 0;
					if(!StringUtils.isEmpty(work.getFinancial_years()) && work.getFinancial_years().length > 0) {
						work.setFinancial_years(CommonMethods.replaceEmptyByNullInSringArray(work.getFinancial_years()));
						if(arraySize < work.getFinancial_years().length) {
							arraySize = work.getFinancial_years().length;
						}
					}
					if(!StringUtils.isEmpty(work.getLatest_revised_cost_units()) && work.getLatest_revised_cost_units().length > 0) {
						work.setLatest_revised_cost_units(CommonMethods.replaceEmptyByNullInSringArray(work.getLatest_revised_cost_units()));
						if(arraySize < work.getLatest_revised_cost_units().length) {
							arraySize = work.getLatest_revised_cost_units().length;
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
						p = 1;
						stmt.setString(p++,(work.getFinancial_years().length > 0)?work.getFinancial_years()[i]:null);
						stmt.setString(p++,(work.getLatest_revised_costs().length > 0)?work.getLatest_revised_costs()[i]:null);
						//stmt.setString(p++,(work.getLatest_revised_cost_units().length > 0)?work.getLatest_revised_cost_units()[i]:null);
						stmt.setString(p++,(work.getYear_of_revisions().length > 0)?work.getYear_of_revisions()[i]:null);						
						stmt.setString(p++,(work.getRevision_numbers().length > 0)?work.getRevision_numbers()[i]:null);
						stmt.setString(p++,workId);
						stmt.addBatch();
					}
					int[] c = stmt.executeBatch();
				}
				
				/********************************************************************************/
				
				String qryUsers ="SELECT incharge_user_id_fk as user_id FROM module where module_name = 'Works' ";
				List<String> users = jdbcTemplate.queryForList( qryUsers, String.class);	
				if(!StringUtils.isEmpty(users) && users.size() > 0) {
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
					String userIds[]  = new String[users.size()];	
					userIds = users.toArray(userIds);
					String messageType = "Work";
					String redirect_url = null;
					String message = "New work "+work.getWork_short_name()+" is adeed under "+work.getProject_name()+" on PMIS ";
					 
					Messages msgObj = new Messages();
					msgObj.setUser_ids(userIds);
					msgObj.setMessage_type(messageType);
					msgObj.setRedirect_url(redirect_url);
					msgObj.setMessage(message);
					messagesDao.addMessages(msgObj,template);
				}
				
			}
			con.commit();
			if(flag) {
				/********************************************************************************/
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(work.getUser_id());
				formHistory.setUser(work.getDesignation()+" - "+work.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Add Work");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Work "+workId + " Created");
				formHistory.setWork_id_fk(workId);
				formHistory.setWork_name(work.getWork_short_name());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
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
			String maxIdQry = "SELECT top 1 CONCAT(SUBSTRING(work_id, 1, 5),'',cast(MAX(SUBSTRING(work_id, 5, LEN(work_id)))+1 as varchar) ) AS maxId FROM work WHERE work_id LIKE ? GROUP BY WORK_ID ORDER BY CAST(SUBSTRING(CONCAT(SUBSTRING(work_id, 1, 5),'',cast(MAX(SUBSTRING(work_id, 5, LEN(work_id)))+1 as varchar) ), 6, 3) AS INT) desc ";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1, projectId+"%");
			rs = stmt.executeQuery();  
			if(rs.next()) {
				workId = rs.getString("maxId");
				if(StringUtils.isEmpty(workId)) {
					workId = projectId+"W01";
				}
			}
			else
			{
				workId = projectId+"W01";
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
			String qry = "select railway_id,railway_name from railway";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Railway>(Railway.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Railway> getExcecuteList()throws Exception{
		List<Railway> objsList = null;
		try {
			String qry = "SELECT DISTINCT executed_by_id_fk FROM work_railway WHERE executed_by_id_fk IS NOT NULL";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Railway>(Railway.class));
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getWorkRevisionsList(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="SELECT work_yearly_sanction_id, work_id_fk, wys.financial_year, wys.pink_book_item_number,wys.latest_revised_cost, wys.year_of_revision, wys.revision_number "
					+ "FROM work_yearly_sanction wys "  
					+"LEFT JOIN Work w ON wys.work_id_fk = w.work_id "
					+"LEFT JOIN project p ON w.project_id_fk = p.project_id ";
					//"LEFT JOIN money_unit m ON wys.latest_revised_cost_unit = m.value  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " where w.project_id_fk = ?";
				arrSize++;
			}	
		
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Work>(Work.class));	
		
		}catch(Exception e){ 
			throw new Exception(e);
		}
		
		return objsList;
	}


	@Override
	public List<Work> getWorkFileTypes() throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="SELECT work_file_type FROM work_file_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getWorksList(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "SELECT DISTINCT work_id,work_name,work_short_name,work_code,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost, " + 
					"(SELECT STRING_AGG(work_railway.railway_id_fk , ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS railway, " + 
					"(SELECT STRING_AGG(work_railway.executed_by_id_fk , ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS executed_by, " + 
					"completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost "  + 
					",w.remarks,FORMAT(w.projected_completion,'dd-MM-yyyy') AS projected_completion, " + 
					"FORMAT(w.projected_completion_date,'dd-MM-yyyy') AS projected_completion_date,work_status_fk,work_type_fk "
					+ "FROM work w  " + 
					"LEFT JOIN project p ON w.project_id_fk = p.project_id  "+
					//"LEFT JOIN money_unit m ON w.sanctioned_estimated_cost_unit = m.value  "+
					//"LEFT JOIN money_unit m1 ON w.sanctioned_completion_cost_unit = m1.value  "+
					//"LEFT JOIN money_unit m2 ON w.anticipated_cost_unit = m2.value  "+
					//"LEFT JOIN money_unit m3 ON w.completion_cost_unit = m3.value  "
					 "where work_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}	
		
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getWorktProjectsList(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "SELECT w.project_id_fk,p.project_name from work w " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			
			qry = qry + "GROUP BY project_id_fk,p.project_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Work>(Work.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getUnitsList() throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getWorkTypeList() throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "SELECT work_type as work_type_fk from work_type where work_type is not null and work_type <> '' ";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Work> getworkCodeList(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry = "SELECT work_code from work where work_code is not null and work_code <> '' and work_code = '"+obj.getWork_code()+"'";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public String[] uploadWorkChainagesData(List<Work> workChainagesList, Work work) throws Exception {
		int count = 0,row =1,sheet = 1,subRow = 1,cnt=0;
		String errMsg = null;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			con = dataSource.getConnection();
			
			String deleteWorkChainageQry = "delete from chainages_master where work_id = ?";
			stmt = con.prepareStatement(deleteWorkChainageQry); 
			stmt.setString(1,work.getWork_id());
			count = stmt.executeUpdate();
			if(stmt != null){stmt.close();}			
			
			
			String qry = "INSERT INTO chainages_master"
					+ "(srno,work_id, chainages, latitude, longitude) "
					+ "VALUES "
					+ "(:srno,:work_id, :chainages, :Latitude,:Longitude)";
			
			for (Work obj : workChainagesList) {
				
				String work_id = obj.getWork_id();
				row++;sheet = 1;
				if(!StringUtils.isEmpty(work_id)) 
				{
						obj.setWork_id(work_id);
						SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					    count = namedParamJdbcTemplate.update(qry, paramSource);
					    cnt=cnt+1;
					
				}
			}
			count=cnt;
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			errMsg = e.getMessage();
		}
		String arr[] = new String[5];
		arr[0] = errMsg;
	    arr[1] = String.valueOf(count);
	    arr[2] = String.valueOf(row);
	    arr[3] = String.valueOf(sheet);
	    arr[4] = String.valueOf(subRow);
		return arr;
	}

	@Override
	public boolean saveWorkChainagesDataUploadFile(Work obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String work_data_id = null;
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);	
			con = dataSource.getConnection();
			
			String deleteWorkChainageQry = "delete from work_chainages_upload_data where work_chainage_id = ?";
			stmt = con.prepareStatement(deleteWorkChainageQry); 
			stmt.setString(1,obj.getWork_id());
			int count1 = stmt.executeUpdate();
			if(stmt != null){stmt.close();}	
			
			
			String qry = "INSERT INTO work_chainages_upload_data"
					+ "(uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on,work_chainage_id) "
					+ "VALUES "
					+ "( :uploaded_file, :status, :remarks, :uploaded_by_user_id_fk,CURRENT_TIMESTAMP,:work_id)";	
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				work_data_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setWork_data_id(work_data_id);
				flag = true;
				
				MultipartFile file = obj.getWorkChainagesFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants.WORK_CHAINAGES_UPLOADED_FILE_SAVING_PATH ;
					String fileName = work_data_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					obj.setUploaded_file(fileName);
					String updateQry = "UPDATE work_chainages_upload_data set uploaded_file= :uploaded_file where work_chainage_id= :work_id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
					template.update(updateQry, paramSource1);
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	/**
	@Override
	public int getTotalRecords(Work obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from work w "
					+ "LEFT JOIN project p ON w.project_id_fk = p.project_id "
					+ "where work_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (project_id_fk like ? or p.project_name like ? or work_id like ?"
						+ " or work_name like ? or sanctioned_year_fk like ?  or w.remarks like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				//arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				//pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}


	@Override
	public List<Work> getWorksList(Work obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="SELECT DISTINCT work_id,work_name,work_short_name,project_id_fk,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost,"
					+ "(SELECT STRING_AGG(work_railway.railway_id_fk SEPARATOR ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS railway," 
					+ "(SELECT STRING_AGG(work_railway.executed_by_id_fk SEPARATOR ',') FROM work_railway WHERE (work_railway.work_id_fk = w.work_id)) AS executed_by,"
					+ "completeion_period_months,sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost" 
					+ ",w.remarks,w.attachment,FORMAT(w.projected_completion,'dd-MM-yyyy') AS projected_completion "
					+ "FROM work w "  
					+"LEFT JOIN project p ON w.project_id_fk = p.project_id where work_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (project_id_fk like ? or p.project_name like ? or work_id like ?"
						+ " or work_name like ? or sanctioned_year_fk like ?  or w.remarks like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				//arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY work_id ASC offset ? rows  fetch next ? rows only";
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
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				//pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Work>(Work.class));
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	*/
}