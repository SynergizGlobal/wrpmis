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
		
			
			qry = qry + " GROUP BY project_id,project_name";
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
		
			
			qry = qry + " GROUP BY work_id,work_short_name";
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
			String qry = "SELECT distinct work_id as work_id_fk,work_short_name,contract_id_fk,contract_short_name from p6_activities a "
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
		
			
			//qry = qry + " GROUP BY contract_id_fk";
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
			String qry = "SELECT a.p6_activity_id as activity_id, FORMAT(a.baseline_start,'dd-MM-yyyy') as baseline_start, FORMAT(a.baseline_finish,'dd-MM-yyyy') as baseline_finish," + 
					"					 FORMAT(a.start,'dd-MM-yyyy') as start, FORMAT(a.finish,'dd-MM-yyyy') as finish, a.contract_id_fk,contract_short_name, a.from_structure_id," + 
					"					 a.to_structure_id, s11.structure_type_fk, a.section, a.line, s11.structure, a.component, a.component_id, a.order_x, a.order_y, " + 
					"					a.p6_activity_name as activity_name, FORMAT(a.baseline_start,'dd-MM-yyyy') as planned_start, FORMAT(a.baseline_finish,'dd-MM-yyyy') as planned_finish, " + 
					"					case  " + 
					"					 when (ISNULL(completed, 0)=0 or completed is null) then ''  " + 
					"					 when ISNULL(completed, 0)>=ISNULL(scope, 0) then (select FORMAT(min(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id)  " + 
					"					 else (select FORMAT(min(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) end as actual_start,case   " + 
					"					 when (ISNULL(completed, 0)=0 or completed is null) then '' " + 
					"					 when ISNULL(completed, 0)>=ISNULL(scope, 0) then (select FORMAT(max(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id)  " + 
					"					 else '' end as actual_finish, a.unit, a.scope, a.completed, CAST(a.weightage AS DECIMAL(10,2)) as weightage, a.component_details, a.remarks, " + 
					"					a.created_date, a.created_by_user_id_fk, a.modified_date, a.modified_by_user_id_fk, a.task_code as p6_task_code  " + 
					"					from p6_activities a left join structure s11 on s11.structure_id = a.structure_id_fk " + 
					"					LEFT JOIN contract c ON a.contract_id_fk = c.contract_id " + 
					"					left join work w on c.work_id_fk = w.work_id " + 
					"					left join project p on w.project_id_fk = p.project_id " + 
					"					 where a.p6_activity_id is not null and a.p6_activity_id <> '' ";
			
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
			
			qry = qry + "  order by ISNULL(a.task_code,''), a.task_code ASC,a.p6_activity_id";
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

	@Override
	public List<StripChart> generateTPCStatusReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select contract_short_name,structure,progress,Status,FORMAT(progress_date,'dd-MM-yyyy') as progress_date from( " + 
					"SELECT c.contract_short_name,structure,cast((round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),0)) as int) AS progress,  " + 
					"					case when SUM((completed*weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed *  weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as Status,   " + 
					"					case when SUM((completed*weightage)*100 / scope) / SUM(weightage)=100 then max(a.actual_finish) else max(a.expected_finish) end as progress_date   " + 
					"					FROM activities_view a   " + 
					"					LEFT join contract c on c.contract_id=a.contract_id   " + 
					"					where a.contract_id like 'P04W04EN%' and structure not in('Badlapur (Deck)') and structure not in('Khar Road (New FOB)')  " + 
					"					 " + 
					"					GROUP BY c.contract_short_name,structure) as a  " + 
					"					 " + 
					"					GROUP BY contract_short_name,structure,progress,Status,progress_date " + 
					"					order by contract_short_name,status ";
			

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getDivisionList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "				select Division,sum(StructureCount) as 'WorkScope',sum(CompletedCount) as Completed,sum(StructureCount)-sum(CompletedCount) as 'Balance',wip from(    " + 
					"				SELECT  contract_short_name  as 'Division',    " + 
					"				count(structure) as StructureCount,case when Status='Completed' then count(Status) else 0 end AS 'CompletedCount',case when Status='In Progress' then count(Status) else 0 end AS wip    " + 
					"				from(    " + 
					"				SELECT substring(c.contract_short_name,CHARINDEX('(',c.contract_short_name),CHARINDEX(')',c.contract_short_name)-CHARINDEX('(',c.contract_short_name)+1) as contract_short_name,structure,round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),2) AS progress,      " + 
					"				case when SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed * weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as Status,      " + 
					"				case when SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then Max(actual_finish) else Max(expected_finish) end as progress_date " + 
					"				FROM activities_view a      " + 
					"				LEFT join contract c on c.contract_id=a.contract_id      " + 
					"				where a.contract_id like 'P04W04EN%' and structure not in('Badlapur (Deck)') and structure not in('Khar Road (New FOB)') GROUP BY c.contract_short_name,structure) as a    " + 
					"				group by contract_short_name,Status) as b    " + 
					"				group by b.Division,b.wip ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> generateMCDOProgressReport(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select * from( " + 
					"select distinct works+' ('+unit+')' as works,cast(during_month as int) as during_month,cast(cumulative as int) as cumulative,cast(balance as int) as balance from  vw_mcdo_pk " + 
					") as a where works is not null  " + 
					" " + 
					"and works in('Earthwork Filling (Cum)','Rock cutting (Cum)','Blanketing (Cum)','Drain (Rm)','TSS Formation (Cum)','Flyover (Nos)','Major Bridge (Nos)','Minor Bridge (Nos)','ROB (Nos)','RUB (Nos)','Approach Cutting (Cum)','Heading (Rm)','Benching (Rm)','Final Lining  (Rm)','Railway Earth Filling (Cum)') " + 
					" " + 
					"order by (case when works='Earthwork Filling (Cum)' then 1 when works='Railway Earth Filling (Cum)' then 2 when works='Rock cutting (Cum)' then 3 when works='Blanketing (Cum)' then 4 when works='Drain (Rm)' then 5 when works='TSS Formation (Cum)' then 6 " + 
					" " + 
					" when works='Flyover (Nos)' then 7 when works='Major Bridge (Nos)' then 8 when works='Minor Bridge (Nos)' then 9 when works='ROB (Nos)' then 10 when works='RUB (Nos)' then 11  " + 
					" when works='Approach Cutting (Cum)' then 12 when works='Heading (Rm)' then 13 when works='Benching (Rm)' then 14 when works='Final Lining  (Rm)' then 15  else 0 end) ";
			

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> generateMCDOProgressReport1(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select * from( " + 
					"select distinct works+' ('+unit+')' as works,cast(during_month as int) as during_month,cast(cumulative as int) as cumulative,cast(balance as int) as balance from  vw_mcdo_virar " + 
					") as a where works is not null  " + 
					" " + 
					"and works in('Earthwork Filling (Cum)','Earthwork Cutting (Cum)','Rock cutting (Cum)','Blanketing (Cum)','Construction of Drain (Rm)','Major Bridge (Nos)','Minor Bridge (Nos)','ROB (Nos)','RUB (Nos)') " + 
					" " + 
					"order by (case when works='Earthwork Filling (Cum)' then 1 when works='Earthwork Cutting (Cum)' then 2 when works='Blanketing (Cum)' then 3 when works='Construction of Drain (Rm)' then 4  when works='Major Bridge (Nos)' then 5 when works='Minor Bridge (Nos)' then 6 when works='ROB (Nos)' then 7 when works='RUB (Nos)' then 8  " + 
					"else 0 end) ";
			

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	
	
}
