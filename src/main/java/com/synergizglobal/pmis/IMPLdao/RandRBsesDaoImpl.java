package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.RandRBsesDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
@Repository
public class RandRBsesDaoImpl implements RandRBsesDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<RRBses> getWorksFilterListInRRBses(RRBses obj) throws Exception {
		List<RRBses> objsList = null;
		try {
			String qry = "SELECT  work_id_fk,w.work_name,w.work_short_name from bses r " + 
					"LEFT JOIN work w on r.work_id_fk = w.work_id "+
					"where r.work_id_fk is not null and r.work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				qry = qry + " and agency_name = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY r.work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				pValues[i++] = obj.getAgency_name();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RRBses> getAgencyNameFilterListInRRBses(RRBses obj) throws Exception {
		List<RRBses> objsList = null;
		try {
			String qry = "SELECT  agency_name from bses r " + 
					"LEFT JOIN work w on r.work_id_fk = w.work_id "+
					"where r.agency_name is not null and r.agency_name <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				qry = qry + " and agency_name = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY r.agency_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				pValues[i++] = obj.getAgency_name();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(RRBses obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from bses r "
					+ "LEFT JOIN work w on r.work_id_fk = w.work_id "
					+ "where bses_id is not null  ";
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				qry = qry + " and agency_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (r.bses_id like ? or r.work_id_fk like ? or work_short_name like ?"
						+ " or agency_name like ? or contract_short_name like ? or contract_id_fk like ? )";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				pValues[i++] = obj.getAgency_name();
			}

			if(!StringUtils.isEmpty(searchParameter)) {
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
	public List<RRBses> getRRBsesList(RRBses obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<RRBses> objsList = null;
		try {
			String qry ="select bses_id, r.work_id_fk, contract_id_fk, agency_name,w.work_short_name,c.contract_short_name, report_submission_to_mrvc, report_submission_to_mmrda, r.remarks from bses r "
					+ "LEFT JOIN work w on r.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on r.contract_id_fk = c.contract_id "
					+ "WHERE bses_id is not null ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				qry = qry + " and agency_name = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (r.bses_id like ? or r.work_id_fk like ? or work_short_name like ?"
						+ " or agency_name like ? or contract_short_name like ? or contract_id_fk like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY bses_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAgency_name())) {
				pValues[i++] = obj.getAgency_name();
			}

			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RRBses> getFileTypeListForRRBsesForm(RRBses obj) throws Exception {
		List<RRBses> objList = null;
		try {
			String qry ="select bses_file_type from bses_file_type ";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RRBses>(RRBses.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<RRBses> getContractsListForRRBsesForm(RRBses obj) throws Exception {
		List<RRBses> objsList = null;
		try {
			String qry ="select contract_id,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RRBses> getWorkListForRRBsesForm(RRBses obj) throws Exception {
		List<RRBses> objsList = new ArrayList<RRBses>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RRBses> getProjectsListForRRBsesForm(RRBses obj) throws Exception {
		List<RRBses> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RRBses>(RRBses.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public RRBses getRRBsesDeatils(RRBses obj) throws Exception {
		RRBses bses = null;
		try {
			String qry = "select bses_id, b.work_id_fk,project_id as project_id_fk,project_name, b.contract_id_fk,w.work_short_name,w.work_name,c.contract_name,c.contract_short_name, agency_name, "
					+ "report_submission_to_mrvc, report_submission_to_mmrda, b.remarks from bses b " + 
					"left join work w on b.work_id_fk = w.work_id " + 
					"left join contract c on b.contract_id_fk = c.contract_id " + 
					"left join project p on w.project_id_fk = p.project_id where bses_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBses_id())) {
				qry = qry + " and bses_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBses_id())) {
				pValues[i++] = obj.getBses_id();
			}
			bses = (RRBses)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<RRBses>(RRBses.class));	
			if(!StringUtils.isEmpty(bses) && !StringUtils.isEmpty(bses.getBses_id())) {
				List<RRBses> objsList = null;
				String qryDetails = "select committee_details_id, bses_id_fk, DATE_FORMAT(date_of_nomination ,'%d-%m-%Y') AS date_of_nomination, committee_details_remarks  "
						+ "from bses_committee_details bcd " 
						+"left join bses b on bcd.bses_id_fk = b.bses_id where bses_id_fk = ?  ORDER BY date_of_nomination DESC";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {bses.getBses_id()}, new BeanPropertyRowMapper<RRBses>(RRBses.class));	
				bses.setDetailsList(objsList);
				if(!StringUtils.isEmpty(objsList)) {
					for(RRBses list : bses.getDetailsList()) {
						String qry2 ="select id, bses_id_fk, committee_details_id_fk, details  from bses_date_of_nomination_details where bses_id_fk = ? "
								+ "and committee_details_id_fk = ? ";
						List<RRBses> objList1 = jdbcTemplate.query( qry2,new Object[] {list.getBses_id_fk(),list.getCommittee_details_id()}, new BeanPropertyRowMapper<RRBses>(RRBses.class));
						list.setComiteDetailsList(objList1);
					}
				}
			}
			if(!StringUtils.isEmpty(bses) && !StringUtils.isEmpty(bses.getBses_id())) {
				List<RRBses> objsList = null;
				String qryDetails = "select id, bses_id_fk, bses_file_type_fk, name, attachment, created_date  "
						+ "from bses_files bf " 
						+"left join bses b on bf.bses_id_fk = b.bses_id where bses_id_fk = ?  ORDER BY created_date DESC";
				objsList = jdbcTemplate.query(qryDetails, new Object[] {bses.getBses_id()}, new BeanPropertyRowMapper<RRBses>(RRBses.class));
				bses.setFilesList(objsList);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
		return bses;
	}

	@Override
	public boolean updateRRBses(RRBses obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			String qry = "UPDATE bses set "
					+ " contract_id_fk= :contract_id_fk, agency_name= :agency_name, report_submission_to_mrvc= :report_submission_to_mrvc"
					+ ", report_submission_to_mmrda= :report_submission_to_mmrda, remarks= :remarks  "
					+ "WHERE  bses_id= :bses_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				
					String docDeleteQry = "DELETE from bses_files where bses_id_fk = :bses_id";		 
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(docDeleteQry, paramSource);
					
					String document_insert_qry = "INSERT into  bses_files ( bses_id_fk, bses_file_type_fk, name, attachment, created_date) VALUES (:bses_id,:bses_file_type_fk,:name,:attachment,CURDATE())";

					int arraySize =0, docArrSize = 0;
			
					if (!StringUtils.isEmpty(obj.getAttachmentFileNames()) && obj.getAttachmentFileNames().length > 0) {
						obj.setAttachmentFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getAttachmentFileNames()));
						if (docArrSize < obj.getAttachmentFileNames().length) {
							docArrSize = obj.getAttachmentFileNames().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getBses_file_type_fks()) && obj.getBses_file_type_fks().length > 0) {
						obj.setBses_file_type_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getBses_file_type_fks()));
						if (docArrSize < obj.getBses_file_type_fks().length) {
							docArrSize = obj.getBses_file_type_fks().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getNames()) && obj.getNames().length > 0) {
						obj.setNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getNames()));
						if (docArrSize < obj.getNames().length) {
							docArrSize = obj.getNames().length;
						}
					}
						
					for (int i = 0; i < docArrSize; i++) {
						if (!StringUtils.isEmpty(obj.getRrFiles()) && obj.getRrFiles().length > 0) {
							MultipartFile multipartFile = obj.getRrFiles()[i];
							if (((null != multipartFile && !multipartFile.isEmpty()) || (obj.getAttachmentFileNames().length > 0 )) 
									&& obj.getBses_file_type_fks().length > 0 && obj.getNames().length > 0 && !StringUtils.isEmpty(obj.getBses_file_type_fks()[i]) 
									&& !StringUtils.isEmpty(obj.getAttachmentFileNames()[i])  && !StringUtils.isEmpty(obj.getNames()[i]))  {
								String saveDirectory =  CommonConstants2.RRBSES_FILE_SAVING_PATH ;
								String fileName = null;
								try {
									 fileName = obj.getAttachmentFileNames()[i];
								}catch(Exception e) {
									fileName = obj.getRrFiles()[i].getOriginalFilename();
								}
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								RRBses fileObj = new RRBses();
								fileObj.setAttachment(fileName);
								
								String fob_file_type_fk = obj.getBses_file_type_fks()[i];
								String name = obj.getNames()[i];
								
								fileObj.setBses_id(obj.getBses_id());
								fileObj.setBses_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(document_insert_qry, paramSource);
							}
						}
					}
					int arrSize = 0;

					if (!StringUtils.isEmpty(obj.getDate_of_nominations()) && obj.getDate_of_nominations().length > 0) {
						obj.setDate_of_nominations(CommonMethods.replaceEmptyByNullInSringArray(obj.getDate_of_nominations()));
						if (arrSize < obj.getDate_of_nominations().length) {
							arrSize = obj.getDate_of_nominations().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getCommittee_details_remarkss()) && obj.getCommittee_details_remarkss().length > 0) {
						obj.setCommittee_details_remarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getCommittee_details_remarkss()));
						if (arrSize < obj.getCommittee_details_remarkss().length) {
							arrSize = obj.getCommittee_details_remarkss().length;
						}
					}
					String committee_details_id = null;
					String detailsDeleteQry = "DELETE from bses_date_of_nomination_details where bses_id_fk = :bses_id";		 
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(detailsDeleteQry, paramSource);
					String committeeDeleteQry = "DELETE from bses_committee_details where bses_id_fk = :bses_id";		 
					paramSource = new BeanPropertySqlParameterSource(obj);		 
					count = namedParamJdbcTemplate.update(committeeDeleteQry, paramSource);
					
					if(!StringUtils.isEmpty(obj.getDate_of_nominations())  && obj.getDate_of_nominations().length > 0) {
						String qry3 = "INSERT into bses_committee_details (bses_id_fk, date_of_nomination, committee_details_remarks) VALUES (:bses_id,:date_of_nomination,:committee_details_remarks)";
						
						for (int a = 0; a < arrSize; a++){
							RRBses fileObj1 = new RRBses();
							fileObj1.setBses_id(obj.getBses_id());
							fileObj1.setDate_of_nomination(DateParser.parse(obj.getDate_of_nominations()[a]));
							fileObj1.setCommittee_details_remarks(obj.getCommittee_details_remarkss()[a]);
							paramSource = new BeanPropertySqlParameterSource(fileObj1);
							KeyHolder keyHolder = new GeneratedKeyHolder();
							count = namedParamJdbcTemplate.update(qry3, paramSource, keyHolder);	
							if(count > 0) {
								committee_details_id = String.valueOf(keyHolder.getKey().intValue());
								obj.setCommittee_details_id_fk(committee_details_id);
								flag = true;
							}
							
							int size = 0;
							if(!StringUtils.isEmpty(obj.getDetailss()) && obj.getDetailss().length > 0) {
		    					obj.setDetailss(CommonMethods.replaceEmptyByNullInSringArray(obj.getDetailss()));
		    					if(size < obj.getDetailss().length) {
		    						size = obj.getDetailss().length;
		    					}
		    				}
							
							String qry4 = "INSERT into bses_date_of_nomination_details (bses_id_fk, committee_details_id_fk, details) VALUES (:bses_id,:committee_details_id_fk,:details)";

								List<String> executives = null;
								if(!StringUtils.isEmpty(obj.getDetailss()[a])){
									if(obj.getDetailss()[a].contains(",_,")) {
										executives = new ArrayList<String>(Arrays.asList(obj.getDetailss()[a].split(",_,")));
									}else {
										executives = new ArrayList<String>(Arrays.asList(obj.getDetailss()[a]));
									}
									for(String eObj : executives) {
										if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getDetailss()[a]) &&  !StringUtils.isEmpty(eObj)) {
											RRBses fileObj = new RRBses();
											fileObj.setBses_id(obj.getBses_id());
											fileObj.setCommittee_details_id_fk(obj.getCommittee_details_id_fk());
											fileObj.setDetails(eObj);
											paramSource = new BeanPropertySqlParameterSource(fileObj);
											namedParamJdbcTemplate.update(qry4, paramSource);
										}
									}
								}
						}
					}	
					
					/********************************************************************************/
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("R & R");
					formHistory.setForm_name("Update RR Bses");
					formHistory.setForm_action_type("Update");
					formHistory.setForm_details("RR Bses "+obj.getBses_id() + " Updated");
					formHistory.setWork(obj.getWork_id_fk());
					formHistory.setContract(obj.getContract_id_fk());
					
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
	public boolean addRRBses(RRBses obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			String bses_id= null;
			String qry = "INSERT INTO bses"
					+ "( work_id_fk, contract_id_fk, agency_name, report_submission_to_mrvc, report_submission_to_mmrda, remarks) "
					+ "VALUES "
					+ "(:work_id_fk,:contract_id_fk,:agency_name,:report_submission_to_mrvc,:report_submission_to_mmrda,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);			
			if(count > 0) {
				bses_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setBses_id(bses_id);
				flag = true;
			}
			if(flag) {
				
					String document_insert_qry = "INSERT into  bses_files ( bses_id_fk, bses_file_type_fk, name, attachment, created_date) VALUES (:bses_id,:bses_file_type_fk,:name,:attachment,CURDATE())";

					int arraySize =0, docArrSize = 0;
			
					if (!StringUtils.isEmpty(obj.getAttachmentFileNames()) && obj.getAttachmentFileNames().length > 0) {
						obj.setAttachmentFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getAttachmentFileNames()));
						if (docArrSize < obj.getAttachmentFileNames().length) {
							docArrSize = obj.getAttachmentFileNames().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getBses_file_type_fks()) && obj.getBses_file_type_fks().length > 0) {
						obj.setBses_file_type_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getBses_file_type_fks()));
						if (docArrSize < obj.getBses_file_type_fks().length) {
							docArrSize = obj.getBses_file_type_fks().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getNames()) && obj.getNames().length > 0) {
						obj.setNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getNames()));
						if (docArrSize < obj.getNames().length) {
							docArrSize = obj.getNames().length;
						}
					}
				
					for (int i = 0; i < docArrSize; i++) {
						if (!StringUtils.isEmpty(obj.getRrFiles()) && obj.getRrFiles().length > 0) {
							MultipartFile multipartFile = obj.getRrFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty()) 
									&& obj.getBses_file_type_fks().length > 0  && obj.getNames().length > 0 
									&& !StringUtils.isEmpty(obj.getBses_file_type_fks()[i]) && !StringUtils.isEmpty(obj.getNames()[i]))  {
								String saveDirectory =  CommonConstants2.RRBSES_FILE_SAVING_PATH ;
								String fileName = obj.getRrFiles()[i].getOriginalFilename();
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								RRBses fileObj = new RRBses();
								fileObj.setAttachment(fileName);
								
								String fob_file_type_fk = obj.getBses_file_type_fks()[i];
								String name = obj.getNames()[i];
								
								fileObj.setBses_id(obj.getBses_id());
								fileObj.setBses_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(document_insert_qry, paramSource);
							}
						}
					}
					int arrSize = 0;

					if (!StringUtils.isEmpty(obj.getDate_of_nominations()) && obj.getDate_of_nominations().length > 0) {
						obj.setDate_of_nominations(CommonMethods.replaceEmptyByNullInSringArray(obj.getDate_of_nominations()));
						if (arrSize < obj.getDate_of_nominations().length) {
							arrSize = obj.getDate_of_nominations().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getCommittee_details_remarkss()) && obj.getCommittee_details_remarkss().length > 0) {
						obj.setCommittee_details_remarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getCommittee_details_remarkss()));
						if (arrSize < obj.getCommittee_details_remarkss().length) {
							arrSize = obj.getCommittee_details_remarkss().length;
						}
					}
					String committee_details_id = null;
					if(!StringUtils.isEmpty(obj.getDate_of_nominations())  && obj.getDate_of_nominations().length > 0) {
						String qry3 = "INSERT into bses_committee_details (bses_id_fk, date_of_nomination, committee_details_remarks) VALUES (:bses_id,:date_of_nomination,:committee_details_remarks)";
						
						for (int a = 0; a < arrSize; a++){
							String remarks = null;
							try {
								remarks = obj.getCommittee_details_remarkss()[a];
							}catch(Exception e) {
								remarks = null;
							}
							RRBses fileObj1 = new RRBses();
							fileObj1.setBses_id(obj.getBses_id());
							fileObj1.setDate_of_nomination(DateParser.parse(obj.getDate_of_nominations()[a]));
							fileObj1.setCommittee_details_remarks(remarks);
							paramSource = new BeanPropertySqlParameterSource(fileObj1);
							keyHolder = new GeneratedKeyHolder();
							count = namedParamJdbcTemplate.update(qry3, paramSource, keyHolder);	
							
							if(count > 0) {
								committee_details_id = String.valueOf(keyHolder.getKey().intValue());
								obj.setCommittee_details_id_fk(committee_details_id);
								flag = true;
							}
							
							int size = 0;
							if(!StringUtils.isEmpty(obj.getDetailss()) && obj.getDetailss().length > 0) {
		    					obj.setDetailss(CommonMethods.replaceEmptyByNullInSringArray(obj.getDetailss()));
		    					if(size < obj.getDetailss().length) {
		    						size = obj.getDetailss().length;
		    					}
		    				}
							String qry4 = "INSERT into bses_date_of_nomination_details (bses_id_fk, committee_details_id_fk, details) VALUES (:bses_id,:committee_details_id_fk,:details)";

								List<String> executives = null;
								if(!StringUtils.isEmpty(obj.getDetailss()[a])){
									if(obj.getDetailss()[a].contains(",_,")) {
										executives = new ArrayList<String>(Arrays.asList(obj.getDetailss()[a].split(",_,")));
									}else {
										executives = new ArrayList<String>(Arrays.asList(obj.getDetailss()[a]));
									}
									for(String eObj : executives) {
										if(!eObj.equals("null") && !StringUtils.isEmpty(obj.getDetailss()[a]) &&  !StringUtils.isEmpty(eObj)) {
											RRBses fileObj = new RRBses();
											fileObj.setBses_id(obj.getBses_id());
											fileObj.setCommittee_details_id_fk(obj.getCommittee_details_id_fk());
											fileObj.setDetails(eObj);
											paramSource = new BeanPropertySqlParameterSource(fileObj);
											namedParamJdbcTemplate.update(qry4, paramSource);
										}
									}
								}
						}
					}	
					
					/********************************************************************************/
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("R & R");
					formHistory.setForm_name("Add RR Bses");
					formHistory.setForm_action_type("Add");
					formHistory.setForm_details("New RR Bses "+obj.getBses_id() + " Created");
					formHistory.setWork(obj.getWork_id_fk());
					formHistory.setContract(obj.getContract_id_fk());
					
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


}
