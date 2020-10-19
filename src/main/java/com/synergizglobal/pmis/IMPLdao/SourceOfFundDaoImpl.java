package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.SourceOfFundDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Work;

@Repository
public class SourceOfFundDaoImpl implements SourceOfFundDao{
	

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<SourceOfFund> getSourceOfFundList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select source_of_funds_fk  from funds f "
					+ "LEFT JOIN source_of_funds sf on f.source_of_funds_fk = sf.source_of_funds "
					+ "GROUP BY source_of_funds_fk";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getRailwayList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select sub_category_railway_id_fk, r.railway_name  from funds "
					+ "LEFT JOIN railway r on sub_category_railway_id_fk = railway_id "
					+ "GROUP BY sub_category_railway_id_fk";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getWorkList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select work_id_fk,w.work_name  from funds f "
					+ "LEFT JOIN work w on f.work_id_fk = w.work_id "
					+ "GROUP BY work_id_fk";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getRailwaysList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select railway_id,railway_name  from railway ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<SourceOfFund> fundsList(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		
		String qry = "SELECT funds_id, f.work_id_fk,w.work_name,f.source_of_funds_fk,f.sub_category_railway_id_fk,DATE_FORMAT(funding_date,'%d-%m-%Y') AS funding_date,cast(fund_amount as CHAR) as fund_amount,ledger_account from funds f "
				+ "LEFT JOIN work w on f.work_id_fk = w.work_id  "
				+ "LEFT JOIN source_of_funds sf on f.source_of_funds_fk = sf.source_of_funds "
				+ "LEFT JOIN railway r on f.sub_category_railway_id_fk = r.railway_id where funds_id is not null ";
		int arrSize = 0;
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
			qry = qry + " and work_id_fk = ?";
			arrSize++;
		}	
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
			qry = qry + " and source_of_funds_fk = ?";
			arrSize++;
		}	
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
			qry = qry + " and sub_category_railway_id_fk = ?";
			arrSize++;
		}	
		Object[] pValues = new Object[arrSize];
		int i = 0;
		
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
			pValues[i++] = obj.getWork_id_fk();
		}
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
			pValues[i++] = obj.getSource_of_funds_fk();
		}
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
			pValues[i++] = obj.getSub_category_railway_id_fk();
		}
		
	 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));
		
		return objsList;
	}

	@Override
	public boolean addFunds(SourceOfFund obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO funds"
					+ "(work_id_fk, source_of_funds_fk, sub_category_railway_id_fk, funding_date, fund_amount, "
					+ "remarks, bank_account, voucher_type, voucher_no, narration, ledger_account,attachment)"
					+ "VALUES"
					+ "(:work_id_fk,:source_of_funds_fk,:sub_category_railway_id_fk,:funding_date,:fund_amount,"
					+ ":remarks,:bank_account,:voucher_type,:voucher_no,:narration,:ledger_account,:attachment)";
			
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

	@Override
	public SourceOfFund getFunds(SourceOfFund obj) throws Exception {
		SourceOfFund funds = null;
		try {
			String qry = "SELECT funds_id, f.work_id_fk,w.work_name,p.project_id,w.project_id_fk,p.project_name, source_of_funds_fk, sub_category_railway_id_fk, DATE_FORMAT(funding_date,'%d-%m-%Y') AS funding_date, "+
					"cast(fund_amount as CHAR) as fund_amount, f.remarks, bank_account, voucher_type, voucher_no,narration, ledger_account, f.attachment from funds f " + 
					"LEFT JOIN work w on f.work_id_fk = w.work_id  " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " + 
					"LEFT JOIN source_of_funds sf on f.source_of_funds_fk = sf.source_of_funds " + 
					"LEFT JOIN railway r on f.sub_category_railway_id_fk = r.railway_id where funds_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFunds_id())) {
				qry = qry + " and funds_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFunds_id())) {
				pValues[i++] = obj.getFunds_id();
			}
			funds = (SourceOfFund)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
				
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return funds;
	}

	@Override
	public boolean updateFunds(SourceOfFund obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE funds set "
					+ "source_of_funds_fk= :source_of_funds_fk, sub_category_railway_id_fk= :sub_category_railway_id_fk, "
					+ "funding_date= :funding_date, fund_amount= :fund_amount, remarks= :remarks,bank_account= :bank_account, "
					+ "voucher_type= :voucher_type, voucher_no= :voucher_no, narration = :narration, ledger_account= :ledger_account,attachment= :attachment  "
					+ "where funds_id= :funds_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
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
	public boolean deleteFunds(SourceOfFund obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM funds where funds_id= :funds_id ";
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
