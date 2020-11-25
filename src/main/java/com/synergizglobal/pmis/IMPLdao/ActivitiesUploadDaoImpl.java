package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesUploadDao;
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
	public int uploadActivities(Set<String> contractList, Set<String> componentList, Set<String> structureList,
			Set<String> lineList, Set<String> sectionList, Set<String> scTypeList, Set<String> orderList,
			Set<String> latitudeList, Set<String> longitudeList, List<StripChart> activityList) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {			
			connection = dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			
			
			String linesQry = "INSERT INTO strip_chart_line (strip_chart_line,contract_id_fk) SELECT * FROM (SELECT ?,?) AS tmp "
					+ "WHERE NOT EXISTS ( SELECT strip_chart_line FROM strip_chart_line WHERE strip_chart_line = ? and contract_id_fk = ? LIMIT 1 );";
			statement = connection.prepareStatement(linesQry);
			if(!StringUtils.isEmpty(lineList) && lineList.size() > 0) {
				for(String line : lineList){
					if(!StringUtils.isEmpty(contractList) && contractList.size() > 0) {
						for (String contract_id : contractList) {
							statement.setString(1, line.toString());
							statement.setString(2, contract_id.toString());
							statement.setString(3, line.toString());
							statement.setString(4, contract_id.toString());
							statement.addBatch();
						}
					}
		        }
				int[] lineCount = statement.executeBatch();				
			}
			
			
			String sectionsQry = "INSERT INTO strip_chart_section (strip_chart_section_name,contract_id_fk) SELECT * FROM (SELECT ?,?) AS tmp "
					+ "WHERE NOT EXISTS ( SELECT strip_chart_section_name FROM strip_chart_section WHERE strip_chart_section_name = ? and contract_id_fk = ? LIMIT 1 );";
			statement = connection.prepareStatement(sectionsQry);
			if(!StringUtils.isEmpty(sectionList) && sectionList.size() > 0) {
				for(String section : sectionList){
					if(!StringUtils.isEmpty(contractList) && contractList.size() > 0) {
						for (String contract_id : contractList) {
							statement.setString(1, section.toString());
							statement.setString(2, contract_id.toString());
							statement.setString(3, section.toString());
							statement.setString(4, contract_id.toString());
							statement.addBatch();
						}
					}
		        }
				int[] sectionCount = statement.executeBatch();				
			}
			
			
			String componentsQry = "INSERT INTO strip_chart_component (strip_chart_component,structure_type_fk) SELECT * FROM (SELECT ?,?) AS tmp "
					+ "WHERE NOT EXISTS ( SELECT strip_chart_component FROM strip_chart_component WHERE strip_chart_component = ? and structure_type_fk = ? LIMIT 1 );";
			statement = connection.prepareStatement(componentsQry);
			if(!StringUtils.isEmpty(componentList) && componentList.size() > 0) {
				for(String component : componentList){
					if(!StringUtils.isEmpty(scTypeList) && scTypeList.size() > 0) {
						for (String scType : scTypeList) {
							statement.setString(1, component.toString());
							statement.setString(2, scType.toString());
							statement.setString(3, component.toString());
							statement.setString(4, scType.toString());
							statement.addBatch();
						}
					}
		        }
				int[] sectionCount = statement.executeBatch();				
			}
			
			
			
			String qry = "INSERT INTO design (structure,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_start,planned_finish,actual_start,actual_finish,unit_fk,scope,completed,data_date,weight,component_details,remarks) "
					+ "VALUES(:structure,:strip_chart_component_id_fk,:strip_chart_activity_id_fk,:planned_start,:planned_finish,:actual_start,:actual_finish,:unit_fk,:scope,:completed,:data_date,:weight,:component_details,:remarks)";
			
			@SuppressWarnings("unchecked")
			Map<String,Object>[] batchOfInputs = new HashMap[activityList.size()];
	        int objCount = 0;
	        for(StripChart obj : activityList){
	           Map<String,Object> map = new HashMap<String,Object>();
	           map.put("structure",obj.getStrip_chart_structure());
	           map.put("strip_chart_component_id_fk",obj.getStrip_chart_component_id());
	           map.put("strip_chart_activity_id_fk",obj.getStrip_chart_activity_id());	           
	           map.put("planned_start",obj.getPlanned_start());
	           map.put("planned_finish",obj.getPlanned_finish());
	           map.put("actual_start",obj.getActual_start());	           
	           map.put("actual_finish",obj.getActual_finish());	           
	           map.put("unit_fk",obj.getUnit_fk());
	           map.put("scope",obj.getScope());	           
	           map.put("completed",obj.getCompleted());
	           map.put("data_date",obj.getData_date());
	           map.put("weight",obj.getWeight());
	           map.put("component_details",obj.getComponent_details());
	           map.put("remarks",obj.getRemarks());
	           
	           batchOfInputs[objCount++]= map;
	        }
	        int[] updateCounts = namedParamJdbcTemplate.batchUpdate(qry,batchOfInputs);

								
			for (StripChart obj : activityList) {
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			    KeyHolder keyHolder = new GeneratedKeyHolder();
			    count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
			    //return keyHolder.getKey().intValue();

				//BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				//int count = namedParamJdbcTemplate.update(qry, paramSource);		
			    String designId = null;
				if(count > 0) {
					 designId = String.valueOf(keyHolder.getKey().intValue());
					 
				}
				
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	}

}
