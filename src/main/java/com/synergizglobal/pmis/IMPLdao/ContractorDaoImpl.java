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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ContractorDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.FormHistory;

@Repository
public class ContractorDaoImpl implements ContractorDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<Contractor> getContractorsList() throws Exception {
		List<Contractor> objsList = null;
		try {
			String qry ="SELECT contractor_id, contractor_name, contractor_specilization_fk, address, primary_contact_name, phone_number, email_id, pan_number, gst_number, bank_name, account_number, ifsc_code, remarks FROM contractor";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contractor>(Contractor.class));	
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
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Contracts");
				formHistory.setForm_name("Add Contractor ");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("Contractor  "+obj.getContractor_id() + " Added");
				//formHistory.setWork(obj.getWork_id_fk());
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

	private String getContractorId() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String contractorId = null;;
		try{
			con = dataSource.getConnection();
			String maxIdQry = "SELECT CONCAT(SUBSTRING(contractor_id, 1, LENGTH(contractor_id)-4),LPAD(MAX(SUBSTRING(contractor_id, 5, LENGTH(contractor_id)))+1,4,'0') ) AS maxId FROM contractor";
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
				//formHistory.setWork(obj.getWork_id_fk());
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
				qry = qry + " and (contractor_name like ? or pan_number like ? or contractor_specilization_fk like ?"
						+ " or address like ? or primary_contact_name like ? or phone_number like ? or email_id like ? )";
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
				qry = qry + " and (contractor_name like ? or pan_number like ? or contractor_specilization_fk like ?"
						+ " or address like ? or primary_contact_name like ? or phone_number like ? or email_id like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY contractor_id ASC limit ?,?";
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
