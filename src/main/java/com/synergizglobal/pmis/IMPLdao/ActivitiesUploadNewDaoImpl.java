package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.ActivitiesUploadNewDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.FormHistory;

@Repository
public class ActivitiesUploadNewDaoImpl implements ActivitiesUploadNewDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<Activity> getWorksInActivitiesUpload(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select work_id,work_name,work_short_name "
					+ "from work "
					+ "where work_id is not null " ;
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Activity>(Activity.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Activity> getContractsInActivitiesUpload(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select contract_id,contract_name,contract_short_name "
					+ "from contract "
					+ "where contract_id is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " GROUP BY contract_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	
	@Override
	public int[] uploadActivities(List<Activity> activityList, Activity obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int arr[] = new int[2];
		try {			
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			List<Activity> insertList = new ArrayList<Activity>();
			List<Activity> updateList = new ArrayList<Activity>();
			for (Activity activity : activityList) {
				boolean flag = isActivityExist(activity,con);
				if(flag) {
					updateList.add(activity);
				}else {
					insertList.add(activity);
				}
			}
			
			String insertQry = "INSERT INTO activities (contract_id_fk,structure_type_fk,from_structure_id,to_structure_id,section,line,structure,component,component_id,`order_x`,`order_y`,activity_name,planned_start,planned_finish,actual_start,actual_finish,unit,scope,completed,weightage,component_details,remarks,created_date,created_by_user_id_fk) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?)";
			stmt = con.prepareStatement(insertQry); 
			
			for (int i = 0;i < insertList.size();i++) {
				int p = 1;
				stmt.setString(p++, insertList.get(i).getContract_id_fk());
                stmt.setString(p++, insertList.get(i).getStructure_type());
                stmt.setString(p++, insertList.get(i).getFrom_structure_id());
                stmt.setString(p++, insertList.get(i).getTo_structure_id());
                stmt.setString(p++, insertList.get(i).getSection());	
                stmt.setString(p++, insertList.get(i).getLine());
                stmt.setString(p++, insertList.get(i).getStructure());
                
                stmt.setString(p++, insertList.get(i).getComponent());
                stmt.setString(p++, insertList.get(i).getComponent_id());
                stmt.setString(p++, insertList.get(i).getOrder_x());
                stmt.setString(p++, insertList.get(i).getOrder_y());
                stmt.setString(p++, insertList.get(i).getActivity_name());
                stmt.setString(p++, insertList.get(i).getPlanned_start());
                
                stmt.setString(p++, insertList.get(i).getPlanned_finish());
                stmt.setString(p++, insertList.get(i).getActual_start());
                stmt.setString(p++, insertList.get(i).getActual_finish());	
                stmt.setString(p++, insertList.get(i).getUnit());
                stmt.setString(p++, insertList.get(i).getScope());
                
                stmt.setString(p++, !StringUtils.isEmpty(insertList.get(i).getCompleted())?insertList.get(i).getCompleted():"0");
                stmt.setString(p++, insertList.get(i).getWeightage());
                String CD=insertList.get(i).getComponent_details();
                if(CD==null)
                {
                	CD="";
                }
                stmt.setString(p++, CD);	
                stmt.setString(p++, insertList.get(i).getRemarks());
                stmt.setString(p++, insertList.get(i).getCreated_by_user_id_fk());
                stmt.addBatch();
			}
			int[] insertCounts = stmt.executeBatch();
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			String updateQry = "UPDATE activities SET planned_start = ?,planned_finish = ?,actual_start = ?,actual_finish = ?,unit = ?,scope = ?,completed = ?,weightage = ?,component_details = ?,remarks = ?,modified_date = CURRENT_TIMESTAMP,modified_by_user_id_fk = ? "
					+ "WHERE (contract_id_fk = ? OR contract_id_fk IS NULL OR contract_id_fk = '') and (structure_type_fk = ? OR structure_type_fk IS NULL OR structure_type_fk = '') "
					+ "and (from_structure_id = ? OR from_structure_id IS NULL OR from_structure_id = '') and (to_structure_id = ? OR to_structure_id IS NULL OR to_structure_id = '') and (section = ? OR section IS NULL OR section = '') and (line = ? OR line IS NULL OR line = '') and (structure = ? OR structure IS NULL OR structure = '') and (component = ? OR component IS NULL OR component = '') "
					+ "and (component_id = ? OR component_id IS NULL OR component_id = '') and (activity_name = ? OR activity_name IS NULL OR activity_name = '') ";
			
			stmt = con.prepareStatement(updateQry); 
			
			for (int i = 0;i < updateList.size();i++) {
				int p = 1;
                stmt.setString(p++, updateList.get(i).getPlanned_start());		                    
                stmt.setString(p++, updateList.get(i).getPlanned_finish());
                stmt.setString(p++, updateList.get(i).getActual_start());
                stmt.setString(p++, updateList.get(i).getActual_finish());	
                stmt.setString(p++, updateList.get(i).getUnit());
                stmt.setString(p++, updateList.get(i).getScope());
                
                stmt.setString(p++, !StringUtils.isEmpty(updateList.get(i).getCompleted())?updateList.get(i).getCompleted():"0");
                stmt.setString(p++, updateList.get(i).getWeightage());
                String CD1=updateList.get(i).getComponent_details();
                if(CD1==null)
                {
                	CD1="";
                }
                stmt.setString(p++, CD1);	
                stmt.setString(p++, updateList.get(i).getRemarks());
                stmt.setString(p++, updateList.get(i).getModified_by_user_id_fk());
                
                stmt.setString(p++, updateList.get(i).getContract_id_fk());
                stmt.setString(p++, updateList.get(i).getStructure_type());
                stmt.setString(p++, updateList.get(i).getFrom_structure_id());
                stmt.setString(p++, updateList.get(i).getTo_structure_id());
                stmt.setString(p++, updateList.get(i).getSection());	
                stmt.setString(p++, updateList.get(i).getLine());
                stmt.setString(p++, updateList.get(i).getStructure());		                    
                stmt.setString(p++, updateList.get(i).getComponent());
                stmt.setString(p++, updateList.get(i).getComponent_id());		                	
                stmt.setString(p++, updateList.get(i).getActivity_name());
                stmt.addBatch();
                
			}
			int[] updateCounts = stmt.executeBatch();
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
					
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			double[] factors = new double[8];
			factors[0] = 0.0014;factors[7] = 0.0014;
			factors[1] = 0.0214;factors[6] = 0.0214;
			factors[2] = 0.1359;factors[5] = 0.1359;
			factors[3] = 0.3413;factors[4] = 0.3413;
			
			for (Activity aObj : activityList) {
				String activity_id = getActivityId(aObj, con);
				
				double total_scope = 0;
				if(!StringUtils.isEmpty(aObj.getScope())) {
					total_scope = Double.parseDouble(aObj.getScope());
				}
				double completed_scope = 0;
				if(!StringUtils.isEmpty(aObj.getCompleted())) {
					completed_scope = Double.parseDouble(aObj.getCompleted());
				}
				
				String actual_start_date = aObj.getActual_start();
				
				if(!StringUtils.isEmpty(activity_id) && total_scope != 0 && completed_scope != 0 && !StringUtils.isEmpty(actual_start_date)) {					
					aObj.setActivity_id_fk(activity_id);
					String deleteQry = "DELETE FROM activity_progress where activity_id_fk = ?";
					stmt = con.prepareStatement(deleteQry);  
					stmt.setString(1, activity_id);
					int cNo = stmt.executeUpdate();
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
					
					if(StringUtils.isEmpty(aObj.getActual_finish())) {
						aObj.setActual_finish(dateFormat.format(date));
					}
					String progress_date = null;
					
					
					String insert_qry = "INSERT into activity_progress (progress_date,activity_id_fk,completed_scope,attachment_url,remarks,created_date,created_by_user_id_fk) "
							+"VALUES (?,?,?,?,?,CURRENT_TIMESTAMP,?)";
					stmt = con.prepareStatement(insert_qry); 
					
					for (int i = 0;i < factors.length;i++) {
						double activity_scope = completed_scope * factors[i];
						
						Date curr_date = new Date();
						//String now = dateFormat.format(curr_date);
				    	String actual_finish = null;
				    	if(!StringUtils.isEmpty(aObj.getActual_finish())) {
				    		actual_finish = aObj.getActual_finish();
						}else {
							actual_finish = dateFormat.format(curr_date);
						}
				    	
				    	progress_date = getProgressdate(actual_finish,i+1,activity_id,con);
						
				    	int p = 1;
				    	stmt.setString(p++,progress_date);
				    	stmt.setString(p++,activity_id);
				    	stmt.setString(p++,String.valueOf(activity_scope));
				    	stmt.setString(p++,aObj.getAttachment_url());
				    	stmt.setString(p++,aObj.getRemarks());
				    	stmt.setString(p++,aObj.getCreated_by_user_id_fk());
				    	stmt.addBatch();
					}
					
					stmt.executeBatch();
					
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
					
				}
			}
			
			con.commit();
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			
			arr[0] = insertCounts.length;
		    arr[1] = updateCounts.length;
		    
		    FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
			formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
			formHistory.setModule_name_fk("Execution &  Monitoring");
			formHistory.setForm_name("Upload Activities");
			formHistory.setForm_action_type("Upload");
			formHistory.setForm_details(insertCounts.length +" activities inserted and "+updateCounts.length+" activities updated");
			formHistory.setWork_id_fk(obj.getWork_id());
			formHistory.setContract_id_fk(obj.getContract_id());
			
			boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
		    
		}catch(Exception e){ 
			e.printStackTrace();
			con.rollback();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return arr;
	}
	
	private String getProgressdate(String actual_finish, int i, String activity_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String progress_date = null;
		try {
			String qry = "select (actual_start + INTERVAL (((TO_DAYS(?) - TO_DAYS(actual_start)) * ?) / 8) DAY) AS date "
	    			+ "from activities where activity_id = ? ";
	    	
			
			stmt = con.prepareStatement(qry);
			int k = 1;			
			
			stmt.setString(k++, actual_finish);
			stmt.setInt(k++, i);
			stmt.setString(k++, activity_id);
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				progress_date = rs.getString("date");
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return progress_date;
	}

	private boolean isActivityExist(Activity obj, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			String qry = "select contract_id_fk from activities "
					+ "where activity_id is not null";
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
			} else {
				qry = qry + " and (contract_id_fk IS NULL OR contract_id_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type())) {
				qry = qry + " and structure_type_fk = ?";
			} else {
				qry = qry + " and (structure_type_fk IS NULL OR structure_type_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				qry = qry + " and section = ?";
			} else {
				qry = qry + " and (section IS NULL OR section = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				qry = qry + " and line = ?";
			} else {
				qry = qry + " and (line IS NULL OR line = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
			} else {
				qry = qry + " and (structure IS NULL OR structure = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				qry = qry + " and component = ?";
			} else {
				qry = qry + " and (component IS NULL OR component = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				qry = qry + " and component_id = ?";
			} else {
				qry = qry + " and (component_id IS NULL OR component_id = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				qry = qry + " and activity_name = ?";
			} else {
				qry = qry + " and (activity_name IS NULL OR activity_name = '')";
			}
			
			stmt = con.prepareStatement(qry);
			int k = 1;			
			
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				stmt.setString(k++, obj.getContract_id_fk());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type())) {
				stmt.setString(k++, obj.getStructure_type());
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				stmt.setString(k++, obj.getSection());
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				stmt.setString(k++, obj.getLine());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				stmt.setString(k++, obj.getStructure());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				stmt.setString(k++, obj.getComponent());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				stmt.setString(k++, obj.getComponent_id());
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				stmt.setString(k++, obj.getActivity_name());
			}
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return flag;
	}
	
	private String getActivityId(Activity obj, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String activity_id = null;
		try {
			String qry = "select activity_id from activities "
					+ "where activity_id is not null";
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
			} else {
				qry = qry + " and (contract_id_fk IS NULL OR contract_id_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type())) {
				qry = qry + " and structure_type_fk = ?";
			} else {
				qry = qry + " and (structure_type_fk IS NULL OR structure_type_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				qry = qry + " and section = ?";
			} else {
				qry = qry + " and (section IS NULL OR section = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				qry = qry + " and line = ?";
			} else {
				qry = qry + " and (line IS NULL OR line = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
			} else {
				qry = qry + " and (structure IS NULL OR structure = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				qry = qry + " and component = ?";
			} else {
				qry = qry + " and (component IS NULL OR component = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				qry = qry + " and component_id = ?";
			} else {
				qry = qry + " and (component_id IS NULL OR component_id = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				qry = qry + " and activity_name = ?";
			} else {
				qry = qry + " and (activity_name IS NULL OR activity_name = '')";
			}
			
			stmt = con.prepareStatement(qry);
			int k = 1;			
			
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				stmt.setString(k++, obj.getContract_id_fk());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type())) {
				stmt.setString(k++, obj.getStructure_type());
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				stmt.setString(k++, obj.getSection());
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				stmt.setString(k++, obj.getLine());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				stmt.setString(k++, obj.getStructure());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				stmt.setString(k++, obj.getComponent());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				stmt.setString(k++, obj.getComponent_id());
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				stmt.setString(k++, obj.getActivity_name());
			}
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				activity_id = rs.getString("activity_id");
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return activity_id;
	}
	
	/**
	 * @throws Exception ******************************************************************************/
	


	@Override
	public boolean addFileInActivitiesDataTable(String data_remarks, Activity obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String activities_data_id = null;		
		try {
			obj.setRemarks(data_remarks);
			obj.setStatus("Success");
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO activities_data"
					+ "(contract_id_fk,structure_type_fk,uploaded_file,status,remarks,uploaded_by_user_id_fk,uploaded_on) "
					+ "VALUES "
					+ "(:contract_id_fk,:structure_type_fk,:uploaded_file,:status,:remarks,:uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				activities_data_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setActivities_data_id(activities_data_id);
				flag = true;
				
				MultipartFile file = obj.getUploadFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.ACTIVITIES_UPLOAD_FILE_SAVING_PATH ;
					String fileName = activities_data_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					obj.setUploaded_file(fileName);
					String updateQry = "UPDATE activities_data set uploaded_file= :uploaded_file where activities_data_id= :activities_data_id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
					template.update(updateQry, paramSource1);
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public List<Activity> getWorksListFilter(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name,w.work_short_name "
					+ "from activities_data ad "
					+ "left join contract c on ad.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE ad.contract_id_fk IS NOT NULL ";
					
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and ad.contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + "and ad.structure_type_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Activity> getContractsListFilter(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select ad.contract_id_fk,c.contract_id,c.contract_name,c.contract_short_name "
					+ "from activities_data ad "
					+ "left join contract c on ad.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE ad.contract_id_fk IS NOT NULL ";
					
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and ad.contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + "and ad.structure_type_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " GROUP BY ad.contract_id_fk ORDER BY ad.contract_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Activity> getStructureTypesListFilter(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select ad.structure_type_fk "
					+ "from activities_data ad "
					+ "left join contract c on ad.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE ad.structure_type_fk IS NOT NULL ";
					
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and ad.contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + "and ad.structure_type_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " GROUP BY ad.structure_type_fk ORDER BY ad.structure_type_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Activity> getActivitiesUploadFilesList(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select activities_data_id,contract_id_fk,structure_type_fk,uploaded_file,ad.status,ad.remarks,"
					+ "uploaded_by_user_id_fk,DATE_FORMAT(uploaded_on,'%d-%m-%Y') as uploaded_on,"
					+ "c.work_id_fk,w.work_id,w.work_name,w.work_short_name,"
					+ "c.contract_id,c.contract_name,c.contract_short_name "
					+ "from activities_data ad "
					+ "left join contract c on ad.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE ad.activities_data_id IS NOT NULL ";
					
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and ad.contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + "and ad.structure_type_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " ORDER BY ad.uploaded_on desc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Activity> getExistingStructures(String contract_id) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "SELECT distinct structure_type_fk,structure "
					+ "FROM structure "
					+ "where structure_id in (SELECT structure_id_fk FROM structure_contract_responsible_people where contract_id_fk = ?);";
			
			objsList = jdbcTemplate.query( qry,new Object[]{contract_id}, new BeanPropertyRowMapper<Activity>(Activity.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

}

