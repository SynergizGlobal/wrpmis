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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class StripChartDaoImpl implements StripChartDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	


	@Override
	public List<StripChart> getStripChartProjectsList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select wr.project_id_fk ,p.project_id,p.project_name "
					+ "from work wr "
					+ "left outer join project p on wr.project_id_fk = p.project_id "					
					+ "where wr.project_id_fk is not null "
					+ "AND wr.work_id IN ("
					+"select c.work_id_fk "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select scv.contract_id_fk FROM strip_chart_general scv WHERE scv.contract_id_fk IS NOT NULL GROUP BY scv.contract_id_fk ) "
					+ "GROUP BY c.work_id_fk) GROUP BY wr.project_id_fk";			
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
			//objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartWorksList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select scv.contract_id_fk FROM strip_chart_general scv WHERE scv.contract_id_fk IS NOT NULL GROUP BY scv.contract_id_fk ) ";
					
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY c.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getStripChartContractsList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select scv.contract_id_fk as contract_id,c.work_id_fk,c.contract_name "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "
					
					+ "where scv.contract_id_fk is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by scv.contract_id_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
			//objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getStripChartStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select s.fob_id_fk as strip_chart_structure_id_fk from strip_chart_general s "
					+ "where s.fob_id_fk is not null and s.fob_id_fk <> '' and s.contract_id_fk = ? "
					+ "and (select count(*) from strip_chart_general s1 where s1.status <> ? "
					+ "and s1.contract_id_fk = ? and s1.fob_id_fk = s.fob_id_fk) > 0 ";
			qry = qry + "group by s.fob_id_fk ";
			
			
			int arrSize = 3;			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id_fk();
			pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			pValues[i++] = obj.getContract_id_fk();
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getStripChartLines(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select line as strip_chart_line_id_fk from strip_chart_general "
					+ "where line is not null and line <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "group by line ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
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
			String qry = "select section_id as strip_chart_section_id_fk,section as strip_chart_section_name "
					+ "from strip_chart_general "
					+ "where section_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and fob_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + "and line = ? ";
				arrSize++;
			}
			
			qry = qry + "group by section_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
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
			/*String qry = "select actual_finish as actual_finish,actual_start as actual_start,planned_start as planned_start,planned_finish as planned_finish,"
					+ "component_id as strip_chart_component_id,component_id_name as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from strip_chart_general "
					+ "where component_id is not null and contract_id_fk = ? and fob_id_fk = ?";*/
			
			String qry = "select component_id as strip_chart_component_id,component_id_name as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from strip_chart_general "
					+ "where component_id is not null and contract_id_fk = ? and fob_id_fk = ?";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and section_id = ?";
			}	
			
			qry = qry + " group by component_id";
			
			statement = connection.prepareStatement(qry);
			int i = 1;
			statement.setString(i++,obj.getContract_id_fk());
			statement.setString(i++,obj.getStrip_chart_structure_id_fk());
						
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_section_id_fk());
			}		
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				sobj = new StripChart();
				/*sobj.setActual_start(resultSet.getString("actual_start"));
				sobj.setActual_finish(resultSet.getString("actual_finish"));
				sobj.setPlanned_start(resultSet.getString("planned_start"));
				sobj.setPlanned_finish(resultSet.getString("planned_finish"));*/
				sobj.setStrip_chart_component_id(resultSet.getString("strip_chart_component_id"));
				sobj.setStrip_chart_component_id_name(resultSet.getString("strip_chart_component_id_name"));
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));
				
				obj.setStrip_chart_component_id(sobj.getStrip_chart_component_id());
				sobj.setComponent_id_color(getComponentIdColor(obj,connection));
				objsList.add(sobj);
			}
			
			/*int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and section_id = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + " and fob_id_fk = ?";
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
			
			String qry = " select (case "  
					+" when ((select count(*) from strip_chart_general s1 where s1.status <> ? "
					+ " and s1.contract_id_fk = ? and s1.fob_id_fk = ? and s1.component_id = ? ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s1.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
						qry = qry + " and s1.section_id = ?";
					}	
					qry = qry + ") = 0) then 'completed' "  
					+" when ((select count(*) from strip_chart_general where (select DATE_FORMAT(max(planned_finish),'%Y-%m-%d') from strip_chart_general s2 where "
					+ " s2.status <> ? and s2.contract_id_fk = ? and s2.fob_id_fk = ? and s2.component_id = ? ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s2.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
						qry = qry + " and s2.section_id = ?";
					}	
					qry = qry + ") < CURDATE() ) > 0) then 'delayed' "  
					+" when ((select count(*) from strip_chart_general s3 where s3.status = ?  "
					+ "and s3.contract_id_fk = ? and s3.fob_id_fk = ? and s3.component_id = ? ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s3.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
						qry = qry + " and s3.section_id = ?";
					}	
					qry = qry + ") > 0) then 'not-started'  "  
					+" else 'in-progress' "  
					+" end ) as color " 
					+" from strip_chart_general ";				
			
			stmt = connection.prepareStatement(qry);
			int p = 1;
			stmt.setString(p++,CommonConstants2.STATUS_COMPLETED );
			stmt.setString(p++,sobj.getContract_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_component_id());
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_section_id_fk());
			}	
			
			stmt.setString(p++,CommonConstants2.STATUS_NOT_STARTED );
			stmt.setString(p++,sobj.getContract_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_component_id());
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_section_id_fk());
			}
			
			stmt.setString(p++,CommonConstants2.STATUS_NOT_STARTED );
			stmt.setString(p++,sobj.getContract_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_component_id());
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_section_id_fk());
			}
			
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
			String qry = "select activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name "
					+ "from strip_chart_general "
					+ "where activity_id is not null and status <> ? "
					+ "and component_id = ? and fob_id_fk = ? ";
			int arrSize = 3;			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and section_id = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED + "," + CommonConstants2.STATUS_DROPPED;
			pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			pValues[i++] = obj.getStrip_chart_component_id();
			pValues[i++] = obj.getStrip_chart_structure_id_fk();
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				pValues[i++] = obj.getStrip_chart_section_id_fk();
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
	public StripChart getStripChartDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select strip_chart_id as strip_chart_id,"
					+ "DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(planned_finish,'%d-%m-%Y') AS planned_finish,"
					+ "component_id as strip_chart_component_id_name,completed as completed,scope as scope,remaining as remaining, units as unit_fk "
					+ "from strip_chart_general "
					+ "where activity_id is not null and component_id = ? and fob_id_fk = ? and activity_id = ? ";
			
			int arrSize = 3;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				qry = qry + " and section_id = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getStrip_chart_component_id();
			pValues[i++] = obj.getStrip_chart_structure_id_fk();
			pValues[i++] = obj.getStrip_chart_activity_id();		
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_id_fk())) {
				pValues[i++] = obj.getStrip_chart_section_id_fk();
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
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String activity = "-";			
			if(!StringUtils.isEmpty(obj.getStrip_chart_section_name()) && !obj.getStrip_chart_section_name().contains("Select")) {
				activity = activity + obj.getStrip_chart_section_name() + " - ";
			}
			if(!StringUtils.isEmpty(obj.getStrip_chart_line()) && !obj.getStrip_chart_line().contains("Select")) {
				activity = activity + obj.getStrip_chart_line() + " - ";
			}
			if(!StringUtils.isEmpty(obj.getStrip_chart_component_id_name())) {
				activity = activity + obj.getStrip_chart_component_id_name() + " - ";
			}
			if(!StringUtils.isEmpty(obj.getStrip_chart_activity_name())) {
				activity = activity + obj.getStrip_chart_activity_name() + " - ";
			}
			String activity_name = activity.substring(1, activity.length() - 2);
			String department_id = getDepartment(obj.getContract_id_fk());
			String issueId = null;
			if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
				String issuesQry = "INSERT INTO issue(contract_id_fk,title,description,reported_by,priority_fk,category_fk,status_fk,date,department_fk,location,activity)VALUES(?,?,?,?,?,?,?,CURDATE(),?,?,?)";				
				KeyHolder holder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(issuesQry, Statement.RETURN_GENERATED_KEYS);
						int i = 1;
						ps.setString(i++, !StringUtils.isEmpty(obj.getContract_id_fk())?obj.getContract_id_fk():null);
						ps.setString(i++, obj.getIssue_description());
						ps.setString(i++, obj.getIssue_description());
						ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
						ps.setString(i++, "Raised");
						ps.setString(i++, !StringUtils.isEmpty(department_id)?department_id:null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())?obj.getStrip_chart_structure_id_fk():null);
						ps.setString(i++, !StringUtils.isEmpty(activity_name)?activity_name:null);
						return ps;
					}
				}, holder);

				issueId = String.valueOf(holder.getKey().longValue());				
			}else{
				issueId = null;
			}
			
			String qry = "INSERT INTO scope_progress"
					+ "(progress_date,strip_chart_id_fk,completed_scope,attachment_url,remarks,"
					//+ "issue_id_fk,"
					+ "created_by_user_id_fk) "
					+ "VALUES (?,?,?,?,?,?)";
			
			int arrSize = 6;
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj)) {				
				pValues[i++] = obj.getProgress_date();			
				pValues[i++] = obj.getStrip_chart_id();
				pValues[i++] = obj.getProgress();
				pValues[i++] = obj.getAttachment_url();
				pValues[i++] = obj.getRemarks();
				//pValues[i++] = issueId;
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
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	private String getDepartment(String contract_id_fk) throws Exception {
		String department_id = null;
		try {
			String qry = "select department_fk from contract where contract_id = ?";
			department_id = (String)jdbcTemplate.queryForObject( qry, new Object[]{contract_id_fk},String.class);			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return department_id;
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
	public StripChart getStripChartData(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select scv.strip_chart_id,scv.contract_id_fk AS contract_id,scv.fob_id_fk AS strip_chart_structure_id,scv.component_id AS strip_chart_component_id,scv.component_id_name AS strip_chart_component_id_name,"
					+ "scv.component AS strip_chart_component,scv.activity_id AS strip_chart_activity_id,scv.activity_name AS strip_chart_activity_name,"
					+ "scv.line AS strip_chart_line,scv.structure AS structure_type,scv.section_id AS strip_chart_section_id,scv.section AS strip_chart_section_name,completed,scope,remaining,units as unit_fk,scv.`status` AS status_name,scv.remarks,"
					+ "DATE_FORMAT(scv.actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(scv.actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(scv.planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(scv.planned_finish,'%d-%m-%Y') AS planned_finish,c.work_id_fk as work_id,c.contract_name,w.project_id_fk as project_id "
					+ "from strip_chart_general scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where strip_chart_id = ? ";
			
			sObj =  (StripChart) jdbcTemplate.queryForObject( qry, new Object[] {obj.getStrip_chart_id()}, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}
	
}
