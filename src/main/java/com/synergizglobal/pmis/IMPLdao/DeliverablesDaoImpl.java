package com.synergizglobal.pmis.IMPLdao;

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

import com.synergizglobal.pmis.Idao.DeliverablesDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Deliverables;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.UtilityShifting;

@Repository
public class DeliverablesDaoImpl implements DeliverablesDao{

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<Deliverables> getDeliverablesList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select deliverable_id,w.work_short_name,c.contract_short_name, d.project_id_fk,p.project_name,d.contract_id_fk,c.contract_name ,d.work_id_fk,w.work_name,deliverable_type_fk,"
					+"deliverable_description,d.status_fk,d.milestone_id,d.milestone_payment,cm.milestone_id,cm.milestone_name, "
					+"d.created_date,d.created_by,d.updated_date,d.updated_by "
					+"from deliverables d " 
					+"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+"LEFT JOIN contract_milestones cm on d.contract_id_fk = cm.contract_id_fk and d.milestone_id = cm.milestone_id "
					+"where deliverable_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and d.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and d.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and d.status_fk = ?";
				arrSize++;
			}	
			qry = qry + " ORDER BY deliverable_id ASC";
				
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		    if(!StringUtils.isEmpty(objsList) && objsList.size() > 0) {
		    	for (Deliverables deliverables : objsList) {
					String qry2 ="select deliverable_document_id,deliverable_document_name,"
							+ "FORMAT(original_due_date,'dd-MM-yyyy') as original_due_date,"
							+ "FORMAT(revised_due_date,'dd-MM-yyyy') as revised_due_date,"
							+ "FORMAT(submission_date,'dd-MM-yyyy') as submission_date,"
							+ "FORMAT(approval_date,'dd-MM-yyyy') as approval_date,"
							+ "payment,remarks,deliverable_document_file_name,"
							+ "created_date,created_by,updated_date,updated_by,deliverable_id_fk "
							+ "from deliverable_documents where deliverable_id_fk = ?";
					List<Deliverables> objList = jdbcTemplate.query( qry2,new Object[] {deliverables.getDeliverable_id()}, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
					deliverables.setDeliverableDocuments(objList);
					
					String maxDueDateQry ="SELECT FORMAT(MAX(UpdateDate),'dd-MM-yyyy') AS LastUpdateDate " 
							+ "FROM "
							+ "(SELECT deliverable_id_fk, original_due_date AS UpdateDate "  
							+ "FROM deliverable_documents " 
							+ "	UNION " 
							+ "SELECT deliverable_id_fk, revised_due_date AS UpdateDate " 
							+ "FROM deliverable_documents "
							+ ") ud " 
							+ "where deliverable_id_fk = ? GROUP BY deliverable_id_fk";
					List<String> maxDueDateList = jdbcTemplate.queryForList( maxDueDateQry,new Object[]{deliverables.getDeliverable_id()}, String.class);
					
					if(!StringUtils.isEmpty(maxDueDateList) && maxDueDateList.size() > 0) {
						deliverables.setDue_date(maxDueDateList.get(0));
					}
					String maxApprovalDateQry ="SELECT FORMAT(MAX(approval_date),'dd-MM-yyyy') " 
							+ "FROM "
							+ "deliverable_documents " 
							+ "where deliverable_id_fk = ? GROUP BY deliverable_id_fk";
					List<String> maxApprovalDateList = jdbcTemplate.queryForList( maxApprovalDateQry,new Object[]{deliverables.getDeliverable_id()}, String.class);
					if(!StringUtils.isEmpty(maxApprovalDateList) && maxApprovalDateList.size() > 0) {
						deliverables.setApproval_date(maxApprovalDateList.get(0));
					}
			    }
		    	
			}

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverablesStatusList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry = "SELECT status_fk from deliverables where status_fk is not null and status_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverablesProjectsList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry = "SELECT distinct project_id_fk, p.project_name from deliverables d"
					+ "LEFT JOIN project p on project_id_fk = p.project_id "
					+ "where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY project_id_fk,project_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverablesWorksList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry = "SELECT distinct work_id_fk,w.work_short_name from deliverables d "
					+ "LEFT JOIN work w on d.work_id_fk = w.work_id "
					+ " where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and d.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry + " GROUP BY work_id_fk,work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverablesContarctsList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry = "SELECT distinct contract_id_fk,c.contract_short_name from deliverables d "
					+ "LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+ " where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and d.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY contract_id_fk,contract_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getStatusList() throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select status as status_fk from deliverables_status ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverableTypeList() throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select deliverable_type as deliverable_type_fk from deliverable_type ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getPriorityList() throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select project_priority as project_priority_fk from project_priority ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getProjectsListForDeliverablesForm(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select project_id as project_id_fk  ,project_name from project order by project_id asc";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Deliverables getDeliverables(Deliverables obj) throws Exception {
		Deliverables deliverables = new Deliverables();;
		try {
			String qry ="select deliverable_id,w.work_short_name,c.contract_short_name, d.project_id_fk,p.project_name,d.contract_id_fk,c.contract_name ,d.work_id_fk,w.work_name,deliverable_type_fk,"
					+"deliverable_description,d.status_fk,d.milestone_id,d.milestone_payment,cm.milestone_id,cm.milestone_name, "
					+"d.created_date,d.created_by,d.updated_date,d.updated_by "
					+"from deliverables d " 
					+"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+"LEFT JOIN contract_milestones cm on d.contract_id_fk = cm.contract_id_fk and d.milestone_id = cm.milestone_id "
					+"where deliverable_id = ? ";
			
			List<Deliverables> deliverablesList  = jdbcTemplate.query(qry, new Object[]{obj.getDeliverable_id()}, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
			
			if(!StringUtils.isEmpty(deliverablesList) && deliverablesList.size() > 0) {
				deliverables = deliverablesList.get(0);
				String qry2 ="select deliverable_document_id,deliverable_document_name,"
						+ "FORMAT(original_due_date,'dd-MM-yyyy') as original_due_date,"
						+ "FORMAT(revised_due_date,'dd-MM-yyyy') as revised_due_date,"
						+ "FORMAT(submission_date,'dd-MM-yyyy') as submission_date,"
						+ "FORMAT(approval_date,'dd-MM-yyyy') as approval_date,"
						+ "payment,remarks,deliverable_document_file_name,"
						+ "created_date,created_by,updated_date,updated_by,deliverable_id_fk "
						+ "from deliverable_documents where deliverable_id_fk = ?";
				List<Deliverables> objList = jdbcTemplate.query( qry2,new Object[] {deliverables.getDeliverable_id()}, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
				deliverables.setDeliverableDocuments(objList);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
		return deliverables;
	}

	@Override
	public boolean addDeliverables(Deliverables obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO deliverables"
					+ "(project_id_fk, work_id_fk, contract_id_fk,milestone_id, deliverable_type_fk, deliverable_description, status_fk,milestone_payment,created_date,created_by)"
					+ "VALUES"
					+ "(:project_id_fk, :work_id_fk, :contract_id_fk,:milestone_id, :deliverable_type_fk, :deliverable_description,:status_fk,:milestone_payment,CURRENT_TIMESTAMP,:user_name)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(insertQry, paramSource, keyHolder);
		    String deliverable_id = null;
			if(count > 0) {
				 deliverable_id = String.valueOf(keyHolder.getKey().intValue());
				 obj.setDeliverable_id(deliverable_id);
				 flag = true;
			}
			if(flag){
				if(!StringUtils.isEmpty(obj.getDeliverable_document_names()) && obj.getDeliverable_document_names().length > 0) {
					String deliverable_document_id = getAutoGeneratedDeliverableDocumentId(obj);
					String insert_qry = "INSERT into  deliverable_documents "
							+ "(deliverable_document_id,deliverable_document_name,original_due_date,revised_due_date,submission_date,approval_date,payment,remarks,deliverable_document_file_name,"
							+ "created_date,created_by,deliverable_id_fk)"
							+ "VALUES "
							+ "(:deliverable_document_id,:deliverable_document_name,:original_due_date,:revised_due_date,:submission_date,:approval_date,:payment,:remarks,:deliverable_document_file_name,"
							+ ":created_date,:created_by,:deliverable_id_fk)";
					String[] deliverable_document_ids = obj.getDeliverable_document_ids();
					String[] deliverable_document_names = obj.getDeliverable_document_names();
					String[] original_due_dates = obj.getOriginal_due_dates();
					String[] revised_due_dates = obj.getRevised_due_dates();
					String[] submission_dates = obj.getSubmission_dates();
					String[] approval_dates = obj.getApproval_dates();
					String[] payments = obj.getPayments();
					String[] remarkss = obj.getRemarkss();
					MultipartFile[] multipartFiles = obj.getDeliverableDocumentFiles();
					String[] deliverable_document_file_names = obj.getDeliverable_document_file_names();
					int length = 0;
					if(!StringUtils.isEmpty(deliverable_document_names)) {
						length = deliverable_document_names.length;
					}
					
					for (int i=0; i<length; i++) {
						Deliverables deliverableDocument = new Deliverables();
						
						deliverableDocument.setDeliverable_document_id(deliverable_document_id);
						
						deliverableDocument.setDeliverable_id_fk(deliverable_id);
						if(!StringUtils.isEmpty(deliverable_document_names) && deliverable_document_names.length > 0) {
							deliverableDocument.setDeliverable_document_name(deliverable_document_names[i]);
						}
						if(!StringUtils.isEmpty(original_due_dates) && original_due_dates.length > 0) {
							deliverableDocument.setOriginal_due_date(DateParser.parse(original_due_dates[i]));
							
						}
						if(!StringUtils.isEmpty(revised_due_dates) && revised_due_dates.length > 0) {
							deliverableDocument.setRevised_due_date(DateParser.parse(revised_due_dates[i]));
						}
						if(!StringUtils.isEmpty(submission_dates) && submission_dates.length > 0) {
							deliverableDocument.setSubmission_date(DateParser.parse(submission_dates[i]));
						}
						if(!StringUtils.isEmpty(approval_dates) && approval_dates.length > 0) {
							deliverableDocument.setApproval_date(DateParser.parse(approval_dates[i]));
						}
						if(!StringUtils.isEmpty(payments) && payments.length > 0 && !StringUtils.isEmpty(payments[i])) {
							deliverableDocument.setPayment(payments[i]);
						}
						if(!StringUtils.isEmpty(remarkss) && remarkss.length > 0) {
							deliverableDocument.setRemarks(remarkss[i]);
						}
						
						if(!StringUtils.isEmpty(multipartFiles) && multipartFiles.length > 0) {
							MultipartFile multipartFile = multipartFiles[i];
							if (null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants.DELIVERABLES_FILE_SAVING_PATH ;
								String fileName = multipartFile.getOriginalFilename();
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								deliverableDocument.setDeliverable_document_file_name(fileName);
							}else {
								if(!StringUtils.isEmpty(deliverable_document_file_names) && deliverable_document_file_names.length > 0) {
									deliverableDocument.setDeliverable_document_file_name(deliverable_document_file_names[i]);
								}
							}
						}
						paramSource = new BeanPropertySqlParameterSource(deliverableDocument);	
						namedParamJdbcTemplate.update(insert_qry, paramSource);
					}
				}	
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Add Deliverable");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Deliverable "+obj.getDeliverable_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
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
	
	private String getAutoGeneratedDeliverableDocumentId(Deliverables obj) throws Exception {
		String deliverable_document_id = obj.getContract_id_fk()+"-"+obj.getMilestone_id()+"-0001";
		try {
			String contract_id = obj.getContract_id_fk();
			String milestone_id = "";
			if(!StringUtils.isEmpty(obj.getMilestone_id())) {
				milestone_id = obj.getMilestone_id().replaceAll(" ", "");
			}
			String contract_id_milestone_id = contract_id+"-"+milestone_id+"-";
			String qry ="SELECT deliverable_document_id FROM deliverable_documents where deliverable_document_id like '"+contract_id_milestone_id+"%' " ;
			List<Deliverables> objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
			if(!StringUtils.isEmpty(objsList) && objsList.size() > 0){
				qry = "select Max(SUBSTRING( deliverable_document_id , LEN(deliverable_document_id) -  CHARINDEX('-',REVERSE(deliverable_document_id)) + 2  , LEN(deliverable_document_id)  ))+1 as deliverable_document_id from deliverable_documents where deliverable_document_id like '"+contract_id_milestone_id+"%' ";
				String incremented_number = jdbcTemplate.queryForObject(qry, new Object[]{}, String.class);
				if(!StringUtils.isEmpty(incremented_number)) {
					if(incremented_number.length() == 1) {
						deliverable_document_id = contract_id_milestone_id+"000"+incremented_number;
					}else if(incremented_number.length() == 2) {
						deliverable_document_id = contract_id_milestone_id+"00"+incremented_number;
					}else if(incremented_number.length() == 3) {
						deliverable_document_id = contract_id_milestone_id+"0"+incremented_number;
					}else if(incremented_number.length() == 4) {
						deliverable_document_id = contract_id_milestone_id+incremented_number;
					}else {
						deliverable_document_id = contract_id_milestone_id+incremented_number;
					}
				}
			}		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
	    return deliverable_document_id;
	}

	@Override
	public boolean updateDeliverables(Deliverables obj) throws Exception {
		boolean flag = false;
		/*TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);*/
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE deliverables SET "
					+ "project_id_fk = :project_id_fk, work_id_fk = :work_id_fk, contract_id_fk = :contract_id_fk,milestone_id = :milestone_id, "
					+ "deliverable_type_fk = :deliverable_type_fk, deliverable_description = :deliverable_description,status_fk = :status_fk,milestone_payment = :milestone_payment,"
					+ "updated_date = CURRENT_TIMESTAMP,updated_by = :user_name "
					+ "WHERE deliverable_id = :deliverable_id";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);
			if(count > 0) {
				 flag = true;
			}
			if(flag){
				
				String deleteProgressDataQry = "delete from deliverable_documents where deliverable_id_fk = :deliverable_id";
				paramSource = new BeanPropertySqlParameterSource(obj);	
				namedParamJdbcTemplate.update(deleteProgressDataQry, paramSource);
				
				if(!StringUtils.isEmpty(obj.getDeliverable_document_names()) && obj.getDeliverable_document_names().length > 0) {
					
					String insert_qry = "INSERT into  deliverable_documents "
							+ "(deliverable_document_id,deliverable_document_name,original_due_date,revised_due_date,submission_date,approval_date,payment,remarks,deliverable_document_file_name,"
							+ "created_date,created_by,deliverable_id_fk)"
							+ "VALUES "
							+ "(:deliverable_document_id,:deliverable_document_name,:original_due_date,:revised_due_date,:submission_date,:approval_date,:payment,:remarks,:deliverable_document_file_name,"
							+ ":created_date,:created_by,:deliverable_id_fk)";
					String[] deliverable_document_ids = obj.getDeliverable_document_ids();
					String[] deliverable_document_names = obj.getDeliverable_document_names();
					String[] original_due_dates = obj.getOriginal_due_dates();
					String[] revised_due_dates = obj.getRevised_due_dates();
					String[] submission_dates = obj.getSubmission_dates();
					String[] approval_dates = obj.getApproval_dates();
					String[] payments = obj.getPayments();
					String[] remarkss = obj.getRemarkss();
					MultipartFile[] multipartFiles = obj.getDeliverableDocumentFiles();
					String[] deliverable_document_file_names = obj.getDeliverable_document_file_names();
					int length = 0;
					if(!StringUtils.isEmpty(deliverable_document_names)) {
						length = deliverable_document_names.length;
					}
					
					for (int i=0; i<length; i++) {
						Deliverables deliverableDocument = new Deliverables();
						if(!StringUtils.isEmpty(deliverable_document_ids) && deliverable_document_ids.length > 0) {
							if(!StringUtils.isEmpty(deliverable_document_ids[i])) {
								deliverableDocument.setDeliverable_document_id(deliverable_document_ids[i]);
							}else {
								String deliverable_document_id = getAutoGeneratedDeliverableDocumentId(obj);								
								deliverableDocument.setDeliverable_document_id(deliverable_document_id);
							}
						}else {
							String deliverable_document_id = getAutoGeneratedDeliverableDocumentId(obj);
							deliverableDocument.setDeliverable_document_id(deliverable_document_id);
						}

						deliverableDocument.setDeliverable_id_fk(obj.getDeliverable_id());
						if(!StringUtils.isEmpty(deliverable_document_names) && deliverable_document_names.length > 0) {
							deliverableDocument.setDeliverable_document_name(deliverable_document_names[i]);
						}
						if(!StringUtils.isEmpty(original_due_dates) && original_due_dates.length > 0) {
							deliverableDocument.setOriginal_due_date(DateParser.parse(original_due_dates[i]));
						}
						if(!StringUtils.isEmpty(revised_due_dates) && revised_due_dates.length > 0) {
							deliverableDocument.setRevised_due_date(DateParser.parse(revised_due_dates[i]));
						}
						if(!StringUtils.isEmpty(submission_dates) && submission_dates.length > 0) {
							deliverableDocument.setSubmission_date(DateParser.parse(submission_dates[i]));
						}
						if(!StringUtils.isEmpty(approval_dates) && approval_dates.length > 0) {
							deliverableDocument.setApproval_date(DateParser.parse(approval_dates[i]));
						}
						if(!StringUtils.isEmpty(payments) && payments.length > 0 && !StringUtils.isEmpty(payments[i])) {
							deliverableDocument.setPayment(payments[i]);
						}
						if(!StringUtils.isEmpty(remarkss) && remarkss.length > 0) {
							deliverableDocument.setRemarks(remarkss[i]);
						}
						
						if(!StringUtils.isEmpty(multipartFiles) && multipartFiles.length > 0) {
							MultipartFile multipartFile = multipartFiles[i];
							if (null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants.DELIVERABLES_FILE_SAVING_PATH ;
								String fileName = multipartFile.getOriginalFilename();
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								deliverableDocument.setDeliverable_document_file_name(fileName);
							}else {
								if(!StringUtils.isEmpty(deliverable_document_file_names) && deliverable_document_file_names.length > 0) {
									deliverableDocument.setDeliverable_document_file_name(deliverable_document_file_names[i]);
								}
							}
						}
						paramSource = new BeanPropertySqlParameterSource(deliverableDocument);	
						namedParamJdbcTemplate.update(insert_qry, paramSource);
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Update Deliverable");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Deliverable "+obj.getDeliverable_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean deleteDeliverables(Deliverables obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Deliverables> getWorkListForDeliverablesForm(Deliverables obj) throws Exception {
		List<Deliverables> objsList = new ArrayList<Deliverables>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	


	@Override
	public List<Deliverables> getContractMilestonesListForDeliverablesForm(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select contract_milestones_id as contract_milestones_id_fk,milestone_id,milestone_name "
					+ "from contract_milestones "
					+ "where contract_id_fk is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id_fk asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getContractsListForDeliverablesForm(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select c.contract_id as contract_id_fk,c.contract_name,c.contract_short_name,c.work_id_fk "
					+ "from contract c "
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
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Deliverables obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records "
					+ "from deliverables d " 
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id "
					+"LEFT JOIN contract_milestones cm on d.contract_id_fk = cm.contract_id_fk and d.milestone_id = cm.milestone_id "
					+"where deliverable_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and d.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and d.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and d.status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and ( d.project_id_fk like ? or p.project_name like ? or d.work_id_fk like ? or w.work_short_name like ? or d.contract_id_fk like ? "
						+ " or c.contract_short_name like ? or deliverable_type_fk like ? or deliverable_description like ? or status_fk like ?)";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
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
	public List<Deliverables> getDeliverablesList(Deliverables obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select deliverable_id,w.work_short_name,c.contract_short_name, d.project_id_fk,p.project_name,d.contract_id_fk,c.contract_name ,d.work_id_fk,w.work_name,deliverable_type_fk,"
					+"deliverable_description,d.status_fk,d.milestone_id,d.milestone_payment,cm.milestone_id,cm.milestone_name, "
					+"d.created_date,d.created_by,d.updated_date,d.updated_by "
					+"from deliverables d " 
					+"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+"LEFT JOIN contract_milestones cm on d.contract_id_fk = cm.contract_id_fk and d.milestone_id = cm.milestone_id "
					+"where deliverable_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and d.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and d.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and d.status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and ( d.project_id_fk like ? or p.project_name like ? or d.work_id_fk like ? or w.work_short_name like ? or d.contract_id_fk like ? "
						+ " or c.contract_short_name like ? or deliverable_type_fk like ? or deliverable_description like ? or status_fk like ?)";
				arrSize++;
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
				qry = qry + " ORDER BY deliverable_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
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
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
		    if(!StringUtils.isEmpty(objsList) && objsList.size() > 0) {
		    	for (Deliverables deliverables : objsList) {
					String qry2 ="select deliverable_document_id,deliverable_document_name,"
							+ "FORMAT(original_due_date,'dd-MM-yyyy') as original_due_date,"
							+ "FORMAT(revised_due_date,'dd-MM-yyyy') as revised_due_date,"
							+ "FORMAT(submission_date,'dd-MM-yyyy') as submission_date,"
							+ "FORMAT(approval_date,'dd-MM-yyyy') as approval_date,"
							+ "payment,remarks,deliverable_document_file_name,"
							+ "created_date,created_by,updated_date,updated_by,deliverable_id_fk "
							+ "from deliverable_documents where deliverable_id_fk = ?";
					List<Deliverables> objList = jdbcTemplate.query( qry2,new Object[] {deliverables.getDeliverable_id()}, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
					deliverables.setDeliverableDocuments(objList);
			    }
			}

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Deliverables> getDeliverablesConractMilestonesList(Deliverables obj) throws Exception {
		List<Deliverables> objsList = null;
		try {
			String qry ="select contract_milestones_id as contract_milestones_id_fk,"
					+ "cm.contract_id_fk,c.work_id_fk,"
					+ "c.contract_id,c.contract_short_name,c.contract_name,"
					+ "w.work_id,w.work_short_name,w.work_name,"
					+ "p.project_id,p.project_name,w.project_id_fk,"
					+ "milestone_id,milestone_name "
					+ "from contract_milestones cm "
					+ "LEFT JOIN contract c ON cm.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p ON w.project_id_fk = p.project_id "
					+ "where cm.contract_id_fk is not null ";
			
			int arrSize = 0;	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and cm.contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by cm.contract_id_fk asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
}
