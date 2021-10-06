package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.UserActivityReportDao;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.ContractResource;
import com.synergizglobal.pmis.model.UserActivityReport;
@Repository
public class UserActivityReportDaoImpl implements UserActivityReportDao{


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<UserActivityReport> getContractsFilterListInUserActivityReport(UserActivityReport obj)
			throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT contract from forms_history where contract is not null and contract <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				qry = qry + " and contract = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				qry = qry + " and work = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				qry = qry + " and user = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY contract";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				pValues[i++] = obj.getContract();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				pValues[i++] = obj.getWork();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				pValues[i++] = obj.getUser();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getWorksFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT work from forms_history where work is not null and work <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				qry = qry + " and contract = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				qry = qry + " and work = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				qry = qry + " and user = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY work";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				pValues[i++] = obj.getContract();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				pValues[i++] = obj.getWork();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				pValues[i++] = obj.getUser();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getHODsFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT user from forms_history where user is not null and user <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				qry = qry + " and contract = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				qry = qry + " and work = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				qry = qry + " and user = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY user";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				pValues[i++] = obj.getContract();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				pValues[i++] = obj.getWork();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				pValues[i++] = obj.getUser();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getModulessFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT module_name from forms_history where module_name is not null and module_name <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				qry = qry + " and contract = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				qry = qry + " and work = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				qry = qry + " and user = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY module_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
				pValues[i++] = obj.getContract();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				pValues[i++] = obj.getWork();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
				pValues[i++] = obj.getUser();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public UserActivityReport getUserActivityReportData(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objtList = null;
		UserActivityReport uObj = null;
		try {
			String datesQry ="SELECT ADDDATE(?, INTERVAL @i:=@i+1 DAY) AS date FROM (SELECT a.a FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) a JOIN (SELECT @i := -1) r1 WHERE @i < DATEDIFF(?, ?)";			
			Object[] dValues = new Object[3];
			int j = 0;
			dValues[j++] = obj.getFrom_date();
			dValues[j++] = obj.getTo_date();
			dValues[j++] = obj.getFrom_date();
			
			List<UserActivityReport> datesList = jdbcTemplate.query( datesQry,dValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
			obj.setDatesList(datesList);
			for (UserActivityReport dataList : datesList) {
				String qry = "SELECT form_history_id, module_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,DATE_FORMAT(created_date,'%H:%i ') time"
						+ " FROM forms_history " + 
						"left join user u on created_by_user_id_fk = u.user_id  "
						+ "where DATE(created_date) = ? ";
				int arrSize = 1;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
					qry = qry + " and module_name = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					qry = qry + " and contract = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					qry = qry + " and work = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					qry = qry + " and user = ?";
					arrSize++;
				}
				qry = qry + " order by time asc ";
				Object[] pValues = new Object[arrSize];
				int i = 0;
				pValues[i++] = dataList.getDate();
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
					pValues[i++] = obj.getModule_name();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					pValues[i++] = obj.getContract();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					pValues[i++] = obj.getWork();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					pValues[i++] = obj.getUser();
				}
				objtList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
				
				dataList.setUserActivitiesList(objtList);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return obj;
	}

	@Override
	public List<UserActivityReport> getUserActivityReportFormData(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objtList = null;
		try {
		
				String qry = "SELECT form_history_id, module_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,DATE_FORMAT(created_date,'%H:%i ') time"
						+ " FROM forms_history " + 
						"left join user u on created_by_user_id_fk = u.user_id  "
						+ "where DATE(created_date) between ? and ?  ";
				int arrSize = 2;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
					qry = qry + " and module_name = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					qry = qry + " and contract = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					qry = qry + " and work = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					qry = qry + " and user = ?";
					arrSize++;
				}
				qry = qry + " order by time asc ";
				Object[] pValues = new Object[arrSize];
				int i = 0;
				pValues[i++] = obj.getFrom_date();
				pValues[i++] = obj.getTo_date();
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
					pValues[i++] = obj.getModule_name();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					pValues[i++] = obj.getContract();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					pValues[i++] = obj.getWork();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					pValues[i++] = obj.getUser();
				}
				objtList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objtList;
	}
}
