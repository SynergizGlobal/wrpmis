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
import com.synergizglobal.pmis.Idao.ExecutionOverviewReportDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.StripChart;
@Repository
public class ExecutionOverviewReportDaoImpl implements ExecutionOverviewReportDao{
	
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
	public List<StripChart> getWorksFilterListInEOR(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "SELECT DISTINCT c.work_id AS work_id,c.work_short_name FROM CONTRACT_DETAILS c WHERE 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department = ?  and c.hod=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod = ? or dyhod = ? or "
						+ "contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
				arrSize++;
			}*/
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getDepartmentFilterListInEOR(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "						select distinct d.department_name as department,concat(department_name,' / ',u.designation) as department_name " + 
					"					    from activities_scurve s  " + 
					 
					"left join (select distinct structure_type,sum(isnull(contract_structure_type_per,0))*100 as completed " + 
					"from activities_scurve s where work_id='"+obj.getWork_id_fk()+"' and structure_type is not null and category='actual' " + 
					 
					"group by structure_type) as stp on stp.structure_type=s.structure_type " + 
					 
					 
					"inner join contract c on c.contract_id=s.contract_id  " + 
					"left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"left join department d on d.department=c.department_fk " + 
					 
					 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and s.structure_type is not null and d.department is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_name = ?  and u.designation=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getContractIdFilterListInEOR(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select distinct c.contract_id,c.contract_short_name " + 
					"					    from activities_scurve s  " + 
					 
					"left join (select distinct structure_type,sum(isnull(contract_structure_type_per,0))*100 as completed " + 
					"from activities_scurve s where work_id='"+obj.getWork_id_fk()+"' and structure_type is not null and category='actual' " + 
					 
					"group by structure_type) as stp on stp.structure_type=s.structure_type " + 
					 
					 
					"inner join contract c on c.contract_id=s.contract_id  " + 
					"left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"left join department d on d.department=c.department_fk " + 
					 
					 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and s.structure_type is not null and d.department is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_name = ?  and u.designation=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<StripChart> getExecutionOverviewReportList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select * from executionreporthistory where 0=0 ";

			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ?  and hod=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getStructureTypesbyWorkId(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			
			String Concat="";
			String JoinQry="";
			String NJoinQry="";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				Concat = Concat + " and d.department_name = '"+ Str[0].trim()+"'  and u.designation='"+ Str[1].trim()+"' ";
				JoinQry=JoinQry +"					    left join p6_activity_progress p on p.p6_activity_id_fk = a.p6_activity_id inner join contract c on c.contract_id=a.contract_id_fk  " + 
						"					    left join [user] u on u.user_id=c.hod_user_id_fk left join department d on d.department=c.department_fk ";

			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				Concat = Concat + " and c.contract_id ='"+obj.getContract_id_fk()+"' ";
				NJoinQry = NJoinQry + " and contract_id ='"+obj.getContract_id_fk()+"' ";
			}			
			
			String qry = "select distinct s.structure_type as structure_type_fk, " + 
					"'%' as unit,100 as scope,round(isnull(completed,0),2) as structure_type_completed, " + 
					 
					"(select FORMAT(MAX(CAST(finish AS Date)),'dd-MM-yyyy') from p6_activities a " + 
					 
					"inner join structure st on st.structure_id=a.structure_id_fk  "+JoinQry+"" + 
					 
					"where st.structure_type_fk=s.structure_type and work_id='"+obj.getWork_id_fk()+"' "+Concat+") as target_date_of_completion " + 
					 
					"from activities_scurve s  " + 
					 
					"left join (select distinct structure_type,sum(isnull(contract_structure_type_per,0))*100 as completed " + 
					"from activities_scurve s where work_id='"+obj.getWork_id_fk()+"' and structure_type is not null "+NJoinQry+" and category='actual' " + 
					 
					"group by structure_type) as stp on stp.structure_type=s.structure_type " + 
					 
					 
					"inner join contract c on c.contract_id=s.contract_id  " + 
					"left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"left join department d on d.department=c.department_fk " + 
					 
					 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and s.structure_type is not null and d.department is not null  " + 
					 
					"  " ;
					
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and d.department_name = ?  and u.designation=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			qry= qry+" order by s.structure_type ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getStructuresByWorkId(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			
			
			String Concat="";
			String JoinQry="";
			String NJoinQry="";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				Concat = Concat + " and d.department_name = '"+ Str[0].trim()+"'  and u.designation='"+ Str[1].trim()+"' ";
				JoinQry=JoinQry +"					    left join p6_activity_progress p on p.p6_activity_id_fk = a.p6_activity_id inner join contract c on c.contract_id=a.contract_id_fk  " + 
						"					    left join [user] u on u.user_id=c.hod_user_id_fk left join department d on d.department=c.department_fk ";

			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				Concat = Concat + " and c.contract_id ='"+obj.getContract_id_fk()+"' ";
				NJoinQry = NJoinQry + " and contract_id ='"+obj.getContract_id_fk()+"' ";
			}				
			
			
			String qry = "select distinct s.structure as strip_chart_structure_id, " + 
					"'%' as unit,100 as scope,round(isnull(completed,0),2) as structure_completed, " + 
					 
					"(select FORMAT(MAX(CAST(finish AS Date)),'dd-MM-yyyy') from p6_activities a " + 
					 
					"inner join structure st on st.structure_id=a.structure_id_fk "+JoinQry+"" + 
					 
					"where st.structure_type_fk=s.structure_type and st.structure=s.structure  and work_id='"+obj.getWork_id_fk()+"' "+Concat+" and s.structure_type='"+obj.getStructure_type_fk()+"') as target_date_of_completion " + 
					 
					"from activities_scurve s  " + 
					 
					"left join (select distinct structure_type,structure,sum(isnull(structure_per,0))*100 as completed " + 
					"from activities_scurve s where work_id='"+obj.getWork_id_fk()+"' "+NJoinQry+" and s.structure_type='"+obj.getStructure_type_fk()+"' and structure_type is not null and category='actual' " + 
					 
					"group by structure_type,structure) as stp on stp.structure_type=s.structure_type and stp.structure=s.structure " + 
					 
					 
					"inner join contract c on c.contract_id=s.contract_id  " + 
					"left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"left join department d on d.department=c.department_fk " + 
					 
					 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and s.structure_type is not null and s.structure_type='"+obj.getStructure_type_fk()+"' and d.department is not null  " + 
					 
					"   ";
		
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and d.department_name = ?  and u.designation=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			qry=qry+" order by s.structure ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getComponentsByWorkId(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			
			String Concat="";
			String JoinQry="";
			String NJoinQry="";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				Concat = Concat + " and d.department_name = '"+ Str[0].trim()+"'  and u.designation='"+ Str[1].trim()+"' ";
				JoinQry=JoinQry +" ";

			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				Concat = Concat + " and c.contract_id ='"+obj.getContract_id_fk()+"' ";
				NJoinQry = NJoinQry + " and contract_id ='"+obj.getContract_id_fk()+"' ";
			}				
			
			
			
			String qry = "select distinct s.component as component, " + 
					"case when s.structure_type='Formation' or  s.structure_type='Flyover' or  s.structure_type='Important Bridges' then (select top 1 unit from p6_activities a1 " + 
					 
					"left join structure st1 on st1.structure_id=a1.structure_id_fk "+JoinQry+"" + 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and st1.structure_type_fk=s.structure_type and st1.structure=s.structure and a1.component=s.component and st1.structure_type_fk is not null and a1.contract_id_fk=s.contract_id and unit is not null and st1.structure_type_fk='"+obj.getStructure_type_fk()+"' and st1.structure='"+obj.getStrip_chart_structure_id()+"') " + 
					 
					"else '%' end as unit, " + 
					 
					 
					 
					"case when s.structure_type='Formation' or  s.structure_type='Flyover' or  s.structure_type='Important Bridges' then (select sum(isnull(scope,0)) from p6_activities a1 " + 
					 
					"left join structure st1 on st1.structure_id=a1.structure_id_fk "+JoinQry+"" + 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and st1.structure_type_fk=s.structure_type and st1.structure=s.structure and a1.component=s.component and st1.structure_type_fk is not null) " + 
					 
					"else 100 end " + 
					 
					 
					"as scope " + 
					 
					 
					",round(isnull(completed,0),2) as component_completed, " + 
					 
					"(select FORMAT(MAX(CAST(finish AS Date)),'dd-MM-yyyy') from p6_activities a1 " + 
					 
					"left join structure st1 on st1.structure_id=a1.structure_id_fk "+JoinQry+"" + 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and st1.structure_type_fk=s.structure_type and st1.structure=s.structure and a1.component=s.component and st1.structure_type_fk='"+obj.getStructure_type_fk()+"' and st1.structure='"+obj.getStrip_chart_structure_id()+"') as target_date_of_completion " + 
					 
					"from component_scurve s  " + 
					 
					"left join (select distinct structure_type,structure,component,case when s.structure_type!='Formation' and  s.structure_type!='Flyover' and  s.structure_type!='Important Bridges' then sum(isnull(component_per,0))*100  " + 
					 
					"else (select sum(isnull(completed,0)) from p6_activities a1 " + 
					 
					"left join structure st1 on st1.structure_id=a1.structure_id_fk "+JoinQry+"" + 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and st1.structure_type_fk=s.structure_type and st1.structure=s.structure and a1.component=s.component ) end " + 
					 
					 
					"as completed " + 
					 
					 
					"from component_scurve s where work_id='"+obj.getWork_id_fk()+"' and structure_type is not null and category='actual' and structure_type='"+obj.getStructure_type_fk()+"' and structure='"+obj.getStrip_chart_structure_id()+"'" + 
					 
					"group by work_id,structure_type,structure,component,contract_id) as stp on stp.structure_type=s.structure_type and stp.structure=s.structure and stp.component=s.component " + 
					 
					 
					"inner join contract c on c.contract_id=s.contract_id  " + 
					"left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"left join department d on d.department=c.department_fk " + 
					 
					 
					 
					"where work_id='"+obj.getWork_id_fk()+"' and s.structure_type is not null and s.structure_type='"+obj.getStructure_type_fk()+"' and s.structure='"+obj.getStrip_chart_structure_id()+"'  " + 
					 
					 
					 
					" ";
		
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and d.department_name = ?  and u.designation=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			qry=qry+" ORDER by s.component";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getActivitiesByWorkId(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select distinct e.p6_activity_name as activity_name,case when  structure_type_fk='E Earthwork' then unit_fk else '%' end as unit,case when  structure_type_fk='E Earthwork' then cast(Scope as decimal(10,2)) else 100 end as Scope,cast(e.structure_type_completed as decimal(10,2)) as structure_type_completed, " + 
					"(select FORMAT(MAX(CAST(target_date_of_completion AS Date)),'dd-MM-yyyy') from executionreporthistory m where m.work_id='"+obj.getWork_id_fk()+"' and m.structure_type_fk=e.structure_type_fk  " + 
					"and m.strip_chart_structure_id=e.strip_chart_structure_id and m.component=e.component and m.component_id=e.component_id and m.p6_activity_name=e.p6_activity_name) as target_date_of_completion " + 
					"from executionreporthistory e where e.work_id='"+obj.getWork_id_fk()+"' and structure_type_fk='"+obj.getStructure_type_fk()+"' and e.strip_chart_structure_id='"+obj.getStrip_chart_structure_id()+"' and component='"+obj.getComponent()+"' and component_id='"+obj.getComponent_id()+"' ";
		
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ?  and hod=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id = ? ";
				arrSize++;
			}
			qry=qry+" ORDER by e.p6_activity_name";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<StripChart> getComponentIDsByWorkId(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select distinct e.component_id as component_id,case when  structure_type_fk='E Earthwork' then unit_fk else '%' end as unit,case when  structure_type_fk='E Earthwork' then cast(Scope as decimal(10,2)) else 100 end as Scope,cast(e.structure_type_completed as decimal(10,2)) as structure_type_completed, " + 
					"(select FORMAT(MAX(CAST(target_date_of_completion AS Date)),'dd-MM-yyyy') from executionreporthistory m where m.work_id='"+obj.getWork_id_fk()+"' and m.structure_type_fk=e.structure_type_fk  " + 
					"and m.strip_chart_structure_id=e.strip_chart_structure_id and m.component=e.component and m.component_id=e.component_id) as target_date_of_completion " + 
					"from executionreporthistory e where e.work_id='"+obj.getWork_id_fk()+"' and structure_type_fk='"+obj.getStructure_type_fk()+"' and e.strip_chart_structure_id='"+obj.getStrip_chart_structure_id()+"' and component='"+obj.getComponent()+"' ";
		
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ?  and hod=?";
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id = ? ";
				arrSize++;
			}
			qry=qry+" ORDER by e.component_id";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				String Str[]=obj.getDepartment_fk().split("/");
				pValues[i++] = Str[0].trim();
				pValues[i++] = Str[1].trim();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChart>(StripChart.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

}

