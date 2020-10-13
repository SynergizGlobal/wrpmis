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
				qry = qry + " and project_id = ?";
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
					"left join contract c on ws.contract_id_fk = c.contract_id where work_status_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(wObj) && !StringUtils.isEmpty(wObj.getWork_status_id())) {
				qry = qry + " and work_status_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(wObj) && !StringUtils.isEmpty(wObj.getWork_status_id())) {
				pValues[i++] = wObj.getWork_status_id();
			}
			obj = (WorkContractModuleStatus)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));	
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_id())) {
				
				List<WorkContractModuleStatus> objsList = null;
			String qryDetails = "select work_status_id,module_name_fk, status_as_on_month from work_status  " + 
					"where work_status_id is not null "+
					"and contract_id_fk = ? ";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getContract_id_fk()}, new BeanPropertyRowMapper<WorkContractModuleStatus>(WorkContractModuleStatus.class));	
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
			String insertQry = "INSERT INTO safety_equipment"
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
			
			for (int i = 0; i < arraySize; i++) {
				if( !StringUtils.isEmpty(obj.getStatus_as_on_months()) && obj.getStatus_as_on_months().length > 0) {
			    int p = 1;
			    insertStmt.setString(p++,(obj.getWork_id_fk()));
			    insertStmt.setString(p++,(obj.getContract_id_fk()));
			    insertStmt.setString(p++,(obj.getModule_name_fk()));
			    insertStmt.setString(p++,DateParser.parse(obj.getMonth()));
			    insertStmt.setString(p++,(obj.getStatus_as_on_months().length > 0)?obj.getStatus_as_on_months()[i]:null);
			}}
			
		}catch(Exception e){ 
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
			return flag;
		}
	
}
