package com.synergizglobal.wrpmis.IMPLdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.wrpmis.common.CommonMethods;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.Idao.FormsHistoryDao;
import com.synergizglobal.wrpmis.Idao.SourceOfFundDao;
import com.synergizglobal.wrpmis.model.Deliverables;
import com.synergizglobal.wrpmis.model.FOB;
import com.synergizglobal.wrpmis.model.FormHistory;
import com.synergizglobal.wrpmis.model.SourceOfFund;

@Repository
public class SourceOfFundDaoImpl implements SourceOfFundDao{
	

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<SourceOfFund> getRailwaysList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select railway_id,railway_name  from railway ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<SourceOfFund> fundsList(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		
		String qry = "SELECT funds_id,f.source_of_funds_fk,f.sub_category_railway_id_fk,r.railway_name,FORMAT(funding_date,'dd-MM-yyyy') AS funding_date,fund_amount,ledger_account, "
				+ "bank_account,voucher_type,voucher_no,narration,f.remarks,f.project_id_fk,p.project_name,fund_amount_units,m.unit as amount_unit  "
				+ "from funds f "
				+ "LEFT JOIN project p on f.project_id_fk = p.project_id  "
				+ "LEFT JOIN source_of_funds sf on f.source_of_funds_fk = sf.source_of_funds "+
				"LEFT JOIN money_unit m on f.fund_amount_units = m.value " 
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
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO funds"
					+ "(project_id_fk, source_of_funds_fk, sub_category_railway_id_fk, funding_date, fund_amount, "
					+ "remarks, bank_account, voucher_type, voucher_no, narration, ledger_account,fund_amount_units)"
					+ "VALUES"
					+ "(:project_id_fk,:source_of_funds_fk,:sub_category_railway_id_fk,:funding_date,:fund_amount,"
					+ ":remarks,:bank_account,:voucher_type,:voucher_no,:narration,:ledger_account,:fund_amount_units)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(insertQry, paramSource, keyHolder);		
			if(count > 0) {
				 String fund_id = String.valueOf(keyHolder.getKey().intValue());
				 obj.setFunds_id(fund_id);
				 flag = true;
				if(!StringUtils.isEmpty(obj.getFundFiles()) && obj.getFundFiles().size() > 0) {
					String file_insert_qry = "INSERT into  funds_files ( funds_id_fk, attachment) VALUES (:funds_id,:attachment)";
					List<MultipartFile> fundFiles = obj.getFundFiles();
					for (MultipartFile multipartFile : fundFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants.FUND_FILE_SAVING_PATH ;
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							SourceOfFund fileObj = new SourceOfFund();
							fileObj.setFunds_id(fund_id);
							fileObj.setAttachment(fileName);
							
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(file_insert_qry, paramSource);
						}
					}
				}	
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Add Source of Fund");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Source of Fund "+obj.getFunds_id() + " Created");
				//formHistory.setWork_id_fk(obj.getWork_id_fk());
				//formHistory.setContract(budget.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public SourceOfFund getFunds(SourceOfFund obj) throws Exception {
		SourceOfFund funds = null;
		try {
			String qry = "SELECT funds_id,f.project_id_fk,p.project_name, source_of_funds_fk, sub_category_railway_id_fk, FORMAT(funding_date,'dd-MM-yyyy') AS funding_date, "+
					"fund_amount, f.remarks, bank_account, voucher_type, voucher_no,narration, ledger_account,f.fund_amount_units "
					+ "from funds f " + 
					"LEFT JOIN project p on f.project_id_fk = p.project_id " + 
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

			if(!StringUtils.isEmpty(funds)) {
				String qry2 ="select id, funds_id_fk, attachment  from funds_files where funds_id_fk = ?";
				List<SourceOfFund> objList = jdbcTemplate.query( qry2,new Object[] {obj.getFunds_id()}, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));
				funds.setFundFilesList(objList);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return funds;
	}

	@Override
	public boolean updateFunds(SourceOfFund obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE funds set "
					+ "source_of_funds_fk= :source_of_funds_fk, sub_category_railway_id_fk= :sub_category_railway_id_fk, "
					+ "funding_date= :funding_date, fund_amount= :fund_amount, remarks= :remarks,bank_account= :bank_account, "
					+ "voucher_type= :voucher_type, voucher_no= :voucher_no, narration = :narration, ledger_account= :ledger_account,fund_amount_units= :fund_amount_units  "
					+ "where funds_id= :funds_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				String deleteFilesQry = "delete from funds_files  where funds_id_fk = :funds_id";		 
				SourceOfFund fileObj = new SourceOfFund();
				fileObj.setFunds_id(obj.getFunds_id());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
			
				String insert_qry = "INSERT into  funds_files ( funds_id_fk, attachment) VALUES (:funds_id,:attachment)";
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getFundFileNames()) && obj.getFundFileNames().length > 0 ) {
					obj.setFundFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getFundFileNames()));
					if(arraySize < obj.getFundFileNames().length) {
						arraySize = obj.getFundFileNames().length;
					}
				}
				for (int i = 0; i < arraySize; i++) {
					fileObj = new SourceOfFund();
					fileObj.setFunds_id(obj.getFunds_id());
					fileObj.setAttachment(obj.getFundFileNames()[i]);
					paramSource = new BeanPropertySqlParameterSource(fileObj);	
					namedParamJdbcTemplate.update(insert_qry, paramSource);
				}
				
				if(!StringUtils.isEmpty(obj.getFundFiles()) && obj.getFundFiles().size() > 0) {
					List<MultipartFile> fobFiles = obj.getFundFiles();
					for (MultipartFile multipartFile : fobFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants.FUND_FILE_SAVING_PATH ;
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							obj.setAttachment(fileName);
						
							fileObj = new SourceOfFund();
							fileObj.setFunds_id(obj.getFunds_id());
							fileObj.setAttachment(fileName);
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(insert_qry, paramSource);
						}
					}
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Update Source of Fund");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Source of Fund "+obj.getFunds_id() + " Updated");
				//formHistory.setWork_id_fk(obj.getWork_id_fk());
				//formHistory.setContract(budget.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<SourceOfFund> getSOFList(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry = "select source_of_funds_fk from funds f  "+
					"where source_of_funds_fk is not null ";	
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
				qry = qry + " and sub_category_railway_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
				qry = qry + " and source_of_funds_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY source_of_funds_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
				pValues[i++] = obj.getSub_category_railway_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
				pValues[i++] = obj.getSource_of_funds_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getRailwayList(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry = "select sub_category_railway_id_fk,r.railway_name from funds f  "+
					"LEFT JOIN railway r on f.sub_category_railway_id_fk = r.railway_id "+
					"where sub_category_railway_id_fk is not null ";	
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
				qry = qry + " and source_of_funds_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
				qry = qry + " and sub_category_railway_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY sub_category_railway_id_fk,r.railway_name ";
			
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
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getFundWorksList(SourceOfFund obj) throws Exception {
		return null;
		
	}

	@Override
	public List<SourceOfFund> getSourceOfFundList() throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry ="select source_of_funds as source_of_funds_fk  from source_of_funds ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getProjectsListForSourceOfFundForm(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry = "select project_id,project_name from project order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<SourceOfFund> getWorkListForSourceOfFundForm(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = new ArrayList<SourceOfFund>();
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(SourceOfFund obj, String searchParameter) throws Exception {
		int totalRecords = 0;	
		try {
			String qry = "SELECT count(*) as total_records from funds f "
					+ "LEFT JOIN project p on f.project_id_fk = p.project_id  "
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
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (f.project_id_fk like ? or project_name like ? or source_of_funds_fk like ?"
						+ " or sub_category_railway_id_fk like ? or railway_name like ? or funding_date like ? or fund_amount like ? or ledger_account like ?)";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
				pValues[i++] = obj.getSource_of_funds_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
				pValues[i++] = obj.getSub_category_railway_id_fk();
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
		e.printStackTrace();
		throw new Exception(e);
	}
	return totalRecords;
	}

	@Override
	public List<SourceOfFund> getSourceOfFundList(SourceOfFund obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<SourceOfFund> objsList = null;
		
		String qry = "SELECT funds_id,f.project_id_fk,f.source_of_funds_fk,f.sub_category_railway_id_fk,r.railway_name,FORMAT(funding_date,'dd-MM-yyyy') AS funding_date,fund_amount,ledger_account, "
				+ "bank_account,voucher_type,voucher_no,narration,f.remarks,f.project_id_fk,p.project_name "
				+ "from funds f "
				+ "LEFT JOIN project p on f.project_id_fk = p.project_id  "
				+ "LEFT JOIN source_of_funds sf on f.source_of_funds_fk = sf.source_of_funds "
				+ "LEFT JOIN railway r on f.sub_category_railway_id_fk = r.railway_id where funds_id is not null ";
		int arrSize = 0;
		
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
			qry = qry + " and source_of_funds_fk = ?";
			arrSize++;
		}	
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
			qry = qry + " and sub_category_railway_id_fk = ?";
			arrSize++;
		}	
		if(!StringUtils.isEmpty(searchParameter)) {
			qry = qry + " and (f.project_id_fk like ? or project_name like ? or source_of_funds_fk like ?"
					+ " or sub_category_railway_id_fk like ? or railway_name like ? or funding_date like ? or fund_amount like ? or ledger_account like ?)";
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
			qry = qry + " ORDER BY funds_id ASC offset ? rows  fetch next ? rows only";
			arrSize++;
			arrSize++;
		}
		Object[] pValues = new Object[arrSize];
		int i = 0;
		
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSource_of_funds_fk())) {
			pValues[i++] = obj.getSource_of_funds_fk();
		}
		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_railway_id_fk())) {
			pValues[i++] = obj.getSub_category_railway_id_fk();
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
	 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));
		
		return objsList;
	}

	@Override
	public List<SourceOfFund> getUnitsList(SourceOfFund obj) throws Exception {
		List<SourceOfFund> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SourceOfFund>(SourceOfFund.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	
}
