package com.synergizglobal.wrpmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.synergizglobal.wrpmis.common.CommonMethods;
import com.synergizglobal.wrpmis.common.DBConnectionHandler;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.Idao.ContractorDao;
import com.synergizglobal.wrpmis.Idao.FormsHistoryDao;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.Contractor;
import com.synergizglobal.wrpmis.model.FormHistory;
import com.synergizglobal.wrpmis.model.User;

@Repository
public class ContractorDaoImpl implements ContractorDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Contractor> getContractorsList(Contractor obj) throws Exception {
		List<Contractor> objsList = null;
		try {
			String qry ="SELECT contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, email_id, pan_number, gst_number, bank_name, account_number, ifsc_code, remarks FROM contractor where 0=0 ";
			int arrSize = 0;

 			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSearchStr())) {
				qry = qry + " and (contractor_id like ? or contractor_name like ? or pan_number like ? or contractor_specilization_fk like ?"
						+ " or address like ? or primary_contact_name like ? or phone_number like ? or email_id like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
 			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj.getSearchStr())) {
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
			} 			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contractor>(Contractor.class));
			
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Contractor> getContractorSpecialization()throws Exception{
		List<Contractor> objsList = null;
		try {
			String qry = "select contractor_specialization as contractor_specilization_fk from contractor_specialization";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contractor>(Contractor.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public Contractor getContractor(Contractor obj)throws Exception{
		Contractor contractor = null;
		try {
			String qry = "SELECT contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, "
					+ "email_id, pan_number, gst_number, bank_name, account_number, ifsc_code,bank_address, remarks from contractor where contractor_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				qry = qry + " and contractor_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id())) {
				pValues[i++] = obj.getContractor_id();
			}
			contractor = (Contractor)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Contractor>(Contractor.class));	
				
		}catch(Exception e) {
			throw new Exception(e);
		}
		return contractor;
	}
	
	@Override
	public boolean addContractor(Contractor obj)throws Exception{
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String contractor_id = getContractorId();
			obj.setContractor_id(contractor_id);
			String pan_no = obj.getPan_number().toUpperCase();
			obj.setPan_number(pan_no);
			String insertQry = "INSERT INTO contractor"
					+ "(contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, "
					+ "email_id, pan_number, gst_number, bank_name, account_number, ifsc_code,bank_address, remarks)"
					+ "VALUES"
					+ "(:contractor_id,:contractor_name,:contractor_specilization_fk,:address,:primary_contact_name,:phone_number,"
					+ ":email_id,:pan_number,:gst_number,:bank_name,:account_number,:ifsc_code,:bank_address,:remarks)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
	            User userObj = new User();
	            userObj.setUser_name(obj.getContractor_name());
	            userObj.setDesignation("Contractor");
	            userObj.setEmail_id(obj.getEmail_id());
	            userObj.setMobile_number(obj.getPhone_number());
	            userObj.setPassword("YENE/LTM8RDMDc08qrSEVQ=="); 
	            userObj.setUser_role_code("CO"); 
	            userObj.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
	            userObj.setUser_role_name_fk("Contractor");
	            userObj.setUser_type_fk("Contractor");
	            userObj.setDepartment_fk("CON");
	            
	            userObj.setIs_password_encrypted("true");
	            userObj.setIs_test_env_enabled("true");            

	            String newUserId = addUser(userObj);
	            System.out.println("New Contractor User ID: " + newUserId);

	            FormHistory formHistory = new FormHistory();
	            formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
	            formHistory.setUser(obj.getDesignation() + " - " + obj.getUser_name());
	            formHistory.setModule_name_fk("Contracts");
	            formHistory.setForm_name("Add Contractor");
	            formHistory.setForm_action_type("Add");
	            formHistory.setForm_details("New Contractor " + obj.getContractor_id() + " Created");

	            boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	public String addUser(User obj) throws Exception {
		String userId = null;
		try {
			String user_id = getMaxUserId(obj.getUser_role_code());
			obj.setUser_id(user_id);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO [user]"
					+ "(user_id,user_name,password,designation,email_id,mobile_number,personal_contact_number,landline,extension,department_fk,reporting_to_id_srfk,user_type_fk,pmis_key_fk,user_role_name_fk,remarks,user_image,is_password_encrypted,is_test_env_enabled) "
					+ "VALUES "
					+ "(:user_id,:user_name,:password,:designation,:email_id,:mobile_number,:personal_contact_number,:landline,:extension,:department_fk,:reporting_to_id_srfk,:user_type_fk,:pmis_key_fk,:user_role_name_fk,:remarks,:user_image,'true','true')";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				userId = user_id;
				obj.setUser_id(userId);
				List<User> module =  getModuleSList(obj);
				String qryM = "INSERT INTO user_module  (soft_delete_status,executive_id_fk,module_fk  )VALUES (:soft_delete_status, :executive_id_fk ,:module_fk)" ;
				for(User uObj : module) {
					obj.setModule_fk(uObj.getModule_name());
					obj.setExecutive_id_fk(userId);
					obj.setSoft_delete_status(CommonConstants.INACTIVE);
					
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					namedParamJdbcTemplate.update(qryM, paramSource);
				}
				String [] permissions = obj.getPermissions_check();
				if (permissions != null && permissions.length > 0) {
					String qry2 = "INSERT INTO user_module  (soft_delete_status,executive_id_fk,module_fk  )VALUES (:soft_delete_status, :executive_id_fk ,:module_fk)" ;
					for(int k = 0; k <= (permissions.length - 1); k++) {
							String[] permissionVal = permissions[k].split("_");
							obj.setModule_fk(permissionVal[0]);
							obj.setExecutive_id_fk(userId);
							obj.setSoft_delete_status(CommonConstants.ACTIVE);
							
							paramSource = new BeanPropertySqlParameterSource(obj);		 
							namedParamJdbcTemplate.update(qry2, paramSource);
						
						if(!StringUtils.isEmpty(permissions[k]) && permissions[k].contains("Contracts")) {
							int c = 0;
							String contarctIds = obj.getContract_id();
							obj.setDepartment_id_fk(obj.getDepartment_fk());
							String qry1 = "INSERT INTO contract_executive"
									+ "(contract_id_fk,department_id_fk,executive_user_id_fk) VALUES (:contract_id_fk,:department_id_fk,:executive_user_id_fk)";	
							obj.setExecutive_user_id_fk(userId); 
							if(!StringUtils.isEmpty(contarctIds)) {
						
								
								if(contarctIds.contains(",")) {
									String [] values = contarctIds.split(",");
									int arrSz = values.length;
									for(int i = 0; i < arrSz; i++) {
										obj.setContract_id_fk(values[i]);
										paramSource = new BeanPropertySqlParameterSource(obj);		 
										c = namedParamJdbcTemplate.update(qry1, paramSource);
									}
								}else {
									obj.setContract_id_fk(contarctIds);
									paramSource = new BeanPropertySqlParameterSource(obj);		 
									c = namedParamJdbcTemplate.update(qry1, paramSource);
								}
							}
						}
						if(!StringUtils.isEmpty(permissions[k]) && permissions[k].contains("Land Acquisition")) {
							int c = 0;
							String workIds = obj.getLand_work();
							String qry1 = "INSERT INTO land_executives"
									+ "(project_id_fk,executive_user_id_fk) VALUES (:project_id_fk,:executive_user_id_fk)";	
							obj.setExecutive_user_id_fk(userId); 
							if(!StringUtils.isEmpty(workIds)) {
						
								
								if(workIds.contains(",")) {
									String [] values = workIds.split(",");
									int arrSz = values.length;
									for(int i = 0; i < arrSz; i++) {
										obj.setProject_id_fk(values[i]);
										paramSource = new BeanPropertySqlParameterSource(obj);		 
										c = namedParamJdbcTemplate.update(qry1, paramSource);
									}
								}else {
									obj.setProject_id_fk(workIds);
									paramSource = new BeanPropertySqlParameterSource(obj);		 
									c = namedParamJdbcTemplate.update(qry1, paramSource);
								}
							}
						}
						if(!StringUtils.isEmpty(permissions[k]) && permissions[k].contains("Utility Shifting")) {
							int c = 0;
							String workIds = obj.getUs_work();
							String qry1 = "INSERT INTO utility_shifting_executives"
									+ "(project_id_fk,executive_user_id_fk) VALUES (:project_id_fk,:executive_user_id_fk)";	
							obj.setExecutive_user_id_fk(userId); 
							if(!StringUtils.isEmpty(workIds)) {
						
								if(workIds.contains(",")) {
									String [] values = workIds.split(",");
									int arrSz = values.length;
									for(int i = 0; i < arrSz; i++) {
										obj.setProject_id_fk(values[i]);
										paramSource = new BeanPropertySqlParameterSource(obj);		 
										c = namedParamJdbcTemplate.update(qry1, paramSource);
									}
								}else {
									obj.setProject_id_fk(workIds);
									paramSource = new BeanPropertySqlParameterSource(obj);		 
									c = namedParamJdbcTemplate.update(qry1, paramSource);
								}
							}
						
						 }
							if(!StringUtils.isEmpty(permissions[k]) && permissions[k].contains("R & R")) {
								int c = 0;
								String workIds = obj.getRr_work();
								String qry1 = "INSERT INTO rr_executives"
										+ "(project_id_fk,executive_user_id_fk) VALUES (:project_id_fk,:executive_user_id_fk)";	
								obj.setExecutive_user_id_fk(userId); 
								if(!StringUtils.isEmpty(workIds)) {
									if(workIds.contains(",")) {
										String [] values = workIds.split(",");
										int arrSz = values.length;
										for(int i = 0; i < arrSz; i++) {
											obj.setProject_id_fk(values[i]);
											paramSource = new BeanPropertySqlParameterSource(obj);		 
											c = namedParamJdbcTemplate.update(qry1, paramSource);
										}
									}else {
										obj.setProject_id_fk(workIds);
										paramSource = new BeanPropertySqlParameterSource(obj);		 
										c = namedParamJdbcTemplate.update(qry1, paramSource);
									}
								
							   }
						  }
							obj.setResponsible_people_id_fk(userId);
							if(!StringUtils.isEmpty(permissions[k]) && permissions[k].contains("Execution &  Monitoring")) {
								int c = 0;
								String qry1 = "INSERT INTO structure_contract_responsible_people"
										+ "( structure_id_fk, contract_id_fk, responsible_people_id_fk) VALUES (:structure_id_fk,:contract_id_fk,:responsible_people_id_fk)";	
								
								int len = obj.getContract_ids().length;
								
								int size = 0;
								if(!StringUtils.isEmpty(obj.getContract_ids()) && obj.getContract_ids().length > 0) {
			    					obj.setContract_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getContract_ids()));
			    					if(size < obj.getContract_ids().length) {
			    						size = obj.getContract_ids().length;
			    					}
			    				}
								if(size == 1 ) {
						    		String joined = String.join(",", obj.getStructures());
						    		String[] strArray = new String[] {joined};
						    		obj.setStructures(strArray);
						    	}
								if(!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
			    					obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
			    					if(size < obj.getStructures().length) {
			    						size = obj.getStructures().length;
			    					}
			    				}
								for (int i = 0; i < size; i++){
									List<String> executives = null;
									if(!StringUtils.isEmpty(obj.getStructures()[i])){
										if(obj.getStructures()[i].contains(",")) {
											executives = new ArrayList<String>(Arrays.asList(obj.getStructures()[i].split(",")));
										}else {
											executives = new ArrayList<String>(Arrays.asList(obj.getStructures()[i]));
										}
										for(String eObj : executives) {
											if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getContract_ids()[i]) &&  !StringUtils.isEmpty(eObj)) {
												User filesObj = new User();
												filesObj.setResponsible_people_id_fk(obj.getResponsible_people_id_fk());
												filesObj.setContract_id_fk(obj.getContract_ids()[i]);
												filesObj.setStructure_id_fk(eObj);
												paramSource = new BeanPropertySqlParameterSource(filesObj);
												namedParamJdbcTemplate.update(qry1, paramSource);
											}
										}
									}
								}
						  }
					 }
				}
			}
			String[] permissions = obj.getPermissions_check();

			if (permissions != null && permissions.length > 0) {
			    String qryU = "UPDATE user_module SET soft_delete_status = :soft_delete_status" +
			                  " WHERE executive_id_fk = :executive_id_fk AND module_fk = :module_fk";

			    for (String permission : permissions) {
			        if (permission != null && permission.contains("Active")) {
			            String[] permissionVal = permission.split("_");
			            if (permissionVal.length > 0) {
			                obj.setModule_fk(permissionVal[0]);
			                obj.setExecutive_id_fk(userId);
			                obj.setSoft_delete_status(CommonConstants.ACTIVE);

			                paramSource = new BeanPropertySqlParameterSource(obj);
			                namedParamJdbcTemplate.update(qryU, paramSource);
			            }
			        }
			    }
			} else {
			    // Optional: log or handle the case where no permissions are found
			    System.out.println("No permissions to process for user: " + userId);
			}

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return userId;
	}
	
	public String getMaxUserId(String role_code) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String user_id = null;

	    try {
	        conn = dataSource.getConnection();

	        String sql =
	            "SELECT 'PMIS_' + ? + '_' + " +
	            "RIGHT('000' + CAST(MAX(CAST(PARSENAME(REPLACE(user_id, '_', '.'), 1) AS INT)) + 1 AS VARCHAR(3)), 3) " +
	            "AS maxId " +
	            "FROM [user] " +
	            "WHERE user_id LIKE 'PMIS_' + ? + '_%'";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, role_code);
	        stmt.setString(2, role_code);

	        rs = stmt.executeQuery();

	        if (rs.next() && rs.getString("maxId") != null) {
	            user_id = rs.getString("maxId");
	        } else {
	            user_id = "PMIS_" + role_code + "_001";
	        }

	    } finally {
	        DBConnectionHandler.closeJDBCResoucrs(conn, stmt, rs);
	    }

	    return user_id;
	}
	
	
	private int getUserCount(String role_code) throws Exception{
		int cnt=0;
		try {
			String qry = "SELECT count(*) FROM [user] WHERE user_id LIKE 'PMIS_"+role_code+"_%'";
			cnt = (int) jdbcTemplate.queryForObject(qry, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return cnt;
	}	
	
	public List<User> getModuleSList(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select distinct module_name from module";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

	private String getContractorId() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contractorId = null;;
		try{
			con = dataSource.getConnection();
			String maxIdQry = "SELECT 'CON' + RIGHT('0000' + CAST(ISNULL(MAX(CAST(SUBSTRING(contractor_id, 4, LEN(contractor_id)) AS INT)), 0) + 1 AS VARCHAR), 4) AS maxId FROM contractor ";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contractorId = rs.getString("maxId");
				if(StringUtils.isEmpty(contractorId)) {
					contractorId = "CON0001"; 
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return contractorId;
	}

	@Override
	public boolean updateContractor(Contractor obj) throws Exception {
		boolean flag = false;
		try {
			String pan_no = obj.getPan_number().toUpperCase();
			obj.setPan_number(pan_no);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE contractor set "
					+ "contractor_name= :contractor_name, contractor_specilization_fk= :contractor_specilization_fk, address= :address,"
					+ "primary_contact_name= :primary_contact_name, phone_number= :phone_number, email_id= :email_id,pan_number= :pan_number, gst_number= :gst_number, "
					+ "bank_name= :bank_name, account_number = :account_number, ifsc_code= :ifsc_code,bank_address= :bank_address, remarks= :remarks "
					+ "where contractor_id= :contractor_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Contracts");
				formHistory.setForm_name("Update Contractor ");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Contractor  "+obj.getContractor_id() + " Updated");
				//formHistory.setWork_id_fk(obj.getWork_id_fk());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public boolean deleteContractorRow(Contractor obj)throws Exception{
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM contractor where contractor_id= :contractor_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<Contractor> getPanNumberList(Contractor obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 List<Contractor> panNo = new ArrayList<Contractor>();
		 String pan_no = null;
		try{
			con = dataSource.getConnection();
			String maxIdQry = "SELECT pan_number FROM contractor where pan_number = ?";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1,obj.getPan_number());
			rs = stmt.executeQuery();  
			while(rs.next()) {
				 obj = new Contractor();
				 pan_no = rs.getString("pan_number");
				 obj.setPan_number(rs.getString("pan_number"));
				 panNo.add(obj);
			}
			if (StringUtils.isEmpty(pan_no)){
				panNo = null;
				
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return panNo;
	}

	@Override
	public int getTotalRecords(Contractor obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from contractor Where contractor_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contractor_id like ? or contractor_name like ? or pan_number like ? or contractor_specilization_fk like ?"
						+ " or address like ? or primary_contact_name like ? or phone_number like ? or email_id like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<Contractor> getContractorsList(Contractor obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Contractor> objsList = null;
		try {
			String qry ="SELECT contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, email_id, pan_number, gst_number, bank_name, account_number, ifsc_code, remarks FROM contractor"
					+ " Where contractor_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contractor_id like ? or contractor_name like ? or pan_number like ? or contractor_specilization_fk like ?"
						+ " or address like ? or primary_contact_name like ? or phone_number like ? or email_id like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY contractor_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contractor>(Contractor.class));
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	

}
