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
			String qry = "						select distinct department,concat(department_name,' / ',u.designation) as department_name " + 
					"					    from p6_activities a " + 
					"					left join structure s on s.structure_id = a.structure_id_fk  " + 
					"					     " + 
					"					    left join p6_activity_progress p on p.p6_activity_id_fk = a.p6_activity_id " + 
					"					     " + 
					"					    inner join contract c on c.contract_id=a.contract_id_fk  " + 
					"					    left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"					     left join department d on d.department=c.department_fk " + 
					" " + 
					"						 where department is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_name = ?  and u.designation=?";
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
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}*/
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
			String qry = "select distinct contract_id,contract_short_name " + 
					"					    from p6_activities a " + 
					"					left join structure s on s.structure_id = a.structure_id_fk  " + 
					"					     " + 
					"					    left join p6_activity_progress p on p.p6_activity_id_fk = a.p6_activity_id " + 
					"					     " + 
					"					    inner join contract c on c.contract_id=a.contract_id_fk  " + 
					"					    left join [user] u on u.user_id=c.hod_user_id_fk " + 
					"					     " + 
					"					     left join department d on d.department=c.department_fk where 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_name = ?  and u.designation=?";
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
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}*/
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
			String qry = "select distinct structure_type_fk,unit,scope,structure_type_completed,target_date_of_completion from (select structure_type_fk,unit,scope,department,hod,sum(cast(structure_type_completed as decimal(10,2))) as structure_type_completed,target_date_of_completion from(select distinct e.structure_type_fk,'%' as unit,100 as Scope,department,hod,contract_id,cast(e.structure_type_completed as decimal(10,2)) as structure_type_completed,   " + 
					"					(select FORMAT(MAX(CAST(target_date_of_completion AS Date)),'dd-MM-yyyy') from executionreporthistory m where m.work_id='"+obj.getWork_id_fk()+"' and m.structure_type_fk=e.structure_type_fk) as target_date_of_completion  " + 
					"					from executionreporthistory e where e.work_id='"+obj.getWork_id_fk()+"') as a  where 0=0  " + 
					" " + 
					"					group by structure_type_fk,unit,scope,target_date_of_completion,department,hod) as a  where 0=0  " ;
					
			
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
			qry= qry+" ORDER by structure_type_fk ";
			
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
			String qry = "select distinct e.strip_chart_structure_id,case when  structure_type_fk='E Earthwork' then unit_fk else '%' end as unit,case when  structure_type_fk='E Earthwork' then sum(cast(Scope as decimal(10,2))) else 100 end as Scope,sum(cast(e.structure_completed as decimal(10,2))) as structure_completed, " + 
					"(select FORMAT(MAX(CAST(target_date_of_completion AS Date)),'dd-MM-yyyy') from executionreporthistory m where m.work_id='"+obj.getWork_id_fk()+"' and m.structure_type_fk=e.structure_type_fk  " + 
					"and m.strip_chart_structure_id=e.strip_chart_structure_id) as target_date_of_completion " + 
					"from executionreporthistory e where e.work_id='"+obj.getWork_id_fk()+"' and structure_type_fk='"+obj.getStructure_type_fk()+"' ";
		
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
			qry=qry+" group by strip_chart_structure_id,structure_type_fk,unit_fk ORDER by e.strip_chart_structure_id";
			
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
			String qry = "select distinct e.component,case when  structure_type_fk='E Earthwork' then unit_fk else '%' end as unit,case when  structure_type_fk='E Earthwork' then cast(Scope as decimal(10,2)) else 100 end as Scope,cast(e.component_completed as decimal(10,2)) as component_completed, " + 
					"(select FORMAT(MAX(CAST(target_date_of_completion AS Date)),'dd-MM-yyyy') from executionreporthistory m where m.work_id='"+obj.getWork_id_fk()+"' and m.structure_type_fk=e.structure_type_fk  " + 
					"and m.strip_chart_structure_id=e.strip_chart_structure_id and m.component=e.component) as target_date_of_completion " + 
					"from executionreporthistory e where e.work_id='"+obj.getWork_id_fk()+"' and structure_type_fk='"+obj.getStructure_type_fk()+"' and e.strip_chart_structure_id='"+obj.getStrip_chart_structure_id()+"' ";
		
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
			qry=qry+" ORDER by e.component";
			
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

