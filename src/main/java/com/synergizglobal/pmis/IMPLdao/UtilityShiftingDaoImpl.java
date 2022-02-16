package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.UtilityShiftingDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.Safety;

@Repository
public class UtilityShiftingDaoImpl implements UtilityShiftingDao {
	public static Logger logger = Logger.getLogger(UtilityShiftingDaoImpl.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<UtilityShifting> getWorksListFilter(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,w.work_short_name " + "from utility_shifting i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on i.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where i.work_id_fk is not null and i.work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY i.work_id_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public int getTotalRecords(UtilityShifting obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = "SELECT count(*) as total_records from utility_shifting s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON s.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where utility_shifting_id is not null " ;
			int arrSize = 0;
			
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				qry = qry + " and utility_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				qry = qry + " and utility_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				qry = qry + " and shifting_status_fk=?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (utility_shifting_id like ? or utility_description like ? or utility_type_fk like ? or utility_category_fk like ? "
						+ "or owner_name like ? or execution_agency_fk like ? or shifting_status_fk like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}				

			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				pValues[i++] = obj.getUtility_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				pValues[i++] = obj.getUtility_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				pValues[i++] = obj.getShifting_status_fk();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}	
	
	@Override
	public List<UtilityShifting> getUtilityShiftingList(UtilityShifting obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT *,s.modified_by,DATE_FORMAT(s.modified_date,'%d-%m-%Y') as modified_date "
					+ "from utility_shifting s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON s.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where utility_shifting_id is not null " ;
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				qry = qry + " and utility_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				qry = qry + " and utility_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				qry = qry + " and shifting_status_fk =?";
				arrSize++;
			}
	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (utility_shifting_id like ? or utility_description like ? or utility_type_fk like ? or utility_category_fk like ? "
						+ "or owner_name like ? or execution_agency_fk like ? or shifting_status_fk like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " order by utility_shifting_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}			
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				pValues[i++] = obj.getUtility_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				pValues[i++] = obj.getUtility_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				pValues[i++] = obj.getShifting_status_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	


	@Override
	public List<UtilityShifting> getLocationListFilter(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT location_name " + "from utility_shifting i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on i.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where i.work_id_fk is not null and i.work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY location_name ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getUtilityCategoryListFilter(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT utility_category_fk " + "from utility_shifting i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on i.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where i.work_id_fk is not null and i.work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY utility_category_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getUtilityTypeListFilter(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT utility_type_fk " + "from utility_shifting i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on i.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where i.work_id_fk is not null and i.work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY utility_type_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getUtilityStatusListFilter(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT shifting_status_fk " + "from utility_shifting i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on i.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where i.work_id_fk is not null and i.work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY shifting_status_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getProjectsListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getWorkListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = new ArrayList<UtilityShifting>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getContractsListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry ="select distinct c.contract_id as contract_id_fk,c.hod_user_id_fk,c.contract_name,c.contract_short_name,c.work_id_fk "
					+ "from contract c "
					+ "left join contract_executive c1 on c1.contract_id_fk = c.contract_id "
					+ "LEFT JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and i.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and c1.department_id_fk = ? and u.department_fk = ?";
					arrSize++;
					arrSize++;
				}
			}			
			qry = qry + " order by c.contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
					pValues[i++] = obj.getDepartment_fk();
				
				}
			}			
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getUtilityTypeList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT utility_type as utility_type_fk FROM utility_type order by utility_type";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getUtilityCategoryList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT utility_category as utility_category_fk FROM utility_category order by utility_category";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getUtilityExecutionAgencyList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT execution_agency as execution_agency_fk FROM utility_execution_agency order by execution_agency";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getImpactedContractList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT execution_agency as execution_agency_fk FROM utility_execution_agency order by execution_agency";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getRequirementStageList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT requirement_stage as requirement_stage_fk FROM utility_requirement_stage order by requirement_stage";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getUnitListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT unit as unit_fk FROM unit order by unit";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<UtilityShifting> getStatusListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT status as shifting_status_fk FROM utility_status order by status";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	private int getMaxCount() throws Exception
	{
		int Count=0;
		try {
			String qry = "select count(*) from utility_shifting";
			Count = (int) jdbcTemplate.queryForObject(qry, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Count;
	}	


	@Override
	public boolean addUtilityShifting(UtilityShifting obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String utility_shifting_id = null;		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			int cnt=getMaxCount()+1;
			int length = String.valueOf(cnt).length();
			String Concat="";
			if(length==1)
			{
				Concat="0"+cnt;
			}
			else
			{
				Concat=""+cnt;
			}
			String USID="VD-US-"+Concat;
			String qry = "INSERT INTO utility_shifting"
					+ "(utility_shifting_id, work_id_fk, identification, location_name, reference_number, utility_description, utility_type_fk, utility_category_fk, owner_name, "
					+ "execution_agency_fk, contract_id_fk, "
					+ "start_date, scope, completed, shifting_status_fk, shifting_completion_date, remarks, latitude,  impacted_contract_id_fk, requirement_stage_fk, "
					+ "planned_completion_date, unit_fk) "
					+ "VALUES "
					+ "('"+USID+"',:work_id_fk,:identification,:location_name,:reference_number,:utility_description,"
							+ ":utility_type_fk,"
							+ ":utility_category_fk,:owner_name,"
							+ ":execution_agency_fk,"
							+ ":contract_id_fk,:start_date,:scope,:completed,:shifting_status_fk,"
							+ ":shifting_completion_date,:remarks,:latitude,"
							+ ":impacted_contract_id_fk,:requirement_stage_fk,:planned_completion_date,:unit_fk"
							+ ")";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				utility_shifting_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setUtility_shifting_id(utility_shifting_id);
				flag = true;
				if(flag) 
				{
					if(!StringUtils.isEmpty(obj.getProgress_dates()) && obj.getProgress_dates().length > 0) {
						obj.setProgress_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_dates()));
					}
					if(!StringUtils.isEmpty(obj.getProgress_of_works()) && obj.getProgress_of_works().length > 0) {
						obj.setProgress_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_of_works()));
					}
					
					String[] progressDates = obj.getProgress_dates();
					String[] progressOfWorks = obj.getProgress_of_works();					
					
					String insertQry = "INSERT INTO utility_shifting_progress"
							+ "(progress_date, progress_of_work, utility_shifting_id)"
							+ "VALUES"
							+ "(?,?,?)";
					
					int[] counts = jdbcTemplate.batchUpdate(insertQry,
				            new BatchPreparedStatementSetter() {			                 
				                @Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {	
				                	int k = 1;
									ps.setString(k++, progressDates.length > 0 ?DateParser.parse(progressDates[i]):null);
									ps.setString(k++, progressOfWorks.length > 0 ?progressOfWorks[i]:null);
									ps.setString(k++, obj.getUtility_shifting_id());
				                }
				                @Override  
				                public int getBatchSize() {		                	
				                	int arraySize = 0;
				    				if(!StringUtils.isEmpty(obj.getProgress_dates()) && obj.getProgress_dates().length > 0) {
				    					obj.setProgress_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_dates()));
				    					if(arraySize < obj.getProgress_dates().length) {
				    						arraySize = obj.getProgress_dates().length;
				    					}
				    				}
				    				if(!StringUtils.isEmpty(obj.getProgress_of_works()) && obj.getProgress_of_works().length > 0) {
				    					obj.setProgress_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_of_works()));
				    					if(arraySize < obj.getProgress_of_works().length) {
				    						arraySize = obj.getProgress_of_works().length;
				    					}
				    				}
				                    return arraySize;
				                }
				            });	
			
					
					
					if((!StringUtils.isEmpty(obj.getUtilityShiftingFiles()) && obj.getUtilityShiftingFiles().size() > 0) || (obj.getAttachment_file_types().length>0 && obj.getAttachmentNames().length>0)) 
					{
						
						String fileQry = "INSERT INTO utility_shifting_files (name,attachment,utility_shifting_id,utility_shifting_file_type_fk)VALUES(:name,:attachment,:utility_shifting_id,:utility_shifting_file_type)";
						
						List<MultipartFile> issueFiles = obj.getUtilityShiftingFiles();

						String[] Attachmentfiletypes = obj.getAttachment_file_types();
						String[] AttachmentNames = obj.getAttachmentNames();
						
						int m=0;
						for (MultipartFile multipartFile : issueFiles) {
							if (null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants2.UTILITY_SHIFTING_FILE_SAVING_PATH;
								String fileName = multipartFile.getOriginalFilename();
								DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
								String fileName_new = "UtilityShifting-"+obj.getUtility_shifting_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
								
								UtilityShifting fileObj = new UtilityShifting();
								fileObj.setName(AttachmentNames[m]);
								fileObj.setAttachment(fileName_new);
								fileObj.setUtility_shifting_id(obj.getUtility_shifting_id());
								fileObj.setUtility_shifting_file_type(Attachmentfiletypes[m]);
								paramSource = new BeanPropertySqlParameterSource(fileObj);	
								template.update(fileQry, paramSource);
							}
							m++;
						}
					}
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Others");
					formHistory.setForm_name("Add Utility Shifting");
					formHistory.setForm_action_type("Add");
					formHistory.setForm_details("Utility Shifting "+USID + " Added");
					formHistory.setWork(obj.getWork_id_fk());
					formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/

					
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}
	
	
	@Override
	public boolean updateUtilityShifting(UtilityShifting obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE utility_shifting SET work_id_fk=:work_id_fk, identification=:identification, location_name=:location_name,"
					+ "reference_number=:reference_number, utility_description=:utility_description, utility_type_fk=:utility_type_fk, utility_category_fk=:utility_category_fk,"
					+ " owner_name=:owner_name, execution_agency_fk=:execution_agency_fk, contract_id_fk=:contract_id_fk, start_date=:start_date, scope=:scope, completed=:completed,"
					+ " shifting_status_fk=:shifting_status_fk, shifting_completion_date=:shifting_completion_date, remarks=:remarks, latitude=:latitude,"
					+ " impacted_contract_id_fk=:impacted_contract_id_fk, requirement_stage_fk=:requirement_stage_fk, planned_completion_date=:planned_completion_date, unit_fk=:unit_fk,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP "
					+ " WHERE id = :id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {

				String deleteProgressDataQry = "delete from utility_shifting_progress where utility_shifting_id = :id";
				
				UtilityShifting fileObj = new UtilityShifting();
				fileObj.setUtility_shifting_id(obj.getId());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				template.update(deleteProgressDataQry, paramSource);
								
				
				if(!StringUtils.isEmpty(obj.getProgress_dates()) && obj.getProgress_dates().length > 0) {
					obj.setProgress_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_dates()));
				}
				if(!StringUtils.isEmpty(obj.getProgress_of_works()) && obj.getProgress_of_works().length > 0) {
					obj.setProgress_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_of_works()));
				}
				
				String[] progressDates = obj.getProgress_dates();
				String[] progressOfWorks = obj.getProgress_of_works();					
				
				String insertQry = "INSERT INTO utility_shifting_progress"
						+ "(progress_date, progress_of_work, utility_shifting_id)"
						+ "VALUES"
						+ "(?,?,?)";
				
				int[] counts = jdbcTemplate.batchUpdate(insertQry,
			            new BatchPreparedStatementSetter() {			                 
			                @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {	
			                	int k = 1;
								ps.setString(k++, progressDates.length > 0 ?DateParser.parse(progressDates[i]):null);
								ps.setString(k++, progressOfWorks.length > 0 ?progressOfWorks[i]:null);
								ps.setString(k++, obj.getId());
			                }
			                @Override  
			                public int getBatchSize() {		                	
			                	int arraySize = 0;
			    				if(!StringUtils.isEmpty(obj.getProgress_dates()) && obj.getProgress_dates().length > 0) {
			    					obj.setProgress_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_dates()));
			    					if(arraySize < obj.getProgress_dates().length) {
			    						arraySize = obj.getProgress_dates().length;
			    					}
			    				}
			    				if(!StringUtils.isEmpty(obj.getProgress_of_works()) && obj.getProgress_of_works().length > 0) {
			    					obj.setProgress_of_works(CommonMethods.replaceEmptyByNullInSringArray(obj.getProgress_of_works()));
			    					if(arraySize < obj.getProgress_of_works().length) {
			    						arraySize = obj.getProgress_of_works().length;
			    					}
			    				}
			                    return arraySize;
			                }
			            });	
				
				
				String deleteFilesQry = "delete from utility_shifting_files where utility_shifting_id = :id";
				
				UtilityShifting fileObj1 = new UtilityShifting();
				fileObj1.setUtility_shifting_id(obj.getId());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				template.update(deleteFilesQry, paramSource);
								
						
				
				if((!StringUtils.isEmpty(obj.getUtilityShiftingFiles()) && obj.getUtilityShiftingFiles().size() > 0) || (obj.getAttachment_file_types().length>0 && obj.getAttachmentNames().length>0)) 
				{
					
					String fileQry = "INSERT INTO utility_shifting_files (name,attachment,utility_shifting_id,utility_shifting_file_type_fk)VALUES(:name,:attachment,:utility_shifting_id,:utility_shifting_file_type)";
					
					List<MultipartFile> issueFiles = obj.getUtilityShiftingFiles();

					String[] Attachmentfiletypes = obj.getAttachment_file_types();
					String[] AttachmentNames = obj.getAttachmentNames();
					
					int m=0;
					for (MultipartFile multipartFile : issueFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.UTILITY_SHIFTING_FILE_SAVING_PATH;
							String fileName = multipartFile.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
							String fileName_new = "UtilityShifting-"+obj.getUtility_shifting_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
							
							UtilityShifting fileObj11 = new UtilityShifting();
							fileObj11.setName(AttachmentNames[m]);
							fileObj11.setAttachment(fileName_new);
							fileObj11.setUtility_shifting_id(obj.getId());
							fileObj11.setUtility_shifting_file_type(Attachmentfiletypes[m]);
							paramSource = new BeanPropertySqlParameterSource(fileObj11);	
							template.update(fileQry, paramSource);
						}
						m++;
					}
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Others");
					formHistory.setForm_name("Update Utility Shifting");
					formHistory.setForm_action_type("Update");
					formHistory.setForm_details("Utility Shifting "+obj.getUtility_shifting_id() + " Updated");
					formHistory.setWork(obj.getWork_id_fk());
					formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/

				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}


	@Override
	public UtilityShifting getUtilityShifting(UtilityShifting obj) throws Exception {
		UtilityShifting sobj = null;
		try {
			String qry = "SELECT s.*,DATE_FORMAT(identification,'%d-%m-%Y') as identification,DATE_FORMAT(start_date,'%d-%m-%Y') as start_date,"
					+ "DATE_FORMAT(planned_completion_date,'%d-%m-%Y') as planned_completion_date,DATE_FORMAT(shifting_completion_date,'%d-%m-%Y') as shifting_completion_date,"
					+ "p.project_name,w.work_short_name,c.contract_short_name,p.project_id as project_id_fk "
					+ "from utility_shifting s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON s.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where id =? " ;
			
			int arrSize = 1;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				qry = qry + " and utility_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				qry = qry + " and utility_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				qry = qry + " and shifting_status_fk ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getId();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				pValues[i++] = obj.getUtility_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				pValues[i++] = obj.getUtility_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				pValues[i++] = obj.getShifting_status_fk();
			}
			sobj = (UtilityShifting)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));	
			
			if(!StringUtils.isEmpty(sobj)) {				
				String filesQry ="select id, utility_shifting_id,name, attachment,utility_shifting_file_type_fk as utility_shifting_file_type from utility_shifting_files where utility_shifting_id = ? ";					
				List<UtilityShifting> objsList = jdbcTemplate.query( filesQry,new Object[] {obj.getId()}, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));					
				if(!StringUtils.isEmpty(objsList)) {
					sobj.setUtilityShiftingFilesList(objsList);
				}
				String filesCMQry ="select id, DATE_FORMAT(progress_date,'%d-%m-%Y') as progress_date, progress_of_work from utility_shifting_progress where utility_shifting_id = ? ";					
				List<UtilityShifting> objsCMList = jdbcTemplate.query( filesCMQry,new Object[] {obj.getId()}, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));					
				if(!StringUtils.isEmpty(objsCMList)) {
					sobj.setUtilityShiftingProgressDetailsList(objsCMList);
				}				
			}	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return sobj;
	}

	@Override
	public List<UtilityShifting> getUtilityTypeListForUtilityShifting(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT utility_shifting_file_type FROM utility_shifting_file_type order by utility_shifting_file_type";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getRDetailsList(String utility_shifting_id) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry ="select rc.id, DATE_FORMAT(progress_date ,'%d-%m-%Y') AS progress_date, progress_of_work, r.utility_shifting_id as utility_shifting_id  from utility_shifting_progress rc "
					+ "LEFT JOIN utility_shifting r on rc.utility_shifting_id = r.id "
					+ "WHERE rc.utility_shifting_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(utility_shifting_id) ) {
				qry = qry + " and rc.utility_shifting_id  = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(utility_shifting_id)) {
				pValues[i++] = utility_shifting_id;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UtilityShifting> getUtilityShiftingList(UtilityShifting obj) throws Exception {
		List<UtilityShifting> objsList = null;
		try {
			String qry = "SELECT id, utility_shifting_id, s.work_id_fk,w.work_short_name,w.work_name,w.project_id_fk,p.project_name,c.contract_short_name,DATE_FORMAT(s.identification ,'%d-%m-%Y') AS  identification, s.location_name, reference_number, utility_description, utility_type_fk, "
					+ "utility_category_fk, s.owner_name, execution_agency_fk, contract_id_fk,  DATE_FORMAT(s.start_date ,'%d-%m-%Y') AS start_date, s.scope, s.completed, s.shifting_status_fk, DATE_FORMAT(shifting_completion_date ,'%d-%m-%Y') AS shifting_completion_date, "
					+ "s.remarks, s.latitude, s.longitude, impacted_contract_id_fk, requirement_stage_fk, DATE_FORMAT(s.planned_completion_date ,'%d-%m-%Y') AS planned_completion_date, unit_fk, s.created_by, s.created_date, s.modified_by,"
					+ " s.modified_date from utility_shifting s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "LEFT OUTER JOIN work w ON s.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "
					+ "where utility_shifting_id is not null " ;
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				qry = qry + " and utility_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				qry = qry + " and utility_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				qry = qry + " and shifting_status_fk =?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_category_fk())) {
				pValues[i++] = obj.getUtility_category_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUtility_type_fk())) {
				pValues[i++] = obj.getUtility_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getShifting_status_fk())) {
				pValues[i++] = obj.getShifting_status_fk();
			}
		
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<UtilityShifting>(UtilityShifting.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

}
