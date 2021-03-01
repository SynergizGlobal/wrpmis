package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesBulkUpdateDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.StripChart;
@Repository
public class ActivitiesBulkUpdateDaoImpl implements ActivitiesBulkUpdateDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;


	@Override
	public List<StripChart> getAcivitiesBulkUpdateProjectsList(StripChart obj) throws Exception { 
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
					+ "WHERE c.contract_id IN (select scv.contract_id_fk FROM activities scv WHERE scv.contract_id_fk IS NOT NULL GROUP BY scv.contract_id_fk ) "
					+ "GROUP BY c.work_id_fk) GROUP BY wr.project_id_fk ORDER BY wr.project_id_fk ASC";			
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateWorksList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name ,w.work_short_name "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select a.contract_id_fk FROM activities a WHERE a.contract_id_fk IS NOT NULL GROUP BY a.contract_id_fk ) ";
					
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk ASC";
			
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
	public List<StripChart> getAcivitiesBulkUpdateContractsList(StripChart obj) throws Exception {
		List<StripChart> objsList = new ArrayList<StripChart>();
		try {
			String qry = "select a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "
					
					+ "where a.contract_id_fk is not null and a.scope <> 'Completed' " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by a.contract_id_fk ORDER BY a.contract_id_fk ASC ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
			//List<StripChart> list = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
			/*for (StripChart contractObj : list) {
				String structures_count_qry = "select count(*) from activities s "
						+ "where s.fob_id_fk is not null and s.fob_id_fk <> '' and s.contract_id_fk = ? "
						+ "and (select count(*) from activities s1 where s1.status <> ? "
						+ "and s1.contract_id_fk = ? and s1.fob_id_fk = s.fob_id_fk) > 0 ";
				
				
				arrSize = 3;			
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = contractObj.getContract_id();
				pValues[i++] = CommonConstants2.STATUS_COMPLETED;
				pValues[i++] = contractObj.getContract_id();
				
				int c = jdbcTemplate.queryForObject( structures_count_qry, pValues ,Integer.class);	
				if(c > 0) {
					objsList.add(contractObj);
				}
			}	*/
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChart getAcivitiesBulkData(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select activity_id AS strip_chart_activity_id,scv.contract_id_fk AS contract_id,scv.structure AS strip_chart_structure_id,scv.component_id AS strip_chart_component_id_name,"
					+ "scv.component AS strip_chart_component,scv.activity_name AS strip_chart_activity_name,"
					+ "scv.line AS strip_chart_line,scv.structure AS structure_type,scv.section AS strip_chart_section_name,completed,scope,remaining,units as unit_fk,scv.`status` AS status_name,scv.remarks,"
					+ "DATE_FORMAT(scv.actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(scv.actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(scv.planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(scv.planned_finish,'%d-%m-%Y') AS planned_finish,c.work_id_fk as work_id,c.contract_name,c.contract_short_name,w.project_id_fk as project_id "
					+ "from activities scv "
					+ "left outer join contract c on scv.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where activity_id = ? ";
			
			sObj =  (StripChart) jdbcTemplate.queryForObject( qry, new Object[] {obj.getActivity_id()}, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	
	@Override
	public List<StripChart> getAcivitiesBulkUpdateStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select s.structure as strip_chart_structure_id_fk from activities s "
					+ "where s.structure is not null and s.structure <> '' and s.contract_id_fk = ? "
					+ "and (select count(*) from activities s1 where s1.scope <> s1.completed and s1.contract_id_fk = ? "
					+ "and s1.structure = s.structure) > 0 ";
			qry = qry + "group by s.structure ";
			
			
			int arrSize = 2;			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id_fk();
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			pValues[i++] = obj.getContract_id_fk();
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateLines(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select line as strip_chart_line_id_fk from activities "
					+ "where line is not null and line <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and structure = ? ";
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
	public List<StripChart> getAcivitiesBulkUpdateSections(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select section as strip_chart_section_name "
					+ "from activities "
					+ "where section is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and structure = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + "and line = ? ";
				arrSize++;
			}
			
			qry = qry + "group by section ";
			
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
	public List<StripChart> getAcivitiesBulkUpdateComponentIds(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();
			/*String qry = "select actual_finish as actual_finish,actual_start as actual_start,planned_start as planned_start,planned_finish as planned_finish,"
					+ "component_id as strip_chart_component_id,component_id as strip_chart_component_id,"
					+ "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and contract_id_fk = ? and fob_id_fk = ?";*/
			
			String qry = "select component_id as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and contract_id_fk = ? and structure = ?";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
			}	
			
			qry = qry + " group by component_id";
			
			statement = connection.prepareStatement(qry);
			int i = 1;
			statement.setString(i++,obj.getContract_id_fk());
			statement.setString(i++,obj.getStrip_chart_structure_id_fk());
						
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				statement.setString(i++,obj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				statement.setString(i++,obj.getStrip_chart_section_name());
			}		
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				sobj = new StripChart();
				/*sobj.setActual_start(resultSet.getString("actual_start"));
				sobj.setActual_finish(resultSet.getString("actual_finish"));
				sobj.setPlanned_start(resultSet.getString("planned_start"));
				sobj.setPlanned_finish(resultSet.getString("planned_finish"));*/
				sobj.setStrip_chart_component_id(resultSet.getString("strip_chart_component_id_name"));
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));
				
				obj.setStrip_chart_component_id(sobj.getStrip_chart_component_id());
				sobj.setComponent_id_color(getComponentIdColor(obj,connection));
				objsList.add(sobj);
			}
						
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
					+" when ((select count(*) from activities s1 where s1.scope - s1.completed <> 0 "
					+ " and s1.contract_id_fk = ? and s1.structure = ? and s1.component_id = ? ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s1.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
						qry = qry + " and s1.section = ?";
					}	
					qry = qry + ") = 0) then 'completed' "  
					//+" when ((select count(*) from activities where (select DATE_FORMAT(max(planned_finish),'%Y-%m-%d') from activities s2 where "
					//+ " s2.completed <> 0 and s2.contract_id_fk = ? and s2.structure = ? and s2.component_id = ? ";
				//	if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				//		qry = qry + " and s2.line = ?";
				//	}			
				//	if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
					//	qry = qry + " and s2.section = ?";
					//}	
					//qry = qry + ") < CURDATE() ) > 0) then 'delayed' "  
					+" when ((select count(*) from activities s3 where s3.completed = 0 and scope <> 0 "
					+ "and s3.contract_id_fk = ? and s3.structure = ? and s3.component_id = ? ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s3.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
						qry = qry + " and s3.section = ?";
					}	
					qry = qry + ") > 0) then 'not-started'  "  
					+" else 'in-progress' "  
					+" end ) as color " 
					+" from activities where scope <> 0";				
			
			stmt = connection.prepareStatement(qry);
			int p = 1;
			//stmt.setString(p++,CommonConstants2.STATUS_COMPLETED );
			stmt.setString(p++,sobj.getContract_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_component_id());
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
				stmt.setString(p++,sobj.getStrip_chart_section_name());
			}	
			
			//stmt.setString(p++,CommonConstants2.STATUS_NOT_STARTED );
			//stmt.setString(p++,sobj.getContract_id_fk());
			//stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			//stmt.setString(p++,sobj.getStrip_chart_component_id());
			//if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
			//	stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			//}			
			//if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
			//	stmt.setString(p++,sobj.getStrip_chart_section_name());
			//}
			
			//stmt.setString(p++,CommonConstants2.STATUS_NOT_STARTED );
			stmt.setString(p++,sobj.getContract_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,sobj.getStrip_chart_component_id());
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++,sobj.getStrip_chart_line_id_fk());
			}			
			if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
				stmt.setString(p++,sobj.getStrip_chart_section_name());
			}
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				color = rs.getString("color");			
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return color;
	}

	@Override
	public List<StripChart> getAcivitiesBulkActivitiesList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name "
					+ "from activities "
					+ "where activity_id is not null and scope <> completed  "
					+ "and component_id = ? and structure = ? ";
			int arrSize = 2;			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED + "," + CommonConstants2.STATUS_DROPPED;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			pValues[i++] = obj.getStrip_chart_component_id();
			pValues[i++] = obj.getStrip_chart_structure_id_fk();
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				pValues[i++] = obj.getStrip_chart_section_name();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChart getAcivitiesBulkUpdateDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select activity_id,"
					+ "DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(planned_finish,'%d-%m-%Y') AS planned_finish,"
					+ "component_id as strip_chart_component_id_name,completed as completed,scope as scope,remaining as remaining, units as unit_fk "
					+ "from activities "
					+ "where activity_id is not null and component_id = ? and structure = ? and activity_id = ? ";
			
			int arrSize = 3;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				pValues[i++] = obj.getStrip_chart_section_name();
			}
			
			sObj =  (StripChart) jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public List<StripChart> getstripChartfilterList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id,component_id as strip_chart_component_id_name,component as strip_chart_component,activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name,planned_start "  
					+",planned_finish,IFNULL(NULLIF(scope, '' ), 0) as scope,IFNULL(NULLIF(completed, '' ), 0) as completed from activities  " 
					+ " where activity_id is not null and scope <> completed ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + "and component_id = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				qry = qry + "and activity_id = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and structure = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by activity_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_activity_id())) {
				pValues[i++] = obj.getStrip_chart_activity_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}
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
	public boolean updateAcivitiesBulk(StripChart obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement updateStmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO activity_progress"
					+ "(created_by_user_id_fk, remarks, completed_scope, activity_id_fk,progress_date)"
					+ "VALUES"
					+ "(?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) {
				obj.setActualScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getActualScopes()));
				if(arraySize < obj.getActualScopes().length) {
					arraySize = obj.getActualScopes().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getActivity_ids()) && obj.getActivity_ids().length > 0) {
				obj.setActivity_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getActivity_ids()));
				if(arraySize < obj.getActivity_ids().length) {
					arraySize = obj.getActivity_ids().length;
				}
			}
			
			for (int i = 0; i < arraySize; i++) {				
			    int k = 1;
			    if( obj.getActualScopes().length > 0 && !StringUtils.isEmpty(obj.getActualScopes()[i])) {
				    insertStmt.setString(k++, obj.getCreated_by_user_id_fk());
				    insertStmt.setString(k++, obj.getRemarks());
				    insertStmt.setString(k++, obj.getActualScopes().length > 0 ?obj.getActualScopes()[i]:null);
				    insertStmt.setString(k++,(obj.getActivity_ids()[i]));
				    insertStmt.setString(k++, obj.getProgress_date());
				    insertStmt.addBatch();
			    }
			}
			int[] insertCount = insertStmt.executeBatch();
			
			if(insertCount.length > 0) {
				flag = true;
			}

			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			
			if(flag) {
				int arrSize =0;
				if( !StringUtils.isEmpty(obj.getCompletedScopes()) && obj.getCompletedScopes().length > 0) {
					obj.setCompletedScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getCompletedScopes()));
					if(arrSize < obj.getCompletedScopes().length) {
						arrSize = obj.getCompletedScopes().length;
					}
				}
				if( !StringUtils.isEmpty(obj.getTotalScopes()) && obj.getTotalScopes().length > 0) {
					obj.setTotalScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getTotalScopes()));
					if(arrSize < obj.getTotalScopes().length) {
						arrSize = obj.getTotalScopes().length;
					}
				}
				if( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) {
					obj.setActualScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getActualScopes()));
					if(arrSize < obj.getActualScopes().length) {
						arrSize = obj.getActualScopes().length;
					}
				}
				if( !StringUtils.isEmpty(obj.getActivity_ids()) && obj.getActivity_ids().length > 0) {
					obj.setActivity_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getActivity_ids()));
					if(arrSize < obj.getActivity_ids().length) {
						arrSize = obj.getActivity_ids().length;
					}
				}
				for (int i = 0; i < arrSize; i++) {	
					
					String updateQry = "UPDATE  activities set completed = IFNULL(NULLIF(completed, '' ), 0) + ?";	
					
					float scope = 0,completed=0,actual=0;
					if(!StringUtils.isEmpty(obj.getTotalScopes()[i])) {
						 scope = Float.parseFloat(obj.getTotalScopes()[i]);
					}
					if(!StringUtils.isEmpty(obj.getCompletedScopes()[i])) {
						 completed = Float.parseFloat(obj.getCompletedScopes()[i]);
					}
					if(!StringUtils.isEmpty(obj.getActualScopes()[i])) {
						 actual = Float.parseFloat(obj.getActualScopes()[i]);
					}
					
					if(completed == 0) {
						updateQry = updateQry + ", actual_start = ?";
					}
					
					
					if((completed+actual) > 0 && scope == (completed+actual)) {
						updateQry = updateQry + ", actual_finish = ?";						
					}
					updateQry = updateQry + " WHERE activity_id = ?";
					updateStmt = con.prepareStatement(updateQry);
						
					int k = 1;
					
					updateStmt.setString(k++, String.valueOf(actual) );	
					
					if(completed == 0) {
						updateStmt.setString(k++, obj.getProgress_date() );	
					}
					if((completed+actual) > 0 && scope == (completed+actual)) {
						updateStmt.setString(k++, obj.getProgress_date());						
					}
					updateStmt.setString(k++,(obj.getActivity_ids()[i]));
					updateStmt.executeUpdate();
				}
				
					
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}	
		return flag;
	}

}

