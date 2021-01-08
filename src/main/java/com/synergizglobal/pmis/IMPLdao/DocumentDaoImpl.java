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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.DocumentDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Document;


@Repository
public class DocumentDaoImpl implements DocumentDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Document> getDocumentsList(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select document_no,d.work_id_fk,w.work_name,w.work_short_name,d.project_id_fk,p.project_name,contract_id_fk,c.contract_short_name,project_priority_fk,document_type_fk,document_name,responsible_for_approval from documents d "
					+ "LEFT JOIN project p on d.project_id_fk = p.project_id "
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
			String qryDetails = "select id,document_no_fk,revision_no, status_fk,document_attachment,"
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
			String qry ="select user_id,user_name,designation  as responsible_for_approval from user where designation  is not null and designation <>'' ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Document> getProjectsListForDocumentForm(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select project_id as project_id_fk  ,project_name from project order by project_id asc ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Document>(Document.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addDocument(Document obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		int  count = 0;
		boolean flag = false;		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insertQry = "INSERT INTO documents"
					+ "(contract_id_fk, project_id_fk, work_id_fk, project_priority_fk, document_type_fk, document_name,responsible_for_approval)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			
			
			int q = 1;
			insertStmt.setString(q++,obj.getContract_id_fk()); 
			insertStmt.setString(q++,obj.getProject_id_fk()); 
			insertStmt.setString(q++,obj.getWork_id_fk()); 
			insertStmt.setString(q++,obj.getProject_priority_fk()); 
			insertStmt.setString(q++,obj.getDocument_type_fk()); 
			insertStmt.setString(q++,obj.getDocument_name()); 
			insertStmt.setString(q++,obj.getResponsible_for_approval()); 
			
			count = insertStmt.executeUpdate();
			rs = insertStmt.getGeneratedKeys();
			if(count > 0) {
				 flag = true;
				 if (rs.next()) {
					 String id = rs.getString(1);
					 obj.setDocument_no(id);
				 }
			}
			if(flag) {
				
				String insertQry1 = "INSERT into  documents_revisions (document_no_fk,revision_no,status_fk,"
						+"submission_date,approval_date,remarks,document_attachment) "
						+"VALUES (?,?,?,?,?,?,?)";
				insertStmt = con.prepareStatement(insertQry1);
				int arraySize = 0;
				
				if(!StringUtils.isEmpty(obj.getRevision_nos()) && obj.getRevision_nos().length > 0) {
					obj.setRevision_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevision_nos()));
					if(arraySize < obj.getRevision_nos().length) {
						arraySize = obj.getRevision_nos().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getStatus_fks()) && obj.getStatus_fks().length > 0) {
					obj.setStatus_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatus_fks()));
					if(arraySize < obj.getStatus_fks().length) {
						arraySize = obj.getStatus_fks().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
					obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
					if(arraySize < obj.getRemarkss().length) {
						arraySize = obj.getRemarkss().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getSubmission_dates()) && obj.getSubmission_dates().length > 0) {
					obj.setSubmission_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getSubmission_dates()));
					if(arraySize < obj.getSubmission_dates().length) {
						arraySize = obj.getSubmission_dates().length;
					}
				}
				if(!StringUtils.isEmpty(obj.getApproval_dates()) && obj.getApproval_dates().length > 0) {
					obj.setApproval_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getApproval_dates()));
					if(arraySize < obj.getApproval_dates().length) {
						arraySize = obj.getApproval_dates().length;
					}
				}
				String[] documentNames = new String[arraySize];
				if(!StringUtils.isEmpty(obj.getDocumentsFile()) && obj.getDocumentsFile().length > 0) {
					if(arraySize < obj.getDocumentsFile().length) {
							arraySize = obj.getDocumentsFile().length;
					}
					String saveDirectory = CommonConstants.DOCUMENT_FILES_SAVING_PATH ;
					documentNames = new String[arraySize];
					for (int k = 0; k < documentNames.length; k++) {
						/*if (rs.next()) {
							String id = rs.getString(1);
							obj.setDocument_no(id);
						}*/
						MultipartFile file = obj.getDocumentsFile()[k];
						if (null != file && !file.isEmpty()){
							String fileName = file.getOriginalFilename();
							//DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							//String fileName_new = "Document-"+obj.getSafety_equipment_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							documentNames[k] = fileName;
							FileUploads.singleFileSaving(file, saveDirectory, fileName);
							obj.setDocument_attachment(fileName);
						}else {
							documentNames[k] = null;
						}
				    }
				}
				if(!StringUtils.isEmpty(obj.getRevision_nos()) && obj.getRevision_nos().length > 0) {
					for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( obj.getRevision_nos().length > 0 && !StringUtils.isEmpty(obj.getRevision_nos()[i])) {
					    insertStmt.setString(p++,(obj.getDocument_no()));
					    insertStmt.setString(p++,(obj.getRevision_nos().length > 0)?obj.getRevision_nos()[i]:null);
					    insertStmt.setString(p++,(obj.getStatus_fks().length > 0)?obj.getStatus_fks()[i]:null);
					    insertStmt.setString(p++,DateParser.parse((obj.getSubmission_dates().length > 0)?obj.getSubmission_dates()[i]:null));
					    insertStmt.setString(p++,DateParser.parse((obj.getApproval_dates().length > 0)?obj.getApproval_dates()[i]:null));
					    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
					    insertStmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
					   
					    insertStmt.addBatch();
				    }
				}
				int[] insertCount = insertStmt.executeBatch();
			  }
		  }
		  con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return flag;
	}

	@Override
	public boolean updateDocument(Document obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String updateQry = "UPDATE documents set "
					+ "project_priority_fk= ?, document_type_fk=? ,"
					+ "document_name= ?, responsible_for_approval= ? where document_no = ?";
			
			updateStmt = con.prepareStatement(updateQry);
			int q = 1;
			updateStmt.setString(q++,obj.getProject_priority_fk()); 
			updateStmt.setString(q++,obj.getDocument_type_fk()); 
			updateStmt.setString(q++,obj.getDocument_name()); 
			updateStmt.setString(q++,obj.getResponsible_for_approval()); 
			updateStmt.setString(q++,obj.getDocument_no()); 
			
			int count = updateStmt.executeUpdate();
			DBConnectionHandler.closeJDBCResoucrs(null, updateStmt, null);
			
			String updateQry1 = "UPDATE documents_revisions set "
					+ "revision_no=? ,"
					+ "status_fk= ?, submission_date= ?, approval_date= ?,remarks = ?,document_attachment = ? "
					+ "where id= ?";
			
			updateStmt = con.prepareStatement(updateQry1);
			
			String insertQry = "INSERT into  documents_revisions (document_no_fk,revision_no,status_fk,"
					+"submission_date,approval_date,remarks,document_attachment) "
					+"VALUES (?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
		
			int	arraySize = 0;
			if(!StringUtils.isEmpty(obj.getRevision_nos()) && obj.getRevision_nos().length > 0) {
				obj.setRevision_nos(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevision_nos()));
				if(arraySize < obj.getRevision_nos().length) {
					arraySize = obj.getRevision_nos().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStatus_fks()) && obj.getStatus_fks().length > 0) {
				obj.setStatus_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatus_fks()));
				if(arraySize < obj.getStatus_fks().length) {
					arraySize = obj.getStatus_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
				obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
				if(arraySize < obj.getRemarkss().length) {
					arraySize = obj.getRemarkss().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getSubmission_dates()) && obj.getSubmission_dates().length > 0) {
				obj.setSubmission_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getSubmission_dates()));
				if(arraySize < obj.getSubmission_dates().length) {
					arraySize = obj.getSubmission_dates().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getApproval_dates()) && obj.getApproval_dates().length > 0) {
				obj.setApproval_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getApproval_dates()));
				if(arraySize < obj.getApproval_dates().length) {
					arraySize = obj.getApproval_dates().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getDocumentsFileNames()) && obj.getDocumentsFileNames().length > 0) {
				obj.setDocumentsFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocumentsFileNames()));
				if(arraySize < obj.getDocumentsFileNames().length) {
					arraySize = obj.getDocumentsFileNames().length;
				}
			}
				
			String saveDirectory = CommonConstants.DOCUMENT_FILES_SAVING_PATH ;
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			if(!StringUtils.isEmpty(obj.getRevision_nos()) && obj.getRevision_nos().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					String dId = obj.getIds()[i];
					if(!StringUtils.isEmpty(dId)) {
					    int p = 1;
					    if( obj.getRevision_nos().length > 0 && !StringUtils.isEmpty(obj.getRevision_nos()[i])) {
						    String docFileName = null;
						    MultipartFile file = obj.getDocumentsFile()[i];
							if (null != file && !file.isEmpty()){
								String fileName = file.getOriginalFilename();
								docFileName = fileName;
								FileUploads.singleFileSaving(file, saveDirectory, docFileName);
							} else {
								docFileName  = (obj.getDocumentsFileNames().length > 0)?obj.getDocumentsFileNames()[i]:null;
							} 
							updateStmt.setString(p++,(obj.getRevision_nos().length > 0)?obj.getRevision_nos()[i]:null);
						    updateStmt.setString(p++,(obj.getStatus_fks().length > 0)?obj.getStatus_fks()[i]:null);
						    updateStmt.setString(p++,DateParser.parse((obj.getSubmission_dates().length > 0)?obj.getSubmission_dates()[i]:null));
						    updateStmt.setString(p++,DateParser.parse((obj.getApproval_dates().length > 0)?obj.getApproval_dates()[i]:null));
						    updateStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
						    updateStmt.setString(p++,docFileName);	
						    updateStmt.setString(p++,(obj.getIds()[i]));
						    updateStmt.addBatch();
					    }
					} else {
						String[] documentNames = new String[arraySize];
						if(!StringUtils.isEmpty(obj.getDocumentsFile()) && obj.getDocumentsFile().length > 0) {
							if(arraySize < obj.getDocumentsFile().length) {
									arraySize = obj.getDocumentsFile().length;
							}
							String saveDirectory1 = CommonConstants.DOCUMENT_FILES_SAVING_PATH ;
							documentNames = new String[arraySize];
							for (int k = 0; k < documentNames.length; k++) {
								MultipartFile file = obj.getDocumentsFile()[k];
								if (null != file && !file.isEmpty()){
									String fileName = file.getOriginalFilename();
									documentNames[k] = fileName;
									FileUploads.singleFileSaving(file, saveDirectory1, fileName);
									obj.setDocument_attachment(fileName);
								}else {
									documentNames[k] = null;
								}
						    }
						}
					    int p = 1;
					    if( obj.getRevision_nos().length > 0 && !StringUtils.isEmpty(obj.getRevision_nos()[i])) {
						    MultipartFile file = obj.getDocumentsFile()[i];
							files.add(file);
							insertStmt.setString(p++,(obj.getDocument_no()));
							insertStmt.setString(p++,(obj.getRevision_nos().length > 0)?obj.getRevision_nos()[i]:null);
							insertStmt.setString(p++,(obj.getStatus_fks().length > 0)?obj.getStatus_fks()[i]:null);
						    insertStmt.setString(p++,DateParser.parse((obj.getSubmission_dates().length > 0)?obj.getSubmission_dates()[i]:null));
						    insertStmt.setString(p++,DateParser.parse((obj.getApproval_dates().length > 0)?obj.getApproval_dates()[i]:null));
						    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
						    insertStmt.setString(p++,documentNames[i]);
						    					    
						    insertStmt.addBatch();
					    }
					}
				}
			}
			
			int[] updateCount = updateStmt.executeBatch();
			
			
			int[] insertCount = insertStmt.executeBatch();
		
			if(updateCount.length > 0 || insertCount.length > 0) {
				flag = true;
			}

			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			DBConnectionHandler.closeJDBCResoucrs(null, updateStmt, null);
			
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}
		return flag;
	}

	@Override
	public boolean deleteDocument(Document obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Document> getWorkListForDocumentForm(Document obj) throws Exception {
		List<Document> objsList = new ArrayList<Document>();
		try {
			String qry = "select work_id as work_id_fk ,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Document>(Document.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Document> getContractsListForDocumentForm(Document obj) throws Exception {
		List<Document> objsList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk "
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
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Document>(Document.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
