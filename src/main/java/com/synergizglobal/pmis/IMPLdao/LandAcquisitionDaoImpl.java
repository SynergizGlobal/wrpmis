package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.LandAcquisitionDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Messages;


@Repository
public class LandAcquisitionDaoImpl implements LandAcquisitionDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select la_id,survey_number,li.remarks,li.area_to_be_acquired,li.category_fk as type_of_land,li.la_land_status_fk,li.work_id_fk,w.work_name,w.project_id_fk,p.project_name,IFNULL(li.category_fk,c.la_category) as type_of_land ,sc.la_sub_category as sub_category_of_land, w.work_short_name,village_id,la_sub_category_fk,village,cast(area_of_plot as CHAR) as area_of_plot,modified_by,DATE_FORMAT(modified_date,'%d-%m-%Y') as modified_date " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "
					+ "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (li.work_id_fk like ? or w.work_short_name like ? or survey_number like ? or village like ?"
						+ " or c.la_category like ? or sc.la_sub_category like ? or area_of_plot like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY la_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}	

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
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
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(LandAcquisition obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "
					+ "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (li.work_id_fk like ? or w.work_short_name like ? or survey_number like ? or village like ?"
						+ " or c.la_category like ? or sc.la_sub_category like ? or area_of_plot like ?)";
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
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
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
			}
			
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return totalRecords;
	}

	
	@Override
	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT li.work_id_fk,w.work_name,w.work_short_name from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where li.work_id_fk is not null and li.work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY li.work_id_fk ORDER BY li.work_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT village from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where village is not null and village <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY village ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT c.la_category as type_of_land from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where c.la_category is not null and c.la_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ?";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY la_category ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT sc.la_sub_category as sub_category_of_land from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where la_sub_category is not null and la_sub_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY la_sub_category ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getStatusList() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select status from la_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));	
		}catch(Exception e){ 
		throw new Exception(e); 
		}
		return objsList;
	}

	@Override
	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception {					
		LandAcquisition LADetails = null;
		try {
			String qry = "select la_id,li.remarks,(select executive_user_id_fk from land_executives re where li.work_id_fk = re.work_id_fk and executive_user_id_fk = ?) as executive_user_id_fk,cast(li.area_to_be_acquired as CHAR) as area_to_be_acquired,IFNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk, work_id_fk,w.project_id_fk,p.project_name,w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo, li.collector, DATE_FORMAT(proposal_submission_date_to_collector,'%d-%m-%Y') AS proposal_submission_date_to_collector, cast(area_of_plot as CHAR) as area_of_plot, jm_fee_amount,jm_fee_amount_units, "
					+ "li.special_feature,cast(li.area_acquired as CHAR) as area_acquired,li.private_land_process,cast(chainage_from as CHAR) as chainage_from,cast(chainage_to as CHAR) as chainage_to, DATE_FORMAT(jm_fee_letter_received_date,'%d-%m-%Y') AS jm_fee_letter_received_date,DATE_FORMAT(jm_fee_paid_date,'%d-%m-%Y') AS jm_fee_paid_date,DATE_FORMAT(jm_start_date,'%d-%m-%Y') AS  jm_start_date,DATE_FORMAT(jm_completion_date,'%d-%m-%Y') AS jm_completion_date, DATE_FORMAT(jm_sheet_date_to_sdo,'%d-%m-%Y') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues"
					
					+ ",lg.id, lg.la_id_fk,DATE_FORMAT(lg.proposal_submission,'%d-%m-%Y') AS proposal_submission, lg.proposal_submission_status_fk, DATE_FORMAT(lg.valuation_date,'%d-%m-%Y') AS valuation_date, DATE_FORMAT(lg.letter_for_payment,'%d-%m-%Y') AS letter_for_payment,"
					+ "lg.amount_demanded,cast(lg.lfp_status_fk as CHAR) as lfp_status_fk,DATE_FORMAT(lg.approval_for_payment,'%d-%m-%Y') AS approval_for_payment,DATE_FORMAT(lg.payment_date,'%d-%m-%Y') AS payment_date, lg.amount_paid, lg.payment_status_fk, DATE_FORMAT(lg.possession_date,'%d-%m-%Y') AS possession_date,"
					+ "lg.possession_status_fk,lf.demanded_amount_units as demanded_amount_units_forest,lf.payment_amount_units as payment_amount_units_forest, "
					//lg.special_feature, 
					+ "lg.amount_demanded_units,lg.amount_paid_units, DATE_FORMAT(lf.on_line_submission,'%d-%m-%Y') AS forest_online_submission, DATE_FORMAT(lf.submission_date_to_dycfo,'%d-%m-%Y') AS forest_submission_date_to_dycfo, DATE_FORMAT(lf.submission_date_to_ccf_thane,'%d-%m-%Y') AS forest_submission_date_to_ccf_thane, "
					+ "DATE_FORMAT(lf.submission_date_to_nodal_officer,'%d-%m-%Y') AS forest_submission_date_to_nodal_officer, DATE_FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS forest_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lf.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS forest_submission_date_to_regional_office_nagpur,"
					+ " DATE_FORMAT(lf.date_of_approval_by_regional_office_nagpur,'%d-%m-%Y') AS forest_date_of_approval_by_regional_office_nagpur, DATE_FORMAT(lf.valuation_by_dycfo,'%d-%m-%Y') AS forest_valuation_by_dycfo,cast(lf.demanded_amount as CHAR) as forest_demanded_amount,cast(lf.payment_amount  as CHAR) as forest_payment_amount, DATE_FORMAT(lf.approval_for_payment,'%d-%m-%Y') AS forest_approval_for_payment"
					+ ", DATE_FORMAT(lf.payment_date,'%d-%m-%Y') AS forest_payment_date,DATE_FORMAT(lf.possession_date,'%d-%m-%Y') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk,"
					+ "lf.payment_status_fk as forest_payment_status_fk"
					//lf.special_feature as forest_special_feature, 
					+ " ,lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,DATE_FORMAT(lr.online_submission,'%d-%m-%Y') AS railway_online_submission,"
					+ "DATE_FORMAT(lr.submission_date_to_DyCFO,'%d-%m-%Y') AS railway_submission_date_to_DyCFO, DATE_FORMAT(lr.submission_date_to_CCF_Thane,'%d-%m-%Y') AS railway_submission_date_to_CCF_Thane, DATE_FORMAT(lr.`submission_date_to_nodal_officer/CCF Nagpur`,'%d-%m-%Y') AS railway_submission_date_to_nodal_officer_CCF_Nagpur, "
					+ " DATE_FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS railway_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lr.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS railway_submission_date_to_regional_office_nagpur, DATE_FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'%d-%m-%Y') AS railway_date_of_approval_by_Rregional_Office_agpur,"
					+ "DATE_FORMAT(lr.valuation_by_DyCFO ,'%d-%m-%Y') AS railway_valuation_by_DyCFO, cast(lr.demanded_amount as CHAR) as railway_demanded_amount, DATE_FORMAT(lr.approval_for_payment,'%d-%m-%Y') AS railway_approval_for_payment, DATE_FORMAT(lr.payment_date,'%d-%m-%Y') AS railway_payment_date,cast(lr.payment_amount as CHAR) as railway_payment_amount, lr.payment_status as railway_payment_status, DATE_FORMAT(lr.possession_date,'%d-%m-%Y') AS railway_possession_date, lr.possession_status as railway_possession_status, "
					+ " "
					//lr.special_feature as railway_special_feature, 
					+ "lpa.basic_rate_units,lpa.agriculture_tree_rate_units,lpa.forest_tree_rate_units, lpa.name_of_the_owner, lpa.basic_rate, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,"
					+ "lpa.forest_tree_rate,DATE_FORMAT(lpa.consent_from_owner,'%d-%m-%Y') AS consent_from_owner, DATE_FORMAT(lpa.legal_search_report,'%d-%m-%Y') AS legal_search_report, DATE_FORMAT(lpa.date_of_registration,'%d-%m-%Y') AS date_of_registration, DATE_FORMAT(lpa.date_of_possession,'%d-%m-%Y') AS date_of_possession, lpa.possession_status_fk as private_possession_status_fk, "
					+ "cast(lpa.hundred_percent_Solatium as CHAR) as hundred_percent_Solatium,cast(lpa.extra_25_percent as CHAR) as extra_25_percent, cast(lpa.total_rate_divide_m2 as CHAR) as total_rate_divide_m2,cast(lpa.land_compensation as CHAR) as land_compensation, "
					+ "cast(lpa.agriculture_tree_compensation as CHAR) as agriculture_tree_compensation,cast(lpa.forest_tree_compensation as CHAR) as forest_tree_compensation,cast(lpa.structure_compensation as CHAR) as structure_compensation,cast(lpa.borewell_compensation as CHAR) as borewell_compensation,cast(lpa.total_compensation as CHAR) as total_compensation,"
					//lpa.special_feature as private_special_feature, 
					+ "lpv.payment_amount_units,DATE_FORMAT(lpv.forest_tree_survey ,'%d-%m-%Y') AS forest_tree_survey,DATE_FORMAT(lpv.forest_tree_valuation ,'%d-%m-%Y') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,DATE_FORMAT(lpv.horticulture_tree_survey ,'%d-%m-%Y') AS horticulture_tree_survey,DATE_FORMAT(lpv.horticulture_tree_valuation ,'%d-%m-%Y') AS horticulture_tree_valuation, "
					+ "DATE_FORMAT(lpv.structure_survey ,'%d-%m-%Y') AS structure_survey,DATE_FORMAT(lpv.structure_valuation ,'%d-%m-%Y') AS structure_valuation,DATE_FORMAT(lpv.borewell_survey ,'%d-%m-%Y') AS borewell_survey,DATE_FORMAT(lpv.borewell_valuation ,'%d-%m-%Y') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, lpv.structure_valuation_status_fk, "
					+ "lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, DATE_FORMAT(lpv.date_of_rfp_to_adtp ,'%d-%m-%Y') AS date_of_rfp_to_adtp,DATE_FORMAT(lpv.date_of_rate_fixation_of_land ,'%d-%m-%Y') AS date_of_rate_fixation_of_land, DATE_FORMAT(lpv.sdo_demand_for_payment ,'%d-%m-%Y') AS sdo_demand_for_payment,DATE_FORMAT(lpv.date_of_approval_for_payment ,'%d-%m-%Y') AS date_of_approval_for_payment, "
					+ "cast(lpv.payment_amount as CHAR) as payment_amount, DATE_FORMAT(lpv.payment_date ,'%d-%m-%Y') AS private_payment_date  "
					
					+ " ,ira.collector as private_ira_collector, DATE_FORMAT(submission_of_proposal_to_GM ,'%d-%m-%Y') AS submission_of_proposal_to_GM,DATE_FORMAT(approval_of_GM ,'%d-%m-%Y') AS  approval_of_GM,DATE_FORMAT(draft_letter_to_con_for_approval_rp ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_rp,DATE_FORMAT(date_of_approval_of_construction_rp ,'%d-%m-%Y') AS  date_of_approval_of_construction_rp,DATE_FORMAT(date_of_uploading_of_gazette_notification_rp ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_rp,"
					+ "DATE_FORMAT(publication_in_gazette_rp ,'%d-%m-%Y') AS publication_in_gazette_rp,DATE_FORMAT(date_of_proposal_to_DC_for_nomination ,'%d-%m-%Y') AS  date_of_proposal_to_DC_for_nomination, DATE_FORMAT(date_of_nomination_of_competenta_authority ,'%d-%m-%Y') AS date_of_nomination_of_competenta_authority, DATE_FORMAT(draft_letter_to_con_for_approval_ca ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_ca, DATE_FORMAT(date_of_approval_of_construction_ca ,'%d-%m-%Y') AS date_of_approval_of_construction_ca, "
					+ "DATE_FORMAT(date_of_uploading_of_gazette_notification_ca ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_ca, DATE_FORMAT(publication_in_gazette_ca ,'%d-%m-%Y') AS publication_in_gazette_ca, DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA,DATE_FORMAT(approval_of_CALA_20a ,'%d-%m-%Y') AS approval_of_CALA_20a,DATE_FORMAT(draft_letter_to_con_for_approval_20a ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_20a,"
					+ "DATE_FORMAT(date_of_approval_of_construction_20a ,'%d-%m-%Y') AS date_of_approval_of_construction_20a,DATE_FORMAT(date_of_uploading_of_gazette_notification_20a ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20a,DATE_FORMAT(publication_in_gazette_20a ,'%d-%m-%Y') AS publication_in_gazette_20a,DATE_FORMAT(publication_in_2_local_news_papers_20a ,'%d-%m-%Y') AS publication_in_2_local_news_papers_20a,DATE_FORMAT(pasting_of_notification_in_villages_20a ,'%d-%m-%Y') AS pasting_of_notification_in_villages_20a,"
					+ "DATE_FORMAT(receipt_of_grievances ,'%d-%m-%Y') AS  receipt_of_grievances, DATE_FORMAT(disposal_of_grievances ,'%d-%m-%Y') AS disposal_of_grievances, DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA_20e ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA_20e, DATE_FORMAT(approval_of_CALA_20e ,'%d-%m-%Y') AS  approval_of_CALA_20e,DATE_FORMAT(draft_letter_to_con_for_approval_20e ,'%d-%m-%Y') AS  draft_letter_to_con_for_approval_20e,"  
					+"DATE_FORMAT(date_of_approval_of_construction_20e ,'%d-%m-%Y') AS  date_of_approval_of_construction_20e,DATE_FORMAT(date_of_uploading_of_gazette_notification_20e ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20e,DATE_FORMAT(publication_in_gazette_20e ,'%d-%m-%Y') AS  publication_in_gazette_20e,DATE_FORMAT(publication_of_notice_in_2_local_news_papers_20e ,'%d-%m-%Y') AS publication_of_notice_in_2_local_news_papers_20e,DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA_20f ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA_20f," 
					+"DATE_FORMAT(approval_of_CALA_20f ,'%d-%m-%Y') AS approval_of_CALA_20f,DATE_FORMAT(draft_letter_to_con_for_approval_20f ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_20f,DATE_FORMAT(date_of_approval_of_construction_20f ,'%d-%m-%Y') AS date_of_approval_of_construction_20f,DATE_FORMAT(date_of_uploading_of_gazette_notification_20f ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20f,DATE_FORMAT(publication_in_gazette_20f ,'%d-%m-%Y') AS publication_in_gazette_20f,"
					+ "DATE_FORMAT(publication_of_notice_in_2_local_news_papers_20f ,'%d-%m-%Y') AS publication_of_notice_in_2_local_news_papers_20f "
					+"from la_land_identification li "
					+"left join la_government_land_acquisition lg on li.la_id = lg.la_id_fk " 
					+"left join la_forest_land_acquisition lf on li.la_id = lf.la_id_fk " 
					+"left join la_railway_land_acquisition lr on li.la_id = lr.la_id_fk " 
					+"left join la_private_land_acquisition lpa on li.la_id = lpa.la_id_fk " 
					+"left join la_private_land_valuation lpv on li.la_id = lpv.la_id_fk " 
					+"left join la_private_ira ira on li.la_id = ira.la_id_fk " 
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join work w on li.work_id_fk = w.work_id "
					+"left join project p on w.project_id_fk = p.project_id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					" where la_id is not null" ; 
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_id())) {
				qry = qry + " and la_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getUser_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_id())) {
				pValues[i++] = obj.getLa_id();
			}
			LADetails = (LandAcquisition)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
			if(!StringUtils.isEmpty(LADetails)) {
				String qry2 ="select id as la_file_id, la_id_fk, la_file_type_fk, name, attachment from la_files where la_id_fk = ?";
				List<LandAcquisition> objList = jdbcTemplate.query( qry2,new Object[] {obj.getLa_id()}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
				LADetails.setLaFilesList(objList);
			}
			
	}catch(Exception e) {
		throw new Exception(e);
	}
	return LADetails;
	}
	private String getAutoGeneratedLAId(LandAcquisition obj) {
		LandAcquisition dObj = null;
		String laId = obj.getWork_code()+"-LA-01";
		List<LandAcquisition> objsList = null;
		try {
			String qry ="SELECT la_id FROM la_land_identification where la_id like '"+obj.getWork_code()+"-LA%' " ;
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));	
			
			if(!StringUtils.isEmpty(objsList) && objsList.size() > 0){
					String qry2 ="SELECT CONCAT('"+obj.getWork_code()+"',SUBSTRING(la_id, 3,4),LPAD(MAX(replace(la_id,'"+obj.getWork_code()+"-LA-',''))+1,"
							+ "IFNULL ((SELECT length(max(replace(la_id,'"+obj.getWork_code()+"-LA-','')))FROM la_land_identification "
							+ " where la_id like '"+obj.getWork_code()+"-LA%' group by length(la_id) order by length(la_id) desc limit 1),2),'0') ) AS la_id "
							+ "FROM la_land_identification WHERE la_id LIKE '"+obj.getWork_code()+"-LA-%' group by length(la_id) order by length(la_id) desc limit 1 " ;
					dObj = (LandAcquisition)jdbcTemplate.queryForObject(qry2, new Object[] {}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
					laId = dObj.getLa_id();
			}
		}catch(Exception e){ 
			e.printStackTrace();
		}
	    return laId;
	}
	@Override
	public boolean addLandAcquisition(LandAcquisition obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String la_id = getAutoGeneratedLAId(obj);
			obj.setLa_id(la_id);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO la_land_identification"
					+ "( la_id, work_id_fk, survey_number, village_id, la_sub_category_fk, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector,"
					+ "area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, jm_start_date, jm_completion_date, "
					+ "jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues, jm_fee_amount_units,special_feature,"
					+ "area_acquired,private_land_process,la_land_status_fk,category_fk,area_to_be_acquired,remarks)"
					+ "VALUES"
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :id, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :issues, :jm_fee_amount_units , :special_feature, :area_acquired, :private_land_process, "
					+ ":la_land_status_fk, :category_fk, :area_to_be_acquired, :remarks)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				String sub_category_of_land = getSubCategoryLand(obj.getId());
				obj.setSub_category_of_land(sub_category_of_land);
				if(!StringUtils.isEmpty(obj.getJm_approval())) {
					if(obj.getCategory_fk().equalsIgnoreCase("Government")) {
						String govInsertQry = "INSERT INTO la_government_land_acquisition"
								+ "( la_id_fk, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, "
								+ "lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk,"//special_feature
								+ "amount_demanded_units, amount_paid_units)"
								+ "VALUES"
								+ "(:la_id, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
								+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk," // :special_feature, 
								+ " :amount_demanded_units, :amount_paid_units)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(govInsertQry, paramSource);	
							
					}else if(obj.getCategory_fk().equalsIgnoreCase("Forest")) {
						String forestInsertSubQry = "INSERT INTO la_forest_land_acquisition "
						 		+ "( la_id_fk, on_line_submission, submission_date_to_dycfo, submission_date_to_ccf_thane, submission_date_to_nodal_officer, "
						 		+ "submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur, valuation_by_dycfo, "
						 		+ "demanded_amount, payment_amount, approval_for_payment, payment_date, possession_date, possession_status_fk, payment_status_fk,"//, special_feature
						 		+ "  demanded_amount_units, payment_amount_units)"
						 		+ "VALUES"
						 		+ "( :la_id, :forest_online_submission, :forest_submission_date_to_dycfo, :forest_submission_date_to_ccf_thane, :forest_submission_date_to_nodal_officer, "
						 		+ ":forest_submission_date_to_revenue_secretary_mantralaya, :forest_submission_date_to_regional_office_nagpur, :forest_date_of_approval_by_regional_office_nagpur, :forest_valuation_by_dycfo,"
						 		+ ":forest_demanded_amount, :forest_payment_amount, :forest_approval_for_payment, :forest_payment_date, :forest_possession_date, :forest_possession_status_fk, :forest_payment_status_fk, "// :forest_special_feature,
						 		+ ":demanded_amount_units_forest, :payment_amount_units_forest)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);	
						
					}else if(obj.getCategory_fk().equalsIgnoreCase("Railway")) {
						String railwayInsertSubQry = " INSERT INTO la_railway_land_acquisition"
						 		+ "(la_id_fk, online_submission, submission_date_to_DyCFO, "
						 		+ "submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`, submission_date_to_revenue_secretary_mantralaya, "
						 		+ "submission_date_to_regional_office_nagpur, date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO, demanded_amount, "
						 		+ "approval_for_payment, payment_date, payment_amount, payment_status, possession_date, possession_status, "
						 		+ "demanded_amount_units, payment_amount_units)"
						 		+ "VALUES"
						 		+ "(:la_id, :railway_online_submission, :railway_submission_date_to_DyCFO, "
						 		+ ":railway_submission_date_to_CCF_Thane, :railway_submission_date_to_nodal_officer_CCF_Nagpur, :railway_submission_date_to_revenue_secretary_mantralaya, "
						 		+ ":railway_submission_date_to_regional_office_nagpur, :railway_date_of_approval_by_Rregional_Office_agpur, :railway_valuation_by_DyCFO, :railway_demanded_amount, "
						 		+ ":railway_approval_for_payment, :railway_payment_date, :railway_payment_amount, :railway_payment_status, :railway_possession_date, :railway_possession_status, "//, :railway_special_feature
						 		+ " :demanded_amount_units, :payment_amount_units_railway)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(railwayInsertSubQry, paramSource);	
							
					}else if(obj.getCategory_fk().equalsIgnoreCase("Private")) {
						if(!StringUtils.isEmpty(obj.getPrivate_land_process()) && obj.getPrivate_land_process().equals("Private DPM")) {
							String  privateInsertQry = "INSERT INTO la_private_land_acquisition "
							 		+ "(la_id_fk, name_of_the_owner, basic_rate, agriculture_tree_nos, agriculture_tree_rate, forest_tree_nos,"
							 		+ " forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, possession_status_fk, "
							 		+ "hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2,"
							 		+ " land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation,"
							 		+ "  basic_rate_units, agriculture_tree_rate_units, forest_tree_rate_units)"
							 		+ "VALUES"
							 		+ "(:la_id, :name_of_the_owner, :basic_rate, :agriculture_tree_nos, :agriculture_tree_rate, :forest_tree_nos,"
							 		+ " :forest_tree_rate, :consent_from_owner, :legal_search_report, :date_of_registration, :date_of_possession, :private_possession_status_fk, "//:private_special_feature, 
							 		+ " :hundred_percent_Solatium, :extra_25_percent, :total_rate_divide_m2,"
							 		+ " :land_compensation, :agriculture_tree_compensation, :forest_tree_compensation, :structure_compensation, :borewell_compensation, :total_compensation,"
							 		+ " : :basic_rate_units, :agriculture_tree_rate_units, :forest_tree_rate_units)";
							 
							 	paramSource = new BeanPropertySqlParameterSource(obj);		 
								count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);
								
							String privateInsertSubQry = "INSERT INTO la_private_land_valuation "
							 		+ "(la_id_fk, forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, "
							 		+ "structure_survey, structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, "
							 		+ "borewell_valuation_status_fk, rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, "
							 		+ "date_of_approval_for_payment, payment_amount, payment_date, payment_amount_units)"
							 		+ "VALUES"
							 		+ "(:la_id, :forest_tree_survey, :forest_tree_valuation, :forest_tree_valuation_status_fk, :horticulture_tree_survey, :horticulture_tree_valuation,"
							 		+ " :structure_survey, :structure_valuation, :borewell_survey, :borewell_valuation, :horticulture_tree_valuation_status_fk, :structure_valuation_status_fk,"
							 		+ " :borewell_valuation_status_fk, :rfp_to_adtp_status_fk, :date_of_rfp_to_adtp, :date_of_rate_fixation_of_land, :sdo_demand_for_payment,"
							 		+ " :date_of_approval_for_payment, :payment_amount, :private_payment_date,:payment_amount_units)";
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);	
						}else {
							String  privateInsertQry = "INSERT INTO la_private_ira "
									+ " (la_id_fk, collector, submission_of_proposal_to_GM, approval_of_GM, draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp, date_of_uploading_of_gazette_notification_rp, "
									+ "publication_in_gazette_rp, date_of_proposal_to_DC_for_nomination, date_of_nomination_of_competenta_authority, draft_letter_to_con_for_approval_ca, date_of_approval_of_construction_ca, "
									+ "date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca, date_of_submission_of_draft_notification_to_CALA, approval_of_CALA_20a, draft_letter_to_con_for_approval_20a,"
									+ " date_of_approval_of_construction_20a, date_of_uploading_of_gazette_notification_20a, publication_in_gazette_20a, publication_in_2_local_news_papers_20a, pasting_of_notification_in_villages_20a,"
									+ " receipt_of_grievances, disposal_of_grievances, date_of_submission_of_draft_notification_to_CALA_20e, approval_of_CALA_20e, draft_letter_to_con_for_approval_20e, date_of_approval_of_construction_20e, "
									+ "date_of_uploading_of_gazette_notification_20e, publication_in_gazette_20e, publication_of_notice_in_2_local_news_papers_20e, date_of_submission_of_draft_notification_to_CALA_20f, approval_of_CALA_20f,"
									+ " draft_letter_to_con_for_approval_20f, date_of_approval_of_construction_20f, date_of_uploading_of_gazette_notification_20f, publication_in_gazette_20f, publication_of_notice_in_2_local_news_papers_20f)"
									+ "VALUES"
									+ "(:la_id, :private_ira_collector, :submission_of_proposal_to_GM, :approval_of_GM, :draft_letter_to_con_for_approval_rp, :date_of_approval_of_construction_rp, :date_of_uploading_of_gazette_notification_rp,"
									+ ":publication_in_gazette_rp, :date_of_proposal_to_DC_for_nomination, :date_of_nomination_of_competenta_authority, :draft_letter_to_con_for_approval_ca, :date_of_approval_of_construction_ca,"
									+ " :date_of_uploading_of_gazette_notification_ca, :publication_in_gazette_ca, :date_of_submission_of_draft_notification_to_CALA, :approval_of_CALA_20a, :draft_letter_to_con_for_approval_20a,"
									+ "	:date_of_approval_of_construction_20a, :date_of_uploading_of_gazette_notification_20a, :publication_in_gazette_20a, :publication_in_2_local_news_papers_20a, :pasting_of_notification_in_villages_20a,"
									+ " :receipt_of_grievances, :disposal_of_grievances, :date_of_submission_of_draft_notification_to_CALA_20e, :approval_of_CALA_20e, :draft_letter_to_con_for_approval_20e, :date_of_approval_of_construction_20e,"
									+ " :date_of_uploading_of_gazette_notification_20e, :publication_in_gazette_20e, :publication_of_notice_in_2_local_news_papers_20e, :date_of_submission_of_draft_notification_to_CALA_20f, :approval_of_CALA_20f, :draft_letter_to_con_for_approval_20f,"
									+ " :date_of_approval_of_construction_20f, :date_of_uploading_of_gazette_notification_20f, :publication_in_gazette_20f, :publication_of_notice_in_2_local_news_papers_20f)";
							 paramSource = new BeanPropertySqlParameterSource(obj);		 
							 count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);	
						}
					}
				}
				if(flag) {
					int arraySize = 0;
					if(!StringUtils.isEmpty(obj.getLaFileNames()) && obj.getLaFileNames().length > 0 ) {
						obj.setLaFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getLaFileNames()));
						if(arraySize < obj.getLaFileNames().length) {
							arraySize = obj.getLaFileNames().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getLa_file_typess()) && obj.getLa_file_typess().length > 0 ) {
						obj.setLa_file_typess(CommonMethods.replaceEmptyByNullInSringArray(obj.getLa_file_typess()));
						if(arraySize < obj.getLa_file_typess().length) {
							arraySize = obj.getLa_file_typess().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getLaDocumentNames()) && obj.getLaDocumentNames().length > 0 ) {
						obj.setLaDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getLaDocumentNames()));
						if(arraySize < obj.getLaDocumentNames().length) {
							arraySize = obj.getLaDocumentNames().length;
						}
					}
					String file_insert_qry = "INSERT into  la_files ( la_id_fk,la_file_type_fk,name,attachment) VALUES (:la_id,:la_file_type_fk,:name,:attachment)";

					/*	if(!StringUtils.isEmpty(obj.getLaFiles()) && obj.getLaFiles().size() > 0) {
							String file_insert_qry = "INSERT into  la_files ( la_id_fk,la_file_type_fk,name,attachment) VALUES (:la_id,:la_file_type_fk,:name:attachment)";
							List<MultipartFile> laFiles = obj.getLaFiles();
							int f =0;
							for (MultipartFile multipartFile : laFiles) {
								if (null != multipartFile && !multipartFile.isEmpty()){
									String saveDirectory = CommonConstants.LAND_ACQUISITION_FILE_SAVING_PATH ;
									String fileName = multipartFile.getOriginalFilename();
									FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
									
									LandAcquisition fileObj = new LandAcquisition();
									fileObj.setLa_id(obj.getLa_id());
									fileObj.setLa_file_type_fk(obj.getLa_file_typess()[f]);
									fileObj.setName(obj.getLaDocumentNames()[f]);
									fileObj.setAttachment(fileName);
									
									paramSource = new BeanPropertySqlParameterSource(fileObj);	
									namedParamJdbcTemplate.update(file_insert_qry, paramSource);
								}
								f++;
							}
						}*/	
					for (int f = 0;  f < arraySize; f++) {
						if ((!StringUtils.isEmpty(obj.getLaFiles()) &&  null != obj.getLaFiles()[f] && !obj.getLaFiles()[f].isEmpty())){
							String saveDirectory = CommonConstants.LAND_ACQUISITION_FILE_SAVING_PATH ;
							String fileName = null;
							MultipartFile multipartFile = obj.getLaFiles()[f];
							
							if(null != multipartFile && !multipartFile.isEmpty()) {
								fileName = multipartFile.getOriginalFilename();
							}else {
								fileName = obj.getLaDocumentFileNames()[f];
							}
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							obj.setAttachment(fileName);
						
							LandAcquisition fileObj = new LandAcquisition();
							fileObj.setLa_id(obj.getLa_id());
							fileObj.setLa_file_type_fk(obj.getLa_file_typess()[f]);
							fileObj.setName(obj.getLaDocumentNames()[f]);
							fileObj.setAttachment(fileName);
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(file_insert_qry, paramSource);
						}
					}
					
					if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
						if(!StringUtils.isEmpty(obj.getIssue_category_id())){
							String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CURDATE())";				
							jdbcTemplate.update(new PreparedStatementCreator() {
								@Override
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps = connection.prepareStatement(issuesQry);
									int i = 1;
									ps.setString(i++, obj.getIssue_description());
									ps.setString(i++, obj.getIssue_description());
									ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
									ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
									ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
									ps.setString(i++, "Raised");
									ps.setString(i++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
									return ps;
								}
							});
						}
					}
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Land Acquisition");
				formHistory.setForm_name("Add Land Acquisition");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Land Acquisition "+obj.getLa_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
	private String getLandExecutives(String work_id) throws Exception {
		String executives="";
		try {
			String qry = "SELECT  GROUP_CONCAT(DISTINCT (u.user_id) SEPARATOR ',') user_id FROM land_executives re " + 
					"left join user u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where work_id=?";
			executives = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return executives;
	}		

	private String getSubCategoryLand(String id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sub_category_of_land = null;;
		try{
			con = dataSource.getConnection();
			String qry = "SELECT  la_sub_category  FROM la_sub_category where id = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				sub_category_of_land = rs.getString("la_sub_category");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return sub_category_of_land;
	}

	@Override
	public List<LandAcquisition> getWorkListForLAForm(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = new ArrayList<LandAcquisition>();
		try {
			String qry = "select distinct work_id,work_name,work_short_name,work_code,project_id_fk,project_name "
					+ "from `work` w "
					+ "left join la_land_identification u on u.work_id_fk = w.work_id  "
					+ "left join land_executives us on u.work_id_fk = us.work_id_fk  "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and us.executive_user_id_fk = ? ";
				arrSize++;
			}			
			
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getProjectsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = new ArrayList<LandAcquisition>();
		try {
			String qry = "select distinct project_id,project_name "
					+ "from `project` p "
					+ "LEFT JOIN work w on w.project_id_fk = p.project_id "
					+ "left join la_land_identification u on u.work_id_fk = w.work_id  "
					+ "left join land_executives us on u.work_id_fk = us.work_id_fk  "
					+ "where project_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and us.executive_user_id_fk = ? ";
				arrSize++;
			}			
			
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandsListForLAForm(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select la_category as type_of_land from `la_category`";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getSubCategorysListForLAForm(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select id,la_sub_category as sub_category_of_land,la_category_fk from `la_sub_category`";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getSubCategoryList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = new ArrayList<LandAcquisition>();
		try {
			String qry = "select id,la_sub_category as sub_category_of_land, la_category_fk from `la_sub_category` ls "
					+ "LEFT OUTER JOIN `la_category` lc ON la_category_fk = la_category ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " where la_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " where la_sub_category  = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = new ArrayList<LandAcquisition>();
		try {
			String sub_category_of_land = getSubCategoryLand(obj.getSub_category_of_land());
			obj.setSub_category_of_land(sub_category_of_land);
			String qry = "select id as category_id,la_category as type_of_land, ls.la_sub_category as sub_category_of_land from `la_category` lc "
					+ "LEFT OUTER JOIN `la_sub_category` ls ON la_category  = la_category_fk ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " where la_sub_category  = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " where la_category = ?";
				arrSize++;
			}
			qry = qry + " group by la_category";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getIssueCatogoriesList() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select category from issue_category";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateLandAcquisition(LandAcquisition obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE la_land_identification SET "
					+ "survey_number= :survey_number, village_id= :village_id, village= :village, taluka= :taluka, dy_slr= :dy_slr, sdo= :sdo, collector= :collector, proposal_submission_date_to_collector= :proposal_submission_date_to_collector,"
					+ "area_of_plot= :area_of_plot, jm_fee_amount = :jm_fee_amount, chainage_from= :chainage_from, chainage_to= :chainage_to, jm_fee_letter_received_date= :jm_fee_letter_received_date, jm_fee_paid_date= :jm_fee_paid_date, jm_start_date= :jm_start_date, jm_completion_date= :jm_completion_date, "
					+ "jm_sheet_date_to_sdo= :jm_sheet_date_to_sdo, jm_remarks= :jm_remarks, jm_approval= :jm_approval, issues= :issues, jm_fee_amount_units= :jm_fee_amount_units,"
					+ "la_land_status_fk= :la_land_status_fk, special_feature= :special_feature,private_land_process= :private_land_process,area_acquired= :area_acquired,"
					+ "category_fk= :category_fk,area_to_be_acquired= :area_to_be_acquired,remarks= :remarks,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP    "
					+ "where la_id= :la_id ";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				if(!StringUtils.isEmpty(obj.getJm_approval())) {
					if(obj.getCategory_fk().equalsIgnoreCase("Government")) {
						
						String govInsertQry = "INSERT INTO la_government_land_acquisition"
								+ "( la_id_fk, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, "
								+ "lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk,"//special_feature
								+ "amount_demanded_units, amount_paid_units)"
								+ "VALUES"
								+ "(:la_id, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
								+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk," // :special_feature, 
								+ "  :amount_demanded_units, :amount_paid_units)";
						
						String govUpdateQry = "UPDATE  la_government_land_acquisition SET "
								+ " proposal_submission= :proposal_submission, proposal_submission_status_fk= :proposal_submission_status_fk, valuation_date= :valuation_date, letter_for_payment= :letter_for_payment, amount_demanded= :amount_demanded, "
								+ "lfp_status_fk= :lfp_status_fk, approval_for_payment= :approval_for_payment, payment_date= :payment_date, amount_paid= :amount_paid, payment_status_fk= :payment_status_fk, possession_date= :possession_date, possession_status_fk= :possession_status_fk,"
								+ "amount_demanded_units= :amount_demanded_units, amount_paid_units= :amount_paid_units "
								+ "where  la_id_fk= :la_id";
						
						String table_name = "la_government_land_acquisition";
						String la_id = checkLAIdMethod(obj,table_name);
						obj.setType_of_land("Government");
						if(!StringUtils.isEmpty(la_id)) {
							obj.setLa_id(la_id);
							paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(govUpdateQry, paramSource);
						}else {
							paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(govInsertQry, paramSource);
						}	
							
					}else if(obj.getCategory_fk().equalsIgnoreCase("Forest")) {
						
						String forestInsertSubQry = "INSERT INTO la_forest_land_acquisition "
						 		+ "( la_id_fk, on_line_submission, submission_date_to_dycfo, submission_date_to_ccf_thane, submission_date_to_nodal_officer, "
						 		+ "submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur, valuation_by_dycfo, "
						 		+ "demanded_amount, payment_amount, approval_for_payment, payment_date, possession_date, possession_status_fk, payment_status_fk, survey_number,"//, special_feature
						 		+ " demanded_amount_units, payment_amount_units)"
						 		+ "VALUES"
						 		+ "( :la_id, :forest_online_submission, :forest_submission_date_to_dycfo, :forest_submission_date_to_ccf_thane, :forest_submission_date_to_nodal_officer, "
						 		+ ":forest_submission_date_to_revenue_secretary_mantralaya, :forest_submission_date_to_regional_office_nagpur, :forest_date_of_approval_by_regional_office_nagpur, :forest_valuation_by_dycfo,"
						 		+ ":forest_demanded_amount, :forest_payment_amount, :forest_approval_for_payment, :forest_payment_date, :forest_possession_date, :forest_possession_status_fk, :forest_payment_status_fk, :survey_number, "// :forest_special_feature,
						 		+ ":demanded_amount_units_forest, :payment_amount_units_forest)";
						
						String forestUpdateSubQry = "UPDATE la_forest_land_acquisition SET "
						 		+ " on_line_submission= :forest_online_submission, submission_date_to_dycfo= :forest_submission_date_to_dycfo, submission_date_to_ccf_thane= :forest_submission_date_to_ccf_thane, submission_date_to_nodal_officer= :forest_submission_date_to_nodal_officer, "
						 		+ "submission_date_to_revenue_secretary_mantralaya= :forest_submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur= :forest_submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur= :forest_date_of_approval_by_regional_office_nagpur, valuation_by_dycfo= :forest_valuation_by_dycfo, "
						 		+ "demanded_amount= :forest_demanded_amount, payment_amount= :forest_payment_amount, approval_for_payment= :forest_approval_for_payment, payment_date= :forest_payment_date, possession_date= :forest_possession_date, possession_status_fk= :forest_possession_status_fk, payment_status_fk=:forest_payment_status_fk,"//, special_feature= :forest_special_feature
						 		+ " demanded_amount_units= :demanded_amount_units_forest,payment_amount_units= :payment_amount_units_forest   "
						 		+ " where la_id_fk= :la_id ";
						
						String table_name = "la_forest_land_acquisition";
						String la_id = checkLAIdMethod(obj,table_name);
						obj.setType_of_land("Forest");
						if(!StringUtils.isEmpty(la_id)) {
							obj.setLa_id(la_id);
							 paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(forestUpdateSubQry, paramSource);
						}else {
							 paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);
						}	
						
					}else if(obj.getCategory_fk().equalsIgnoreCase("Railway")) {
						String railwayInsertSubQry = " INSERT INTO la_railway_land_acquisition"
						 		+ "(la_id_fk, online_submission, submission_date_to_DyCFO, "
						 		+ "submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`, submission_date_to_revenue_secretary_mantralaya, "
						 		+ "submission_date_to_regional_office_nagpur, date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO, demanded_amount, "
						 		+ "approval_for_payment, payment_date, payment_amount, payment_status, possession_date, possession_status, "
						 		+ "  demanded_amount_units, payment_amount_units)"
						 		+ "VALUES"
						 		+ "(:la_id, :railway_online_submission, :railway_submission_date_to_DyCFO, "
						 		+ ":railway_submission_date_to_CCF_Thane, :railway_submission_date_to_nodal_officer_CCF_Nagpur, :railway_submission_date_to_revenue_secretary_mantralaya, "
						 		+ ":railway_submission_date_to_regional_office_nagpur, :railway_date_of_approval_by_Rregional_Office_agpur, :railway_valuation_by_DyCFO, :railway_demanded_amount, "
						 		+ ":railway_approval_for_payment, :railway_payment_date, :railway_payment_amount, :railway_payment_status, :railway_possession_date, :railway_possession_status, "//, :railway_special_feature
						 		+ "  :demanded_amount_units, :payment_amount_units_railway)";
						
						String railwayUpdateSubQry = " UPDATE la_railway_land_acquisition SET "
						 		+ "survey_number= :survey_number, online_submission= :railway_online_submission, submission_date_to_DyCFO= :railway_submission_date_to_DyCFO, "
						 		+ "submission_date_to_CCF_Thane= :railway_submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`= :railway_submission_date_to_nodal_officer_CCF_Nagpur, submission_date_to_revenue_secretary_mantralaya= :railway_submission_date_to_revenue_secretary_mantralaya,  "
						 		+ "submission_date_to_regional_office_nagpur= :railway_submission_date_to_regional_office_nagpur,date_of_approval_by_Rregional_Office_agpur= :railway_date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO= :railway_valuation_by_DyCFO, demanded_amount= :railway_demanded_amount, "
						 		+ "approval_for_payment= :railway_approval_for_payment, payment_date= :railway_payment_date, payment_amount= :railway_payment_amount, payment_status= :railway_payment_status, possession_date= :railway_possession_date, possession_status= :railway_possession_status, "//special_feature= :railway_special_feature, 
						 		+ "demanded_amount_units= :demanded_amount_units, payment_amount_units= :payment_amount_units_railway   "
						 		+ "where la_id_fk= :la_id ";
				
						String table_name = "la_railway_land_acquisition";
						String la_id = checkLAIdMethod(obj,table_name);
						obj.setType_of_land("Railway");
						if(!StringUtils.isEmpty(la_id)) {
							obj.setLa_id(la_id);
							 paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(railwayUpdateSubQry, paramSource);
						}else {
							 paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(railwayInsertSubQry, paramSource);
						}	
							
					}else if(obj.getCategory_fk().equalsIgnoreCase("Private")) {
						
						if(!StringUtils.isEmpty(obj.getPrivate_land_process()) && obj.getPrivate_land_process().equals("Private DPM")) {
							String  privateLAInsertQry = "INSERT INTO la_private_land_acquisition "
							 		+ "(la_id_fk, name_of_the_owner, basic_rate, agriculture_tree_nos,agriculture_tree_rate, forest_tree_nos,"
							 		+ " forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, possession_status_fk, "
							 		+ " hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2,"
							 		+ " land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation,"
							 		+ " basic_rate_units, agriculture_tree_rate_units, forest_tree_rate_units)"
							 		+ "VALUES"
							 		+ "(:la_id, :name_of_the_owner, :basic_rate, :agriculture_tree_nos, :agriculture_tree_rate,:forest_tree_nos,"
							 		+ " :forest_tree_rate, :consent_from_owner, :legal_search_report, :date_of_registration, :date_of_possession, :private_possession_status_fk,"//:private_special_feature, 
							 		+ "  :hundred_percent_Solatium, :extra_25_percent, :total_rate_divide_m2,"
							 		+ " :land_compensation, :agriculture_tree_compensation, :forest_tree_compensation, :structure_compensation, :borewell_compensation, :total_compensation,"
							 		+ "  :basic_rate_units, :agriculture_tree_rate_units, :forest_tree_rate_units)";
							 
							String  privateLAUpdateQry = "UPDATE la_private_land_acquisition SET "
							 		+ " name_of_the_owner= :name_of_the_owner, basic_rate= :basic_rate,  agriculture_tree_nos= :agriculture_tree_nos, agriculture_tree_rate= :agriculture_tree_rate, forest_tree_nos= :forest_tree_nos,"
							 		+ "forest_tree_rate= :forest_tree_rate, consent_from_owner= :consent_from_owner, legal_search_report= :legal_search_report, date_of_registration= :date_of_registration, date_of_possession= :date_of_possession, possession_status_fk= :private_possession_status_fk, "//special_feature= :private_special_feature, 
							 		+ " hundred_percent_Solatium= :hundred_percent_Solatium, extra_25_percent= :extra_25_percent, total_rate_divide_m2= :total_rate_divide_m2,"
							 		+ "land_compensation= :land_compensation, agriculture_tree_compensation= :agriculture_tree_compensation, forest_tree_compensation= :forest_tree_compensation, structure_compensation= :structure_compensation, borewell_compensation= :borewell_compensation, "
							 		+ "total_compensation= :total_compensation,basic_rate_units= :basic_rate_units,agriculture_tree_rate_units= :agriculture_tree_rate_units,forest_tree_rate_units= :forest_tree_rate_units  "
							 		+ "where la_id_fk= :la_id";
							
								String table_name = "la_private_land_acquisition";
								String la_id = checkLAIdMethod(obj,table_name);
								obj.setType_of_land("Private");
								if(!StringUtils.isEmpty(la_id)) {
									obj.setLa_id(la_id);
									 paramSource = new BeanPropertySqlParameterSource(obj);
								    count = namedParamJdbcTemplate.update(privateLAUpdateQry, paramSource);
								}else {
									 paramSource = new BeanPropertySqlParameterSource(obj);
								    count = namedParamJdbcTemplate.update(privateLAInsertQry, paramSource);
								}
								
								String privateInsertSubQry = "INSERT INTO la_private_land_valuation "
								 		+ "(la_id_fk, forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, "
								 		+ "structure_survey, structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, "
								 		+ "borewell_valuation_status_fk, rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, "
								 		+ "date_of_approval_for_payment, payment_amount, payment_date, payment_amount_units)"
								 		+ "VALUES"
								 		+ "(:la_id, :forest_tree_survey, :forest_tree_valuation, :forest_tree_valuation_status_fk, :horticulture_tree_survey, :horticulture_tree_valuation,"
								 		+ " :structure_survey, :structure_valuation, :borewell_survey, :borewell_valuation, :horticulture_tree_valuation_status_fk, :structure_valuation_status_fk,"
								 		+ " :borewell_valuation_status_fk, :rfp_to_adtp_status_fk, :date_of_rfp_to_adtp, :date_of_rate_fixation_of_land, :sdo_demand_for_payment,"
								 		+ " :date_of_approval_for_payment, :payment_amount, :private_payment_date, :payment_amount_units)";
								 
								String privateUpdateSubQry = "UPDATE la_private_land_valuation SET "
								 		+ " forest_tree_survey=:forest_tree_survey, forest_tree_valuation= :forest_tree_valuation, forest_tree_valuation_status_fk= :forest_tree_valuation_status_fk, horticulture_tree_survey= :horticulture_tree_survey, horticulture_tree_valuation= :horticulture_tree_valuation, "
								 		+ "structure_survey= :structure_survey, structure_valuation= :structure_valuation, borewell_survey= :borewell_survey, borewell_valuation= :borewell_valuation, horticulture_tree_valuation_status_fk= :horticulture_tree_valuation_status_fk, structure_valuation_status_fk= :structure_valuation_status_fk, "
								 		+ "borewell_valuation_status_fk=:borewell_valuation_status_fk, rfp_to_adtp_status_fk= :rfp_to_adtp_status_fk, date_of_rfp_to_adtp= :date_of_rfp_to_adtp, date_of_rate_fixation_of_land= :date_of_rate_fixation_of_land, sdo_demand_for_payment= :sdo_demand_for_payment, "
								 		+ "date_of_approval_for_payment= :date_of_approval_for_payment, payment_amount= :payment_amount, payment_date= :private_payment_date,payment_amount_units= :payment_amount_units  "
								 		+ "where la_id_fk= :la_id";
								
									String table_name1 = "la_private_land_valuation";
									String la_id1 = checkLAIdMethod(obj,table_name1);
									obj.setType_of_land("Private");
									if(!StringUtils.isEmpty(la_id1)) {
										obj.setLa_id(la_id1);
										 paramSource = new BeanPropertySqlParameterSource(obj);
									    count = namedParamJdbcTemplate.update(privateUpdateSubQry, paramSource);
									}else {
										 paramSource = new BeanPropertySqlParameterSource(obj);
									    count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);
									}	
						}else{
							String  privateInsertQry = "INSERT INTO la_private_ira "
									+ " (la_id_fk, collector, submission_of_proposal_to_GM, approval_of_GM, draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp, date_of_uploading_of_gazette_notification_rp, "
									+ "publication_in_gazette_rp, date_of_proposal_to_DC_for_nomination, date_of_nomination_of_competenta_authority, draft_letter_to_con_for_approval_ca, date_of_approval_of_construction_ca, "
									+ "date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca, date_of_submission_of_draft_notification_to_CALA, approval_of_CALA_20a, draft_letter_to_con_for_approval_20a,"
									+ " date_of_approval_of_construction_20a, date_of_uploading_of_gazette_notification_20a, publication_in_gazette_20a, publication_in_2_local_news_papers_20a, pasting_of_notification_in_villages_20a,"
									+ " receipt_of_grievances, disposal_of_grievances, date_of_submission_of_draft_notification_to_CALA_20e, approval_of_CALA_20e, draft_letter_to_con_for_approval_20e, date_of_approval_of_construction_20e, "
									+ "date_of_uploading_of_gazette_notification_20e, publication_in_gazette_20e, publication_of_notice_in_2_local_news_papers_20e, date_of_submission_of_draft_notification_to_CALA_20f, approval_of_CALA_20f,"
									+ " draft_letter_to_con_for_approval_20f, date_of_approval_of_construction_20f, date_of_uploading_of_gazette_notification_20f, publication_in_gazette_20f, publication_of_notice_in_2_local_news_papers_20f)"
									+ "VALUES"
									+ "(:la_id, :private_ira_collector, :submission_of_proposal_to_GM, :approval_of_GM, :draft_letter_to_con_for_approval_rp, :date_of_approval_of_construction_rp, :date_of_uploading_of_gazette_notification_rp,"
									+ ":publication_in_gazette_rp, :date_of_proposal_to_DC_for_nomination, :date_of_nomination_of_competenta_authority, :draft_letter_to_con_for_approval_ca, :date_of_approval_of_construction_ca,"
									+ " :date_of_uploading_of_gazette_notification_ca, :publication_in_gazette_ca, :date_of_submission_of_draft_notification_to_CALA, :approval_of_CALA_20a, :draft_letter_to_con_for_approval_20a,"
									+ "	:date_of_approval_of_construction_20a, :date_of_uploading_of_gazette_notification_20a, :publication_in_gazette_20a, :publication_in_2_local_news_papers_20a, :pasting_of_notification_in_villages_20a,"
									+ " :receipt_of_grievances, :disposal_of_grievances, :date_of_submission_of_draft_notification_to_CALA_20e, :approval_of_CALA_20e, :draft_letter_to_con_for_approval_20e, :date_of_approval_of_construction_20e,"
									+ " :date_of_uploading_of_gazette_notification_20e, :publication_in_gazette_20e, :publication_of_notice_in_2_local_news_papers_20e, :date_of_submission_of_draft_notification_to_CALA_20f, :approval_of_CALA_20f, :draft_letter_to_con_for_approval_20f,"
									+ " :date_of_approval_of_construction_20f, :date_of_uploading_of_gazette_notification_20f, :publication_in_gazette_20f, :publication_of_notice_in_2_local_news_papers_20f)";
							
							String  privateUpdateQry = "UPDATE la_private_ira set "
									+ "collector= :private_ira_collector, submission_of_proposal_to_GM= :submission_of_proposal_to_GM, approval_of_GM= :approval_of_GM, draft_letter_to_con_for_approval_rp= :draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp= :date_of_approval_of_construction_rp,"
									+ " date_of_uploading_of_gazette_notification_rp= :date_of_uploading_of_gazette_notification_rp,publication_in_gazette_rp= :publication_in_gazette_rp, date_of_proposal_to_DC_for_nomination= :date_of_proposal_to_DC_for_nomination,"
									+ " date_of_nomination_of_competenta_authority= :date_of_nomination_of_competenta_authority, draft_letter_to_con_for_approval_ca= :draft_letter_to_con_for_approval_ca,date_of_approval_of_construction_ca= :date_of_approval_of_construction_ca, "
									+ "date_of_uploading_of_gazette_notification_ca= :date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca= :publication_in_gazette_ca, date_of_submission_of_draft_notification_to_CALA= :date_of_submission_of_draft_notification_to_CALA, "
									+ "approval_of_CALA_20a= :approval_of_CALA_20a, draft_letter_to_con_for_approval_20a= :draft_letter_to_con_for_approval_20a,"
									+ " date_of_approval_of_construction_20a= :date_of_approval_of_construction_20a, date_of_uploading_of_gazette_notification_20a= :date_of_uploading_of_gazette_notification_20a, publication_in_gazette_20a= :publication_in_gazette_20a, "
									+ "publication_in_2_local_news_papers_20a= :publication_in_2_local_news_papers_20a, pasting_of_notification_in_villages_20a= :pasting_of_notification_in_villages_20a,"
									+ " receipt_of_grievances= :receipt_of_grievances, disposal_of_grievances= :disposal_of_grievances, date_of_submission_of_draft_notification_to_CALA_20e= :date_of_submission_of_draft_notification_to_CALA_20e, approval_of_CALA_20e= :approval_of_CALA_20e,"
									+ " draft_letter_to_con_for_approval_20e= :draft_letter_to_con_for_approval_20e, date_of_approval_of_construction_20e= :date_of_approval_of_construction_20e, "
									+ "date_of_uploading_of_gazette_notification_20e= :date_of_uploading_of_gazette_notification_20e, publication_in_gazette_20e= :publication_in_gazette_20e, publication_of_notice_in_2_local_news_papers_20e= :publication_of_notice_in_2_local_news_papers_20e,"
									+ " date_of_submission_of_draft_notification_to_CALA_20f= :date_of_submission_of_draft_notification_to_CALA_20f, approval_of_CALA_20f= :approval_of_CALA_20f,"
									+ " draft_letter_to_con_for_approval_20f= :draft_letter_to_con_for_approval_20f, date_of_approval_of_construction_20f= :date_of_approval_of_construction_20f, date_of_uploading_of_gazette_notification_20f= :date_of_uploading_of_gazette_notification_20f, "
									+ "publication_in_gazette_20f= :publication_in_gazette_20f, publication_of_notice_in_2_local_news_papers_20f= :publication_of_notice_in_2_local_news_papers_20f "
									+ "where la_id_fk= :la_id";
								String table_name = "la_private_ira";
								String la_id = checkLAIdMethod(obj,table_name);
								if(!StringUtils.isEmpty(la_id)) {
									obj.setLa_id(la_id);
									 paramSource = new BeanPropertySqlParameterSource(obj);
								    count = namedParamJdbcTemplate.update(privateUpdateQry, paramSource);
								}else {
									 paramSource = new BeanPropertySqlParameterSource(obj);
								    count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);
								}
							}
						}
					
					String messageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
							+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";	
					String executives=getLandExecutives(obj.getWork_id_fk());
					String [] SplitStr=executives.split(",");
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
					for(int i=0;i<SplitStr.length;i++)
					{
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(SplitStr[i]);
						String JMStatus="";
						if(obj.getJm_approval().compareTo("Done")==0)
						{
							JMStatus="Approved";
						}
						else
						{
							JMStatus="Rejected";
						}
						msgObj.setMessage("A new Land Acquisition against "+obj.getWork_id_fk()+" has been JM "+JMStatus);
						msgObj.setRedirect_url("/get-land-acquisition/"+obj.getLa_id());
						msgObj.setMessage_type("Land Acquisition");	
						BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
						template.update(messageQry, paramSource1);						
					}
					if(!StringUtils.isEmpty(obj.getPossession_status_fk()) && obj.getPossession_status_fk().compareTo("Done")==0)
					{
						for(int i=0;i<SplitStr.length;i++)
						{
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(SplitStr[i]);
							msgObj.setMessage("A Land Acquisition against "+obj.getWork_id_fk()+" is Acquired.");
							msgObj.setRedirect_url("/get-land-acquisition/"+obj.getLa_id());
							msgObj.setMessage_type("Land Acquisition");	
							BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
							template.update(messageQry, paramSource1);						
						}						
					}					
					
				}
				if(flag) {
					
					String deleteFilesQry = "delete from la_files  where la_id_fk = :la_id";		 
					LandAcquisition fileObj = new LandAcquisition();
					fileObj.setLa_id(obj.getLa_id());
					paramSource = new BeanPropertySqlParameterSource(obj);	
					namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
				
					String insert_qry =  "INSERT into  la_files ( la_id_fk,la_file_type_fk,name,attachment) VALUES (:la_id,:la_file_type_fk,:name,:attachment)";
					
					int arraySize = 0;
					if(!StringUtils.isEmpty(obj.getLaFileNames()) && obj.getLaFileNames().length > 0 ) {
						obj.setLaFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getLaFileNames()));
						if(arraySize < obj.getLaFileNames().length) {
							arraySize = obj.getLaFileNames().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getLa_file_typess()) && obj.getLa_file_typess().length > 0 ) {
						obj.setLa_file_typess(CommonMethods.replaceEmptyByNullInSringArray(obj.getLa_file_typess()));
						if(arraySize < obj.getLa_file_typess().length) {
							arraySize = obj.getLa_file_typess().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getLaDocumentNames()) && obj.getLaDocumentNames().length > 0 ) {
						obj.setLaDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getLaDocumentNames()));
						if(arraySize < obj.getLaDocumentNames().length) {
							arraySize = obj.getLaDocumentNames().length;
						}
					}
					/*for (int i = 0; i < arraySize; i++) {
						fileObj = new LandAcquisition();
						fileObj.setLa_id(obj.getLa_id());
						fileObj.setAttachment(obj.getLaFileNames()[i]);
						paramSource = new BeanPropertySqlParameterSource(fileObj);	
						namedParamJdbcTemplate.update(insert_qry, paramSource);
					}*/
					
						for (int f = 0;  f < arraySize; f++) {
							if ((!StringUtils.isEmpty(obj.getLaFiles()) && null != obj.getLaFiles()[f] && !obj.getLaFiles()[f].isEmpty() ) || 
									(!StringUtils.isEmpty(obj.getLaDocumentFileNames()) && !StringUtils.isEmpty(obj.getLaDocumentFileNames()[f]) )){
								String saveDirectory = CommonConstants.LAND_ACQUISITION_FILE_SAVING_PATH ;
								String fileName = null;
								MultipartFile multipartFile = obj.getLaFiles()[f];
								
								if(null != multipartFile && !multipartFile.isEmpty()) {
									fileName = multipartFile.getOriginalFilename();
								}else {
									fileName = obj.getLaDocumentFileNames()[f];
								}
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
								obj.setAttachment(fileName);
							
								fileObj = new LandAcquisition();
								fileObj.setLa_id(obj.getLa_id());
								fileObj.setLa_file_type_fk(obj.getLa_file_typess()[f]);
								fileObj.setName(obj.getLaDocumentNames()[f]);
								fileObj.setAttachment(fileName);
								paramSource = new BeanPropertySqlParameterSource(fileObj);	
								namedParamJdbcTemplate.update(insert_qry, paramSource);
							}
						}                            
					
					if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
						if(!StringUtils.isEmpty(obj.getIssue_category_id())){
							String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CURDATE())";				
							jdbcTemplate.update(new PreparedStatementCreator() {
								@Override
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps = connection.prepareStatement(issuesQry);
									int i = 1;
									ps.setString(i++, obj.getIssue_description());
									ps.setString(i++, obj.getIssue_description());
									ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
									ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
									ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
									ps.setString(i++, "Raised");
									ps.setString(i++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
									return ps;
								}
							});
						}
					}
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Land Acquisition");
				formHistory.setForm_name("Update Land Acquisition");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Land Acquisition "+obj.getLa_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				//formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
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
	public List<LandAcquisition> getLandAcquisitionStatusList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT la_land_status_fk from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where li.la_land_status_fk is not null and li.la_land_status_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY la_land_status_fk ";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getUnitsList() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLaFileType() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select la_file_type from la_file_type ";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	private int checkWorkinLA(String work_id,String userId) throws Exception {
		int Count=0;
		try {
			String qry = "SELECT count(*) AS count FROM land_executives WHERE work_id_fk=? and executive_user_id_fk=?";
			Count = (int) jdbcTemplate.queryForObject(qry, new Object[] { work_id,userId }, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Count;
	}	

	@Override
	public String[] uploadLAData(List<LandAcquisition> lasList, LandAcquisition la) throws Exception {
		boolean flag = false;
		String errMsg = null;
		int count = 0,row =2,sheet = 2,subRow = 3;
		int sheet1 =2,sheet2=2,sheet3=2,sheet4=2,sheet5=2,sheet6=2;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO la_land_identification"
					+ "( la_id, work_id_fk, survey_number, village_id, la_sub_category_fk, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector,"
					+ "area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, jm_start_date, jm_completion_date, "
					+ "jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues, jm_fee_amount_units,special_feature,"
					+ "area_acquired,private_land_process,la_land_status_fk,category_fk,area_to_be_acquired,remarks)"
					+ "VALUES"
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :la_sub_category_fk, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :issues, :jm_fee_amount_units , :special_feature, :area_acquired, :private_land_process, "
					+ ":la_land_status_fk, :category_fk, :area_to_be_acquired, :remarks)";
			
			String updatetQry = "UPDATE la_land_identification SET "
					+ "survey_number= :survey_number, village_id= :village_id,la_sub_category_fk= :la_sub_category_fk, village= :village, taluka= :taluka, dy_slr= :dy_slr, sdo= :sdo, collector= :collector, proposal_submission_date_to_collector= :proposal_submission_date_to_collector,"
					+ "area_of_plot= :area_of_plot, jm_fee_amount = :jm_fee_amount, chainage_from= :chainage_from, chainage_to= :chainage_to, jm_fee_letter_received_date= :jm_fee_letter_received_date, jm_fee_paid_date= :jm_fee_paid_date, jm_start_date= :jm_start_date, jm_completion_date= :jm_completion_date, "
					+ "jm_sheet_date_to_sdo= :jm_sheet_date_to_sdo, jm_remarks= :jm_remarks, jm_approval= :jm_approval, issues= :issues,  jm_fee_amount_units= :jm_fee_amount_units,"
					+ "la_land_status_fk= :la_land_status_fk, special_feature= :special_feature,private_land_process= :private_land_process,area_acquired= :area_acquired,"
					+ "category_fk= :category_fk,area_to_be_acquired= :area_to_be_acquired,remarks= :remarks,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP    "
					+ "where la_id= :la_id ";
		//	int rNo = 0;
			for (LandAcquisition obj : lasList) {
				String [] codes = {"Private", "Government", "Railway", "Forest"};
				if(Arrays.asList(codes).contains(obj.getCategory_fk())) {
				String table_name = "la_land_identification";
				String la_id = checkLAIdMethod(obj,table_name);
				String sub_category_no = getSubCategoryNo(obj);
				obj.setLa_sub_category_fk(sub_category_no);
				row++;sheet = 1;
				if(!StringUtils.isEmpty(la_id)) {
					obj.setLa_id(la_id);
					//System.out.println(rNo++);
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						if(checkWorkinLA(obj.getWork_id_fk(),obj.getCreated_by_user_id_fk())>0)
						{
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(updatetQry, paramSource);						
						}
					}					
					else
					{
						SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					    count = namedParamJdbcTemplate.update(updatetQry, paramSource);
					}
				}else {
					//System.out.println(rNo++);
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						if(checkWorkinLA(obj.getWork_id_fk(),obj.getCreated_by_user_id_fk())>0)
						{
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
						    count = namedParamJdbcTemplate.update(insertQry, paramSource);						
						}
					}					
					else
					{
						SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					    count = namedParamJdbcTemplate.update(insertQry, paramSource);
					}
				}
				if(!StringUtils.isEmpty(obj.getPrivateIRAList())) {
					subRow = sheet1;
					String  privateInsertQry = "INSERT INTO la_private_ira "
							+ " (la_id_fk, collector, submission_of_proposal_to_GM, approval_of_GM, draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp, date_of_uploading_of_gazette_notification_rp, "
							+ "publication_in_gazette_rp, date_of_proposal_to_DC_for_nomination, date_of_nomination_of_competenta_authority, draft_letter_to_con_for_approval_ca, date_of_approval_of_construction_ca, "
							+ "date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca, date_of_submission_of_draft_notification_to_CALA, approval_of_CALA_20a, draft_letter_to_con_for_approval_20a,"
							+ " date_of_approval_of_construction_20a, date_of_uploading_of_gazette_notification_20a, publication_in_gazette_20a, publication_in_2_local_news_papers_20a, pasting_of_notification_in_villages_20a,"
							+ " receipt_of_grievances, disposal_of_grievances, date_of_submission_of_draft_notification_to_CALA_20e, approval_of_CALA_20e, draft_letter_to_con_for_approval_20e, date_of_approval_of_construction_20e, "
							+ "date_of_uploading_of_gazette_notification_20e, publication_in_gazette_20e, publication_of_notice_in_2_local_news_papers_20e, date_of_submission_of_draft_notification_to_CALA_20f, approval_of_CALA_20f,"
							+ " draft_letter_to_con_for_approval_20f, date_of_approval_of_construction_20f, date_of_uploading_of_gazette_notification_20f, publication_in_gazette_20f, publication_of_notice_in_2_local_news_papers_20f)"
							+ "VALUES"
							+ "(:la_id, :private_ira_collector, :submission_of_proposal_to_GM, :approval_of_GM, :draft_letter_to_con_for_approval_rp, :date_of_approval_of_construction_rp, :date_of_uploading_of_gazette_notification_rp,"
							+ ":publication_in_gazette_rp, :date_of_proposal_to_DC_for_nomination, :date_of_nomination_of_competenta_authority, :draft_letter_to_con_for_approval_ca, :date_of_approval_of_construction_ca,"
							+ " :date_of_uploading_of_gazette_notification_ca, :publication_in_gazette_ca, :date_of_submission_of_draft_notification_to_CALA, :approval_of_CALA_20a, :draft_letter_to_con_for_approval_20a,"
							+ "	:date_of_approval_of_construction_20a, :date_of_uploading_of_gazette_notification_20a, :publication_in_gazette_20a, :publication_in_2_local_news_papers_20a, :pasting_of_notification_in_villages_20a,"
							+ " :receipt_of_grievances, :disposal_of_grievances, :date_of_submission_of_draft_notification_to_CALA_20e, :approval_of_CALA_20e, :draft_letter_to_con_for_approval_20e, :date_of_approval_of_construction_20e,"
							+ " :date_of_uploading_of_gazette_notification_20e, :publication_in_gazette_20e, :publication_of_notice_in_2_local_news_papers_20e, :date_of_submission_of_draft_notification_to_CALA_20f, :approval_of_CALA_20f, :draft_letter_to_con_for_approval_20f,"
							+ " :date_of_approval_of_construction_20f, :date_of_uploading_of_gazette_notification_20f, :publication_in_gazette_20f, :publication_of_notice_in_2_local_news_papers_20f)";
					
					String  privateUpdateQry = "UPDATE la_private_ira set "
							+ "collector= :private_ira_collector, submission_of_proposal_to_GM= :submission_of_proposal_to_GM, approval_of_GM= :approval_of_GM, draft_letter_to_con_for_approval_rp= :draft_letter_to_con_for_approval_rp, date_of_approval_of_construction_rp= :date_of_approval_of_construction_rp,"
							+ " date_of_uploading_of_gazette_notification_rp= :date_of_uploading_of_gazette_notification_rp,publication_in_gazette_rp= :publication_in_gazette_rp, date_of_proposal_to_DC_for_nomination= :date_of_proposal_to_DC_for_nomination,"
							+ " date_of_nomination_of_competenta_authority= :date_of_nomination_of_competenta_authority, draft_letter_to_con_for_approval_ca= :draft_letter_to_con_for_approval_ca,date_of_approval_of_construction_ca= :date_of_approval_of_construction_ca, "
							+ "date_of_uploading_of_gazette_notification_ca= :date_of_uploading_of_gazette_notification_ca, publication_in_gazette_ca= :publication_in_gazette_ca, date_of_submission_of_draft_notification_to_CALA= :date_of_submission_of_draft_notification_to_CALA, "
							+ "approval_of_CALA_20a= :approval_of_CALA_20a, draft_letter_to_con_for_approval_20a= :draft_letter_to_con_for_approval_20a,"
							+ " date_of_approval_of_construction_20a= :date_of_approval_of_construction_20a, date_of_uploading_of_gazette_notification_20a= :date_of_uploading_of_gazette_notification_20a, publication_in_gazette_20a= :publication_in_gazette_20a, "
							+ "publication_in_2_local_news_papers_20a= :publication_in_2_local_news_papers_20a, pasting_of_notification_in_villages_20a= :pasting_of_notification_in_villages_20a,"
							+ " receipt_of_grievances= :receipt_of_grievances, disposal_of_grievances= :disposal_of_grievances, date_of_submission_of_draft_notification_to_CALA_20e= :date_of_submission_of_draft_notification_to_CALA_20e, approval_of_CALA_20e= :approval_of_CALA_20e,"
							+ " draft_letter_to_con_for_approval_20e= :draft_letter_to_con_for_approval_20e, date_of_approval_of_construction_20e= :date_of_approval_of_construction_20e, "
							+ "date_of_uploading_of_gazette_notification_20e= :date_of_uploading_of_gazette_notification_20e, publication_in_gazette_20e= :publication_in_gazette_20e, publication_of_notice_in_2_local_news_papers_20e= :publication_of_notice_in_2_local_news_papers_20e,"
							+ " date_of_submission_of_draft_notification_to_CALA_20f= :date_of_submission_of_draft_notification_to_CALA_20f, approval_of_CALA_20f= :approval_of_CALA_20f,"
							+ " draft_letter_to_con_for_approval_20f= :draft_letter_to_con_for_approval_20f, date_of_approval_of_construction_20f= :date_of_approval_of_construction_20f, date_of_uploading_of_gazette_notification_20f= :date_of_uploading_of_gazette_notification_20f, "
							+ "publication_in_gazette_20f= :publication_in_gazette_20f, publication_of_notice_in_2_local_news_papers_20f= :publication_of_notice_in_2_local_news_papers_20f "
							+ "where la_id_fk= :la_id";
					
					String upLandQry = "UPDATE la_land_identification set private_land_process = 'Private IRA',modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP where la_id= :la_id";
					
					for (LandAcquisition obj1 : obj.getPrivateIRAList()) {
						String table_name1 = "la_private_ira";
						String la_id1 = checkLAIdMethod(obj1,table_name1);
						sheet = 2;subRow++;
						if(!StringUtils.isEmpty(la_id1)) {
							obj.setLa_id(la_id1);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj1);
						    count = namedParamJdbcTemplate.update(privateUpdateQry, paramSource);
						    
						    paramSource = new BeanPropertySqlParameterSource(obj1);
						    namedParamJdbcTemplate.update(upLandQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj1);
						    count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);
						    
						    paramSource = new BeanPropertySqlParameterSource(obj1);
						    namedParamJdbcTemplate.update(upLandQry, paramSource);
						}
					}
					sheet1 = sheet1 + obj.getPrivateIRAList().size();
				}
				if(!StringUtils.isEmpty(obj.getPrivateLAList())) {
					subRow = sheet2;
					String  privateLAInsertQry = "INSERT INTO la_private_land_acquisition "
					 		+ "(la_id_fk, name_of_the_owner, basic_rate, agriculture_tree_nos,agriculture_tree_rate, forest_tree_nos,"
					 		+ " forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, possession_status_fk, "
					 		+ " hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2,"
					 		+ " land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation,"
					 		+ " basic_rate_units, agriculture_tree_rate_units, forest_tree_rate_units)"
					 		+ "VALUES"
					 		+ "(:la_id, :name_of_the_owner, :basic_rate,  :agriculture_tree_nos, :agriculture_tree_rate, :forest_tree_nos,"
					 		+ " :forest_tree_rate, :consent_from_owner, :legal_search_report, :date_of_registration, :date_of_possession, :private_possession_status_fk, "//:private_special_feature, 
					 		+ "  :hundred_percent_Solatium, :extra_25_percent, :total_rate_divide_m2,"
					 		+ " :land_compensation, :agriculture_tree_compensation, :forest_tree_compensation, :structure_compensation, :borewell_compensation, :total_compensation,"
					 		+ "  :basic_rate_units, :agriculture_tree_rate_units, :forest_tree_rate_units)";
					 
					String  privateLAUpdateQry = "UPDATE la_private_land_acquisition SET "
					 		+ " name_of_the_owner= :name_of_the_owner, basic_rate= :basic_rate,  agriculture_tree_nos= :agriculture_tree_nos, agriculture_tree_rate= :agriculture_tree_rate, forest_tree_nos= :forest_tree_nos,"
					 		+ "forest_tree_rate= :forest_tree_rate, consent_from_owner= :consent_from_owner, legal_search_report= :legal_search_report, date_of_registration= :date_of_registration, date_of_possession= :date_of_possession, possession_status_fk= :private_possession_status_fk, "//special_feature= :private_special_feature, 
					 		+ " hundred_percent_Solatium= :hundred_percent_Solatium, extra_25_percent= :extra_25_percent, total_rate_divide_m2= :total_rate_divide_m2,"
					 		+ "land_compensation= :land_compensation, agriculture_tree_compensation= :agriculture_tree_compensation, forest_tree_compensation= :forest_tree_compensation, structure_compensation= :structure_compensation, borewell_compensation= :borewell_compensation, "
					 		+ "total_compensation= :total_compensation,basic_rate_units= :basic_rate_units,agriculture_tree_rate_units= :agriculture_tree_rate_units,forest_tree_rate_units= :forest_tree_rate_units  "
					 		+ "where la_id_fk= :la_id";
					
					String upLandQry = "UPDATE la_land_identification set private_land_process = 'Private DPM',modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP where la_id= :la_id";
					for (LandAcquisition obj2 : obj.getPrivateLAList()) {
						sheet = 4;subRow++;
						String table_name2 = "la_private_land_acquisition";
						String la_id2 = checkLAIdMethod(obj2,table_name2);
						obj.setType_of_land("Private");
						if(!StringUtils.isEmpty(la_id2)) {
							obj.setLa_id(la_id2);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj2);
						    count = namedParamJdbcTemplate.update(privateLAUpdateQry, paramSource);
						    
						    paramSource = new BeanPropertySqlParameterSource(obj2);
						    namedParamJdbcTemplate.update(upLandQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj2);
						    count = namedParamJdbcTemplate.update(privateLAInsertQry, paramSource);
						    
						    paramSource = new BeanPropertySqlParameterSource(obj2);
						    namedParamJdbcTemplate.update(upLandQry, paramSource);
						}
					}
					sheet2 = sheet2 + obj.getPrivateLAList().size();
				}
				if(!StringUtils.isEmpty(obj.getPrivateLVList())) {
					subRow = sheet3;
					String privateInsertSubQry = "INSERT INTO la_private_land_valuation "
					 		+ "(la_id_fk, forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, "
					 		+ "structure_survey, structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, "
					 		+ "borewell_valuation_status_fk, rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, "
					 		+ "date_of_approval_for_payment, payment_amount, payment_date, payment_amount_units)"
					 		+ "VALUES"
					 		+ "(:la_id, :forest_tree_survey, :forest_tree_valuation, :forest_tree_valuation_status_fk, :horticulture_tree_survey, :horticulture_tree_valuation,"
					 		+ " :structure_survey, :structure_valuation, :borewell_survey, :borewell_valuation, :horticulture_tree_valuation_status_fk, :structure_valuation_status_fk,"
					 		+ " :borewell_valuation_status_fk, :rfp_to_adtp_status_fk, :date_of_rfp_to_adtp, :date_of_rate_fixation_of_land, :sdo_demand_for_payment,"
					 		+ " :date_of_approval_for_payment, :payment_amount, :private_payment_date, :payment_amount_units)";
					 
					String privateUpdateSubQry = "UPDATE la_private_land_valuation SET "
					 		+ " forest_tree_survey=:forest_tree_survey, forest_tree_valuation= :forest_tree_valuation, forest_tree_valuation_status_fk= :forest_tree_valuation_status_fk, horticulture_tree_survey= :horticulture_tree_survey, horticulture_tree_valuation= :horticulture_tree_valuation, "
					 		+ "structure_survey= :structure_survey, structure_valuation= :structure_valuation, borewell_survey= :borewell_survey, borewell_valuation= :borewell_valuation, horticulture_tree_valuation_status_fk= :horticulture_tree_valuation_status_fk, structure_valuation_status_fk= :structure_valuation_status_fk, "
					 		+ "borewell_valuation_status_fk=:borewell_valuation_status_fk, rfp_to_adtp_status_fk= :rfp_to_adtp_status_fk, date_of_rfp_to_adtp= :date_of_rfp_to_adtp, date_of_rate_fixation_of_land= :date_of_rate_fixation_of_land, sdo_demand_for_payment= :sdo_demand_for_payment, "
					 		+ "date_of_approval_for_payment= :date_of_approval_for_payment, payment_amount= :payment_amount, payment_date= :private_payment_date,payment_amount_units= :payment_amount_units  "
					 		+ "where la_id_fk= :la_id";
					
					for (LandAcquisition obj3 : obj.getPrivateLVList()) {
						sheet = 3;subRow++;
						String table_name3 = "la_private_land_valuation";
						String la_id3 = checkLAIdMethod(obj3,table_name3);
						obj.setType_of_land("Private");
						if(!StringUtils.isEmpty(la_id3)) {
							obj.setLa_id(la_id3);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj3);
						    count = namedParamJdbcTemplate.update(privateUpdateSubQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj3);
						    count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);
						}
					}
					sheet3 = sheet3 + obj.getPrivateLVList().size();
				}
				if(!StringUtils.isEmpty(obj.getGovList())) {
					subRow = sheet4;
					String govInsertQry = "INSERT INTO la_government_land_acquisition"
							+ "( la_id_fk, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, "
							+ "lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk,"//special_feature
							+ "amount_demanded_units, amount_paid_units)"
							+ "VALUES"
							+ "(:la_id, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
							+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk," // :special_feature, 
							+ "  :amount_demanded_units, :amount_paid_units)";
					
					String govUpdateQry = "UPDATE  la_government_land_acquisition SET "
							+ " proposal_submission= :proposal_submission, proposal_submission_status_fk= :proposal_submission_status_fk, valuation_date= :valuation_date, letter_for_payment= :letter_for_payment, amount_demanded= :amount_demanded, "
							+ "lfp_status_fk= :lfp_status_fk, approval_for_payment= :approval_for_payment, payment_date= :payment_date, amount_paid= :amount_paid, payment_status_fk= :payment_status_fk, possession_date= :possession_date, possession_status_fk= :possession_status_fk,"
							+ "amount_demanded_units= :amount_demanded_units, amount_paid_units= :amount_paid_units "
							+ "where  la_id_fk= :la_id";
					for (LandAcquisition obj4 : obj.getGovList()) {
						sheet = 5;subRow++;
						String table_name4 = "la_government_land_acquisition";
						String la_id4 = checkLAIdMethod(obj4,table_name4);
						obj.setType_of_land("Government");
						if(!StringUtils.isEmpty(la_id4)) {
							obj.setLa_id(la_id4);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj4);
						    count = namedParamJdbcTemplate.update(govUpdateQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj4);
						    count = namedParamJdbcTemplate.update(govInsertQry, paramSource);
						} 
					}
					sheet4 = sheet4 + obj.getGovList().size();
				}
				if(!StringUtils.isEmpty(obj.getForestList())) {
					subRow = sheet5;
					String forestInsertSubQry = "INSERT INTO la_forest_land_acquisition "
					 		+ "( la_id_fk, on_line_submission, submission_date_to_dycfo, submission_date_to_ccf_thane, submission_date_to_nodal_officer, "
					 		+ "submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur, valuation_by_dycfo, "
					 		+ "demanded_amount, payment_amount, approval_for_payment, payment_date, possession_date, possession_status_fk, payment_status_fk, survey_number,"//, special_feature
					 		+ " demanded_amount_units, payment_amount_units)"
					 		+ "VALUES"
					 		+ "( :la_id, :forest_online_submission, :forest_submission_date_to_dycfo, :forest_submission_date_to_ccf_thane, :forest_submission_date_to_nodal_officer, "
					 		+ ":forest_submission_date_to_revenue_secretary_mantralaya, :forest_submission_date_to_regional_office_nagpur, :forest_date_of_approval_by_regional_office_nagpur, :forest_valuation_by_dycfo,"
					 		+ ":forest_demanded_amount, :forest_payment_amount, :forest_approval_for_payment, :forest_payment_date, :forest_possession_date, :forest_possession_status_fk, :forest_payment_status_fk, :survey_number, "// :forest_special_feature,
					 		+ ":demanded_amount_units_forest, :payment_amount_units_forest)";
					
					String forestUpdateSubQry = "UPDATE la_forest_land_acquisition SET "
					 		+ " on_line_submission= :forest_online_submission, submission_date_to_dycfo= :forest_submission_date_to_dycfo, submission_date_to_ccf_thane= :forest_submission_date_to_ccf_thane, submission_date_to_nodal_officer= :forest_submission_date_to_nodal_officer, "
					 		+ "submission_date_to_revenue_secretary_mantralaya= :forest_submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur= :forest_submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur= :forest_date_of_approval_by_regional_office_nagpur, valuation_by_dycfo= :forest_valuation_by_dycfo, "
					 		+ "demanded_amount= :forest_demanded_amount, payment_amount= :forest_payment_amount, approval_for_payment= :forest_approval_for_payment, payment_date= :forest_payment_date, possession_date= :forest_possession_date, possession_status_fk= :forest_possession_status_fk, payment_status_fk=:forest_payment_status_fk,"//, special_feature= :forest_special_feature
					 		+ " demanded_amount_units= :demanded_amount_units_forest,payment_amount_units= :payment_amount_units_forest   "
					 		+ " where la_id_fk= :la_id ";

					for (LandAcquisition obj5 : obj.getForestList()) {
						sheet = 6;subRow++;
						String table_name5 = "la_forest_land_acquisition";
						String la_id5 = checkLAIdMethod(obj5,table_name5);
						obj.setType_of_land("Forest");
						if(!StringUtils.isEmpty(la_id5)) {
							obj.setLa_id(la_id5);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj5);
						    count = namedParamJdbcTemplate.update(forestUpdateSubQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj5);
						    count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);
						}
					}
					sheet5 = sheet5 + obj.getForestList().size();
				}
				if(!StringUtils.isEmpty(obj.getRailwayList())) {
					subRow = sheet6;
					String railwayInsertSubQry = " INSERT INTO la_railway_land_acquisition"
					 		+ "(la_id_fk, online_submission, submission_date_to_DyCFO, "
					 		+ "submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`, submission_date_to_revenue_secretary_mantralaya, "
					 		+ "submission_date_to_regional_office_nagpur, date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO, demanded_amount, "
					 		+ "approval_for_payment, payment_date, payment_amount, payment_status, possession_date, possession_status, "
					 		+ "  demanded_amount_units, payment_amount_units)"
					 		+ "VALUES"
					 		+ "(:la_id, :railway_online_submission, :railway_submission_date_to_DyCFO, "
					 		+ ":railway_submission_date_to_CCF_Thane, :railway_submission_date_to_nodal_officer_CCF_Nagpur, :railway_submission_date_to_revenue_secretary_mantralaya, "
					 		+ ":railway_submission_date_to_regional_office_nagpur, :railway_date_of_approval_by_Rregional_Office_agpur, :railway_valuation_by_DyCFO, :railway_demanded_amount, "
					 		+ ":railway_approval_for_payment, :railway_payment_date, :railway_payment_amount, :railway_payment_status, :railway_possession_date, :railway_possession_status, "//, :railway_special_feature
					 		+ "  :demanded_amount_units, :payment_amount_units_railway)";
					
					String railwayUpdateSubQry = " UPDATE la_railway_land_acquisition SET "
					 		+ "survey_number= :survey_number, online_submission= :railway_online_submission, submission_date_to_DyCFO= :railway_submission_date_to_DyCFO, "
					 		+ "submission_date_to_CCF_Thane= :railway_submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`= :railway_submission_date_to_nodal_officer_CCF_Nagpur, submission_date_to_revenue_secretary_mantralaya= :railway_submission_date_to_revenue_secretary_mantralaya,  "
					 		+ "submission_date_to_regional_office_nagpur= :railway_submission_date_to_regional_office_nagpur,date_of_approval_by_Rregional_Office_agpur= :railway_date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO= :railway_valuation_by_DyCFO, demanded_amount= :railway_demanded_amount, "
					 		+ "approval_for_payment= :railway_approval_for_payment, payment_date= :railway_payment_date, payment_amount= :railway_payment_amount, payment_status= :railway_payment_status, possession_date= :railway_possession_date, possession_status= :railway_possession_status, "//special_feature= :railway_special_feature, 
					 		+ "demanded_amount_units= :demanded_amount_units, payment_amount_units= :payment_amount_units_railway   "
					 		+ "where la_id_fk= :la_id ";
					for (LandAcquisition obj6 : obj.getRailwayList()) {
						sheet = 7;subRow++;
						String table_name6 = "la_railway_land_acquisition";
						String la_id6 = checkLAIdMethod(obj6,table_name6);
						obj.setType_of_land("Railway");
						if(!StringUtils.isEmpty(la_id6)) {
							obj.setLa_id(la_id6);
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj6);
						    count = namedParamJdbcTemplate.update(railwayUpdateSubQry, paramSource);
						}else {
							SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj6);
						    count = namedParamJdbcTemplate.update(railwayInsertSubQry, paramSource);
						}
					}
					sheet6 = sheet6 + obj.getRailwayList().size();
				}
				count = lasList.size();
			   }
			}
		   
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

	// checking if given id is alredy in table or not 
	
	private String getSubCategoryNo(LandAcquisition obj) {
		LandAcquisition dObj = null;
		String subCateroryNo = null;
		try {
			String qry ="select id from la_sub_category where la_sub_category = ? and la_category_fk = ? " ;
			dObj = (LandAcquisition)jdbcTemplate.queryForObject(qry, new Object[] {obj.getLa_sub_category_fk(),obj.getCategory_fk()}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			subCateroryNo = dObj.getId();
			return subCateroryNo;
		}catch(Exception e){ 
			subCateroryNo = null;
			return subCateroryNo;
		}
	}

	private String checkLAIdMethod(LandAcquisition obj, String table_name) {
		LandAcquisition dObj = null;
		String laId = null;
		String column_name = "la_id";
		try {
			if(!(table_name.equals("la_land_identification"))){
				column_name = "la_id_fk";
			}
			String qry ="select "+column_name+" as la_id from "+table_name+" where "+column_name+" = ? " ;
			dObj = (LandAcquisition)jdbcTemplate.queryForObject(qry, new Object[] {obj.getLa_id()}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			laId = dObj.getLa_id();
			return laId;
		}
		catch(Exception e){ 
			laId = null;
			return laId;
		}
	}

	@Override
	public List<LandAcquisition> getLaLandStatus() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select la_land_status from la_land_status";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select la_id,li.remarks,cast(li.area_to_be_acquired as CHAR) as area_to_be_acquired,IFNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk, li.work_id_fk,w.project_id_fk,p.project_name,w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo, li.collector, DATE_FORMAT(proposal_submission_date_to_collector,'%d-%m-%Y') AS proposal_submission_date_to_collector, cast(area_of_plot as CHAR) as area_of_plot, jm_fee_amount,jm_fee_amount_units, " + 
					"li.special_feature,cast(li.area_acquired as CHAR) as area_acquired,li.private_land_process,cast(chainage_from as CHAR) as chainage_from,cast(chainage_to as CHAR) as chainage_to, DATE_FORMAT(jm_fee_letter_received_date,'%d-%m-%Y') AS jm_fee_letter_received_date,DATE_FORMAT(jm_fee_paid_date,'%d-%m-%Y') AS jm_fee_paid_date,DATE_FORMAT(jm_start_date,'%d-%m-%Y') AS  jm_start_date,DATE_FORMAT(jm_completion_date,'%d-%m-%Y') AS jm_completion_date, DATE_FORMAT(jm_sheet_date_to_sdo,'%d-%m-%Y') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues " + 
					" from la_land_identification li " +
					"left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ?";
				arrSize++;
			}	
 			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
 				qry = qry + " and le.executive_user_id_fk = ? ";
 				arrSize++;
			}
 			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				pValues[i++] = obj.getLa_land_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				pValues[i++] = obj.getVillage();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				pValues[i++] = obj.getType_of_land();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				pValues[i++] = obj.getSub_category_of_land();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> geprivateIRAList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk,ira.collector as private_ira_collector, DATE_FORMAT(submission_of_proposal_to_GM ,'%d-%m-%Y') AS submission_of_proposal_to_GM,DATE_FORMAT(approval_of_GM ,'%d-%m-%Y') AS  approval_of_GM,DATE_FORMAT(draft_letter_to_con_for_approval_rp ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_rp,DATE_FORMAT(date_of_approval_of_construction_rp ,'%d-%m-%Y') AS  date_of_approval_of_construction_rp,DATE_FORMAT(date_of_uploading_of_gazette_notification_rp ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_rp," + 
					"DATE_FORMAT(publication_in_gazette_rp ,'%d-%m-%Y') AS publication_in_gazette_rp,DATE_FORMAT(date_of_proposal_to_DC_for_nomination ,'%d-%m-%Y') AS  date_of_proposal_to_DC_for_nomination, DATE_FORMAT(date_of_nomination_of_competenta_authority ,'%d-%m-%Y') AS date_of_nomination_of_competenta_authority, DATE_FORMAT(draft_letter_to_con_for_approval_ca ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_ca, DATE_FORMAT(date_of_approval_of_construction_ca ,'%d-%m-%Y') AS date_of_approval_of_construction_ca, " + 
					"DATE_FORMAT(date_of_uploading_of_gazette_notification_ca ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_ca, DATE_FORMAT(publication_in_gazette_ca ,'%d-%m-%Y') AS publication_in_gazette_ca, DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA,DATE_FORMAT(approval_of_CALA_20a ,'%d-%m-%Y') AS approval_of_CALA_20a,DATE_FORMAT(draft_letter_to_con_for_approval_20a ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_20a," + 
					"DATE_FORMAT(date_of_approval_of_construction_20a ,'%d-%m-%Y') AS date_of_approval_of_construction_20a,DATE_FORMAT(date_of_uploading_of_gazette_notification_20a ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20a,DATE_FORMAT(publication_in_gazette_20a ,'%d-%m-%Y') AS publication_in_gazette_20a,DATE_FORMAT(publication_in_2_local_news_papers_20a ,'%d-%m-%Y') AS publication_in_2_local_news_papers_20a,DATE_FORMAT(pasting_of_notification_in_villages_20a ,'%d-%m-%Y') AS pasting_of_notification_in_villages_20a," + 
					"DATE_FORMAT(receipt_of_grievances ,'%d-%m-%Y') AS  receipt_of_grievances, DATE_FORMAT(disposal_of_grievances ,'%d-%m-%Y') AS disposal_of_grievances, DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA_20e ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA_20e, DATE_FORMAT(approval_of_CALA_20e ,'%d-%m-%Y') AS  approval_of_CALA_20e,DATE_FORMAT(draft_letter_to_con_for_approval_20e ,'%d-%m-%Y') AS  draft_letter_to_con_for_approval_20e,"
					+ "DATE_FORMAT(date_of_approval_of_construction_20e ,'%d-%m-%Y') AS  date_of_approval_of_construction_20e,DATE_FORMAT(date_of_uploading_of_gazette_notification_20e ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20e,DATE_FORMAT(publication_in_gazette_20e ,'%d-%m-%Y') AS  publication_in_gazette_20e,DATE_FORMAT(publication_of_notice_in_2_local_news_papers_20e ,'%d-%m-%Y') AS publication_of_notice_in_2_local_news_papers_20e,DATE_FORMAT(date_of_submission_of_draft_notification_to_CALA_20f ,'%d-%m-%Y') AS date_of_submission_of_draft_notification_to_CALA_20f," + 
					"DATE_FORMAT(approval_of_CALA_20f ,'%d-%m-%Y') AS approval_of_CALA_20f,DATE_FORMAT(draft_letter_to_con_for_approval_20f ,'%d-%m-%Y') AS draft_letter_to_con_for_approval_20f,DATE_FORMAT(date_of_approval_of_construction_20f ,'%d-%m-%Y') AS date_of_approval_of_construction_20f,DATE_FORMAT(date_of_uploading_of_gazette_notification_20f ,'%d-%m-%Y') AS date_of_uploading_of_gazette_notification_20f,DATE_FORMAT(publication_in_gazette_20f ,'%d-%m-%Y') AS publication_in_gazette_20f," + 
					"DATE_FORMAT(publication_of_notice_in_2_local_news_papers_20f ,'%d-%m-%Y') AS publication_of_notice_in_2_local_news_papers_20f " + 
					"from la_private_ira ira " + 
					"left join la_land_identification li on ira.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<LandAcquisition> getPrivateValList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk,lpv.payment_amount_units,DATE_FORMAT(lpv.forest_tree_survey ,'%d-%m-%Y') AS forest_tree_survey,DATE_FORMAT(lpv.forest_tree_valuation ,'%d-%m-%Y') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,DATE_FORMAT(lpv.horticulture_tree_survey ,'%d-%m-%Y') AS horticulture_tree_survey,DATE_FORMAT(lpv.horticulture_tree_valuation ,'%d-%m-%Y') AS horticulture_tree_valuation," + 
					"DATE_FORMAT(lpv.structure_survey ,'%d-%m-%Y') AS structure_survey,DATE_FORMAT(lpv.structure_valuation ,'%d-%m-%Y') AS structure_valuation,DATE_FORMAT(lpv.borewell_survey ,'%d-%m-%Y') AS borewell_survey,DATE_FORMAT(lpv.borewell_valuation ,'%d-%m-%Y') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, lpv.structure_valuation_status_fk," + 
					"lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, DATE_FORMAT(lpv.date_of_rfp_to_adtp ,'%d-%m-%Y') AS date_of_rfp_to_adtp,DATE_FORMAT(lpv.date_of_rate_fixation_of_land ,'%d-%m-%Y') AS date_of_rate_fixation_of_land, DATE_FORMAT(lpv.sdo_demand_for_payment ,'%d-%m-%Y') AS sdo_demand_for_payment,DATE_FORMAT(lpv.date_of_approval_for_payment ,'%d-%m-%Y') AS date_of_approval_for_payment," + 
					"cast(lpv.payment_amount as CHAR) as payment_amount, DATE_FORMAT(lpv.payment_date ,'%d-%m-%Y') AS private_payment_date  " + 
					" from la_private_land_valuation lpv " + 
					"left join la_land_identification li on lpv.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<LandAcquisition> getPrivateLandList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk,lpa.basic_rate_units,lpa.agriculture_tree_rate_units,lpa.forest_tree_rate_units, lpa.name_of_the_owner, lpa.basic_rate, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos," + 
					"lpa.forest_tree_rate,DATE_FORMAT(lpa.consent_from_owner,'%d-%m-%Y') AS consent_from_owner, DATE_FORMAT(lpa.legal_search_report,'%d-%m-%Y') AS legal_search_report, DATE_FORMAT(lpa.date_of_registration,'%d-%m-%Y') AS date_of_registration, DATE_FORMAT(lpa.date_of_possession,'%d-%m-%Y') AS date_of_possession, lpa.possession_status_fk as private_possession_status_fk," + 
					"cast(lpa.hundred_percent_Solatium as CHAR) as hundred_percent_Solatium,cast(lpa.extra_25_percent as CHAR) as extra_25_percent, cast(lpa.total_rate_divide_m2 as CHAR) as total_rate_divide_m2,cast(lpa.land_compensation as CHAR) as land_compensation," + 
					"cast(lpa.agriculture_tree_compensation as CHAR) as agriculture_tree_compensation,cast(lpa.forest_tree_compensation as CHAR) as forest_tree_compensation,cast(lpa.structure_compensation as CHAR) as structure_compensation,cast(lpa.borewell_compensation as CHAR) as borewell_compensation,cast(lpa.total_compensation as CHAR) as total_compensation" + 
					" from la_private_land_acquisition lpa " + 
					"left join la_land_identification li on lpa.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<LandAcquisition> getGovList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk,lg.id, lg.la_id_fk,DATE_FORMAT(lg.proposal_submission,'%d-%m-%Y') AS proposal_submission, lg.proposal_submission_status_fk, DATE_FORMAT(lg.valuation_date,'%d-%m-%Y') AS valuation_date, DATE_FORMAT(lg.letter_for_payment,'%d-%m-%Y') AS letter_for_payment," + 
					"lg.amount_demanded,cast(lg.lfp_status_fk as CHAR) as lfp_status_fk,DATE_FORMAT(lg.approval_for_payment,'%d-%m-%Y') AS approval_for_payment,DATE_FORMAT(lg.payment_date,'%d-%m-%Y') AS payment_date, lg.amount_paid, lg.payment_status_fk, DATE_FORMAT(lg.possession_date,'%d-%m-%Y') AS possession_date," + 
					"lg.possession_status_fk " + 
					" from la_government_land_acquisition lg " + 
					"left join la_land_identification li on lg.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<LandAcquisition> getForestList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk, DATE_FORMAT(lf.on_line_submission,'%d-%m-%Y') AS forest_online_submission, DATE_FORMAT(lf.submission_date_to_dycfo,'%d-%m-%Y') AS forest_submission_date_to_dycfo, DATE_FORMAT(lf.submission_date_to_ccf_thane,'%d-%m-%Y') AS forest_submission_date_to_ccf_thane, " + 
					"DATE_FORMAT(lf.submission_date_to_nodal_officer,'%d-%m-%Y') AS forest_submission_date_to_nodal_officer, DATE_FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS forest_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lf.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS forest_submission_date_to_regional_office_nagpur," + 
					" DATE_FORMAT(lf.date_of_approval_by_regional_office_nagpur,'%d-%m-%Y') AS forest_date_of_approval_by_regional_office_nagpur, DATE_FORMAT(lf.valuation_by_dycfo,'%d-%m-%Y') AS forest_valuation_by_dycfo,cast(lf.demanded_amount as CHAR) as forest_demanded_amount,cast(lf.payment_amount  as CHAR) as forest_payment_amount, DATE_FORMAT(lf.approval_for_payment,'%d-%m-%Y') AS forest_approval_for_payment" + 
					", DATE_FORMAT(lf.payment_date,'%d-%m-%Y') AS forest_payment_date,DATE_FORMAT(lf.possession_date,'%d-%m-%Y') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk," + 
					"lf.payment_status_fk as forest_payment_status_fk" + 
					" from la_forest_land_acquisition lf " + 
					"left join la_land_identification li on lf.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<LandAcquisition> getRailwayList(String la_id) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry = "select la_id_fk,lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,DATE_FORMAT(lr.online_submission,'%d-%m-%Y') AS railway_online_submission," + 
					"DATE_FORMAT(lr.submission_date_to_DyCFO,'%d-%m-%Y') AS railway_submission_date_to_DyCFO, DATE_FORMAT(lr.submission_date_to_CCF_Thane,'%d-%m-%Y') AS railway_submission_date_to_CCF_Thane, DATE_FORMAT(lr.`submission_date_to_nodal_officer/CCF Nagpur`,'%d-%m-%Y') AS railway_submission_date_to_nodal_officer_CCF_Nagpur, " + 
					" DATE_FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS railway_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lr.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS railway_submission_date_to_regional_office_nagpur, DATE_FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'%d-%m-%Y') AS railway_date_of_approval_by_Rregional_Office_agpur," + 
					"DATE_FORMAT(lr.valuation_by_DyCFO ,'%d-%m-%Y') AS railway_valuation_by_DyCFO, cast(lr.demanded_amount as CHAR) as railway_demanded_amount, DATE_FORMAT(lr.approval_for_payment,'%d-%m-%Y') AS railway_approval_for_payment, DATE_FORMAT(lr.payment_date,'%d-%m-%Y') AS railway_payment_date,cast(lr.payment_amount as CHAR) as railway_payment_amount, lr.payment_status as railway_payment_status, DATE_FORMAT(lr.possession_date,'%d-%m-%Y') AS railway_possession_date, lr.possession_status as railway_possession_status" + 
					" from la_railway_land_acquisition lr " + 
					"left join la_land_identification li on lr.la_id_fk = li.la_id  " + 
					"where la_id_fk = ? ";
			
			objList = jdbcTemplate.query( qry, new Object[] {la_id}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}

	

}
