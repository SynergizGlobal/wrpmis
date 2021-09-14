package com.synergizglobal.pmis.IMPLdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
			String qry = "select work_id_fk,work_id,work_name,work_short_name "
					+ "from risk_work_hod rwh " 
					+ "left join risk r on rwh.sub_work = r.sub_work " 
					+ "left join work w on rwh.work_id_fk = w.work_id "
					+ "group by rwh.work_id_fk";
			
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<RiskReport> getSubWorksListInRiskReport(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select r.sub_work "
					+ "from risk r "
					+ "LEFT JOIN risk_work_hod rwh ON r.sub_work = rwh.sub_work "
					+ "group by sub_work ORDER BY rwh.priority asc";
			
			Object[] pValues = new Object[] {};
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select DATE_FORMAT(date,'%d-%b-%Y') AS assessment_date "
					+ "from risk_revision rr "
					+ "left join risk r on risk_id_pk_fk = risk_id_pk " 
					+ "where sub_work = ? group by date order by date desc";
			Object[] pValues = new Object[] {obj.getSub_work()};
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
			String workIdQuery = "select work_id_fk from risk_work_hod where sub_work = ? limit 1";
			String work_id = jdbcTemplate.queryForObject( workIdQuery, new Object[] {obj.getSub_work()}, String.class);
			obj.setWork_id(work_id);
			
			String qry = "select rwh.work_id_fk as work_id,rv.sub_work,DATE_FORMAT(date,'%d-%m-%Y') AS assessment_date,work_name,work_short_name,"
					+ "project_id,project_name,owner,"
					+ "(select IFNULL((select latest_revised_cost from work_yearly_sanction where work_id_fk = ? order by work_id_fk desc limit 1),sanctioned_estimated_cost) from work where work_id = ?) as estimatedOrRevisedCost,"
					+ "(select IFNULL((select financial_year from work_yearly_sanction where work_id_fk = ? order by work_id_fk desc limit 1),sanctioned_year_fk) from work where work_id = ?) as estimatedOrRevisedDate "
					+ "from risk_revision_view rrv " 
					+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk "
					+ "left outer join risk_work_hod rwh on rv.sub_work = rwh.sub_work "
					+ "left outer join work w on rwh.work_id_fk = w.work_id "
					+ "left outer join project p on w.project_id_fk = p.project_id "
					+ "where rwh.work_id_fk = ? and rv.sub_work = ? and date = ? order by date limit 1";
			
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getWork_id(),obj.getWork_id(),obj.getWork_id(),obj.getWork_id(),obj.getSub_work(),obj.getAssessment_date()};
			
			riskObject = (RiskReport)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
			
			if(!StringUtils.isEmpty(riskObject)) {
				
				qry = "select risk_revision_id,rwh.work_id_fk as work_id,rv.sub_work,area,area_item_no,sub_area,sub_area_item_no,date,"
						+ "priority_fk as priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan "
						+ "from risk_revision_view rrv " 
						+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk "
						+ "left outer join risk_work_hod rwh on rv.sub_work = rwh.sub_work "
						+ "left outer join work w on rwh.work_id_fk = w.work_id "
						+ "left outer join project p on w.project_id_fk = p.project_id "
						+ "where rwh.work_id_fk = ? and rv.sub_work = ? and date = ? group by area order by area_item_no";
				
				pValues = new Object[] {obj.getWork_id(),obj.getSub_work(),obj.getAssessment_date()};
				
				areaList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			}
			
			if(!StringUtils.isEmpty(areaList) && areaList.size() > 0) {
				for (RiskReport area : areaList) {
					qry = "select risk_revision_id,rwh.work_id_fk as work_id,rv.sub_work,area,area_item_no,sub_area,sub_area_item_no,date,"
							+ "priority_fk as priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan "
							+ "from risk_revision_view rrv " 
							+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk "
							+ "left outer join risk_work_hod rwh on rv.sub_work = rwh.sub_work "
							+ "left outer join work w on rwh.work_id_fk = w.work_id "
							+ "left outer join project p on w.project_id_fk = p.project_id "
							+ "where rwh.work_id_fk = ? and rv.sub_work = ? and date = ? and area = ? order by sub_area_item_no";
					
					pValues = new Object[] {obj.getWork_id(),obj.getSub_work(),obj.getAssessment_date(),area.getArea()};
					
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
			String qry = "select risk_revision_id,rwh.work_id_fk as work_id,area,area_item_no,sub_area,sub_area_item_no,date,"
							+ "priority_fk as priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan "
							+ "from risk_revision_view rrv " 
							+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk "
							+ "left outer join risk_work_hod rwh on rv.sub_work = rwh.sub_work "
							+ "left outer join work w on rwh.work_id_fk = w.work_id "
							+ "left outer join project p on w.project_id_fk = p.project_id "
							+ "where rwh.work_id_fk = ? and rv.sub_work = ? and date = ? and priority_fk <> 'Accepted' "
							+ "ORDER BY CONCAT( REPEAT(  '0', 8 - LENGTH( priority_fk ) ) , priority_fk ) asc, area_item_no ASC , sub_area_item_no ASC ";
					
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getSub_work(),obj.getAssessment_date()};
					
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
			/*String qry = "select risk_revision_id,work_id,risk_id,area,area_item_no,sub_area,sub_area_item_no,date,"
							+ "priority_fk as priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,action_taken,atr_date "
							+ "from risk_revision_view rrv " 
							+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk " 
							+ "left outer join risk_action ra on rrv.risk_id_pk_fk = ra.risk_id_pk_fk " 
							+ "where work_id = ? and sub_work = ? and date = ? and atr_date is not null and priority_fk <> 'Accepted' order by priority_fk";*/
			
			String qry = "select risk_revision_id,rwh.work_id_fk as work_id,area,area_item_no,sub_area,sub_area_item_no,date,"
					+ "priority_fk as priority,probability,impact,risk_rating,classification,owner,"
					+ "responsible_person,mitigation_plan,action_taken,DATE_FORMAT(atr_date,'%d-%m-%Y') as atr_date "
					+ "from risk_revision_view rrv " 
					+ "left outer join risk_action ra on rrv.risk_revision_id = ra.risk_revision_id_fk " 
					+ "left outer join risk_view rv on rrv.risk_id_pk_fk = rv.risk_id_pk " 
					+ "left outer join risk_work_hod rwh on rv.sub_work = rwh.sub_work "
					+ "left outer join work w on rwh.work_id_fk = w.work_id "
					+ "left outer join project p on w.project_id_fk = p.project_id "
					+ "where rwh.work_id_fk = ? and rv.sub_work = ? and date = ? and priority_fk <> 'Accepted'  "
					+ "ORDER BY CONCAT( REPEAT(  '0', 8 - LENGTH( priority_fk ) ) , priority_fk ) asc, area_item_no ASC , sub_area_item_no ASC, DATE_FORMAT(atr_date,'%Y-%m-%d') ASC";
			
					
			Object[] pValues = new Object[] {obj.getWork_id(),obj.getSub_work(),obj.getAssessment_date()};
					
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<RiskReport> getSummaryOfRiskAssessmentOfProjects() throws Exception {
		List<RiskReport> objsList = null;
		try {
			String qry = "select r.sub_work,DATE_FORMAT(max(rr.date),'%d-%m-%Y') as last_assessment_date,rr.owner," + 
					"(select max(probability)*max(impact) from risk_revision where date = max(rr.date) and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as risk_score, " + 
					"(select sum(probability*impact) from risk_revision where date = max(rr.date) and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as total_risk_rating," + 
					"(select count(classification) from risk_revision_view where date = max(rr.date) and classification = 'High' and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as total_high_risks," + 
					"(select count(classification) from risk_revision_view where date = max(rr.date) and classification = 'Substantial' and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as total_substantial_risks," + 
					"(select count(classification) from risk_revision_view where date = max(rr.date) and classification = 'Moderate' and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as total_moderate_risks," + 
					"(select count(priority_fk) from risk_revision where date = max(rr.date) and priority_fk <> 'Accepted' and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work)) as total_priority_risks," + 
					"(select count(risk_revision_id_fk) from risk_action where risk_revision_id_fk in(select risk_revision_id from risk_revision where date = max(rr.date) and risk_id_pk_fk in(select risk_id_pk from risk where sub_work = r.sub_work))) as atr_submitted " + 
					"from risk r " + 
					"LEFT JOIN risk_revision rr on rr.risk_id_pk_fk = r.risk_id_pk " + 
					"LEFT JOIN risk_work_hod rwh on rwh.sub_work = r.sub_work " + 
					"WHERE (rwh.risk_work_completed is null or rwh.risk_work_completed = '' or rwh.risk_work_completed = 'No') group by r.sub_work "+
					"order by rwh.priority asc ";			
					//"order by max(rr.date) desc";
			
					
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Map<String,List<RiskReport>> getTop5RiskAreas() throws Exception {
		Map<String,List<RiskReport>> mObj = new LinkedHashMap<String,List<RiskReport>>();
		try {
			String qry = "select area,sub_area,sum(risk_rating) as total_risk_rating " + 
					"from " + 
					"(select * " + 
					"from " + 
					"( " + 
					"select risk_id_pk_fk,date,probability,(`rr`.`probability` * `rr`.`impact`) AS `risk_rating`,rw.sub_work,(SELECT  " + 
					"`sa`.`risk_area_fk` " + 
					"FROM " + 
					"`risk_sub_area` `sa` " + 
					"WHERE " + 
					"(`sa`.`sub_area` = `r`.`sub_area_fk`)) AS `area`, " + 
					"`r`.`sub_area_fk` AS `sub_area`, " + 
					"dense_rank() over(partition by sub_work order by date desc ) as r from  risk_revision rr " + 
					"left join risk  r  on r.risk_id_pk=rr.risk_id_pk_fk " + 
					"left join  pmis.risk_work_hod rw on r.sub_work=rw.sub_work " + 
					"where risk_work_completed='No' ) s " + 
					"where r =1)ss " + 
					"group by area,sub_area " + 
					"order by sum(risk_rating) desc limit 10";
					
			List<RiskReport> objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
			List<String> tObjs = new ArrayList<String>();
			for (RiskReport riskReport : objsList) {
				if(!tObjs.contains(riskReport.getArea()) && tObjs.size() < 5) {
					tObjs.add(riskReport.getArea());
				}
			}
			
			for (String area : tObjs) {
				List<RiskReport> tObjs2 = new ArrayList<RiskReport>();
				for (RiskReport riskReport : objsList) {
					if(area.equals(riskReport.getArea())) {
						tObjs2.add(riskReport);
					}
				}
				mObj.put(area, tObjs2);
			}			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return mObj;
	}


}
