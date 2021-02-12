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
			String qry ="select user_id as hod_user_id_fk,designation,user_name from user ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Training>(Training.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Training> getScheduledTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,sum(ta.required_fk = ?) as nominated,sum(ta.participated_fk = ?) as attended,title,faculty_name,status_fk,designation, description, training_center, status_fk, t.remarks,"
					+ "DATE_FORMAT(start_time,'%d-%m-%Y')  as date,DATE_FORMAT(min(start_time),'%d-%m-%Y')  as start_time ,DATE_FORMAT(max(end_time),'%d-%m-%Y') as end_time,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(end_time) - TIME_TO_SEC(start_time))),'%H:%i') as hours "
					+ "from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk = training_id ";
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
			qry = qry + "  group by ts.training_id_fk";
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
	public List<Training> getEmployeeTrainings(Training obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Training> getCompletedTrainings(Training obj) throws Exception {
		List<Training> objsList = null;
		try {
			String qry ="select training_id,training_type_fk,training_category_fk,sum(ta.required_fk = ?) as nominated,sum(ta.participated_fk = ?) as attended,title,faculty_name,status_fk,designation, description, training_center, status_fk, t.remarks,"
					+ "DATE_FORMAT(start_time,'%d-%m-%Y')  as date,DATE_FORMAT(min(start_time),'%d-%m-%Y')  as start_time ,DATE_FORMAT(max(end_time),'%d-%m-%Y') as end_time,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(end_time) - TIME_TO_SEC(start_time))),'%H:%i') as hours "
					+ "from training t "
					+ "LEFT JOIN training_session ts on t.training_id = ts.training_id_fk "
					+ "left join training_attendees ta on training_session_id = training_session_id_fk "
					+ " where ts.training_id_fk = training_id ";
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
			qry = qry + "  group by ts.training_id_fk";
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
}
