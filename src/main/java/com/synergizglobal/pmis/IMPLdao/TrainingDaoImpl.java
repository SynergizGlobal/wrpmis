package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.TrainingDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Training;

@Repository
public class TrainingDaoImpl implements TrainingDao {

	public static Logger logger = Logger.getLogger(TrainingDaoImpl.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<Training> getTrainingList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select training_id,training_type_fk,training_category_fk,sum(ta.required_fk = ?) as nominated,sum(ta.participated_fk = ?) as attended,title,faculty_name,status_fk,t.designation, description, training_center, status_fk, t.remarks,"
					+ "FORMAT(start_time,'dd-MM-yyyy')  as date,FORMAT(min(start_time),'dd-MM-yyyy')  as start_time ,FORMAT(max(end_time),'dd-MM-yyyy') as end_time,(SELECT  time_format(timediff(time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(end_time))),'%H:%i'),time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(start_time))),'%H:%i')), '%H:%i')"
					+ " FROM training_session ts where ts.training_id_fk  = training_id group by training_id_fk) as hours "
					+ "from training t " + "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk  = training_id ";
			int arrSize = 2;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ?";
				arrSize++;
			}

			qry = qry + "  group by ts.training_id_fk order by ts.start_time desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.YES;
			pValues[i++] = CommonConstants.YES;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingTypesList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT training_type_fk from training  "
					+ "where training_type_fk is not null and training_type_fk <> '' ";
			int arrSize = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY training_type_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingCategorysList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT training_category_fk from training  "
					+ "where training_category_fk is not null and training_category_fk <> '' ";
			int arrSize = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY training_category_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingTitlesList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT distinct title from training  " + "where title is not null and title <> '' ";
			int arrSize = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ? ";
				arrSize++;
			}
			qry = qry + " order BY title ";
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getStatusList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT status_fk from training  " + "where status_fk is not null and status_fk <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " and training_type_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry = qry + " and training_category_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getStatusList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select status as status_fk from training_status ";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getCategoriesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select training_category as training_category_fk from training_category ";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingTypesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select training_type as training_type_fk from training_type ";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Training getTraining(Training obj) throws Exception {
		Training sObj = null;
		Connection con = null;
//		List<Training> depList = null;
		try {
			String qry = "select training_id, training_type_fk, training_category_fk, faculty_name, t.designation, title, description, training_center, status_fk, remarks, period_fk , conduct_by_fk ,provide_to_fk ,contract_short_name_fk  "
					+ "from training t " + "where training_id is not null";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				qry = qry + " and training_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				pValues[i++] = obj.getTraining_id();
			}
			sObj = (Training) jdbcTemplate.queryForObject(qry, pValues,
					new BeanPropertyRowMapper<Training>(Training.class));

			if (!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
				List<Training> objsList = null;
				String qryDetails = "select training_session_id,training_id_fk as training_id,session_no,"
						+ " FORMAT(start_time,'dd-MM-yyyy HH:mm:ss') AS start_time, FORMAT(end_time,'dd-MM-yyyy HH:mm:ss') AS end_time , Date as date , no_of_Participants, no_of_Absentees , remarks , file_name "
						+ "from training_session " + "where training_id_fk is not null and training_id_fk = ? ";

				objsList = jdbcTemplate.query(qryDetails, new Object[] { sObj.getTraining_id() },
						new BeanPropertyRowMapper<Training>(Training.class));
				sObj.setTrainingSessions(objsList);
			}
			if (!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
				for (Training session : sObj.getTrainingSessions()) {
					session.setTrainingAttendees(
							getDeptList(sObj.getTraining_id(), session.getTraining_session_id(), con));
				}
				/*
				 * for (Training sessionsNew : sObj.getTrainingSessions()) {
				 * sessionsNew.setTrainingNewList(getUserList(sObj.getTraining_id(),sessionsNew.
				 * getTraining_session_id(),CommonConstants.YES,con)); }
				 */
				if (!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
					for (Training session : sObj.getTrainingSessions()) {
						List<Training> objsList = null;
						String qry2 = "select id, training_id_fk, training_session_id_fk, attachment  from training_files where training_id_fk = ? and training_session_id_fk = ?";

						objsList = jdbcTemplate.query(qry2,
								new Object[] { sObj.getTraining_id(), session.getTraining_session_id() },
								new BeanPropertyRowMapper<Training>(Training.class));
						session.setTrainingFilesList(objsList);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return sObj;
	}

	private List<Training> getUserList(String training_id, String training_session_id, String yes, Connection con)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Training> objsList = new ArrayList<Training>();
		Training obj = null;
		try {
			con = dataSource.getConnection();

			String qry = "select training_attendees_id,d.department_name, training_id_fk, training_session_id_fk, u.department_fk, u.user_name as attendee,u.designation as trainee_designation, reporting_to_id_srfk as hod_user_id_fk,u.mobile_number as mobile_no, required_fk, participated_fk, email_id as email "
					+ "from training_attendees ta " + "left join [user] u on ta.user_id = u.user_id  "
					+ "LEFT JOIN department d on d.department = u.department_fk  "
					+ "where training_id_fk = ? and  training_session_id_fk = ? and is_new_user = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, training_id);
			stmt.setString(2, training_session_id);
			stmt.setString(3, yes);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				obj = new Training();
				obj.setTraining_attendees_id(resultSet.getString("training_attendees_id"));
				obj.setDepartment_name(resultSet.getString("d.department_name"));
				obj.setTraining_id_fk(resultSet.getString("training_id_fk"));
				obj.setTraining_session_id_fk(resultSet.getString("training_session_id_fk"));
				obj.setDepartment_fk(resultSet.getString("department_fk"));
				obj.setAttendee(resultSet.getString("attendee"));
				obj.setTrainee_designation(resultSet.getString("trainee_designation"));
				obj.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				obj.setMobile_no(resultSet.getString("mobile_no"));
				obj.setRequired_fk(resultSet.getString("required_fk"));
				obj.setParticipated_fk(resultSet.getString("participated_fk"));
				obj.setEmail(resultSet.getString("email"));
				obj.setDepartment_fk(obj.getDepartment_fk());
				obj.setHODsList(getUsersList(obj));
				objsList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}

	private List<Training> getDeptList(String training_id, String training_session_id, Connection con)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Training> objsList = new ArrayList<Training>();
		Training obj = null;
		try {
			con = dataSource.getConnection();
			String qry = "select distinct training_attendees_id,d.department_name, training_id_fk,reporting_to_id_srfk as hod_user_id_fk,mobile_number as mobile_no,"
					+ " training_session_id_fk, u.department_fk, u.user_name as attendee,u.designation as designation, required_fk, participated_fk,email_id as email "
					+ " from training_attendees ta " + "left join [user] u on ta.user_id = u.user_id  "
					+ "LEFT JOIN department d on d.department = u.department_fk  "
					+ "where training_id_fk = ? and  training_session_id_fk = ?  ";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, training_id);
			stmt.setString(2, training_session_id);

			resultSet = stmt.executeQuery();
			ArrayList<String> list = new ArrayList<String>();
			while (resultSet.next()) {
				obj = new Training();
				/*
				 * if(list.indexOf(resultSet.getString("attendee"))==-1) {
				 * list.add(resultSet.getString("attendee"));
				 */
				obj.setTraining_attendees_id(resultSet.getString("training_attendees_id"));
				obj.setDepartment_name(resultSet.getString("department_name"));
				obj.setTraining_id_fk(resultSet.getString("training_id_fk"));
				obj.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				obj.setMobile_no(resultSet.getString("mobile_no"));
				obj.setTraining_session_id_fk(resultSet.getString("training_session_id_fk"));
				obj.setDepartment_fk(resultSet.getString("department_fk"));
				obj.setAttendee(resultSet.getString("attendee"));
				obj.setDesignation(resultSet.getString("designation"));
				obj.setRequired_fk(resultSet.getString("required_fk"));
				obj.setParticipated_fk(resultSet.getString("participated_fk"));
				obj.setEmail(resultSet.getString("email"));
				obj.setDepartment_fk(obj.getDepartment_fk());
				obj.setAttendeesList(getAttendeesList(obj));
				objsList.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}

	@Override
	public List<Training> getDepartmentsList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select department as department_fk,department_name from department ";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getIssueCatogoriesList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select category from issue_category";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateTraining(Training obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		int y = 0;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		PreparedStatement insertStmt2 = null;
		PreparedStatement insertStmt3 = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String qry = "UPDATE training SET training_type_fk=:training_type_fk,training_category_fk=:training_category_fk,status_fk=:status_fk,"
					+ "period_fk=:period_fk ,conduct_by_fk=:conduct_by_fk,provide_to_fk=:provide_to_fk,contract_short_name_fk=:contract_short_name_fk,faculty_name=:faculty_name,"
					+ "designation=:designation,title=:title,description=:description,training_center=:training_center,remarks=:remarks "
					+ "WHERE training_id = :training_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = namedParamJdbcTemplate.update(qry, paramSource);
			if (count > 0) {
				flag = true;
			}
			if (flag) {
				String deleteQry = "DELETE from training_attendees where training_id_fk = :training_id";
				paramSource = new BeanPropertySqlParameterSource(obj);
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				String deleteQry1 = "DELETE from training_session where training_id_fk = :training_id";
				paramSource = new BeanPropertySqlParameterSource(obj);
				count = namedParamJdbcTemplate.update(deleteQry1, paramSource);
				String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time,No_of_Participants,No_of_Absentees,remarks,file_name) "
						+ "VALUES (?,?,?,?,?,?,?,?)";
				insertStmt = con.prepareStatement(insertQry1, Statement.RETURN_GENERATED_KEYS);

				int arraySize = 0;
				if (!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
					obj.setSession_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getSession_nos()));
					if (arraySize < obj.getSession_nos().length) {
						arraySize = obj.getSession_nos().length;
					}
				}

				if (!StringUtils.isEmpty(obj.getStart_times()) && obj.getStart_times().length > 0) {
					obj.setStart_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getStart_times()));
					if (arraySize < obj.getStart_times().length) {
						arraySize = obj.getStart_times().length;
					}
				}

				if (!StringUtils.isEmpty(obj.getEnd_times()) && obj.getEnd_times().length > 0) {
					obj.setEnd_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEnd_times()));
					if (arraySize < obj.getEnd_times().length) {
						arraySize = obj.getEnd_times().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getNum_participants()) && obj.getNum_participants().length > 0) {
					obj.setNum_participants(CommonMethods.replaceEmptyByNullInSringArray(obj.getNum_participants()));
					if (arraySize < obj.getNum_participants().length) {
						arraySize = obj.getNum_participants().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getNum_absentees()) && obj.getNum_absentees().length > 0) {
					obj.setNum_absentees(CommonMethods.replaceEmptyByNullInSringArray(obj.getNum_absentees()));
					if (arraySize < obj.getNum_absentees().length) {
						arraySize = obj.getNum_absentees().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
					obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
					if (arraySize < obj.getRemarkss().length) {
						arraySize = obj.getRemarkss().length;
					}
				}

				String trainingId = obj.getTraining_id();

				// create the directory path using the training ID
				String directoryPath = CommonConstants2.TRAINING_GALLERY_FILE_SAVING_PATH + "/" + trainingId + "/";

				// create the directory if it doesn't exist
				File directory = new File(directoryPath);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				// save the files in the directory
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = obj.getProjectGalleryFiles()[i];
					if (null != multipartFile && !multipartFile.isEmpty()) {
						String fileName = obj.getProjectGalleryFileNames()[i];
						FileUploads.singleFileSaving(multipartFile, directoryPath, fileName);
					}
				}
				for (int i = 0; i < arraySize; i++) {
					if (obj.getSession_nos().length > 0 && !StringUtils.isEmpty(obj.getSession_nos()[i])) {
						int p = 1;

						insertStmt.setString(p++, (obj.getTraining_id()));
						insertStmt.setString(p++, (obj.getSession_nos().length > 0) ? obj.getSession_nos()[i] : null);

						insertStmt.setString(p++, DateParser
								.parseDateTime((obj.getStart_times().length > 0) ? obj.getStart_times()[i] : null));
						insertStmt.setString(p++, DateParser
								.parseDateTime((obj.getEnd_times().length > 0) ? obj.getEnd_times()[i] : null));
						insertStmt.setString(p++,
								(obj.getNum_participants().length > 0) ? obj.getNum_participants()[i] : null);
						insertStmt.setString(p++,
								(obj.getNum_absentees().length > 0) ? obj.getNum_absentees()[i] : null);
						insertStmt.setString(p++, (obj.getRemarkss().length > 0) ? obj.getRemarkss()[i] : null);
						insertStmt.setString(p++,
								(obj.getProjectGalleryFileNames().length > 0) ? obj.getProjectGalleryFileNames()[i]
										: null);

					}

					int insertCount = insertStmt.executeUpdate();
					rs = insertStmt.getGeneratedKeys();
					if (insertCount > 0) {

						if (rs.next()) {
							String sessionId = rs.getString(1);
							obj.setTraining_session_id(sessionId);
						}
						String insertQry2 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,required_fk,participated_fk,user_id) "
								+ "VALUES (?,?,?,?,?)";
						insertStmt1 = con.prepareStatement(insertQry2);

						int a = r++;
						String user_id = null;

						if (obj.getNew_attendees() != null && obj.getNew_attendees().length > 0) {
							for (int j = 0; j < obj.getNew_attendees().length; j++) {
								int k = 1;

								String role_code = "SU";
								obj.setUser_type_fk("Training");
								obj.setUser_name(obj.getNew_attendees()[j]);
								obj.setDepartment_fk(obj.getDepartment_fks()[j]);
								obj.setEmail(obj.getEmails()[j + 1]);
								obj.setMobile_no(obj.getMobile_nos()[a + 1]);
								obj.setHod_user_id_fk(obj.getHod_user_id_fks()[j + 1]);
								user_id = getMaxUserId(role_code);
								obj.setDesignation(obj.getTrainee_designations()[j + 1]);
								obj.setUser_id(user_id);
								namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
								String addUserQry = "INSERT INTO [user]"
										+ "(user_id,user_name,designation,email_id,mobile_number,department_fk,reporting_to_id_srfk,user_type_fk) "
										+ "VALUES "
										+ "(:user_id,:user_name,:designation,:email,:mobile_no,:department_fk,:hod_user_id_fk,:user_type_fk)";
								paramSource = new BeanPropertySqlParameterSource(obj);
								int userCount = namedParamJdbcTemplate.update(addUserQry, paramSource);
								String insertQry21 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,required_fk,participated_fk,user_id) "
										+ "VALUES (?,?,?,?,?)";
								insertStmt3 = con.prepareStatement(insertQry21);
								insertStmt3.setString(k++, (obj.getTraining_id()));
								insertStmt3.setString(k++, (obj.getTraining_session_id()));
								String[] newrequiredFks = obj.getNew_required_fks();
								insertStmt3.setString(k++, newrequiredFks[a]);
								String[] newparticipatedFks = obj.getNew_participated_fks();
								insertStmt3.setString(k++, newparticipatedFks[a]);
								insertStmt3.setString(k++, user_id);
								insertStmt3.executeUpdate();
							}
						}
						if (!StringUtils.isEmpty(obj.getDepartment_fks()) && obj.getDepartment_fks().length > 0) {

							for (int j = 0; j < obj.getAttendees().length; j++) {
								int M = 1;
								user_id = obj.getAttendees()[j];
								insertStmt1.setString(M++, (obj.getTraining_id()));
								insertStmt1.setString(M++, (obj.getTraining_session_id()));
								String[] requiredFks = obj.getRequired_fks();
								insertStmt1.setString(M++, requiredFks[j]);
								String[] participatedFks = obj.getParticipated_fks();
								insertStmt1.setString(M++, participatedFks[j]);
								insertStmt1.setString(M++, user_id);
								insertStmt1.executeUpdate();

							}
						}

					}

				}
			}

			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
			formHistory.setUser(obj.getDesignation() + " - " + obj.getUser_name());
			formHistory.setModule_name_fk("Training");
			formHistory.setForm_name("Update Training");
			formHistory.setForm_action_type("Update");
			formHistory.setForm_details("Training " + obj.getTraining_id() + " Updated");
			// formHistory.setWork(obj.getWork_id_fk());
			// formHistory.setContract(obj.getContract_id_fk());

			boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			/********************************************************************************/

			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			// DBConnectionHandler.closeJDBCResoucrs(null, updateStmt, null);
			con.commit();
		} catch (

		Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, null, null);
		}
		return flag;
	}

	public String getMaxUserId(String role_code) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_id = null;

		try {
			connection = dataSource.getConnection();
			String maxIdQry = "SELECT MAX(CAST(SUBSTRING(user_id, CHARINDEX('_', user_id, CHARINDEX('_', user_id) + 1) + 1, LEN(user_id) - CHARINDEX('_', user_id, CHARINDEX('_', user_id) + 1)) AS INT)) AS maxId "
					+ "FROM [user] WHERE user_id LIKE 'PMIS_%'";

			stmt = connection.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();

			int maxId = 0;
			if (rs.next()) {
				maxId = rs.getInt("maxId");
			}

			user_id = "PMIS_" + role_code + "_" + String.format("%03d", maxId + 1);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, rs);
		}

		return user_id;
	}

	@Override
	public boolean addTraining(Training obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		int y = 0;
		PreparedStatement insertStmt = null;
		// PreparedStatement insertStmt1 = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String qry = "INSERT INTO training (training_type_fk,training_category_fk,faculty_name,designation,title,description,training_center,"
					+ "status_fk,remarks,period_fk,conduct_by_fk,provide_to_fk,contract_short_name_fk) " + "VALUES"
					+ "(:training_type_fk,:training_category_fk,:faculty_name,:designation,:title,:description,:training_center,:status_fk,:remarks,"
					+ ":period_fk,:conduct_by_fk,:provide_to_fk,:contract_short_name_fk)";
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
//		   

			int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
			String training_id = null;
			if (count > 0) {
				training_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setTraining_id(training_id);
				flag = true;
			}

			if (flag) {
				if (!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
					String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time,No_of_Participants,No_of_Absentees,remarks,file_name) "
							+ "VALUES (?,?,?,?,?,?,?,?)";
					insertStmt = con.prepareStatement(insertQry1, Statement.RETURN_GENERATED_KEYS);

					int arraySize = 0;
					if (!StringUtils.isEmpty(obj.getSession_nos()) && obj.getSession_nos().length > 0) {
						obj.setSession_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getSession_nos()));
						if (arraySize < obj.getSession_nos().length) {
							arraySize = obj.getSession_nos().length;
						}
					}

					if (!StringUtils.isEmpty(obj.getStart_times()) && obj.getStart_times().length > 0) {
						obj.setStart_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getStart_times()));
						if (arraySize < obj.getStart_times().length) {
							arraySize = obj.getStart_times().length;
						}
					}

					if (!StringUtils.isEmpty(obj.getEnd_times()) && obj.getEnd_times().length > 0) {
						obj.setEnd_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEnd_times()));
						if (arraySize < obj.getEnd_times().length) {
							arraySize = obj.getEnd_times().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getNum_participants()) && obj.getNum_participants().length > 0) {
						obj.setNum_participants(
								CommonMethods.replaceEmptyByNullInSringArray(obj.getNum_participants()));
						if (arraySize < obj.getNum_participants().length) {
							arraySize = obj.getNum_participants().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getNum_absentees()) && obj.getNum_absentees().length > 0) {
						obj.setNum_absentees(CommonMethods.replaceEmptyByNullInSringArray(obj.getNum_absentees()));
						if (arraySize < obj.getNum_absentees().length) {
							arraySize = obj.getNum_absentees().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
						obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
						if (arraySize < obj.getRemarkss().length) {
							arraySize = obj.getRemarkss().length;
						}
					}

					String trainingId = obj.getTraining_id();

					// create the directory path using the training ID
					String directoryPath = CommonConstants2.TRAINING_GALLERY_FILE_SAVING_PATH + "/" + trainingId + "/";

					// create the directory if it doesn't exist
					File directory = new File(directoryPath);
					if (!directory.exists()) {
						directory.mkdirs();
					}

					// save the files in the directory
					for (int i = 0; i < arraySize; i++) {
						MultipartFile multipartFile = obj.getProjectGalleryFiles()[i];
						if (null != multipartFile && !multipartFile.isEmpty()) {
							String fileName = obj.getProjectGalleryFileNames()[i];
							FileUploads.singleFileSaving(multipartFile, directoryPath, fileName);
						}
					}
					for (int i = 0; i < arraySize; i++) {
						if (obj.getSession_nos().length > 0 && !StringUtils.isEmpty(obj.getSession_nos()[i])) {
							int p = 1;

							insertStmt.setString(p++, (obj.getTraining_id()));
							insertStmt.setString(p++,
									(obj.getSession_nos().length > 0) ? obj.getSession_nos()[i] : null);

							insertStmt.setString(p++, DateParser
									.parseDateTime((obj.getStart_times().length > 0) ? obj.getStart_times()[i] : null));
							insertStmt.setString(p++, DateParser
									.parseDateTime((obj.getEnd_times().length > 0) ? obj.getEnd_times()[i] : null));
							insertStmt.setString(p++,
									(obj.getNum_participants().length > 0) ? obj.getNum_participants()[i] : null);
							insertStmt.setString(p++,
									(obj.getNum_absentees().length > 0) ? obj.getNum_absentees()[i] : null);
							insertStmt.setString(p++, (obj.getRemarkss().length > 0) ? obj.getRemarkss()[i] : null);
							insertStmt.setString(p++,
									(obj.getProjectGalleryFileNames().length > 0) ? obj.getProjectGalleryFileNames()[i]
											: null);
							insertStmt.executeUpdate();
						}
					}
					flag = true;
				}

				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation() + " - " + obj.getUser_name());
				formHistory.setModule_name_fk("Training");
				formHistory.setForm_name("Add Training");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Training " + obj.getTraining_id() + " Created");
				// formHistory.setWork(obj.getWork_id_fk());
				// formHistory.setContract(obj.getContract_id_fk());

				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/

			}
			if (flag) {
				con.commit();
			}
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}

		return flag;
	}

	@Override
	public List<Training> getUsersList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select user_id as hod_user_id_fk,designation,department_fk,user_name FROM [user] u where user_type_fk = ? ";

			int arrSize = 1;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_fk = ? ";
				arrSize++;
			}
			qry = qry + " ORDER BY case when u.designation='ED Civil' then 1 \r\n"
					+ "   when u.designation='CPM I' then 2 \r\n" + "   when u.designation='CPM II' then 3\r\n"
					+ "   when u.designation='CPM III' then 4 \r\n" + "   when u.designation='CPM V' then 5\r\n"
					+ "   when u.designation='CE' then 6 \r\n" + "   when u.designation='ED S&T' then 7 \r\n"
					+ "   when u.designation='CSTE' then 8\r\n" + "   when u.designation='GM Electrical' then 9\r\n"
					+ "   when u.designation='CEE Project I' then 10\r\n"
					+ "   when u.designation='CEE Project II' then 11\r\n"
					+ "   when u.designation='ED Finance & Planning' then 12\r\n"
					+ "   when u.designation='AGM Civil' then 13\r\n"
					+ "   when u.designation='DyCPM Civil' then 14\r\n"
					+ "   when u.designation='DyCPM III' then 15\r\n" + "   when u.designation='DyCPM V' then 16\r\n"
					+ "   when u.designation='DyCE EE' then 17\r\n"
					+ "   when u.designation='DyCE Badlapur' then 18\r\n"
					+ "   when u.designation='DyCPM Pune' then 19\r\n" + "   when u.designation='DyCE Proj' then 20\r\n"
					+ "   when u.designation='DyCEE I' then 21\r\n"
					+ "   when u.designation='DyCEE Projects' then 22\r\n"
					+ "   when u.designation='DyCEE PSI' then 23\r\n" + "   when u.designation='DyCSTE I' then 24\r\n"
					+ "   when u.designation='DyCSTE IT' then 25\r\n"
					+ "   when u.designation='DyCSTE Projects' then 26\r\n"
					+ "   when u.designation='XEN Consultant' then 27\r\n"
					+ "   when u.designation='AEN Adhoc' then 28\r\n"
					+ "   when u.designation='AEN Project' then 29\r\n"
					+ "   when u.designation='AEN P-Way' then 30\r\n" + "   when u.designation='AEN' then 31\r\n"
					+ "   when u.designation='Sr Manager Signal' then 32 \r\n"
					+ "   when u.designation='Manager Signal' then 33\r\n"
					+ "   when u.designation='Manager Civil' then 34 \r\n"
					+ "   when u.designation='Manager OHE' then 35\r\n"
					+ "   when u.designation='Manager GS' then 36\r\n"
					+ "   when u.designation='Manager Finance' then 37\r\n"
					+ "   when u.designation='Planning Manager' then 38\r\n"
					+ "   when u.designation='Manager Project' then 39\r\n"
					+ "   when u.designation='Manager' then 40 \r\n" + "   when u.designation='SSE' then 41\r\n"
					+ "   when u.designation='SSE Project' then 42\r\n"
					+ "   when u.designation='SSE Works' then 43\r\n" + "   when u.designation='SSE Drg' then 44\r\n"
					+ "   when u.designation='SSE BR' then 45\r\n" + "   when u.designation='SSE P-Way' then 46\r\n"
					+ "   when u.designation='SSE OHE' then 47\r\n" + "   when u.designation='SPE' then 48\r\n"
					+ "   when u.designation='PE' then 49\r\n" + "   when u.designation='JE' then 50\r\n"
					+ "   when u.designation='Demo-HOD-Elec' then 51\r\n"
					+ "   when u.designation='Demo-HOD-Engg' then 52\r\n"
					+ "   when u.designation='Demo-HOD-S&T' then 53\r\n" + "\r\n" + "   end asc";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
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
		String training_id = null;
		try {

			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "insert into  training  (training_type_fk , training_category_fk, faculty_name, designation,title,description,training_center,status_fk,remarks) "
					+ "VALUES(:training_type_fk,:training_category_fk,:faculty_name,:designation,:title,:description,:training_center,:status_fk,:remarks)";
			for (Training obj : trainingsList) {
				if (!StringUtils.isEmpty(obj.getTraining_id())) {
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					KeyHolder keyHolder = new GeneratedKeyHolder();
					count = namedParamJdbcTemplate.update(insertQry, paramSource, keyHolder);
					if (count > 0) {
						training_id = String.valueOf(keyHolder.getKey().intValue());
						obj.setTraining_id(training_id);
						insertCount++;
					}
					if (!StringUtils.isEmpty(obj.getTrainingSessions())) {
						String insertQry1 = "INSERT into  training_session (training_id_fk,session_no,start_time,end_time) "
								+ "VALUES (?,?,?,?)";
						con = dataSource.getConnection();
						insertStmt = con.prepareStatement(insertQry1, Statement.RETURN_GENERATED_KEYS);
						for (int i = 0; i < obj.getTrainingSessions().size(); i++) {
							String session_no = obj.getTrainingSessions().get(i).getSession_no();
							String start_date = obj.getTrainingSessions().get(i).getStart_time();
							String end_date = obj.getTrainingSessions().get(i).getEnd_time();
							String remarks = obj.getTrainingSessions().get(i).getRemarks();

							int k = 1;
							insertStmt.setString(k++, (obj.getTraining_id()));
							insertStmt.setString(k++, !StringUtils.isEmpty(session_no) ? session_no : null);
							insertStmt.setString(k++,
									DateParser.parseDateTime(!StringUtils.isEmpty(start_date) ? start_date : null));
							insertStmt.setString(k++,
									DateParser.parseDateTime(!StringUtils.isEmpty(end_date) ? end_date : null));
							insertStmt.setString(k++, !StringUtils.isEmpty(remarks) ? remarks : null);
							insertStmt.addBatch();
						}
						int[] insertCount1 = insertStmt.executeBatch();
						rs = insertStmt.getGeneratedKeys();
						if (rs.next()) {
							String revisionId = rs.getString(1);
							obj.setTraining_session_id(revisionId);
						}
						if (!StringUtils.isEmpty(obj.getTrainingAttendees())) {
							String insertQry2 = "INSERT into  training_attendees (training_id_fk,training_session_id_fk,required_fk,participated_fk,user_id) "
									+ "VALUES (?,?,?,?,?)";

							int[] counts1 = jdbcTemplate.batchUpdate(insertQry2, new BatchPreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps, int j) throws SQLException {
									try {

										String requried = obj.getTrainingAttendees().get(j).getRequired_fk();
										String participated = obj.getTrainingAttendees().get(j).getParticipated_fk();

										String userId = getUserIdByAttendee(
												obj.getTrainingAttendees().get(j).getAttendee());
										int p = 1;
										ps.setString(p++, (obj.getTraining_id()));
										ps.setString(p++, (obj.getTraining_session_id()));

										ps.setString(p++, !StringUtils.isEmpty(requried) ? requried : null);
										ps.setString(p++, !StringUtils.isEmpty(participated) ? participated : null);
										ps.setString(p++, !StringUtils.isEmpty(userId) ? userId : null);

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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}

		return insertCount;

	}

	private String getUserId(String hod) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_id = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String deptFkQry = "SELECT user_id FROM [user] where designation = ? ";
			stmt = con.prepareStatement(deptFkQry);
			int k = 1;
			stmt.setString(k++, hod);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user_id = rs.getString("user_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return user_id;
	}

	private String getUserIdByAttendee(String Attendee) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_id = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String deptFkQry = "SELECT user_id FROM [user] where user_name = ? ";
			stmt = con.prepareStatement(deptFkQry);
			int k = 1;
			stmt.setString(k++, Attendee);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user_id = rs.getString("user_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return user_id;
	}

	private String getDepartmentFk(String department) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String department_fk = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String deptFkQry = "SELECT department from department where department_name = ? ";
			stmt = con.prepareStatement(deptFkQry);
			int k = 1;
			stmt.setString(k++, department);
			rs = stmt.executeQuery();
			if (rs.next()) {
				department_fk = rs.getString("department");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return department_fk;
	}

	@Override
	public List<Training> getTrainingSessionsList(String id) throws Exception {
		List<Training> sessionsList = null;
		try {
			String qry = "select training_session_id,ts.training_id_fk as training_id,t.description,SUM(CASE WHEN ta.required_fk = 'Yes' THEN 1 ELSE 0 END) AS nominated, "
					+ "SUM(CASE WHEN ta.participated_fk = 'Yes' THEN 1 ELSE 0 END) AS attended, session_no,FORMAT(start_time,'dd-MM-yyyy')  as date, "
					+ "FORMAT(start_time,'dd-MM-yyyy HH:mm:ss') AS start_time, FORMAT(end_time,'dd-MM-yyyy HH:mm:ss') AS end_time from training_session ts "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk left join training t on ts.training_id_fk = t.training_id "
					+ " where ts.training_id_fk = ? group by session_no,training_session_id_fk,training_session_id,ts.training_id_fk, t.description,start_time,end_time ";
			Object[] pValues = new Object[] { id };
			sessionsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return sessionsList;
	}

	@Override
	public List<Training> getTrainingAttendeesList(String trainingId) throws Exception {
		List<Training> attendeesList = null;
		try {
			String qry = "select training_attendees_id,d.department_name,ts.session_no,u.designation,t.description,u.email_id as email, ta.training_id_fk as training_id, training_session_id_fk as training_session_id, u.department_fk"
					+ ", u.user_name as attendee,u.designation as trainee_designation, u1.designation as hod_user_id_fk,u.mobile_number as mobile_no, required_fk, participated_fk "
					+ "from training_attendees ta " + "left join [user] u on ta.user_id = u.user_id  "
					+ "left join [user] u1 on u.reporting_to_id_srfk = u1.user_id "
					+ "LEFT JOIN department d on d.department=u.department_fk "
					+ "left join training t on ta.training_id_fk = t.training_id "
					+ "LEFT JOIN training_session ts on ta.training_session_id_fk = ts.training_session_id "
					+ "where ta.training_id_fk = ? ";

			attendeesList = jdbcTemplate.query(qry, new Object[] { trainingId },
					new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return attendeesList;
	}

	@Override
	public List<Training> getAttendeesList(Training obj) throws Exception {
		List<Training> objsList = null;
		List<Training> objsList1 = null;
		try {
			int arrSize = 0;
			String qry = " SELECT user_id, user_name AS attendee, department_fk, designation\n"
					+ "FROM [user] u WHERE user_name NOT LIKE '%User%' AND user_id LIKE 'PMIS%' ";
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {

				qry = qry + " AND u.department_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY user_id, user_name, department_fk, designation , user_type_fk ";
			qry = qry + " ORDER BY CASE WHEN user_type_fk IS NULL THEN 1 ELSE 0 END, CASE user_type_fk \n"
					+ "WHEN 'Management' THEN '1' WHEN 'HOD' THEN '2' WHEN 'DYHOD' THEN '3'\n"
					+ "WHEN 'Officers ( Jr./Sr. Scale )' THEN '4' ELSE '5' END  ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
			objsList1 = getUsersListFrAttendees(obj);

			if (objsList1.size() > 0) {
				for (Training con : objsList1) {
					boolean found = false;
					for (Training con1 : objsList) {
						if ((con.getAttendee().equals(con1.getAttendee()))) {
							found = true;
							break;
						}
					}
					if (!found) {
						objsList.add(con);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	private List<Training> getUsersListFrAttendees(Training obj) throws Exception {
		List<Training> objsList1 = null;
		try {
			int arrSize = 0;
			String qry = "select  u.user_name as attendee,u.department_fk,u.designation from training_attendees ta "
					+ "left join [user] u on ta.user_id = u.user_id  "
					+ "LEFT JOIN department d on d.department = u.department_fk  " + "  where u.user_name <> '' ";

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY u.user_name ,department_fk,designation,user_type_fk ";
			qry = qry + " ORDER BY CASE WHEN user_type_fk IS NULL THEN 1 ELSE 0 END, CASE user_type_fk \n"
					+ "WHEN 'Management' THEN '1' WHEN 'HOD' THEN '2' WHEN 'DYHOD' THEN '3'\n"
					+ "WHEN 'Officers ( Jr./Sr. Scale )' THEN '4' ELSE '5' END  ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			objsList1 = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList1;
	}

	@Override
	public int getTotalRecords(Training obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = " SELECT COUNT(DISTINCT training_id) AS total_records \n"
					+ "FROM (SELECT training_id, training_type_fk, training_category_fk, SUM(CASE WHEN ta.required_fk = 'Yes' THEN 1 ELSE 0 END) AS nominated, \n"
					+ "SUM(CASE WHEN ta.participated_fk = 'Yes' THEN 1 ELSE 0 END) AS attended,title, faculty_name, \n"
					+ "t.designation,description, training_center, status_fk, t.remarks,\n"
					+ "FORMAT(start_time, 'dd-MM-yyyy') AS date, FORMAT(MIN(start_time), 'dd-MM-yyyy') AS start_time, \n"
					+ "FORMAT(MAX(end_time), 'dd-MM-yyyy') AS end_time,\n"
					+ "CONVERT(VARCHAR(5), DATEADD(SECOND, DATEDIFF(SECOND, MIN(start_time), MAX(end_time)), '00:00'), 108) AS total_hours\n"
					+ "FROM training t INNER JOIN training_session ts ON ts.training_id_fk = t.training_id\n"
					+ "LEFT JOIN training_attendees ta ON ts.training_session_id = ta.training_session_id_fk\n"
					+ "GROUP BY training_id, training_type_fk, training_category_fk, title, faculty_name, t.designation, \n"
					+ "description, training_center, status_fk, t.remarks,start_time) AS subquery WHERE 1=1 ";

			List<Object> pValues = new ArrayList<>();

			if (obj != null && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry += " AND training_type_fk = ? ";
				pValues.add(obj.getTraining_type_fk());
			}
			if (obj != null && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				qry += " AND training_category_fk = ? ";
				pValues.add(obj.getTraining_category_fk());
			}
			if (obj != null && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry += " AND status_fk = ? ";
				pValues.add(obj.getStatus_fk());
			}
			if (obj != null && !StringUtils.isEmpty(obj.getTitle())) {
				qry += " AND title = ? ";
				pValues.add(obj.getTitle());
			}
			if (!StringUtils.isEmpty(searchParameter)) {
				qry += " AND (training_id LIKE ? OR training_type_fk LIKE ? OR training_category_fk LIKE ? OR description LIKE ?"
						+ " OR faculty_name LIKE ? OR start_time LIKE ? OR end_time LIKE ? OR status_fk LIKE ?) ";
				for (int i = 0; i < 8; i++) {
					pValues.add("%" + searchParameter + "%");
				}
			}

			Object[] pValuesArray = pValues.toArray();
			for (Object value : pValuesArray) {

			}

			totalRecords = jdbcTemplate.queryForObject(qry, pValuesArray, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

//	@Override
//	public List<Training> getTrainingsList(Training obj, int startIndex, int offset, String searchParameter)
//			throws Exception {
//		List<Training> objsList = null;
//		try {
//			String qry = " SELECT * FROM (\n" + "  SELECT training_id, training_type_fk, training_category_fk,\n"
//					+ "    SUM(CASE WHEN ta.required_fk = 'Yes' THEN 1 ELSE 0 END) AS nominated, \n"
//					+ "    SUM(CASE WHEN ta.participated_fk = 'Yes' THEN 1 ELSE 0 END) AS attended, \n"
//					+ "    title, faculty_name, t.designation, description,\n"
//					+ "    training_center, status_fk, t.remarks, \n" + "    FORMAT(start_time,'dd-MM-yyyy') AS date,\n"
//					+ "    FORMAT(start_time,'dd-MM-yyyy HH:mm:ss') AS start_time,\n"
//					+ "    FORMAT(end_time,'dd-MM-yyyy HH:mm:ss') AS end_time, \n"
//					+ "    CONCAT(DATEDIFF(minute, start_time, end_time) / 60,'h ',DATEDIFF(minute, start_time, end_time) % 60,'m') AS hours,\n"
//					+ "    period_fk, no_of_Participants, no_of_Absentees \n" + "  FROM training t  \n"
//					+ "  LEFT JOIN training_session ts ON t.training_id = ts.training_id_fk \n"
//					+ "  LEFT JOIN training_attendees ta ON ts.training_session_id = ta.training_session_id_fk\n"
//					+ "  GROUP BY t.training_id, training_type_fk, training_category_fk, period_fk, \n"
//					+ "    title, faculty_name, t.designation, description, training_center, \n"
//					+ "    status_fk, t.remarks, start_time, end_time, no_of_Participants, no_of_Absentees\n"
//					+ ") subquery \n" + "ORDER BY start_time DESC;\n";
//			int arrSize = 0;
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
//				qry = qry + " and training_type_fk = ? ";
//				arrSize++;
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
//				qry = qry + " and training_category_fk = ? ";
//				arrSize++;
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
//				qry = qry + " and status_fk = ? ";
//				arrSize++;
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
//				qry = qry + " and title = ? ";
//				arrSize++;
//			}
//			if (!StringUtils.isEmpty(searchParameter)) {
//				qry = qry
//						+ " and (training_id like ? or training_type_fk like ? or training_category_fk like ? or description like ?"
//						+ " or faculty_name like ? or start_time like ? or end_time like ? or status_fk like ? )";
//				arrSize++;
//				arrSize++;
//				arrSize++;
//				arrSize++;
//				arrSize++;
//				arrSize++;
//				arrSize++;
//				arrSize++;
//			}
//			if (!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
//				qry = qry + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
//				arrSize++;
//				arrSize++;
//			}
//			Object[] pValues = new Object[arrSize];
//			int i = 0;
//
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
//				pValues[i++] = obj.getTraining_type_fk();
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
//				pValues[i++] = obj.getTraining_category_fk();
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
//				pValues[i++] = obj.getStatus_fk();
//			}
//			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
//				pValues[i++] = obj.getTitle();
//			}
//			if (!StringUtils.isEmpty(searchParameter)) {
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//				pValues[i++] = "%" + searchParameter + "%";
//			}
//			if (!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
//				pValues[i++] = startIndex;
//				pValues[i++] = offset;
//			}
//
//			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception(e);
//		}
//		return objsList;
//	}

	@Override
	public List<Training> getperiodicityList() throws Exception {
		List<Training> periodicityList = null;
		try {
			String qry = "select period as period_fk from training_periodicity";
			periodicityList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return periodicityList;

	}

	@Override
	public List<Training> getprovidedList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select provide_to as provide_to_fk from training_provided ";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> gettraining_ConductedList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select conduct_by as conduct_by_fk from training_Conducted";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		 return objsList;
	}

	@Override
	public List<Training> getcontract_short_nameList() throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "select contract_short_name as contract_short_name_fk  from contract_details";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Training>(Training.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getTrainingsList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry = "SELECT * FROM (SELECT training_id,ta.training_id_fk, training_type_fk, training_category_fk, SUM(CASE WHEN required_fk = 'Yes' THEN 1 ELSE 0 END) AS nominated, SUM(CASE WHEN participated_fk = 'Yes' THEN 1 ELSE 0 END) AS attended,title, faculty_name, t.designation, description,\n"
					+ "training_center, status_fk, t.remarks, FORMAT(start_time,'dd-MM-yyyy') AS date,\n"
					+ "FORMAT(start_time,'dd-MM-yyyy HH:mm:ss') AS start_time,\n"
					+ "FORMAT(end_time,'dd-MM-yyyy HH:mm:ss') AS end_time, CONCAT(DATEDIFF(minute, start_time, end_time) / 60,'h ',DATEDIFF(minute, start_time, end_time) % 60,'m') AS hours,\n"
					+ "period_fk, no_of_Participants, no_of_Absentees FROM training t  \n"
					+ "LEFT JOIN training_session ts ON t.training_id = ts.training_id_fk \n"
					+ "left join training_attendees ta on training_session_id = training_session_id_fk\n"
					+ "GROUP BY t.training_id,ta.training_id_fk, training_type_fk, training_category_fk, period_fk, title, faculty_name, t.designation, description, \n"
					+ "training_center, status_fk, t.remarks, start_time, end_time, no_of_Participants, no_of_Absentees) subquery ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				qry = qry + " WHERE training_type_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {

				qry = qry + (arrSize > 0 ? " AND " : " WHERE ");
				qry = qry + " training_category_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {

				qry = qry + (arrSize > 0 ? " AND " : " WHERE ");
				qry = qry + " title = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_type_fk())) {
				pValues[i++] = obj.getTraining_type_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_category_fk())) {
				pValues[i++] = obj.getTraining_category_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}

			qry = qry + " ORDER BY start_time DESC ";
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Training>(Training.class));

		} catch (Exception e) {
			throw new Exception(e);
		}

		return objsList;
	}

}
