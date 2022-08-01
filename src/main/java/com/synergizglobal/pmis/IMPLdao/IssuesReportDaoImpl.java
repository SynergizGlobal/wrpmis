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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getHODListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name,hod_user_id_fk,u.designation,u.user_name as hod_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
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
			
			qry = qry + " GROUP BY hod_user_id_fk  ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
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
					"   end asc" ;

			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Issue> getStatusListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT status_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where status_fk is not null and status_fk <> '' ";
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
				qry = qry + " and status_fk = ?";
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
			qry = qry + " GROUP BY status_fk ORDER BY status_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Issue> getLocationsListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT location "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where location is not null and location <> '' ";
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
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY location ORDER BY location";
			
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getCategoriesListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT category_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where category_fk is not null and category_fk <> '' ";
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
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation())) {
				qry = qry + " and location = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY category_fk ORDER BY category_fk";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation())) {
				pValues[i++] = obj.getLocation();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getTitlesListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT issue_id,title,c.work_id_fk,contract_id_fk,status_fk,c.hod_user_id_fk,location,category_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where title is not null and title <> '' ";
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
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation())) {
				qry = qry + " and location = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation())) {
				pValues[i++] = obj.getLocation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public Map<String,Map<String,List<Issue>>> getPendingIssues(Issue obj) throws Exception {
		Map<String,Map<String,List<Issue>>> objsList = new LinkedHashMap<String,Map<String,List<Issue>>>();
		try {
			
			String hodQry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name,hod_user_id_fk,u.designation,u.user_name as hod_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
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
			hodQry = hodQry + " ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
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
					"   end asc" ;
			
			
			List<Issue> hodObjsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			for (Issue hod : hodObjsList) {
				Map<String,List<Issue>> workIssuesList = new LinkedHashMap<String,List<Issue>>();
				String workQry = "SELECT c.work_id_fk,work_name,work_short_name "
						+ "from issue i "
						+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
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
					String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,i.title,i.description,FORMAT(date,'dd-MM-yyyy') AS date,location,reported_by,responsible_person,other_organization,c.department_fk," 
							+ "priority_fk,category_fk,status_fk,corrective_measure,FORMAT(resolved_date,'dd-MM-yyyy') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,"
							+ "i.zonal_railway_fk,r.railway_name,c.contractor_id_fk,ctr.contractor_id,ctr.contractor_name,"
							+ "d.department_name,hod_user_id_fk,u.designation,u.user_name as hod_name,DATEDIFF(CONVERT(date, getdate()), date) as pending_since,FORMAT(date,'dd-MM-yyyy') AS date, "
							+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
							+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,other_org_resposible_person_name,other_org_resposible_person_designation "
							+ "from issue i "
							+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
							+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
							+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
							+ "LEFT OUTER JOIN contractor ctr ON c.contractor_id_fk= ctr.contractor_id "
							+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
							+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
							+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
							+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
							+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk  = r.railway_id "
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public String getEmailIdsOfHodDyHodManagement() throws Exception {
		String email_ids = null;
		try {
			String qry = "select email_id FROM [user] where user_type_fk in ('HOD','DyHOD','Management') and email_id is not null and email_id <> ''";
			
			List<String> list = jdbcTemplate.queryForList( qry, String.class);	
			if(!StringUtils.isEmpty(list) && list.size() > 0) {
				email_ids = String.join(",", list);
			}
		}catch(Exception e){ 
			throw new Exception(e);
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
					+ "from contract c "
					//+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				hodQry = hodQry + " and contract_id = ?";
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
			hodQry = hodQry + " ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
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
					"   end asc" ;
			
			objsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> IssuesSummaryData(Issue obj) throws Exception {
		List<Issue> objsList = new ArrayList<Issue>();
		try {
			
			String hodQry = "SELECT hod_user_id_fk,u.designation, "
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk) as total_issues," 
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk and status_fk = 'Closed') as closed_issues,"
					+ "(select count(*) from issue left join contract on contract_id_fk = contract_id where hod_user_id_fk = c.hod_user_id_fk and status_fk <> 'Closed') as open_issues "
					+ "from contract c "
					//+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				hodQry = hodQry + " and contract_id = ?";
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
			hodQry = hodQry + " ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
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
					"   end asc" ;
			
			objsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	
}
