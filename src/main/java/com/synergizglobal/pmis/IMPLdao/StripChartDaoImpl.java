package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class StripChartDaoImpl implements StripChartDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Contract> getContractsList(StripChart obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select scv.Contract_ID as contract_id,c.work_id_fk,c.contract_name "
					+ "from strip_chart_view scv "
					+ "left outer join contract c on scv.Contract_ID = c.contract_id "
					
					+ "where scv.Contract_ID is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by scv.Contract_ID ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
			//objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getStripChartLines(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select Line as strip_chart_line_id_fk from strip_chart_view ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "where Contract_ID = ? ";
				arrSize++;
			}
			qry = qry + "group by Line ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartSections(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_section_strip_chart_section_id as strip_chart_section_id_fk,strip_chart_section_strip_chart_section_name as strip_chart_section_name from strip_chart_view ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "where Contract_ID = ? ";
				arrSize++;
			}
			qry = qry + "group by strip_chart_section_strip_chart_section_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_structure_strip_chart_structure as strip_chart_structure_id_fk from strip_chart_view ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "where Contract_ID = ? ";
				arrSize++;
			}
			qry = qry + "group by strip_chart_structure_strip_chart_structure ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getStripChartComponentIds(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select Actual_Finish_Date as actual_finish,Actual_Start_Date as actual_start,Planned_Start_Date as planned_start,Planned_Finish_Date as planned_finish,"
					+ "strip_chart_component_id_strip_chart_component_id as strip_chart_component_id,Component_id as strip_chart_component_id_name,"
					+ "strip_chart_component_strip_chart_component as strip_chart_component "
					+ "from strip_chart_view "
					+ "where strip_chart_component_id_strip_chart_component_id is not null ";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and Contract_ID = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and Line = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and strip_chart_section_strip_chart_section_id = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + " and strip_chart_structure_strip_chart_structure = ?";
			}
			
			qry = qry + " group by strip_chart_component_id_strip_chart_component_id";
			
			statement = connection.prepareStatement(qry);
			int i = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				statement.setString(i++,obj.getContract_id_fk());
			}				
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_section_id_fk());
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_structure_id_fk());
			}			
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				sobj = new StripChart();
				sobj.setActual_start(resultSet.getString("actual_start"));
				sobj.setActual_finish(resultSet.getString("actual_finish"));
				sobj.setPlanned_start(resultSet.getString("planned_start"));
				sobj.setPlanned_finish(resultSet.getString("planned_finish"));
				sobj.setStrip_chart_component_id(resultSet.getString("strip_chart_component_id"));
				sobj.setStrip_chart_component_id_name(resultSet.getString("strip_chart_component_id_name"));
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));
				sobj.setComponent_id_color(getComponentIdColor(sobj,connection));
				objsList.add(sobj);
			}
			
			/*int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and Contract_ID = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and Line = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and strip_chart_section_strip_chart_section_id = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + " and strip_chart_structure_strip_chart_structure = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}				
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				pValues[i++] = obj.getStrip_chart_section_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));*/			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	private String getComponentIdColor(StripChart sobj, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String color = "";
		try {			
			String updateQry = " select (case "  
					+" when ((select count(*) from strip_chart_view where Actual_Finish_Date is null and strip_chart_component_id_strip_chart_component_id = ?) = 0) then 'completed' "  
					+" when ((select DATE_FORMAT(max(Planned_Finish_Date),'%Y-%m-%d') < CURDATE() from strip_chart_view where strip_chart_component_id_strip_chart_component_id = ?) <> 0) then 'delayed' "  
					+" when ((select count(*) from strip_chart_view where Actual_Start_Date is not null and strip_chart_component_id_strip_chart_component_id = ?) = 0) then 'not-started'  "  
					+" else 'in-progress' "  
					+" end ) as color " 
					+" from strip_chart_view "
					+ "where strip_chart_component_id_strip_chart_component_id = ? "
					+ "group by strip_chart_component_id_strip_chart_component_id";
			stmt = connection.prepareStatement(updateQry);	
			stmt.setString(1,sobj.getStrip_chart_component_id() );
			stmt.setString(2,sobj.getStrip_chart_component_id() );
			stmt.setString(3,sobj.getStrip_chart_component_id() );
			stmt.setString(4,sobj.getStrip_chart_component_id() );
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				color = rs.getString("color");			
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return color;
	}

	@Override
	public List<StripChart> getStripChartActivities(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_activity_strip_chart_activity_id as strip_chart_activity_id,Activity as strip_chart_activity_name "
					+ "from strip_chart_view "
					+ "where Actual_Finish_Date is null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " and strip_chart_component_id_strip_chart_component_id = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));		
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
	public StripChart getStripChartDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select strip_chart_strip_chart_id as strip_chart_id,Actual_Finish_Date as actual_finish,Actual_Start_Date as actual_start,Planned_Start_Date as planned_start,"
					+ "Planned_Finish_Date as planned_finish,strip_chart_component_id_strip_chart_component_id as strip_chart_component_id,"
					+ "Component_id as strip_chart_component_id_name,Completed as completed,Total_Scope as scope,unit_unit as unit_fk "
					+ "from strip_chart_view "
					+ "where strip_chart_activity_strip_chart_activity_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " and strip_chart_component_id_strip_chart_component_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				qry = qry + " and strip_chart_activity_strip_chart_activity_id = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				pValues[i++] = obj.getStrip_chart_activity_id();
			}	
			
			sObj =  (StripChart) jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public boolean updateStripChart(StripChart obj) throws Exception {
		boolean flag = false;
		try {
			
			String issueId = null;
			if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
				String issuesQry = "INSERT INTO issue(contract_id_fk,description,reported_by,priority_fk,category_fk,date)VALUES(?,?,?,?,?,CURDATE())";				
				KeyHolder holder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(issuesQry, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, obj.getContract_id_fk());
						ps.setString(2, obj.getIssue_description());
						ps.setString(3, obj.getCreated_by_user_id_fk());
						ps.setString(4, obj.getIssue_priority_id());
						ps.setString(5, obj.getIssue_category_id());
						return ps;
					}
				}, holder);

				issueId = String.valueOf(holder.getKey().longValue());				
			}else{
				issueId = null;
			}
			
			String qry = "INSERT INTO scope_progress"
					+ "(progress_date,strip_chart_id_fk,completed_scope,attachment_url,remarks,issue_id_fk,created_by_user_id_fk) "
					+ "VALUES (?,?,?,?,?,?,?)";
			
			int arrSize = 7;
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj)) {				
				pValues[i++] = obj.getProgress_date();			
				pValues[i++] = obj.getStrip_chart_id();
				pValues[i++] = obj.getCompleted();
				pValues[i++] = obj.getAttachment_url();
				pValues[i++] = obj.getRemarks();
				pValues[i++] = issueId;
				pValues[i++] = obj.getCreated_by_user_id_fk();
			}			
			int count = jdbcTemplate.update( qry, pValues);			
			if(count > 0) {
				flag = true;
			}
			
			String updateQry = "UPDATE strip_chart SET  completed = ? + ?";				
			
			int arrSize2 = 3;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCompleted())) {
				float completed = Float.parseFloat(obj.getCompleted());
				if(completed == 0) {
					updateQry = updateQry + ", actual_start = ?";
					arrSize2++;
				}
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getScope()) && !StringUtils.isEmpty(obj.getCompleted()) && !StringUtils.isEmpty(obj.getRemaining())) {
				float scope = Float.parseFloat(obj.getScope());
				float completed = Float.parseFloat(obj.getCompleted());
				float progress = Float.parseFloat(obj.getProgress());
				if(scope == (completed+progress)) {
					updateQry = updateQry + ", planned_finish = ?";
					arrSize2++;
				}
			}
			
			updateQry = updateQry + " WHERE strip_chart_id = ?";
			
			Object[] pValues2 = new Object[arrSize2];
			
			int j = 0;
			if(!StringUtils.isEmpty(obj)) {				
				pValues2[j++] = obj.getCompleted();			
				pValues2[j++] = obj.getProgress();
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCompleted())) {
					float completed = Float.parseFloat(obj.getCompleted());
					if(completed == 0) {
						pValues2[j++] = obj.getProgress_date();
					}
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getScope()) && !StringUtils.isEmpty(obj.getCompleted()) && !StringUtils.isEmpty(obj.getRemaining())) {
					float scope = Float.parseFloat(obj.getScope());
					float completed = Float.parseFloat(obj.getCompleted());
					float progress = Float.parseFloat(obj.getProgress());
					if(scope == (completed+progress)) {
						pValues2[j++] = obj.getProgress_date();
					}
				}
				pValues2[j++] = obj.getStrip_chart_id();
			}
			
			int count2 = jdbcTemplate.update( updateQry, pValues2);			
			if(count2 > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
}
