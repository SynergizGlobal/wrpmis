package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LoginDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.User;


@Repository
public class LoginDaoImpl implements LoginDao{
	
	Logger logger = Logger.getLogger(LoginDao.class);
	
	@Autowired
	DataSource dataSource;

	/**
	 * This method validate the user.
	 * @param user is object for the model class User
	 * @return type of this method is userDetails
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public User validateUser(User user) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User userDetails = null;
		try{  
			con = dataSource.getConnection();
			
			String qry = "select user_id,user_name,password,email_id,employee_type_fk,department_fk,designation,reporting_to_id_srfk,user_role_name_fk,secondary_email_id,mobile_number,alternate_mobile_number,landline,extension,pmis_key_fk,remarks "
					+ "from user "
					+ "where password = BINARY ? and user_id = BINARY ?";
			
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getUser_id());
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				userDetails = new User();
				
				userDetails.setUser_id(rs.getString("user_id"));
				userDetails.setUser_name(rs.getString("user_name"));
				userDetails.setPassword(rs.getString("password"));
				if(!StringUtils.isEmpty(rs.getString("pmis_key_fk"))) {
					userDetails.setPmis_key_fk(rs.getString("pmis_key_fk"));
					addUserLogin(userDetails.getUser_id());
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userDetails;
	}
	
	@Override
	public boolean addUserLogin(String user_id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			
			String qry = "select number_of_logins from user_login where user_id_fk = ?";
			stmt = con.prepareStatement(qry); 
			stmt.setString(1, user_id);
			rs = stmt.executeQuery();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String current = formatter.format(date);
			
			if(rs.next()) {				
				String updateQry = "UPDATE user_login set last_login = ?,number_of_logins = ? WHERE user_id_fk = ?";
				stmt = con.prepareStatement(updateQry);
				int count = rs.getInt("number_of_logins");
				
				stmt.setString(1,current );
				stmt.setInt(2, ++count);
				stmt.setString(3,user_id );
				
				int c = stmt.executeUpdate();
				if (c > 0) {
					flag = true;					
				}				
			} else {
				String insertQry = "INSERT INTO user_login (user_id_fk,last_login,last_login_application_fk,number_of_logins)"  
						+ " VALUES (?,?,?,?)";
				stmt = con.prepareStatement(insertQry);
				
				stmt.setString(1,user_id);
				stmt.setString(2,current);
				stmt.setString(3,null);
				stmt.setInt(4,1);
				int c = stmt.executeUpdate();
				if (c > 0) {
					flag = true;
					
				}					
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
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
		String currentExpiryDays = null;
		try{  
			con = dataSource.getConnection();
			String qry = "SELECT user_id,password,password_expiry_days FROM user WHERE user_id = ? and password = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1,user.getUser_id());
			stmt.setString(2,user.getOldPassword());
			rs = stmt.executeQuery();  
			if(rs.next()) {
				currentExpiryDays = rs.getString("password_expiry_days");
				flag = true;
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			
			int expiryDays = 0;
			if(!StringUtils.isEmpty(currentExpiryDays)){
				expiryDays = Integer.parseInt(currentExpiryDays);
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String current = formatter.format(date);
			   
			SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
			Calendar cal = Calendar.getInstance();    
			cal.setTime( dateFormat.parse(current));    
			cal.add( Calendar.DATE, expiryDays );    
			String convertedDate=dateFormat.format(cal.getTime());
			
			if(flag){
				String qry2 = "UPDATE user set password_update_date = ?,password = ? WHERE user_id = ?";
				stmt = con.prepareStatement(qry2);
				stmt.setString(1, convertedDate);
				stmt.setString(2, user.getNewPassword());
				stmt.setString(3, user.getUser_id());
				int c = stmt.executeUpdate();  
				if(c > 0) {
					temp = "true";
				}
			}else{
				temp = "false";
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return temp;
	}

}



