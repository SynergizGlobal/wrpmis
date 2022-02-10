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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mysql.cj.jdbc.CallableStatement;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.NewActivitiesUpdateDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.StripChart;
@Repository
public class NewActivitiesUpdateDaoImpl implements NewActivitiesUpdateDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<StripChart> getNewActivitiesUpdateProjectsList(StripChart obj) throws Exception { 
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
					+ "WHERE a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and structure_type_fk!='FOB' and a.scope <> IFNULL('Completed',0) ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk) "
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getNewActivitiesUpdateWorksList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name ,w.work_short_name "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN ("
					+ "select a.contract_id_fk "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "					
					+ "where a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and structure_type_fk!='FOB' and a.scope <> IFNULL('Completed',0) " ;
					
					int arrSize = 0;
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk) "
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
			throw new Exception(e);
		}
		return objsList;
	}
	
	
 	

	@Override
	public List<StripChart> getNewActivitiesUpdateContractsList(StripChart obj) throws Exception {
		List<StripChart> objsList = new ArrayList<StripChart>();
		List<StripChart> objsList1 = null;
		try {
			/*String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "*/
			
			String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "
					+ "left outer join contract_executive c1 on c1.contract_id_fk = c.contract_id "	
					+ "where a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and structure_type_fk!='FOB' and a.scope <> IFNULL('Completed',0) " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and c1.department_id_fk=? and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+" or contract_id in (select contract_id from contract where contract_id in(select contract_id_fk from structure_contract_responsible_people where structure_id_fk in(select structure_id_fk from pmis.structure_contract_responsible_people where responsible_people_id_fk = ?))) )";
				arrSize++;
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
				
				pValues[i++] = obj.getDepartment_fk();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();

				//objsList1 = getExecutivesList(obj);	
			
			}

			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	
	@Override
	public List<StripChart> getNewActivitiesUpdateStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT s.structure as strip_chart_structure_id_fk "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "WHERE s.structure is not null and (s.component_details != 'OBC' or s.component_details is null) and structure_type_fk!='FOB' and s.structure_type_fk = ? AND s.structure <> '' AND s.contract_id_fk = ? "
					+ "AND (select count(*) from activities WHERE scope <> IFNULL(completed,0) and (component_details != 'OBC' or component_details is null) and structure_type_fk!='FOB' and s.structure_type_fk = ? and contract_id_fk = ? AND structure = s.structure ) > 0 ";
			int arrSize = 4;
			
		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ( "
						+ "structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk)) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			qry = qry + " group by s.structure ";			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getStructure_type_fk();
			pValues[i++] = obj.getContract_id_fk();
			pValues[i++] = obj.getStructure_type_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	@Override
	public List<StripChart> getNewActivitiesUpdateInProgressStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		List<StripChart> objsList1 = null;
		try {
			String qry = "SELECT s.structure as strip_chart_structure_id_fk "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "LEFT JOIN structure f ON f.structure = s.structure "
					+ "WHERE s.structure is not null and (s.component_details != 'OBC' or s.component_details is null) and structure_type_fk!='FOB' and f.work_status_fk='In Progress' AND s.structure <> '' AND s.contract_id_fk = ? "
					+ "AND (select count(*) from activities WHERE scope <> IFNULL(completed,0) and (component_details != 'OBC' or component_details is null) and structure_type_fk!='FOB' and contract_id_fk = ? AND structure = s.structure ) > 0 ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ( "
						+ "structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk)) ";
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
			throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public List<StripChart> getNewActivitiesUpdateLines(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select line as strip_chart_line_id_fk from activities "
					+ "where line is not null and (component_details != 'OBC' or component_details is null) and line <> '' and structure_type_fk!='FOB' ";
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
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getNewActivitiesUpdateSections(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select section as strip_chart_section_name "
					+ "from activities "
					+ "where section is not null and (component_details != 'OBC' or component_details is null) and structure_type_fk!='FOB' ";
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
			throw new Exception(e);
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
					+ "where activity_id = ? and (scv.component_details != 'OBC' or scv.component_details is null) and structure_type_fk <> 'FOB' ";
			
			sObj =  (StripChart) jdbcTemplate.queryForObject( qry, new Object[] {obj.getActivity_id()}, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return sObj;
	}


	@Override
	public List<StripChart> getNewActivitiesUpdateComponentsList(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "select component as strip_chart_component "
					+ "from activities "
					+ "where component is not null and component <> '' and (component_details != 'OBC' or component_details is null) and structure_type_fk!='FOB'  and contract_id_fk = ? and structure = ?";
			
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
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getNewActivitiesUpdateComponentIds(StripChart obj) throws Exception {
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
					+ "where component_id is not null and contract_id_fk = ? and structure_id_fk = ?";*/
			
			String qry = "select distinct component_id as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and (component_details != 'OBC' or component_details is null) and component_id <> '' and structure_type_fk!='FOB' and contract_id_fk = ? and structure = ? and component = ?";
			
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
			throw new Exception(e);
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
			String qry = " select * from (select (case "  
					+" when ((select count(*) from activities s1 where s1.structure_type_fk!='FOB' and (s1.component_details != 'OBC' or s1.component_details is null) and (s1.scope - IFNULL(s1.completed,0)) <> 0 "
					+ " and s1.contract_id_fk = ? and s1.structure = ? and s1.component_id = ? and s1.component = ? AND activity_id=a.activity_id ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s1.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
						qry = qry + " and s1.section = ?";
					}	
					qry = qry + ") = 0) then 'over' "  
					//+" when ((select count(*) from activities where (select DATE_FORMAT(max(planned_finish),'%Y-%m-%d') from activities s2 where "
					//+ " s2.completed <> 0 and s2.contract_id_fk = ? and s2.structure = ? and s2.component_id = ? ";
				//	if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
				//		qry = qry + " and s2.line = ?";
				//	}			
				//	if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
					//	qry = qry + " and s2.section = ?";
					//}	
					//qry = qry + ") < CURDATE() ) > 0) then 'delayed' "  
					+" when ((select count(*) from activities s3 where s3.structure_type_fk!='FOB' and (s3.component_details != 'OBC' or s3.component_details is null) and IFNULL(s3.completed,0) = 0 and scope <> 0 "
					+ "and s3.contract_id_fk = ? and s3.structure = ? and s3.component_id = ? and s3.component = ? AND activity_id=a.activity_id ";
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_line_id_fk())) {
						qry = qry + " and s3.line = ?";
					}			
					if(!StringUtils.isEmpty(sobj) && !StringUtils.isEmpty(sobj.getStrip_chart_section_name())) {
						qry = qry + " and s3.section = ?";
					}	
					qry = qry + ") > 0) then 'not-started'  "  
					+" else 'in-progress' "  
					+" end ) as color " 
					+" from activities a where scope <> 0 and structure_type_fk!='FOB' and (a.component_details != 'OBC' or a.component_details is null) and contract_id_fk = ? and structure = ? and component_id = ? and component = ?) as a order by color ";				
			
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
				int Ncount=0;
				int Icount=0;
				int Ocount=0;				
		        if (rs != null) 
		        {
		            while (rs.next()) 
		            {
						if(rs.getString("color").compareTo("not-started")==0)
						{
							Ncount++;
						}
						if(rs.getString("color").compareTo("in-progress")==0)
						{
							Icount++;
						}
						if(rs.getString("color").compareTo("over")==0)
						{
							Ocount++;
						}	
						if(Ncount>0 && Icount>0 && Ocount==0)
						{
							color="in-progress";
						}
						else if(Ncount>0 && Ocount>0 && Icount==0)
						{
							color="in-progress";
						}
						else if(Ocount>0 && Icount>0 && Ncount==0)
						{
							color="in-progress";
						}
						else if(Ncount==0 && Ocount==0 && Icount>0)
						{
							color="in-progress";
						}
						else if(Icount==0 && Ocount==0 && Ncount>0)
						{
							color="not-started";
						}	
						else if(Ocount==0 && Ncount==0 && Icount>0)
						{
							color="in-progress";
						}	
						else if(Icount==0 && Ncount==0 && Ocount>0)
						{
							color="over";
						}
						
						if(color.compareTo("over")==0)
						{
							color="completed";
						}						
		            }
		        }
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
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
					+ "where activity_id is not null and structure_type_fk!='FOB' and (component_details != 'OBC' or component_details is null)  and scope <> IFNULL(completed,0)  "
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public StripChart getNewActivitiesUpdateDetails(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select activity_id,"
					+ "DATE_FORMAT(actual_start,'%d-%m-%Y') AS actual_start,DATE_FORMAT(actual_finish,'%d-%m-%Y') AS actual_finish,DATE_FORMAT(planned_start,'%d-%m-%Y') AS planned_start,"
					+ "DATE_FORMAT(planned_finish,'%d-%m-%Y') AS planned_finish,"
					+ "component_id as strip_chart_component_id_name,completed as completed,scope as scope,remaining as remaining, units as unit_fk "
					+ "from activities "
					+ "where activity_id is not null and structure_type_fk!='FOB' and (component_details != 'OBC' or component_details is null) and component_id = ? and structure = ? and activity_id = ? ";
			
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
			throw new Exception(e);
		}
		return sObj;
	}

	@Override
	public List<StripChart> getActivitiesfiltersList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id,component_id as strip_chart_component_id_name,component as strip_chart_component,activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name,DATE_FORMAT(planned_start,'%d-%b-%y') AS planned_start "  
					+",DATE_FORMAT(planned_finish,'%d-%b-%y') AS planned_finish,IFNULL(NULLIF(scope, '' ), 0) as scope,IFNULL(NULLIF(completed, '' ), 0) as completed, unit as unit_fk from activities  " 
					+ " where activity_id is not null and (component_details != 'OBC' or component_details is null) and structure_type_fk!='FOB' ";
			
				if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()))
				{
					//qry = qry + " and scope <> completed ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				qry = qry + "and structure_type_fk = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				pValues[i++] = obj.getStructure_type_fk();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	private String getScopeValue(String activity_id) throws Exception
	{
		String Scope="";
		try {
			String qry = "select scope from activities where (component_details != 'OBC' or component_details is null) and activity_id = ?";
			Scope = (String) jdbcTemplate.queryForObject(qry, new Object[] { activity_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Scope;
	}
	
	private String getPlannedStart(String activity_id) throws Exception
	{
		String PlannedStart="";
		try {
			String qry = "select planned_start from activities where (component_details != 'OBC' or component_details is null)  and activity_id = ?";
			PlannedStart = (String) jdbcTemplate.queryForObject(qry, new Object[] { activity_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return PlannedStart;
	}
	
	private String getPlannedFinish(String activity_id) throws Exception
	{
		String PlannedFinish="";
		try {
			String qry = "select planned_finish from activities where (component_details != 'OBC' or component_details is null) and activity_id = ?";
			PlannedFinish = (String) jdbcTemplate.queryForObject(qry, new Object[] { activity_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return PlannedFinish;
	}	
	
	
	@Override
	public boolean insertFOBDailyUpdate(StripChart obj) throws Exception 
	{
		Connection con = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO fobdailyupdate (contract_id_fk,structure,reporting_date,remarks,created_date,created_by_user_id_fk)"  
					+ " VALUES (?,?,?,?,CURRENT_TIMESTAMP,?)";
			stmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			int p = 1;
			
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
			
			stmt.setString(p++,obj.getContract_id_fk());
			stmt.setString(p++,obj.getStrip_chart_structure_id_fk());
			stmt.setString(p++,Prdate);
			stmt.setString(p++,obj.getRemarks());
			stmt.setString(p++,obj.getCreated_by_user_id_fk());
			
			int c = stmt.executeUpdate();
			if (c > 0) {
				flag=true;	
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, null);
		}
		return flag;
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
					+ "(created_by_user_id_fk, remarks, completed_scope, activity_id_fk,progress_date,approval_status_fk,updated_scope,planned_start,planned_finish)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?)";
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
			String[] SplitPlannedStart=null;
			String[] SplitPlannedFinish=null;
			if(obj.getPlanned_start()!=null)
			{
				SplitPlannedStart=obj.getPlanned_start().split(",");
			}
			if(obj.getPlanned_finish()!=null)
			{
				SplitPlannedFinish=obj.getPlanned_finish().split(",");
			}
			String Message="Scope";
			
			for (int i = 0; i < arraySize; i++) 
			{				
			    int k = 1;
			    //if( obj.getActualScopes().length > 0 && !StringUtils.isEmpty(obj.getActualScopes()[i]))
			    if( obj.getActualScopes().length > 0)
			    {
			    	
			    	String Str1=getScopeValue(obj.getActivity_ids()[i]);
			    	String Str2[]=obj.getScope().split(",");
			    	Float Str=Float.parseFloat(Str2[i]);

			    	boolean insertFlag=false;		
			    	if((Str1.compareTo(String.valueOf(Str))!=0) || (obj.getActualScopes()[i]!=null && obj.getActualScopes()[i]!="") || (obj.getPlanned_start()!=null) || (obj.getPlanned_finish()!=null))
			    	{
				    	String Prdate=null;
						if(!StringUtils.isEmpty(obj.getProgress_date())) 
						{	
					    	/*Calendar c3 = Calendar.getInstance();
					    	String[] SplitWith3=obj.getProgress_date().split("-");
					    	
				            SimpleDateFormat PrFormat = new SimpleDateFormat("MMMM");
				            c3.setTime(PrFormat.parse(SplitWith3[1]));
				            c3.set(Calendar.DATE, Integer.parseInt(SplitWith3[0]));
				            
							DateFormat dfm3 = new SimpleDateFormat("dd-MM-yy");	
							DateFormat rdfm3 = new SimpleDateFormat("YYYY");
							Date Cdfm3=dfm3.parse(SplitWith3[0]+'-'+c3.get(Calendar.MONTH)+'-'+SplitWith3[2]);	
							
				            String gdate3=rdfm3.format(Cdfm3);
				            
			            
				            
				            c3.set(Calendar.YEAR, Integer.parseInt(gdate3));		            
				            
				            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");*/
				            
							Prdate=obj.getData_date();
						}
						
						if(Str1.compareTo(String.valueOf(Str))!=0)
						{
							Message="Scope";
						}
			            if(obj.getActualScopes()[i]!=null && obj.getActualScopes()[i]!="")
			            {
			            	Message="Progress";
			            }
			            
			            if(obj.getActualScopes()[i]!=null && obj.getActualScopes()[i]!="" && Str1.compareTo(String.valueOf(Str))!=0)
			            {
			            	Message="Progress";
			            }
			            
					    insertStmt.setString(k++, obj.getCreated_by_user_id_fk());
					    insertStmt.setString(k++, obj.getRemarks());
				    	insertStmt.setString(k++, obj.getActualScopes().length > 0 ?obj.getActualScopes()[i]:null);
					    insertStmt.setString(k++,(obj.getActivity_ids()[i]));
					    insertStmt.setString(k++, Prdate);
					    insertStmt.setString(k++, "Pending");
					    
					    if(obj.getActualScopes().length > 0 && obj.getActualScopes()[i]!=null && obj.getActualScopes()[i]!="")
					    {
					    	insertFlag=true;
					    }
					    
					    if(Str1.compareTo(String.valueOf(Str))!=0)
					    {
					    	insertFlag=true;
						    insertStmt.setString(k++,(SplitScope[i]));
					    }
					    else
					    {
						    insertStmt.setString(k++,(null));
					    }
					    
						  if( !StringUtils.isEmpty(obj.getPlanned_start())) 
						  {
							  if(SplitPlannedStart.length>0)
							  {
								  String StrPS=getPlannedStart(obj.getActivity_ids()[i]);

								  if(i<SplitPlannedStart.length) 
								  {
									  if(!StringUtils.isEmpty(SplitPlannedStart[i])) 
									  {
											  String PStrdate=null;
			
											  Calendar c4 = Calendar.getInstance();
											  String[] SplitWith4=SplitPlannedStart[i].split("-");
			
											  SimpleDateFormat PrFormat = new SimpleDateFormat("MMMM");
											  c4.setTime(PrFormat.parse(SplitWith4[1]));
											  c4.set(Calendar.DATE, Integer.parseInt(SplitWith4[0]));
			
											  DateFormat dfm4 = new SimpleDateFormat("dd-MM-yy");	
											  DateFormat rdfm4 = new SimpleDateFormat("YYYY");
											  Date Cdfm4=dfm4.parse(SplitWith4[0]+'-'+c4.get(Calendar.MONTH)+'-'+SplitWith4[2]);	
			
											  String gdate4=rdfm4.format(Cdfm4);
			
			
			
											  c4.set(Calendar.YEAR, Integer.parseInt(gdate4));		            
			
											  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
											  PStrdate=df.format(c4.getTime());	
											  if(StrPS!=null)
											  {
											  	 if(StrPS.compareTo(String.valueOf(PStrdate))!=0)
												 {	
											  		 	insertFlag=true;
											  		  insertStmt.setString(k++,PStrdate);
												 }
											     else
											     {
												  insertStmt.setString(k++,null);
											     }
											  }
											  else
											  {
												  insertFlag=true;
												  insertStmt.setString(k++,PStrdate);
											  }
									  }
									  else
									  {
										  insertStmt.setString(k++,null);
									  }									  
								  }
								  else
								  {
									  insertStmt.setString(k++,null);
								  }
							  }
							  else
							  {
								  insertStmt.setString(k++,null);
							  }
						  }	
						  else
						  {
							  insertStmt.setString(k++,null);
						  }
						  
						  if( !StringUtils.isEmpty(obj.getPlanned_finish())) 
						  {
							  if(SplitPlannedFinish.length>0)
							  {
								  String StrPF=getPlannedFinish(obj.getActivity_ids()[i]);
								  if(i<SplitPlannedFinish.length) 
								  {
									  
									  if(!StringUtils.isEmpty(SplitPlannedFinish[i])) 
									  {
										  String Pfndate=null;
		
										  Calendar c5 = Calendar.getInstance();
										  String[] SplitWith5=SplitPlannedFinish[i].split("-");
		
										  SimpleDateFormat PrFormat = new SimpleDateFormat("MMMM");
										  c5.setTime(PrFormat.parse(SplitWith5[1]));
										  c5.set(Calendar.DATE, Integer.parseInt(SplitWith5[0]));
		
										  DateFormat dfm5 = new SimpleDateFormat("dd-MM-yy");	
										  DateFormat rdfm5 = new SimpleDateFormat("YYYY");
										  Date Cdfm5=dfm5.parse(SplitWith5[0]+'-'+c5.get(Calendar.MONTH)+'-'+SplitWith5[2]);	
		
										  String gdate5=rdfm5.format(Cdfm5);
		
		
		
										  c5.set(Calendar.YEAR, Integer.parseInt(gdate5));		            
		
										  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
										  Pfndate=df.format(c5.getTime());	
										  if(StrPF!=null)
										  {
										  	  if(StrPF.compareTo(String.valueOf(Pfndate))!=0)
											  {	
										  		  insertFlag=true;
										  		  insertStmt.setString(k++,Pfndate);
											  }
											  else
											  {
												  insertStmt.setString(k++,null);
											  }
										  }
										  else
										  {
											  insertFlag=true;
											  insertStmt.setString(k++,Pfndate);
										  }
									  }
									  else
									  {
										  insertStmt.setString(k++,null);
									  }									  
								  }
								  else
								  {
									  insertStmt.setString(k++,null);
								  }
							  }
							  else
							  {
								  insertStmt.setString(k++,null);
							  }
						  }	
						  else
						  {
							  insertStmt.setString(k++,null);
						  }						  
						  if(insertFlag==true)
						  {
							  insertStmt.addBatch();
						  }
			    	}
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
			
			/*if(CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()))
			{*/			
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
					
					if(obj.getPlanned_start()!=null && obj.getPlanned_finish()!=null)
					{

					String SplitStr=obj.getPlanned_start();
					String[] StrVar=SplitStr.split(",");
					
					String SplitStr1=obj.getPlanned_finish();
					String[] StrVar1=SplitStr1.split(",");	
					
					
					String SplitStr2=obj.getScope();
					String[] StrVar2=SplitStr2.split(",");	
					
					String[] SplitWith=null;
					String[] SplitWith1=null;
					
					if(i >=0 && i < StrVar.length)
					{
							SplitWith=StrVar[i].split("-");
					}
					
					if(i >=0 && i < StrVar1.length)
					{
						
							SplitWith1=StrVar1[i].split("-");
					}

					
		            Calendar c1 = Calendar.getInstance();
		            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
		            
		            if(i >=0 && i < StrVar.length)
					{
		            	if(SplitWith[0].length()>0) 
		            	{
				            c1.setTime(inputFormat.parse(SplitWith[1]));
				            c1.set(Calendar.DATE, Integer.parseInt(SplitWith[0]));
	
							DateFormat dfm1 = new SimpleDateFormat("dd-MM-yy");
							DateFormat rdfm1 = new SimpleDateFormat("YYYY");
							Date Cdfm1=dfm1.parse(SplitWith[0]+'-'+c1.get(Calendar.MONTH)+'-'+SplitWith[2]);	
							
				            String gdate1=rdfm1.format(Cdfm1);
				            
				            c1.set(Calendar.YEAR, Integer.parseInt(gdate1));
		            	}
		            
					}
		            Calendar c2 = Calendar.getInstance();
		            
		            if(i >=0 && i < StrVar1.length)
					{		            
		            	if(SplitWith1[0].length()>0) 
		            	{
				            c2.setTime(inputFormat.parse(SplitWith1[1]));
				            c2.set(Calendar.DATE, Integer.parseInt(SplitWith1[0]));
				            
							DateFormat dfm2 = new SimpleDateFormat("dd-MM-yy");
							DateFormat rdfm2 = new SimpleDateFormat("YYYY");
							Date Cdfm2=dfm2.parse(SplitWith1[0]+'-'+c2.get(Calendar.MONTH)+'-'+SplitWith1[2]);	
							
				            String gdate2=rdfm2.format(Cdfm2);
				            
				            c2.set(Calendar.YEAR, Integer.parseInt(gdate2));
		            	}
					}
		            
		            
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            
		            String date1=sdf.format(c1.getTime());
		            String date2=sdf.format(c2.getTime());
		            
					
		            String Str1=getScopeValue(obj.getActivity_ids()[i]);
		            String Str2[]=obj.getScope().split(",");
		            Float Str=Float.parseFloat(Str2[i]);		            
		            
					if(Float.parseFloat(StrVar2[i])>=completed && date2.compareTo(date1)>=0 && ((Str1.compareTo(String.valueOf(Str))!=0) || ( !StringUtils.isEmpty(obj.getActualScopes()) && obj.getActualScopes().length > 0) ) )
					{
						//String updateQry = "UPDATE  activities set completed = IFNULL(NULLIF(completed, '' ), 0) + ?";	
						
						String updateQry = "UPDATE  activities set modified_by_user_id_fk=?,modified_date=CURRENT_TIMESTAMP,completed = IFNULL(NULLIF(completed, '' ), 0) ";
						
						
						if(!StringUtils.isEmpty(obj.getProgress_date())) 
						{
							if(completed == 0) {
								updateQry = updateQry + ", actual_start = ?";
							}
							
							
							if((completed+actual) > 0 && Str == (completed+actual)) 
							{
								updateQry = updateQry + ", actual_finish = ? ";						
							}
						}
						
						if(StrVar.length>0)
						{
							if(!StringUtils.isEmpty(StrVar[i])) 
							{
								updateQry = updateQry + ", planned_start = ? ";	
							}
						}

						if(StrVar1.length>0)
						{
							if(!StringUtils.isEmpty(StrVar1[i])) 
							{
								updateQry = updateQry + ", planned_finish = ? ";	
							}
						}
						
						/*if(!StringUtils.isEmpty(scope)) 
						{
							updateQry = updateQry + ", scope = ? ";	
						}*/							
						
						updateQry = updateQry + " WHERE activity_id = ? ";
						updateStmt = con.prepareStatement(updateQry);
							
						int k = 1;
						//updateStmt.setString(k++, String.valueOf(actual) );
						updateStmt.setString(k++, obj.getCreated_by_user_id_fk() );	
						

						
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
							if((completed+actual) > 0 && Str == (completed+actual)) 
							{
								updateStmt.setString(k++, Prdate);						
							}
						}
						
						if(StrVar.length>0)
						{
							if(!StringUtils.isEmpty(StrVar[i])) 
							{
								updateStmt.setString(k++, DateParser.parse(date1) );	
							}
						}

						if(StrVar1.length>0)
						{
							if(!StringUtils.isEmpty(StrVar1[i])) 
							{
								updateStmt.setString(k++, DateParser.parse(date2) );	
							}	
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
					else
					{
						String SplitStr2=obj.getScope();
						String[] StrVar2=SplitStr2.split(",");	

			            String Str1=getScopeValue(obj.getActivity_ids()[i]);
			            String Str2[]=obj.getScope().split(",");
			            Float Str=Float.parseFloat(Str2[i]);
					
						if(Float.parseFloat(StrVar2[i])>=completed && ((Str1.compareTo(String.valueOf(Str))!=0) || (obj.getActualScopes()[i]!=null && obj.getActualScopes()[i]!="") ) )
						{
						
							String updateQry = "UPDATE  activities set modified_by_user_id_fk=?,modified_date=CURRENT_TIMESTAMP,completed = IFNULL(NULLIF(completed, '' ), 0) ";
							
							
							if(!StringUtils.isEmpty(obj.getProgress_date())) 
							{
								if(completed == 0) {
									updateQry = updateQry + ", actual_start = ?";
								}
								
								
								if((completed+actual) > 0 && Str == (completed+actual)) 
								{
									updateQry = updateQry + ", actual_finish = ? ";						
								}
							}
				
							
							updateQry = updateQry + " WHERE activity_id = ? ";
							updateStmt = con.prepareStatement(updateQry);
								
							int k = 1;
							updateStmt.setString(k++, obj.getCreated_by_user_id_fk() );	
							
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
								if((completed+actual) > 0 && Str == (completed+actual)) 
								{
									updateStmt.setString(k++, Prdate);						
								}
							}
							updateStmt.setString(k++,(obj.getActivity_ids()[i]));
							updateStmt.executeUpdate();
							flag=true;
						}
					}
				}
			//}
				/*}*/
				
				/********************************************************************************/
				if(!StringUtils.isEmpty(obj.getStrip_chart_structure_id_fk())) {
					String qryUsers ="SELECT dy_hod_user_id_fk "
							+ "FROM structure_contract_responsible_people "
							+ "left join contract on contract_id_fk = contract_id "
							+ "where dy_hod_user_id_fk is not null and structure_id_fk = ? group by dy_hod_user_id_fk";
					List<String> users = jdbcTemplate.queryForList( qryUsers,new Object[]{obj.getStrip_chart_structure_id_fk()}, String.class);	
					if(!StringUtils.isEmpty(users) && users.size() > 0) {
						NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
						String userIds[]  = new String[users.size()];	
						userIds = users.toArray(userIds);
						String messageType = "Activity Progress";
						String redirect_url = "/progress-approval-page";
						
						String qryFOBName ="SELECT structure_name FROM structure where structure = ? ";
						String fob_name = jdbcTemplate.queryForObject( qryFOBName,new Object[]{obj.getStrip_chart_structure_id_fk()}, String.class);	
						if(StringUtils.isEmpty(fob_name)) {
							fob_name = obj.getStrip_chart_structure_id_fk();
						}
						String message = Message+" Update for Structure "+fob_name+". Pending approval.";
						 
						Messages msgObj = new Messages();
						msgObj.setUser_ids(userIds);
						msgObj.setMessage_type(messageType);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage(message);
						messagesDao.addMessages(msgObj,template);
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Execution &  Monitoring");
				formHistory.setForm_name("New Update Activities");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details(insertCount.length + " activities updated for "+obj.getStrip_chart_structure_id_fk());
				formHistory.setWork(obj.getWork_id_fk());
				formHistory.setContract(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRemarks())) 
				{
					boolean FOBDailyUpdate_flag = insertFOBDailyUpdate(obj);
					
				}
				flag=true;
			
				/********************************************************************************/	
			//}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
		}	
		return flag;
	}
	
	private List<String> getDyHodsOfActivity(String structure) throws Exception {
		List<String>  dy_hods = null;
		try {
			String qryUsers ="SELECT c.dy_hod_user_id_fk "
					+ "FROM structure_contract_responsible_people s1 "
					+ "left join structure s on s.structure_id = s1.structure_id_fk "
					+ "left join contract c on c.contract_id = s1.contract_id_fk "
					+ "where c.dy_hod_user_id_fk is not null and structure = ? group by dy_hod_user_id_fk";
			dy_hods = jdbcTemplate.queryForList( qryUsers,new Object[]{structure}, String.class);
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return dy_hods;
	}

	@Override
	public List<StripChart> getStructureTypesInActivitiesUpdate(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT distinct structure_type_fk as structure_type "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "WHERE s.structure is not null and structure_type_fk!='FOB' and (s.component_details != 'OBC' or s.component_details is null) AND s.structure <> '' AND s.contract_id_fk = ? ";
			int arrSize = 1;
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id_fk();
		
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public List<StripChart> getStructureTypesInDeleteActivities(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT distinct structure_type_fk as structure_type "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "WHERE s.structure is not null and (s.component_details != 'OBC' or s.component_details is null) AND s.structure <> '' AND s.contract_id_fk = ? ";
			int arrSize = 1;
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getContract_id_fk();
		
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public StripChart getNewAcivitiesUpdateData(StripChart obj) throws Exception {
		StripChart sObj = null;
		try {
			String qry = "select a.activity_id,a.contract_id_fk AS contract_id,a.structure AS strip_chart_structure_id,a.component_id AS strip_chart_component_id,"
					+ "a.component AS strip_chart_component,a.activity_id AS strip_chart_activity_id,a.activity_name AS strip_chart_activity_name,"
					+ "a.line AS strip_chart_line,a.structure_type_fk AS structure_type,a.section AS strip_chart_section_id,"
					+ "c.work_id_fk as work_id,c.contract_name,c.contract_short_name,w.project_id_fk as project_id "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "where (a.component_details != 'OBC' or a.component_details is null)  and activity_id = ? ";

			sObj = (StripChart) jdbcTemplate.queryForObject(qry, new Object[] { obj.getActivity_id() },
					new BeanPropertyRowMapper<StripChart>(StripChart.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return sObj;
	}
	
	@Override
	public List<StripChart> getProjectsList(StripChart obj) throws Exception { 
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
					+ "WHERE a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and a.scope <> IFNULL('Completed',0) ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk) "
						+ ") group by a.contract_id_fk ORDER BY a.contract_id_fk ASC "
						+ ")";
				
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
				
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
			
			}
	
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getWorksList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select c.work_id_fk,w.work_id,w.work_name ,w.work_short_name "
					+ "from contract c "
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "WHERE c.contract_id IN ("
					+ "select a.contract_id_fk "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "					
					+ "where a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and a.scope <> IFNULL('Completed',0) " ;
					
					int arrSize = 0;
					if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
								+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk) "
								+ ") group by a.contract_id_fk ORDER BY a.contract_id_fk ASC "
								+ ")";
						
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
			throw new Exception(e);
		}
		return objsList;
	}
	
	
 	

	@Override
	public List<StripChart> getContractsList(StripChart obj) throws Exception {
		List<StripChart> objsList = new ArrayList<StripChart>();
		List<StripChart> objsList1 = null;
		try {
			/*String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "*/
			
			String qry = "select distinct a.contract_id_fk as contract_id,c.work_id_fk,c.contract_name,c.contract_short_name "
					+ "from activities a "
					+ "left outer join contract c on a.contract_id_fk = c.contract_id "
					+ "left outer join contract_executive c1 on c1.contract_id_fk = c.contract_id "	
					+ "where a.contract_id_fk is not null and (a.component_details != 'OBC' or a.component_details is null) and a.scope <> IFNULL('Completed',0) " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code()) &&  (!CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) && !CommonConstants.USER_TYPE_DYHOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and c1.department_id_fk=? and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+" or contract_id in (select contract_id from contract where contract_id in(select contract_id_fk from structure_contract_responsible_people where structure_id_fk in(select structure_id_fk from pmis.structure_contract_responsible_people where responsible_people_id_fk = ?))) )";
			
				qry = qry + " and c1.department_id_fk=? and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? "
						+" or contract_id in (select contract_id from contract where contract_id in(select contract_id_fk from fob_contract_responsible_people where fob_id_fk in(select fob_id_fk from pmis.fob_contract_responsible_people where responsible_people_id_fk = ?))) )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;	
				
				arrSize++;
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
				
				pValues[i++] = obj.getDepartment_fk();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				
				pValues[i++] = obj.getDepartment_fk();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				

				//objsList1 = getExecutivesList(obj);	
			
			}

			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}	
	
	
	
	@Override
	public List<StripChart> getDeleteActivitiesfiltersList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select activity_id,component_id as strip_chart_component_id_name,component as strip_chart_component,activity_id as strip_chart_activity_id,activity_name as strip_chart_activity_name,DATE_FORMAT(planned_start,'%d-%b-%y') AS planned_start "  
					+",DATE_FORMAT(planned_finish,'%d-%b-%y') AS planned_finish,IFNULL(NULLIF(scope, '' ), 0) as scope,IFNULL(NULLIF(completed, '' ), 0) as completed, unit as unit_fk from activities  " 
					+ " where activity_id is not null and (component_details != 'OBC' or component_details is null)  ";
			
		
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				qry = qry + "and structure_type_fk = ?";
				arrSize++;
			}			
			qry = qry + " group by activity_id ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())){
				pValues[i++] = obj.getStructure_type_fk();
			}			
			
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getDeleteActivitiesStructures(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT s.structure as strip_chart_structure_id_fk "
					+ "FROM activities s "
					+ "LEFT JOIN contract c ON c.contract_id = s.contract_id_fk "
					+ "WHERE s.structure is not null and (s.component_details != 'OBC' or s.component_details is null) and s.structure_type_fk = ? AND s.structure <> '' AND s.contract_id_fk = ? "
					+ "AND (select count(*) from activities WHERE scope <> IFNULL(completed,0) and (component_details != 'OBC' or component_details is null) and s.structure_type_fk = ? and contract_id_fk = ? AND structure = s.structure ) > 0 ";
			int arrSize = 4;
			
		
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ( "
						+ "structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk)) ";

				qry = qry + " and ( "
						+ "structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by fob_id_fk) "
						+ "or structure in (select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ? group by fob_id_fk)) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			qry = qry + " group by s.structure ";			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getStructure_type_fk();
			pValues[i++] = obj.getContract_id_fk();
			pValues[i++] = obj.getStructure_type_fk();
			pValues[i++] = obj.getContract_id_fk();
			
			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
				
			}	
		
			objsList = jdbcTemplate.query( qry, pValues ,new BeanPropertyRowMapper<StripChart>(StripChart.class));			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getDeleteActivitiesComponentsList(StripChart obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<StripChart> objsList = new ArrayList<StripChart>();
		StripChart sobj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "select component as strip_chart_component "
					+ "from activities "
					+ "where component is not null and (component_details != 'OBC' or component_details is null) and component <> '' and contract_id_fk = ? and structure = ?";
			
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
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}

	@Override
	public List<StripChart> getDeleteActivitiesComponentIds(StripChart obj) throws Exception {
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
					+ "where component_id is not null and contract_id_fk = ? and structure_id_fk = ?";*/
			
			String qry = "select distinct component_id as strip_chart_component_id_name,"
					+ "component as strip_chart_component "
					+ "from activities "
					+ "where component_id is not null and (component_details != 'OBC' or component_details is null) and component_id <> '' and contract_id_fk = ? and structure = ? and component = ?";
			
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
				sobj.setStrip_chart_component_id(resultSet.getString("strip_chart_component_id_name"));
				sobj.setStrip_chart_component(resultSet.getString("strip_chart_component"));
				
				obj.setStrip_chart_component_id(sobj.getStrip_chart_component_id());
				sobj.setComponent_id_color(getComponentIdColor(obj,connection));
				objsList.add(sobj);
			}
						
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}

	@Override
	public boolean deleteAcivitiesBulk(StripChart obj) throws Exception {
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		ResultSet resultSet = null;
		try{
				String concat="[";
				for (int i = 0; i < obj.getActivity_ids().length; i++)
				{
					if(obj.getIds()[i].compareTo("1")==0)
					{
						concat=concat+obj.getActivity_ids()[i]+",";
					}
				}
				concat=concat.substring(0, concat.length() - 1);  
				concat=concat+"]";
				connection = dataSource.getConnection();		
				statement = connection.prepareCall("{call deleteActivities(?)}");
				statement.setString(1, concat);

				boolean hadResults = statement.execute();
				if(hadResults)
				{
					return true;
				}
		}catch(Exception e){ 
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return false;				
	}	

}

