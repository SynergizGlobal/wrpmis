package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ActivitiesBulkUpdateDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.StripChart;
@Repository
public class ActivitiesBulkUpdateDaoImpl implements ActivitiesBulkUpdateDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	MessagesDao messagesDao;
	
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
					+ "WHERE c.contract_id IN ("
					+ "select a.contract_id_fk "
					+ "FROM activities a "
					+ "left outer join contract co on a.contract_id_fk = co.contract_id "	
					+ "WHERE a.contract_id_fk is not null and a.scope <> IFNULL('Completed',0) ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ? group by fob_id_fk) "
						+ ") group by a.contract_id_fk ORDER BY a.contract_id_fk ASC "
						+ ")";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}else {
				qry = qry + ")";
			}
			qry = qry + "GROUP BY c.work_id_fk) GROUP BY wr.project_id_fk ORDER BY wr.project_id_fk ASC";	
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			
			}
	
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
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
					+ "WHERE c.contract_id IN ("
					+ "select a.contract_id_fk "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "					
					+ "where a.contract_id_fk is not null and a.scope <> IFNULL('Completed',0) " ;
					
					int arrSize = 0;
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
								+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by fob_id_fk) "
								+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by fob_id_fk) "
								+ "or structure in (select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ? group by fob_id_fk) "
								+ ") group by a.contract_id_fk ORDER BY a.contract_id_fk ASC "
								+ ")";
						arrSize++;
						arrSize++;
						arrSize++;
						arrSize++;
						arrSize++;
						arrSize++;
					}else {
						qry = qry + ")";
					}
					
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			
			}
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
		List<StripChart> objsList1 = null;
		try {
			/*String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "*/
			
			String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "					
					+ "where a.contract_id_fk is not null and a.scope <> IFNULL('Completed',0) " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+" or contract_id in (select contract_id from contract where contract_id in(select contract_id_fk from fob_contract_responsible_people where fob_id_fk in(select fob_id_fk from pmis.fob_responsible_people where responsible_people_id_fk = ?))) )";
				arrSize++;
				arrSize++;
				arrSize++;

			}
			
			
			qry = qry + " group by a.contract_id_fk ORDER BY a.contract_id_fk ASC ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();

				//objsList1 = getExecutivesList(obj);	
			
			}

			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				for (StripChart con : objsList1) {
			        boolean found=false;
			        for (StripChart con1 : objsList) {
			            if ((con.getContract_id().equals(con1.getContract_id()))) {
			                found=true;
			                break;
			            }
			        }
			        if(!found){
			        	objsList.add(con);
			        }
			    }
			}	*/		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
	
	@Override
	public List<StripChart> getAcivitiesBulkUpdateStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		List<StripChart> objsList1 = null;
		try {
			String qry = "SELECT s.structure as strip_chart_structure_id_fk "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "WHERE s.structure is not null AND s.structure <> '' AND s.contract_id_fk = ? "
					+ "AND (select count(*) from activities WHERE scope <> IFNULL(completed,0) and contract_id_fk = ? AND structure = s.structure ) > 0 ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ( "
						+ "structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ? group by fob_id_fk)) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			qry = qry + " group by s.structure ";			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id_fk();
			pValues[i++] = obj.getContract_id_fk();
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}	
		
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
	
	private List<StripChart> getExecutivesList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry ="SELECT id, w.work_name,w.work_short_name,f.contract_id_fk as contract_id,"
					+ "w.project_id_fk,p.project_name,c.hod_user_id_fk as hod_user_id,u.designation,us.designation as dy_hod_designation,u.user_name,"
					+ "c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_status_fk,c.dy_hod_user_id_fk as dy_hod_user_id,"
					+ "c.contract_short_name,contractor_id_fk,cr.contractor_name,c.hod_user_id_fk,c.dy_hod_user_id_fk,f.fob_id,f.fob_name,f.work_status_fk"
					+ " FROM fob_responsible_people ce "
					+ "LEFT JOIN fob f on f.fob_id = ce.fob_id_fk "
					+ "LEFT JOIN contract c on c.contract_id = f.contract_id_fk "+
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id where contract_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and f.work_id_fk = ?";
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id()) && !obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN)) {
				qry = qry + " and  responsible_people_id_fk = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
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
	public List<StripChart> getAcivitiesBulkUpdateComponentsList(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "select component as strip_chart_component "
					+ "from activities "
					+ "where component is not null and component <> '' and contract_id_fk = ? and structure = ?";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_line_id_fk())) {
				qry = qry + " and line = ?";
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_section_name())) {
				qry = qry + " and section = ?";
			}	
			
			qry = qry + " group by component";
			
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
			
			String qry = "select distinct component_id as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and component_id <> '' and contract_id_fk = ? and structure = ? and component = ?";
			
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
			statement.setString(i++,obj.getStrip_chart_component());
						
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
					+" when ((select count(*) from activities s1 where (s1.scope - IFNULL(s1.completed,0)) <> 0 "
					+ " and s1.contract_id_fk = ? and s1.structure = ? and s1.component_id = ? and s1.component = ? ";
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
					+" when ((select count(*) from activities s3 where IFNULL(s3.completed,0) = 0 and scope <> 0 "
					+ "and s3.contract_id_fk = ? and s3.structure = ? and s3.component_id = ? and s3.component = ? ";
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
			stmt.setString(p++,sobj.getStrip_chart_component());
			
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
			stmt.setString(p++,sobj.getStrip_chart_component());
			
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
					+ "where activity_id is not null and scope <> IFNULL(completed,0)  "
					+ "and component_id = ? and structure = ? and component = ? ";
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
			pValues[i++] = obj.getStrip_chart_component();
			
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
	public List<StripChart> getActivitiesfiltersList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id,component_id as strip_chart_component_id_name,component as strip_chart_component,activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name,DATE_FORMAT(planned_start,'%d-%b-%y') AS planned_start "  
					+",DATE_FORMAT(planned_finish,'%d-%b-%y') AS planned_finish,IFNULL(NULLIF(scope, '' ), 0) as scope,IFNULL(NULLIF(completed, '' ), 0) as completed, unit as unit_fk from activities  " 
					+ " where activity_id is not null ";
			
				if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()))
				{
					qry = qry + " and scope <> completed ";
				}			
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				qry = qry + "and component = ? ";
				arrSize++;
			}
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_component())) {
				pValues[i++] = obj.getStrip_chart_component();
			}
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
			String insertQry = "INSERT INTO approvable_activity_progress"
					+ "(created_by_user_id_fk, remarks, completed_scope, activity_id_fk,progress_date,approval_status_fk,updated_scope)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) {
				obj.setActualScopes(CommonMethods.replaceEmptyByNullInSringArray(obj.getActualScopes()));
				if(arraySize < obj.getActualScopes().length) {
					arraySize = obj.getActualScopes().length;
				}
			}
			
			  if( !StringUtils.isEmpty(obj.getActivity_ids()) && obj.getActivity_ids().length > 0) 
			  {
				 obj.setActivity_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getActivity_ids())); if(arraySize < obj.getActivity_ids().length) 
				 { 
					 arraySize= obj.getActivity_ids().length; 
				 } 
			 }
			 
			String[] SplitScope=obj.getScope().split(",");
			
			for (int i = 0; i < arraySize; i++) 
			{				
			    int k = 1;
			    //if( obj.getActualScopes().length > 0 && !StringUtils.isEmpty(obj.getActualScopes()[i]))
			    if( obj.getActualScopes().length > 0)
			    {
			    	String Prdate=null;
					if(!StringUtils.isEmpty(obj.getProgress_date())) 
					{	
				    	Calendar c3 = Calendar.getInstance();
				    	String[] SplitWith3=obj.getProgress_date().split("-");
				    	
			            SimpleDateFormat PrFormat = new SimpleDateFormat("MMMM");
			            c3.setTime(PrFormat.parse(SplitWith3[1]));
			            c3.set(Calendar.DATE, Integer.parseInt(SplitWith3[0]));
			            
						DateFormat dfm3 = new SimpleDateFormat("dd-MM-yy");	
						DateFormat rdfm3 = new SimpleDateFormat("YYYY");
						Date Cdfm3=dfm3.parse(SplitWith3[0]+'-'+c3.get(Calendar.MONTH)+'-'+SplitWith3[2]);	
						
			            String gdate3=rdfm3.format(Cdfm3);
			            
		            
			            
			            c3.set(Calendar.YEAR, Integer.parseInt(gdate3));		            
			            
			            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			            
			            Prdate=df.format(c3.getTime());
					}
		            
				    insertStmt.setString(k++, obj.getCreated_by_user_id_fk());
				    insertStmt.setString(k++, obj.getRemarks());
				    insertStmt.setString(k++, obj.getActualScopes().length > 0 ?obj.getActualScopes()[i]:null);
				    insertStmt.setString(k++,(obj.getActivity_ids()[i]));
				    insertStmt.setString(k++, Prdate);
				    insertStmt.setString(k++, "Pending");
				    insertStmt.setString(k++,(SplitScope[i]));
				    insertStmt.addBatch();
			    }
			}
			int[] insertCount = insertStmt.executeBatch();
			
			if(insertCount.length > 0) {
				flag = true;
				List<String> generatedIds = new ArrayList<String>();
				ResultSet rs = insertStmt.getGeneratedKeys();
		        if (rs != null) {
		            while (rs.next()) {
		                int generatedId = rs.getInt(1);
		                generatedIds.add(String.valueOf(generatedId));
		            }
		        }
		        DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
		        
		        String qry = "INSERT INTO approvable_activity_progress_dyhod(dyhod_user_id_fk, progress_id_fk)values(?,?)";
		        insertStmt = con.prepareStatement(qry);
		        List<String> dyHodsList = getDyHodsOfActivity(obj.getStrip_chart_structure_id_fk());
		        for (String dyhod : dyHodsList) {
					for (String generated_id : generatedIds) {
						insertStmt.setString(1, dyhod);
					    insertStmt.setString(2, generated_id);
					    insertStmt.addBatch();
					}
				}
		        insertStmt.executeBatch();
		        
		        DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			}
			
			if(CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()))
			{			
			/*if(flag) {*/
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
				for (int i = 0; i < arrSize; i++) 
				{	
					
					
					float scope = 0,completed=0,actual=0;
					if(!StringUtils.isEmpty(obj.getTotalScopes()[i])) {
						 scope = Float.parseFloat(obj.getTotalScopes()[i]);
					}
					if(!StringUtils.isEmpty(obj.getCompletedScopes()[i])) {
						 completed = Float.parseFloat(obj.getCompletedScopes()[i]);
					}	
					if( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) {
						 actual = Float.parseFloat(obj.getActualScopes()[i]==null?"0":obj.getActualScopes()[i]);
					}
					else
					{
						actual=0;
					}
					/*Date planstartDate =null;*/

					String SplitStr=obj.getPlanned_start();
					String[] StrVar=SplitStr.split(",");
					
					String SplitStr1=obj.getPlanned_finish();
					String[] StrVar1=SplitStr1.split(",");	
					
					
					String SplitStr2=obj.getScope();
					String[] StrVar2=SplitStr2.split(",");	
					
					
					String[] SplitWith=StrVar[i].split("-");
					String[] SplitWith1=StrVar1[i].split("-");
					
		            Calendar c1 = Calendar.getInstance();
		            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
		            
		            
		            c1.setTime(inputFormat.parse(SplitWith[1]));
		            c1.set(Calendar.DATE, Integer.parseInt(SplitWith[0]));

					DateFormat dfm1 = new SimpleDateFormat("dd-MM-yy");
					DateFormat rdfm1 = new SimpleDateFormat("YYYY");
					Date Cdfm1=dfm1.parse(SplitWith[0]+'-'+c1.get(Calendar.MONTH)+'-'+SplitWith[2]);	
					
		            String gdate1=rdfm1.format(Cdfm1);
		            
		            c1.set(Calendar.YEAR, Integer.parseInt(gdate1));
		            
		            
		            
		            
		            Calendar c2 = Calendar.getInstance();

		            c2.setTime(inputFormat.parse(SplitWith1[1]));
		            c2.set(Calendar.DATE, Integer.parseInt(SplitWith1[0]));
		            
					DateFormat dfm2 = new SimpleDateFormat("dd-MM-yy");
					DateFormat rdfm2 = new SimpleDateFormat("YYYY");
					Date Cdfm2=dfm2.parse(SplitWith1[0]+'-'+c2.get(Calendar.MONTH)+'-'+SplitWith1[2]);	
					
		            String gdate2=rdfm2.format(Cdfm2);
		            
		            c2.set(Calendar.YEAR, Integer.parseInt(gdate2));		            
		            
		            
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            
		            String date1=sdf.format(c1.getTime());
		            String date2=sdf.format(c2.getTime());
					
					if(Float.parseFloat(StrVar2[i])>=completed && date2.compareTo(date1)>=0)
					{
						//String updateQry = "UPDATE  activities set completed = IFNULL(NULLIF(completed, '' ), 0) + ?";	
						
						String updateQry = "UPDATE  activities set completed = IFNULL(NULLIF(completed, '' ), 0) ";
						
						
						if(!StringUtils.isEmpty(obj.getProgress_date())) 
						{
							if(completed == 0) {
								updateQry = updateQry + ", actual_start = ?";
							}
							
							
							if((completed+actual) > 0 && scope == (completed+actual)) 
							{
								updateQry = updateQry + ", actual_finish = ? ";						
							}
						}
						
						if(!StringUtils.isEmpty(StrVar[i])) 
						{
							updateQry = updateQry + ", planned_start = ? ";	
						}

						
						if(!StringUtils.isEmpty(StrVar1[i])) 
						{
							updateQry = updateQry + ", planned_finish = ? ";	
						}
						
						/*if(!StringUtils.isEmpty(scope)) 
						{
							updateQry = updateQry + ", scope = ? ";	
						}*/							
						
						updateQry = updateQry + " WHERE activity_id = ? ";
						updateStmt = con.prepareStatement(updateQry);
							
						int k = 1;
						//updateStmt.setString(k++, String.valueOf(actual) );	

						
						if(!StringUtils.isEmpty(obj.getProgress_date())) 
						{	

							
					    	Calendar c4 = Calendar.getInstance();
					    	String[] SplitWith4=obj.getProgress_date().split("-");
							
				            SimpleDateFormat PrFormat = new SimpleDateFormat("MMMM");
				            c4.setTime(PrFormat.parse(SplitWith4[1]));
				            c4.set(Calendar.DATE, Integer.parseInt(SplitWith4[0]));
				            
							DateFormat dfm = new SimpleDateFormat("dd-MM-yy");
							DateFormat rdfm = new SimpleDateFormat("YYYY");
							Date Cdfm=dfm.parse(SplitWith4[0]+'-'+c4.get(Calendar.MONTH)+'-'+SplitWith4[2]);	
							
				            String gdate=rdfm.format(Cdfm);
				            
				            c4.set(Calendar.YEAR, Integer.parseInt(gdate));		            
				            
				            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				            
				            String Prdate=df.format(c4.getTime());						
							
						
						
							if(completed == 0) {
								updateStmt.setString(k++, Prdate );	
							}
							if((completed+actual) > 0 && scope == (completed+actual)) 
							{
								updateStmt.setString(k++, Prdate);						
							}
						}
						
						if(!StringUtils.isEmpty(StrVar[i])) 
						{
							updateStmt.setString(k++, DateParser.parse(date1) );	
						}

						
						if(!StringUtils.isEmpty(StrVar1[i])) 
						{
							updateStmt.setString(k++, DateParser.parse(date2) );	
						}	
						
						
						/*if(!StringUtils.isEmpty(scope)) 
						{
							updateStmt.setString(k++, StrVar2[i] );
						}	*/					
						
						updateStmt.setString(k++,(obj.getActivity_ids()[i]));
						updateStmt.executeUpdate();
						flag=true;
					}
				}
			}
				/*}*/
				
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
			//}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
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

}

