package com.synergizglobal.pmis.IMPLdao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Deliverables;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Safety;

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
			String qry ="select id,project_priority_fk,w.work_short_name,c.contract_short_name, d.project_id_fk,p.project_name,contract_id_fk,c.contract_name ,d.work_id_fk,w.work_name,deliverable_type_fk,"
					+ "deliverable_description, status_fk,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,DATE_FORMAT(start_date,'%d-%m-%Y') AS start_date,DATE_FORMAT(finish_date,'%d-%m-%Y') AS finish_date,d.remarks "
					+ " from deliverables d " + 
					"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id  "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id  where id is not null ";
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
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
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
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));

		}catch(Exception e){ 
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
			String qry = "SELECT project_id_fk,p.project_name from deliverables d"
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
			qry = qry + " GROUP BY project_id_fk ";
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
			String qry = "SELECT work_id_fk,w.work_short_name from deliverables d "
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
			qry = qry + " GROUP BY work_id_fk ";
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
			String qry = "SELECT contract_id_fk,c.contract_short_name from deliverables d "
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
			qry = qry + " GROUP BY contract_id_fk ";
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
		Deliverables deliverables = null;
		try {
			String qry = "select id, project_priority_fk, d.project_id_fk,p.project_name,w.work_name,c.contract_name, d.work_id_fk, contract_id_fk, deliverable_type_fk, deliverable_description,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date, DATE_FORMAT(start_date,'%d-%m-%Y') AS start_date,DATE_FORMAT(finish_date,'%d-%m-%Y') AS  finish_date, status_fk, d.remarks from deliverables d " + 
						 "LEFT JOIN work w on d.work_id_fk = w.work_id "
						 +"LEFT JOIN project p on d.project_id_fk = p.project_id "
						 +"LEFT JOIN contract c on d.contract_id_fk = c.contract_id where id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getId())) {
				qry = qry + " and id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getId())) {
				pValues[i++] = obj.getId();
			}
			deliverables = (Deliverables)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));	
			
			if(!StringUtils.isEmpty(deliverables)) {
				String qry2 ="select id, deliverable_id_fk, attachment  from deliverable_files where deliverable_id_fk = ?";
				List<Deliverables> objList = jdbcTemplate.query( qry2,new Object[] {obj.getId()}, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
				deliverables.setDeliverableFilesList(objList);
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
					+ "( project_priority_fk, project_id_fk, work_id_fk, contract_id_fk, deliverable_type_fk, deliverable_description, target_date, start_date, finish_date, status_fk, remarks)"
					+ "VALUES"
					+ "(:project_priority_fk, :project_id_fk, :work_id_fk, :contract_id_fk, :deliverable_type_fk, :deliverable_description, :target_date, :start_date, :finish_date, :status_fk,:remarks)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(insertQry, paramSource, keyHolder);
		    String deliverable_id = null;
			if(count > 0) {
				deliverable_id = String.valueOf(keyHolder.getKey().intValue());
				 obj.setId(deliverable_id);
				 flag = true;
			}
			if(flag){
				if(!StringUtils.isEmpty(obj.getDeliverableFiles()) && obj.getDeliverableFiles().size() > 0) {
					String file_insert_qry = "INSERT into  deliverable_files ( deliverable_id_fk, attachment) VALUES (:id,:attachment)";
					List<MultipartFile> deliverableFiles = obj.getDeliverableFiles();
					for (MultipartFile multipartFile : deliverableFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants.DELIVERABLES_FILE_SAVING_PATH ;
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							Deliverables fileObj = new Deliverables();
							fileObj.setId(deliverable_id);
							fileObj.setAttachment(fileName);
							
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(file_insert_qry, paramSource);
						}
					}
				}	
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Add Deliverable");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Deliverable "+obj.getId() + " Created");
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

	@Override
	public boolean updateDeliverables(Deliverables obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE deliverables set "
					+ "project_priority_fk= :project_priority_fk,deliverable_type_fk = :deliverable_type_fk,deliverable_description= :deliverable_description,target_date= :target_date, start_date= :start_date, finish_date= :finish_date,status_fk= :status_fk,remarks= :remarks "
					+ "where id= :id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
				String deleteFilesQry = "delete from deliverable_files  where deliverable_id_fk = :id";		 
				Deliverables fileObj = new Deliverables();
				fileObj.setId(obj.getId());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
			
				String insert_qry = "INSERT into  deliverable_files ( deliverable_id_fk, attachment) VALUES (:id,:attachment)";
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getDeliverableFileNames()) && obj.getDeliverableFileNames().length > 0 ) {
					obj.setDeliverableFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getDeliverableFileNames()));
					if(arraySize < obj.getDeliverableFileNames().length) {
						arraySize = obj.getDeliverableFileNames().length;
					}
				}
				for (int i = 0; i < arraySize; i++) {
					fileObj = new Deliverables();
					fileObj.setId(obj.getId());
					fileObj.setAttachment(obj.getDeliverableFileNames()[i]);
					paramSource = new BeanPropertySqlParameterSource(fileObj);	
					namedParamJdbcTemplate.update(insert_qry, paramSource);
				}
				
				if(!StringUtils.isEmpty(obj.getDeliverableFiles()) && obj.getDeliverableFiles().size() > 0) {
					List<MultipartFile> fobFiles = obj.getDeliverableFiles();
					for (MultipartFile multipartFile : fobFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants.DELIVERABLES_FILE_SAVING_PATH ;
							String fileName = multipartFile.getOriginalFilename();
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							obj.setAttachment(fileName);
						
							fileObj = new Deliverables();
							fileObj.setId(obj.getId());
							fileObj.setAttachment(fileName);
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(insert_qry, paramSource);
						}
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Update Deliverable");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Deliverable "+obj.getId() + " Updated");
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Deliverables>(Deliverables.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
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
			String qry ="select count(*) as total_records from deliverables d " + 
					"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id  "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id  where id is not null ";
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
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
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
			String qry ="select id,project_priority_fk,w.work_short_name,c.contract_short_name, d.project_id_fk,p.project_name,contract_id_fk,c.contract_name ,d.work_id_fk,w.work_name,deliverable_type_fk,"
					+ "deliverable_description, status_fk,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,DATE_FORMAT(start_date,'%d-%m-%Y') AS start_date,DATE_FORMAT(finish_date,'%d-%m-%Y') AS finish_date,d.remarks"
					+ " from deliverables d " + 
					"LEFT join work w on d.work_id_fk = w.work_id "
					+"LEFT JOIN project p on w.project_id_fk = p.project_id  "
					+"LEFT JOIN contract c on d.contract_id_fk = c.contract_id  where id is not null ";
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
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
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
				qry = qry + " ORDER BY id ASC limit ?,?";
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

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
}
