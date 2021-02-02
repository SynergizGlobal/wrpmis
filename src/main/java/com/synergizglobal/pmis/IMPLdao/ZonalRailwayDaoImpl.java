package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ZonalRailwayDao;
import com.synergizglobal.pmis.model.ZonalRailway;
@Repository
public class ZonalRailwayDaoImpl implements ZonalRailwayDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;


	@Override
	public List<ZonalRailway> getZonalRailwayList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry ="select contract_id, work_id_fk, execution_agency_railway_fk, source_of_funds, sanction_cost, latest_revised_cost, cumulative_expenditure_upto_last_finacial_year, actual_start,"
					+ " expected_finish, actual_finish, z.completion_cost, status_fk, as_on_date from zonal_railway_contracts z " + 
					"left join work w on z.work_id_fk = w.work_id  where contract_id is not null  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";  
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<ZonalRailway> getZonalRailwayWorksList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_name,w.work_short_name from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwayProjectsList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT project_id as project_id_fk,p.project_name from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where project_id is not null and project_id <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwayExecutionAgencyRailwayList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT execution_agency_railway_fk from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where execution_agency_railway_fk is not null and execution_agency_railway_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwaySourceOfFundsList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT source_of_funds from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where source_of_funds is not null and source_of_funds <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwayStatusList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT status_fk from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where status_fk is not null and status_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				qry = qry + " and z.execution_agency_railway_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				qry = qry + " and source_of_funds = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_railway_fk())) {
				pValues[i++] = obj.getExecution_agency_railway_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds())) {
				pValues[i++] = obj.getSource_of_funds();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
