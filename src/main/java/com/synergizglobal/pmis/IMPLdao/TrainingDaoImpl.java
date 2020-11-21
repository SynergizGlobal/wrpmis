package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.TrainingDao;
import com.synergizglobal.pmis.model.Budget;
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
					+ "ts.start_time,ts.end_time,TIME_FORMAT(TIMEDIFF(start_time,end_time),'%H:%i') as hours from training t "
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
	

}
