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
			
			String workWiseQry ="select distinct department,hod,[Dy HoD] as dyhod,[Structure Type] as structure_type_fk,structure_id_fk,isnull(component,'') as component,isnull(design_seq_id,'') as drawing_id,\r\n" + 
					"isnull(consult_contarct,'') as consult_contarct,isnull(proof_consult_contarct,'') as proof_consult_contarct,isnull(consultant_contract_id_fk,'') as consultant_contract_id_fk,isnull(proof_consultant_contract_id_fk,'') as proof_consultant_contract_id_fk,prepared_by,drawing_type,approval_authority,\r\n" + 
					"required_date,drawing_name,mrvc_drawing_no,division_drawing_no,hq_drawing_no,current_stage from design_view where 0=0 \r\n" + 
					"\r\n" + 
					"\r\n" + 
					" ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				workWiseQry = workWiseQry + " and work_id_fk = ?";
				arrSize++;
			}
			
		
			
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
