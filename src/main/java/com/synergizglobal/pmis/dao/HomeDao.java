package com.synergizglobal.pmis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;

@Repository
public class HomeDao {
	Logger logger = Logger.getLogger(HomeDao.class);
	@Autowired
	DataSource dataSource;
	
	/**
	 * This method get the Menu list
	 * @return type of this method is  menuList that is List type object
	 * @throws Exception will raise an exception when abnormal termination occur.
	 */
	public List<TableauDashboard> getMenuList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> menuList = new ArrayList<TableauDashboard>();
		TableauDashboard tableau = null;
		List<TableauDashboard> tableauSubList = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,tum.priority,icon_path "
					+ "FROM dashboard tum "
					+ "WHERE parent_id_sr_fk = tum.dashboard_id and tum.soft_delete_status_id_fk = ? order by priority";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				tableauSubList = null;
				tableau = new TableauDashboard();
				tableau.setTableauDashboardId(resultSet.getString("dashboard_id"));
				tableau.setTableauDashboardName(resultSet.getString("dashboard_name"));
				tableau.setPriority(resultSet.getString("priority"));
				tableau.setImagePath(resultSet.getString("icon_path"));
				tableauSubList = getTableauSubList(tableau.getTableauDashboardId(),connection);
				if(!tableauSubList.isEmpty() && tableauSubList.size() > 0){
					tableau.setTableauSubList(tableauSubList);
				}
				
				menuList.add(tableau);
			}	
			
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return menuList;
	}
	
	/**
	 * This method get the tableau sub list 
	 * @param parentId it is string type variable that holds the parentId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is  menuList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<TableauDashboard> getTableauSubList(String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> menuList = new ArrayList<TableauDashboard>();
		TableauDashboard tableauDashboard = null;
		
		try {
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,tum.priority,icon_path "
					+ "FROM dashboard tum "
					+ "WHERE parent_id_sr_fk <> tum.dashboard_id and parent_id_sr_fk = ? and tum.soft_delete_status_id_fk = ? order by priority";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, parentId);
			statement.setString(2, CommonConstants.ACTIVE);
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				tableauDashboard = new TableauDashboard();
				tableauDashboard.setTableauDashboardId(resultSet.getString("dashboard_id"));
				tableauDashboard.setTableauDashboardName(resultSet.getString("dashboard_name"));
				tableauDashboard.setImagePath(resultSet.getString("icon_path"));
				menuList.add(tableauDashboard);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return menuList;
	}
	
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Forms> getFormsList(String base) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			connection = dataSource.getConnection();
			//String qry = "SELECT id,form_name,web_form_url,mobile_form_url,priority,status_id FROM forms WHERE status_id = ? ";
			
			String qry = "SELECT id,form_name,parent_id,web_form_url,mobile_form_url,priority,status_id "
					+ "FROM forms f "
					+ "WHERE parent_id = f.id and f.status_id = ? ";
			
			
			/*if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and mobile_form_url IS NOT NULL and mobile_form_url <> ''";
			}*/
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("status_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("web_form_url"));
				obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("status_id"));
				String parentId = resultSet.getString("parent_id");
				
				obj.setFormsSubMenu(getFormsSubList(base,parentId, connection));
				
				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the forms sub list
	 * @param base it is string type variable that holds the base
	 * @param parentId it is string type variable that holds the parentId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<Forms> getFormsSubList(String base, String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			String qry = "SELECT id,form_name,parent_id,web_form_url,mobile_form_url,priority,status_id "
					+ "FROM forms f "
					+ "WHERE parent_id <> f.id and parent_id = ? and f.status_id = ? ";
			
			
			if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and mobile_form_url IS NOT NULL and mobile_form_url <> ''";
			}
			qry = qry + " ORDER BY priority ASC";
			
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, parentId);
			statement.setString(2, CommonConstants.ACTIVE);
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("status_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("web_form_url"));
				obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("status_id"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the projects list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProjectsList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select project_id,project_description from project";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setProjectId(resultSet.getString("project_id"));
				obj.setProjectName(resultSet.getString("project_description"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the work list by project
	 * 
	 * @param obj is object for the model class Activity 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 * 
	 */
	public List<Activity> getWorksListByProject(Activity obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity work = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select work_id,work_name from `work` where project_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, obj.getProjectId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				work = new Activity();
				work.setWorkId(resultSet.getString("work_id"));
				work.setWorkName(resultSet.getString("work_name"));
				objsList.add(work);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the modules list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getModulesList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select module_id,module_name from module";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setModuleId(resultSet.getString("module_id"));
				obj.setModuleName(resultSet.getString("module_name"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the work module status
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is workModuleStatus that is object type of model class Activity 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getWorkModuleStatus(Activity obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Activity workModuleStatus = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select work_id_fk,module_id_fk,`status` as workModuleStatus from work_module_status where work_id_fk = ? and module_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, obj.getWorkId());
			statement.setString(2, obj.getModuleId());
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {		
				workModuleStatus = new Activity();
				workModuleStatus.setWorkModuleStatus(resultSet.getString("workModuleStatus"));
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return workModuleStatus;
	}

	public List<User> getUserList(String user_Id) throws Exception {
		System.out.println(user_Id);
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> userDetails = new ArrayList<User>();
		User obj = null;
		try{  
			con = dataSource.getConnection();
			
			//String adminRoleId = getRoleId(CommonConstants.ADMIN_ROLE_NAME,con);
			
			String qry = "select user_id,user_name,email_id "
					+ "from user "
					+ "where user_id = BINARY ?";
			
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1, user_Id);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				obj = new User();
			//	userDeails.setUserId(rs.getString("user_id"));
			//	userDeails.setPassword(rs.getString("user_name"));
				obj.setUserId(rs.getString("user_id"));
				obj.setUserName(rs.getString("user_name"));
				obj.setEmailId(rs.getString("email_id"));
				userDetails.add(obj);
				
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userDetails;
	}

	
}
