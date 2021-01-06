package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DataGatheringsDao;
import com.synergizglobal.pmis.model.DataGathering;

@Repository
public class DataGatheringsDaoImpl implements DataGatheringsDao{


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<DataGathering> getDataGatheringsList(DataGathering obj) throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry ="select id,contract_id_fk,contract_name, project_priority_fk, dg.work_id_fk,w.work_name, target_date, start_date, finish_date, status_fk,dg.remarks"
					+ " from data_gathering dg " + 
					"left join work w on dg.work_id_fk = w.work_id "
					+"left join contract c on dg.contract_id_fk = c.contract_id where id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
				
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<DataGathering> getDataGatherigsStatusList(DataGathering obj) throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry = "SELECT status_fk from data_gathering where status_fk is not null and status_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<DataGathering> getDataGatherigsProjectPriorityList(DataGathering obj) throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry = "SELECT project_priority_fk from data_gathering where project_priority_fk is not null and project_priority_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY project_priority_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<DataGathering> getStatusList() throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry ="select status as status_fk from data_gathering_status ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<DataGathering> getPriorityList() throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry ="select project_priority as project_priority_fk from project_priority ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<DataGathering> getWorksList() throws Exception {
		List<DataGathering> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,work_name from work ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public DataGathering getDataGathering(DataGathering obj) throws Exception {
		DataGathering dataGathering = null;
		try {
			String qry = "select id, project_priority_fk, work_id_fk,w.work_name, target_date, start_date, finish_date, status_fk, dg.remarks from data_gathering dg " + 
						 "left join work w on dg.work_id_fk = w.work_id where id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getId())) {
				qry = qry + " and id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getId())) {
				pValues[i++] = obj.getId();
			}
			dataGathering = (DataGathering)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<DataGathering>(DataGathering.class));	
				
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return dataGathering;
	}

	@Override
	public boolean addDataGathering(DataGathering obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO data_gathering"
					+ "(project_priority_fk,work_id_fk, target_date, start_date, finish_date, status_fk, remarks)"
					+ "VALUES"
					+ "(:project_priority_fk,:work_id_fk,:target_date,:start_date,:finish_date,:status_fk,:remarks)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateDataGathering(DataGathering obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE data_gathering set "
					+ "project_priority_fk= :project_priority_fk,target_date= :target_date, start_date= :start_date, finish_date= :finish_date,status_fk= :status_fk,remarks= :remarks "
					+ "where id= :id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteDataGathering(DataGathering obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
