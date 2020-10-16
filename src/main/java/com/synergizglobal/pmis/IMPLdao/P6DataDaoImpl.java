package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.P6DataDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.P6Data;

@Repository
public class P6DataDaoImpl implements P6DataDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<P6Data> getFobList(P6Data obj) throws Exception {
		List<P6Data> objsList = null;
		try {
			String qry ="SELECT fob_id as fob_id_fk,contract_id_fk FROM fob f "
					+"left join contract c on  f.contract_id_fk = c.contract_id  where fob_id is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<P6Data>(P6Data.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int p6WbsUpload(List<P6Data> p6dataList) throws Exception {
		int  count = 0;
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
	
		try {
			con = dataSource.getConnection();
			String insertQry ="INSERT INTO p6_wbs (contract_id_fk,fob_id_fk,p6_wbs_code,p6_wbs_name,p6_wbs_parent_code,p6_wbs_category_fk)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			for (P6Data obj : p6dataList) {
				int p = 1;
			    insertStmt.setString(p++,(obj.getContract_id_fk()));
			    insertStmt.setString(p++,(obj.getFob_id_fk()));
			    insertStmt.setString(p++,(obj.getP6_wbs_code()));
			    insertStmt.setString(p++,(obj.getP6_wbs_name()));
			    insertStmt.setString(p++,(obj.getP6_wbs_parent_code()));
			    insertStmt.setString(p++,(obj.getP6_wbs_category_fk()));
			    insertStmt.addBatch();
				
			}
			 insertStmt.executeBatch();	
			 count = p6dataList.size();
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	
	}

	@Override
	public int p6ActivitiesUpload(List<P6Data> p6dataList) throws Exception {
		int  count = 0;
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
	
		try {
			con = dataSource.getConnection();
			String insertQry ="INSERT INTO p6_activity ( p6_wbs_code_fk,p6_task_code,p6_activity_name,status_fk,"
					+ " baseline_start,baseline_finish,start,finish,float)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?)";
			
			insertStmt = con.prepareStatement(insertQry);
			for (P6Data obj : p6dataList) {
				int p = 1;
			    insertStmt.setString(p++,(obj.getP6_wbs_code_fk()));
			    insertStmt.setString(p++,(obj.getP6_task_code()));
			    insertStmt.setString(p++,(obj.getP6_activity_name()));
			    insertStmt.setString(p++,(obj.getStatus_fk()));
			    insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getBaseline_start())?obj.getBaseline_start():null));
			    insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getBaseline_finish())?obj.getBaseline_finish():null));
			    insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getStart())?obj.getStart():null));
			    insertStmt.setString(p++,DateParser.parse(!StringUtils.isEmpty(obj.getFinish())?obj.getFinish():null));
			    insertStmt.setString(p++,(obj.getP6_float()));
			    insertStmt.addBatch();
			
			}
			 insertStmt.executeBatch();	
			 count = p6dataList.size();
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	
	}
	
	
}
