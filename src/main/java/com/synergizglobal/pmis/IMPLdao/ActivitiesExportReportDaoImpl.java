package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesExportReportDao;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.StripChart;
@Repository
public class ActivitiesExportReportDaoImpl implements ActivitiesExportReportDao{
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<StripChart> getProjectsFilterListInActivitiesExportReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT project_id,project_name from p6_activities a "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "left join work w on work_id = c.work_id_fk "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where project_id is not null and project_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
		
			
			qry = qry + " GROUP BY project_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getWorksFilterListInActivitiesExportReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT  work_id as work_id_fk,work_short_name from p6_activities a "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "left join work w on work_id = c.work_id_fk "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where c.work_id_fk is not null and c.work_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
		
			
			qry = qry + " GROUP BY work_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getContractListInActivitiesExportReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,work_short_name,contract_id_fk,contract_short_name from p6_activities a "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "left join work w on work_id = c.work_id_fk "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where contract_id_fk is not null and contract_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
		
			
			qry = qry + " GROUP BY contract_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public StripChart generateActivitiesExportReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT a.p6_activity_id as activity_id, DATE_FORMAT(a.baseline_start,'%d-%m-%Y') as baseline_start, DATE_FORMAT(a.baseline_finish,'%d-%m-%Y') as baseline_finish,"
					+ " DATE_FORMAT(a.start,'%d-%m-%Y') as start, DATE_FORMAT(a.finish,'%d-%m-%Y') as finish,pcv.status, a.contract_id_fk,contract_short_name, a.from_structure_id, a.to_structure_id, s11.structure_type_fk, a.section, a.line, s11.structure, a.component, a.component_id, a.order_x, a.order_y, "
					+ "a.p6_activity_name as activity_name, DATE_FORMAT(a.baseline_start,'%d-%m-%Y') as planned_start, DATE_FORMAT(a.baseline_finish,'%d-%m-%Y') as planned_finish, "
					+ "case  " + 
					" when (IFNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then '' " + 
					" when IFNULL(NULLIF(completed, '' ), 0)>=IFNULL(NULLIF(scope, '' ), 0) then (select DATE_FORMAT(min(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) " + 
					" else (select DATE_FORMAT(min(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) end as actual_start,case  " + 
					" when (IFNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then '' " + 
					" when IFNULL(NULLIF(completed, '' ), 0)>=IFNULL(NULLIF(scope, '' ), 0) then (select DATE_FORMAT(max(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) " + 
					" else '' end as actual_finish, a.unit, a.scope, a.completed, CAST(a.weightage AS DECIMAL(10,2)) as weightage, a.component_details, a.remarks, "
					+ "a.created_date, a.created_by_user_id_fk, a.modified_date, a.modified_by_user_id_fk, a.task_code as p6_task_code " + 
					"from p6_activities a left join structure s11 on s11.structure_id = a.structure_id_fk "
					+ "left join p6_contract_view pcv on pcv.contract_id = a.contract_id_fk and a.task_code = pcv.task_code "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "left join work w on c.work_id_fk = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where a.p6_activity_id is not null and a.p6_activity_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + "  order by ISNULL(a.task_code), a.task_code ASC,a.p6_activity_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			obj.setReport1List(objsList);
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return obj;
	}
	
	
	
	
}
