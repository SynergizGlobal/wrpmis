package com.synergizglobal.pmis.IMPLdao;

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

import com.synergizglobal.pmis.Idao.IssuesReportDao;
import com.synergizglobal.pmis.model.Issue;

@Repository
public class IssuesReportDaoImpl implements IssuesReportDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,w.work_short_name "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where work_id_fk is not null and work_id_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk <> ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			qry = qry + " GROUP BY work_id_fk ORDER BY work_id_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk <> ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			qry = qry + " GROUP BY contract_id_fk ORDER BY contract_id_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getHODListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name,hod_user_id_fk,u.designation,u.user_name as hod_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk <> ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			qry = qry + " GROUP BY hod_user_id_fk ORDER BY u.designation";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public Map<String,Map<String,List<Issue>>> getPendingIssues(Issue obj) throws Exception {
		Map<String,Map<String,List<Issue>>> objsList = new LinkedHashMap<String,Map<String,List<Issue>>>();
		try {
			
			String hodQry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name,hod_user_id_fk,u.designation,u.user_name as hod_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				hodQry = hodQry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				hodQry = hodQry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				hodQry = hodQry + " and status_fk <> ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			hodQry = hodQry + " GROUP BY hod_user_id_fk ";
			hodQry = hodQry + " ORDER BY FIELD(designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO'),designation" ;
			
			
			List<Issue> hodObjsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			for (Issue hod : hodObjsList) {
				Map<String,List<Issue>> workIssuesList = new LinkedHashMap<String,List<Issue>>();
				String workQry = "SELECT c.work_id_fk,work_name,work_short_name "
						+ "from issue i "
						+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
						+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
						+ "where c.work_id_fk is not null and c.work_id_fk <> '' ";
						
				arrSize = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					workQry = workQry + " and work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
					workQry = workQry + " and contract_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
					workQry = workQry + " and status_fk <> ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hod) && !StringUtils.isEmpty(hod.getHod_user_id_fk())) {
					workQry = workQry + " and hod_user_id_fk = ?";
					arrSize++;
				}
				
				workQry = workQry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk";
				
				pValues = new Object[arrSize];
				
				i = 0;

				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
					pValues[i++] = obj.getContract_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
					pValues[i++] = obj.getStatus_fk();
				}	
				if(!StringUtils.isEmpty(hod) && !StringUtils.isEmpty(hod.getHod_user_id_fk())) {
					pValues[i++] = hod.getHod_user_id_fk();
				}
				
				List<Issue> worksList = jdbcTemplate.query( workQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
				for (Issue work : worksList) {
					String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,i.title,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,other_organization,c.department_fk," 
							+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,"
							+ "i.zonal_railway_fk,r.railway_name,c.contractor_id_fk,ctr.contractor_id,ctr.contractor_name,"
							+ "d.department_name,hod_user_id_fk,u.designation,u.user_name as hod_name,DATEDIFF(NOW(), date) as pending_since,DATE_FORMAT(date,'%d-%m-%Y') AS date, "
							+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
							+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,other_org_resposible_person_name,other_org_resposible_person_designation "
							+ "from issue i "
							+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
							+ "LEFT OUTER JOIN user u3 on i.escalated_to = u3.user_id "
							+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
							+ "LEFT OUTER JOIN contractor ctr ON c.contractor_id_fk= ctr.contractor_id "
							+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
							+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
							+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
							+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
							+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk COLLATE utf8mb4_unicode_ci = r.railway_id "
							+ "where issue_id is not null " ;
					arrSize = 0;
					if(!StringUtils.isEmpty(work) && !StringUtils.isEmpty(work.getWork_id_fk())) {
						qry = qry + " and work_id_fk = ?";
						arrSize++;
					}	
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
						qry = qry + " and contract_id_fk = ?";
						arrSize++;
					}
					if(!StringUtils.isEmpty(hod) && !StringUtils.isEmpty(hod.getHod_user_id_fk())) {
						qry = qry + " and hod_user_id_fk = ?";
						arrSize++;
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
						qry = qry + " and status_fk <> ?";
						arrSize++;
					}
					qry = qry + " ORDER BY location,i.date ASC";
					
					pValues = new Object[arrSize];
					
					i = 0;
					if(!StringUtils.isEmpty(work) && !StringUtils.isEmpty(work.getWork_id_fk())) {
						pValues[i++] = work.getWork_id_fk();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
						pValues[i++] = obj.getContract_id_fk();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hod.getHod_user_id_fk())) {
						pValues[i++] = hod.getHod_user_id_fk();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
						pValues[i++] = obj.getStatus_fk();
					}
					
					List<Issue> issuesList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
					workIssuesList.put(work.getWork_short_name(), issuesList);
					
				}
				objsList.put(hod.getDesignation(), workIssuesList);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public String getEmailIdsOfHodDyHodManagement() throws Exception {
		String email_ids = null;
		try {
			String qry = "select group_concat(email_id) from user where user_type_fk in ('HOD','DyHOD','Management') and email_id is not null and email_id <> ''";
			
			email_ids = jdbcTemplate.queryForObject( qry, String.class);	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return email_ids;
	}

	@Override
	public List<Issue> getIssuesSummaryData(Issue obj) throws Exception {
		List<Issue> objsList = new ArrayList<Issue>();
		try {
			
			String hodQry = "SELECT hod_user_id_fk,u.designation, "
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk) as total_issues," 
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk and status_fk = 'Closed') as closed_issues,"
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk and status_fk <> 'Closed') as open_issues "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				hodQry = hodQry + " and c.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				hodQry = hodQry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			hodQry = hodQry + " GROUP BY hod_user_id_fk ";
			hodQry = hodQry + " ORDER BY FIELD(designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO'),designation" ;
			
			objsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
}
