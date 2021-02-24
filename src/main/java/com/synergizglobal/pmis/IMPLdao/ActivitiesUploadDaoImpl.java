package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public int uploadActivities(List<Activity> activityList) throws Exception {
		int count = 0;
		try {			
			String qry = "INSERT INTO activities (contract_id_fk,struture_type_fk,section,line,structure,component,component_id,`order`,activity_name,planned_start,planned_finish,actual_start,actual_finish,unit,scope,completed,weightage,component_details,remarks) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			int[] counts = jdbcTemplate.batchUpdate(qry,
		            new BatchPreparedStatementSetter() {		                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {
		                	int p = 1;
		                    ps.setString(p++, activityList.get(i).getContract_id_fk());
		                    ps.setString(p++, activityList.get(i).getStruture_type_fk());
		                    ps.setString(p++, activityList.get(i).getSection());	
		                    ps.setString(p++, activityList.get(i).getLine());
		                    ps.setString(p++, activityList.get(i).getStructure());
		                    
		                    ps.setString(p++, activityList.get(i).getComponent());
		                    ps.setString(p++, activityList.get(i).getComponent_id());
		                    ps.setString(p++, activityList.get(i).getOrder());	
		                    ps.setString(p++, activityList.get(i).getActivity_name());
		                    ps.setString(p++, activityList.get(i).getPlanned_start());
		                    
		                    ps.setString(p++, activityList.get(i).getPlanned_finish());
		                    ps.setString(p++, activityList.get(i).getActual_start());
		                    ps.setString(p++, activityList.get(i).getActual_finish());	
		                    ps.setString(p++, activityList.get(i).getUnit());
		                    ps.setString(p++, activityList.get(i).getScope());
		                    
		                    ps.setString(p++, activityList.get(i).getCompleted());
		                    ps.setString(p++, activityList.get(i).getWeightage());
		                    ps.setString(p++, activityList.get(i).getComponent_details());	
		                    ps.setString(p++, activityList.get(i).getRemarks());
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                    return activityList.size();
		                }
		            });
			count = counts.length;
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
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
