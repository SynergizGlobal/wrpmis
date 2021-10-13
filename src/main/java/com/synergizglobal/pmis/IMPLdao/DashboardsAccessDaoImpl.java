package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DashboardsAccessDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Dashboard;
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
					" d.icon_path,d.published_by_user_id_fk,d.published_on,d.modified_by_user_id_fk,d.modified_on,d.soft_delete_status_fk FROM dashboard d " + 
					" left join dashboard d1 on d.parent_dashboard_id_sr_fk = d1.dashboard_id " + 
					" left join work w on d.work_id_fk = w.work_id " + 
					" left join contract c on d.contract_id_fk = c.contract_id " + 
					" where d.dashboard_url <> '' ";
			
			int arrSize = 0;
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
					"where module_name_fk is not null and module_name_fk <> '' ";
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
			qry = qry + "GROUP BY module_name_fk ";
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
					"where dashboard_type_fk is not null and dashboard_type_fk <> '' ";
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
					"where soft_delete_status_fk is not null and soft_delete_status_fk <> '' ";
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
					+ "from `work` "
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
					" left join dashboard d1 on  d.parent_dashboard_id_sr_fk = d1.dashboard_id where d.dashboard_url <> ''";
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
					"d.icon_path,d.published_by_user_id_fk,d.published_on,d.modified_by_user_id_fk,d.modified_on,d.soft_delete_status_fk FROM dashboard d  " + 
					"left join dashboard d1 on d.parent_dashboard_id_sr_fk = d1.dashboard_id  " + 
					"left join work w on d.work_id_fk = w.work_id  " + 
					"left join contract c on d.contract_id_fk = c.contract_id " 
					+ "where d.dashboard_id is not null and d.dashboard_id = ?" ;
			
			dObj = (Dashboard)jdbcTemplate.queryForObject(qry, new Object[] {obj.getDashboard_id()}, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));
			
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
	public boolean addDashboard(Dashboard obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String dashboard_id = getdashboardId(con);
			obj.setDashboard_id(dashboard_id);
			if(StringUtils.isEmpty(obj.getFolder())) {
				obj.setFolder(dashboard_id);
			}
			String insertQry = "INSERT INTO dashboard"
					+ "( dashboard_id,dashboard_name,parent_dashboard_id_sr_fk, work_id_fk, contract_id_fk, module_name_fk, dashboard_url, "
					+ "mobile_view, dashboard_type_fk, priority, icon_path, published_by_user_id_fk, published_on,soft_delete_status_fk)"
					+ "VALUES"
					+ "(:dashboard_id,:dashboard_name,:folder,:work_id_fk,:contract_id_fk,:module_name_fk,:dashboard_url,"
					+ ":mobile_view,:dashboard_type_fk,:priority,:icon_path,:published_by_user_id_fk,CURDATE(),:soft_delete_status_fk)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
					if(arraySize < obj.getAccess_types().length) {
						arraySize = obj.getAccess_types().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
					if(arraySize < obj.getAccess_values().length) {
						arraySize = obj.getAccess_values().length;
					}
				}
				
				if(arraySize > 0) {				
					
					String[] types = obj.getAccess_types();
					String[] values = obj.getAccess_values();
					
					String qryUserPermissions = "INSERT INTO dashboard_access (dashboard_id_fk,access_type,access_value) VALUES  (?,?,?)";		
					
					int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
				            new BatchPreparedStatementSetter() {
				                 
				                @Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
				                    ps.setString(1, dashboard_id);
				                    ps.setString(2, types.length > 0?types[i]:null);
				                    ps.setString(3, values.length > 0?values[i]:null);			                    
				                }
				                @Override  
				                public int getBatchSize() {				                	
				                	int arraySize = 0;
				    				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
				    					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
				    					if(arraySize < obj.getAccess_types().length) {
				    						arraySize = obj.getAccess_types().length;
				    					}
				    				}
				    				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
				    					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
				    					if(arraySize < obj.getAccess_values().length) {
				    						arraySize = obj.getAccess_values().length;
				    					}
				    				}
				    				return arraySize; 
				                }
				            });
					
				}
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	private String getdashboardId(Connection con) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String obj = null;
		try {
		
			String qry = "select (dashboard_id + 1) as dashboard_id  from dashboard ORDER BY dashboard_id DESC LIMIT 1";
			ps  = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if(rs.next()) {
				obj = rs.getString("dashboard_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, ps, rs);
		}
		return obj;
	}

	@Override
	public boolean updateDashboard(Dashboard obj) throws Exception {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(obj.getFolder())) {
				obj.setFolder(obj.getDashboard_id());
			}
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE dashboard set "
					+ "dashboard_name= :dashboard_name,parent_dashboard_id_sr_fk= :folder, work_id_fk= :work_id_fk, contract_id_fk= :contract_id_fk,"
					+ "module_name_fk= :module_name_fk, dashboard_url= :dashboard_url, mobile_view= :mobile_view,dashboard_type_fk= :dashboard_type_fk, priority= :priority, "
					+ "icon_path= :icon_path, modified_by_user_id_fk = :modified_by_user_id_fk, modified_on= CURDATE(),soft_delete_status_fk= :soft_delete_status_fk "
					+ "where dashboard_id= :dashboard_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				
				String deleteQry ="delete from dashboard_access where dashboard_id_fk = :dashboard_id ";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
					if(arraySize < obj.getAccess_types().length) {
						arraySize = obj.getAccess_types().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
					if(arraySize < obj.getAccess_values().length) {
						arraySize = obj.getAccess_values().length;
					}
				}
				
				
				if(arraySize > 0) {				
					
					String[] types = obj.getAccess_types();
					String[] values = obj.getAccess_values();
					
					String qryUserPermissions = "INSERT INTO dashboard_access (dashboard_id_fk,access_type,access_value) VALUES  (?,?,?)";		
					
					int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
				            new BatchPreparedStatementSetter() {
				                 
				                @Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
				                    ps.setString(1, obj.getDashboard_id());
				                    ps.setString(2, types.length > 0?types[i]:null);
				                    ps.setString(3, values.length > 0?values[i]:null);			                    
				                }
				                @Override  
				                public int getBatchSize() {				                	
				                	int arraySize = 0;
				    				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
				    					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
				    					if(arraySize < obj.getAccess_types().length) {
				    						arraySize = obj.getAccess_types().length;
				    					}
				    				}
				    				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
				    					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
				    					if(arraySize < obj.getAccess_values().length) {
				    						arraySize = obj.getAccess_values().length;
				    					}
				    				}
				    				return arraySize; 
				                }
				            });
					
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<Dashboard> getUserRolesInDashboardAccess(Dashboard obj) throws Exception {
		List<Dashboard> objsList = null;
		try {
			String qry = "select user_role_name as access_value_id from user_role";
			
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
			String qry = "select user_type as access_value_id from user_type";
			
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
			String qry = "select user_id as access_value_id,user_name as access_value_name from user";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Dashboard>(Dashboard.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	
}
