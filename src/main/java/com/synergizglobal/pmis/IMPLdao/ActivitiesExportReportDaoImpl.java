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
			String qry = "				select Division,sum(StructureCount) as 'WorkScope',sum(CompletedCount) as Completed,sum(StructureCount)-sum(CompletedCount) as 'Balance',sum(wip) as wip from(    " + 
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
					"				group by b.Division ";
			
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
			
			String qry = "";
			if(obj.getWork_id_fk().compareTo("P04W02")==0)
			{			
			 qry = "      \r\n" + 
					"    \r\n" + 
					"WITH bridges_progress AS (    \r\n" + 
					"    SELECT    \r\n" + 
					"        structure_type,    \r\n" + 
					"        structure,    \r\n" + 
					"        MAX(actual) AS actual_finish,    \r\n" + 
					"        SUM(result) AS bridge_progress    \r\n" + 
					"    FROM    \r\n" + 
					"        (    \r\n" + 
					"            SELECT    \r\n" + 
					"                structure_type,    \r\n" + 
					"                structure,    \r\n" + 
					"                MAX(actual_finish) AS actual,    \r\n" + 
					"                SUM([Weightage] * [Completed] * 100) /    \r\n" + 
					"                (    \r\n" + 
					"                    [Scope] *    \r\n" + 
					"                    (    \r\n" + 
					"                        SELECT    \r\n" + 
					"                            SUM([Weightage])    \r\n" + 
					"                        FROM    \r\n" + 
					"                            activities_view    \r\n" + 
					"                        WHERE    \r\n" + 
					"                            [Work_Id] = T.[Work_Id] AND    \r\n" + 
					"                            [Contract_Id] = T.[Contract_Id] AND    \r\n" + 
					"                            [Structure_Type] = T.[Structure_Type] AND    \r\n" + 
					"                            [Structure] = T.[Structure]    \r\n" + 
					"                    )    \r\n" + 
					"                ) AS result    \r\n" + 
					"            FROM    \r\n" + 
					"                activities_view t    \r\n" + 
					"            WHERE    \r\n" + 
					"                work_id = 'P04W02'    \r\n" + 
					"            GROUP BY    \r\n" + 
					"                structure_type, weightage, completed, scope, work_id, contract_id, structure    \r\n" + 
					"        ) a    \r\n" + 
					"    GROUP BY    \r\n" + 
					"        structure_type, structure    \r\n" + 
					")    \r\n" + 
					"    \r\n" + 
					", structure_count AS (    \r\n" + 
					"    SELECT    \r\n" + 
					"        structure_type,    \r\n" + 
					"        during_the_month,    \r\n" + 
					"        cum AS cummulative,    \r\n" + 
					"        (scope - cum) AS balance    \r\n" + 
					"    FROM    \r\n" + 
					"        (    \r\n" + 
					"            SELECT    \r\n" + 
					"                structure_type,    \r\n" + 
					"                SUM(scope) AS scope,    \r\n" + 
					"                SUM(cumm) AS cum,    \r\n" + 
					"                SUM(during_month) AS during_the_month    \r\n" + 
					"            FROM    \r\n" + 
					"                (    \r\n" + 
					"                    SELECT    \r\n" + 
					"                        structure_type,    \r\n" + 
					"                        structure,    \r\n" + 
					"                        COUNT(DISTINCT structure) AS scope,    \r\n" + 
					"                        CASE WHEN MIN(bridge_progress) > 95 THEN COUNT(DISTINCT structure) ELSE 0 END AS cumm,    \r\n" + 
					"                        CASE WHEN MIN(bridge_progress) > 95 AND     \r\n" + 
					"                        actual_finish >=  '"+obj.getFrom_date()+"'              -- comment :to change the date put in format of 'yyyy - mm- dd'    \r\n" + 
					"      AND (actual_finish) <  '"+obj.getTo_date()+"'           \r\n" + 
					"                        THEN COUNT(DISTINCT structure) ELSE 0 END AS during_month    \r\n" + 
					"                    FROM    \r\n" + 
					"                        bridges_progress    \r\n" + 
					"                    GROUP BY    \r\n" + 
					"                        structure_type, structure, actual_finish    \r\n" + 
					"                ) a    \r\n" + 
					"            GROUP BY    \r\n" + 
					"                structure_type    \r\n" + 
					"        ) a    \r\n" + 
					")    \r\n" + 
					",formation_count as (    \r\n" + 
					"  select works, sum(during_the_month) as during_the_month , round(sum(cum),2) as cummulative , round(sum(balance),2) as balance from    \r\n" + 
					"    \r\n" + 
					"(select case when structure_type = 'Formation' then [structure] when [Component] = 'Approach Cutting' OR [Component] = 'Heading' OR    \r\n" + 
					"[Component] = 'Final Lining' OR [Component] = 'Benching'     \r\n" + 
					"then a.[component] else [structure_type] end  as works , (during_the_month) , cum  , balance from    \r\n" + 
					"(select b.work_id ,b.structure_type , b.structure, b.component, b.during_the_month, b.cum,( a.scope-isnull(b.cum,0)) as balance from    \r\n" + 
					"(SELECT work_id , structure_type, structure,component,    \r\n" + 
					"    SUM(CASE     \r\n" + 
					"            WHEN structure_type = 'Formation'     \r\n" + 
					"                OR [Component] = 'Approach Cutting' OR [Component] = 'Heading'OR [Component] = 'Final Lining' OR [Component] = 'Benching'     \r\n" + 
					"            THEN scope ELSE 0 END ) AS scope    \r\n" + 
					"FROM activities_view    \r\n" + 
					"WHERE WORK_ID = 'P04W02'    \r\n" + 
					"GROUP BY  WORK_ID ,structure_type, structure,component) a    \r\n" + 
					"join    \r\n" + 
					"    \r\n" + 
					"(select work_id ,structure_type , structure, component,sum(during_the_month) as during_the_month, sum(completed_scope) as cum from    \r\n" + 
					"    \r\n" + 
					"(select a.work_id ,a.structure_type,a.structure,component, case when [Structure_type] = 'Formation' or [Component] = 'Approach Cutting' or [Component] = 'Heading'    \r\n" + 
					"or [Component] = 'Final Lining' or [Component] = 'Benching' then b.Result else 0 end as during_the_month ,CASE     \r\n" + 
					"            WHEN structure_type = 'Formation'  OR [Component] = 'Approach Cutting' OR [Component] = 'Heading'OR [Component] = 'Final Lining' OR [Component] = 'Benching' then    \r\n" + 
					"b.completed_scope else 0 end as completed_scope    \r\n" + 
					"from activities_view a    \r\n" + 
					" left join    \r\n" + 
					"(SELECT p6_activity_id_fk , completed_scope,    \r\n" + 
					"    CASE     \r\n" + 
					"        WHEN [progress_date] >= '"+obj.getFrom_date()+"'        -- comment : to change the date put in format of 'yyyy-mm-dd'     \r\n" + 
					"             AND [progress_date] < '"+obj.getTo_date()+"'             \r\n" + 
					"        THEN [completed_scope]     \r\n" + 
					"        ELSE 0     \r\n" + 
					"    END AS Result    \r\n" + 
					"FROM p6_activity_progress) b on a.activity_id = b.p6_activity_id_fk) a WHERE    \r\n" + 
					"work_id = 'P04W02'    \r\n" + 
					"group by work_id,structure_type , structure,component) b    \r\n" + 
					"    \r\n" + 
					"on a.structure_type = b.structure_type and a.structure=b.structure and a.work_id = b.work_id    \r\n" + 
					"and a.component=b.component) a ) b    \r\n" + 
					"group by works    \r\n" + 
					")    \r\n" + 
					"    \r\n" + 
					"select works,case when works in ( 'Final lining','heading','rock cutting','approach cutting','drain','benching', 'earthwork filling','railway earth filling',  \r\n" + 
					"'Blanketing', 'Retaining Wall', 'TSS Formation', 'Ballast') then case when works='Approach Cutting' then 'Cum'  when works='Heading' then 'Rm' when works='Benching' then 'Rm'  when works='Final Lining' then 'Rm'    else 'Cum' end else 'Nos' end as unit,\r\n" + 
					"case when a.works in ('Final lining','heading','rock cutting','approach cutting','drain','benching', 'earthwork filling','railway earth filling',\r\n" + 
					"'Blanketing', 'Retaining Wall', 'TSS Formation', 'Ballast') then a.during_the_month else b.during_the_month end as During_month,\r\n" + 
					"case when a.works in ('Final lining','heading','rock cutting','approach cutting','drain','benching', 'earthwork filling','railway earth filling',\r\n" + 
					"'Blanketing', 'Retaining Wall', 'TSS Formation', 'Ballast') then a.cummulative else b.cummulative end as Cumulative,\r\n" + 
					"case when a.works in ('Final lining','heading','rock cutting','approach cutting','drain','benching', 'earthwork filling','railway earth filling',\r\n" + 
					"'Blanketing', 'Retaining Wall', 'TSS Formation', 'Ballast') then a.balance else b.balance end as Balance\r\n" + 
					"from formation_count a left join structure_count b on b.structure_type = a.works";
			}
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
			String qry = "";
			if(obj.getWork_id_fk().compareTo("P04W01")==0)
			{
			
			 qry = "WITH bridges_progress AS (  \r\n" + 
			 		"    SELECT  \r\n" + 
			 		"        structure_type,  \r\n" + 
			 		"        structure,  \r\n" + 
			 		"        MAX(actual) AS actual_finish,  \r\n" + 
			 		"        SUM(result) AS bridge_progress  \r\n" + 
			 		"    FROM  \r\n" + 
			 		"        (  \r\n" + 
			 		"            SELECT  \r\n" + 
			 		"                structure_type,  \r\n" + 
			 		"                structure,  \r\n" + 
			 		"                MAX(actual_finish) AS actual,  \r\n" + 
			 		"                SUM([Weightage]  * [Completed] * 100) /  \r\n" + 
			 		"                (  \r\n" + 
			 		"                    [Scope] *  \r\n" + 
			 		"                    (  \r\n" + 
			 		"                        SELECT  \r\n" + 
			 		"                            SUM([Weightage])  \r\n" + 
			 		"                        FROM  \r\n" + 
			 		"                            activities_view  \r\n" + 
			 		"                        WHERE  \r\n" + 
			 		"                            [Work_Id] = T.[Work_Id] AND  \r\n" + 
			 		"                            [Contract_Id] = T.[Contract_Id] AND  \r\n" + 
			 		"                            [Structure_Type] = T.[Structure_Type] AND  \r\n" + 
			 		"                            [Structure] = T.[Structure]  \r\n" + 
			 		"                    )  \r\n" + 
			 		"                ) AS result  \r\n" + 
			 		"            FROM  \r\n" + 
			 		"                activities_view t  \r\n" + 
			 		"            WHERE  \r\n" + 
			 		"                work_id = 'P04W01'  \r\n" + 
			 		"            GROUP BY  \r\n" + 
			 		"                structure_type, weightage, completed, scope, work_id, contract_id, structure  \r\n" + 
			 		"        ) a  \r\n" + 
			 		"    GROUP BY  \r\n" + 
			 		"        structure_type, structure  \r\n" + 
			 		")  \r\n" + 
			 		"  \r\n" + 
			 		", structure_count AS (  \r\n" + 
			 		"    SELECT  \r\n" + 
			 		"        structure_type,  \r\n" + 
			 		"        during_the_month,  \r\n" + 
			 		"        cum AS cummulative,  \r\n" + 
			 		"        (scope - cum) AS balance  \r\n" + 
			 		"    FROM  \r\n" + 
			 		"        (  \r\n" + 
			 		"            SELECT  \r\n" + 
			 		"                structure_type,  \r\n" + 
			 		"                SUM(scope) AS scope,  \r\n" + 
			 		"                SUM(cumm) AS cum,  \r\n" + 
			 		"                SUM(during_month) AS during_the_month  \r\n" + 
			 		"            FROM  \r\n" + 
			 		"                (  \r\n" + 
			 		"                    SELECT  \r\n" + 
			 		"                        structure_type,  \r\n" + 
			 		"                        structure,  \r\n" + 
			 		"                        COUNT(DISTINCT structure) AS scope,  \r\n" + 
			 		"                        CASE WHEN MIN(bridge_progress) >= 93 THEN COUNT(DISTINCT structure) ELSE 0 END AS cumm,  \r\n" + 
			 		"                        CASE WHEN MIN(bridge_progress) >= 93 AND \r\n" + 
			 		"						actual_finish >= '"+obj.getFrom_date()+"'                                      -- comment :to change the date put in format of 'yyyy - mm- dd(it will change during period value)'\r\n" + 
			 		"						AND (actual_finish) <  '"+obj.getTo_date()+"'  \r\n" + 
			 		"                        \r\n" + 
			 		"                             THEN COUNT(DISTINCT structure) ELSE 0 END AS during_month  \r\n" + 
			 		"                    FROM  \r\n" + 
			 		"                        bridges_progress  \r\n" + 
			 		"                    GROUP BY  \r\n" + 
			 		"                        structure_type, structure, actual_finish  \r\n" + 
			 		"                ) a  \r\n" + 
			 		"            GROUP BY  \r\n" + 
			 		"                structure_type  \r\n" + 
			 		"        ) a  \r\n" + 
			 		")  \r\n" + 
			 		",formation_count as (  \r\n" + 
			 		"  select works, sum(during_the_month) as during_the_month , round(sum(cum),2) as cummulative , round(sum(balance),2) as balance from  \r\n" + 
			 		"(select case when structure_type = 'Formation' then [structure] else [structure_type] end  as works , (during_the_month) , cum  , balance from  \r\n" + 
			 		"(select b.work_id ,b.structure_type , b.structure, b.during_the_month, b.cum,( a.scope-isnull(b.cum,0)) as balance from  \r\n" + 
			 		"(SELECT work_id , structure_type, structure,  \r\n" + 
			 		"    SUM(CASE   \r\n" + 
			 		"            WHEN structure_type = 'Formation'   \r\n" + 
			 		"            THEN scope ELSE 0 END ) AS scope  \r\n" + 
			 		"FROM activities_view  \r\n" + 
			 		"WHERE WORK_ID = 'P04W01'  \r\n" + 
			 		"GROUP BY  WORK_ID ,structure_type, structure) a  \r\n" + 
			 		"join  \r\n" + 
			 		"(select work_id ,structure_type , structure,sum(during_the_month) as during_the_month, sum(completed_scope) as cum from  \r\n" + 
			 		"(select a.work_id ,a.structure_type,a.structure, case when [Structure_type] = 'Formation' then b.Result else 0 end as during_the_month ,CASE   \r\n" + 
			 		"            WHEN structure_type = 'Formation' then  \r\n" + 
			 		"b.completed_scope else 0 end as completed_scope  \r\n" + 
			 		"from activities_view a  \r\n" + 
			 		" left join  \r\n" + 
			 		"(SELECT p6_activity_id_fk , completed_scope,  \r\n" + 
			 		"    CASE   \r\n" + 
			 		"        WHEN [progress_date] >= '"+obj.getFrom_date()+"'                                                       -- comment :to change the date put in format of 'yyyy - mm- dd'  \r\n" + 
			 		"             AND [progress_date] < '"+obj.getTo_date()+"'   \r\n" + 
			 		"        THEN [completed_scope]   \r\n" + 
			 		"        ELSE 0   \r\n" + 
			 		"    END AS Result  \r\n" + 
			 		"FROM p6_activity_progress) b on a.activity_id = b.p6_activity_id_fk) a WHERE  \r\n" + 
			 		"work_id = 'P04W01'  \r\n" + 
			 		"group by work_id,structure_type , structure) b  \r\n" + 
			 		"on a.structure_type = b.structure_type and a.structure=b.structure and a.work_id = b.work_id) a ) b  \r\n" + 
			 		"group by works  \r\n" + 
			 		")  \r\n" + 
			 		"  \r\n" + 
			 		"select distinct a.works as works, case when a.works in ( 'construction of drain','earthwork cutting', 'earthwork filling',  \r\n" + 
			 		"'Blanketing') then c.unit else 'Nos' end as unit,  \r\n" + 
			 		"case when a.works in ('construction of drain','earthwork cutting', 'earthwork filling',  \r\n" + 
			 		"'Blanketing') then a.during_the_month else b.during_the_month end as during_month,  \r\n" + 
			 		"case when a.works in ('construction of drain','earthwork cutting', 'earthwork filling',  \r\n" + 
			 		"'Blanketing') then a.cummulative else b.cummulative end as cummulative,  \r\n" + 
			 		"case when a.works in ('construction of drain','earthwork cutting', 'earthwork filling',  \r\n" + 
			 		"'Blanketing') then a.balance else b.balance end as balance  \r\n" + 
			 		"from formation_count a left join structure_count b on b.structure_type = a.works  \r\n" + 
			 		"left join activities_view c on c.structure = a.works    ";
			
			}

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> generateTPCStructureList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select structure,sum(CStructureCount) as 'CScope',sum(CCompletedCount) as CCompleted,sum(CStructureCount)-sum(CCompletedCount) as 'CBalance',sum(CWIP) as CWIP,\r\n" + 
					"sum(WStructureCount) as 'WScope',sum(WCompletedCount) as 'WCompleted',sum(WStructureCount)-sum(WCompletedCount) as 'WBalance',sum(WWIP) as WWIP from(\r\n" + 
					"select replace(replace(contract_short_name,'(',''),')','') as contract_short_name,replace(replace(structure,'(',''),')','') as structure,cprogress,wprogress,CStatus,WStatus,\r\n" + 
					"\r\n" + 
					"case when contract_short_name like '%CR%' and CStatus='Completed' then count(CStatus) else 0 end AS 'CCompletedCount',case when contract_short_name like '%CR%' and CStatus='In Progress' then count(CStatus) else 0 end AS 'CWIP', \r\n" + 
					"case when contract_short_name like '%WR%' and WStatus='Completed' then count(WStatus) else 0 end AS 'WCompletedCount',case when contract_short_name like '%WR%' and WStatus='In Progress' then count(WStatus) else 0 end AS 'WWIP' \r\n" + 
					",case when contract_short_name like '%CR%' THEN count(Cstructure) else 0 end as CStructureCount\r\n" + 
					",case when contract_short_name like '%WR%' THEN count(Wstructure) else 0 end as WStructureCount\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"from(\r\n" + 
					"\r\n" + 
					"select  substring(c.contract_short_name,CHARINDEX('(',c.contract_short_name),CHARINDEX(')',c.contract_short_name)-CHARINDEX('(',c.contract_short_name)+1) as contract_short_name,substring(structure,LEN(structure) - CHARINDEX('(',REVERSE(structure)), LEN(structure) - CHARINDEX(')',REVERSE(structure)))   as structure,\r\n" + 
					"\r\n" + 
					"case when c.contract_short_name like '%CR%' then round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),2) else 0 end AS cprogress,\r\n" + 
					"case when c.contract_short_name like '%WR%' then round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),2) else 0 end AS wprogress,\r\n" + 
					"case when c.contract_short_name like '%CR%' and SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed * weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as CStatus,\r\n" + 
					"case when c.contract_short_name like '%WR%' and SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed * weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as WStatus\r\n" + 
					",case when c.contract_short_name like '%CR%' then count(structure) else 0 end as Cstructure\r\n" + 
					",case when c.contract_short_name like '%WR%' then count(structure) else 0 end as Wstructure\r\n" + 
					"\r\n" + 
					"FROM activities_view a    \r\n" + 
					"LEFT join contract c on c.contract_id=a.contract_id    \r\n" + 
					"where a.contract_id like 'P04W04EN%' and structure not in('Badlapur (Deck)') and structure not in('Khar Road (New FOB)') \r\n" + 
					"and substring(structure,CHARINDEX('(',structure),CHARINDEX(')',structure)-CHARINDEX('(',structure)+1)!=''  group by contract_short_name,structure) as m\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"group by m.contract_short_name,m.structure,m.cprogress,m.wprogress,m.CStatus,m.WStatus,M.Cstructure,M.Wstructure) AS l\r\n" + 
					"\r\n" + 
					"group by structure ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> generateTPCStructureCumList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select structure,sum(CStructureCount+WStructureCount) as 'Scope',sum(CCompletedCount+WCompletedCount) as Completed,sum(CStructureCount+WStructureCount)-sum(CCompletedCount+WCompletedCount) as 'Balance',sum(CWIP+WWIP) as WIP from(\r\n" + 
					"select replace(replace(contract_short_name,'(',''),')','') as contract_short_name,replace(replace(structure,'(',''),')','') as structure,cprogress,wprogress,CStatus,WStatus,\r\n" + 
					"\r\n" + 
					"case when contract_short_name like '%CR%' and CStatus='Completed' then count(CStatus) else 0 end AS 'CCompletedCount',case when contract_short_name like '%CR%' and CStatus='In Progress' then count(CStatus) else 0 end AS 'CWIP', \r\n" + 
					"case when contract_short_name like '%WR%' and WStatus='Completed' then count(WStatus) else 0 end AS 'WCompletedCount',case when contract_short_name like '%WR%' and WStatus='In Progress' then count(WStatus) else 0 end AS 'WWIP' \r\n" + 
					",case when contract_short_name like '%CR%' THEN count(Cstructure) else 0 end as CStructureCount\r\n" + 
					",case when contract_short_name like '%WR%' THEN count(Wstructure) else 0 end as WStructureCount\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"from(\r\n" + 
					"\r\n" + 
					"select  substring(c.contract_short_name,CHARINDEX('(',c.contract_short_name),CHARINDEX(')',c.contract_short_name)-CHARINDEX('(',c.contract_short_name)+1) as contract_short_name,substring(structure,LEN(structure) - CHARINDEX('(',REVERSE(structure)), LEN(structure) - CHARINDEX(')',REVERSE(structure)))   as structure,\r\n" + 
					"\r\n" + 
					"case when c.contract_short_name like '%CR%' then round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),2) else 0 end AS cprogress,\r\n" + 
					"case when c.contract_short_name like '%WR%' then round(cast((isnull(SUM((completed * weightage)*100 / scope) / SUM(weightage),0)) as decimal(10,2)),2) else 0 end AS wprogress,\r\n" + 
					"case when c.contract_short_name like '%CR%' and SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed * weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as CStatus,\r\n" + 
					"case when c.contract_short_name like '%WR%' and SUM((completed * weightage)*100 / scope) / SUM(weightage)=100 then 'Completed' when SUM((completed * weightage)*100 / scope) / SUM(weightage)<100 and SUM((completed * weightage)*100 / scope) / SUM(weightage)*100>0 then 'In Progress' else 'Not Started' end as WStatus\r\n" + 
					",case when c.contract_short_name like '%CR%' then count(structure) else 0 end as Cstructure\r\n" + 
					",case when c.contract_short_name like '%WR%' then count(structure) else 0 end as Wstructure\r\n" + 
					"\r\n" + 
					"FROM activities_view a    \r\n" + 
					"LEFT join contract c on c.contract_id=a.contract_id    \r\n" + 
					"where a.contract_id like 'P04W04EN%' and structure not in('Badlapur (Deck)') and structure not in('Khar Road (New FOB)') \r\n" + 
					"and substring(structure,CHARINDEX('(',structure),CHARINDEX(')',structure)-CHARINDEX('(',structure)+1)!=''  group by contract_short_name,structure) as m\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"group by m.contract_short_name,m.structure,m.cprogress,m.wprogress,m.CStatus,m.WStatus,M.Cstructure,M.Wstructure) as l\r\n" + 
					"\r\n" + 
					"group by structure ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getWorksListForSelectedProject(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String Str=obj.getProject_id().toString();
			String StrVal=Str.replaceAll("%2C", "','");
			String qry = "SELECT  work_id as work_id_fk,work_short_name from p6_activities a "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "left join work w on work_id = c.work_id_fk "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where c.work_id_fk is not null and c.work_id_fk <> '' and w.project_id_fk in('"+StrVal+"') ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			/*if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProjects())) {
				qry = qry + " and w.project_id_fk in (?";
				arrSize++;
				int length = obj.getProjects().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				qry = qry + " ) ";
			}*/
		
			qry = qry + " GROUP BY work_id,work_short_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			/*if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProjects())) {
				pValues[i++] = obj.getProjects()[0];
				int length = obj.getProjects().length;
				if(length > 1) {
					for(int j =1; j< length; j++) {
						pValues[i++] = obj.getProjects()[j];
					}
				}
			}*/
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	
	
}
