package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.TrainingReportDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.ContractResource;
import com.synergizglobal.pmis.model.Training;
@Repository
public class TrainingReportDaoImpl implements TrainingReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<Training> getEmployeesInTraining(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select ta.user_id,department_fk,user_name ,designation  "
					+ "from training_attendees ta "
					+ "LEFT JOIN user u on ta.user_id = u.user_id where ta.user_id <> '' "
					+ "GROUP BY ta.user_id ORDER BY ta.user_id ASC";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	


	@Override
	public List<Training> getScheduledTrainingTitles(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,title,faculty_name,status_fk,designation, description, training_center, status_fk,remarks "
					+ "from training "
					+ "where training_id IS NOT NULL and title <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY title ORDER BY training_id ASC";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		    	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getCompletedTrainingTitles(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,title,faculty_name,status_fk,designation, description, training_center, status_fk,remarks "
					+ "from training "
					+ "where training_id IS NOT NULL";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY title ORDER BY training_id ASC";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		    	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getScheduledTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,title,faculty_name,status_fk,designation, description, training_center, status_fk,remarks "
					+ "from training "
					+ "where training_id IS NOT NULL";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				qry = qry + " and training_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ?";
				arrSize++;
			}
			qry = qry + " ORDER BY training_id ASC";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				pValues[i++] = obj.getTraining_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		    
		    for (Training tObj : objsList) {
		    	String sessionsQry ="select training_session_id,training_id_fk,session_no,DATE_FORMAT(start_time,'%d-%m-%Y') AS date,DATE_FORMAT(start_time,'%h:%i %p') AS start_time,DATE_FORMAT(end_time,'%h:%i %p') AS end_time,remarks,attachment "
						+ "from training_session "
						+ "where training_id_fk = ? ORDER BY training_session_id ASC";
		    	List<Training> sessonObjsList = jdbcTemplate.query( sessionsQry,new Object[] {tObj.getTraining_id()}, new BeanPropertyRowMapper<Training>(Training.class));
		    	
		    	if(!StringUtils.isEmpty(sessonObjsList) && !sessonObjsList.isEmpty()) {
			    	for (Training sObj : sessonObjsList) {
			    		String attendeesQry = "select training_attendees_id,d.department_name,u.user_name, training_id_fk, training_session_id_fk, u.department_fk,"
			    				+ " required_fk, participated_fk,ta.user_id,u1.designation as reporting_to,u.designation as trainee_designation " 
								+ "from training_attendees ta "
								+ "LEFT JOIN user u on ta.user_id = u.user_id "
								+ "LEFT JOIN user u1 on u.reporting_to_id_srfk = u1.user_id "
								+ "LEFT JOIN department d on u.department_fk = d.department "
								+ "where training_id_fk = ? and  training_session_id_fk = ? ";
						
			    		List<Training> attendeesObjsList = jdbcTemplate.query(attendeesQry, new Object[] {sObj.getTraining_id_fk(),sObj.getTraining_session_id()}, new BeanPropertyRowMapper<Training>(Training.class));	
						sObj.setTrainingAttendees(attendeesObjsList);
			    	}
		    	}
		    	tObj.setTrainingSessions(sessonObjsList);		    	
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getEmployeeTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String attendeesQry = "select training_attendees_id,d.department_name, ta.training_id_fk, ta.training_session_id_fk, u.department_fk, "
    				+ "required_fk, participated_fk,ta.user_id, (SELECT u1.designation FROM user u join user u1 on u.reporting_to_id_srfk = u1.user_id where u.user_id = ?) as reporting_to"
    				+ ",u.designation as reporting_to_designation,u.designation as trainee_designation,u.user_name,"
    				+ "(select count(*) from training_attendees where user_id= ? and required_fk = ?) as nominated,"
    				+ "(select count(*) from training_attendees where user_id= ? and participated_fk = ?) as attended,"
    				+ "training_center,session_no,title,description,training_category_fk as category,DATE_FORMAT(start_time,'%d-%m-%Y') AS date,status_fk " 
					+ "from training_attendees ta "
					+ "LEFT JOIN user u on ta.user_id = u.user_id "
					+ "LEFT JOIN department d on u.department_fk = d.department "
					+ "LEFT JOIN training t on ta.training_id_fk = t.training_id "
					+ "LEFT JOIN training_session ts on ta.training_session_id_fk = ts.training_session_id "
					+ "where ta.user_id = ? ORDER BY start_time desc";
			Object[] pValues = new Object[] {obj.getAttendee(),obj.getAttendee(),CommonConstants.YES,obj.getAttendee(),CommonConstants.YES,obj.getAttendee()};
    		objsList = jdbcTemplate.query(attendeesQry, pValues, new BeanPropertyRowMapper<Training>(Training.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Training> getCompletedTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,title,faculty_name,status_fk,designation, description, training_center, status_fk,remarks "
					+ "from training "
					+ "where training_id IS NOT NULL";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				qry = qry + " and training_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				qry = qry + " and title = ?";
				arrSize++;
			}
			qry = qry + " ORDER BY training_id ASC";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id())) {
				pValues[i++] = obj.getTraining_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTitle())) {
				pValues[i++] = obj.getTitle();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Training>(Training.class));
		    
		    for (Training tObj : objsList) {
		    	String sessionsQry ="select training_session_id,training_id_fk,session_no,DATE_FORMAT(start_time,'%d-%m-%Y') AS date,DATE_FORMAT(start_time,'%h:%i %p') AS start_time,DATE_FORMAT(end_time,'%h:%i %p') AS end_time,remarks,attachment "
						+ "from training_session "
						+ "where training_id_fk = ? ORDER BY training_session_id ASC";
		    	List<Training> sessonObjsList = jdbcTemplate.query( sessionsQry,new Object[] {tObj.getTraining_id()}, new BeanPropertyRowMapper<Training>(Training.class));
		    	
		    	if(!StringUtils.isEmpty(sessonObjsList) && !sessonObjsList.isEmpty()) {
			    	for (Training sObj : sessonObjsList) {
			    		String attendeesQry = "select training_attendees_id,d.department_name,u.user_name, training_id_fk, training_session_id_fk, u.department_fk,"
			    				+ " required_fk, participated_fk,ta.user_id,u1.designation as reporting_to,u.designation as trainee_designation,"
			    				+ "(select count(*) from training_attendees where required_fk = ? and training_id_fk = ? and training_session_id_fk = ?) as nominated,(select count(*) from training_attendees where participated_fk = ? and training_id_fk = ? and training_session_id_fk = ?) as attended " 
								+ "from training_attendees ta "
								+ "LEFT JOIN user u on ta.user_id = u.user_id "
								+ "LEFT JOIN user u1 on u.reporting_to_id_srfk = u1.user_id "
								+ "LEFT JOIN department d on u.department_fk = d.department "
								+ "where training_id_fk = ? and  training_session_id_fk = ? ";
						pValues = new Object[] {CommonConstants.YES,sObj.getTraining_id_fk(),sObj.getTraining_session_id(),CommonConstants.YES,sObj.getTraining_id_fk(),sObj.getTraining_session_id(),sObj.getTraining_id_fk(),sObj.getTraining_session_id()};
			    		List<Training> attendeesObjsList = jdbcTemplate.query(attendeesQry, pValues, new BeanPropertyRowMapper<Training>(Training.class));	
						sObj.setTrainingAttendees(attendeesObjsList);
						
						for (Training aObj : attendeesObjsList) {
							sObj.setNominated(aObj.getNominated());
							sObj.setAttended(aObj.getAttended());
							break;
						}
						
			    	}
		    	}
		    	tObj.setTrainingSessions(sessonObjsList);		    	
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}



	@Override
	public Training getEmployeeTrainingWithStatus(Training obj) throws Exception {
		List<Training> statusList = null;
		try {
			String statusQry = "select training_attendees_id,d.department_name, ta.training_id_fk, ta.training_session_id_fk, u.department_fk, "
    				+ "required_fk, participated_fk,ta.user_id, (SELECT u1.designation FROM user u join user u1 on u.reporting_to_id_srfk = u1.user_id where u.user_id = ?) as reporting_to"
    				+ ",u.designation as reporting_to_designation,u.designation as trainee_designation,u.user_name,"
    				+ "(select count(*) from training_attendees where user_id= ? and required_fk = ?) as nominated,"
    				+ "(select count(*) from training_attendees where user_id= ? and participated_fk = ?) as attended,"
    				+ "status_fk from training_attendees ta "
					+ "LEFT JOIN user u on ta.user_id = u.user_id "
					+ "LEFT JOIN department d on u.department_fk = d.department "
					+ "LEFT JOIN training t on ta.training_id_fk = t.training_id "
					+ "LEFT JOIN training_session ts on ta.training_session_id_fk = ts.training_session_id "
					+ "where ta.user_id = ?  and status_fk in ('Completed','Scheduled') group by status_fk  order by field(status_fk,'Scheduled','Completed') ";
			Object[] pValues = new Object[] {obj.getAttendee(),obj.getAttendee(),CommonConstants.YES,obj.getAttendee(),CommonConstants.YES,obj.getAttendee()};
			statusList = jdbcTemplate.query(statusQry, pValues, new BeanPropertyRowMapper<Training>(Training.class));	
			obj.setStatusList(statusList);
			for (Training dataList : statusList) {
				String attendeesQry = "select training_attendees_id, participated_fk,ta.training_id_fk, ta.training_session_id_fk,"
						+ "(select count(*) from training_attendees ta LEFT JOIN training t on ta.training_id_fk = t.training_id where user_id= ? and required_fk = ? and status_fk = ?) as nominated,"
						+ "(select count(*) from training_attendees ta LEFT JOIN training t on ta.training_id_fk = t.training_id where user_id= ? and participated_fk = ? and status_fk = ?) as attended,"
	    				+ "training_center,session_no,title,description,training_category_fk as category,DATE_FORMAT(start_time,'%d-%m-%Y') AS date,status_fk " 
						+ "from training_attendees ta "
						+ "LEFT JOIN user u on ta.user_id = u.user_id "
						+ "LEFT JOIN training t on ta.training_id_fk = t.training_id "
						+ "LEFT JOIN training_session ts on ta.training_session_id_fk = ts.training_session_id "
						+ "where ta.user_id = ?  and status_fk = ?  ORDER BY start_time desc";
				Object[] pValues1 = new Object[] {obj.getAttendee(),CommonConstants.YES,dataList.getStatus_fk(),obj.getAttendee(),CommonConstants.YES,dataList.getStatus_fk(),obj.getAttendee(),dataList.getStatus_fk()};
				List<Training>	employeeList = jdbcTemplate.query(attendeesQry, pValues1, new BeanPropertyRowMapper<Training>(Training.class));	
				dataList.setEmployeeReportList(employeeList);
			}
		
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return obj;
	}
}
