package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.PMISProgressDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class PMISProgressDaoImpl implements PMISProgressDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<StripChart> getMileStoneFilterList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select id,milestone_fk,cm.milestone_name,activity_description,DATE_FORMAT(planned_start_date,'%d-%m-%Y') as planned_start "  
					+",DATE_FORMAT(planned_finish_date,'%d-%m-%Y') as planned_finish,DATE_FORMAT(actual_start_date,'%d-%m-%Y') as actual_start,DATE_FORMAT(actual_finish_date,'%d-%m-%Y') as actual_finish,"
					+ "IFNULL(NULLIF(total_scope, '' ), 0) as total_scope,IFNULL(NULLIF(completed, '' ), 0) as completed from pmis_strip_chart psc "
					+ "LEFT JOIN contract_milestones cm on milestone_fk = contract_milestones_id " 
					+ " where id is not null and (total_scope - completed) > 0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and psc.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				qry = qry + " and milestone_fk = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				pValues[i++] = obj.getMilestone_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getMileStonesFilterList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT milestone_fk,milestone_name from pmis_strip_chart  psc " + 
					"LEFT JOIN contract_milestones cm on milestone_fk = contract_milestones_id  " + 
					"where milestone_fk is not null and milestone_fk <> '' and (total_scope - completed) > 0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and psc.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				qry = qry + " and milestone_fk = ? ";
				arrSize++;
			}
			
			qry = qry + "GROUP BY milestone_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				pValues[i++] = obj.getMilestone_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean updateProgressForm(StripChart obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String updateQry = "UPDATE  pmis_strip_chart set completed = IFNULL(NULLIF(completed, '' ), 0) + ?,actual_start_date = ?,actual_finish_date = ?   where id = ?";	
			stmt = con.prepareStatement(updateQry);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) {
				obj.setActualScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getActualScopes()));
				if(arraySize < obj.getActualScopes().length) {
					arraySize = obj.getActualScopes().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getCompletedScopes()) && obj.getCompletedScopes().length > 0) {
				obj.setCompletedScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getCompletedScopes()));
				if(arraySize < obj.getCompletedScopes().length) {
					arraySize = obj.getCompletedScopes().length;
				}
		    }
			for ( int i = 0; i < arraySize; i++) {		
				int k = 1;
				String actual = "0";
				if( obj.getActualScopes().length > 0 && !StringUtils.isEmpty(obj.getActualScopes()[i])) {
					actual = obj.getActualScopes()[i];
					stmt.setString(k++, actual );		
					stmt.setString(k++,DateParser.parse((obj.getActual_starts().length > 0)?obj.getActual_starts()[i]:null));
				    stmt.setString(k++,DateParser.parse((obj.getActual_finishs().length > 0)?obj.getActual_finishs()[i]:null));
					stmt.setString(k++,(obj.getIds()[i]));
					stmt.addBatch();
				}
			}
			int[] c = stmt.executeBatch();
			if(c.length > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}	
		return flag;
	}

	
	@Override
	public List<StripChart> getContractsFilterList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_short_name from pmis_strip_chart  psc " + 
					"LEFT JOIN contract c on contract_id_fk = contract_id  " + 
					"where contract_id_fk is not null and contract_id_fk <> '' and (total_scope - completed) > 0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and psc.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				qry = qry + " and milestone_fk = ? ";
				arrSize++;
			}
			
			qry = qry + "GROUP BY contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMilestone_fk())) {
				pValues[i++] = obj.getMilestone_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
