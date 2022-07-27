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

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.P6DataDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.P6Data;

@Repository
public class P6DataDaoImpl implements P6DataDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<P6Data> getContractsList(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT contract_id,contract_name FROM contract ";
			objsList = jdbcTemplate.query( qry,new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<P6Data> getFobList(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			/*String qry ="SELECT fob_id,fob_name FROM fob where contract_id_fk = ?";*/
			
			String qry ="SELECT fob_id,fob_name FROM fob "
					+ "where fob_id IN (select fob_id_fk from fob_contract_responsible_people where contract_id_fk = ? group by fob_id_fk) ";
			
			objsList = jdbcTemplate.query( qry,new Object[]{obj.getContract_id_fk()}, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<P6Data> getContractsListFilter(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT contract_id_fk as contract_id,contract_name FROM p6_activity_data "
					+ "LEFT OUTER JOIN contract ON contract_id_fk = contract_id "
					+ "WHERE fob_id_fk is not null AND contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				qry = qry + "and upload_type = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + "and soft_delete_status_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				pValues[i++] = obj.getFob_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				pValues[i++] = obj.getUpload_type();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			qry = qry + "GROUP BY contract_id_fk";
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<P6Data> getFobListFilter(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT fob_id_fk as fob_id,fob_name FROM p6_activity_data p "
					+ "LEFT OUTER JOIN fob f ON fob_id_fk = fob_id "
					+ "where fob_id_fk is not null and fob_id <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "and p.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				qry = qry + "and upload_type = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + "and soft_delete_status_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				pValues[i++] = obj.getFob_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				pValues[i++] = obj.getUpload_type();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			qry = qry + "GROUP BY fob_id_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<P6Data> getUploadTypesFilter(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT upload_type FROM p6_activity_data "
					+ "where fob_id_fk is not null AND upload_type is not null and upload_type <> '' ";
					
					int arrSize = 0;
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
						qry = qry + "and contract_id_fk = ? ";
						arrSize++;
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
						qry = qry + "and fob_id_fk = ? ";
						arrSize++;
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
						qry = qry + "and upload_type = ? ";
						arrSize++;
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
						qry = qry + "and soft_delete_status_fk = ? ";
						arrSize++;
					}
					Object[] pValues = new Object[arrSize]; 
					int i = 0;
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
						pValues[i++] = obj.getContract_id();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
						pValues[i++] = obj.getFob_id();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
						pValues[i++] = obj.getUpload_type();
					}
					if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
						pValues[i++] = obj.getStatus_fk();
					}
					qry = qry + "GROUP BY upload_type";
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<P6Data> getStatusListFilter(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT soft_delete_status_fk FROM p6_activity_data "
					+ "where fob_id_fk is not null AND soft_delete_status_fk is not null and soft_delete_status_fk <> '' ";
					int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				qry = qry + "and upload_type = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + "and soft_delete_status_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				pValues[i++] = obj.getFob_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				pValues[i++] = obj.getUpload_type();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			qry = qry + "GROUP BY soft_delete_status_fk";
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<P6Data>(P6Data.class));	
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
					+ "where contract_id_fk = ?";
			
			if(!StringUtils.isEmpty((pobj.getFob_id_fk()))) {
				updateQry = updateQry + " and fob_id_fk = ?";
			}
			
			stmt = con.prepareStatement(updateQry);
			int p = 1;
			stmt.setString(p++,(CommonConstants.INACTIVE));
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
			if(!StringUtils.isEmpty((pobj.getFob_id_fk()))) {
				stmt.setString(p++,pobj.getFob_id_fk());
			}
			stmt.executeUpdate();	
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
	
			String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk,upload_type,data_date, "
					+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
					+ " VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
			
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
					+ " start = ?,finish = ?,float =  ? where  p6_wbs_code_fk = ? and p6_task_code = ? ";
			
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
			
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(pobj.getCreated_by_user_id_fk());
			formHistory.setUser(pobj.getDesignation()+" - "+pobj.getUser_name());
			formHistory.setModule_name_fk("Execution &  Monitoring");
			formHistory.setForm_name("P6 Data");
			formHistory.setForm_action_type("Update");
			formHistory.setForm_details("Data date updated and "+ count + " activities updated");
			//formHistory.setWork_id_fk(pobj.getWork_id_fk());
			formHistory.setContract(pobj.getContract_id_fk());
			
			boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			
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
	public List<P6Data> getActivityDataList(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="select contract_id_fk, fob_id_fk,upload_type, FORMAT(data_date,'%d-%m-%Y') as data_date, soft_delete_status_fk,"
					+ " p6_file_path, uploaded_by_user_id_fk, FORMAT(uploaded_date,'%d-%m-%Y  %h:%i %p')  uploaded_date  "
					+ "from p6_activity_data "
					+ "WHERE fob_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				qry = qry + "and upload_type = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + "and soft_delete_status_fk = ? ";
				arrSize++;
			}
			qry = qry + " ORDER BY  FORMAT(uploaded_date,'%y-%m-%d %H : %i : %s') desc ";
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id_fk())) {
				pValues[i++] = obj.getFob_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpload_type())) {
				pValues[i++] = obj.getUpload_type();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
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
					+ "where contract_id_fk = ?";
			
			if(!StringUtils.isEmpty((pobj.getFob_id_fk()))) {
				updateQry = updateQry + " and fob_id_fk = ?";
			}
			
			stmt = con.prepareStatement(updateQry);
			int p = 1;
			stmt.setString(p++,(CommonConstants.INACTIVE));
			stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
			if(!StringUtils.isEmpty((pobj.getFob_id_fk()))) {
				stmt.setString(p++,pobj.getFob_id_fk());
			}
			
			stmt.executeUpdate();	
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
	
			String activityDataqry = "INSERT INTO p6_activity_data (contract_id_fk, fob_id_fk,upload_type, data_date, "
					+ "soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date)"
					+ " VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
			
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
			
			String wbsQry = "INSERT INTO p6_wbs (contract_id_fk,fob_id_fk,p6_wbs_code,p6_wbs_name,p6_wbs_parent_code,p6_wbs_category_fk)"
					+ " VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE p6_wbs_code = p6_wbs_code";
			stmt = con.prepareStatement(wbsQry);
			for (P6Data obj : wbsList) {
				
				PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) as total FROM p6_wbs WHERE p6_wbs_code = ?");
				p = 1;
				pstmt.setString(p++,obj.getP6_wbs_code());
				rs = pstmt.executeQuery();		
				int count = 0;
				if(rs.next()) {
					count = rs.getInt("total");
				}
				DBConnectionHandler.closeJDBCResoucrs(null, pstmt, rs);
				if(count == 0) {
					p = 1;
					stmt.setString(p++,!StringUtils.isEmpty((obj.getContract_id_fk()))?obj.getContract_id_fk():null);
					stmt.setString(p++,!StringUtils.isEmpty((obj.getFob_id_fk()))?obj.getFob_id_fk():null);
					stmt.setString(p++,obj.getP6_wbs_code());
					stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_name()))?obj.getP6_wbs_name():null);
					stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_parent_code()))?obj.getP6_wbs_parent_code():null);
					stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_wbs_category_fk()))?obj.getP6_wbs_category_fk():null);
					
					stmt.addBatch();
				}				
			}
			int[] c = stmt.executeBatch();		
			for (int i = 0; i < c.length; i++) {
				wbsCount = wbsCount + c[i];
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			String activitiesQry ="INSERT INTO p6_activity(p6_task_code,p6_wbs_code_fk,p6_activity_name,status_fk,baseline_start,baseline_finish,start,finish,float)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			
			stmt = con.prepareStatement(activitiesQry);
			for (P6Data obj : activitiesList) {
				
				PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) as total FROM p6_activity WHERE p6_task_code = ? and p6_wbs_code_fk = ?");
				p = 1;
				pstmt.setString(p++,obj.getP6_task_code());
				pstmt.setString(p++,obj.getP6_wbs_code_fk());
				rs = pstmt.executeQuery();		
				int count = 0;
				if(rs.next()) {
					count = rs.getInt("total");
				}
				DBConnectionHandler.closeJDBCResoucrs(null, pstmt, rs);
				if(count == 0) {
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
					
				    stmt.addBatch();
				}
			}
			c = stmt.executeBatch();		
			for (int i = 0; i < c.length; i++) {
				activitiesCount = activitiesCount + c[i];
			}
			counts = wbsCount + "," + activitiesCount;	
			con.commit();
			
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(pobj.getCreated_by_user_id_fk());
			formHistory.setUser(pobj.getDesignation()+" - "+pobj.getUser_name());
			formHistory.setModule_name_fk("Execution &  Monitoring");
			formHistory.setForm_name("P6 Data");
			formHistory.setForm_action_type("Add");
			formHistory.setForm_details("Data date updated and "+ counts + " WBS, activities added");
			//formHistory.setWork_id_fk(pobj.getWork_id_fk());
			formHistory.setContract(pobj.getContract_id_fk());
			
			boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
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
