package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.RandRMainDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
@Repository
public class RandRMainDaoImpl implements RandRMainDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<RandRMain> getWorksFilterListInRR(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.work_id as work_id_fk,w.work_name,w.work_short_name from rr r " + 
					"LEFT JOIN work w on r.work_id = w.work_id "+
					"where r.work_id is not null and r.work_id <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY r.work_id ";
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
					"where r.boundary_wall_status is not null and r.boundary_wall_status <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
					"where r.location_name is not null and r.location_name <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
					"where r.type_of_use is not null and r.type_of_use <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
					"where r.structure_id is not null and r.structure_id <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
					"where r.phase is not null and r.phase <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
			String qry ="select count(*) as total_records from rr r "
					+ "LEFT JOIN work w on r.work_id = w.work_id "
					+ "where rr_id is not null  ";
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
			String qry ="select rr_id, r.work_id, identification_no, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor, carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, estimate_approval_date, letter_to_mmrda, estimates_by_mmrda, payment_to_mmrda, alternate_housing_allotment,"
					+ " relocation, encroachment_removal, boundary_wall_status, boundary_wall_doc, handed_over_to_execution, occupier_name_during_verification from rr r "
					+ "LEFT JOIN work w on r.work_id = w.work_id "
					+ "WHERE rr_id is not null ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPhase())) {
				qry = qry + " and phase = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBoundary_wall_status())) {
				qry = qry + " and boundary_wall_status = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
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
				qry = qry + " ORDER BY rr_id ASC limit ?,?";
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
		List<RandRMain> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getWorkListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = new ArrayList<RandRMain>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
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
			String qry = "select structure as structure_id from structure ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getLocationListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_location as location_name from rr_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getSubLocationListForRRForm(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "select rr_sub_location as sub_location_name from rr_sub_location ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));			
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
			String qry = "select rr_verification_by as verification_by from rr_verification_by ";
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
			String qry = "select maritua_status   from maritua_status ";
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
			String qry = "select rr_id, r.work_id, identification_no,w.work_short_name,w.work_name,w.project_id_fk,p.project_name, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, "
					+ "type_of_structure_wall, type_of_structure_floor, r.carpet_area, DATE_FORMAT(year_of_construction ,'%d-%m-%Y') AS year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, DATE_FORMAT(physical_verification ,'%d-%m-%Y') AS physical_verification, verification_by,DATE_FORMAT(approval_by_committee ,'%d-%m-%Y') AS  approval_by_committee,r.remarks,estimation_amount_units,estimated_by_mmrda_amount_units,"
					+ "DATE_FORMAT(rr_approval_status_by_mrvc ,'%d-%m-%Y') AS  rr_approval_status_by_mrvc, estimation_amount,DATE_FORMAT(estimate_approval_date ,'%d-%m-%Y') AS estimate_approval_date,DATE_FORMAT(letter_to_mmrda ,'%d-%m-%Y') AS letter_to_mmrda, estimates_by_mmrda, DATE_FORMAT(payment_to_mmrda ,'%d-%m-%Y') AS payment_to_mmrda, DATE_FORMAT(alternate_housing_allotment ,'%d-%m-%Y') AS alternate_housing_allotment,DATE_FORMAT(relocation ,'%d-%m-%Y') AS relocation,DATE_FORMAT(encroachment_removal ,'%d-%m-%Y') AS encroachment_removal, boundary_wall_status, "
					+ "DATE_FORMAT(boundary_wall_doc ,'%d-%m-%Y') AS boundary_wall_doc,DATE_FORMAT(handed_over_to_execution ,'%d-%m-%Y') AS handed_over_to_execution, occupier_name_during_verification,"
					+ "rr1.id, rr1.rr_id_fk, rr1.name_of_activity, rr1.year_of_establishment, rr1.carpet_area as com_carpet_area, rr1.monthly_turnover_amount, rr1.monthly_turnover_amount_units, rr1.number_of_employees, rr1.remarks as com_remarks,"
					+ "rr2.id, rr2.rr_id_fk, rr2.occupancy_status, rr2.gender, rr2.family_income_amount_units,rr2.tenure_status, rr2.caste, rr2.mother_tongue, rr2.type_of_family, rr2.family_size, rr2.number_of_married_couple, rr2.family_income_amount, rr2.vulnerable_category"
					+ " from rr r " + 
					"left join work w on r.work_id = w.work_id  "+
					"left join project p on w.project_id_fk = p.project_id  "
					+ "left join rr_commercial_details rr1 on r.rr_id = rr1.rr_id_fk "
					+ "left join rr_residential_details rr2 on r.rr_id = rr2.rr_id_fk "
					+ " where rr_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(rr) && !StringUtils.isEmpty(rr.getRr_id())) {
				qry = qry + " and rr_id = ?";
				arrSize++;
			}
			qry = qry + " group by rr_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
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
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO rr"
					+ "( rr_id, work_id, identification_no, map_sr_no, location_name, sub_location_name, phase, structure_id, type_of_structure_roof, type_of_structure_wall, type_of_structure_floor,"
					+ "carpet_area, year_of_construction, name_of_the_owner, type_of_use, document_type, document_no, physical_verification, verification_by, "
					+ "approval_by_committee, rr_approval_status_by_mrvc, estimation_amount, letter_to_mmrda, estimate_approval_date,estimates_by_mmrda,"
					+ "payment_to_mmrda,alternate_housing_allotment,relocation,encroachment_removal,boundary_wall_status,boundary_wall_doc,handed_over_to_execution,occupier_name_during_verification,remarks,estimated_by_mmrda_amount_units,estimation_amount_units)"
					+ "VALUES"
					+ "(:rr_id, :work_id, :identification_no, :map_sr_no, :location_name, :sub_location_name, :phase, :structure_id, :type_of_structure_roof, :type_of_structure_wall, :type_of_structure_floor, "
					+ ":carpet_area, :year_of_construction, :name_of_the_owner, :type_of_use, :document_type, :document_no, :physical_verification, :verification_by, "
					+ ":approval_by_committee, :rr_approval_status_by_mrvc, :estimation_amount, :letter_to_mmrda, :estimate_approval_date , :estimates_by_mmrda, :payment_to_mmrda, :alternate_housing_allotment, "
					+ ":relocation, :encroachment_removal, :boundary_wall_status, :boundary_wall_doc, :handed_over_to_execution, :occupier_name_during_verification, :remarks, :estimated_by_mmrda_amount_units, :estimation_amount_units)";
			
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

	@Override
	public boolean updateRR(RandRMain obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		PreparedStatement stmt = null;
		boolean flag = false;
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
					+ ",occupier_name_during_verification= :occupier_name_during_verification, remarks= :remarks, estimation_amount_units= :estimation_amount_units, estimated_by_mmrda_amount_units= :estimated_by_mmrda_amount_units  "
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
								 paramSource = new BeanPropertySqlParameterSource(obj);		 
								 count = namedParamJdbcTemplate.update(updateQry, paramSource);
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
	
	
	
}
