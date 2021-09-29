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

import com.synergizglobal.pmis.Idao.RandRDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.RandR;

@Repository
public class RandRDaoImpl implements RandRDao{
	@Autowired
	DataSource dataSource;
	/**
	 * This method get RandR list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<RandR> getRandRList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<RandR> objsList = new ArrayList<RandR>();
		RandR obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select r_and_r_id,work_id_fk,drawing_no,latitude,longitude,map_no,plot_no,location,sub_location,chainage_from,chainage_to,"
					+ "affected_structure,affected_people,`phase` as phaseName,name_of_owner,identification_status_id_fk,approval_status_id_fk,house_allocated,"
					+ "resettlement_status_id_fk,rehabilitation_status_id_fk,payment_amount,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,r.remarks,"
					+ "work_name,"
					+ "s1.status_name as identification_status,s2.status_name as approval_status,s3.status_name as resettlement_status,s4.status_name as rehabilitation_status "
					+ "from r_and_r r "
					+ "LEFT OUTER JOIN `r_and_r_work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN `r_and_r_status` s1 ON identification_status_id_fk = s1.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s2 ON approval_status_id_fk = s2.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s3 ON resettlement_status_id_fk = s3.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s4 ON rehabilitation_status_id_fk = s4.status_id ";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new RandR();
				obj.setRandRId(resultSet.getString("r_and_r_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setDrawingNo(resultSet.getString("drawing_no"));
				obj.setMapNo(resultSet.getString("map_no"));
				obj.setLocation(resultSet.getString("location"));
				obj.setSubLocation(resultSet.getString("sub_location"));
				obj.setLatitude(resultSet.getString("latitude"));
				obj.setLongitude(resultSet.getString("longitude"));
				obj.setChainageFrom(resultSet.getString("chainage_from"));
				obj.setChainageTo(resultSet.getString("chainage_to"));
				obj.setAffectedStructure(resultSet.getString("affected_structure"));
				obj.setAffectedPeople(resultSet.getString("affected_people"));
				obj.setPhase(resultSet.getString("phaseName"));
				obj.setNameOfOwner(resultSet.getString("name_of_owner"));
				obj.setHouseAllocated(resultSet.getString("house_allocated"));
				
				obj.setIdentificationStatusId(resultSet.getString("identification_status_id_fk"));				
				obj.setApprovalStatusId(resultSet.getString("approval_status_id_fk"));
				obj.setResettlementStatusId(resultSet.getString("resettlement_status_id_fk"));
				obj.setRehabilitationStatusId(resultSet.getString("rehabilitation_status_id_fk"));
				
				obj.setPaymentAmount(resultSet.getString("payment_amount"));
				obj.setPaymentDate(resultSet.getString("payment_date"));
				obj.setRemarks(resultSet.getString("r.remarks"));
				
				obj.setWorkName(resultSet.getString("work_name"));
				obj.setIdentificationStatus(resultSet.getString("identification_status"));
				obj.setApprovalStatus(resultSet.getString("approval_status"));
				obj.setResettlementStatus(resultSet.getString("resettlement_status"));
				obj.setRehabilitationStatus(resultSet.getString("rehabilitation_status"));
				
				String activeStatus = "";
				if(!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("1") 
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("1")) {
					activeStatus = "Not Started";
				} else if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("3") 
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("3")) {
					activeStatus = "Completed";
				} else  if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("2")) {
					activeStatus = "Identification";
				} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("2")) {
					activeStatus = "Approval";
				} else  if (!StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("2")) {
					activeStatus = "Resettlement";
				} else  if (!StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("2")) {
					activeStatus = "Rehabilitation";
				}
				
				 else  if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("1")) {
						activeStatus = "Identification Not Started";
					} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")) {
						activeStatus = "Approval Not Started";
					} else  if (!StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("1")) {
						activeStatus = "Resettlement Not Started";
					} else  if (!StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("1")) {
						activeStatus = "Rehabilitation Not Started";
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
	 * This method get RandR r_and_r_status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<RandR> getRandRSatausList() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<RandR> objsList = new ArrayList<RandR>();
		RandR obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select status_id,status_name from r_and_r_status";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new RandR();
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
	 * This method get RadnR
	 * 
	 * @param randRId it is string type variable that holds the randRId
	 * @return type of this method is obj that is model class RandR type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public RandR getRandR(String randRId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RandR obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select r_and_r_id,work_id_fk,drawing_no,latitude,longitude,map_no,plot_no,location,sub_location,chainage_from,chainage_to,"
					+ "affected_structure,affected_people,`phase` as phaseName,name_of_owner,identification_status_id_fk,approval_status_id_fk,house_allocated,"
					+ "resettlement_status_id_fk,rehabilitation_status_id_fk,payment_amount,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,r.remarks,"
					+ "work_name,"
					+ "s1.status_name as identification_status,s2.status_name as approval_status,s3.status_name as resettlement_status,s4.status_name as rehabilitation_status "
					+ "from r_and_r r "
					+ "LEFT OUTER JOIN `r_and_r_work` w ON work_id_fk = work_id "					
					+ "LEFT OUTER JOIN `r_and_r_status` s1 ON identification_status_id_fk = s1.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s2 ON approval_status_id_fk = s2.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s3 ON resettlement_status_id_fk = s3.status_id "
					+ "LEFT OUTER JOIN `r_and_r_status` s4 ON rehabilitation_status_id_fk = s4.status_id "
					+ "where r_and_r_id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, randRId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new RandR();
				obj.setRandRId(resultSet.getString("r_and_r_id"));
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setDrawingNo(resultSet.getString("drawing_no"));
				obj.setMapNo(resultSet.getString("map_no"));
				obj.setLocation(resultSet.getString("location"));
				obj.setSubLocation(resultSet.getString("sub_location"));
				obj.setLatitude(resultSet.getString("latitude"));
				obj.setLongitude(resultSet.getString("longitude"));
				obj.setChainageFrom(resultSet.getString("chainage_from"));
				obj.setChainageTo(resultSet.getString("chainage_to"));
				obj.setAffectedStructure(resultSet.getString("affected_structure"));
				obj.setAffectedPeople(resultSet.getString("affected_people"));
				obj.setPhase(resultSet.getString("phaseName"));
				obj.setNameOfOwner(resultSet.getString("name_of_owner"));
				obj.setHouseAllocated(resultSet.getString("house_allocated"));
				
				obj.setIdentificationStatusId(resultSet.getString("identification_status_id_fk"));				
				obj.setApprovalStatusId(resultSet.getString("approval_status_id_fk"));
				obj.setResettlementStatusId(resultSet.getString("resettlement_status_id_fk"));
				obj.setRehabilitationStatusId(resultSet.getString("rehabilitation_status_id_fk"));
				
				obj.setPaymentAmount(resultSet.getString("payment_amount"));
				obj.setPaymentDate(resultSet.getString("payment_date"));
				obj.setRemarks(resultSet.getString("r.remarks"));
				
				obj.setWorkName(resultSet.getString("work_name"));
				obj.setIdentificationStatus(resultSet.getString("identification_status"));
				obj.setApprovalStatus(resultSet.getString("approval_status"));
				obj.setResettlementStatus(resultSet.getString("resettlement_status"));
				obj.setRehabilitationStatus(resultSet.getString("rehabilitation_status"));
				
				String activeStatus = "";
				if(!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("1") 
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("1")
						&& !StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("1")) {
					activeStatus = "Not Started";
				} else if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("3") 
						&& !StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("3")
						&& !StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("3")) {
					activeStatus = "Completed";
				} else  if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("2")) {
					activeStatus = "Identification";
				} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("2")) {
					activeStatus = "Approval";
				} else  if (!StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("2")) {
					activeStatus = "Resettlement";
				} else  if (!StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("2")) {
					activeStatus = "Rehabilitation";
				}
				
				 else  if (!StringUtils.isEmpty(obj.getIdentificationStatusId()) && obj.getIdentificationStatusId().equals("1")) {
						activeStatus = "Identification Not Started";
					} else  if (!StringUtils.isEmpty(obj.getApprovalStatusId()) && obj.getApprovalStatusId().equals("1")) {
						activeStatus = "Approval Not Started";
					} else  if (!StringUtils.isEmpty(obj.getResettlementStatusId()) && obj.getResettlementStatusId().equals("1")) {
						activeStatus = "Resettlement Not Started";
					} else  if (!StringUtils.isEmpty(obj.getRehabilitationStatusId()) && obj.getRehabilitationStatusId().equals("1")) {
						activeStatus = "Rehabilitation Not Started";
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
	 * This method update the RandR
	 * 
	 * @param obj is object for the model class RandR
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRandR(RandR obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = dataSource.getConnection();
			/*String qry = "UPDATE r_and_r SET identification_status_id_fk = ?,approval_status_id_fk = ?,house_allocated = ?," 
					+"resettlement_status_id_fk = ?,rehabilitation_status_id_fk = ?,payment_amount = ?,payment_date = ?,remarks = ? "
					+ "WHERE r_and_r_id = ?";*/
			String qry = "UPDATE r_and_r SET house_allocated = ?,payment_amount = ?,payment_date = ?,remarks = ? "
					+ "WHERE r_and_r_id = ?";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			//stmt.setString(c++, !StringUtils.isEmpty(obj.getIdentificationStatusId())?obj.getIdentificationStatusId():null);
			//stmt.setString(c++, !StringUtils.isEmpty(obj.getApprovalStatusId())?obj.getApprovalStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getHouseAllocated())?obj.getHouseAllocated():null);
			//stmt.setString(c++, !StringUtils.isEmpty(obj.getResettlementStatusId())?obj.getResettlementStatusId():null);
			//stmt.setString(c++, !StringUtils.isEmpty(obj.getRehabilitationStatusId())?obj.getRehabilitationStatusId():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentAmount())?obj.getPaymentAmount():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getPaymentDate())?obj.getPaymentDate():null);
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
			
			stmt.setString(c++, !StringUtils.isEmpty(obj.getRandRId())?obj.getRandRId():null);			
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
	 * This method update RandR r_and_r_status
	 * 
	 * @param obj is object for the model class RandR
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRandRStatus(RandR obj) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			connection = dataSource.getConnection();
			String qry = "UPDATE r_and_r SET identification_status_id_fk = ?,approval_status_id_fk = ?," 
					+"resettlement_status_id_fk = ?,rehabilitation_status_id_fk = ? "
					+ "WHERE r_and_r_id = ?";
			
			stmt = connection.prepareStatement(qry);
			int c = 1;
			if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Rehabilitation")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Resettlement")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Approval")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Identification")) {
				stmt.setString(c++, "3");
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
			}else if(!StringUtils.isEmpty(obj.getActiveStatus()) && obj.getActiveStatus().equals("Not Started")) {
				stmt.setString(c++, "2");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
				stmt.setString(c++, "1");
			}					
			
			stmt.setString(c++, obj.getRandRId());			
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
