package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.RiskDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;
@Repository
public class RiskDaoImpl implements RiskDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	MessagesDao messagesDao;

	@Override
	public List<Risk> getWorksList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select work_id,work_name,work_short_name "
					+ "from risk_work_hod rwh "
					+ "left join work on work_id_fk = work_id "
					+ "group by work_id_fk";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Risk> getSubWorksList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "select sub_work from risk_work_hod group by sub_work order by priority asc";
			
			Object[] pValues = new Object[] {};
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Risk> getSubWorkHodFilterListInRiskAssessmnt(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT sub_work, hod_user_id_fk "
					+ "from risk_work_hod "
					+ "where hod_user_id_fk = ? and (risk_work_completed = 'No' or risk_work_completed is null or risk_work_completed = '')";
			qry = qry + " group by sub_work order by priority asc";
			Object[] pValues = new Object[] {obj.getUser_id()};			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int[] uploadRiskAssessments(List<Risk> risksList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt1 = null;
		PreparedStatement insertStmt = null;
		Risk sObj = null;
		String subWork = null;
		String assessmentDate = null;
		ResultSet rs = null;
		int idVal = 0;
		int count = 0;
		int updateCount = 0 ;
		int insertCount = 0 ;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			con = dataSource.getConnection();
			for (Risk obj : risksList) {
				String risk_id_pk = getRiskIdIfExists(obj.getSub_work(),obj.getSub_area_fk(),con);
				obj.setRisk_id_pk(risk_id_pk);
				String area_item_no = null;
				String sub_area_item_no = null;
				subWork = obj.getSub_work();
				assessmentDate = obj.getDate();
				if(!StringUtils.isEmpty(obj.getItem_no())) {
					String[] temp = obj.getItem_no().split("\\.");
					area_item_no = temp[0];
					sub_area_item_no = temp[1];
				}
				String risk_area = getRiskArea(obj.getRisk_area_fk(),area_item_no,con);
				String risk_sub_area = getRiskSubArea(obj.getRisk_area_fk(),obj.getSub_area_fk(),sub_area_item_no,con);
				obj.setRisk_area_fk(risk_area);
				obj.setSub_area_fk(risk_sub_area);
				
				if(!StringUtils.isEmpty(risk_id_pk)) {
					 NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
					 String revisionId = getRevisionIdIfExists(obj.getRisk_id_pk(),obj.getDate(),con);
					 obj.setRisk_revision_id(revisionId);
					 if(!StringUtils.isEmpty(revisionId)) {
							String updateRevisionsQry = "UPDATE risk_revision set date =:date, priority_fk =:priority_fk, probability =:probability, impact =:impact, owner =:owner"
									+ ", responsible_person =:responsible_person, mitigation_plan =:mitigation_plan "
									+ " WHERE risk_revision_id =:risk_revision_id";
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
							count = namedParamJdbcTemplate.update(updateRevisionsQry, paramSource);
							
							updateCount = count;
							
							
							String deleteRiskActionQry = "DELETE from risk_action WHERE risk_revision_id_fk =:risk_revision_id";
							paramSource = new BeanPropertySqlParameterSource(obj);		 
							count = namedParamJdbcTemplate.update(deleteRiskActionQry, paramSource);							
					 }else {
						 String insertRevisionsQry = "INSERT into risk_revision  (risk_id_pk_fk,date , priority_fk, probability , impact, owner "
									+ ", responsible_person , mitigation_plan) "
									+ "VALUES(:risk_id_pk,:date, :priority_fk,:probability, :impact,:owner,:responsible_person,:mitigation_plan) ";
						 	BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
							KeyHolder keyHolder1 = new GeneratedKeyHolder();
							count = namedParamJdbcTemplate.update(insertRevisionsQry, paramSource,keyHolder1);
							updateCount = count;
					 }
						 
				} else {
					NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
					String insertQry = "insert into  risk  (risk_id_pk,sub_work ,sub_area_fk) "
							+ "VALUES(:risk_id_pk,:sub_work,:sub_area_fk)";
					
					risk_id_pk = getMaxRiskIdFromExisting(con);
					idVal++;
					String id = risk_id_pk;
					int riskId = Integer.parseInt(id) + idVal;
					risk_id_pk = String.valueOf(riskId);
					obj.setRisk_id_pk(risk_id_pk);
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					//KeyHolder keyHolder = new GeneratedKeyHolder();
					//count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);
					count = namedParamJdbcTemplate.update(insertQry, paramSource);
					if(count > 0) {
						//String risk_id_fk = String.valueOf(keyHolder.getKey().intValue());
						//obj.setRisk_id_pk(risk_id_fk);
						
						String insertRevisionsQry = "INSERT into risk_revision  (risk_id_pk_fk,date , priority_fk, probability , impact, owner "
								+ ", responsible_person , mitigation_plan) "
								+ "VALUES(:risk_id_pk,:date, :priority_fk,:probability, :impact,:owner,:responsible_person,:mitigation_plan) ";
						paramSource = new BeanPropertySqlParameterSource(obj);	
						KeyHolder keyHolder2 = new GeneratedKeyHolder();
						count = namedParamJdbcTemplate.update(insertRevisionsQry, paramSource,keyHolder2);
						updateCount = count;
					}
				}
				String qry = "SELECT u.user_id as owner_user_id,u1.user_id as responsible_user_id,owner,responsible_person,u.reporting_to_id_srfk as reporting_to_user_id "
						+ "from risk_revision rr "
						+ "left join user u on rr.owner = u.designation "
						+ "left join user u1 on rr.responsible_person = u1.designation where owner = ? and responsible_person = ? group by owner";
				
				Object[] pValues = new Object[] {obj.getOwner(),obj.getResponsible_person()};
				sObj = (Risk)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));
			}
			if(updateCount > 0) {
				
				 String ownerId = sObj.getOwner_user_id(); 
				 String responsibleId = sObj.getResponsible_user_id();
				 String reporting_to_user_id = sObj.getReporting_to_user_id();
				 String userIds[]  = null;
				 if("PMIS_SU_002".equals(reporting_to_user_id)) {
					 userIds  = new String[5];
					 userIds[0]  = ownerId;
					 userIds[1]  = responsibleId;
					 userIds[2]  = reporting_to_user_id;
					 userIds[3]  = "PMIS_SU_006";
					 userIds[4]  = "PMIS_SU_052";
				 }else {
					 userIds  = new String[3];
					 userIds[0]  = ownerId;
					 userIds[1]  = responsibleId;
					 userIds[2]  = reporting_to_user_id;
				 }
				  				 
				 String messageType = "Risk";
				 String redirect_url = "/InfoViz/risks/risk-detail?&sub_work="+subWork+"&assessment_date="+assessmentDate;
				 String message = "Risk Analysis Report of "+subWork+" has been uploaded.";
				 
				 Messages msgObj = new Messages();
				 msgObj.setUser_ids(userIds);
				 msgObj.setMessage_type(messageType);
				 msgObj.setRedirect_url(redirect_url);
				 msgObj.setMessage(message);
				  
				 NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				 messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
				 
				 
				/*String message_qry = "INSERT into messages (message,user_id_fk,redirect_url,message_type,created_date)VALUES (?,?,?,?,CURRENT_TIMESTAMP())";	
				insertStmt = con.prepareStatement(message_qry);
				for(int i = 0; i < userIds.length; i++) {	
				int j = 1;
				if((!StringUtils.isEmpty(userIds[i]))) {
					String redirect_url = "/InfoViz/risks/risk-detail?&sub_work="+subWork+"&assessment_date="+assessmentDate;
					insertStmt.setString(j++,"Risk Analysis Report of "+subWork+" has been uploaded.");
					insertStmt.setString(j++,(userIds[i]));
					insertStmt.setString(j++,redirect_url);
					insertStmt.setString(j++,messageType);
					insertStmt.addBatch();
				}
				}
				int [] insertCount1 = insertStmt.executeBatch();*/
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt1, rs);
		}
		
		int arr[] = new int[2];
		arr[0] = updateCount;
	    arr[1] = insertCount;
		return arr;
	}
	
	
@Override
public boolean checkRiskAssessment(String subwork,String Date) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag=false;		
		Connection con = null;	
		try{
			con = dataSource.getConnection();
			String riskIdQry = "select * from risk r left join risk_revision p on p.risk_id_pk_fk = r.risk_id_pk where sub_work = ? and date = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k = 1;
			stmt.setString(k++, subwork);
			stmt.setString(k++,  Date);
			rs = stmt.executeQuery();  
			if(rs.next()) 
			{
				flag = true;
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return flag;
}
	
	private String getRiskIdIfExists(String sub_work, String sub_area_fk, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_id_pk = null;
		try{
			String riskIdQry = "SELECT risk_id_pk from risk where sub_work = ? and sub_area_fk = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, sub_work);
			stmt.setString(k++, sub_area_fk);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				risk_id_pk = rs.getString("risk_id_pk");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return risk_id_pk;
	}
	
	private String getRevisionIdIfExists(String risk_id_pk, String date, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_revision_id = null;
		try{
			String riskIdQry = "SELECT risk_revision_id from risk_revision where  risk_id_pk_fk = ? ";
			if(!StringUtils.isEmpty(date)) {
				riskIdQry = riskIdQry + " and date = ? ";
			}else {
				riskIdQry = riskIdQry + " and date IS NULL ";
			}
					
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, risk_id_pk);			
			if(!StringUtils.isEmpty(date)) {
				stmt.setString(k++, date);
			}
			rs = stmt.executeQuery();  
			if(rs.next()) {
				risk_revision_id = rs.getString("risk_revision_id");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return risk_revision_id;
	}
	
	private String getMaxRiskIdFromExisting(Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_id_pk = null;
		try{
			String riskIdQry = "select IFNULL(max(risk_id_pk),1) as risk_id_pk from risk";
			stmt = con.prepareStatement(riskIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				risk_id_pk = rs.getString("risk_id_pk");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return risk_id_pk;
	}
	
	@Override
	public List<Risk> getRiskAssessmentList(Risk obj) throws Exception {
		List<Risk> objsList =null;		
		try {
			String qry = "SELECT risk_id_pk,r.sub_work,w.work_id,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,ra.item_no as area_item_no,p.project_name,sub_area,sub_area_fk,rsa.item_no as sub_area_item_no, "
					+ "risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS date,"
					+ "rr.owner,rr.responsible_person "+
					"from risk r  "+
					"left join risk_work_hod rwh on r.sub_work = rwh.sub_work " + 
					"left join work w on rwh.work_id_fk = w.work_id " + 
					"left join risk_sub_area rsa on r.sub_area_fk = sub_area " + 
					"left join risk_area ra on rsa.risk_area_fk = ra.area " +
					"left join project p on w.project_id_fk = p.project_id " +
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					"where risk_id_pk is not null and priority_fk <> 'Accepted' ";
			
			int arrSize = 0;	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and date = ?";
				arrSize++;
			}else {
				qry = qry + " and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " and r.sub_work = ?";
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (rr.owner = ? or rr.responsible_person = ?)";
				arrSize++;
				arrSize++;
			} 
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_designation();
				pValues[i++] = obj.getUser_designation();
			} 
			
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			for (Risk risk : objsList) {
				String actionTakenQry = "SELECT action_taken from risk_action where risk_revision_id_fk = ? and atr_date = (select max(atr_date) from risk_action where risk_revision_id_fk = ?) ";
				
				List<Risk> atList = jdbcTemplate.query(actionTakenQry, new Object[]{risk.getRisk_revision_id(),risk.getRisk_revision_id()}, new BeanPropertyRowMapper<Risk>(Risk.class));
				for (Risk action_taken : atList) {
					risk.setAction_taken(action_taken.getAction_taken());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getSubWorksFilterListInRiskAssessmnt(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT r.sub_work,risk_area_fk from risk r " + 
					"LEFT JOIN risk_sub_area rs on r.sub_area_fk = rs.sub_area "+
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					"left join risk_work_hod rwh on r.sub_work = rwh.sub_work " + 
					//"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
					"where r.sub_work is not null and r.sub_work <> '' and priority_fk <> 'Accepted'";
					int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " and r.sub_work = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and risk_area_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and date = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (rr.owner = ? or rr.responsible_person = ?)";
				arrSize++;
				arrSize++;
			} 
			
			qry = qry + " group by r.sub_work ORDER BY rwh.priority asc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_designation();
				pValues[i++] = obj.getUser_designation();
			} 
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getAreasFilterListInRiskAssessment(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT sub_work,risk_area_fk "
					+ "from risk_sub_area rs " + 
					"LEFT JOIN risk r on rs.sub_area = r.sub_area_fk "+
					"where risk_area_fk is not null and risk_area_fk <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " and sub_work = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (rr.owner = ? or rr.responsible_person = ?)";
				arrSize++;
				arrSize++;
			} 
			
			qry = qry + " group by risk_area_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_designation();
				pValues[i++] = obj.getUser_designation();
			} 
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {			
			String qry = "SELECT DATE_FORMAT(date,'%d-%b-%Y') AS assessment_date "+
					"from risk r " + 
					"LEFT JOIN risk_sub_area rs on r.sub_area_fk = rs.sub_area "+
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					//"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk) ";
					"where date is not null and priority_fk <> 'Accepted' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " and sub_work = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				qry = qry + " and risk_area_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and date = ?";
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (rr.owner = ? or rr.responsible_person = ?)";
				arrSize++;
				arrSize++;
			} 
			
			qry = qry + " group by date order by date desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getArea())) {
				pValues[i++] = obj.getArea();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_designation();
				pValues[i++] = obj.getUser_designation();
			} 
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Risk> getRiskAssessmentDates(Risk obj) throws Exception {
		List<Risk> objsList =null;		
		try {
			String qry = "SELECT risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS assessment_date "
					+"from risk_revision "
					+"where risk_id_pk_fk = ? ";
			
			Object[] pValues = new Object[] {obj.getRisk_id_pk()};
			
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Risk getRiskAssessment(Risk obj) throws Exception {
		Risk sObj =null;
		
		try {
			/*String qry = "SELECT risk_id_pk,sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,p.project_name,risk_id,sub_area_fk,"
					//+ "risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS date "+
					+ "(select owner from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as owner,"
					+ "(select responsible_person from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as responsible_person, "
					+ "(select priority_fk from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as priority_fk,"
					+ "(select mitigation_plan from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as mitigation_plan, "
					+ "(select DATE_FORMAT(max(date),'%d-%m-%Y') from risk_revision where risk_id_pk_fk = ? ) as assessment_date "
					+"from risk r "+
					"LEFT OUTER join work w on r.work_id_fk = w.work_id " + 
					"left join risk_sub_area rsa on r.sub_area_fk = sub_area " + 
					"left join risk_area ra on rsa.risk_area_fk = ra.area " +
					"LEFT join project p on w.project_id_fk = p.project_id " +
					"where risk_id_pk = ?";
			
			Object[] pValues = new Object[] {obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk()};
			*/
			String qry = "SELECT risk_id_pk,r.sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,p.project_name,sub_area_fk,"
					+ "risk_revision_id,risk_id_pk_fk,DATE_FORMAT(date,'%d-%m-%Y') AS assessment_date,"
					+ "u.user_id as owner_user_id,u1.user_id as responsible_user_id,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "priority_fk,probability,impact,owner,responsible_person,mitigation_plan,rwh.hod_user_id_fk "
					+ "from risk_revision rr "
					+ "LEFT OUTER join risk r on rr.risk_id_pk_fk = r.risk_id_pk "
					+ "left join risk_work_hod rwh on r.sub_work = rwh.sub_work "
					+ "left join user u on rr.owner = u.designation "
					+ "left join user u1 on rr.responsible_person = u1.designation "
					+ "LEFT OUTER join work w on rwh.work_id_fk = w.work_id " 
					+ "left join risk_sub_area rsa on r.sub_area_fk = sub_area " 
					+ "left join risk_area ra on rsa.risk_area_fk = ra.area "
					+ "LEFT join project p on w.project_id_fk = p.project_id "
					+ "where risk_revision_id = ? ORDER BY rwh.priority asc ";
			
			Object[] pValues = new Object[] {obj.getRisk_revision_id()};
			sObj = (Risk)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id_pk())) {
				String qryDetails = "select risk_action_id,risk_revision_id_fk,action_taken,DATE_FORMAT(atr_date,'%d-%m-%Y') AS atr_date " + 
						"from risk_action "
						+"where risk_revision_id_fk = ? "; 
				
				List<Risk> objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getRisk_revision_id()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
				sObj.setRiskActions(objsList); 
			}
			
			if(!StringUtils.isEmpty(sObj)) {
				if((!StringUtils.isEmpty(obj.getUser_designation()) && (obj.getUser_designation().equals(sObj.getOwner()) || obj.getUser_designation().equals(sObj.getResponsible_person()))) || CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())  ){
					sObj.setReadonlyForm(false);
				}else {
					sObj.setReadonlyForm(true);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public boolean updateRiskAssessment(Risk obj) throws Exception {
		boolean flag = false;
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;		 
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String updateQry = "update risk_revision set mitigation_plan = ? where risk_revision_id = ?";	
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, obj.getMitigation_plan());
			stmt.setString(2, obj.getRisk_revision_id());
			int c = stmt.executeUpdate();
			if(c > 0) {
				flag = true;
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			int	arraySize = 0;		
			
			/*if(!StringUtils.isEmpty(obj.getAssessment_dates()) && obj.getAssessment_dates().length > 0) {
				obj.setAssessment_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAssessment_dates()));
				if(arraySize < obj.getAction_takens().length) {
					arraySize = obj.getAction_takens().length;
				}
			}*/
			
			/*if(!StringUtils.isEmpty(obj.getAction_takens()) && obj.getAction_takens().length > 0) {
				obj.setAction_takens(CommonMethods.replaceEmptyByNullInSringArray(obj.getAction_takens()));
				if(arraySize < obj.getAction_takens().length) {
					arraySize = obj.getAction_takens().length;
				}
			}
			
			if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().length > 0) {
				obj.setAtr_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAtr_dates()));
				if(arraySize < obj.getAtr_dates().length) {
					arraySize = obj.getAtr_dates().length;
				}
			}*/
			
			if(!StringUtils.isEmpty(obj.getAtr_dates()) && obj.getAtr_dates().size() > 0) {
				if(arraySize < obj.getAtr_dates().size()) {
					arraySize = obj.getAtr_dates().size();
				}
			}
			
			if(!StringUtils.isEmpty(obj.getAction_takens()) && obj.getAction_takens().size() > 0) {
				if(arraySize < obj.getAction_takens().size()) {
					arraySize = obj.getAction_takens().size();
				}
			}
			
			
			String deleteQry = "DELETE from risk_action where risk_revision_id_fk = ?";	
			stmt = con.prepareStatement(deleteQry);
			stmt.setString(1, obj.getRisk_revision_id());
			stmt.executeUpdate();
			
			String qry = "INSERT into risk_action (risk_revision_id_fk,action_taken,atr_date)VALUES (?,?,?)";	
			insertStmt = con.prepareStatement(qry);
			for(int i = 1; i < arraySize; i++) {	
				int k = 1;
				if(!StringUtils.isEmpty(obj.getAtr_dates().get(i)) && !StringUtils.isEmpty(obj.getAction_takens().get(i)) ) {
					insertStmt.setString(k++,obj.getRisk_revision_id());
					insertStmt.setString(k++,(obj.getAction_takens().size() > 0)?obj.getAction_takens().get(i):null);
					insertStmt.setString(k++,DateParser.parse((obj.getAtr_dates().size() > 0)?obj.getAtr_dates().get(i):null));
					insertStmt.addBatch();
				} 
			}
			int[] insertCount = insertStmt.executeBatch();
			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			if(insertCount.length > 0) {
				for(int j = 1; j < arraySize; j++) {	
					if((!StringUtils.isEmpty(obj.getAtr_dates_old()) && obj.getAtr_dates_old().size() > 0 && !(obj.getAtr_dates().get(j).equals(obj.getAtr_dates_old().get(j)))) 
							|| (!StringUtils.isEmpty(obj.getAction_takens_old()) && obj.getAction_takens_old().size() > 0 && !(obj.getAction_takens().get(j).equals(obj.getAction_takens_old().get(j)))) 
							|| (StringUtils.isEmpty(obj.getAtr_dates_old()) || (!StringUtils.isEmpty(obj.getAtr_dates_old()) && obj.getAtr_dates_old().size() <= 0) 
									&& (StringUtils.isEmpty(obj.getAction_takens_old()) || (!StringUtils.isEmpty(obj.getAction_takens_old()) && obj.getAction_takens_old().size() <= 0))) 
							) {

						/*String messageType = "Risk";
						String userId[]  = { obj.getOwner_user_id(),obj.getResponsible_user_id(),obj.getReporting_to_user_id(),"PMIS_SU_006","PMIS_SU_052" };
						flag = true;
						String message_qry = "INSERT into messages (message,user_id_fk,redirect_url,message_type,created_date)VALUES (?,?,?,?,CURRENT_TIMESTAMP())";	
						insertStmt = con.prepareStatement(message_qry);
						for(int i = 0; i < userId.length; i++) {	
						int p = 1;
						if((!StringUtils.isEmpty(userId[i]))) {
							String redirect_url = "/InfoViz/risks/risk-detail?&sub_work="+obj.getSub_work()+"&assessment_date="+DateParser.parse(obj.getAssessment_date());
							insertStmt.setString(p++,"ATR of prioritized risk(s) for "+obj.getSub_work()+" has been updated.");
							insertStmt.setString(p++,(userId[i]));
							insertStmt.setString(p++,redirect_url);
							insertStmt.setString(p++,messageType);
							insertStmt.addBatch();
						}
						}
						insertCount = insertStmt.executeBatch();*/
						
						String ownerId = obj.getOwner_user_id(); 
						String responsibleId = obj.getResponsible_user_id();
						String reporting_to_user_id = obj.getReporting_to_user_id();
						 
						String userIds[]  = null;
						 if("PMIS_SU_002".equals(reporting_to_user_id)) {
							 userIds  = new String[5];
							 userIds[0]  = ownerId;
							 userIds[1]  = responsibleId;
							 userIds[2]  = reporting_to_user_id;
							 userIds[3]  = "PMIS_SU_006";
							 userIds[4]  = "PMIS_SU_052";
						 }else {
							 userIds  = new String[3];
							 userIds[0]  = ownerId;
							 userIds[1]  = responsibleId;
							 userIds[2]  = reporting_to_user_id;
						 }
						  				 
						 String messageType = "Risk";
						 String redirect_url = "/InfoViz/risks/risk-detail?&sub_work="+obj.getSub_work()+"&assessment_date="+DateParser.parse(obj.getAssessment_date());
						 String message = "ATR of prioritized risk(s) for "+obj.getSub_work()+" has been updated.";
						 
						 Messages msgObj = new Messages();
						 msgObj.setUser_ids(userIds);
						 msgObj.setMessage_type(messageType);
						 msgObj.setRedirect_url(redirect_url);
						 msgObj.setMessage(message);
						  
						 NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						 messagesDao.addMessages(msgObj,namedParamJdbcTemplate);
						 
						 break;
					}
				}
				
			}
			
			con.commit();
		}catch(Exception e){ 
			con.rollback();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return flag;
	}

	
	


	@Override
	public List<Risk> getRiskAssessmentUploadsList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT risk_upload_id,sub_work,r.attachment,status,r.remarks,uploaded_by_user_id_fk,DATE_FORMAT(uploaded_on,'%d-%b-%Y') as uploaded_on,user_name as uploaded_by,DATE_FORMAT(assessment_date,'%d-%b-%Y') as assessment_date "
					+ "from risk_upload r " 
					+ "LEFT JOIN user u ON r.uploaded_by_user_id_fk = u.user_id "
					+ "where sub_work is not null";
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " and sub_work = ?";
				arrSize++;
			}	
			qry = qry + " order by risk_upload_id desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getSubWorksListFromRiskUploads(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT ru.sub_work "
					+ "from risk_upload ru "
					+ "LEFT JOIN risk_work_hod rwh ON ru.sub_work = rwh.sub_work "
					+ "GROUP BY ru.sub_work ORDER BY rwh.priority asc";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
	@Override
	public boolean saveRiskAssessmentUploadFile(Risk obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String risk_upload_id = null;		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO risk_upload"
					+ "(sub_work,attachment,status,remarks,uploaded_by_user_id_fk,uploaded_on,assessment_date) "
					+ "VALUES "
					+ "(:sub_work,:attachment,:status,:remarks,:uploaded_by_user_id_fk,CURRENT_TIMESTAMP,:assessment_date)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				risk_upload_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setRisk_upload_id(risk_upload_id);
				flag = true;
				
				MultipartFile file = obj.getRiskAssessmentFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.RISK_ASSESSMENT_UPLOADED_FILE_SAVING_PATH ;
					String fileName = risk_upload_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					obj.setAttachment(fileName);
					String updateQry = "UPDATE risk_upload set attachment= :attachment where risk_upload_id= :risk_upload_id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
					template.update(updateQry, paramSource1);
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	private String getRiskArea(String risk_area_fk, String area_item_no, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String area = null;
		try{
			String riskIdQry = "select area from risk_area where area = ?";
			stmt = con.prepareStatement(riskIdQry);
			stmt.setString(1, risk_area_fk);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				area = rs.getString("area");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			if(StringUtils.isEmpty(area)) {				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
				stmt = con.prepareStatement("insert into risk_area(area,item_no)values(?,?)");
				stmt.setString(1, risk_area_fk);
				stmt.setString(2, area_item_no);
				int c = stmt.executeUpdate(); 
			}
			
			area = risk_area_fk;
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return area;
	}
	private String getRiskSubArea(String risk_area_fk, String sub_area_fk, String area_item_no, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String area = null;
		try{
			String riskIdQry = "select sub_area,risk_area_fk from risk_sub_area where sub_area = ? and risk_area_fk = ?";
			stmt = con.prepareStatement(riskIdQry);
			stmt.setString(1, sub_area_fk);
			stmt.setString(2, risk_area_fk);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				area = rs.getString("sub_area");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			stmt = con.prepareStatement("select sub_area,risk_area_fk from risk_sub_area where sub_area = ?");
			stmt.setString(1, sub_area_fk);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				area = rs.getString("sub_area");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
			
			if(StringUtils.isEmpty(area)) {
				
				DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
				stmt = con.prepareStatement("insert into risk_sub_area(sub_area,risk_area_fk,item_no)values(?,?,?)");
				stmt.setString(1, sub_area_fk);
				stmt.setString(2, risk_area_fk);
				stmt.setString(3, area_item_no);
				int c = stmt.executeUpdate(); 
			}
			
			area = sub_area_fk;
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return area;
	}

	@Override
	public Risk getLastUpdatedRiskAssessmentFile(Risk obj) throws Exception {
		List<Risk> objsList = null;
		Risk rObj = new Risk();
		try {
			String qry = "SELECT attachment from risk_upload where sub_work = ? and status = ? order by assessment_date desc,uploaded_on desc limit 1";
		    objsList = jdbcTemplate.query( qry,new Object[]{obj.getSub_work(),"Success"}, new BeanPropertyRowMapper<Risk>(Risk.class));
		    for (Risk risk : objsList) {
		    	rObj = risk;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return rObj;
	}

}
