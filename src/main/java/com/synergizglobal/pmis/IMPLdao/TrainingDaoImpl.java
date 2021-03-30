package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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

import com.synergizglobal.pmis.Idao.TrainingDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.ZonalRailway;

@Repository
public class TrainingDaoImpl implements TrainingDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Training> getTrainingList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,sum(ta.required_fk = ?) as nominated,sum(ta.participated_fk = ?) as attended,title,faculty_name,status_fk,t.designation, description, training_center, status_fk, t.remarks,"
					+ "DATE_FORMAT(start_time,'%d-%m-%Y')  as date,DATE_FORMAT(min(start_time),'%d-%m-%Y')  as start_time ,DATE_FORMAT(max(end_time),'%d-%m-%Y') as end_time,(SELECT  time_format(timediff(time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(end_time))),'%H:%i'),time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(start_time))),'%H:%i')), '%H:%i')"
					+ " FROM training_session ts where ts.training_id_fk  = training_id group by training_id_fk) as hours "
					+ "from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk  = training_id ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + "  group by ts.training_id_fk order by ts.start_time desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.YES;
			pValues[i++] = CommonConstants.YES;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingTypesList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT training_type_fk from training  " + 
					"where training_type_fk is not null and training_type_fk <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY training_type_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingCategorysList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT training_category_fk from training  " + 
					"where training_category_fk is not null and training_category_fk <> '' ";
			int arrSize = 0;
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY training_category_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;

			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getStatusList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT status_fk from training  " + 
					"where status_fk is not null and status_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getStatusList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select status as status_fk from training_status ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getCategoriesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_category as training_category_fk from training_category ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingTypesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_type as training_type_fk from training_type ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Training getTraining(Training obj) throws Exception {
		Training sObj =null;
		
		try {
			String qry = "select training_id, training_type_fk, training_category_fk, faculty_name, t.designation, title, description, training_center, status_fk, remarks "
					+ "from training t "
					+"where training_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				qry = qry + " and training_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				pValues[i++] = obj.getTraining_id();
			}
			sObj = (Training)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
				List<Training> objsList = null;
				String qryDetails = "select training_session_id,training_id_fk as training_id,session_no,"
						+" DATE_FORMAT(start_time,'%d-%m-%Y %H:%i:%s') AS start_time,remarks, DATE_FORMAT(end_time,'%d-%m-%Y %H:%i:%s') AS end_time,attachment "
						+ "from training_session "
						+"where training_id_fk is not null and training_id_fk = ? ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getTraining_id()}, new BeanPropertyRowMapper<Training>(Training.class));	
				sObj.setTrainingSessions(objsList); 
			}
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
				for (Training session : sObj.getTrainingSessions()) {
					List<Training> objsList = null;
					String qryDetails = "select training_attendees_id,d.department_name, training_id_fk, training_session_id_fk, ta.department_fk, attendee,ta.designation as trainee_designation, hod_user_id_fk,mobile_no, required_fk, participated_fk " + 
							"from training_attendees ta "
							+ "LEFT JOIN department d on ta.department_fk = d.department  "
							+"where training_id_fk = ? and  training_session_id_fk = ? ";
					
					objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getTraining_id(),session.getTraining_session_id()}, new BeanPropertyRowMapper<Training>(Training.class));	
					session.setTrainingAttendees(objsList); 
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
		return sObj;
	}

	@Override
	public List<Training> getDepartmentsList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select department as department_fk,department_name from department ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id() ))
			 * {
			 * 
			 * String qryDetails =
			 * "select training_attendees_id, training_id_fk, training_session_id_fk, department_fk, attendee, mobile_no, required_fk, participated_fk "
			 * + "from training_attendees "
			 * +"where training_id_fk is not null and training_id_fk = ? and training_session_id_fk = ? "
			 * ;
			 * 
			 * objsList = jdbcTemplate.query(qryDetails, new Object[]
			 * {obj.getTraining_id(),obj.getTraining_session_id()}, new
			 * BeanPropertyRowMapper<Training>(Training.class));
			 * 
			 * }
			 */
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getIssueCatogoriesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select category from issue_category";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean updateTraining(Training obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "UPDATE training SET training_type_fk=:training_type_fk,training_category_fk=:training_category_fk,status_fk=:status_fk,faculty_name=:faculty_name,"
					+ "designation=:designation,title=:title,description=:description,training_center=:training_center,remarks=:remarks "
					+ "WHERE training_id = :training_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				String deleteQry = "DELETE from training_attendees where training_id_fk = :training_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				String deleteQry1 = "DELETE from training_session where training_id_fk = :training_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry1, paramSource);
				
				String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time,"
						+"remarks,attachment) "
						+"VALUES (?,?,?,?,?,?)";
				insertStmt = con.prepareStatement(insertQry1,Statement.RETURN_GENERATED_KEYS);
				
				int	arraySize = 0;
				if(!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
					obj.setSession_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getSession_nos()));
					if(arraySize < obj.getSession_nos().length) {
						arraySize = obj.getSession_nos().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getStart_times()) && obj.getStart_times().length > 0) {
					obj.setStart_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getStart_times()));
					if(arraySize < obj.getStart_times().length) {
						arraySize = obj.getStart_times().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getEnd_times()) && obj.getEnd_times().length > 0) {
					obj.setEnd_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEnd_times()));
					if(arraySize < obj.getEnd_times().length) {
						arraySize = obj.getEnd_times().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
					obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
					if(arraySize < obj.getRemarkss().length) {
						arraySize = obj.getRemarkss().length;
					}
				}
				int	arraySize1 = 0;
				if(!StringUtils.isEmpty(obj.getDepartment_fks()) && obj.getDepartment_fks().length > 0) {
					obj.setDepartment_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getDepartment_fks()));
					if(arraySize1 < obj.getDepartment_fks().length) {
						arraySize1 = obj.getDepartment_fks().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAttendees()) && obj.getAttendees().length > 0) {
					obj.setAttendees(CommonMethods.replaceEmptyByNullInSringArray(obj.getAttendees()));
					if(arraySize1 < obj.getAttendees().length) {
						arraySize1 = obj.getAttendees().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getTrainee_designations()) && obj.getTrainee_designations().length > 0) {
					obj.setTrainee_designations(CommonMethods.replaceEmptyByNullInSringArray(obj.getTrainee_designations()));
					if(arraySize1 < obj.getTrainee_designations().length) {
						arraySize1 = obj.getTrainee_designations().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getMobile_nos()) && obj.getMobile_nos().length > 0) {
					obj.setMobile_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getMobile_nos()));
					if(arraySize1 < obj.getMobile_nos().length) {
						arraySize1 = obj.getMobile_nos().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getRequired_fks()) && obj.getRequired_fks().length > 0) {
					obj.setRequired_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getRequired_fks()));
					if(arraySize1 < obj.getRequired_fks().length) {
						arraySize1 = obj.getRequired_fks().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getParticipated_fks()) && obj.getParticipated_fks().length > 0) {
					obj.setParticipated_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getParticipated_fks()));
					if(arraySize1 < obj.getParticipated_fks().length) {
						arraySize1 = obj.getParticipated_fks().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getHod_user_id_fks()) && obj.getHod_user_id_fks().length > 0) {
					obj.setHod_user_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getHod_user_id_fks()));
					if(arraySize1 < obj.getHod_user_id_fks().length) {
						arraySize1 = obj.getHod_user_id_fks().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
					for (int i = 0; i < arraySize; i++) {
						 if( obj.getSession_nos().length > 0 && !StringUtils.isEmpty(obj.getSession_nos()[i])) {
						    int p = 1;
						    String saveDirectory = CommonConstants.TRAINING_SESSION_FILE_SAVING_PATH ;
						    String sessionFileName = null;
						    MultipartFile file = obj.getTrainingSessionFiles()[i];
							if (null != file && !file.isEmpty()){
								String fileName = file.getOriginalFilename();
								sessionFileName = fileName;
								FileUploads.singleFileSaving(file, saveDirectory, sessionFileName);
							} else {
								sessionFileName  = (obj.getTrainingSessionFileNames().length > 0)?obj.getTrainingSessionFileNames()[i]:null;
							} 
							
						    insertStmt.setString(p++,(obj.getTraining_id()));
							insertStmt.setString(p++,(obj.getSession_nos().length > 0)?obj.getSession_nos()[i]:null);
						    insertStmt.setString(p++,DateParser.parseDateTime((obj.getStart_times().length > 0)?obj.getStart_times()[i]:null));
						    insertStmt.setString(p++,DateParser.parseDateTime((obj.getEnd_times().length > 0)?obj.getEnd_times()[i]:null));
						    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
						    insertStmt.setString(p++,sessionFileName);
						    insertStmt.addBatch();
						 }
					int[] insertCount = insertStmt.executeBatch();
					rs = insertStmt.getGeneratedKeys();
					if(insertCount.length > 0) {
						
						String insertQry2 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,department_fk,attendee,designation,hod_user_id_fk,mobile_no,required_fk,"
								+"participated_fk) "
								+"VALUES (?,?,?,?,?,?,?,?,?)";
						insertStmt1 = con.prepareStatement(insertQry2);
						if (rs.next()) {
							String sessionId = rs.getString(1);
							obj.setTraining_session_id(sessionId);
						}
						if(!StringUtils.isEmpty(obj.getDepartment_fks()) && obj.getDepartment_fks().length > 0) {
						for (int j = 0; j < obj.getRowCounts()[i]; j++) {
							    int k = 1;
							    int a = r++; 
							    if( obj.getDepartment_fks().length > 0 && !StringUtils.isEmpty(obj.getDepartment_fks()[a])) {
								    insertStmt1.setString(k++,(obj.getTraining_id()));
								    insertStmt1.setString(k++,(obj.getTraining_session_id()));
									insertStmt1.setString(k++,(obj.getDepartment_fks().length > 0)?obj.getDepartment_fks()[a]:null);
									insertStmt1.setString(k++,(obj.getAttendees().length > 0)?obj.getAttendees()[a]:null);
									insertStmt1.setString(k++,(obj.getTrainee_designations().length > 0)?obj.getTrainee_designations()[a]:null);
									insertStmt1.setString(k++,(obj.getHod_user_id_fks().length > 0)?obj.getHod_user_id_fks()[a]:null);
									insertStmt1.setString(k++,(obj.getMobile_nos().length > 0)?obj.getMobile_nos()[a]:null);
									insertStmt1.setString(k++,(obj.getRequired_fks().length > 0)?obj.getRequired_fks()[a]:null);
								    insertStmt1.setString(k++,(obj.getParticipated_fks().length > 0)?obj.getParticipated_fks()[a]:null);
								    insertStmt1.addBatch();
								 }
							}
						
							int[] insertCount1 = insertStmt1.executeBatch();
							
							if(insertCount1.length > 0) {
								flag = true;
							}
					    }
					 }
					
				}
			}
			if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
				String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CURDATE())";				
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(issuesQry);
						int i = 1;
						ps.setString(i++, obj.getIssue_description());
						ps.setString(i++, obj.getIssue_description());
						ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
						ps.setString(i++, "Raised");
						ps.setString(i++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
						return ps;
					}
				});

			}
		
		}
		DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
		DBConnectionHandler.closeJDBCResoucrs(null, updateStmt, null);
		con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}
		return flag;
	}

	@Override
	public boolean addTraining(Training obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "INSERT INTO training (training_type_fk,training_category_fk,faculty_name,designation,title,description,training_center,"
					+ "status_fk) "
					+ "VALUES"
					+ "(:training_type_fk,:training_category_fk,:faculty_name,:designation,:title,:description,:training_center,:status_fk)";
			

			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
		    //return keyHolder.getKey().intValue();

			//BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			//int count = namedParamJdbcTemplate.update(qry, paramSource);		
		    String training_id = null;
			if(count > 0) {
				training_id = String.valueOf(keyHolder.getKey().intValue());
				 obj.setTraining_id(training_id);
				 flag = true;
			}
			
			if(flag) {
				if(!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
					String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time,"
							+"attachment,remarks) "
							+"VALUES (?,?,?,?,?,?)";
					insertStmt = con.prepareStatement(insertQry1,Statement.RETURN_GENERATED_KEYS);
					
					int	arraySize = 0;
					if(!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
						obj.setSession_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getSession_nos()));
						if(arraySize < obj.getSession_nos().length) {
							arraySize = obj.getSession_nos().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getStart_times()) && obj.getStart_times().length > 0) {
						obj.setStart_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getStart_times()));
						if(arraySize < obj.getStart_times().length) {
							arraySize = obj.getStart_times().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getEnd_times()) && obj.getEnd_times().length > 0) {
						obj.setEnd_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEnd_times()));
						if(arraySize < obj.getEnd_times().length) {
							arraySize = obj.getEnd_times().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
						obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
						if(arraySize < obj.getRemarkss().length) {
							arraySize = obj.getRemarkss().length;
						}
					}
					int	arraySize1 = 0;
					if(!StringUtils.isEmpty(obj.getDepartment_fks()) && obj.getDepartment_fks().length > 0) {
						obj.setDepartment_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getDepartment_fks()));
						if(arraySize1 < obj.getDepartment_fks().length) {
							arraySize1 = obj.getDepartment_fks().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getAttendees()) && obj.getAttendees().length > 0) {
						obj.setAttendees(CommonMethods.replaceEmptyByNullInSringArray(obj.getAttendees()));
						if(arraySize1 < obj.getAttendees().length) {
							arraySize1 = obj.getAttendees().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getTrainee_designations()) && obj.getTrainee_designations().length > 0) {
						obj.setTrainee_designations(CommonMethods.replaceEmptyByNullInSringArray(obj.getTrainee_designations()));
						if(arraySize1 < obj.getTrainee_designations().length) {
							arraySize1 = obj.getTrainee_designations().length;
						}
					}
					
					if(!StringUtils.isEmpty(obj.getMobile_nos()) && obj.getMobile_nos().length > 0) {
						obj.setMobile_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getMobile_nos()));
						if(arraySize1 < obj.getMobile_nos().length) {
							arraySize1 = obj.getMobile_nos().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getRequired_fks()) && obj.getRequired_fks().length > 0) {
						obj.setRequired_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getRequired_fks()));
						if(arraySize1 < obj.getRequired_fks().length) {
							arraySize1 = obj.getRequired_fks().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getParticipated_fks()) && obj.getParticipated_fks().length > 0) {
						obj.setParticipated_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getParticipated_fks()));
						if(arraySize1 < obj.getParticipated_fks().length) {
							arraySize1 = obj.getParticipated_fks().length;
						}
					}
					for (int i = 0; i < arraySize; i++) {
						 if( obj.getSession_nos().length > 0 && !StringUtils.isEmpty(obj.getSession_nos()[i])) {
						    int p = 1;
						    
						    String saveDirectory = CommonConstants.TRAINING_SESSION_FILE_SAVING_PATH ;
						    String sessionFileName = null;
						    MultipartFile file = obj.getTrainingSessionFiles()[i];
							if (null != file && !file.isEmpty()){
								String fileName = file.getOriginalFilename();
								sessionFileName = fileName;
								FileUploads.singleFileSaving(file, saveDirectory, sessionFileName);
							} else {
								sessionFileName  = (obj.getTrainingSessionFileNames().length > 0)?obj.getTrainingSessionFileNames()[i]:null;
							} 
							
						    insertStmt.setString(p++,(obj.getTraining_id()));
							insertStmt.setString(p++,(obj.getSession_nos().length > 0)?obj.getSession_nos()[i]:null);
						    insertStmt.setString(p++,DateParser.parseDateTime((obj.getStart_times().length > 0)?obj.getStart_times()[i]:null));
						    insertStmt.setString(p++,DateParser.parseDateTime((obj.getEnd_times().length > 0)?obj.getEnd_times()[i]:null));
						    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
						    insertStmt.setString(p++,sessionFileName);
						    insertStmt.addBatch();
						 }
						   
					int[] insertCount = insertStmt.executeBatch();
					rs = insertStmt.getGeneratedKeys();
					if(insertCount.length > 0) {
						
						String insertQry2 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,department_fk,attendee,designation,hod_user_id_fk,mobile_no,required_fk,"
								+"participated_fk) "
								+"VALUES (?,?,?,?,?,?,?,?,?)";
						insertStmt1 = con.prepareStatement(insertQry2);
						if (rs.next()) {
							String sessionId = rs.getString(1);
							obj.setTraining_session_id(sessionId);
						}
						
						if(!StringUtils.isEmpty(obj.getDepartment_fks()) && obj.getDepartment_fks().length > 0) {
							for (int j = 0; j < obj.getRowCounts()[i]; j++) {
								    int k = 1;
								    int a = r++; 
								    if( obj.getDepartment_fks().length > 0 && !StringUtils.isEmpty(obj.getDepartment_fks()[a])) {
							    	    insertStmt1.setString(k++,(obj.getTraining_id()));
									    insertStmt1.setString(k++,(obj.getTraining_session_id()));
										insertStmt1.setString(k++,(obj.getDepartment_fks().length > 0)?obj.getDepartment_fks()[a]:null);
										insertStmt1.setString(k++,(obj.getAttendees().length > 0)?obj.getAttendees()[a]:null);
										insertStmt1.setString(k++,(obj.getTrainee_designations().length > 0)?obj.getTrainee_designations()[a]:null);
										insertStmt1.setString(k++,(obj.getHod_user_id_fks().length > 0)?obj.getHod_user_id_fks()[a]:null);
										insertStmt1.setString(k++,(obj.getMobile_nos().length > 0)?obj.getMobile_nos()[a]:null);
										insertStmt1.setString(k++,(obj.getRequired_fks().length > 0)?obj.getRequired_fks()[a]:null);
									    insertStmt1.setString(k++,(obj.getParticipated_fks().length > 0)?obj.getParticipated_fks()[a]:null);
									    insertStmt1.addBatch();
								    }
							}
						}
							int[] insertCount1 = insertStmt1.executeBatch();
							
							if(insertCount1.length > 0) {
								flag = true;
							}
					    }
						
					}
				}
				if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
					String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CURDATE())";				
					jdbcTemplate.update(new PreparedStatementCreator() {
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps = connection.prepareStatement(issuesQry);
							int i = 1;
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
							ps.setString(i++, "Raised");
							ps.setString(i++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
							return ps;
						}
					});

				}
			}
			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt1, null);
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
			
			
		return flag;
	}

	@Override
	public List<Training> getUsersList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select user_id as hod_user_id_fk,designation,user_name from user where user_type_fk = ? ";
			
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));		
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int uploadTraining(List<Training> trainingsList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		boolean flag = false;
		int count = 0;
		int insertCount = 0;
		String training_id =null;
		try{
			
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String insertQry = "insert into  training  (training_type_fk , training_category_fk, faculty_name, designation,title,description,training_center,status_fk,remarks) "
					+ "VALUES(:training_type_fk,:training_category_fk,:faculty_name,:designation,:title,:description,:training_center,:status_fk,:remarks)";
			for (Training obj : trainingsList) {
				if(!StringUtils.isEmpty(obj.getTraining_id())) {
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);
				if(count > 0) {
				    training_id = String.valueOf(keyHolder.getKey().intValue());
					obj.setTraining_id(training_id);
					insertCount++;
				}
				if(!StringUtils.isEmpty(obj.getTrainingSessions())) {
						String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time,"
								+"remarks) "
								+"VALUES (?,?,?,?,?)";
						con = dataSource.getConnection();
						insertStmt = con.prepareStatement(insertQry1,Statement.RETURN_GENERATED_KEYS);
						for (int i=0; i<obj.getTrainingSessions().size(); i++) {
							String session_no = obj.getTrainingSessions().get(i).getSession_no();
							String start_date = obj.getTrainingSessions().get(i).getStart_time();
							String end_date = obj.getTrainingSessions().get(i).getEnd_time();
							String remarks = obj.getTrainingSessions().get(i).getRemarks();
							
							int k =1;
						 	insertStmt.setString(k++,(obj.getTraining_id()));
							insertStmt.setString(k++,!StringUtils.isEmpty(session_no)?session_no:null);
						    insertStmt.setString(k++,DateParser.parseDateTime(!StringUtils.isEmpty(start_date)?start_date:null));
						    insertStmt.setString(k++,DateParser.parseDateTime(!StringUtils.isEmpty(end_date)?end_date:null));
						    insertStmt.setString(k++,!StringUtils.isEmpty(remarks)?remarks:null);
						    insertStmt.addBatch();
						 }
						int[] insertCount1 = insertStmt.executeBatch();
						rs = insertStmt.getGeneratedKeys();
						if (rs.next()) {
							String revisionId = rs.getString(1);
							obj.setTraining_session_id(revisionId);
						}
					    if(!StringUtils.isEmpty(obj.getTrainingAttendees())) {
							String insertQry2 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,department_fk,attendee,designation,hod_user_id_fk,mobile_no,required_fk,"
									+"participated_fk) "
									+"VALUES (?,?,?,?,?,?,?,?,?)";
						  
							        int[] counts1 = jdbcTemplate.batchUpdate(insertQry2,
						            new BatchPreparedStatementSetter() {
										@Override
										public void setValues(PreparedStatement ps, int j) throws SQLException {
											try {										
												String department = obj.getTrainingAttendees().get(j).getDepartment_fk();
												String attende = obj.getTrainingAttendees().get(j).getAttendee();
												String designation = obj.getTrainingAttendees().get(j).getTrainee_designation();
												String hod = obj.getTrainingAttendees().get(j).getHod_user_id_fk();												
												String mobile_no = obj.getTrainingAttendees().get(j).getMobile_no();
												String requried = obj.getTrainingAttendees().get(j).getRequired_fk();
												String participated = obj.getTrainingAttendees().get(j).getParticipated_fk();
												
												String departmentFk = getDepartmentFk(department);
												String userId = getUserId(hod);
												int p = 1;
												ps.setString(p++,(obj.getTraining_id()));
												ps.setString(p++,(obj.getTraining_session_id()));
												ps.setString(p++,!StringUtils.isEmpty(departmentFk)?departmentFk:null);
												ps.setString(p++,!StringUtils.isEmpty(attende)?attende:null);
												ps.setString(p++,!StringUtils.isEmpty(designation)?designation:null);
												ps.setString(p++,!StringUtils.isEmpty(userId)?userId:null);
												ps.setString(p++,!StringUtils.isEmpty(mobile_no)?mobile_no:null);
												ps.setString(p++,!StringUtils.isEmpty(requried)?requried:null);
												ps.setString(p++,!StringUtils.isEmpty(participated)?participated:null);
											
											} catch (Exception e) {
												
											}
										}
										@Override
										public int getBatchSize() {
											return obj.getTrainingAttendees().size();
									}
							  });
						}
					}
			    }
		   }
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		
		return insertCount;
		
	}

	private String getUserId(String hod) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_id = null;
		 Connection con = null;
		try{
			con = dataSource.getConnection();
			String deptFkQry = "SELECT user_id from user where designation = ? ";
			stmt = con.prepareStatement(deptFkQry);
			int k =1;
			stmt.setString(k++, hod);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				user_id = rs.getString("user_id");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return user_id;
	}

	private String getDepartmentFk(String department) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String department_fk = null;
		 Connection con = null;
		try{
			con = dataSource.getConnection();
			String deptFkQry = "SELECT department from department where department_name = ? ";
			stmt = con.prepareStatement(deptFkQry);
			int k =1;
			stmt.setString(k++, department);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				department_fk = rs.getString("department");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return department_fk;
	}

	@Override
	public List<Training> getTrainingSessionsList(String id) throws Exception {
		List<Training> sessionsList = null;
		try {
			  String qry = "select training_session_id,ts.training_id_fk as training_id,sum(ta.required_fk = ?) as nominated,t.description,sum(ta.participated_fk = ?) as attended,session_no,DATE_FORMAT(start_time,'%d-%m-%Y')  as date,"
	  					+" time_format(start_time,'%h:%i:%s') as start_time,time_format(end_time,'%h:%i:%s') as end_time,ts.remarks as session_remarks "
	  					+ "from training_session ts "
	  					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
	  					+ "left join training t on ts.training_id_fk = t.training_id "
	  					+"where ts.training_id_fk = ? group by session_no,training_session_id_fk";
			Object[] pValues = new Object[] { CommonConstants.YES, CommonConstants.YES,id};
			
			sessionsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sessionsList;
	}

	@Override
	public List<Training> getTrainingAttendeesList(String trainingId) throws Exception {
		List<Training> attendeesList = null;
		try {
			String qry = "select training_attendees_id,d.department_name,ts.session_no,u.designation,t.description, ta.training_id_fk as training_id, training_session_id_fk as training_session_id, ta.department_fk, attendee,ta.designation as trainee_designation, hod_user_id_fk,mobile_no, required_fk, participated_fk " + 
					"from training_attendees ta " + 
					"LEFT JOIN department d on ta.department_fk = d.department  " + 
					"LEFT JOIN user u on ta.hod_user_id_fk = u.user_id  " + 
					"left join training t on ta.training_id_fk = t.training_id "+
					"LEFT JOIN training_session ts on ta.training_session_id_fk = ts.training_session_id " + 
					"where ta.training_id_fk = ? ";
			
			attendeesList = jdbcTemplate.query( qry, new Object[] {trainingId}, new BeanPropertyRowMapper<Training>(Training.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return attendeesList;
	}

	@Override
	public List<Training> getAttendeesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select  attendee from training_attendees where attendee <> '' " + 
					" UNION " + 
					"SELECT user_name FROM user where user_name NOT LIKE '%User%' and user_name  <> ''";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Training obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(distinct training_id) as total_records  from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk  = training_id ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (training_id like ? or training_type_fk like ? or training_category_fk like ? or title like ?"
						+ " or faculty_name like ? or start_time like ? or end_time like ? or status_fk like ?) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}

	@Override
	public List<Training> getTrainingsList(Training obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,sum(ta.required_fk = ?) as nominated,sum(ta.participated_fk = ?) as attended,title,faculty_name,status_fk,t.designation, description, training_center, status_fk, t.remarks,"
					+ "DATE_FORMAT(start_time,'%d-%m-%Y')  as date,DATE_FORMAT(min(start_time),'%d-%m-%Y')  as start_time ,DATE_FORMAT(max(end_time),'%d-%m-%Y') as end_time,(SELECT  time_format(timediff(time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(end_time))),'%H:%i'),time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(start_time))),'%H:%i')), '%H:%i')"
					+ " FROM training_session ts where ts.training_id_fk  = training_id group by training_id_fk) as hours "
					+ "from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk  = training_id ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (training_id like ? or training_type_fk like ? or training_category_fk like ? or title like ?"
						+ " or faculty_name like ? or start_time like ? or end_time like ? or status_fk like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + "group by ts.training_id_fk order by ts.start_time desc limit ?,?";
				arrSize++;
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.YES;
			pValues[i++] = CommonConstants.YES;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
