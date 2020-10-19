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
	public int updateP6Activities(List<P6Data> p6dataList,P6Data pobj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			
			con.setAutoCommit(false);
			String updateQry ="update p6_activity_data set soft_delete_status_fk  = ? "
					+ "where contract_id_fk = ? and fob_id_fk = ?";
			
			stmt = con.prepareStatement(updateQry);
			int p = 1;
			stmt.setString(p++,(CommonConstants.INACTIVE));
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getFob_id_fk()))?pobj.getFob_id_fk():null);
			
			stmt.executeUpdate();	
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
	
			String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk,upload_type,data_date, "
					+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
					+ " VALUES (?,?,?,?,?,?,?,curdate())";
			
			stmt = con.prepareStatement(activityDataqry);
			p = 1;
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getFob_id_fk()))?pobj.getFob_id_fk():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getUpload_type()))?pobj.getUpload_type():null);
			stmt.setString(p++,!StringUtils.isEmpty(pobj.getData_date())?pobj.getData_date():null);
			stmt.setString(p++,CommonConstants.ACTIVE);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getP6_file_path()))?pobj.getP6_file_path():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getUploaded_by_user_id_fk()))?pobj.getUploaded_by_user_id_fk():null);
			stmt.executeUpdate();
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			String updateActivitiesQry ="UPDATE p6_activity set p6_activity_name = ?,status_fk = ?,"
					+ " `start` = ?,finish = ?,`float` =  ? where  p6_wbs_code_fk = ? and p6_task_code = ? ";
			
			stmt = con.prepareStatement(updateActivitiesQry);
			for (P6Data obj : p6dataList) {
				p = 1;				
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty(obj.getStart())?obj.getStart():null);
				stmt.setString(p++,!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null);				
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_code_fk()))?obj.getP6_wbs_code_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
				stmt.addBatch();
			}
			int[] c = stmt.executeBatch();		
			for (int i = 0; i < c.length; i++) {
				count = count + c[i];
			}
			con.commit();
		}catch(Exception e){ 
			e.printStackTrace();
			con.rollback();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return count;
	
	}

	@Override
	public List<P6Data> getActivityDataList() throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="select contract_id_fk, fob_id_fk,upload_type, DATE_FORMAT(data_date,'%d-%m-%Y') as data_date, soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, DATE_FORMAT(uploaded_date,'%d-%m-%Y') as uploaded_date  from p6_activity_data ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			
		}
		return objsList;
	}


	@Override
	public String uploadP6WBSActivities(List<P6Data> wbsList, List<P6Data> activitiesList, P6Data pobj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String counts = null;
		int wbsCount = 0;
		int activitiesCount = 0;
		try {
			con = dataSource.getConnection();
			
			con.setAutoCommit(false);
			String updateQry ="update p6_activity_data set soft_delete_status_fk  = ? "
					+ "where contract_id_fk = ? and fob_id_fk = ?";
			
			stmt = con.prepareStatement(updateQry);
			int p = 1;
			stmt.setString(p++,(CommonConstants.INACTIVE));
			stmt.setString(p++,(!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null));
			stmt.setString(p++,(!StringUtils.isEmpty((pobj.getFob_id_fk()))?pobj.getFob_id_fk():null));
			
			stmt.executeUpdate();	
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
	
			String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk,upload_type, data_date, "
					+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
					+ " VALUES (?,?,?,?,?,?,?,curdate())";
			
			stmt = con.prepareStatement(activityDataqry);
			p = 1;
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getFob_id_fk()))?pobj.getFob_id_fk():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getUpload_type()))?pobj.getUpload_type():null);
			stmt.setString(p++,!StringUtils.isEmpty(pobj.getData_date())?pobj.getData_date():null);
			stmt.setString(p++,CommonConstants.ACTIVE);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getP6_file_path()))?pobj.getP6_file_path():null);
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getUploaded_by_user_id_fk()))?pobj.getUploaded_by_user_id_fk():null);
			stmt.executeUpdate();
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			/*String wbsQry = "INSERT INTO p6_wbs (contract_id_fk,fob_id_fk,p6_wbs_code,p6_wbs_name,p6_wbs_parent_code,p6_wbs_category_fk)"
					+ " VALUES (?,?,?,?,?,?)";*/
			
			String wbsQry = "INSERT INTO p6_wbs (contract_id_fk,fob_id_fk,p6_wbs_code,p6_wbs_name,p6_wbs_parent_code,p6_wbs_category_fk)" + 
					"SELECT * FROM (SELECT ?,?,?,?,?,?) AS tmp " + 
					"WHERE NOT EXISTS (" + 
					"    SELECT contract_id_fk,fob_id_fk,p6_wbs_code FROM p6_wbs WHERE contract_id_fk = ? and fob_id_fk = ? and p6_wbs_code = ?" + 
					") LIMIT 1;";
			stmt = con.prepareStatement(wbsQry);
			for (P6Data obj : wbsList) {
				p = 1;
				stmt.setString(p++,!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null);
				stmt.setString(p++,obj.getP6_wbs_code());
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_name()))?obj.getP6_wbs_name():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_parent_code()))?obj.getP6_wbs_parent_code():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_category_fk()))?obj.getP6_wbs_category_fk():null);
				
				stmt.setString(p++,!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null);
				stmt.setString(p++,obj.getP6_wbs_code());
				
				stmt.addBatch();
				
			}
			int[] c = stmt.executeBatch();		
			for (int i = 0; i < c.length; i++) {
				wbsCount = wbsCount + c[i];
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			/*String activitiesQry ="INSERT INTO p6_activity(p6_task_code,p6_wbs_code_fk,p6_activity_name,status_fk,baseline_start,baseline_finish,`start`,finish,`float`)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";*/
			
			String activitiesQry = "INSERT INTO p6_activity (p6_task_code,p6_wbs_code_fk,p6_activity_name,status_fk,baseline_start,baseline_finish,`start`,finish,`float`)" + 
					"SELECT * FROM (SELECT ?,?,?,?,?,?,?,?,?) AS tmp " + 
					"WHERE NOT EXISTS (" + 
					"    SELECT p6_task_code,p6_wbs_code_fk FROM p6_activity WHERE p6_task_code = ? and p6_wbs_code_fk = ?" + 
					") LIMIT 1;";
			
			stmt = con.prepareStatement(activitiesQry);
			for (P6Data obj : activitiesList) {
				p = 1;
			    stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_code_fk()))?obj.getP6_wbs_code_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
			    stmt.setString(p++,!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null);
			    stmt.setString(p++,!StringUtils.isEmpty(obj.getBaseline_start())?obj.getBaseline_start():null);
			    stmt.setString(p++,!StringUtils.isEmpty(obj.getBaseline_finish())?obj.getBaseline_finish():null);
			    stmt.setString(p++,!StringUtils.isEmpty(obj.getStart())?obj.getStart():null);
			    stmt.setString(p++,!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null);
			    stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null);
			    
			    stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_code_fk()))?obj.getP6_wbs_code_fk():null);
				
			    stmt.addBatch();			
			}
			c = stmt.executeBatch();		
			for (int i = 0; i < c.length; i++) {
				activitiesCount = activitiesCount + c[i];
			}
			counts = wbsCount + "," + activitiesCount;	
			con.commit();
		}catch(Exception e){ 
			e.printStackTrace();
			con.rollback();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return counts;
	}
	
}
