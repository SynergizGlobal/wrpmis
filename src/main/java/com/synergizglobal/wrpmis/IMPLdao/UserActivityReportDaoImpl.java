package com.synergizglobal.wrpmis.IMPLdao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.wrpmis.Idao.UserActivityReportDao;
import com.synergizglobal.wrpmis.model.UserActivityReport;
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
				qry = qry + " and [user] = ?";
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
				qry = qry + " and [user] = ?";
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
			String qry = "SELECT [user] from forms_history where [user] is not null and [user] <> ''  ";
			
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
				qry = qry + " and [user] = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY [user] ";
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
				qry = qry + " and [user] = ?";
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
			
			String qry = "SELECT DISTINCT [user]"
					+ " FROM forms_history " + 
					"LEFT JOIN [user] u on created_by_user_id_fk = u.user_id  "
					+ "where created_date >= ?  and created_date <= ?";
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
				qry = qry + " and [user] = ?";
				arrSize++;
			}
			qry = qry + " order by [user] asc ";
			
			
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
				String qry1 = "SELECT form_history_id, module_name_fk,form_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, user,created_date as date,FORMAT(created_date,'%H:%i ') time"
						+ " FROM forms_history " + 
						"LEFT JOIN [user] u on created_by_user_id_fk = u.user_id  "
						+ "where user=? and created_date >= ?  and created_date <= ? ";
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
					qry1 = qry1 + " and [user] = ?";
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
		
				String qry = "SELECT form_history_id, module_name_fk,form_name, work, u.user_name,contract, form_action_type, form_details, created_by_user_id_fk, [user],FORMAT(created_date,'hh:m ') time \r\n" + 
						"FROM forms_history LEFT JOIN [user] u on created_by_user_id_fk = u.user_id  where created_date between ? and ?  ";
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
					qry = qry + " and [user] = ?";
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
			
			qry = qry + " GROUP BY work_id,work_name,work_short_name";
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
			String qry = "SELECT module_name from module where module_name IN('Contracts','Execution &  Monitoring') ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name = ? ";
				arrSize++;
			}			
			qry = qry + " GROUP BY module_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int checkInactiveUsersExistsOrNot(UserActivityReport obj) throws Exception {
		int count = 0;
		try {
			UserActivityReport inactiveUsers = getInactiveUsersReportData(obj);
			if(!StringUtils.isEmpty(inactiveUsers.getInactiveUsers())) {
				Map<String, List<UserActivityReport>> mObj = inactiveUsers.getInactiveUsers();
				for (Map.Entry<String, List<UserActivityReport>> entry : mObj.entrySet()) {
					List<UserActivityReport> list = entry.getValue();
					if(!StringUtils.isEmpty(list) && list.size() > 0) {
						count = count + list.size();
					}
				}
			}
		}catch (Exception e) {
			throw new Exception(e);
		}
		return count;
	}

	@Override
	public UserActivityReport getInactiveUsersReportData(UserActivityReport obj) throws Exception {
		UserActivityReport reportObj = new UserActivityReport();
		try {
			List<UserActivityReport> modules = getModulesListForUserInactiveReportForm(obj);
			Map<String,List<UserActivityReport>> inactiveUsers = new LinkedHashMap<String, List<UserActivityReport>>();
			for (UserActivityReport module : modules) {
				List<String> userIdsList = new ArrayList<String>();
				List<UserActivityReport> list = new ArrayList<UserActivityReport>();
				if("Contracts".equals(module.getModule_name()) ) {
					/*String qry = "SELECT hod_user_id_fk as user_id_fk,contract_id,contract_name,contract_short_name FROM contract where hod_user_id_fk IS NOT NULL AND status = ? group by hod_user_id_fk";
					List<UserActivityReport> hodList = jdbcTemplate.query( qry,new Object[]{"Open"},new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
					if(!StringUtils.isEmpty(hodList) && hodList.size() > 0) {
						list.addAll(hodList);
					}
					
					qry = "SELECT dy_hod_user_id_fk as user_id_fk,contract_id,contract_name,contract_short_name FROM contract where dy_hod_user_id_fk IS NOT NULL AND status = ? group by dy_hod_user_id_fk";
					List<UserActivityReport> dyhodList = jdbcTemplate.query( qry,new Object[]{"Open"},new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
					if(!StringUtils.isEmpty(dyhodList) && dyhodList.size() > 0) {
						list.addAll(dyhodList);
					}
					
					qry = "SELECT executive_user_id_fk as user_id_fk,c.contract_id,c.contract_name,c.contract_short_name "
							+ "FROM contract_executive ce "
						+ "left join contract c on ce.contract_id_fk = c.contract_id "
						+ "where executive_user_id_fk IS NOT NULL AND c.status = ? group by executive_user_id_fk";*/
					
					String qry = "SELECT user,user_id_fk,contract_id,contract_name,contract_short_name "
							+ "FROM "
							+ "(SELECT CONCAT(u.designation,' - ',u.user_name) as [user],hod_user_id_fk as user_id_fk,contract_id,contract_name,contract_short_name "
							+ "FROM contract c "
							+ "LEFT JOIN [user] u ON hod_user_id_fk = user_id "
							+ "where hod_user_id_fk IS NOT NULL AND status = ? AND c.work_id_fk = ? group by hod_user_id_fk "
							+ "UNION ALL "
							+ "SELECT CONCAT(u.designation,' - ',u.user_name) as [user],dy_hod_user_id_fk as user_id_fk,contract_id,contract_name,contract_short_name "
							+ "FROM contract c "
							+ "LEFT JOIN [user] u ON dy_hod_user_id_fk = user_id "
							+ "where dy_hod_user_id_fk IS NOT NULL AND status = ? AND c.work_id_fk = ? group by dy_hod_user_id_fk "
							+ "UNION ALL "
							+ "SELECT CONCAT(u.designation,' - ',u.user_name) as [user],executive_user_id_fk as user_id_fk,c.contract_id,c.contract_name,c.contract_short_name "
							+ "FROM contract_executive ce "
							+ "LEFT JOIN [user] u ON executive_user_id_fk = user_id "
							+ "LEFT JOIN contract c on ce.contract_id_fk = c.contract_id "
							+ "where executive_user_id_fk IS NOT NULL AND c.status = ? AND c.work_id_fk = ? group by executive_user_id_fk "
							+ ") t "
							+ "GROUP BY user_id_fk,contract_id ";
					int arrSize = 6;
					Object[] pValues = new Object[arrSize];
					int i = 0;
					pValues[i++] = "Open";
					pValues[i++] = obj.getWork_id_fk();
					pValues[i++] = "Open";
					pValues[i++] = obj.getWork_id_fk();
					pValues[i++] = "Open";
					pValues[i++] = obj.getWork_id_fk();
					List<UserActivityReport> executivesList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
					if(!StringUtils.isEmpty(executivesList) && executivesList.size() > 0) {
						list.addAll(executivesList);
					}
					/*if(!StringUtils.isEmpty(list) && list.size() > 0) {
						list = list.stream().distinct().collect(Collectors.toList());
					}*/
					if(!StringUtils.isEmpty(list) && list.size() > 0) {
						obj.setModule_name(module.getModule_name());
						List<UserActivityReport> inactiveUsersList = new ArrayList<UserActivityReport>();
						for (UserActivityReport userObj : list) {
							UserActivityReport inactiveUser = getInactiveUser(obj,userObj.getUser_id_fk());
							if(!StringUtils.isEmpty(inactiveUser) && "YES".equals(inactiveUser.getUser_inactive()) ) {
								userObj.setLast_updated_date(inactiveUser.getLast_updated_date());
								inactiveUsersList.add(userObj);
							}
						}
						if(!StringUtils.isEmpty(inactiveUsersList) && inactiveUsersList.size() > 0) {
							inactiveUsers.put(module.getModule_name(), inactiveUsersList);
						}
					}
				}else if("Execution &  Monitoring".equals(module.getModule_name()) ) {
					/*String qry = "SELECT responsible_people_id_fk as user_id_fk,c.contract_id,c.contract_name,c.contract_short_name "
							+ "FROM fob_contract_responsible_people fcr "
						+ "left join contract c on fcr.contract_id_fk = c.contract_id "
						+ "where responsible_people_id_fk IS NOT NULL AND c.status = ? group by responsible_people_id_fk";
					List<UserActivityReport> responsiblePeopleList = jdbcTemplate.query( qry,new Object[]{"Open"},new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
					if(!StringUtils.isEmpty(responsiblePeopleList) && responsiblePeopleList.size() > 0) {
						list.addAll(responsiblePeopleList);
					}
					
					qry = "SELECT responsible_people_id_fk as user_id_fk,c.contract_id,c.contract_name,c.contract_short_name "
							+ "FROM structure_contract_responsible_people scr "
						+ "left join contract c on scr.contract_id_fk = c.contract_id "
						+ "where responsible_people_id_fk IS NOT NULL AND c.status = ? group by responsible_people_id_fk";*/
					
					String qry = "SELECT [user],user_id_fk,contract_id,contract_name,contract_short_name FROM (SELECT CONCAT(u.designation,' - ',u.user_name) as [user], responsible_people_id_fk as user_id_fk,c.contract_id,\r\n" + 
							"c.contract_name,c.contract_short_name FROM fob_contract_responsible_people fcr LEFT JOIN [user] u ON responsible_people_id_fk = user_id LEFT JOIN contract c on fcr.contract_id_fk = c.contract_id \r\n" + 
							"where responsible_people_id_fk IS NOT NULL AND c.status = ? AND c.work_id_fk = ?\r\n" + 
							"group by responsible_people_id_fk ,u.designation,u.user_name,contract_id,contract_name,contract_short_name\r\n" + 
							"\r\n" + 
							"UNION ALL SELECT CONCAT(u.designation,' - ',u.user_name) as [user],\r\n" + 
							"responsible_people_id_fk as user_id_fk,c.contract_id,c.contract_name,c.contract_short_name FROM structure_contract_responsible_people scr \r\n" + 
							"LEFT JOIN [user] u ON responsible_people_id_fk = user_id LEFT JOIN contract c on scr.contract_id_fk = c.contract_id \r\n" + 
							"where responsible_people_id_fk IS NOT NULL  AND c.status = ? AND c.work_id_fk = ? group by responsible_people_id_fk,u.designation,u.user_name,contract_id,contract_name,contract_short_name) t \r\n" + 
							"GROUP BY user_id_fk,contract_id,[user],contract_name,contract_short_name";
					
					int arrSize = 4;
					Object[] pValues = new Object[arrSize];
					int i = 0;
					pValues[i++] = "Open";
					pValues[i++] = obj.getWork_id_fk();
					pValues[i++] = "Open";
					pValues[i++] = obj.getWork_id_fk();
					
					List<UserActivityReport> structureContractRespList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
					if(!StringUtils.isEmpty(structureContractRespList) && structureContractRespList.size() > 0) {
						list.addAll(structureContractRespList);
					}
					
					/*if(!StringUtils.isEmpty(list) && list.size() > 0) {
						list = list.stream().distinct().collect(Collectors.toList());
					}*/
					if(!StringUtils.isEmpty(list) && list.size() > 0) {
						obj.setModule_name(module.getModule_name());
						List<UserActivityReport> inactiveUsersList = new ArrayList<UserActivityReport>();
						for (UserActivityReport userObj : list) {
							UserActivityReport inactiveUser = getInactiveUser(obj,userObj.getUser_id_fk());
							if(!StringUtils.isEmpty(inactiveUser) && "YES".equals(inactiveUser.getUser_inactive()) ) {
								userObj.setLast_updated_date(inactiveUser.getLast_updated_date());
								inactiveUsersList.add(userObj);
							}
						}
						
						if(!StringUtils.isEmpty(inactiveUsersList) && inactiveUsersList.size() > 0) {
							inactiveUsers.put(module.getModule_name(), inactiveUsersList);
						}
					}
				}
			}
			reportObj.setInactiveUsers(inactiveUsers);
		}catch (Exception e) {
			throw new Exception(e);
		}
		return reportObj;
	}

	private UserActivityReport getInactiveUser(UserActivityReport obj, String user_id) throws Exception {
		UserActivityReport inactiveUser = null;
		try {
			String qry = "SELECT form_history_id,module_name_fk,form_name,work,contract,form_action_type,"
					+ "form_details,created_by_user_id_fk,user,created_date "
					+ "FROM forms_history fh "
					+ "WHERE (FORMAT(created_date,'%Y-%m-%d') BETWEEN FORMAT((CONVERT(date, getdate()) - INTERVAL ? DAY),'%Y-%m-%d') AND FORMAT(CONVERT(date, getdate()),'%Y-%m-%d')) "
					+ "AND module_name_fk = ? "
					+ "AND created_by_user_id_fk = ? ";
			
			int arrSize = 3;
			
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " AND fh.work  = (select work_short_name from work where work_id = ?)";
				arrSize++;
			}
			qry = qry + " ORDER BY created_date DESC offset 0 rows  fetch next 1 rows only";
			//qry = qry + " GROUP BY u.user_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getInactive_since();
			pValues[i++] = obj.getModule_name();
			pValues[i++] = user_id;
			if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			List<UserActivityReport> inactiveUsers = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
			UserActivityReport tempObj = null;
			for (UserActivityReport userActivityReport : inactiveUsers) {
				tempObj = userActivityReport;
			}
			
			if(StringUtils.isEmpty(tempObj)){
				inactiveUser = new UserActivityReport();
				qry = "SELECT FORMAT(MAX(created_date),'dd-MMM-yy') AS last_updated_date "
						+ "FROM forms_history fh "
						+ "WHERE module_name_fk = ? "
						+ "AND created_by_user_id_fk = ? ";
				
				arrSize = 2;
				
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " AND fh.work  = (select work_short_name from work where work_id = ?)";
					arrSize++;
				}
				//qry = qry + " GROUP BY u.user_id";
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = obj.getModule_name();
				pValues[i++] = user_id;
				if(!StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				inactiveUsers = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UserActivityReport>(UserActivityReport.class));
				for (UserActivityReport userActivityReport : inactiveUsers) {
					inactiveUser = userActivityReport;
				}
				inactiveUser.setUser_inactive("YES");
			}
			/*******************************************************************************/
			
			
			
			/********************************************************************************/
		} catch (Exception e) {
			throw new Exception(e);
		}
		return inactiveUser;
	}
	
	
}
