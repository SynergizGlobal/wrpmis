package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

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
		try {
			String qry = "select progress_date,strip_chart_id_fk,completed_scope,strip_chart_id,contract_id_fk,fob_id_fk,component_id_name," + 
					"component,activity_name,structure,scope,completed,contract_name,contract_short_name,work_name,work_short_name,contractor_name " + 
					"from scope_progress sp " + 
					"left outer join strip_chart_general scg on strip_chart_id_fk = strip_chart_id " + 
					"left outer join contract_view cv on scg.contract_id_fk = cv.contract_id " + 
					"where contract_id_fk = ? and progress_date = ?" ;
			
			Object[] pValues = new Object[] {obj.getContract_id(),obj.getReporting_date()};
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<StripChartReport>(StripChartReport.class));
					
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

}
