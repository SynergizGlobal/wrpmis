package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.RiskReportDao;
import com.synergizglobal.pmis.model.RiskReport;

@Repository
public class RiskReportDaoImpl implements RiskReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	


	@Override
	public List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select work_id_fk,assessment_date,work_id,work_name,work_short_name,project_id,project_name from risk_view rv " 
					+ "left outer join work_view wv on rv.work_id_fk = wv.work_id group by work_id_fk";
			
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select DATE_FORMAT(assessment_date,'%d-%m-%Y') AS assessment_date,DATE_FORMAT(max_assessment_date,'%d-%m-%Y') AS max_assessment_date "
					+ "from risk_view rv " 
					+ "where work_id_fk = ? group by assessment_date";
			Object[] pValues = new Object[] {obj.getWork_id()};
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception {
		RiskReport riskObject = null;
		List<RiskReport> areaList = null;
		try {
			String qry = "select work_id_fk,DATE_FORMAT(assessment_date,'%d-%m-%Y') AS assessment_date,work_id,work_name,work_short_name,project_id,project_name,owner "
					+ "from risk_view rv " 
					+ "left outer join work_view wv on rv.work_id_fk = wv.work_id "
					+ "where work_id_fk = ? and assessment_date = ? limit 1";
			
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getAssessment_date()};
			
			riskObject = (RiskReport)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
			
			if(!StringUtils.isEmpty(riskObject)) {
				
				qry = "select id,work_id_fk,risk_id,identification_date,area,area_item_no,sub_area,sub_area_item_no,revision_id,assessment_date,max_assessment_date,"
						+ "priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,attachment,action_taken,atr_date "
						+ "from risk_view rv " 
						+ "where work_id_fk = ? and assessment_date = ? group by area order by area_item_no";
				
				pValues = new Object[] {obj.getWork_id(),obj.getAssessment_date()};
				
				areaList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			}
			
			if(!StringUtils.isEmpty(areaList) && areaList.size() > 0) {
				for (RiskReport area : areaList) {
					qry = "select id,work_id_fk,risk_id,identification_date,area,area_item_no,sub_area,sub_area_item_no,revision_id,assessment_date,max_assessment_date,"
							+ "priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,attachment,action_taken,atr_date "
							+ "from risk_view rv " 
							+ "where work_id_fk = ? and assessment_date = ? and area = ? order by sub_area_item_no";
					
					pValues = new Object[] {obj.getWork_id(),obj.getAssessment_date(),area.getArea()};
					
					List<RiskReport> subAreaList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
					area.setSubAreaList(subAreaList);
				}
				
				riskObject.setAreaList(areaList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return riskObject;
	}

	@Override
	public List<RiskReport> getPrioritizationOfRisks(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select id,work_id_fk,risk_id,identification_date,area,area_item_no,sub_area,sub_area_item_no,revision_id,assessment_date,max_assessment_date,"
							+ "priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,attachment,action_taken,atr_date "
							+ "from risk_view rv " 
							+ "where work_id_fk = ? and assessment_date = ? and priority <> 'Accepted' order by priority";
					
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getAssessment_date()};
					
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<RiskReport> getReductionPlanRisks(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select id,work_id_fk,risk_id,identification_date,area,area_item_no,sub_area,sub_area_item_no,revision_id,assessment_date,max_assessment_date,"
							+ "priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,attachment,action_taken,atr_date "
							+ "from risk_view rv " 
							+ "where work_id_fk = ? and assessment_date = ? and atr_date is not null order by priority";
					
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getAssessment_date()};
					
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
