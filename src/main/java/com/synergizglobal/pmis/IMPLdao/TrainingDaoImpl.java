package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.TrainingDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.Training;

@Repository
public class TrainingDaoImpl implements TrainingDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Training> getTrainingList(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,title,faculty_name,status_fk,"
					+ "DATE_FORMAT(ts.start_time,'%d-%m-%Y') as start_time ,DATE_FORMAT(ts.end_time,'%d-%m-%Y') as end_time,TIME_FORMAT(TIMEDIFF(start_time,end_time),'%H:%i') as hours from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ " where training_id is not null";
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
			
			qry = qry + "  group by training_id";
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
			String qry = "select training_id, training_type_fk, training_category_fk, faculty_name, designation, title, description, training_center, status_fk, remarks from training t "
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
						+" DATE_FORMAT(start_time,'%d-%m-%Y %H:%i:%s') AS start_time,remarks, DATE_FORMAT(end_time,'%d-%m-%Y %H:%i:%s') AS end_time "
						+ "from training_session "
						+"where training_id_fk is not null and training_id_fk = ? ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getTraining_id()}, new BeanPropertyRowMapper<Training>(Training.class));	
				sObj.setTrainingSessions(objsList); 
			}
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())) {
				for (Training session : sObj.getTrainingSessions()) {
					List<Training> objsList = null;
					String qryDetails = "select training_attendees_id, training_id_fk, training_session_id_fk, department_fk, attendee, mobile_no, required_fk, participated_fk\r\n" + 
							"from training_attendees "
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
			String qry ="select department as department_fk from department ";
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
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTraining_id() )) {
			
			String qryDetails = "select training_attendees_id, training_id_fk, training_session_id_fk, department_fk, attendee, mobile_no, required_fk, participated_fk "
					+ "from training_attendees "
					+"where training_id_fk is not null and training_id_fk = ? and training_session_id_fk = ? ";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getTraining_id(),obj.getTraining_session_id()}, new BeanPropertyRowMapper<Training>(Training.class));	
			
			}
		
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	

}
