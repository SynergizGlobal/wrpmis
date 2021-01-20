package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LandAcquisitionDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.LandAcquisition;

@Repository
public class LandAcquisitionDaoImpl implements LandAcquisitionDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

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
			String qry = "select la_id, work_id_fk, li.survey_number, li.village_id,c.la_category as type_of_land ,sc.la_sub_category as sub_category_of_land, li.village, taluka, dy_slr, sdo, collector, DATE_FORMAT(proposal_submission_date_to_collector,'%d-%m-%Y') AS proposal_submission_date_to_collector, area_of_plot, jm_fee_amount, chainage_from, chainage_to, DATE_FORMAT(jm_fee_letter_received_date,'%d-%m-%Y') AS jm_fee_letter_received_date,DATE_FORMAT(jm_fee_paid_date,'%d-%m-%Y') AS jm_fee_paid_date,DATE_FORMAT(jm_start_date,'%d-%m-%Y') AS  jm_start_date,DATE_FORMAT(jm_completion_date,'%d-%m-%Y') AS jm_completion_date, DATE_FORMAT(jm_sheet_date_to_sdo,'%d-%m-%Y') AS jm_sheet_date_to_sdo, jm_remarks, jm_approval, li.issues"
					
					+ ",lg.id, lg.la_id_fk, lg.area_to_be_acquired,DATE_FORMAT(lg.proposal_submission,'%d-%m-%Y') AS proposal_submission, lg.proposal_submission_status_fk, DATE_FORMAT(lg.valuation_date,'%d-%m-%Y') AS valuation_date, lg.letter_for_payment,"
					+ " lg.amount_demanded, lg.lfp_status_fk,DATE_FORMAT(lg.approval_for_payment,'%d-%m-%Y') AS approval_for_payment,DATE_FORMAT(lg.payment_date,'%d-%m-%Y') AS payment_date, lg.amount_paid, lg.payment_status_fk, DATE_FORMAT(lg.possession_date,'%d-%m-%Y') AS possession_date,"
					+ " lg.possession_status_fk, lg.special_feature, lg.area_acquired, "
					
					+ " lf.area_to_be_acquired as forest_area_to_be_acquired, lf.on_line_submission as forest_online_submission, lf.submission_date_to_dycfo as forest_submission_date_to_dycfo, lf.submission_date_to_ccf_thane as forest_submission_date_to_ccf_thane, "
					+ "lf.submission_date_to_nodal_officer as forest_submission_date_to_nodal_officer, lf.submission_date_to_revenue_secretary_mantralaya as forest_submission_date_to_revenue_secretary_mantralaya,lf.submission_date_to_regional_office_nagpur as forest_submission_date_to_regional_office_nagpur,"
					+ "lf.date_of_approval_by_regional_office_nagpur as forest_date_of_approval_by_regional_office_nagpur,lf.valuation_by_dycfo as forest_valuation_by_dycfo, lf.demanded_amount as forest_demanded_amount,lf.payment_amount as forest_payment_amount,lf.approval_for_payment as forest_approval_for_payment"
					+ ",lf.payment_date as forest_payment_date,lf.possession_date as forest_payment_date,lf.possession_status_fk as forest_possession_status_fk,"
					+ "lf.payment_status_fk as forest_payment_status_fk,lf.area_acquired as forest_area_acquired,lf.special_feature as forest_special_feature, lf.attachment_No as forest_attachment_No "
					
					+ " ,lr.area_to_be_acquired as railway_area_to_be_acquired, lr.area_acquired as railway_area_acquired, lr.online_submission as railway_online_submission,"
					+ " lr.submission_date_to_DyCFO as railway_submission_date_to_DyCFO, lr.submission_date_to_CCF_Thane as railway_submission_date_to_CCF_Thane, lr.`submission_date_to_nodal_officer/CCF Nagpur` as railway_submission_date_to_nodal_officer_CCF_Nagpur, "
					+ "lr.submission_date_to_revenue_secretary_mantralaya as railway_submission_date_to_revenue_secretary_mantralaya, lr.submission_date_to_regional_office_nagpur as railway_submission_date_to_regional_office_nagpur, lr.date_of_approval_by_Rregional_Office_agpur as railway_date_of_approval_by_Rregional_Office_agpur,"
					+ " lr.valuation_by_DyCFO as railway_valuation_by_DyCFO, lr.demanded_amount as railway_demanded_amount, lr.approval_for_payment as railway_approval_for_payment, lr.payment_date as railway_payment_date, lr.payment_amount as railway_payment_amount, lr.payment_status as railway_payment_status, lr.possession_date as railway_possession_date, lr.possession_status as railway_possession_status, "
					+ "lr.special_feature as railway_special_feature, lr.remarks, lr.`attachment_no.` as railway_attachment_no, "
					
					+ " lpa.area_to_be_acquired as private_area_to_be_acquired, name_of_the_owner, basic_rate, lpa.attachment_no as private_attachment_no, agriculture_tree_nos, agriculture_tree_rate, forest_tree_nos,"
					+ " forest_tree_rate, consent_from_owner, legal_search_report, date_of_registration, date_of_possession, possession_status_fk, lpa.special_feature as private_special_feature, "
					+ " lpa.area_acquired as private_area_acquired, hundred_percent_Solatium, extra_25_percent, total_rate_divide_m2, land_compensation, "
					+ "agriculture_tree_compensation, forest_tree_compensation, structure_compensation, borewell_compensation, total_compensation,"
					+ "id, la_id_fk, forest_tree_survey, forest_tree_valuation, forest_tree_valuation_status_fk, horticulture_tree_survey, horticulture_tree_valuation, "
					+ "structure_survey, structure_valuation, borewell_survey, borewell_valuation, horticulture_tree_valuation_status_fk, structure_valuation_status_fk, "
					+ "borewell_valuation_status_fk, rfp_to_adtp_status_fk, date_of_rfp_to_adtp, date_of_rate_fixation_of_land, sdo_demand_for_payment, date_of_approval_for_payment, "
					+ "payment_amount, lpv.payment_date as private_payment_date "
					+"from la_land_identification li "
					+"left join la_government_land_acquisition lg on li.la_id = lg.la_id_fk " 
					+"left join la_forest_land_acquisition lf on li.la_id = lf.la_id_fk " 
					+"left join la_railway_land_acquisition lr on li.la_id = lr.la_id_fk " 
					+"left join la_private_land_acquisition lpa on li.la_id = lpa.la_id_fk " 
					+"left join la_private_land_valuation lpv on li.la_id = lpv.la_id_fk " 
					+"left join la_sub_category sc on li.la_sub_category_fk = sc.id "
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
	
}
