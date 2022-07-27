package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DashboardsAccessDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.model.Messages;
@Repository
public class DashboardsAccessDaoImpl implements DashboardsAccessDao{


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Dashboard> getDashboardsList(Dashboard obj) throws Exception {
		List<Dashboard>  objsList = null;
		try {
			String qry ="SELECT d.dashboard_id,d1.dashboard_name as folder,c.contract_short_name,w.work_short_name,d.dashboard_name,d.work_id_fk,d.contract_id_fk,d.module_name_fk,d.parent_dashboard_id_sr_fk,d.dashboard_url,d.mobile_view,d.dashboard_type_fk,d.priority, " + 
					" d.icon_path,d.published_by_user_id_fk,d.published_on,d.modified_by_user_id_fk,d.modified_on,d.soft_delete_status_fk,"
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_role_access, "
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_type_access, "
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_access "
					+ "FROM dashboard d " + 
					" left join dashboard d1 on d.parent_dashboard_id_sr_fk = d1.dashboard_id " + 
					" left join work w on d.work_id_fk = w.work_id " + 
					" left join contract c on d.contract_id_fk = c.contract_id " + 
					" where d.dashboard_id is not null ";
			
			int arrSize = 3;
			if("MRVC".equals(obj.getUser_type_access())) {
				qry = qry + " and d.dashboard_url IS NOT NULL and d.dashboard_url <>  ";
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and d.module_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				qry = qry + " and d.dashboard_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and d.soft_delete_status_fk = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "user_role";
			pValues[i++] = "user_type";
			pValues[i++] = "user";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				pValues[i++] = obj.getDashboard_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
 
	@Override
	public List<Dashboard> getModulesFilterListInDashboard(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "SELECT module_name_fk from dashboard  " + 
					"where module_name_fk is not null and module_name_fk <>  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				qry = qry + " and dashboard_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY module_name_fk ORDER BY module_name_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				pValues[i++] = obj.getDashboard_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getDashboardTypesFilterListInDashboard(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "SELECT dashboard_type_fk from dashboard  " + 
					"where dashboard_type_fk is not null and dashboard_type_fk <>  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				qry = qry + " and dashboard_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY dashboard_type_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				pValues[i++] = obj.getDashboard_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getStatusFilterListInDashboard(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "SELECT soft_delete_status_fk from dashboard  " + 
					"where soft_delete_status_fk is not null and soft_delete_status_fk <>  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				qry = qry + " and dashboard_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY soft_delete_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDashboard_type_fk())) {
				pValues[i++] = obj.getDashboard_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getWorkListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = new ArrayList<Dashboard>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name "
					+ "from work "
					+ "where work_id is not null ";
			
			qry = qry + " order by work_id asc";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getContractsListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getDashboardTypesListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select dashboard_type as dashboard_type_fk from dashboard_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<Dashboard> getModulesListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select module_name as module_name_fk from module ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<Dashboard> getFolderssListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "SELECT d1.dashboard_id,d1.dashboard_name as folder FROM dashboard d " + 
					" left join dashboard d1 on  d.parent_dashboard_id_sr_fk = d1.dashboard_id where d.dashboard_url <> ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<Dashboard> getStatusListForDashboardForm(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select soft_delete_status as soft_delete_status_fk from soft_delete_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public Dashboard getDashboardForm(Dashboard obj) throws Exception {
		Dashboard dObj = null;
		try {
			String qry ="SELECT d.dashboard_id,d1.dashboard_name as folder,c.contract_short_name,w.work_short_name,d.dashboard_name,d.work_id_fk,d.contract_id_fk,d.module_name_fk,d.parent_dashboard_id_sr_fk,d.dashboard_url,d.mobile_view,d.dashboard_type_fk,d.priority, " + 
					"d.icon_path,d.published_by_user_id_fk,d.published_on,d.modified_by_user_id_fk,d.modified_on,d.soft_delete_status_fk, "
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_role_access, "
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_type_access, "
					+ "(select STRING_AGG(access_value) from dashboard_access where dashboard_id_fk = d.dashboard_id and access_type = ?) as user_access "
					+ "FROM dashboard d  " + 
					"left join dashboard d1 on d.parent_dashboard_id_sr_fk = d1.dashboard_id  " + 
					"left join work w on d.work_id_fk = w.work_id  " + 
					"left join contract c on d.contract_id_fk = c.contract_id " 
					+ "where d.dashboard_id is not null and d.dashboard_id = ?"; 
					
			int arrSize = 4;

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "user_role";
			pValues[i++] = "user_type";
			pValues[i++] = "user";
			pValues[i++] = obj.getDashboard_id();
			
			dObj = (Dashboard)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
			
			if(!StringUtils.isEmpty(dObj) && !StringUtils.isEmpty(dObj.getDashboard_id())) {
				List<Dashboard> objsList = null;
				String qryUserPermission = "select access_type,access_value from dashboard_access where dashboard_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryUserPermission, new Object[] {dObj.getDashboard_id()}, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));	
				
				dObj.setAccessPermissions(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return dObj;
	}

	@Override
	public List<Dashboard> getUserRolesInDashboardAccess(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select user_role_name as access_value_id FROM [user]_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getUserTypesInDashboardAccess(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select user_type as access_value_id FROM [user]_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Dashboard> getUsersInDashboardAccess(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select user_id as access_value_id,user_name as access_value_name FROM [user]";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	
	
	@Override
	public boolean updateTableauDashboard(Dashboard obj) throws Exception {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(obj.getFolder())) {
				obj.setFolder(obj.getDashboard_id());
			}
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE dashboard set "
					+ "mobile_view= :mobile_view,priority= :priority,modified_by_user_id_fk = :modified_by_user_id_fk, modified_on= CURDATE(),soft_delete_status_fk= :soft_delete_status_fk "
					+ "where dashboard_id= :dashboard_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				
				String deleteQry ="delete from dashboard_access where dashboard_id_fk = :dashboard_id ";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				if(!StringUtils.isEmpty(obj.getUser_role_access())) {
					String[] user_role_access = obj.getUser_role_access().split(",");
					int user_role_access_count = user_role_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
					String messageQry = "INSERT INTO dashboard_access (dashboard_id_fk,access_type,access_value)"
							+ "VALUES" + "(:dashboard_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_role_access_count; i++) {
						Dashboard msgObj = new Dashboard();
						msgObj.setDashboard_id(obj.getDashboard_id());
						msgObj.setAccess_type("user_role");
						msgObj.setAccess_value(user_role_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_type_access())) {
					String[] user_type_access = obj.getUser_type_access().split(",");
					int user_type_access_count = user_type_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
					String messageQry = "INSERT INTO dashboard_access (dashboard_id_fk,access_type,access_value)"
							+ "VALUES" + "(:dashboard_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_type_access_count; i++) {
						Dashboard msgObj = new Dashboard();
						msgObj.setDashboard_id(obj.getDashboard_id());
						msgObj.setAccess_type("user_type");
						msgObj.setAccess_value(user_type_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_access())) {
					String[] user_access = obj.getUser_access().split(",");
					int user_access_count = user_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_access_count];
					String messageQry = "INSERT INTO dashboard_access (dashboard_id_fk,access_type,access_value)"
							+ "VALUES" + "(:dashboard_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_access_count; i++) {
						Dashboard msgObj = new Dashboard();
						msgObj.setDashboard_id(obj.getDashboard_id());
						msgObj.setAccess_type("user");
						msgObj.setAccess_value(user_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
}
