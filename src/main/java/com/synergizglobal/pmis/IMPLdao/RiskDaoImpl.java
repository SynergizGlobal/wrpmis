package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.RiskDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Work;
@Repository
public class RiskDaoImpl implements RiskDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Risk> getRiskList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select id, work_id_fk, risk_id, identification_date, area, area_item_no, sub_area, sub_area_item_no, "
					+ "revision_id, assessment_date, max_assessment_date, priority, probability, impact, risk_rating, classification, owner, "
					+ "responsible_person, mitigation_plan, action_taken, atr_date from risk_view rv " + 
					"left join work w on rv.work_id_fk = w.work_id  where assessment_date = max_assessment_date ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Risk> getRisktWorksList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_short_name from risk_view rv " + 
					"LEFT JOIN work w on rv.work_id_fk = w.work_id "+
					"where work_id_fk is not null and assessment_date = max_assessment_date and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			qry = qry + " group by work_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getRiskAreasList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT area from risk_view rv " + 
					"where area is not null and assessment_date = max_assessment_date and area <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			qry = qry + " group by area";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getRiskPriotityList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT priority from risk_view rv " + 
					"where priority is not null and assessment_date = max_assessment_date and priority <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			qry = qry + " group by priority";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getRiskClassificationsList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT classification from risk_view rv " + 
					"where classification is not null and assessment_date = max_assessment_date and classification <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			qry = qry + " group by classification";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getRiskResponsiblePersonsFilterList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT responsible_person from risk_view rv " + 
					"where responsible_person is not null and assessment_date = max_assessment_date and responsible_person <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				qry = qry + " and classification = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				qry = qry + " and priority = ?";
				arrSize++;
			}	
			
			qry = qry + " group by responsible_person";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getClassification())) {
				pValues[i++] = obj.getClassification();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority())) {
				pValues[i++] = obj.getPriority();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getPrioritiesList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select priority as priority_fk from risk_view ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getProjectList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select project_id as project_id_fk,project_name from project ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getSubAreasList(Risk obj) throws Exception {
		List<Risk> objsList = new ArrayList<Risk>();
		try {
			String qry = "select sub_area as sub_area_fk, risk_area_fk, rsa.item_no from `risk_sub_area` rsa "
					+ "LEFT OUTER JOIN `risk_area` ra ON risk_area_fk = area ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " where area = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_area_fk())) {
				qry = qry + " where sub_area  = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_area_fk())) {
				pValues[i++] = obj.getSub_area_fk();
			}	
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Risk> getAreasList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select area, item_no from risk_area ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getAreaList(Risk obj) throws Exception {
		List<Risk> objsList = new ArrayList<Risk>();
		try {
			String qry = "select area, ra.item_no from `risk_area` ra "
					+ "LEFT OUTER JOIN `risk_sub_area` rsa ON area  = risk_area_fk ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_area_fk())) {
				qry = qry + " where sub_area  = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " where area = ?";
				arrSize++;
			}
			qry = qry + " group by area";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_area_fk())) {
				pValues[i++] = obj.getSub_area_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Risk> getSubAreasList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select sub_area as sub_area_fk,risk_area_fk, item_no from risk_sub_area ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Risk getRiskDetails(Risk obj) throws Exception {
		Risk sObj =null;
		
		try {
			String qry = "select risk_id, work_id_fk,w.work_name,p.project_name,rv.sub_area, identification_date from risk_view rv "
					+ "LEFT JOIN risk_sub_area rsa on rv.sub_area = rsa.sub_area "
					+ "Left JOIN risk_area ra on rsa.risk_area_fk = ra.area "
					+ "LEFT join work w on rv.work_id_fk = w.work_id " + 
					" LEFT join project p on w.project_id_fk = p.project_id "
					+"where risk_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRisk_id())) {
				qry = qry + " and risk_id= ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRisk_id())) {
				pValues[i++] = obj.getRisk_id();
			}
			sObj = (Risk)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id())) {
				List<Risk> objsList = null;
				String qryDetails = "select risk_id,owner,responsible_person,mitigation_plan,priority,"
						+"probability,impact, DATE_FORMAT(assessment_date,'%d-%m-%Y') AS assessment_date  "
						+ "from risk_view "
						+"where work_id_fk = ? ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getWork_id_fk()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
				sObj.setRisks(objsList); 
			}
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id())) {
				for (Risk session : sObj.getRisks()) {
					List<Risk> objsList = null;
					String qryDetails = "select risk_id, action_taken, DATE_FORMAT(atr_date,'%d-%m-%Y') AS atr_date " + 
							"from risk_view "
							+"where  risk_id = ? ";
					
					objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getRisk_id()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
					session.setRiskActions(objsList); 
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

}
