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

import com.synergizglobal.pmis.Idao.ExpenditureDao;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Work;

@Repository
public class ExpenditureDaoImpl implements ExpenditureDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Expenditure> getWorksList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry ="SELECT c.work_id_fk,w.work_name,e.contract_id_fk from expenditure e "+
					"LEFT JOIN contract c on e.contract_id_fk = c.contract_id "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id GROUP BY c.work_id_fk ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getContractsList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT contract_id_fk,c.contract_name from expenditure e  "
					+ "LEFT JOIN  contract c on e.contract_id_fk = c.contract_id "
					+ "GROUP BY contract_id_fk ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getLedgerAccountsList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT ledger_account from expenditure "
					+ "GROUP BY ledger_account";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getContractorNamesList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT contractor_name from expenditure "
					+ "GROUP BY contractor_name";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getVoucherTypesList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT voucher_type from expenditure e  "
					+ "GROUP BY voucher_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> expendituresList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry  ="SELECT expenditure_id,c.work_id_fk,w.work_name,e.contract_id_fk,ledger_account,e.contractor_name,DATE_FORMAT(date,'%d-%m-%Y') AS date,voucher_type "
					+ "from expenditure e "
					+ "LEFT JOIN contract c on e.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN contractor cr on e.contractor_name = cr.contractor_name where expenditure_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and ledger_account = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and voucher_type = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				pValues[i++] = obj.getLedger_account();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				pValues[i++] = obj.getContractor_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				pValues[i++] = obj.getVoucher_type();
			}
			
		  objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getVoucherList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT financial_year from financial_year  ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Expenditure getExpenditure(Expenditure obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Expenditure expenditure = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT expenditure_id,w.project_id_fk,p.project_name,c.work_id_fk,w.work_name,e.contract_id_fk,c.contract_name,ledger_account,e.contractor_name,DATE_FORMAT(date,'%d-%m-%Y') AS date,voucher_type , " + 
					"voucher_no,narration,cast(net_paid as CHAR) as net_paid,cast(gross_work_done as CHAR) as gross_work_done,cast(sd_payable as CHAR) as sd_payable,cast(contractor_income_tax as CHAR) as contractor_income_tax,"+
					"cast(cgst_tds as CHAR) as cgst_tds,cast(sgst_tds as CHAR) as sgst_tds,cast(vat_wct as CHAR) as vat_wct,cast(mob_advance as CHAR) as mob_advance,cast(`interest on_mob_adv` as CHAR) as `interest on_mob_adv`," + 
					"cast(amount_withheld as CHAR) as amount_withheld,e.remarks from expenditure e " + 
					"LEFT JOIN contract c on e.contract_id_fk = c.contract_id  " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " + 
					"LEFT JOIN contractor cr on e.contractor_name = cr.contractor_name where expenditure_id is not null and expenditure_id = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, obj.getExpenditure_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				expenditure = new Expenditure();
				expenditure.setExpenditure_id(resultSet.getString("expenditure_id"));
				expenditure.setProject_id_fk(resultSet.getString("w.project_id_fk"));
				expenditure.setWork_id_fk(resultSet.getString("c.work_id_fk"));
				expenditure.setProject_name(resultSet.getString("p.project_name"));
				expenditure.setWork_name(resultSet.getString("w.work_name"));
				expenditure.setContract_id_fk(resultSet.getString("e.contract_id_fk"));
				expenditure.setContract_name(resultSet.getString("c.contract_name"));
				expenditure.setContractor_name(resultSet.getString("e.contractor_name"));
				expenditure.setLedger_account(resultSet.getString("ledger_account"));
				expenditure.setDate(resultSet.getString("date"));
				expenditure.setVoucher_type(resultSet.getString("voucher_type"));
				expenditure.setVoucher_no(resultSet.getString("voucher_no"));
				expenditure.setNarration(resultSet.getString("narration"));
				expenditure.setNet_paid(resultSet.getString("net_paid"));
				expenditure.setGross_work_done(resultSet.getString("gross_work_done"));
				expenditure.setSd_payable(resultSet.getString("sd_payable"));
				expenditure.setContractor_income_tax(resultSet.getString("contractor_income_tax"));
				expenditure.setCgst_tds(resultSet.getString("cgst_tds"));
				expenditure.setSgst_tds(resultSet.getString("sgst_tds"));
				expenditure.setVat_wct(resultSet.getString("vat_wct"));
				expenditure.setMob_advance(resultSet.getString("mob_advance"));
				expenditure.setInterest_on_mob_adv(resultSet.getString("interest on_mob_adv"));
				expenditure.setAmount_withheld(resultSet.getString("amount_withheld"));
				expenditure.setRemarks(resultSet.getString("e.remarks"));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return expenditure;
	
	}

	@Override
	public boolean addExpenditure(Expenditure obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO expenditure"
					+ "(contract_id_fk, ledger_account, date, contractor_name, voucher_type, "
					+ "voucher_no, narration, net_paid, gross_work_done, sd_payable, contractor_income_tax,cgst_tds"
					+ ",sgst_tds,vat_wct,mob_advance,`interest on_mob_adv`,amount_withheld,remarks)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int p =1;
			stmt = con.prepareStatement(insertQry); 
			stmt.setString(p++,obj.getContract_id_fk());
			stmt.setString(p++,obj.getLedger_account());
			stmt.setString(p++,obj.getDate());
			stmt.setString(p++,obj.getContractor_name());
			stmt.setString(p++,obj.getVoucher_type());
			stmt.setString(p++,obj.getVoucher_no());
			stmt.setString(p++,obj.getNarration());
			stmt.setString(p++,obj.getNet_paid());
			stmt.setString(p++,obj.getGross_work_done());
			stmt.setString(p++,obj.getSd_payable());
			stmt.setString(p++,obj.getContractor_income_tax());
			stmt.setString(p++,obj.getCgst_tds());
			stmt.setString(p++,obj.getSgst_tds());
			stmt.setString(p++,obj.getVat_wct());
			stmt.setString(p++,obj.getMob_advance());
			stmt.setString(p++,obj.getInterest_on_mob_adv());
			stmt.setString(p++,obj.getAmount_withheld());
			stmt.setString(p++,obj.getRemarks());
			count = stmt.executeUpdate();
			if(count > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateExpenditure(Expenditure obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();		
			String updateQry = "UPDATE expenditure set "
					+ "ledger_account= ?, date= ?, "
					+ "contractor_name= ?, voucher_type= ?, voucher_no= ?,narration= ?, "
					+ "net_paid= ?, gross_work_done= ?, sd_payable = ?, contractor_income_tax= ?, "
					+ "cgst_tds= ?, sgst_tds= ?, vat_wct = ?, mob_advance= ?, "
					+ "`interest on_mob_adv`= ? , amount_withheld= ?, remarks = ? "
					+ "where expenditure_id= ?";
			int p = 1;
			stmt = con.prepareStatement(updateQry); 
			stmt.setString(p++,obj.getLedger_account());
			stmt.setString(p++,obj.getDate());
			stmt.setString(p++,obj.getContractor_name());
			stmt.setString(p++,obj.getVoucher_type());
			stmt.setString(p++,obj.getVoucher_no());
			stmt.setString(p++,obj.getNarration());
			stmt.setString(p++,obj.getNet_paid());
			stmt.setString(p++,obj.getGross_work_done());
			stmt.setString(p++,obj.getSd_payable());
			stmt.setString(p++,obj.getContractor_income_tax());
			stmt.setString(p++,obj.getCgst_tds());
			stmt.setString(p++,obj.getSgst_tds());
			stmt.setString(p++,obj.getVat_wct());
			stmt.setString(p++,obj.getMob_advance());
			stmt.setString(p++,obj.getInterest_on_mob_adv());
			stmt.setString(p++,obj.getAmount_withheld());
			stmt.setString(p++,obj.getRemarks());
			stmt.setString(p++,obj.getExpenditure_id());
			int count = stmt.executeUpdate();
			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteExpenditure(Expenditure obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM expenditure where expenditure_id= :expenditure_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	
	

}
