package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			String qry ="select la_id,survey_number,li.work_id_fk,w.work_name,c.la_category as type_of_land ,sc.la_sub_category as sub_category_of_land, w.work_short_name,village_id,la_sub_category_fk,village,area_of_plot " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id_fk = w.work_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "
					+"where la_id is not null  ";
			int arrSize = 0;
			
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
					"LEFT JOIN work w on li.work_id_fk = w.work_id "
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where li.work_id_fk is not null and li.work_id_fk <> '' ";
			int arrSize = 0;
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
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where village is not null and village <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where c.la_category is not null and c.la_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
					"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
					+"left join la_category c on sc.la_category_fk = c.la_category "+
					"where la_sub_category is not null and la_sub_category <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception {					//DATE_FORMAT(date_of_start,'%d-%m-%Y') AS
		LandAcquisition LADetails = null;
		try {
			String qry = "select la_id,li.attachment, work_id_fk,w.project_id_fk,p.project_name,w.work_short_name, li.survey_number, li.village_id,c.la_category as type_of_land ,sc.la_sub_category as sub_category_of_land, li.village, taluka, dy_slr, sdo, collector, DATE_FORMAT(proposal_submission_date_to_collector,'%d-%m-%Y') AS proposal_submission_date_to_collector, area_of_plot, jm_fee_amount, chainage_from, chainage_to, DATE_FORMAT(jm_fee_letter_received_date,'%d-%m-%Y') AS jm_fee_letter_received_date,DATE_FORMAT(jm_fee_paid_date,'%d-%m-%Y') AS jm_fee_paid_date,DATE_FORMAT(jm_start_date,'%d-%m-%Y') AS  jm_start_date,DATE_FORMAT(jm_completion_date,'%d-%m-%Y') AS jm_completion_date, DATE_FORMAT(jm_sheet_date_to_sdo,'%d-%m-%Y') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues"
					
					+ ",lg.id, lg.la_id_fk, lg.area_to_be_acquired,DATE_FORMAT(lg.proposal_submission,'%d-%m-%Y') AS proposal_submission, lg.proposal_submission_status_fk, DATE_FORMAT(lg.valuation_date,'%d-%m-%Y') AS valuation_date, lg.letter_for_payment,"
					+ "lg.amount_demanded, lg.lfp_status_fk,DATE_FORMAT(lg.approval_for_payment,'%d-%m-%Y') AS approval_for_payment,DATE_FORMAT(lg.payment_date,'%d-%m-%Y') AS payment_date, lg.amount_paid, lg.payment_status_fk, DATE_FORMAT(lg.possession_date,'%d-%m-%Y') AS possession_date,"
					+ "lg.possession_status_fk, lg.special_feature, lg.area_acquired, "
					
					+ "lf.area_to_be_acquired as forest_area_to_be_acquired, lf.on_line_submission as forest_online_submission, lf.submission_date_to_dycfo as forest_submission_date_to_dycfo, lf.submission_date_to_ccf_thane as forest_submission_date_to_ccf_thane, "
					+ "lf.submission_date_to_nodal_officer as forest_submission_date_to_nodal_officer, lf.submission_date_to_revenue_secretary_mantralaya as forest_submission_date_to_revenue_secretary_mantralaya,lf.submission_date_to_regional_office_nagpur as forest_submission_date_to_regional_office_nagpur,"
					+ "lf.date_of_approval_by_regional_office_nagpur as forest_date_of_approval_by_regional_office_nagpur,lf.valuation_by_dycfo as forest_valuation_by_dycfo, lf.demanded_amount as forest_demanded_amount,lf.payment_amount as forest_payment_amount,lf.approval_for_payment as forest_approval_for_payment"
					+ ",lf.payment_date as forest_payment_date,lf.possession_date as forest_payment_date,lf.possession_status_fk as forest_possession_status_fk,"
					+ "lf.payment_status_fk as forest_payment_status_fk,lf.area_acquired as forest_area_acquired,lf.special_feature as forest_special_feature, lf.attachment_No as forest_attachment_No "
					
					+ " ,lr.area_to_be_acquired as railway_area_to_be_acquired, lr.area_acquired as railway_area_acquired, lr.online_submission as railway_online_submission,"
					+ "lr.submission_date_to_DyCFO as railway_submission_date_to_DyCFO, lr.submission_date_to_CCF_Thane as railway_submission_date_to_CCF_Thane, lr.`submission_date_to_nodal_officer/CCF Nagpur` as railway_submission_date_to_nodal_officer_CCF_Nagpur, "
					+ "lr.submission_date_to_revenue_secretary_mantralaya as railway_submission_date_to_revenue_secretary_mantralaya, lr.submission_date_to_regional_office_nagpur as railway_submission_date_to_regional_office_nagpur, lr.date_of_approval_by_Rregional_Office_agpur as railway_date_of_approval_by_Rregional_Office_agpur,"
					+ "lr.valuation_by_DyCFO as railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, lr.approval_for_payment as railway_approval_for_payment, lr.payment_date as railway_payment_date, lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, lr.possession_date as railway_possession_date, lr.possession_status as railway_possession_status, "
					+ "lr.special_feature as railway_special_feature, lr.remarks, lr.`attachment_no.` as railway_attachment_no, "
					
					+ "lpa.area_to_be_acquired as private_area_to_be_acquired, lpa.name_of_the_owner, lpa.basic_rate,lpa.attachment_no as private_attachment_no, lpa.agriculture_tree_nos, lpa.agriculture_tree_rate, lpa.forest_tree_nos,"
					+ "lpa.forest_tree_rate, lpa.consent_from_owner, lpa.legal_search_report, lpa.date_of_registration, lpa.date_of_possession, lpa.possession_status_fk, lpa.special_feature as private_special_feature, "
					+ "lpa.area_acquired as private_area_acquired, lpa.hundred_percent_Solatium, lpa.extra_25_percent, lpa.total_rate_divide_m2, lpa.land_compensation, "
					+ "lpa.agriculture_tree_compensation, lpa.forest_tree_compensation, lpa.structure_compensation, lpa.borewell_compensation, lpa.total_compensation,"
					
					+ "lpv.forest_tree_survey, lpv.forest_tree_valuation, lpv.forest_tree_valuation_status_fk, lpv.horticulture_tree_survey, lpv.horticulture_tree_valuation, "
					+ "lpv.structure_survey, lpv.structure_valuation, lpv.borewell_survey, lpv.borewell_valuation, lpv.horticulture_tree_valuation_status_fk, lpv.structure_valuation_status_fk, "
					+ "lpv.borewell_valuation_status_fk, lpv.rfp_to_adtp_status_fk, lpv.date_of_rfp_to_adtp, lpv.date_of_rate_fixation_of_land, lpv.sdo_demand_for_payment, lpv.date_of_approval_for_payment, "
					+ "lpv.payment_amount, lpv.payment_date as private_payment_date "
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
					+ "(:la_id, :work_id_fk, :survey_number, :village_id, :la_sub_category_fk, :village, :taluka, :dy_slr, :sdo, :collector, :proposal_submission_date_to_collector, "
					+ ":area_of_plot, :jm_fee_amount, :chainage_from, :chainage_to, :jm_fee_letter_received_date, :jm_fee_paid_date, :jm_start_date, :jm_completion_date, "
					+ ":jm_sheet_date_to_sdo, :jm_remarks, :jm_approval, :is_there_issue, :attachment)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
				if(obj.getType_of_land().equals("Government")) {
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
						
				}else if(obj.getType_of_land().equals("Forest")) {
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
					
				}else if(obj.getType_of_land().equals("Railway")) {
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
						
				}else if(obj.getType_of_land().equals("Private")) {
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
				
				
				if(flag) {
					if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
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
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
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
			String qry = "select la_category as type_of_land, ls.la_sub_category as sub_category_of_land from `la_category` lc "
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


}
