package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.synergizglobal.pmis.Idao.LandAcquisitionDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.LandAcquisition;

@Repository
public class LandAcquisitionDaoImpl implements LandAcquisitionDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select la_id,survey_number,li.work_id_fk,w.work_name,w.project_id_fk,p.project_name,c.la_category as type_of_land ,sc.la_sub_category as sub_category_of_land, w.work_short_name,village_id,la_sub_category_fk,village,area_of_plot " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			//qry = qry +" GROUP BY work_id_fk";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT li.work_id_fk,w.work_name,w.work_short_name from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where li.work_id_fk is not null and li.work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			qry = qry + "GROUP BY li.work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT village from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where village is not null and village <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			qry = qry + "GROUP BY village ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT c.la_category as type_of_land from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where c.la_category is not null and c.la_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			qry = qry + "GROUP BY la_category ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT sc.la_sub_category as sub_category_of_land from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "+
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where la_sub_category is not null and la_sub_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			qry = qry + "GROUP BY la_sub_category ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getStatusList() throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry ="select status,status_of from la_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception {					
		LandAcquisition LADetails = null;
		try {
			String qry = "select la_id,li.attachment, work_id_fk,w.project_id_fk,p.project_name,w.work_short_name, li.survey_number, li.village_id,c.la_category as type_of_land ,sc.la_sub_category as sub_category_of_land, li.village, taluka, dy_slr, sdo, collector, DATE_FORMAT(proposal_submission_date_to_collector,'%d-%m-%Y') AS proposal_submission_date_to_collector, cast(area_of_plot as CHAR) as area_of_plot, jm_fee_amount, cast(chainage_from as CHAR) as chainage_from,cast(chainage_to as CHAR) as chainage_to, DATE_FORMAT(jm_fee_letter_received_date,'%d-%m-%Y') AS jm_fee_letter_received_date,DATE_FORMAT(jm_fee_paid_date,'%d-%m-%Y') AS jm_fee_paid_date,DATE_FORMAT(jm_start_date,'%d-%m-%Y') AS  jm_start_date,DATE_FORMAT(jm_completion_date,'%d-%m-%Y') AS jm_completion_date, DATE_FORMAT(jm_sheet_date_to_sdo,'%d-%m-%Y') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues"
					
					+ ",lg.id, lg.la_id_fk,cast(lg.area_to_be_acquired as CHAR) as area_to_be_acquired,DATE_FORMAT(lg.proposal_submission,'%d-%m-%Y') AS proposal_submission, lg.proposal_submission_status_fk, DATE_FORMAT(lg.valuation_date,'%d-%m-%Y') AS valuation_date, DATE_FORMAT(lg.letter_for_payment,'%d-%m-%Y') AS letter_for_payment,"
					+ "lg.amount_demanded,cast(lg.lfp_status_fk as CHAR) as lfp_status_fk,DATE_FORMAT(lg.approval_for_payment,'%d-%m-%Y') AS approval_for_payment,DATE_FORMAT(lg.payment_date,'%d-%m-%Y') AS payment_date, lg.amount_paid, lg.payment_status_fk, DATE_FORMAT(lg.possession_date,'%d-%m-%Y') AS possession_date,"
					+ "lg.possession_status_fk, lg.special_feature, cast(lg.area_acquired as CHAR) as area_acquired,lg.remarks as gov_remarks, "
					
					+ "cast(lf.area_to_be_acquired as CHAR) as forest_area_to_be_acquired, DATE_FORMAT(lf.on_line_submission,'%d-%m-%Y') AS forest_online_submission, DATE_FORMAT(lf.submission_date_to_dycfo,'%d-%m-%Y') AS forest_submission_date_to_dycfo, DATE_FORMAT(lf.submission_date_to_ccf_thane,'%d-%m-%Y') AS forest_submission_date_to_ccf_thane, "
					+ "DATE_FORMAT(lf.submission_date_to_nodal_officer,'%d-%m-%Y') AS forest_submission_date_to_nodal_officer, DATE_FORMAT(lf.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS forest_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lf.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS forest_submission_date_to_regional_office_nagpur,"
					+ " DATE_FORMAT(lf.date_of_approval_by_regional_office_nagpur,'%d-%m-%Y') AS forest_date_of_approval_by_regional_office_nagpur, DATE_FORMAT(lf.valuation_by_dycfo,'%d-%m-%Y') AS forest_valuation_by_dycfo,cast(lf.demanded_amount as CHAR) as forest_demanded_amount,cast(lf.payment_amount  as CHAR) as forest_payment_amount, DATE_FORMAT(lf.approval_for_payment,'%d-%m-%Y') AS forest_approval_for_payment"
					+ ", DATE_FORMAT(lf.payment_date,'%d-%m-%Y') AS forest_payment_date,DATE_FORMAT(lf.possession_date,'%d-%m-%Y') AS forest_possession_date,lf.possession_status_fk as forest_possession_status_fk,"
					+ "lf.payment_status_fk as forest_payment_status_fk,cast(lf.area_acquired as CHAR) as forest_area_acquired,lf.special_feature as forest_special_feature, lf.attachment_No as forest_attachment_No ,lf.remarks as forest_remarks"
					
					+ " ,cast(lr.area_to_be_acquired as CHAR) as railway_area_to_be_acquired, cast(lr.area_acquired as CHAR) as railway_area_acquired,DATE_FORMAT(lr.online_submission,'%d-%m-%Y') AS railway_online_submission,"
					+ "DATE_FORMAT(lr.submission_date_to_DyCFO,'%d-%m-%Y') AS railway_submission_date_to_DyCFO, DATE_FORMAT(lr.submission_date_to_CCF_Thane,'%d-%m-%Y') AS railway_submission_date_to_CCF_Thane, DATE_FORMAT(lr.`submission_date_to_nodal_officer/CCF Nagpur`,'%d-%m-%Y') AS railway_submission_date_to_nodal_officer_CCF_Nagpur, "
					+ " DATE_FORMAT(lr.submission_date_to_revenue_secretary_mantralaya,'%d-%m-%Y') AS railway_submission_date_to_revenue_secretary_mantralaya, DATE_FORMAT(lr.submission_date_to_regional_office_nagpur,'%d-%m-%Y') AS railway_submission_date_to_regional_office_nagpur, DATE_FORMAT( lr.date_of_approval_by_Rregional_Office_agpur,'%d-%m-%Y') AS railway_date_of_approval_by_Rregional_Office_agpur,"
					+ "DATE_FORMAT(lr.valuation_by_DyCFO ,'%d-%m-%Y') AS railway_valuation_by_DyCFO, cast(lr.demanded_amount as CHAR) as railway_demanded_amount, DATE_FORMAT(lr.approval_for_payment,'%d-%m-%Y') AS railway_approval_for_payment, DATE_FORMAT(lr.payment_date,'%d-%m-%Y') AS railway_payment_date,cast(lr.payment_amount as CHAR) as railway_payment_amount, lr.payment_status as railway_payment_status, DATE_FORMAT(lr.possession_date,'%d-%m-%Y') AS railway_possession_date, lr.possession_status as railway_possession_status, "
					+ "lr.special_feature as railway_special_feature, lr.remarks, lr.`attachment_no.` as railway_attachment_no,lr.remarks as railway_remarks, "
					
					+ "cast(lpa.area_to_be_acquired as CHAR) as private_area_to_be_acquired, lpa.name_of_the_owner, lpa.basic_rate,lpa.attachment_no as private_attachment_no, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,"
					+ "lpa.forest_tree_rate,DATE_FORMAT(lpa.consent_from_owner,'%d-%m-%Y') AS consent_from_owner, DATE_FORMAT(lpa.legal_search_report,'%d-%m-%Y') AS legal_search_report, DATE_FORMAT(lpa.date_of_registration,'%d-%m-%Y') AS date_of_registration, DATE_FORMAT(lpa.date_of_possession,'%d-%m-%Y') AS date_of_possession, lpa.possession_status_fk, lpa.special_feature as private_special_feature, "
					+ "cast(lpa.area_acquired as CHAR) as private_area_acquired,cast(lpa.hundred_percent_Solatium as CHAR) as hundred_percent_Solatium,cast(lpa.extra_25_percent as CHAR) as extra_25_percent, cast(lpa.total_rate_divide_m2 as CHAR) as total_rate_divide_m2,cast(lpa.land_compensation as CHAR) as land_compensation, "
					+ "cast(lpa.agriculture_tree_compensation as CHAR) as agriculture_tree_compensation,cast(lpa.forest_tree_compensation as CHAR) as forest_tree_compensation,cast(lpa.structure_compensation as CHAR) as structure_compensation,cast(lpa.borewell_compensation as CHAR) as borewell_compensation,cast(lpa.total_compensation as CHAR) as total_compensation,lpa.remarks as private_remarks,"
					
					+ "DATE_FORMAT(lpv.forest_tree_survey ,'%d-%m-%Y') AS forest_tree_survey,DATE_FORMAT(lpv.forest_tree_valuation ,'%d-%m-%Y') AS forest_tree_valuation, lpv.forest_tree_valuation_status_fk,DATE_FORMAT(lpv.horticulture_tree_survey ,'%d-%m-%Y') AS horticulture_tree_survey,DATE_FORMAT(lpv.horticulture_tree_valuation ,'%d-%m-%Y') AS horticulture_tree_valuation, "
					+ "DATE_FORMAT(lpv.structure_survey ,'%d-%m-%Y') AS structure_survey,DATE_FORMAT(lpv.structure_valuation ,'%d-%m-%Y') AS structure_valuation,DATE_FORMAT(lpv.borewell_survey ,'%d-%m-%Y') AS borewell_survey,DATE_FORMAT(lpv.borewell_valuation ,'%d-%m-%Y') AS borewell_valuation, lpv.horticulture_tree_valuation_status_fk, lpv.structure_valuation_status_fk, "
					+ "lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, DATE_FORMAT(lpv.date_of_rfp_to_adtp ,'%d-%m-%Y') AS date_of_rfp_to_adtp,DATE_FORMAT(lpv.date_of_rate_fixation_of_land ,'%d-%m-%Y') AS date_of_rate_fixation_of_land, DATE_FORMAT(lpv.sdo_demand_for_payment ,'%d-%m-%Y') AS sdo_demand_for_payment,DATE_FORMAT(lpv.date_of_approval_for_payment ,'%d-%m-%Y') AS date_of_approval_for_payment, "
					+ "cast(lpv.payment_amount as CHAR) as payment_amount, DATE_FORMAT(lpv.payment_date ,'%d-%m-%Y') AS private_payment_date ,lpv.remarks as private_remarks "
					+"from la_land_identification li "
					+"left join la_government_land_acquisition lg on li.la_id = lg.la_id_fk " 
					+"left join la_forest_land_acquisition lf on li.la_id = lf.la_id_fk " 
					+"left join la_railway_land_acquisition lr on li.la_id = lr.la_id_fk " 
					+"left join la_private_land_acquisition lpa on li.la_id = lpa.la_id_fk " 
					+"left join la_private_land_valuation lpv on li.la_id = lpv.la_id_fk " 
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join work w on li.work_id_fk = w.work_id "
					+"left join project p on w.project_id_fk = p.project_id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					" where la_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_id())) {
				qry = qry + " and la_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_id())) {
				pValues[i++] = obj.getLa_id();
			}
			LADetails = (LandAcquisition)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	return LADetails;
	}

	@Override
	public boolean addLandAcquisition(LandAcquisition obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO la_land_identification"
					+ "( la_id, work_id_fk, survey_number, village_id, la_sub_category_fk, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector,"
					+ "area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, jm_start_date, jm_completion_date, "
					+ "jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues, attachment)"
					+ "VALUES"
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :id, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :is_there_issue, :attachment)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				String sub_category_of_land = getSubCategoryLand(obj.getId());
				obj.setSub_category_of_land(sub_category_of_land);
				if(!StringUtils.isEmpty(obj.getJm_approval())) {
					if(obj.getType_of_land().equalsIgnoreCase("Government")) {
						String govInsertQry = "INSERT INTO la_government_land_acquisition"
								+ "( la_id_fk, area_to_be_acquired, proposal_submission, proposal_submission_status_fk, valuation_date, letter_for_payment, amount_demanded, "
								+ "lfp_status_fk, approval_for_payment, payment_date, amount_paid, payment_status_fk, possession_date, possession_status_fk, special_feature,"
								+ "survey_number, type_of_land, sub_category_of_land, village_id, area_acquired, remarks)"
								+ "VALUES"
								+ "(:la_id, :area_to_be_acquired, :proposal_submission, :proposal_submission_status_fk, :valuation_date, :letter_for_payment,:amount_demanded, "
								+ " :lfp_status_fk, :approval_for_payment, :payment_date, :amount_paid, :payment_status_fk, :possession_date, :possession_status_fk, :special_feature, "
								+ " :survey_number, :type_of_land, :sub_category_of_land, :village_id, :area_acquired, :remarks)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(govInsertQry, paramSource);	
							
					}else if(obj.getType_of_land().equalsIgnoreCase("Forest")) {
						String forestInsertSubQry = "INSERT INTO la_forest_land_acquisition "
						 		+ "( la_id_fk, area_to_be_acquired, on_line_submission, submission_date_to_dycfo, submission_date_to_ccf_thane, submission_date_to_nodal_officer, "
						 		+ "submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur, valuation_by_dycfo, "
						 		+ "demanded_amount, payment_amount, approval_for_payment, payment_date, possession_date, possession_status_fk, payment_status_fk, special_feature, survey_number,"
						 		+ " type_of_land, sub_category_of_land, village_id, area_acquired, remarks, attachment_No, issues)"
						 		+ "VALUES"
						 		+ "( :la_id, :forest_area_to_be_acquired, :forest_online_submission, :forest_submission_date_to_dycfo, :forest_submission_date_to_ccf_thane, :forest_submission_date_to_nodal_officer, "
						 		+ ":forest_submission_date_to_revenue_secretary_mantralaya, :forest_submission_date_to_regional_office_nagpur, :forest_date_of_approval_by_regional_office_nagpur, :forest_valuation_by_dycfo,"
						 		+ ":forest_demanded_amount, :forest_payment_amount, :forest_approval_for_payment, :forest_payment_date, :forest_possession_date, :forest_possession_status_fk, :forest_payment_status_fk, :forest_special_feature, :survey_number, "
						 		+ ":type_of_land, :sub_category_of_land, :village_id, :forest_area_acquired, :remarks, :forest_attachment_No, :is_there_issue)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);	
						
					}else if(obj.getType_of_land().equalsIgnoreCase("Railway")) {
						String railwayInsertSubQry = " INSERT INTO la_railway_land_acquisition"
						 		+ "(la_id_fk, survey_number, type_of_land, sub_category_of_land, village_id, area_to_be_acquired, area_acquired, online_submission, submission_date_to_DyCFO, "
						 		+ "submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`, submission_date_to_revenue_secretary_mantralaya, "
						 		+ "submission_date_to_regional_office_nagpur, date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO, demanded_amount, "
						 		+ "approval_for_payment, payment_date, payment_amount, payment_status, possession_date, possession_status, special_feature, remarks, "
						 		+ "`attachment_no.`, Issues)"
						 		+ "VALUES"
						 		+ "(:la_id, :survey_number, :type_of_land, :sub_category_of_land, :village_id, :railway_area_to_be_acquired, :railway_area_acquired, :railway_online_submission, :railway_submission_date_to_DyCFO, "
						 		+ ":railway_submission_date_to_CCF_Thane, :railway_submission_date_to_nodal_officer_CCF_Nagpur, :railway_submission_date_to_revenue_secretary_mantralaya, "
						 		+ ":railway_submission_date_to_regional_office_nagpur, :railway_date_of_approval_by_Rregional_Office_agpur, :railway_valuation_by_DyCFO, :railway_demanded_amount, "
						 		+ ":railway_approval_for_payment, :railway_payment_date, :railway_payment_amount, :railway_payment_status, :railway_possession_date, :railway_possession_status, :railway_special_feature, :remarks, "
						 		+ " :railway_attachment_no, :is_there_issue)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(railwayInsertSubQry, paramSource);	
							
					}else if(obj.getType_of_land().equalsIgnoreCase("Private")) {
						String  privateInsertQry = "INSERT INTO la_private_land_acquisition "
						 		+ "(la_id_fk, area_to_be_acquired, name_of_the_owner, basic_rate, attachment_no, agriculture_tree_nos, agriculture_tree_rate, forest_tree_nos,"
						 		+ " forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, possession_status_fk, special_feature, "
						 		+ "survey_number, type_of_land, sub_category_of_land, village_id, area_acquired, hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2,"
						 		+ " land_compensation, agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation, remarks, issues)"
						 		+ "VALUES"
						 		+ "(:la_id, :private_area_to_be_acquired, :name_of_the_owner, :basic_rate, :private_attachment_no, :agriculture_tree_nos, :agriculture_tree_rate, :forest_tree_nos,"
						 		+ " :forest_tree_rate, :consent_from_owner, :legal_search_report, :date_of_registration, :date_of_possession, :private_possession_status_fk, :private_special_feature, "
						 		+ " :survey_number, :type_of_land, :sub_category_of_land, :village_id, :private_area_acquired, :hundred_percent_Solatium, :extra_25_percent, :total_rate_divide_m2,"
						 		+ " :land_compensation, :agriculture_tree_compensation, :forest_tree_compensation, :structure_compensation, :borewell_compensation, :total_compensation, :remarks, :is_there_issue)";
						 
						 	paramSource = new BeanPropertySqlParameterSource(obj);		 
							count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);
							
						String privateInsertSubQry = "INSERT INTO la_private_land_valuation "
						 		+ "(la_id_fk, forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, "
						 		+ "structure_survey, structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, "
						 		+ "borewell_valuation_status_fk, rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, "
						 		+ "date_of_approval_for_payment, payment_amount, payment_date, survey_number, type_of_land, sub_category_of_land, village_id, remarks, issues)"
						 		+ "VALUES"
						 		+ "(:la_id, :forest_tree_survey, :forest_tree_valuation, :forest_tree_valuation_status_fk, :horticulture_tree_survey, :horticulture_tree_valuation,"
						 		+ " :structure_survey, :structure_valuation, :borewell_survey, :borewell_valuation, :horticulture_tree_valuation_status_fk, :structure_valuation_status_fk,"
						 		+ " :borewell_valuation_status_fk, :rfp_to_adtp_status_fk, :date_of_rfp_to_adtp, :date_of_rate_fixation_of_land, :sdo_demand_for_payment,"
						 		+ " :date_of_approval_for_payment, :payment_amount, :private_payment_date, :survey_number, :type_of_land, :sub_category_of_land,:village_id,:remarks, :is_there_issue)";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);	
						}
					
				}
				if(flag) {
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
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
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
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getProjectsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
		throw new Exception(e.getMessage());
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
					+ "jm_sheet_date_to_sdo= :jm_sheet_date_to_sdo, jm_remarks= :jm_remarks, jm_approval= :jm_approval, issues= :is_there_issue, attachment= :attachment "
					+ "where la_id= :la_id ";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				if(!StringUtils.isEmpty(obj.getJm_approval())) {
					if(obj.getType_of_land().equalsIgnoreCase("Government")) {
						String govInsertQry = "UPDATE  la_government_land_acquisition SET "
								+ "area_to_be_acquired= :area_to_be_acquired, proposal_submission= :proposal_submission, proposal_submission_status_fk= :proposal_submission_status_fk, valuation_date= :valuation_date, letter_for_payment= :letter_for_payment, amount_demanded= :amount_demanded, "
								+ "lfp_status_fk= :lfp_status_fk, approval_for_payment= :approval_for_payment, payment_date= :payment_date, amount_paid= :amount_paid, payment_status_fk= :payment_status_fk, possession_date= :possession_date, possession_status_fk= :possession_status_fk, special_feature= :special_feature,"
								+ "survey_number= :survey_number,village_id= :village_id, area_acquired= :area_acquired, remarks= :remarks "
								+ "where  la_id_fk= :la_id";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(govInsertQry, paramSource);	
							
					}else if(obj.getType_of_land().equalsIgnoreCase("Forest")) {
						String forestInsertSubQry = "UPDATE la_forest_land_acquisition SET "
						 		+ "area_to_be_acquired= :forest_area_to_be_acquired, on_line_submission= :forest_online_submission, submission_date_to_dycfo= :forest_submission_date_to_dycfo, submission_date_to_ccf_thane= :forest_submission_date_to_ccf_thane, submission_date_to_nodal_officer= :forest_submission_date_to_nodal_officer, "
						 		+ "submission_date_to_revenue_secretary_mantralaya= :forest_submission_date_to_revenue_secretary_mantralaya, submission_date_to_regional_office_nagpur= :forest_submission_date_to_regional_office_nagpur, date_of_approval_by_regional_office_nagpur= :forest_date_of_approval_by_regional_office_nagpur, valuation_by_dycfo= :forest_valuation_by_dycfo, "
						 		+ "demanded_amount= :forest_demanded_amount, payment_amount= :forest_payment_amount, approval_for_payment= :forest_approval_for_payment, payment_date= :forest_payment_date, possession_date= :forest_possession_date, possession_status_fk= :forest_possession_status_fk, payment_status_fk=:forest_payment_status_fk, special_feature= :forest_special_feature, survey_number= :survey_number,"
						 		+ "village_id= :village_id, area_acquired= :forest_area_acquired, remarks= :remarks, attachment_No= :forest_attachment_No, issues= :is_there_issue   "
						 		+ " where la_id_fk= :la_id ";
	
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(forestInsertSubQry, paramSource);	
						
					}else if(obj.getType_of_land().equalsIgnoreCase("Railway")) {
						String railwayInsertSubQry = " UPDATE la_railway_land_acquisition SET "
						 		+ "survey_number= :survey_number, village_id= :village_id, area_to_be_acquired= :railway_area_to_be_acquired, area_acquired= :railway_area_acquired, online_submission= :railway_online_submission, submission_date_to_DyCFO= :railway_submission_date_to_DyCFO, "
						 		+ "submission_date_to_CCF_Thane= :railway_submission_date_to_CCF_Thane, `submission_date_to_nodal_officer/CCF Nagpur`= :railway_submission_date_to_nodal_officer_CCF_Nagpur, submission_date_to_revenue_secretary_mantralaya= :railway_submission_date_to_revenue_secretary_mantralaya,  "
						 		+ "submission_date_to_regional_office_nagpur= :railway_submission_date_to_regional_office_nagpur,date_of_approval_by_Rregional_Office_agpur= :railway_date_of_approval_by_Rregional_Office_agpur, valuation_by_DyCFO= :railway_valuation_by_DyCFO, demanded_amount= :railway_demanded_amount, "
						 		+ "approval_for_payment= :railway_approval_for_payment, payment_date= :railway_payment_date, payment_amount= :railway_payment_amount, payment_status= :railway_payment_status, possession_date= :railway_possession_date, possession_status= :railway_possession_status, special_feature= :railway_special_feature, remarks= :remarks, "
						 		+ "`attachment_no.`= :railway_attachment_no, Issues= :is_there_issue  "
						 		+ "where la_id_fk= :la_id ";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(railwayInsertSubQry, paramSource);	
							
					}else if(obj.getType_of_land().equalsIgnoreCase("Private")) {
						String  privateInsertQry = "UPDATE la_private_land_acquisition SET "
						 		+ "area_to_be_acquired= :private_area_to_be_acquired, name_of_the_owner= :name_of_the_owner, basic_rate= :basic_rate, attachment_no= :private_attachment_no, agriculture_tree_nos= :agriculture_tree_nos, agriculture_tree_rate= :agriculture_tree_rate, forest_tree_nos= :forest_tree_nos,"
						 		+ "forest_tree_rate= :forest_tree_rate, consent_from_owner= :consent_from_owner, legal_search_report= :legal_search_report, date_of_registration= :date_of_registration, date_of_possession= :date_of_possession, possession_status_fk= :private_possession_status_fk, special_feature= :private_special_feature, "
						 		+ "survey_number= :survey_number,village_id= :village_id, area_acquired= :private_area_acquired, hundred_percent_Solatium= :hundred_percent_Solatium, extra_25_percent= :extra_25_percent, total_rate_divide_m2= :total_rate_divide_m2,"
						 		+ "land_compensation= :land_compensation, agriculture_tree_compensation= :agriculture_tree_compensation, forest_tree_compensation= :forest_tree_compensation, structure_compensation= :structure_compensation, borewell_compensation= :borewell_compensation, total_compensation= :total_compensation, remarks= :remarks, issues= :is_there_issue "
						 		+ "where la_id_fk= :la_id";
						 
						 	paramSource = new BeanPropertySqlParameterSource(obj);		 
							count = namedParamJdbcTemplate.update(privateInsertQry, paramSource);
							
						String privateInsertSubQry = "UPDATE la_private_land_valuation SET "
						 		+ " forest_tree_survey=:forest_tree_survey, forest_tree_valuation= :forest_tree_valuation, forest_tree_valuation_status_fk= :forest_tree_valuation_status_fk, horticulture_tree_survey= :horticulture_tree_survey, horticulture_tree_valuation= :horticulture_tree_valuation, "
						 		+ "structure_survey= :structure_survey, structure_valuation= :structure_valuation, borewell_survey= :borewell_survey, borewell_valuation= :borewell_valuation, horticulture_tree_valuation_status_fk= :horticulture_tree_valuation_status_fk, structure_valuation_status_fk= :structure_valuation_status_fk, "
						 		+ "borewell_valuation_status_fk=:borewell_valuation_status_fk, rfp_to_adtp_status_fk= :rfp_to_adtp_status_fk, date_of_rfp_to_adtp= :date_of_rfp_to_adtp, date_of_rate_fixation_of_land= :date_of_rate_fixation_of_land, sdo_demand_for_payment= :sdo_demand_for_payment, "
						 		+ "date_of_approval_for_payment= :date_of_approval_for_payment, payment_amount= :payment_amount, payment_date= :private_payment_date, survey_number= :survey_number, type_of_land= :type_of_land, sub_category_of_land= :sub_category_of_land, village_id= :village_id, remarks= :remarks, issues= :is_there_issue "
						 		+ "where la_id_fk= :la_id";
						 paramSource = new BeanPropertySqlParameterSource(obj);		 
						 count = namedParamJdbcTemplate.update(privateInsertSubQry, paramSource);	
						}
					
				}
				if(flag) {
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
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public List<LandAcquisition> getLandAcquisitionProjectsList(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT w.project_id_fk,p.project_name from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id_fk = w.work_id "+
					"left join project p on w.project_id_fk = p.project_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where li.work_id_fk is not null and li.work_id_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
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
			qry = qry + "GROUP BY w.project_id_fk ";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


}
