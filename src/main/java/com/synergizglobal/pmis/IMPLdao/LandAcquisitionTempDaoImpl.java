package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LandAcquisitionTempDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.LandAcquisitionTemp;
@Repository
public class LandAcquisitionTempDaoImpl implements LandAcquisitionTempDao{
	@Autowired
	DataSource mrvcDataSource;
	
	/**
	 * This method get the land acquisition list
	 * @return type of this method is objsList that is List type object   
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisitionTemp> getLandAcquisitionList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LandAcquisitionTemp> objsList = new ArrayList<LandAcquisitionTemp>();
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select land_acqisition_id,work_id_fk,drawing_no,map_no,plot_no,location,sub_location,latitude,longitude,chainage_from,"
					+ "chainage_to,la_sub_category_id_fk,area,area_units,survey_status_id_fk,valuation_status_id_fk,approval_status_id_fk,"
					+ "payment_status_id_fk,aquisition_status_id_fk,payment_in_cr,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,la.remarks,la_sub_category_name,work_name,s1.status_name as survey_status,"
					+ "s2.status_name as valuation_status,s3.status_name as approval_status,s4.status_name as payment_status,s5.status_name as aquisition_status,lasc.la_category_id_fk,lac.la_category_name "
					+ "from land_acqisition la "
					+ "LEFT OUTER JOIN la_sub_category lasc ON la_sub_category_id_fk = la_sub_category_id "
					+ "LEFT OUTER JOIN `work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN `status` s1 ON survey_status_id_fk = s1.status_id "
					+ "LEFT OUTER JOIN `status` s2 ON valuation_status_id_fk = s2.status_id "
					+ "LEFT OUTER JOIN `status` s3 ON approval_status_id_fk = s3.status_id "
					+ "LEFT OUTER JOIN `status` s4 ON payment_status_id_fk = s4.status_id "
					+ "LEFT OUTER JOIN `status` s5 ON aquisition_status_id_fk = s5.status_id "
					+ "LEFT OUTER JOIN la_category lac ON lasc.la_category_id_fk = la_category_id ";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new LandAcquisitionTemp();
				obj.setLandAcquisitionId(resultSet.getString("land_acqisition_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setDrawingNo(resultSet.getString("drawing_no"));
				obj.setMapNo(resultSet.getString("map_no"));
				obj.setPlotNo(resultSet.getString("plot_no"));
				obj.setLocation(resultSet.getString("location"));
				obj.setSubLocation(resultSet.getString("sub_location"));
				obj.setLatitude(resultSet.getString("latitude"));
				obj.setLongitude(resultSet.getString("longitude"));
				obj.setChainageFrom(resultSet.getString("chainage_from"));
				obj.setChainageTo(resultSet.getString("chainage_to"));
				obj.setLaSubCategoryId(resultSet.getString("la_sub_category_id_fk"));
				obj.setArea(resultSet.getString("area"));
				obj.setAreaUnits(resultSet.getString("area_units"));
				obj.setSurveyStatusId(resultSet.getString("survey_status_id_fk"));
				obj.setValuationStatusId(resultSet.getString("valuation_status_id_fk"));
				obj.setApprovalStatusId(resultSet.getString("approval_status_id_fk"));
				obj.setPaymentStatusId(resultSet.getString("payment_status_id_fk"));
				obj.setAcquisitionStatusId(resultSet.getString("aquisition_status_id_fk"));
				obj.setPaymentInCr(resultSet.getString("payment_in_cr"));
				obj.setPaymentDate(resultSet.getString("payment_date"));
				obj.setRemarks(resultSet.getString("la.remarks"));				
				obj.setLaSubCategoryName(resultSet.getString("la_sub_category_name"));
				obj.setWorkName(resultSet.getString("work_name"));
				obj.setSurveyStatus(resultSet.getString("survey_status"));
				obj.setValuationStatus(resultSet.getString("valuation_status"));
				obj.setApprovalStatus(resultSet.getString("approval_status"));
				obj.setPaymentStatus(resultSet.getString("payment_status"));
				obj.setAcquisitionStatus(resultSet.getString("aquisition_status"));
				
				obj.setLaCategoryId(resultSet.getString("lasc.la_category_id_fk"));
				obj.setLaCategoryName(resultSet.getString("lac.la_category_name"));
				
				String activeStatus = "";
				
				if(!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("1") 
						&& !StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("1")) {
					activeStatus = "Not Started";
				} else if (!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("3") 
						&& !StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("3")) {
					activeStatus = "Completed";
				} else  if (!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("2")) {
					activeStatus = "Survey";
				} else  if (!StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("2")) {
					activeStatus = "Valuation";
				} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("2")) {
					activeStatus = "Approval";
				} else  if (!StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("2")) {
					activeStatus = "Acquisition";
				} else  if (!StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("2")) {
					activeStatus = "Payment";
				}
				
				 else  if (!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("1")) {
						activeStatus = "Survey Not Started";
					} else  if (!StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("1")) {
						activeStatus = "Valuation Not Started";
					} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")) {
						activeStatus = "Approval Not Started";
					} else  if (!StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("1")) {
						activeStatus = "Payment Not Started";
					} else  if (!StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("1")) {
						activeStatus = "Acquisition Not Started";
					}
				
				obj.setActiveStatus(activeStatus);
				
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
	 * This method get the work list
	 *
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisitionTemp> getWorksList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LandAcquisitionTemp> objsList = new ArrayList<LandAcquisitionTemp>();
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select work_id,work_name from `work`";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new LandAcquisitionTemp();
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
	 * This method get the land acquisition category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisitionTemp> getLandAcquisitionCategoryList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LandAcquisitionTemp> objsList = new ArrayList<LandAcquisitionTemp>();
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select la_category_id,la_category_name from la_category";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new LandAcquisitionTemp();
				obj.setLaCategoryId(resultSet.getString("la_category_id"));
				obj.setLaCategoryName(resultSet.getString("la_category_name"));
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
	 * This method get the land acquisition sub category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisitionTemp> getLandAcquisitionSubCategoryList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LandAcquisitionTemp> objsList = new ArrayList<LandAcquisitionTemp>();
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select la_sub_category_id,la_sub_category_name,la_category_id_fk,la_category_name "
					+ "from la_sub_category lasc "
					+ "LEFT OUTER JOIN la_category lac ON la_category_id_fk = la_category_id";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new LandAcquisitionTemp();
				obj.setLaSubCategoryId(resultSet.getString("la_sub_category_id"));
				obj.setLaSubCategoryName(resultSet.getString("la_sub_category_name"));
				obj.setLaCategoryId(resultSet.getString("la_category_id_fk"));
				obj.setLaCategoryName(resultSet.getString("la_category_name"));
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
	 * This method get the land acquisition status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisitionTemp> getLandAcquisitionSatausList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<LandAcquisitionTemp> objsList = new ArrayList<LandAcquisitionTemp>();
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select status_id,status_name from status";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new LandAcquisitionTemp();
				obj.setStatusId(resultSet.getString("status_id"));
				obj.setStatusName(resultSet.getString("status_name"));
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
	 * This method add land acquisition
	 * 
	 * @param obj is object for the model class LandAcquisitionTemp
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean addLandAcquisition(LandAcquisitionTemp obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = mrvcDataSource.getConnection();
			String qry = "INSERT INTO land_acqisition (work_id_fk,drawing_no,map_no,plot_no,location,sub_location,latitude,longitude,chainage_from,chainage_to,"
					+ "la_sub_category_id_fk,area,area_units,survey_status_id_fk,valuation_status_id_fk,approval_status_id_fk,payment_status_id_fk,"
					+ "aquisition_status_id_fk,payment_in_cr,remarks)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			stmt.setString(c++, !StringUtils.isEmpty(obj.getWorkId())?obj.getWorkId():null);
			
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
	 * This method get the land acquisition
	 * 
	 * @param landAcquisitionId it is string type variable that holds the land acquisition id
	 * @return type of this method is obj that is model class Land acquisition type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public LandAcquisitionTemp getLandAcquisition(String landAcquisitionId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		LandAcquisitionTemp obj = null;
		try {
			connection = mrvcDataSource.getConnection();
			String qry = "select land_acqisition_id,work_id_fk,drawing_no,map_no,plot_no,location,sub_location,latitude,longitude,chainage_from,"
					+ "chainage_to,la_sub_category_id_fk,area,area_units,survey_status_id_fk,valuation_status_id_fk,approval_status_id_fk,"
					+ "payment_status_id_fk,aquisition_status_id_fk,payment_in_cr,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,la.remarks,la_sub_category_name,work_name,s1.status_name as survey_status,"
					+ "s2.status_name as valuation_status,s3.status_name as approval_status,s4.status_name as payment_status,s5.status_name as aquisition_status,lasc.la_category_id_fk,lac.la_category_name "
					+ "from land_acqisition la "
					+ "LEFT OUTER JOIN la_sub_category lasc ON la_sub_category_id_fk = la_sub_category_id "
					+ "LEFT OUTER JOIN `work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN `status` s1 ON survey_status_id_fk = s1.status_id "
					+ "LEFT OUTER JOIN `status` s2 ON valuation_status_id_fk = s2.status_id "
					+ "LEFT OUTER JOIN `status` s3 ON approval_status_id_fk = s3.status_id "
					+ "LEFT OUTER JOIN `status` s4 ON payment_status_id_fk = s4.status_id "
					+ "LEFT OUTER JOIN `status` s5 ON aquisition_status_id_fk = s5.status_id "
					+ "LEFT OUTER JOIN la_category lac ON lasc.la_category_id_fk = la_category_id "
					+ "where land_acqisition_id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, landAcquisitionId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new LandAcquisitionTemp();
				obj.setLandAcquisitionId(resultSet.getString("land_acqisition_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setDrawingNo(resultSet.getString("drawing_no"));
				obj.setMapNo(resultSet.getString("map_no"));
				obj.setPlotNo(resultSet.getString("plot_no"));
				obj.setLocation(resultSet.getString("location"));
				obj.setSubLocation(resultSet.getString("sub_location"));
				obj.setLatitude(resultSet.getString("latitude"));
				obj.setLongitude(resultSet.getString("longitude"));
				obj.setChainageFrom(resultSet.getString("chainage_from"));
				obj.setChainageTo(resultSet.getString("chainage_to"));
				obj.setLaSubCategoryId(resultSet.getString("la_sub_category_id_fk"));
				obj.setArea(resultSet.getString("area"));
				obj.setAreaUnits(resultSet.getString("area_units"));
				obj.setSurveyStatusId(resultSet.getString("survey_status_id_fk"));
				obj.setValuationStatusId(resultSet.getString("valuation_status_id_fk"));
				obj.setApprovalStatusId(resultSet.getString("approval_status_id_fk"));
				obj.setPaymentStatusId(resultSet.getString("payment_status_id_fk"));
				obj.setAcquisitionStatusId(resultSet.getString("aquisition_status_id_fk"));
				obj.setPaymentInCr(resultSet.getString("payment_in_cr"));
				obj.setPaymentDate(resultSet.getString("payment_date"));
				obj.setRemarks(resultSet.getString("la.remarks"));				
				obj.setLaSubCategoryName(resultSet.getString("la_sub_category_name"));
				obj.setWorkName(resultSet.getString("work_name"));
				obj.setSurveyStatus(resultSet.getString("survey_status"));
				obj.setValuationStatus(resultSet.getString("valuation_status"));
				obj.setApprovalStatus(resultSet.getString("approval_status"));
				obj.setPaymentStatus(resultSet.getString("payment_status"));
				obj.setAcquisitionStatus(resultSet.getString("aquisition_status"));
				
				obj.setLaCategoryId(resultSet.getString("lasc.la_category_id_fk"));
				obj.setLaCategoryName(resultSet.getString("lac.la_category_name"));
				
				String activeStatus = "";
				if(!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("1") 
						&& !StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("1")) {
					activeStatus = "Not Started";
				} else if (!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("3") 
						&& !StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("3")) {
					activeStatus = "Completed";
				} else  if (!StringUtils.isEmpty(obj.getSurveyStatusId()) && obj.getSurveyStatusId().equals("2")) {
					activeStatus = "Survey";
				} else  if (!StringUtils.isEmpty(obj.getValuationStatusId()) && obj.getValuationStatusId().equals("2")) {
					activeStatus = "Valuation";
				} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("2")) {
					activeStatus = "Approval";
				} else  if (!StringUtils.isEmpty(obj.getPaymentStatusId()) && obj.getPaymentStatusId().equals("2")) {
					activeStatus = "Payment";
				} else  if (!StringUtils.isEmpty(obj.getAcquisitionStatusId()) && obj.getAcquisitionStatusId().equals("2")) {
					activeStatus = "Acquisition";
				} 
				
				obj.setActiveStatus(activeStatus);
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
	 * This method update the land acquisition
	 * 
	 * @param obj is object for the model class LandAcquisitionTemp
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisition(LandAcquisitionTemp obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = mrvcDataSource.getConnection();
			String qry = "UPDATE land_acqisition SET survey_status_id_fk = ?,valuation_status_id_fk = ?,approval_status_id_fk = ?,payment_status_id_fk = ?,"
					+ "aquisition_status_id_fk = ?,payment_in_cr = ?,payment_date = ?,remarks = ? "
					+ "WHERE land_acqisition_id = ?";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			stmt.setString(c++, !StringUtils.isEmpty(obj.getSurveyStatusId())?obj.getSurveyStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getValuationStatusId())?obj.getValuationStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getApprovalStatusId())?obj.getApprovalStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentStatusId())?obj.getPaymentStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getAcquisitionStatusId())?obj.getAcquisitionStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentInCr())?obj.getPaymentInCr():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentDate())?obj.getPaymentDate():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getLandAcquisitionId())?obj.getLandAcquisitionId():null);			
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
	 * This method update the land acquisition1
	
	 * @param obj is object for the model class LandAcquisitionTemp
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisition1(LandAcquisitionTemp obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = mrvcDataSource.getConnection();
			String qry = "UPDATE land_acqisition SET payment_in_cr = ?,payment_date = ?,remarks = ? "
					+ "WHERE land_acqisition_id = ?";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentInCr())?obj.getPaymentInCr():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentDate())?obj.getPaymentDate():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getLandAcquisitionId())?obj.getLandAcquisitionId():null);			
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
	 * This method update the land acquisition status
	 * 
	 * @param obj is object for the model class LandAcquisitionTemp
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisitionStatus(LandAcquisitionTemp obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = mrvcDataSource.getConnection();
			String qry = "UPDATE land_acqisition SET survey_status_id_fk = ?,valuation_status_id_fk = ?,approval_status_id_fk = ?,payment_status_id_fk = ?,"  
					+"aquisition_status_id_fk = ? WHERE land_acqisition_id = ?";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Acquisition")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Payment")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Approval")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Valuation")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Survey")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Not Started")) {
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
			}					
			
			stmt.setString(c++, obj.getLandAcquisitionId());			
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
}
