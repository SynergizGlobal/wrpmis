package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ModulePermissionDao;
import com.synergizglobal.pmis.model.Form;
import com.synergizglobal.pmis.model.ModulePermission;
@Repository
public class ModulePermissionDaoImpl implements ModulePermissionDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public ModulePermission getModulePermission(ModulePermission obj) throws Exception {
		ModulePermission  mObj = new ModulePermission();
		try {
			
			String qry ="SELECT module_name,incharge_user_id_fk,user_name as incharge_user_name,soft_delete_status_fk "
					+ "FROM module m "
					+ "left join user u on incharge_user_id_fk = user_id "
					+ "where module_name = ?";
			
			
			List<ModulePermission> objsList = jdbcTemplate.query( qry,new Object[]{obj.getModule_name_fk()}, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			for (ModulePermission module : objsList) {
		    	mObj = module;
			}
			
			String urlTypeQry ="SELECT url_type FROM form where module_name_fk = ? group by url_type";
			
			
			List<ModulePermission> urlTypesList = jdbcTemplate.query( urlTypeQry,new Object[]{obj.getModule_name_fk()}, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			mObj.setUrlTypes(urlTypesList);
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUrl_type())) {
				String urls_qry ="SELECT f1.form_id,f1.module_name_fk,f1.form_name,f1.web_form_url as form_url,f1.priority,f1.soft_delete_status_fk, "
						+ "(select group_concat(access_value) from form_access where form_id_fk = f1.form_id and access_type = ?) as user_role_access, "
						+ "(select group_concat(access_value) from form_access where form_id_fk = f1.form_id and access_type = ?) as user_type_access, "
						+ "(select group_concat(access_value) from form_access where form_id_fk = f1.form_id and access_type = ?) as user_access "
						+ "FROM form f1 "
						+ "where f1.web_form_url is not null ";
				
				int arrSize = 3;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name_fk())) {
					urls_qry = urls_qry + " and f1.module_name_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUrl_type())) {
					urls_qry = urls_qry + " and f1.url_type = ?";
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
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUrl_type())) {
					pValues[i++] = obj.getUrl_type();
				}
				List<ModulePermission> accessPermissions = jdbcTemplate.query( urls_qry,pValues, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			    mObj.setAccessPermissions(accessPermissions);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return mObj;
	}

	@Override
	public boolean updateModulePermission(ModulePermission obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			int arraySize = 0;
			if(!StringUtils.isEmpty(obj.getForm_ids()) && obj.getForm_ids().size() > 0) {
				if(arraySize < obj.getForm_ids().size()) {
					arraySize = obj.getForm_ids().size();
				}
			}
			if(!StringUtils.isEmpty(obj.getForm_names()) && obj.getForm_names().size() > 0) {
				if(arraySize < obj.getForm_names().size()) {
					arraySize = obj.getForm_names().size();
				}
			}
			if(!StringUtils.isEmpty(obj.getForm_urls()) && obj.getForm_urls().size() > 0) {
				if(arraySize < obj.getForm_urls().size()) {
					arraySize = obj.getForm_urls().size();
				}
			}
			
			if(arraySize > 0) {		
				String updateQry = "UPDATE form set "
						+ "form_name= :form_name,web_form_url= :form_url "
						+ "where form_id = :form_id";
				
				for (int i = 0; i < arraySize; i++) {
					ModulePermission pObj = new ModulePermission();
					if(!StringUtils.isEmpty(obj.getForm_names()) && obj.getForm_names().size() > 0 && !StringUtils.isEmpty(obj.getForm_names().get(i))) {
						pObj.setForm_name(obj.getForm_names().get(i));
					}
					if(!StringUtils.isEmpty(obj.getForm_urls()) && obj.getForm_urls().size() > 0 && !StringUtils.isEmpty(obj.getForm_urls().get(i))) {
						pObj.setForm_url(obj.getForm_urls().get(i));
					}
					if(!StringUtils.isEmpty(obj.getForm_ids()) && obj.getForm_ids().size() > 0 && !StringUtils.isEmpty(obj.getForm_ids().get(i))) {
						pObj.setForm_id(obj.getForm_ids().get(i));
					}
					/*BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(pObj);	
					int count = namedParamJdbcTemplate.update(updateQry, paramSource);*/		
					int count = 1;
					if(count > 0) {
						flag = true;
						
						String deleteQry ="delete from form_access where form_id_fk = :form_id ";
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(pObj);		 
						count = namedParamJdbcTemplate.update(deleteQry, paramSource);
						
						if(!StringUtils.isEmpty(obj.getAccess_user_roles()) && obj.getAccess_user_roles().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_roles().get(i))) {
							String asccess_user_roles = obj.getAccess_user_roles().get(i);
							if(!StringUtils.isEmpty(asccess_user_roles) && asccess_user_roles.contains("~$~")) {
								asccess_user_roles = asccess_user_roles.replaceAll("[~$~]+" , ",");
						    }

							String[] user_role_access = asccess_user_roles.split(",");
							int user_role_access_count = user_role_access.length;
							SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
							String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
									+ "VALUES" + "(:form_id,:access_type,:access_value)";
							
							for (int j = 0; j < user_role_access_count; j++) {
								Form msgObj = new Form();
								msgObj.setForm_id(pObj.getForm_id());
								msgObj.setAccess_type("user_role");
								msgObj.setAccess_value(user_role_access[j]);
						        source[j] = new BeanPropertySqlParameterSource(msgObj);
						    }
							namedParamJdbcTemplate.batchUpdate(messageQry, source);
						}
						
						if(!StringUtils.isEmpty(obj.getAccess_user_types()) && obj.getAccess_user_types().size() > 0 && !StringUtils.isEmpty(obj.getAccess_user_types().get(i))) {
							String asccess_user_types = obj.getAccess_user_types().get(i);
							if(!StringUtils.isEmpty(asccess_user_types) && asccess_user_types.contains("~$~")) {
								asccess_user_types = asccess_user_types.replaceAll("[~$~]+" , ",");
						    }
							String[] user_type_access = asccess_user_types.split(",");
							int user_type_access_count = user_type_access.length;
							SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
							String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
									+ "VALUES" + "(:form_id,:access_type,:access_value)";
							
							for (int j = 0; j < user_type_access_count; j++) {
								Form msgObj = new Form();
								msgObj.setForm_id(pObj.getForm_id());
								msgObj.setAccess_type("user_type");
								msgObj.setAccess_value(user_type_access[j]);
						        source[j] = new BeanPropertySqlParameterSource(msgObj);
						    }
							namedParamJdbcTemplate.batchUpdate(messageQry, source);
						}
						
						if(!StringUtils.isEmpty(obj.getAccess_users()) && obj.getAccess_users().size() > 0 && !StringUtils.isEmpty(obj.getAccess_users().get(i))) {
							String asccess_users = obj.getAccess_users().get(i);
							if(!StringUtils.isEmpty(asccess_users) && asccess_users.contains("~$~")) {
								asccess_users = asccess_users.replaceAll("[~$~]+" , ",");
						    }
							String[] user_access = asccess_users.split(",");
							int user_access_count = user_access.length;
							SqlParameterSource[] source = new SqlParameterSource[user_access_count];
							String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
									+ "VALUES" + "(:form_id,:access_type,:access_value)";
							
							for (int j = 0; j < user_access_count; j++) {
								Form msgObj = new Form();
								msgObj.setForm_id(pObj.getForm_id());
								msgObj.setAccess_type("user");
								msgObj.setAccess_value(user_access[j]);
						        source[j] = new BeanPropertySqlParameterSource(msgObj);
						    }
							namedParamJdbcTemplate.batchUpdate(messageQry, source);
						}
						
					}
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<ModulePermission> getModulesList(ModulePermission obj) throws Exception {
		List<ModulePermission>  objsList = null;
		try {
			String qry ="SELECT module_name,incharge_user_id_fk,user_name as incharge_user_name,soft_delete_status_fk "
					+ "FROM module m "
					+ "left join user u on incharge_user_id_fk = user_id "
					+ "WHERE module_name IS NOT NULL ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + "AND module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + "AND soft_delete_status_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
					
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	@Override
	public List<ModulePermission> getUserRoles(ModulePermission obj) throws Exception {
		List<ModulePermission> objsList = null;
		try {
			String qry = "select user_role_name as access_value_id from user_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ModulePermission> getUserTypes(ModulePermission obj) throws Exception {
		List<ModulePermission> objsList = null;
		try {
			String qry = "select user_type as access_value_id from user_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ModulePermission> getUsers(ModulePermission obj) throws Exception {
		List<ModulePermission> objsList = null;
		try {
			String qry = "select user_id as access_value_id,user_name as access_value_name from user";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateUrlPermissions(ModulePermission obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String updateQry = "UPDATE form set "
						+ "form_name= :form_name,web_form_url= :form_url "
						+ "where form_id = :form_id";
			/*BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);	*/	
			int count = 1;
			if(count > 0) {
				flag = true;
				
				String deleteQry ="delete from form_access where form_id_fk = :form_id ";
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
				
				if(!StringUtils.isEmpty(obj.getUser_role_access())) {
					String asccess_user_roles = obj.getUser_role_access();
					if(asccess_user_roles.contains("~$~")) {
						asccess_user_roles = asccess_user_roles.replaceAll("[~$~]+" , ",");
				    }

					String[] user_role_access = asccess_user_roles.split(",");
					int user_role_access_count = user_role_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_role_access_count];
					String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_role_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user_role");
						msgObj.setAccess_value(user_role_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_type_access())) {
					String asccess_user_types = obj.getUser_type_access();
					if(!StringUtils.isEmpty(asccess_user_types) && asccess_user_types.contains("~$~")) {
						asccess_user_types = asccess_user_types.replaceAll("[~$~]+" , ",");
				    }
					String[] user_type_access = asccess_user_types.split(",");
					int user_type_access_count = user_type_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_type_access_count];
					String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_type_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user_type");
						msgObj.setAccess_value(user_type_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
				if(!StringUtils.isEmpty(obj.getUser_access())) {
					String asccess_users = obj.getUser_access();
					if(!StringUtils.isEmpty(asccess_users) && asccess_users.contains("~$~")) {
						asccess_users = asccess_users.replaceAll("[~$~]+" , ",");
				    }
					String[] user_access = asccess_users.split(",");
					int user_access_count = user_access.length;
					SqlParameterSource[] source = new SqlParameterSource[user_access_count];
					String messageQry = "INSERT INTO form_access (form_id_fk,access_type,access_value)"
							+ "VALUES" + "(:form_id,:access_type,:access_value)";
					
					for (int j = 0; j < user_access_count; j++) {
						Form msgObj = new Form();
						msgObj.setForm_id(obj.getForm_id());
						msgObj.setAccess_type("user");
						msgObj.setAccess_value(user_access[j]);
				        source[j] = new BeanPropertySqlParameterSource(msgObj);
				    }
					namedParamJdbcTemplate.batchUpdate(messageQry, source);
				}
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<ModulePermission> getModulesForFilter(ModulePermission obj) throws Exception {
		List<ModulePermission>  objsList = null;
		try {
			String qry = "SELECT module_name,incharge_user_id_fk,user_name as incharge_user_name,soft_delete_status_fk "
					+ "FROM module m "
					+ "left join user u on incharge_user_id_fk = user_id "
					+ "WHERE module_name IS NOT NULL ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + "AND module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + "AND soft_delete_status_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
			
		    objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ModulePermission> getModuleStatusListForFilter(ModulePermission obj) throws Exception {
		List<ModulePermission>  objsList = null;
		try {
			String qry ="SELECT module_name,incharge_user_id_fk,user_name as incharge_user_name,soft_delete_status_fk "
					+ "FROM module m "
					+ "left join user u on incharge_user_id_fk = user_id "
					+ "WHERE soft_delete_status_fk IS NOT NULL ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				qry = qry + "AND module_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				qry = qry + "AND soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY soft_delete_status_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_name())) {
				pValues[i++] = obj.getModule_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSoft_delete_status_fk())) {
				pValues[i++] = obj.getSoft_delete_status_fk();
			}
					
		    objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ModulePermission>(ModulePermission.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}	
	
}
