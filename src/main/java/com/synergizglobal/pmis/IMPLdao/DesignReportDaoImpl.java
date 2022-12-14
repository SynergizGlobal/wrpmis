package com.synergizglobal.pmis.IMPLdao;

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

import com.synergizglobal.pmis.Idao.DesignReportDao;
import com.synergizglobal.pmis.model.DesignReport;
@Repository
public class DesignReportDaoImpl implements DesignReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<DesignReport> getWorksListInDesignReport(DesignReport obj) throws Exception {
		List<DesignReport> objsList = null;
		try {
			String qry = "select work_id_fk, work_id,work_name,work_short_name from design left join work on work_id_fk = work_id group by work_id_fk, work_id,work_name,work_short_name order by work_id_fk asc";
			
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DesignReport> getHodListInDesignReport(DesignReport obj) throws Exception {
		List<DesignReport> objsList = null;
		try {
			String qry = "select hod,u.designation from design d LEFT JOIN [user] u on d.hod = u.designation where hod is not null  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by hod,designation  ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
					"   when u.designation='CPM I' then 2 \r\n" + 
					"   when u.designation='CPM II' then 3\r\n" + 
					"   when u.designation='CPM III' then 4 \r\n" + 
					"   when u.designation='CPM V' then 5\r\n" + 
					"   when u.designation='CE' then 6 \r\n" + 
					"   when u.designation='ED S&T' then 7 \r\n" + 
					"   when u.designation='CSTE' then 8\r\n" + 
					"   when u.designation='GM Electrical' then 9\r\n" + 
					"   when u.designation='CEE Project I' then 10\r\n" + 
					"   when u.designation='CEE Project II' then 11\r\n" + 
					"   when u.designation='ED Finance & Planning' then 12\r\n" + 
					"   when u.designation='AGM Civil' then 13\r\n" + 
					"   when u.designation='DyCPM Civil' then 14\r\n" + 
					"   when u.designation='DyCPM III' then 15\r\n" + 
					"   when u.designation='DyCPM V' then 16\r\n" + 
					"   when u.designation='DyCE EE' then 17\r\n" + 
					"   when u.designation='DyCE Badlapur' then 18\r\n" + 
					"   when u.designation='DyCPM Pune' then 19\r\n" + 
					"   when u.designation='DyCE Proj' then 20\r\n" + 
					"   when u.designation='DyCEE I' then 21\r\n" + 
					"   when u.designation='DyCEE Projects' then 22\r\n" + 
					"   when u.designation='DyCEE PSI' then 23\r\n" + 
					"   when u.designation='DyCSTE I' then 24\r\n" + 
					"   when u.designation='DyCSTE IT' then 25\r\n" + 
					"   when u.designation='DyCSTE Projects' then 26\r\n" + 
					"   when u.designation='XEN Consultant' then 27\r\n" + 
					"   when u.designation='AEN Adhoc' then 28\r\n" + 
					"   when u.designation='AEN Project' then 29\r\n" + 
					"   when u.designation='AEN P-Way' then 30\r\n" + 
					"   when u.designation='AEN' then 31\r\n" + 
					"   when u.designation='Sr Manager Signal' then 32 \r\n" + 
					"   when u.designation='Manager Signal' then 33\r\n" + 
					"   when u.designation='Manager Civil' then 34 \r\n" + 
					"   when u.designation='Manager OHE' then 35\r\n" + 
					"   when u.designation='Manager GS' then 36\r\n" + 
					"   when u.designation='Manager Finance' then 37\r\n" + 
					"   when u.designation='Planning Manager' then 38\r\n" + 
					"   when u.designation='Manager Project' then 39\r\n" + 
					"   when u.designation='Manager' then 40 \r\n" + 
					"   when u.designation='SSE' then 41\r\n" + 
					"   when u.designation='SSE Project' then 42\r\n" + 
					"   when u.designation='SSE Works' then 43\r\n" + 
					"   when u.designation='SSE Drg' then 44\r\n" + 
					"   when u.designation='SSE BR' then 45\r\n" + 
					"   when u.designation='SSE P-Way' then 46\r\n" + 
					"   when u.designation='SSE OHE' then 47\r\n" + 
					"   when u.designation='SPE' then 48\r\n" + 
					"   when u.designation='PE' then 49\r\n" + 
					"   when u.designation='JE' then 50\r\n" + 
					"   when u.designation='Demo-HOD-Elec' then 51\r\n" + 
					"   when u.designation='Demo-HOD-Engg' then 52\r\n" + 
					"   when u.designation='Demo-HOD-S&T' then 53\r\n" + 
					"\r\n" + 
					"   end asc\r\n" + 
					"";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DesignReport> getDepartmentsListInDesignReport(DesignReport obj) throws Exception {
		List<DesignReport> objsList = null;
		try {
			String qry = "select department_id_fk,department,department_name,contract_id_code from design left join department on department_id_fk = department group by department_id_fk order by department_id_fk asc";
			
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DesignReport> getDesignReportData(DesignReport obj) throws Exception {
		List<DesignReport> objsList = null;
		try {
			
			String workWiseQry ="select design_id,d.work_id_fk,w.project_id_fk,d.structure_type_fk,d.structure_id_fk,w.work_short_name,d.approving_railway,d.approval_authority_fk,w.work_name,c.contract_name, " + 
					"c.contract_short_name,d.contract_id_fk,d.department_id_fk,isnull(d.consultant_contract_id_fk,'') as consultant_contract_id_fk,isnull(d.proof_consultant_contract_id_fk,'') as proof_consultant_contract_id_fk,d.hod,d.dy_hod,d.prepared_by_id_fk,d.structure_type_fk, " + 
					"d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no,d.hq_drawing_no,d.drawing_title,FORMAT(d.required_date,'dd-MM-yyyy') AS required_date,  " + 
					"FORMAT(d.gfc_released,'dd-MM-yyyy') AS gfc_released,d.remarks,(case when (SELECT count(dss.submitted_date) FROM design_status dss  " + 
					"where dss.submitted_date = max(ds.submitted_date)) > 1 then  (SELECT submssion_purpose FROM design_status dss where max(ds.id) = dss.id )  " + 
					"else (SELECT submssion_purpose FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) end) as submission_purpose, " + 
					"(case when (SELECT count(dss.submitted_date) FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) > 1 then   " + 
					"(SELECT stage_fk FROM design_status dss where max(ds.id) = dss.id  ) else (SELECT stage_fk FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) end) as stage_fk, " + 
					"(case when (SELECT count(dss.submitted_date) FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) > 1 then   " + 
					"(SELECT submitted_by FROM design_status dss where max(ds.id) = dss.id ) else (SELECT submitted_by FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) end) as submitted_by, " + 
					"(case when (SELECT count(dss.submitted_date) FROM design_status dss where dss.submitted_date = max(ds.submitted_date)) > 1 then   " + 
					"(SELECT submitted_to FROM design_status dss where max(ds.id) = dss.id) else (SELECT submitted_to FROM design_status dss  " + 
					"where dss.submitted_date = max(ds.submitted_date)) end) as submitted_to ,FORMAT(max(ds.submitted_date) ,'dd-MM-yyyy') AS submitted_date,  " + 
					"FORMAT(required_date ,'dd-MM-yyyy') AS required_date ,u.user_name,u.designation as hod_designation,u1.user_name,u1.designation as dy_hod_designation, " + 
					"dt.department_name ,isnull(c1.contract_short_name,'') as consult_contarct, isnull(c2.contract_short_name,'') as proof_consult_contarct,component,design_seq_id   " + 
					" " + 
					"from design d  " + 
					"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id  " + 
					"LEFT OUTER JOIN contract c1 ON d.consultant_contract_id_fk = c1.contract_id  " + 
					"LEFT OUTER JOIN contract c2 ON d.proof_consultant_contract_id_fk = c2.contract_id  " + 
					"LEFT OUTER JOIN work w  ON d.work_id_fk  =  w.work_id  " + 
					"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id  " + 
					"left outer join [user] u  ON d.hod  =  u.user_id  " + 
					"left outer join [user] u1  ON d.dy_hod  =  u1.user_id  " + 
					"LEFT OUTER JOIN department dt  ON d.department_id_fk  =  dt.department   " + 
					"left join design_status ds on d.design_id = ds.design_id_fk  where design_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				workWiseQry = workWiseQry + " and w.work_id = ?";
				arrSize++;
			}
			
			workWiseQry = workWiseQry + " group by design_id,d.work_id_fk,w.project_id_fk,d.structure_type_fk,d.structure_id_fk,w.work_short_name, " + 
					"d.approving_railway,d.approval_authority_fk,w.work_name,c.contract_name,c.contract_short_name,d.contract_id_fk,d.department_id_fk,d.consultant_contract_id_fk,d.proof_consultant_contract_id_fk,d.hod,d.dy_hod,d.prepared_by_id_fk,d.structure_type_fk, " + 
					"d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no,d.hq_drawing_no,d.drawing_title,FORMAT(d.required_date,'dd-MM-yyyy'),FORMAT(d.gfc_released,'dd-MM-yyyy'),d.remarks, " + 
					"u.user_name,u.designation,u1.user_name,u1.designation,dt.department_name,c1.contract_short_name,c2.contract_short_name,component,design_seq_id ";			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( workWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

}
