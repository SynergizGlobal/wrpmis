package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class StripChartDaoImpl implements StripChartDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<StripChart> getStripChartActivities(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_id,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_finish,actual_start,actual_finish,unit_fk,"
					+ "scope,completed,weight,component_details,remarks,strip_chart_activity_id,strip_chart_activity_name "
					+ "from strip_chart "
					+ "LEFT OUTER JOIN strip_chart_activity a ON strip_chart_activity_id_fk = strip_chart_activity_id "
					+ "where actual_finish is null or actual_finish <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " and strip_chart_component_id_fk = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				placeholders[i++] = obj.getStrip_chart_component_id();
			}		
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartComponents(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_component_fk from strip_chart_component_id";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " where strip_chart_component_id = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				placeholders[i++] = obj.getStrip_chart_component_id();
			}		
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));		
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartComponentIds(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_component_id,contract_id_fk,strip_chart_line_id_fk,strip_chart_component_fk,strip_chart_section_id_fk,"
					+ "strip_chart_component_name,order,latitude,longtitude,ci.weight,strip_chart_component,strip_chart_structure "
					+ "from strip_chart_component_id ci "
					+ "LEFT OUTER JOIN strip_chart_component c ON strip_chart_component_fk = strip_chart_component "
					+ "where strip_chart_component_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and strip_chart_line_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and strip_chart_section_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure())) {
				qry = qry + " and c.strip_chart_structure = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				placeholders[i++] = obj.getContract_id_fk();
			}				
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				placeholders[i++] = obj.getStrip_chart_line_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				placeholders[i++] = obj.getStrip_chart_section_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure())) {
				placeholders[i++] = obj.getStrip_chart_structure();
			}
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartLines() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_line from strip_chart_line";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartSections() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_section_id,strip_chart_section_name from strip_chart_section";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartStructures() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_structure from strip_chart_structure";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartTypes() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_type from strip_chart_type";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartStructureTypes() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select structure_type from structure_type";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractsList(StripChart obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select contract_id,work_id_fk,contract_name,contract_type_fk,strip_chart_type_fk,scope_of_contract,contractor_id_fk,"
					+ "department_fk,hod_user_id_fk,dy_hod_user_id_fk,tally_head,estimated_cost,awarded_cost,loa_letter_number,loa_date,"
					+ "ca_no,ca_date,date_of_start,doc,actual_completion_date,completed_cost,contract_closure_date,weight,remarks "
					+ "from contract ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " where work_id_fk = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				placeholders[i++] = obj.getWork_id_fk();
			}		
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
			//objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChart getStripChartDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select strip_chart_id,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_finish,actual_start,actual_finish,unit_fk,"
					+ "scope,completed,weight,component_details,remarks,strip_chart_activity_id,strip_chart_activity_name "
					+ "from strip_chart "
					+ "LEFT OUTER JOIN strip_chart_activity a ON strip_chart_activity_id_fk = strip_chart_activity_id "
					+ "where strip_chart_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " and strip_chart_component_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				qry = qry + " and strip_chart_activity_id_fk = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				placeholders[i++] = obj.getStrip_chart_component_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				placeholders[i++] = obj.getStrip_chart_activity_id();
			}	
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			sObj = (StripChart) jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public boolean updateStripChart(StripChart obj) throws Exception {
		boolean flag = false;
		try {
			String qry = "select strip_chart_id,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_finish,actual_start,actual_finish,unit_fk,"
					+ "scope,completed,weight,component_details,remarks,strip_chart_activity_id,strip_chart_activity_name "
					+ "from strip_chart "
					+ "LEFT OUTER JOIN strip_chart_activity a ON strip_chart_activity_id_fk = strip_chart_activity_id "
					+ "where strip_chart_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " and strip_chart_component_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				qry = qry + " and strip_chart_activity_id_fk = ?";
				arrSize++;
			}
			
			String[] placeholders = new String[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				placeholders[i++] = obj.getStrip_chart_component_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				placeholders[i++] = obj.getStrip_chart_activity_id();
			}	
			
			Object[] pValues = new Object[]{};
			
			if(placeholders.length > 0) {
				pValues = new Object[] {StringUtils.arrayToCommaDelimitedString(placeholders)};
			}
			
			int count = jdbcTemplate.update( qry, pValues);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
}
