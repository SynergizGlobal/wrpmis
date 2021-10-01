package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.ActivitiesDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.StripChart;

@Repository
public class ActivitiesDaoImpl implements ActivitiesDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	IssueDaoImpl issueDaoImpl;
	
	@Autowired
	MessagesDao messagesDao;

	@Override
	public List<StripChart> getActivitiesProjectsList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select wr.project_id_fk ,p.project_id,p.project_name " + "from work wr "
					+ "left outer join project p on wr.project_id_fk = p.project_id "
					+ "where wr.project_id_fk is not null " + "AND wr.work_id IN (" + "select c.work_id_fk "
					+ "from contract c " + "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select scv.contract_id_fk FROM activities scv WHERE scv.contract_id_fk IS NOT NULL GROUP BY scv.contract_id_fk ) "
					+ "GROUP BY c.work_id_fk) GROUP BY wr.project_id_fk ORDER BY wr.project_id_fk ASC";

			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesWorksList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name ,w.work_short_name " + "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN (select a.contract_id_fk FROM activities a WHERE a.contract_id_fk IS NOT NULL GROUP BY a.contract_id_fk ) ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk ASC";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesContractsList(StripChart obj) throws Exception {
		List<StripChart> objsList = new ArrayList<StripChart>();
		try {
			String qry = "select a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name,department_fk,department_name "
					+ "from activities a " + "left outer join contract c on a.contract_id_fk = c.contract_id "
					+ "left outer join department d on c.department_fk = d.department "
					+ "where a.contract_id_fk is not null and a.scope <> 'Completed' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by a.contract_id_fk ORDER BY a.contract_id_fk ASC ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

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
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesStructures(StripChart obj) throws Exception {
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

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesLines(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select line as strip_chart_line_id_fk from activities "
					+ "where line is not null and line <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and structure = ? ";
				arrSize++;
			}
			qry = qry + "group by line ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesSections(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select section as strip_chart_section_name " + "from activities "
					+ "where section is not null ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and contract_id_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				qry = qry + "and structure = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + "and line = ? ";
				arrSize++;
			}

			qry = qry + "group by section ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				pValues[i++] = obj.getStrip_chart_structure_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivityComponentsList(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();

			String qry = "select component as strip_chart_component " + "from activities "
					+ "where component is not null and contract_id_fk = ? and structure = ?";

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
			}

			qry = qry + " group by component";

			statement = connection.prepareStatement(qry);
			int i = 1;
			statement.setString(i++, obj.getContract_id_fk());
			statement.setString(i++, obj.getStrip_chart_structure_id_fk());

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				statement.setString(i++, obj.getStrip_chart_line_id_fk());
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				statement.setString(i++, obj.getStrip_chart_section_name());
			}

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sobj = new StripChart();
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));

				obj.setStrip_chart_component_id(sobj.getStrip_chart_component_id());
				sobj.setComponent_id_color(getComponentIdColor(obj, connection));
				objsList.add(sobj);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesComponentIds(StripChart obj) throws Exception {
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

			String qry = "select component_id as strip_chart_component_id_name," + "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and contract_id_fk = ? and structure = ? and component = ?  ";

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
			}

			qry = qry + " group by component_id";

			statement = connection.prepareStatement(qry);
			int i = 1;
			statement.setString(i++, obj.getContract_id_fk());
			statement.setString(i++, obj.getStrip_chart_structure_id_fk());
			statement.setString(i++, obj.getStrip_chart_component());

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				statement.setString(i++, obj.getStrip_chart_line_id_fk());
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				statement.setString(i++, obj.getStrip_chart_section_name());
			}

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sobj = new StripChart();
				/*sobj.setActual_start(resultSet.getString("actual_start"));
				sobj.setActual_finish(resultSet.getString("actual_finish"));
				sobj.setPlanned_start(resultSet.getString("planned_start"));
				sobj.setPlanned_finish(resultSet.getString("planned_finish"));*/
				sobj.setStrip_chart_component_id(resultSet.getString("strip_chart_component_id_name"));
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));

				obj.setStrip_chart_component_id(sobj.getStrip_chart_component_id());
				sobj.setComponent_id_color(getComponentIdColor(obj, connection));
				objsList.add(sobj);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
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
					+ " when ((select count(*) from activities s1 where s1.scope - s1.completed <> 0 "
					+ " and s1.contract_id_fk = ? and s1.structure = ? and s1.component_id = ? ";
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				qry = qry + " and s1.line = ?";
			}
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
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
					+ " when ((select count(*) from activities s3 where s3.completed = 0 and scope <> 0 "
					+ "and s3.contract_id_fk = ? and s3.structure = ? and s3.component_id = ? ";
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				qry = qry + " and s3.line = ?";
			}
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
				qry = qry + " and s3.section = ?";
			}
			qry = qry + ") > 0) then 'not-started'  " + " else 'in-progress' " + " end ) as color "
					+ " from activities where scope <> 0";

			stmt = connection.prepareStatement(qry);
			int p = 1;
			//stmt.setString(p++,CommonConstants2.STATUS_COMPLETED );
			stmt.setString(p++, sobj.getContract_id_fk());
			stmt.setString(p++, sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++, sobj.getStrip_chart_component_id());
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++, sobj.getStrip_chart_line_id_fk());
			}
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
				stmt.setString(p++, sobj.getStrip_chart_section_name());
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
			stmt.setString(p++, sobj.getContract_id_fk());
			stmt.setString(p++, sobj.getStrip_chart_structure_id_fk());
			stmt.setString(p++, sobj.getStrip_chart_component_id());
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				stmt.setString(p++, sobj.getStrip_chart_line_id_fk());
			}
			if (!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
				stmt.setString(p++, sobj.getStrip_chart_section_name());
			}

			rs = stmt.executeQuery();
			if (rs.next()) {
				color = rs.getString("color");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return color;
	}

	@Override
	public List<StripChart> getActivitiesActivities(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name "
					+ "from activities " + "where activity_id is not null and scope <> completed  "
					+ "and component_id = ? and structure = ? and component = ? ";
			int arrSize = 3;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];

			int i = 0;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED + "," + CommonConstants2.STATUS_DROPPED;
			//pValues[i++] = CommonConstants2.STATUS_COMPLETED;
			pValues[i++] = obj.getStrip_chart_component_id();
			pValues[i++] = obj.getStrip_chart_structure_id_fk();
			pValues[i++] = obj.getStrip_chart_component();

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				pValues[i++] = obj.getStrip_chart_section_name();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesComponents(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_component_fk from strip_chart_component_id";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				qry = qry + " where strip_chart_component_id = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component_id())) {
				pValues[i++] = obj.getStrip_chart_component_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChart getActivitiesDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select activity_id,"
					+ "DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(planned_finish,'%d-%m-%Y') AS planned_finish,"
					+ "component_id as strip_chart_component_id_name,completed as completed,scope as scope,(scope - completed) as remaining, unit as unit_fk "
					+ "from activities "
					+ "where activity_id is not null and component_id = ? and structure = ? and activity_id = ? ";

			int arrSize = 3;

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];

			int i = 0;
			pValues[i++] = obj.getStrip_chart_component_id();
			pValues[i++] = obj.getStrip_chart_structure_id_fk();
			pValues[i++] = obj.getStrip_chart_activity_id();

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				pValues[i++] = obj.getStrip_chart_line_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				pValues[i++] = obj.getStrip_chart_section_name();
			}

			sObj = (StripChart) jdbcTemplate.queryForObject(qry, pValues,
					new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public boolean updateActivities(StripChart obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			/*String activity = "-";			
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

			boolean tempflag = false;*/
			//if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
			/*if(tempflag){
				String issuesQry = "INSERT INTO issue(contract_id_fk,title,reported_by,priority_fk,category_fk,status_fk,date,location,"
						+ "corrective_measure,created_by_user_id_fk,created_date,zonal_railway_fk,other_organization)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?)";				
				KeyHolder holder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(issuesQry, Statement.RETURN_GENERATED_KEYS);
						int i = 1;
						ps.setString(i++, !StringUtils.isEmpty(obj.getContract_id_fk())?obj.getContract_id_fk():null);
						ps.setString(i++, obj.getIssue_description());
						ps.setString(i++, !StringUtils.isEmpty(obj.getReported_by())?obj.getReported_by():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
						ps.setString(i++, CommonConstants.ISSUE_STATUS_RAISED);
						ps.setString(i++, obj.getProgress_date());
						ps.setString(i++, !StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())?obj.getStrip_chart_structure_id_fk():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getRemarks())?obj.getRemarks():null);
						ps.setString(i++, obj.getCreated_by_user_id_fk());
						ps.setString(i++, !StringUtils.isEmpty(obj.getZonal_railway_fk())?obj.getZonal_railway_fk():null);
						ps.setString(i++, !StringUtils.isEmpty(obj.getOther_organization())?obj.getOther_organization():null);
						return ps;
					}
				}, holder);

				issueId = String.valueOf(holder.getKey().longValue());	*/
				
				
				/********************************************************************************************/
				
				/*if(!StringUtils.isEmpty(obj.getIssueFiles()) && obj.getIssueFiles().size() > 0) {
					
					String fileQry = "INSERT INTO issue_files (file_name,issue_id_fk)VALUES(:file_name,:issue_id)";
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
					
					List<MultipartFile> issueFiles = obj.getIssueFiles();
					for (MultipartFile multipartFile : issueFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + issueId +File.separator ;
							String fileName = multipartFile.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Issue-"+issue_id +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							Issue fileObj = new Issue();
							fileObj.setFile_name(fileName);
							fileObj.setIssue_id(issueId);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(fileObj);	
							template.update(fileQry, paramSource);
						}
					}
				}*/	

				/********************************************************************************************/
				
			/*String issue_id = issueId;
			String issue_status = CommonConstants.ISSUE_STATUS_RAISED;
			String reported_by_email_id = obj.getReported_by_email_id();
			issueDaoImpl.sendEmailWithIssueAlert(issue_id,issue_status,reported_by_email_id,null,null,null);
			
			}else{
			issueId = null;
			}*/
			
			String insertQry = "INSERT INTO approvable_activity_progress"
					+ "(progress_date,activity_id_fk,completed_scope,remarks,"
					+ "created_by_user_id_fk,approval_status_fk) "
					+ "VALUES (?,?,?,?,?,?)";
			
			
			KeyHolder holder = new GeneratedKeyHolder();
			int count = jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(insertQry, Statement.RETURN_GENERATED_KEYS);
					int i = 1;
					ps.setString(i++, obj.getProgress_date());
					ps.setString(i++, obj.getActivity_id());
					ps.setString(i++, obj.getProgress());
					ps.setString(i++, obj.getRemarks());
					ps.setString(i++, obj.getCreated_by_user_id_fk());
					ps.setString(i++, "Pending");
					return ps;
				}
			}, holder);
					
			if(count > 0) {
				flag = true;				
				String generated_id = String.valueOf(holder.getKey().longValue());		        
		        String qry = "INSERT INTO approvable_activity_progress_dyhod(dyhod_user_id_fk, progress_id_fk)values(:dyhod_user_id_fk,:progress_id)";
		        List<String> dyHodsList = getDyHodsOfActivity(obj.getStrip_chart_structure_id_fk());
		        if(!StringUtils.isEmpty(dyHodsList) && dyHodsList.size() > 0) {
		        	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			        int size = dyHodsList.size();
					SqlParameterSource[] source = new SqlParameterSource[size];
					int i = 0;
			        for (String dyhod : dyHodsList) {
			        	Activity aObj = new Activity();
						aObj.setDyhod_user_id_fk(dyhod);
						aObj.setProgress_id(generated_id);
						source[i] = new BeanPropertySqlParameterSource(aObj);
						i++;
					}
			        template.batchUpdate(qry, source);
		        }
			}
			
			/*String updateQry = "UPDATE activities SET  completed = ? + ?";				
			
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
			
			updateQry = updateQry + " WHERE activity_id = ?";
			
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
				pValues2[j++] = obj.getActivity_id();
			}
			
			int count2 = jdbcTemplate.update( updateQry, pValues2);			
			if(count2 > 0) {
				flag = true;
			}*/
			
			/********************************************************************************/
			if(!StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
				String qryUsers ="SELECT dy_hod_user_id_fk "
						+ "FROM fob_contract_responsible_people "
						+ "left join contract on contract_id_fk = contract_id "
						+ "where dy_hod_user_id_fk is not null and fob_id_fk = ? group by dy_hod_user_id_fk";
				List<String> users = jdbcTemplate.queryForList( qryUsers,new Object[]{obj.getStrip_chart_structure_id_fk()}, String.class);	
				if(!StringUtils.isEmpty(users) && users.size() > 0) {
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
					String userIds[]  = new String[users.size()];	
					userIds = users.toArray(userIds);
					String messageType = "Activity Progress";
					String redirect_url = "/progress-approval-page";
					
					String qryFOBName ="SELECT fob_name FROM fob where fob_id = ? ";
					String fob_name = jdbcTemplate.queryForObject( qryFOBName,new Object[]{obj.getStrip_chart_structure_id_fk()}, String.class);	
					if(StringUtils.isEmpty(fob_name)) {
						fob_name = obj.getStrip_chart_structure_id_fk();
					}
					String message = "Progress updated for FOB "+fob_name+". Pending approval.";
					 
					Messages msgObj = new Messages();
					msgObj.setUser_ids(userIds);
					msgObj.setMessage_type(messageType);
					msgObj.setRedirect_url(redirect_url);
					msgObj.setMessage(message);
					messagesDao.addMessages(msgObj,template);
				}
			}
			/********************************************************************************/	
			
			
			transactionManager.commit(status);
		}catch( Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	private List<String> getDyHodsOfActivity(String structure) throws Exception {
		List<String>  dy_hods = null;
		try {
			String qryUsers ="SELECT dy_hod_user_id_fk "
					+ "FROM fob_contract_responsible_people "
					+ "left join contract on contract_id_fk = contract_id "
					+ "where dy_hod_user_id_fk is not null and fob_id_fk = ? group by dy_hod_user_id_fk";
			dy_hods = jdbcTemplate.queryForList( qryUsers,new Object[]{structure}, String.class);
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return dy_hods;
	}

	private String getDepartment(String contract_id_fk) throws Exception {
		String department_id = null;
		try {
			String qry = "select department_fk from contract where contract_id = ?";
			department_id = (String) jdbcTemplate.queryForObject(qry, new Object[] { contract_id_fk }, String.class);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return department_id;
	}

	@Override
	public List<StripChart> getActivitiesTypes() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select strip_chart_type from strip_chart_type";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChart> getActivitiesStructureTypes() throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select structure_type from structure_type";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChart getActivitiesData(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select a.activity_id,a.contract_id_fk AS contract_id,a.structure AS strip_chart_structure_id,a.component_id AS strip_chart_component_id,"
					+ "a.component AS strip_chart_component,a.activity_id AS strip_chart_activity_id,a.activity_name AS strip_chart_activity_name,"
					+ "a.line AS strip_chart_line,a.structure AS structure_type,a.section AS strip_chart_section_id,completed,scope,(scope - completed) as remaining,unit as unit_fk,a.remarks,"
					+ "DATE_FORMAT(a.actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(a.actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(a.planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(a.planned_finish,'%d-%m-%Y') AS planned_finish,c.work_id_fk as work_id,c.contract_name,c.contract_short_name,w.project_id_fk as project_id "
					+ "from activities a " + "left outer join contract c on a.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id " + "where activity_id = ? ";

			sObj = (StripChart) jdbcTemplate.queryForObject(qry, new Object[] { obj.getActivity_id() },
					new BeanPropertyRowMapper<StripChart>(StripChart.class));

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public List<Issue> getIssuesCategoryList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select category from issue_category";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesPriorityList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select priority from issue_priority";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesStatusList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select status from issue_status";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
