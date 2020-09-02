package com.synergizglobal.pmis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.RandomGenerator;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Risk;


@Repository
public class RiskDao {
	@Autowired
	DataSource dataSource;
	
	/**
	 * This method get the Risk list
	 * 
	 * @param riskObj is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskList(Risk riskObj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			/*String qry = "select risk_id,work_id_fk,risk_owner,risk_sub_area_id_fk,risk_description,risk_category_id_fk,risk_probability_id_fk,risk_impact_id_fk,risk_priority_number,"
					+ "DATE_FORMAT(date_of_identification,'%d-%m-%Y') AS date_of_identification,risk_mitigation_plan,DATE_FORMAT(target_date_for_mitigation,'%d-%m-%Y') AS target_date_for_mitigation,responsible_person_name,risk_status_id_fk,risk_analysis_report_link,r.remarks,DATE_FORMAT(risk_mitigated_on,'%d-%m-%Y') AS risk_mitigated_on,"
					+ "soft_delete_status_id_fk,created_by,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,"
					+ "rsa.risk_sub_area_name,rc.category,ri.impact,rp.probability,w.work_name,rs.risk_status,s.status,rsa.risk_area_id_fk,ra.risk_area,w.project_id_fk,p.project_description "
					+ "from risk r "
					+ "LEFT OUTER JOIN risk_sub_area rsa ON risk_sub_area_id_fk = risk_sub_area_id "
					+ "LEFT OUTER JOIN risk_category rc ON risk_category_id_fk = risk_category_id "
					+ "LEFT OUTER JOIN risk_impact ri ON risk_impact_id_fk = risk_impact_id "
					+ "LEFT OUTER JOIN risk_probability rp ON risk_probability_id_fk = risk_probability_id "
					+ "LEFT OUTER JOIN `work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN risk_status rs ON risk_status_id_fk = risk_status_id "
					+ "LEFT OUTER JOIN soft_delete_status s ON soft_delete_status_id_fk = soft_delete_status_id "
					+ "LEFT OUTER JOIN risk_area ra ON rsa.risk_area_id_fk = ra.risk_area_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id ";*/
			
			String qry = "select `Risk ID` as risk_id,`Work ID` as work_id_fk,`Owner / HOD` as risk_owner,`Sub Area ID` as risk_sub_area_id_fk,Description as risk_description,`Category ID` as risk_category_id_fk,`Probability ID` as risk_probability_id_fk,`Impact ID` as risk_impact_id_fk,Classification,`Priority Number` as risk_priority_number,"
					+ "DATE_FORMAT(`Date of Identification`,'%d-%m-%Y') AS date_of_identification,`Mitigation Plan` as risk_mitigation_plan,DATE_FORMAT(`Target Date`,'%d-%m-%Y') AS target_date_for_mitigation,`Responsible Person` as responsible_person_name,`Status ID` as risk_status_id_fk,`Analysis Report` as risk_analysis_report_link,`Action Plan` remarks,DATE_FORMAT(`Mitigated on`,'%d-%m-%Y') AS risk_mitigated_on,"
					+ "`Sub Area` as risk_sub_area_name,`Category` as category,Impact as impact,Probability as probability,`Status` as risk_status,`Area ID` as risk_area_id_fk,`Area` as risk_area "
					+ "from risk_view WHERE `Risk ID` IS NOT NULL ";
			
			if(!StringUtils.isEmpty(riskObj)) {
				if(!StringUtils.isEmpty(riskObj.getWorkId())) {
					qry = qry + "and `Work ID` = ? ";
				}
				
				if(!StringUtils.isEmpty(riskObj.getRiskStatusId())) {
					qry = qry + "and `Status ID` = ? ";
				}
				if(!StringUtils.isEmpty(riskObj.getRiskCategoryId())) {
					qry = qry + "and `Category ID` = ? ";
				}
				if(!StringUtils.isEmpty(riskObj.getRiskAreaId())) {
					qry = qry + "and `Area ID` = ? ";
				}
				if(!StringUtils.isEmpty(riskObj.getRiskClassificationName())) {
					qry = qry + "and Classification = ? ";
				}
				if(!StringUtils.isEmpty(riskObj.getRiskPriorityNumber())) {
					qry = qry + "and `Priority Number` = ? ";
				}
				if(!StringUtils.isEmpty(riskObj.getResponsiblePersonName())) {
					qry = qry + "and `Responsible Person` = ? ";
				}
			}
			
			statement = connection.prepareStatement(qry);
			
			if(!StringUtils.isEmpty(riskObj)) {
				int c = 1;
				
				if(!StringUtils.isEmpty(riskObj.getWorkId())) {
					statement.setString(c++, riskObj.getWorkId());
				}
				if(!StringUtils.isEmpty(riskObj.getRiskStatusId())) {
					statement.setString(c++, riskObj.getRiskStatusId());
				}
				if(!StringUtils.isEmpty(riskObj.getRiskCategoryId())) {
					statement.setString(c++, riskObj.getRiskCategoryId());
				}
				if(!StringUtils.isEmpty(riskObj.getRiskAreaId())) {
					statement.setString(c++, riskObj.getRiskAreaId());
				}
				if(!StringUtils.isEmpty(riskObj.getRiskClassificationName())) {
					statement.setString(c++, riskObj.getRiskClassificationName());
				}
				if(!StringUtils.isEmpty(riskObj.getRiskPriorityNumber())) {
					statement.setString(c++, riskObj.getRiskPriorityNumber());
				}
				if(!StringUtils.isEmpty(riskObj.getResponsiblePersonName())) {
					statement.setString(c++, riskObj.getResponsiblePersonName());
				}
			}
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskId(resultSet.getString("risk_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setRiskOwner(resultSet.getString("risk_owner"));
				obj.setRiskSubAreaId(resultSet.getString("risk_sub_area_id_fk"));
				obj.setRiskDescription(resultSet.getString("risk_description"));
				obj.setRiskCategoryId(resultSet.getString("risk_category_id_fk"));
				obj.setRiskProbabilityId(resultSet.getString("risk_probability_id_fk"));
				obj.setRiskImpactId(resultSet.getString("risk_impact_id_fk"));
				obj.setRiskPriorityNumber(resultSet.getString("risk_priority_number"));
				obj.setDateOfIdentification(resultSet.getString("date_of_identification"));
				obj.setRiskMitigationPlan(resultSet.getString("risk_mitigation_plan"));
				obj.setTargetDateForMitigation(resultSet.getString("target_date_for_mitigation"));
				obj.setResponsiblePersonName(resultSet.getString("responsible_person_name"));
				obj.setRiskStatusId(resultSet.getString("risk_status_id_fk"));
				obj.setRiskAnalysisReportLink(resultSet.getString("risk_analysis_report_link"));
				obj.setRemarks(resultSet.getString("remarks"));	
				obj.setRiskMitigatedOn(resultSet.getString("risk_mitigated_on"));
				obj.setRiskSubArea(resultSet.getString("risk_sub_area_name"));
				obj.setRiskCategory(resultSet.getString("category"));
				obj.setRiskImpact(resultSet.getString("impact"));
				obj.setRiskProbability(resultSet.getString("probability"));
				obj.setRiskStatus(resultSet.getString("risk_status"));
				obj.setRiskAreaId(resultSet.getString("risk_area_id_fk"));
				obj.setRiskArea(resultSet.getString("risk_area"));
				
				obj.setRiskClassificationName(resultSet.getString("Classification"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the projects list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */

	public List<Risk> getProjectsList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select project_id,project_description from `project`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setProjectId(resultSet.getString("project_id"));
				obj.setProjectName(resultSet.getString("project_description"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the works list
	 * 
	 * @param risk is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getWorksList(Risk risk) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select work_id,work_name from `work` ";
			
			if(!StringUtils.isEmpty(risk) && !StringUtils.isEmpty(risk.getProjectId())) {
				qry = qry + "where project_id_fk = ?";
			}
			
			statement = connection.prepareStatement(qry);
			
			if(!StringUtils.isEmpty(risk) && !StringUtils.isEmpty(risk.getProjectId())) {
				statement.setString(1,risk.getProjectId());
			}
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setWorkId(resultSet.getString("work_id"));
				obj.setWorkName(resultSet.getString("work_name"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSatausList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_status_id,risk_status from risk_status";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskStatusId(resultSet.getString("risk_status_id"));
				obj.setRiskStatus(resultSet.getString("risk_status"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk area list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskAreaList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_area_id,risk_area from risk_area";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskAreaId(resultSet.getString("risk_area_id"));
				obj.setRiskArea(resultSet.getString("risk_area"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk sub area list
	 * 
	 * @param risk is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSubAreaList(Risk risk) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_sub_area_id,risk_sub_area_name from risk_sub_area ";
			
			if(!StringUtils.isEmpty(risk) && !StringUtils.isEmpty(risk.getRiskAreaId())) {
				qry = qry + "where risk_area_id_fk = ?";
			}
			
			statement = connection.prepareStatement(qry);
			
			if(!StringUtils.isEmpty(risk) && !StringUtils.isEmpty(risk.getRiskAreaId())) {
				statement.setString(1,risk.getRiskAreaId());
			}
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskSubAreaId(resultSet.getString("risk_sub_area_id"));
				obj.setRiskSubArea(resultSet.getString("risk_sub_area_name"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskCategoryList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_category_id,category from risk_category";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskCategoryId(resultSet.getString("risk_category_id"));
				obj.setRiskCategory(resultSet.getString("category"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk impact list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskImpactList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_impact_id,impact from risk_impact";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskImpactId(resultSet.getString("risk_impact_id"));
				obj.setRiskImpact(resultSet.getString("impact"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the risk probability list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskProbabilityList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_probability_id,probability from risk_probability";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskProbabilityId(resultSet.getString("risk_probability_id"));
				obj.setRiskProbability(resultSet.getString("probability"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	
	/**
	 * This method get the risk
	 * 
	 * @param riskId it is string type variable that holds the riskId
	 * @return type of this method is obj that is model class Risk type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Risk getRisk(String riskId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_id,work_id_fk,risk_owner,risk_sub_area_id_fk,risk_description,risk_category_id_fk,risk_probability_id_fk,risk_impact_id_fk,risk_priority_number,"
					+ "DATE_FORMAT(date_of_identification,'%d-%m-%Y') AS date_of_identification,risk_mitigation_plan,DATE_FORMAT(target_date_for_mitigation,'%d-%m-%Y') AS target_date_for_mitigation,responsible_person_name,risk_status_id_fk,risk_analysis_report_link,r.remarks,DATE_FORMAT(risk_mitigated_on,'%d-%m-%Y') AS risk_mitigated_on,"
					+ "soft_delete_status_id_fk,created_by,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,"
					+ "rsa.risk_sub_area_name,rc.category,ri.impact,rp.probability,w.work_name,rs.risk_status,s.status,rsa.risk_area_id_fk,ra.risk_area,w.project_id_fk,p.project_description "
					+ "from risk r "
					+ "LEFT OUTER JOIN risk_sub_area rsa ON risk_sub_area_id_fk = risk_sub_area_id "
					+ "LEFT OUTER JOIN risk_category rc ON risk_category_id_fk = risk_category_id "
					+ "LEFT OUTER JOIN risk_impact ri ON risk_impact_id_fk = risk_impact_id "
					+ "LEFT OUTER JOIN risk_probability rp ON risk_probability_id_fk = risk_probability_id "
					+ "LEFT OUTER JOIN `work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN risk_status rs ON risk_status_id_fk = risk_status_id "
					+ "LEFT OUTER JOIN soft_delete_status s ON soft_delete_status_id_fk = soft_delete_status_id "
					+ "LEFT OUTER JOIN risk_area ra ON rsa.risk_area_id_fk = ra.risk_area_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "
					+ "where risk_id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, riskId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new Risk();
				obj.setRiskId(resultSet.getString("risk_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setRiskOwner(resultSet.getString("risk_owner"));
				obj.setRiskSubAreaId(resultSet.getString("risk_sub_area_id_fk"));
				obj.setRiskDescription(resultSet.getString("risk_description"));
				obj.setRiskCategoryId(resultSet.getString("risk_category_id_fk"));
				obj.setRiskProbabilityId(resultSet.getString("risk_probability_id_fk"));
				obj.setRiskImpactId(resultSet.getString("risk_impact_id_fk"));
				obj.setRiskPriorityNumber(resultSet.getString("risk_priority_number"));
				obj.setDateOfIdentification(resultSet.getString("date_of_identification"));
				obj.setRiskMitigationPlan(resultSet.getString("risk_mitigation_plan"));
				obj.setTargetDateForMitigation(resultSet.getString("target_date_for_mitigation"));
				obj.setResponsiblePersonName(resultSet.getString("responsible_person_name"));
				obj.setRiskStatusId(resultSet.getString("risk_status_id_fk"));
				obj.setRiskAnalysisReportLink(resultSet.getString("risk_analysis_report_link"));
				obj.setRemarks(resultSet.getString("r.remarks"));	
				obj.setRiskMitigatedOn(resultSet.getString("risk_mitigated_on"));
				obj.setWorkName(resultSet.getString("work_name"));
				obj.setRiskSubArea(resultSet.getString("rsa.risk_sub_area_name"));
				obj.setRiskCategory(resultSet.getString("rc.category"));
				obj.setRiskImpact(resultSet.getString("ri.impact"));
				obj.setRiskProbability(resultSet.getString("rp.probability"));
				obj.setRiskStatus(resultSet.getString("rs.risk_status"));
				obj.setStatus(resultSet.getString("s.status"));
				obj.setRiskAreaId(resultSet.getString("rsa.risk_area_id_fk"));
				obj.setRiskArea(resultSet.getString("ra.risk_area"));
				obj.setProjectId(resultSet.getString("w.project_id_fk"));
				obj.setProjectName(resultSet.getString("p.project_description"));
				
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
	
		return obj;
	}
	
	/**
	 * This method update the risk
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRisk(Risk obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = dataSource.getConnection();
			
			String qry = "UPDATE risk SET risk_id = ?,work_id_fk = ?,risk_owner = ?,risk_sub_area_id_fk = ?,risk_description = ?,risk_category_id_fk = ?,risk_probability_id_fk = ?,risk_impact_id_fk = ?,risk_priority_number = ?,"  
					+"date_of_identification = ?,risk_mitigation_plan = ?,target_date_for_mitigation = ?,responsible_person_name = ?,risk_status_id_fk = ?,risk_analysis_report_link = ?,remarks = ?,risk_mitigated_on  = ? "
					+ "WHERE risk_id = ?";
					
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			if(StringUtils.isEmpty(org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId()))) {
				String riskId = obj.getWorkId() + "-" + RandomGenerator.generateNumericRandom(CommonConstants.RANDOM_NUMERIC_NUMBER_LENGTH);
				stmt.setString(c++, riskId);
			}else {
				stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskId())?obj.getRiskId():null);
			}
			//stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskId())?obj.getRiskId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getWorkId())?obj.getWorkId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskOwner())?obj.getRiskOwner():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskSubAreaId())?obj.getRiskSubAreaId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskDescription())?obj.getRiskDescription():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskCategoryId())?obj.getRiskCategoryId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskProbabilityId())?obj.getRiskProbabilityId():null);	
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskImpactId())?obj.getRiskImpactId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskPriorityNumber())?obj.getRiskPriorityNumber():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getDateOfIdentification())?obj.getDateOfIdentification():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskMitigationPlan())?obj.getRiskMitigationPlan():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getTargetDateForMitigation())?obj.getTargetDateForMitigation():null);	
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getResponsiblePersonName())?obj.getResponsiblePersonName():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskStatusId())?obj.getRiskStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskAnalysisReportLink())?obj.getRiskAnalysisReportLink():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskMitigatedOn())?obj.getRiskMitigatedOn():null);
			
			stmt.setString(c++, obj.getRiskId());			
			int count = stmt.executeUpdate();  
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, rs);
		}
		return flag;
	}
	
	/**
	 * This method get the risk ranking list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskRankingList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select risk_ranking_id,ranking from risk_ranking";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setRiskRankingId(resultSet.getString("risk_ranking_id"));
				obj.setRiskRanking(resultSet.getString("ranking"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method update risk status
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRiskStatus(Risk obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = dataSource.getConnection();
			
			String qry = "UPDATE risk SET risk_status_id_fk = ?,risk_mitigated_on = ? WHERE risk_id = ?";					
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			stmt.setString(c++, "2");
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRiskMitigatedOn())?obj.getRiskMitigatedOn():null);	
			stmt.setString(c++, obj.getRiskId());			
			int count = stmt.executeUpdate();  
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, rs);
		}
		return flag;
	}
	
	/**
	 * This method add the risk
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean addRisk(Risk obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			
			String qry = "INSERT INTO risk (risk_id,work_id_fk,risk_owner,risk_sub_area_id_fk,risk_description,risk_category_id_fk,risk_probability_id_fk,risk_impact_id_fk,risk_priority_number,"  
					+"date_of_identification,risk_mitigation_plan,target_date_for_mitigation,responsible_person_name,risk_status_id_fk,risk_analysis_report_link,remarks,risk_mitigated_on,created_by)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			stmt = connection.prepareStatement(qry);
			
			int pCount = 1;
			
			
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskId())?obj.getRiskId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getWorkId())?obj.getWorkId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskOwner())?obj.getRiskOwner():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskSubAreaId())?obj.getRiskSubAreaId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskDescription())?obj.getRiskDescription():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskCategoryId())?obj.getRiskCategoryId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskProbabilityId())?obj.getRiskProbabilityId():null);	
			
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskImpactId())?obj.getRiskImpactId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskPriorityNumber())?obj.getRiskPriorityNumber():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getDateOfIdentification())?obj.getDateOfIdentification():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskMitigationPlan())?obj.getRiskMitigationPlan():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getTargetDateForMitigation())?obj.getTargetDateForMitigation():null);	
			
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getResponsiblePersonName())?obj.getResponsiblePersonName():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskStatusId())?obj.getRiskStatusId():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskAnalysisReportLink())?obj.getRiskAnalysisReportLink():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskMitigatedOn())?obj.getRiskMitigatedOn():null);
			stmt.setString(pCount++, !StringUtils.isEmpty(obj.getCreatedBy())?obj.getCreatedBy():null);
			
			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag = true;;
			}
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}
	
	/**
	 * This method upload the risks
	 * 
	 * @param risksList is list type object
	 * @return type of this method is count that is integer type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public int uploadRisks(List<Risk> risksList) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = dataSource.getConnection();
			
			String qry = "INSERT INTO risk (risk_id,work_id_fk,risk_owner,risk_sub_area_id_fk,risk_description,risk_category_id_fk,risk_probability_id_fk,risk_impact_id_fk,risk_priority_number,"  
					+"date_of_identification,risk_mitigation_plan,target_date_for_mitigation,responsible_person_name,risk_status_id_fk,risk_analysis_report_link,remarks,risk_mitigated_on,created_by)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			stmt = connection.prepareStatement(qry);
			for (Risk obj : risksList) {
				
				String riskId = null;
				String riskAreaId = null;
				String riskSubAreaId = null;
				String riskCategoryId = null;
				String riskProbabilityId = null;
				String riskImpactId = null;
				String riskStatusId = null;
				
				if(!StringUtils.isEmpty(obj)) {
					if(!StringUtils.isEmpty(obj.getRiskProbability())) {
						riskProbabilityId = getRiskProbabilityId(obj.getRiskProbability(), connection);
						obj.setRiskProbabilityId(riskProbabilityId);
					}
					if(!StringUtils.isEmpty(obj.getRiskCategory())) {
						riskCategoryId = getRiskCategoryId(obj.getRiskCategory(), connection);
						obj.setRiskCategoryId(riskCategoryId);
					}
					if(!StringUtils.isEmpty(obj.getRiskImpact())) {
						riskImpactId = getRiskImpactId(obj.getRiskImpact(), connection);
						obj.setRiskImpactId(riskImpactId);
					}
					if(!StringUtils.isEmpty(obj.getRiskStatus())) {
						riskStatusId = getRiskStatusId(obj.getRiskStatus(), connection);
						obj.setRiskStatusId(riskStatusId);
					}
					if(!StringUtils.isEmpty(obj.getRiskArea())) {
						riskAreaId = getRiskAreaId(obj.getRiskArea(), connection);
						obj.setRiskAreaId(riskAreaId);
					}
					if(!StringUtils.isEmpty(obj.getRiskSubArea())) {
						riskSubAreaId = getRiskSubAreaId(riskAreaId,obj.getRiskSubArea(), connection);
						obj.setRiskSubAreaId(riskSubAreaId);
					}
					
					if(StringUtils.isEmpty(org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId()))) {
						riskId = obj.getWorkId() + "-" + RandomGenerator.generateNumericRandom(CommonConstants.RANDOM_NUMERIC_NUMBER_LENGTH);
						obj.setRiskId(riskId);
					}
					
					int pCount = 1;
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskId())?obj.getRiskId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getWorkId())?obj.getWorkId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskOwner())?obj.getRiskOwner():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskSubAreaId())?obj.getRiskSubAreaId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskDescription())?obj.getRiskDescription():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskCategoryId())?obj.getRiskCategoryId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskProbabilityId())?obj.getRiskProbabilityId():null);	
					
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskImpactId())?obj.getRiskImpactId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskPriorityNumber())?obj.getRiskPriorityNumber():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getDateOfIdentification())?obj.getDateOfIdentification():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskMitigationPlan())?obj.getRiskMitigationPlan():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getTargetDateForMitigation())?obj.getTargetDateForMitigation():null);	
					
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getResponsiblePersonName())?obj.getResponsiblePersonName():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskStatusId())?obj.getRiskStatusId():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskAnalysisReportLink())?obj.getRiskAnalysisReportLink():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getRiskMitigatedOn())?obj.getRiskMitigatedOn():null);
					stmt.setString(pCount++, !StringUtils.isEmpty(obj.getCreatedBy())?obj.getCreatedBy():null);
					
					stmt.addBatch();
				}
				
			}
			
			int[] c = stmt.executeBatch();
			for (int i = 0; i < c.length; i++) {
				count = count + c[i];
			}
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return count;
	}

	
	/**
	 * This method
	 * 
	 * @param riskAreaId it is string type variable that holds the riskAreaId
	 * @param riskSubArea it is string type variable that holds the riskSubArea
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskSubAreaId(String riskAreaId, String riskSubArea, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_sub_area_id from risk_sub_area WHERE risk_sub_area_name = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskSubArea);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_sub_area_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_sub_area (risk_sub_area_name,risk_area_id_fk) VALUES (?,?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskSubArea);
				stmt.setString(2, riskAreaId);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}


	/**
	 * This method get the risk area id
	 * 
	 * @param riskArea it is string type variable that holds the riskArea
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskAreaId(String riskArea, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_area_id from risk_area WHERE risk_area = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskArea);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_area_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_area (risk_area) VALUES (?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskArea);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}


	/**
	 * This method get the risk status id
	 * 
	 * @param riskStatus it is string type variable that holds the riskStatus
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskStatusId(String riskStatus, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_status_id from risk_status WHERE risk_status = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskStatus);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_status_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_status (risk_status) VALUES (?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskStatus);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}

	
	/**
	 * This method get the risk impact id
	 * 
	 * @param riskImpact it is string type variable that holds the riskImpact
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskImpactId(String riskImpact, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_impact_id from risk_impact WHERE impact = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskImpact);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_impact_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_impact (impact) VALUES (?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskImpact);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}


	/**
	 * This method get the risk category id
	 * 
	 * @param riskCategory it is string type variable that holds the riskCategory
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskCategoryId(String riskCategory, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_category_id from risk_category WHERE category = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskCategory);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_category_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_category (category) VALUES (?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskCategory);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}


	/**
	 * This method get the risk probability id
	 * 
	 * @param riskProbability it is string type variable that holds the riskProbability
	 * @param connection is object for the Connection Interface
	 * @return type of this method is id that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getRiskProbabilityId(String riskProbability, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String id = null;
		try{
			String qry = "select risk_probability_id from risk_probability WHERE probability = ?";
			stmt = connection.prepareStatement(qry);
			stmt.setString(1, riskProbability);
			rs = stmt.executeQuery(); 
			if(rs.next()) {
				id = rs.getString("risk_probability_id");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(id)) {
				String insertQry = "INSERT INTO risk_probability (probability) VALUES (?)";
				stmt = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, riskProbability);
				int c = stmt.executeUpdate(); 
				rs = stmt.getGeneratedKeys();
				if(c > 0) {
					if(rs.next()) {
						id = String.valueOf(rs.getInt(1));
					}
				}
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return id;
	}


	/**
	 * This method get the risk area list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskAreaListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Area ID` as risk_area_id_fk,`Area` as risk_area from risk_view where `Area ID` is not null and `Area ID` <> '' GROUP BY `Area ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskAreaId(resultSet.getString("risk_area_id_fk"));
				obj.setRiskArea(resultSet.getString("risk_area"));		
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk sub area list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSubAreaListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Sub Area ID` as risk_sub_area_id_fk,`Sub Area` as risk_sub_area_name from risk_view where `Sub Area ID` is not null and `Sub Area ID` <> '' GROUP BY `Sub Area ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskSubAreaId(resultSet.getString("risk_sub_area_id_fk"));
				obj.setRiskSubArea(resultSet.getString("risk_sub_area_name"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk category list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskCategoryListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Category ID` as risk_category_id_fk,`Category` as category from risk_view where `Category ID` is not null and `Category ID` <> '' GROUP BY `Category ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskCategoryId(resultSet.getString("risk_category_id_fk"));
				obj.setRiskCategory(resultSet.getString("category"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk impact list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskImpactListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Impact ID` as risk_impact_id_fk,Impact as impact from risk_view where `Impact ID` is not null and `Impact ID` <> '' GROUP BY `Impact ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskImpactId(resultSet.getString("risk_impact_id_fk"));
				obj.setRiskImpact(resultSet.getString("impact"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk probability list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskProbabilityListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Probability ID` as risk_probability_id_fk,Probability as probability from risk_view where `Probability ID` is not null and `Probability ID` <> '' GROUP BY `Probability ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskProbabilityId(resultSet.getString("risk_probability_id_fk"));
				obj.setRiskProbability(resultSet.getString("probability"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk status list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSatausListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Status ID` as risk_status_id_fk,`Status` as risk_status from risk_view where `Status ID` is not null and `Status ID` <> '' GROUP BY `Status ID`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskStatusId(resultSet.getString("risk_status_id_fk"));
				obj.setRiskStatus(resultSet.getString("risk_status"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk classification list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskClassificationListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select Classification from risk_view where `Classification` is not null and `Classification` <> '' GROUP BY Classification ORDER BY FIELD(Classification,'High','Substantial', 'Moderate', 'Low')";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskClassificationName(resultSet.getString("Classification"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the risk priority list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskPriorityListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Priority Number` as risk_priority_number from risk_view where `Priority Number` is not null GROUP BY `Priority Number` ORDER BY `Priority Number` asc";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				
				obj.setRiskPriorityNumber(resultSet.getString("risk_priority_number"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the responsible person list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getResponsiblePersonsListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Risk> objsList = new ArrayList<Risk>();
		Risk obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select `Responsible Person` as responsible_person_name from risk_view where `Responsible Person` is not null and `Responsible Person` <> '' GROUP BY `Responsible Person`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Risk();
				obj.setResponsiblePersonName(resultSet.getString("responsible_person_name"));				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	
	
	
}
