package com.synergizglobal.pmis.IMPLdao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.UAVDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.UAV;


@Repository
public class UAVDaoImpl implements UAVDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ; 
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public String updateP6Activities(List<UAV> UAVList,UAV pobj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String count = "";
		int counts = 0;
		String lineNos = null;
		try {
			con = dataSource.getConnection();
			
			con.setAutoCommit(false);
			String updateQry ="update p6_data set soft_delete_status_fk  = ? "
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
	
			String activityDataqry = "INSERT INTO p6_data (contract_id_fk, fob_id_fk,upload_type,data_date, "
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
			
			String updateActivitiesQry ="UPDATE p6_activities set "
					+ "status_fk = ?,start = ?,finish = ?,float =  ? ,p6_activity_name = ?";
					if(pobj.getIsRevised().contentEquals("Yes")) {
						updateActivitiesQry = updateActivitiesQry + ",baseline_start = ?,baseline_finish = ? ";

					}
			updateActivitiesQry	= updateActivitiesQry+ ",modified_by_user_id_fk = ?, modified_date = CURRENT_TIMESTAMP where  contract_id_fk = ? and task_code = ? ";
			
			stmt = con.prepareStatement(updateActivitiesQry);
			int lineNo = 3;
			for (UAV obj : UAVList) {
				p = 1;				
				
				stmt.setString(p++,!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null);

				stmt.setString(p++,!StringUtils.isEmpty(obj.getStart())?obj.getStart():null);
				stmt.setString(p++,!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null);	
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
				if(pobj.getIsRevised().contentEquals("Yes")) {
					//stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
					stmt.setString(p++,!StringUtils.isEmpty(obj.getBaseline_start())?obj.getBaseline_start():null);
					stmt.setString(p++,!StringUtils.isEmpty(obj.getBaseline_finish())?obj.getBaseline_finish():null);
					
				}
				stmt.setString(p++,!StringUtils.isEmpty((pobj.getModified_by_user_id_fk()))?pobj.getModified_by_user_id_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
				stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
				stmt.addBatch();
				
			} 
			int[] c = stmt.executeBatch();
			String noErr = "",err = "";
			for (int i = 0; i < c.length; i++) {
				if((c[i] > 0)) {
					counts = counts + c[i];
					noErr = Integer.toString(counts);
				}else {
					if(lineNos != null) {
						lineNos = lineNos +", "+ lineNo;
					}else {
						lineNos = lineNo+"";
					}
					if(!lineNos.contains(",")) {
						err = "^ row "+lineNos+ " Not Updated Because Contract or Activity ID Missmatch";
					}else {
						err = "^ rows "+lineNos+ " Not Updated Because Contract or Activity ID Missmatch";
					}
			    }
				lineNo++;
			}
			count = noErr + err;
			con.commit();
			
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(pobj.getCreated_by_user_id_fk());
			formHistory.setUser(pobj.getDesignation()+" - "+pobj.getUser_name());
			formHistory.setModule_name_fk("Execution &  Monitoring");
			formHistory.setForm_name("P6 Data");
			formHistory.setForm_action_type("Update");
			formHistory.setForm_details("Data date updated and "+ counts + " activities updated");
			//formHistory.setWork(pobj.getWork_id_fk());
			formHistory.setContract_id_fk(pobj.getContract_id_fk());
			
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
	public List<UAV> getActivityDataList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="select contract_id_fk, fob_id_fk,upload_type, FORMAT(data_date,'dd-MM-yyyy') as data_date, soft_delete_status_fk,"
					+ " p6_file_path, uploaded_by_user_id_fk, FORMAT(uploaded_date,'dd-MM-yyyy  hh:mm ss') uploaded_date  "
					+ "from p6_data "
					+ "WHERE (fob_id_fk is null OR fob_id_fk = '') ";
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
			qry = qry + " ORDER BY  FORMAT(uploaded_date,'yyyy-MM-dd hh:mm ss') desc ";
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			
		}
		return objsList;
	}


	@Override
	public String uploadP6WBSActivities(List<UAV> wbsList, List<UAV> activitiesList, UAV pobj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtUpdate = null;
		ResultSet rs = null;
		String counts = null;
		String rows = null;
		String norows = null;
		int wbsCount = 0;
		int wbsCountUpdate = 0;
		int activitiesCount = 3;
		int rowNo = 3;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			
			con.setAutoCommit(false);
			String updateQry ="update p6_data set soft_delete_status_fk  = ? "
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
	
			String activityDataqry = "INSERT INTO p6_data (contract_id_fk, fob_id_fk,upload_type, data_date, "
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
			int row =3;
			String wbsQry = "INSERT INTO p6_activities (task_code, contract_id_fk, structure_id_fk, p6_activity_name, from_structure_id, to_structure_id, "
					+ "section, line, component, component_id, baseline_start, baseline_finish, start, finish, float, status_fk, unit, scope, "
					+ "completed, weightage, component_details, remarks, created_by_user_id_fk,original_duration,created_date)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CAST(? as decimal(18,2)),CAST(? as decimal(18,2)),CAST(? as decimal(18,2)),?,?,?,?,CURRENT_TIMESTAMP) ";
			
			String wbsUpdateQry = "Update p6_activities set structure_id_fk = ?, p6_activity_name = ?, from_structure_id = ?, to_structure_id = ?, "
					+ "section = ?, line = ?, component = ?, component_id = ?, baseline_start = ?, baseline_finish = ?, start = ?, finish = ?, float = ?, status_fk = ?, unit = ?, scope = CAST(? as decimal(18,2)), "
					+ "completed = CAST(? as decimal(18,2)), weightage = CAST(? as decimal(18,2)), component_details = ?, remarks = ?, modified_by_user_id_fk = ?,original_duration = ?, modified_date = CURRENT_TIMESTAMP where task_code = ? and contract_id_fk = ?";
			stmt = con.prepareStatement(wbsQry);
			stmtUpdate = con.prepareStatement(wbsUpdateQry);
			int loop=0;
			int loop1=0;
			for (UAV obj : activitiesList) {
						PreparedStatement pstmt = con.prepareStatement("SELECT p6_activity_id as p6_activity_id FROM p6_activities WHERE task_code = ? and contract_id_fk = ?");
						p = 1;
						pstmt.setString(p++,obj.getP6_task_code());
						pstmt.setString(p++,pobj.getContract_id_fk());
						rs = pstmt.executeQuery();		
						String p6_activity_id = null;
						if(rs.next()) {
							p6_activity_id = rs.getString("p6_activity_id");
						}
						DBConnectionHandler.closeJDBCResoucrs(null, pstmt, rs);
						PreparedStatement structureStmt = con.prepareStatement("SELECT top 1 structure_id as structure_id_fk FROM structure WHERE work_id_fk=(select work_id_fk from contract where contract_id = '"+pobj.getContract_id_fk()+"') and structure_type_fk = ? and structure = ? and structure_id in(select structure_id_fk from structure_contract_responsible_people where contract_id_fk='"+pobj.getContract_id_fk()+"')");
						p = 1;
						structureStmt.setString(p++,obj.getStructure_type_fk());
						structureStmt.setString(p++,obj.getStructure());
						rs = structureStmt.executeQuery();		
						String val = null;
						if(rs.next()) {
							val = rs.getString("structure_id_fk");
						}
						DBConnectionHandler.closeJDBCResoucrs(null, structureStmt, rs);
						obj.setStructure_id_fk(val);
						String ContractIs_Available = "";
						if(!StringUtils.isEmpty(obj.getStructure_id_fk())) {
							DBConnectionHandler.closeJDBCResoucrs(null, pstmt, rs);
							PreparedStatement contarctStmt = con.prepareStatement("SELECT contract_id_fk as contract_id_fk FROM structure_contract_responsible_people WHERE contract_id_fk = ? and structure_id_fk = ?");
							p = 1;
							contarctStmt.setString(p++,pobj.getContract_id_fk());
							contarctStmt.setString(p++,val);
							rs = contarctStmt.executeQuery();		
							
							if(rs.next()) {
								ContractIs_Available = rs.getString("contract_id_fk");
							}
							DBConnectionHandler.closeJDBCResoucrs(null, contarctStmt, rs);
						}
				
						if(!StringUtils.isEmpty(ContractIs_Available) ) {
							if( StringUtils.isEmpty(p6_activity_id) && p6_activity_id == null) {
								p = 1;
								System.out.println(activitiesCount++);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
								stmt.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
								stmt.setString(p++,obj.getStructure_id_fk());
								stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getFrom_structure_id()))?obj.getFrom_structure_id():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getTo_structure_id()))?obj.getTo_structure_id():null);
								
								stmt.setString(p++,!StringUtils.isEmpty((pobj.getSection()))?pobj.getSection():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getLine()))?obj.getLine():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getComponent()))?obj.getComponent():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getComponent_id()))?obj.getComponent_id():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getBaseline_start()))?obj.getBaseline_start():null);
								
								stmt.setString(p++,!StringUtils.isEmpty((obj.getBaseline_finish()))?obj.getBaseline_finish():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getStart()))?obj.getStart():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getFinish()))?obj.getFinish():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getUnit()))?obj.getUnit():null);
								
								stmt.setString(p++,!StringUtils.isEmpty((obj.getScope()))?obj.getScope():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getCompleted()))?obj.getCompleted():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getWeightage()))?obj.getWeightage():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getComponent_details()))?obj.getComponent_details():null);
								stmt.setString(p++,!StringUtils.isEmpty((obj.getRemarks()))?obj.getRemarks():null);
								stmt.setString(p++,!StringUtils.isEmpty((pobj.getCreated_by_user_id_fk()))?pobj.getCreated_by_user_id_fk():null);
								stmt.setString(p++,!StringUtils.isEmpty((pobj.getOriginal_duration()))?pobj.getOriginal_duration():null);
								stmt.executeUpdate();
								loop++;
							}else {
								
								String qry = "DELETE FROM p6_activity_progress WHERE p6_activity_id_fk = ? ";
								PreparedStatement deleteExistingIDStmt = con.prepareStatement(qry);
								deleteExistingIDStmt.setString(1, p6_activity_id);
								int c = deleteExistingIDStmt.executeUpdate();  
								DBConnectionHandler.closeJDBCResoucrs(null, deleteExistingIDStmt, rs);
								System.out.println("update "+activitiesCount++);
								p = 1;
								stmtUpdate.setString(p++,obj.getStructure_id_fk());
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getP6_activity_name()))?obj.getP6_activity_name():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getFrom_structure_id()))?obj.getFrom_structure_id():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getTo_structure_id()))?obj.getTo_structure_id():null);
								
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getSection()))?obj.getSection():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getLine()))?obj.getLine():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getComponent()))?obj.getComponent():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getComponent_id()))?obj.getComponent_id():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getBaseline_start()))?obj.getBaseline_start():null);
								
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getBaseline_finish()))?obj.getBaseline_finish():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getStart()))?obj.getStart():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getFinish()))?obj.getFinish():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getP6_float()))?obj.getP6_float():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getStatus_fk()))?obj.getStatus_fk():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getUnit()))?obj.getUnit():null);
								
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getScope()))?obj.getScope():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getCompleted()))?obj.getCompleted():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getWeightage()))?obj.getWeightage():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getComponent_details()))?obj.getComponent_details():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getRemarks()))?obj.getRemarks():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((pobj.getModified_by_user_id_fk()))?pobj.getModified_by_user_id_fk():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((pobj.getOriginal_duration()))?pobj.getOriginal_duration():null);
								
								stmtUpdate.setString(p++,!StringUtils.isEmpty((obj.getP6_task_code()))?obj.getP6_task_code():null);
								stmtUpdate.setString(p++,!StringUtils.isEmpty((pobj.getContract_id_fk()))?pobj.getContract_id_fk():null);
								stmtUpdate.executeUpdate();
								loop1++;
								
							}
							rowNo ++;
						}else {
							if(norows != null) {
								norows = norows +", "+ rowNo;
							}else {
								norows = rowNo+"";
							}
							rowNo ++;
						}
			}
			//int[] c = stmt.executeBatch();	
			//int[] d = stmtUpdate.executeBatch();	
			for (int i = 0; i < loop; i++) {
				wbsCount = wbsCount + (i+1);
			}
			for (int i = 0; i < loop1; i++) {
				wbsCountUpdate = wbsCountUpdate + (i+1);
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmtUpdate, rs);
			counts = wbsCount + "," + wbsCountUpdate;
			rows = "<span style='color:green;'> "+wbsCount + " records Added, " + wbsCountUpdate+" records Updated </span><br>";	
			if(!StringUtils.isEmpty(norows)) {
				if(!norows.contains(",")) {
					rows = rows +"<span style='color:red;'> row no ( "+ norows+" )  Not Inserted Because Selected Contract & Structure Type & Structure combination is not present in Structure</span> ";
				}else {
					rows = rows +"<span style='color:red;'> row nos ( "+ norows+" )  Not Inserted Because Selected Contract & Structure Type & Structure combination is not present in Structure</span> ";
				}
			}
			con.commit();
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(pobj.getCreated_by_user_id_fk());
			formHistory.setUser(pobj.getDesignation()+" - "+pobj.getUser_name());
			formHistory.setModule_name_fk("Execution &  Monitoring");
			formHistory.setForm_name("P6 Data");
			formHistory.setForm_action_type("Add");
			formHistory.setForm_details("Data date updated and "+ counts + " WBS, activities added");
			//formHistory.setWork(pobj.getWork_id_fk());
			formHistory.setContract_id_fk(pobj.getContract_id_fk());
			
			boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
		}catch(Exception e){ 
			e.printStackTrace();
			con.rollback();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return rows;
	}



	@Override
	public List<UAV> getWorksList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="SELECT c.work_id_fk,work_id,work_name,work_short_name FROM contract c  "
					+ "left join work  on c.work_id_fk = work_id  where work_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + "and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or"
						+ " contract_id in(select contract_id_fk from contract_executive where  executive_user_id_fk = ?) "
						+ " or contract_id in(select contract_id_fk from structure_contract_responsible_people where  responsible_people_id_fk = ?) ) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry +"group by c.work_id_fk,work_id,work_name,work_short_name ";
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<UAV> getProjectsList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="SELECT distinct p.project_id as project_id_fk,p.project_name FROM contract c  "
					+ "left join work w on c.work_id_fk = w.work_id  left join project p  on p.project_id = w.project_id_fk where w.work_id is not null and p.project_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + "and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or"
						+ " contract_id in(select contract_id_fk from contract_executive where  executive_user_id_fk = ?) "
						+ " or contract_id in(select contract_id_fk from structure_contract_responsible_people where  responsible_people_id_fk = ?) ) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry +"group by p.project_id,p.project_name ";
			Object[] pValues = new Object[arrSize]; 
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(UAV obj) throws Exception {
		int totalRecords = 0;
		try {
			String qry  ="select count(*) from(select id,work as work_id,survey_date,video_file_name,created_date as upload_date,work_short_name from uav_video_data_structure u " + 
					"inner join work w on w.work_id=u.work) as a where 0=0 ";		
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and survey_date = ?";
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
						
			
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<UAV> getUAVList(UAV obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry = "select * from (select u.id,work as work_id,survey_date,video_file_name,n.station_name as from_station,n1.station_name as to_station,created_date as upload_date,work_short_name from uav_video_data_structure u inner join stationnames n on n.id=u.from_station inner join stationnames n1 on n1.id=u.to_station " + 
					"inner join work w on w.work_id=u.work " + 
					
					") as a where 0=0 ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and survey_date = ?";
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UAV>(UAV.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UAV> getUavVideoDataStructure(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="select id,project as project_id_fk,work as work_id_fk,survey_date,video_file_name,upload_date,work_short_name from uav_video_data_structure u left join work w on w.work_id=u.work where 0=0 ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and work = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			
		}
		return objsList;
	}

	@Override
	public List<UAV> getWorksFilterListInUAV(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry = "select distinct work_id as work_id_fk,work_short_name from (select id,work as work_id,survey_date,video_file_name,created_date as upload_date,work_short_name from uav_video_data_structure u " + 
					"inner join work w on w.work_id=u.work ) as a where 0=0  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and survey_date = ?";
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UAV>(UAV.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UAV> getSurveyDatesFilterList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry="select distinct u.survey_date from uav_video_data_structure u  where 0=0  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and u.work = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and u.survey_date = ?";
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UAV>(UAV.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UAV> getSurveyDateVideoSpecifications(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry = "select distinct n.station_name +' to '+n1.station_name as to_station,u.video_file_name from uav_video_data_structure u inner join stationnames n on n.id=u.from_station inner join stationnames n1 on n1.id=u.to_station where 0=0  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and u.work = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and u.survey_date = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and n.station_name = ? and n1.station_name=?";
				arrSize++;
				arrSize++;
			}				
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				
				String[] SplitStr=obj.getStructure().split(" to ");
				pValues[i++] = SplitStr[0];
				pValues[i++] = SplitStr[1];
			}			
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UAV>(UAV.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UAV> getStructuresFilterList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry = "select distinct n.station_name +' to '+n1.station_name as to_station from uav_video_data_structure u inner join stationnames n on n.id=u.from_station inner join stationnames n1 on n1.id=u.to_station " + 
					"inner join work w on w.work_id=u.work " ;
							
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and u.work = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and u.survey_date = ?";
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
			
			objsList = jdbcTemplate.query( qry,pValues,new BeanPropertyRowMapper<UAV>(UAV.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UAV> getStructureSurvey_Date(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="select u1.video_file_name,time_from,time_to,difftime,date as srt_date,u1.latitude,u1.longitude as longtitude,altitude,u1.work as work_id,u1.created_date,u1.survey_date,framecnt,cnt_number from uav_video_data_structure u inner join uav_srt_file_data_structure u1 on u1.video_file_name=u.video_file_name and u.work=u1.work and u.survey_date=u1.survey_date inner join annotation_file_data_structure a on a.survey_date=u.survey_date and a.work=u.work and a.video_file_name=u.video_file_name where 0=0 ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and u.work = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				qry = qry + " and u.survey_date = ?";
				arrSize++;
			}			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSurvey_date())) {
				pValues[i++] = obj.getSurvey_date();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			
		}
		return objsList;
	}

	@Override
	public UAV getUav(UAV obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		UAV uav = null;
		try {
			connection = dataSource.getConnection();
			String qry ="select id,project as project_id_fk,work as work_id,FORMAT(survey_date,'dd-MM-yyyy') as survey_date,video_file_name,upload_date,created_date,from_station,to_station from uav_video_data_structure where id=? ";

			stmt = connection.prepareStatement(qry);
			stmt.setString(1, obj.getId());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				uav = new UAV();
				uav.setId(resultSet.getString("id"));
				uav.setProject_id_fk(resultSet.getString("project_id_fk"));
				uav.setWork_id_fk(resultSet.getString("work_id"));
				uav.setSurvey_date(resultSet.getString("survey_date"));
				uav.setVideo_file_name(resultSet.getString("video_file_name"));
				uav.setFrom_station(resultSet.getString("from_station"));
				uav.setTo_station(resultSet.getString("to_station"));				

			}
				
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return uav;
		
	}

	@Override
	public int uploadMP4Data(UAV obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;	
		int count=0;
		try {
			con = dataSource.getConnection();
			
			String[] Survey_dateStr=obj.getSurvey_date().split("-");
			
			if(Survey_dateStr[0].length()==2)
			{
				obj.setSurvey_date(Survey_dateStr[2]+"-"+Survey_dateStr[1]+"-"+Survey_dateStr[0]);
			}			
			
		
			//if(getUavMP4DataCheck(obj.getProject_id_fk(),obj.getWork_id_fk(),obj.getSurvey_date())==0)
			//{
			
			if(StringUtils.isEmpty(obj.getId())) 
			{			
		        String insertQuery = "Insert into uav_video_data_structure (project,work,survey_date,video_file_name,upload_date,created_date,from_station,to_station) values (?,?,?,?,getdate(),CURRENT_TIMESTAMP,?,?)";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		        
				stmt.setString(1,obj.getProject_id_fk());
				stmt.setString(2,obj.getWork_id_fk());
				stmt.setString(3,obj.getSurvey_date());
				stmt.setString(4,obj.getFilename());
				stmt.setString(5,obj.getFrom_station());
				stmt.setString(6,obj.getTo_station());
				
				int c = stmt.executeUpdate();
				if (c > 0) {
					count=1;	
				}
			}
			else
			{
				String insertQuery = "update uav_video_data_structure set project=?,work=?,survey_date=?,video_file_name=?,upload_date=getdate(),from_station=?,to_station=? where id=?";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		        
				stmt.setString(1,obj.getProject_id_fk());
				stmt.setString(2,obj.getWork_id_fk());
				stmt.setString(3,obj.getSurvey_date());
				stmt.setString(4,obj.getFilename());
				stmt.setString(5,obj.getFrom_station());
				stmt.setString(6,obj.getTo_station());
				stmt.setString(7,obj.getId());
				
				int c = stmt.executeUpdate();
				if (c > 0) {
					count=1;	
				}					
			}
				
								
			//}
			/*else
			{
				
				String qry = "DELETE FROM uav_video_data_structure WHERE project=? and work=? and survey_date=? ";
				PreparedStatement deleteExistingIDStmt = con.prepareStatement(qry);
			
				deleteExistingIDStmt.setString(1, obj.getProject_id_fk());
				deleteExistingIDStmt.setString(2, obj.getWork_id_fk());
				deleteExistingIDStmt.setString(3, obj.getSurvey_date());
				
				int c = deleteExistingIDStmt.executeUpdate(); 
				

		        String insertQuery = "Insert into uav_video_data_structure (project,work,survey_date,video_file_name,upload_date,created_date,from_station,to_station) values (?,?,?,?,getdate(),CURRENT_TIMESTAMP,?,?)";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		        
				stmt.setString(1,obj.getProject_id_fk());
				stmt.setString(2,obj.getWork_id_fk());
				stmt.setString(3,obj.getSurvey_date());
				stmt.setString(4,obj.getFilename());
				stmt.setString(5,obj.getFrom_station());
				stmt.setString(6,obj.getTo_station());				
				
				int c1 = stmt.executeUpdate();
				if (c1 > 0) {
					count=1;	
				}

			}*/
			
			

		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}
		return count;
	}
	
	private int getUavMP4DataCheck(String project_id_fk,String work_id,String survey_date) throws Exception{
		int cnt=0;
		try {
			String qry = "select count(*) from uav_video_data_structure where project = ? and work=? and survey_date=?";
			cnt = (int) jdbcTemplate.queryForObject(qry, new Object[] { project_id_fk,work_id,survey_date }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return cnt;
	}
	
	private int getUavSRTDataCheck(String work_id,String survey_date) throws Exception{
		int cnt=0;
		try {
			String qry = "select count(*) from uav_srt_file_data_structure where work=? and survey_date=?";
			cnt = (int) jdbcTemplate.queryForObject(qry, new Object[] { work_id,survey_date }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return cnt;
	}	
	
	private String getUavMP4VideoFile(String work_id,String survey_date) throws Exception{
		String video_file_name="";
		try {
			String qry = "select distinct video_file_name from uav_video_data_structure where  work=? and survey_date=?";
			video_file_name = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id,survey_date }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return video_file_name;
	}	
	
	private int getUavMP4VideoFileCount(String work_id,String survey_date) throws Exception{
		int cnt=0;
		try {
			String qry = "select count(*) from uav_video_data_structure where work=? and survey_date=?";
			cnt = (int) jdbcTemplate.queryForObject(qry, new Object[] { work_id,survey_date }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return cnt;
	}
	
	
	private int getAnnotationVideoFileCount(String work_id,String survey_date) throws Exception{
		int cnt=0;
		try {
			String qry = "select count(*) from annotation_file_data_structure where work=? and survey_date=?";
			cnt = (int) jdbcTemplate.queryForObject(qry, new Object[] { work_id,survey_date }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return cnt;
	}		
	

	@Override
	public int uploadSRTData(UAV obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;	
		int count=0;
		try {
			con = dataSource.getConnection();
			MultipartFile multipartFile = obj.getSrtFileUpload();
			String saveDirectory = CommonConstants2.DRONE_SURVEY_SAVING_PATH ;
			String fileName="";
	
			if (null != multipartFile && multipartFile.getSize() > 0){
				fileName = multipartFile.getOriginalFilename();
			
				FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);	
			}
			
			
			String[] Survey_dateStr=obj.getSurvey_date().split("-");
			
			if(Survey_dateStr[0].length()==2)
			{
				obj.setSurvey_date(Survey_dateStr[2]+"-"+Survey_dateStr[1]+"-"+Survey_dateStr[0]);
			}

			if(getUavSRTDataCheck(obj.getWork_id_fk(),obj.getSurvey_date())==0)
			{			
			
			String line = null;
		    File file = new File( CommonConstants2.DRONE_SURVEY_SAVING_PATH+"/"+fileName);

		    FileReader fr = null;

		        fr = new FileReader( file );
		   
		    BufferedReader br = new BufferedReader( fr );

		    String 	s_no,	time_from,	time_to,	difftime,	date,	latitude,	longitude,	altitude,		framecnt,	cnt_number="";
		    int w=0;
		    int p=0;
		    ArrayList<String> ar = new ArrayList<String>();

		    while( (line = br.readLine()) != null )
		    {
		    	if(!StringUtils.isEmpty(line))
		    	{
		    	if(w==0)
		    	{
		    		s_no=line;
		    		ar.add(s_no);
		    	}
		    	if(w==1)
		    	{
		    		String[] Str=line.split("-->");
		    		time_from=Str[0];
		    		time_to=Str[1];
		    		ar.add(time_from);
		    		ar.add(time_to);

		    	}
		    	if(w==2)
		    	{
		    		String[] Str=line.split(",");
		    		String[] STR1=Str[0].split(":");
		    		String[] STR2=Str[1].split(":");
		    		difftime=STR2[1];
		    		framecnt=STR1[1];
		    		ar.add(difftime);
		    		ar.add(framecnt);
		    		
		    	}
		    	if(w==3)
		    	{
		    		date=line;
		    		ar.add(date);
		    	}
		    	if(w==4)
		    	{
		    		
		    		int latiIndex3=line.indexOf("[ct :");
		    		int next3 = line.indexOf("]", latiIndex3+1);
		    		
		    		String GRT3=line.substring(latiIndex3+5, next3);
		    		ar.add(GRT3);
		    		
		    		
		    		int latiIndex=line.indexOf("[latitude :");
		    		int next = line.indexOf("]", latiIndex+1);
		    		
		    		String GRT=line.substring(latiIndex+11, next);
		    		ar.add(GRT);
		    		

		    		int latiIndex1=line.indexOf("[longtitude :");
		    		int next1 = line.indexOf("]", latiIndex1+1);
		    		
		    		String GRT1=line.substring(latiIndex1+13, next1);
		    		ar.add(GRT1);
		    		
		    		int latiIndex2=line.indexOf("[altitude:");
		    		int next2 = line.indexOf("]", latiIndex2+1);
		    		
		    		String GRT2=line.substring(latiIndex2+11, next2);
		    		ar.add(GRT2);
		    		
		    		
		    		
		    		/*String[] Str3=line.split(":");
		    		String First=Str3[0].replaceAll("latitude", "");
		    		String FirstStr=First.replaceAll("[\\[\\]]", "");
		    		String GRT=FirstStr.replaceAll("          ", "");
		    		
		    		ar.add(GRT);
		    		
		    		String SecondStr=Str3[1].replaceAll("[\\[\\]]", "");
		    		String SecondStr2=SecondStr.replaceAll("longtitude","");
		    		
		    		ar.add(SecondStr2);
		    		
		    		String ThirdStr=Str3[2].replaceAll("[\\[\\]]", "");
		    		String ThirdStr2=ThirdStr.replaceAll("altitude","");
		    		
		    		ar.add(ThirdStr2);
		    		
		    		String Fourth=Str3[3].replaceAll("]", "");
		    		altitude= Fourth;
		    		
		    		ar.add(altitude);
		    		*/

				        String insertQuery = "Insert into uav_srt_file_data_structure (video_file_name,s_no,time_from,time_to,difftime,date,latitude,longitude,altitude,work,created_date,survey_date,framecnt,cnt_number) values (?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?)";
				        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
				        
				        if(getUavMP4VideoFileCount(obj.getWork_id_fk(),obj.getSurvey_date())>0)
				        {
				        	stmt.setString(1, getUavMP4VideoFile(obj.getWork_id_fk(),obj.getSurvey_date()));
				        }
				        else
				        {
				        	stmt.setString(1, fileName);
				        }
						stmt.setString(2,ar.get(0));
						stmt.setString(3,ar.get(1));
						stmt.setString(4,ar.get(2));
						stmt.setString(5,ar.get(3));
						
						stmt.setString(6,ar.get(5));
						stmt.setString(7,ar.get(7));
						stmt.setString(8,ar.get(8));
						stmt.setString(9,ar.get(9));
						
						stmt.setString(10,obj.getWork_id_fk());
						stmt.setString(11,obj.getSurvey_date());
						stmt.setString(12,ar.get(4));
						stmt.setString(13,ar.get(6));
						
						int c = stmt.executeUpdate();
						if (c > 0) {
							count=1;	
						}
					

					ar.clear();
					
		    	}
			    	if(w==4)
			    	{
			    		w=0;
			    	}
			    	else
			    	{
			    		w++;
			    	}
		    	
		    	}
		    }
			}
			else
			{
				/*String qry = "DELETE FROM uav_srt_file_data_structure WHERE work=? and survey_date=? ";
				PreparedStatement deleteExistingIDStmt = con.prepareStatement(qry);
			
				deleteExistingIDStmt.setString(1, obj.getWork_id_fk());
				deleteExistingIDStmt.setString(2, obj.getSurvey_date());
				
				int c = deleteExistingIDStmt.executeUpdate(); */	
				
				String line = null;
			    File file = new File( CommonConstants2.DRONE_SURVEY_SAVING_PATH+"/"+fileName);

			    FileReader fr = null;

			        fr = new FileReader( file );
			   
			    BufferedReader br = new BufferedReader( fr );

			    String 	s_no,	time_from,	time_to,	difftime,	date,	latitude,	longitude,	altitude,		framecnt,	cnt_number="";
			    int w=0;
			    int p=0;
			    ArrayList<String> ar = new ArrayList<String>();

			    while( (line = br.readLine()) != null )
			    {
			    	if(!StringUtils.isEmpty(line))
			    	{
			    	if(w==0)
			    	{
			    		s_no=line;
			    		ar.add(s_no);
			    	}
			    	if(w==1)
			    	{
			    		String[] Str=line.split("-->");
			    		time_from=Str[0];
			    		time_to=Str[1];
			    		ar.add(time_from);
			    		ar.add(time_to);

			    	}
			    	if(w==2)
			    	{
			    		String[] Str=line.split(",");
			    		String[] STR1=Str[0].split(":");
			    		String[] STR2=Str[1].split(":");
			    		difftime=STR2[1];
			    		framecnt=STR1[1];
			    		ar.add(difftime);
			    		ar.add(framecnt);
			    		
			    	}
			    	if(w==3)
			    	{
			    		date=line;
			    		ar.add(date);
			    	}
			    	if(w==4)
			    	{
			    		
			    		int latiIndex3=line.indexOf("[ct :");
			    		int next3 = line.indexOf("]", latiIndex3+1);
			    		
			    		String GRT3=line.substring(latiIndex3+5, next3);
			    		ar.add(GRT3);
			    		
			    		
			    		int latiIndex=line.indexOf("[latitude :");
			    		int next = line.indexOf("]", latiIndex+1);
			    		
			    		String GRT=line.substring(latiIndex+11, next);
			    		ar.add(GRT);
			    		

			    		int latiIndex1=line.indexOf("[longtitude :");
			    		int next1 = line.indexOf("]", latiIndex1+1);
			    		
			    		String GRT1=line.substring(latiIndex1+13, next1);
			    		ar.add(GRT1);
			    		
			    		int latiIndex2=line.indexOf("[altitude:");
			    		int next2 = line.indexOf("]", latiIndex2+1);
			    		
			    		String GRT2=line.substring(latiIndex2+11, next2);
			    		ar.add(GRT2);
			    		
			    		
			    		/*String[] Str3=line.split(":");
			    		String First=Str3[0].replaceAll("latitude", "");
			    		String FirstStr=First.replaceAll("[\\[\\]]", "");
			    		String GRT=FirstStr.replaceAll("          ", "");
			    		
			    		ar.add(GRT);
			    		
			    		String SecondStr=Str3[1].replaceAll("[\\[\\]]", "");
			    		String SecondStr2=SecondStr.replaceAll("longtitude","");
			    		
			    		ar.add(SecondStr2);
			    		
			    		String ThirdStr=Str3[2].replaceAll("[\\[\\]]", "");
			    		String ThirdStr2=ThirdStr.replaceAll("altitude","");
			    		
			    		ar.add(ThirdStr2);
			    		
			    		String Fourth=Str3[3].replaceAll("]", "");
			    		altitude= Fourth;
			    		
			    		ar.add(altitude);*/

					        String insertQuery = "Insert into uav_srt_file_data_structure (video_file_name,s_no,time_from,time_to,difftime,date,latitude,longitude,altitude,work,created_date,survey_date,framecnt,cnt_number) values (?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?)";
					        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
					        
					        if(getUavMP4VideoFileCount(obj.getWork_id_fk(),obj.getSurvey_date())>0)
					        {
					        	stmt.setString(1, getUavMP4VideoFile(obj.getWork_id_fk(),obj.getSurvey_date()));
					        }
					        else
					        {
					        	stmt.setString(1, fileName);
					        }
							stmt.setString(2,ar.get(0));
							stmt.setString(3,ar.get(1));
							stmt.setString(4,ar.get(2));
							stmt.setString(5,ar.get(3));
							
							stmt.setString(6,ar.get(5));
							stmt.setString(7,ar.get(7));
							stmt.setString(8,ar.get(8));
							stmt.setString(9,ar.get(9));
							
							stmt.setString(10,obj.getWork_id_fk());
							stmt.setString(11,obj.getSurvey_date());
							stmt.setString(12,ar.get(4));
							stmt.setString(13,ar.get(6));
							
							int c1 = stmt.executeUpdate();
							if (c1 > 0) {
								count=1;	
							}
						

						ar.clear();
						
			    	}
				    	if(w==4)
				    	{
				    		w=0;
				    	}
				    	else
				    	{
				    		w++;
				    	}
			    	
			    	}
			    }				
				
			}

		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}
		return count;
	}

	@Override
	public int uploadAnnotationData(UAV obj) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;	
		int count=0;
        int batchSize = 20;		
        
		try {
			con = dataSource.getConnection();
			MultipartFile multipartFile = obj.getAnnotationFileUpload();
			String saveDirectory = CommonConstants2.DRONE_SURVEY_SAVING_PATH ;
			String fileName="";
	
			if (null != multipartFile && multipartFile.getSize() > 0){
				fileName = multipartFile.getOriginalFilename();
			
				FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);	
			}

			String[] Survey_dateStr=obj.getSurvey_date().split("-");
			
			if(Survey_dateStr[0].length()==2)
			{
				obj.setSurvey_date(Survey_dateStr[2]+"-"+Survey_dateStr[1]+"-"+Survey_dateStr[0]);
			}
				
			
			
			if(getAnnotationVideoFileCount(obj.getWork_id_fk(),obj.getSurvey_date())==0)
			{
				
				
		        String insertQuery = "Insert into annotation_file_data_structure (video_file_name,latitude,longitude,structure,work,created_date,survey_date) "
		        		+ "values (?,?,?,?,?,CURRENT_TIMESTAMP,?)";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		        
		        
		        BufferedReader lineReader = new BufferedReader(new FileReader(saveDirectory+"/"+fileName));
		        String lineText = null;
		        
		        
		        lineReader.readLine(); // skip header line
		        
		        while ((lineText = lineReader.readLine()) != null) 
		        {
			        String[] data = lineText.split(",");
			        
			        if(getUavMP4VideoFileCount(obj.getWork_id_fk(),obj.getSurvey_date())>0)
			        {
			        	stmt.setString(1, getUavMP4VideoFile(obj.getWork_id_fk(),obj.getSurvey_date()));
			        }
			        else
			        {
			        	stmt.setString(1, fileName);
			        }
			        stmt.setString(2, data[0]);
			        stmt.setString(3, data[1]);
			        stmt.setString(4, data[2]);
			        stmt.setString(5, obj.getWork_id_fk());
			        stmt.setString(6, obj.getSurvey_date());
			        
			        stmt.addBatch();
		        
			        if (count % batchSize == 0) 
			        {
			        	stmt.executeBatch();
			        }
		        }
		        
		        lineReader.close();
		        
		        stmt.executeBatch();
		        
		        con.commit();
		        con.close();		        
				
				int c = stmt.executeUpdate();
				if (c > 0) {
					count=1;	
				}
			}
			else
			{
				
				
				/*String qry = "DELETE FROM annotation_file_data_structure WHERE work=? and survey_date=? ";
				PreparedStatement deleteExistingIDStmt = con.prepareStatement(qry);
			
				deleteExistingIDStmt.setString(1, obj.getWork_id_fk());
				deleteExistingIDStmt.setString(2, obj.getSurvey_date());
				
				int c = deleteExistingIDStmt.executeUpdate();*/		
				
				
		        String insertQuery = "Insert into annotation_file_data_structure (video_file_name,latitude,longitude,structure,work,created_date,survey_date) "
		        		+ "values (?,?,?,?,?,CURRENT_TIMESTAMP,?)";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		        
		        
		        BufferedReader lineReader = new BufferedReader(new FileReader(saveDirectory+"/"+fileName));
		        String lineText = null;
		        
		        
		        lineReader.readLine(); // skip header line
		        
		        while ((lineText = lineReader.readLine()) != null) 
		        {
			        String[] data = lineText.split(",");
			        
			        if(getUavMP4VideoFileCount(obj.getWork_id_fk(),obj.getSurvey_date())>0)
			        {
			        	stmt.setString(1, getUavMP4VideoFile(obj.getWork_id_fk(),obj.getSurvey_date()));
			        }
			        else
			        {
			        	stmt.setString(1, fileName);
			        }
			        stmt.setString(2, data[0]);
			        stmt.setString(3, data[1]);
			        stmt.setString(4, data[2]);
			        stmt.setString(5, obj.getWork_id_fk());
			        stmt.setString(6, obj.getSurvey_date());
			        
			        stmt.addBatch();
		        
			        if (count % batchSize == 0) 
			        {
			        	stmt.executeBatch();
			        }
		        }
		        
		        lineReader.close();
		        
		        stmt.executeBatch();
		        
		        con.commit();
		        con.close();		        
				
				int c1 = stmt.executeUpdate();
				if (c1 > 0) {
					count=1;	
				}
				
				
		        /*String insertQuery = "update annotation_file_data_structure set video_file_name=?,latitude=?,longitude=?,structure=? where work=? and survey_date=?";
		        stmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
				
		        BufferedReader lineReader = new BufferedReader(new FileReader(saveDirectory+"/"+fileName));
		        String lineText = null;
		        
		        
		        lineReader.readLine(); // skip header line
		        
		        while ((lineText = lineReader.readLine()) != null) 
		        {
			        String[] data = lineText.split(",");
			        
			        if(getUavMP4VideoFileCount(obj.getWork_id(),obj.getSurvey_date())>0)
			        {
			        	stmt.setString(1, getUavMP4VideoFile(obj.getWork_id(),obj.getSurvey_date()));
			        }
			        else
			        {
			        	stmt.setString(1, fileName);
			        }
			        stmt.setString(2,data[0]);
					stmt.setString(3,data[1]);
					stmt.setString(4,data[2]);
					stmt.setString(5,obj.getWork_id_fk());
					stmt.setString(6,obj.getSurvey_date());			        
			        
			        stmt.addBatch();
		        
			        if (count % batchSize == 0) 
			        {
			        	stmt.executeBatch();
			        }
		        }
		        
		        lineReader.close();
		        
		        stmt.executeBatch();
		        
		        con.commit();
		        con.close();		        
				
				int c = stmt.executeUpdate();
				if (c > 0) {
					count=1;	
				}*/			
			}

		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}
		return count;
	}

	@Override
	public List<UAV> getStationList(UAV obj) throws Exception {
		List<UAV> objsList = null;
		try {
			String qry ="select id as station_id,station_name from stationnames ";
	
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UAV>(UAV.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			
		}
		return objsList;
	}

	@Override
	public boolean checkDataAvailable(String id,String work_id_fk, String survey_date, String from_station, String to_station)
			throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean process = false;
		try{  
			con = dataSource.getConnection();
			String updateQry ="";
			
			if(StringUtils.isEmpty(id)) 
			{
				updateQry = "select survey_date from uav_video_data_structure WHERE work = ? and survey_date=? and from_station=? and to_station=?";
				stmt = con.prepareStatement(updateQry);
				stmt.setString(1, work_id_fk);
				stmt.setString(2, survey_date);	
				stmt.setString(3, from_station);	
				stmt.setString(4, to_station);	
			}
			else
			{
				updateQry = "select survey_date from uav_video_data_structure WHERE work = ? and survey_date=? and from_station=? and to_station=? and id not in(?)";
				stmt = con.prepareStatement(updateQry);
				stmt.setString(1, work_id_fk);
				stmt.setString(2, survey_date);	
				stmt.setString(3, from_station);	
				stmt.setString(4, to_station);	
				stmt.setString(5, id);
			}
			rs = stmt.executeQuery(); 
			if(rs.next()) {		
				process=true;			
			}
		}catch(Exception e){ 
			throw new SQLException(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return process;
	}

	
}
