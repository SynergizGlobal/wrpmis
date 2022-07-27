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

import com.synergizglobal.pmis.Idao.ActivitiesStatusReportDao;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.Training;
@Repository
public class ActivitiesStatusReportDaoImpl implements ActivitiesStatusReportDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception {
		List<ActivitiesProgressReport> objList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.0000");
		try {
			String contractsQry = "select contract_id,c.work_id_fk,contract_name,contract_short_name,contractor_id_fk, work_name,work_short_name,contractor_name,f.work_status_fk " + 
					"from contract c " + 
					"left outer join work w on work_id_fk = work_id " + 
					"left outer join p6_activities a on c.contract_id = a.contract_id_fk " 
					+ "left join structure s on s.structure_id = a.structure_id_fk "+
					"left outer join fob f on f.fob_id = a.structure " +
					"left outer join contractor cr on contractor_id_fk = contractor_id " + 
					"where contract_id is not null and s.structure_type_fk='FOB' " ;
			int arrSize = 0;
			String []structures = null;
			if(!StringUtils.isEmpty(obj.getFob_id_fks())) {
				String [] fobs = obj.getFob_id_fks();
				structures = new String[fobs.length]; 
				for (int i = 0; i < fobs.length; i++) {     
					structures[i] = fobs[i];     
		        }  
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fks())) {
				contractsQry = contractsQry + " and a.structure  in (?";
				int length = obj.getFob_id_fks().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						contractsQry = contractsQry + ",?";
						arrSize++;
					}
				}
				contractsQry = contractsQry + " ) ";
				arrSize++;
			}
			contractsQry = contractsQry + " GROUP BY a.contract_id_fk ORDER BY FIELD(component,'New FOB site on PF','PF and service buildings','New Constructed FOB','New Constructed  FOB','PF sheds Under new FOB','Dismantling of old & unservicable FOB','PF s cover shed of dismantalling FOB','Station')";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fks())) {
				int length = obj.getFob_id_fks().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getFob_id_fks()[j];
					}
				}	
			}
			obj = jdbcTemplate.queryForObject( contractsQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));		
			
			/***********************************************************************/
			String structureQry = "select a.contract_id_fk,contract_id,a.structure as fob_id_fk,component,f.fob_name "
					+ "from p6_activities a "
					+ "left join structure s on s.structure_id = a.structure_id_fk "
					+"left outer join fob f on f.fob_id = a.structure " 
					+ "left join contract c on a.contract_id_fk = c.contract_id "  
					+ "where a.contract_id_fk = ? and s.structure_type_fk='FOB' ";
			arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(structures)) {
				structureQry = structureQry + " and a.structure  in (?";
				int length = structures.length;
				if(length > 1) {
					for(int k =0; k< (length-1); k++) {
						structureQry = structureQry + ",?";
						arrSize++;
					}
				}
				structureQry = structureQry + " ) ";
				arrSize++;
			}
			structureQry = structureQry + " GROUP BY a.structure ";
			pValues = new Object[arrSize];
			i = 0;
			pValues[i++] = obj.getContract_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(structures)) {
				int length = structures.length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = structures[j];
					}
				}	
			}
			List<ActivitiesProgressReport> structuresList = jdbcTemplate.query( structureQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
			obj.setStructuressList(structuresList);
			
			/***********************************************************************/
			for (ActivitiesProgressReport componentObj : structuresList) {
			String progressStructuresQry = "select a.contract_id_fk,contract_id,a.structure as fob_id_fk,component "
						+ "from p6_activities a "
						+ "left join structure s on s.structure_id = a.structure_id_fk "
						+ "left join contract c on a.contract_id_fk = c.contract_id "  
						+ "where a.contract_id_fk = ? and s.structure_type_fk='FOB' ";
				arrSize = 1;
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
					progressStructuresQry = progressStructuresQry + " and a.structure =?";
					arrSize++;
				}
				progressStructuresQry = progressStructuresQry + " GROUP BY a.component ORDER BY FIELD(component,'Station') asc";
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = obj.getContract_id();
				if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
					pValues[i++] = componentObj.getFob_id_fk();
				}
				List<ActivitiesProgressReport> contractProgressStructuresList = jdbcTemplate.query( progressStructuresQry, pValues, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
				componentObj.setComponentsList(contractProgressStructuresList);
			
				/**********************************************************************************************************************************/				
				List<ActivitiesProgressReport> totalContractProgresList = new ArrayList<ActivitiesProgressReport>();
				
				for (ActivitiesProgressReport contractProgressStructure : contractProgressStructuresList) {
					String contractProgressDatesQry = "select p6_activity_id as activity_id,activity_name,structure as fob_id_fk,FORMAT(baseline_start,'%d-%m-%Y') AS planned_start,FORMAT(baseline_finish,'%d-%m-%Y') AS planned_finish,case  " + 
							"  when (ISNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then '' " + 
							"  when ISNULL(NULLIF(completed, '' ), 0)>=ISNULL(NULLIF(scope, '' ), 0) then (select FORMAT(min(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) " + 
							"  else (select FORMAT(min(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) end as actual_start,case  " + 
							"  when (ISNULL(NULLIF(completed, '' ), 0)=0 or completed is null) then '' " + 
							"  when ISNULL(NULLIF(completed, '' ), 0)>=ISNULL(NULLIF(scope, '' ), 0) then (select FORMAT(max(progress_date),'%d-%m-%Y') from p6_activity_progress where p6_activity_id_fk=a.p6_activity_id) " + 
							"  else '' end as actual_finish,unit,ISNULL(NULLIF(scope, '' ), 0) AS scope,ISNULL(NULLIF(completed, '' ), 0) AS completed,contract_id_fk,work_id,project_id,project_name "
							+ "from  p6_activities a "
							+ "left join structure s on s.structure_id = a.structure_id_fk "
							+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
							+ "LEFT JOIN work w on c.work_id_fk = w.work_id "  
							+ "LEFT JOIN project p on w.project_id_fk = p.project_id " 
							+ "where a.scope>0 and a.contract_id_fk = ? and s.structure_type_fk='FOB' ";
					
					arrSize = 1;
					
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getComponent())) {
						contractProgressDatesQry = contractProgressDatesQry + " and a.component = ?";
						arrSize++;
					}
					if(!StringUtils.isEmpty(componentObj) && !StringUtils.isEmpty(componentObj.getFob_id_fk())) {
						contractProgressDatesQry = contractProgressDatesQry + " and a.structure = ?";
						arrSize++;
					}
					
					contractProgressDatesQry=contractProgressDatesQry+" ORDER BY FIELD(component,'New FOB site on PF','PF and service buildings','New Constructed FOB','New Constructed  FOB','PF sheds Under new FOB','Dismantling of old & unservicable FOB','PF s cover shed of dismantalling FOB','Station')";
					pValues = new Object[arrSize];
					i = 0;
					pValues[i++] = obj.getContract_id();
					
					if(!StringUtils.isEmpty(contractProgressStructure) && !StringUtils.isEmpty(contractProgressStructure.getComponent())) {
						pValues[i++] = contractProgressStructure.getComponent();
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
			String qryDetails = "select structure as fob_id_fk,contract_id_fk as contract_id from p6_activities a left join structure s on s.structure_id = a.structure_id_fk  "
					+"where contract_id_fk = ?  and s.structure_type_fk='FOB' group by structure";
			
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
					"from p6_activities a " 
					+ "left join structure s on s.structure_id = a.structure_id_fk "+
					"LEFT JOIN fob f on s.structure = f.fob_id " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where project_id is not null and project_id <> ''   and fob_id is not null and fob_id <> '' and s.structure_type_fk='FOB' ";
			
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
					"from p6_activities a " 
					+ "left join structure s on s.structure_id = a.structure_id_fk "+
					"LEFT JOIN fob f on s.structure = f.fob_id " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where w.work_id is not null and w.work_id <> ''   and fob_id is not null and fob_id <> '' and s.structure_type_fk='FOB' ";
			
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
					"from p6_activities a " 
					+ "left join structure s on s.structure_id = a.structure_id_fk "+
					"LEFT JOIN fob f on s.structure = f.fob_id " +
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where c.contract_id is not null and c.contract_id <> ''   and fob_id is not null and fob_id <> '' and s.structure_type_fk='FOB' ";
			
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
			
			String qry = "SELECT fob_id,fob_name "+
					"from p6_activities a "  
					+ "left join structure s on s.structure_id = a.structure_id_fk "+
					"LEFT JOIN fob f on s.structure = f.fob_id " + 
					"LEFT JOIN contract c on a.contract_id_fk = c.contract_id " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where fob_id is not null and fob_id <> ''  and s.structure_type_fk='FOB' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY fob_id ORDER BY fob_id ASC";
			
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
			String qryDetails = "select contract_id_fk as contract_id,c.contract_short_name from p6_activities a left join structure s on s.structure_id = a.structure_id_fk  "
					+ "LEFT JOIN contract c on a.contract_id_fk = c.contract_id "
					+"where contract_id_fk is not null and contract_id_fk <> ''  and s.structure_type_fk='FOB' group by contract_id";
			
			objsList = jdbcTemplate.query(qryDetails, new BeanPropertyRowMapper<ActivitiesProgressReport>(ActivitiesProgressReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
}
