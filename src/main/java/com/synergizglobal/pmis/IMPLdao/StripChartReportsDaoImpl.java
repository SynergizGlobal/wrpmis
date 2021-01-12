package com.synergizglobal.pmis.IMPLdao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StripChartReportsDao;
import com.synergizglobal.pmis.model.StripChartReport;
@Repository
public class StripChartReportsDaoImpl implements StripChartReportsDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<StripChartReport> getWorksListInRiskReport(StripChartReport obj) throws Exception {
		List<StripChartReport> objsList = null;
		try {
			String qry = "select work_id,work_name,work_short_name from `work`";
					
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChartReport> getContractsListInStripChartReport(StripChartReport obj) throws Exception {
		List<StripChartReport> objsList = null;
		try {
			String qry = "select contract_id,contract_name,contract_short_name from contract "
					+ "where work_id_fk is not null" ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<StripChartReport> getStripChartDPRReportData(StripChartReport obj) throws Exception {
		List<StripChartReport> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.0000");
		try {
			/*String qry = "select progress_date,strip_chart_id_fk,completed_scope,strip_chart_id,contract_id_fk,fob_id_fk,component_id_name," + 
					"component,activity_name,structure,scope,completed,contract_name,contract_short_name,work_name,work_short_name,contractor_name " + 
					"from scope_progress sp " + 
					"left outer join strip_chart_general scg on strip_chart_id_fk = strip_chart_id " + 
					"left outer join contract_view cv on scg.contract_id_fk = cv.contract_id " + 
					"where contract_id_fk = ? and progress_date = ?" ;*/
			
			String qry = "select sp.progress_date,sp.strip_chart_id_fk,sp.completed_scope,scg.strip_chart_id,scg.contract_id_fk,scg.fob_id_fk,scg.component_id_name," + 
					"	scg.component,scg.activity_name,scg.structure,scg.scope,scg.completed,cv.contract_name,cv.contract_short_name,cv.work_name,cv.work_short_name,cv.contractor_name," + 
					"    (scg.completed - IFNULL((select sum(completed_scope) " + 
					"    from scope_progress sp1 " + 
					"    left outer join strip_chart_general scg1 on sp1.strip_chart_id_fk = scg1.strip_chart_id " + 
					"	left outer join contract_view cv1 on scg1.contract_id_fk = cv1.contract_id " + 
					"    where scg1.contract_id_fk = ? and sp1.progress_date > ? and sp1.strip_chart_id_fk = sp.strip_chart_id_fk),0)) as cumulative_completed " + 
					"	from scope_progress sp " + 
					"	left outer join strip_chart_general scg on sp.strip_chart_id_fk = scg.strip_chart_id " + 
					"	left outer join contract_view cv on scg.contract_id_fk = cv.contract_id " + 
					"	where scg.contract_id_fk = ? and sp.progress_date = ?";
			
			Object[] pValues = new Object[] {obj.getContract_id(),obj.getReporting_date(),obj.getContract_id(),obj.getReporting_date()};
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
			
			
			for (StripChartReport object : objsList) {
				object.setCumulative_completed(numberFormatter.format(Double.parseDouble(object.getCumulative_completed())));
			}
			
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public StripChartReport getStripChartDPRReportDetails(StripChartReport obj) throws Exception {
		StripChartReport sObj = null;
		try {
			String qry = "select contract_id,work_id_fk,contract_name,contract_short_name,contractor_id_fk, work_name,work_short_name,contractor_name " + 
					"from contract c " + 
					"left outer join work w on work_id_fk = work_id " + 
					"left outer join contractor cr on contractor_id_fk = contractor_id " + 
					"where contract_id = ?" ;
			
			Object[] pValues = new Object[] {obj.getContract_id()};
			
			sObj = jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
					
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sObj;
	}

	@Override
	public Map<StripChartReport, List<StripChartReport>> getStripChartReportData(StripChartReport obj)
			throws Exception {
		Map<StripChartReport, List<StripChartReport>> mapObjsList = new HashMap<StripChartReport, List<StripChartReport>>();
		NumberFormat numberFormatter = new DecimalFormat("#0.0000");
		try {
			String qry = "select strip_chart_id_fk,contract_id_fk,work_id "
					+ "from scope_progress sp " 
					+ "left outer join strip_chart_general scg on strip_chart_id_fk = strip_chart_id  " 
					+ "left outer join contract_view cv on scg.contract_id_fk = cv.contract_id "
					+ "where progress_date = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by contract_id_fk order by contract_id_fk DESC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getReporting_date();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			
			List<StripChartReport> contractsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
			
			qry = "select sp.progress_date,sp.strip_chart_id_fk,sp.completed_scope,scg.strip_chart_id,scg.contract_id_fk,scg.fob_id_fk,scg.component_id_name," + 
					"	scg.component,scg.activity_name,scg.structure,scg.scope,scg.completed,cv.contract_name,cv.contract_short_name,cv.work_name,cv.work_short_name,cv.contractor_name," + 
					"    (scg.completed - IFNULL((select sum(completed_scope) " + 
					"    from scope_progress sp1 " + 
					"    left outer join strip_chart_general scg1 on sp1.strip_chart_id_fk = scg1.strip_chart_id " + 
					"	left outer join contract_view cv1 on scg1.contract_id_fk = cv1.contract_id " + 
					"    where scg1.contract_id_fk = ? and sp1.progress_date > ? and sp1.strip_chart_id_fk = sp.strip_chart_id_fk),0)) as cumulative_completed " + 
					"	from scope_progress sp " + 
					"	left outer join strip_chart_general scg on sp.strip_chart_id_fk = scg.strip_chart_id " + 
					"	left outer join contract_view cv on scg.contract_id_fk = cv.contract_id " + 
					"	where scg.contract_id_fk = ? and sp.progress_date = ?";
			
			String sQry = "select contract_id,work_id_fk,contract_name,contract_short_name,contractor_id_fk, work_name,work_short_name,contractor_name " + 
					"from contract c " + 
					"left outer join work w on work_id_fk = work_id " + 
					"left outer join contractor cr on contractor_id_fk = contractor_id " + 
					"where contract_id = ?" ;
			
			List<StripChartReport> objsList = new ArrayList<StripChartReport>();
			StripChartReport sObj = new StripChartReport();
			
			for (StripChartReport cObj : contractsList) {
				pValues = new Object[] {cObj.getContract_id_fk(),obj.getReporting_date(),cObj.getContract_id_fk(),obj.getReporting_date()};
				
				objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
				
				for (StripChartReport object : objsList) {
					object.setCumulative_completed(numberFormatter.format(Double.parseDouble(object.getCumulative_completed())));
				}
				
				pValues = new Object[] {cObj.getContract_id_fk()};
				
				sObj = jdbcTemplate.queryForObject( sQry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
				
				mapObjsList.put(sObj, objsList);
			}
			
			
					
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return mapObjsList;
	}

}
