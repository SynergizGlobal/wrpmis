package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

import com.synergizglobal.pmis.Idao.RiskDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;
@Repository
public class RiskDaoImpl implements RiskDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;



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
			String riskIdQry = "SELECT risk_revision_id from risk_revision where  risk_id_pk_fk = ? and date = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, risk_id_pk);
			stmt.setString(k++, date);
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
	public Risk getRiskAssessment(Risk obj) throws Exception {
		Risk sObj =null;
		
		try {
			String qry = "SELECT risk_id_pk,sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,p.project_name,risk_id,sub_area_fk,"
					//+ "risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS date "+
					+ "(select owner from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as owner,"
					+ "(select responsible_person from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as responsible_person, "
					+ "(select priority_fk from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as priority_fk,"
					+ "(select mitigation_plan from risk_revision where risk_id_pk_fk = ? and date = (select max(date) from risk_revision where risk_id_pk_fk = ?)) as mitigation_plan "
					+"from risk r "+
					"LEFT OUTER join work w on r.work_id_fk = w.work_id " + 
					"left join risk_sub_area rsa on r.sub_area_fk = sub_area " + 
					"left join risk_area ra on rsa.risk_area_fk = ra.area " +
					"LEFT join project p on w.project_id_fk = p.project_id " +
					"where risk_id_pk = ?";
			
			Object[] pValues = new Object[] {obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk(),obj.getRisk_id_pk()};
			
			sObj = (Risk)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Risk>(Risk.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getRisk_id_pk())) {
				String qryDetails = "select risk_action_id,risk_revision_id_fk,action_taken,DATE_FORMAT(atr_date,'%d-%m-%Y') AS atr_date " + 
						"from risk_action "
						+"where risk_revision_id_fk in (select risk_revision_id from risk_revision where risk_id_pk_fk = ?) ";
				
				List<Risk> objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getRisk_id_pk()}, new BeanPropertyRowMapper<Risk>(Risk.class));	
				sObj.setRiskActions(objsList); 
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
			
			int	arraySize = 0;		
			
			if(!StringUtils.isEmpty(obj.getAssessment_dates()) && obj.getAssessment_dates().length > 0) {
				obj.setAssessment_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getAssessment_dates()));
				if(arraySize < obj.getAction_takens().length) {
					arraySize = obj.getAction_takens().length;
				}
			}
			
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
			
			
			String deleteQry = "DELETE from risk_action where risk_revision_id_fk in (select risk_revision_id from risk_revision where risk_id_pk_fk = ?)";	
			stmt = con.prepareStatement(deleteQry);
			stmt.setString(1, obj.getRisk_id_pk());
			stmt.executeUpdate();
			
			String qry = "INSERT into risk_action (risk_revision_id_fk,action_taken,atr_date)VALUES (?,?,?)";	
			insertStmt = con.prepareStatement(qry);
			for(int i = 0; i < arraySize; i++) {	
				int k = 1;
				insertStmt.setString(k++,(obj.getAssessment_dates().length > 0)?obj.getAssessment_dates()[i]:null);
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
	public List<Risk> getRiskAssessmentList(Risk obj) throws Exception {
		List<Risk> objsList =null;		
		try {
			String qry = "SELECT risk_id_pk,risk_id,sub_work,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,"
					+ "ra.area,ra.item_no as area_item_no,p.project_name,risk_id,sub_area,sub_area_fk,rsa.item_no as sub_area_item_no, "+
					"risk_revision_id,risk_id_pk_fk,mitigation_plan,priority_fk,probability,impact,DATE_FORMAT(date,'%d-%m-%Y') AS date "+
					"from risk r  "+
					"left join work w on r.work_id_fk = w.work_id " + 
					"left join risk_sub_area rsa on r.sub_area_fk = sub_area " + 
					"left join risk_area ra on rsa.risk_area_fk = ra.area " +
					"left join project p on w.project_id_fk = p.project_id " +
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					"where risk_id_pk is not null and priority_fk <> 'Accepted' ";
					//+"and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
			
			int arrSize = 0;	
			if(!StringUtils.isEmpty(obj) && StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
			}
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
					"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk)";
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
			qry = qry + " group by risk_area_fk";
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
	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			
			
			String qry = "SELECT DATE_FORMAT(date,'%d-%b-%Y') AS assessment_date "+
					"from risk r " + 
					"LEFT JOIN risk_sub_area rs on r.sub_area_fk = rs.sub_area "+
					"left join risk_revision rr on r.risk_id_pk = rr.risk_id_pk_fk " + 
					//"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' and date = (select max(date) from risk_revision where risk_id_pk_fk = risk_id_pk) ";
					"where sub_work is not null and sub_work <> '' and priority_fk <> 'Accepted' ";
			
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
	
	
	
	/***********************************************************************/
	
	@Override
	public List<Risk> getRiskList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select id as risk_id_pk, w.work_name,rv.work_id_fk, rv.risk_id, identification_date, area, area_item_no, sub_area, sub_area_item_no, "
					+ "revision_id, DATE_FORMAT(assessment_date,'%d-%b-%Y') AS assessment_date, max_assessment_date, priority, probability, impact, risk_rating, classification, owner, "
					+ "responsible_person, mitigation_plan, action_taken, atr_date from risk_view rv " + 
					"left join work w on rv.work_id_fk = w.work_id  "
					+ "where  id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
					"where work_id_fk is not null   and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			
			qry = qry + " group by work_id_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
					"where area is not null   and area <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			qry = qry + " group by area";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
					"where priority is not null   and priority <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			
			qry = qry + " group by priority";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
					"where classification is not null  and classification <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			
			qry = qry + " group by classification";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
	public List<Risk> getAssessmentDatesFilterList(Risk obj) throws Exception {
		List<Risk> objsList = null;
		try {
			String qry = "SELECT DATE_FORMAT(assessment_date,'%d-%b-%Y') AS assessment_date from risk_view rv " + 
					"where assessment_date is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			
			qry = qry + " group by assessment_date";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
					"where responsible_person is not null  and responsible_person <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
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
			qry = qry + " group by responsible_person";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
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
			String qry = "SELECT risk_id_pk,w.work_id,work_id_fk,w.work_name,w.work_short_name,project_id_fk,ra.area,p.project_name,risk_id,date_of_identification,sub_area_fk from risk r  "+
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
			String qry = "UPDATE risk SET risk_id=:risk_id,sub_area_fk=:sub_area_fk  "
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

	@Override
	public int[] uploadRisks(List<Risk> risksList, List<Risk> revisionList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt1 = null;
		ResultSet rs = null;
		boolean flag = false;
		int count = 0;
		int updateCount = 0 ;
		int insertCount = 0 ;
		try{
			con = dataSource.getConnection();
			for (Risk obj : risksList) {
				String riskid_pk = getRiskId(obj.getWork_id_fk(),obj.getRisk_id(),obj.getSub_area_fk(),con);
				obj.setRisk_id_pk(riskid_pk);
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
				
				if(!StringUtils.isEmpty(riskid_pk)) {
					NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
					String updateQry = "UPDATE risk set work_id_fk =:work_id_fk, risk_id =:risk_id, sub_area_fk =:sub_area_fk, date_of_identification =:date_of_identification"
							+ " WHERE risk_id_pk =:risk_id_pk";
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
					 count = namedParamJdbcTemplate.update(updateQry, paramSource);			
					if(count > 0) {
						flag = true;
						String revisionId = getRevisionId(obj.getRisk_id_pk(),obj.getDate(),con);
						 obj.setRisk_revision_id(revisionId);
						 if(!StringUtils.isEmpty(revisionId)) {
								String updateRevisionsQry = "UPDATE risk_revision set date =:date, priority_fk =:priority_fk, probability =:probability, impact =:impact, owner =:owner"
										+ ", responsible_person =:responsible_person, mitigation_plan =:mitigation_plan "
										+ " WHERE risk_revision_id =:risk_revision_id";
								paramSource = new BeanPropertySqlParameterSource(obj);		 
								count = namedParamJdbcTemplate.update(updateRevisionsQry, paramSource);
								
								updateCount++;
								
						 }else {
							 String insertRevisionsQry = "INSERT into risk_revision  (risk_id_pk_fk,date , priority_fk, probability , impact, owner "
										+ ", responsible_person , mitigation_plan) "
										+ "VALUES(:risk_id_pk,:date, :priority_fk,:probability, :impact,:owner,:responsible_person,:mitigation_plan) ";
								paramSource = new BeanPropertySqlParameterSource(obj);	
								KeyHolder keyHolder1 = new GeneratedKeyHolder();
								count = namedParamJdbcTemplate.update(insertRevisionsQry, paramSource,keyHolder1);
								insertCount++;
						 }
						
					}
				} else {
					NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
					String insertQry = "insert into  risk  (risk_id_pk,work_id_fk , risk_id, sub_area_fk, date_of_identification) "
							+ "VALUES(:risk_id_pk,:work_id_fk,:risk_id,:sub_area_fk,:date_of_identification)";
					
					String risk_id_pk = getMaxRiskId(con);
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
			
			String qryAction = "INSERT INTO risk_action (risk_revision_id_fk,action_taken,atr_date) VALUES(?,?,?)";
			insertStmt1 = con.prepareStatement(qryAction);
					
			if(!StringUtils.isEmpty(revisionList) && revisionList.size() > 0) {
				for (Risk obj : revisionList) {
					 int k = 1;
					    String riskId = obj.getRisk_id();
						String date = obj.getDate();
						String actionTaken = obj.getAction_taken();
						String atr_date = obj.getAtr_date();
						
						String revisionId = getRevisionIdFk(riskId,date);
						obj.setRisk_revision_id(revisionId);
						 
					    insertStmt1.setString(k++,(obj.getRisk_revision_id()));
						insertStmt1.setString(k++,!StringUtils.isEmpty(actionTaken)?actionTaken:null);
						insertStmt1.setString(k++,DateParser.parse(!StringUtils.isEmpty(atr_date)?atr_date:null));
					    insertStmt1.addBatch();
				}
				int[] insertCount1 = insertStmt1.executeBatch();
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

	private String getMaxRiskId(Connection con) throws Exception {
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

	private String getRevisionIdFk(String risk_id, String date) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_revision_id = null;
		Connection con = null;
		try{
			con = dataSource.getConnection();
			String riskIdQry = "SELECT risk_revision_id from risk_revision rr "
					+ "LEFT JOIN risk r on rr.risk_id_pk_fk = r.risk_id_pk"
					+ " where  r.risk_id = ? and date = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, risk_id);
			stmt.setString(k++, date);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				risk_revision_id = rs.getString("risk_revision_id");
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return risk_revision_id;
	}

	private String getRevisionId(String risk_id_pk, String date, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_revision_id = null;
		try{
			String riskIdQry = "SELECT risk_revision_id from risk_revision where  risk_id_pk_fk = ? and date = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, risk_id_pk);
			stmt.setString(k++, date);
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
	

	private String getRiskId(String work_id_fk, String risk_id, String sub_area_fk, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String risk_id_pk = null;
		try{
			String riskIdQry = "SELECT risk_id_pk from risk where work_id_fk = ? and risk_id = ? and sub_area_fk = ? ";
			stmt = con.prepareStatement(riskIdQry);
			int k =1;
			stmt.setString(k++, work_id_fk);
			stmt.setString(k++, risk_id);
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
	@Override
	public List<RiskReport> getExportRiskList(Risk obj) throws Exception {
		List<RiskReport> risksList = null;
		try {
			String qry = "select id,work_id_fk,risk_id,identification_date,area,area_item_no,sub_area,sub_area_item_no,revision_id,DATE_FORMAT(assessment_date,'%d/%m/%Y') AS assessment_date,max_assessment_date,"
							+ "priority,probability,impact,risk_rating,classification,owner,responsible_person,mitigation_plan,attachment,action_taken,atr_date "
							+ "from risk_view rv " 
							+ "where rv.work_id_fk = ?";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				qry = qry + " and assessment_date = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getWork_id_fk();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAssessment_date())) {
				pValues[i++] = obj.getAssessment_date();
			}
			risksList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return risksList;
	}

	@Override
	public List<RiskReport> getATRRevisionDataList(Risk obj) throws Exception {
		List<RiskReport> risksList = null;
		try {
			String qry = "select risk_id,DATE_FORMAT(assessment_date,'%d/%m/%Y') AS assessment_date,action_taken,DATE_FORMAT(atr_date,'%d/%m/%Y') AS atr_date "
							+ "from risk_view rv " 
							+ "where rv.work_id_fk = ? and assessment_date = max_assessment_date and atr_date is not null";
			
			Object[] pValues = new Object[] {obj.getWork_id_fk()};
			
			risksList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<RiskReport>(RiskReport.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return risksList;
	}

}
