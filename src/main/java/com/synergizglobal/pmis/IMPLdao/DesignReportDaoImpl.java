package com.synergizglobal.pmis.IMPLdao;

import java.util.HashMap;
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<DesignReport> getHodListInDesignReport(DesignReport obj) throws Exception {
		List<DesignReport> objsList = null;
		try {
			String qry = "select hod from design group by hod order by hod asc";
			
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Map<String,List<DesignReport>> getDesignReportData(DesignReport obj) throws Exception {
		Map<String,List<DesignReport>> objsMap = new HashMap<String,List<DesignReport>>();
		
		try {
			String workWiseQry = "select concat(work_id_fk,' - ',work_short_name) as name,work_id_fk,work_name,work_short_name,(select count(*) from design d2 where d2.work_id_fk = d1.work_id_fk) as total_scope," + 
					"(select count(*) from design d3 where d3.work_id_fk = d1.work_id_fk and gfc_released is not null) as total_drawings_approved," + 
					"(select count(*) from design d4 where d4.work_id_fk = d1.work_id_fk and consultant_submission is not null) as total_submitted_by_consultans," + 
					"(select count(*) from design d5 where d5.work_id_fk = d1.work_id_fk and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
					"(select count(*) from design d6 where d6.work_id_fk = d1.work_id_fk and submitted_to_division is not null) as total_submitted_to_division," + 
					"(select count(*) from design d7 where d7.work_id_fk = d1.work_id_fk and divisional_approval is not null) as total_divisional_approval," + 
					"(select count(*) from design d8 where d8.work_id_fk = d1.work_id_fk and submitted_to_hq is not null) as total_submitted_to_hq," + 
					"(select count(*) from design d9 where d9.work_id_fk = d1.work_id_fk and hq_approval is not null) as total_hq_approval " + 
					"from design d1 "+
					"left join work on d1.work_id_fk = work_id " +
					"where d1.work_id_fk is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				workWiseQry = workWiseQry + " and d1.work_id_fk = ?";
				arrSize++;
			}
			workWiseQry = workWiseQry + " group by d1.work_id_fk order by d1.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			List<DesignReport> workWiseObjsList = jdbcTemplate.query( workWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    
		    if(!StringUtils.isEmpty(workWiseObjsList) && workWiseObjsList.size() > 0) {		    	
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
		    }
		    
		    /********************************* HOD wise  *****************************************************************/
		    
		    String hodWiseQry = "";
			
			arrSize = 0;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				 hodWiseQry = "select d1.hod as name," + 
				    		"(select count(*) from design d2 where d2.hod = d1.hod and d2.work_id_fk = ?) as total_scope," + 
				    		"(select count(*) from design d3 where d3.hod = d1.hod and d3.work_id_fk = ? and gfc_released is not null) as total_drawings_approved," + 
				    		"(select count(*) from design d4 where d4.hod = d1.hod and d4.work_id_fk = ? and consultant_submission is not null) as total_submitted_by_consultans," + 
				    		"(select count(*) from design d5 where d5.hod = d1.hod and d5.work_id_fk = ? and mrvc_reviewed is not null) as total_mrvc_reviewed," + 
				    		"(select count(*) from design d6 where d6.hod = d1.hod and d6.work_id_fk = ? and submitted_to_division is not null) as total_submitted_to_division," + 
				    		"(select count(*) from design d7 where d7.hod = d1.hod and d7.work_id_fk = ? and divisional_approval is not null) as total_divisional_approval," + 
				    		"(select count(*) from design d8 where d8.hod = d1.hod and d8.work_id_fk = ? and submitted_to_hq is not null) as total_submitted_to_hq," + 
				    		"(select count(*) from design d9 where d9.hod = d1.hod and d9.work_id_fk = ? and hq_approval is not null) as total_hq_approval " + 
				    		"from design d1 where d1.hod is not null and d1.work_id_fk = ? group by d1.hod order by d1.hod";
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
						"from design d1 where d1.hod is not null group by d1.hod order by d1.hod";
			}
			
			
			
			pValues = new Object[arrSize];
			
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				for (int j = 0; j < arrSize; j++) {
					pValues[j++] = obj.getWork_id_fk();
				}				
			}
			
			List<DesignReport> hodWiseObjsList = jdbcTemplate.query( hodWiseQry,pValues, new BeanPropertyRowMapper<DesignReport>(DesignReport.class));
		    
		    if(!StringUtils.isEmpty(hodWiseObjsList) && hodWiseObjsList.size() > 0) {		    	
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
		    }
		    
		    /************************************ Departments wise  **************************************************************/
		    
		    String departmentWiseQry = "";
			
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
		    objsMap.put("Responsibily", hodWiseObjsList);
		    objsMap.put("Project", workWiseObjsList);

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsMap;
	}

}
