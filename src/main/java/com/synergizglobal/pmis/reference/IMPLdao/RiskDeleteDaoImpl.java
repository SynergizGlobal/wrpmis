package com.synergizglobal.pmis.reference.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.reference.Idao.RiskDeleteDao;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class RiskDeleteDaoImpl implements RiskDeleteDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getRisksList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="SELECT risk_revision_id,sub_work,date, count(risk_id_pk_fk) as count" + 
					" FROM risk left join risk_revision on risk_id_pk= risk_id_pk_fk ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				qry = qry + " where sub_work = ? ";
				arrSize++;
			}
			qry = qry + "group by sub_work, date order by date desc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSub_work())) {
				pValues[i++] = obj.getSub_work();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean deleteRisk(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		List<TrainingType> objsList = null;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			if(!StringUtils.isEmpty(obj.getDate())) {
				String deleteRiskActionsQry ="DELETE ra from risk_action ra " + 
						"LEFT join risk_revision rr on rr.risk_revision_id = ra.risk_revision_id_fk " + 
						"LEFT join risk r on r.risk_id_pk = rr.risk_id_pk_fk WHERE rr.date =:date and r.sub_work= :sub_work";
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteRiskActionsQry, paramSource);
				
				String deleteRiskRevisionsQry ="DELETE rr from  risk_revision rr " + 
						"LEFT join risk r on r.risk_id_pk = rr.risk_id_pk_fk WHERE rr.date =:date and r.sub_work= :sub_work";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteRiskRevisionsQry, paramSource);
			}
			int riskCaunt = getRiskCount(obj.getSub_work(),obj);
			if(riskCaunt == 0) {
				 String deleteRiskQry ="DELETE from risk WHERE sub_work= :sub_work; ";
				 BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				 count = namedParamJdbcTemplate.update(deleteRiskQry, paramSource);
			}
				
			if(count > 0) {
				 flag = true;
				 String temp = "sub_work="+obj.getSub_work()+"&assessment_date="+obj.getDate();
				 
				 String deleteRiskQry ="DELETE from messages WHERE message_type = 'Risk' and redirect_url like '%"+temp+"'";
				 BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				 count = namedParamJdbcTemplate.update(deleteRiskQry, paramSource);
				 
				 String deleteRiskUploadQry ="DELETE from risk_upload WHERE sub_work= :sub_work and assessment_date=:date";
				 paramSource = new BeanPropertySqlParameterSource(obj);		 
				 count = namedParamJdbcTemplate.update(deleteRiskUploadQry, paramSource);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	private int getRiskCount(String subWork, TrainingType obj) throws Exception {
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String qry ="SELECT count(risk_id_pk_fk) as count FROM risk left join risk_revision on risk_id_pk= risk_id_pk_fk where sub_work = ? ";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, subWork);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new TrainingType();
				obj.setCounts(resultSet.getInt("count"));
				count = obj.getCounts();
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return count;
	}

	@Override
	public List<TrainingType> getSubWorkFilterList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry = "SELECT sub_work from risk group by sub_work ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
}
