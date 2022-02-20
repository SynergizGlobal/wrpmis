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
			String qry = "select distinct work_id,work_short_name FROM "
					+ "(`activities_actual` `a` "
					+ "LEFT JOIN `contract_details` `c` ON ((`a`.`contract_id_fk` = `c`.`contract_id`))) where work_id in('P04w01') ";
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod = ? or dyhod = ? or "
						+ "contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
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
	public List<StripChart> getDepartmentFilterListInEOR(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select distinct d.department,concat(department_name,' / ',hod) as department_name FROM "
					+ "(`activities_actual` `a` "
					+ "LEFT JOIN `contract_details` `c` ON ((`a`.`contract_id_fk` = `c`.`contract_id`))) left join department d on d.department_name=c.department where work_id in('P04w01') ";
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod = ? or dyhod = ? or "
						+ "contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
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
	public List<StripChart> getContractIdFilterListInEOR(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select distinct contract_id,contract_short_name FROM "
					+ "(`activities_actual` `a` "
					+ "LEFT JOIN `contract_details` `c` ON ((`a`.`contract_id_fk` = `c`.`contract_id`))) where work_id in('P04w01') ";
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod = ? or dyhod = ? or "
						+ "contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
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
	public List<StripChart> getExecutionOverviewReportList(StripChart obj) throws Exception {
		List<StripChart> objsList = null;
		try {
			String qry = "select * from (select distinct contract_id,structure_type_fk,structure as strip_chart_structure_id,'%' as unit_fk,\r\n"
					+ "    '100%' as Scope,work_id_fk as work_id,d.department_name as department,\r\n"
					+ "    ifnull((select DATE_FORMAT(MAX(modified_date),'%d-%b-%y') from activities a1 where a1.structure=a.structure),'') as modified_date,\r\n"
					+ "    ifnull((select remarks from activities a1 where a1.structure=a.structure and a1.remarks is not null limit 1),'') as remarks,u.designation as hod,\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "        ifnull(ROUND( SUM( (`a`.`weightage` * `p`.`completed_scope`) / `a`.`scope`)  / (SELECT \r\n"
					+ "                SUM(`d`.`baseline_weight`)\r\n"
					+ "            FROM\r\n"
					+ "                `activities_structure_department_weight` `d`\r\n"
					+ "            WHERE\r\n"
					+ "                (`d`.`structure` = `a`.`structure`)\r\n"
					+ "            GROUP BY `d`.`structure`)*100,2),0) AS `completed`,\r\n"
					+ "	ifnull(ROUND(100-( SUM( (`a`.`weightage` * `p`.`completed_scope`) / `a`.`scope`)  / (SELECT \r\n"
					+ "                SUM(`d`.`baseline_weight`)\r\n"
					+ "            FROM\r\n"
					+ "                `activities_structure_department_weight` `d`\r\n"
					+ "            WHERE\r\n"
					+ "                (`d`.`structure` = `a`.`structure`)\r\n"
					+ "            GROUP BY `d`.`structure`)*100),2),0) as pending\r\n"
					+ "    \r\n"
					+ "    from activities a\r\n"
					+ "    \r\n"
					+ "    left join activity_progress p on `p`.`activity_id_fk` = `a`.`activity_id`\r\n"
					+ "    \r\n"
					+ "    inner join contract c on c.contract_id=a.contract_id_fk \r\n"
					+ "    left join user u on u.user_id=c.hod_user_id_fk\r\n"
					+ "    \r\n"
					+ "     left join department d on d.department=c.department_fk\r\n"
					+ "    \r\n"
					+ "    where work_id_fk in('P04w01') group by contract_id,structure order by structure_type_fk,structure) as eor where 0=0 ";

			
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

}

