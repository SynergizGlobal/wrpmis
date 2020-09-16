package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.TableauDashboard;
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
	public List<TableauDashboard> getDashboardsList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> dashboardsList = new ArrayList<TableauDashboard>();
		TableauDashboard tableau = null;
		List<TableauDashboard> tableauSubList = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,tum.priority,icon_path "
					+ "FROM dashboard tum "
					+ "WHERE parent_dashboard_id_sr_fk = tum.dashboard_id and tum.soft_delete_status_fk = ? order by priority";
			
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
				
				dashboardsList.add(tableau);
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
	 * @param connection is object for the Connection Interface
	 * @return type of this method is  dashboardsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private List<TableauDashboard> getTableauSubList(String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TableauDashboard> dashboardsList = new ArrayList<TableauDashboard>();
		TableauDashboard tableauDashboard = null;
		
		try {
			String qry = "SELECT tum.dashboard_id,tum.dashboard_name,tum.priority,icon_path  "
					+ "FROM dashboard tum "
					+ "WHERE parent_dashboard_id_sr_fk <> tum.dashboard_id and parent_dashboard_id_sr_fk = ? and tum.soft_delete_status_fk = ? order by priority";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, parentId);
			statement.setString(2, CommonConstants.ACTIVE);
			
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
	public List<Forms> getFormsList(String base) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Forms> objsList = new ArrayList<Forms>();
		Forms obj = null;
		try {
			connection = dataSource.getConnection();
			//String qry = "SELECT id,form_name,web_form_url,mobile_form_url,priority,status_id FROM forms WHERE status_id = ? ";
			
			String qry = "SELECT form_id,module_name_fk,pmis_access_id_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,formcol,priority,soft_delete_status_fk "
					+ "FROM form f "
					+ "WHERE parent_form_id_sr_fk = f.form_id and f.soft_delete_status_fk = ? ";
			
			
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
				obj.setFormId(resultSet.getString("soft_delete_status_fk"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("web_form_url"));
				obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
				String parentId = resultSet.getString("parent_form_id_sr_fk");
				
				obj.setFormsSubMenu(getFormsSubList(base,parentId, connection));
				
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
			String qry = "SELECT form_id,module_name_fk,pmis_access_id_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,formcol,priority,soft_delete_status_fk "
					+ "FROM form f "
					+ "WHERE parent_form_id_sr_fk <> f.form_id and parent_form_id_sr_fk = ? and f.soft_delete_status_fk = ? ";
			
			
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
				obj.setFormId(resultSet.getString("soft_delete_status_fk"));
				obj.setFormName(resultSet.getString("form_name"));
				obj.setWebFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("web_form_url"));
				obj.setMobileFormUrl(CommonConstants.CONTEXT_PATH+"/"+resultSet.getString("mobile_form_url"));
				obj.setPriority(resultSet.getString("priority"));
				obj.setStatusId(resultSet.getString("soft_delete_status_fk"));
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
			String qry = "select project_id,project_name,plan_head_number,pink_book_item_number,project_description,remarks from `project`";
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
			String qry = "select work_id,work_name,project_id_fk,sanctioned_year,sanctioned_estimated_cost,completeion_period_months,"
					+ "sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,weight,w.remarks,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " where project_id_fk = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				placeholders[i++] = obj.getProject_id_fk();
			}		
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
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
	
}
