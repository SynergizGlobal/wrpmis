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

import com.synergizglobal.pmis.Idao.OverviewDashboardDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.PermissionURLs;
import com.synergizglobal.pmis.common.TimeAgo;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.OverviewDashboard;

@Repository
public class OverviewDashboardDaoImpl implements OverviewDashboardDao {
	Logger logger = Logger.getLogger(OverviewDashboardDao.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;


	@Override
	public List<OverviewDashboard> getFormsList(int ParentId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboard> objsList = new ArrayList<OverviewDashboard>();
		OverviewDashboard obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select id,name,icon,link_url,parent_id from leftmenu where status='Active' and parent_id=? order by `order`";
			statement = connection.prepareStatement(qry);
			statement.setInt(1, ParentId);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new OverviewDashboard();
				int childParentId = resultSet.getInt("id");
				List<OverviewDashboard> subList=getFormsList(childParentId);
				obj.setFormsSubMenu(subList);
				
				obj.setId(resultSet.getString("id"));
				obj.setName(resultSet.getString("name"));
				obj.setIcon(resultSet.getString("icon"));
				obj.setLink_url(resultSet.getString("link_url"));
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
	
	private List<OverviewDashboard> getFormsSubList(String parentId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<OverviewDashboard> objsList = new ArrayList<OverviewDashboard>();
		OverviewDashboard obj = null;
		try {
			String qry = "select id,name,icon,link_url from leftmenu where status='Active' and parent_id<>id and parent_id=? order by `order`";
			statement = connection.prepareStatement(qry);
			statement.setString(1, parentId);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new OverviewDashboard();
				obj.setId(resultSet.getString("id"));
				obj.setName(resultSet.getString("name"));
				obj.setIcon(resultSet.getString("icon"));
				obj.setLink_url(resultSet.getString("link_url"));
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
	public String getTableauUrl(String name) throws Exception {
		String link_url="";
		try {
			String qry = "SELECT link_url FROM pmis.leftmenu WHERE name= ?";
			link_url = (String) jdbcTemplate.queryForObject(qry, new Object[] { name }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return link_url;
	}

	@Override
	public boolean saveLeftNavData(OverviewDashboard obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateParentStmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();		
		    if( obj.getIds().length > 0)
		    {
				for (int i = 0; i < obj.getIds().length; i++) 
				{	
					if(obj.getIds()[i]!=null)
					{
						String updateQry = "UPDATE leftmenu set parent_id = ?,`order`=? where id=? ";
						updateStmt = con.prepareStatement(updateQry);
						updateStmt.setString(1,(obj.getParentids()[i]));
						updateStmt.setString(2,(obj.getOrderids()[i]));
						updateStmt.setString(3,(obj.getIds()[i]));
						updateStmt.executeUpdate();
						flag=true;
					}
				
				}
				
				for (int i = 0; i < obj.getParentidsorder().length; i++) 
				{	
					if(obj.getParentidsorder()[i]!=null)
					{
						int OrderVal=i+1;
						String updateParentQry = "UPDATE leftmenu set `order`=? where id=? ";
						updateParentStmt = con.prepareStatement(updateParentQry);
						updateParentStmt.setInt(1,OrderVal);
						updateParentStmt.setString(2,(obj.getParentidsorder()[i]));
						updateParentStmt.executeUpdate();
						flag=true;
					}
				
				}
				
		    }
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}	
		return flag;
	}	
	
}
