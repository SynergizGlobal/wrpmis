package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Idao.WorkContractModuleStatusDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

@Repository
public class WorkContractModuleStatusDaoImpl implements WorkContractModuleStatusDao {
	

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<WorkContractModuleStatus> getContractsList() throws Exception {
		List<WorkContractModuleStatus> objList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name from contract";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}

	@Override
	public List<WorkContractModuleStatus> workStatusList(WorkContractModuleStatus obj) throws Exception {
		List<WorkContractModuleStatus> objsList = null;
		try {
			String qry ="select work_status_id, ws.work_id_fk,w.project_id_fk,p.project_name,w.work_name,c.contract_name, ws.contract_id_fk, module_name_fk, DATE_FORMAT(month,'%d-%m-%Y') AS month, status_as_on_month from work_status ws " + 
					"left join work w on ws.work_id_fk = w.work_id  " + 
					"left join project p on  w.project_id_fk = p.project_id " + 
					"left join contract c on ws.contract_id_fk = c.contract_id where work_status_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and ws.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and ws.contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry + "  GROUP BY w.project_id_fk,ws.work_id_fk,ws.contract_id_fk ";
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
			
		 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public WorkContractModuleStatus getWorkStatus(WorkContractModuleStatus wObj) throws Exception {
		WorkContractModuleStatus obj = null;
		try {
			String qry ="select work_status_id, ws.work_id_fk,w.project_id_fk,p.project_name,w.work_name,c.contract_name,DATE_FORMAT(month,'%d-%m-%Y') AS month, ws.contract_id_fk from work_status ws " + 
					"left join work w on ws.work_id_fk = w.work_id  " + 
					"left join project p on  w.project_id_fk = p.project_id "  + 
					"left join contract c on ws.contract_id_fk = c.contract_id where work_status_id is not null  and ws.work_id_fk =? and ws.contract_id_fk =? LIMIT 1";
			
			
			obj = (WorkContractModuleStatus)jdbcTemplate.queryForObject(qry, new Object[] {wObj.getWork_id_fk(),wObj.getContract_id_fk()}, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));	
			
			if(!StringUtils.isEmpty(obj)) {
				List<WorkContractModuleStatus> objsList = null;
				String qryDetails = "select work_status_id,module_name_fk, status_as_on_month from work_status  " + 
						"where work_status_id is not null  and work_id_fk =? and contract_id_fk =?";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {wObj.getWork_id_fk(),wObj.getContract_id_fk()}, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));	
				obj.setWorkContractStatus(objsList);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return obj;
	}

	@Override
	public boolean addWorkstatus(WorkContractModuleStatus obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO work_status"
					+ "(work_id_fk, contract_id_fk, module_name_fk, month, status_as_on_month)"
					+ "VALUES"
					+ "(?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getStatus_as_on_months()) && obj.getStatus_as_on_months().length > 0) {
				obj.setStatus_as_on_months(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatus_as_on_months()));
				if(arraySize < obj.getStatus_as_on_months().length) {
					arraySize = obj.getStatus_as_on_months().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getModule_name_fks()) && obj.getModule_name_fks().length > 0) {
				obj.setModule_name_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getModule_name_fks()));
				if(arraySize < obj.getModule_name_fks().length) {
					arraySize = obj.getModule_name_fks().length;
				}
			}
			
			for (int i = 0; i < arraySize; i++) {
			
			    int p = 1;
			    insertStmt.setString(p++,(obj.getWork_id_fk()));
			    insertStmt.setString(p++,(obj.getContract_id_fk()));
			    insertStmt.setString(p++,(obj.getModule_name_fks().length > 0)?obj.getModule_name_fks()[i]:null);
			    insertStmt.setString(p++,DateParser.parse(obj.getMonth()));
			    insertStmt.setString(p++,(obj.getStatus_as_on_months().length > 0)?obj.getStatus_as_on_months()[i]:null);
			    insertStmt.addBatch();
			  
			}
			int[] insertCount = insertStmt.executeBatch();		
			
			if(insertCount.length > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
			return flag;
		}

	@Override
	public boolean updateWorkStatus(WorkContractModuleStatus obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String updateQry = "UPDATE work_status set "
					+ "module_name_fk=? ,"
					+ "month= ?, status_as_on_month= ? "
					+ "where work_status_id= ?";
			
			updateStmt = con.prepareStatement(updateQry);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getStatus_as_on_months()) && obj.getStatus_as_on_months().length > 0) {
				obj.setStatus_as_on_months(CommonMethods.replaceEmptyByNullInSringArray(obj.getStatus_as_on_months()));
				if(arraySize < obj.getStatus_as_on_months().length) {
					arraySize = obj.getStatus_as_on_months().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getModule_name_fks()) && obj.getModule_name_fks().length > 0) {
				obj.setModule_name_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getModule_name_fks()));
				if(arraySize < obj.getModule_name_fks().length) {
					arraySize = obj.getModule_name_fks().length;
				}
			}
			
			for (int i = 0; i < arraySize; i++) {
			
			    int p = 1;
			    updateStmt.setString(p++,(obj.getModule_name_fks().length > 0)?obj.getModule_name_fks()[i]:null);
			    updateStmt.setString(p++,DateParser.parse(obj.getMonth()));
			    updateStmt.setString(p++,(obj.getStatus_as_on_months().length > 0)?obj.getStatus_as_on_months()[i]:null);
			    updateStmt.setString(p++,(obj.getWork_status_ids()[i]));
			    updateStmt.addBatch();
			  
			}
			int[] updateCount = updateStmt.executeBatch();
			if(updateCount.length > 0) {
				flag = true;
			}
	}catch(Exception e){ 
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	return flag;
}
	
}
