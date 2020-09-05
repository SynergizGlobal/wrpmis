package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class StripChartDaoImpl implements StripChartDao {
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<StripChart> getProjectsList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select project_id,project_description from `project`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new StripChart();
				obj.setProjectId(resultSet.getString("project_id"));
				obj.setProjectName(resultSet.getString("project_description"));
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

	@Override
	public List<StripChart> getWorksList(StripChart stripObj) throws Exception {
		
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			List<StripChart> objsList = new ArrayList<StripChart>();
			StripChart obj = null;
			try {
				connection = dataSource.getConnection();
				String qry = "select work_id,work_name from `work` ";
				
				if(!StringUtils.isEmpty(stripObj) && !StringUtils.isEmpty(stripObj.getProjectId())) {
					qry = qry + "where project_id_fk = ?";
				}
				
				statement = connection.prepareStatement(qry);
				
				if(!StringUtils.isEmpty(stripObj) && !StringUtils.isEmpty(stripObj.getProjectId())) {
					statement.setString(1,stripObj.getProjectId());
				}
				
				resultSet = statement.executeQuery();  
				while(resultSet.next()) {
					obj = new StripChart();
					obj.setWorkId(resultSet.getString("work_id"));
					obj.setWorkName(resultSet.getString("work_name"));
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
}
