package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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
			String qry ="select id,r.risk_id_pk, rv.work_id_fk, rv.risk_id, rv.identification_date, area, area_item_no, sub_area, sub_area_item_no, "
					+ "revision_id, assessment_date, max_assessment_date, priority, probability, impact, risk_rating, classification, owner, "
					+ "responsible_person, mitigation_plan, action_taken, atr_date from risk_view rv " + 
					"left join work w on rv.work_id_fk = w.work_id  "
					+ "LEFT JOIN risk r on rv.risk_id = r.risk_id "
					+ "where assessment_date = max_assessment_date ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and area = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and rv.work_id_fk = ?";
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
			qry = qry + " group by risk_id"; 
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
			String qry ="select risk_priority as priority  from risk_priority ";
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
			String qry = "SELECT risk_id_pk,w.work_id,work_id_fk,w.work_name,project_id_fk,ra.area,p.project_name,risk_id,date_of_identification,sub_area_fk from risk r  "+
					"LEFT OUTER join work w on r.work_id_fk = w.work_id " + 
					"left join risk_sub_area rsa on r.sub_area_fk = sub_area " + 
					"left join risk_area ra on rsa.risk_area_fk = ra.area "+
					"LEFT OUTER join project p on w.project_id_fk = p.project_id where risk_id_pk is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRisk_id_pk())) {
				qry = qry + " and risk_id_pk= ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRisk_id_pk())) {
				pValues[i++] = obj.getRisk_id_pk();
			}
			sObj = (Risk)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id_pk())) {
				List<Risk> objsList = null;
				String qryDetails = "select risk_revision_id,risk_id_pk_fk,owner,responsible_person,mitigation_plan,priority_fk,"
						+"probability,impact, DATE_FORMAT(date,'%d-%m-%Y') AS date  "
						+ "from risk_revision rv "
						+ "LEFT JOIN risk r on rv.risk_id_pk_fk = r.risk_id_pk "
						+"where r.work_id_fk = ? and r.sub_area_fk = ? order by date asc ";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getWork_id_fk(),sObj.getSub_area_fk()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
				sObj.setRisks(objsList); 
			}
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id_pk())) {
				for (Risk session : sObj.getRisks()) {
					List<Risk> objsList = null;
					String qryDetails = "select risk_revision_id_fk,risk_action_id, action_taken, DATE_FORMAT(atr_date,'%d-%m-%Y') AS atr_date " + 
							"from risk_action "
							+"where  risk_revision_id_fk = ? ";
					
					objsList = jdbcTemplate.query(qryDetails, new Object[] {session.getRisk_revision_id()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
					session.setRiskActions(objsList); 
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public boolean addRisk(Risk obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "INSERT INTO risk (work_id_fk,risk_id,sub_area_fk,date_of_identification) "
					+ "VALUES"
					+ "(:work_id_fk,:risk_id,:sub_area_fk,:date_of_identification)";
			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
		   	
		    String risk_id_pk = null;
			if(count > 0) {
				risk_id_pk = String.valueOf(keyHolder.getKey().intValue());
				 obj.setRisk_id_pk(risk_id_pk);
				 flag = true;
			}
			
			if(flag) {
					String insertQry1 = "INSERT into  risk_revision (risk_id_pk_fk,owner,date,priority_fk,probability,impact,responsible_person,mitigation_plan) "
							+"VALUES (?,?,?,?,?,?,?,?)";
					insertStmt = con.prepareStatement(insertQry1,Statement.RETURN_GENERATED_KEYS);
					
					int	arraySize = 0;
					if(!StringUtils.isEmpty(obj.getDates()) && obj.getDates().length > 0) {
						obj.setDates(CommonMethods.replaceEmptyByNullInSringArray(obj.getDates()));
						if(arraySize < obj.getDates().length) {
							arraySize = obj.getDates().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getOwners()) && obj.getOwners().length > 0) {
						obj.setOwners(CommonMethods.replaceEmptyByNullInSringArray(obj.getOwners()));
						if(arraySize < obj.getOwners().length) {
							arraySize = obj.getOwners().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getPrioritys()) && obj.getPrioritys().length > 0) {
						obj.setPrioritys(CommonMethods.replaceEmptyByNullInSringArray(obj.getPrioritys()));
						if(arraySize < obj.getPrioritys().length) {
							arraySize = obj.getPrioritys().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getProbabilitys()) && obj.getProbabilitys().length > 0) {
						obj.setProbabilitys(CommonMethods.replaceEmptyByNullInSringArray(obj.getProbabilitys()));
						if(arraySize < obj.getProbabilitys().length) {
							arraySize = obj.getProbabilitys().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getImpacts()) && obj.getImpacts().length > 0) {
						obj.setImpacts(CommonMethods.replaceEmptyByNullInSringArray(obj.getImpacts()));
						if(arraySize < obj.getImpacts().length) {
							arraySize = obj.getImpacts().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getResponsible_persons()) && obj.getResponsible_persons().length > 0) {
						obj.setResponsible_persons(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_persons()));
						if(arraySize < obj.getResponsible_persons().length) {
							arraySize = obj.getResponsible_persons().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getMitigation_plans()) && obj.getMitigation_plans().length > 0) {
						obj.setMitigation_plans(CommonMethods.replaceEmptyByNullInSringArray(obj.getMitigation_plans()));
						if(arraySize < obj.getMitigation_plans().length) {
							arraySize = obj.getMitigation_plans().length;
						}
					}
					int	arraySize1 = 0;
					if(!StringUtils.isEmpty(obj.getAction_takens()) && obj.getAction_takens().length > 0) {
						obj.setAction_takens(CommonMethods.replaceEmptyByNullInSringArray(obj.getAction_takens()));
						if(arraySize1 < obj.getAction_takens().length) {
							arraySize1 = obj.getAction_takens().length;
						}
					}
				
					if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().length > 0) {
						obj.setAtr_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAtr_dates()));
						if(arraySize1 < obj.getAtr_dates().length) {
							arraySize1 = obj.getAtr_dates().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getDates()) && obj.getDates().length > 0) {
						 for (int i = 0; i < arraySize; i++) {
							 if( obj.getDates().length > 0 && !StringUtils.isEmpty(obj.getDates()[i])) {
								    int p = 1;
								    insertStmt.setString(p++,(obj.getRisk_id_pk()));
									insertStmt.setString(p++,(obj.getOwners().length > 0)?obj.getOwners()[i]:null);
								    insertStmt.setString(p++,DateParser.parse((obj.getDates().length > 0)?obj.getDates()[i]:null));
								    insertStmt.setString(p++,(obj.getPrioritys().length > 0)?obj.getPrioritys()[i]:null);
								    insertStmt.setString(p++,(obj.getProbabilitys().length > 0)?obj.getProbabilitys()[i]:null);
								    insertStmt.setString(p++,(obj.getImpacts().length > 0)?obj.getImpacts()[i]:null);
								    insertStmt.setString(p++,(obj.getResponsible_persons().length > 0)?obj.getResponsible_persons()[i]:null);
								    insertStmt.setString(p++,(obj.getMitigation_plans().length > 0)?obj.getMitigation_plans()[i]:null);
								    insertStmt.addBatch();
							 }
						   
							int[] insertCount = insertStmt.executeBatch();
							rs = insertStmt.getGeneratedKeys();
							if(insertCount.length > 0) {
								
								String insertQry2 = "INSERT into risk_action (risk_revision_id_fk,action_taken,atr_date) "
										+"VALUES (?,?,?)";
								insertStmt1 = con.prepareStatement(insertQry2);
								if (rs.next()) {
									String revisionId = rs.getString(1);
									obj.setRisk_revision_id(revisionId);
								}
								
								if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().length > 0) {
									for (int j = 0; j < obj.getRowCounts()[i]; j++) {
										    int k = 1;
										    int a = r++; 
										    if( obj.getAtr_dates().length > 0 && !StringUtils.isEmpty(obj.getAtr_dates()[a])) {
												    insertStmt1.setString(k++,(obj.getRisk_revision_id()));
												    insertStmt1.setString(k++,(obj.getAction_takens().length > 0)?obj.getAction_takens()[a]:null);
												    insertStmt1.setString(k++,DateParser.parse((obj.getAtr_dates().length > 0)?obj.getAtr_dates()[a]:null));
												    insertStmt1.addBatch();
										    }
									 }
								   }
									int[] insertCount1 = insertStmt1.executeBatch();
									
									if(insertCount1.length > 0) {
										flag = true;
									}
							   }
					
					     }
				   }
		   	   
			}
			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt1, null);
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return flag;
	}

	@Override
	public boolean updateRisk(Risk obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		int r = 0;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		 
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "UPDATE risk SET risk_id=:risk_id,sub_area_fk=:sub_area_fk,date_of_identification=:date_of_identification  "
					+ "WHERE risk_id_pk = :risk_id_pk";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				
				  String deleteQry = "DELETE from risk_action where risk_revision_id_fk = ?";	
				  stmt = con.prepareStatement(deleteQry);
					
				  for(int x =0; x < obj.getRisk_revision_ids().length; x++) {
						  int k =1;
						  stmt.setString(k++,(obj.getRisk_revision_ids()[x]));
						  stmt.addBatch();
				  }
				  int[] deleteCount = stmt.executeBatch();
				  if(deleteCount.length > 0) {
							
							String deleteQry1 = "DELETE from risk_revision where risk_revision_id = ?";	
							stmt1 = con.prepareStatement(deleteQry1);
							
							for(int x =0; x < obj.getRisk_revision_ids().length; x++) {
								  int k =1;
								  stmt1.setString(k++,(obj.getRisk_revision_ids()[x]));
								  stmt1.addBatch();
						}
						  int[] deleteCount1 = stmt1.executeBatch();
						  if(deleteCount.length > 0) {
							  flag = true;
						  }
					}
					String insertQry1 = "INSERT into  risk_revision (risk_id_pk_fk,owner,date,priority_fk,probability,impact,responsible_person,mitigation_plan) "
							+"VALUES (?,?,?,?,?,?,?,?)";
					insertStmt = con.prepareStatement(insertQry1,Statement.RETURN_GENERATED_KEYS);
					
					int	arraySize = 0;
					if(!StringUtils.isEmpty(obj.getDates()) && obj.getDates().length > 0) {
						obj.setDates(CommonMethods.replaceEmptyByNullInSringArray(obj.getDates()));
						if(arraySize < obj.getDates().length) {
							arraySize = obj.getDates().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getOwners()) && obj.getOwners().length > 0) {
						obj.setOwners(CommonMethods.replaceEmptyByNullInSringArray(obj.getOwners()));
						if(arraySize < obj.getOwners().length) {
							arraySize = obj.getOwners().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getPrioritys()) && obj.getPrioritys().length > 0) {
						obj.setPrioritys(CommonMethods.replaceEmptyByNullInSringArray(obj.getPrioritys()));
						if(arraySize < obj.getPrioritys().length) {
							arraySize = obj.getPrioritys().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getProbabilitys()) && obj.getProbabilitys().length > 0) {
						obj.setProbabilitys(CommonMethods.replaceEmptyByNullInSringArray(obj.getProbabilitys()));
						if(arraySize < obj.getProbabilitys().length) {
							arraySize = obj.getProbabilitys().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getImpacts()) && obj.getImpacts().length > 0) {
						obj.setImpacts(CommonMethods.replaceEmptyByNullInSringArray(obj.getImpacts()));
						if(arraySize < obj.getImpacts().length) {
							arraySize = obj.getImpacts().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getResponsible_persons()) && obj.getResponsible_persons().length > 0) {
						obj.setResponsible_persons(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_persons()));
						if(arraySize < obj.getResponsible_persons().length) {
							arraySize = obj.getResponsible_persons().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getMitigation_plans()) && obj.getMitigation_plans().length > 0) {
						obj.setMitigation_plans(CommonMethods.replaceEmptyByNullInSringArray(obj.getMitigation_plans()));
						if(arraySize < obj.getMitigation_plans().length) {
							arraySize = obj.getMitigation_plans().length;
						}
					}
					int	arraySize1 = 0;
					if(!StringUtils.isEmpty(obj.getAction_takens()) && obj.getAction_takens().length > 0) {
						obj.setAction_takens(CommonMethods.replaceEmptyByNullInSringArray(obj.getAction_takens()));
						if(arraySize1 < obj.getAction_takens().length) {
							arraySize1 = obj.getAction_takens().length;
						}
					}
				
					if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().length > 0) {
						obj.setAtr_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAtr_dates()));
						if(arraySize1 < obj.getAtr_dates().length) {
							arraySize1 = obj.getAtr_dates().length;
						}
					}
					if(!StringUtils.isEmpty(obj.getDates()) && obj.getDates().length > 0) {
						 for (int i = 0; i < arraySize; i++) {
							 if( obj.getDates().length > 0 && !StringUtils.isEmpty(obj.getDates()[i])) {
								    int p = 1;
								    insertStmt.setString(p++,(obj.getRisk_id_pk()));
									insertStmt.setString(p++,(obj.getOwners().length > 0)?obj.getOwners()[i]:null);
								    insertStmt.setString(p++,DateParser.parse((obj.getDates().length > 0)?obj.getDates()[i]:null));
								    insertStmt.setString(p++,(obj.getPrioritys().length > 0)?obj.getPrioritys()[i]:null);
								    insertStmt.setString(p++,(obj.getProbabilitys().length > 0)?obj.getProbabilitys()[i]:null);
								    insertStmt.setString(p++,(obj.getImpacts().length > 0)?obj.getImpacts()[i]:null);
								    insertStmt.setString(p++,(obj.getResponsible_persons().length > 0)?obj.getResponsible_persons()[i]:null);
								    insertStmt.setString(p++,(obj.getMitigation_plans().length > 0)?obj.getMitigation_plans()[i]:null);
								    insertStmt.addBatch();
							 }
						   
							int[] insertCount = insertStmt.executeBatch();
							rs = insertStmt.getGeneratedKeys();
							if(insertCount.length > 0) {
								
								String insertQry2 = "INSERT into risk_action (risk_revision_id_fk,action_taken,atr_date) "
										+"VALUES (?,?,?)";
								insertStmt1 = con.prepareStatement(insertQry2);
								if (rs.next()) {
									String revisionId = rs.getString(1);
									obj.setRisk_revision_id(revisionId);
								}
								
								if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().length > 0) {
									for (int j = 0; j < obj.getRowCounts()[i]; j++) {
										    int k = 1;
										    int a = r++; 
										    if( obj.getAtr_dates().length > 0 && !StringUtils.isEmpty(obj.getAtr_dates()[a])) {
												    insertStmt1.setString(k++,(obj.getRisk_revision_id()));
												    insertStmt1.setString(k++,(obj.getAction_takens().length > 0)?obj.getAction_takens()[a]:null);
												    insertStmt1.setString(k++,DateParser.parse((obj.getAtr_dates().length > 0)?obj.getAtr_dates()[a]:null));
												    insertStmt1.addBatch();
										    }
									 }
								   }
									int[] insertCount1 = insertStmt1.executeBatch();
									
									if(insertCount1.length > 0) {
										flag = true;
									}
						      }
						
				      }
			   }
		   	   
		}
		DBConnectionHandler.closeJDBCResoucrs(null, insertStmt1, null);
		DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
		DBConnectionHandler.closeJDBCResoucrs(null, stmt1, null);

		con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return flag;
	}

}
