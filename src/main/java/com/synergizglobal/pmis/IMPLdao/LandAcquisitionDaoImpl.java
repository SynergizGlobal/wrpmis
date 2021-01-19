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
			String qry ="select la_id,survey_number,li.work_id as work_id_fk,w.work_name,w.work_short_name,village_id,type_of_land,sub_category_of_land,village,area_of_plot " + 
					" from la_land_identification li " + 
					"left join work w on li.work_id = w.work_id where la_id is not null  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and type_of_land = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sub_category_of_land = ?";
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
			String qry = "SELECT li.work_id as work_id_fk,w.work_name,w.work_short_name from la_land_identification li " + 
					"LEFT JOIN work w on li.work_id = w.work_id "+
					"where li.work_id is not null and li.work_id <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and li.work_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and type_of_land = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sub_category_of_land = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY li.work_id ";
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
			String qry = "SELECT village from la_land_identification " + 
					"where village is not null and village <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and type_of_land = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sub_category_of_land = ?";
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
			String qry = "SELECT type_of_land from la_land_identification  " + 
					"where type_of_land is not null and type_of_land <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and type_of_land = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sub_category_of_land = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY type_of_land ";
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
			String qry = "SELECT sub_category_of_land from la_land_identification " + 
					"where sub_category_of_land is not null and sub_category_of_land <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getVillage())) {
				qry = qry + " and village = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_land())) {
				qry = qry + " and type_of_land = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_category_of_land())) {
				qry = qry + " and sub_category_of_land = ?";
				arrSize++;
			}	
			qry = qry + "GROUP BY sub_category_of_land ";
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
	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception {
		LandAcquisition LADetails = null;
		try {
			String qry = "select la_id, work_id as work_id_fk, survey_number, village_id, type_of_land, sub_category_of_land, village, taluka, dy_slr, sdo, collector, proposal_submission_date_to_collector, area_of_plot, jm_fee_amount, chainage_from, chainage_to, jm_fee_letter_received_date, jm_fee_paid_date, jm_start_date, jm_completion_date, jm_sheet_date_to_sdo, jm_remarks, jm_approval, issues"
					+ " from la_land_identification li" + 
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
