package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.ContractDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.BankGuarantee;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Insurence;
import com.synergizglobal.pmis.model.User;

@Repository
public class ContractDaoImpl implements ContractDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Contract> contractList(Contract obj)throws Exception{
			List<Contract> objsList = null;
			try {
				String qry ="select w.work_name,dt.department_name,u.designation,dt.department_name,c.work_id_fk,c.contract_id,c.contract_name,c.contract_short_name,c.contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk " + 
							"from contract c left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
							"left join contractor cr on c.contractor_id_fk = cr.contractor_id "
							+"left join user u on c.hod_user_id_fk = u.user_id "
							+"left join department dt on c.department_fk = dt.department " + 
							"where contract_id is not null ";
				
				int arrSize = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and c.department_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c. work_id_fk = ?";
					arrSize++;
				}
				Object[] pValues = new Object[arrSize];
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				
			 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
			}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
			return objsList;
	}

	@Override
	public List<User> setHodList()throws Exception{
		List<User> objsList = null;
		try {
			String qry ="select user_id,user_name,designation from user where designation is not null and designation <>''";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractTypeList()throws Exception{
		List<Contract> objsList = null;
		try {
			String qry ="select contract_type as contract_type_fk from contract_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e.getMessage());
		}
		return objsList;
		
	}
	@Override
	public List<Contract> getInsurenceTypeList()throws Exception{
		List<Contract> objsList = null;
		try {
			String qry ="select insurance_type from insurance_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<BankGuarantee> bankGuarantee()throws Exception{
		List<BankGuarantee> objsList = null;
		try {
			String qry ="select bg_type as bg_type_fk from bg_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<BankGuarantee>(BankGuarantee.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Insurence> insurenceType()throws Exception{
		List<Insurence> objsList = null;
		try {
			String qry ="select insurance_type as insurance_type_fk from insurance_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Insurence>(Insurence.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Contract> getDepartmentList()throws Exception{
		List<Contract> objsList = null;
		try {
			String qry = "select department as department_fk,department_name,contract_id_code from department";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	
	}

	@Override
	public List<Contract> getContractStatusType()throws Exception{
		List<Contract> objsList = null;
		try {
			String qry ="select general_status as contract_status_fk from general_status";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	
	@Override
	public boolean addContract(Contract contract)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		int[] c = {};
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String contract_id = getContractIdByWorkId(contract.getWork_id_fk(),getDepartmentCode(contract.getDepartment_fk(),con),con);
			
			String ContractQry = "INSERT INTO contract "
							+"(contract_id,work_id_fk,department_fk,contract_name,contract_short_name,contractor_id_fk,contract_type_fk,scope_of_contract,hod_user_id_fk,"
							+ "dy_hod_user_id_fk,doc,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,actual_completion_date,completed_cost,date_of_start,"
							+ "estimated_cost,contract_closure_date,completion_certificate_release,final_takeover,final_bill_release,defect_liability_period,"
							+ "retention_money_release,pbg_release,contract_status_fk,bg_required,insurance_required,remarks)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(ContractQry);
			int q = 1;
			stmt.setString(q++,contract_id); 
			stmt.setString(q++,contract.getWork_id_fk()); 
			stmt.setString(q++,contract.getDepartment_fk()); 
			stmt.setString(q++,contract.getContract_name()); 
			stmt.setString(q++,contract.getContract_short_name()); 
			stmt.setString(q++,contract.getContractor_id_fk()); 
			stmt.setString(q++,contract.getContract_type_fk());
			stmt.setString(q++,contract.getScope_of_contract()); 
			stmt.setString(q++,contract.getHod_user_id_fk());
			stmt.setString(q++,contract.getDy_hod_user_id_fk());
			stmt.setString(q++,contract.getDoc());
			stmt.setString(q++,contract.getAwarded_cost());
			stmt.setString(q++,contract.getLoa_letter_number());
			stmt.setString(q++,contract.getLoa_date());
			stmt.setString(q++,contract.getCa_no());
			stmt.setString(q++,contract.getCa_date());
			stmt.setString(q++,contract.getActual_completion_date());
			stmt.setString(q++,contract.getCompleted_cost()); 
			stmt.setString(q++,contract.getDate_of_start()); 
			stmt.setString(q++,contract.getEstimated_cost()); 
			stmt.setString(q++,contract.getContract_closure_date()); 
			stmt.setString(q++,contract.getCompletion_certificate_release()); 
			stmt.setString(q++,contract.getFinal_takeover()); 
			stmt.setString(q++,contract.getFinal_bill_release()); 
			stmt.setString(q++,contract.getDefect_liability_period()); 
			stmt.setString(q++,contract.getRetention_money_release()); 
			stmt.setString(q++,contract.getPbg_release()); 
			stmt.setString(q++,contract.getContract_status_fk()); 
			stmt.setString(q++,contract.getBg_required()); 
			stmt.setString(q++,contract.getBg_required()); 
			stmt.setString(q++,contract.getRemarks()); 

			count = stmt.executeUpdate();
			
			if(count > 0) {
				flag = true;
			}
			if(stmt != null){stmt.close();}
			
			int arraySize = 0;
			if(flag) {
				String BankG_qry = "INSERT into  bank_guarantee (bg_type_fk,issuing_bank,bank_address,"
						+"bg_number,bg_value,valid_upto,remarks,contract_id_fk) "
						+"VALUES (?,?,?,?,?,?,?,?)";
				stmt = con.prepareStatement(BankG_qry);
		
				if(!StringUtils.isEmpty(contract.getBg_type_fks()) && contract.getBg_type_fks().length > 0) {
					contract.setBg_type_fks(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_type_fks()));
					if(arraySize < contract.getBg_type_fks().length) {
						arraySize = contract.getBg_type_fks().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getIssuing_banks()) && contract.getIssuing_banks().length > 0) {
					contract.setIssuing_banks(CommonMethods.replaceEmptyByNullInSringArray(contract.getIssuing_banks()));
					if(arraySize < contract.getIssuing_banks().length) {
						arraySize = contract.getIssuing_banks().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getBank_addresss()) && contract.getBank_addresss().length > 0) {
					contract.setBank_addresss(CommonMethods.replaceEmptyByNullInSringArray(contract.getBank_addresss()));
					if(arraySize < contract.getBank_addresss().length) {
						arraySize = contract.getBank_addresss().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getBg_numbers()) && contract.getBg_numbers().length > 0) {
					contract.setBg_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_numbers()));
					if(arraySize < contract.getBg_numbers().length) {
						arraySize = contract.getBg_numbers().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getBg_values()) && contract.getBg_values().length > 0) {
					contract.setBg_values(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_values()));
					if(arraySize < contract.getBg_values().length) {
						arraySize = contract.getBg_values().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getBg_valid_uptos()) && contract.getBg_valid_uptos().length > 0) {
					contract.setBg_valid_uptos(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_valid_uptos()));
					if(arraySize < contract.getBg_valid_uptos().length) {
						arraySize = contract.getBg_valid_uptos().length;
					}
				}			
				if(!StringUtils.isEmpty(contract.getRemarkss()) && contract.getRemarkss().length > 0) {
					contract.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(contract.getRemarkss()));
					if(arraySize < contract.getRemarkss().length) {
						arraySize = contract.getRemarkss().length;
					}
				}
				
			    for (int i = 0; i < arraySize; i++) {
					int k = 1;
					stmt.setString(k++,(contract.getBg_type_fks().length > 0)?contract.getBg_type_fks()[i]:null);
					stmt.setString(k++,(contract.getIssuing_banks().length > 0)?contract.getIssuing_banks()[i]:null);
					stmt.setString(k++,(contract.getBank_addresss().length > 0)?contract.getBank_addresss()[i]:null);
					stmt.setString(k++,(contract.getBg_numbers().length > 0)?contract.getBg_numbers()[i]:null);
					stmt.setString(k++,(contract.getBg_values().length > 0)?contract.getBg_values()[i]:null);
					stmt.setString(k++,DateParser.parse((contract.getBg_valid_uptos().length > 0)?contract.getBg_valid_uptos()[i]:null));
					stmt.setString(k++,(contract.getRemarkss().length > 0)?contract.getRemarkss()[i]:null);
					stmt.setString(k++,contract.getContract_id());
					stmt.addBatch();
				}
			    c = stmt.executeBatch();
				if(stmt != null){stmt.close();}
				
				String Insurence_qry = "INSERT into  insurance (insurance_type_fk,issuing_agency,agency_address,"
									+"insurance_number,insurance_value,valid_upto,remarks,contract_id_fk) "
									+"VALUES (?,?,?,?,?,?,?,?)";
				stmt = con.prepareStatement(Insurence_qry); 
				arraySize = 0;
				if(!StringUtils.isEmpty(contract.getInsurance_type_fks()) && contract.getInsurance_type_fks().length > 0) {
					contract.setInsurance_type_fks(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_type_fks()));
					if(arraySize < contract.getInsurance_type_fks().length) {
						arraySize = contract.getInsurance_type_fks().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getIssuing_agencys()) && contract.getIssuing_agencys().length > 0) {
					contract.setIssuing_agencys(CommonMethods.replaceEmptyByNullInSringArray(contract.getIssuing_agencys()));
					if(arraySize < contract.getIssuing_agencys().length) {
						arraySize = contract.getIssuing_agencys().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getAgency_addresss()) && contract.getAgency_addresss().length > 0) {
					contract.setAgency_addresss(CommonMethods.replaceEmptyByNullInSringArray(contract.getAgency_addresss()));
					if(arraySize < contract.getAgency_addresss().length) {
						arraySize = contract.getAgency_addresss().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getInsurance_numbers()) && contract.getInsurance_numbers().length > 0) {
					contract.setInsurance_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_numbers()));
					if(arraySize < contract.getInsurance_numbers().length) {
						arraySize = contract.getInsurance_numbers().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getInsurance_values()) && contract.getInsurance_values().length > 0) {
					contract.setInsurance_values(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_values()));
					if(arraySize < contract.getInsurance_values().length) {
						arraySize = contract.getInsurance_values().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getInsurence_valid_uptos()) && contract.getInsurence_valid_uptos().length > 0) {
					contract.setInsurence_valid_uptos(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurence_valid_uptos()));
					if(arraySize < contract.getInsurence_valid_uptos().length) {
						arraySize = contract.getInsurence_valid_uptos().length;
					}
				}			
				if(!StringUtils.isEmpty(contract.getInsurence_remarks()) && contract.getInsurence_remarks().length > 0) {
					contract.setInsurence_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurence_remarks()));
					if(arraySize < contract.getInsurence_remarks().length) {
						arraySize = contract.getInsurence_remarks().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
				    int k = 1;
				    stmt.setString(k++,(contract.getInsurance_type_fks().length > 0)?contract.getInsurance_type_fks()[i]:null);
					stmt.setString(k++,(contract.getIssuing_agencys().length > 0)?contract.getIssuing_agencys()[i]:null);
					stmt.setString(k++,(contract.getAgency_addresss().length > 0)?contract.getAgency_addresss()[i]:null);
					stmt.setString(k++,(contract.getInsurance_numbers().length > 0)?contract.getInsurance_numbers()[i]:null);
					stmt.setString(k++,(contract.getInsurance_values().length > 0)?contract.getInsurance_values()[i]:null);
					stmt.setString(k++,DateParser.parse((contract.getInsurence_valid_uptos().length > 0)?contract.getInsurence_valid_uptos()[i]:null));
					stmt.setString(k++,(contract.getInsurence_remarks().length > 0)?contract.getInsurence_remarks()[i]:null);
					stmt.setString(k++,contract.getContract_id());
					stmt.addBatch();
				}
				c = stmt.executeBatch();
				if(stmt != null){stmt.close();}
				
				String Milestone_qry = "INSERT into  contract_milestones (milestone_name,milestone_date,actual_date,revision,remarks,contract_id_fk) "
									+"VALUES (?,?,?,?,?,?)";
				stmt = con.prepareStatement(Milestone_qry); 
				arraySize = 0; 
				if(!StringUtils.isEmpty(contract.getMilestone_names()) && contract.getMilestone_names().length > 0) {
					contract.setMilestone_names(CommonMethods.replaceEmptyByNullInSringArray(contract.getMilestone_names()));
					if(arraySize < contract.getMilestone_names().length) {
						arraySize = contract.getMilestone_names().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getMilestone_dates()) && contract.getMilestone_dates().length > 0) {
					contract.setMilestone_dates(CommonMethods.replaceEmptyByNullInSringArray(contract.getMilestone_dates()));
					if(arraySize < contract.getMilestone_dates().length) {
						arraySize = contract.getMilestone_dates().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getActual_dates()) && contract.getActual_dates().length > 0) {
					contract.setActual_dates(CommonMethods.replaceEmptyByNullInSringArray(contract.getActual_dates()));
					if(arraySize < contract.getActual_dates().length) {
						arraySize = contract.getActual_dates().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getRevisions()) && contract.getRevisions().length > 0) {
					contract.setRevisions(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevisions()));
					if(arraySize < contract.getRevisions().length) {
						arraySize = contract.getRevisions().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getMile_remarks()) && contract.getMile_remarks().length > 0) {
					contract.setMile_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getMile_remarks()));
					if(arraySize < contract.getMile_remarks().length) {
						arraySize = contract.getMile_remarks().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					 int k = 1;
					    stmt.setString(k++,(contract.getMilestone_names().length > 0)?contract.getMilestone_names()[i]:null);
						stmt.setString(k++,DateParser.parse((contract.getMilestone_dates().length > 0)?contract.getMilestone_dates()[i]:null));
						stmt.setString(k++,DateParser.parse((contract.getActual_dates().length > 0)?contract.getActual_dates()[i]:null));
						stmt.setString(k++,(contract.getRevisions().length > 0)?contract.getRevisions()[i]:null);
						stmt.setString(k++,(contract.getMile_remarks().length > 0)?contract.getMile_remarks()[i]:null);
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
				}
			
				c = stmt.executeBatch();
				if(stmt != null){stmt.close();}
				
				String Revision_qry = "INSERT into  contract_revision (revision_number,revised_amount,revised_doc,remarks,contract_id_fk) "
									  +"VALUES (?,?,?,?,?)";
				stmt = con.prepareStatement(Revision_qry); 
				
				arraySize = 0;
				if(!StringUtils.isEmpty(contract.getRevision_numbers()) && contract.getRevision_numbers().length > 0) {
					contract.setRevision_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevision_numbers()));
					if(arraySize < contract.getRevision_numbers().length) {
						arraySize = contract.getRevision_numbers().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getRevised_amounts()) && contract.getRevised_amounts().length > 0) {
					contract.setRevised_amounts(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevised_amounts()));
					if(arraySize < contract.getRevised_amounts().length) {
						arraySize = contract.getRevised_amounts().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getRevised_docs()) && contract.getRevised_docs().length > 0) {
					contract.setRevised_docs(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevised_docs()));
					if(arraySize < contract.getRevised_docs().length) {
						arraySize = contract.getRevised_docs().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getRevision_remarks()) && contract.getRevision_remarks().length > 0) {
					contract.setRevision_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevision_remarks()));
					if(arraySize < contract.getRevision_remarks().length) {
						arraySize = contract.getRevision_remarks().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					int k = 1;
					stmt.setString(k++,(contract.getRevision_numbers().length > 0)?contract.getRevision_numbers()[i]:null);
					stmt.setString(k++,(contract.getRevised_amounts().length > 0)?contract.getRevised_amounts()[i]:null);
					stmt.setString(k++,DateParser.parse((contract.getRevised_docs().length > 0)?contract.getRevised_docs()[i]:null));								
					stmt.setString(k++,(contract.getRevision_remarks().length > 0)?contract.getRevision_remarks()[i]:null);
					stmt.setString(k++,contract.getContract_id());
					stmt.addBatch();
				}
				c = stmt.executeBatch();
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				String key_personnel_qry = "INSERT into contract_key_personnel (name,mobile_no,email_id,contract_id_fk) "
						  +"VALUES (?,?,?,?)";
				stmt = con.prepareStatement(key_personnel_qry); 
				
				arraySize = 0;
				if(!StringUtils.isEmpty(contract.getContractKeyPersonnelNames()) && contract.getContractKeyPersonnelNames().length > 0) {
					contract.setContractKeyPersonnelNames(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelNames()));
					if(arraySize < contract.getContractKeyPersonnelNames().length) {
						arraySize = contract.getContractKeyPersonnelNames().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getContractKeyPersonnelMobileNos()) && contract.getContractKeyPersonnelMobileNos().length > 0) {
					contract.setContractKeyPersonnelMobileNos(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelMobileNos()));
					if(arraySize < contract.getContractKeyPersonnelMobileNos().length) {
						arraySize = contract.getContractKeyPersonnelMobileNos().length;
					}
				}
				if(!StringUtils.isEmpty(contract.getContractKeyPersonnelEmailIds()) && contract.getContractKeyPersonnelEmailIds().length > 0) {
					contract.setContractKeyPersonnelEmailIds(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelEmailIds()));
					if(arraySize < contract.getContractKeyPersonnelEmailIds().length) {
						arraySize = contract.getContractKeyPersonnelEmailIds().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					int k = 1;
					stmt.setString(k++,(contract.getContractKeyPersonnelNames().length > 0)?contract.getContractKeyPersonnelNames()[i]:null);
					stmt.setString(k++,(contract.getContractKeyPersonnelMobileNos().length > 0)?contract.getContractKeyPersonnelMobileNos()[i]:null);
					stmt.setString(k++,(contract.getContractKeyPersonnelEmailIds().length > 0)?contract.getContractKeyPersonnelEmailIds()[i]:null);
					stmt.setString(k++,contract.getContract_id());
					stmt.addBatch();
				}
				c = stmt.executeBatch();
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				
				String documents_qry = "INSERT into contract_documents (name,attachment,contract_id_fk) "
						  +"VALUES (?,?,?)";
				stmt = con.prepareStatement(documents_qry); 
				
				arraySize = 0;
				if(!StringUtils.isEmpty(contract.getContractDocumentNames()) && contract.getContractDocumentNames().length > 0) {
					contract.setContractDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractDocumentNames()));
					if(arraySize < contract.getContractDocumentNames().length) {
						arraySize = contract.getContractDocumentNames().length;
					}
				}
				String[] documentNames = new String[arraySize];
				if(!StringUtils.isEmpty(contract.getContractDocumentFiles()) && contract.getContractDocumentFiles().length > 0) {
					if(arraySize < contract.getContractDocumentFiles().length) {
						arraySize = contract.getContractDocumentFiles().length;
					}
					String saveDirectory = CommonConstants.CONTRACT_FILE_SAVING_PATH ;
					documentNames = new String[arraySize];
					for (int i = 0; i < documentNames.length; i++) {
						MultipartFile file = contract.getContractDocumentFiles()[i];
						if (null != file && !file.isEmpty()){
							String fileName = file.getOriginalFilename();
							documentNames[i] = fileName;
							FileUploads.singleFileSaving(file, saveDirectory, fileName);
						}else {
							documentNames[i] = null;
						}
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					int k = 1;
					stmt.setString(k++,(contract.getContractDocumentNames().length > 0)?contract.getContractDocumentNames()[i]:null);
					stmt.setString(k++,(documentNames.length > 0)?documentNames[i]:null);					
					stmt.setString(k++,contract.getContract_id());
					stmt.addBatch();
				}
				c = stmt.executeBatch();
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			}
			
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;
	}

	
	private String getDepartmentCode(String deptId, Connection con) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String obj = null;
		try {
			String qry = "select contract_id_code from department where department = ?";
			ps  = con.prepareStatement(qry);
			ps.setString(1, deptId);
			rs = ps.executeQuery();
			if(rs.next()) {
				obj = rs.getString("contract_id_code");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, ps, rs);
		}
		return obj;
	}

	private String getContractIdByWorkId(String work_id, String department_code, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contract_id = null;
		try{
			String maxIdQry = "SELECT CONCAT(SUBSTRING(contract_id, 1, LENGTH(contract_id)-4),'"+department_code+"',LPAD(MAX(SUBSTRING(contract_id, 9, LENGTH(contract_id)))+1,2,'0') ) AS maxId FROM contract WHERE contract_id LIKE ?";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1, work_id+department_code+"%");
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contract_id = rs.getString("maxId");
				if(StringUtils.isEmpty(contract_id)) {
					contract_id =  work_id+department_code+"01";
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return contract_id;
	}
	
	@Override
	public Contract getContract(Contract obj)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Contract contract = null;
		try{
			con = dataSource.getConnection();
			String contract_updateQry = "select w.work_name,dt.contract_id_code,w.project_id_fk,u.designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk  " + 
									",scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
									+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
									+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,contract_status_fk,bg_required,insurance_required " + 
									"from contract c " + 
									"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
									"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
									"left join user u on c.hod_user_id_fk = u.user_id "
									+"left join department dt on c.department_fk = dt.department where contract_id = ?" ;
			stmt = con.prepareStatement(contract_updateQry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				contract = new Contract();
				contract.setWork_name(resultSet.getString("work_name"));
				contract.setWork_id_fk(resultSet.getString("work_id_fk"));
				contract.setDesignation(resultSet.getString("designation"));
				contract.setUser_name(resultSet.getString("user_name"));
				contract.setContract_id_code(resultSet.getString("contract_id_code"));
				contract.setProject_id_fk(resultSet.getString("project_id_fk"));
				contract.setContract_id(resultSet.getString("contract_id"));
				contract.setContract_type_fk(resultSet.getString("contract_type_fk"));
				contract.setContract_name(resultSet.getString("contract_name"));
				contract.setContract_short_name(resultSet.getString("contract_short_name"));
				contract.setContractor_id_fk(resultSet.getString("contractor_id_fk"));
				contract.setContractor_name(resultSet.getString("contractor_name"));
				contract.setDepartment_fk(resultSet.getString("department_fk"));
				contract.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				contract.setDy_hod_user_id_fk(resultSet.getString("dy_hod_user_id_fk"));
				contract.setScope_of_contract(resultSet.getString("scope_of_contract"));
				contract.setDoc(resultSet.getString("doc"));
				contract.setAwarded_cost(resultSet.getString("awarded_cost"));
				contract.setLoa_letter_number(resultSet.getString("loa_letter_number"));
				contract.setLoa_date(resultSet.getString("loa_date"));
				contract.setCa_no(resultSet.getString("ca_no"));
				contract.setCa_date(resultSet.getString("ca_date"));
				contract.setActual_completion_date(resultSet.getString("actual_completion_date"));
				contract.setCompleted_cost(resultSet.getString("completed_cost"));
				contract.setEstimated_cost(resultSet.getString("estimated_cost"));
				contract.setDate_of_start(resultSet.getString("date_of_start"));
				contract.setContract_closure_date(resultSet.getString("contract_closure_date"));
				contract.setCompletion_certificate_release(resultSet.getString("completion_certificate_release"));
				contract.setFinal_takeover(resultSet.getString("final_takeover"));
				contract.setFinal_bill_release(resultSet.getString("final_bill_release"));
				contract.setDefect_liability_period(resultSet.getString("defect_liability_period"));
				contract.setRetention_money_release(resultSet.getString("retention_money_release"));
				contract.setPbg_release(resultSet.getString("pbg_release"));
				contract.setContract_status_fk(resultSet.getString("contract_status_fk"));
				contract.setBg_required(resultSet.getString("bg_required"));
				contract.setInsurance_required(resultSet.getString("insurance_required"));
				contract.setRemarks(resultSet.getString("remarks"));

				contract.setBankGauranree(getBankGauranree(contract.getContract_id(),con));	
				contract.setInsurence(getInsurence(contract.getContract_id(),con));	
				contract.setMilestones(getMilestones(contract.getContract_id(),con));	
				contract.setContract_revision(getContract_revision(contract.getContract_id(),con));	
				
				contract.setContractKeyPersonnels(getContractKeyPersonnels(contract.getContract_id(),con));	
				contract.setContractDocuments(getContractDocuments(contract.getContract_id(),con));
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return contract;	
		}

	private List<Contract> getContractDocuments(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT name,attachment from contract_documents where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setName(resultSet.getString("name"));
				obj.setAttachment(resultSet.getString("attachment"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
	}

	private List<Contract> getContractKeyPersonnels(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT name,cast(mobile_no as CHAR) as mobile_no,email_id from contract_key_personnel where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setName(resultSet.getString("name"));
				obj.setMobile_no(resultSet.getString("mobile_no"));
				obj.setEmail_id(resultSet.getString("email_id"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
	}

	private List<Contract> getContract_revision(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> contract_revision = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT revision_number,cast(revised_amount as CHAR) as revised_amount ,DATE_FORMAT(revised_doc,'%d-%m-%Y') AS revised_doc"
					+ ",remarks from contract_revision where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setRevision_number(resultSet.getString("revision_number"));
				obj.setRevised_amount(resultSet.getString("revised_amount"));
				obj.setRevised_doc(resultSet.getString("revised_doc"));
				obj.setRemarks(resultSet.getString("remarks"));
				contract_revision.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return contract_revision;
	}
	

	private List<Contract> getMilestones(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> milestones = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT milestone_name,DATE_FORMAT(milestone_date,'%d-%m-%Y') AS milestone_date,DATE_FORMAT(actual_date,'%d-%m-%Y') AS actual_date, revision"
					+ ",remarks from contract_milestones where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setMilestone_name(resultSet.getString("milestone_name"));
				obj.setMilestone_date(resultSet.getString("milestone_date"));
				obj.setActual_date(resultSet.getString("actual_date"));
				obj.setRevision(resultSet.getString("revision"));
				obj.setRemarks(resultSet.getString("remarks"));
				milestones.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return milestones;
	}

	private List<Contract> getInsurence(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> insurence = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT insurance_type_fk,issuing_agency,agency_address,insurance_number,cast(insurance_value as CHAR) as insurance_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS valid_upto"
					+ ",remarks from insurance where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setInsurance_type_fk(resultSet.getString("insurance_type_fk"));
				obj.setIssuing_agency(resultSet.getString("issuing_agency"));
				obj.setAgency_address(resultSet.getString("agency_address"));
				obj.setInsurance_number(resultSet.getString("insurance_number"));
				obj.setInsurance_value(resultSet.getString("insurance_value"));
				obj.setInsurence_valid_upto(resultSet.getString("valid_upto"));
				obj.setRemarks(resultSet.getString("remarks"));
				insurence.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return insurence;
	}

	private List<Contract> getBankGauranree(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> bankGauranree = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT bg_type_fk,issuing_bank,bank_address, bg_number,cast(bg_value as CHAR) as bg_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS valid_upto"
					+ ",remarks from bank_guarantee where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setBg_type_fk(resultSet.getString("bg_type_fk"));
				obj.setIssuing_bank(resultSet.getString("issuing_bank"));
				obj.setBank_address(resultSet.getString("bank_address"));
				obj.setBg_number(resultSet.getString("bg_number"));
				obj.setBg_value(resultSet.getString("bg_value"));
				obj.setBg_valid_upto(resultSet.getString("valid_upto"));
				obj.setRemarks(resultSet.getString("remarks"));
				bankGauranree.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return bankGauranree;
	}
	@Override
	public boolean updateContract(Contract contract)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String contractUpdate_Qry = "UPDATE contract SET work_id_fk = ?,department_fk = ?,contract_name = ?,contract_short_name = ?,contractor_id_fk = ?,contract_type_fk = ?,"
								+"scope_of_contract = ?,hod_user_id_fk = ?,dy_hod_user_id_fk = ?,doc = ?,awarded_cost = ?,loa_letter_number = ?,loa_date = ?,ca_no = ?,ca_date = ?"
								+",actual_completion_date = ?,completed_cost = ? ,date_of_start = ?," + 
								"estimated_cost = ?,contract_closure_date = ?,completion_certificate_release = ?,final_takeover = ?,final_bill_release = ?,defect_liability_period = ?," + 
								"retention_money_release = ?,pbg_release = ?,contract_status_fk = ?,bg_required = ?,insurance_required = ?,remarks = ? "
								+ "where contract_id = ?";
				stmt = con.prepareStatement(contractUpdate_Qry);
				int p = 1;
				stmt.setString(p++,contract.getWork_id_fk()); 
				stmt.setString(p++,contract.getDepartment_fk()); 
				stmt.setString(p++,contract.getContract_name()); 
				stmt.setString(p++,contract.getContract_short_name()); 
				stmt.setString(p++,contract.getContractor_id_fk()); 
				stmt.setString(p++,contract.getContract_type_fk());
				stmt.setString(p++,contract.getScope_of_contract()); 
				stmt.setString(p++,contract.getHod_user_id_fk());
				stmt.setString(p++,contract.getDy_hod_user_id_fk());
				stmt.setString(p++,contract.getDoc());
				stmt.setString(p++,contract.getAwarded_cost());
				stmt.setString(p++,contract.getLoa_letter_number());
				stmt.setString(p++,contract.getLoa_date());
				stmt.setString(p++,contract.getCa_no());
				stmt.setString(p++,contract.getCa_date());
				stmt.setString(p++,contract.getActual_completion_date());
				stmt.setString(p++,contract.getCompleted_cost()); 
				stmt.setString(p++,contract.getDate_of_start()); 
				stmt.setString(p++,contract.getEstimated_cost()); 
				stmt.setString(p++,contract.getContract_closure_date()); 
				stmt.setString(p++,contract.getCompletion_certificate_release()); 
				stmt.setString(p++,contract.getFinal_takeover()); 
				stmt.setString(p++,contract.getFinal_bill_release()); 
				stmt.setString(p++,contract.getDefect_liability_period()); 
				stmt.setString(p++,contract.getRetention_money_release()); 
				stmt.setString(p++,contract.getPbg_release());  
				stmt.setString(p++,contract.getContract_status_fk()); 
				stmt.setString(p++,contract.getBg_required()); 
				stmt.setString(p++,contract.getInsurance_required()); 
				stmt.setString(p++,contract.getRemarks()); 
				stmt.setString(p++,contract.getContract_id()); 
				count = stmt.executeUpdate();
				if(count > 0) {
					flag = true;
				}
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);				
				 
				int arraySize = 0;
				if(flag) {
					String deleteQry = "DELETE from bank_guarantee where contract_id_fk = ?";		 
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id());
					stmt.executeUpdate();
					if(stmt != null){stmt.close();}
					
					String BankG_qry = "INSERT into  bank_guarantee (bg_type_fk,issuing_bank,bank_address,"
							+"bg_number,bg_value,valid_upto,remarks,contract_id_fk) "
							+"VALUES (?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(BankG_qry);
			
					if(!StringUtils.isEmpty(contract.getBg_type_fks()) && contract.getBg_type_fks().length > 0) {
						contract.setBg_type_fks(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_type_fks()));
						if(arraySize < contract.getBg_type_fks().length) {
							arraySize = contract.getBg_type_fks().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getIssuing_banks()) && contract.getIssuing_banks().length > 0) {
						contract.setIssuing_banks(CommonMethods.replaceEmptyByNullInSringArray(contract.getIssuing_banks()));
						if(arraySize < contract.getIssuing_banks().length) {
							arraySize = contract.getIssuing_banks().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getBank_addresss()) && contract.getBank_addresss().length > 0) {
						contract.setBank_addresss(CommonMethods.replaceEmptyByNullInSringArray(contract.getBank_addresss()));
						if(arraySize < contract.getBank_addresss().length) {
							arraySize = contract.getBank_addresss().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getBg_numbers()) && contract.getBg_numbers().length > 0) {
						contract.setBg_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_numbers()));
						if(arraySize < contract.getBg_numbers().length) {
							arraySize = contract.getBg_numbers().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getBg_values()) && contract.getBg_values().length > 0) {
						contract.setBg_values(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_values()));
						if(arraySize < contract.getBg_values().length) {
							arraySize = contract.getBg_values().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getBg_valid_uptos()) && contract.getBg_valid_uptos().length > 0) {
						contract.setBg_valid_uptos(CommonMethods.replaceEmptyByNullInSringArray(contract.getBg_valid_uptos()));
						if(arraySize < contract.getBg_valid_uptos().length) {
							arraySize = contract.getBg_valid_uptos().length;
						}
					}			
					if(!StringUtils.isEmpty(contract.getRemarkss()) && contract.getRemarkss().length > 0) {
						contract.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(contract.getRemarkss()));
						if(arraySize < contract.getRemarkss().length) {
							arraySize = contract.getRemarkss().length;
						}
					}
					
				    for (int i = 0; i < arraySize; i++) {
						int k = 1;
						stmt.setString(k++,(contract.getBg_type_fks().length > 0)?contract.getBg_type_fks()[i]:null);
						stmt.setString(k++,(contract.getIssuing_banks().length > 0)?contract.getIssuing_banks()[i]:null);
						stmt.setString(k++,(contract.getBank_addresss().length > 0)?contract.getBank_addresss()[i]:null);
						stmt.setString(k++,(contract.getBg_numbers().length > 0)?contract.getBg_numbers()[i]:null);
						stmt.setString(k++,(contract.getBg_values().length > 0)?contract.getBg_values()[i]:null);
						stmt.setString(k++,DateParser.parse((contract.getBg_valid_uptos().length > 0)?contract.getBg_valid_uptos()[i]:null));
						stmt.setString(k++,(contract.getRemarkss().length > 0)?contract.getRemarkss()[i]:null);
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
					}
				    int[] c = stmt.executeBatch();
					if(stmt != null){stmt.close();}
					
					deleteQry = "DELETE from insurance where contract_id_fk = ?";
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id()); 
					stmt.executeUpdate();
					if(stmt != null){stmt.close();}
					
					String Insurence_qry = "INSERT into  insurance (insurance_type_fk,issuing_agency,agency_address,"
										+"insurance_number,insurance_value,valid_upto,remarks,contract_id_fk) "
										+"VALUES (?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(Insurence_qry); 
					arraySize = 0;
					if(!StringUtils.isEmpty(contract.getInsurance_type_fks()) && contract.getInsurance_type_fks().length > 0) {
						contract.setInsurance_type_fks(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_type_fks()));
						if(arraySize < contract.getInsurance_type_fks().length) {
							arraySize = contract.getInsurance_type_fks().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getIssuing_agencys()) && contract.getIssuing_agencys().length > 0) {
						contract.setIssuing_agencys(CommonMethods.replaceEmptyByNullInSringArray(contract.getIssuing_agencys()));
						if(arraySize < contract.getIssuing_agencys().length) {
							arraySize = contract.getIssuing_agencys().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getAgency_addresss()) && contract.getAgency_addresss().length > 0) {
						contract.setAgency_addresss(CommonMethods.replaceEmptyByNullInSringArray(contract.getAgency_addresss()));
						if(arraySize < contract.getAgency_addresss().length) {
							arraySize = contract.getAgency_addresss().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getInsurance_numbers()) && contract.getInsurance_numbers().length > 0) {
						contract.setInsurance_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_numbers()));
						if(arraySize < contract.getInsurance_numbers().length) {
							arraySize = contract.getInsurance_numbers().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getInsurance_values()) && contract.getInsurance_values().length > 0) {
						contract.setInsurance_values(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurance_values()));
						if(arraySize < contract.getInsurance_values().length) {
							arraySize = contract.getInsurance_values().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getInsurence_valid_uptos()) && contract.getInsurence_valid_uptos().length > 0) {
						contract.setInsurence_valid_uptos(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurence_valid_uptos()));
						if(arraySize < contract.getInsurence_valid_uptos().length) {
							arraySize = contract.getInsurence_valid_uptos().length;
						}
					}			
					if(!StringUtils.isEmpty(contract.getInsurence_remarks()) && contract.getInsurence_remarks().length > 0) {
						contract.setInsurence_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getInsurence_remarks()));
						if(arraySize < contract.getInsurence_remarks().length) {
							arraySize = contract.getInsurence_remarks().length;
						}
					}
					
					for (int i = 0; i < arraySize; i++) {
					    int k = 1;
					    stmt.setString(k++,(contract.getInsurance_type_fks().length > 0)?contract.getInsurance_type_fks()[i]:null);
						stmt.setString(k++,(contract.getIssuing_agencys().length > 0)?contract.getIssuing_agencys()[i]:null);
						stmt.setString(k++,(contract.getAgency_addresss().length > 0)?contract.getAgency_addresss()[i]:null);
						stmt.setString(k++,(contract.getInsurance_numbers().length > 0)?contract.getInsurance_numbers()[i]:null);
						stmt.setString(k++,(contract.getInsurance_values().length > 0)?contract.getInsurance_values()[i]:null);
						stmt.setString(k++,DateParser.parse((contract.getInsurence_valid_uptos().length > 0)?contract.getInsurence_valid_uptos()[i]:null));
						stmt.setString(k++,(contract.getInsurence_remarks().length > 0)?contract.getInsurence_remarks()[i]:null);
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
					}
					c = stmt.executeBatch();
					if(stmt != null){stmt.close();}
				
					deleteQry = "DELETE from contract_milestones where contract_id_fk = ?";		 
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id()); 
					stmt.executeUpdate();
					if(stmt != null){stmt.close();}
					
					String Milestone_qry = "INSERT into  contract_milestones (milestone_name,milestone_date,actual_date,revision,remarks,contract_id_fk) "
										+"VALUES (?,?,?,?,?,?)";
					stmt = con.prepareStatement(Milestone_qry); 
					arraySize = 0; 
					if(!StringUtils.isEmpty(contract.getMilestone_names()) && contract.getMilestone_names().length > 0) {
						contract.setMilestone_names(CommonMethods.replaceEmptyByNullInSringArray(contract.getMilestone_names()));
						if(arraySize < contract.getMilestone_names().length) {
							arraySize = contract.getMilestone_names().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getMilestone_dates()) && contract.getMilestone_dates().length > 0) {
						contract.setMilestone_dates(CommonMethods.replaceEmptyByNullInSringArray(contract.getMilestone_dates()));
						if(arraySize < contract.getMilestone_dates().length) {
							arraySize = contract.getMilestone_dates().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getActual_dates()) && contract.getActual_dates().length > 0) {
						contract.setActual_dates(CommonMethods.replaceEmptyByNullInSringArray(contract.getActual_dates()));
						if(arraySize < contract.getActual_dates().length) {
							arraySize = contract.getActual_dates().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getRevisions()) && contract.getRevisions().length > 0) {
						contract.setRevisions(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevisions()));
						if(arraySize < contract.getRevisions().length) {
							arraySize = contract.getRevisions().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getMile_remarks()) && contract.getMile_remarks().length > 0) {
						contract.setMile_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getMile_remarks()));
						if(arraySize < contract.getMile_remarks().length) {
							arraySize = contract.getMile_remarks().length;
						}
					}
					
					for (int i = 0; i < arraySize; i++) {
						 int k = 1;
						    stmt.setString(k++,(contract.getMilestone_names().length > 0)?contract.getMilestone_names()[i]:null);
							stmt.setString(k++,DateParser.parse((contract.getMilestone_dates().length > 0)?contract.getMilestone_dates()[i]:null));
							stmt.setString(k++,DateParser.parse((contract.getActual_dates().length > 0)?contract.getActual_dates()[i]:null));
							stmt.setString(k++,(contract.getRevisions().length > 0)?contract.getRevisions()[i]:null);
							stmt.setString(k++,(contract.getMile_remarks().length > 0)?contract.getMile_remarks()[i]:null);
							stmt.setString(k++,contract.getContract_id());
							stmt.addBatch();
					}
				
					c = stmt.executeBatch();
					if(stmt != null){stmt.close();}
				
					deleteQry = "DELETE from contract_revision where contract_id_fk = ?";		 
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id()); 
					stmt.executeUpdate();
					if(stmt != null){stmt.close();}
					
					String Revision_qry = "INSERT into  contract_revision (revision_number,revised_amount,revised_doc,remarks,contract_id_fk) "
										  +"VALUES (?,?,?,?,?)";
					stmt = con.prepareStatement(Revision_qry); 
					
					arraySize = 0;
					if(!StringUtils.isEmpty(contract.getRevision_numbers()) && contract.getRevision_numbers().length > 0) {
						contract.setRevision_numbers(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevision_numbers()));
						if(arraySize < contract.getRevision_numbers().length) {
							arraySize = contract.getRevision_numbers().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getRevised_amounts()) && contract.getRevised_amounts().length > 0) {
						contract.setRevised_amounts(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevised_amounts()));
						if(arraySize < contract.getRevised_amounts().length) {
							arraySize = contract.getRevised_amounts().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getRevised_docs()) && contract.getRevised_docs().length > 0) {
						contract.setRevised_docs(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevised_docs()));
						if(arraySize < contract.getRevised_docs().length) {
							arraySize = contract.getRevised_docs().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getRevision_remarks()) && contract.getRevision_remarks().length > 0) {
						contract.setRevision_remarks(CommonMethods.replaceEmptyByNullInSringArray(contract.getRevision_remarks()));
						if(arraySize < contract.getRevision_remarks().length) {
							arraySize = contract.getRevision_remarks().length;
						}
					}
					
					for (int i = 0; i < arraySize; i++) {
						int k = 1;
						stmt.setString(k++,(contract.getRevision_numbers().length > 0)?contract.getRevision_numbers()[i]:null);
						stmt.setString(k++,(contract.getRevised_amounts().length > 0)?contract.getRevised_amounts()[i]:null);
						stmt.setString(k++,DateParser.parse((contract.getRevised_docs().length > 0)?contract.getRevised_docs()[i]:null));								
						stmt.setString(k++,(contract.getRevision_remarks().length > 0)?contract.getRevision_remarks()[i]:null);
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
					}
					c = stmt.executeBatch();
					
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					deleteQry = "DELETE from contract_key_personnel where contract_id_fk = ?";		 
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id()); 
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					String key_personnel_qry = "INSERT into contract_key_personnel (name,mobile_no,email_id,contract_id_fk) "
							  +"VALUES (?,?,?,?)";
					stmt = con.prepareStatement(key_personnel_qry); 
					
					arraySize = 0;
					if(!StringUtils.isEmpty(contract.getContractKeyPersonnelNames()) && contract.getContractKeyPersonnelNames().length > 0) {
						contract.setContractKeyPersonnelNames(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelNames()));
						if(arraySize < contract.getContractKeyPersonnelNames().length) {
							arraySize = contract.getContractKeyPersonnelNames().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getContractKeyPersonnelMobileNos()) && contract.getContractKeyPersonnelMobileNos().length > 0) {
						contract.setContractKeyPersonnelMobileNos(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelMobileNos()));
						if(arraySize < contract.getContractKeyPersonnelMobileNos().length) {
							arraySize = contract.getContractKeyPersonnelMobileNos().length;
						}
					}
					if(!StringUtils.isEmpty(contract.getContractKeyPersonnelEmailIds()) && contract.getContractKeyPersonnelEmailIds().length > 0) {
						contract.setContractKeyPersonnelEmailIds(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractKeyPersonnelEmailIds()));
						if(arraySize < contract.getContractKeyPersonnelEmailIds().length) {
							arraySize = contract.getContractKeyPersonnelEmailIds().length;
						}
					}
					
					for (int i = 0; i < arraySize; i++) {
						int k = 1;
						stmt.setString(k++,(contract.getContractKeyPersonnelNames().length > 0)?contract.getContractKeyPersonnelNames()[i]:null);
						stmt.setString(k++,(contract.getContractKeyPersonnelMobileNos().length > 0)?contract.getContractKeyPersonnelMobileNos()[i]:null);
						stmt.setString(k++,(contract.getContractKeyPersonnelEmailIds().length > 0)?contract.getContractKeyPersonnelEmailIds()[i]:null);
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
					}
					c = stmt.executeBatch();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					deleteQry = "DELETE from contract_documents where contract_id_fk = ?";		 
					stmt = con.prepareStatement(deleteQry);
					stmt.setString(1,contract.getContract_id()); 
					stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
					
					String documents_qry = "INSERT into contract_documents (name,attachment,contract_id_fk) "
							  +"VALUES (?,?,?)";
					stmt = con.prepareStatement(documents_qry); 
					
					arraySize = 0;
					if(!StringUtils.isEmpty(contract.getContractDocumentNames()) && contract.getContractDocumentNames().length > 0) {
						contract.setContractDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(contract.getContractDocumentNames()));
						if(arraySize < contract.getContractDocumentNames().length) {
							arraySize = contract.getContractDocumentNames().length;
						}
					}
					String[] documentNames = new String[arraySize];
					if(!StringUtils.isEmpty(contract.getContractDocumentFiles()) && contract.getContractDocumentFiles().length > 0) {
						if(arraySize < contract.getContractDocumentFiles().length) {
							arraySize = contract.getContractDocumentFiles().length;
						}
						String saveDirectory = CommonConstants.CONTRACT_FILE_SAVING_PATH ;
						documentNames = new String[arraySize];
						for (int i = 0; i < documentNames.length; i++) {
							MultipartFile file = contract.getContractDocumentFiles()[i];
							if (null != file && !file.isEmpty()){
								String fileName = file.getOriginalFilename();
								documentNames[i] = fileName;
								FileUploads.singleFileSaving(file, saveDirectory, fileName);
							} else if (!StringUtils.isEmpty(contract.getContractDocumentFileNames()) && contract.getContractDocumentFileNames().length > 0 && !StringUtils.isEmpty(contract.getContractDocumentFileNames()[i])){
								documentNames[i] = contract.getContractDocumentFileNames()[i];
							} else {
								documentNames[i] = null;
							}
						}
					}
					
					for (int i = 0; i < arraySize; i++) {
						int k = 1;
						stmt.setString(k++,(contract.getContractDocumentNames().length > 0)?contract.getContractDocumentNames()[i]:null);
						stmt.setString(k++,(documentNames.length > 0)?documentNames[i]:null);					
						stmt.setString(k++,contract.getContract_id());
						stmt.addBatch();
					}
					c = stmt.executeBatch();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				}
			
				con.commit();
		
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;	
	
	}
 
	@Override
	public List<Contract> getContractorsList() throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select contractor_id as contractor_id_fk,contractor_name from contractor";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> contractorsFilterList(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT contractor_id_fk,cr.contractor_name from contract c "+
					 "LEFT JOIN contractor cr on c.contractor_id_fk = cr.contractor_id "+
					 "where contractor_id_fk is not null AND contractor_id_fk <> '' ";	
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY contractor_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> departmentsFilterList(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT department_fk,d.department_name from contract c "+
					 "LEFT JOIN department d on c.department_fk = d.department " +
					 "where department_fk is not null and department_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY department_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> worksFilterList(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_name,w.work_short_name from contract c " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	


}
