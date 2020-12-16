package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	public List<TableauDashboard> getDashboardsList(String dashboardType) throws Exception {
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
					+ "WHERE parent_dashboard_id_sr_fk = tum.dashboard_id and tum.soft_delete_status_fk = ? and dashboard_type_fk = ? order by priority";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			statement.setString(2, dashboardType);
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
			
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk "
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
				obj.setFormId(resultSet.getString("form_id"));
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
			String qry = "SELECT form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk "
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
				obj.setFormId(resultSet.getString("form_id"));
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
			String qry = "select project_id,project_name,plan_head_number,pink_book_item_number,remarks from `project`";
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
	public List<String> getGeneralStatusList() throws Exception {
		List<String> objsList = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "select general_status from general_status where general_status is not null and general_status <> ''";
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				objsList.add(resultSet.getString("general_status").trim());
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
		try {
			String projectQry = "select project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_description,project_status "
					+ "from `project`";
			
			String projectDetailsQry = "select sum(wr.sanctioned_estimated_cost) as sanctioned_estimated_cost,max(wr.sanctioned_year_fk) as sanctioned_year_fk,"
					+ "sum(wr.completion_cost) as completion_cost,max(wr.year_of_completion) as year_of_completion, " 
					+ "max(wr.projected_completion) as projected_completion_year,"
					+ "(SELECT sum(y.latest_revised_cost) FROM work_yearly_sanction y left join `work` w on w.work_id = y.work_id_fk  WHERE y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk) and w.project_id_fk = ? group by w.project_id_fk) as latest_revised_cost " 
					+ "from work wr where wr.project_id_fk = ? group by wr.project_id_fk";
			
			String workQry = "select wr.work_id,wr.work_short_name,wr.pink_book_item_number,wr.sanctioned_estimated_cost as sanctioned_estimated_cost,wr.sanctioned_year_fk as sanctioned_year_fk,"
					+ "wr.sanctioned_completion_cost as sanctioned_completion_cost,wr.year_of_completion as year_of_completion, " 
					+ "wr.completion_cost as completion_cost,wr.projected_completion as projected_completion_year, "
					+ "(SELECT y.latest_revised_cost FROM work_yearly_sanction y WHERE y.work_id_fk = wr.work_id and y.financial_year = (SELECT MAX(z.financial_year) FROM work_yearly_sanction z WHERE z.work_id_fk = y.work_id_fk)) as latest_revised_cost " 
					+ "from work wr where wr.project_id_fk = ? order by wr.work_short_name";
			
			objsList = jdbcTemplate.query( projectQry, new BeanPropertyRowMapper<Project>(Project.class));
			
			
			
			for (Project project : objsList) {
				Project projectInfo = jdbcTemplate.queryForObject( projectDetailsQry, new Object[] {project.getProject_id(),project.getProject_id()}, new BeanPropertyRowMapper<Project>(Project.class));
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
				
				List<Work> worksInfo = jdbcTemplate.query( workQry, new Object[] {project.getProject_id()}, new BeanPropertyRowMapper<Work>(Work.class));
				
				for (Work work : worksInfo) {
					work.setRailwayAgency(getRailwayAgencyList(work.getWork_id()));
					work.setExecutedBy(getExecutedByList(work.getWork_id()));
				}
				
				project.setWorksInfo(worksInfo);
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
					+ "where executed_by_id_fk is not null and executed_by_id_fk <> '' and work_id_fk = ?";
		
			objsList = jdbcTemplate.query( qry, new Object[] {work_id}, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work work : objsList) {
				executedByList = executedByList + ", " + work.getRailway_name();
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
				railwayAgencyList = railwayAgencyList + ", " + work.getRailway_name();
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
	
}
