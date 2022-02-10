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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getWorksFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT work from forms_history where work is not null and work <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getHODsFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT user from forms_history where user is not null and user <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getModulessFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT module_name_fk from forms_history where module_name_fk is not null and module_name_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
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
			
			qry = qry + " GROUP BY module_name_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public UserActivityReport getUserActivityReportData(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objtList = null;
		List<UserActivityReport> objtList1 = null;
		UserActivityReport uObj = null;
		try {
			
			String qry = "SELECT DISTINCT user"
					+ " FROM forms_history " + 
					"left join user u on created_by_user_id_fk = u.user_id  "
					+ "where DATE(created_date) >= ?  and DATE(created_date) <= ?";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
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
			qry = qry + " order by user asc ";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getFrom_date();
			pValues[i++] = obj.getTo_date();
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
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
			List<UserActivityReport> usersList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
			obj.setUsersList(usersList);	

			for (UserActivityReport dataList : usersList) 
			{
				String qry1 = "SELECT form_history_id, module_name_fk,form_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,created_date as date,DATE_FORMAT(created_date,'%H:%i ') time"
						+ " FROM forms_history " + 
						"left join user u on created_by_user_id_fk = u.user_id  "
						+ "where user=? and DATE(created_date) >= ?  and DATE(created_date) <= ? ";
				int arrSize1 = 3;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
					qry1 = qry1 + " and module_name_fk = ? ";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					qry1 = qry1 + " and contract = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					qry1 = qry1 + " and work = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					qry1 = qry1 + " and user = ?";
					arrSize1++;
				}
				qry = qry + " order by time asc ";
				Object[] pValues1 = new Object[arrSize1];
				int i1 = 0;
				pValues1[i1++] = dataList.getUser();
				pValues1[i1++] = obj.getFrom_date();
				pValues1[i1++] = obj.getTo_date();
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
					pValues1[i1++] = obj.getModule_name_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract())) {
					pValues1[i1++] = obj.getContract();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
					pValues1[i1++] = obj.getWork();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser())) {
					pValues1[i1++] = obj.getUser();
				}
				objtList1 = jdbcTemplate.query( qry1,pValues1, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
				
				dataList.setUserActivitiesList(objtList1);
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
		
				String qry = "SELECT form_history_id, module_name_fk,form_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,DATE_FORMAT(created_date,'%H:%i ') time"
						+ " FROM forms_history " + 
						"left join user u on created_by_user_id_fk = u.user_id  "
						+ "where DATE(created_date) between ? and ?  ";
				int arrSize = 2;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
					qry = qry + " and module_name_fk = ? ";
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
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
					pValues[i++] = obj.getModule_name_fk();
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

	@Override
	public List<UserActivityReport> getWorksListForUserInactiveReportForm(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT work_id,work_name,work_short_name from work where work_id is not null and work_id <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY work_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> getModulesListForUserInactiveReportForm(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objsList = null;
		try {
			String qry = "SELECT module_name from module where module_name IN('Contracts','Design','Execution &  Monitoring','Issues','Risk','Safety') ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}			
			qry = qry + " GROUP BY module_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserActivityReport> checkInactiveUsersExistsOrNot(UserActivityReport obj) throws Exception {
		List<UserActivityReport> objtList = null;
		try {
		
			String qry = "SELECT form_history_id, module_name_fk, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,DATE_FORMAT(created_date,'%H:%i ') time"
					+ " FROM forms_history " + 
					"left join user u on created_by_user_id_fk = u.user_id  "
					+ "where DATE(created_date) between ? and ?  ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				qry = qry + " and work = ?";
				arrSize++;
			}
			qry = qry + " order by time asc ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getFrom_date();
			pValues[i++] = obj.getTo_date();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork())) {
				pValues[i++] = obj.getWork();
			}
			objtList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objtList;
	}
}
