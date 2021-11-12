package com.synergizglobal.pmis.IMPLdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ProgressUpdateReportDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.Training;
@Repository
public class ProgressUpdateReportDaoImpl implements ProgressUpdateReportDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT c.contract_id,c.contract_name,c.contract_short_name "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT c.work_id_fk,w.work_id,w.work_name,w.work_short_name "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT c.contractor_id_fk,contractor_id,contractor_name "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getHodFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {			
			String qry = "SELECT user_id,user_name,designation "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			
			qry = qry + " GROUP BY c.hod_user_id_fk  ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T'),u.designation" ;

			
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT p.project_id,p.project_name "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT fob_id,fob_name "+
					"from approvable_activity_progress acp "
					+"left join activities a on  a.activity_id = acp.activity_id_fk  " + 
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public ActivitiesProgressReport getActivitiesProgressUpdate(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> datāL̥īśt = null;
		
		try {
			String progressQry = "SELECT distinct date_format(progress_date,'%d-%b-%y') as progress_date,"
					//+ "d.department_name,"
					+ "a.contract_id_fk,c.hod_user_id_fk,"
					+ "u2.designation as hod_designation,u3.designation as dyhod_designation,c.work_id_fk,w.work_short_name,p.project_name, c.contract_short_name,structure as structure_type_fk,u.user_id,u.designation,u.user_name,c.department_fk," 
					//+ "(select  count(distinct progress_date)) as progress_dates,"
					+ " COALESCE( (select COUNT(a1.created_by_user_id_fk) FROM approvable_activity_progress a1 left join activities a2 on  a2.activity_id = a1.activity_id_fk where a1.created_by_user_id_fk = acp.created_by_user_id_fk and a2.structure=a.structure  and a1.created_by_user_id_fk is not null "
					+ "  and a1.progress_date >= ? and a1.progress_date <= ? and a1.progress_date=acp.progress_date), 0)  as updated,"
					+ " COALESCE((select count(a11.approval_status_fk) FROM approvable_activity_progress a11  left join activities a12 on  a12.activity_id = a11.activity_id_fk where approval_status_fk = 'approved' and a11.created_by_user_id_fk = acp.created_by_user_id_fk and a12.structure=a.structure "
					+ " and a11.created_by_user_id_fk is not null "
					+ "  and a11.progress_date >= ? and a11.progress_date <= ? and a11.progress_date=acp.progress_date), 0) as approved, "
					+ " COALESCE((select count(a13.approval_status_fk) FROM approvable_activity_progress a13   left join activities a14 on  a14.activity_id = a13.activity_id_fk where approval_status_fk = 'rejected' and a13.created_by_user_id_fk = acp.created_by_user_id_fk and a14.structure=a.structure and a13.created_by_user_id_fk is not null "
					+ "  and a13.progress_date >= ? and a13.progress_date <= ? and a13.progress_date=acp.progress_date), 0) as rejected "
					+ " FROM approvable_activity_progress acp  "
					+ "left join activities a on  a.activity_id = acp.activity_id_fk   "
					+ "left join contract c on a.contract_id_fk = c.contract_id "  
					+ " left join contractor cr on c.contractor_id_fk = cr.contractor_id  "
					+ "left join work w on c.work_id_fk = w.work_id  "
					+ "left join project p on w.project_id_fk = p.project_id "
					//+ "left join department d on c.department_fk = d.department "
					//+ "left join contract_executive cr1 on cr1.contract_id_fk = c.contract_id  and cr1.department_id_fk=d.department "
				    + "left join user u2 on c.hod_user_id_fk = u2.user_id " 
					+ "left join user u3 on c.dy_hod_user_id_fk = u3.user_id " + 
					" left join user u on acp.created_by_user_id_fk = u.user_id where acp.created_by_user_id_fk is not null ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				progressQry = progressQry + " and progress_date >= ? and progress_date <= ?";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
				arrSize++;
				arrSize++;
			}else {
				progressQry = progressQry + " and progress_date <=  ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				progressQry = progressQry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				progressQry = progressQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				progressQry = progressQry + " and contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				progressQry = progressQry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				progressQry = progressQry + " and contractor_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				progressQry = progressQry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				progressQry = progressQry + " and dy_hod_user_id_fk = ?";
				arrSize++;
			}
			
			//progressQry = progressQry + " GROUP BY structure";
			progressQry = progressQry + " order by date(progress_date) desc";
			
			Object[] pValues1 = new Object[arrSize];
			
			int j = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				pValues1[j++] = obj.getFrom_date();
				pValues1[j++] = obj.getTo_date();
				pValues1[j++] = obj.getFrom_date();
				pValues1[j++] = obj.getTo_date();				
				pValues1[j++] = obj.getFrom_date();
				pValues1[j++] = obj.getTo_date();				
				pValues1[j++] = obj.getFrom_date();
				pValues1[j++] = obj.getTo_date();
			}else {
				pValues1[j++] = obj.getFrom_date();
			}

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues1[j++] = obj.getProject_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues1[j++] = obj.getWork_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues1[j++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues1[j++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues1[j++] = obj.getContractor_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues1[j++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDyhod())) {
				pValues1[j++] = obj.getDyhod();
			}
			
			datāL̥īśt = jdbcTemplate.query(progressQry, pValues1, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));	
			List<String> contracts = new ArrayList<>();
			for (ActivitiesProgressReport aObj : datāL̥īśt) 
			{
				if(contracts.indexOf(aObj.getContract_short_name())==-1)
				{
					contracts.add(aObj.getContract_short_name());
				}
			}
	    obj.setContractsList(contracts);
		obj.setProgressUpdateList(datāL̥īśt);
	}catch(Exception e){ 
		throw new Exception(e);
	}
	return obj;
	}
}
