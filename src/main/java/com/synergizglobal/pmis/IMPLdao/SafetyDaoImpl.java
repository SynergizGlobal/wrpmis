package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.SafetyDao;
import com.synergizglobal.pmis.model.Safety;

@Repository
public class SafetyDaoImpl implements SafetyDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Safety> getSafetyList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,s.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,"
					+ "investigation_completed,corrective_measure_short_term,corrective_measure_long_term,status_remark_fk,compensation,payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where safety_id is not null " ;
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				qry = qry + " and impact_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				qry = qry + " and root_cause_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				pValues[i++] = obj.getImpact_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				pValues[i++] = obj.getRoot_cause_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyStatusList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select status from safety_status";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyImpactList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select impact from safety_impact";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyCategoryList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select category from safety_category";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyRootCauseList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select root_cause from safety_root_cause";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addSafety(Safety obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO safety"
					+ "(contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,category_fk,impact_fk,root_cause_fk,status_fk,"
					+ "closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,investigation_completed,corrective_measure_short_term,"
					+ "corrective_measure_long_term,status_remark_fk,compensation,payment_date,remarks) "
					+ "VALUES "
					+ "(:contract_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:department_fk,:category_fk,:impact_fk,:root_cause_fk,:status_fk,:"
					+ "closure_date,:lti_hours,:equipment_impact,:people_impact,:work_impact,:committee_formed_fk,:investigation_completed,:corrective_measure_short_term,:"
					+ "corrective_measure_long_term,:status_remark_fk,:compensation,:payment_date,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public Safety getSafety(Safety obj) throws Exception {
		Safety sobj = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,s.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,"
					+ "investigation_completed,corrective_measure_short_term,corrective_measure_long_term,status_remark_fk,compensation,payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where safety_id = ? " ;
			
			int arrSize = 1;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				qry = qry + " and impact_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				qry = qry + " and root_cause_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getSafety_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				pValues[i++] = obj.getImpact_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				pValues[i++] = obj.getRoot_cause_fk();
			}
			sobj = (Safety)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sobj;
	}

	@Override
	public boolean updateSafety(Safety obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE safety SET contract_id_fk=:contract_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,department_fk=:department_fk,category_fk=:category_fk,impact_fk=:impact_fk,root_cause_fk=:root_cause_fk,status_fk=:status_fk,"
					+ "closure_date=:closure_date,lti_hours=:lti_hours,equipment_impact=:equipment_impact,people_impact=:people_impact,work_impact=:work_impact,committee_formed_fk=:committee_formed_fk,investigation_completed=:investigation_completed,corrective_measure_short_term=:corrective_measure_short_term,"
					+ "corrective_measure_long_term=:corrective_measure_long_term,status_remark_fk=:status_remark_fk,compensation=:compensation,payment_date=:payment_date,remarks=:remarks "
					+ "WHERE safety_id = :safety_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteSafety(Safety obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
