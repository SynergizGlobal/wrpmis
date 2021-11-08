package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ReportsAccessDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.Report;
@Repository
public class ReportsAccessDaoImpl implements ReportsAccessDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Override
	public List<Report> getReportsList(Report obj) throws Exception {
		List<Report>  objsList = null;
		try {
			String qry ="SELECT f1.form_id,f1.module_name_fk,f1.form_name,f1.parent_form_id_sr_fk,f1.web_form_url,f1.mobile_form_url,f1.priority,f1.soft_delete_status_fk,"
					+ "f2.form_name as folder_name,f1.display_in_mobile, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_role_access, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_type_access, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_access "
					+ "FROM report_form f1 "
					+ "LEFT OUTER JOIN report_form f2 on f1.parent_form_id_sr_fk = f2.form_id "
					+ "where f1.form_id is not null ";
			
			int arrSize = 3;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and f1.module_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and f1.soft_delete_status_fk = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "user_role";
			pValues[i++] = "user_type";
			pValues[i++] = "user";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Report>(Report.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Report> getModulesFilterListInReport(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "SELECT module_name_fk from report_form  " + 
					"where module_name_fk is not null and module_name_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++; 
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY module_name_fk ORDER BY module_name_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Report>(Report.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Report> getStatusFilterListInReport(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "SELECT soft_delete_status_fk from report_form  " + 
					"where soft_delete_status_fk is not null and soft_delete_status_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + " and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY soft_delete_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
				pValues[i++] = obj.getModule_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Report>(Report.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Report> getModulesListForReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "select module_name as module_name_fk from module ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<Report> getFolderssListForReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "SELECT form_id,form_name FROM report_form ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<Report> getStatusListForReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "select soft_delete_status as soft_delete_status_fk from soft_delete_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public Report getReport(Report obj) throws Exception {
		Report dObj = null;
		try {
			String qry ="SELECT f1.form_id,f1.module_name_fk,f1.form_name,f1.parent_form_id_sr_fk,f1.web_form_url,f1.mobile_form_url,f1.priority,f1.soft_delete_status_fk,"
					+ "f2.form_name as folder_name,f1.display_in_mobile, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_role_access, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_type_access, "
					+ "(select group_concat(access_value) from report_access where form_id_fk = f1.form_id and access_type = ?) as user_access "
					+ "FROM report_form f1 " 
					+ "LEFT OUTER JOIN report_form f2 on f1.parent_form_id_sr_fk = f2.form_id "
					+ "where f1.form_id = ?" ;
			
			int arrSize = 4;

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "user_role";
			pValues[i++] = "user_type";
			pValues[i++] = "user";
			pValues[i++] = obj.getForm_id();
			
			dObj = (Report)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Report>(Report.class));
			
			/*if(!StringUtils.isEmpty(dObj) && !StringUtils.isEmpty(dObj.getForm_id())) {
				List<Report> objsList = null;
				String qryUserPermission = "select access_type,access_value from report_access where form_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryUserPermission, new Object[] {dObj.getForm_id()}, new BeanPropertyRowMapper<Report>(Report.class));	
				
				dObj.setAccessPermissions(objsList);
			}*/
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return dObj;
	}

	@Override
	public boolean addReport(Report obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String form_id = getformId(con);
			obj.setForm_id(form_id);
			if(StringUtils.isEmpty(obj.getParent_form_id_sr_fk())) {
				obj.setParent_form_id_sr_fk(form_id);
			}
			String insertQry = "INSERT INTO report_form"
					+ "( form_id,form_name,module_name_fk,parent_form_id_sr_fk, web_form_url,mobile_form_url, "
					+ "soft_delete_status_fk,display_in_mobile)"
					+ "VALUES"
					+ "(:form_id,:form_name,:module_name_fk,:parent_form_id_sr_fk,:web_form_url,:mobile_form_url,"
					+ ":soft_delete_status_fk,:display_in_mobile)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
					if(arraySize < obj.getAccess_types().length) {
						arraySize = obj.getAccess_types().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
					if(arraySize < obj.getAccess_values().length) {
						arraySize = obj.getAccess_values().length;
					}
				}
				
				if(arraySize > 0) {				
					
					String[] types = obj.getAccess_types();
					String[] values = obj.getAccess_values();
					
					String qryUserPermissions = "INSERT INTO report_access (form_id_fk,access_type,access_value) VALUES  (?,?,?)";		
					
					int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
				            new BatchPreparedStatementSetter() {
				                 
				                @Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
				                    ps.setString(1, form_id);
				                    ps.setString(2, types.length > 0?types[i]:null);
				                    ps.setString(3, values.length > 0?values[i]:null);			                    
				                }
				                @Override  
				                public int getBatchSize() {				                	
				                	int arraySize = 0;
				    				if(!StringUtils.isEmpty(obj.getAccess_types()) && obj.getAccess_types().length > 0 ) {
				    					obj.setAccess_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_types()));
				    					if(arraySize < obj.getAccess_types().length) {
				    						arraySize = obj.getAccess_types().length;
				    					}
				    				}
				    				if(!StringUtils.isEmpty(obj.getAccess_values()) && obj.getAccess_values().length > 0 ) {
				    					obj.setAccess_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getAccess_values()));
				    					if(arraySize < obj.getAccess_values().length) {
				    						arraySize = obj.getAccess_values().length;
				    					}
				    				}
				    				return arraySize; 
				                }
				            });
					
				}
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	private String getformId(Connection con) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String obj = null;
		try {
		
			String qry = "select (form_id + 1) as form_id from report_form ORDER BY form_id DESC LIMIT 1";
			ps  = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if(rs.next()) {
				obj = rs.getString("form_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, ps, rs);
		}
		return obj;
	}

	@Override
	public boolean updateReport(Report obj) throws Exception {
		boolean flag = false;
		try {
			
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE report_form set "
					+ "priority= :priority,soft_delete_status_fk= :soft_delete_status_fk,display_in_mobile=:display_in_mobile "
					+ "where form_id= :form_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				
				String deleteQry ="delete from report_access where form_id_fk = :form_id ";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				if(!StringUtils.isEmpty(obj.getUser_role_access())) {
					String[] user_role_access = obj.getUser_role_access().split(",");
					int user_role_access_count = user_role_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
					String messageQry = "INSERT INTO report_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_role_access_count; i++) {
						Report msgObj = new Report();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user_role");
						msgObj.setAccess_value(user_role_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_type_access())) {
					String[] user_type_access = obj.getUser_type_access().split(",");
					int user_type_access_count = user_type_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
					String messageQry = "INSERT INTO report_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_type_access_count; i++) {
						Report msgObj = new Report();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user_type");
						msgObj.setAccess_value(user_type_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_access())) {
					String[] user_access = obj.getUser_access().split(",");
					int user_access_count = user_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_access_count];
					String messageQry = "INSERT INTO report_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int i = 0; i < user_access_count; i++) {
						Report msgObj = new Report();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user");
						msgObj.setAccess_value(user_access[i]);
				        source[i] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<Report> getUserRolesInReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "select user_role_name as access_value_id from user_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Report> getUserTypesInReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "select user_type as access_value_id from user_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Report> getUsersInReportAccess(Report obj) throws Exception {
		List<Report> objsList = null;
		try {
			String qry = "select user_id as access_value_id,user_name as access_value_name from user";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Report>(Report.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

}
