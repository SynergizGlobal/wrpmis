package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.reference.Idao.LeftMenueDao;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class LeftMenueDaoImpl implements LeftMenueDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getLeftMenuList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="SELECT dashboard_id,dashboard_name,dashboard_icon,dashboard_url, order, parent_id, dashboard_url, status,source_field_name, "  
					+ "(select STRING_AGG(access_value) from left_menu_access where dashboard_id = l.dashboard_id and access_type = 'user_role') as user_roles, "
					+ "(select STRING_AGG(access_value) from left_menu_access where dashboard_id = l.dashboard_id and access_type = 'user_type') as user_types, "
					+ "(select STRING_AGG(access_value) from left_menu_access where dashboard_id = l.dashboard_id and access_type = 'user') as users, "	
					+ "(select STRING_AGG(archive_date) from left_menu_archive_details where dashboard_id = l.dashboard_id) as archive_dates, "
					+ "(select STRING_AGG(archive_url) from left_menu_archive_details where dashboard_id = l.dashboard_id) as archive_urls "
					+" FROM left_menu l where dashboard_id is not null and show_left_menu = ?";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				qry = qry + " and parent_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				qry = qry + " and status = ? ";
				arrSize++;
			}
			qry = qry + "  order by [order] asc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "Yes";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				pValues[i++] = obj.getParent_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				pValues[i++] = obj.getStatus();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	
	}

	@Override
	public List<TrainingType> getStatusFilterList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry = "SELECT status from left_menu where status is not null and show_left_menu = ? ";

			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				qry = qry + " and parent_id = ? ";
				arrSize++;
			}
			qry = qry + " group by status ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "Yes";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				pValues[i++] = obj.getParent_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TrainingType> getParentFilterList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry = "SELECT parent_id from left_menu where parent_id is not null and show_left_menu = ? ";

			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				qry = qry + " and parent_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				qry = qry + " and status = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY parent_id ORDER BY parent_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "Yes";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getParent_id())) {
				pValues[i++] = obj.getParent_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				pValues[i++] = obj.getStatus();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addLeftMenu(TrainingType obj) throws Exception {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		try {
			connection = dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO left_menu"
					+ "(dashboard_name,order, parent_id,dashboard_url,status,source_field_name) VALUES (?,?,?,?,?,?)";
			
			stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, obj.getDashboard_name());
			stmt.setString(2, obj.getOrder());
			stmt.setString(3, obj.getParent_id());
			stmt.setString(4, obj.getDashboard_url());
			stmt.setString(5, obj.getStatus());
			stmt.setString(6, obj.getSource_field_name());
			stmt.executeUpdate();			 
			 
			rs = stmt.getGeneratedKeys();
			String generatedKey = "0";
			if (rs.next()) {generatedKey = rs.getString(1);flag = true;}	
			
			if(flag==true)
			{
				if(!StringUtils.isEmpty(obj.getAccess_user_roles()) && obj.getAccess_user_roles().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_roles().get(0))) {
					String asccess_user_roles = obj.getAccess_user_roles().get(0);
					if(!StringUtils.isEmpty(asccess_user_roles) && asccess_user_roles.contains("~$~")) {
						asccess_user_roles = asccess_user_roles.replaceAll("[~$~]+" , ",");
				    }

					String[] user_role_access = asccess_user_roles.split(",");
					int user_role_access_count = user_role_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_role_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(generatedKey);
						msgObj.setAccess_type("user_role");
						msgObj.setAccess_value(user_role_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getAccess_user_types()) && obj.getAccess_user_types().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_types().get(0))) {
					String asccess_user_types = obj.getAccess_user_types().get(0);
					if(!StringUtils.isEmpty(asccess_user_types) && asccess_user_types.contains("~$~")) {
						asccess_user_types = asccess_user_types.replaceAll("[~$~]+" , ",");
				    }
					String[] user_type_access = asccess_user_types.split(",");
					int user_type_access_count = user_type_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_type_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(generatedKey);
						msgObj.setAccess_type("user_type");
						msgObj.setAccess_value(user_type_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getAccess_users()) && obj.getAccess_users().size() > 0 && !StringUtils.isEmpty(obj.getAccess_users().get(0))) {
					String asccess_users = obj.getAccess_users().get(0);
					if(!StringUtils.isEmpty(asccess_users) && asccess_users.contains("~$~")) {
						asccess_users = asccess_users.replaceAll("[~$~]+" , ",");
				    }
					String[] user_access = asccess_users.split(",");
					int user_access_count = user_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(generatedKey);
						msgObj.setAccess_type("user");
						msgObj.setAccess_value(user_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				

				if (!StringUtils.isEmpty(obj.getArchive_date()) && obj.getArchive_date().length > 0
						&& !StringUtils.isEmpty(obj.getArchive_url()) && obj.getArchive_url().length > 0) {
					PreparedStatement insertStmt = null;
					String insert_qry = "INSERT into  left_menu_archive_details (dashboard_id, archive_date, archive_url, created_by_user_id_fk, created_date) " + "VALUES (?,?,?,?,CURRENT_TIMESTAMP)";
					insertStmt = connection.prepareStatement(insert_qry, Statement.RETURN_GENERATED_KEYS);

					for (int i = 0; i < obj.getArchive_url().length; i++) 
					{
						int p = 1;
						if (!StringUtils.isEmpty(obj.getArchive_date()[i]) && !StringUtils.isEmpty(obj.getArchive_url()[i])) 
						{
							
							insertStmt.setString(p++, generatedKey);
							String[] Prdate=obj.getArchive_date()[i].split("-");
							
							 String result =Prdate[2]+"-"+Prdate[1]+"-"+Prdate[0];
							
							insertStmt.setString(p++,(obj.getArchive_date().length > 0) ? result : null);
							insertStmt.setString(p++, (obj.getArchive_url().length > 0) ? obj.getArchive_url()[i] : null);
							insertStmt.setString(p++, obj.getCreated_by_user_id_fk());
							insertStmt.addBatch();
							int[] count = insertStmt.executeBatch();
						}
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
	public boolean updateLeftMenu(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		Connection connection = null;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			connection = dataSource.getConnection();			
			List<TrainingType> tablesList = getTablesList(obj);
			List<TrainingType> list = getDataDetails(obj);
			obj.setdList(list);

			String disableQry = "SET foreign_key_checks = 0 ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			namedParamJdbcTemplate.update(disableQry, paramSource);	
			
			String  updatereferenceTableQry = "UPDATE left_menu SET dashboard_name= :value_new,order= :order,"
					+ "parent_id= :parent_id, dashboard_url= :dashboard_url, status= :status,source_field_name = :source_field_name WHERE dashboard_id= :dashboard_id " ;
			paramSource = new BeanPropertySqlParameterSource(obj);		 
			count = namedParamJdbcTemplate.update(updatereferenceTableQry, paramSource);	
			
			for (TrainingType bObj : obj.getdList()) {
				
				String updateTableQry = "UPDATE "+bObj.getTable_name()+" SET "+bObj.getColumn_name()+" =:value_new WHERE "+bObj.getColumn_name()+"= :value_old " ;
				
				 paramSource = new BeanPropertySqlParameterSource(obj);		 
				 namedParamJdbcTemplate.update(updateTableQry, paramSource);	
			}
			String  enableQry =	"SET foreign_key_checks = 1";
			paramSource = new BeanPropertySqlParameterSource(obj);	
			namedParamJdbcTemplate.update(enableQry, paramSource);
			if(count > 0) 
			{
				flag = true;
				String deleteQry ="delete from left_menu_access where dashboard_id = :dashboard_id ";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				if(!StringUtils.isEmpty(obj.getAccess_user_roles()) && obj.getAccess_user_roles().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_roles().get(0))) {
					String asccess_user_roles = obj.getAccess_user_roles().get(0);
					if(!StringUtils.isEmpty(asccess_user_roles) && asccess_user_roles.contains("~$~")) {
						asccess_user_roles = asccess_user_roles.replaceAll("[~$~]+" , ",");
				    }

					String[] user_role_access = asccess_user_roles.split(",");
					int user_role_access_count = user_role_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_role_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getDashboard_id());
						msgObj.setAccess_type("user_role");
						msgObj.setAccess_value(user_role_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getAccess_user_types()) && obj.getAccess_user_types().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_types().get(0))) {
					String asccess_user_types = obj.getAccess_user_types().get(0);
					if(!StringUtils.isEmpty(asccess_user_types) && asccess_user_types.contains("~$~")) {
						asccess_user_types = asccess_user_types.replaceAll("[~$~]+" , ",");
				    }
					String[] user_type_access = asccess_user_types.split(",");
					int user_type_access_count = user_type_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_type_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getDashboard_id());
						msgObj.setAccess_type("user_type");
						msgObj.setAccess_value(user_type_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getAccess_users()) && obj.getAccess_users().size() > 0 && !StringUtils.isEmpty(obj.getAccess_users().get(0))) {
					String asccess_users = obj.getAccess_users().get(0);
					if(!StringUtils.isEmpty(asccess_users) && asccess_users.contains("~$~")) {
						asccess_users = asccess_users.replaceAll("[~$~]+" , ",");
				    }
					String[] user_access = asccess_users.split(",");
					int user_access_count = user_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_access_count];
					String messageQry = "INSERT INTO left_menu_access (dashboard_id,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getDashboard_id());
						msgObj.setAccess_type("user");
						msgObj.setAccess_value(user_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}

				if (!StringUtils.isEmpty(obj.getArchive_date()) && obj.getArchive_date().length > 0
						&& !StringUtils.isEmpty(obj.getArchive_url()) && obj.getArchive_url().length > 0) {
					
					String deletearchiveQry ="delete from left_menu_archive_details where dashboard_id = :dashboard_id ";
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(deletearchiveQry, paramSource);					
					
					PreparedStatement insertStmt = null;
					String insert_qry = "INSERT into  left_menu_archive_details (dashboard_id, archive_date, archive_url, modified_by, modified_date) " + "VALUES (?,?,?,?,CURRENT_TIMESTAMP)";
					insertStmt = connection.prepareStatement(insert_qry, Statement.RETURN_GENERATED_KEYS);

					for (int i = 0; i < obj.getArchive_url().length; i++) 
					{
						int p = 1;
						if (!StringUtils.isEmpty(obj.getArchive_date()[i]) && !StringUtils.isEmpty(obj.getArchive_url()[i])) 
						{
							
							insertStmt.setString(p++, obj.getDashboard_id());
							String[] Prdate=obj.getArchive_date()[i].split("-");
							
							 String result =Prdate[2]+"-"+Prdate[1]+"-"+Prdate[0];
							
							insertStmt.setString(p++,(obj.getArchive_date().length > 0) ? result : null);
							insertStmt.setString(p++, (obj.getArchive_url().length > 0) ? obj.getArchive_url()[i] : null);
							insertStmt.setString(p++, obj.getCreated_by_user_id_fk());
							insertStmt.addBatch();
							int[] count1 = insertStmt.executeBatch();
						}
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
	public boolean deleteLeftMenu(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String deleteQry ="DELETE FROM left_menu WHERE dashboard_id= :dashboard_id";
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
	
	
	private List<TrainingType> getTablesList(TrainingType obj) throws Exception {
		List<TrainingType> tablesList = null;
		try {
			String qry = "SELECT TABLE_NAME as tName,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME " + 
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'left_menu' and TABLE_SCHEMA = 'pmis' group by TABLE_NAME";
			
			tablesList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return tablesList;
	}


	private List<TrainingType> getDataDetails(TrainingType obj) throws Exception {
		List<TrainingType> list = null;
		try {
			String qry = "SELECT TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME " + 
					"FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = 'left_menu' and TABLE_SCHEMA = 'pmis'";
			
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public List<TrainingType> getParentList() throws Exception {
		List<TrainingType> list = null;
		try {
			String qry = "SELECT dashboard_id,dashboard_name from left_menu WHERE parent_id = ? and show_left_menu = ? GROUP BY dashboard_id";
			 list = jdbcTemplate.query( qry,new Object[]{"0","Yes"}, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}

	@Override
	public List<TrainingType> getStatusList() throws Exception {
		List<TrainingType> list = null;
		try {
			String qry = "SELECT status from  left_menu group by status";
			 list = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return list;
	}

}
