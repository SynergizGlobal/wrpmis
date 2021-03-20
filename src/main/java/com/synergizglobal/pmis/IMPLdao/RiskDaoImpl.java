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
import com.synergizglobal.pmis.model.Risk;
@Repository
public class RiskDaoImpl implements RiskDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<Risk> getWorksList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select work_id,work_name,work_short_name from work ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int[] uploadRiskAssessments(List<Risk> risksList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt1 = null;
		ResultSet rs = null;
		int count = 0;
		int updateCount = 0 ;
		int insertCount = 0 ;
		try{
			con = dataSource.getConnection();
			for (Risk obj : risksList) {
				String risk_id_pk = getRiskIdIfExists(obj.getWork_id_fk(),obj.getSub_work(),obj.getRisk_id(),obj.getSub_area_fk(),con);
				obj.setRisk_id_pk(risk_id_pk);
				String area_item_no = null;
				String sub_area_item_no = null;
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
							
							updateCount++;
							
					 }else {
						 String insertRevisionsQry = "INSERT into risk_revision  (risk_id_pk_fk,date , priority_fk, probability , impact, owner "
									+ ", responsible_person , mitigation_plan) "
									+ "VALUES(:risk_id_pk,:date, :priority_fk,:probability, :impact,:owner,:responsible_person,:mitigation_plan) ";
						 	BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
							KeyHolder keyHolder1 = new GeneratedKeyHolder();
							count = namedParamJdbcTemplate.update(insertRevisionsQry, paramSource,keyHolder1);
							insertCount++;
					 }
						 
				} else {
					NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
					String insertQry = "insert into  risk  (risk_id_pk,work_id_fk,sub_work , risk_id, sub_area_fk) "
							+ "VALUES(:risk_id_pk,:work_id_fk,:sub_work,:risk_id,:sub_area_fk)";
					
					risk_id_pk = getMaxRiskIdFromExisting(con);
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
						insertCount++;
					}
				}
				
			}
		}catch(Exception e){ 
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
	
	
	private String getRiskIdIfExists(String work_id_fk, String sub_work, String risk_id, String sub_area_fk, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_id_pk = null;
		try{
			String riskIdQry = "SELECT risk_id_pk from risk where work_id_fk = ? and sub_work = ? and sub_area_fk = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, work_id_fk);
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
			String riskIdQry = "select IFNULL(max(risk_id_pk)+1,1) as risk_id_pk from risk";
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
			String qry = "SELECT risk_id_pk,risk_id,sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,ra.item_no as area_item_no,p.project_name,risk_id,sub_area,sub_area_fk,rsa.item_no as sub_area_item_no, "
					+ "risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS date,"
					+ "rr.owner,rr.responsible_person "+
					"from risk r  "+
					"left join work w on r.work_id_fk = w.work_id " + 
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
				qry = qry + " and sub_work = ?";
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
			String qry = "SELECT sub_work,risk_area_fk from risk r " + 
					"LEFT JOIN risk_sub_area rs on r.sub_area_fk = rs.sub_area "+
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					//"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
					"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted'";
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
			
			qry = qry + " group by sub_work";
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
			
			qry = qry + " group by date";
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
			String qry = "SELECT risk_id_pk,sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,p.project_name,risk_id,sub_area_fk,"
					+ "risk_revision_id,risk_id_pk_fk,DATE_FORMAT(date,'%d-%m-%Y') AS assessment_date,priority_fk,probability,impact,owner,responsible_person,mitigation_plan "
					+ "from risk_revision rr "
					+ "LEFT OUTER join risk r on rr.risk_id_pk_fk = r.risk_id_pk "
					+ "LEFT OUTER join work w on r.work_id_fk = w.work_id " 
					+ "left join risk_sub_area rsa on r.sub_area_fk = sub_area " 
					+ "left join risk_area ra on rsa.risk_area_fk = ra.area "
					+ "LEFT join project p on w.project_id_fk = p.project_id "
					+ "where risk_revision_id = ?";
			
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
				if(!StringUtils.isEmpty(obj)) {
					if(obj.getUser_designation().equals(sObj.getOwner()) || obj.getUser_designation().equals(sObj.getResponsible_person()) || obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN) ){
						sObj.setReadonlyForm(false);
					}else {
						sObj.setReadonlyForm(true);
					}
					
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
			
			String updateQry = "update risk_revision set mitigation_plan = ? where risk_revision_id = ?";	
			stmt = con.prepareStatement(updateQry);
			stmt.setString(1, obj.getMitigation_plan());
			stmt.setString(2, obj.getRisk_revision_id());
			stmt.executeUpdate();
			
			int	arraySize = 0;		
			
			/*if(!StringUtils.isEmpty(obj.getAssessment_dates()) && obj.getAssessment_dates().length > 0) {
				obj.setAssessment_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAssessment_dates()));
				if(arraySize < obj.getAction_takens().length) {
					arraySize = obj.getAction_takens().length;
				}
			}*/
			
			if(!StringUtils.isEmpty(obj.getAction_takens()) && obj.getAction_takens().length > 0) {
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
			}
			
			
			String deleteQry = "DELETE from risk_action where risk_revision_id_fk = ?";	
			stmt = con.prepareStatement(deleteQry);
			stmt.setString(1, obj.getRisk_revision_id());
			stmt.executeUpdate();
			
			String qry = "INSERT into risk_action (risk_revision_id_fk,action_taken,atr_date)VALUES (?,?,?)";	
			insertStmt = con.prepareStatement(qry);
			for(int i = 0; i < arraySize; i++) {	
				int k = 1;
				insertStmt.setString(k++,obj.getRisk_revision_id());
				insertStmt.setString(k++,(obj.getAction_takens().length > 0)?obj.getAction_takens()[i]:null);
				insertStmt.setString(k++,DateParser.parse((obj.getAtr_dates().length > 0)?obj.getAtr_dates()[i]:null));
				insertStmt.addBatch();
			}
			int[] insertCount = insertStmt.executeBatch();
			if(insertCount.length > 0) {
				  flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
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
			String qry = "SELECT risk_upload_id,work_id_fk,r.attachment,status,r.remarks,uploaded_by_user_id_fk,DATE_FORMAT(uploaded_on,'%d-%b-%Y') as uploaded_on,work_id,user_name as uploaded_by,work_name,work_short_name "
					+ "from risk_upload r " 
					+ "LEFT JOIN work w ON r.work_id_fk = w.work_id " 
					+ "LEFT JOIN user u ON r.uploaded_by_user_id_fk = u.user_id "
					+ "where work_id_fk is not null";
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry + " order by risk_upload_id desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Risk> getWorksListFromRiskUploads(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT work_id_fk,work_id,work_name,work_short_name "
					+ "from risk_upload r "
					+ "LEFT JOIN work w ON r.work_id_fk = w.work_id " 
					+ "where work_id_fk is not null";
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry + " group by work_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Risk>(Risk.class));

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
					+ "(work_id_fk,attachment,status,remarks,uploaded_by_user_id_fk,uploaded_on) "
					+ "VALUES "
					+ "(:work_id_fk,:attachment,:status,:remarks,:uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";	
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
	

}
