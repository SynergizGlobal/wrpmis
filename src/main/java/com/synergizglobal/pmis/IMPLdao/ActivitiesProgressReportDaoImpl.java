package com.synergizglobal.pmis.IMPLdao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesProgressReportDao;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
@Repository
public class ActivitiesProgressReportDaoImpl implements ActivitiesProgressReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT p.project_id,p.project_name "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where project_id is not null and project_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY p.project_id ORDER BY p.project_id ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT c.work_id_fk,w.work_id,w.work_name,w.work_short_name "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where w.work_id is not null and w.work_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY w.work_id ORDER BY w.work_id ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT c.contract_id,c.contract_name,c.contract_short_name "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.contract_id is not null and c.contract_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.contract_id ORDER BY c.contract_id ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	


	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT fob_id,fob_name "+
					"from activities a " + 
					"LEFT JOIN fob f on a.structure = f.fob_id " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where fob_id is not null and fob_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY fob_id ORDER BY fob_id ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT c.contractor_id_fk,contractor_id,contractor_name "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN contractor ctr on c.contractor_id_fk = ctr.contractor_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.contractor_id_fk is not null and c.contractor_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.contractor_id_fk ORDER BY c.contractor_id_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {			
			String qry = "SELECT user_id,user_name,designation "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN user u on c.hod_user_id_fk = u.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.hod_user_id_fk is not null and c.hod_user_id_fk <> '' ";
			
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.hod_user_id_fk ORDER BY c.hod_user_id_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation "+
					"from activities a " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN user u on c.dy_hod_user_id_fk = u.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.dy_hod_user_id_fk is not null and c.dy_hod_user_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				qry = qry + " and c.dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.dy_hod_user_id_fk ORDER BY c.dy_hod_user_id_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj)
			throws Exception {
		//Map<ActivitiesProgressReport, List<ActivitiesProgressReport>> mapObjsList = new HashMap<ActivitiesProgressReport, List<ActivitiesProgressReport>>();
		Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> mapObjsList = new LinkedHashMap<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>>();
		NumberFormat numberFormatter = new DecimalFormat("#0.0000");
		try {
			
			String contractsQry = "select activity_id_fk,contract_id_fk,work_id,project_id,project_name "
					+ "from activity_progress ap " 
					+ "LEFT JOIN activities a on activity_id_fk = activity_id " 
					+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
					+ "where progress_date is not null";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				contractsQry = contractsQry + " and progress_date >= ? and progress_date <= ?";
				arrSize++;
				arrSize++;
			}else {
				contractsQry = contractsQry + " and progress_date <=  ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				contractsQry = contractsQry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				contractsQry = contractsQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				contractsQry = contractsQry + " and contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				contractsQry = contractsQry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				contractsQry = contractsQry + " and contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				contractsQry = contractsQry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				contractsQry = contractsQry + " and dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			contractsQry = contractsQry + " GROUP BY a.contract_id_fk ORDER BY a.contract_id_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				pValues[i++] = obj.getFrom_date();
				pValues[i++] = obj.getTo_date();
			}else {
				pValues[i++] = obj.getFrom_date();
			}

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues[i++] = obj.getDyhod();
			}
			
			List<ActivitiesProgressReport> contractsList = jdbcTemplate.query( contractsQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
			
			/***********************************************************************/
			
			for (ActivitiesProgressReport cObj : contractsList) {
				
				Map<String,List<ActivitiesProgressReport>> structureProgresses = new LinkedHashMap<String, List<ActivitiesProgressReport>>();

				ActivitiesProgressReport sObj = new ActivitiesProgressReport();
				/*******************************************************************************************************************/
				String progressStructuresQry = "select ap.activity_id_fk,a.contract_id_fk,a.structure "
						+ "from activity_progress ap "  
						+ "left join activities a on ap.activity_id_fk = a.activity_id "  
						+ "left join contract c on a.contract_id_fk = c.contract_id "  
						+ "where a.contract_id_fk = ?";
				
				
				arrSize = 1;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
					progressStructuresQry = progressStructuresQry + " and progress_date >= ? and progress_date <= ?";
					arrSize++;
					arrSize++;
				}else {
					progressStructuresQry = progressStructuresQry + " and progress_date = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
					progressStructuresQry = progressStructuresQry + " and a.structure = ?";
					arrSize++;
				}
				
				progressStructuresQry = progressStructuresQry + " GROUP BY a.structure ORDER BY a.structure ASC";
				
				pValues = new Object[arrSize];
				
				i = 0;
				pValues[i++] = cObj.getContract_id_fk();
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
					pValues[i++] = obj.getFrom_date();
					pValues[i++] = obj.getTo_date();
				}else {
					pValues[i++] = obj.getFrom_date();
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
					pValues[i++] = obj.getFob_id_fk();
				}
				
				List<ActivitiesProgressReport> contractProgressStructuresList = jdbcTemplate.query( progressStructuresQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
				
				
				/**********************************************************************************************************************************/				
				
				
				for (ActivitiesProgressReport contractProgressStructure : contractProgressStructuresList) {
					String contractProgressDatesQry = "select activity_id_fk,MAX(ap.progress_date) AS progress_date,contract_id_fk,work_id,project_id,project_name "
							+ "from activity_progress ap " 
							+ "LEFT JOIN activities a on activity_id_fk = activity_id  " 
							+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
							+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
							+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
							+ "where a.contract_id_fk = ?";
					
					arrSize = 1;
					
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
						contractProgressDatesQry = contractProgressDatesQry + " and progress_date >= ? and progress_date <= ?";
						arrSize++;
						arrSize++;
					}else {
						contractProgressDatesQry = contractProgressDatesQry + " and progress_date = ?";
						arrSize++;
					}
					
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getStructure())) {
						contractProgressDatesQry = contractProgressDatesQry + " and a.structure = ?";
						arrSize++;
					}
					
					contractProgressDatesQry = contractProgressDatesQry + " ORDER BY progress_date DESC";
					
					pValues = new Object[arrSize];
					
					i = 0;
					pValues[i++] = cObj.getContract_id_fk();
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
						pValues[i++] = obj.getFrom_date();
						pValues[i++] = obj.getTo_date();
					}else {
						pValues[i++] = obj.getFrom_date();
					}
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getStructure())) {
						pValues[i++] = contractProgressStructure.getStructure();
					}
					
					List<ActivitiesProgressReport> contractProgressDatesList = jdbcTemplate.query( contractProgressDatesQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
					List<ActivitiesProgressReport> totalContractProgresList = new ArrayList<ActivitiesProgressReport>();
					for (ActivitiesProgressReport contractProgressDate : contractProgressDatesList) {
						
						String progressQry = "select distinct ap.progress_date,ap.activity_id_fk,max(ap.completed_scope) as completed_scope,a.activity_id,a.contract_id_fk,a.structure_type_fk,a.component_id," + 
								"a.component,a.activity_name,a.structure,a.scope,a.completed,c.contract_name,c.contract_short_name," + 
								"(a.completed - IFNULL((select sum(completed_scope) " + 
								"from activity_progress ap1 " + 
								"left outer join activities a1 on ap1.activity_id_fk = a1.activity_id " + 
								"left outer join contract c1 on a1.contract_id_fk = c1.contract_id " + 
								"where a1.contract_id_fk = ? and ap1.progress_date > ? and a1.structure = ? and ap1.activity_id_fk = ap.activity_id_fk),0)) as cumulative_completed " + 
								"from activity_progress ap " + 
								"left outer join activities a on ap.activity_id_fk = a.activity_id " + 
								"left outer join contract c on a.contract_id_fk = c.contract_id " + 
								"where a.contract_id_fk = ? and ap.progress_date >= ? and ap.progress_date <= ? and a.structure = ? group by a.activity_name";
						
						pValues = new Object[] {cObj.getContract_id_fk(),contractProgressDate.getProgress_date(),contractProgressStructure.getStructure(),cObj.getContract_id_fk(),obj.getFrom_date(),obj.getTo_date(),contractProgressStructure.getStructure()};
						
						List<ActivitiesProgressReport> pList = jdbcTemplate.query( progressQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
						
						for (ActivitiesProgressReport object : pList) {
							if(!StringUtils.isEmpty(object.getCumulative_completed())) {
								object.setCumulative_completed(numberFormatter.format(Double.parseDouble(object.getCumulative_completed())));
							}
						}
						totalContractProgresList.addAll(pList);
					}
					
					structureProgresses.put(contractProgressStructure.getStructure(), totalContractProgresList);
				}
				
				String sQry = "select contract_id,work_id_fk,contract_name,contract_short_name,contractor_id_fk, work_name,work_short_name,contractor_name " + 
						"from contract c " + 
						"left outer join work w on work_id_fk = work_id " + 
						"left outer join contractor cr on contractor_id_fk = contractor_id " + 
						"where contract_id = ?" ;
				
				pValues = new Object[] {cObj.getContract_id_fk()};
				
				sObj = jdbcTemplate.queryForObject( sQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
				
				mapObjsList.put(sObj, structureProgresses);
				
				
			}
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return mapObjsList;
	}

}
