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

import com.synergizglobal.pmis.Idao.OverviewDashboardDaoNew;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.OverviewDashboardNew;

@Repository
public class OverviewDashboardDaoImplNew implements OverviewDashboardDaoNew {
	Logger logger = Logger.getLogger(OverviewDashboardDaoImplNew.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;


	@Override
	public List<OverviewDashboardNew> getLeftNavNodes(OverviewDashboardNew dObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboardNew> objsList = new ArrayList<OverviewDashboardNew>();
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT dashboard_id,dashboard_name,dashboard_icon,dashboard_url,parent_id,source_table_name,source_field_name,source_field_value "
					+ "FROM left_menu "
					+ "WHERE status = ? and parent_id = ? ORDER BY `order`";
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			statement.setString(2, dObj.getParent_id());
			resultSet = statement.executeQuery();  
			
			String work_id = dObj.getWork_id();
			while(resultSet.next()) {
				OverviewDashboardNew obj = new OverviewDashboardNew();
				String childParentId = resultSet.getString("dashboard_id");
				List<OverviewDashboardNew> subList = getDashboardsSubList(work_id,childParentId,connection);
				obj.setFormsSubMenu(subList);
				
				obj.setDashboard_id(resultSet.getString("dashboard_id"));
				obj.setDashboard_name(resultSet.getString("dashboard_name"));
				obj.setDashboard_icon(resultSet.getString("dashboard_icon"));
				obj.setDashboard_url(resultSet.getString("dashboard_url"));
				obj.setSource_table_name(resultSet.getString("source_table_name"));
				obj.setSource_field_name(resultSet.getString("source_field_name"));
				obj.setSource_field_value(resultSet.getString("source_field_value"));				
				if(!StringUtils.isEmpty(work_id)) {
					obj.setSource_field_value(work_id);
				}
				
				if(!StringUtils.isEmpty(obj.getSource_table_name()) && !StringUtils.isEmpty(obj.getSource_field_name()) && !StringUtils.isEmpty(obj.getSource_field_value())) {
					//obj.setWork_exists_or_not(getWorkExistsOrNot(obj,connection));
				}
				
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
	
	private int getWorkExistsOrNot(OverviewDashboardNew obj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String qry = "SELECT count(*) AS count FROM `"+obj.getSource_table_name()+"` WHERE `"+obj.getSource_field_name()+"` = '"+obj.getSource_field_value()+"'";
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				count = resultSet.getInt("count");
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return count;
	}

	private List<OverviewDashboardNew> getDashboardsSubList(String work_id, String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboardNew> objsList = new ArrayList<OverviewDashboardNew>();
		OverviewDashboardNew obj = null;
		try {
			String qry = "select dashboard_id,dashboard_name,dashboard_icon,dashboard_url,source_table_name,source_field_name,source_field_value "
					+ "FROM left_menu "
					+ "WHERE status = ? AND parent_id <> dashboard_id AND parent_id = ? ORDER BY `order`";
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			statement.setString(2, parentId);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new OverviewDashboardNew();
				obj.setDashboard_id(resultSet.getString("dashboard_id"));
				obj.setDashboard_name(resultSet.getString("dashboard_name"));
				obj.setDashboard_icon(resultSet.getString("dashboard_icon"));
				obj.setDashboard_url(resultSet.getString("dashboard_url"));
				obj.setSource_table_name(resultSet.getString("source_table_name"));
				obj.setSource_field_name(resultSet.getString("source_field_name"));
				obj.setSource_field_value(resultSet.getString("source_field_value"));
				if(!StringUtils.isEmpty(work_id)) {
					obj.setSource_field_value(work_id);
				}
				if(!StringUtils.isEmpty(obj.getSource_table_name()) && !StringUtils.isEmpty(obj.getSource_field_name()) && !StringUtils.isEmpty(obj.getSource_field_value())) {
					//obj.setWork_exists_or_not(getWorkExistsOrNot(obj,connection));
				}
				
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

	@Override
	public OverviewDashboardNew getTableauUrl(String dashboardId) throws Exception {
		OverviewDashboardNew dObj = null;;
		try {
			String qry = "SELECT dashboard_url,show_left_menu,source_table_name,source_field_name,source_field_value FROM left_menu WHERE dashboard_id = ?";
			List<OverviewDashboardNew> objsList = jdbcTemplate.query(qry, new Object[] { dashboardId },new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
			for (OverviewDashboardNew overviewDashboard : objsList) {
				dObj = overviewDashboard;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return dObj;
	}
	


	@Override
	public List<OverviewDashboardNew> getFilters(String dashboardId) throws Exception {
		List<OverviewDashboardNew> objList = new ArrayList<OverviewDashboardNew>();
		try {
			String qry = "SELECT filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_id, filter_column_name, default_filter_column, default_filter_value, selected_value "
					+ "FROM left_menu_filters WHERE left_menu_id_fk = ? "
					+ "ORDER BY priority ASC";
			objList = jdbcTemplate.query(qry, new Object[] { dashboardId },new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
			for (OverviewDashboardNew obj : objList) {		
				if(!StringUtils.isEmpty(obj.getFilter_column_name()) && !StringUtils.isEmpty(obj.getFilters_table())) {
					String filterQry = "SELECT "
							+ "`" + obj.getFilter_column_name() + "` as filter_option_value";
							if(!StringUtils.isEmpty(obj.getFilter_column_name())) {
								filterQry = filterQry + ",`" + obj.getFilter_column_name() + "` as filter_option_id ";
							}
							filterQry = filterQry + " FROM "
							+ "`"+ obj.getFilters_table()+ "`";
							if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
								filterQry = filterQry + " WHERE "
								+ "`"+ obj.getDefault_filter_column()+ "`"
								+ " = "
								+ obj.getDefault_filter_value();
							}
							filterQry = filterQry + " GROUP BY "
							+ "`"+ obj.getFilter_column_name()+ "`";
					List<OverviewDashboardNew> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
					obj.setFilter(filter);
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return objList;
	}	
	
}
