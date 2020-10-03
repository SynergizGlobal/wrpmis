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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ContractDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
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
	
	@Override
	public List<Contract> contractList(Contract obj)throws Exception{
			List<Contract> objsList = null;
			try {
				String qry ="select w.work_name,c.work_id_fk,c.contract_id,c.contract_name,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk " + 
							"from contract c left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
							"left join contractor cr on c.contractor_id_fk = cr.contractor_id where contract_id is not null ";
				
				int arrSize = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
					qry = qry + " and hod_user_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and department_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and work_id_fk = ?";
					arrSize++;
				}
				Object[] pValues = new Object[arrSize];
				int i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
					pValues[i++] = obj.getHod_user_id_fk();
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
			String qry ="select user_id,user_name from user";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractorList()throws Exception{
		List<Contract> objsList = null;
		try {
			String qry ="select contractor_id as contractor_id_fk,contractor_name from contractor";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
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
			String contract_id = getContract_id(contract.getDepartment_fk(),con);
			String ContarctID = contract.getWork_id_fk()+contract_id;
			
		String ContractQry = "INSERT INTO contract "
							+"(contract_id,work_id_fk,department_fk,contract_name,contractor_id_fk,contract_type_fk,scope_of_contract,hod_user_id_fk,"
							+ "dy_hod_user_id_fk,doc,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,actual_completion_date,completed_cost,date_of_start,"
							+ "estimated_cost,contract_closure_date,completion_certificate_release,final_takeover,final_bill_release,defect_liability_period,"
							+ "retention_money_release,pbg_release,contract_closure,contract_status_fk,bg_required,insurance_required,remarks)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						stmt = con.prepareStatement(ContractQry);
						stmt.setString(1,ContarctID); 
						stmt.setString(2,contract.getWork_id_fk()); 
						stmt.setString(3,contract.getDepartment_fk()); 
						stmt.setString(4,contract.getContract_name()); 
						stmt.setString(5,contract.getContractor_id_fk()); 
						stmt.setString(6,contract.getContract_type_fk());
						stmt.setString(7,contract.getScope_of_contract()); 
						stmt.setString(8,contract.getHod_user_id_fk());
						stmt.setString(9,contract.getDy_hod_user_id_fk());
						stmt.setString(10,contract.getDoc());
						stmt.setString(11,contract.getAwarded_cost());
						stmt.setString(12,contract.getLoa_letter_number());
						stmt.setString(13,contract.getLoa_date());
						stmt.setString(14,contract.getCa_no());
						stmt.setString(15,contract.getCa_date());
						stmt.setString(16,contract.getActual_completion_date());
						stmt.setString(17,contract.getCompleted_cost()); 
						stmt.setString(18,contract.getDate_of_start()); 
						stmt.setString(19,contract.getEstimated_cost()); 
						stmt.setString(20,contract.getContract_closure_date()); 
						stmt.setString(21,contract.getCompletion_certificate_release()); 
						stmt.setString(22,contract.getFinal_takeover()); 
						stmt.setString(23,contract.getFinal_bill_release()); 
						stmt.setString(24,contract.getDefect_liability_period()); 
						stmt.setString(25,contract.getRetention_money_release()); 
						stmt.setString(26,contract.getPbg_release()); 
						stmt.setString(27,contract.getContract_closure()); 
						stmt.setString(28,contract.getContract_status_fk()); 
						stmt.setString(29,contract.getBg_required()); 
						stmt.setString(30,contract.getBg_required()); 
						stmt.setString(31,contract.getRemarks()); 

						count = stmt.executeUpdate();
						if(stmt != null){stmt.close();}
		
		String BankG_qry = "INSERT into  bank_guarantee (bg_type_fk,issuing_bank,bank_address,"
							+"bg_number,bg_value,valid_upto,remarks,contract_id_fk) "
							+"VALUES (?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(BankG_qry); 
		    if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getBg_numbers()) && contract.getBg_numbers().length > 0) {
		    	for (int i = 0; i < contract.getBg_numbers().length; i++) {
					if(!StringUtils.isEmpty(contract.getBg_numbers()[i])){
						stmt.setString(1,contract.getBg_type_fks()[i]);
						stmt.setString(2,contract.getIssuing_banks()[i]);
						stmt.setString(3,contract.getBank_addresss()[i]);
						stmt.setString(4,contract.getBg_numbers()[i]);
						stmt.setString(5,contract.getBg_values()[i]);
						stmt.setString(6,contract.getBg_valid_uptos()[i]);
						stmt.setString(7,contract.getRemarkss()[i]);
						stmt.setString(8,ContarctID);
						stmt.addBatch();
					}
				}
			}
			c = stmt.executeBatch();
			if(stmt != null){stmt.close();}
			
		String Insurence_qry = "INSERT into  insurance (insurance_type_fk,issuing_agency,agency_address,"
								+"insurance_number,insurance_value,valid_upto,remarks,contract_id_fk) "
								+"VALUES (?,?,?,?,?,?,?,?)";
				stmt = con.prepareStatement(Insurence_qry); 
				if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getInsurance_type_fks()) && contract.getInsurance_type_fks().length > 0) {
					for (int i = 0; i < contract.getInsurance_type_fks().length; i++) {
						if(!StringUtils.isEmpty(contract.getInsurance_type_fks()[i])){
							stmt.setString(1,contract.getInsurance_type_fks()[i]);
							stmt.setString(2,contract.getIssuing_agencys()[i]);
							stmt.setString(3,contract.getAgency_addresss()[i]);
							stmt.setString(4,contract.getInsurance_numbers()[i]);
							stmt.setString(5,contract.getInsurance_values()[i]);
							stmt.setString(6,contract.getInsurence_valid_uptos()[i]);
							stmt.setString(7,contract.getInsurence_remarks()[i]);
							stmt.setString(8,ContarctID);
							stmt.addBatch();
						}
				}
			}
			c = stmt.executeBatch();
			if(stmt != null){stmt.close();}
		
		String Milestone_qry = "INSERT into  contract_milestones (milestone_name,milestone_date,actual_date,revision,remarks,contract_id_fk) "
								+"VALUES (?,?,?,?,?,?)";
			stmt = con.prepareStatement(Milestone_qry); 
				if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getMilestone_names()) && contract.getMilestone_names().length > 0) {
					for (int i = 0; i < contract.getMilestone_names().length; i++) {
						if(!StringUtils.isEmpty(contract.getMilestone_names()[i])){
							stmt.setString(1,contract.getMilestone_names()[i]);
							stmt.setString(2,contract.getMilestone_dates()[i]);
							stmt.setString(3,contract.getActual_dates()[i]);
							stmt.setString(4,contract.getRevisions()[i]);
							stmt.setString(5,contract.getMile_remarks()[i]);
							stmt.setString(6,ContarctID);
							stmt.addBatch();
						}
			}
		}
		c = stmt.executeBatch();
		if(stmt != null){stmt.close();}
	
		String Revision_qry = "INSERT into  contract_revision (revision_number,revised_amount,revised_doc,remarks,contract_id_fk) "
							  +"VALUES (?,?,?,?,?)";
			stmt = con.prepareStatement(Revision_qry); 
				if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getRevision_numbers()) && contract.getRevision_numbers().length > 0) {
					for (int i = 0; i < contract.getRevision_numbers().length; i++) {
						if(!StringUtils.isEmpty(contract.getRevision_numbers()[i])){
							stmt.setString(1,contract.getRevision_numbers()[i]);
							stmt.setString(2,contract.getRevised_amounts()[i]);
							stmt.setString(3,contract.getRevised_docs()[i]);
							stmt.setString(4,contract.getRevision_remarks()[i]);
							stmt.setString(5,ContarctID);
							stmt.addBatch();
						}
				}
			}
			c = stmt.executeBatch();
			if(c.length > 0 || count > 0){
				flag = true; 
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;
	}

	private String getContract_id(String CID, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contract_id = null;;
		 String[] arrOfStr = null;
		 String dept = CID;
		try{
			String maxIdQry = "SELECT CONCAT(SUBSTRING(contract_id, 1, LENGTH(contract_id)-2),LPAD(MAX(SUBSTRING(contract_id, 9, LENGTH(contract_id)))+1,2,'0') ) AS maxId FROM contract";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contract_id = rs.getString("maxId");
				if(contract_id == null) {
					if(dept.contains("Eng") ){
					  contract_id = "EN01";}
				if(dept.contains("S&T") ){
					  contract_id = "ST01";}
				if(dept.contains("Elec") ){
					  contract_id = "EL01";}
				else {
					 contract_id = "EL01";}
			}
				if(contract_id.length()>6) {
					if(contract_id.contains("L")) {
						  arrOfStr = contract_id.split("L");}
					if(contract_id.contains("T")) {
						  arrOfStr = contract_id.split("T");}
					if(contract_id.contains("N")) {
						  arrOfStr = contract_id.split("N");}
					if(dept.contains("Eng") ){
						 for (String a : arrOfStr) 
							 contract_id = "EN"+arrOfStr[1];}
					if(dept.contains("S&T") ){
						 for (String a : arrOfStr) 
							 contract_id = "ST"+arrOfStr[1];}
					if(dept.contains("Elec") ){
						  for (String a : arrOfStr) 
							  contract_id = "EL"+arrOfStr[1];}
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
	public Contract getcontract(String contractId, Contract obj)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Contract contract = null;
		try{
			con = dataSource.getConnection();
			String contract_updateQry = "select w.work_name,w.project_id_fk,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk  " + 
									",scope_of_contract,estimated_cost,date_of_start,doc,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,actual_completion_date,c.remarks,"
									+"contract_closure_date,completion_certificate_release,final_takeover,final_bill_release,defect_liability_period,completed_cost,"
									+"retention_money_release,pbg_release,contract_closure,contract_status_fk,bg_required,insurance_required, " + 
									"bg.bg_type_fk,bg.bg_number,bg.bg_value,bg.bank_address,bg.valid_upto,bg.issuing_bank,bg.remarks, " + 
									"i.insurance_type_fk,i.insurance_number,i.insurance_value,i.issuing_agency,i.valid_upto,i.remarks, " + 
									"cm.milestone_name,cm.milestone_date,cm.actual_date,cm.revision,cm.remarks, " + 
									"crn.revision_number,crn.revised_doc,crn.revised_amount,crn.remarks " + 
									"from contract c " + 
									"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
									"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
									"left join bank_guarantee bg on c.contract_id = bg.contract_id_fk " + 
									"left join insurance i on c.contract_id = i.contract_id_fk " + 
									"left join contract_milestones cm on c.contract_id = cm.contract_id_fk " + 
									"left join contract_revision crn on c.contract_id = crn.contract_id_fk  where contract_id = ?" ;
			stmt = con.prepareStatement(contract_updateQry);
			stmt.setString(1, contractId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				contract = new Contract();
				contract.setWork_name(resultSet.getString("work_name"));
				contract.setWork_id_fk(resultSet.getString("work_id_fk"));
				contract.setProject_id_fk(resultSet.getString("project_id_fk"));
				contract.setContract_id(resultSet.getString("contract_id"));
				contract.setContract_type_fk(resultSet.getString("contract_type_fk"));
				contract.setContract_name(resultSet.getString("contract_name"));
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
				contract.setContract_closure(resultSet.getString("contract_closure"));
				contract.setContract_status_fk(resultSet.getString("contract_status_fk"));
				contract.setBg_required(resultSet.getString("bg_required"));
				contract.setInsurance_required(resultSet.getString("insurance_required"));
				contract.setRemarks(resultSet.getString("remarks"));

				contract.setBankGauranree(getBankGauranree(contract.getContract_id(),con));	
				contract.setInsurence(getInsurence(contract.getContract_id(),con));	
				contract.setMilestones(getMilestones(contract.getContract_id(),con));	
				contract.setContract_revision(getContract_revision(contract.getContract_id(),con));	
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

	private List<Contract> getContract_revision(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> contract_revision = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT revision_number,revised_amount,revised_doc"
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
			String qry ="SELECT milestone_name,milestone_date,actual_date, revision"
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
			String qry ="SELECT insurance_type_fk,issuing_agency,agency_address, insurance_number,insurance_value,valid_upto"
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
			String qry ="SELECT bg_type_fk,issuing_bank,bank_address, bg_number,bg_value,valid_upto"
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
		int count = 1;
		int[] c = {};
		boolean flag = false;
		try{
			con = dataSource.getConnection();
			String contractUpdate_Qry = "UPDATE  contract SET work_id_fk = ?,department_fk = ?,contract_name = ?,contractor_id_fk = ?,contract_type_fk = ?,"
								+"scope_of_contract = ?,hod_user_id_fk = ?,dy_hod_user_id_fk = ?,doc = ?,awarded_cost = ?,loa_letter_number = ?,loa_date = ?,ca_no = ?,ca_date = ?"
								+",actual_completion_date = ?,completed_cost = ? ,date_of_start = ?," + 
								"estimated_cost = ?,contract_closure_date = ?,completion_certificate_release = ?,final_takeover = ?,final_bill_release = ?,defect_liability_period = ?," + 
								"retention_money_release = ?,pbg_release = ?,contract_closure = ?,contract_status_fk = ?,bg_required = ?,insurance_required = ?,remarks = ? "
								+ "where contract_id = ?";
						stmt = con.prepareStatement(contractUpdate_Qry);
						stmt.setString(1,contract.getWork_id_fk()); 
						stmt.setString(2,contract.getDepartment_fk()); 
						stmt.setString(3,contract.getContract_name()); 
						stmt.setString(4,contract.getContractor_id_fk()); 
						stmt.setString(5,contract.getContract_type_fk());
						stmt.setString(6,contract.getScope_of_contract()); 
						stmt.setString(7,contract.getHod_user_id_fk());
						stmt.setString(8,contract.getDy_hod_user_id_fk());
						stmt.setString(9,contract.getDoc());
						stmt.setString(10,contract.getAwarded_cost());
						stmt.setString(11,contract.getLoa_letter_number());
						stmt.setString(12,contract.getLoa_date());
						stmt.setString(13,contract.getCa_no());
						stmt.setString(14,contract.getCa_date());
						stmt.setString(15,contract.getActual_completion_date());
						stmt.setString(16,contract.getCompleted_cost()); 
						stmt.setString(17,contract.getDate_of_start()); 
						stmt.setString(18,contract.getEstimated_cost()); 
						stmt.setString(19,contract.getContract_closure_date()); 
						stmt.setString(20,contract.getCompletion_certificate_release()); 
						stmt.setString(21,contract.getFinal_takeover()); 
						stmt.setString(22,contract.getFinal_bill_release()); 
						stmt.setString(23,contract.getDefect_liability_period()); 
						stmt.setString(24,contract.getRetention_money_release()); 
						stmt.setString(25,contract.getPbg_release()); 
						stmt.setString(26,contract.getContract_closure()); 
						stmt.setString(27,contract.getContract_status_fk()); 
						stmt.setString(28,contract.getBg_required()); 
						stmt.setString(29,contract.getInsurance_required()); 
						stmt.setString(30,contract.getRemarks()); 
						stmt.setString(31,contract.getContract_id()); 

						count = stmt.executeUpdate();
						if(stmt != null){stmt.close();}

				String qryDelete = "delete from bank_guarantee where contract_id_fk = ?";
				stmt = con.prepareStatement(qryDelete); 
				stmt.setString(1,contract.getContract_id());
				count = stmt.executeUpdate();
				if(stmt != null){stmt.close();}

				String BankG_qry = "INSERT into  bank_guarantee (bg_type_fk,issuing_bank,bank_address,"
									+"bg_number,bg_value,valid_upto,remarks,contract_id_fk) "
									+"VALUES (?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(BankG_qry); 
				    if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getBg_type_fks()) && contract.getBg_type_fks().length > 0) {
				    	for (int i = 0; i < contract.getBg_type_fks().length; i++) {
							if(!StringUtils.isEmpty(contract.getBg_type_fks()[i])){
								stmt.setString(1,contract.getBg_type_fks()[i]);
								stmt.setString(2,contract.getIssuing_banks()[i]);
								stmt.setString(3,contract.getBank_addresss()[i]);
								stmt.setString(4,contract.getBg_numbers()[i]);
								stmt.setString(5,contract.getBg_values()[i]);
								stmt.setString(6,contract.getBg_valid_uptos()[i]);
								stmt.setString(7,contract.getRemarkss()[i]);
								stmt.setString(8,contract.getContract_id());
								stmt.addBatch();
							}
					}
				}
				c = stmt.executeBatch();
				
	
					String qryInsurenceDelete = "delete from insurance where contract_id_fk = ?";
					stmt = con.prepareStatement(qryInsurenceDelete); 
					stmt.setString(1,contract.getContract_id());
					count = stmt.executeUpdate();
					if(stmt != null){stmt.close();}
				
				
		    	String Insurence_qry = "INSERT into  insurance (insurance_type_fk,issuing_agency,agency_address,"
									+"insurance_number,insurance_value,valid_upto,remarks,contract_id_fk) "
									+"VALUES (?,?,?,?,?,?,?,?)";
					stmt = con.prepareStatement(Insurence_qry); 
					if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getInsurance_type_fks()) && contract.getInsurance_type_fks().length > 0) {
						for (int i = 0; i < contract.getInsurance_type_fks().length; i++) {
							if(!StringUtils.isEmpty(contract.getInsurance_type_fks()[i])){
								stmt.setString(1,contract.getInsurance_type_fks()[i]);
								stmt.setString(2,contract.getIssuing_agencys()[i]);
								stmt.setString(3,contract.getAgency_addresss()[i]);
								stmt.setString(4,contract.getInsurance_numbers()[i]);
								stmt.setString(5,contract.getInsurance_values()[i]);
								stmt.setString(6,contract.getInsurence_valid_uptos()[i]);
								stmt.setString(7,contract.getInsurence_remarks()[i]);
								stmt.setString(8,contract.getContract_id());
								stmt.addBatch();
							}
					}
				}
				c = stmt.executeBatch();
				

				String qryMilestoneDelete = "delete from contract_milestones where contract_id_fk = ?";
				stmt = con.prepareStatement(qryMilestoneDelete); 
				stmt.setString(1,contract.getContract_id());
				count = stmt.executeUpdate();
				if(stmt != null){stmt.close();}
			
				String Milestone_qry = "INSERT into  contract_milestones (milestone_name,milestone_date,actual_date,revision,remarks,contract_id_fk) "
						+"VALUES (?,?,?,?,?,?)";
				stmt = con.prepareStatement(Milestone_qry); 
				if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getMilestone_names()) && contract.getMilestone_names().length > 0) {
					for (int i = 0; i < contract.getMilestone_names().length; i++) {
						if(!StringUtils.isEmpty(contract.getMilestone_names()[i])){
							stmt.setString(1,contract.getMilestone_names()[i]);
							stmt.setString(2,contract.getMilestone_dates()[i]);
							stmt.setString(3,contract.getActual_dates()[i]);
							stmt.setString(4,contract.getRevisions()[i]);
							stmt.setString(5,contract.getMile_remarks()[i]);
							stmt.setString(6,contract.getContract_id());
							stmt.addBatch();
						}
					}
				}
				c = stmt.executeBatch();
				
				
				String qryRevisionDelete = "delete from contract_revision where contract_id_fk = ?";
				stmt = con.prepareStatement(qryRevisionDelete); 
				stmt.setString(1,contract.getContract_id());
				count = stmt.executeUpdate();
				if(stmt != null){stmt.close();}
				
				String Revision_qry = "INSERT into  contract_revision (revision_number,revised_amount,revised_doc,remarks,contract_id_fk) "
						  +"VALUES (?,?,?,?,?)";
					stmt = con.prepareStatement(Revision_qry); 
						if(!StringUtils.isEmpty(contract) && !StringUtils.isEmpty(contract.getRevision_numbers()) && contract.getRevision_numbers().length > 0) {
							for (int i = 0; i < contract.getRevision_numbers().length; i++) {
								if(!StringUtils.isEmpty(contract.getRevision_numbers()[i])){
									stmt.setString(1,contract.getRevision_numbers()[i]);
									stmt.setString(2,contract.getRevised_amounts()[i]);
									stmt.setString(3,contract.getRevised_docs()[i]);
									stmt.setString(4,contract.getRevision_remarks()[i]);
									stmt.setString(5,contract.getContract_id());
									stmt.addBatch();
								}
						}
					}
					c = stmt.executeBatch();
			if(c.length > 0 || count > 0){
				flag = true; 
			}
		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}		
		return flag;	
	
	}


}
