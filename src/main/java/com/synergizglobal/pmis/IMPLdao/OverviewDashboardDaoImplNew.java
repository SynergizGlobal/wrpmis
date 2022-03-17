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
	public List<OverviewDashboardNew> getFilters(OverviewDashboardNew dObj) throws Exception {
		List<OverviewDashboardNew> objList = new ArrayList<OverviewDashboardNew>();
		try {
			
			OverviewDashboardNew tempObj = getWorkColumnName(dObj.getDashboard_id());
			
			String qry = "SELECT filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_id, filter_column_name, "
					+ "default_filter_column, default_filter_value, selected_value,query_for_filter_options,filters_table_alias_name,order_by,is_first_option_selected "
					+ "FROM left_menu_filters WHERE left_menu_id_fk = ? AND status = ? "
					+ "ORDER BY priority ASC";
			objList = jdbcTemplate.query(qry, new Object[] { dObj.getDashboard_id(),CommonConstants.ACTIVE },new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
			/*for (OverviewDashboardNew obj : objList) {		
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
					if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name())) {
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
					
					filterQry = filterQry + " GROUP BY ";
					if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
						filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
					}
					filterQry = filterQry + obj.getFilter_column_id();
					if(!StringUtils.isEmpty(obj.getOrder_by())) {
						filterQry = filterQry + " ORDER BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getOrder_by();
					}
					
					List<OverviewDashboardNew> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
					obj.setFilter(filter);
				}else if(!StringUtils.isEmpty(obj.getFilter_column_name()) && !StringUtils.isEmpty(obj.getFilters_table())) {
					String filterQry = "SELECT "
							+ "`" + obj.getFilter_column_name() + "` as filter_option_value";
							if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
								filterQry = filterQry + ",`" + obj.getFilter_column_id() + "` as filter_option_id ";
							}else {
								filterQry = filterQry + ",`" + obj.getFilter_column_name() + "` as filter_option_id ";
							}
							filterQry = filterQry + " FROM "
							+ "`"+ obj.getFilters_table()+ "`";
							
							filterQry = filterQry + " WHERE "
									+ "`"+ obj.getFilter_column_id()+ "`"
									+ " IS NOT NULL ";
							if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name())) {
								filterQry = filterQry + " AND "
								+ "`"+ tempObj.getSource_field_name()+ "`"
								+ " = "
								+ "'"+ dObj.getWork_id()+ "'";
							}
							if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
								filterQry = filterQry + " AND "
								+ "`"+ obj.getDefault_filter_column()+ "`"
								+ " = "
								+ "'"+ obj.getDefault_filter_value()+ "'";
							}
							
							
							filterQry = filterQry + " GROUP BY " + "`"+ obj.getFilter_column_id()+ "`";
							if(!StringUtils.isEmpty(obj.getOrder_by())) {
								filterQry = filterQry + " ORDER BY ";
								if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
									filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
								}
								filterQry = filterQry + obj.getOrder_by();
							}
					List<OverviewDashboardNew> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
					obj.setFilter(filter);
				} 
			}*/
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return objList;
	}	
	
	public OverviewDashboardNew getWorkColumnName(String dashboardId) throws Exception {
		OverviewDashboardNew dObj = null;;
		try {
			String qry = "SELECT source_field_name,source_table_alias_name FROM left_menu WHERE dashboard_id = ?";
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
	public List<OverviewDashboardNew> getFilteredOptions(OverviewDashboardNew dObj) throws Exception {
		List<OverviewDashboardNew> objList = new ArrayList<OverviewDashboardNew>();
		try {
			
			OverviewDashboardNew tempObj = getWorkColumnName(dObj.getDashboard_id());
			
			String qry = "SELECT filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_id, filter_column_name, "
					+ "default_filter_column, default_filter_value, selected_value,query_for_filter_options,filters_table_alias_name,order_by,is_first_option_selected,union_all "
					+ "FROM left_menu_filters WHERE filter_id = ? "
					+ "ORDER BY priority ASC";
			objList = jdbcTemplate.query(qry, new Object[] { dObj.getFilter_id()},new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
			for (OverviewDashboardNew obj : objList) {		
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
					if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name())) {
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
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD")) {
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
						if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name())) {
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
							if(!StringUtils.isEmpty(obj.getFilters_table_alias_name()) && !obj.getOrder_by().contains("FIELD")) {
								unionFilterQry = unionFilterQry + obj.getFilters_table_alias_name() + ".";
							}
							unionFilterQry = unionFilterQry + obj.getOrder_by();
						}
						
						filterQry = filterQry + " UNION ALL " +unionFilterQry;
					}
					
					
					/*************************************************************************************/
					
					List<OverviewDashboardNew> filter = jdbcTemplate.query(filterQry,new BeanPropertyRowMapper<OverviewDashboardNew>(OverviewDashboardNew.class));
					obj.setFilter(filter);
				}else if(!StringUtils.isEmpty(obj.getFilter_column_name()) && !StringUtils.isEmpty(obj.getFilters_table())) {
					String filterQry = "SELECT "
					+ "`" + obj.getFilter_column_name() + "` as filter_option_value ";
					if(!StringUtils.isEmpty(obj.getFilter_column_id())) {
						filterQry = filterQry + ",`" + obj.getFilter_column_id() + "` as filter_option_id ";
					}
					filterQry = filterQry + " FROM "
					+ "`"+ obj.getFilters_table()+ "`";
					
					filterQry = filterQry + " WHERE "
							+ "`"+ obj.getFilter_column_id()+ "`"
							+ " IS NOT NULL ";
					if(!StringUtils.isEmpty(tempObj) && !StringUtils.isEmpty(tempObj.getSource_field_name())) {
						filterQry = filterQry + " AND "
						+ "`"+ tempObj.getSource_field_name()+ "`"
						+ " = "
						+ "'"+ dObj.getWork_id()+ "'";
					}
					if(!StringUtils.isEmpty(obj.getDefault_filter_column()) && !StringUtils.isEmpty(obj.getDefault_filter_value())) {
						filterQry = filterQry + " AND "
						+ "`"+ obj.getDefault_filter_column()+ "`"
						+ " = "
						+ "'"+ obj.getDefault_filter_value()+ "'";
					}
					
					if(!StringUtils.isEmpty(dObj.getParams())) {
						filterQry = filterQry + " AND "+dObj.getParams();
					}
					
					
					filterQry = filterQry + " GROUP BY " + "`"+ obj.getFilter_column_id()+ "`";
					if(!StringUtils.isEmpty(obj.getOrder_by())) {
						filterQry = filterQry + " ORDER BY ";
						if(!StringUtils.isEmpty(obj.getFilters_table_alias_name())) {
							filterQry = filterQry + obj.getFilters_table_alias_name() + ".";
						}
						filterQry = filterQry + obj.getOrder_by();
					}
							
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
