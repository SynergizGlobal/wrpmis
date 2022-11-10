package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.RRBSESDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.RandRMain;

@Repository
public class RRBSESDaoImpl implements RRBSESDao{
	
	public static Logger logger = Logger.getLogger(IssueDaoImpl.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<RandRMain> getWorkFilterListInRRBSES(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select work_id_fk,work_name,work_short_name "
					+ "from rr_agency rr "
					+ "LEFT OUTER JOIN work w ON w.work_id = rr.work_id_fk "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + "and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by work_id_fk,work_name,work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getHodFilterListInRRBSES(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select hod,user_name,designation "
					+ "from rr_agency rr "
					+ "LEFT OUTER JOIN [user] w ON rr.hod = w.user_id  "
					+ "where hod is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + "and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by hod,user_name,designation ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		} 
		return objsList;
	}

	@Override
	public int getTotalRecords(RandRMain obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(id) as total_records from rr_agency r "
					+ "LEFT JOIN work w on r.work_id_fk = w.work_id "
					+ "left join [user] u on r.hod = u.user_id "
					+ "left join [user] uu on r.mrvc_responsible_person = uu.user_id "
					+ "where id is not null  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + "and hod = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}
	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (r.id like ? or r.work_id_fk like ? or work_short_name like ? "
						+ " or u.user_name like ? or u.designation like ? or bses_agency_name like ? or agency_responsible_person like ? ) ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
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
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<RandRMain> getRRBSESList(RandRMain obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select * from(select id as rrbses_id,r.id as id,agency_id, r.work_id_fk,work_short_name, hod,u.user_name,u.designation as designation,uu.user_name as res_user_name,uu.designation as res_designation,mrvc_responsible_person, bses_agency_name, agency_responsible_person, r.contact_number, r.email_id,  " + 
					"submission_date_report_ca, actual_submission_date_bses_report_to_mrvc, approval_by_mrvc_responsible_person, report_submission_date_to_mrvc,  " + 
					"approval_date_by_mrvc from rr_agency r "
					+ "LEFT JOIN work w on r.work_id_fk = w.work_id "
					+ "left join [user] u on r.hod = u.user_id "
					+ "left join [user] uu on r.mrvc_responsible_person = uu.user_id "
					+ "WHERE id is not null) as a where 0=0 ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + "and hod = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (id like ? or work_id_fk like ? or work_short_name like ? "
						+ " or user_name like ? or designation like ? or bses_agency_name like ? or agency_responsible_person like ? or agency_id like ?) ";
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
				qry = qry + " ORDER BY id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getPeopleListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select user_id,user_name,designation "
					+ "from [user] "
					+ "where user_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + "and reporting_to_id_srfk = ?";
				arrSize++;
			}
			qry = qry + " order by user_id asc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public RandRMain getRRBSES(RandRMain rr) throws Exception {
		RandRMain obj = null;
		try {
			String qry ="select id as rrbses_id, agency_id,work_id_fk,work_short_name, hod,u.user_name,u.designation as designation,"
					+ " uu.user_name as res_user_name,uu.designation as res_designation,mrvc_responsible_person, bses_agency_name, agency_responsible_person, r.contact_number, r.email_id as bses_email, "
					+ "FORMAT(submission_date_report_ca,'dd-MM-yyyy') AS submission_date_report_ca, FORMAT(actual_submission_date_bses_report_to_mrvc,'dd-MM-yyyy') AS actual_submission_date_bses_report_to_mrvc, approval_by_mrvc_responsible_person, FORMAT(report_submission_date_to_mrvc,'dd-MM-yyyy') AS report_submission_date_to_mrvc, "
					+ "FORMAT(approval_date_by_mrvc,'dd-MM-yyyy') AS approval_date_by_mrvc,attachment_file from rr_agency r "
					+ "LEFT JOIN work w on r.work_id_fk = w.work_id " 
					+ "left join [user] u on r.hod = u.user_id "
					+ "left join [user] uu on r.mrvc_responsible_person = uu.user_id "
					+ "WHERE id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr) && !StringUtils.isEmpty(rr.getRrbses_id())) {
				qry = qry + " and id = ?";
				arrSize++;
			}
			//qry = qry + " group by id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			//pValues[i++] = rr.getUser_id();
			if(!StringUtils.isEmpty(rr) && !StringUtils.isEmpty(rr.getRrbses_id())) {
				pValues[i++] = rr.getRrbses_id();
			}
			
			obj = (RandRMain)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRrbses_id())) {
				List<RandRMain> objsList = null;
				String qryDetails = "select rrr.id, rr_agency_id_fk, FORMAT(date_of_appointment,'dd-MM-yyyy') AS date_of_appointment, committee_name,name_of_representative, phone_no, rrr.email_id "
						+ "from rr_appointment_of_committee rrr " 
						+"left join rr_agency r1 on rrr.rr_agency_id_fk = r1.id where rr_agency_id_fk = ?  ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getRrbses_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
				obj.setRrBSESLIst(objsList);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
		return obj;
	}

	@Override
	public String addRRBSES(RandRMain obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			List<RandRMain> objsList = null;
			String rr_id = null;
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO rr_agency"
					+ "( work_id_fk, hod, mrvc_responsible_person, bses_agency_name, agency_responsible_person, contact_number, email_id, submission_date_report_ca, actual_submission_date_bses_report_to_mrvc, approval_by_mrvc_responsible_person,"
					+ "report_submission_date_to_mrvc, approval_date_by_mrvc,attachment_file,agency_id)"
					+ "VALUES"
					+ "(:work_id_fk, :hod, :mrvc_responsible_person, :bses_agency_name, :agency_responsible_person, :contact_number, :bses_email, :submission_date_report_ca, :actual_submission_date_bses_report_to_mrvc, :approval_by_mrvc_responsible_person, "
					+ ":report_submission_date_to_mrvc, :approval_date_by_mrvc,:attachment_file,:agency_id)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			if (!StringUtils.isEmpty(obj.getRragencyFiles())){
				String saveDirectory = CommonConstants.RR_AGENCY_FILE_SAVING_PATH ;
				String fileName = null;
				MultipartFile multipartFile = obj.getRragencyFiles()[0];
				
				if(null != multipartFile) {
					fileName = multipartFile.getOriginalFilename();
				}else {
					fileName = obj.getRrDocumentFileNames()[0];
				}
				FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
				obj.setAttachment_file(fileName);

			}
			
			obj.setAgency_id(getAgencyId(obj.getWork_id_fk()));
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);			
			if(count > 0) {
				rr_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setRr_id(rr_id);
				flag = true;
					con = dataSource.getConnection();
						if(count > 0) {
							String detailsQry = "INSERT INTO rr_appointment_of_committee"
									+ "( rr_agency_id_fk, date_of_appointment, name_of_representative, phone_no, email_id,committee_name)"
									+ "VALUES"
									+ "(?,?,?,?,?,?)";
							 insertStmt = con.prepareStatement(detailsQry); 
							int arraySize = 0;
							if(!StringUtils.isEmpty(obj.getDate_of_appointments()) && obj.getDate_of_appointments().length > 0 ) {
								obj.setDate_of_appointments(CommonMethods.replaceEmptyByNullInSringArray(obj.getDate_of_appointments()));
								if(arraySize < obj.getDate_of_appointments().length) {
									arraySize = obj.getDate_of_appointments().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getName_of_representatives()) && obj.getName_of_representatives().length > 0 ) {
								obj.setName_of_representatives(CommonMethods.replaceEmptyByNullInSringArray(obj.getName_of_representatives()));
								if(arraySize < obj.getName_of_representatives().length) {
									arraySize = obj.getName_of_representatives().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getPhone_nos()) && obj.getPhone_nos().length > 0 ) {
								obj.setPhone_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getPhone_nos()));
								if(arraySize < obj.getPhone_nos().length) {
									arraySize = obj.getPhone_nos().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getEmail_ids()) && obj.getEmail_ids().length > 0 ) {
								obj.setEmail_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmail_ids()));
								if(arraySize < obj.getEmail_ids().length) {
									arraySize = obj.getEmail_ids().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getCommittee_names()) && obj.getCommittee_names().length > 0 ) {
								obj.setCommittee_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getCommittee_names()));
								if(arraySize < obj.getCommittee_names().length) {
									arraySize = obj.getCommittee_names().length;
								}
							}							
				
							for (int i = 0;  i < arraySize; i++) {
								 if( obj.getDate_of_appointments().length > 0 && !StringUtils.isEmpty(obj.getDate_of_appointments()[i])) {
									int p = 1;
									    insertStmt.setString(p++,(obj.getRr_id()));
									    insertStmt.setString(p++,DateParser.parse((obj.getDate_of_appointments().length > 0)?obj.getDate_of_appointments()[i]:null));
									    insertStmt.setString(p++,((obj.getName_of_representatives().length > 0)?obj.getName_of_representatives()[i]:null));
									    insertStmt.setString(p++,(obj.getPhone_nos().length > 0)?obj.getPhone_nos()[i]:null);
									    insertStmt.setString(p++,(obj.getEmail_ids().length > 0)?obj.getEmail_ids()[i]:null);
									    insertStmt.setString(p++,(obj.getCommittee_names().length > 0)?obj.getCommittee_names()[i]:null);
									    insertStmt.addBatch();
								    }
								  int[] insertCount = insertStmt.executeBatch();
							}
						
						}
						
						
						
						FormHistory formHistory = new FormHistory();
						formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
						formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
						formHistory.setModule_name_fk("RR BSES");
						formHistory.setForm_name("Add RR BSES");
						formHistory.setForm_action_type("Add");
						formHistory.setForm_details("New RR BSES "+obj.getRr_id() + " Created");
						formHistory.setWork_id_fk(obj.getWork_id());
						//formHistory.setContract(obj.getContract_id_fk());
						
						boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					
					
						
				}
			
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return obj.getAgency_id();
	}
	
	public String getRRAgencyID(String id) throws Exception {
		
		RandRMain dObj = null;
		String agencyid = null;
		
		
		String qry ="select agency_id from(select id as rrbses_id,r.id as id,(select work_code from work where work_id=work_id_fk)+'/'+   " + 
				"					case when len((cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY  " + 
				"					(select work_code from work where work_id=work_id_fk)) as varchar)))=3 then concat('0',(cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY (select work_code from work where work_id=work_id_fk)) as varchar))) when len((cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY (select work_code from work where work_id=work_id_fk)) as varchar)))=2 then concat('00',(cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY (select work_code from work where work_id=work_id_fk)) as varchar))) when len((cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY (select work_code from work where work_id=work_id_fk)) as varchar)))=1 then concat('000',(cast(ROW_NUMBER() OVER(PARTITION BY (select work_code from work where work_id=work_id_fk) ORDER BY (select work_code from work where work_id=work_id_fk)) as varchar))) end   " + 
				"					as agency_id, r.work_id_fk,work_short_name, hod,u.user_name,u.designation as designation,uu.user_name as res_user_name,uu.designation as res_designation,mrvc_responsible_person, bses_agency_name, agency_responsible_person, r.contact_number, r.email_id,   " + 
				"					submission_date_report_ca, actual_submission_date_bses_report_to_mrvc, approval_by_mrvc_responsible_person, report_submission_date_to_mrvc,    " + 
				"					approval_date_by_mrvc from rr_agency r  " + 
				"					LEFT JOIN work w on r.work_id_fk = w.work_id  " + 
				"					left join [user] u on r.hod = u.user_id  " + 
				"					left join [user] uu on r.mrvc_responsible_person = uu.user_id  " + 
				"					WHERE id is not null) as a where 0=0 and id=?" ;
		dObj = (RandRMain)jdbcTemplate.queryForObject(qry, new Object[] {id}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		agencyid = dObj.getAgency_id();
		return  agencyid;
	}

	private String getAgencyId(String work_id) throws Exception
	{
		RandRMain dObj = null;
		RandRMain WorkCodedObj = null;
		String laId = null;
		try {
			String qry2 ="select work_code  from work where work_id='"+work_id+"'" ;
			WorkCodedObj = (RandRMain)jdbcTemplate.queryForObject(qry2, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
			laId=WorkCodedObj.getWork_code()+"/0001";
			
			String qry3 ="select work_code+'/'+case  " + 
					"when LEN(cast(count(*)+1 as varchar))=1 then '000'+cast(count(*)+1 as varchar) " + 
					"when LEN(cast(count(*)+1 as varchar))=2 then '00'+cast(count(*)+1 as varchar) " + 
					"when LEN(cast(count(*)+1 as varchar))=3 then '0'+cast(count(*)+1 as varchar) " + 
					"else '0' end as agency_id " + 
					"from rr_agency r " + 
					"left join work w on w.work_id=r.work_id_fk " + 
					"where w.work_id='"+work_id+"' " + 
					"group by work_code" ;
			dObj = (RandRMain)jdbcTemplate.queryForObject(qry3, new Object[] {}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
				
					laId = dObj.getAgency_id();
			
		}catch(Exception e){ 
			e.printStackTrace();
		}
	    return laId;		
		
		
		
		
		
	}
	

	@Override
	public String updateRRBSES(RandRMain obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			List<RandRMain> objsList = null;
			String rr_id = null;
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "Update rr_agency set "
					+ "hod= :hod, mrvc_responsible_person = :mrvc_responsible_person, bses_agency_name= :bses_agency_name, "
					+ "agency_responsible_person= :agency_responsible_person, contact_number= :contact_number, email_id=  :bses_email, "
					+ "submission_date_report_ca = :submission_date_report_ca, actual_submission_date_bses_report_to_mrvc= :actual_submission_date_bses_report_to_mrvc"
					+ ", approval_by_mrvc_responsible_person= :approval_by_mrvc_responsible_person,"
					+ "report_submission_date_to_mrvc= :report_submission_date_to_mrvc, approval_date_by_mrvc= :approval_date_by_mrvc "
					+ " where id= :rr_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
					con = dataSource.getConnection();
						if(count > 0) {
							
							String deleteExecutivesQry = "DELETE from rr_appointment_of_committee where rr_agency_id_fk = ?";		 
							insertStmt = con.prepareStatement(deleteExecutivesQry);
							insertStmt.setString(1,obj.getRr_id());
							insertStmt.executeUpdate();
							if(insertStmt != null){insertStmt.close();}
							
							String detailsQry = "INSERT INTO rr_appointment_of_committee"
									+ "( rr_agency_id_fk, date_of_appointment, name_of_representative, phone_no, email_id,committee_name)"
									+ "VALUES"
									+ "(?,?,?,?,?,?)";
							 insertStmt = con.prepareStatement(detailsQry); 
							int arraySize = 0;
							if(!StringUtils.isEmpty(obj.getDate_of_appointments()) && obj.getDate_of_appointments().length > 0 ) {
								obj.setDate_of_appointments(CommonMethods.replaceEmptyByNullInSringArray(obj.getDate_of_appointments()));
								if(arraySize < obj.getDate_of_appointments().length) {
									arraySize = obj.getDate_of_appointments().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getName_of_representatives()) && obj.getName_of_representatives().length > 0 ) {
								obj.setName_of_representatives(CommonMethods.replaceEmptyByNullInSringArray(obj.getName_of_representatives()));
								if(arraySize < obj.getName_of_representatives().length) {
									arraySize = obj.getName_of_representatives().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getPhone_nos()) && obj.getPhone_nos().length > 0 ) {
								obj.setPhone_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getPhone_nos()));
								if(arraySize < obj.getPhone_nos().length) {
									arraySize = obj.getPhone_nos().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getEmail_ids()) && obj.getEmail_ids().length > 0 ) {
								obj.setEmail_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmail_ids()));
								if(arraySize < obj.getEmail_ids().length) {
									arraySize = obj.getEmail_ids().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getCommittee_names()) && obj.getCommittee_names().length > 0 ) {
								obj.setCommittee_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getCommittee_names()));
								if(arraySize < obj.getCommittee_names().length) {
									arraySize = obj.getCommittee_names().length;
								}
							}							
				
							for (int i = 0;  i < arraySize; i++) {
								 if( obj.getDate_of_appointments().length > 0 && !StringUtils.isEmpty(obj.getDate_of_appointments()[i])) {
									int p = 1;
									    insertStmt.setString(p++,(obj.getRr_id()));
									    insertStmt.setString(p++,DateParser.parse((obj.getDate_of_appointments().length > 0)?obj.getDate_of_appointments()[i]:null));
									    insertStmt.setString(p++,((obj.getName_of_representatives().length > 0)?obj.getName_of_representatives()[i]:null));
									    insertStmt.setString(p++,(obj.getPhone_nos().length > 0)?obj.getPhone_nos()[i]:null);
									    insertStmt.setString(p++,(obj.getEmail_ids().length > 0)?obj.getEmail_ids()[i]:null);
									    insertStmt.setString(p++,(obj.getCommittee_names().length > 0)?obj.getCommittee_names()[i]:null);
									    insertStmt.addBatch();
								    }
								  int[] insertCount = insertStmt.executeBatch();
							}
						
						}
						FormHistory formHistory = new FormHistory();
						formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
						formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
						formHistory.setModule_name_fk("RR BSES");
						formHistory.setForm_name("Update RR BSES");
						formHistory.setForm_action_type("Add");
						formHistory.setForm_details("RR BSES "+obj.getRr_id() + " Updated");
						formHistory.setWork_id_fk(obj.getWork_id());
						//formHistory.setContract(obj.getContract_id_fk());
						
						boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					
					
						
				}
			
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return obj.getAgency_id();
	}

	@Override
	public List<RandRMain> getWorkFilterList(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select work_id as work_id_fk, work_short_name from work ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getHodFilterList(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select user_id as hod,user_name,designation from [user] where user_type_fk = 'HOD' ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
}
