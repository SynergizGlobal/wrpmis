package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ZonalRailwayDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Training;
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
	public List<ZonalRailway> getZonalsList(ZonalRailway obj, int startIndex, int offset,String searchParameter) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry ="select contract_id, work_id_fk,w.work_short_name,u.designation,sub_work,r.railway_name, execution_agency_railway_fk, source_of_funds_fk as source_of_funds, sanction_cost, latest_revised_cost, cast(((cumulative_expenditure_upto_last_finacial_year * cumilative_expenditure_units)/10000000) as CHAR) as cumulative_expenditure_upto_last_finacial_year, DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,"
					+ "DATE_FORMAT(expected_finish,'%d-%m-%Y') AS expected_finish,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish, z.completion_cost, status_fk, DATE_FORMAT(as_on_date,'%d-%m-%Y') AS as_on_date, responsible_person_user_fk from zonal_railway_contracts z " + 
					"left join work w on z.work_id_fk = w.work_id "+
					"left join railway r on z.execution_agency_railway_fk = r.railway_id "
					+"left join user u on z.responsible_person_user_fk = u.user_id  where contract_id is not null  ";
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (sub_work like ? or execution_agency_railway_fk like ? or source_of_funds_fk like ?"
						+ " or status_fk like ? or as_on_date like ? or expected_finish like ? or actual_finish like ? or cumulative_expenditure_upto_last_finacial_year like ?)";
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
				qry = qry + " ORDER BY status_fk IS NULL,FIELD(status_fk,'In Progress','Not Started','Completed',status_fk) limit ?,?";
				arrSize++;
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public int getTotalRecords(ZonalRailway obj, java.lang.String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from zonal_railway_contracts z " + 
					"left join work w on z.work_id_fk = w.work_id "+
					"left join railway r on z.execution_agency_railway_fk = r.railway_id "
					+"left join user u on z.responsible_person_user_fk = u.user_id  where contract_id is not null  ";
				
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (sub_work like ? or execution_agency_railway_fk like ? or source_of_funds_fk like ?"
						+ " or status_fk like ? or as_on_date like ? or expected_finish like ? or actual_finish like ? or cumulative_expenditure_upto_last_finacial_year like ?)";
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
			throw new Exception(e);
		}
		return totalRecords;
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry +" group by work_id_fk ";
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
			throw new Exception(e);
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry +" group by project_id ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwayExecutionAgencyRailwayList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT execution_agency_railway_fk,railway_name from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"left join railway r on z.execution_agency_railway_fk = r.railway_id "+
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry +" group by execution_agency_railway_fk ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwaySourceOfFundsList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "SELECT source_of_funds_fk as source_of_funds from zonal_railway_contracts z " + 
					"LEFT JOIN work w on z.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where source_of_funds_fk is not null and source_of_funds_fk <> '' ";
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry +" group by source_of_funds_fk ";
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
			throw new Exception(e);
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
				qry = qry + " and source_of_funds_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry +" group by status_fk  ORDER BY FIELD(status_fk,'In Progress','Not Started','Completed',status_fk) ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getProjectsListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getWorkListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = new ArrayList<ZonalRailway>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getRailwayListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select railway_id as execution_agency_railway_fk, railway_name from `railway` ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	} 

	@Override
	public List<ZonalRailway> getSourceOfFundListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select zonal_railway_funds as source_of_funds  from `zonal_railway_funds` ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getStatusListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select distinct(general_status) as status_fk from `general_status` ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public ZonalRailway getZonalRailway(ZonalRailway obj) throws Exception {
		ZonalRailway zonalRailway = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry ="select contract_id,max(cum_actual_expenditure) as cum_actual_expenditure,(select cum_actual_expenditure_units from zonal_railway_progress where cum_actual_expenditure = (SELECT max(cum_actual_expenditure) FROM zonal_railway_progress) ) as cum_actual_expenditure_units,work_id_fk,w.work_short_name,user_name, designation,project_id_fk,project_name, execution_agency_railway_fk,railway_id, responsible_person_user_fk,railway_name, source_of_funds_fk as source_of_funds,cast(sanction_cost as CHAR) as sanction_cost,cast(latest_revised_cost as CHAR) as latest_revised_cost, cast(cumulative_expenditure_upto_last_finacial_year as CHAR) as cumulative_expenditure_upto_last_finacial_year, DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,"
					+ "DATE_FORMAT(expected_finish,'%d-%m-%Y') AS  expected_finish,sub_work,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS  actual_finish, cast(z.completion_cost as CHAR) as completion_cost, status_fk, DATE_FORMAT(as_on_date,'%d-%m-%Y') AS as_on_date"
					+ ",sanction_cost_units,latest_revised_cost_units,cumilative_expenditure_units,completion_cost_units from zonal_railway_contracts z " + 
					"left join work w on z.work_id_fk = w.work_id "+
					"left join railway r on z.execution_agency_railway_fk = r.railway_id "+
					"left join user u on z.responsible_person_user_fk = u.user_id  "
					+"left join zonal_railway_progress zp on z.contract_id = zp.contract_id_fk  "
					+"left join project p on w.project_id_fk = p.project_id where contract_id is not null  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			zonalRailway = (ZonalRailway)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));	
			if(!StringUtils.isEmpty(zonalRailway) && !StringUtils.isEmpty(zonalRailway.getContract_id())) {
				List<ZonalRailway> objsList = null;
				String qryDetails = "select progress_id, contract_id_fk,  DATE_FORMAT(month,'%Y-%m') AS month, cast(cum_actual_expenditure_fy_cr as CHAR) as cum_actual_expenditure_fy_cr,(cum_planned_expenditure_per * 100) as cum_planned_expenditure_per,"
						+ " cast(cum_actual_expenditure as CHAR) as cum_actual_expenditure, (cum_actual_expenditure_per * 100) as cum_actual_expenditure_per, (cum_planned_physical_progress_per * 100) as cum_planned_physical_progress_per, "
						+ "(cum_actual_physical_progress_per * 100) as cum_actual_physical_progress_per, progress, issue, assistance_required, status "
						+ "from zonal_railway_progress " 
						+" where contract_id_fk = ? and status = ? order by month asc";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {zonalRailway.getContract_id(),CommonConstants.ACTIVE}, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));	
				for(ZonalRailway tObj : objsList  ) {
					if(!StringUtils.isEmpty(tObj.getCum_actual_expenditure_per())) {
						tObj.setCum_actual_expenditure_per(numberFormatter.format(Double.parseDouble(tObj.getCum_actual_expenditure_per())));
					}
					if(!StringUtils.isEmpty(tObj.getCum_actual_physical_progress_per())) {
						tObj.setCum_actual_physical_progress_per(numberFormatter.format(Double.parseDouble(tObj.getCum_actual_physical_progress_per())));
					}
					if(!StringUtils.isEmpty(tObj.getCum_planned_expenditure_per())) {
						tObj.setCum_planned_expenditure_per(numberFormatter.format(Double.parseDouble(tObj.getCum_planned_expenditure_per())));
					}
					if(!StringUtils.isEmpty(tObj.getCum_planned_physical_progress_per())) {
						tObj.setCum_planned_physical_progress_per(numberFormatter.format(Double.parseDouble(tObj.getCum_planned_physical_progress_per())));
					}
				}
				zonalRailway.setZonalRailway(objsList);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return zonalRailway;
	}

	@Override
	public boolean addZonalRailway(ZonalRailway obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt2 = null;
		int  count = 0;
		boolean flag = false;	
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insertQry = "INSERT INTO zonal_railway_contracts"
					+ "(contract_id, work_id_fk, execution_agency_railway_fk, source_of_funds_fk, sanction_cost,responsible_person_user_fk, latest_revised_cost, "
					+ "cumulative_expenditure_upto_last_finacial_year, actual_start, expected_finish, actual_finish, completion_cost, status_fk, as_on_date,sub_work,"
					+ "sanction_cost_units,latest_revised_cost_units,cumilative_expenditure_units,completion_cost_units)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			
			int q = 1;
			String contractId = obj.getWork_id_fk() + obj.getExecution_agency_railway_fk();
			String contarct_id = getContractIdByWorkId(contractId,con);
			obj.setContract_id(contarct_id);
			
			insertStmt.setString(q++,obj.getContract_id()); 
			insertStmt.setString(q++,obj.getWork_id_fk()); 
			insertStmt.setString(q++,obj.getExecution_agency_railway_fk()); 
			insertStmt.setString(q++,obj.getSource_of_funds()); 
			insertStmt.setString(q++,obj.getSanction_cost()); 
			insertStmt.setString(q++,obj.getResponsible_person_user_fk()); 
			insertStmt.setString(q++,obj.getLatest_revised_cost()); 
			insertStmt.setString(q++,obj.getCumulative_expenditure_upto_last_finacial_year()); 
			insertStmt.setString(q++,obj.getActual_start()); 
			insertStmt.setString(q++,obj.getExpected_finish()); 
			insertStmt.setString(q++,obj.getActual_finish()); 
			insertStmt.setString(q++,obj.getCompletion_cost()); 
			insertStmt.setString(q++,obj.getStatus_fk()); 
			insertStmt.setString(q++,obj.getAs_on_date()); 
			insertStmt.setString(q++,obj.getSub_work());
			insertStmt.setString(q++,obj.getSanction_cost_units()); 
			insertStmt.setString(q++,obj.getLatest_revised_cost_units()); 
			insertStmt.setString(q++,obj.getCumilative_expenditure_units()); 
			insertStmt.setString(q++,obj.getCompletion_cost_units());
			
			count = insertStmt.executeUpdate();
			if(count > 0) {
				 flag = true;
			}
			if(flag) {
				String insertQry2 = "INSERT into  zonal_railway_progress "
						+ " (contract_id_fk, month, cum_planned_expenditure_per, cum_actual_expenditure, "
						+ "cum_actual_expenditure_per, cum_planned_physical_progress_per, cum_actual_physical_progress_per, progress, issue, assistance_required, status,cum_actual_expenditure_units) "
						+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				insertStmt2 = con.prepareStatement(insertQry2);
				int arraySize = 0;
				
				if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
					obj.setMonths(CommonMethods.replaceEmptyByNullInSringArray(obj.getMonths()));
					if(arraySize < obj.getMonths().length) {
						arraySize = obj.getMonths().length;
					}
				}
				
				if(!StringUtils.isEmpty(obj.getCum_planned_expenditure_pers()) && obj.getCum_planned_expenditure_pers().length > 0) {
					obj.setCum_planned_expenditure_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_planned_expenditure_pers()));
					if(arraySize < obj.getCum_planned_expenditure_pers().length) {
						arraySize = obj.getCum_planned_expenditure_pers().length;
					}
				}
				
				if(!StringUtils.isEmpty(obj.getCum_actual_expenditure_pers()) && obj.getCum_actual_expenditure_pers().length > 0) {
					obj.setCum_actual_expenditure_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_actual_expenditure_pers()));
					if(arraySize < obj.getCum_actual_expenditure_pers().length) {
						arraySize = obj.getCum_actual_expenditure_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getCum_planned_physical_progress_pers()) && obj.getCum_planned_physical_progress_pers().length > 0) {
					obj.setCum_planned_physical_progress_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_planned_physical_progress_pers()));
					if(arraySize < obj.getCum_planned_physical_progress_pers().length) {
						arraySize = obj.getCum_planned_physical_progress_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getCum_actual_physical_progress_pers()) && obj.getCum_actual_physical_progress_pers().length > 0) {
					obj.setCum_actual_physical_progress_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_actual_physical_progress_pers()));
					if(arraySize < obj.getCum_actual_physical_progress_pers().length) {
						arraySize = obj.getCum_actual_physical_progress_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getProgresss()) && obj.getProgresss().length > 0) {
					obj.setProgresss(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgresss()));
					if(arraySize < obj.getProgresss().length) {
						arraySize = obj.getProgresss().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getIssues()) && obj.getIssues().length > 0) {
					obj.setIssues(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssues()));
					if(arraySize < obj.getIssues().length) {
						arraySize = obj.getIssues().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAssistance_requireds()) && obj.getAssistance_requireds().length > 0) {
					obj.setAssistance_requireds(CommonMethods.replaceEmptyByNullInSringArray(obj.getAssistance_requireds()));
					if(arraySize < obj.getAssistance_requireds().length) {
						arraySize = obj.getAssistance_requireds().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
					for (int i = 0; i < arraySize; i++) {
						 double plannedExpenditurePercentages = 0;
						 double actualExpenditurePercentages = 0;
						 double plannedPhysicalProgressPercentages = 0;
						 double actualPhysicalProgressPercentages = 0;
						 
						 String plannedExpenditurePercentage = (obj.getCum_planned_expenditure_pers().length > 0)?obj.getCum_planned_expenditure_pers()[i]:null;
						 String actualExpenditurePercentage = (obj.getCum_actual_expenditure_pers().length > 0)?obj.getCum_actual_expenditure_pers()[i]:null;
						 String plannedPhysicalProgressPercentage = (obj.getCum_planned_physical_progress_pers().length > 0)?obj.getCum_planned_physical_progress_pers()[i]:null;
						 String actualPhysicalProgressPercentage = (obj.getCum_actual_physical_progress_pers().length > 0)?obj.getCum_actual_physical_progress_pers()[i]:null;

						 if(!StringUtils.isEmpty(plannedExpenditurePercentage)) {
							 plannedExpenditurePercentages = Double.parseDouble(plannedExpenditurePercentage)/100;
						 }
						 if(!StringUtils.isEmpty(actualExpenditurePercentage)) {
							 actualExpenditurePercentages = Double.parseDouble(actualExpenditurePercentage)/100;
						 }
						 if(!StringUtils.isEmpty(plannedPhysicalProgressPercentage)) {
							 plannedPhysicalProgressPercentages = Double.parseDouble(plannedPhysicalProgressPercentage)/100;
						 }
						 if(!StringUtils.isEmpty(actualPhysicalProgressPercentage)) {
							 actualPhysicalProgressPercentages = Double.parseDouble(actualPhysicalProgressPercentage)/100;
						 }
						 String cum_actual_expenditure = null,cum_actual_expenditure_units = null;
						 if((arraySize - 1) == i) {
							 cum_actual_expenditure = obj.getCum_actual_expenditure();
							 cum_actual_expenditure_units = obj.getCum_actual_expenditure_units();
						 }
					     int p = 1;
					     if( obj.getMonths().length > 0 && !StringUtils.isEmpty(obj.getMonths()[i])) {
					    	String date = obj.getMonths()[i] + "-01";
						    insertStmt2.setString(p++,(obj.getContract_id()));
						    insertStmt2.setString(p++,(date));
						    //insertStmt2.setString(p++,(obj.getCum_actual_expenditure_fy_crs().length > 0)?obj.getCum_actual_expenditure_fy_crs()[i]:null);
						    insertStmt2.setString(p++,(String.valueOf(plannedExpenditurePercentages)));
						    insertStmt2.setString(p++,(cum_actual_expenditure));
						    insertStmt2.setString(p++,(String.valueOf(actualExpenditurePercentages)));
						    insertStmt2.setString(p++,(String.valueOf(plannedPhysicalProgressPercentages)));
						    insertStmt2.setString(p++,(String.valueOf(actualPhysicalProgressPercentages)));
						    insertStmt2.setString(p++,(obj.getProgresss().length > 0)?obj.getProgresss()[i]:null);
						    insertStmt2.setString(p++,(obj.getIssues().length > 0)?obj.getIssues()[i]:null);
						    insertStmt2.setString(p++,(obj.getAssistance_requireds().length > 0)?obj.getAssistance_requireds()[i]:null);
						    insertStmt2.setString(p++,CommonConstants.ACTIVE);
						    insertStmt2.setString(p++,(cum_actual_expenditure_units));
						    insertStmt2.addBatch();
					    }
				  }
				  int[] insertCount = insertStmt2.executeBatch();
			  }
				if(insertStmt2 != null){insertStmt2.close();}
			 }
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

	private java.lang.String getContractIdByWorkId(String contractId, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contract_id = null;
		String[] contarct_ids = null;
		try{
			String maxIdQry = "SELECT MAX(contract_id) as maxId  FROM zonal_railway_contracts WHERE contract_id LIKE ?";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1, contractId+"%");
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contract_id = rs.getString("maxId");
				if(StringUtils.isEmpty(contract_id)) {
					contract_id =  contractId;
				}else {
					contarct_ids = contract_id.split(contractId);
					if(contarct_ids.length > 0) {
						contract_id = contractId + String.format("%02d", Integer.parseInt(contarct_ids[1])+1) ;
					}else {
						contract_id =  contractId+"01";
					}
				}				
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return contract_id;
	}


	@Override
	public boolean updateZonalRailway(ZonalRailway obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement stmt = null;
		boolean flag = false;		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String updateQry = "UPDATE zonal_railway_contracts set "
					+ "source_of_funds_fk = ?, sanction_cost = ?, responsible_person_user_fk = ?, latest_revised_cost = ?,"
					+ "cumulative_expenditure_upto_last_finacial_year = ?, actual_start = ?, expected_finish = ?, actual_finish = ?, completion_cost = ?, status_fk = ?, "
					+ "as_on_date = ?,sub_work = ?,sanction_cost_units = ?,latest_revised_cost_units = ?,cumilative_expenditure_units = ?,completion_cost_units = ? where contract_id = ?";
			
			updateStmt = con.prepareStatement(updateQry);
			int q = 1;
			updateStmt.setString(q++,obj.getSource_of_funds()); 
			updateStmt.setString(q++,obj.getSanction_cost()); 
			updateStmt.setString(q++,obj.getResponsible_person_user_fk()); 
			updateStmt.setString(q++,obj.getLatest_revised_cost()); 
			updateStmt.setString(q++,obj.getCumulative_expenditure_upto_last_finacial_year()); 
			updateStmt.setString(q++,obj.getActual_start()); 
			updateStmt.setString(q++,obj.getExpected_finish()); 
			updateStmt.setString(q++,obj.getActual_finish()); 
			updateStmt.setString(q++,obj.getCompletion_cost()); 
			updateStmt.setString(q++,obj.getStatus_fk()); 
			updateStmt.setString(q++,obj.getAs_on_date()); 
			updateStmt.setString(q++,obj.getSub_work()); 
			updateStmt.setString(q++,obj.getSanction_cost_units()); 
			updateStmt.setString(q++,obj.getLatest_revised_cost_units()); 
			updateStmt.setString(q++,obj.getCumilative_expenditure_units()); 
			updateStmt.setString(q++,obj.getCompletion_cost_units());
			updateStmt.setString(q++,obj.getContract_id()); 
			
			int count = updateStmt.executeUpdate();
			DBConnectionHandler.closeJDBCResoucrs(null, updateStmt, null);
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				
				String inactiveQry = "delete from zonal_railway_progress where contract_id_fk = ?";		 
				stmt = con.prepareStatement(inactiveQry);
				stmt.setString(1,obj.getContract_id());
				stmt.executeUpdate();
				if(stmt != null){stmt.close();}
				
				String insertQry = "INSERT into  zonal_railway_progress "
						+ " (contract_id_fk, month, cum_planned_expenditure_per, cum_actual_expenditure, "
						+ "cum_actual_expenditure_per, cum_planned_physical_progress_per, cum_actual_physical_progress_per, progress, issue, assistance_required, status,cum_actual_expenditure_units) "
						+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				insertStmt = con.prepareStatement(insertQry);
				int arraySize = 0;
				
				if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
					obj.setMonths(CommonMethods.replaceEmptyByNullInSringArray(obj.getMonths()));
					if(arraySize < obj.getMonths().length) {
						arraySize = obj.getMonths().length;
					}
				}
				
				if(!StringUtils.isEmpty(obj.getCum_planned_expenditure_pers()) && obj.getCum_planned_expenditure_pers().length > 0) {
					obj.setCum_planned_expenditure_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_planned_expenditure_pers()));
					if(arraySize < obj.getCum_planned_expenditure_pers().length) {
						arraySize = obj.getCum_planned_expenditure_pers().length;
					}
				}
			
				if(!StringUtils.isEmpty(obj.getCum_actual_expenditure_pers()) && obj.getCum_actual_expenditure_pers().length > 0) {
					obj.setCum_actual_expenditure_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_actual_expenditure_pers()));
					if(arraySize < obj.getCum_actual_expenditure_pers().length) {
						arraySize = obj.getCum_actual_expenditure_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getCum_planned_physical_progress_pers()) && obj.getCum_planned_physical_progress_pers().length > 0) {
					obj.setCum_planned_physical_progress_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_planned_physical_progress_pers()));
					if(arraySize < obj.getCum_planned_physical_progress_pers().length) {
						arraySize = obj.getCum_planned_physical_progress_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getCum_actual_physical_progress_pers()) && obj.getCum_actual_physical_progress_pers().length > 0) {
					obj.setCum_actual_physical_progress_pers(CommonMethods.replaceEmptyByNullInSringArray(obj.getCum_actual_physical_progress_pers()));
					if(arraySize < obj.getCum_actual_physical_progress_pers().length) {
						arraySize = obj.getCum_actual_physical_progress_pers().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getProgresss()) && obj.getProgresss().length > 0) {
					obj.setProgresss(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgresss()));
					if(arraySize < obj.getProgresss().length) {
						arraySize = obj.getProgresss().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getIssues()) && obj.getIssues().length > 0) {
					obj.setIssues(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssues()));
					if(arraySize < obj.getIssues().length) {
						arraySize = obj.getIssues().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAssistance_requireds()) && obj.getAssistance_requireds().length > 0) {
					obj.setAssistance_requireds(CommonMethods.replaceEmptyByNullInSringArray(obj.getAssistance_requireds()));
					if(arraySize < obj.getAssistance_requireds().length) {
						arraySize = obj.getAssistance_requireds().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
					for (int i = 0; i < arraySize; i++) {
						 String pId = (obj.getProgress_ids().length > 0)?obj.getProgress_ids()[i]:null;
						 double plannedExpenditurePercentages = 0;
						 double actualExpenditurePercentages = 0;
						 double plannedPhysicalProgressPercentages = 0;
						 double actualPhysicalProgressPercentages = 0;
						 
						 String plannedExpenditurePercentage = (obj.getCum_planned_expenditure_pers().length > 0)?obj.getCum_planned_expenditure_pers()[i]:null;
						 String actualExpenditurePercentage = (obj.getCum_actual_expenditure_pers().length > 0)?obj.getCum_actual_expenditure_pers()[i]:null;
						 String plannedPhysicalProgressPercentage = (obj.getCum_planned_physical_progress_pers().length > 0)?obj.getCum_planned_physical_progress_pers()[i]:null;
						 String actualPhysicalProgressPercentage = (obj.getCum_actual_physical_progress_pers().length > 0)?obj.getCum_actual_physical_progress_pers()[i]:null;

						 if(!StringUtils.isEmpty(plannedExpenditurePercentage)) {
							 plannedExpenditurePercentages = Double.parseDouble(plannedExpenditurePercentage)/100;
						 }
						 if(!StringUtils.isEmpty(actualExpenditurePercentage)) {
							 actualExpenditurePercentages = Double.parseDouble(actualExpenditurePercentage)/100;
						 }
						 if(!StringUtils.isEmpty(plannedPhysicalProgressPercentage)) {
							 plannedPhysicalProgressPercentages = Double.parseDouble(plannedPhysicalProgressPercentage)/100;
						 }
						 if(!StringUtils.isEmpty(actualPhysicalProgressPercentage)) {
							 actualPhysicalProgressPercentages = Double.parseDouble(actualPhysicalProgressPercentage)/100;
						 }
						 String cum_actual_expenditure = null,cum_actual_expenditure_units = null;
						 if((arraySize - 1) == i) {
							 cum_actual_expenditure = obj.getCum_actual_expenditure();
							 cum_actual_expenditure_units = obj.getCum_actual_expenditure_units();
						 }
						 if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
						    int p = 1;
						    if( obj.getMonths().length > 0 && !StringUtils.isEmpty(obj.getMonths()[i])) {
						    	String date = obj.getMonths()[i] + "-01";
							    insertStmt.setString(p++,(obj.getContract_id()));
							    insertStmt.setString(p++,(date));
							   // insertStmt.setString(p++,(obj.getCum_actual_expenditure_fy_crs().length > 0)?obj.getCum_actual_expenditure_fy_crs()[i]:null);
							    insertStmt.setString(p++,(String.valueOf(plannedExpenditurePercentages)));
							    insertStmt.setString(p++,(cum_actual_expenditure));
							    insertStmt.setString(p++,(String.valueOf(actualExpenditurePercentages)));
							    insertStmt.setString(p++,(String.valueOf(plannedPhysicalProgressPercentages)));
							    insertStmt.setString(p++,(String.valueOf(actualPhysicalProgressPercentages)));
							    insertStmt.setString(p++,(obj.getProgresss().length > 0)?obj.getProgresss()[i]:null);
							    insertStmt.setString(p++,(obj.getIssues().length > 0)?obj.getIssues()[i]:null);
							    insertStmt.setString(p++,(obj.getAssistance_requireds().length > 0)?obj.getAssistance_requireds()[i]:null);
							    insertStmt.setString(p++,CommonConstants.ACTIVE);
							    insertStmt.setString(p++,(cum_actual_expenditure_units));
							    insertStmt.addBatch();
						    }
						
					  }
						
					}
				}
				int[] insertCount = insertStmt.executeBatch();
				if(insertCount.length > 0 ) {
					flag = true;
				}
				DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
				con.commit();
			}
	   }catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
	   }finally {
		   DBConnectionHandler.closeJDBCResoucrs(con, null, null);
	   }
	   return flag;	
	}

	@Override
	public List<ZonalRailway> getUserListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select user_id as responsible_person_user_fk, user_name, designation  from user where user_type_fk = ? ";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));		
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> getRailwayListForZonalRailwaysForm(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			List<ZonalRailway> list = getRailwaysList(obj.getWork_id_fk(),con);
			if(list.isEmpty()) {
				String qry = "select railway_id as execution_agency_railway_fk, railway_name from `railway` ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
			}else {
				String qry = "select railway_id as execution_agency_railway_fk, railway_name  from railway where railway_id <> ? ";
				int arraySize = 1;
				int i = 0;
				Object[] pValues = new Object[list.size()];
				for(ZonalRailway s : list) {
					if(list.size() > arraySize) {
						qry = qry + "and railway_id <> ?  ";
						arraySize++;
					}
					if(list.size() > i) {
						pValues[i++] = s.getExecution_agency_railway_fk();

					}
				}
				objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));	
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	private List<ZonalRailway> getRailwaysList(String work_id_fk,Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<ZonalRailway> objsList = new ArrayList<ZonalRailway>();
		ZonalRailway obj = null;
		try {
			String qry ="SELECT execution_agency_railway_fk FROM zonal_railway_contracts where  contract_id like '"+work_id_fk+"%';";
			stmt = con.prepareStatement(qry);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new ZonalRailway();
				obj.setExecution_agency_railway_fk(resultSet.getString("execution_agency_railway_fk"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally { 
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}

	@Override
	public List<ZonalRailway> String(String sub_work_id) throws Exception {
		List<ZonalRailway> progressList = null;
		try {
			  String qry = "select progress_id, contract_id_fk as contract_id, DATE_FORMAT(month,'%d-%m-%Y') AS month, cum_actual_expenditure_fy_cr, (cum_planned_expenditure_per * 100) as cum_planned_expenditure_per, cum_actual_expenditure,(cum_actual_expenditure_per * 100) as cum_actual_expenditure_per,cum_actual_expenditure_units, "
			  		+ "(cum_planned_physical_progress_per * 100) as cum_planned_physical_progress_per,(cum_actual_physical_progress_per * 100) as  cum_actual_physical_progress_per, progress, issue, assistance_required, status from zonal_railway_progress "
	  					+"where contract_id_fk is not null and contract_id_fk = ? ";
			
			Object[] pValues = new Object[] {sub_work_id};
			
			progressList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return progressList;
	}

	@Override
	public List<ZonalRailway> getZonalRailwayList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry ="select contract_id, work_id_fk,w.work_short_name,u.designation,sub_work,r.railway_name, execution_agency_railway_fk, source_of_funds_fk as source_of_funds, sanction_cost, latest_revised_cost, cast(cumulative_expenditure_upto_last_finacial_year as CHAR) as cumulative_expenditure_upto_last_finacial_year, DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,"
					+ "DATE_FORMAT(expected_finish,'%d-%m-%Y') AS expected_finish,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish, z.completion_cost, status_fk, DATE_FORMAT(as_on_date,'%d-%m-%Y') AS as_on_date, responsible_person_user_fk "
					+ "sanction_cost_units,latest_revised_cost_units,cumilative_expenditure_units,completion_cost_units "
					+ " m.unit as sanction_unit,m.unit as revised_cost_unit,m.unit as cumilative_unit,m.unit as completion_unit from zonal_railway_contracts z " + 
					"left join work w on z.work_id_fk = w.work_id "+
					"left join railway r on z.execution_agency_railway_fk = r.railway_id "+
					"left join money_unit m on z.sanction_cost_units = m.value "+
					"left join money_unit m1 on z.latest_revised_cost_units = m1.value "+
					"left join money_unit m2 on z.cumilative_expenditure_units = m2.value "+
					"left join money_unit m3 on z.completion_cost_units = m3.value "
					+"left join user u on z.responsible_person_user_fk = u.user_id  where contract_id is not null  ";
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
				qry = qry + " and source_of_funds_fk = ?";
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
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<ZonalRailway> getUnitsList(ZonalRailway obj) throws Exception {
		List<ZonalRailway> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ZonalRailway>(ZonalRailway.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


}
