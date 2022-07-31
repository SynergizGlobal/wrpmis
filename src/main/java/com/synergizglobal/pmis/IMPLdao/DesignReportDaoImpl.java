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
			String qry = "select work_id_fk, work_id,work_name,work_short_name from design left join work on work_id_fk = work_id group by work_id_fk order by work_id_fk asc";
			
			
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
			String qry = "select hod from design d LEFT JOIN [user] u on d.hod = u.designation where hod is not null  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by hod  ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
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
	public Map<String,List<DesignReport>> getDesignReportData(DesignReport obj) throws Exception {
		Map<String,List<DesignReport>> objsMap = new LinkedHashMap<String,List<DesignReport>>();
		
		try {
			
			String filter2 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter2 = filter2 + " and d2.hod = ?"; }
		    
		    String filter3 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter3 = filter3 + " and d3.hod = ?"; }
		    
		    String filter4 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter4 = filter4 + " and d4.hod = ?"; }
		    
		    String filter5 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter5 = filter5 + " and d5.hod = ?"; }
		    
		    String filter6 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter6 = filter6 + " and d6.hod = ?"; }
		    
		    String filter7 = "";
		    if(!StringUtils.isEmpty(obj.getHod())) { filter7 = filter7 + " and d7.hod = ?"; }
		    
		    
		    
			/*String workWiseQry = "select concat(work_id_fk,' - ',work_short_name) as name,work_id_fk,work_name,work_short_name,"+
					"(select count(*) from design d2 where d2.work_id_fk = d1.work_id_fk"+filter1+") as total_scope," + 
					"(select count(*) from design d3 where d3.work_id_fk = d1.work_id_fk"+filter2+" and gfc_released is not null) as total_drawings_approved," + 
					"(select count(*) from design d4 where d4.work_id_fk = d1.work_id_fk"+filter3+" and consultant_submission is not null) as total_submitted_by_consultans," + 
					"(select count(*) from design d5 where d5.work_id_fk = d1.work_id_fk"+filter4+" and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
					"(select count(*) from design d6 where d6.work_id_fk = d1.work_id_fk"+filter5+" and submitted_to_division is not null) as total_submitted_to_division," + 
					"(select count(*) from design d7 where d7.work_id_fk = d1.work_id_fk"+filter6+" and divisional_approval is not null) as total_divisional_approval," + 
					"(select count(*) from design d8 where d8.work_id_fk = d1.work_id_fk"+filter7+" and submitted_to_hq is not null) as total_submitted_to_hq," + 
					"(select count(*) from design d9 where d9.work_id_fk = d1.work_id_fk"+filter8+" and hq_approval is not null) as total_hq_approval " + 
					"from design d1 "+
					"left join work on d1.work_id_fk = work_id " +
					"where d1.design_id is not null";*/
			
			 String workWiseQry = "select concat(work_id_fk,' - ',work_short_name) as name,work_id_fk,work_name,work_short_name," + 
			    		"(select count(*) from design d2 where d2.work_id_fk = d1.work_id_fk"+filter2+") as total_scope," + 
			    		"(select count(*) from design d3 where d3.work_id_fk = d1.work_id_fk"+filter3+" and gfc_released is not null) as total_drawings_approved," + 
			    		"(select count(*) from design d4 where d4.work_id_fk = d1.work_id_fk"+filter4+" and consultant_submission is not null) as total_submitted_by_consultans," + 
			    		"(select count(*) from design d5 where d5.work_id_fk = d1.work_id_fk"+filter5+" and ((consultant_submission is not null and mrvc_reviewed is null) or (consultant_submission is not null and mrvc_reviewed is not null and divisional_submission_fk = 'Yes' and submitted_to_division is null)) ) as under_review_by_mrvc," + 
			    		"(select count(*) from design d6 where d6.work_id_fk = d1.work_id_fk"+filter6+" and ((submitted_to_division is not null and divisional_approval is null) or (divisional_approval is not null and hq_submission_fk = 'Yes' and submitted_to_hq is null)) ) as under_review_by_division," + 
			    		"(select count(*) from design d7 where d7.work_id_fk = d1.work_id_fk"+filter7+" and (submitted_to_hq is not null and hq_approval is null)) as under_review_by_hq " +
			    		"from design d1 "
			    		+"left join work on d1.work_id_fk = work_id "
			    		+ "where d1.design_id is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				workWiseQry = workWiseQry + " and d1.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj.getHod())) {
				workWiseQry = workWiseQry + " and d1.hod = ?";
				arrSize = arrSize + 1 + 6;
			}
			workWiseQry = workWiseQry + " group by d1.work_id_fk order by d1.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj.getHod())) {
				for (int j = 0; j < 6; j++) {
					pValues[i++] = obj.getHod();
				}
			}
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			List<DesignReport> workWiseObjsList = jdbcTemplate.query( workWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    
			/*if(!StringUtils.isEmpty(workWiseObjsList) && workWiseObjsList.size() > 0) {		    	
				for (DesignReport dObj : workWiseObjsList) {
					int under_review_by_mrvc = 0,under_review_by_division = 0,under_review_by_hq = 0;
			    	int total_submitted_by_consultans = 0,total_mrvc_reviewed = 0,total_submitted_to_division = 0,
			    			total_divisional_approval = 0,total_submitted_to_hq = 0,total_hq_approval = 0;
			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_by_consultans())) {
			    		total_submitted_by_consultans = Integer.parseInt(dObj.getTotal_submitted_by_consultans());
			    	}			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_mrvc_reviewed())) {
			    		total_mrvc_reviewed = Integer.parseInt(dObj.getTotal_mrvc_reviewed());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_division())) {
			    		total_submitted_to_division = Integer.parseInt(dObj.getTotal_submitted_to_division());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_divisional_approval())) {
			    		total_divisional_approval = Integer.parseInt(dObj.getTotal_divisional_approval());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_hq())) {
			    		total_submitted_to_hq = Integer.parseInt(dObj.getTotal_submitted_to_hq());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_hq_approval())) {
			    		total_hq_approval = Integer.parseInt(dObj.getTotal_hq_approval());
			    	}
			    	
			    	under_review_by_mrvc = total_submitted_by_consultans - total_mrvc_reviewed;
			    	under_review_by_division = total_submitted_to_division - total_divisional_approval;			    	
			    	under_review_by_hq = total_submitted_to_hq - total_hq_approval;
			    	
			    	dObj.setUnder_review_by_mrvc(String.valueOf(under_review_by_mrvc));
			    	dObj.setUnder_review_by_division(String.valueOf(under_review_by_division));
			    	dObj.setUnder_review_by_hq(String.valueOf(under_review_by_hq));
				}
			}*/
		    
		    /********************************* HOD wise  *****************************************************************/
		    filter2 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter2 = filter2 + " and d2.work_id_fk = ?"; }
		    
		    filter3 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter3 = filter3 + " and d3.work_id_fk = ?"; }
		    
		    filter4 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter4 = filter4 + " and d4.work_id_fk = ?"; }
		    
		    filter5 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter5 = filter5 + " and d5.work_id_fk = ?"; }
		    
		    filter6 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter6 = filter6 + " and d6.work_id_fk = ?"; }
		    
		    filter7 = "";
		    if(!StringUtils.isEmpty(obj.getWork_id_fk())) { filter7 = filter7 + " and d7.work_id_fk = ?"; }
		    		    
		    
			/* String hodWiseQry = "select d1.hod as name," + 
					"(select count(*) from design d2 where d2.hod = d1.hod"+filter1+") as total_scope," + 
					"(select count(*) from design d3 where d3.hod = d1.hod"+filter2+" and gfc_released is not null) as total_drawings_approved," + 
					"(select count(*) from design d4 where d4.hod = d1.hod"+filter3+" and consultant_submission is not null) as total_submitted_by_consultans," + 
					"(select count(*) from design d5 where d5.hod = d1.hod"+filter4+" and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
					"(select count(*) from design d6 where d6.hod = d1.hod"+filter5+" and submitted_to_division is not null) as total_submitted_to_division," + 
					"(select count(*) from design d7 where d7.hod = d1.hod"+filter6+" and divisional_approval is not null) as total_divisional_approval," + 
					"(select count(*) from design d8 where d8.hod = d1.hod"+filter7+" and submitted_to_hq is not null) as total_submitted_to_hq," + 
					"(select count(*) from design d9 where d9.hod = d1.hod"+filter8+" and hq_approval is not null) as total_hq_approval " + 
					"from design d1 where d1.design_id is not null";*/
		    
		    String hodWiseQry = "select d1.hod as name," + 
		    		"(select count(*) from design d2 where d2.hod = d1.hod"+filter2+") as total_scope," + 
		    		"(select count(*) from design d3 where d3.hod = d1.hod"+filter3+" and gfc_released is not null) as total_drawings_approved," + 
		    		"(select count(*) from design d4 where d4.hod = d1.hod"+filter4+" and consultant_submission is not null) as total_submitted_by_consultans," + 
		    		"(select count(*) from design d5 where d5.hod = d1.hod"+filter5+" and ((consultant_submission is not null and mrvc_reviewed is null) or (consultant_submission is not null and mrvc_reviewed is not null and divisional_submission_fk = 'Yes' and submitted_to_division is null)) ) as under_review_by_mrvc," + 
		    		"(select count(*) from design d6 where d6.hod = d1.hod"+filter6+" and ((submitted_to_division is not null and divisional_approval is null) or (divisional_approval is not null and hq_submission_fk = 'Yes' and submitted_to_hq is null)) ) as under_review_by_division," + 
		    		"(select count(*) from design d7 where d7.hod = d1.hod"+filter7+" and (submitted_to_hq is not null and hq_approval is null)) as under_review_by_hq " +
		    		"from design d1 where d1.design_id is not null";
			
			arrSize = 0;
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodWiseQry = hodWiseQry + " and d1.work_id_fk = ?";
				arrSize = arrSize + 1 + 6;
			}
			if(!StringUtils.isEmpty(obj.getHod())) {
				hodWiseQry = hodWiseQry + " and d1.hod = ?";
				arrSize++;
			}
			hodWiseQry = hodWiseQry + " group by d1.hod order by d1.hod";
			
			pValues = new Object[arrSize];
			
			i = 0;
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				for (int j = 0; j < 6; j++) {
					pValues[i++] = obj.getWork_id_fk();
				}				
			}
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			/*if(!StringUtils.isEmpty(obj.getHod())) {
				for (int j = 0; j < 9; j++) {
					pValues[i++] = obj.getHod();
				}				
			}*/
			
			/*if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				 hodWiseQry = "select d1.hod as name," + 
				    		"(select count(*) from design d2 where d2.hod = d1.hod and d2.work_id_fk = ?) as total_scope," + 
				    		"(select count(*) from design d3 where d3.hod = d1.hod and d3.work_id_fk = ? and gfc_released is not null) as total_drawings_approved," + 
				    		"(select count(*) from design d4 where d4.hod = d1.hod and d4.work_id_fk = ? and consultant_submission is not null) as total_submitted_by_consultans," + 
				    		"(select count(*) from design d5 where d5.hod = d1.hod and d5.work_id_fk = ? and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
				    		"(select count(*) from design d6 where d6.hod = d1.hod and d6.work_id_fk = ? and submitted_to_division is not null) as total_submitted_to_division," + 
				    		"(select count(*) from design d7 where d7.hod = d1.hod and d7.work_id_fk = ? and divisional_approval is not null) as total_divisional_approval," + 
				    		"(select count(*) from design d8 where d8.hod = d1.hod and d8.work_id_fk = ? and submitted_to_hq is not null) as total_submitted_to_hq," + 
				    		"(select count(*) from design d9 where d9.hod = d1.hod and d9.work_id_fk = ? and hq_approval is not null) as total_hq_approval " + 
				    		"from design d1 where d1.design_id is not null and d1.work_id_fk = ? group by d1.hod order by d1.hod";
				arrSize = 9;
			}else {
				hodWiseQry = "select d1.hod as name,(select count(*) from design d2 where d2.hod = d1.hod) as total_scope," + 
						"(select count(*) from design d3 where d3.hod = d1.hod and gfc_released is not null) as total_drawings_approved," + 
						"(select count(*) from design d4 where d4.hod = d1.hod and consultant_submission is not null ) as total_submitted_by_consultans," + 
						"(select count(*) from design d5 where d5.hod = d1.hod and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
						"(select count(*) from design d6 where d6.hod = d1.hod and submitted_to_division is not null) as total_submitted_to_division," + 
						"(select count(*) from design d7 where d7.hod = d1.hod and divisional_approval is not null) as total_divisional_approval," + 
						"(select count(*) from design d8 where d8.hod = d1.hod and submitted_to_hq is not null) as total_submitted_to_hq," + 
						"(select count(*) from design d9 where d9.hod = d1.hod and hq_approval is not null) as total_hq_approval " + 
						"from design d1 where d1.design_id is not null group by d1.hod order by d1.hod";
			}*/
			
			
			
			/*pValues = new Object[arrSize];
			
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				for (int j = 0; j < arrSize; j++) {
					pValues[j++] = obj.getWork_id_fk();
				}				
			}*/
			
			List<DesignReport> hodWiseObjsList = jdbcTemplate.query( hodWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    
			/*if(!StringUtils.isEmpty(hodWiseObjsList) && hodWiseObjsList.size() > 0) {		    	
				for (DesignReport dObj : hodWiseObjsList) {
					int under_review_by_mrvc = 0,under_review_by_division = 0,under_review_by_hq = 0;
			    	int total_submitted_by_consultans = 0,total_mrvc_reviewed = 0,total_submitted_to_division = 0,
			    			total_divisional_approval = 0,total_submitted_to_hq = 0,total_hq_approval = 0;
			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_by_consultans())) {
			    		total_submitted_by_consultans = Integer.parseInt(dObj.getTotal_submitted_by_consultans());
			    	}			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_mrvc_reviewed())) {
			    		total_mrvc_reviewed = Integer.parseInt(dObj.getTotal_mrvc_reviewed());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_division())) {
			    		total_submitted_to_division = Integer.parseInt(dObj.getTotal_submitted_to_division());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_divisional_approval())) {
			    		total_divisional_approval = Integer.parseInt(dObj.getTotal_divisional_approval());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_hq())) {
			    		total_submitted_to_hq = Integer.parseInt(dObj.getTotal_submitted_to_hq());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_hq_approval())) {
			    		total_hq_approval = Integer.parseInt(dObj.getTotal_hq_approval());
			    	}
			    	
			    	under_review_by_mrvc = total_submitted_by_consultans - total_mrvc_reviewed;
			    	under_review_by_division = total_submitted_to_division - total_divisional_approval;			    	
			    	under_review_by_hq = total_submitted_to_hq - total_hq_approval;
			    	
			    	dObj.setUnder_review_by_mrvc(String.valueOf(under_review_by_mrvc));
			    	dObj.setUnder_review_by_division(String.valueOf(under_review_by_division));
			    	dObj.setUnder_review_by_hq(String.valueOf(under_review_by_hq));
				}
			}*/
		    
		    /************************************ Departments wise  **************************************************************/
		    
		    /*String departmentWiseQry = "";
			
		    arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				departmentWiseQry = "select d1.department_id_fk as name," + 
				    		"(select count(*) from design d2 where d2.department_id_fk = d1.department_id_fk and d2.work_id_fk = ?) as total_scope," + 
				    		"(select count(*) from design d3 where d3.department_id_fk = d1.department_id_fk and d3.work_id_fk = ? and gfc_released is not null) as total_drawings_approved," + 
				    		"(select count(*) from design d4 where d4.department_id_fk = d1.department_id_fk and d4.work_id_fk = ? and consultant_submission is not null) as total_submitted_by_consultans," + 
				    		"(select count(*) from design d5 where d5.department_id_fk = d1.department_id_fk and d5.work_id_fk = ? and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
				    		"(select count(*) from design d6 where d6.department_id_fk = d1.department_id_fk and d6.work_id_fk = ? and submitted_to_division is not null) as total_submitted_to_division," + 
				    		"(select count(*) from design d7 where d7.department_id_fk = d1.department_id_fk and d7.work_id_fk = ? and divisional_approval is not null) as total_divisional_approval," + 
				    		"(select count(*) from design d8 where d8.department_id_fk = d1.department_id_fk and d8.work_id_fk = ? and submitted_to_hq is not null) as total_submitted_to_hq," + 
				    		"(select count(*) from design d9 where d9.department_id_fk = d1.department_id_fk and d9.work_id_fk = ? and hq_approval is not null) as total_hq_approval " + 
				    		"from design d1 where d1.department_id_fk is not null and d1.work_id_fk = ? group by d1.department_id_fk order by d1.department_id_fk";
				arrSize = 9;
			}else {
				departmentWiseQry = "select d1.department_id_fk as name,(select count(*) from design d2 where d2.department_id_fk = d1.department_id_fk) as total_scope," + 
						"(select count(*) from design d3 where d3.department_id_fk = d1.department_id_fk and gfc_released is not null) as total_drawings_approved," + 
						"(select count(*) from design d4 where d4.department_id_fk = d1.department_id_fk and consultant_submission is not null ) as total_submitted_by_consultans," + 
						"(select count(*) from design d5 where d5.department_id_fk = d1.department_id_fk and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
						"(select count(*) from design d6 where d6.department_id_fk = d1.department_id_fk and submitted_to_division is not null) as total_submitted_to_division," + 
						"(select count(*) from design d7 where d7.department_id_fk = d1.department_id_fk and divisional_approval is not null) as total_divisional_approval," + 
						"(select count(*) from design d8 where d8.department_id_fk = d1.department_id_fk and submitted_to_hq is not null) as total_submitted_to_hq," + 
						"(select count(*) from design d9 where d9.department_id_fk = d1.department_id_fk and hq_approval is not null) as total_hq_approval " + 
						"from design d1 where d1.department_id_fk is not null group by d1.department_id_fk order by d1.department_id_fk";
			}
			
			
			
			pValues = new Object[arrSize];
			
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				for (int j = 0; j < arrSize; j++) {
					pValues[j++] = obj.getWork_id_fk();
				}				
			}
			
			List<DesignReport> departmentWiseObjsList = jdbcTemplate.query( departmentWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    
		    if(!StringUtils.isEmpty(departmentWiseObjsList) && departmentWiseObjsList.size() > 0) {		    	
		    	for (DesignReport dObj : departmentWiseObjsList) {
		    		int under_review_by_mrvc = 0,under_review_by_division = 0,under_review_by_hq = 0;
			    	int total_submitted_by_consultans = 0,total_mrvc_reviewed = 0,total_submitted_to_division = 0,
			    			total_divisional_approval = 0,total_submitted_to_hq = 0,total_hq_approval = 0;
			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_by_consultans())) {
			    		total_submitted_by_consultans = Integer.parseInt(dObj.getTotal_submitted_by_consultans());
			    	}			    	
			    	if(!StringUtils.isEmpty(dObj.getTotal_mrvc_reviewed())) {
			    		total_mrvc_reviewed = Integer.parseInt(dObj.getTotal_mrvc_reviewed());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_division())) {
			    		total_submitted_to_division = Integer.parseInt(dObj.getTotal_submitted_to_division());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_divisional_approval())) {
			    		total_divisional_approval = Integer.parseInt(dObj.getTotal_divisional_approval());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_submitted_to_hq())) {
			    		total_submitted_to_hq = Integer.parseInt(dObj.getTotal_submitted_to_hq());
			    	}
			    	if(!StringUtils.isEmpty(dObj.getTotal_hq_approval())) {
			    		total_hq_approval = Integer.parseInt(dObj.getTotal_hq_approval());
			    	}
			    	
			    	under_review_by_mrvc = total_submitted_by_consultans - total_mrvc_reviewed;
			    	under_review_by_division = total_submitted_to_division - total_divisional_approval;			    	
			    	under_review_by_hq = total_submitted_to_hq - total_hq_approval;
			    	
			    	dObj.setUnder_review_by_mrvc(String.valueOf(under_review_by_mrvc));
			    	dObj.setUnder_review_by_division(String.valueOf(under_review_by_division));
			    	dObj.setUnder_review_by_hq(String.valueOf(under_review_by_hq));
				}
		    }
		    
		    objsMap.put("Department", departmentWiseObjsList);
		    */
		    
		    objsMap.put("Project", workWiseObjsList);
		    objsMap.put("Responsibily", hodWiseObjsList);

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsMap;
	}

}
