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

import com.synergizglobal.pmis.Idao.ContractorDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;

@Repository
public class ContractorDaoImpl implements ContractorDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Contractor> getContractorsList() throws Exception {
		List<Contractor> objsList = null;
		try {
			String qry ="SELECT contractor_id,contractor_name,contractor_specilization_fk,address,remarks FROM contractor";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contractor>(Contractor.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public Contractor getContractor(Contractor obj)throws Exception{
		Contractor contractor = null;
		try {
			String qry = "SELECT contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, "
					+ "email_id, pan_number, gst_number, bank_name, account_number, ifsc_code, remarks from contractor where contractor_id is not null";
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
			throw new Exception(e.getMessage());
		}
		return contractor;
	}
	
	@Override
	public boolean addContractor(Contractor obj)throws Exception{
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String contractor_id = getContractId();
			obj.setContractor_id(contractor_id);
			String insertQry = "INSERT INTO contractor"
					+ "(contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, "
					+ "email_id, pan_number, gst_number, bank_name, account_number, ifsc_code, remarks)"
					+ "VALUES"
					+ "(:contractor_id,:contractor_name,:contractor_specilization_fk,:address,:primary_contact_name,:phone_number,"
					+ ":email_id,:pan_number,:gst_number,:bank_name,:account_number,:ifsc_code,:remarks)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	private String getContractId() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contractorId = null;;
		String cId = null;
		try{
			con = dataSource.getConnection();
			String maxIdQry = "SELECT CONCAT(SUBSTRING(contractor_id, 1, LENGTH(contractor_id)-2),LPAD(MAX(SUBSTRING(contractor_id, 5, LENGTH(contractor_id)))+1,2,'0') ) AS maxId FROM contractor";
			stmt = con.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				contractorId = rs.getString("maxId");
				if(contractorId == null) {
					contractorId = "CON0001"; 
				}
				if(contractorId.length()>3) {
					String[] arrOfStr = contractorId.split("N"); 
				       for (String a : arrOfStr) 
				         cId = "CON"+arrOfStr[1];
				}
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return cId;
	}

	@Override
	public boolean updateContractor(Contractor obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE contractor set "
					+ "contractor_name= :contractor_name, contractor_specilization_fk= :contractor_specilization_fk, address= :address,"
					+ "primary_contact_name= :primary_contact_name, phone_number= :phone_number, email_id= :email_id,pan_number= :pan_number, gst_number= :gst_number, "
					+ "bank_name= :bank_name, account_number = :account_number, ifsc_code= :ifsc_code, remarks= :remarks "
					+ "where contractor_id= :contractor_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	

}
