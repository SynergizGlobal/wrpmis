package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.P6DataDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.P6Data;

@Repository
public class P6DataDaoImpl implements P6DataDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<P6Data> getFobList(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT fob_id as fob_id_fk,contract_id_fk FROM fob f "
					+"left join contract c on  f.contract_id_fk = c.contract_id  where fob_id is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int p6WbsUpload(List<P6Data> p6dataList,String userName) throws Exception {
		int  count = 0;
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement updateStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String insertQry ="INSERT INTO p6_wbs (contract_id_fk,fob_id_fk,p6_wbs_code,p6_wbs_name,p6_wbs_parent_code,p6_wbs_category_fk)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			for (P6Data obj : p6dataList) {
				int p = 1;
			    insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
			    insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
			    insertStmt.setString(p++,(obj.getP6_wbs_code()));
			    insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_wbs_name()))?obj.getP6_wbs_name():null));
			    insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_wbs_parent_code()))?obj.getP6_wbs_parent_code():null));
			    insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_wbs_category_fk()))?obj.getP6_wbs_category_fk():null));
			    insertStmt.addBatch();
				
			}
			 insertStmt.executeBatch();	
			 
			 
			 String testQry = "SELECT contract_id_fk,fob_id_fk,soft_delete_status_fk from p6_activity_data where contract_id_fk = ? and fob_id_fk = ?";
				
				selectStmt = con.prepareStatement(testQry);
				for (P6Data obj : p6dataList) {
					int p = 1;
					selectStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
					selectStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
					selectStmt.addBatch();
				}
				rs = selectStmt.executeQuery();	
				
		
				String updateQry ="update p6_activity_data set soft_delete_status_fk  = ? "
						+ "where contract_id_fk = ? and fob_id_fk = ?";
				
				updateStmt = con.prepareStatement(updateQry);
				for (P6Data obj : p6dataList) {
					
					int p = 1;
					updateStmt.setString(p++,(CommonConstants.INACTIVE));
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
					updateStmt.addBatch();
				}
				updateStmt.executeBatch();	
		
				String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk, data_date, "
						+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
						+ "VALUES"
						+ "(?,?,?,?,?,?,curdate())";
				
				insertStmt = con.prepareStatement(activityDataqry);
				for (P6Data obj : p6dataList) {
					int p = 1;
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
					// insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getData_type()))?obj.getData_type():null));
					 insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getData_date())?obj.getData_date():null));
					 insertStmt.setString(p++,(CommonConstants.ACTIVE));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_file_path()))?obj.getP6_file_path():null));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((userName))?userName:null));
					 //insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty((obj.getUploaded_date()))?obj.getUploaded_date():null));
					 insertStmt.addBatch();
				}	
				insertStmt.executeBatch();	
				if(insertStmt != null){insertStmt.close();}
				
			 count = p6dataList.size();
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	}

	@Override
	public int p6ActivitiesUpload(List<P6Data> p6dataList,String userName) throws Exception {
		int  count = 0;
		int[] c ;
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement updateStmt = null;
		
		try {
			con = dataSource.getConnection();
			String qry ="insert into p6_activity (p6_task_code) SELECT * FROM (SELECT ?) AS tmp WHERE NOT EXISTS (" + 
					"    SELECT p6_task_code FROM p6_activity WHERE p6_task_code = ? " + 
					") LIMIT 1;";
			insertStmt = con.prepareStatement(qry);
			for (P6Data obj : p6dataList) {
				  insertStmt.setString(1,(!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null));
				  insertStmt.setString(2,(!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null));
				  insertStmt.addBatch();
			}
			c = insertStmt.executeBatch();
			if(insertStmt != null){insertStmt.close();}
			for(int  i = 0; i< c.length; i++)
			if(c[i] > 0 ) {
				String updateQry ="update p6_activity set p6_wbs_code_fk =?,p6_activity_name = ?,status_fk = ?,"
						+ " baseline_start = ?,baseline_finish = ?,start = ?,finish = ?,`float` = ? "
						+ "where p6_task_code = ?";
				
				updateStmt = con.prepareStatement(updateQry);
				for (P6Data obj : p6dataList) {
					int p = 1;
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_wbs_code_fk()))?obj.getP6_wbs_code_fk():null));
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null));
				    updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null));
				    updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getBaseline_start())?obj.getBaseline_start():null));
				    updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getBaseline_finish())?obj.getBaseline_finish():null));
				    updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getStart())?obj.getStart():null));
				    updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null));
				    updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null));
				    updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null));
				    updateStmt.addBatch();
				
				}
				 updateStmt.executeBatch();	
			 }
			 count = p6dataList.size();
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return count;
	}

	@Override
	public int p6ActivitiesUpdate(List<P6Data> p6dataList,String userName) throws Exception {
		int  count = 0;
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String updateQry ="UPDATE p6_activity set p6_activity_name = ?,status_fk = ?,"
					+ " start = ?,finish = ?,`float` =  ? where  p6_wbs_code_fk = ? and p6_task_code = ? ";
			
			updateStmt = con.prepareStatement(updateQry);
			for (P6Data obj : p6dataList) {
				int p = 1;
				
				updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null));
				updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null));
				updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getStart())?obj.getStart():null));
				updateStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null));
				updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null));
				
				updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_wbs_code_fk()))?obj.getP6_wbs_code_fk():null));
				updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null));
				updateStmt.addBatch();
			}
			updateStmt.executeBatch();	
		
				String uQry ="update p6_activity_data set soft_delete_status_fk  = ? "
						+ "where contract_id_fk = ? and fob_id_fk = ?";
				
				updateStmt = con.prepareStatement(uQry);
				
				for (P6Data obj : p6dataList) {
					
					int p = 1;
					updateStmt.setString(p++,(CommonConstants.INACTIVE));
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
					updateStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
					updateStmt.addBatch();
				}
				updateStmt.executeBatch();	
		
				String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk, data_date, "
						+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
						+ "VALUES"
						+ "(?,?,?,?,?,?,curdate())";
				
				insertStmt = con.prepareStatement(activityDataqry);
				for (P6Data obj : p6dataList) {
					int p = 1;
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null));
					// insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getData_type()))?obj.getData_type():null));
					 insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getData_date())?obj.getData_date():null));
					 insertStmt.setString(p++,(CommonConstants.ACTIVE));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((obj.getP6_file_path()))?obj.getP6_file_path():null));
					 insertStmt.setString(p++,(!StringUtils.isEmpty((userName))?userName:null));
					 //insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty((obj.getUploaded_date()))?obj.getUploaded_date():null));
					 insertStmt.addBatch();
				}	
				insertStmt.executeBatch();	
				if(insertStmt != null){insertStmt.close();}
			
			 count = p6dataList.size();
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	
	}

	@Override
	public List<P6Data> getActivityDataList() throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="select contract_id_fk, fob_id_fk, DATE_FORMAT(data_date,'%d-%m-%Y') as data_date, soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, DATE_FORMAT(uploaded_date,'%d-%m-%Y') as uploaded_date  from p6_activity_data ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
}
