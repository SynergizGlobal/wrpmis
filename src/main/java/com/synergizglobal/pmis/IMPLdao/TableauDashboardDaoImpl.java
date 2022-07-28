package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.TableauDashboardDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.TableauDashboard;

@Repository
public class TableauDashboardDaoImpl implements TableauDashboardDao {
	Logger logger = Logger.getLogger(TableauDashboardDao.class);
	@Autowired
	DataSource dataSource;
	
	/**
	 * This method get the TableauUrl
	 * @param dashboardName is string type variable that holds the dashboardName
	 * @return type of this method is tableauUrl
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public TableauDashboard getTableauUrl(String dashboardName) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TableauDashboard tableauUrl = null;
		try {
			
			connection = dataSource.getConnection();
			String qry = "SELECT dashboard_url,work_id_fk FROM dashboard WHERE dashboard_url is not null and dashboard_url <> '' and LOWER(dashboard_name) = ?";
			statement = connection.prepareStatement(qry); 
			statement.setString(1, dashboardName);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				tableauUrl = new TableauDashboard();
				tableauUrl.setTableauUrl(resultSet.getString("dashboard_url"));
				tableauUrl.setWork_id_fk(resultSet.getString("work_id_fk"));
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return tableauUrl;
	}
	
	/**
	 * This method get the tableau dashboard
	 * @return type of this method is dashboard
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public TableauDashboard getTableauDashBoard() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TableauDashboard dashboard = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT dashboard_id,dashboard_name "
					+ "FROM dashboard "
					+ "where parent_id_sr_fk = dashboard_id and soft_delete_status_id_fk = ? order by priority ASC offset 0 rows  fetch next 1 rows only";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				dashboard = new TableauDashboard();
				dashboard.setTableauDashboardName(resultSet.getString("dashboard_name"));
			}				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return dashboard;
	}

	/**
	 * getting Tableau dashboard Url For Mobile
	 * @param getTableauUrlForMobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		TableauDashboard obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT dashboard_url FROM dashboard WHERE dashboard_id = ?";
			statement = connection.prepareStatement(qry); 
			statement.setString(1, infovizId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new TableauDashboard();
				obj.setTableauUrl(resultSet.getString("dashboard_url"));
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return obj;
	}
}
