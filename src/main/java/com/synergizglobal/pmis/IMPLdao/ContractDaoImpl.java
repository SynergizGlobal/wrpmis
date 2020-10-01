package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
							+ "dy_hod_user_id_fk,doc,awarded_cost,loa_letter_number,loa_date,ca_no,ca_date,actual_completion_date,completed_cost)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
							  +"VALUES (?,?,?,?,?,?)";
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
		 String Dept = CID;
		try{
			String maxIdQry = "SELECT CONCAT(SUBSTRING(contract_id, 1, LENGTH(contract_id)-2),LPAD(MAX(SUBSTRING(contract_id, 9, LENGTH(contract_id)))+1,2,'0') ) AS maxId FROM contract";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contract_id = rs.getString("maxId");
				if(contract_id == null) {
					if(Dept.equals("Engg") ){
					  contract_id = "EN01";}
				if(Dept.equals("S&T") ){
					  contract_id = "ST01";}
				if(Dept.equals("Elec") ){
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
					if(Dept.equals("Engg") ){
						 for (String a : arrOfStr) 
							 contract_id = "EN"+arrOfStr[1];}
					if( Dept.equals("S&T")  ) {
						 for (String a : arrOfStr) 
							 contract_id = "ST"+arrOfStr[1];}
					if( Dept.equals("Elec") ) {
						  for (String a : arrOfStr) 
							  contract_id = "EL"+arrOfStr[1];}
					else {
						 contract_id = "EL01";}
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
		int count = 1;
		int[] c = {};
		boolean flag = false;
		Contract contract = null;
		try{
			con = dataSource.getConnection();
			String contract_updateQry = "select w.work_name,c.work_id_fk,c.contract_id,c.contract_name,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk " + 
									"from contract c left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
									"left join contractor cr on c.contractor_id_fk = cr.contractor_id "+
									"left join bank_guarantee bg on c.contract_id = bg.contract_id_fk " + 
									"left join insurance i on c.contract_id = i.contract_id_fk " + 
									"left join contract_milestones cm on c.contract_id = cm.contract_id_fk " + 
									"left join contract_revision cr on c.contract_id = cr.contract_id_fk where contract_id is ?" ;
			stmt = con.prepareStatement(contract_updateQry);
			stmt.setString(1, contractId);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				contract = new Contract();
				contract.setWork_name(resultSet.getString("work_name"));
				contract.setWork_id_fk(resultSet.getString("work_id_fk"));
				contract.setContract_id(resultSet.getString("contract_id"));
				contract.setContract_name(resultSet.getString("contract_name"));
				contract.setContractor_name(resultSet.getString("contractor_name"));
				contract.setDepartment_fk(resultSet.getString("department_fk"));
				contract.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				contract.setDy_hod_user_id_fk(resultSet.getString("dy_hod_user_id_fk"));
				contract.setDy_hod_user_id_fk(resultSet.getString("dy_hod_user_id_fk"));







				
				
			}
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
		return contract;	
		}


}
