package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LoginDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
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
					+ "cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks,user_image,user_role_code "
					+ "from user u "
					+ "LEFT JOIN user_role ur ON user_role_name_fk = user_role_name "
					+ "where password = BINARY ? and (user_id = BINARY ? OR mobile_number = ? OR email_id = ?)";
			
			
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
				
				if(!StringUtils.isEmpty(rs.getString("pmis_key_fk"))) {
					saveUserLoginDetails(userDetails.getUser_id(),con);
				}else {
					throw new NoKeyException(noKeyAssigned);
				}
			}
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userDetails;
	}
	
	
	public boolean saveUserLoginDetails(String user_id, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {			
			String updateQry = "UPDATE user_login set last_login = CURRENT_TIMESTAMP,number_of_logins = number_of_logins + 1 WHERE user_id_fk = ?";
			stmt = con.prepareStatement(updateQry);	
			stmt.setString(1,user_id );
			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag = true;					
			}	
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			if(!flag) {
				String insertQry = "INSERT INTO user_login (user_id_fk,last_login,number_of_logins)"  
						+ " VALUES (?,CURRENT_TIMESTAMP,?)";
				stmt = con.prepareStatement(insertQry);
				
				int s = 1;
				stmt.setString(s++,user_id);
				stmt.setInt(s++,1);
				c = stmt.executeUpdate();
				if (c > 0) {
					flag = true;				
				}
			}
		}catch(SQLException e){ 
			throw new SQLException(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return flag;
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

}



