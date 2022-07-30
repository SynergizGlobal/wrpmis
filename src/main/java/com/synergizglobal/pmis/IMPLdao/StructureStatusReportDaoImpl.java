package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.synergizglobal.pmis.Idao.StructureStatusReportDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
@Repository
public class StructureStatusReportDaoImpl implements StructureStatusReportDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String contractsQry = "select contract_id,c.work_id_fk,contract_name,contract_short_name,contractor_id_fk, work_name,work_short_name,contractor_name,f.work_status_fk,f.structure_type_fk as structure_type " + 
					"from contract c " + 
					"left outer join work w on work_id_fk = work_id " + 
					"left join p6_activities a on c.contract_id = a.contract_id_fk "
					+"left join structure f on f.structure_id = a.structure_id_fk " + 
					"left outer join contractor cr on contractor_id_fk = contractor_id " + 
					"where contract_id is not null and f.structure_type_fk<>'FOB' " ;
			int arrSize = 0;

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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				contractsQry = contractsQry + " and f.structure_type_fk = ?";
				arrSize++;
			}
			contractsQry = contractsQry + " GROUP BY f.structure_type_fk ORDER BY FIELD(component,'New FOB site on PF','PF and service buildings','New Constructed FOB','New Constructed  FOB','PF sheds Under new FOB','Dismantling of old & unservicable FOB','PF s cover shed of dismantalling FOB','Station')";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			String strType=obj.getStructure_type_fk();
			objsList = jdbcTemplate.query( contractsQry,pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
			
			obj.setWork_id_fk(objsList.get(0).getWork_id_fk());
			obj.setWork_name(objsList.get(0).getWork_short_name());
			obj.setContract_id(objsList.get(0).getContract_id());
			obj.setContract_name(objsList.get(0).getContract_short_name());
			obj.setContractor_name(objsList.get(0).getContractor_name());
			
			/***********************************************************************/
			String structureQry = "select distinct a.contract_id_fk,contract_id,s.structure as fob_id_fk,s.structure as fob_name,s.structure_type_fk as structure_type "
					+ "from p6_activities a left join structure s on s.structure_id = a.structure_id_fk "
					+ "left join contract c on a.contract_id_fk = c.contract_id "  
					+ "where a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
			arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(strType)) {
				structureQry = structureQry + " and s.structure_type_fk = ?";
				arrSize++;
			}
			//structureQry = structureQry + " GROUP BY a.structure_type_fk ";
			pValues = new Object[arrSize];
			i = 0;
			pValues[i++] = obj.getContract_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(strType)) {
				pValues[i++] = strType;
			}
			List<ActivitiesProgressReport> structuresList = jdbcTemplate.query( structureQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
			obj.setStructuressList(structuresList);
			
			/***********************************************************************/
			for (ActivitiesProgressReport componentObj : structuresList) {
			String progressStructuresQry = "select distinct a.contract_id_fk,contract_id,s.structure as fob_id_fk,component "
						+ "from p6_activities a left join structure s on s.structure_id = a.structure_id_fk "
						+ "left join contract c on a.contract_id_fk = c.contract_id "  
						+ "where a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
				arrSize = 1;
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getStructure_type())) {
					progressStructuresQry = progressStructuresQry + " and s.structure_type_fk =?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
					progressStructuresQry = progressStructuresQry + " and s.structure =?";
					arrSize++;
				}				
				//progressStructuresQry = progressStructuresQry + " GROUP BY a.component ORDER BY FIELD(component,'Station') asc";
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = obj.getContract_id();
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getStructure_type())) {
					pValues[i++] = componentObj.getStructure_type();
				}
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
					pValues[i++] = componentObj.getFob_id_fk();
				}				
				List<ActivitiesProgressReport> contractProgressStructuresList = jdbcTemplate.query( progressStructuresQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
				componentObj.setComponentsList(contractProgressStructuresList);
			
				/**********************************************************************************************************************************/				
				
				for (ActivitiesProgressReport contractProgressStructure : contractProgressStructuresList) {
					String contractProgressDatesQry = "select distinct p6_activity_name as activity_name,component_id,FORMAT(baseline_start,'dd-MM-yyyy') AS planned_start,FORMAT(baseline_finish,'dd-MM-yyyy') AS planned_finish,case \r\n" + 
							"	   when (ISNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then ''\r\n" + 
							"	   when ISNULL(NULLIF(completed, '' ), 0)>=ISNULL(NULLIF(scope, '' ), 0) then (select FORMAT(min(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id)\r\n" + 
							"	   else (select FORMAT(min(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) end as actual_start,case \r\n" + 
							"	   when (ISNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then ''\r\n" + 
							"	   when ISNULL(NULLIF(completed, '' ), 0)>=ISNULL(NULLIF(scope, '' ), 0) then (select FORMAT(max(progress_date),'dd-MM-yyyy') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id)\r\n" + 
							"	   else '' end as actual_finish,unit,ISNULL(NULLIF(scope, '' ), 0) AS scope,ISNULL(NULLIF(completed, '' ), 0) AS completed,a.contract_id_fk,work_id,project_id,project_name "
							+ "from  p6_activities a left join structure s on s.structure_id = a.structure_id_fk "
							+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
							+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
							+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
							+ "where a.scope>0 and a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
					
					arrSize = 1;
					
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getComponent())) {
						contractProgressDatesQry = contractProgressDatesQry + " and a.component = ?";
						arrSize++;
					}
					if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getStructure_type())) {
						contractProgressDatesQry = contractProgressDatesQry + " and s.structure_type_fk = ?";
						arrSize++;
					}
					if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
						contractProgressDatesQry = contractProgressDatesQry + " and s.structure =?";
						arrSize++;
					}						
					
					contractProgressDatesQry=contractProgressDatesQry+" ORDER BY component_id,activity_name ASC";
					pValues = new Object[arrSize];
					i = 0;
					pValues[i++] = obj.getContract_id();
					
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getComponent())) {
						pValues[i++] = contractProgressStructure.getComponent();
					}
					if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getStructure_type())) {
						pValues[i++] = componentObj.getStructure_type();
					}
					if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
						pValues[i++] = componentObj.getFob_id_fk();
					}						
					
					List<ActivitiesProgressReport> contractProgressDatesList = jdbcTemplate.query( contractProgressDatesQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
					
					contractProgressStructure.setActivitiessList(contractProgressDatesList);
				}
			}	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return obj;
	}

	@Override
	public List<ActivitiesProgressReport> getStructuresList(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qryDetails = "select structure as fob_id_fk,contract_id_fk as contract_id from p6_activities a left join structure s on s.structure_id = a.structure_id_fk "
					+"where contract_id_fk = ? and s.structure_type_fk<>'FOB' group by structure";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getContract_id()}, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT p.project_id,p.project_name "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where project_id is not null and s.structure is not null and s.structure <> '' and project_id <> '' and s.structure_type_fk<>'FOB' ";
			
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
			
			qry = qry + " GROUP BY p.project_id ORDER BY FIELD(a.component,'New FOB site on PF','PF and service buildings','New Constructed FOB','New Constructed  FOB','PF sheds Under new FOB','Dismantling of old & unservicable FOB','PF s cover shed of dismantalling FOB','Station')";
			
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT c.work_id_fk,w.work_id,w.work_name,w.work_short_name "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where w.work_id is not null and s.structure is not null and s.structure <> '' and w.work_id <> '' and s.structure_type_fk<>'FOB' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY w.work_id ORDER BY w.work_id ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				pValues[i++] = obj.getProject_id();
			}	
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT w.project_id_fk as project_id,c.work_id_fk as work_id,c.contract_id,c.contract_name,c.contract_short_name "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.contract_id is not null and c.contract_id <> '' and s.structure_type_fk<>'FOB' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and c.work_id_fk = ?";
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
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT s.structure as fob_id,s.structure_name as fob_name "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where s.structure is not null and s.structure <> '' and s.structure_type_fk<>'FOB' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY s.structure ORDER BY s.structure ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getContractsListInActivities() throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qryDetails = "select contract_id_fk as contract_id,c.contract_short_name from p6_activities a left join structure s on s.structure_id = a.structure_id_fk "
					+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
					+"where contract_id_fk is not null and contract_id_fk <> '' and s.structure_type_fk<>'FOB' group by contract_id";
			
			objsList = jdbcTemplate.query(qryDetails, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {			
			String qry = "SELECT user_id,user_name,designation "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.hod_user_id_fk is not null and c.hod_user_id_fk <> '' and s.structure_type_fk<>'FOB' ";
			
			
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
				qry = qry + " and s.structure = ?";
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
	public List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN [user] u on c.dy_hod_user_id_fk = u.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.dy_hod_user_id_fk is not null and c.dy_hod_user_id_fk <> ''  and s.structure_type_fk<>'FOB' ";
			
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
				qry = qry + " and s.structure = ?";
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
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			
			String qry = "SELECT c.contractor_id_fk,contractor_id,contractor_name "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN contractor ctr on c.contractor_id_fk = ctr.contractor_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.contractor_id_fk is not null and c.contractor_id_fk <> '' and s.structure_type_fk<>'FOB' ";
			
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
				qry = qry + " and s.structure = ?";
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
	public Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj)
			throws Exception {
		//Map<ActivitiesProgressReport, List<ActivitiesProgressReport>> mapObjsList = new HashMap<ActivitiesProgressReport, List<ActivitiesProgressReport>>();
		Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> mapObjsList = new LinkedHashMap<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>>();
		NumberFormat numberFormatter = new DecimalFormat("#0.0000");
		try {
			
			String contractsQry = "select p6_activity_id_fk as activity_id_fk,a.contract_id_fk,c.contract_short_name,work_id,project_id,project_name,a.unit,s.structure_type_fk as structure_type "
					+ "from p6_activity_progress ap " 
					+ "LEFT JOIN p6_activities a on p6_activity_id_fk = p6_activity_id left join structure s on s.structure_id = a.structure_id_fk "
					+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
					+ "where progress_date is not null and completed_scope is not null and s.structure_type_fk<>'FOB' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				contractsQry = contractsQry + " and progress_date >= ? and progress_date <= ?";
				arrSize++;
				arrSize++;
			}else {
				contractsQry = contractsQry + " and progress_date =  ?";
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
				contractsQry = contractsQry + " and c.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				contractsQry = contractsQry + " and s.structure_type_fk = ?";
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
			
			contractsQry = contractsQry + " GROUP BY s.structure_type_fk ORDER BY a.contract_id_fk ASC";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
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
				String progressStructuresQry = "select ap.p6_activity_id_fk as activity_id_fk,a.contract_id_fk,contract_short_name,s.structure,a.unit,s.structure_type_fk as structure_type  "
						+ "from p6_activity_progress ap "  
						+ "left join p6_activities a on ap.p6_activity_id_fk = a.p6_activity_id left join structure s on s.structure_id = a.structure_id_fk "
						+ "left join contract c on a.contract_id_fk = c.contract_id "  
						+ "where completed_scope is not null and a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
				
				
				arrSize = 1;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
					progressStructuresQry = progressStructuresQry + " and progress_date >= ? and progress_date <= ?";
					arrSize++;
					arrSize++;
				}else {
					progressStructuresQry = progressStructuresQry + " and progress_date = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
					progressStructuresQry = progressStructuresQry + " and s.structure_type_fk = ?";
					arrSize++;
				}
				
				progressStructuresQry = progressStructuresQry + " GROUP BY s.structure ORDER BY s.structure ASC";
				
				pValues = new Object[arrSize];
				
				i = 0;
				pValues[i++] = cObj.getContract_id_fk();
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
					pValues[i++] = obj.getFrom_date();
					pValues[i++] = obj.getTo_date();
				}else {
					pValues[i++] = obj.getFrom_date();
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
					pValues[i++] = obj.getStructure_type_fk();
				}
				
				List<ActivitiesProgressReport> contractProgressStructuresList = jdbcTemplate.query( progressStructuresQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
				
				
				/**********************************************************************************************************************************/				
				
				
				for (ActivitiesProgressReport contractProgressStructure : contractProgressStructuresList) {
					String contractProgressDatesQry = "select p6_activity_id_fk as activity_id_fk,MAX(ap.progress_date) AS progress_date,a.contract_id_fk,contract_short_name,work_id,project_id,project_name,a.unit,s.structure_type_fk as structure_type   "
							+ "from p6_activity_progress ap " 
							+ "LEFT JOIN p6_activities a on p6_activity_id_fk = p6_activity_id  left join structure s on s.structure_id = a.structure_id_fk "
							+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
							+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
							+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
							+ "where completed_scope is not null and a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
					
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
						contractProgressDatesQry = contractProgressDatesQry + " and s.structure = ?";
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
						
						String progressQry = "select distinct ap.progress_date,ap.p6_activity_id_fk as activity_id_fk,sum(ap.completed_scope) as completed_scope,a.p6_activity_id as activity_id,a.contract_id_fk,s2.structure_type_fk as structure_type,s2.structure_type_fk,a.component_id," + 
								"a.component,a.p6_activity_name as activity_name,a.unit,s2.structure,a.scope,a.completed,c.contract_name,c.contract_short_name," + 
								"(a.completed - ISNULL((select sum(completed_scope) " + 
								"from p6_activity_progress ap1 " + 
								"left outer join p6_activities a1 on ap1.p6_activity_id_fk = a1.p6_activity_id "
								+ "left join structure s on s.structure_id = a1.structure_id_fk "+ 
								"left outer join contract c1 on a1.contract_id_fk = c1.contract_id " + 
								"where ap1.completed_scope is not null and s.structure_type_fk<>'FOB' and a1.contract_id_fk = ? and s.structure = ? and ap1.progress_date > ? and ap1.p6_activity_id_fk = ap.p6_activity_id_fk),0)) as cumulative_completed " + 
								"from p6_activity_progress ap " + 
								"left outer join p6_activities a on ap.p6_activity_id_fk = a.p6_activity_id left join structure s2 on s2.structure_id = a.structure_id_fk " +
								"left outer join contract c on a.contract_id_fk = c.contract_id " + 
								"where completed_scope is not null and a.contract_id_fk = ? and s2.structure = ? and s2.structure_type_fk<>'FOB' ";
						
						arrSize = 5;
						
						if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
							progressQry = progressQry + " and ap.progress_date >= ? and ap.progress_date <= ?";
							arrSize++;
							arrSize++;
						}else {
							progressQry = progressQry + " and ap.progress_date = ?";
							arrSize++;
						}
						
						progressQry = progressQry + " group by ap.p6_activity_id_fk";
						
						pValues = new Object[arrSize];
						
						i = 0;
						pValues[i++] = cObj.getContract_id_fk();
						pValues[i++] = contractProgressStructure.getStructure();
						pValues[i++] = contractProgressDate.getProgress_date();
						pValues[i++] = cObj.getContract_id_fk();
						pValues[i++] = contractProgressStructure.getStructure();
						
						if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
							pValues[i++] = obj.getFrom_date();
							pValues[i++] = obj.getTo_date();
						}else {
							pValues[i++] = obj.getFrom_date();
						}
						
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

	@Override
	public String getActivitiesRemarks(String structure, String from_date) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String remarks = null;
			try {
				con = dataSource.getConnection();
				String qry = "select remarks from fobdailyupdate where structure =? and reporting_date = ?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,structure);
				stmt.setString(2,from_date);
				rs = stmt.executeQuery(); 
				while(rs.next()) 
				{
					remarks=rs.getString("remarks");
				}
			
				
			}catch(Exception e){ 
				throw new Exception(e.getMessage());
			}
			finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}
			return remarks;
		}

	@Override
	public String getContractorName(String contract_id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String contractorname = null;
			try {
				con = dataSource.getConnection();
				String qry = "select contractor_name from contractor where contractor_id =?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,contract_id);
				rs = stmt.executeQuery(); 
				while(rs.next()) 
				{
					contractorname=rs.getString("contractor_name");
				}
			
				
			}catch(Exception e){ 
				throw new Exception(e.getMessage());
			}
			finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}
			return contractorname;
	}
	
	@Override
	public String getWorkName(String work_id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String workname = null;
			try {
				con = dataSource.getConnection();
				String qry = "select work_short_name from work where work_id =?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,work_id);
				rs = stmt.executeQuery(); 
				while(rs.next()) 
				{
					workname=rs.getString("work_short_name");
				}
			
				
			}catch(Exception e){ 
				throw new Exception(e.getMessage());
			}
			finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}
			return workname;
	}
	
	@Override
	public String getContractName(String contract_id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String contractname = null;
			try {
				con = dataSource.getConnection();
				String qry = "select contract_short_name from contract where contract_id =?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,contract_id);
				rs = stmt.executeQuery(); 
				while(rs.next()) 
				{
					contractname=rs.getString("contract_short_name");
				}
			
				
			}catch(Exception e){ 
				throw new Exception(e.getMessage());
			}
			finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}
			return contractname;
	}

	@Override
	public List<ActivitiesProgressReport> getContarctDetaisl(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT contract_id,contract_short_name,w.work_id ,c.work_id_fk,w.work_name,w.work_short_name,u1.designation as hod_designation,u.designation as dyhod_designation,cr.contractor_name,c.dy_hod_user_id_fk as dyhod ,c.hod_user_id_fk as hod,c.contractor_id_fk as contractor_id,s.structure_type_fk "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN [user] u on c.dy_hod_user_id_fk = u.user_id " +
					"LEFT JOIN [user] u1 on c.hod_user_id_fk = u1.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"LEFT JOIN contractor cr on c.contractor_id_fk = cr.contractor_id " +
					"where c.dy_hod_user_id_fk is not null and c.dy_hod_user_id_fk <> '' and s.structure_type_fk<>'FOB' ";
			
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
				qry = qry + " and s.structure = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and s.structure_type_fk = ?";
				arrSize++;
			}			
			
			qry = qry + " GROUP BY s.structure_type_fk ";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ActivitiesProgressReport> getAllContractDetails(ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objsList = null;
		try {
			String qry = "SELECT contract_id,contract_short_name,w.work_id ,c.work_id_fk,w.work_name,w.work_short_name,u1.designation as hod_designation,u.designation as dyhod_designation,cr.contractor_name,c.dy_hod_user_id_fk as dyhod ,c.hod_user_id_fk as hod,c.contractor_id_fk as contractor_id "+
					"from p6_activities a left join structure s on s.structure_id = a.structure_id_fk " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN [user] u on c.dy_hod_user_id_fk = u.user_id " +
					"LEFT JOIN [user] u1 on c.hod_user_id_fk = u1.user_id " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"LEFT JOIN contractor cr on c.contractor_id_fk = cr.contractor_id " +
					"where c.dy_hod_user_id_fk is not null and c.dy_hod_user_id_fk <> '' and s.structure_type_fk<>'FOB' ";
			
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
				qry = qry + " and s.structure = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and s.structure_type_fk = ?";
				arrSize++;
			}			
			
			qry = qry + " GROUP BY contract_id order by s.structure_type_fk ";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<ActivitiesProgressReport> getStructureRemarks(ActivitiesProgressReport obj) throws Exception {
			Map<String,List<ActivitiesProgressReport>> structureProgresses = new LinkedHashMap<String, List<ActivitiesProgressReport>>();
	
			ActivitiesProgressReport sObj = new ActivitiesProgressReport();
			String progressStructuresQry = "select ap.remarks,ap.structure "
					+ "from fobdailyupdate ap "  
					+ "left join p6_activities a on ap.contract_id_fk = a.contract_id_fk left join structure s on s.structure_id = a.structure_id_fk "
					+ "left join contract c on a.contract_id_fk = c.contract_id and c.contract_id=ap.contract_id_fk "  
					+ " where a.contract_id_fk = ? and s.structure_type_fk<>'FOB' ";
			
			
			int arrSize = 1;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFrom_date()) && !StringUtils.isEmpty(obj.getTo_date())) {
				progressStructuresQry = progressStructuresQry + " and ap.reporting_date >= ? and reporting_date <= ?";
				arrSize++;
				arrSize++;
			}else {
				progressStructuresQry = progressStructuresQry + " and reporting_date = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				progressStructuresQry = progressStructuresQry + " and s.structure = ?";
				arrSize++;
			}
			
			progressStructuresQry = progressStructuresQry + " GROUP BY s.structure ORDER BY s.structure ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id();
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
			return contractProgressStructuresList;
		}

	@Override
	public String getReportforthePeriodActivitiesRemarks(String structure, String from_date, String to_date) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    String remarks = null;
			try {
				con = dataSource.getConnection();
				String qry = "select STRING_AGG(DISTINCT concat(FORMAT(reporting_date,'dd-MM-yyyy'),' - ',remarks) SEPARATOR '\n') as remarks from fobdailyupdate where structure =? and reporting_date>=? and reporting_date<=?";
				stmt = con.prepareStatement(qry);
				stmt.setString(1,structure);
				stmt.setString(2,from_date);
				stmt.setString(3,to_date);
				
				rs = stmt.executeQuery(); 
				while(rs.next()) 
				{
					remarks=rs.getString("remarks");
				}
			
				
			}catch(Exception e){ 
				throw new Exception(e.getMessage());
			}
			finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}
			return remarks;
	}		
}
