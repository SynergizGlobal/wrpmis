package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ProfileDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;	
	
	@Override
	public User getUserProfile(String userId) throws Exception {
		User userDetails = null;
		try{  
			
			/*String qry = "select user_id,user_name,password,designation,email_id,cast(mobile_number as CHAR) as mobile_number,cast(landline as CHAR) as landline,cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks "
					+ "from user "
					+ "where user_id = BINARY ?";*/
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.user_type_fk,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_image,department_name,usr.user_name as reporting_to_name,usr.designation as reporting_to_designation "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id = ? " ;
			
			
			userDetails = (User)jdbcTemplate.queryForObject( qry,  new Object[] {userId}, new BeanPropertyRowMapper<User>(User.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return userDetails;

	}

	@Override
	public boolean updateProfile(User user) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE user set "
					+ "user_name= :user_name, email_id= :email_id, mobile_number= :mobile_number,"
					+ "personal_contact_number= :personal_contact_number, landline= :landline, extension= :extension,user_image= :user_image "
					+ "where user_id= :user_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean insertLeaveResponsibility(User obj) throws Exception {

		String userId = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag=false;
		
		try {
			connection = dataSource.getConnection();
			long Key=0;
			if(obj.getUser_leave_id()==0)
			{
				String qry = "INSERT INTO user_leave_responsibility"
						+ "(employee_id,from_date,to_date,delete_status,created_by,created_date) "
						+ "VALUES "
						+ "(?,?,?,?,?,CURRENT_TIMESTAMP)";		 
	
				stmt = connection.prepareStatement(qry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, obj.getUser_id());
				stmt.setString(2, obj.getFrom_date());
				stmt.setString(3, obj.getTo_date());
				stmt.setString(4, "No");
				stmt.setString(5, obj.getCreated_by_user_id_fk());
				
				stmt.executeUpdate();
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) 
				{
					Key=generatedKeys.getLong(1);
	            }
				if(stmt != null){stmt.close();}
			}
			else
			{
				String qry = "update user_leave_responsibility set from_date=?,to_date=?,modified_by=?,modified_date=CURRENT_TIMESTAMP,employee_id=? where user_leave_id=?";		 
	
				stmt = connection.prepareStatement(qry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, obj.getFrom_date());
				stmt.setString(2, obj.getTo_date());
				stmt.setString(3, obj.getCreated_by_user_id_fk());
				stmt.setString(4, obj.getUser_id());
				stmt.setLong(5, obj.getUser_leave_id());


				stmt.executeUpdate();	
				Key=obj.getUser_leave_id();
				if(stmt != null){stmt.close();}
				
				NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
				String deleteQry = "DELETE from user_assign_responsibility where user_leave_id = :user_leave_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				int count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
			}
			
			if(!StringUtils.isEmpty(obj.getModules()) && obj.getModules().length > 0) 
			{				
				String userassignresponsibility = "INSERT INTO user_assign_responsibility (user_leave_id,module,responsible_person) VALUES  (?,?,?)";
				String[] SplitVar=obj.getModules();
				String[] SplitVar1=obj.getResponsible_persons();
				
				for(int i=0;i<SplitVar.length;i++)
				{
					stmt = connection.prepareStatement(userassignresponsibility);
					stmt.setLong(1, Key);
					stmt.setString(2, SplitVar[i]);
					stmt.setString(3, SplitVar1[i]);
	                stmt.executeUpdate();  
				}
				flag=true;
				if(stmt != null){stmt.close();}
				
			}
			else
			{
				if(stmt != null){stmt.close();}
				String qry = "select distinct module from user_leave_tables";
				stmt = connection.prepareStatement(qry);
				rs = stmt.executeQuery();
				while(rs.next()) 
				{
					String userassignresponsibility = "INSERT INTO user_assign_responsibility (user_leave_id,module,responsible_person) VALUES  (?,?,?)";
					String[] SplitVar1=obj.getResponsible_persons();
					
						stmt = connection.prepareStatement(userassignresponsibility);
						stmt.setLong(1, Key);
						stmt.setString(2, rs.getString("module"));
						stmt.setString(3, SplitVar1[0]);
		                stmt.executeUpdate();  
					
				}
				if(stmt != null){stmt.close();}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public List<User> getPastLeaves(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "SELECT distinct l.user_leave_id,employee_id as user_id,from_date,to_date,"
					+ "group_concat(module) as modules,group_concat(concat(u.designation,'-',u.user_name)) as responsible_persons "
					+ " FROM pmis.user_leave_responsibility l "
					+ "inner join user_assign_responsibility a on a.user_leave_id=l.user_leave_id inner join user u on u.user_id=a.responsible_person COLLATE utf8mb4_unicode_ci where employee_id=? and delete_status='No' "
					+ " group by l.user_leave_id order by user_leave_id " ;
			objsList = jdbcTemplate.query( qry,new Object[] { obj.getUser_id() },new BeanPropertyRowMapper<User>(User.class));
	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean checkLeaveResponsibility(User obj) throws Exception {
		boolean flag=false;
		List<User> objsList = null;
		try {
			String qry = "SELECT distinct l.user_leave_id,employee_id as user_id,from_date,to_date,"
					+ "group_concat(module) as modules,group_concat(responsible_person) as responsible_persons "
					+ " FROM pmis.user_leave_responsibility l "
					+ "inner join user_assign_responsibility a on a.user_leave_id=l.user_leave_id where delete_status='No' and employee_id=? and from_date>=? and to_date>=CURDATE() ";
					
			
			int arrSize = 2;
			
			if(obj.getUser_leave_id()!=0)
			{
				qry =qry+ " and l.user_leave_id not in(?) and from_date>=? and to_date<=?" ;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry =qry+ " group by l.user_leave_id" ;
			Object[] pValues = new Object[arrSize];

			int i = 0;
			pValues[i++] = obj.getUser_id();
			pValues[i++] = obj.getFrom_date();
			
			if(obj.getUser_leave_id()!=0)
			{
				pValues[i++] = obj.getUser_leave_id();
				pValues[i++] = obj.getFrom_date();
				pValues[i++] = obj.getTo_date();
			}
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<User>(User.class));
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
	public boolean generateAutoResponsibility() throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
				connection = dataSource.getConnection();		
				String qry = "select * from user_leave_responsibility l inner join user_assign_responsibility a on a.user_leave_id=l.user_leave_id where delete_status='No' and CURDATE()>=from_date and CURDATE()<=to_date";
				statement = connection.prepareStatement(qry);
				resultSet = statement.executeQuery();  
				while(resultSet.next()) 
				{
					String EmployeeId=resultSet.getString("employee_id");
					String module=resultSet.getString("module");
					String responsibleperson=resultSet.getString("responsible_person");
					String userleaveid=resultSet.getString("user_leave_id");
					
					ResultSet resultSet1 = null;
					PreparedStatement statement1 = null;
					String qry1 = "select * from user_leave_tables where module=?";
					
					statement1 = connection.prepareStatement(qry1);
					statement1.setString(1, module);
					resultSet1 = statement1.executeQuery(); 
					while(resultSet1.next()) 
					{
						String tablename=resultSet1.getString("tablename");
						String columnname=resultSet1.getString("columnname");
						PreparedStatement statement2 = null;

						
						String qry5 ="";
						String qryString ="";
						if(tablename.compareTo("contract")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{
								qry5 = "select group_concat(contract_id) as contract_id from contract where hod_user_id_fk=?";
								qryString = "select contract_id  from contract where hod_user_id_fk=?";
							}
							else if(columnname.compareTo("dy_hod_user_id_fk")==0)
							{
								qry5 = "select group_concat(contract_id) as group_concat from contract where dy_hod_user_id_fk=?";
								qryString = "select contract_id from contract where dy_hod_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("contract_executive")==0)
						{
							if(columnname.compareTo("executive_user_id_fk")==0)
							{
								qry5 = "select group_concat(id) as id from contract_executive where executive_user_id_fk=?";
								qryString = "select id from contract_executive where executive_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("risk_work_hod")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{							
								qry5 = "select group_concat(risk_work_hod_id) as risk_work_hod_id from risk_work_hod where hod_user_id_fk=?";
								qryString = "select risk_work_hod_id from risk_work_hod where hod_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("fob_contract_responsible_people")==0)
						{
							if(columnname.compareTo("responsible_people_id_fk")==0)
							{							
								qry5 = "select group_concat(id) as id from fob_contract_responsible_people where responsible_people_id_fk=?";
								qryString = "select id from fob_contract_responsible_people where responsible_people_id_fk=?";
							}
						}						
						else if(tablename.compareTo("safety")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{							
								qry5 = "select group_concat(safety_id) as safety_id from safety where responsible_person=?";
								qryString = "select safety_id from safety where responsible_person=?";
							}
						}	
						else if(tablename.compareTo("safety_committee_members")==0)
						{
							if(columnname.compareTo("committee_member_name")==0)
							{							
								qry5 = "select group_concat(id) as id from safety_committee_members where committee_member_name=?";
								qryString = "select id from safety_committee_members where committee_member_name=?";
							}
						}
						else if(tablename.compareTo("issue")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{
								qry5 = "select group_concat(issue_id) as issue_id  from issue where responsible_person=?";
								qryString = "select issue_id  from issue where responsible_person=?";
							}
							else if(columnname.compareTo("escalated_to")==0)
							{
								qry5 = "select group_concat(issue_id) as issue_id from issue where escalated_to=?";
								qryString = "select issue_id from issue where escalated_to=?";
							}
						}
						else if(tablename.compareTo("design")==0)
						{
							if(columnname.compareTo("hod")==0)
							{
								qry5 = "select group_concat(design_id) as design_id from design where hod=?";
								qryString = "select design_id from design where hod=?";
							}
							else if(columnname.compareTo("dy_hod")==0)
							{
								qry5 = "select group_concat(design_id) as design_id from design where dy_hod=?";
								qryString = "select design_id from design where dy_hod=?";
							}
						}						
						
						
						PreparedStatement statement5 = connection.prepareStatement(qry5);
						statement5.setString(1, EmployeeId);
						ResultSet resultSet5 = statement5.executeQuery(); 
						while(resultSet5.next()) 
						{
							String PKIDORValue=resultSet5.getString(1);

						
							if(PKIDORValue!=null)
							{
							String qry2 = "insert into user_leave_responsibility_history(tablename,columnname,primary_key_value,user_leave_id,reverted_back,assigned_on) values (?,?,?,?,?,CURRENT_TIMESTAMP)";
							
							statement2 = connection.prepareStatement(qry2);
							statement2.setString(1, tablename);
							statement2.setString(2, columnname);
							statement2.setString(3,PKIDORValue);

							
							statement2.setString(4, userleaveid);
							statement2.setString(5, "No");						
							
						 
							statement2.executeUpdate();		
							
							
							PreparedStatement statement3 = null;	
							String qry3="";
							
							if(tablename.compareTo("contract")==0)
							{
								if(columnname.compareTo("hod_user_id_fk")==0)
								{
									qry3 = "update contract a inner join ("+qryString+") b on a.contract_id=b.contract_id set hod_user_id_fk=?";
								}
								else if(columnname.compareTo("dy_hod_user_id_fk")==0)
								{
									qry3 = "update contract a inner join ("+qryString+") b on a.contract_id=b.contract_id set dy_hod_user_id_fk=?";
								}
							}
							else if(tablename.compareTo("contract_executive")==0)
							{
								if(columnname.compareTo("executive_user_id_fk")==0)
								{
									qry3 = "update contract_executive a inner join ("+qryString+") b on a.id=b.id set executive_user_id_fk=?";
								}
							}
							else if(tablename.compareTo("risk_work_hod")==0)
							{
								if(columnname.compareTo("hod_user_id_fk")==0)
								{							
									qry3 = "update risk_work_hod a inner join ("+qryString+") b on a.risk_work_hod_id=b.risk_work_hod_id set hod_user_id_fk=?";
								}
							}
							else if(tablename.compareTo("fob_contract_responsible_people")==0)
							{
								if(columnname.compareTo("responsible_people_id_fk")==0)
								{							
									qry3 = "update fob_contract_responsible_people a inner join ("+qryString+") b on a.id=b.id set responsible_people_id_fk=?";
								}
							}						
							else if(tablename.compareTo("safety")==0)
							{
								if(columnname.compareTo("responsible_person")==0)
								{							
									qry3 = "update safety a inner join ("+qryString+") b on a.safety_id=b.safety_id set responsible_person=?";
								}
							}	
							else if(tablename.compareTo("safety_committee_members")==0)
							{
								if(columnname.compareTo("committee_member_name")==0)
								{							
									qry3 = "update safety_committee_members a inner join ("+qryString+") b on a.id=b.id set committee_member_name=?";
								}
							}
							else if(tablename.compareTo("issue")==0)
							{
								if(columnname.compareTo("responsible_person")==0)
								{
									qry3 = "update issue a inner join ("+qryString+") b on a.issue_id=b.issue_id set responsible_person=?";
								}
								else if(columnname.compareTo("escalated_to")==0)
								{
									qry3 = "update issue a inner join ("+qryString+") b on a.issue_id=b.issue_id set escalated_to=?";
								}
							}
							else if(tablename.compareTo("design")==0)
							{
								if(columnname.compareTo("hod")==0)
								{
									qry3 = "update design a inner join ("+qryString+") b on a.design_id=b.design_id set hod=?";
								}
								else if(columnname.compareTo("dy_hod")==0)
								{
									qry3 = "update design a inner join ("+qryString+") b on a.design_id=b.design_id set dy_hod=?";
								}
							}						
							
							
							statement3 = connection.prepareStatement(qry3);
							statement3.setString(1, EmployeeId);
							statement3.setString(2, responsibleperson);
							statement3.executeUpdate();	
						}
						}
					}
					
				}
					
			}catch(Exception e){ 


				
			}finally {
				DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
			}
		return false;
	}

	@Override
	public boolean generateRevertAutoResponsibility() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
				connection = dataSource.getConnection();		
				String qry = "select * from user_leave_responsibility l inner join user_assign_responsibility a on a.user_leave_id=l.user_leave_id where delete_status='No' and to_date=SUBDATE(CURDATE(),1)";
				statement = connection.prepareStatement(qry);
				resultSet = statement.executeQuery();  
				while(resultSet.next()) 
				{
					String EmployeeId=resultSet.getString("employee_id");
					String userleaveid=resultSet.getString("user_leave_id");
					
					ResultSet resultSet1 = null;
					PreparedStatement statement1 = null;
					String qry1 = "select * from user_leave_responsibility_history where reverted_back='No' and user_leave_id=?";
					
					statement1 = connection.prepareStatement(qry1);
					statement1.setString(1, userleaveid);
					resultSet1 = statement1.executeQuery(); 
					while(resultSet1.next()) 
					{
						String tablename=resultSet1.getString("tablename");
						String columnname=resultSet1.getString("columnname");

						String history_responsibility_id=resultSet1.getString("history_responsibility_id");
						String pkvalue=resultSet1.getString("primary_key_value");
						String fnlValue=pkvalue.replaceAll(",","\",\"");
						fnlValue="\""+fnlValue+"\"";
						
						
						String qryString ="";
						if(tablename.compareTo("contract")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( contract_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
							else if(columnname.compareTo("dy_hod_user_id_fk")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( contract_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}
						else if(tablename.compareTo("contract_executive")==0)
						{
							qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( id VARCHAR(100) PATH \"$\" )) AS  jt1";
						}
						else if(tablename.compareTo("risk_work_hod")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( risk_work_hod_id VARCHAR(100) PATH \"$\" )) AS  jt1";
								
							}
						}
						else if(tablename.compareTo("fob_contract_responsible_people")==0)
						{
							if(columnname.compareTo("responsible_people_id_fk")==0)
							{							
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}						
						else if(tablename.compareTo("safety")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{							
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( safety_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}	
						else if(tablename.compareTo("safety_committee_members")==0)
						{
							if(columnname.compareTo("committee_member_name")==0)
							{							
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}
						else if(tablename.compareTo("issue")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( issue_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
							else if(columnname.compareTo("escalated_to")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( issue_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}
						else if(tablename.compareTo("design")==0)
						{
							if(columnname.compareTo("hod")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( design_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
							else if(columnname.compareTo("dy_hod")==0)
							{
								qryString="SELECT * FROM JSON_TABLE('["+fnlValue+"]',\"$[*]\" COLUMNS( design_id VARCHAR(100) PATH \"$\" )) AS  jt1";
							}
						}							
						
						
						
						
						PreparedStatement statement2 = null;	
						
						String qry2 = "update user_leave_responsibility_history set reverted_back=?,reverted_on=CURRENT_TIMESTAMP where history_responsibility_id=?";
						
						statement2 = connection.prepareStatement(qry2);
						statement2.setString(1, "Yes");
						statement2.setString(2, history_responsibility_id);	
						statement2.executeUpdate();		
						
						PreparedStatement statement3 = null;	
						String qry3="";
						
						if(tablename.compareTo("contract")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{
								qry3 = "update contract a inner join ("+qryString+") b on a.contract_id=b.contract_id COLLATE utf8mb4_unicode_ci set hod_user_id_fk=?";
							}
							else if(columnname.compareTo("dy_hod_user_id_fk")==0)
							{
								qry3 = "update contract a inner join ("+qryString+") b on a.contract_id=b.contract_id COLLATE utf8mb4_unicode_ci set dy_hod_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("contract_executive")==0)
						{
							if(columnname.compareTo("executive_user_id_fk")==0)
							{
								qry3 = "update contract_executive a inner join ("+qryString+") b on a.id=b.id COLLATE utf8mb4_unicode_ci set executive_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("risk_work_hod")==0)
						{
							if(columnname.compareTo("hod_user_id_fk")==0)
							{							
								qry3 = "update risk_work_hod a inner join ("+qryString+") b on a.risk_work_hod_id=b.risk_work_hod_id COLLATE utf8mb4_unicode_ci set hod_user_id_fk=?";
							}
						}
						else if(tablename.compareTo("fob_contract_responsible_people")==0)
						{
							if(columnname.compareTo("responsible_people_id_fk")==0)
							{							
								qry3 = "update fob_contract_responsible_people a inner join ("+qryString+") b on a.id=b.id COLLATE utf8mb4_unicode_ci set responsible_people_id_fk=?";
							}
						}						
						else if(tablename.compareTo("safety")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{							
								qry3 = "update safety a inner join ("+qryString+") b on a.safety_id=b.safety_id COLLATE utf8mb4_unicode_ci set responsible_person=?";
							}
						}	
						else if(tablename.compareTo("safety_committee_members")==0)
						{
							if(columnname.compareTo("committee_member_name")==0)
							{							
								qry3 = "update safety_committee_members a inner join ("+qryString+") b on a.id=b.id COLLATE utf8mb4_unicode_ci set committee_member_name=?";
							}
						}
						else if(tablename.compareTo("issue")==0)
						{
							if(columnname.compareTo("responsible_person")==0)
							{
								qry3 = "update issue a inner join ("+qryString+") b on a.issue_id=b.issue_id COLLATE utf8mb4_unicode_ci set responsible_person=?";
							}
							else if(columnname.compareTo("escalated_to")==0)
							{
								qry3 = "update issue a inner join ("+qryString+") b on a.issue_id=b.issue_id COLLATE utf8mb4_unicode_ci set escalated_to=?";
							}
						}
						else if(tablename.compareTo("design")==0)
						{
							if(columnname.compareTo("hod")==0)
							{
								qry3 = "update design a inner join ("+qryString+") b on a.design_id=b.design_id COLLATE utf8mb4_unicode_ci set hod=?";
							}
							else if(columnname.compareTo("dy_hod")==0)
							{
								qry3 = "update design a inner join ("+qryString+") b on a.design_id=b.design_id COLLATE utf8mb4_unicode_ci set dy_hod=?";
							}
						}						
						
						
						statement3 = connection.prepareStatement(qry3);
						statement3.setString(1, EmployeeId);
						statement3.executeUpdate();					
					}
					
				}
					
			}catch(Exception e){ 


				
			}finally {
				DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
			}
		return false;
	}

	@Override
	public int getModulesCount() throws Exception
	{
		int count=0;
		try {
			String qry = "select count(distinct(module)) from user_leave_tables";
			count = (int) jdbcTemplate.queryForObject(qry, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return count;
	}

	@Override
	public boolean deleteLeaveResponsibility(User obj) throws Exception {
		boolean flag=false;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String qry = "update user_leave_responsibility set delete_status='Yes' where user_leave_id=? and Employee_id=?";
			statement = connection.prepareStatement(qry);
			statement.setLong(1, obj.getUser_leave_id());
			statement.setString(2, obj.getUser_id());
			int c=statement.executeUpdate();
			if(c>0)
			{
				flag=true;
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}

}
