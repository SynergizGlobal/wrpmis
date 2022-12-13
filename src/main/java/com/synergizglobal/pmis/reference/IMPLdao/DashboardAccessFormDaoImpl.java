package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
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
			String qry = "	select access_type,STRING_AGG(access_value,',') as access_value from left_menu d left join left_menu_access da on da.dashboard_id=d.dashboard_id where 0=0 ";

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
	public boolean addDashboardUserAccess(DashboardAccessForm obj) throws Exception {
		boolean flag = false;
		try {
			String query ="";
			PreparedStatement stmt = null;
			Connection con =  dataSource.getConnection();
			
			if(!StringUtils.isEmpty(obj.getModule_name_fk())) {
			 String splitStr[]=obj.getModule_name_fk().split("###");
				for(int i1=0;i1<splitStr.length;i1++)
				{
					String splitStrColumns[]=splitStr[i1].split("___");
					for(int j=0;j<splitStrColumns.length;j++)
					{
						if(splitStrColumns[0].compareTo("Contracts")==0)
						{
							
							String deleteQry = "DELETE from contractexecutives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}							

				
							query = " insert into contractexecutives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("Design")==0)
						{
						
							String deleteQry = "DELETE from designexecutives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				
							query = " insert into designexecutives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("Finance")==0)
						{
						
							String deleteQry = "DELETE from finance_executives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				
							query = " insert into finance_executives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("LandAcquisition")==0)
						{
						
							String deleteQry = "DELETE from land_executives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}		
				
							query = " insert into land_executives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("RandR")==0)
						{
						
							String deleteQry = "DELETE from rr_executives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}		
				
							query = " insert into rr_executives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("Risk")==0)
						{
						
							String deleteQry = "DELETE from risk_work_hod  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}	
				
							query = " insert into risk_work_hod (work_id_fk, hod_user_id_fk)"
					               + " values (?,?)";
						}
						
						if(splitStrColumns[0].compareTo("UtilityShifting")==0)
						{
						
							String deleteQry = "DELETE from utility_shifting_executives  where work_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getWork_id_fk());
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}		
				
							query = " insert into utility_shifting_executives (work_id_fk, executive_user_id_fk)"
					               + " values (?,?)";
						}
						
						stmt = con.prepareStatement(query);
					    
						String Str2[]=splitStrColumns[1].split(",");
							for (int i = 0; i < Str2.length; i++) 
							{
								stmt.setString(1, obj.getWork_id_fk());
								stmt.setString(2, Str2[i]);
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

}




