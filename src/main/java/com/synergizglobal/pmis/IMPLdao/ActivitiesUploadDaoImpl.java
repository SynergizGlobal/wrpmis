package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesUploadDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class ActivitiesUploadDaoImpl implements ActivitiesUploadDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;	
	

	@Override
	public List<StripChart> getWorksListFilter(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name,w.work_short_name "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select scv.contract_id_fk FROM strip_chart_general scv WHERE scv.strip_chart_id IS NOT NULL ";
					
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			qry = qry + " GROUP BY scv.contract_id_fk )";
			
			qry = qry + " GROUP BY c.work_id_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getContractsListFilter(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select scv.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where scv.contract_id_fk is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			qry = qry + " GROUP BY scv.contract_id_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStructureListFilter(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select scv.fob_id_fk as strip_chart_structure "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where scv.fob_id_fk is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			qry = qry + " GROUP BY scv.fob_id_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getComponentIdsListFilter(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select scv.component_id as strip_chart_component_id,scv.component_id_name as strip_chart_component_id_name "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where scv.component_id is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			qry = qry + " GROUP BY scv.component_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getComponentsListFilter(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select scv.component as strip_chart_component "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where scv.component is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			qry = qry + " GROUP BY scv.component ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(StripChart obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="SELECT count(*) as total_records "
					+ "from strip_chart_general scv " 
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where strip_chart_id is not null ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contract_id_fk like ? or c.contract_name like ? or fob_id_fk like ? or component_id_name like ? or component like ? or activity_name like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}

	@Override
	public List<StripChart> getActivitiesList(StripChart obj, Integer startIndex, Integer offset, String searchParameter)
			throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_id,scv.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name, scv.fob_id_fk as strip_chart_structure,component_id as strip_chart_component_id,component as strip_chart_component,component_id_name as strip_chart_component_id_name,activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name,planned_start "  
					+",planned_finish,IFNULL(NULLIF(scope, '' ), 0) as scope,IFNULL(NULLIF(completed, '' ), 0) as completed,IFNULL(NULLIF(weight, '' ), 0) as weight "
					+ "from strip_chart_general scv " 
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "	
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where strip_chart_id is not null ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and c.work_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
			
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contract_id_fk like ? or c.contract_name like ? or fob_id_fk like ? or component_id_name like ? or component like ? or activity_name like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY strip_chart_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int[] uploadActivities(List<Activity> activityList) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int arr[] = new int[2];
		try {			
			con = dataSource.getConnection();
			List<Activity> insertList = new ArrayList<Activity>();
			List<Activity> updateList = new ArrayList<Activity>();
			for (Activity activity : activityList) {
				boolean flag = isActivityExist(activity,con);
				if(flag) {
					updateList.add(activity);
				}else {
					insertList.add(activity);
				}
			}
			
			String insertQry = "INSERT INTO activities (contract_id_fk,structure_type_fk,section,line,structure,component,component_id,`order`,activity_name,planned_start,planned_finish,actual_start,actual_finish,unit,scope,completed,weightage,component_details,remarks) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			int[] insertCounts = jdbcTemplate.batchUpdate(insertQry,
		            new BatchPreparedStatementSetter() {		                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {
		                	int p = 1;
		                    ps.setString(p++, insertList.get(i).getContract_id_fk());
		                    ps.setString(p++, insertList.get(i).getStructure_type_fk());
		                    ps.setString(p++, insertList.get(i).getSection());	
		                    ps.setString(p++, insertList.get(i).getLine());
		                    ps.setString(p++, insertList.get(i).getStructure());
		                    
		                    ps.setString(p++, insertList.get(i).getComponent());
		                    ps.setString(p++, insertList.get(i).getComponent_id());
		                    ps.setString(p++, insertList.get(i).getOrder());	
		                    ps.setString(p++, insertList.get(i).getActivity_name());
		                    ps.setString(p++, insertList.get(i).getPlanned_start());
		                    
		                    ps.setString(p++, insertList.get(i).getPlanned_finish());
		                    ps.setString(p++, insertList.get(i).getActual_start());
		                    ps.setString(p++, insertList.get(i).getActual_finish());	
		                    ps.setString(p++, insertList.get(i).getUnit());
		                    ps.setString(p++, insertList.get(i).getScope());
		                    
		                    ps.setString(p++, insertList.get(i).getCompleted());
		                    ps.setString(p++, insertList.get(i).getWeightage());
		                    ps.setString(p++, insertList.get(i).getComponent_details());	
		                    ps.setString(p++, insertList.get(i).getRemarks());
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                    return insertList.size();
		                }
		            });
			
			
			String updateQry = "UPDATE activities SET `order` = ?,activity_name = ?,planned_start = ?,planned_finish = ?,actual_start = ?,actual_finish = ?,unit = ?,scope = ?,completed = ?,weightage = ?,component_details = ?,remarks = ? "
					+ "WHERE contract_id_fk = ? and structure_type_fk = ? and section = ? and line = ? and structure = ? and component = ? and component_id = ? ";
			int[] updateCounts = jdbcTemplate.batchUpdate(updateQry,
		            new BatchPreparedStatementSetter() {		                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {
		                	int p = 1;
		                    ps.setString(p++, updateList.get(i).getOrder());	
		                    ps.setString(p++, updateList.get(i).getActivity_name());
		                    ps.setString(p++, updateList.get(i).getPlanned_start());
		                    
		                    ps.setString(p++, updateList.get(i).getPlanned_finish());
		                    ps.setString(p++, updateList.get(i).getActual_start());
		                    ps.setString(p++, updateList.get(i).getActual_finish());	
		                    ps.setString(p++, updateList.get(i).getUnit());
		                    ps.setString(p++, updateList.get(i).getScope());
		                    
		                    ps.setString(p++, updateList.get(i).getCompleted());
		                    ps.setString(p++, updateList.get(i).getWeightage());
		                    ps.setString(p++, updateList.get(i).getComponent_details());	
		                    ps.setString(p++, updateList.get(i).getRemarks());
		                    
		                    ps.setString(p++, updateList.get(i).getContract_id_fk());
		                    ps.setString(p++, updateList.get(i).getStructure_type_fk());
		                    ps.setString(p++, updateList.get(i).getSection());	
		                    ps.setString(p++, updateList.get(i).getLine());
		                    ps.setString(p++, updateList.get(i).getStructure());		                    
		                    ps.setString(p++, updateList.get(i).getComponent());
		                    ps.setString(p++, updateList.get(i).getComponent_id());
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                    return updateList.size();
		                }
		            });
			
			
			arr[0] = insertCounts.length;
		    arr[1] = updateCounts.length;
		    
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return arr;
	}
	
	private boolean isActivityExist(Activity obj, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			String qry = "select contract_id_fk from activities "
					+ "where activities_id is not null";
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
			} else {
				qry = qry + " and (contract_id_fk IS NULL OR contract_id_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
			} else {
				qry = qry + " and (structure_type_fk IS NULL OR structure_type_fk = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				qry = qry + " and section = ?";
			} else {
				qry = qry + " and (section IS NULL OR section = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				qry = qry + " and line = ?";
			} else {
				qry = qry + " and (line IS NULL OR line = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
			} else {
				qry = qry + " and (structure IS NULL OR structure = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				qry = qry + " and component = ?";
			} else {
				qry = qry + " and (component IS NULL OR component = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				qry = qry + " and component_id = ?";
			} else {
				qry = qry + " and (component_id IS NULL OR component_id = '')";
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				qry = qry + " and activity_name = ?";
			} else {
				qry = qry + " and (activity_name IS NULL OR activity_name = '')";
			}
			
			stmt = con.prepareStatement(qry);
			int k = 1;			
			
			if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
				stmt.setString(k++, obj.getContract_id_fk());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure_type_fk())) {
				stmt.setString(k++, obj.getStructure_type_fk());
			}
			
			if(!StringUtils.isEmpty(obj.getSection())) {
				stmt.setString(k++, obj.getSection());
			}
			
			if(!StringUtils.isEmpty(obj.getLine())) {
				stmt.setString(k++, obj.getLine());
			}
			
			if(!StringUtils.isEmpty(obj.getStructure())) {
				stmt.setString(k++, obj.getStructure());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent())) {
				stmt.setString(k++, obj.getComponent());
			}
			
			if(!StringUtils.isEmpty(obj.getComponent_id())) {
				stmt.setString(k++, obj.getComponent_id());
			}
			
			if(!StringUtils.isEmpty(obj.getActivity_name())) {
				stmt.setString(k++, obj.getActivity_name());
			}
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return flag;
	}

	@Override
	public List<Activity> getWorksInActivitiesUpload(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select work_id,work_name,work_short_name "
					+ "from work "
					+ "where work_id is not null " ;
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Activity>(Activity.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Activity> getContractsInActivitiesUpload(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select contract_id,contract_name,contract_short_name "
					+ "from contract "
					+ "where contract_id is not null " ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}	
			
			qry = qry + " GROUP BY contract_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Activity> getStructureTypesInActivitiesUpload(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select structure_type "
					+ "from structure_type "
					+ "where structure_type is not null " ;
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Activity>(Activity.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
