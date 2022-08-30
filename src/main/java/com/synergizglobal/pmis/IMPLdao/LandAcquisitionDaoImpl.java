package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
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
	public static Logger logger = Logger.getLogger(LandAcquisitionDaoImpl.class);

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
			String qry ="select distinct la_id,survey_number,li.remarks,li.area_to_be_acquired,li.area_acquired,li.category_fk as type_of_land,li.la_land_status_fk,li.work_id_fk,w.work_name,w.project_id_fk,p.project_name,ISNULL(li.category_fk,c.la_category) as type_of_land ,sc.la_sub_category as sub_category_of_land, w.work_short_name,village_id,la_sub_category_fk,village,area_of_plot  as area_of_plot,modified_by,FORMAT(modified_date,'dd-MM-yyyy') as modified_date " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "
					+ "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ? ";
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
				qry = qry + " ORDER BY la_id ASC offset ? rows  fetch next ? rows only";
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
			String qry ="select count(distinct la_id) as total_records from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "
					+ "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ? ";
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
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY li.work_id_fk,w.work_name,w.work_short_name ORDER BY li.work_id_fk";
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
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ? ";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY village ";
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
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ? ";
				arrSize++;
			}		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY la_category ";
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
			String qry = "SELECT distinct sc.la_sub_category as sub_category_of_land from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					 "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where la_sub_category is not null and la_sub_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY la_sub_category ";
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
			String qry = "select distinct la_id,li.remarks,(select executive_user_id_fk from land_executives re where li.work_id_fk = re.work_id_fk and executive_user_id_fk = ?) as executive_user_id_fk,\r\n" + 
					"li.area_to_be_acquired as area_to_be_acquired,ISNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk, work_id_fk,w.project_id_fk,p.project_name,\r\n" + 
					"w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo, li.collector, \r\n" + 
					"FORMAT(proposal_submission_date_to_collector,'dd-MM-yyyy') AS proposal_submission_date_to_collector, area_of_plot as area_of_plot, \r\n" + 
					"jm_fee_amount,jm_fee_amount_units, li.special_feature,li.area_acquired as area_acquired,li.private_land_process,chainage_from,\r\n" + 
					"chainage_to, FORMAT(jm_fee_letter_received_date,'dd-MM-yyyy') AS jm_fee_letter_received_date,FORMAT(jm_fee_paid_date,'dd-MM-yyyy') AS jm_fee_paid_date,\r\n" + 
					"FORMAT(jm_start_date,'dd-MM-yyyy') AS  jm_start_date,FORMAT(jm_completion_date,'dd-MM-yyyy') AS jm_completion_date, FORMAT(jm_sheet_date_to_sdo,'dd-MM-yyyy') AS jm_sheet_date_to_sdo,\r\n" + 
					"jm_remarks, jm_approval, li.issues,lg.id, lg.la_id_fk,FORMAT(lg.proposal_submission,'dd-MM-yyyy') AS proposal_submission, lg.proposal_submission_status_fk, \r\n" + 
					"FORMAT(lg.valuation_date,'dd-MM-yyyy') AS valuation_date, FORMAT(lg.letter_for_payment,'dd-MM-yyyy') AS letter_for_payment,lg.amount_demanded,lg.lfp_status_fk as lfp_status_fk,\r\n" + 
					"FORMAT(lg.approval_for_payment,'dd-MM-yyyy') AS approval_for_payment,FORMAT(lg.payment_date,'dd-MM-yyyy') AS payment_date, lg.amount_paid, lg.payment_status_fk, \r\n" + 
					"FORMAT(lg.possession_date,'dd-MM-yyyy') AS possession_date,lg.possession_status_fk,lf.demanded_amount_units as demanded_amount_units_forest,\r\n" + 
					"lf.payment_amount_units as payment_amount_units_forest, lg.amount_demanded_units,lg.amount_paid_units, FORMAT(lf.on_line_submission,'dd-MM-yyyy') AS forest_online_submission, \r\n" + 
					"FORMAT(lf.submission_date_to_dycfo,'dd-MM-yyyy') AS forest_submission_date_to_dycfo, FORMAT(lf.submission_date_to_ccf_thane,'dd-MM-yyyy') AS forest_submission_date_to_ccf_thane, \r\n" + 
					"FORMAT(lf.submission_date_to_nodal_officer,'dd-MM-yyyy') AS forest_submission_date_to_nodal_officer, \r\n" + 
					"FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS forest_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
					"FORMAT(lf.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS forest_submission_date_to_regional_office_nagpur, \r\n" + 
					"FORMAT(lf.date_of_approval_by_regional_office_nagpur,'dd-MM-yyyy') AS forest_date_of_approval_by_regional_office_nagpur,\r\n" + 
					"FORMAT(cast(lf.valuation_by_DyCFO as date)  ,'dd-MM-yyyy') AS forest_valuation_by_dycfo,lf.demanded_amount as forest_demanded_amount,\r\n" + 
					"lf.payment_amount as forest_payment_amount, FORMAT(lf.approval_for_payment,'dd-MM-yyyy') AS forest_approval_for_payment,\r\n" + 
					"FORMAT(lf.payment_date,'dd-MM-yyyy') AS forest_payment_date,FORMAT(lf.possession_date,'dd-MM-yyyy') AS forest_possession_date,\r\n" + 
					"lf.possession_status_fk as forest_possession_status_fk,lf.payment_status_fk as forest_payment_status_fk ,\r\n" + 
					"lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,FORMAT(lr.online_submission,'dd-MM-yyyy') AS railway_online_submission,\r\n" + 
					"FORMAT(lr.submission_date_to_DyCFO,'dd-MM-yyyy') AS railway_submission_date_to_DyCFO, FORMAT(lr.submission_date_to_CCF_Thane,'dd-MM-yyyy') AS railway_submission_date_to_CCF_Thane,\r\n" + 
					"FORMAT([submission_date_to_nodal_officer/CCF Nagpur] ,'dd-MM-yyyy') AS railway_submission_date_to_nodal_officer_CCF_Nagpur, \r\n" + 
					"FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS railway_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
					"FORMAT(lr.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS railway_submission_date_to_regional_office_nagpur, \r\n" + 
					"FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'dd-MM-yyyy') AS railway_date_of_approval_by_Rregional_Office_agpur,\r\n" + 
					"FORMAT(cast(lr.valuation_by_DyCFO as date)  ,'dd-MM-yyyy') AS railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, \r\n" + 
					"FORMAT(cast(lr.approval_for_payment as date),'dd-MM-yyyy') AS railway_approval_for_payment, FORMAT(lr.payment_date,'dd-MM-yyyy') AS railway_payment_date,\r\n" + 
					"lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, FORMAT(lr.possession_date,'dd-MM-yyyy') AS railway_possession_date,\r\n" + 
					"lr.possession_status as railway_possession_status,  lpa.basic_rate_units,lpa.agriculture_tree_rate_units,lpa.forest_tree_rate_units, \r\n" + 
					"lpa.name_of_the_owner, lpa.basic_rate, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,lpa.forest_tree_rate,\r\n" + 
					"FORMAT(lpa.consent_from_owner,'dd-MM-yyyy') AS consent_from_owner, FORMAT(lpa.legal_search_report,'dd-MM-yyyy') AS legal_search_report, \r\n" + 
					"FORMAT(lpa.date_of_registration,'dd-MM-yyyy') AS date_of_registration, FORMAT(lpa.date_of_possession,'dd-MM-yyyy') AS date_of_possession,\r\n" + 
					"lpa.possession_status_fk as private_possession_status_fk, lpa.hundred_percent_Solatium as hundred_percent_Solatium,\r\n" + 
					"lpa.extra_25_percent as extra_25_percent, cast(lpa.total_rate_divide_m2 as CHAR) as total_rate_divide_m2,cast(lpa.land_compensation as CHAR) as land_compensation, \r\n" + 
					"lpa.agriculture_tree_compensation as agriculture_tree_compensation,cast(lpa.forest_tree_compensation as CHAR) as forest_tree_compensation,\r\n" + 
					"lpa.structure_compensation as structure_compensation,lpa.borewell_compensation as borewell_compensation,lpa.total_compensation as total_compensation,\r\n" + 
					"lpv.payment_amount_units,FORMAT(lpv.forest_tree_survey ,'dd-MM-yyyy') AS forest_tree_survey,FORMAT(lpv.forest_tree_valuation ,'dd-MM-yyyy') AS forest_tree_valuation, \r\n" + 
					"lpv.forest_tree_valuation_status_fk,FORMAT(lpv.horticulture_tree_survey ,'dd-MM-yyyy') AS horticulture_tree_survey,\r\n" + 
					"FORMAT(lpv.horticulture_tree_valuation ,'dd-MM-yyyy') AS horticulture_tree_valuation, FORMAT(lpv.structure_survey ,'dd-MM-yyyy') AS structure_survey,\r\n" + 
					"FORMAT(lpv.structure_valuation ,'dd-MM-yyyy') AS structure_valuation,FORMAT(lpv.borewell_survey ,'dd-MM-yyyy') AS borewell_survey,\r\n" + 
					"FORMAT(lpv.borewell_valuation ,'dd-MM-yyyy') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, \r\n" + 
					"lpv.structure_valuation_status_fk, lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, \r\n" + 
					"FORMAT(lpv.date_of_rfp_to_adtp ,'dd-MM-yyyy') AS date_of_rfp_to_adtp,FORMAT(lpv.date_of_rate_fixation_of_land ,'dd-MM-yyyy') AS date_of_rate_fixation_of_land, \r\n" + 
					"FORMAT(lpv.sdo_demand_for_payment ,'dd-MM-yyyy') AS sdo_demand_for_payment,FORMAT(lpv.date_of_approval_for_payment ,'dd-MM-yyyy') AS date_of_approval_for_payment, \r\n" + 
					"lpv.payment_amount as payment_amount, FORMAT(lpv.payment_date ,'dd-MM-yyyy') AS private_payment_date   ,ira.collector as private_ira_collector, \r\n" + 
					"FORMAT(submission_of_proposal_to_GM ,'dd-MM-yyyy') AS submission_of_proposal_to_GM,FORMAT(approval_of_GM ,'dd-MM-yyyy') AS  approval_of_GM,\r\n" + 
					"FORMAT(draft_letter_to_con_for_approval_rp ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_rp,\r\n" + 
					"FORMAT(date_of_approval_of_construction_rp ,'dd-MM-yyyy') AS  date_of_approval_of_construction_rp,\r\n" + 
					"FORMAT(date_of_uploading_of_gazette_notification_rp ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_rp,\r\n" + 
					"FORMAT(publication_in_gazette_rp ,'dd-MM-yyyy') AS publication_in_gazette_rp,\r\n" + 
					"FORMAT(date_of_proposal_to_DC_for_nomination ,'dd-MM-yyyy') AS  date_of_proposal_to_DC_for_nomination, \r\n" + 
					"FORMAT(date_of_nomination_of_competenta_authority ,'dd-MM-yyyy') AS date_of_nomination_of_competenta_authority,longitude,latitude  "
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
				String qry2 ="select id as la_file_id, la_id_fk, la_file_type_fk, name, attachment from la_files where la_id_fk = ? ";
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
					/*String qry2 ="SELECT CONCAT('"+obj.getWork_code()+"',SUBSTRING(la_id, 3,4),SUBSTRING(cast(MAX(replace(la_id,'"+obj.getWork_code()+"-LA-',''))+1 as varchar),0,"
							+ "IFNULL ((SELECT LEN(max(replace(la_id,'"+obj.getWork_code()+"-LA-','')))FROM la_land_identification "
							+ " where la_id like '"+obj.getWork_code()+"-LA%' group by LEN(la_id) order by length(la_id) desc offset 0 rows  fetch next 1 rows only),2)) ) AS la_id "
							+ "FROM la_land_identification WHERE la_id LIKE '"+obj.getWork_code()+"-LA-%' group by LEN(la_id) order by LEN(la_id) desc offset 0 rows  fetch next 1 rows only " ;

*/
				String qry2="select CONCAT('"+obj.getWork_code()+"','-LA-',case when len(la_id)=3 then concat('0',la_id) when len(la_id)=2 then concat('00',la_id) when len(la_id)=1 then concat('000',la_id) end) as la_id from(\r\n" + 
						"select Max(SUBSTRING( la_id , LEN(la_id) -  CHARINDEX('-',REVERSE(la_id)) + 2  , LEN(la_id)  ))+1 as la_id from la_land_identification where left(la_id,2) ='"+obj.getWork_code()+"') as a";
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
					+ "area_acquired,private_land_process,la_land_status_fk,category_fk,area_to_be_acquired,remarks,created_by,created_date,latitude,longitude)"
					+ "VALUES"
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :id, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :issues, :jm_fee_amount_units , :special_feature, :area_acquired, :private_land_process, "
					+ ":la_land_status_fk, :category_fk, :area_to_be_acquired, :remarks, :created_by_user_id_fk, CURRENT_TIMESTAMP,:latitude,:longitude)";
			
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
								+ "amount_demanded_units, amount_paid_units,planned_date_of_possession)"
								+ "VALUES"
								+ "(:la_id, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
								+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk," // :special_feature, 
								+ " :amount_demanded_units, :amount_paid_units,:planned_date_of_possession)";
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
						 		+ "submission_date_to_CCF_Thane, [submission_date_to_nodal_officer/CCF Nagpur], submission_date_to_revenue_secretary_mantralaya, "
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
						msgObj.setMessage("A new Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" has been JM "+JMStatus);
						msgObj.setRedirect_url("/get-land-acquisition/"+la_id);
						msgObj.setMessage_type("Land Acquisition");	
						BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
						template.update(messageQry, paramSource1);						
					}
					if(!StringUtils.isEmpty(obj.getLa_land_status_fk()))
					{
						for(int i=0;i<SplitStr.length;i++)
						{
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(SplitStr[i]);
							msgObj.setMessage("A Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" "+obj.getLa_land_status_fk());
							msgObj.setRedirect_url("/get-land-acquisition/"+la_id);
							msgObj.setMessage_type("Land Acquisition");	
							BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
							template.update(messageQry, paramSource1);						
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
							String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CONVERT(date, getdate()))";				
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
			String qry = "SELECT  ISNULL(STRING_AGG((u.user_id) , ','),'') as  user_id FROM land_executives re " + 
					"LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where work_id=?";
			executives = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return executives;
	}	
	
	private String getLandExecutivesEmail(String work_id) throws Exception {
		String executivesEmail="";
		try {
			String qry = "SELECT  ISNULL(STRING_AGG(u.email_id , ','),'') as email_id FROM land_executives re " + 
					"LEFT JOIN [user] u on re.executive_user_id_fk = u.user_id left join work w on re.work_id_fk = w.work_id  where work_id=?";
			executivesEmail = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return executivesEmail;
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
					+ "from work w "
					+ "left join la_land_identification u on u.work_id_fk = w.work_id  "
					+ "left join land_executives us on u.work_id_fk = us.work_id_fk  "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and us.executive_user_id_fk = ? ";
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
					+ "from project p "
					+ "LEFT JOIN work w on w.project_id_fk = p.project_id "
					+ "left join la_land_identification u on u.work_id_fk = w.work_id  "
					+ "left join land_executives us on u.work_id_fk = us.work_id_fk  "
					+ "where project_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and us.executive_user_id_fk = ? ";
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
			String qry = "select la_category as type_of_land from la_category";
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
			String qry = "select id,la_sub_category as sub_category_of_land,la_category_fk from la_sub_category";
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
			String qry = "select id,la_sub_category as sub_category_of_land, la_category_fk from la_sub_category ls "
					+ "LEFT OUTER JOIN la_category lc ON la_category_fk = la_category ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " where la_category_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " where la_sub_category  = ? ";
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
			String qry = "select id as category_id,la_category as type_of_land, ls.la_sub_category as sub_category_of_land from la_category lc "
					+ "LEFT OUTER JOIN la_sub_category ls ON la_category  = la_category_fk ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " where la_sub_category  = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " where la_category = ? ";
				arrSize++;
			}
			qry = qry + " group by id,la_category,ls.la_sub_category";
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
	
	private String getJMApprovalandLandStatus(String column,String laid) throws Exception {
		String Status="";
		try {
			String qry = "SELECT ISNULL("+column+",'') AS "+column+" FROM la_land_identification WHERE la_id=?";
			Status = (String) jdbcTemplate.queryForObject(qry, new Object[] { laid }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Status;
	}	
	

	@Override
	public boolean updateLandAcquisition(LandAcquisition obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			//String jmapproval=getJMApprovalandLandStatus("jm_approval",obj.getLa_id());
			//String landstatus=getJMApprovalandLandStatus("la_land_status_fk",obj.getLa_id());
			
			String insertQry = "UPDATE la_land_identification SET "
					+ "survey_number= :survey_number, village_id= :village_id, village= :village, taluka= :taluka, dy_slr= :dy_slr, sdo= :sdo, collector= :collector, proposal_submission_date_to_collector= :proposal_submission_date_to_collector,"
					+ "area_of_plot= :area_of_plot, jm_fee_amount = :jm_fee_amount, chainage_from= :chainage_from, chainage_to= :chainage_to, jm_fee_letter_received_date= :jm_fee_letter_received_date, jm_fee_paid_date= :jm_fee_paid_date, jm_start_date= :jm_start_date, jm_completion_date= :jm_completion_date, "
					+ "jm_sheet_date_to_sdo= :jm_sheet_date_to_sdo, jm_remarks= :jm_remarks, jm_approval= :jm_approval, issues= :issues, jm_fee_amount_units= :jm_fee_amount_units,"
					+ "la_land_status_fk= :la_land_status_fk, special_feature= :special_feature,private_land_process= :private_land_process,area_acquired= :area_acquired,"
					+ "category_fk= :category_fk,area_to_be_acquired= :area_to_be_acquired,remarks= :remarks,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP,latitude=:latitude,longitude=:longitude    "
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
								+ "amount_demanded_units, amount_paid_units,planned_date_of_possession)"
								+ "VALUES"
								+ "(:la_id, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
								+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk," // :special_feature, 
								+ "  :amount_demanded_units, :amount_paid_units,:planned_date_of_possession)";
						
						String govUpdateQry = "UPDATE  la_government_land_acquisition SET "
								+ " proposal_submission= :proposal_submission, proposal_submission_status_fk= :proposal_submission_status_fk, valuation_date= :valuation_date, letter_for_payment= :letter_for_payment, amount_demanded= :amount_demanded, "
								+ "lfp_status_fk= :lfp_status_fk, approval_for_payment= :approval_for_payment, payment_date= :payment_date, amount_paid= :amount_paid, payment_status_fk= :payment_status_fk, possession_date= :possession_date, possession_status_fk= :possession_status_fk,"
								+ "amount_demanded_units= :amount_demanded_units, amount_paid_units= :amount_paid_units,planned_date_of_possession= :planned_date_of_possession "
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
						 		+ "submission_date_to_CCF_Thane, [submission_date_to_nodal_officer/CCF Nagpur], submission_date_to_revenue_secretary_mantralaya, "
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
						 		+ "submission_date_to_CCF_Thane= :railway_submission_date_to_CCF_Thane, [submission_date_to_nodal_officer/CCF Nagpur]= :railway_submission_date_to_nodal_officer_CCF_Nagpur, submission_date_to_revenue_secretary_mantralaya= :railway_submission_date_to_revenue_secretary_mantralaya,  "
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
						String executivesEmail=getLandExecutivesEmail(obj.getWork_id_fk());
						
						String [] SplitStr=executives.split(",");
						String [] SplitEmail=executivesEmail.split(",");
						
						NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
						if (!StringUtils.isEmpty(obj.getJm_approval())) 
						{						
						//if(obj.getJm_approval().compareTo(jmapproval)==0)
						//{	
							String JMStatus="";
							if(obj.getJm_approval().compareTo("Done")==0)
							{
								JMStatus="Approved";
							}
							else
							{
								JMStatus="Rejected";
							}							
							for(int i=0;i<SplitStr.length;i++)
							{
								Messages msgObj = new Messages();
								msgObj.setUser_id_fk(SplitStr[i]);

								msgObj.setMessage("A new Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" has been JM "+JMStatus);
								msgObj.setRedirect_url("/get-land-acquisition/"+obj.getLa_id());
								msgObj.setMessage_type("Land Acquisition");	
								BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
								template.update(messageQry, paramSource1);						
							}
							
							
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

							String mailBodyHeader =  "A Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" has been JM "+JMStatus;
							
							
							LandAcquisition sobj = null;

							String qry = "select distinct la_id,li.remarks,li.area_to_be_acquired  as area_to_be_acquired,ISNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk,\r\n" + 
									"work_id_fk,w.project_id_fk,p.project_name,w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo,\r\n" + 
									"li.collector, FORMAT(proposal_submission_date_to_collector,'dd-MM-yyyy') AS proposal_submission_date_to_collector, area_of_plot, \r\n" + 
									"jm_fee_amount,jm_fee_amount_units, li.special_feature,li.area_acquired as area_acquired,li.private_land_process,chainage_from,\r\n" + 
									"chainage_to, FORMAT(jm_fee_letter_received_date,'dd-MM-yyyy') AS jm_fee_letter_received_date,FORMAT(jm_fee_paid_date,'dd-MM-yyyy') AS jm_fee_paid_date,\r\n" + 
									"FORMAT(jm_start_date,'dd-MM-yyyy') AS  jm_start_date,FORMAT(jm_completion_date,'dd-MM-yyyy') AS jm_completion_date, FORMAT(jm_sheet_date_to_sdo,'dd-MM-yyyy') AS jm_sheet_date_to_sdo,\r\n" + 
									"jm_remarks, jm_approval, li.issues,lg.id, lg.la_id_fk,FORMAT(lg.proposal_submission,'dd-MM-yyyy') AS proposal_submission, lg.proposal_submission_status_fk, \r\n" + 
									"FORMAT(lg.valuation_date,'dd-MM-yyyy') AS valuation_date, FORMAT(lg.letter_for_payment,'dd-MM-yyyy') AS letter_for_payment,lg.amount_demanded,\r\n" + 
									"lg.lfp_status_fk as lfp_status_fk,FORMAT(lg.approval_for_payment,'dd-MM-yyyy') AS approval_for_payment,FORMAT(lg.payment_date,'dd-MM-yyyy') AS payment_date,\r\n" + 
									"lg.amount_paid, lg.payment_status_fk, FORMAT(lg.possession_date,'dd-MM-yyyy') AS possession_date,lg.possession_status_fk,\r\n" + 
									"lf.demanded_amount_units as demanded_amount_units_forest,lf.payment_amount_units as payment_amount_units_forest, lg.amount_demanded_units,\r\n" + 
									"lg.amount_paid_units, FORMAT(lf.on_line_submission,'dd-MM-yyyy') AS forest_online_submission, FORMAT(lf.submission_date_to_dycfo,'dd-MM-yyyy') AS forest_submission_date_to_dycfo, \r\n" + 
									"FORMAT(lf.submission_date_to_ccf_thane,'dd-MM-yyyy') AS forest_submission_date_to_ccf_thane, FORMAT(lf.submission_date_to_nodal_officer,'dd-MM-yyyy') AS forest_submission_date_to_nodal_officer, \r\n" + 
									"FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS forest_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
									"FORMAT(lf.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS forest_submission_date_to_regional_office_nagpur, \r\n" + 
									"FORMAT(lf.date_of_approval_by_regional_office_nagpur,'dd-MM-yyyy') AS forest_date_of_approval_by_regional_office_nagpur,\r\n" + 
									"FORMAT(lf.valuation_by_dycfo,'dd-MM-yyyy') AS forest_valuation_by_dycfo,lf.demanded_amount as forest_demanded_amount,\r\n" + 
									"lf.payment_amount as forest_payment_amount, FORMAT(lf.approval_for_payment,'dd-MM-yyyy') AS forest_approval_for_payment, \r\n" + 
									"FORMAT(lf.payment_date,'dd-MM-yyyy') AS forest_payment_date,FORMAT(lf.possession_date,'dd-MM-yyyy') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk,\r\n" + 
									"lf.payment_status_fk as forest_payment_status_fk ,lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,\r\n" + 
									"FORMAT(lr.online_submission,'dd-MM-yyyy') AS railway_online_submission,FORMAT(lr.submission_date_to_DyCFO,'dd-MM-yyyy') AS railway_submission_date_to_DyCFO,\r\n" + 
									"FORMAT(lr.submission_date_to_CCF_Thane,'dd-MM-yyyy') AS railway_submission_date_to_CCF_Thane, \r\n" + 
									"FORMAT(lr.[submission_date_to_nodal_officer/CCF Nagpur],'dd-MM-yyyy') AS railway_submission_date_to_nodal_officer_CCF_Nagpur,  \r\n" + 
									"FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS railway_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
									"FORMAT(lr.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS railway_submission_date_to_regional_office_nagpur, \r\n" + 
									"FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'dd-MM-yyyy') AS railway_date_of_approval_by_Rregional_Office_agpur,\r\n" + 
									"FORMAT(cast(lr.valuation_by_DyCFO as date) ,'dd-MM-yyyy') AS railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, \r\n" + 
									"FORMAT(cast(lr.approval_for_payment as date),'dd-MM-yyyy') AS railway_approval_for_payment, FORMAT(lr.payment_date,'dd-MM-yyyy') AS railway_payment_date,\r\n" + 
									"lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, FORMAT(lr.possession_date,'dd-MM-yyyy') AS railway_possession_date, \r\n" + 
									"lr.possession_status as railway_possession_status,  lpa.basic_rate_units,lpa.agriculture_tree_rate_units,lpa.forest_tree_rate_units, lpa.name_of_the_owner, \r\n" + 
									"lpa.basic_rate, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,lpa.forest_tree_rate,FORMAT(lpa.consent_from_owner,'dd-MM-yyyy') AS consent_from_owner,\r\n" + 
									"FORMAT(lpa.legal_search_report,'dd-MM-yyyy') AS legal_search_report, FORMAT(lpa.date_of_registration,'dd-MM-yyyy') AS date_of_registration,\r\n" + 
									"FORMAT(lpa.date_of_possession,'dd-MM-yyyy') AS date_of_possession, lpa.possession_status_fk as private_possession_status_fk, \r\n" + 
									"lpa.hundred_percent_Solatium as hundred_percent_Solatium,lpa.extra_25_percent as extra_25_percent, \r\n" + 
									"lpa.total_rate_divide_m2  as total_rate_divide_m2,lpa.land_compensation as land_compensation, \r\n" + 
									"lpa.agriculture_tree_compensation  as agriculture_tree_compensation,lpa.forest_tree_compensation as forest_tree_compensation,\r\n" + 
									"lpa.structure_compensation  as structure_compensation,lpa.borewell_compensation as borewell_compensation,\r\n" + 
									"lpa.total_compensation  as total_compensation,lpv.payment_amount_units,FORMAT(lpv.forest_tree_survey ,'dd-MM-yyyy') AS forest_tree_survey,\r\n" + 
									"FORMAT(lpv.forest_tree_valuation ,'dd-MM-yyyy') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,\r\n" + 
									"FORMAT(lpv.horticulture_tree_survey ,'dd-MM-yyyy') AS horticulture_tree_survey,FORMAT(lpv.horticulture_tree_valuation ,'dd-MM-yyyy') AS horticulture_tree_valuation,\r\n" + 
									"FORMAT(lpv.structure_survey ,'dd-MM-yyyy') AS structure_survey,FORMAT(lpv.structure_valuation ,'dd-MM-yyyy') AS structure_valuation,\r\n" + 
									"FORMAT(lpv.borewell_survey ,'dd-MM-yyyy') AS borewell_survey,FORMAT(lpv.borewell_valuation ,'dd-MM-yyyy') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, \r\n" + 
									"lpv.structure_valuation_status_fk, lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, \r\n" + 
									"FORMAT(lpv.date_of_rfp_to_adtp ,'dd-MM-yyyy') AS date_of_rfp_to_adtp,FORMAT(lpv.date_of_rate_fixation_of_land ,'dd-MM-yyyy') AS date_of_rate_fixation_of_land,\r\n" + 
									"FORMAT(lpv.sdo_demand_for_payment ,'dd-MM-yyyy') AS sdo_demand_for_payment,FORMAT(lpv.date_of_approval_for_payment ,'dd-MM-yyyy') AS date_of_approval_for_payment, \r\n" + 
									"lpv.payment_amount as payment_amount, FORMAT(lpv.payment_date ,'dd-MM-yyyy') AS private_payment_date   ,ira.collector as private_ira_collector, \r\n" + 
									"FORMAT(submission_of_proposal_to_GM ,'dd-MM-yyyy') AS submission_of_proposal_to_GM,FORMAT(approval_of_GM ,'dd-MM-yyyy') AS  approval_of_GM,\r\n" + 
									"FORMAT(draft_letter_to_con_for_approval_rp ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_rp,\r\n" + 
									"FORMAT(date_of_approval_of_construction_rp ,'dd-MM-yyyy') AS  date_of_approval_of_construction_rp,\r\n" + 
									"FORMAT(date_of_uploading_of_gazette_notification_rp ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_rp,\r\n" + 
									"FORMAT(publication_in_gazette_rp ,'dd-MM-yyyy') AS publication_in_gazette_rp,FORMAT(date_of_proposal_to_DC_for_nomination ,'dd-MM-yyyy') AS  date_of_proposal_to_DC_for_nomination,\r\n" + 
									"FORMAT(date_of_nomination_of_competenta_authority ,'dd-MM-yyyy') AS date_of_nomination_of_competenta_authority, \r\n" + 
									"FORMAT(draft_letter_to_con_for_approval_ca ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_ca, \r\n" + 
									"FORMAT(date_of_approval_of_construction_ca ,'dd-MM-yyyy') AS date_of_approval_of_construction_ca,\r\n" + 
									"FORMAT(date_of_uploading_of_gazette_notification_ca ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_ca,\r\n" + 
									"FORMAT(publication_in_gazette_ca ,'dd-MM-yyyy') AS publication_in_gazette_ca, \r\n" + 
									"FORMAT(date_of_submission_of_draft_notification_to_CALA ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA,\r\n" + 
									"FORMAT(approval_of_CALA_20a ,'dd-MM-yyyy') AS approval_of_CALA_20a,FORMAT(draft_letter_to_con_for_approval_20a ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20a,\r\n" + 
									"FORMAT(date_of_approval_of_construction_20a ,'dd-MM-yyyy') AS date_of_approval_of_construction_20a,\r\n" + 
									"FORMAT(date_of_uploading_of_gazette_notification_20a ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20a,\r\n" + 
									"FORMAT(publication_in_gazette_20a ,'dd-MM-yyyy') AS publication_in_gazette_20a,\r\n" + 
									"FORMAT(publication_in_2_local_news_papers_20a ,'dd-MM-yyyy') AS publication_in_2_local_news_papers_20a,\r\n" + 
									"FORMAT(pasting_of_notification_in_villages_20a ,'dd-MM-yyyy') AS pasting_of_notification_in_villages_20a,\r\n" + 
									"FORMAT(receipt_of_grievances ,'dd-MM-yyyy') AS  receipt_of_grievances,\r\n" + 
									"FORMAT(disposal_of_grievances ,'dd-MM-yyyy') AS disposal_of_grievances, \r\n" + 
									"FORMAT(date_of_submission_of_draft_notification_to_CALA_20e ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20e, \r\n" + 
									"FORMAT(approval_of_CALA_20e ,'dd-MM-yyyy') AS  approval_of_CALA_20e,FORMAT(draft_letter_to_con_for_approval_20e ,'dd-MM-yyyy') AS  draft_letter_to_con_for_approval_20e,\r\n" + 
									"FORMAT(date_of_approval_of_construction_20e ,'dd-MM-yyyy') AS  date_of_approval_of_construction_20e,\r\n" + 
									"FORMAT(date_of_uploading_of_gazette_notification_20e ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20e,\r\n" + 
									"FORMAT(publication_in_gazette_20e ,'dd-MM-yyyy') AS  publication_in_gazette_20e,FORMAT(publication_of_notice_in_2_local_news_papers_20e ,'dd-MM-yyyy') \r\n" + 
									"AS publication_of_notice_in_2_local_news_papers_20e,FORMAT(date_of_submission_of_draft_notification_to_CALA_20f ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20f,\r\n" + 
									"FORMAT(approval_of_CALA_20f ,'dd-MM-yyyy') AS approval_of_CALA_20f,FORMAT(draft_letter_to_con_for_approval_20f ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20f,\r\n" + 
									"FORMAT(date_of_approval_of_construction_20f ,'dd-MM-yyyy') AS date_of_approval_of_construction_20f,\r\n" + 
									"FORMAT(date_of_uploading_of_gazette_notification_20f ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20f,\r\n" + 
									"FORMAT(publication_in_gazette_20f ,'dd-MM-yyyy') AS publication_in_gazette_20f,\r\n" + 
									"FORMAT(publication_of_notice_in_2_local_news_papers_20f ,'dd-MM-yyyy') AS publication_of_notice_in_2_local_news_papers_20f, \r\n" + 
									"FORMAT(lg.planned_date_of_possession ,'dd-MM-yyyy') as planned_date_of_possession \r\n" + 
									"from la_land_identification li left join la_government_land_acquisition lg on li.la_id = lg.la_id_fk \r\n" + 
									"left join la_forest_land_acquisition lf on li.la_id = lf.la_id_fk left join la_railway_land_acquisition lr on li.la_id = lr.la_id_fk \r\n" + 
									"left join la_private_land_acquisition lpa on li.la_id = lpa.la_id_fk left join la_private_land_valuation lpv on li.la_id = lpv.la_id_fk \r\n" + 
									"left join la_private_ira ira on li.la_id = ira.la_id_fk left join la_sub_category sc on li.la_sub_category_fk = sc.id left join work w on li.work_id_fk = w.work_id left join project p on w.project_id_fk = p.project_id left join la_category c on sc.la_category_fk = c.la_category  where la_id is not null and la_id=?" ; 
							Object[] pValues = new Object[] { obj.getLa_id() };
									
							sobj = (LandAcquisition)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));						

							sobj.setMail_body_header(mailBodyHeader);

							String emailSubject = "PMIS Land Acquisition Notification - Land Acquisition ";

							Mail mail = new Mail();
							mail.setMailTo(mailTo);
							mail.setMailCc(mailCC);
							mail.setMailBcc(CommonConstants.BCC_MAIL);
							mail.setMailSubject(emailSubject);
							mail.setTemplateName("LandAcquisitionAlert.vm");

							SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
							String today_date = monthFormat.format(new Date()).toUpperCase();

							SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
							String current_year = yearFormat.format(new Date()).toUpperCase();

							if (!StringUtils.isEmpty(mailTo)) {
								EMailSender emailSender = new EMailSender();
								logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail to " + mailTo + ": Start ");
								logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail CC " + mailCC + ": Start ");
								emailSender.sendEmailWithLandAcquisitionAlert(mail, sobj, today_date, current_year);
								logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail to " + mailTo + ": end ");
								logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail CC " + mailCC + ": end ");
							}							
							
						//}
						}
						if (!StringUtils.isEmpty(obj.getLa_land_status_fk())) 
						{
						//if(obj.getLa_land_status_fk().compareTo(landstatus)==0)
						//{
							if(!StringUtils.isEmpty(obj.getLa_land_status_fk()))
							{
								for(int i=0;i<SplitStr.length;i++)
								{
									Messages msgObj = new Messages();
									msgObj.setUser_id_fk(SplitStr[i]);
									msgObj.setMessage("A Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" "+obj.getLa_land_status_fk());
									msgObj.setRedirect_url("/get-land-acquisition/"+obj.getLa_id());
									msgObj.setMessage_type("Land Acquisition");	
									BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(msgObj);
									template.update(messageQry, paramSource1);						
								}

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

								String mailBodyHeader =  "A Land Acquisition against "+getWorkName(obj.getWork_id_fk())+" "+obj.getLa_land_status_fk();
								
								
								LandAcquisition sobj = null;

								String qry = "select distinct la_id,li.remarks,li.area_to_be_acquired as area_to_be_acquired,ISNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk,\r\n" + 
										"work_id_fk,w.project_id_fk,p.project_name,w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo,\r\n" + 
										"li.collector, FORMAT(proposal_submission_date_to_collector,'dd-MM-yyyy') AS proposal_submission_date_to_collector, area_of_plot, \r\n" + 
										"jm_fee_amount,jm_fee_amount_units, li.special_feature,li.area_acquired as area_acquired,li.private_land_process,chainage_from,\r\n" + 
										"chainage_to, FORMAT(jm_fee_letter_received_date,'dd-MM-yyyy') AS jm_fee_letter_received_date,FORMAT(jm_fee_paid_date,'dd-MM-yyyy') AS jm_fee_paid_date,\r\n" + 
										"FORMAT(jm_start_date,'dd-MM-yyyy') AS  jm_start_date,FORMAT(jm_completion_date,'dd-MM-yyyy') AS jm_completion_date, FORMAT(jm_sheet_date_to_sdo,'dd-MM-yyyy') AS jm_sheet_date_to_sdo,\r\n" + 
										"jm_remarks, jm_approval, li.issues,lg.id, lg.la_id_fk,FORMAT(lg.proposal_submission,'dd-MM-yyyy') AS proposal_submission, lg.proposal_submission_status_fk, \r\n" + 
										"FORMAT(lg.valuation_date,'dd-MM-yyyy') AS valuation_date, FORMAT(lg.letter_for_payment,'dd-MM-yyyy') AS letter_for_payment,lg.amount_demanded,\r\n" + 
										"lg.lfp_status_fk as lfp_status_fk,FORMAT(lg.approval_for_payment,'dd-MM-yyyy') AS approval_for_payment,FORMAT(lg.payment_date,'dd-MM-yyyy') AS payment_date,\r\n" + 
										"lg.amount_paid, lg.payment_status_fk, FORMAT(lg.possession_date,'dd-MM-yyyy') AS possession_date,lg.possession_status_fk,\r\n" + 
										"lf.demanded_amount_units as demanded_amount_units_forest,lf.payment_amount_units as payment_amount_units_forest, lg.amount_demanded_units,\r\n" + 
										"lg.amount_paid_units, FORMAT(lf.on_line_submission,'dd-MM-yyyy') AS forest_online_submission, FORMAT(lf.submission_date_to_dycfo,'dd-MM-yyyy') AS forest_submission_date_to_dycfo, \r\n" + 
										"FORMAT(lf.submission_date_to_ccf_thane,'dd-MM-yyyy') AS forest_submission_date_to_ccf_thane, FORMAT(lf.submission_date_to_nodal_officer,'dd-MM-yyyy') AS forest_submission_date_to_nodal_officer, \r\n" + 
										"FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS forest_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
										"FORMAT(lf.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS forest_submission_date_to_regional_office_nagpur, \r\n" + 
										"FORMAT(lf.date_of_approval_by_regional_office_nagpur,'dd-MM-yyyy') AS forest_date_of_approval_by_regional_office_nagpur,\r\n" + 
										"FORMAT(lf.valuation_by_dycfo,'dd-MM-yyyy') AS forest_valuation_by_dycfo,lf.demanded_amount as forest_demanded_amount,\r\n" + 
										"lf.payment_amount  as forest_payment_amount, FORMAT(lf.approval_for_payment,'dd-MM-yyyy') AS forest_approval_for_payment, \r\n" + 
										"FORMAT(lf.payment_date,'dd-MM-yyyy') AS forest_payment_date,FORMAT(lf.possession_date,'dd-MM-yyyy') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk,\r\n" + 
										"lf.payment_status_fk as forest_payment_status_fk ,lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,\r\n" + 
										"FORMAT(lr.online_submission,'dd-MM-yyyy') AS railway_online_submission,FORMAT(lr.submission_date_to_DyCFO,'dd-MM-yyyy') AS railway_submission_date_to_DyCFO,\r\n" + 
										"FORMAT(lr.submission_date_to_CCF_Thane,'dd-MM-yyyy') AS railway_submission_date_to_CCF_Thane, \r\n" + 
										"FORMAT(lr.[submission_date_to_nodal_officer/CCF Nagpur],'dd-MM-yyyy') AS railway_submission_date_to_nodal_officer_CCF_Nagpur,  \r\n" + 
										"FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS railway_submission_date_to_revenue_secretary_mantralaya, \r\n" + 
										"FORMAT(lr.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS railway_submission_date_to_regional_office_nagpur, \r\n" + 
										"FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'dd-MM-yyyy') AS railway_date_of_approval_by_Rregional_Office_agpur,\r\n" + 
										"FORMAT(cast(lr.valuation_by_DyCFO as date) ,'dd-MM-yyyy') AS railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, \r\n" + 
										"FORMAT(cast(lr.approval_for_payment as date),'dd-MM-yyyy') AS railway_approval_for_payment, FORMAT(lr.payment_date,'dd-MM-yyyy') AS railway_payment_date,\r\n" + 
										"lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, FORMAT(lr.possession_date,'dd-MM-yyyy') AS railway_possession_date, \r\n" + 
										"lr.possession_status as railway_possession_status,  lpa.basic_rate_units,lpa.agriculture_tree_rate_units,lpa.forest_tree_rate_units, lpa.name_of_the_owner, \r\n" + 
										"lpa.basic_rate, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,lpa.forest_tree_rate,FORMAT(lpa.consent_from_owner,'dd-MM-yyyy') AS consent_from_owner,\r\n" + 
										"FORMAT(lpa.legal_search_report,'dd-MM-yyyy') AS legal_search_report, FORMAT(lpa.date_of_registration,'dd-MM-yyyy') AS date_of_registration,\r\n" + 
										"FORMAT(lpa.date_of_possession,'dd-MM-yyyy') AS date_of_possession, lpa.possession_status_fk as private_possession_status_fk, \r\n" + 
										"lpa.hundred_percent_Solatium as hundred_percent_Solatium,lpa.extra_25_percent as extra_25_percent, \r\n" + 
										"lpa.total_rate_divide_m2 as total_rate_divide_m2,lpa.land_compensation as land_compensation, \r\n" + 
										"lpa.agriculture_tree_compensation as agriculture_tree_compensation,lpa.forest_tree_compensation as forest_tree_compensation,\r\n" + 
										"lpa.structure_compensation as structure_compensation,lpa.borewell_compensation as borewell_compensation,\r\n" + 
										"lpa.total_compensation as total_compensation,lpv.payment_amount_units,FORMAT(lpv.forest_tree_survey ,'dd-MM-yyyy') AS forest_tree_survey,\r\n" + 
										"FORMAT(lpv.forest_tree_valuation ,'dd-MM-yyyy') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,\r\n" + 
										"FORMAT(lpv.horticulture_tree_survey ,'dd-MM-yyyy') AS horticulture_tree_survey,FORMAT(lpv.horticulture_tree_valuation ,'dd-MM-yyyy') AS horticulture_tree_valuation,\r\n" + 
										"FORMAT(lpv.structure_survey ,'dd-MM-yyyy') AS structure_survey,FORMAT(lpv.structure_valuation ,'dd-MM-yyyy') AS structure_valuation,\r\n" + 
										"FORMAT(lpv.borewell_survey ,'dd-MM-yyyy') AS borewell_survey,FORMAT(lpv.borewell_valuation ,'dd-MM-yyyy') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, \r\n" + 
										"lpv.structure_valuation_status_fk, lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, \r\n" + 
										"FORMAT(lpv.date_of_rfp_to_adtp ,'dd-MM-yyyy') AS date_of_rfp_to_adtp,FORMAT(lpv.date_of_rate_fixation_of_land ,'dd-MM-yyyy') AS date_of_rate_fixation_of_land,\r\n" + 
										"FORMAT(lpv.sdo_demand_for_payment ,'dd-MM-yyyy') AS sdo_demand_for_payment,FORMAT(lpv.date_of_approval_for_payment ,'dd-MM-yyyy') AS date_of_approval_for_payment, \r\n" + 
										"lpv.payment_amount as payment_amount, FORMAT(lpv.payment_date ,'dd-MM-yyyy') AS private_payment_date   ,ira.collector as private_ira_collector, \r\n" + 
										"FORMAT(submission_of_proposal_to_GM ,'dd-MM-yyyy') AS submission_of_proposal_to_GM,FORMAT(approval_of_GM ,'dd-MM-yyyy') AS  approval_of_GM,\r\n" + 
										"FORMAT(draft_letter_to_con_for_approval_rp ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_rp,\r\n" + 
										"FORMAT(date_of_approval_of_construction_rp ,'dd-MM-yyyy') AS  date_of_approval_of_construction_rp,\r\n" + 
										"FORMAT(date_of_uploading_of_gazette_notification_rp ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_rp,\r\n" + 
										"FORMAT(publication_in_gazette_rp ,'dd-MM-yyyy') AS publication_in_gazette_rp,FORMAT(date_of_proposal_to_DC_for_nomination ,'dd-MM-yyyy') AS  date_of_proposal_to_DC_for_nomination,\r\n" + 
										"FORMAT(date_of_nomination_of_competenta_authority ,'dd-MM-yyyy') AS date_of_nomination_of_competenta_authority, \r\n" + 
										"FORMAT(draft_letter_to_con_for_approval_ca ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_ca, \r\n" + 
										"FORMAT(date_of_approval_of_construction_ca ,'dd-MM-yyyy') AS date_of_approval_of_construction_ca,\r\n" + 
										"FORMAT(date_of_uploading_of_gazette_notification_ca ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_ca,\r\n" + 
										"FORMAT(publication_in_gazette_ca ,'dd-MM-yyyy') AS publication_in_gazette_ca, \r\n" + 
										"FORMAT(date_of_submission_of_draft_notification_to_CALA ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA,\r\n" + 
										"FORMAT(approval_of_CALA_20a ,'dd-MM-yyyy') AS approval_of_CALA_20a,FORMAT(draft_letter_to_con_for_approval_20a ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20a,\r\n" + 
										"FORMAT(date_of_approval_of_construction_20a ,'dd-MM-yyyy') AS date_of_approval_of_construction_20a,\r\n" + 
										"FORMAT(date_of_uploading_of_gazette_notification_20a ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20a,\r\n" + 
										"FORMAT(publication_in_gazette_20a ,'dd-MM-yyyy') AS publication_in_gazette_20a,\r\n" + 
										"FORMAT(publication_in_2_local_news_papers_20a ,'dd-MM-yyyy') AS publication_in_2_local_news_papers_20a,\r\n" + 
										"FORMAT(pasting_of_notification_in_villages_20a ,'dd-MM-yyyy') AS pasting_of_notification_in_villages_20a,\r\n" + 
										"FORMAT(receipt_of_grievances ,'dd-MM-yyyy') AS  receipt_of_grievances,\r\n" + 
										"FORMAT(disposal_of_grievances ,'dd-MM-yyyy') AS disposal_of_grievances, \r\n" + 
										"FORMAT(date_of_submission_of_draft_notification_to_CALA_20e ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20e, \r\n" + 
										"FORMAT(approval_of_CALA_20e ,'dd-MM-yyyy') AS  approval_of_CALA_20e,FORMAT(draft_letter_to_con_for_approval_20e ,'dd-MM-yyyy') AS  draft_letter_to_con_for_approval_20e,\r\n" + 
										"FORMAT(date_of_approval_of_construction_20e ,'dd-MM-yyyy') AS  date_of_approval_of_construction_20e,\r\n" + 
										"FORMAT(date_of_uploading_of_gazette_notification_20e ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20e,\r\n" + 
										"FORMAT(publication_in_gazette_20e ,'dd-MM-yyyy') AS  publication_in_gazette_20e,FORMAT(publication_of_notice_in_2_local_news_papers_20e ,'dd-MM-yyyy') \r\n" + 
										"AS publication_of_notice_in_2_local_news_papers_20e,FORMAT(date_of_submission_of_draft_notification_to_CALA_20f ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20f,\r\n" + 
										"FORMAT(approval_of_CALA_20f ,'dd-MM-yyyy') AS approval_of_CALA_20f,FORMAT(draft_letter_to_con_for_approval_20f ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20f,\r\n" + 
										"FORMAT(date_of_approval_of_construction_20f ,'dd-MM-yyyy') AS date_of_approval_of_construction_20f,\r\n" + 
										"FORMAT(date_of_uploading_of_gazette_notification_20f ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20f,\r\n" + 
										"FORMAT(publication_in_gazette_20f ,'dd-MM-yyyy') AS publication_in_gazette_20f,\r\n" + 
										"FORMAT(publication_of_notice_in_2_local_news_papers_20f ,'dd-MM-yyyy') AS publication_of_notice_in_2_local_news_papers_20f, \r\n" + 
										"FORMAT(lg.planned_date_of_possession ,'dd-MM-yyyy') as planned_date_of_possession \r\n" + 
										"from la_land_identification li left join la_government_land_acquisition lg on li.la_id = lg.la_id_fk \r\n" + 
										"left join la_forest_land_acquisition lf on li.la_id = lf.la_id_fk left join la_railway_land_acquisition lr on li.la_id = lr.la_id_fk \r\n" + 
										"left join la_private_land_acquisition lpa on li.la_id = lpa.la_id_fk left join la_private_land_valuation lpv on li.la_id = lpv.la_id_fk \r\n" + 
										"left join la_private_ira ira on li.la_id = ira.la_id_fk left join la_sub_category sc on li.la_sub_category_fk = sc.id left join work w on li.work_id_fk = w.work_id left join project p on w.project_id_fk = p.project_id left join la_category c on sc.la_category_fk = c.la_category  where la_id is not null and la_id=?" ; 
								Object[] pValues = new Object[] { obj.getLa_id() };
										
								sobj = (LandAcquisition)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));						

								sobj.setMail_body_header(mailBodyHeader);

								String emailSubject = "PMIS Land Acquisition Notification - Land Acquisition ";

								Mail mail = new Mail();
								mail.setMailTo(mailTo);
								mail.setMailCc(mailCC);
								mail.setMailBcc(CommonConstants.BCC_MAIL);
								mail.setMailSubject(emailSubject);
								mail.setTemplateName("LandAcquisitionAlert.vm");

								SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
								String today_date = monthFormat.format(new Date()).toUpperCase();

								SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
								String current_year = yearFormat.format(new Date()).toUpperCase();

								if (!StringUtils.isEmpty(mailTo)) {
									EMailSender emailSender = new EMailSender();
									logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail to " + mailTo + ": Start ");
									logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail CC " + mailCC + ": Start ");
									emailSender.sendEmailWithLandAcquisitionAlert(mail, sobj, today_date, current_year);
									logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail to " + mailTo + ": end ");
									logger.error("sendEmailWithUtilityShiftingAlert() >> Sending mail CC " + mailCC + ": end ");
								}
								
								
							}
							
							
						}
					//}
					
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
							String issuesQry = "INSERT INTO issue(title,description,reported_by,priority_fk,category_fk,status_fk,remarks,date)VALUES(?,?,?,?,?,?,?,CONVERT(date, getdate()))";				
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
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and la_sub_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY la_land_status_fk ";
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
	
	private String getWorkName(String work_id) throws Exception {
		String workname="";
		try {
			String qry = "SELECT work_short_name FROM work WHERE work_id=?";
			workname = (String) jdbcTemplate.queryForObject(qry, new Object[] { work_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return workname;
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
					+ "area_acquired,private_land_process,la_land_status_fk,category_fk,area_to_be_acquired,remarks,latitude,longitude)"
					+ "VALUES"
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :la_sub_category_fk, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :issues, :jm_fee_amount_units , :special_feature, :area_acquired, :private_land_process, "
					+ ":la_land_status_fk, :category_fk, :area_to_be_acquired, :remarks,:latitude,:longitude)";
			
			String updatetQry = "UPDATE la_land_identification SET "
					+ "survey_number= :survey_number, village_id= :village_id,la_sub_category_fk= :la_sub_category_fk, village= :village, taluka= :taluka, dy_slr= :dy_slr, sdo= :sdo, collector= :collector, proposal_submission_date_to_collector= :proposal_submission_date_to_collector,"
					+ "area_of_plot= :area_of_plot, jm_fee_amount = :jm_fee_amount, chainage_from= :chainage_from, chainage_to= :chainage_to, jm_fee_letter_received_date= :jm_fee_letter_received_date, jm_fee_paid_date= :jm_fee_paid_date, jm_start_date= :jm_start_date, jm_completion_date= :jm_completion_date, "
					+ "jm_sheet_date_to_sdo= :jm_sheet_date_to_sdo, jm_remarks= :jm_remarks, jm_approval= :jm_approval, issues= :issues,  jm_fee_amount_units= :jm_fee_amount_units,"
					+ "la_land_status_fk= :la_land_status_fk, special_feature= :special_feature,private_land_process= :private_land_process,area_acquired= :area_acquired,"
					+ "category_fk= :category_fk,area_to_be_acquired= :area_to_be_acquired,remarks= :remarks,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP,latitude=:latitude,longitude=:longitude    "
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
					 		+ "submission_date_to_CCF_Thane, [submission_date_to_nodal_officer/CCF Nagpur], submission_date_to_revenue_secretary_mantralaya, "
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
					 		+ "submission_date_to_CCF_Thane= :railway_submission_date_to_CCF_Thane, [submission_date_to_nodal_officer/CCF Nagpur]= :railway_submission_date_to_nodal_officer_CCF_Nagpur, submission_date_to_revenue_secretary_mantralaya= :railway_submission_date_to_revenue_secretary_mantralaya,  "
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
			String qry ="select distinct la_id,li.remarks,cast(li.area_to_be_acquired as CHAR) as area_to_be_acquired,ISNULL(li.category_fk,c.la_category) as type_of_land,li.la_land_status_fk, li.work_id_fk,w.project_id_fk,p.project_name,w.work_short_name,sc.la_sub_category as sub_category_of_land, li.survey_number, li.village_id, li.village, taluka, dy_slr, sdo, li.collector, FORMAT(proposal_submission_date_to_collector,'dd-MM-yyyy') AS proposal_submission_date_to_collector, cast(area_of_plot as CHAR) as area_of_plot, jm_fee_amount,jm_fee_amount_units, " + 
					"li.special_feature,latitude,longitude,cast(li.area_acquired as CHAR) as area_acquired,li.private_land_process,cast(chainage_from as CHAR) as chainage_from,cast(chainage_to as CHAR) as chainage_to, FORMAT(jm_fee_letter_received_date,'dd-MM-yyyy') AS jm_fee_letter_received_date,FORMAT(jm_fee_paid_date,'dd-MM-yyyy') AS jm_fee_paid_date,FORMAT(jm_start_date,'dd-MM-yyyy') AS  jm_start_date,FORMAT(jm_completion_date,'dd-MM-yyyy') AS jm_completion_date, FORMAT(jm_sheet_date_to_sdo,'dd-MM-yyyy') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues " + 
					" from la_land_identification li " +
					"left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_land_status_fk())) {
				qry = qry + " and la_land_status_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and c.la_category = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sc.la_sub_category = ? ";
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
			String qry = "select la_id_fk,ira.collector as private_ira_collector, FORMAT(submission_of_proposal_to_GM ,'dd-MM-yyyy') AS submission_of_proposal_to_GM,FORMAT(approval_of_GM ,'dd-MM-yyyy') AS  approval_of_GM,FORMAT(draft_letter_to_con_for_approval_rp ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_rp,FORMAT(date_of_approval_of_construction_rp ,'dd-MM-yyyy') AS  date_of_approval_of_construction_rp,FORMAT(date_of_uploading_of_gazette_notification_rp ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_rp," + 
					"FORMAT(publication_in_gazette_rp ,'dd-MM-yyyy') AS publication_in_gazette_rp,FORMAT(date_of_proposal_to_DC_for_nomination ,'dd-MM-yyyy') AS  date_of_proposal_to_DC_for_nomination, FORMAT(date_of_nomination_of_competenta_authority ,'dd-MM-yyyy') AS date_of_nomination_of_competenta_authority, FORMAT(draft_letter_to_con_for_approval_ca ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_ca, FORMAT(date_of_approval_of_construction_ca ,'dd-MM-yyyy') AS date_of_approval_of_construction_ca, " + 
					"FORMAT(date_of_uploading_of_gazette_notification_ca ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_ca, FORMAT(publication_in_gazette_ca ,'dd-MM-yyyy') AS publication_in_gazette_ca, FORMAT(date_of_submission_of_draft_notification_to_CALA ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA,FORMAT(approval_of_CALA_20a ,'dd-MM-yyyy') AS approval_of_CALA_20a,FORMAT(draft_letter_to_con_for_approval_20a ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20a," + 
					"FORMAT(date_of_approval_of_construction_20a ,'dd-MM-yyyy') AS date_of_approval_of_construction_20a,FORMAT(date_of_uploading_of_gazette_notification_20a ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20a,FORMAT(publication_in_gazette_20a ,'dd-MM-yyyy') AS publication_in_gazette_20a,FORMAT(publication_in_2_local_news_papers_20a ,'dd-MM-yyyy') AS publication_in_2_local_news_papers_20a,FORMAT(pasting_of_notification_in_villages_20a ,'dd-MM-yyyy') AS pasting_of_notification_in_villages_20a," + 
					"FORMAT(receipt_of_grievances ,'dd-MM-yyyy') AS  receipt_of_grievances, FORMAT(disposal_of_grievances ,'dd-MM-yyyy') AS disposal_of_grievances, FORMAT(date_of_submission_of_draft_notification_to_CALA_20e ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20e, FORMAT(approval_of_CALA_20e ,'dd-MM-yyyy') AS  approval_of_CALA_20e,FORMAT(draft_letter_to_con_for_approval_20e ,'dd-MM-yyyy') AS  draft_letter_to_con_for_approval_20e,"
					+ "FORMAT(date_of_approval_of_construction_20e ,'dd-MM-yyyy') AS  date_of_approval_of_construction_20e,FORMAT(date_of_uploading_of_gazette_notification_20e ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20e,FORMAT(publication_in_gazette_20e ,'dd-MM-yyyy') AS  publication_in_gazette_20e,FORMAT(publication_of_notice_in_2_local_news_papers_20e ,'dd-MM-yyyy') AS publication_of_notice_in_2_local_news_papers_20e,FORMAT(date_of_submission_of_draft_notification_to_CALA_20f ,'dd-MM-yyyy') AS date_of_submission_of_draft_notification_to_CALA_20f," + 
					"FORMAT(approval_of_CALA_20f ,'dd-MM-yyyy') AS approval_of_CALA_20f,FORMAT(draft_letter_to_con_for_approval_20f ,'dd-MM-yyyy') AS draft_letter_to_con_for_approval_20f,FORMAT(date_of_approval_of_construction_20f ,'dd-MM-yyyy') AS date_of_approval_of_construction_20f,FORMAT(date_of_uploading_of_gazette_notification_20f ,'dd-MM-yyyy') AS date_of_uploading_of_gazette_notification_20f,FORMAT(publication_in_gazette_20f ,'dd-MM-yyyy') AS publication_in_gazette_20f," + 
					"FORMAT(publication_of_notice_in_2_local_news_papers_20f ,'dd-MM-yyyy') AS publication_of_notice_in_2_local_news_papers_20f " + 
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
			String qry = "select la_id_fk,lpv.payment_amount_units,FORMAT(lpv.forest_tree_survey ,'dd-MM-yyyy') AS forest_tree_survey,FORMAT(lpv.forest_tree_valuation ,'dd-MM-yyyy') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,FORMAT(lpv.horticulture_tree_survey ,'dd-MM-yyyy') AS horticulture_tree_survey,FORMAT(lpv.horticulture_tree_valuation ,'dd-MM-yyyy') AS horticulture_tree_valuation," + 
					"FORMAT(lpv.structure_survey ,'dd-MM-yyyy') AS structure_survey,FORMAT(lpv.structure_valuation ,'dd-MM-yyyy') AS structure_valuation,FORMAT(lpv.borewell_survey ,'dd-MM-yyyy') AS borewell_survey,FORMAT(lpv.borewell_valuation ,'dd-MM-yyyy') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, lpv.structure_valuation_status_fk," + 
					"lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, FORMAT(lpv.date_of_rfp_to_adtp ,'dd-MM-yyyy') AS date_of_rfp_to_adtp,FORMAT(lpv.date_of_rate_fixation_of_land ,'dd-MM-yyyy') AS date_of_rate_fixation_of_land, FORMAT(lpv.sdo_demand_for_payment ,'dd-MM-yyyy') AS sdo_demand_for_payment,FORMAT(lpv.date_of_approval_for_payment ,'dd-MM-yyyy') AS date_of_approval_for_payment," + 
					"cast(lpv.payment_amount as CHAR) as payment_amount, FORMAT(lpv.payment_date ,'dd-MM-yyyy') AS private_payment_date  " + 
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
					"lpa.forest_tree_rate,FORMAT(lpa.consent_from_owner,'dd-MM-yyyy') AS consent_from_owner, FORMAT(lpa.legal_search_report,'dd-MM-yyyy') AS legal_search_report, FORMAT(lpa.date_of_registration,'dd-MM-yyyy') AS date_of_registration, FORMAT(lpa.date_of_possession,'dd-MM-yyyy') AS date_of_possession, lpa.possession_status_fk as private_possession_status_fk," + 
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
			String qry = "select la_id_fk,lg.id, lg.la_id_fk,FORMAT(lg.proposal_submission,'dd-MM-yyyy') AS proposal_submission, lg.proposal_submission_status_fk, FORMAT(lg.valuation_date,'dd-MM-yyyy') AS valuation_date, FORMAT(lg.letter_for_payment,'dd-MM-yyyy') AS letter_for_payment," + 
					"lg.amount_demanded,cast(lg.lfp_status_fk as CHAR) as lfp_status_fk,FORMAT(lg.approval_for_payment,'dd-MM-yyyy') AS approval_for_payment,FORMAT(lg.payment_date,'dd-MM-yyyy') AS payment_date, lg.amount_paid, lg.payment_status_fk, FORMAT(lg.possession_date,'dd-MM-yyyy') AS possession_date," + 
					"lg.possession_status_fk,FORMAT(lg.planned_date_of_possession ,'dd-MM-yyyy') as planned_date_of_possession " + 
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
			String qry = "select la_id_fk, FORMAT(lf.on_line_submission,'dd-MM-yyyy') AS forest_online_submission, FORMAT(lf.submission_date_to_dycfo,'dd-MM-yyyy') AS forest_submission_date_to_dycfo, FORMAT(lf.submission_date_to_ccf_thane,'dd-MM-yyyy') AS forest_submission_date_to_ccf_thane, " + 
					"FORMAT(lf.submission_date_to_nodal_officer,'dd-MM-yyyy') AS forest_submission_date_to_nodal_officer, FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS forest_submission_date_to_revenue_secretary_mantralaya, FORMAT(lf.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS forest_submission_date_to_regional_office_nagpur," + 
					" FORMAT(lf.date_of_approval_by_regional_office_nagpur,'dd-MM-yyyy') AS forest_date_of_approval_by_regional_office_nagpur, FORMAT(lf.valuation_by_dycfo,'dd-MM-yyyy') AS forest_valuation_by_dycfo,cast(lf.demanded_amount as CHAR) as forest_demanded_amount,cast(lf.payment_amount  as CHAR) as forest_payment_amount, FORMAT(lf.approval_for_payment,'dd-MM-yyyy') AS forest_approval_for_payment" + 
					", FORMAT(lf.payment_date,'dd-MM-yyyy') AS forest_payment_date,FORMAT(lf.possession_date,'dd-MM-yyyy') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk," + 
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
			String qry = "select la_id_fk,lr.demanded_amount_units,lr.payment_amount_units as payment_amount_units_railway,FORMAT(lr.online_submission,'dd-MM-yyyy') AS railway_online_submission," + 
					"FORMAT(lr.submission_date_to_DyCFO,'dd-MM-yyyy') AS railway_submission_date_to_DyCFO, FORMAT(lr.submission_date_to_CCF_Thane,'dd-MM-yyyy') AS railway_submission_date_to_CCF_Thane, FORMAT(lr.[submission_date_to_nodal_officer/CCF Nagpur],'dd-MM-yyyy') AS railway_submission_date_to_nodal_officer_CCF_Nagpur, " + 
					" FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'dd-MM-yyyy') AS railway_submission_date_to_revenue_secretary_mantralaya, FORMAT(lr.submission_date_to_regional_office_nagpur,'dd-MM-yyyy') AS railway_submission_date_to_regional_office_nagpur, FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'dd-MM-yyyy') AS railway_date_of_approval_by_Rregional_Office_agpur," + 
					"FORMAT(cast(lr.valuation_by_DyCFO as date) ,'dd-MM-yyyy') AS railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, FORMAT(cast(lr.approval_for_payment as date),'dd-MM-yyyy') AS railway_approval_for_payment, FORMAT(lr.payment_date,'dd-MM-yyyy') AS railway_payment_date,lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, FORMAT(lr.possession_date,'dd-MM-yyyy') AS railway_possession_date, lr.possession_status as railway_possession_status" + 
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

	@Override
	public boolean checkSurveyNumber(String survey_number, String village_id, String la_id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LandAcquisition> getLADetails(LandAcquisition dObj) throws Exception {
		List<LandAcquisition> objList = null;
		try {
			String qry ="select distinct la_id,survey_number,li.remarks,li.area_to_be_acquired,c1.contract_id as contract_id_fk,li.area_acquired,li.category_fk as type_of_land,li.la_land_status_fk,li.work_id_fk,w.work_name,w.project_id_fk,p.project_name,ISNULL(li.category_fk,c.la_category) as type_of_land ,sc.la_sub_category as sub_category_of_land, w.work_short_name,village_id,la_sub_category_fk,village " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "+
					"left join contract c1 on c1.work_id_fk = w.work_id "
					+ "left join land_executives le on li.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  and la_id=? ";			
			objList = jdbcTemplate.query( qry, new Object[] {dObj.getLa_id()}, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objList;
	}


	

}
