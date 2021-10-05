package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LoginDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.exceptions.NoKeyException;
import com.synergizglobal.pmis.model.User;


@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	DataSource dataSource;
	
	@Value("${no.pmis.form.Key}")
	public String noKeyAssigned;

	/**
	 * This method validate the user.
	 * @param user is object for the model class User
	 * @return type of this method is userDetails
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public User validateUser(User user) throws SQLException,NoKeyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User userDetails = null;
		try{  
			con = dataSource.getConnection();
			
			String qry = "select user_id,user_name,password,designation,email_id,cast(mobile_number as CHAR) as mobile_number,cast(landline as CHAR) as landline,"
					+ "cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks,user_image,"
					+ "user_role_code,user_type_fk,single_login_session_id "
					+ "from user u "
					+ "LEFT JOIN user_role ur ON user_role_name_fk = user_role_name "
					+ "where password = BINARY ? and (user_id = ? OR mobile_number = ? OR email_id = ?)";
			
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getUser_id());
			stmt.setString(3, user.getUser_id());
			stmt.setString(4, user.getUser_id());
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				userDetails = new User();
				
				userDetails.setUser_id(rs.getString("user_id"));
				userDetails.setUser_name(rs.getString("user_name"));
				userDetails.setPassword(rs.getString("password"));
				userDetails.setDesignation(rs.getString("designation"));
				userDetails.setEmail_id(rs.getString("email_id"));
				userDetails.setMobile_number(rs.getString("mobile_number"));
				userDetails.setLandline(rs.getString("landline"));
				userDetails.setExtension(rs.getString("extension"));
				userDetails.setDepartment_fk(rs.getString("department_fk"));
				userDetails.setReporting_to_id_srfk(rs.getString("reporting_to_id_srfk"));
				userDetails.setPmis_key_fk(rs.getString("pmis_key_fk"));
				userDetails.setUser_role_name_fk(rs.getString("user_role_name_fk"));
				userDetails.setUser_role_code(rs.getString("user_role_code"));
				userDetails.setRemarks(rs.getString("remarks"));
				userDetails.setUser_image(rs.getString("user_image"));
				userDetails.setUser_type_fk(rs.getString("user_type_fk"));
				userDetails.setSingle_login_session_id(rs.getString("single_login_session_id"));
				
				if(!StringUtils.isEmpty(rs.getString("pmis_key_fk"))) {
					userDetails.setSystem_ipa(user.getSystem_ipa());
					userDetails.setPublic_ipa(user.getPublic_ipa());
					
					String user_login_details_id = addUserLoginDetails(userDetails,con);
					userDetails.setUser_login_details_id(user_login_details_id);
				}else {
					throw new NoKeyException(noKeyAssigned);
				}
			}
		}catch(Exception e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userDetails;
	}
	
	public String addUserLoginDetails(User uObj, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_login_details_id = null;
		try {			
			String insertQry = "INSERT INTO user_login_details (user_id_fk,login_date_time,last_active_date_time,system_ipa,public_ipa)"  
					+ " VALUES (?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)";
			stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			int p = 1;
			stmt.setString(p++,uObj.getUser_id());
			stmt.setString(p++,uObj.getSystem_ipa());
			stmt.setString(p++,uObj.getPublic_ipa());
			
			int c = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (c > 0) {
				if(rs.next()) {
					user_login_details_id = String.valueOf(rs.getLong(1));
				}		
			}
			
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return user_login_details_id;
	}
	
	/**
	 * This method change the password
	 * @param user is object for the model class User 
	 * @return type of this method is temp that is string type
	 * @throws Exception will raise an exception when abnormal termination occur.
	 */
	@Override
	public String changePassword(User user) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String temp = null;
		boolean flag = false;
		try{  
			con = dataSource.getConnection();
			String qry = "SELECT user_id,password FROM user WHERE user_id = ? and password = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,user.getUser_id());
			stmt.setString(2,user.getOldPassword());
			rs = stmt.executeQuery();  
			if(rs.next()) {
				flag = true;
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			if(flag){
				String qry2 = "UPDATE user set password = ? WHERE user_id = ?";
				stmt = con.prepareStatement(qry2);
				stmt.setString(1, user.getNewPassword());
				stmt.setString(2, user.getUser_id());
				int c = stmt.executeUpdate();  
				if(c > 0) {
					temp = "true";
				}
			}else{
				temp = "false";
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return temp;
	}
	
	
	
	@Override
	public String resetPassword(User user) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String temp = null;
		try{  
				con = dataSource.getConnection();

				String qry2 = "UPDATE user set password = ? WHERE user_id = ?";
				
				stmt = con.prepareStatement(qry2);
				stmt.setString(1, user.getNewPassword());
				stmt.setString(2, user.getUser_id());
				
				
				int c = stmt.executeUpdate();  
				if(c > 0) {
					temp = "true";
				}

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return temp;
	}
	

	@Override
	public boolean addUserLogoutDateTime(User uObj) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {			
			con = dataSource.getConnection();
			String insertQry = "UPDATE user_login_details SET logout_date_time = CURRENT_TIMESTAMP,logout_type_fk = ?"  
					+ " WHERE user_id_fk = ? and user_login_id = ?";
			stmt = con.prepareStatement(insertQry);
			int p = 1;
			stmt.setString(p++,CommonConstants2.LOGOUT_TYPE_LOGOUT);
			stmt.setString(p++,uObj.getUser_id());
			stmt.setString(p++,uObj.getUser_login_details_id());
			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag = true;				
			}
			
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}

	@Override
	public boolean updateSingleLoginSessionId(String single_login_session_id, String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{  
			con = dataSource.getConnection();
			
			String updateQry = "UPDATE user set single_login_session_id = ? WHERE user_id = ?";
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, single_login_session_id);
			stmt.setString(2, user_id);			
			int c = stmt.executeUpdate(); 
			if(c > 0) {		
				flag = true;				
			}
		}catch(Exception e){ 
			throw new SQLException(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}

	@Override
	public boolean logoutFromAllDevices(User obj) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{  
			con = dataSource.getConnection();
			
			String updateQry = "UPDATE user set single_login_session_id = ? WHERE user_id = ?";
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, null);
			stmt.setString(2, obj.getUser_id());	
			int c = stmt.executeUpdate(); 
			if(c > 0) {		
				flag = true;				
			}
		}catch(Exception e){ 
			throw new SQLException(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return flag;
	}

	@Override
	public String getSingleLoginSessionId(User obj) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String single_login_session_id = null;
		try{  
			con = dataSource.getConnection();
			
			String updateQry = "select single_login_session_id from user WHERE user_id = ?";
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, obj.getUser_id());	
			rs = stmt.executeQuery(); 
			if(rs.next()) {		
				single_login_session_id = rs.getString("single_login_session_id");				
			}
		}catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return single_login_session_id;
	}
	
	@Override
	public boolean checkUserName(String UserName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean process = false;
		try{  
			con = dataSource.getConnection();
			
			String updateQry = "select user_name from user WHERE user_name = ?";
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, UserName);	
			rs = stmt.executeQuery(); 
			if(rs.next()) {		
				process=true;	
			}
		}catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return process;
	}
	
	@Override
	public boolean checkUserEmail(String Email) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean process = false;
		try{  
			con = dataSource.getConnection();
			
			String updateQry = "select email_id from user WHERE email_id = ?";
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, Email);	
			rs = stmt.executeQuery(); 
			if(rs.next()) {		
				process=true;			
			}
		}catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return process;
	}	

}



