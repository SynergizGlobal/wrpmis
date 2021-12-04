package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.HomeDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.TimeAgo;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Admin;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Work;

@Repository
public class HomeDaoImpl implements HomeDao {
	Logger logger = Logger.getLogger(HomeDao.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	/**
	 * This method get the Menu list
	 * @return type of this method is  dashboardsList that is List type object
	 * @throws Exception will raise an exception when abnormal termination occur.
	 */
	@Override
	public List<TableauDashboard> getDashboardsList(String dashboardType,String base, User uObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> dashboardsList = new ArrayList<TableauDashboard>();
		TableauDashboard tableau = null;
		List<TableauDashboard> tableauSubList = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,dashboard_url,tum.priority,icon_path,mobile_view "
					+ "FROM dashboard tum "
					+ "WHERE parent_dashboard_id_sr_fk = tum.dashboard_id and tum.soft_delete_status_fk = ? and dashboard_type_fk = ? "
					+ " and (select count(*) from dashboard_access where dashboard_id_fk = tum.dashboard_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 "
					+ "order by priority";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			statement.setString(2, dashboardType);
			statement.setString(3, uObj.getUser_type_fk());
			statement.setString(4, uObj.getUser_role_name_fk());
			statement.setString(5, uObj.getUser_id());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				tableauSubList = null;
				tableau = new TableauDashboard();
				tableau.setTableauDashboardId(resultSet.getString("dashboard_id"));
				tableau.setTableauDashboardName(resultSet.getString("dashboard_name"));
				tableau.setPriority(resultSet.getString("priority"));
				tableau.setImagePath(resultSet.getString("icon_path"));
				tableauSubList = getTableauSubList(tableau.getTableauDashboardId(),base,uObj,connection);
				if(!tableauSubList.isEmpty() && tableauSubList.size() > 0){
					tableau.setTableauSubList(tableauSubList);
				}
				
				String dashboardUrl = resultSet.getString("dashboard_url");
				String mobile_view = resultSet.getString("mobile_view");
				
				if(base.equals("web") && (!StringUtils.isEmpty(dashboardUrl) || (!StringUtils.isEmpty(tableauSubList) && tableauSubList.size() > 0 ))) {
					dashboardsList.add(tableau);
				}else if(base.equals("mobile") && !StringUtils.isEmpty(mobile_view) && mobile_view.toUpperCase().equals("YES") && (!StringUtils.isEmpty(dashboardUrl) || (!StringUtils.isEmpty(tableauSubList) && tableauSubList.size() > 0 ))){
					dashboardsList.add(tableau);
				}
			}	
			
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return dashboardsList;
	}

	/**
	 * This method get the tableau sub list 
	 * @param parentId it is string type variable that holds the parentId
	 * @param base 
	 * @param uObj 
	 * @param connection is object for the Connection Interface
	 * @return type of this method is  dashboardsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<TableauDashboard> getTableauSubList(String parentId, String base, User uObj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> dashboardsList = new ArrayList<TableauDashboard>();
		TableauDashboard tableauDashboard = null;
		
		try {
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,tum.priority,icon_path  "
					+ "FROM dashboard tum "
					+ "WHERE parent_dashboard_id_sr_fk <> tum.dashboard_id and parent_dashboard_id_sr_fk = ? "
					+ "and tum.soft_delete_status_fk = ? ";
					
			if(base.equals("mobile")) {
				qry = qry + " and UPPER(mobile_view) = ? ";
			}
			qry = qry + "and (select count(*) from dashboard_access where dashboard_id_fk = tum.dashboard_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			qry = qry + " order by priority";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, parentId);
			statement.setString(p++, CommonConstants.ACTIVE);
			if(base.equals("mobile")) {
				statement.setString(p++, "YES");
			}	
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				tableauDashboard = new TableauDashboard();
				tableauDashboard.setTableauDashboardId(resultSet.getString("dashboard_id"));
				tableauDashboard.setTableauDashboardName(resultSet.getString("dashboard_name"));
				tableauDashboard.setImagePath(resultSet.getString("icon_path"));
				dashboardsList.add(tableauDashboard);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return dashboardsList;
	}
	
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public List<Forms> getFormsList(String base, User uObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			connection = dataSource.getConnection();
			//String qry = "SELECT id,form_name,web_form_url,mobile_form_url,priority,status_id FROM forms WHERE status_id = ? ";
			
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM form f "
					+ "WHERE parent_form_id_sr_fk = f.form_id and f.soft_delete_status_fk = ? ";
			
			
			/*if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and mobile_form_url IS NOT NULL and mobile_form_url <> ''";
			}*/
			
			qry = qry + "and (select count(*) from form_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));
				String parentId = resultSet.getString("parent_form_id_sr_fk");
				List<Forms> subList = getFormsSubList(base,parentId,uObj, connection);
				obj.setFormsSubMenu(subList);
				
				String formUrl = null;
				if(!StringUtils.isEmpty(base) && base.equals("web")) {
					formUrl = resultSet.getString("web_form_url");
				}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
					formUrl = resultSet.getString("mobile_form_url");
				} 
				/*if(!StringUtils.isEmpty(formUrl) || (!StringUtils.isEmpty(subList) && subList.size() > 0 )) {
					objsList.add(obj);
				}*/
				
				objsList.add(obj);
				
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
	 * @param uObj 
	 * @param connection is object for the Connection Interface
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<Forms> getFormsSubList(String base, String parentId, User uObj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM form f "
					+ "WHERE parent_form_id_sr_fk <> f.form_id and parent_form_id_sr_fk = ? and f.soft_delete_status_fk = ? ";
			
			
			/*if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and mobile_form_url IS NOT NULL and mobile_form_url <> ''";
			}*/
			
			qry = qry + "and (select count(*) from form_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, parentId);
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));

				String parentIdLevel2 = resultSet.getString("form_id");
				List<Forms> subList = getFormsSubListLevel2(base,parentIdLevel2,uObj, connection);
				obj.setFormsSubMenuLevel2(subList); 
				
				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objsList;
	}
	
	private List<Forms> getFormsSubListLevel2(String base, String parentId, User uObj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM form f "
					+ "WHERE parent_form_id_sr_fk <> f.form_id and parent_form_id_sr_fk = ? and f.soft_delete_status_fk = ? ";
			
			
			if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			
			qry = qry + "and (select count(*) from form_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, parentId);
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));

				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objsList;
	}
	
	
	private List<Forms> getReportFormsSubListLevel2(String base, String parentId, User uObj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM report_form f "
					+ "WHERE parent_form_id_sr_fk <> f.form_id and parent_form_id_sr_fk = ? and f.soft_delete_status_fk = ? ";
			
			
			if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			
			qry = qry + "and (select count(*) from report_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, parentId);
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));

				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objsList;
	}	
	
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public List<Forms> getReportFormsList(String base, User uObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			connection = dataSource.getConnection();
			//String qry = "SELECT id,form_name,web_form_url,mobile_form_url,priority,status_id FROM forms WHERE status_id = ? ";
			
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM report_form f "
					+ "WHERE parent_form_id_sr_fk = f.form_id and f.soft_delete_status_fk = ? ";
			
			
			/*if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and mobile_form_url IS NOT NULL and mobile_form_url <> ''";
			}*/
			qry = qry + "and (select count(*) from report_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));

				String parentId = resultSet.getString("parent_form_id_sr_fk");
				
				List<Forms> subList = getReportFormsSubList(base,parentId,uObj, connection);
				obj.setFormsSubMenu(subList);
				
				String formUrl = null;
				if(!StringUtils.isEmpty(base) && base.equals("web")) {
					formUrl = resultSet.getString("web_form_url");
				}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
					qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
				}
				if(!StringUtils.isEmpty(formUrl) || (!StringUtils.isEmpty(subList) && subList.size() > 0 )) {
					objsList.add(obj);
				}
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
	 * @param uObj 
	 * @param connection is object for the Connection Interface
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<Forms> getReportFormsSubList(String base, String parentId, User uObj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,f.display_in_mobile "
					+ "FROM report_form f "
					+ "WHERE parent_form_id_sr_fk <> f.form_id and parent_form_id_sr_fk = ? and f.soft_delete_status_fk = ? ";
			
			
			if(!StringUtils.isEmpty(base) && base.equals("web")) {
				qry = qry + " and web_form_url IS NOT NULL and web_form_url <> ''";
			}else if(!StringUtils.isEmpty(base) && base.equals("mobile")) {
				qry = qry + " and display_in_mobile IS NOT NULL and display_in_mobile <> '' and display_in_mobile = 'Yes' ";
			}
			
			qry = qry + "and (select count(*) from report_access where form_id_fk = f.form_id and (access_value = ? or access_value = ? or access_value = ?) ) > 0 ";
			
			qry = qry + " ORDER BY priority ASC";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, parentId);
			statement.setString(p++, CommonConstants.ACTIVE);
			
			statement.setString(p++, uObj.getUser_type_fk());
			statement.setString(p++, uObj.getUser_role_name_fk());
			statement.setString(p++, uObj.getUser_id());
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Forms();
				obj.setFormId(resultSet.getString("form_id"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(resultSet.getString("web_form_url"));
				//obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setMobileFormUrl(resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				obj.setDisplayInMobile(resultSet.getString("display_in_mobile"));
				
				String parentIdLevel2 = resultSet.getString("form_id");
				List<Forms> subList = getReportFormsSubListLevel2(base,parentIdLevel2,uObj, connection);
				obj.setFormsSubMenuLevel2(subList); 

				objsList.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objsList;
	}

	@Override
	public List<Project> getProjectsList() throws Exception {
		List<Project> objsList = null;
		try {
			String qry = "select project_id,project_name,plan_head_number,remarks from `project`";
			//objsList = jdbcTemplate.query( qry, BeanPropertyRowMapper.newInstance(Project.class));
			//OR
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Work> getWorksList(Work obj) throws Exception {
		List<Work> objsList = new ArrayList<Work>();
		try {
			String qry = "select work_id,work_name,project_id_fk,sanctioned_year_fk,sanctioned_estimated_cost,completeion_period_months,"
					+ "sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,w.remarks,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " where project_id_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Work>(Work.class));
			
			/*if(placeholders.length > 0) {
				objsList = jdbcTemplate.query( qry, new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)}, new BeanPropertyRowMapper<Work>(Work.class));
			}else {
				objsList = jdbcTemplate.query( qry, new Object[] {}, new BeanPropertyRowMapper<Work>(Work.class));
			}*/
			
			//OR
			//objsList = jdbcTemplate.query( qry, new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)}, BeanPropertyRowMapper.newInstance(Work.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<String> getExecutionStatusList() throws Exception {
		List<String> objsList = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "select execution_status from execution_status where execution_status is not null and execution_status <> '' ORDER BY FIELD(execution_status,'Not Started','In Progress','On Hold','Commissioned','Completed','Dropped')";
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				objsList.add(resultSet.getString("execution_status").trim());
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}

	@Override
	public List<Project> getProjectsInformation(Project obj) throws Exception {
		List<Project> objsList = new ArrayList<Project>();
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		List<Work> workDocs = null;
		try {
			String projectQry = "select project_id,project_name,plan_head_number,remarks,project_status,attachment,benefits "
					+ "from `project`";
			
			/*String projectDetailsQry = "select sum(wr.sanctioned_estimated_cost) as sanctioned_estimated_cost,max(wr.sanctioned_year_fk) as sanctioned_year_fk,"
					+ "sum(wr.completion_cost) as completion_cost,max(wr.year_of_completion) as year_of_completion, " 
					+ "max(wr.projected_completion) as projected_completion_year,"
					+ "(SELECT sum(y.latest_revised_cost) FROM work_yearly_sanction y left join `work` w on w.work_id = y.work_id_fk  WHERE y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk) and w.project_id_fk = ? group by w.project_id_fk) as latest_revised_cost " 
					+ "from work wr where wr.project_id_fk = ? group by wr.project_id_fk";
			
			String workQry = "select wr.work_id,wr.work_short_name,wr.sanctioned_estimated_cost as sanctioned_estimated_cost,wr.sanctioned_year_fk as sanctioned_year_fk,"
					+ "wr.sanctioned_completion_cost as sanctioned_completion_cost,wr.year_of_completion as year_of_completion, " 
					+ "wr.completion_cost as completion_cost,wr.projected_completion as projected_completion_year, wr.attachment as work_attachment,"
					+ "(SELECT y.latest_revised_cost FROM work_yearly_sanction y WHERE y.work_id_fk = wr.work_id and y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk)) as latest_revised_cost " 
					+ "from work wr "
					+ "left join work_railway wy ON wr.work_id = wy.work_id_fk " + 
					" left join railway ON executed_by_id_fk = railway_id where wr.project_id_fk = ? and executed_by_id_fk <> '' group by work_id ORDER BY (CASE executed_by_id_fk WHEN 'MRVC' THEN 0 WHEN 'CR' THEN 1 WHEN 'WR' THEN 2 else 'Others' end),work_id;";
			*/
			
			String projectDetailsQry = "select sum(wr.sanctioned_estimated_cost) as sanctioned_estimated_cost,max(wr.sanctioned_year_fk) as sanctioned_year_fk,"
					+ "sum(wr.completion_cost) as completion_cost,max(wr.year_of_completion) as year_of_completion, "
					+ "(SELECT (CASE WHEN MONTH(max(wr.projected_completion)) >= 4 THEN concat(YEAR(max(wr.projected_completion)), '-',SUBSTR(YEAR(max(wr.projected_completion))+1,3,2)) ELSE concat(YEAR(max(wr.projected_completion))-1,'-', SUBSTR(YEAR(max(wr.projected_completion)),3,2)) END) AS financial_year) as projected_completion_year," 
					//+ "max(wr.projected_completion) as projected_completion_year,"
					+ "(SELECT sum(y.latest_revised_cost) FROM work_yearly_sanction y left join `work` w on w.work_id = y.work_id_fk  WHERE y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk) and w.project_id_fk = ? group by w.project_id_fk) as latest_revised_cost " 
					+ "from work wr where wr.project_id_fk = ? group by wr.project_id_fk";
			
			String workQry = "select wr.work_id,wr.work_short_name,wr.sanctioned_estimated_cost as sanctioned_estimated_cost,wr.sanctioned_year_fk as sanctioned_year_fk,"
					+ "wr.sanctioned_completion_cost as sanctioned_completion_cost,wr.year_of_completion as year_of_completion, " 
					+ "wr.completion_cost as completion_cost,"
					+ "(SELECT (CASE WHEN MONTH(wr.projected_completion) >= 4 THEN concat(YEAR(wr.projected_completion), '-',SUBSTR(YEAR(wr.projected_completion)+1,3,2)) ELSE concat(YEAR(wr.projected_completion)-1,'-', SUBSTR(YEAR(wr.projected_completion),3,2)) END) AS financial_year) as projected_completion_year," 
					//+ "wr.projected_completion as projected_completion_year,"
					+ " wr.attachment as work_attachment,"
					+ "(SELECT y.latest_revised_cost FROM work_yearly_sanction y WHERE y.work_id_fk = wr.work_id and y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk)) as latest_revised_cost " 
					+ "from work wr "
					+ "left join work_railway wy ON wr.work_id = wy.work_id_fk " + 
					" left join railway ON executed_by_id_fk = railway_id where wr.project_id_fk = ? and executed_by_id_fk <> '' group by work_id ORDER BY (CASE executed_by_id_fk WHEN 'MRVC' THEN 0 WHEN 'CR' THEN 1 WHEN 'WR' THEN 2 else 'Others' end),work_id;";
			
			
			String projectGalleryQry = "select id,file_name,project_id_fk,created_date,created_by from project_gallery where project_id_fk = ? ";
			
			String projectDocumentsQry = "select id, project_id_fk, attachment, project_file_type_fk,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date from project_files where project_id_fk = ? ";
			
			String workDocumentsQry = "select id, work_id_fk, attachment, work_file_type_fk,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date from work_files where work_id_fk = ? ";

			objsList = jdbcTemplate.query( projectQry, new BeanPropertyRowMapper<Project>(Project.class));
			
			
			
			for (Project project : objsList) {
				List<Project> projectInfoList = jdbcTemplate.query( projectDetailsQry, new Object[] {project.getProject_id(),project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
				for (Project projectInfo : projectInfoList) {
					if(!StringUtils.isEmpty(projectInfo)) {
						String sanctioned_estimated_cost = projectInfo.getSanctioned_estimated_cost();
						if(!StringUtils.isEmpty(sanctioned_estimated_cost)) {
							sanctioned_estimated_cost = numberFormatter.format(Double.parseDouble(sanctioned_estimated_cost));
						}
						project.setSanctioned_estimated_cost(sanctioned_estimated_cost);
						project.setSanctioned_year_fk(projectInfo.getSanctioned_year_fk());
						
						String completion_cost = projectInfo.getCompletion_cost();
						if(!StringUtils.isEmpty(completion_cost)) {
							completion_cost = numberFormatter.format(Double.parseDouble(completion_cost));
						}
						
						project.setCompletion_cost(completion_cost);
						project.setYear_of_completion(projectInfo.getYear_of_completion());
						
						String latest_revised_cost = projectInfo.getLatest_revised_cost();
						if(!StringUtils.isEmpty(latest_revised_cost)) {
							latest_revised_cost = numberFormatter.format(Double.parseDouble(latest_revised_cost));
						}
						
						project.setProjected_completion_year(projectInfo.getProjected_completion_year());
						project.setLatest_revised_cost(latest_revised_cost);
					}
				}
				List<Work> worksInfo = jdbcTemplate.query( workQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Work>(Work.class));
				

				for (Work work : worksInfo) {
					work.setRailwayAgency(getRailwayAgencyList(work.getWork_id()));
					work.setExecutedBy(getExecutedByList(work.getWork_id()));
				    workDocs = jdbcTemplate.query( workDocumentsQry, new Object[] {work.getWork_id()}, new BeanPropertyRowMapper<Work>(Work.class));
				    work.setWorkDocs(workDocs);

				}
				project.setWorksInfo(worksInfo);
				
				List<Project> projectGallery = jdbcTemplate.query( projectGalleryQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
				
				List<Project> projectDocs = jdbcTemplate.query( projectDocumentsQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));


				project.setProjectGallery(projectGallery);
				project.setProjectDocs(projectDocs);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	private String getExecutedByList(String work_id) throws Exception {
		List<Work> objsList = new ArrayList<Work>();
		String executedByList = "";
		try {
			String qry ="SELECT executed_by_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON executed_by_id_fk = railway_id "
					+ "where executed_by_id_fk is not null and executed_by_id_fk <> '' and work_id_fk = ? ORDER BY (CASE executed_by_id_fk WHEN 'MRVC' THEN 0 WHEN 'CR' THEN 1 WHEN 'WR' THEN 2 else 'Others' end)";
		
			objsList = jdbcTemplate.query( qry, new Object[] {work_id}, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work work : objsList) {
				executedByList = executedByList + ", " + work.getExecuted_by_id_fk();
			}
			if(!StringUtils.isEmpty(executedByList)) {
				executedByList = executedByList.startsWith(",") ? executedByList.substring(1) : executedByList;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return executedByList;
	}


	private String getRailwayAgencyList(String work_id) throws Exception {
		List<Work> objsList = new ArrayList<Work>();
		String railwayAgencyList = "";
		try {
			String qry ="SELECT railway_id_fk,railway_name "
					+ "from work_railway wr "
					+ "left join railway ON railway_id_fk = railway_id "
					+ "where railway_id_fk is not null and railway_id_fk <> '' and work_id_fk = ?";
		
			objsList = jdbcTemplate.query( qry, new Object[] {work_id}, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work work : objsList) {
				railwayAgencyList = railwayAgencyList + ", " + work.getRailway_id_fk();
			}
			
			if(!StringUtils.isEmpty(railwayAgencyList)) {
				railwayAgencyList = railwayAgencyList.startsWith(",") ? railwayAgencyList.substring(1) : railwayAgencyList;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return railwayAgencyList;
		
	}
	

	@Override
	public List<Work> getWorkDetails(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="select work_id_fk as work_id from dashboard where soft_delete_status_fk = ?";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Work> getDashBoardNames(Work work) throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="select dashboard_id, dashboard_name ,parent_dashboard_id_sr_fk from dashboard where work_id_fk = ?";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = work.getWork_id();
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Work> getSubLink(Work obj) throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="select dashboard_name as subLink  from dashboard where dashboard_id = ?";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getParent_dashboard_id_sr_fk();
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Admin> getAdminList(Admin admin) throws Exception {
		List<Admin> objsList = null;
		try {
			String qry ="select admin_form_id, form_name, url, priority, soft_delete_status_fk from admin_form where soft_delete_status_fk = ? order by priority ASC ";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Admin>(Admin.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addUserLastActiveDateTime(User uObj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {			
			con = dataSource.getConnection();
			String insertQry = "UPDATE user_login_details SET last_active_date_time = CURRENT_TIMESTAMP"  
					+ " WHERE user_id_fk = ? and user_login_id = ?";
			stmt = con.prepareStatement(insertQry);
			int p = 1;
			stmt.setString(p++,uObj.getUser_id());
			stmt.setString(p++,uObj.getUser_login_details_id());
			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag = true;				
			}
			
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}

	@Override
	public boolean userLoginTimeout() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {			
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			List<User> user_ids = new ArrayList<User>();
			String qry = "SELECT user_id_fk FROM user_login_details WHERE logout_date_time is null and last_active_date_time < (NOW() - INTERVAL 30 MINUTE)";
			stmt = con.prepareStatement(qry);
			rs = stmt.executeQuery();
			while(rs.next()) {
				User obj = new User();
				obj.setUser_id(rs.getString("user_id_fk"));
				user_ids.add(obj);
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			String updateQry = "UPDATE user set single_login_session_id = ? WHERE user_id = ?";
			stmt = con.prepareStatement(updateQry);
			for (User user : user_ids) {
				stmt.setString(1, null);
				stmt.setString(2, user.getUser_id());
				stmt.addBatch();
			}
				
			int[] count = stmt.executeBatch(); 	
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			String insertQry = "UPDATE user_login_details SET logout_date_time = CURRENT_TIMESTAMP,logout_type_fk = ? "
					+ "WHERE logout_date_time is null and last_active_date_time < (NOW() - INTERVAL 30 MINUTE)";
			stmt = con.prepareStatement(insertQry);
			int p = 1;
			stmt.setString(p++,CommonConstants2.LOGOUT_TYPE_TIMEOUT);			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag = true;	
			}
			
			con.commit();
		}catch(SQLException e){ 
			con.rollback();
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}

	@Override
	public List<Messages> getMessages(Messages mObj) throws Exception {
		List<Messages> objsList = null;
		try {
			String qry ="select * from (select message_id,message,user_id_fk,redirect_url,DATE_FORMAT(created_date,'%d-%m-%Y %h:%i %p') as created_date,created_date as created_date_24hr_format, "
					+ "read_time,message_type "
					+ "from messages where user_id_fk = ? "
					+ " and ((read_time is null and created_date> (NOW() - INTERVAL 3 DAY)) or (read_time is not null and read_time > (NOW() - INTERVAL 1 DAY))) and message_type not in ('Risk') union all "
				    + " select distinct (SELECT MAX(message_id) FROM messages m where m.redirect_url=redirect_url and left(m.created_date,10)=left(m2.created_date,10) "
				    + "and m.message_type=m2.message_type) as message_id,message,user_id_fk,redirect_url,left(DATE_FORMAT(created_date,'%d-%m-%Y %h:%i %p'),10) as created_date,"
					+ "(SELECT MAX(created_date) FROM messages m where m.redirect_url=redirect_url and left(m.created_date,10)=left(m2.created_date,10) "
					+ "and m.message_type=m2.message_type"
					+ ") as created_date_24hr_format, "
					+ "read_time,message_type "
					+ "from messages m2 where user_id_fk = ? "
					+ " and ((read_time is null and created_date> (NOW() - INTERVAL 3 DAY)) or (read_time is not null and read_time > (NOW() - INTERVAL 1 DAY))) and message_type in ('Risk')) as a ";
			
			int arrSize = 2;		
			if(!StringUtils.isEmpty(mObj.getMessage_type())) {
				qry = qry + "where message_type = ? ";
				arrSize++;
			}
			qry = qry + "order by created_date_24hr_format desc,DATE_FORMAT(created_date_24hr_format,'%H:%i:%s') DESC";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = mObj.getUser_id_fk();
			pValues[i++] = mObj.getUser_id_fk();
			if(!StringUtils.isEmpty(mObj.getMessage_type())) {
				pValues[i++] = mObj.getMessage_type();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Messages>(Messages.class));	
			if(objsList != null && !objsList.isEmpty()) {
				for (Messages obj : objsList) {
					if(!StringUtils.isEmpty(obj.getCreated_date_24hr_format())){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        Date date = format.parse(obj.getCreated_date_24hr_format());
				        Date currentDate = format.parse(format.format(new Date()));
				        long currTime = currentDate.getTime();
				        long userTime = date.getTime();
				        long time =  currTime - userTime ;
				        obj.setTimeAgo(TimeAgo.toDuration(time));
					}
				}
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Messages> getMessageTypes(Messages mObj) throws Exception {
		List<Messages> objsList = null;
		try {
			String qry ="select message_type "
					+ "from messages where user_id_fk = ? "
					+ "and (read_time is null or read_time > (NOW() - INTERVAL 3 DAY)) "
					+ "group by message_type order by message_type ASC";
			objsList = jdbcTemplate.query( qry,new Object[] {mObj.getUser_id_fk()}, new BeanPropertyRowMapper<Messages>(Messages.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Messages> changeMessagesReadStatus(Messages mObj) throws Exception {
		Connection con = null;
		try {			
			con = dataSource.getConnection();
			
			PreparedStatement stmt = null;

			for(int i=0;i<mObj.getMessage_ids().length;i++) 
			{
				String updateQry = "UPDATE messages SET read_time=CURRENT_TIMESTAMP where message_id = ?";
				stmt = con.prepareStatement(updateQry);
				stmt.setString(1, mObj.getMessage_ids()[i]);
				stmt.addBatch();
				int[] count = stmt.executeBatch(); 	
			}
				
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, null, null);
		}
		return null;
	}
	
}
