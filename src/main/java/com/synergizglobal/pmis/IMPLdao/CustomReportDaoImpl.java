package com.synergizglobal.pmis.IMPLdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.synergizglobal.pmis.Idao.CustomReportDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.CustomReportColumns;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class CustomReportDaoImpl implements CustomReportDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<Contract> getAllContractList(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select w.work_name,w.work_short_name, GROUP_CONCAT(DISTINCT dt.department_name SEPARATOR ', ') as department_name,hoddt.department_name as hod_department,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head  " + 
					",scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
					+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
					+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%m-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required " + 
					"from contract c " + 
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join department hoddt on u.department_fk = hoddt.department "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "+
					"left join contract_executive ce on c.contract_id = ce.contract_id_fk "
					+"left join department dt on ce.department_id_fk = dt.department "
					+"where contract_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDesignation())) {
				qry = qry + " and c.hod_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDy_hod_designation())) {
				qry = qry + " and c.dy_hod_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status())) {
				qry = qry + " and c.status = ?";
				arrSize++;
			}

			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or "
						+ "contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
				arrSize++;
			}
			
			qry = qry + "GROUP BY contract_id ORDER BY contract_id ASC ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDesignation())) {
				pValues[i++] = obj.getDesignation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDy_hod_designation())) {
				pValues[i++] = obj.getDy_hod_designation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();	
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<CustomReportColumns> getModuleGroups(CustomReportColumns obj) throws Exception {
		PreparedStatement stmt = null;
		CustomReportColumns sobj = null;
		ResultSet rs=null;
		Connection con = null;
	    List<CustomReportColumns> objsList = new ArrayList<CustomReportColumns>();
	    try{  

		
		con = dataSource.getConnection();
		String qry ="select * from custom_reports_module_child_tables where module_name_fk=?";
		stmt = con.prepareStatement(qry);
		stmt.setString(1,obj.getModule_name_fk());
		
		 rs = stmt.executeQuery();

	      while(rs.next())
	      {
				sobj = new CustomReportColumns();
				sobj.setName(rs.getString(3));
				sobj.setTable_name(rs.getString(4));
				objsList.add(sobj);

	      }
	    }catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}	      
        
		return objsList;
	}
	
	@Override
	public List<CustomReportColumns> getModuleGroupColumns(CustomReportColumns obj) throws Exception {
 		PreparedStatement stmt = null;
		CustomReportColumns sobj = null;
	    List<CustomReportColumns> objsList = new ArrayList<CustomReportColumns>();
		Connection con = null;
		ResultSet rs=null;
		
	    	try
	    	{ 
				con = dataSource.getConnection();
				String qry ="select * from "+obj.getTable_name();
				stmt = con.prepareStatement(qry);
				
				rs = stmt.executeQuery();
		
				  java.sql.ResultSetMetaData rsMetaData = rs.getMetaData();
			      int count = rsMetaData.getColumnCount();
			      for(int i = 1; i<=count; i++) 
			      {
						sobj = new CustomReportColumns();
						String checkQry ="select count(*) from custom_report_required_field where table_fk='"+obj.getTable_name()+"' and filed_name in('"+rsMetaData.getColumnName(i)+"')";
						int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
						String columnValue=rsMetaData.getColumnName(i);
						
						if(fieldCnt>0)
						{
							columnValue=columnValue+" <span class='required'>*</span>";
						}
		                String columnName = columnValue.replaceAll("_", " ");
						sobj.setColumn_name(WordUtils.capitalizeFully(columnName));
						objsList.add(sobj);
		
			      }
		    }catch(Exception e){ 
				throw new SQLException(e);
			}finally {
				DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			}	        
		return objsList;
	}	
	
	private String getTableName(String name,String module) throws Exception
	{
		String table_name="";
		try {
			String qry = "SELECT table_name FROM pmis.custom_reports_module_child_tables WHERE name= ? and module_name_fk=?";
			table_name = (String) jdbcTemplate.queryForObject(qry, new Object[] { name,module }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return table_name;
	}	
	
	private String getMasterTable(String module) throws Exception
	{
		String table_name="";
		try {
			String qry = "SELECT table_name FROM pmis.custom_reports_module_child_tables WHERE master_table='Yes' and module_name_fk= ?";
			table_name = (String) jdbcTemplate.queryForObject(qry, new Object[] { module }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return table_name;
	}	

	private String checkMasterTable(String name,String module) throws Exception
	{
		String flag="";
		try {
			String qry = "SELECT master_table FROM pmis.custom_reports_module_child_tables WHERE module_name_fk= ? and name=?";
			 flag = (String) jdbcTemplate.queryForObject(qry, new Object[] { module,name }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return  flag;
	}
	
	private String getForeignKeyMaster(String module) throws Exception
	{
		String foreign_key="";
		try {
			String qry = "SELECT foreign_key FROM pmis.custom_reports_module_child_tables WHERE master_table='Yes' and module_name_fk= ?";
			foreign_key = (String) jdbcTemplate.queryForObject(qry, new Object[] { module }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return  foreign_key;
	}
	
	
	private String getForeignKeyChild(String name,String module) throws Exception
	{
		String foreign_key="";
		try {
			String qry = "SELECT foreign_key FROM pmis.custom_reports_module_child_tables WHERE module_name_fk= ? and name=?";
			foreign_key = (String) jdbcTemplate.queryForObject(qry, new Object[] { module,name }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return  foreign_key;
	}		
	
	@Override
	public ArrayList<String[]> getTableColumns(CustomReportColumns obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
	    ArrayList<String[]> arrayList = new ArrayList<>();
	    String[] row;
	    ResultSet resultSet=null;
	    try {
		    con = dataSource.getConnection();
		    
		    String qry ="";
		    
		    if(checkMasterTable(obj.getName(),obj.getModule_name_fk()).compareTo("Yes")==0)
		    {
			    qry ="select "+obj.getColumn_name()+" from "+getTableName(obj.getName(),obj.getModule_name_fk());
			    
			    if(obj.getFilterColumns()!=null)
			    {
			    	qry=qry+" a where "+obj.getFilterColumns();
			    }
		    }
		    else
		    {
		    	qry ="select "+obj.getColumn_name()+" from "+getMasterTable(obj.getModule_name_fk())+" as a ";
		    	
				qry = qry+" left join "+getTableName(obj.getName(),obj.getModule_name_fk())+" b on b."+getForeignKeyChild(obj.getName(),obj.getModule_name_fk())+"=a."+getForeignKeyMaster(obj.getModule_name_fk())+" ";
				
			    if(obj.getFilterColumns()!=null)
			    {
			    	qry=qry+" where "+obj.getFilterColumns();
			    }				
		    }
		    stmt = con.prepareStatement(qry);
	        resultSet = stmt.executeQuery();
	        int countColumn = resultSet.getMetaData().getColumnCount();
	        if (countColumn==0) return null;
	        while (resultSet.next()){
	            row = new String[countColumn];
	            for (int i = 0; i<countColumn; i++){
	                row[i] = resultSet.getString(i+1);
	            }
	            arrayList.add(row);
	        }
	    }catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}	
	    return arrayList;
	}

	@Override
	public List<CustomReportColumns> getModuleFilters(CustomReportColumns obj) throws Exception {
		PreparedStatement stmt = null;
		CustomReportColumns sobj = null;
		ResultSet rs=null;
		Connection con = null;
	    List<CustomReportColumns> objsList = new ArrayList<CustomReportColumns>();
	    try{  
		con = dataSource.getConnection();
		String qry ="select * from custom_reports_module_filter_table_columns where module_name_fk=?";
		stmt = con.prepareStatement(qry);
		stmt.setString(1,obj.getModule_name_fk());
		
		 rs = stmt.executeQuery();

	      while(rs.next())
	      {
				sobj = new CustomReportColumns();
				sobj.setFilter_name(rs.getString(2));
				sobj.setOption_id(rs.getString(3));
				sobj.setOption_value(rs.getString(4));
				sobj.setTable_name(rs.getString(5));
				objsList.add(sobj);
	      }
	    }catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}	      
        
		return objsList;
	}
	
	@Override
	public List<CustomReportColumns> getModuleFiltersOptionValues(CustomReportColumns obj) throws Exception {
		PreparedStatement stmt = null;
		CustomReportColumns sobj = null;
		ResultSet rs=null;
		Connection con = null;
	    List<CustomReportColumns> objsList = new ArrayList<CustomReportColumns>();
	    try{  
		con = dataSource.getConnection();
		String qry ="select * from "+obj.getTable_name();
		stmt = con.prepareStatement(qry);
	
		 rs = stmt.executeQuery();

	      while(rs.next())
	      {
				sobj = new CustomReportColumns();
				sobj.setOption_id(rs.getString(1));
				sobj.setOption_value(rs.getString(2));
				objsList.add(sobj);
	      }
	    }catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}	      
        
		return objsList;
	}	

}
