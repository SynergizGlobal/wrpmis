package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;

@Repository
public class FOBDaoImpl implements FOBDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<FOB> getFOBList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		List<FOB> objsList1 = null;
		try {
			String qry = "select distinct fob_id,fob_name,f.work_id_fk,w.work_short_name,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,revised_completion,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,f.work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,"
					+ "work_name,w.project_id_fk,p.project_name,c.contract_short_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN work w ON f.work_id_fk = w.work_id "
					+ "left join fob_contract_responsible_people ce on f.fob_id = ce.fob_id_fk "
					+ "LEFT JOIN contract c ON c.contract_id = ce.contract_id_fk "
					+"left join user u on c.hod_user_id_fk = u.user_id "
					+"left join user us on c.dy_hod_user_id_fk = us.user_id "				
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "	
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and f.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and f.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and ce.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				//qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ?)";
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
				//arrSize++;
			}			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
				//pValues[i++] = obj.getUser_id();
				//objsList1 = getExecutivesList(obj);	

			}			
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				if(objsList1.size() > 0) {
					for (FOB con : objsList1) {
				        boolean found=false;
				        for (FOB con1 : objsList) {
				            if ((con.getContract_id().equals(con1.getContract_id()))) {
				                found=true;
				                break;
				            }
				        }
				        if(!found){
				        	objsList.add(con);
				        }
				    }
				}
			}*/			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

 	private List<FOB> getExecutivesList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry ="SELECT  distinct w.work_name,w.work_short_name,f.contract_id_fk as contract_id,"
					+ "w.project_id_fk,p.project_name,c.hod_user_id_fk as hod_user_id,u.designation,us.designation as dy_hod_designation,u.user_name,"
					+ "c.work_id_fk,contract_type_fk,"
					//+ "c.contract_id,"
					//+ "c.contract_name,
					+"c.contract_status_fk,c.dy_hod_user_id_fk as dy_hod_user_id,"
					//+ "c.contract_short_name,"
					//+ "contractor_id_fk,"
					//+ "cr.contractor_name,
					+"c.hod_user_id_fk,c.dy_hod_user_id_fk,f.fob_id,f.fob_name,f.work_status_fk"
					+ " FROM fob_contract_responsible_people ce "
					+ "LEFT JOIN fob f on f.fob_id = ce.fob_id_fk "
					+ "LEFT JOIN contract c on c.contract_id = ce.contract_id_fk "+
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id where contract_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and f.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and f.work_status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id()) && !obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN)) {
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
 	
 	
	@Override
	public List<FOB> getResponsibleExecutives(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			
			String qry ="SELECT u.user_id as user_id,u.user_name,u.designation,u.department_fk "
					+ "FROM user u " 
					+ "left join department d on u.department_fk = d.department "
					+ "where  user_id is not null and user_type_fk <> ''  and u.user_type_fk not in('Others')  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and u.department_fk in( select u.department_fk from contract c left join user u on u.user_id=c.hod_user_id_fk where c.contract_id= ? )";
				arrSize++;
			}
			qry = qry + " and user_name not like '%user%' and pmis_key_fk not like '%SGS%'";// and department_fk in('Engg','Elec','S&T') 
			
			qry = qry + " ORDER BY FIELD(user_type_fk,'HOD','DYHOD','Officers ( Jr./Sr. Scale )','Others'),"
					+ "FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','AGM Civil'," 
					+ " 'DyCPM Civil','DyCPM III','DyCPM V','DyCE EE','DyCE Badlapur','DyCPM Pune','DyCE Proj','DyCEE I','DyCEE Projects','DyCEE PSI','DyCSTE I','DyCSTE IT','DyCSTE Projects','XEN Consultant'," 
					+ " 'AEN Adhoc','AEN Project','AEN P-Way','AEN','Sr Manager Signal','Manager Signal','Manager Civil','Manager OHE','Manager GS','Manager Finance','Planning Manager'," 
					+ " 'Manager Project','Manager','SSE','SSE Project','SSE Works','SSE Drg','SSE BR','SSE P-Way','SSE OHE','SPE','PE','JE','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			//pValues[i++] = CommonConstants.USER_TYPE_HOD;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
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
					+ "(fob_id,fob_name,work_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,commissioning_date,"
					+ "estimated_cost,completion_cost,work_status_fk,latitude,longitude,remarks,revised_completion,estimated_cost_units,completion_cost_units) "
					+ "VALUES "
					+ "(:fob_id,:fob_name,:work_id_fk,:date_of_approval,:target_date,:construction_start_date,:actual_completion_date,:commissioning_date,:" 
					+ "estimated_cost,:completion_cost,:work_status_fk,:latitude,:longitude,:remarks,:revised_completion,:estimated_cost_units,:completion_cost_units)";		 
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
				
				
				
					
					String file_insert_qry = "INSERT into  fob_files ( fob_id_fk, attachment,created_date,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:created_date,:fob_file_type_fk,:name)";
					String document_insert_qry = "INSERT into  fob_documents ( fob_id_fk, attachment,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:fob_file_type_fk,:name)";

					int arraySize =0, docArrSize = 0;
					if (!StringUtils.isEmpty(obj.getFileNamesFob()) && obj.getFileNamesFob().length > 0) {
						obj.setFileNamesFob(CommonMethods.replaceEmptyByNullInSringArray(obj.getFileNamesFob()));
						if (docArrSize < obj.getFileNamesFob().length) {
							docArrSize = obj.getFileNamesFob().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getFobDoc_file_types()) && obj.getFobDoc_file_types().length > 0) {
						obj.setFobDoc_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobDoc_file_types()));
						if (docArrSize < obj.getFobDoc_file_types().length) {
							docArrSize = obj.getFobDoc_file_types().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getDocumentNamesFob()) && obj.getDocumentNamesFob().length > 0) {
						obj.setDocumentNamesFob(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocumentNamesFob()));
						if (docArrSize < obj.getDocumentNamesFob().length) {
							docArrSize = obj.getDocumentNamesFob().length;
						}
					}
					
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
					if (!StringUtils.isEmpty(obj.getFobDocumentNames()) && obj.getFobDocumentNames().length > 0) {
						obj.setFobDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobDocumentNames()));
						if (arraySize < obj.getFobDocumentNames().length) {
							arraySize = obj.getFobDocumentNames().length;
						}
					}
					if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
						obj.setCreated_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getCreated_dates()));
						if (arraySize < obj.getCreated_dates().length) {
							arraySize = obj.getCreated_dates().length;
						}
					}

					/*if (!StringUtils.isEmpty(obj.getFob_file_types()) && obj.getFob_file_types().length > 0) {
						obj.setFob_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_file_types()));
						if (arraySize < obj.getFob_file_types().length) {
							arraySize = obj.getFob_file_types().length;
						}
					}*/
					for (int i = 0; i < docArrSize; i++) {
						if (!StringUtils.isEmpty(obj.getFobDocumentFiles()) && obj.getFobDocumentFiles().length > 0) {
							MultipartFile multipartFile = obj.getFobDocumentFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty())) {
								String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH + obj.getFob_id() + File.separator;
								String fileName = obj.getFileNamesFob()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								FOB fileObj = new FOB();
								fileObj.setAttachment(fileName);
								//fileObj.setFob_file_type_fk(obj.getFob_file_types()[i]);
								
								String fob_file_type_fk = obj.getFobDoc_file_types()[i];
								String name = obj.getDocumentNamesFob()[i];
								
								fileObj.setFob_id(obj.getFob_id());
								fileObj.setFob_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(document_insert_qry, paramSource);
							}
						}
					}
					for (int i = 0; i < arraySize; i++) {
						if (!StringUtils.isEmpty(obj.getFobFiles()) && obj.getFobFiles().length > 0) {
							MultipartFile multipartFile = obj.getFobFiles()[i];
							if ((null != multipartFile && !multipartFile.isEmpty())) {
								String saveDirectory = CommonConstants2.FOB_GALLERY_SAVING_PATH + obj.getFob_id() + File.separator;
								String fileName = obj.getFobFileNames()[i];
								if (null != multipartFile && !multipartFile.isEmpty()) {
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								}
								FOB fileObj = new FOB();
								fileObj.setAttachment(fileName);
								//fileObj.setFob_file_type_fk(obj.getFob_file_types()[i]);
								String created_date = null;
								if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
									created_date = obj.getCreated_dates()[i];
								}
								if(!StringUtils.isEmpty(created_date)) {
									created_date = DateParser.parse(created_date);
								}else {
									SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
									created_date = sqlDate.format(new Date());
								}
								
								String fob_file_type_fk = obj.getFob_file_types()[i];
								String name = obj.getFobDocumentNames()[i];
								
								fileObj.setCreated_date(created_date);
								fileObj.setFob_id(obj.getFob_id());
								fileObj.setFob_file_type_fk(fob_file_type_fk);
								fileObj.setName(name);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(file_insert_qry, paramSource);
							}
						}
					}
					
					
					/********************************************************************/
					
					
					/*if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
						for(int k =0; k<obj.getContracts_id_fk().length; k++) {
							String contarctId = obj.getContracts_id_fk()[k];
							String qry3 = "INSERT into fob_contract (fob_id_fk,contract_id_fk) VALUES (:fob_id_fk,:contract_id_fk)";
							if(contarctId.contains(",")) {
								String[] ids = contarctId.split(",");					
								for (int i = 0; i < ids.length; i++) {
									FOB fileObj = new FOB();
									fileObj.setContract_id_fk(!StringUtils.isEmpty(ids[i])?ids[i]:null);
									fileObj.setFob_id_fk(obj.getFob_id());
									paramSource = new BeanPropertySqlParameterSource(fileObj);
									namedParamJdbcTemplate.update(qry3, paramSource);
								}			
							}else {
								FOB fileObj = new FOB();
								fileObj.setContract_id_fk(contarctId);
								fileObj.setFob_id_fk(obj.getFob_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(qry3, paramSource);
							}
						}	
					}*/
					
					if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
						String qry3 = "INSERT into fob_contract_responsible_people (fob_id_fk,contract_id_fk,responsible_people_id_fk) VALUES (:fob_id_fk,:contract_id_fk,:responsible_people_id_fk)";
						int len = obj.getContracts_id_fk().length;
						
							for (int i = 0; i < len; i++) 
							{
								int r=0;
								for (int j = 0; j < obj.getFilecounts()[i]; j++) 
								{
									FOB fileObj = new FOB();
									fileObj.setContract_id_fk(obj.getContracts_id_fk()[i]);
									if(i==(len-1) && len>1)
									{
										String SplitVar[]=obj.getResponsible_people_id_fk().split(",");
										fileObj.setResponsible_people_id_fk(SplitVar[j]);

									}
									else
									{
										fileObj.setResponsible_people_id_fk((obj.getResponsible_people_id_fks().length > 0)?obj.getResponsible_people_id_fks()[r]:null);
									}
									fileObj.setFob_id_fk(obj.getFob_id());
									paramSource = new BeanPropertySqlParameterSource(fileObj);
									namedParamJdbcTemplate.update(qry3, paramSource);
									r++;
								}
							}			
			
					}	
					
					/********************************************************************************/
					
					if(!StringUtils.isEmpty(obj.getResponsible_people_id_fk())) {
						String userIds[] = new String[0];
						if(obj.getResponsible_people_id_fk().contains(",")) {
							userIds = obj.getResponsible_people_id_fk().split(",");
						}else {
							userIds = new String[]{obj.getResponsible_people_id_fk()};
						}
						String messageType = "FOB";
						String redirect_url = "/get-fob?fob_id="+obj.getFob_id();
						String message = "New FOB "+obj.getFob_name() + " & "+ obj.getFob_id() +" is added under contract(s) "+obj.getContract_name()+" on PMIS ";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
					}
					/********************************************************************************/
				
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name("FOB");
					formHistory.setForm_action_type("Add");
					formHistory.setForm_details("FOB "+obj.getFob_name() + " has been created.");
					formHistory.setWork(obj.getWork_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public FOB getFOB(FOB obj) throws Exception {
		FOB fobj = null;
		try {
			String qry = "select fob_id,fob_name,f.work_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(revised_completion,'%d-%m-%Y') AS revised_completion,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,"
					+ "cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,"
					+ "f.work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,f.attachment,"
					+ "work_name,w.project_id_fk,p.project_name,estimated_cost_units,completion_cost_units "
					+ "from fob f "
					+ "LEFT OUTER JOIN work w ON f.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "
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
				qryFOBDetail = qryFOBDetail + " and detail_name in('Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos') order by field(detail_name,'Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos')";
				
				objsList = jdbcTemplate.query(qryFOBDetail, new Object[] {fobj.getFob_id()}, new BeanPropertyRowMapper<FOB>(FOB.class));	
				if(StringUtils.isEmpty(objsList) || objsList.size() == 0) {
					qryFOBDetail = "select detail_name from fob_detail f " ;
					qryFOBDetail = qryFOBDetail + " where detail_name in('Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos') "
							+ "group by detail_name order by field(detail_name,'Type','Location of FOB','KM','FOB Length (m)','FOB Width (m)','Platforms Connecting','No. of Staircases','No. of Skywalk','Future Provision Escalators nos')";
					
					objsList = jdbcTemplate.query(qryFOBDetail, new Object[] {}, new BeanPropertyRowMapper<FOB>(FOB.class));
				}
				fobj.setFobDetails(objsList);
			}
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBImages = "select id as fob_file_id,fob_id_fk,attachment,DATE_FORMAT(created_date,'%d-%m-%Y') as created_date,fob_file_type_fk,name from fob_files where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBImages, new Object[] {fobj.getFob_id() }, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobImages(objsList);
			}
			
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBImages = "select fob_documents_id ,fob_id_fk,attachment,fob_file_type_fk,name from fob_documents where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBImages, new Object[] {fobj.getFob_id() }, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobDocuments(objsList);
			}
			
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBResponsiblePeople = "select responsible_people_id_fk as responsible_people_id_fk,contract_id_fk,designation,user_name from fob_contract_responsible_people r left join user u on u.user_id=r.responsible_people_id_fk where fob_id_fk = ?" ;
				
				objsList = jdbcTemplate.query(qryFOBResponsiblePeople, new Object[] {fobj.getFob_id() }, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setResponsiblePeopleList(objsList);
			}
			
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBContracts = "select DISTINCT contract_id_fk,contract_short_name from fob_contract_responsible_people f left join contract c on c.contract_id=f.contract_id_fk where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBContracts, new Object[] {fobj.getFob_id() }, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setContractsList(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return fobj;
	}

	@Override
	public boolean updateFOB(FOB obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection con = null;
		try {
			//con=dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE fob set "
					+ "fob_name = :fob_name,work_id_fk=:work_id_fk,date_of_approval = :date_of_approval,target_date = :target_date,construction_start_date = :construction_start_date,actual_completion_date = :actual_completion_date,commissioning_date = :commissioning_date,"
					+"estimated_cost = :estimated_cost,completion_cost = :completion_cost,work_status_fk = :work_status_fk,latitude = :latitude,longitude = :longitude,remarks = :remarks,revised_completion=:revised_completion,estimated_cost_units=:estimated_cost_units,completion_cost_units=:completion_cost_units  "
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
				int arraySize = 0, docArrSize = 0;
				if (!StringUtils.isEmpty(obj.getFileNamesFob()) && obj.getFileNamesFob().length > 0) {
					obj.setFileNamesFob(CommonMethods.replaceEmptyByNullInSringArray(obj.getFileNamesFob()));
					if (docArrSize < obj.getFileNamesFob().length) {
						docArrSize = obj.getFileNamesFob().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getFobDoc_file_types()) && obj.getFobDoc_file_types().length > 0) {
					obj.setFobDoc_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobDoc_file_types()));
					if (docArrSize < obj.getFobDoc_file_types().length) {
						docArrSize = obj.getFobDoc_file_types().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getDocumentNamesFob()) && obj.getDocumentNamesFob().length > 0) {
					obj.setDocumentNamesFob(CommonMethods.replaceEmptyByNullInSringArray(obj.getDocumentNamesFob()));
					if (docArrSize < obj.getDocumentNamesFob().length) {
						docArrSize = obj.getDocumentNamesFob().length;
					}
				}
				
				
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
				if (!StringUtils.isEmpty(obj.getFobDocumentNames()) && obj.getFobDocumentNames().length > 0) {
					obj.setFobDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getFobDocumentNames()));
					if (arraySize < obj.getFobDocumentNames().length) {
						arraySize = obj.getFobDocumentNames().length;
					}
				}
				if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
					obj.setCreated_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getCreated_dates()));
					if (arraySize < obj.getCreated_dates().length) {
						arraySize = obj.getCreated_dates().length;
					}
				}

				/*if (!StringUtils.isEmpty(obj.getFob_file_types()) && obj.getFob_file_types().length > 0) {
					obj.setFob_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_file_types()));
					if (arraySize < obj.getFob_file_types().length) {
						arraySize = obj.getFob_file_types().length;
					}
				}*/
				
				String fob_file_ids = "",fob_documents_ids =  "";
				if (!StringUtils.isEmpty(obj.getFob_file_ids()) && obj.getFob_file_ids().length > 0) {
					for (int i = 0; i < obj.getFob_file_ids().length; i++) {
						if(!StringUtils.isEmpty(obj.getFob_file_ids()[i])) {
							fob_file_ids = fob_file_ids + obj.getFob_file_ids()[i] + ",";
						}
					}  
				}
				
				if (!StringUtils.isEmpty(obj.getFob_documents_ids()) && obj.getFob_documents_ids().length > 0) {
					for (int i = 0; i < obj.getFob_documents_ids().length; i++) {
						if(!StringUtils.isEmpty(obj.getFob_documents_ids()[i])) {
							fob_documents_ids = fob_documents_ids + obj.getFob_documents_ids()[i] + ",";
						}
					}  
				}
				 
				if (!StringUtils.isEmpty(fob_file_ids)) {				
					fob_file_ids = org.apache.commons.lang3.StringUtils.chop(fob_file_ids);

					String deleteFilesQry = "delete from fob_files where id not in("+fob_file_ids+") and fob_id_fk = :fob_id";
					FOB fileObj = new FOB();
					fileObj.setFob_id(obj.getFob_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
				}else{
					String deleteFilesQry = "delete from fob_files where  fob_id_fk = :fob_id";
					FOB fileObj = new FOB();
					fileObj.setFob_id(obj.getFob_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
				}
				
				if (!StringUtils.isEmpty(fob_documents_ids)) {				
					fob_documents_ids = org.apache.commons.lang3.StringUtils.chop(fob_documents_ids);

					String deleteDocumentsQry = "delete from fob_documents where fob_documents_id not in("+fob_documents_ids+") and fob_id_fk = :fob_id";
					FOB fileObj = new FOB();
					fileObj.setFob_id(obj.getFob_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					namedParamJdbcTemplate.update(deleteDocumentsQry, paramSource);
				}else{
					String deleteDocumentsQry = "delete from fob_documents where  fob_id_fk = :fob_id";
					FOB fileObj = new FOB();
					fileObj.setFob_id(obj.getFob_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					namedParamJdbcTemplate.update(deleteDocumentsQry, paramSource);
				}
			
				String insertFileQry = "INSERT into  fob_files ( fob_id_fk, attachment,created_date,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:created_date,:fob_file_type_fk,:name)";
				String updateFileQry = "UPDATE fob_files set fob_id_fk=:fob_id,attachment=:attachment,created_date=:created_date,fob_file_type_fk=:fob_file_type_fk,name=:name WHERE id=:fob_file_id";

				String insertDocumentQry = "INSERT into  fob_documents ( fob_id_fk, attachment,fob_file_type_fk,name) VALUES (:fob_id,:attachment,:fob_file_type_fk,:name)";
				String updateDocumentQry = "UPDATE fob_documents set fob_id_fk=:fob_id,attachment=:attachment,fob_file_type_fk=:fob_file_type_fk,name=:name WHERE fob_documents_id=:fob_file_id";
				for (int i = 0; i < docArrSize; i++) {
					if (!StringUtils.isEmpty(obj.getFobDocumentFiles()) && obj.getFobDocumentFiles().length > 0) {
						MultipartFile multipartFile = obj.getFobDocumentFiles()[i];
						if ((null != multipartFile && !multipartFile.isEmpty()) || (!StringUtils.isEmpty(obj.getFileNamesFob()) && obj.getFileNamesFob().length > 0)) {
							String saveDirectory = CommonConstants2.FOB_FILE_SAVING_PATH + obj.getFob_id() + File.separator;
							String fileName = obj.getFileNamesFob()[i];
							String fob_documents_id = null;
							if (!StringUtils.isEmpty(obj.getFob_documents_ids()) && obj.getFob_documents_ids().length > 0) {
								fob_documents_id = obj.getFob_documents_ids()[i];
							}
							if (null != multipartFile && !multipartFile.isEmpty()) {
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							}
							
							FOB fileObj = new FOB();
							fileObj.setAttachment(fileName);
						
							String fob_file_type_fk = obj.getFobDoc_file_types()[i];
							String name = obj.getDocumentNamesFob()[i];
							
							fileObj.setFob_documents_id(fob_documents_id);
							fileObj.setFob_id(obj.getFob_id());
							fileObj.setFob_file_type_fk(fob_file_type_fk);
							fileObj.setName(name);
							
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							if(!StringUtils.isEmpty(fob_documents_id)) {
								namedParamJdbcTemplate.update(updateDocumentQry, paramSource);
							}else {
								namedParamJdbcTemplate.update(insertDocumentQry, paramSource);
							}
						}
					}
				}
					
				for (int i = 0; i < arraySize; i++) {
					if (!StringUtils.isEmpty(obj.getFobFiles()) && obj.getFobFiles().length > 0) {
						MultipartFile multipartFile = obj.getFobFiles()[i];
						if ((null != multipartFile && !multipartFile.isEmpty()) || (!StringUtils.isEmpty(obj.getFobFileNames()) && obj.getFobFileNames().length > 0)) {
							String saveDirectory = CommonConstants2.FOB_GALLERY_SAVING_PATH + obj.getFob_id() + File.separator;
							String fileName = obj.getFobFileNames()[i];
							String fob_file_id = null;
							if (!StringUtils.isEmpty(obj.getFob_file_ids()) && obj.getFob_file_ids().length > 0) {
								fob_file_id = obj.getFob_file_ids()[i];
							}
							if (null != multipartFile && !multipartFile.isEmpty()) {
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							}
							
							FOB fileObj = new FOB();
							fileObj.setAttachment(fileName);
							//fileObj.setFob_file_type_fk(obj.getFob_file_types()[i]);
							String created_date = null;
							if (!StringUtils.isEmpty(obj.getCreated_dates()) && obj.getCreated_dates().length > 0) {
								created_date = obj.getCreated_dates()[i];
							}
							
							if(!StringUtils.isEmpty(created_date)) {
								created_date = DateParser.parse(created_date);
							}else {
								SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
								created_date = sqlDate.format(new Date());
							}
							String fob_file_type_fk = obj.getFob_file_types()[i];
							String name = obj.getFobDocumentNames()[i];
							
							fileObj.setCreated_date(created_date);
							fileObj.setFob_file_id(fob_file_id);
							fileObj.setFob_id(obj.getFob_id());
							fileObj.setFob_file_type_fk(fob_file_type_fk);
							fileObj.setName(name);
							
							paramSource = new BeanPropertySqlParameterSource(fileObj);
							if(!StringUtils.isEmpty(fob_file_id)) {
								namedParamJdbcTemplate.update(updateFileQry, paramSource);
							}else {
								namedParamJdbcTemplate.update(insertFileQry, paramSource);
							}
						}
					}
				}
				
				
				/********************************************************************/
				
				String deleteContractsQry = "DELETE from fob_contract_responsible_people where fob_id_fk = :fob_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteContractsQry, paramSource);
				
				
				if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
					String qry3 = "INSERT into fob_contract_responsible_people (fob_id_fk,contract_id_fk,responsible_people_id_fk) VALUES (:fob_id_fk,:contract_id_fk,:responsible_people_id_fk)";
					int len = obj.getContracts_id_fk().length;
					
					    int r=0;
						for (int i = 0; i < len; i++) 
						{
							
							for (int j = 0; j < obj.getFilecounts()[i]; j++) 
							{
								FOB fileObj = new FOB();
								fileObj.setContract_id_fk(obj.getContracts_id_fk()[i]);
								if(i==(len-1) && len>1)
								{
									if(obj.getResponsible_people_id_fk()!=null)
									{
										String SplitVar[]=obj.getResponsible_people_id_fk().split(",");
										fileObj.setResponsible_people_id_fk(SplitVar[j]);
									}
									else
									{
										fileObj.setResponsible_people_id_fk((obj.getResponsible_people_id_fks().length > 0)?obj.getResponsible_people_id_fks()[r]:null);
									}
								}
								else
								{
									fileObj.setResponsible_people_id_fk((obj.getResponsible_people_id_fks().length > 0)?obj.getResponsible_people_id_fks()[r]:null);
								}
								fileObj.setFob_id_fk(obj.getFob_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(qry3, paramSource);
								r++;
							}
						}			
		
				}
				
				String qryFOBResponsiblePeople = "select responsible_people_id_fk from fob_contract_responsible_people where fob_id_fk = ? " ;					
				List<String> existedResponsiblePeoples = jdbcTemplate.queryForList(qryFOBResponsiblePeople, new Object[] {obj.getFob_id() },String.class);
				
				/*String deleteResponsiblePeopleQry = "delete from fob_contract_responsible_people where  fob_id_fk=:fob_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteResponsiblePeopleQry, paramSource);
			
				
				if(!StringUtils.isEmpty(obj.getContracts_id_fk())) {
					String qry3 = "INSERT into fob_contract_responsible_people (fob_id_fk,contract_id_fk,responsible_people_id_fk) VALUES (:fob_id_fk,:contract_id_fk,:responsible_people_id_fk)";
					int len = obj.getContracts_id_fk().length;
					int r=0;
					
						for (int i = 0; i < len; i++) 
						{
							for (int j = 0; j < obj.getFilecounts()[i]; j++) 
							{
								FOB fileObj = new FOB();
								fileObj.setContract_id_fk(obj.getContracts_id_fk()[i]);
								fileObj.setResponsible_people_id_fk((obj.getResponsible_people_id_fks().length > 0)?obj.getResponsible_people_id_fks()[r]:null);
								fileObj.setFob_id_fk(obj.getFob_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);
								namedParamJdbcTemplate.update(qry3, paramSource);
								r++;
							}
						}			
		
				}*/				
				
				/*********************************************************************************/
				
				/********************************************************************************/
				
				if(!StringUtils.isEmpty(obj.getResponsible_people_id_fk())) {
					if("Commissioned".equals(obj.getWork_status_fk()) && !"Commissioned".equals(obj.getExisting_work_status_fk())) {
						String userIds[] = new String[0];
						if(obj.getResponsible_people_id_fk().contains(",")) {
							userIds = obj.getResponsible_people_id_fk().split(",");
						}else {
							userIds = new String[]{obj.getResponsible_people_id_fk()};
						}
						String messageType = "FOB";
						String redirect_url = "/get-fob?fob_id="+obj.getFob_id();
						String message = "FOB at "+obj.getFob_name() + " & "+ obj.getFob_id() +" commissioned";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
					}
					
					String userIds[] = new String[0];
					if(obj.getResponsible_people_id_fk().contains(",")) {
						userIds = obj.getResponsible_people_id_fk().split(",");
					}else {
						userIds = new String[]{obj.getResponsible_people_id_fk()};
					}					
					for (int i = 0; i < userIds.length; i++) {
						boolean tempFlag = true;		
						String responsible_person = userIds[i];
						if (!StringUtils.isEmpty(existedResponsiblePeoples) && existedResponsiblePeoples.size() > 0) {	
							if(existedResponsiblePeoples.contains(responsible_person)) {
								tempFlag = false;
							}
						}
						if(tempFlag) {
							String newUserIds[] = new String[]{userIds[i]};
							String messageType = "FOB";
							String redirect_url = "/get-fob?fob_id="+obj.getFob_id();
							String message = "New FOB "+obj.getFob_name() + " & "+ obj.getFob_id() +" is adeed under contract(s) "+obj.getContract_name()+" on PMIS ";
							 
							Messages msgObj = new Messages();
							msgObj.setUser_ids(newUserIds);
							msgObj.setMessage_type(messageType);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage(message);
							messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
						}
					}					
				}
				/********************************************************************************/
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name("FOB");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("FOB "+obj.getFob_name() + " has been updated.");
				formHistory.setWork(obj.getWork_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
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
	public boolean deleteFOB(FOB obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FOB> getWorkStatusList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		List<FOB> objsList1 = null;
		try { 
			String qry = "SELECT f.work_status_fk "
					+ "from fob f "
					+ "left join fob_contract_responsible_people ce on f.fob_id = ce.fob_id_fk "+
					"LEFT JOIN contract c on ce.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where f.work_status_fk is not null and f.work_status_fk <> ''  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and f.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and ce.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
			}	
			/*
			 * if(!StringUtils.isEmpty(obj) &&
			 * !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) { qry =
			 * qry + " and ( " +
			 * "fob_id in (select fob_id_fk from fob_contract where contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by fob_id_fk) "
			 * +
			 * "or fob_id in (select fob_id_fk from fob_contract where contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by fob_id_fk) "
			 * +
			 * "or fob_id in (select fob_id_fk from fob_responsible_people where responsible_people_id_fk = ? group by fob_id_fk)) "
			 * ; arrSize++; arrSize++; arrSize++; arrSize++; }
			 */		
			qry = qry + " GROUP BY f.work_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();

			}
			/*
			 * if(!StringUtils.isEmpty(obj) &&
			 * !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
			 * pValues[i++] = obj.getUser_id(); pValues[i++] = obj.getUser_id();
			 * pValues[i++] = obj.getUser_id(); pValues[i++] = obj.getUser_id(); //objsList1
			 * = getExecutivesList(obj); }
			 */
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				for (FOB con : objsList1) {
			        boolean found=false;
			        for (FOB con1 : objsList) {
			            if ((con.getWork_status_fk().equals(con1.getWork_status_fk()))) {
			                found=true;
			                break;
			            }
			        }
			        if(!found){
			        	objsList.add(con);
			        }
			    }
			}	*/	    
		    
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<FOB> getWorksListForFilter(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT c.work_id_fk,work_name,work_short_name "
					+ "from fob f "
					+ "left join fob_contract_responsible_people ce on f.fob_id = ce.fob_id_fk "+
					"LEFT JOIN contract c on ce.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where c.work_id_fk is not null ";
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and f.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and ce.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
			}	
			qry = qry + " GROUP BY work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();

			}
			/*
			 * if(!StringUtils.isEmpty(obj) &&
			 * !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
			 * pValues[i++] = obj.getUser_id(); pValues[i++] = obj.getUser_id();
			 * pValues[i++] = obj.getUser_id(); pValues[i++] = obj.getUser_id(); //objsList1
			 * = getExecutivesList(obj); }
			 */
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				for (FOB con : objsList1) {
			        boolean found=false;
			        for (FOB con1 : objsList) {
			            if ((con.getWork_id_fk().equals(con1.getWork_id_fk()))) {
			                found=true;
			                break;
			            }
			        }
			        if(!found){
			        	objsList.add(con);
			        }
			    }
			}	*/	    
		    
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getContractsListForFilter(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT ce.contract_id_fk,c.contract_name,c.contract_short_name from fob f " 
					+ "left join fob_contract_responsible_people ce on f.fob_id = ce.fob_id_fk "+
					"LEFT JOIN contract c on ce.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "+
					"where ce.contract_id_fk is not null ";
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and f.work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and ce.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
			}	
			qry = qry + "GROUP BY ce.contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())){
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();

			}	
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
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
			String qry ="select distinct contract_id,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			/*
			 * if(!StringUtils.isEmpty(obj.getUser_id())) { qry = qry +
			 * " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ?)"; arrSize++; arrSize++; }
			 */
			qry = qry + " order by contract_id asc";
			
			
			  Object[] pValues = new Object[arrSize];
			 		
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			/*
			 * if(!StringUtils.isEmpty(obj.getUser_id())) { pValues[i++] = obj.getUser_id();
			 * pValues[i++] = obj.getUser_id(); }
			 */
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
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
					+ "LEFT OUTER JOIN work w ON f.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and f.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getFobIdCheck(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_id from fob ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getFobDetailsLocations(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_details_location from fob_details_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getFobDetailsTypes(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_details_type from fob_details_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getResponsiblePeopleListForFOBForm(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation,department_fk FROM user where user_name not like '%user%' and pmis_key_fk not like '%SGS%' and department_fk in('Engg','Elec','S&T')";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getUnitsList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FOB> getFobFileTypeList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_file_type from `fob_file_type` ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

}
