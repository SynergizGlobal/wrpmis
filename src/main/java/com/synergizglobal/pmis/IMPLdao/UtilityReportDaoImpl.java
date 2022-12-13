package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.UtilityReportDao;
import com.synergizglobal.pmis.model.UtilityShifting;
@Repository
public class UtilityReportDaoImpl implements UtilityReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT execution_agency_fk from utility_shifting us " + 
					"left join work w on us.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"where us.execution_agency_fk is not null and us.execution_agency_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				qry = qry + " and execution_agency_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and us.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY execution_agency_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				pValues[i++] = obj.getExecution_agency_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT project_id as project_id_fk,project_name from utility_shifting us " + 
					"left join work w on us.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"where us.work_id_fk is not null and us.work_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				qry = qry + " and execution_agency_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and us.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY project_id,project_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				pValues[i++] = obj.getExecution_agency_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT work_id_fk,work_short_name from utility_shifting us " + 
					"left join work w on us.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"where us.execution_agency_fk is not null and us.execution_agency_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				qry = qry + " and execution_agency_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and us.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY work_id_fk,work_short_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				pValues[i++] = obj.getExecution_agency_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT work_short_name,work_id_fk,count(*) as utilities,execution_agency_fk, (select count(*)\r\n" + 
					"FROM utility_shifting u \r\n" + 
					"left join work w on u.work_id_fk = w.work_id \r\n" + 
					"left join project p on w.project_id_fk = p.project_id \r\n" + 
					"	where execution_agency_fk is not null and execution_agency_fk <> '' and shifting_status_fk='Completed') as remaining,\r\n" + 
					"count(*)-(select count(*)\r\n" + 
					"FROM utility_shifting u \r\n" + 
					"left join work w on u.work_id_fk = w.work_id \r\n" + 
					"left join project p on w.project_id_fk = p.project_id \r\n" + 
					"	where execution_agency_fk is not null and execution_agency_fk <> '' and shifting_status_fk='Completed') as balance \r\n" + 
					"FROM utility_shifting u \r\n" + 
					"left join work w on u.work_id_fk = w.work_id \r\n" + 
					"left join project p on w.project_id_fk = p.project_id \r\n" + 
					"	where execution_agency_fk is not null and execution_agency_fk <> '' ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				qry = qry + " and execution_agency_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and u.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by work_short_name,work_id_fk,execution_agency_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
				pValues[i++] = obj.getExecution_agency_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
			obj.setReport1List(objsList);
			if(objsList.size() > 0) {
					
				/*String qry2 = "SELECT id, utility_shifting_id, work_id_fk,work_short_name, identification, location_name, "
						+ "reference_number, utility_description, utility_type_fk, utility_category_fk, owner_name, execution_agency_fk, "
						+ "contract_id_fk, start_date, scope, completed, shifting_status_fk, shifting_completion_date, "
						+ "impacted_contract_id_fk, requirement_stage_fk, FORMAT(planned_completion_date,'dd-MM-yyyy') as planned_completion_date, unit_fk " + 
						"from utility_shifting u "
						+ "left join work w on u.work_id_fk = w.work_id "
						+ "left join project p on w.project_id_fk = p.project_id "
						+ " where utility_shifting_id is not null and utility_shifting_id <> '' ";*/
				
				String qry2 = "SELECT distinct s.*,s.id, s.utility_shifting_id, s.work_id_fk,w.work_short_name,w.work_name,w.project_id_fk,p.project_name,c.contract_short_name,FORMAT(s.identification ,'dd-MM-yyyy') AS  identification, s.location_name, reference_number, utility_description, utility_type_fk, "
						+ "utility_category_fk, s.owner_name, execution_agency_fk, contract_id_fk,  FORMAT(s.start_date ,'dd-MM-yyyy') AS start_date, s.scope, s.completed, s.shifting_status_fk, FORMAT(shifting_completion_date ,'dd-MM-yyyy') AS shifting_completion_date, "
						+ "s.remarks, s.latitude, s.longitude, impacted_contract_id_fk, requirement_stage_fk, FORMAT(s.planned_completion_date ,'dd-MM-yyyy') AS planned_completion_date, unit_fk, s.created_by, s.created_date, s.modified_by,"
						+ " s.modified_date,custodian,executed_by,impacted_element,affected_structures,c.contract_id,c.contract_name,c.contract_short_name,w.work_name,w.project_id_fk,p.project_name,"
						+ "w.work_short_name,s.hod_user_id_fk,u.user_name,u.designation,chainage,"
						+ "(SELECT s1.progress_date FROM utility_shifting_progress s1 where s1.progress_date = (select max(s2.progress_date) from utility_shifting_progress s2 where s2.utility_shifting_id = s.utility_shifting_id group by s2.utility_shifting_id) and s1.utility_shifting_id = s.utility_shifting_id) as latest_progress_date, "
						+ "(SELECT s1.progress_of_work FROM utility_shifting_progress s1 where s1.progress_date = (select max(s2.progress_date) from utility_shifting_progress s2 where s2.utility_shifting_id = s.utility_shifting_id group by s2.utility_shifting_id) and s1.utility_shifting_id = s.utility_shifting_id) as latest_progress_update "
						+ " from utility_shifting s "					
						+ "LEFT OUTER JOIN contract c ON s.impacted_contract_id_fk  = c.contract_id "
						+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
						+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "
						+ "LEFT OUTER JOIN utility_shifting_executives us on w.work_id = us.work_id_fk "
						+ "LEFT OUTER JOIN [user] u on s.hod_user_id_fk = u.user_id "
						+ " where utility_shifting_id is not null and utility_shifting_id <> '' ";
				
				
				
				int arrSize1 = 0;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
					qry2 = qry2 + " and execution_agency_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry2 = qry2 + " and u.work_id_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					qry2 = qry2 + " and w.project_id_fk = ?";
					arrSize1++;
				}
			
				
				Object[] pValues1 = new Object[arrSize1];
				int j = 0;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getExecution_agency_fk())) {
					pValues1[j++] = obj.getExecution_agency_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues1[j++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					pValues1[j++] = obj.getProject_id_fk();
				}
				
				List<UtilityShifting>  objsList1 = jdbcTemplate.query( qry2,pValues1, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
				obj.setReport2List(objsList1);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return obj;
	}

}
