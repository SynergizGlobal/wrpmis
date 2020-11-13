package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DocumentDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

@Repository
public class DocumentDaoImpl implements DocumentDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Document> documentList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select document_no,d.work_id_fk,w.work_name,contract_id_fk,c.contract_name,project_priority_fk,document_type_fk,document_name,responsible_for_approval from documents d "
					+ "LEFT JOIN work w on d.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					+ "where document_no is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				qry = qry + " and document_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				qry = qry + " and responsible_for_approval = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				pValues[i++] = obj.getDocument_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				pValues[i++] = obj.getResponsible_for_approval();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getDocumentContractsList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_short_name from documents d "
					+ "LEFT JOIN contract c on d.contract_id_fk = c.contract_id  " + 
					"where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				qry = qry + " and document_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				qry = qry + " and responsible_for_approval = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				pValues[i++] = obj.getDocument_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				pValues[i++] = obj.getResponsible_for_approval();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getDocumentProjectPriorityList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry = "SELECT project_priority_fk from documents "
					+"where project_priority_fk is not null and project_priority_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				qry = qry + " and document_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				qry = qry + " and responsible_for_approval = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}	
			
			qry = qry + "GROUP BY project_priority_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				pValues[i++] = obj.getDocument_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				pValues[i++] = obj.getResponsible_for_approval();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getDocumentTypesList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry = "SELECT document_type_fk from documents "
					+ "where document_type_fk is not null and document_type_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				qry = qry + " and responsible_for_approval = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				qry = qry + " and document_type_fk = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY document_type_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				pValues[i++] = obj.getResponsible_for_approval();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				pValues[i++] = obj.getDocument_type_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getDocumentResponsibleForApprovalList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry = "SELECT responsible_for_approval from documents "
					+ "where responsible_for_approval is not null and responsible_for_approval <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				qry = qry + " and project_priority_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				qry = qry + " and document_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				qry = qry + " and responsible_for_approval = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY responsible_for_approval ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_priority_fk())) {
				pValues[i++] = obj.getProject_priority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_type_fk())) {
				pValues[i++] = obj.getDocument_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_for_approval())) {
				pValues[i++] = obj.getResponsible_for_approval();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Document getDocument(Document obj) throws Exception {
		Document sObj =null;
		
		try {
			String qry = "select d.work_id_fk,document_no,c.contract_name,d.project_id_fk,p.project_name,w.work_name,contract_id_fk,document_type_fk,document_name,project_priority_fk,responsible_for_approval "
					+ "from documents d "
					+"LEFT OUTER join contract c on contract_id_fk =c.contract_id " 
					+"LEFT OUTER join work w on d.work_id_fk = w.work_id "  
					+"LEFT OUTER join project p on d.project_id_fk = p.project_id "
					+"where document_no is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_no())) {
				qry = qry + " and document_no = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDocument_no())) {
				pValues[i++] = obj.getDocument_no();
			}
			sObj = (Document)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Document>(Document.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getDocument_no())) {
			
			List<Document> objsList = null;
			String qryDetails = "select document_no_fk,revision_no, status_fk,document_attachment,"
					+" DATE_FORMAT(submission_date,'%d-%m-%Y') AS submission_date,remarks, DATE_FORMAT(approval_date,'%d-%m-%Y') AS approval_date "
					+ "from documents_revisions "
					+"where document_no_fk is not null and document_no_fk = ? ";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getDocument_no()}, new BeanPropertyRowMapper<Document>(Document.class));	
			sObj.setDocuments(objsList);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public List<Document> getStatusList() throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select status as status_fk from documents_revisions_status ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getDocumentTypeList() throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select document_type as document_type_fk from document_type ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getPriorityListList() throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select project_priority as project_priority_fk from project_priority ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getUserList() throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select user_id as responsible_for_approval,user_name from user ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getProjectsList() throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select project_id as project_id_fk  ,project_name from project ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getContractList() throws Exception {
		List<Document> objList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name from contract";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}

}
