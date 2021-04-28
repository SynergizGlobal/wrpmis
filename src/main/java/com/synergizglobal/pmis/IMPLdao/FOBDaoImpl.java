package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FOBDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.FOB;

@Repository
public class FOBDaoImpl implements FOBDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<FOB> getFOBList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,c.contract_short_name,w.work_short_name,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,revised_completion,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,"
					+ "contract_name,c.work_id_fk,work_name,module_name_fk,month,status_as_on_month,w.project_id_fk,p.project_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addFOB(FOB obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			
			String qry = "INSERT INTO fob"
					+ "(fob_id,fob_name,contract_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,commissioning_date,"
					+ "estimated_cost,completion_cost,work_status_fk,latitude,longitude,remarks) "
					+ "VALUES "
					+ "(:fob_id,:fob_name,:contract_id_fk,:date_of_approval,:target_date,:construction_start_date,:actual_completion_date,:commissioning_date,:" 
					+ "estimated_cost,:completion_cost,:work_status_fk,:latitude,:longitude,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag) {
				if(flag && !StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
				}
				if(flag && !StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
				}
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
							ps.setString(k++, obj.getFob_id());
							ps.setString(k++, fobDetailNames.length > 0 ?fobDetailNames[i]:null);
							ps.setString(k++, fobDetailValues.length > 0 ?fobDetailValues[i]:null);
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                	int arraySize = 0;
		    				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
		    					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
		    					if(arraySize < obj.getFob_detail_names().length) {
		    						arraySize = obj.getFob_detail_names().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
		    					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
		    					if(arraySize < obj.getFob_detail_values().length) {
		    						arraySize = obj.getFob_detail_values().length;
		    					}
		    				}
		                    return arraySize;
		                }
		            });
				
				
				
					
					String file_insert_qry = "INSERT into  fob_images ( fob_id_fk, attachment,fob_file_type_fk,created_date) VALUES (:fob_id,:attachment,:fob_file_type_fk,CURRENT_TIMESTAMP())";
					
					int arraySize = 0;
					if (!StringUtils.isEmpty(obj.getFobFileNames()) && obj.getFobFileNames().length > 0) {
						obj.setFobFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobFileNames()));
						if (arraySize < obj.getFobFileNames().length) {
							arraySize = obj.getFobFileNames().length;
						}
					}

					if (!StringUtils.isEmpty(obj.getFob_file_types()) && obj.getFob_file_types().length > 0) {
						obj.setFob_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_file_types()));
						if (arraySize < obj.getFob_file_types().length) {
							arraySize = obj.getFob_file_types().length;
						}
					}
					for (int i = 0; i < arraySize; i++) {
						MultipartFile multipartFile = obj.getFobFiles()[i];
						if ((null != multipartFile && !multipartFile.isEmpty())) {
							String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH 
									+ File.separator;
							String fileName = obj.getFobFileNames()[i];
							if (null != multipartFile && !multipartFile.isEmpty()) {
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							}
							FOB fileObj = new FOB();
							fileObj.setAttachment(fileName);
							fileObj.setFob_file_type_fk(obj.getFob_file_types()[i]);
							fileObj.setFob_id(obj.getFob_id());
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							namedParamJdbcTemplate.update(file_insert_qry, paramSource);
						}
					}
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public FOB getFOB(FOB obj) throws Exception {
		FOB fobj = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,f.attachment,"
					+ "contract_name,c.work_id_fk,work_name,module_name_fk,month,status_as_on_month,w.project_id_fk,p.project_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				qry = qry + " and fob_id = ?";
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				pValues[i++] = obj.getFob_id();
			}
			
			fobj = (FOB)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
			
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBDetail = "select detail_name,value from fob_detail f where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBDetail, new Object[] {fobj.getFob_id()}, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobDetails(objsList);
			}
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBImages = "select id as fob_file_id, fob_id_fk, attachment, fob_file_type_fk, created_date from fob_images  where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBImages, new Object[] {fobj.getFob_id() }, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobImages(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return fobj;
	}

	@Override
	public boolean updateFOB(FOB obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE fob set "
					+ "fob_name = :fob_name,contract_id_fk = :contract_id_fk,date_of_approval = :date_of_approval,target_date = :target_date,construction_start_date = :construction_start_date,actual_completion_date = :actual_completion_date,commissioning_date = :commissioning_date,"
					+"estimated_cost = :estimated_cost,completion_cost = :completion_cost,work_status_fk = :work_status_fk,latitude = :latitude,longitude = :longitude,remarks = :remarks "
					+ "where fob_id = :fob_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag) {
				
				String deleteQry = "DELETE from fob_detail where fob_id_fk = :fob_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);				
				
				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
				}
				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
				}
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail, new BatchPreparedStatementSetter() { 
	                @Override
	                public void setValues(PreparedStatement ps, int i) throws SQLException {
	                	int k = 1;
	                	ps.setString(k++, obj.getFob_id());
						ps.setString(k++,fobDetailNames.length > 0?fobDetailNames[i]:null);
						ps.setString(k++,fobDetailValues.length > 0?fobDetailValues[i]:null);
						
	                }
	                @Override  
	                public int getBatchSize() {
	                	int arraySize = 0;
	    				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
	    					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
	    					if(arraySize < obj.getFob_detail_names().length) {
	    						arraySize = obj.getFob_detail_names().length;
	    					}
	    				}
	    				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
	    					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
	    					if(arraySize < obj.getFob_detail_values().length) {
	    						arraySize = obj.getFob_detail_values().length;
	    					}
	    				}
	                    return arraySize;
	                }
	            });
				int arraySize = 0;
				if (!StringUtils.isEmpty(obj.getFobFileNames()) && obj.getFobFileNames().length > 0) {
					obj.setFobFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobFileNames()));
					if (arraySize < obj.getFobFileNames().length) {
						arraySize = obj.getFobFileNames().length;
					}
				}

				if (!StringUtils.isEmpty(obj.getFob_file_types()) && obj.getFob_file_types().length > 0) {
					obj.setFob_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_file_types()));
					if (arraySize < obj.getFob_file_types().length) {
						arraySize = obj.getFob_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(obj.getFob_file_ids()) && obj.getFob_file_ids().length > 0) {
					obj.setFob_file_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_file_ids()));
					if (arraySize < obj.getFob_file_ids().length) {
						arraySize = obj.getFob_file_ids().length;
					}
				}
				String placeholders = "";
				String fob_file_ids = "";
				for (int i = 0; i < arraySize; i++) {
					if(!StringUtils.isEmpty(obj.getFob_file_ids()[i])) {
						placeholders = placeholders + "?,";
						fob_file_ids = fob_file_ids + obj.getFob_file_ids()[i] + ",";
					}
				}
				
				if (!StringUtils.isEmpty(placeholders)) {
					placeholders = org.apache.commons.lang3.StringUtils.chop(placeholders);					
					fob_file_ids = org.apache.commons.lang3.StringUtils.chop(fob_file_ids);

					String deleteFilesQry = "delete from fob_images where id not in("+fob_file_ids+") and fob_id_fk = :fob_id";
					FOB fileObj = new FOB();
					fileObj.setFob_id(obj.getFob_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
				}
			
				String insertFileQry = "INSERT into  fob_images ( fob_id_fk, attachment,fob_file_type_fk,created_date) VALUES (:fob_id,:attachment,:fob_file_type_fk,CURRENT_TIMESTAMP())";
				String updateFileQry = "UPDATE fob_images set fob_id_fk=:fob_id,attachment=:attachment,fob_file_type_fk=:fob_file_type_fk WHERE id=:fob_file_id";

					
					for (int i = 0; i < arraySize; i++) {
						MultipartFile multipartFile = obj.getFobFiles()[i];
						if ((null != multipartFile && !multipartFile.isEmpty())
								|| !StringUtils.isEmpty(obj.getFobFileNames()[i])) {
							String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH+ File.separator;
							String fileName = obj.getFobFileNames()[i];
							String fob_file_id = obj.getFob_file_ids()[i];
							if (null != multipartFile && !multipartFile.isEmpty()) {
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							}
							FOB fileObj = new FOB();
							fileObj.setAttachment(fileName);
							fileObj.setFob_file_type_fk(obj.getFob_file_types()[i]);
							fileObj.setFob_file_id(fob_file_id);
							fileObj.setFob_id(obj.getFob_id());
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							if(!StringUtils.isEmpty(fob_file_id)) {
								namedParamJdbcTemplate.update(updateFileQry, paramSource);
							}else {
								namedParamJdbcTemplate.update(insertFileQry, paramSource);
							}
						}
					}
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteFOB(FOB obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FOB> getWorkStatusList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT work_status_fk from fob where work_status_fk is not null and work_status_fk <> ''  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY work_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getContractsList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_name,c.contract_short_name from fob f " + 
					"LEFT JOIN contract c on f.contract_id_fk = c.contract_id "+
					"where contract_id_fk is not null ";
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getProjectsListForFOBForm(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getWorkListForFOBForm(FOB obj) throws Exception {
		List<FOB> objsList = new ArrayList<FOB>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getContractsListForFOBForm(FOB obj) throws Exception {
		List<FOB> objsList = null;
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
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	/**
	@Override
	public List<FOB> getFOBsList(FOB obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,c.contract_short_name,w.work_short_name,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,revised_completion,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,"
					+ "contract_name,c.work_id_fk,work_name,module_name_fk,month,status_as_on_month,w.project_id_fk,p.project_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or f.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or fob_id like ? or fob_name like ? or work_status_fk like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY contract_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
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
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(FOB obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = "select count(*) as total_records from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or f.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or fob_id like ? or fob_name like ? or work_status_fk like ?)";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
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
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}
*/
	@Override
	public List<FOB> getFobDetailsList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_detail_id, fob_id_fk, detail_name, value from `fob_detail` GROUP BY detail_name ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getFOBDetails(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_detail_id, fob_id_fk, detail_name, value "
					+ "from fob_detail fd "
					+ "LEFT OUTER JOIN fob f ON fd.fob_id_fk = f.fob_id "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getFobFileTypesList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_file_type from fob_file_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
