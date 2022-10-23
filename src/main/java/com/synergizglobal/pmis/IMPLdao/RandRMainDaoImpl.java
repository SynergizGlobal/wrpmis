package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.RandRMainDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.RandRMain;
@Repository
public class RandRMainDaoImpl implements RandRMainDao{
	public static Logger logger = Logger.getLogger(UtilityShiftingDaoImpl.class);
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<RandRMain> getWorksFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.work_id as work_id_fk,w.work_code,w.work_name,w.work_short_name from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "
					+ "left join rr_executives re on r.work_id = re.work_id_fk " +
					"where r.work_id is not null and r.work_id <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.work_id,w.work_code,w.work_name,w.work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getStatusFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.boundary_wall_status from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"left join rr_executives re on r.work_id = re.work_id_fk  "+
					"where r.boundary_wall_status is not null and r.boundary_wall_status <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.boundary_wall_status ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getLocationsFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.location_name from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"left join rr_executives re on r.work_id = re.work_id_fk  "+
					"where r.location_name is not null and r.location_name <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.location_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getTypeofUseFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.type_of_use from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"left join rr_executives re on r.work_id = re.work_id_fk  "+
					"where r.type_of_use is not null and r.type_of_use <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.type_of_use ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getStructuresFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.structure_id from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"left join rr_executives re on r.work_id = re.work_id_fk  "+
					"where r.structure_id is not null and r.structure_id <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.structure_id ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getPhasesFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.phase from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"left join rr_executives re on r.work_id = re.work_id_fk  "+
					"where r.phase is not null and r.phase <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY r.phase ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(RandRMain obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(distinct rr_id) as total_records from rr r "
					+ "LEFT JOIN work w on r.work_id = w.work_id "
					+ "left join rr_executives re on r.work_id = re.work_id_fk  "
					+ "where rr_id is not null  ";
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (r.rr_id like ? or r.occupier_name_during_verification like ? or boundary_wall_status like ?"
						+ " or type_of_use like ? or structure_id like ? or location_name like ? or sub_location_name like ? or phase like ?)";
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
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

	@Override
	public List<RandRMain> getRRList(RandRMain obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rr_id, r.work_id, identification_no, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor, carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, estimate_approval_date, letter_to_mmrda, estimates_by_mmrda, payment_to_mmrda, alternate_housing_allotment,"
					+ " relocation, encroachment_removal, boundary_wall_status, boundary_wall_doc, handed_over_to_execution, occupier_name_during_verification,modified_by,FORMAT(modified_date,'dd-MM-yyyy') as modified_date,chainage,latitude,longitude from rr r "
					+ "LEFT JOIN work w on r.work_id = w.work_id "
					+ "left join rr_executives re on r.work_id = re.work_id_fk  "
					+ "WHERE rr_id is not null ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (r.rr_id like ? or r.occupier_name_during_verification like ? or boundary_wall_status like ?"
						+ " or type_of_use like ? or structure_id like ? or location_name like ? or sub_location_name like ? or phase like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY rr_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getProjectsListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select distinct project_id as project_id_fk,project_name "
					+ "from project p "
					+ "LEFT JOIN work w on w.project_id_fk = p.project_id "
					+ "LEFT JOIN rr_executives r ON r.work_id_fk = w.work_id "
					+ "where project_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and r.executive_user_id_fk = ? ";
				arrSize++;
			}			

			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getWorkListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select distinct work_id as work_id_fk,work_name,work_code,work_short_name,project_id_fk,project_name "
					+ "from work w "
					+ "LEFT JOIN rr_executives r ON r.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON p.project_id = w.project_id_fk "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and r.executive_user_id_fk = ? ";
				arrSize++;
			}				
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getDocTypeListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select document_type from rr_doc_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getPhaseListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select phase from rr_phase ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getStructureListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select structure as structure_id from structure where structure_type_fk = 'R&R' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));		
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getLocationListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_location as location_name from rr_location l "
					+ "left join rr_sub_location s on rr_location = rr_location_fk where rr_location is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_location_name())) {
				qry = qry + "and s.rr_sub_location = ?";
				arrSize++;
			}
			qry = qry + " group by rr_location ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_location_name())) {
				pValues[i++] = obj.getSub_location_name();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getSubLocationListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_sub_location as sub_location_name from rr_sub_location where rr_sub_location is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + "and rr_location_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getTypeofUseListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_type_of_use as type_of_use from rr_type_of_use ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getVerificationByListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select committee_name as verification_by from rr_appointment_of_committee ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getUnitsListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select id, unit, value  from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getStatusListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_boundarywall_status as boundary_wall_status from rr_boundarywall_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getOccupancyStatusListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_occupancy_status as occupancy_status from rr_occupancy_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getGenderListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select gender  from gender ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getTenureStatusListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_tenure_status as tenure_status from rr_tenure_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getCasteListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select caste  from caste ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getMotherTongueListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select mother_tongue  from mother_tongue ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getTypeofFamilyListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_type_of_family as type_of_family  from rr_type_of_family ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getMaritualStatusListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select marital_status as maritua_status   from marital_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public RandRMain getRandRMainForm(RandRMain rr) throws Exception {
		RandRMain obj = null;
		try {
			String qry = "select distinct rr_id, r.work_id,(select executive_user_id_fk from rr_executives re where r.work_id = re.work_id_fk and executive_user_id_fk = ?) as executive_user_id_fk, identification_no,w.work_short_name,w.work_name,w.project_id_fk,p.project_name, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, "
					+ "type_of_structure_wall, type_of_structure_floor, r.carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, FORMAT(physical_verification ,'dd-MM-yyyy') AS physical_verification, verification_by,FORMAT(approval_by_committee ,'dd-MM-yyyy') AS  approval_by_committee,r.remarks,estimation_amount_units,estimated_by_mmrda_amount_units,"
					+ "FORMAT(rr_approval_status_by_mrvc ,'dd-MM-yyyy') AS  rr_approval_status_by_mrvc, estimation_amount,FORMAT(estimate_approval_date ,'dd-MM-yyyy') AS estimate_approval_date,FORMAT(letter_to_mmrda ,'dd-MM-yyyy') AS letter_to_mmrda, estimates_by_mmrda, FORMAT(payment_to_mmrda ,'dd-MM-yyyy') AS payment_to_mmrda, FORMAT(alternate_housing_allotment ,'dd-MM-yyyy') AS alternate_housing_allotment,FORMAT(relocation ,'dd-MM-yyyy') AS relocation,FORMAT(encroachment_removal ,'dd-MM-yyyy') AS encroachment_removal, boundary_wall_status, "
					+ "FORMAT(boundary_wall_doc ,'dd-MM-yyyy') AS boundary_wall_doc,FORMAT(handed_over_to_execution ,'dd-MM-yyyy') AS handed_over_to_execution, occupier_name_during_verification,"
					+ "rr1.rr_id_fk, rr1.name_of_activity, rr1.year_of_establishment, rr1.carpet_area as com_carpet_area, rr1.monthly_turnover_amount, rr1.monthly_turnover_amount_units, rr1.number_of_employees, rr1.remarks as com_remarks,"
					+ "rr2.id, rr2.rr_id_fk, rr2.occupancy_status, rr2.gender, rr2.family_income_amount_units,rr2.tenure_status, rr2.caste, rr2.mother_tongue, rr2.type_of_family, rr2.family_size, rr2.number_of_married_couple, rr2.family_income_amount, rr2.vulnerable_category, FORMAT(r.planned_date_of_completion ,'dd-MM-yyyy') as planned_date_of_completion,chainage,latitude,longitude"
					+ " from rr r " + 
					"left join work w on r.work_id = w.work_id  "
					+ "left join rr_executives re on r.work_id = re.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id  "
					+ "left join rr_commercial_details rr1 on r.rr_id = rr1.rr_id_fk "
					+ "left join rr_residential_details rr2 on r.rr_id = rr2.rr_id_fk "
					+ " where rr_id is not null" ; 
			int arrSize = 1;
			if(!StringUtils.isEmpty(rr) && !StringUtils.isEmpty(rr.getRr_id())) {
				qry = qry + " and rr_id = ?";
				arrSize++;
			}
			//qry = qry + " group by rr_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = rr.getUser_id();
			if(!StringUtils.isEmpty(rr) && !StringUtils.isEmpty(rr.getRr_id())) {
				pValues[i++] = rr.getRr_id();
			}
			
			obj = (RandRMain)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRr_id())) {
				List<RandRMain> objsList = null;
				String qryDetails = "select id, rr_id_fk, residential_name, residential_relation_with_head, residential_age, residential_gender, residential_maritual_status, "
						+ "residential_education, residential_employment, residential_salary, residential_salary_units "
						+ "from rr_residential_family_details rrr " 
						+"left join rr r1 on rrr.rr_id_fk = r1.rr_id where rr_id_fk = ?  ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getRr_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
				obj.setResidentialList(objsList);
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRr_id())) {
				List<RandRMain> objsList = null;
				String qryDetails = "select id, rr_id_fk, employee_name, employee_age, employee_gender, employee_literacy, "
						+ "employee_travel_time, employee_salary, employee_salary_units,employee_attended, employee_nature_of_work  "
						+ "from rr_commercial_employee_details rrr1 " 
						+"left join rr r2 on rrr1.rr_id_fk = r2.rr_id where rr_id_fk = ?  ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {obj.getRr_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
				obj.setCommercialList(objsList);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
		return obj;
	}

	@Override
	public boolean addRR(RandRMain obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			List<RandRMain> objsList = null;
			obj.setWork_id_fk(obj.getWork_id());
			String rr_id = getAutoGeneratedRRId(obj);
			obj.setRr_id(rr_id);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO rr"
					+ "( rr_id, work_id, identification_no, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor,"
					+ "carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, "
					+ "approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, letter_to_mmrda, estimate_approval_date,estimates_by_mmrda,"
					+ "payment_to_mmrda,alternate_housing_allotment,relocation,encroachment_removal,boundary_wall_status,boundary_wall_doc,handed_over_to_execution,occupier_name_during_verification,remarks,estimated_by_mmrda_amount_units,estimation_amount_units,planned_date_of_completion,chainage,latitude,longitude)"
					+ "VALUES"
					+ "(:rr_id, :work_id, :identification_no, :map_sr_no, :location_name, :sub_location_name, :phase, :structure_id, :type_of_structure_roof, :type_of_structure_wall, :type_of_structure_floor, "
					+ ":carpet_area, :year_of_construction, :name_of_the_owner, :type_of_use, :document_type, :document_no, :physical_verification, :verification_by, "
					+ ":approval_by_committee, :rr_approval_status_by_mrvc, :estimation_amount, :letter_to_mmrda, :estimate_approval_date , :estimates_by_mmrda, :payment_to_mmrda, :alternate_housing_allotment, "
					+ ":relocation, :encroachment_removal, :boundary_wall_status, :boundary_wall_doc, :handed_over_to_execution, :occupier_name_during_verification, :remarks, :estimated_by_mmrda_amount_units, :estimation_amount_units,:planned_date_of_completion,:chainage,:latitude,:longitude)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				if(!StringUtils.isEmpty(obj.getType_of_use())) {
					if(obj.getType_of_use().equalsIgnoreCase("Residential")) {
						String govInsertQry = "INSERT INTO rr_residential_details"
								+ "( rr_id_fk, occupancy_status, gender, tenure_status, caste, mother_tongue, "
								+ "type_of_family, family_size, number_of_married_couple, family_income_amount, family_income_amount_units, vulnerable_category)"
								+ "VALUES"
								+ "(:rr_id, :occupancy_status, :gender, :tenure_status, :caste,:mother_tongue, "
								+ " :type_of_family, :family_size, :number_of_married_couple, :family_income_amount, :family_income_amount_units, :vulnerable_category)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(govInsertQry, paramSource);	
						 if(count > 0) {
							 con = dataSource.getConnection();
							 String detailsQry = "INSERT INTO rr_residential_family_details"
										+ "( rr_id_fk, residential_name, residential_relation_with_head, residential_age, residential_gender, residential_maritual_status, "
										+ "residential_education, residential_employment, residential_salary, residential_salary_units)"
										+ "VALUES"
										+ "(?,?,?,?,?,?,?,?,?,?)";
							 insertStmt = con.prepareStatement(detailsQry); 
							 int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getResidential_names()) && obj.getResidential_names().length > 0 ) {
									obj.setResidential_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_names()));
									if(arraySize < obj.getResidential_names().length) {
										arraySize = obj.getResidential_names().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_relation_with_heads()) && obj.getResidential_relation_with_heads().length > 0 ) {
									obj.setResidential_relation_with_heads(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_relation_with_heads()));
									if(arraySize < obj.getResidential_relation_with_heads().length) {
										arraySize = obj.getResidential_relation_with_heads().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_ages()) && obj.getResidential_ages().length > 0 ) {
									obj.setResidential_ages(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_ages()));
									if(arraySize < obj.getResidential_ages().length) {
										arraySize = obj.getResidential_ages().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_genders()) && obj.getResidential_genders().length > 0 ) {
									obj.setResidential_genders(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_genders()));
									if(arraySize < obj.getResidential_genders().length) {
										arraySize = obj.getResidential_genders().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_maritual_statuss()) && obj.getResidential_maritual_statuss().length > 0 ) {
									obj.setResidential_maritual_statuss(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_maritual_statuss()));
									if(arraySize < obj.getResidential_maritual_statuss().length) {
										arraySize = obj.getResidential_maritual_statuss().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_educations()) && obj.getResidential_educations().length > 0 ) {
									obj.setResidential_educations(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_educations()));
									if(arraySize < obj.getResidential_educations().length) {
										arraySize = obj.getResidential_educations().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_employments()) && obj.getResidential_employments().length > 0 ) {
									obj.setResidential_employments(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_employments()));
									if(arraySize < obj.getResidential_employments().length) {
										arraySize = obj.getResidential_employments().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_salarys()) && obj.getResidential_salarys().length > 0 ) {
									obj.setResidential_salarys(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_salarys()));
									if(arraySize < obj.getResidential_salarys().length) {
										arraySize = obj.getResidential_salarys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_salary_unitss()) && obj.getResidential_salary_unitss().length > 0 ) {
									obj.setResidential_salary_unitss(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_salary_unitss()));
									if(arraySize < obj.getResidential_salary_unitss().length) {
										arraySize = obj.getResidential_salary_unitss().length;
									}
								}
								for (int i = 0;  i < arraySize; i++) {
									 if( obj.getResidential_names().length > 0 && !StringUtils.isEmpty(obj.getResidential_names()[i])) {
										int p = 1;
										    insertStmt.setString(p++,(obj.getRr_id()));
										    insertStmt.setString(p++,(obj.getResidential_names().length > 0)?obj.getResidential_names()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_relation_with_heads().length > 0)?obj.getResidential_relation_with_heads()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_ages().length > 0)?obj.getResidential_ages()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_genders().length > 0)?obj.getResidential_genders()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_maritual_statuss().length > 0)?obj.getResidential_maritual_statuss()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_educations().length > 0)?obj.getResidential_educations()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_employments().length > 0)?obj.getResidential_employments()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_salarys().length > 0)?obj.getResidential_salarys()[i]:null);
										    insertStmt.setString(p++,(obj.getResidential_salary_unitss().length > 0)?obj.getResidential_salary_unitss()[i]:null);
										    insertStmt.addBatch();
									    }
									  int[] insertCount = insertStmt.executeBatch();
								}
								
								
						 }
						 
							
					}else {
						con = dataSource.getConnection();
						String forestInsertSubQry = "INSERT INTO rr_commercial_details "
						 		+ "( rr_id_fk, name_of_activity, year_of_establishment, carpet_area, monthly_turnover_amount,monthly_turnover_amount_units, number_of_employees, remarks)"
						 		+ "VALUES"
						 		+ "( :rr_id, :name_of_activity, :year_of_establishment, :com_carpet_area, :monthly_turnover_amount,:monthly_turnover_amount_units, :number_of_employees, :com_remarks)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);	
							if(count > 0) {
								String detailsQry = "INSERT INTO rr_commercial_employee_details"
										+ "( rr_id_fk, employee_name, employee_age, employee_gender, employee_literacy, employee_attended, "
										+ "employee_travel_time, employee_salary, employee_salary_units, employee_nature_of_work)"
										+ "VALUES"
										+ "(?,?,?,?,?,?,?,?,?,?)";
								 insertStmt = con.prepareStatement(detailsQry); 
								int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getEmployee_names()) && obj.getEmployee_names().length > 0 ) {
									obj.setEmployee_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_names()));
									if(arraySize < obj.getEmployee_names().length) {
										arraySize = obj.getEmployee_names().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_ages()) && obj.getEmployee_ages().length > 0 ) {
									obj.setEmployee_ages(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_ages()));
									if(arraySize < obj.getEmployee_ages().length) {
										arraySize = obj.getEmployee_ages().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_genders()) && obj.getEmployee_genders().length > 0 ) {
									obj.setEmployee_genders(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_genders()));
									if(arraySize < obj.getEmployee_genders().length) {
										arraySize = obj.getEmployee_genders().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_literacys()) && obj.getEmployee_literacys().length > 0 ) {
									obj.setEmployee_literacys(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_literacys()));
									if(arraySize < obj.getEmployee_literacys().length) {
										arraySize = obj.getEmployee_literacys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_attendeds()) && obj.getEmployee_attendeds().length > 0 ) {
									obj.setEmployee_attendeds(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_attendeds()));
									if(arraySize < obj.getEmployee_attendeds().length) {
										arraySize = obj.getEmployee_attendeds().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_travel_times()) && obj.getEmployee_travel_times().length > 0 ) {
									obj.setEmployee_travel_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_travel_times()));
									if(arraySize < obj.getEmployee_travel_times().length) {
										arraySize = obj.getEmployee_travel_times().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_salarys()) && obj.getEmployee_salarys().length > 0 ) {
									obj.setEmployee_salarys(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_salarys()));
									if(arraySize < obj.getEmployee_salarys().length) {
										arraySize = obj.getEmployee_salarys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_salary_unitss()) && obj.getEmployee_salary_unitss().length > 0 ) {
									obj.setEmployee_salary_unitss(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_salary_unitss()));
									if(arraySize < obj.getEmployee_salary_unitss().length) {
										arraySize = obj.getEmployee_salary_unitss().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_nature_of_works()) && obj.getEmployee_nature_of_works().length > 0 ) {
									obj.setEmployee_nature_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_nature_of_works()));
									if(arraySize < obj.getEmployee_nature_of_works().length) {
										arraySize = obj.getEmployee_nature_of_works().length;
									}
								}
								for (int i = 0;  i < arraySize; i++) {
									 if( obj.getEmployee_names().length > 0 && !StringUtils.isEmpty(obj.getEmployee_names()[i])) {
										int p = 1;
										    insertStmt.setString(p++,(obj.getRr_id()));
										    insertStmt.setString(p++,(obj.getEmployee_names().length > 0)?obj.getEmployee_names()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_ages().length > 0)?obj.getEmployee_ages()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_genders().length > 0)?obj.getEmployee_genders()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_literacys().length > 0)?obj.getEmployee_literacys()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_attendeds().length > 0)?obj.getEmployee_attendeds()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_travel_times().length > 0)?obj.getEmployee_travel_times()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_salarys().length > 0)?obj.getEmployee_salarys()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_salary_unitss().length > 0)?obj.getEmployee_salary_unitss()[i]:null);
										    insertStmt.setString(p++,(obj.getEmployee_nature_of_works().length > 0)?obj.getEmployee_nature_of_works()[i]:null);
										    insertStmt.addBatch();
									    }
									  int[] insertCount = insertStmt.executeBatch();
								}
							
							}
					}
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("R & R");
				formHistory.setForm_name("Add R & R");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New R & R "+obj.getRr_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
				String messageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
						+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";	
				String executives=getRRExecutives(obj.getWork_id());
				String executivesEmail=getRRExecutivesEmail(obj.getWork_id());
				if(!StringUtils.isEmpty(executives) && !StringUtils.isEmpty(executivesEmail))
				{
					String [] SplitStr=executives.split(",");
					String [] SplitEmail=executivesEmail.split(",");
						
					for(int i=0;i<SplitStr.length;i++)
					{
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(SplitStr[i]);
						msgObj.setMessage("A new R & R against "+obj.getWork_id()+" has been added");
						msgObj.setRedirect_url("/get-rr/"+rr_id);
						msgObj.setMessage_type("R & R");	
						BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
						template.update(messageQry, paramSource1);						
					}
				
				
				
				/*********************************************************************************************/
				String mailTo = "";
				String mailCC = "";
				for(int i=0;i<SplitEmail.length;i++)
				{
					if (!StringUtils.isEmpty(SplitEmail[i])) {
						mailTo = mailTo + SplitEmail[i] + ",";
					}
				}

				if (!StringUtils.isEmpty(mailTo)) {
					mailTo = org.apache.commons.lang3.StringUtils.chop(mailTo);
				}

				if (!StringUtils.isEmpty(mailCC)) {
					mailCC = org.apache.commons.lang3.StringUtils.chop(mailCC);
				}

				String mailBodyHeader =  "A new R & R against "+obj.getWork_id()+" has been added";
				
				
				RandRMain sobj = null;

				String query = "select distinct rr_id, r.work_id, identification_no,w.work_short_name,w.work_name,w.project_id_fk,p.project_name, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, "
						+ "type_of_structure_wall, type_of_structure_floor, r.carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, FORMAT(physical_verification ,'dd-MM-yyyy') AS physical_verification, verification_by,FORMAT(approval_by_committee ,'dd-MM-yyyy') AS  approval_by_committee,r.remarks,estimation_amount_units,estimated_by_mmrda_amount_units,"
						+ "FORMAT(rr_approval_status_by_mrvc ,'dd-MM-yyyy') AS  rr_approval_status_by_mrvc, estimation_amount,FORMAT(estimate_approval_date ,'dd-MM-yyyy') AS estimate_approval_date,FORMAT(letter_to_mmrda ,'dd-MM-yyyy') AS letter_to_mmrda, estimates_by_mmrda, FORMAT(payment_to_mmrda ,'dd-MM-yyyy') AS payment_to_mmrda, FORMAT(alternate_housing_allotment ,'dd-MM-yyyy') AS alternate_housing_allotment,FORMAT(relocation ,'dd-MM-yyyy') AS relocation,FORMAT(encroachment_removal ,'dd-MM-yyyy') AS encroachment_removal, boundary_wall_status, "
						+ "FORMAT(boundary_wall_doc ,'dd-MM-yyyy') AS boundary_wall_doc,FORMAT(handed_over_to_execution ,'dd-MM-yyyy') AS handed_over_to_execution, occupier_name_during_verification,"
						+ "rr1.id, rr1.rr_id_fk, rr1.name_of_activity, rr1.year_of_establishment, rr1.carpet_area as com_carpet_area, rr1.monthly_turnover_amount, rr1.monthly_turnover_amount_units, rr1.number_of_employees, rr1.remarks as com_remarks,"
						+ "rr2.id, rr2.rr_id_fk, rr2.occupancy_status, rr2.gender, rr2.family_income_amount_units,rr2.tenure_status, rr2.caste, rr2.mother_tongue, rr2.type_of_family, rr2.family_size, rr2.number_of_married_couple, rr2.family_income_amount, rr2.vulnerable_category"
						+ " from rr r " + 
						"left join work w on r.work_id = w.work_id  "
						+ "LEFT OUTER JOIN contract c ON c.work_id_fk = w.work_id "
						+ "left join rr_executives re on r.work_id = re.work_id_fk  "+
						"left join project p on w.project_id_fk = p.project_id  "
						+ "left join rr_commercial_details rr1 on r.rr_id = rr1.rr_id_fk "
						+ "left join rr_residential_details rr2 on r.rr_id = rr2.rr_id_fk "
						+ " where rr_id=? " ;
				Object[] pValues = new Object[] { rr_id };
						
				sobj = (RandRMain)jdbcTemplate.queryForObject( query, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));						

				sobj.setMail_body_header(mailBodyHeader);

				String emailSubject = "PMIS R & R Notification - R & R ";

				Mail mail = new Mail();
				mail.setMailTo(mailTo);
				mail.setMailCc(mailCC);
				mail.setMailBcc(CommonConstants.BCC_MAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("RandRAlert.vm");

				SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
				String today_date = monthFormat.format(new Date()).toUpperCase();

				SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
				String current_year = yearFormat.format(new Date()).toUpperCase();

				if (!StringUtils.isEmpty(mailTo)) {
					EMailSender emailSender = new EMailSender();
					logger.error("sendEmailWithRandRAlert() >> Sending mail to " + mailTo + ": Start ");
					logger.error("sendEmailWithRandRAlert() >> Sending mail CC " + mailCC + ": Start ");
					emailSender.sendEmailWithRandRAlert(mail, sobj, today_date, current_year);
					logger.error("sendEmailWithRandRAlert() >> Sending mail to " + mailTo + ": end ");
					logger.error("sendEmailWithRandRAlert() >> Sending mail CC " + mailCC + ": end ");
				}
				}
				
			}
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return flag;
	}
	
	private String getRRExecutives(String work_id) throws Exception {
		String executives="";
		try {
			String qry = "SELECT  STRING_AGG(u.user_id , ',') user_id FROM rr_executives re " + 
					"LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where work_id=?";
			executives = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return executives;
	}
	
	private String getRRExecutivesEmail(String work_id) throws Exception {
		String executivesEmail="";
		try {
			String qry = "SELECT  STRING_AGG(u.email_id , ',') email_id FROM rr_executives re " + 
					"LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where work_id=?";
			executivesEmail = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return executivesEmail;
	}		

	private String getAutoGeneratedRRId(RandRMain obj) {
		RandRMain dObj = null;
		RandRMain WorkCodedObj = null;
		String laId = obj.getWork_code()+"-RR-0001";
		try {
			String qry ="select work_code  from work where work_id='"+obj.getWork_id_fk()+"'" ;
			WorkCodedObj = (RandRMain)jdbcTemplate.queryForObject(qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	
			
				String qry2="select CONCAT('"+WorkCodedObj.getWork_code()+"','-RR-',case when len(rr_id)=3 then concat('0',rr_id) when len(rr_id)=2 then concat('00',rr_id) when len(rr_id)=1 then concat('000',rr_id) end) as rr_id from(" + 
						"select (case when (select count(*) from rr where left(rr_id,2) ='"+WorkCodedObj.getWork_code()+"')>0 then Max(SUBSTRING( rr_id , LEN(rr_id) -  CHARINDEX('-',REVERSE(rr_id)) + 2  , LEN(rr_id)  ))+1 else 1 end )as rr_id from rr where left(rr_id,2) ='"+WorkCodedObj.getWork_code()+"') as a";
						dObj = (RandRMain)jdbcTemplate.queryForObject(qry2, new Object[] {}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
				
					laId = dObj.getRr_id();
		}catch(Exception e){ 
			e.printStackTrace();
		}
	    return laId;
	}	

	@Override
	public boolean updateRR(RandRMain obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		int checkCnt=checkRandRAnyColumnUpdate(obj);
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE rr "
					+ " set identification_no= :identification_no, map_sr_no= :map_sr_no, location_name= :location_name, sub_location_name= :sub_location_name"
					+ ", phase= :phase, structure_id= :structure_id, type_of_structure_roof= :type_of_structure_roof, type_of_structure_wall= :type_of_structure_wall"
					+ ", type_of_structure_floor= :type_of_structure_floor,"
					+ "carpet_area= :carpet_area, year_of_construction= :year_of_construction, name_of_the_owner= :name_of_the_owner, type_of_use= :type_of_use"
					+ ", document_type= :document_type, document_no= :document_no, physical_verification= :physical_verification, verification_by=  :verification_by, "
					+ "approval_by_committee= :approval_by_committee, rr_approval_status_by_mrvc= :rr_approval_status_by_mrvc, estimation_amount= :estimation_amount"
					+ ", letter_to_mmrda= :letter_to_mmrda, estimate_approval_date= :estimate_approval_date,estimates_by_mmrda= :estimates_by_mmrda,"
					+ "payment_to_mmrda= :payment_to_mmrda,alternate_housing_allotment= :alternate_housing_allotment,relocation= :relocation,encroachment_removal= :encroachment_removal"
					+ ",boundary_wall_status= :boundary_wall_status,boundary_wall_doc= :boundary_wall_doc,handed_over_to_execution= :handed_over_to_execution"
					+ ",occupier_name_during_verification= :occupier_name_during_verification, remarks= :remarks, estimation_amount_units= :estimation_amount_units, estimated_by_mmrda_amount_units= :estimated_by_mmrda_amount_units,planned_date_of_completion= :planned_date_of_completion,chainage=:chainage,latitude=:latitude,longitude=:longitude,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP  "
					+ " WHERE   rr_id= :rr_id ";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				if(!StringUtils.isEmpty(obj.getType_of_use())) {
					if(obj.getType_of_use().equalsIgnoreCase("Residential")) {
						String updateQry = "UPDATE rr_residential_details SET "
								+ " occupancy_status= :occupancy_status, gender= :gender, tenure_status= :tenure_status, caste= :caste, mother_tongue= :mother_tongue, "
								+ "type_of_family= :type_of_family, family_size= :family_size, number_of_married_couple= :number_of_married_couple,"
								+ " family_income_amount= family_income_amount, family_income_amount_units= :family_income_amount_units, vulnerable_category= :vulnerable_category "
								+ " Where rr_id_fk = :rr_id";
						
						String insertQry1 = "INSERT INTO rr_residential_details"
								+ "( rr_id_fk, occupancy_status, gender, tenure_status, caste, mother_tongue, "
								+ "type_of_family, family_size, number_of_married_couple, family_income_amount, family_income_amount_units, vulnerable_category)"
								+ "VALUES"
								+ "(:rr_id, :occupancy_status, :gender, :tenure_status, :caste,:mother_tongue, "
								+ " :type_of_family, :family_size, :number_of_married_couple, :family_income_amount, :family_income_amount_units, :vulnerable_category)";
							
						String table_name= "rr_residential_details";
						String rrIDavailableOrNot = getAvailabilityStatusOfRRId(obj,table_name);
						
						if(!StringUtils.isEmpty(rrIDavailableOrNot)) {
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 count = namedParamJdbcTemplate.update(updateQry, paramSource);
						}else {
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 count = namedParamJdbcTemplate.update(insertQry1, paramSource);
						}
						 if(count > 0) {
							con = dataSource.getConnection();
							String deleteQry = "DELETE from rr_residential_family_details where rr_id_fk = ?";		 
							stmt = con.prepareStatement(deleteQry);
							stmt.setString(1,obj.getRr_id()); 
							stmt.executeUpdate();
							DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
								
							 String detailsQry = "INSERT INTO rr_residential_family_details"
										+ "( rr_id_fk, residential_name, residential_relation_with_head, residential_age, residential_gender, residential_maritual_status, "
										+ "residential_education, residential_employment, residential_salary, residential_salary_units)"
										+ "VALUES"
										+ "(?,?,?,?,?,?,?,?,?,?)";
							 updateStmt = con.prepareStatement(detailsQry); 
							 int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getResidential_names()) && obj.getResidential_names().length > 0 ) {
									obj.setResidential_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_names()));
									if(arraySize < obj.getResidential_names().length) {
										arraySize = obj.getResidential_names().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_relation_with_heads()) && obj.getResidential_relation_with_heads().length > 0 ) {
									obj.setResidential_relation_with_heads(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_relation_with_heads()));
									if(arraySize < obj.getResidential_relation_with_heads().length) {
										arraySize = obj.getResidential_relation_with_heads().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_ages()) && obj.getResidential_ages().length > 0 ) {
									obj.setResidential_ages(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_ages()));
									if(arraySize < obj.getResidential_ages().length) {
										arraySize = obj.getResidential_ages().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_genders()) && obj.getResidential_genders().length > 0 ) {
									obj.setResidential_genders(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_genders()));
									if(arraySize < obj.getResidential_genders().length) {
										arraySize = obj.getResidential_genders().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_maritual_statuss()) && obj.getResidential_maritual_statuss().length > 0 ) {
									obj.setResidential_maritual_statuss(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_maritual_statuss()));
									if(arraySize < obj.getResidential_maritual_statuss().length) {
										arraySize = obj.getResidential_maritual_statuss().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_educations()) && obj.getResidential_educations().length > 0 ) {
									obj.setResidential_educations(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_educations()));
									if(arraySize < obj.getResidential_educations().length) {
										arraySize = obj.getResidential_educations().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_employments()) && obj.getResidential_employments().length > 0 ) {
									obj.setResidential_employments(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_employments()));
									if(arraySize < obj.getResidential_employments().length) {
										arraySize = obj.getResidential_employments().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_salarys()) && obj.getResidential_salarys().length > 0 ) {
									obj.setResidential_salarys(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_salarys()));
									if(arraySize < obj.getResidential_salarys().length) {
										arraySize = obj.getResidential_salarys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getResidential_salary_unitss()) && obj.getResidential_salary_unitss().length > 0 ) {
									obj.setResidential_salary_unitss(CommonMethods.replaceEmptyByNullInSringArray(obj.getResidential_salary_unitss()));
									if(arraySize < obj.getResidential_salary_unitss().length) {
										arraySize = obj.getResidential_salary_unitss().length;
									}
								}
								for (int i = 0;  i < arraySize; i++) {
									 if( obj.getResidential_names().length > 0 && !StringUtils.isEmpty(obj.getResidential_names()[i])) {
										int p = 1;
										    updateStmt.setString(p++,(obj.getRr_id()));
										    updateStmt.setString(p++,(obj.getResidential_names().length > 0)?obj.getResidential_names()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_relation_with_heads().length > 0)?obj.getResidential_relation_with_heads()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_ages().length > 0)?obj.getResidential_ages()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_genders().length > 0)?obj.getResidential_genders()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_maritual_statuss().length > 0)?obj.getResidential_maritual_statuss()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_educations().length > 0)?obj.getResidential_educations()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_employments().length > 0)?obj.getResidential_employments()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_salarys().length > 0)?obj.getResidential_salarys()[i]:null);
										    updateStmt.setString(p++,(obj.getResidential_salary_unitss().length > 0)?obj.getResidential_salary_unitss()[i]:null);
										    updateStmt.addBatch();
									    }
								}
								int[] insertCount = updateStmt.executeBatch();
								//if(updateStmt != null){updateStmt.close();}
						 }
					}else if(obj.getType_of_use().equalsIgnoreCase("Commercial")) {
						
								con = dataSource.getConnection();
								String deleteQryCommercial = "DELETE from rr_commercial_details where rr_id_fk = ?";		 
								stmt = con.prepareStatement(deleteQryCommercial);
								stmt.setString(1,obj.getRr_id()); 
								stmt.executeUpdate();
								DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);						
						
							String updateQry = "update rr_commercial_details set  "
									+ "name_of_activity= :name_of_activity,year_of_establishment= :year_of_establishment,carpet_area= :com_carpet_area,monthly_turnover_amount= :monthly_turnover_amount,"
									+ "monthly_turnover_amount_units= :monthly_turnover_amount_units,number_of_employees= :number_of_employees,remarks= :com_remarks "
									+ "  where rr_id_fk= :rr_id";
							
							String insertQry1 = "INSERT INTO rr_commercial_details "
							 		+ "( rr_id_fk, name_of_activity, year_of_establishment, carpet_area, monthly_turnover_amount,monthly_turnover_amount_units, number_of_employees, remarks)"
							 		+ "VALUES"
							 		+ "( :rr_id, :name_of_activity, :year_of_establishment, :com_carpet_area, :monthly_turnover_amount,:monthly_turnover_amount_units, :number_of_employees, :com_remarks)";
							
							String table_name= "rr_commercial_details";
							String rrIDavailableOrNot = getAvailabilityStatusOfRRId(obj,table_name);
							
							if(!StringUtils.isEmpty(rrIDavailableOrNot)) {
								 //paramSource = new BeanPropertySqlParameterSource(obj);		 
								 //count = namedParamJdbcTemplate.update(updateQry, paramSource);
							}else {
								 paramSource = new BeanPropertySqlParameterSource(obj);		 
								 count = namedParamJdbcTemplate.update(insertQry1, paramSource);
							}
							if(count > 0) {
								con = dataSource.getConnection();
								String deleteQry = "DELETE from rr_commercial_employee_details where rr_id_fk = ?";		 
								stmt = con.prepareStatement(deleteQry);
								stmt.setString(1,obj.getRr_id()); 
								stmt.executeUpdate();
								if(stmt != null){stmt.close();}
								
								String detailsQry = "INSERT INTO rr_commercial_employee_details"
											+ "( rr_id_fk, employee_name, employee_age, employee_gender, employee_literacy, employee_attended, "
											+ "employee_travel_time, employee_salary, employee_salary_units, employee_nature_of_work)"
											+ "VALUES"
											+ "(?,?,?,?,?,?,?,?,?,?)";
								 updateStmt = con.prepareStatement(detailsQry); 
								int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getEmployee_names()) && obj.getEmployee_names().length > 0 ) {
									obj.setEmployee_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_names()));
									if(arraySize < obj.getEmployee_names().length) {
										arraySize = obj.getEmployee_names().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_ages()) && obj.getEmployee_ages().length > 0 ) {
									obj.setEmployee_ages(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_ages()));
									if(arraySize < obj.getEmployee_ages().length) {
										arraySize = obj.getEmployee_ages().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_genders()) && obj.getEmployee_genders().length > 0 ) {
									obj.setEmployee_genders(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_genders()));
									if(arraySize < obj.getEmployee_genders().length) {
										arraySize = obj.getEmployee_genders().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_literacys()) && obj.getEmployee_literacys().length > 0 ) {
									obj.setEmployee_literacys(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_literacys()));
									if(arraySize < obj.getEmployee_literacys().length) {
										arraySize = obj.getEmployee_literacys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_attendeds()) && obj.getEmployee_attendeds().length > 0 ) {
									obj.setEmployee_attendeds(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_attendeds()));
									if(arraySize < obj.getEmployee_attendeds().length) {
										arraySize = obj.getEmployee_attendeds().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_travel_times()) && obj.getEmployee_travel_times().length > 0 ) {
									obj.setEmployee_travel_times(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_travel_times()));
									if(arraySize < obj.getEmployee_travel_times().length) {
										arraySize = obj.getEmployee_travel_times().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_salarys()) && obj.getEmployee_salarys().length > 0 ) {
									obj.setEmployee_salarys(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_salarys()));
									if(arraySize < obj.getEmployee_salarys().length) {
										arraySize = obj.getEmployee_salarys().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_salary_unitss()) && obj.getEmployee_salary_unitss().length > 0 ) {
									obj.setEmployee_salary_unitss(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_salary_unitss()));
									if(arraySize < obj.getEmployee_salary_unitss().length) {
										arraySize = obj.getEmployee_salary_unitss().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getEmployee_nature_of_works()) && obj.getEmployee_nature_of_works().length > 0 ) {
									obj.setEmployee_nature_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getEmployee_nature_of_works()));
									if(arraySize < obj.getEmployee_nature_of_works().length) {
										arraySize = obj.getEmployee_nature_of_works().length;
									}
								}
								for (int i = 0;  i < arraySize; i++) {
									 if( obj.getEmployee_names().length > 0 && !StringUtils.isEmpty(obj.getEmployee_names()[i])) {
										int p = 1;
										    updateStmt.setString(p++,(obj.getRr_id()));
										    updateStmt.setString(p++,(obj.getEmployee_names().length > 0)?obj.getEmployee_names()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_ages().length > 0)?obj.getEmployee_ages()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_genders().length > 0)?obj.getEmployee_genders()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_literacys().length > 0)?obj.getEmployee_literacys()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_attendeds().length > 0)?obj.getEmployee_attendeds()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_travel_times().length > 0)?obj.getEmployee_travel_times()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_salarys().length > 0)?obj.getEmployee_salarys()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_salary_unitss().length > 0)?obj.getEmployee_salary_unitss()[i]:null);
										    updateStmt.setString(p++,(obj.getEmployee_nature_of_works().length > 0)?obj.getEmployee_nature_of_works()[i]:null);
										    updateStmt.addBatch();
									    }
								}
								 int[] insertCount = updateStmt.executeBatch();
							}
					}
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("R & R");
				formHistory.setForm_name("Update R & R");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("R & R "+obj.getRr_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
				
				if(checkCnt>0)
				{
				
					String messageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
							+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";	
					String executives=getRRExecutives(obj.getWork_id());
					if(!StringUtils.isEmpty(executives) && executives!=null)
							{
								String [] SplitStr=executives.split(",");
									
								for(int i=0;i<SplitStr.length;i++)
								{
									Messages msgObj = new Messages();
									msgObj.setUser_id_fk(SplitStr[i]);
									msgObj.setMessage("A new R & R against "+obj.getWork_id()+" has been updated");
									msgObj.setRedirect_url("/get-rr/"+obj.getRr_id());
									msgObj.setMessage_type("R & R");	
									BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
									namedParamJdbcTemplate.update(messageQry, paramSource1);						
								}	
							}
				
				}				
				
				
			}
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}
		return flag;
	}
	
	
	private int checkRandRAnyColumnUpdate(RandRMain obj) throws Exception {
		int checkCnt=0;
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT * from rr where rr_id='"+obj.getRr_id()+"'";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));	

			if(!StringUtils.isEmpty(objsList) && objsList.size() > 0)
			{
					if(objsList.get(0).getWork_id().compareTo(obj.getWork_id())!=0)
					{
						checkCnt=1;
					}
					if(objsList.get(0).getLocation_name().compareTo(obj.getLocation_name())!=0)
					{
						checkCnt=1;
					}
					if(objsList.get(0).getSub_location_name().compareTo(obj.getSub_location_name())!=0)
					{
						checkCnt=1;
					}
					if(objsList.get(0).getIdentification_no().compareTo(obj.getIdentification_no())!=0)
					{
						checkCnt=1;
					}
					if(objsList.get(0).getType_of_use().compareTo(obj.getType_of_use())!=0)
					{
						checkCnt=1;
					}
					if(!StringUtils.isEmpty(objsList.get(0).getBoundary_wall_status()))
					{
						if(objsList.get(0).getBoundary_wall_status().compareTo(obj.getBoundary_wall_status())!=0)
						{
							checkCnt=1;
						}	
					}
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return checkCnt;
	}	
	

	private String getAvailabilityStatusOfRRId(RandRMain obj, String table_name) {
		RandRMain dObj = null;
		String rr_id = null;
		try {
			String qry ="select rr_id_fk from "+table_name+" where rr_id_fk = ? " ;
			dObj = (RandRMain)jdbcTemplate.queryForObject(qry, new Object[] {obj.getRr_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			rr_id = dObj.getId();
			return rr_id;
		}catch(Exception e){ 
			rr_id = null;
			return rr_id;
		}
	}

	@Override
	public List<RandRMain> getRRIdListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_id  from rr ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getRandRMainList(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rr_id, r.work_id as work_id_fk, identification_no ,w.work_short_name,w.work_name,w.project_id_fk,p.project_name, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall," + 
					"  type_of_structure_floor, carpet_area,  year_of_construction, name_of_the_owner, type_of_use,"
					+ " document_type, document_no, FORMAT(physical_verification ,'dd-MM-yyyy') AS physical_verification, verification_by,"
					+ "FORMAT(approval_by_committee ,'dd-MM-yyyy') AS  approval_by_committee,"
					+ "FORMAT(rr_approval_status_by_mrvc ,'dd-MM-yyyy') AS  rr_approval_status_by_mrvc,cast(estimation_amount as CHAR) as  estimation_amount, m1.unit as estimation_amount_units, "
					+ "FORMAT(estimate_approval_date ,'dd-MM-yyyy') AS estimate_approval_date,FORMAT(letter_to_mmrda ,'dd-MM-yyyy') AS letter_to_mmrda, "
					+ "cast(estimates_by_mmrda as CHAR) as estimates_by_mmrda, m.unit as estimated_by_mmrda_amount_units, FORMAT(payment_to_mmrda ,'dd-MM-yyyy') AS payment_to_mmrda, "
					+ "FORMAT(alternate_housing_allotment ,'dd-MM-yyyy') AS alternate_housing_allotment,"
					+ "FORMAT(relocation ,'dd-MM-yyyy') AS relocation,FORMAT(encroachment_removal ,'dd-MM-yyyy') AS encroachment_removal,"
					+ "FORMAT(boundary_wall_doc ,'dd-MM-yyyy') AS boundary_wall_doc,"
					+ "FORMAT(handed_over_to_execution ,'dd-MM-yyyy') AS handed_over_to_execution, occupier_name_during_verification, r.remarks,chainage,latitude,longitude from rr r " + 
					" LEFT JOIN work w on r.work_id = w.work_id " + 
					 "LEFT JOIN rr_executives re ON re.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id  " + 
					"left join money_unit m on r.estimated_by_mmrda_amount_units = m.value  " + 
					"left join money_unit m1 on r.estimation_amount_units = m1.value  " + 
					" WHERE rr_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and re.executive_user_id_fk = ? ";
				arrSize++;
			}
 			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSearchStr())) {
 					qry = qry + " and (r.rr_id like ? or r.occupier_name_during_verification like ? or boundary_wall_status like ?"
 							+ " or type_of_use like ? or structure_id like ? or location_name like ? or sub_location_name like ? or phase like ?)";
 					arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				pValues[i++] = obj.getPhase();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				pValues[i++] = obj.getBoundary_wall_status();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSearchStr())) {
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
			}				
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> gecommercialList(String rr_id) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rr_id_fk, name_of_activity, year_of_establishment,"
					+ " rc.carpet_area as com_carpet_area,cast(monthly_turnover_amount as CHAR) as monthly_turnover_amount, m.unit as monthly_turnover_amount_units, number_of_employees, rc.remarks as com_remarks from rr_commercial_details rc "
					+ "LEFT JOIN rr r on rc.rr_id_fk = r.rr_id "
					+"left join money_unit m on rc.monthly_turnover_amount_units = m.value  "
					+ "WHERE rr_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr_id) ) {
				qry = qry + " and rr_id_fk  = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(rr_id)) {
				pValues[i++] = rr_id;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getComDetailsListList(String rr_id) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rc.id, rr_id_fk, employee_name, employee_age, employee_gender, employee_literacy, employee_attended, "
					+ "employee_travel_time, cast(employee_salary as CHAR) as employee_salary,m.unit as  employee_salary_units, employee_nature_of_work  from rr_commercial_employee_details rc "
					+ "LEFT JOIN rr r on rc.rr_id_fk = r.rr_id "
					+"left join money_unit m on rc.employee_salary_units = m.value  "
					+ "WHERE rr_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr_id) ) {
				qry = qry + " and rr_id_fk  = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(rr_id)) {
				pValues[i++] = rr_id;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getResidentialList(String rr_id) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rr_id_fk, occupancy_status, gender, tenure_status, caste, mother_tongue, type_of_family, "
					+ "family_size, number_of_married_couple,cast(family_income_amount as CHAR) as family_income_amount,m.unit as family_income_amount_units, vulnerable_category from rr_residential_details rc "
					+ "LEFT JOIN rr r on rc.rr_id_fk = r.rr_id "
					+"left join money_unit m on rc.family_income_amount_units = m.value  "
					+ "WHERE rr_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr_id) ) {
				qry = qry + " and rr_id_fk  = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(rr_id)) {
				pValues[i++] = rr_id;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getRDetailsList(String rr_id) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry ="select distinct rc.id, rr_id_fk, residential_name, residential_relation_with_head, residential_age, residential_gender, residential_maritual_status, residential_education,"
					+ " residential_employment,cast(residential_salary as CHAR) as residential_salary,m.unit as  residential_salary_units from rr_residential_family_details rc "
					+ "LEFT JOIN rr r on rc.rr_id_fk = r.rr_id "
					+"left join money_unit m on rc.residential_salary_units = m.value  "
					+ "WHERE rr_id_fk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr_id) ) {
				qry = qry + " and rr_id_fk  = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(rr_id)) {
				pValues[i++] = rr_id;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	private int checkWorkinRandR(String work_id,String userId) throws Exception {
		int Count=0;
		try {
			String qry = "SELECT count(*) AS count FROM rr_executives WHERE work_id_fk=? and executive_user_id_fk=?";
			Count = (int) jdbcTemplate.queryForObject(qry, new Object[] { work_id,userId }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Count;
	}

	@Override
	public String[] uploadRRData(List<RandRMain> rrsList, RandRMain rr) throws Exception {
		boolean flag = false;
		int count = 0,row =1,sheet = 1,subRow = 1,cnt=0;
		int sheet1 =1,sheet2=1,sheet3=1,sheet4=1;
		String errMsg = null;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO rr"
					+ "( rr_id, work_id, identification_no, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor,"
					+ "carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, "
					+ "approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, letter_to_mmrda, estimate_approval_date,estimates_by_mmrda,planned_date_of_completion,"
					+ "payment_to_mmrda,alternate_housing_allotment,relocation,encroachment_removal,boundary_wall_status,boundary_wall_doc,handed_over_to_execution,occupier_name_during_verification,remarks,created_by,created_date,chainage,latitude,longitude)"
					+ "VALUES"
					+ "(:rr_id, :work_id, :identification_no, :map_sr_no, :location_name, :sub_location_name, :phase, :structure_id, :type_of_structure_roof, :type_of_structure_wall, :type_of_structure_floor, "
					+ ":carpet_area, :year_of_construction, :name_of_the_owner, :type_of_use, :document_type, :document_no, :physical_verification, :verification_by, "
					+ ":approval_by_committee, :rr_approval_status_by_mrvc, :estimation_amount, :letter_to_mmrda, :estimate_approval_date , :estimates_by_mmrda,:planned_date_of_completion, :payment_to_mmrda, :alternate_housing_allotment, "
					+ ":relocation, :encroachment_removal, :boundary_wall_status, :boundary_wall_doc,:handed_over_to_execution, :occupier_name_during_verification, :remarks, :created_by_user_id_fk,CURRENT_TIMESTAMP,:chainage,:latitude,:longitude)";
			
			String updatetQry = "UPDATE rr "
					+ " set identification_no= :identification_no, map_sr_no= :map_sr_no, location_name= :location_name, sub_location_name= :sub_location_name"
					+ ", phase= :phase, structure_id= :structure_id, type_of_structure_roof= :type_of_structure_roof, type_of_structure_wall= :type_of_structure_wall"
					+ ", type_of_structure_floor= :type_of_structure_floor,"
					+ "carpet_area= :carpet_area, year_of_construction= :year_of_construction, name_of_the_owner= :name_of_the_owner, type_of_use= :type_of_use"
					+ ", document_type= :document_type, document_no= :document_no, physical_verification= :physical_verification, verification_by=  :verification_by, "
					+ "approval_by_committee= :approval_by_committee, rr_approval_status_by_mrvc= :rr_approval_status_by_mrvc, estimation_amount= :estimation_amount"
					+ ", letter_to_mmrda= :letter_to_mmrda, estimate_approval_date= :estimate_approval_date,estimates_by_mmrda= :estimates_by_mmrda,"
					+ "payment_to_mmrda= :payment_to_mmrda,alternate_housing_allotment= :alternate_housing_allotment,relocation= :relocation,encroachment_removal= :encroachment_removal"
					+ ",boundary_wall_status= :boundary_wall_status,boundary_wall_doc= :boundary_wall_doc,handed_over_to_execution= :handed_over_to_execution"
					+ ",occupier_name_during_verification= :occupier_name_during_verification, remarks= :remarks, estimation_amount_units= :estimation_amount_units, estimated_by_mmrda_amount_units= :estimated_by_mmrda_amount_units,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP,chainage=:chainage,latitude=:latitude,longitude=:longitude  "
					+ " WHERE   rr_id= :rr_id ";
		//	int rNo = 0;
			for (RandRMain obj : rrsList) {
				
				String table_name = "rr";
				String rr_id = obj.getRr_id();
				

				if(!StringUtils.isEmpty(obj.getChainage())) {
					double c1=Double.parseDouble(obj.getChainage());
					obj.setWork_id_fk(obj.getWork_id());
					
					List<RandRMain> getChainageCoordinates=getRRCoordinates(obj);
					
					if(!StringUtils.isEmpty(getChainageCoordinates.get(0).getChainage()))
					{
					
						String splitChainage=getChainageCoordinates.get(0).getChainage();
						String splitChainage1=splitChainage.toString();
						String[] splitChainage2=splitChainage1.split(",");
							
						String splitLatitude=getChainageCoordinates.get(0).getLatitude();
						String splitLatitude1=splitLatitude.toString();
						String[] splitLatitude2=splitLatitude1.split(",");
		            	
						String splitLongitude=getChainageCoordinates.get(0).getLongitude();
						String splitLongitude1=splitLongitude.toString();
						String[] splitLongitude2=splitLongitude1.split(",");                    	
		            	
		            	
						String a1= splitChainage2[0];    String x1=splitLatitude2[0]; String y1=splitLongitude2[0];
						String b1=splitChainage2[1];	 String x2=splitLatitude2[1]; String y2=splitLongitude2[1];
		
						double x3=0;   double y3=0;
		
		                x3=Double.parseDouble(x2)+(((c1-Double.parseDouble(b1))/(Double.parseDouble(b1)-Double.parseDouble(a1)))*(Double.parseDouble(x2)-Double.parseDouble(x1)));
		                y3=Double.parseDouble(y2)+(((c1-Double.parseDouble(b1))/(Double.parseDouble(b1)-Double.parseDouble(a1)))*(Double.parseDouble(y2)-Double.parseDouble(y1)));				
						
						obj.setLatitude(String.valueOf(x3));
						obj.setLongitude(String.valueOf(y3));
					}
					else
					{
						
						String splitLatitude=getChainageCoordinates.get(0).getLatitude();
						String splitLongitude=getChainageCoordinates.get(0).getLongitude();
						
						obj.setLatitude(String.valueOf(splitLatitude));
						obj.setLongitude(String.valueOf(splitLongitude));					
					}
				}

				row++;sheet = 1;
				if(!StringUtils.isEmpty(rr_id)) {
					obj.setRr_id(rr_id);
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						if(checkWorkinRandR(obj.getWork_id(),obj.getCreated_by_user_id_fk())>0)
						{
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(updatetQry, paramSource);	
						    cnt=cnt+1;
						}
					}					
					else
					{
						SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					    count = namedParamJdbcTemplate.update(updatetQry, paramSource);
					    cnt=cnt+1;
					}
				}else {
					obj.setWork_id(obj.getWork_id());
					obj.setWork_id_fk(obj.getWork_id());
					rr_id = getAutoGeneratedRRId(obj);
					obj.setRr_id(rr_id);					
					//System.out.println(rNo++);
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						if(checkWorkinRandR(obj.getWork_id(),obj.getCreated_by_user_id_fk())>0)
						{
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(insertQry, paramSource);	
						    cnt=cnt+1;
						}
					}					
					else
					{
						SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					    count = namedParamJdbcTemplate.update(insertQry, paramSource);
					    cnt=cnt+1;
					}
				}
				
				if(!StringUtils.isEmpty(obj.getResList())) {
					subRow = sheet1;
					String comInsertQry = "INSERT INTO rr_residential_details"
							+ "( rr_id_fk, occupancy_status, gender, tenure_status, caste, mother_tongue, "
							+ "type_of_family, family_size, number_of_married_couple, family_income_amount, family_income_amount_units, vulnerable_category)"
							+ "VALUES"
							+ "(:rr_id, :occupancy_status, :gender, :tenure_status, :caste,:mother_tongue, "
							+ " :type_of_family, :family_size, :number_of_married_couple, :family_income_amount, :family_income_amount_units, :vulnerable_category)";
					
					String  comUpdateQry  = "UPDATE rr_residential_details SET "
							+ " occupancy_status= :occupancy_status, gender= :gender, tenure_status= :tenure_status, caste= :caste, mother_tongue= :mother_tongue, "
							+ "type_of_family= :type_of_family, family_size= :family_size, number_of_married_couple= :number_of_married_couple,"
							+ " family_income_amount= family_income_amount, family_income_amount_units= :family_income_amount_units, vulnerable_category= :vulnerable_category "
							+ " Where rr_id_fk = :rr_id";
					
					for (RandRMain obj1 : obj.getResList()) {
						String table_name1 = "rr_residential_details";
						String rr_id1 = checkLAIdMethod(obj1,table_name1);
						sheet = 4;subRow++;
						if(!StringUtils.isEmpty(rr_id1)) {
							obj.setRr_id(rr_id1);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj1);
						    count = namedParamJdbcTemplate.update(comUpdateQry, paramSource);
						   
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj1);
						    count = namedParamJdbcTemplate.update(comInsertQry, paramSource);
						   
						}
					}
					sheet1 = sheet1 + obj.getResList().size();
				}
				if(!StringUtils.isEmpty(obj.getResFamList())) {
					subRow = sheet2;
					String  privateLAInsertQry = "INSERT INTO rr_residential_family_details"
							+ "( rr_id_fk, residential_name, residential_relation_with_head, residential_age, residential_gender, residential_maritual_status, "
							+ "residential_education, residential_employment, residential_salary, residential_salary_units)"
							+ "VALUES"
							+ "( :rr_id, :residential_name, :residential_relation_with_head, :residential_age, :residential_gender, :residential_maritual_status,"
							+ ":residential_education, :residential_employment, :residential_salary, :residential_salary_units)"
							;
					 
					String  privateLAUpdateQry = "UPDATE rr_residential_family_details SET "
							+ " residential_name =:residential_name, residential_relation_with_head =:residential_relation_with_head, "
							+ "residential_age =:residential_age, residential_gender =:residential_gender, residential_maritual_status =:residential_maritual_status, "
							+ "residential_education =:residential_education, residential_employment =:residential_employment, "
							+ "residential_salary =:residential_salary "
					 		+ "where rr_id_fk= :rr_id";
					
					for (RandRMain obj2 : obj.getResFamList()) {
						sheet = 5;subRow++;
						String table_name2 = "rr_residential_family_details";
						String rr_id2 = checkLAIdMethod(obj2,table_name2);
						if(!StringUtils.isEmpty(rr_id2)) {
							obj.setRr_id(rr_id2);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj2);
						    count = namedParamJdbcTemplate.update(privateLAUpdateQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj2);
						    count = namedParamJdbcTemplate.update(privateLAInsertQry, paramSource);
						}
					}
					sheet2 = sheet2 + obj.getResFamList().size();
				}
				if(!StringUtils.isEmpty(obj.getComList())) {
					subRow = sheet3;
					String privateInsertSubQry = "INSERT INTO rr_commercial_details "
					 		+ "( rr_id_fk, name_of_activity, year_of_establishment, carpet_area, monthly_turnover_amount,monthly_turnover_amount_units, number_of_employees, remarks)"
					 		+ "VALUES"
					 		+ "( :rr_id, :name_of_activity, :year_of_establishment, :com_carpet_area, :monthly_turnover_amount,:monthly_turnover_amount_units, :number_of_employees, :com_remarks)";
					 
					 
					String privateUpdateSubQry = "UPDATE rr_commercial_details SET "
					 		+ "name_of_activity =:name_of_activity, year_of_establishment =:year_of_establishment, carpet_area =:com_carpet_area,"
					 		+ "monthly_turnover_amount =:monthly_turnover_amount, number_of_employees =:number_of_employees, remarks =:com_remarks "
					 		+ "where rr_id_fk= :rr_id";
					
					for (RandRMain obj3 : obj.getComList()) {
						sheet = 2;subRow++;
						String table_name3 = "rr_commercial_details";
						String rr_id3 = checkLAIdMethod(obj3,table_name3);
						if(!StringUtils.isEmpty(rr_id3)) {
							obj.setRr_id(rr_id3);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj3);
						    count = namedParamJdbcTemplate.update(privateUpdateSubQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj3);
						    count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);
						}
					}
					sheet3 = sheet3 + obj.getComList().size();
				}
				if(!StringUtils.isEmpty(obj.getComFamList())) {
					subRow = sheet4;
					String govInsertQry = "INSERT INTO rr_commercial_employee_details"
							+ "( rr_id_fk, employee_name, employee_age, employee_gender, employee_literacy, employee_attended, "
							+ "employee_travel_time, employee_salary, employee_nature_of_work)"
							+ "VALUES"
							+ "( :rr_id, :employee_name, :employee_age, :employee_gender, :employee_literacy, :employee_attended, "
							+ ":employee_travel_time, :employee_salary, :employee_nature_of_work)";
					
					String govUpdateQry = "UPDATE  rr_commercial_employee_details SET "
							+ " employee_name= :employee_name, employee_age= :employee_age, employee_gender= :employee_gender, employee_literacy= :employee_literacy, "
							+ "employee_attended= :employee_attended,employee_travel_time= :employee_travel_time, employee_salary= :employee_salary, employee_nature_of_work= :employee_nature_of_work "
							+ "where  rr_id_fk= :rr_id";
					for (RandRMain obj4 : obj.getComFamList()) {
						sheet = 3;subRow++;
						String table_name4 = "rr_commercial_employee_details";
						String rr_id4 = checkLAIdMethod(obj4,table_name4);
						if(!StringUtils.isEmpty(rr_id4)) {
							obj.setRr_id(rr_id4);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj4);
						    count = namedParamJdbcTemplate.update(govUpdateQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj4);
						    count = namedParamJdbcTemplate.update(govInsertQry, paramSource);
						} 
					}
					sheet4 = sheet4 + obj.getComFamList().size();
				}
			   
			}
		   //count = rrsList.size();
			count=cnt;
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			errMsg = e.getMessage();
		}
		String arr[] = new String[5];
		arr[0] = errMsg;
	    arr[1] = String.valueOf(count);
	    arr[2] = String.valueOf(row);
	    arr[3] = String.valueOf(sheet);
	    arr[4] = String.valueOf(subRow);
		return arr;
	}
	@Override
	public String checkLAIdMethod(RandRMain obj, String table_name) {
		RandRMain dObj = null;
		String laId = null;
		String qry1 = "";
		String column_name = "rr_id";
		try {
			if((table_name.equals("test"))){
				table_name = "rr";
			}
			if(!(table_name.equals("rr"))){
				column_name = "rr_id_fk";
			}
			String qry ="select "+column_name+" as rr_id from "+table_name+" where "+column_name+" = ? " ;
			dObj = (RandRMain)jdbcTemplate.queryForObject(qry, new Object[] {obj.getRr_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			laId = dObj.getRr_id();
			if((table_name.equals("rr"))){
				try {
					qry1 = " and work_id = ? ";
					String qry2 ="select "+column_name+" as rr_id from "+table_name+" where "+column_name+" = ? "+qry1 ;
					dObj = (RandRMain)jdbcTemplate.queryForObject(qry2, new Object[] {obj.getRr_id(),obj.getWork_id()}, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
					laId = dObj.getRr_id();
				}
				catch(Exception e){ 
					laId = null;
					return laId;
				}
			}
			return laId;
		}
		catch(Exception e){ 
				laId = null;
				return laId;
		}
	}

	@Override
	public List<RandRMain> getRRUploadsList(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT distinct rr_data_id, uploaded_file, rru.status, rru.remarks, uploaded_by_user_id_fk, FORMAT(uploaded_on,'dd-MMM-yy hh:mm tt') as uploaded_on "
					+ ",uploaded_on as date from rr_upload_data rru " 
					+ "LEFT JOIN [user] u ON rru.uploaded_by_user_id_fk = u.user_id "
					+ "where rr_data_id is not null order by rr_data_id desc ";
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean saveRRDataUploadFile(RandRMain obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String rr_data_id = null;		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO rr_upload_data"
					+ "(uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on) "
					+ "VALUES "
					+ "( :uploaded_file, :status, :remarks, :uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				rr_data_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setRr_data_id(rr_data_id);
				flag = true;
				
				MultipartFile file = obj.getRandRFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants.RR_UPLOADED_FILE_SAVING_PATH ;
					String fileName = rr_data_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					obj.setUploaded_file(fileName);
					String updateQry = "UPDATE rr_upload_data set uploaded_file= :uploaded_file where rr_data_id= :rr_data_id ";
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
	
	public int getCoordinatesCount(RandRMain obj) throws Exception
	{
		int count=0;
		try {
			String qry = "select count(*) from chainages_master where work_id='"+obj.getWork_id_fk()+"' and chainages='"+obj.getChainage()+"'";
			count = (int) jdbcTemplate.queryForObject(qry, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return count;
	}	

	@Override
	public List<RandRMain> getRRCoordinates(RandRMain obj) throws Exception {
		List<RandRMain> objList = null;
			
		if(getCoordinatesCount(obj)>0)
		{
			try {
				String qry ="select latitude,longitude from chainages_master where work_id='"+obj.getWork_id_fk()+"' and chainages='"+obj.getChainage()+"'";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
				
			}catch(Exception e){ 
				e.printStackTrace();
				throw new Exception(e);
			}
		}
		else
		{
			try {
				String qry ="select string_agg(chainages,',') as chainage,string_agg(latitude,',') as latitude,string_agg(longitude,',') as longitude from chainages_master where work_id='"+obj.getWork_id_fk()+"' and id between (select min(id)-1 from chainages_master where work_id='"+obj.getWork_id_fk()+"' and chainages>=cast('"+obj.getChainage()+"' as decimal(18,2))) and (select min(id) from chainages_master where work_id='"+obj.getWork_id_fk()+"' and chainages>=cast('"+obj.getChainage()+"' as decimal(18,2)))";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
				
			}catch(Exception e){ 
				e.printStackTrace();
				throw new Exception(e);
			}
			return objList;				
		}
		return objList;
	}

	@Override
	public List<RandRMain> getBSESAgencyNamesList(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select distinct bses_agency_name "
					+ "from work w "
					+ "LEFT JOIN rr_agency r ON r.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON p.project_id = w.project_id_fk "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + "and work_id = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public String getRRId(String action) throws Exception {
		String rrid="";
		try {
			if(action.compareTo("add")==0)
			{
				String qry = "select top 1 rr_id  from rr order by created_date desc";
				rrid = (String) jdbcTemplate.queryForObject(qry, String.class);
			}
			else if(action.compareTo("update")==0)
			{
				String qry = "select top 1 rr_id  from rr order by modified_date desc";
				rrid = (String) jdbcTemplate.queryForObject(qry, String.class);				
			}
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return rrid;
	}

	
}
