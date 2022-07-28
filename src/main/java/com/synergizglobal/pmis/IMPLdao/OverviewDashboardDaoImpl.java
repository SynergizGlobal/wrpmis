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

import com.synergizglobal.pmis.Idao.OverviewDashboardDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.OverviewDashboard;
import com.synergizglobal.pmis.model.User;

@Repository
public class OverviewDashboardDaoImpl implements OverviewDashboardDao {
	Logger logger = Logger.getLogger(OverviewDashboardDaoImpl.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;


	@Override
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard dObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboard> objsList = new ArrayList<OverviewDashboard>();
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT dashboard_id,dashboard_name,dashboard_icon,dashboard_url,parent_id,source_table_name,"
					+ "source_field_name,source_field_value,show_left_menu,"
					+ "(SELECT (CASE WHEN COUNT(*)>0 THEN 'true' ELSE 'false' END) FROM left_menu_access lma WHERE lma.dashboard_id = lm.dashboard_id AND (access_value = ? or access_value = ? or access_value = ?)) AS accessibility "
					+ "FROM left_menu lm "
					+ "WHERE lm.status = ? and lm.parent_id = ? AND lm.show_left_menu = ? AND lm.dashboard_type_fk = ?";
			if(!StringUtils.isEmpty(dObj.getDashboard_id()) && "Modules".equals(dObj.getDashboard_type())) {
				qry = qry + " AND lm.dashboard_id = ?";
			}
			qry = qry + " ORDER BY [order]";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, dObj.getUser_type_fk());
			statement.setString(p++, dObj.getUser_role_name_fk());
			statement.setString(p++, dObj.getUser_id());
			statement.setString(p++, CommonConstants.ACTIVE);
			statement.setString(p++, dObj.getParent_id());
			statement.setString(p++, "Yes");
			statement.setString(p++, dObj.getDashboard_type());
			if(!StringUtils.isEmpty(dObj.getDashboard_id()) && "Modules".equals(dObj.getDashboard_type())){
				statement.setString(p++, dObj.getDashboard_id());
			}
			resultSet = statement.executeQuery();  
			
			String work_id = dObj.getWork_id();
			while(resultSet.next()) {
				OverviewDashboard obj = new OverviewDashboard();
				String childParentId = resultSet.getString("dashboard_id");
				List<OverviewDashboard> subList = getDashboardsSubList(dObj,work_id,childParentId,connection);
				obj.setFormsSubMenu(subList);
				
				obj.setDashboard_id(resultSet.getString("dashboard_id"));
				obj.setDashboard_name(resultSet.getString("dashboard_name"));
				obj.setDashboard_icon(resultSet.getString("dashboard_icon"));
				obj.setDashboard_url(resultSet.getString("dashboard_url"));
				obj.setSource_table_name(resultSet.getString("source_table_name"));
				obj.setSource_field_name(resultSet.getString("source_field_name"));
				obj.setSource_field_value(resultSet.getString("source_field_value"));	
				obj.setAccessibility(resultSet.getString("accessibility"));
				if(!StringUtils.isEmpty(work_id)) {
					obj.setSource_field_value(work_id);
				}
				
				if(!StringUtils.isEmpty(obj.getSource_table_name()) && !StringUtils.isEmpty(obj.getSource_field_name()) && !StringUtils.isEmpty(obj.getSource_field_value())) {
					//obj.setWork_exists_or_not(getWorkExistsOrNot(obj,connection));
				}
				boolean aFlag = false;
				for (OverviewDashboard overviewDashboard : subList) {
					if("true".equals(overviewDashboard.getAccessibility())) {
						aFlag = true;
					}
				}
				if("true".equals(obj.getAccessibility()) || aFlag) {
					objsList.add(obj);
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	
	private String getDashboardType(String dashboard_id,Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String dashboardtypefk = null;
		try {
			String qry = "select dashboard_type_fk from left_menu where dashboard_id = ? ";
	    	
			
			stmt = con.prepareStatement(qry);
			int k = 1;			
			
			stmt.setString(k++, dashboard_id);
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				dashboardtypefk = rs.getString("dashboard_type_fk");
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return dashboardtypefk;
	}	
	
	
	private int getWorkExistsOrNot(OverviewDashboard obj, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String qry = "SELECT count(*) AS count FROM '"+obj.getSource_table_name()+"' WHERE '"+obj.getSource_field_name()+"' = '"+obj.getSource_field_value()+"'";
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

	private List<OverviewDashboard> getDashboardsSubList(OverviewDashboard dObj, String work_id, String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboard> objsList = new ArrayList<OverviewDashboard>();
		OverviewDashboard obj = null;
		try {
			String qry = "select dashboard_id,dashboard_name,dashboard_icon,dashboard_url,source_table_name,"
					+ "source_field_name,source_field_value,show_left_menu, "
					+ "(SELECT (CASE WHEN COUNT(*)>0 THEN 'true' ELSE 'false' END) FROM left_menu_access lma WHERE lma.dashboard_id = lm.dashboard_id AND (access_value = ? or access_value = ? or access_value = ?)) AS accessibility "
					+ "FROM left_menu lm "
					+ "WHERE status = ? AND parent_id <> dashboard_id AND parent_id = ? AND show_left_menu = ? ORDER BY [order]";
			statement = connection.prepareStatement(qry);
			int p = 1;
			statement.setString(p++, dObj.getUser_type_fk());
			statement.setString(p++, dObj.getUser_role_name_fk());
			statement.setString(p++, dObj.getUser_id());
			statement.setString(p++, CommonConstants.ACTIVE);
			statement.setString(p++, parentId);
			statement.setString(p++, "Yes");
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new OverviewDashboard();
				
				String childParentId = resultSet.getString("dashboard_id");
				List<OverviewDashboard> subList = getDashboardsSubList(dObj,work_id,childParentId,connection);
				obj.setFormsSubMenu(subList);
				
				obj.setDashboard_id(resultSet.getString("dashboard_id"));
				obj.setDashboard_name(resultSet.getString("dashboard_name"));
				obj.setDashboard_icon(resultSet.getString("dashboard_icon"));
				obj.setDashboard_url(resultSet.getString("dashboard_url"));
				obj.setSource_table_name(resultSet.getString("source_table_name"));
				obj.setSource_field_name(resultSet.getString("source_field_name"));
				obj.setSource_field_value(resultSet.getString("source_field_value"));
				obj.setAccessibility(resultSet.getString("accessibility"));
				if(!StringUtils.isEmpty(work_id)) {
					obj.setSource_field_value(work_id);
				}
				if(!StringUtils.isEmpty(obj.getSource_table_name()) && !StringUtils.isEmpty(obj.getSource_field_name()) && !StringUtils.isEmpty(obj.getSource_field_value())) {
					//obj.setWork_exists_or_not(getWorkExistsOrNot(obj,connection));
				}
				boolean aFlag = false;
				for (OverviewDashboard overviewDashboard : subList) {
					if("true".equals(overviewDashboard.getAccessibility())) {
						aFlag = true;
					}
				}
				if("true".equals(obj.getAccessibility()) || aFlag) {
					objsList.add(obj);
				}		
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
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception {
		OverviewDashboard dObj = null;;
		try {
			String qry = "SELECT dashboard_url,show_left_menu,source_table_name,source_field_name,source_field_value FROM left_menu WHERE dashboard_id = ?";
			List<OverviewDashboard> objsList = jdbcTemplate.query(qry, new Object[] { dashboardId },new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
			for (OverviewDashboard overviewDashboard : objsList) {
				dObj = overviewDashboard;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return dObj;
	}
	


	@Override
	public List<OverviewDashboard> getFilters(OverviewDashboard dObj) throws Exception {
		List<OverviewDashboard> objList = new ArrayList<OverviewDashboard>();
		try {
			
			OverviewDashboard tempObj = getWorkColumnName(dObj.getDashboard_id());
			
			String qry = "SELECT filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_id, filter_column_name, "
					+ "default_filter_column, default_filter_value, selected_value,query_for_filter_options,filters_table_alias_name,order_by,"
					+ "is_first_option_selected,union_all "
					+ "FROM left_menu_filters WHERE left_menu_id_fk = ? AND status = ? "
					+ "ORDER BY priority ASC";
			objList = jdbcTemplate.query(qry, new Object[] { dObj.getDashboard_id(),CommonConstants.ACTIVE },new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
			for (OverviewDashboard obj : objList) {		
				if(!StringUtils.isEmpty(obj.getIs_first_option_selected()) && (obj.getIs_first_option_selected()).toUpperCase().equals("YES")){
					if(!StringUtils.isEmpty(obj.getQuery_for_filter_options())){
						String[] qryArr = obj.getQuery_for_filter_options().split("FROM");
						String firstPart = qryArr[0];
						String secondPart = qryArr[1];
						String filterQry = firstPart + ",";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getFilter_column_name() + " as filter_option_value";
						
						if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
							filterQry = filterQry + ",";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
							}
							filterQry = filterQry + obj.getFilter_column_id() + " as filter_option_id ";
						}else {
							filterQry = filterQry + ",";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
							}
							filterQry = filterQry + obj.getFilter_column_name() + " as filter_option_id ";
						}
						filterQry = filterQry + " FROM ";
						filterQry = filterQry + secondPart;
						if(!secondPart.contains("WHERE")) {
							filterQry = filterQry + " WHERE ";
									if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
										filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
									}
									filterQry = filterQry + obj.getFilter_column_id()
											+ " IS NOT NULL ";
						}							
						if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
							filterQry = filterQry + " AND ";
							if(!StringUtils.isEmpty(tempObj.getSource_table_alias_name())) {
								filterQry = filterQry + tempObj.getSource_table_alias_name()+ ".";
							}
							filterQry = filterQry + ""+ tempObj.getSource_field_name()+ ""
							+ " = "
							+ "'"+ dObj.getWork_id()+ "'";
						}
						if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
							filterQry = filterQry + " AND "
							+ ""+ obj.getDefault_filter_column()+ ""
							+ " = "
							+ "'"+ obj.getDefault_filter_value()+ "'";
						}
						if(!StringUtils.isEmpty(dObj.getParams())) {
							filterQry = filterQry + " AND "+dObj.getParams();
						}
						
						filterQry = filterQry + " GROUP BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getFilter_column_id();
						if(!StringUtils.isEmpty(obj.getOrder_by())) {
							filterQry = filterQry + " ORDER BY ";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
								filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
							}
							filterQry = filterQry + obj.getOrder_by();
						}
						
						/************************************************************************************/
						if(!StringUtils.isEmpty(obj.getUnion_all())){
							String[] unionQryArr = obj.getUnion_all().split("FROM");
							String unionFirstPart = unionQryArr[0];
							String unionSecondPart = unionQryArr[1];
							String unionFilterQry = unionFirstPart + ",";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getFilter_column_name() + " as filter_option_value";
							
							if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
								unionFilterQry = unionFilterQry + ",";
								if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
									unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
								}
								unionFilterQry = unionFilterQry + obj.getFilter_column_id() + " as filter_option_id ";
							}else {
								unionFilterQry = unionFilterQry + ",";
								if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
									unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
								}
								unionFilterQry = unionFilterQry + obj.getFilter_column_name() + " as filter_option_id ";
							}
							unionFilterQry = unionFilterQry + " FROM ";
							unionFilterQry = unionFilterQry + unionSecondPart;
							if(!unionSecondPart.contains("WHERE")) {
								unionFilterQry = unionFilterQry + " WHERE ";
										if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
											unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
										}
										unionFilterQry = unionFilterQry + obj.getFilter_column_id()
												+ " IS NOT NULL ";
							}							
							if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
								unionFilterQry = unionFilterQry + " AND ";
								if(!StringUtils.isEmpty(tempObj.getSource_table_alias_name())) {
									unionFilterQry = unionFilterQry + tempObj.getSource_table_alias_name()+ ".";
								}
								unionFilterQry = unionFilterQry + ""+ tempObj.getSource_field_name()+ ""
								+ " = "
								+ "'"+ dObj.getWork_id()+ "'";
							}
							if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
								unionFilterQry = unionFilterQry + " AND "
								+ ""+ obj.getDefault_filter_column()+ ""
								+ " = "
								+ "'"+ obj.getDefault_filter_value()+ "'";
							}
							if(!StringUtils.isEmpty(dObj.getParams())) {
								unionFilterQry = unionFilterQry + " AND "+dObj.getParams();
							}
							
							unionFilterQry = unionFilterQry + " GROUP BY ";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getFilter_column_id();
							if(!StringUtils.isEmpty(obj.getOrder_by())) {
								unionFilterQry = unionFilterQry + " ORDER BY ";
								if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
									unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
								}
								unionFilterQry = unionFilterQry + obj.getOrder_by();
							}
							
							filterQry = filterQry + " UNION ALL " +unionFilterQry;
						}
						
						
						/*************************************************************************************/
						List<OverviewDashboard> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
						obj.setFilter(filter);
					}else if(!StringUtils.isEmpty(obj.getFilter_column_name()) && !StringUtils.isEmpty(obj.getFilters_table())) {
						String filterQry = "SELECT "
						+ "" + obj.getFilter_column_name() + " as filter_option_value ";
						if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
							filterQry = filterQry + "," + obj.getFilter_column_id() + " as filter_option_id ";
						}
						filterQry = filterQry + " FROM "
						+ ""+ obj.getFilters_table()+ "";
						
						filterQry = filterQry + " WHERE "
								+ "'"+ obj.getFilter_column_id()+ "'"
								+ " IS NOT NULL ";
						if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
							filterQry = filterQry + " AND "
							+ ""+ tempObj.getSource_field_name()+ ""
							+ " = "
							+ "'"+ dObj.getWork_id()+ "'";
						}
						if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
							filterQry = filterQry + " AND "
							+ ""+ obj.getDefault_filter_column()+ ""
							+ " = "
							+ "'"+ obj.getDefault_filter_value()+ "'";
						}
						
						if(!StringUtils.isEmpty(dObj.getParams())) {
							filterQry = filterQry + " AND "+dObj.getParams();
						}
						
						
						//filterQry = filterQry + " GROUP BY " + ""+ obj.getFilter_column_id()+ "";
						if(!StringUtils.isEmpty(obj.getOrder_by())) {
							filterQry = filterQry + " ORDER BY ";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
								filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
							}
							filterQry = filterQry + obj.getOrder_by();
						}
						List<OverviewDashboard> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
						obj.setFilter(filter);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return objList;
	}	
	
	public OverviewDashboard getWorkColumnName(String dashboardId) throws Exception {
		OverviewDashboard dObj = null;;
		try {
			String qry = "SELECT source_field_name,source_table_alias_name FROM left_menu WHERE dashboard_id = ?";
			List<OverviewDashboard> objsList = jdbcTemplate.query(qry, new Object[] { dashboardId },new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
			for (OverviewDashboard overviewDashboard : objsList) {
				dObj = overviewDashboard;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return dObj;
	}

	@Override
	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception {
		List<OverviewDashboard> objList = new ArrayList<OverviewDashboard>();
		try {
			
			OverviewDashboard tempObj = getWorkColumnName(dObj.getDashboard_id());
			
			String qry = "SELECT filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_id, filter_column_name, "
					+ "default_filter_column, default_filter_value, selected_value,query_for_filter_options,filters_table_alias_name,order_by,"
					+ "is_first_option_selected,union_all "
					+ "FROM left_menu_filters WHERE filter_id = ? "
					+ "ORDER BY priority ASC";
			objList = jdbcTemplate.query(qry, new Object[] { dObj.getFilter_id()},new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
			for (OverviewDashboard obj : objList) {		
				if(!StringUtils.isEmpty(obj.getQuery_for_filter_options())){
					String[] qryArr = obj.getQuery_for_filter_options().split("FROM");
					String firstPart = qryArr[0];
					String secondPart = qryArr[1];
					String filterQry = firstPart + ",";
					if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
						filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
					}
					filterQry = filterQry + obj.getFilter_column_name() + " as filter_option_value";
					
					if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
						filterQry = filterQry + ",";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getFilter_column_id() + " as filter_option_id ";
					}else {
						filterQry = filterQry + ",";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getFilter_column_name() + " as filter_option_id ";
					}
					filterQry = filterQry + " FROM ";
					filterQry = filterQry + secondPart;
					if(!secondPart.contains("WHERE")) {
						filterQry = filterQry + " WHERE ";
								if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
									filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
								}
								filterQry = filterQry + obj.getFilter_column_id()
										+ " IS NOT NULL ";
					}							
					if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
						filterQry = filterQry + " AND ";
						if(!StringUtils.isEmpty(tempObj.getSource_table_alias_name())) {
							filterQry = filterQry + tempObj.getSource_table_alias_name()+ ".";
						}
						filterQry = filterQry + ""+ tempObj.getSource_field_name()+ ""
						+ " = "
						+ "'"+ dObj.getWork_id()+ "'";
					}
					if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
						filterQry = filterQry + " AND "
						+ ""+ obj.getDefault_filter_column()+ ""
						+ " = "
						+ "'"+ obj.getDefault_filter_value()+ "'";
					}
					if(!StringUtils.isEmpty(dObj.getParams())) {
						filterQry = filterQry + " AND "+dObj.getParams();
					}
					
					//filterQry = filterQry + " GROUP BY ";
					if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
						filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
					}
					//filterQry = filterQry + obj.getFilter_column_id();
					if(!StringUtils.isEmpty(obj.getOrder_by())) {
						filterQry = filterQry + " ORDER BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getOrder_by();
					}
					
					/************************************************************************************/
					if(!StringUtils.isEmpty(obj.getUnion_all())){
						String[] unionQryArr = obj.getUnion_all().split("FROM");
						String unionFirstPart = unionQryArr[0];
						String unionSecondPart = unionQryArr[1];
						String unionFilterQry = unionFirstPart + ",";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
						}
						unionFilterQry = unionFilterQry + obj.getFilter_column_name() + " as filter_option_value";
						
						if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
							unionFilterQry = unionFilterQry + ",";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getFilter_column_id() + " as filter_option_id ";
						}else {
							unionFilterQry = unionFilterQry + ",";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getFilter_column_name() + " as filter_option_id ";
						}
						unionFilterQry = unionFilterQry + " FROM ";
						unionFilterQry = unionFilterQry + unionSecondPart;
						if(!unionSecondPart.contains("WHERE")) {
							unionFilterQry = unionFilterQry + " WHERE ";
									if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
										unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
									}
									unionFilterQry = unionFilterQry + obj.getFilter_column_id()
											+ " IS NOT NULL ";
						}							
						if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
							unionFilterQry = unionFilterQry + " AND ";
							if(!StringUtils.isEmpty(tempObj.getSource_table_alias_name())) {
								unionFilterQry = unionFilterQry + tempObj.getSource_table_alias_name()+ ".";
							}
							unionFilterQry = unionFilterQry + ""+ tempObj.getSource_field_name()+ ""
							+ " = "
							+ "'"+ dObj.getWork_id()+ "'";
						}
						if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
							unionFilterQry = unionFilterQry + " AND "
							+ ""+ obj.getDefault_filter_column()+ ""
							+ " = "
							+ "'"+ obj.getDefault_filter_value()+ "'";
						}
						if(!StringUtils.isEmpty(dObj.getParams())) {
							unionFilterQry = unionFilterQry + " AND "+dObj.getParams();
						}
						
						unionFilterQry = unionFilterQry + " GROUP BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
						}
						unionFilterQry = unionFilterQry + obj.getFilter_column_id();
						if(!StringUtils.isEmpty(obj.getOrder_by())) {
							unionFilterQry = unionFilterQry + " ORDER BY ";
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getOrder_by();
						}
						
						filterQry = filterQry + " UNION ALL " +unionFilterQry;
					}
					
					
					/*************************************************************************************/
					List<OverviewDashboard> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
					obj.setFilter(filter);
				}else if(!StringUtils.isEmpty(obj.getFilter_column_name()) && !StringUtils.isEmpty(obj.getFilters_table())) {
					String filterQry = "SELECT "
					+ "'" + obj.getFilter_column_name() + "' as filter_option_value ";
					if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
						filterQry = filterQry + ",'" + obj.getFilter_column_id() + "' as filter_option_id ";
					}
					filterQry = filterQry + " FROM "
					+ "'"+ obj.getFilters_table()+ "'";
					
					filterQry = filterQry + " WHERE "
							+ "'"+ obj.getFilter_column_id()+ "'"
							+ " IS NOT NULL ";
					if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name()) && !StringUtils.isEmpty(dObj.getWork_id())) {
						filterQry = filterQry + " AND "
						+ "'"+ tempObj.getSource_field_name()+ "'"
						+ " = "
						+ "'"+ dObj.getWork_id()+ "'";
					}
					if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
						filterQry = filterQry + " AND "
						+ "'"+ obj.getDefault_filter_column()+ "'"
						+ " = "
						+ "'"+ obj.getDefault_filter_value()+ "'";
					}
					
					if(!StringUtils.isEmpty(dObj.getParams())) {
						filterQry = filterQry + " AND "+dObj.getParams();
					}
					
					
					filterQry = filterQry + " GROUP BY " + "'"+ obj.getFilter_column_id()+ "'";
					if(!StringUtils.isEmpty(obj.getOrder_by())) {
						filterQry = filterQry + " ORDER BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD") && !obj.getOrder_by().contains("CONCAT")) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getOrder_by();
					}
					List<OverviewDashboard> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
					obj.setFilter(filter);
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return objList;
	}

	@Override
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception {
		boolean flag=false;
		List<User> objsList = null;
		try {
			String qry="";

			if(dObj.getLevel().compareTo("3")==0)
			{
			    qry = "select  count(distinct dashboard_name) as count from left_menu l "
				+ "left join left_menu_access ls on ls.dashboard_id=l.dashboard_id "
				+ "where l.dashboard_id=? and (ls.access_value = ? or ls.access_value = ? or ls.access_value = ?) having count(distinct dashboard_name) >0 ";
				
	
				int arrSize = 4;
				Object[] pValues = new Object[arrSize];				
				int i = 0;
	
				pValues[i++] = dObj.getDashboard_id();
	
				pValues[i++] = dObj.getUser_type_fk();
				pValues[i++] = dObj.getUser_role_name_fk();
				pValues[i++] = dObj.getUser_id();
				objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<User>(User.class));
			}
			
			if(dObj.getLevel().compareTo("2")==0)
			{
				qry = "select  count(distinct dashboard_name) as count from left_menu l "
						+ "left join left_menu_access ls on ls.dashboard_id=l.dashboard_id "
						+ "left join left_menu_access ls1 on ls1.dashboard_id=l.parent_id "
						+ "where (l.dashboard_id=? or l.parent_id=?) and ((ls.access_value = ? or ls.access_value = ? or ls.access_value = ?) or (ls1.access_value = ? or ls1.access_value = ? or ls1.access_value = ?) ) having count(distinct dashboard_name) >0 ";
					
				int arrSize = 8;
				Object[] pValues = new Object[arrSize];				
				int i = 0;
				
				pValues[i++] = dObj.getDashboard_id();
				pValues[i++] = dObj.getDashboard_id();

				pValues[i++] = dObj.getUser_type_fk();
				pValues[i++] = dObj.getUser_role_name_fk();
				pValues[i++] = dObj.getUser_id();
				pValues[i++] = dObj.getUser_type_fk();
				pValues[i++] = dObj.getUser_role_name_fk();
				pValues[i++] = dObj.getUser_id();
				objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<User>(User.class));

			}			
	
			if(objsList.size()>0)
			{
				flag=true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public List<OverviewDashboard> getArchiveDates(OverviewDashboard dObj) throws Exception {
		List<OverviewDashboard> objsList;
		try {
			String qry = "SELECT FORMAT(archive_date ,'%d/%m/%Y') as dashboard_name,archive_url as dashboard_url FROM left_menu_archive_details WHERE dashboard_id = ?";
			objsList = jdbcTemplate.query(qry, new Object[] { dObj.getDashboard_id() },new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));
			if(StringUtils.isEmpty(objsList))
			{
				String qry1 = "SELECT dashboard_url FROM dashboard WHERE dashboard_id = ?";
				String Dashboardurl = (String) jdbcTemplate.queryForObject(qry1, new Object[] { dObj.getDashboard_id() }, String.class);
				String DId="";
				if(!StringUtils.isEmpty(Dashboardurl)) {
					String []SplitStr=Dashboardurl.split("/");
					DId=SplitStr[1];
				}
				objsList = jdbcTemplate.query(qry, new Object[] { DId },new BeanPropertyRowMapper<OverviewDashboard>(OverviewDashboard.class));				
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return objsList;
	}	
}
