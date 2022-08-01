package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ExpenditureDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.FormHistory;

@Repository
public class ExpenditureDaoImpl implements ExpenditureDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<Expenditure> getExpendituresList(Expenditure obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry  ="SELECT expenditure_id,c.work_id_fk,c.contract_short_name,w.work_short_name,w.work_name,e.contract_id_fk,c.contract_name,ledger_account,e.contractor_name,FORMAT(date,'dd-MM-yyyy') AS date,voucher_type,voucher_no, "
					+ "narration,cast(net_paid as CHAR) as net_paid,cast(gross_work_done as CHAR) as gross_work_done,cast(sd_payable as CHAR) as sd_payable,cast(contractor_income_tax as CHAR) as contractor_income_tax,"
					+ "cast(cgst_tds as CHAR) as cgst_tds,cast(sgst_tds as CHAR) as sgst_tds,cast(igst_tds as CHAR) as igst_tds,cast(vat_wct as CHAR) as vat_wct,cast(mob_advance as CHAR) as mob_advance,"
					+ "cast([interest on_mob_adv]  as CHAR) as interest_on_mob_adv,cast(amount_withheld as CHAR) as amount_withheld,e.remarks from  expenditure e "
					+ "LEFT JOIN contract c on e.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					//+ "LEFT JOIN contractor cr on e.contractor_name = cr.contractor_name "
					+ "where expenditure_id is not null ";
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
				qry = qry + " and ledger_account like CONCAT(?,'%')";
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
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or e.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or ledger_account like ? or e.contractor_name like ? or date like ? or voucher_type like ?)";
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
				qry = qry + " ORDER BY  date DESC offset ? rows  fetch next ? rows only";
				arrSize++;
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
		  objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public int getTotalRecords(Expenditure obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from  expenditure e  " + 
					"LEFT JOIN contract c on e.contract_id_fk = c.contract_id  " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id  where expenditure_id is not null ";
				
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
				qry = qry + " and ledger_account like CONCAT(?,'%')";
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
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or e.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or ledger_account like ? or e.contractor_name like ? or date like ? or voucher_type like ?)";
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
	public List<Expenditure> getVoucherList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT financial_year from financial_year  order by financial_year desc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e);
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
			String qry = "SELECT expenditure_id,w.project_id_fk,p.project_name,c.work_id_fk,w.work_name,e.contract_id_fk,c.contract_name,ledger_account,e.contractor_name,FORMAT(date,'dd-MM-yyyy') AS date,voucher_type , " + 
					"voucher_no,narration,cast(net_paid as CHAR) as net_paid,cast(gross_work_done as CHAR) as gross_work_done,cast(sd_payable as CHAR) as sd_payable,cast(contractor_income_tax as CHAR) as contractor_income_tax,"+
					"cast(cgst_tds as CHAR) as cgst_tds,cast(sgst_tds as CHAR) as sgst_tds,cast(igst_tds as CHAR) as igst_tds,cast(vat_wct as CHAR) as vat_wct,cast(mob_advance as CHAR) as mob_advance,cast([interest on_mob_adv] as CHAR) as [interest on_mob_adv]," + 
					"cast(amount_withheld as CHAR) as amount_withheld,e.remarks,e.net_paid_units,e.gross_work_done_units,e.sd_payable_units,e.contractor_income_tax_units,e.cgst_tds_units,e.sgst_tds_units,e.igst_tds_units,e.vat_wct_units,e.mob_advance_units,e.interest_on_mob_adv_units,e.amount_withheld_units,m.unit " + 
					" from expenditure e " + 
					"LEFT JOIN contract c on e.contract_id_fk = c.contract_id  " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id "
				    + "LEFT JOIN money_unit m on e.net_paid_units = m.value "
					//+"LEFT JOIN contractor cr on e.contractor_name = cr.contractor_name "
					+ "where expenditure_id is not null and expenditure_id = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, obj.getExpenditure_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				expenditure = new Expenditure();
				expenditure.setExpenditure_id(resultSet.getString("expenditure_id"));
				expenditure.setProject_id_fk(resultSet.getString("project_id_fk"));
				expenditure.setWork_id_fk(resultSet.getString("work_id_fk"));
				expenditure.setProject_name(resultSet.getString("project_name"));
				expenditure.setWork_name(resultSet.getString("work_name"));
				expenditure.setContract_id_fk(resultSet.getString("contract_id_fk"));
				expenditure.setContract_name(resultSet.getString("contract_name"));
				expenditure.setContractor_name(resultSet.getString("contractor_name"));
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
				expenditure.setIgst_tds(resultSet.getString("igst_tds"));
				expenditure.setVat_wct(resultSet.getString("vat_wct"));
				expenditure.setMob_advance(resultSet.getString("mob_advance"));
				expenditure.setInterest_on_mob_adv(resultSet.getString("interest on_mob_adv"));
				expenditure.setAmount_withheld(resultSet.getString("amount_withheld"));
				expenditure.setRemarks(resultSet.getString("remarks"));
				
				expenditure.setNet_paid_units(resultSet.getString("net_paid_units"));
				expenditure.setGross_work_done_units(resultSet.getString("gross_work_done_units"));
				expenditure.setSd_payable_units(resultSet.getString("sd_payable_units"));
				expenditure.setContractor_income_tax_units(resultSet.getString("contractor_income_tax_units"));
				expenditure.setCgst_tds_units(resultSet.getString("cgst_tds_units"));
				expenditure.setSgst_tds_units(resultSet.getString("sgst_tds_units"));
				expenditure.setIgst_tds_units(resultSet.getString("igst_tds_units"));
				expenditure.setVat_wct_units(resultSet.getString("vat_wct_units"));
				expenditure.setMob_advance_units(resultSet.getString("mob_advance_units"));
				expenditure.setInterest_on_mob_adv_units(resultSet.getString("interest_on_mob_adv_units"));
				expenditure.setAmount_withheld_units(resultSet.getString("amount_withheld_units"));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return expenditure;
	
	}

	@Override
	public boolean addExpenditure(Expenditure obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO expenditure"
					+ "(contract_id_fk, ledger_account, date, contractor_name, voucher_type, "
					+ "voucher_no, narration, net_paid, gross_work_done, sd_payable, contractor_income_tax,cgst_tds"
					+ ",sgst_tds,igst_tds,vat_wct,mob_advance,[interest on_mob_adv],amount_withheld,remarks,net_paid_units,gross_work_done_units,sd_payable_units"
					+ ",contractor_income_tax_units,cgst_tds_units,sgst_tds_units,igst_tds_units,vat_wct_units,mob_advance_units,interest_on_mob_adv_units,amount_withheld_units)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int p =1;
			stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS); 
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
			stmt.setString(p++,obj.getIgst_tds());
			stmt.setString(p++,obj.getVat_wct());
			stmt.setString(p++,obj.getMob_advance());
			stmt.setString(p++,obj.getInterest_on_mob_adv());
			stmt.setString(p++,obj.getAmount_withheld());
			stmt.setString(p++,obj.getRemarks());
			
			stmt.setString(p++,obj.getNet_paid_units());
			stmt.setString(p++,obj.getGross_work_done_units());
			stmt.setString(p++,obj.getSd_payable_units());
			stmt.setString(p++,obj.getContractor_income_tax_units());
			stmt.setString(p++,obj.getCgst_tds_units());
			stmt.setString(p++,obj.getSgst_tds_units());
			stmt.setString(p++,obj.getIgst_tds_units());
			stmt.setString(p++,obj.getVat_wct_units());
			
			stmt.setString(p++,obj.getMob_advance_units());
			stmt.setString(p++,obj.getInterest_on_mob_adv_units());
			stmt.setString(p++,obj.getAmount_withheld_units());
			
			count = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(count > 0) {
				flag = true;
				String eId = null;
				if (rs.next()) {
					eId = rs.getString(1);
					obj.setExpenditure_id(eId);
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Add Expenditure");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Expenditure "+obj.getExpenditure_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
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
					+ "voucher_type= ?, voucher_no= ?,narration= ?, "
					+ "net_paid= ?, gross_work_done= ?, sd_payable = ?, contractor_income_tax= ?, "
					+ "cgst_tds= ?, sgst_tds= ?,igst_tds= ?, vat_wct = ?, mob_advance= ?, "
					+ "interest on_mob_adv= ? , amount_withheld= ?, remarks = ? ,net_paid_units = ?,gross_work_done_units = ?,sd_payable_units = ?,contractor_income_tax_units = ?,"
					+ "cgst_tds_units = ?,sgst_tds_units = ?,igst_tds_units = ?,vat_wct_units = ?,mob_advance_units = ?,interest_on_mob_adv_units = ?,amount_withheld_units = ? "
					+ "where expenditure_id= ?";
			int p = 1;
			stmt = con.prepareStatement(updateQry); 
			stmt.setString(p++,obj.getLedger_account());
			stmt.setString(p++,obj.getDate());
			stmt.setString(p++,obj.getVoucher_type());
			stmt.setString(p++,obj.getVoucher_no());
			stmt.setString(p++,obj.getNarration());
			stmt.setString(p++,obj.getNet_paid());
			stmt.setString(p++,obj.getGross_work_done());
			stmt.setString(p++,obj.getSd_payable());
			stmt.setString(p++,obj.getContractor_income_tax());
			stmt.setString(p++,obj.getCgst_tds());
			stmt.setString(p++,obj.getSgst_tds());
			stmt.setString(p++,obj.getIgst_tds());
			stmt.setString(p++,obj.getVat_wct());
			stmt.setString(p++,obj.getMob_advance());
			stmt.setString(p++,obj.getInterest_on_mob_adv());
			stmt.setString(p++,obj.getAmount_withheld());
			stmt.setString(p++,obj.getRemarks());
			stmt.setString(p++,obj.getNet_paid_units());
			stmt.setString(p++,obj.getGross_work_done_units());
			stmt.setString(p++,obj.getSd_payable_units());
			stmt.setString(p++,obj.getContractor_income_tax_units());
			stmt.setString(p++,obj.getCgst_tds_units());
			stmt.setString(p++,obj.getSgst_tds_units());
			stmt.setString(p++,obj.getIgst_tds_units());
			stmt.setString(p++,obj.getVat_wct_units());
			
			stmt.setString(p++,obj.getMob_advance_units());
			stmt.setString(p++,obj.getInterest_on_mob_adv_units());
			stmt.setString(p++,obj.getAmount_withheld_units());
			stmt.setString(p++,obj.getExpenditure_id());
			int count = stmt.executeUpdate();
			
			if(count > 0) {
				flag = true;
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Update Expenditure");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Expenditure "+obj.getExpenditure_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
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
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<Expenditure> getWorksFilterList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "SELECT w.work_id as work_id_fk,w.work_name,w.work_short_name from expenditure e " + 
					"LEFT JOIN contract c on c.contract_id = e.contract_id_fk " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and e.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and e.ledger_account = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and e.voucher_type = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY w.work_id,w.work_name,w.work_short_name ";
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
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public List<Expenditure> getContractsFilterList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_short_name from expenditure e " + 
					"LEFT JOIN contract c on c.contract_id = e.contract_id_fk " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and e.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and e.ledger_account = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and e.voucher_type = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY contract_id_fk,c.contract_short_name ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getledgerAccountsList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "SELECT ledger_account from expenditure e " + 
					"LEFT JOIN contract c on c.contract_id = e.contract_id_fk " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where ledger_account is not null and ledger_account <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and e.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and e.ledger_account = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and e.voucher_type = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY ledger_account ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getContractorNamesFilterList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "SELECT contractor_name from expenditure e " + 
					"LEFT JOIN contract c on c.contract_id = e.contract_id_fk " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where contractor_name is not null and contractor_name <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and e.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and e.ledger_account = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and e.voucher_type = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY contractor_name ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getVoucherTypesFilterList(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "SELECT voucher_type from expenditure e " + 
					"LEFT JOIN contract c on c.contract_id = e.contract_id_fk " +
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"where voucher_type is not null and voucher_type <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and e.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLedger_account())) {
				qry = qry + " and e.ledger_account = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_name())) {
				qry = qry + " and e.contractor_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVoucher_type())) {
				qry = qry + " and e.voucher_type = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY voucher_type ";
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getProjectsListForExpenditureForm(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry = "select project_id,project_name from project order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getWorkListForExpenditureForm(Expenditure obj) throws Exception {
		List<Expenditure> objsList = new ArrayList<Expenditure>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getContractsListForExpenditureForm(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry ="select c.contract_id,c.contract_name,c.contract_short_name,c.contractor_id_fk,cr.contractor_name,c.work_id_fk "
					+ "from contract c "
					+ "left join contractor cr on c.contractor_id_fk = cr.contractor_id "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by c.contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int uploadExpenditures(List<Expenditure> expendituresList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		int count = 0;
		int insertCount =0;
		try{
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String insertQry = "insert into  expenditure  "
					+ "(contract_id_fk, ledger_account, date, contractor_name, voucher_type, voucher_no, narration,"
					+ "net_paid, gross_work_done, sd_payable,contractor_income_tax, cgst_tds, sgst_tds, igst_tds, vat_wct, mob_advance, [interest on_mob_adv], amount_withheld, remarks,"
					+ "net_paid_units,gross_work_done_units, sd_payable_units,contractor_income_tax_units, cgst_tds_units, sgst_tds_units, igst_tds_units, vat_wct_units, mob_advance_units, interest_on_mob_adv_units, amount_withheld_units) "
					+ "VALUES(:contract_id_fk, :ledger_account, :date, :contractor_name, :voucher_type, :voucher_no, :narration, :net_paid, :gross_work_done, :sd_payable,"
					+ ":contractor_income_tax, :cgst_tds, :sgst_tds, :igst_tds, :vat_wct,:mob_advance, :interest_on_mob_adv, :amount_withheld, :remarks,"
					+ ":net_paid_units,:gross_work_done_units, :sd_payable_units,:contractor_income_tax_units, :cgst_tds_units, :sgst_tds_units, :igst_tds_units, :vat_wct_units, :mob_advance_units, :interest_on_mob_adv_units, :amount_withheld_units) ";
			for (Expenditure obj : expendituresList) {
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);
				if(count > 0) {
					insertCount++;
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return insertCount;
	}


	@Override
	public List<Expenditure> getExpendituresListForExport(Expenditure obj) throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry  ="SELECT expenditure_id,c.work_id_fk,c.contract_short_name,w.work_short_name,w.work_name,e.contract_id_fk,c.contract_name,ledger_account,e.contractor_name,FORMAT(date,'dd-MM-yyyy') AS date,voucher_type,voucher_no, "
					+ "narration,cast(net_paid as CHAR) as net_paid,cast(gross_work_done as CHAR) as gross_work_done,cast(sd_payable as CHAR) as sd_payable,cast(contractor_income_tax as CHAR) as contractor_income_tax,"
					+ "cast(cgst_tds as CHAR) as cgst_tds,cast(sgst_tds as CHAR) as sgst_tds,cast(igst_tds as CHAR) as igst_tds,cast(vat_wct as CHAR) as vat_wct,cast(mob_advance as CHAR) as mob_advance,"
					+ "cast([interest on_mob_adv] as CHAR) as interest_on_mob_adv,cast(amount_withheld as CHAR) as amount_withheld,e.remarks,e.net_paid_units,e.gross_work_done_units,e.sd_payable_units,"
					+ "e.contractor_income_tax_units,e.cgst_tds_units,e.sgst_tds_units,e.igst_tds_units,e.vat_wct_units,e.mob_advance_units,e.interest_on_mob_adv_units,e.amount_withheld_units,m.unit, "
					+ "m1.unit as gross_units,m2.unit as sd_units,m3.unit as contractor_income_units,m4.unit as cgst_units,m5.unit as sgst_units,m6.unit as igst_units,"
					+ "m7.unit as vat_units,m8.unit as mob_units,m9.unit as interest_units,m10.unit as withheld_units "
					+ "from  expenditure e "
					+ "LEFT JOIN contract c on e.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN money_unit m on e.net_paid_units = m.value "
					+ "LEFT JOIN money_unit m1 on e.gross_work_done_units = m1.value "
					+ "LEFT JOIN money_unit m2 on e.sd_payable_units = m2.value "
					+ "LEFT JOIN money_unit m3 on e.contractor_income_tax_units = m3.value "
					+ "LEFT JOIN money_unit m4 on e.cgst_tds_units = m4.value "
					+ "LEFT JOIN money_unit m5 on e.sgst_tds_units = m5.value "
					+ "LEFT JOIN money_unit m6 on e.igst_tds_units = m6.value "
					+ "LEFT JOIN money_unit m7 on e.vat_wct_units = m7.value "
					+ "LEFT JOIN money_unit m8 on e.mob_advance_units = m8.value "
					+ "LEFT JOIN money_unit m9 on e.interest_on_mob_adv_units = m9.value "
					+ "LEFT JOIN money_unit m10 on e.amount_withheld_units = m10.value "
					//+ "LEFT JOIN contractor cr on e.contractor_name = cr.contractor_name "
					+ "where expenditure_id is not null ";
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
				qry = qry + " and ledger_account like CONCAT(?,'%')";
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
			qry = qry + " ORDER BY expenditure_id ASC";
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
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Expenditure> getUnitsList() throws Exception {
		List<Expenditure> objsList = null;
		try {
			String qry =" SELECT id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Expenditure>(Expenditure.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

}
	
	


