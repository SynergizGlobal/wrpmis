package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RiskReport;
import com.synergizglobal.pmis.reference.Idao.DashboardAccessFormDao;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.reference.model.DashboardAccessForm;
@Repository
public class DashboardAccessFormDaoImpl implements DashboardAccessFormDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<DashboardAccessForm> getDashboardAccessFormsList() throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry ="select bank_name from bank_name ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));	
			
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public DashboardAccessForm getDashboardAccessFormDetails(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		List<DashboardAccessForm> objsList1 = null;
		DashboardAccessForm sObj =null;
		try {
			String qry ="select bank_name from bank_name ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
			obj.setDashboardAccessFormList1(objsList);
			
			List<DashboardAccessForm> tablesList = getTablesList(obj);
			
			obj.setTablesList(tablesList);
			if(tablesList.size() > 0) {
				List<DashboardAccessForm> list = getDataDetails( obj);
				obj.setDashboardAccessFormList(list);
				String qry1 = "";
				int i = 1;
				for (DashboardAccessForm bObj : obj.getDashboardAccessFormList()) {
					
					qry1 = qry1 +"select "+bObj.getColumn_name()+" as bank_name_fk,count("+bObj.getColumn_name()+") as count,'"+bObj.getTable_name()+"' as tName from "+bObj.getTable_name()+" where "+bObj.getColumn_name()+" <> '' group by "+bObj.getColumn_name()+"  ";
					if( list.size() >  i) {
						qry1 = qry1 + " UNION ";
						i++;
					}
				}
				objsList1 = jdbcTemplate.query( qry1, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));
				obj.setDashboardAccessFormList(objsList1);
				obj.setCountList(objsList1);
				if(objsList1.size() > 0) {
					String qry2 = "select bank_name from bank_name where bank_name NOT IN (?";
		
					Object[] pValues = new Object[objsList1.size()];
					int j =0, p=1;
					for (DashboardAccessForm aObj : obj.getDashboardAccessFormList()) {
						pValues[j++] = aObj.getBank_name_fk();
						if( objsList1.size() >  p) {
							qry2 = qry2 + ",?";
							p++;
						}
					}
					qry2 = qry2 + ")";
					objsList1 = jdbcTemplate.query( qry2,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));
					obj.setDashboardAccessFormList(objsList1);
				}else {
					 obj.setdList(objsList);
				}
			}else {
				obj.setdList(objsList);
			}
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return obj;
	}

	@Override
	public boolean addDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO bank_name"
					+ "( bank_name) VALUES (:bank_name)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<DashboardAccessForm> tablesList = getTablesList(obj);
			List<DashboardAccessForm> list = getDataDetails(obj);
			obj.setDashboardAccessFormList(list);

			String disableQry = "Alter Table bank_name NOCHECK Constraint All ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String  updatereferenceTableQry = "UPDATE bank_name SET bank_name= :bank_name_new WHERE bank_name= :bank_name_old " ;
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(updatereferenceTableQry, paramSource);	
			
			for (DashboardAccessForm bObj : obj.getDashboardAccessFormList()) {
				
				String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:bank_name_new WHERE "+bObj.getColumn_name()+"= :bank_name_old " ;
				
				 paramSource = new BeanPropertySqlParameterSource(obj);		 
				 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
			}
			String  enableQry =	"Alter Table bank_name CHECK Constraint All";
			paramSource = new BeanPropertySqlParameterSource(obj);	
			namedParamJdbcTemplate.update(enableQry, paramSource);
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}


	private List<DashboardAccessForm> getTablesList(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> tablesList = null;
		try {
			String qry = "select  object_name(fkx.fkeyid) as tName,\r\n" + 
					"        \r\n" + 
					"        col_name(fkx.fkeyid, fkx.fkey) as COLUMN_NAME,\r\n" + 
					"		'' as CONSTRAINT_NAME,\r\n" + 
					"        object_name(fkx.rkeyid) as REFERENCED_TABLE_NAME,\r\n" + 
					"        col_name(fkx.rkeyid, fkx.rkey) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sysforeignkeys fkx \r\n" + 
					"inner join sys.tables t on object_name(fkx.rkeyid)=t.name \r\n" + 
					"inner join sys.schemas s on s.schema_id=t.schema_id \r\n" + 
					"where object_name(fkx.rkeyid)='bank_name' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));	
			for(DashboardAccessForm tName : tablesList) {
				 String name = tName.gettName();
				 name = name.replaceAll("[_]", " ");  
				 String regex = "\\b(.)(.*?)\\b";
			     String captilizedName = Pattern.compile(regex).matcher(name).replaceAll(
			            matche -> matche.group(1).toUpperCase() + matche.group(2)
			     );
			     tName.setCaptiliszedTableName(captilizedName);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return tablesList;
	}


	private List<DashboardAccessForm> getDataDetails(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> list = null;
		try {
			String qry = "select  object_name(fkx.fkeyid) as TABLE_NAME,\r\n" + 
					"        \r\n" + 
					"        col_name(fkx.fkeyid, fkx.fkey) as COLUMN_NAME,\r\n" + 
					"		'' as CONSTRAINT_NAME,\r\n" + 
					"        object_name(fkx.rkeyid) as REFERENCED_TABLE_NAME,\r\n" + 
					"        col_name(fkx.rkeyid, fkx.rkey) as REFERENCED_COLUMN_NAME\r\n" + 
					"from sysforeignkeys fkx \r\n" + 
					"inner join sys.tables t on object_name(fkx.rkeyid)=t.name \r\n" + 
					"inner join sys.schemas s on s.schema_id=t.schema_id \r\n" + 
					"where object_name(fkx.rkeyid)='bank_name' and s.name='dbo'\r\n" + 
					"order by object_name(fkx.fkeyid), fkx.keyno";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}


	@Override
	public boolean deleteDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE FROM bank_name WHERE bank_name= :bank_name; ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			 count = namedParamJdbcTemplate.update(deleteQry, paramSource);
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return flag;
	}


	@Override
	public List<DashboardAccessForm> getProjectsList(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = new ArrayList<DashboardAccessForm>();
		try {
			String qry = "select distinct project_id as project_id_fk,project_name "
					+ "from project p "
					+ "LEFT JOIN work w on w.project_id_fk = p.project_id "
					+ "where project_id is not null ";

			objsList = jdbcTemplate.query( qry,  new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<DashboardAccessForm> getWorksList(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = new ArrayList<DashboardAccessForm>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and p.project_id = ? ";
				arrSize++;
			}	

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			
			qry = qry + " order by work_id asc";

			objsList = jdbcTemplate.query( qry,pValues,  new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DashboardAccessForm> getUsersDetails(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objList = null;
		try {
			String qry = "	select distinct user_id,user_name,designation from( " + 
					"	select distinct ur.user_id,ur.user_name,w.work_id,ur.designation from work w " + 
					"	inner join contract c on c.work_id_fk=w.work_id " + 
					"	left join [user] u on c.hod_user_id_fk = u.user_id  " + 
					"	left join [user] ur on u.user_id = ur.reporting_to_id_srfk  " + 
					"	where  ur.user_name is not null) as a where 0=0 ";

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}


	@Override
	public List<DashboardAccessForm> getUserRolesInDashboardAccess(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry = "select user_role_name as access_value_id FROM user_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DashboardAccessForm> getUserTypesInDashboardAccess(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry = "select user_type as access_value_id FROM user_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<DashboardAccessForm> getUsersInDashboardAccess(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry = "select user_id as access_value_id,user_name as access_value_name FROM [user]";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<DashboardAccessForm> getDashboardUserAccess(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objList = null;
		try {
			String qry = "	select access_type,STRING_AGG(access_value,',') as access_value from dashboard d left join dashboard_access da on da.dashboard_id_fk=d.dashboard_id where 0=0 ";

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " group by access_type ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}
	
	public List<DashboardAccessForm> getDashboardModules(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objList = null;
		try {
			String qry = "	select distinct dashboard_name from dashboard d left join dashboard_access da on da.dashboard_id_fk=d.dashboard_id " + 
					"where parent_dashboard_id_sr_fk=dashboard_id and soft_delete_status_fk='Active' and dashboard_type_fk='Module' and dashboard_name in(select module_name from module where soft_delete_status_fk='Active')";
			
			objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}	


	@Override
	public boolean addDashboardUserAccess(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		try {
			String query ="";
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			Connection con =  dataSource.getConnection();
			con = dataSource.getConnection();
			
			ResultSet rs = null;	
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			
			if(!StringUtils.isEmpty(obj.getAccess_value())) {
			 String splitStr[]=obj.getAccess_value().split("###");
				for(int i1=0;i1<splitStr.length;i1++)
				{
					String splitStrColumns[]=splitStr[i1].split("___");
					for(int j=0;j<splitStrColumns.length;j++)
					{
						String dashboardid="";
						
						String[] splitModuleAccessType=splitStrColumns[0].split("@@@");
						
						if(splitModuleAccessType[1].compareTo("UserRole")==0)
						{

							String deleteQry = "delete from left_menu_access where dashboard_id in(select distinct d.dashboard_id from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id " + 
									"where source_field_value=? and access_type='user_role' and dashboard_name=?) and access_type='user_role'";	
							
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.setString(2,splitModuleAccessType[0]);
							
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
							
							
							String checkQry ="select count(*) from left_menu where source_field_value='"+obj.getWork_id_fk()+"' and dashboard_name='"+splitModuleAccessType[0]+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
							       + " select distinct dashboard_id,'user_role',? from left_menu where source_field_value=? and dashboard_name=?";
							}
							else
							{
								String insertQry = "INSERT INTO left_menu"
										+ "(dashboard_name,[order], parent_id,dashboard_url,status,source_field_name,source_field_value) VALUES (?,?,?,?,?,?,?)";
								
								stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
								stmt.setString(1, splitModuleAccessType[0]);
								stmt.setInt(2, 10);
								stmt.setInt(3, 0);
								stmt.setString(4, "NULL");
								stmt.setString(5, "Active");
								stmt.setString(6, "work_id");
								stmt.setString(7, obj.getWork_id_fk());
								stmt.executeUpdate();			 
								 
								rs = stmt.getGeneratedKeys();
								String generatedKey = "0";
								if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
								
								if(flag==true)
								{
									query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
										       + " select "+generatedKey+",'user_role',? from left_menu where source_field_value=? and dashboard_name=?";	
								}
							}
						}

						if(splitModuleAccessType[1].compareTo("UserType")==0)
						{

							String deleteQry = "delete from left_menu_access where dashboard_id in(select distinct d.dashboard_id from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id " + 
									"where source_field_value=? and access_type='user_type' and dashboard_name=?) and access_type='user_type' ";	
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.setString(2,splitModuleAccessType[0]);
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	


							
							
							String checkQry ="select count(*) from left_menu where source_field_value='"+obj.getWork_id_fk()+"' and dashboard_name='"+splitModuleAccessType[0]+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
									       + " select distinct dashboard_id,'user_type',? from left_menu where source_field_value=? and dashboard_name=?";
							}
							else
							{
								String insertQry = "INSERT INTO left_menu"
										+ "(dashboard_name,[order], parent_id,dashboard_url,status,source_field_name,source_field_value) VALUES (?,?,?,?,?,?,?)";
								
								stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
								stmt.setString(1, splitModuleAccessType[0]);
								stmt.setInt(2, 10);
								stmt.setInt(3, 0);
								stmt.setString(4, "NULL");
								stmt.setString(5, "Active");
								stmt.setString(6, "work_id");
								stmt.setString(7, obj.getWork_id_fk());
								stmt.executeUpdate();			 
								 
								rs = stmt.getGeneratedKeys();
								String generatedKey = "0";
								if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
								
								if(flag==true)
								{
									query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
										       + " select distinct dashboard_id,'user_type',? from left_menu where source_field_value=? and dashboard_name=?";	
								}
							}							
							
							
							
							
							
						}

						if(splitModuleAccessType[1].compareTo("User")==0)
						{

							String deleteQry = "delete from left_menu_access where dashboard_id in(select distinct d.dashboard_id from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id " + 
									"where source_field_value=? and access_type='user' and dashboard_name=?) and access_type='user'";	
							stmt = con.prepareStatement(deleteQry);
							
							stmt.setString(1,obj.getWork_id_fk());
							stmt.setString(2,splitModuleAccessType[0]);
							
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	


							
							String checkQry ="select count(*) from left_menu where source_field_value='"+obj.getWork_id_fk()+"' and dashboard_name='"+splitModuleAccessType[0]+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
									       + " select distinct dashboard_id,'user',? from left_menu where source_field_value=? and dashboard_name=?";
							}
							else
							{
								String insertQry = "INSERT INTO left_menu"
										+ "(dashboard_name,[order], parent_id,dashboard_url,status,source_field_name,source_field_value) VALUES (?,?,?,?,?,?,?)";
								
								stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
								stmt.setString(1, splitModuleAccessType[0]);
								stmt.setInt(2, 10);
								stmt.setInt(3, 0);
								stmt.setString(4, "NULL");
								stmt.setString(5, "Active");
								stmt.setString(6, "work_id");
								stmt.setString(7, obj.getWork_id_fk());
								stmt.executeUpdate();			 
								 
								rs = stmt.getGeneratedKeys();
								String generatedKey = "0";
								if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
								
								if(flag==true)
								{
									query = " insert into left_menu_access (dashboard_id,access_type,access_value)"
										       + " select distinct dashboard_id,'user',? from left_menu where source_field_value=? and dashboard_name=?";	
								}
							}							
							
						}
						

						
						stmt = con.prepareStatement(query);
					    
						String Str2[]=splitStrColumns[1].split(",");
							for (int i = 0; i < Str2.length; i++) 
							{
								stmt.setString(1, Str2[i]);
								stmt.setString(2, obj.getWork_id_fk());
								stmt.setString(3, splitModuleAccessType[0]);
								stmt.execute();
							}
											

					    flag = true;
												
					}					
				}
			}
			

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}


	@Override
	public List<DashboardAccessForm> getdashboardNames(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry = "select distinct dashboard_id as access_value_id,dashboard_name as access_value_name from left_menu where parent_id=0 and status='Active' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and source_field_value = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	@Override
	public List<DashboardAccessForm> getLeftmenuDashboardNames(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objsList = null;
		try {
			String qry = "select distinct dashboard_name from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id where parent_id=0 and status='Active' and (access_type='user_role' or access_type='user_type' or access_type='user') ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and source_field_value = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public boolean updateWorkAccess(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		ResultSet rs = null;
		try {
			String query ="";
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			Connection con =  dataSource.getConnection();
			
			if(!StringUtils.isEmpty(obj.getAccess_value())) {
			 String splitStr[]=obj.getAccess_value().split("###");
				for(int i1=0;i1<splitStr.length;i1++)
				{
					String splitStrColumns[]=splitStr[i1].split("___");
					for(int j=0;j<splitStrColumns.length;j++)
					{
						String dashboardid="";
						if(splitStrColumns[0].compareTo("UserRole")==0)
						{
							
							String deleteQry = "delete from dashboard_access where dashboard_id_fk in(select distinct dashboard_id from dashboard d left join dashboard_access da on da.dashboard_id_fk=d.dashboard_id " + 
									"where work_id_fk=? and access_type='user_role') and access_type='user_role'";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				

							
							
							
							String checkQry ="select count(*) from dashboard where work_id_fk='"+obj.getWork_id_fk()+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
							               + " select distinct dashboard_id,'user_role',? from dashboard where work_id_fk=? ";
							}
							else
							{
								
								List<DashboardAccessForm>  DashboardModules =getDashboardModules(obj);
								for (DashboardAccessForm module : DashboardModules) 
								{
								
									String insertQry = "INSERT INTO dashboard"
											+ "(dashboard_name,work_id_fk, module_name_fk,dashboard_url,dashboard_type_fk, priority,soft_delete_status_fk,dashboard_id,parent_dashboard_id_sr_fk) VALUES (?,?,?,?,?,?,?,(select isnull(max(dashboard_id),0)+1 from dashboard),(select isnull(max(dashboard_id),0)+1 from dashboard))";
									
									stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
									stmt.setString(1, module.getDashboard_name());
									stmt.setString(2, obj.getWork_id_fk());
									stmt.setString(3,  module.getDashboard_name());
									stmt.setString(4, "NULL");
									stmt.setString(5, "Module");
									stmt.setInt(6, 10);
									stmt.setString(7, "Active");								
									stmt.executeUpdate();				 
									 
									rs = stmt.getGeneratedKeys();
									String generatedKey = "0";
									if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
									
									if(flag==true)
									{
										query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
									               + " select distinct "+generatedKey+",'user_role',? from dashboard where work_id_fk=? ";	
									}
								}
							}							
							
							
							
							
							
							
						}
						
						if(splitStrColumns[0].compareTo("UserType")==0)
						{
						
							String deleteQry = "delete from dashboard_access where dashboard_id_fk in(select distinct dashboard_id from dashboard d left join dashboard_access da on da.dashboard_id_fk=d.dashboard_id " + 
									"where work_id_fk=? and access_type='user_type') and access_type='user_type'";	
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				

							

							String checkQry ="select count(*) from dashboard where work_id_fk='"+obj.getWork_id_fk()+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
							               + " select distinct dashboard_id,'user_type',? from dashboard where work_id_fk=?";
							}
							else
							{
								List<DashboardAccessForm>  DashboardModules =getDashboardModules(obj);
								for (DashboardAccessForm module : DashboardModules) 
								{
									String insertQry = "INSERT INTO dashboard"
											+ "(dashboard_name,work_id_fk, module_name_fk,dashboard_url,dashboard_type_fk, priority,soft_delete_status_fk,dashboard_id,parent_dashboard_id_sr_fk) VALUES (?,?,?,?,?,?,?,(select isnull(max(dashboard_id),0)+1 from dashboard),(select isnull(max(dashboard_id),0)+1 from dashboard))";
									
									stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
									stmt.setString(1, module.getDashboard_name());
									stmt.setString(2, obj.getWork_id_fk());
									stmt.setString(3,  module.getDashboard_name());
									stmt.setString(4, "NULL");
									stmt.setString(5, "Module");
									stmt.setInt(6, 10);
									stmt.setString(7, "Active");								
									stmt.executeUpdate();			 
									 
									rs = stmt.getGeneratedKeys();
									String generatedKey = "0";
									if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
									
									if(flag==true)
									{
										query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
									               + " select distinct "+generatedKey+",'user_type',? from dashboard where work_id_fk=?";
									}
								}
							}	
							
							
							
							
						}
						
						if(splitStrColumns[0].compareTo("User")==0)
						{
						
							String deleteQry = "delete from dashboard_access where dashboard_id_fk in(select distinct dashboard_id from dashboard d left join dashboard_access da on da.dashboard_id_fk=d.dashboard_id " + 
									"where work_id_fk=? and access_type='user') and access_type='user'";	
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				

							
							String checkQry ="select count(*) from dashboard where work_id_fk='"+obj.getWork_id_fk()+"'";
							int fieldCnt = (int) jdbcTemplate.queryForObject(checkQry, int.class);
							
							if(fieldCnt>0)
							{
								query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
							               + " select distinct dashboard_id,'user',? from dashboard where work_id_fk=?";
							}
							else
							{
								List<DashboardAccessForm>  DashboardModules =getDashboardModules(obj);
								for (DashboardAccessForm module : DashboardModules) 
								{
									String insertQry = "INSERT INTO dashboard"
											+ "(dashboard_name,work_id_fk, module_name_fk,dashboard_url,dashboard_type_fk, priority,soft_delete_status_fk,dashboard_id,parent_dashboard_id_sr_fk) VALUES (?,?,?,?,?,?,?,(select isnull(max(dashboard_id),0)+1 from dashboard),(select isnull(max(dashboard_id),0)+1 from dashboard))";
									
									stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
									stmt.setString(1, module.getDashboard_name());
									stmt.setString(2, obj.getWork_id_fk());
									stmt.setString(3,  module.getDashboard_name());
									stmt.setString(4, "NULL");
									stmt.setString(5, "Module");
									stmt.setInt(6, 10);
									stmt.setString(7, "Active");								
									stmt.executeUpdate();			 
									 
									rs = stmt.getGeneratedKeys();
									String generatedKey = "0";
									if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
									
									if(flag==true)
									{
										query = " insert into dashboard_access (dashboard_id_fk,access_type,access_value)"
									               + " select distinct "+generatedKey+",'user',? from dashboard where work_id_fk=?";
									}
								}
							}							
							
						}
						

						
						stmt = con.prepareStatement(query);
					    
						String Str2[]=splitStrColumns[1].split(",");
							for (int i = 0; i < Str2.length; i++) 
							{
								stmt.setString(1, Str2[i]);
								stmt.setString(2, obj.getWork_id_fk());
								stmt.execute();
							}
											

					    flag = true;
												
					}					
				}
			}
			

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}


	@Override
	public List<DashboardAccessForm> getOverviewDashboardUserAccess(DashboardAccessForm obj) throws Exception {
		List<DashboardAccessForm> objList = null;
		try {
			String qry = "	select distinct access_type, STRING_AGG(access_value,',') as access_value,dashboard_name from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id where parent_id=0 and status='Active' ";

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and source_field_value = ? ";
				arrSize++;
			}
			qry = qry + " group by access_type,dashboard_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<DashboardAccessForm>(DashboardAccessForm.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

}




