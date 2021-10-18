package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ContractReportDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;

@Repository
public class ContractReportDaoImpl implements ContractReportDao {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Contract> getHODListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select hod_user_id_fk,user_id,user_name,designation "
					+ "from contract c "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where hod_user_id_fk IS NOT NULL and user_type_fk='HOD' and hod_user_id_fk <> ''";

			int arrSize = 0;
			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			
			qry = qry + " group by u.designation";
			
			qry = qry + " ORDER BY FIELD(designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T') ";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}	
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	public List<Contract> getWorksListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select work_id_fk,work_id,work_name,work_short_name "
					+ "from contract c "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where c.work_id_fk IS NOT NULL and c.work_id_fk <> ''";

			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by c.work_id_fk order by c.work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
				
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractorsListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select contractor_id_fk,contractor_id,contractor_name "
					+ "from contract c "
					+ "LEFT JOIN contractor ctr ON contractor_id_fk = contractor_id "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where contractor_id_fk IS NOT NULL and contractor_id_fk <> ''";

			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by c.contractor_id_fk order by c.contractor_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) 
			{
				
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}				
				
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
	@Override
	public List<Contract> getStatusofWorkItems(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select distinct contract_status_fk "
					+ "from contract c "
					+ "LEFT JOIN contractor ctr ON contractor_id_fk = contractor_id "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where contract_status_fk IS NOT NULL and contract_status_fk <> ''";

			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				qry = qry + " and c.status = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by c.contract_status_fk order by c.contract_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}				
				
				
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				pValues[i++] = obj.getStatus();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	
	
	@Override
	public List<Contract> getContractStatusListInContractReport(Contract obj) throws Exception {
		/*List<Contract> objsList = null;
		try {
			String qry ="select general_status as contract_status_fk from general_status";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;*/
		
		List<Contract> objsList = null;
		try {
			String qry ="select contract_status_fk "
					+ "from contract c "
					+ "LEFT JOIN contractor ctr ON contractor_id_fk = contractor_id "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where contract_status_fk IS NOT NULL and contract_status_fk <> ''";

			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by c.contract_status_fk order by c.contract_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}				
				
				
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Contract> getContractListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select contract_id,contract_name,contract_short_name "
					+ "from contract c "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where contract_id IS NOT NULL and contract_id <> ''";

			int arrSize = 0;
			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) 
			{
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				
				qry = qry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by contract_id order by contract_id ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}	
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public Map<String,List<Contract>> getContractsListForReport(Contract obj) throws Exception {
		Map<String,List<Contract>> mapObjsList = new LinkedHashMap<String, List<Contract>>();
		List<Contract> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			
			String hodQry ="select u.designation as hod_designation,c.hod_user_id_fk,DATE_FORMAT(doc,'%d-%b-%Y') AS doc,doc as doc_date, "
					+ "(select revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as  revised_doc_temp "
					+ "from contract c "
					+ "left join work w on c.work_id_fk = w.work_id "  
					+ "left join contractor cr on c.contractor_id_fk = cr.contractor_id "  
					+ "left join project p on w.project_id_fk = p.project_id "  
					+ "left join user u on c.hod_user_id_fk = u.user_id "
					+ "left join user us on c.dy_hod_user_id_fk = us.user_id "
					+ "left join department dt on c.department_fk = dt.department "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
				hodQry = hodQry + " having ((revised_doc_temp is not null and DATE(revised_doc_temp) <= DATE(?) ) or (revised_doc_temp is null and DATE(doc_date) <= DATE(?)))";
				arrSize++;
				arrSize++;
			}
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk ";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}	
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
				pValues[i++] = obj.getDate();
				pValues[i++] = obj.getDate();
			}
			
			List<Contract> hodObjsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
			for (Contract hodObj : hodObjsList) {
				
				obj.setHod_designation(hodObj.getHod_designation());
				
				String qry ="select w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head,"  
						+"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%b-%y') AS doc,doc as doc_date,TRUNCATE(cast((IFNULL(awarded_cost,0)*IFNULL(awarded_cost_units,0)/10000000) as CHAR),2) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%b-%y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%b-%y') AS actual_completion_date,"
						+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
						+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%m-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required,c.remarks, "
						/*+ "(select revision_number from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_number," 
						+ "(select revision_date from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_date," 
						+ "(select cast(revised_amount as CHAR) as revised_amount from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revised_amount," 
						+ "(select DATE_FORMAT(revised_doc,'%d-%b-%Y') AS revised_doc from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revised_doc," 
						+ "(select revised_doc from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revised_doc_temp," 
						+ "(select remarks from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_remarks " */
						+ "(select revision_number from contract_revision where revision_number is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as  revision_number," 
						+ "(select revision_date from contract_revision where revision_date is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as  revision_date," 
						+ "(select cast((IFNULL(revised_amount,0)/10000000) as CHAR) as revised_amount from contract_revision where revised_amount is not null and revision_amounts_status = 'Yes' and contract_id_fk = contract_id) as  revised_amount,"
						+ "(select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as  revised_doc," 
						+ "(select revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as  revised_doc_temp," 
						+ "(select remarks from contract_revision where action = 'Yes' and contract_id_fk = contract_id limit 1) as revision_remarks,"
						
						+ "(select cast((IFNULL(SUM(gross_work_done),0)/10000000) as CHAR) AS gross_work_done from expenditure where contract_id_fk = contract_id) as cumulative_expenditure, "
						+ "(select GROUP_CONCAT(DISTINCT DATE_FORMAT(i1.valid_upto,'%d-%b-%y') SEPARATOR '\n<space>' )  from insurance i1 where i1.contract_id_fk = c.contract_id  and (released_fk is null or released_fk<>'Yes')) as insurance_valid_till, "
						+ "(select GROUP_CONCAT(DISTINCT DATE_FORMAT(valid_upto,'%d-%b-%y') SEPARATOR '\n<space>' ) from bank_guarantee bg where bg.contract_id_fk = c.contract_id  and bg_type_fk is not null and release_date is null) as pbg_valid_till, "
						+ "(SELECT TRUNCATE(sum(contract_per)*100,1) FROM activities_scurve where contract_id = contract_id and category COLLATE utf8mb4_unicode_ci= 'Actual' COLLATE utf8mb4_unicode_ci) as PhysicalProgress,"
						+ "DATE_FORMAT(c.target_doc,'%d-%b-%y') AS target_doc,status  "
						+ " from contract c "  
						+ "left join work w on c.work_id_fk = w.work_id "  
						+ "left join contractor cr on c.contractor_id_fk = cr.contractor_id "  
						+ "left join project p on w.project_id_fk = p.project_id "  
						+ "left join user u on c.hod_user_id_fk = u.user_id "
						+ "left join user us on c.dy_hod_user_id_fk = us.user_id "
						+ "left join department dt on c.department_fk = dt.department "
						+ "where contract_id is not null ";
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				else 
				{
					qry = qry + " and c.hod_user_id_fk is null ";
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
					qry = qry + " and c.status = ?";
					arrSize++;
				}				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " having ((revised_doc_temp is not null and DATE(revised_doc_temp) <= DATE(?) ) or (revised_doc_temp is null and DATE(doc_date) <= DATE(?))) order by c.status ";
					arrSize++;
					arrSize++;
				}

				
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
					pValues[i++] = obj.getStatus();
				}				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					pValues[i++] = obj.getDate();
					pValues[i++] = obj.getDate();
				}
					
				objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
				for (Contract contract : objsList) {
					double awarded_cost = 0,revised_contract_cost = 0,cumulative_expenditure = 0;
					if(!StringUtils.isEmpty(contract.getAwarded_cost())) {
						awarded_cost = Double.parseDouble(contract.getAwarded_cost());
					}
					if(!StringUtils.isEmpty(contract.getRevised_amount())) {
						revised_contract_cost = Double.parseDouble(contract.getRevised_amount());
					}
					if(!StringUtils.isEmpty(contract.getCumulative_expenditure())) {
						cumulative_expenditure = Double.parseDouble(contract.getCumulative_expenditure());
					}
					if(awarded_cost == 0) {
						contract.setAwarded_cost("");
					}else {
						contract.setAwarded_cost(numberFormatter.format(awarded_cost));
					}
					if(revised_contract_cost == 0) {
						contract.setRevised_amount("");
					}else {
						contract.setRevised_amount(numberFormatter.format(revised_contract_cost));
					}
					if(cumulative_expenditure == 0) {
						contract.setCumulative_expenditure("");
					}else {
						contract.setCumulative_expenditure(numberFormatter.format(cumulative_expenditure));
					}
					/*contract.setRevised_amount(numberFormatter.format(revised_contract_cost));
					contract.setRevised_amount(numberFormatter.format(revised_contract_cost));
					contract.setCumulative_expenditure(numberFormatter.format(cumulative_expenditure));*/
				}
				String hod = hodObj.getHod_designation();
				if(StringUtils.isEmpty(hod)) {
					hod = "Not assigned to this contract";
				}
				mapObjsList.put(hod, objsList);
			}	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return mapObjsList;
	}

	
	@Override
	public Map<String,List<Contract>> getContractsBankGuaranteeForReport(Contract obj) throws Exception {
		Map<String,List<Contract>> objsList = new LinkedHashMap<String, List<Contract>>();
		try {
			
			String hodQry ="select u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name " + 
					"from bank_guarantee bg " +
					"left join contract c on bg.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null and bg.release_date is null ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) { hodQry
			 * = hodQry + " and bg.valid_upto <= ?"; arrSize++; }
			 */
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
			 * pValues[i++] = obj.getDate(); }
			 */
				
			List<Contract> hodList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			for (Contract hodObj : hodList) {			
				String qry ="select DISTINCT contract_short_name,contractor_name,GROUP_CONCAT(DISTINCT bg_number SEPARATOR '\n<space>' ) as bg_number,GROUP_CONCAT(DISTINCT bg_value SEPARATOR '\n<space>' ) as bg_value,GROUP_CONCAT(DISTINCT bg_valid_upto SEPARATOR '\n<space>' ) as bg_valid_upto,GROUP_CONCAT(DISTINCT ContractAlertRemarks SEPARATOR '\n' ) as ContractAlertRemarks from(select distinct bank_guarantee_id,bg.contract_id_fk,bg_type_fk,issuing_bank,bg_number,TRUNCATE(cast((IFNULL(bg_value,0)*IFNULL(bg_value_units,0)/100000) as CHAR),2) as bg_value,DATE_FORMAT(valid_upto,'%d-%b-%y') AS bg_valid_upto,code,DATE_FORMAT(bg_date,'%d-%b-%Y') AS bg_date,DATE_FORMAT(release_date,'%d-%b-%Y') AS release_date,"
						+ "w.work_id,w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
						"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%b-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%b-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%b-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%b-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
						+"DATE_FORMAT(contract_closure_date,'%d-%b-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%b-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%b-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%b-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
						+"DATE_FORMAT(retention_money_release,'%d-%b-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%b-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%b-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required, " + 
						"(select DISTINCT remarks from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
								+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
								+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
								+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
		+ "AND created_date = (select DISTINCT max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
								+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
								+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
								+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))) as ContractAlertRemarks "+						
						
						"from bank_guarantee bg " +
						"left join contract c on bg.contract_id_fk = c.contract_id " +
						"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
						"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join user u on c.hod_user_id_fk = u.user_id "+
						"left join user us on c.dy_hod_user_id_fk = us.user_id "
						+"left join department dt on c.department_fk = dt.department "
						+"where contract_id is not null and bg.release_date is null ";
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " and bg.valid_upto <= ?";
					arrSize++;
				}
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					pValues[i++] = obj.getDate();
				}
				
				qry = qry + " ) as a ";
				
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					qry = qry +" where 1=(case when (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"') then 0 else 1 end) ";
				}		
				
				qry = qry + " GROUP BY contractor_name,contract_short_name";
					
				List<Contract> bgList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
				objsList.put(hodObj.getHod_designation(), bgList);
			}	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public Map<String,List<Contract>> getContractsDocBGInsuranceForReport(Contract obj) throws Exception {
		Map<String,List<Contract>> objsList = new LinkedHashMap<String, List<Contract>>();
		try {
			
			String hodQry ="select u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name "
					+"from contract c " + 
					"left join bank_guarantee bg on bg.contract_id_fk = c.contract_id " +
					"left join insurance i on i.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null and (i.released_fk <> 'Yes' or i.released_fk is null) ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
				hodQry = hodQry + " and (";
				for(int i1=0; i1<3; i1++) 
				{
					if(i1==0)
					{
						hodQry = hodQry + " c.doc<= ? ";
					}
					if(i1==1)
					{
						hodQry = hodQry + " or bg.valid_upto <= ? ";
					}
					if(i1==2)
					{
						hodQry = hodQry + " or i.valid_upto <= ? ";
					}					
					arrSize++;
				}
				hodQry = hodQry + " ) ";
			}
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) 
			{
				for(int j=0; j<3; j++) 
				{
					pValues[i++] = obj.getDate();
				}
			}
			
			List<Contract> hodList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			for (Contract hodObj : hodList) {		
				
				var conCatBGQry="(select GROUP_CONCAT(DISTINCT DATE_FORMAT(valid_upto,'%d-%b-%y') order by valid_upto asc SEPARATOR '\n<space>' ) from bank_guarantee bg where bg.contract_id_fk = c.contract_id  and bg_type_fk is not null and release_date is null )";
				
				var conCatQry="(select GROUP_CONCAT(DISTINCT DATE_FORMAT(i1.valid_upto,'%d-%b-%y') order by valid_upto asc SEPARATOR '\n<space>' )  from insurance i1 where i1.contract_id_fk = c.contract_id  and (released_fk is null or released_fk<>'Yes') )";
				
				var conCatDocQry="case when (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) is not null then (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) else DATE_FORMAT(doc,'%d-%b-%y') end ";
				

				String qry ="select distinct contract_short_name,contractor_name,GROUP_CONCAT(DISTINCT doc SEPARATOR '\n' ) as doc,GROUP_CONCAT(DISTINCT bg_valid_upto SEPARATOR '\n' ) as bg_valid_upto,GROUP_CONCAT(DISTINCT insurance_valid_upto SEPARATOR '\n' ) as insurance_valid_upto,GROUP_CONCAT(DISTINCT ContractAlertRemarks SEPARATOR '\n' ) as ContractAlertRemarks from (select distinct "+conCatQry+" AS insurance_valid_upto,"
						+ "c.contract_short_name,cr.contractor_name," + 
						conCatDocQry+" AS doc,"
						+conCatBGQry+" AS bg_valid_upto, "
						
						+"GROUP_CONCAT(distinct CONCAT(replace(replace((coalesce((select CONCAT('DOC-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Contract Period' and contract_id = c.contract_id and alert_value ="

					+ "(case when (cr1.action = 'Yes' and cr1.revised_doc is not null) then (CONCAT('Date of Completion : ',DATE_FORMAT(cr1.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end )),'')),'DOC-NO Data',''),'DOC-.',''),"+			
						
					"replace((coalesce((select distinct CONCAT('\nBG-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
+ "AND created_date = (select max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))),'')),'\nBG-NO Data','')," +
					
					"replace((coalesce((select CONCAT('Insurance-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
+ "AND created_date = (select max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id  and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))),'')),'Insurance-NO Data','')) SEPARATOR '\n<space>' ) AS ContractAlertRemarks "
					
					
						
						
						+"from contract c " + 
						"left join bank_guarantee bg on bg.contract_id_fk = c.contract_id " +
						"left join insurance i on i.contract_id_fk = c.contract_id " +
						"LEFT JOIN contract_revision cr1 on cr1.contract_id_fk = c.contract_id and cr1.action = 'Yes' and cr1.revised_doc is not null "+
						"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
						"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join user u on c.hod_user_id_fk = u.user_id "+
						"left join user us on c.dy_hod_user_id_fk = us.user_id "
						+"left join department dt on c.department_fk = dt.department "
						+"where contract_id is not null ";
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " and (";
					for(int i1=0; i1<3; i1++) 
					{
						if(i1==0)
						{
							qry = qry + " c.doc<= ? ";
						}
						if(i1==1)
						{
							qry = qry + " or bg.valid_upto <= ? ";
						}
						if(i1==2)
						{
							qry = qry + " or i.valid_upto <= ? ";
						}					
						arrSize++;
					}
					qry = qry + " )  ";
				}
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
							pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) 
				{
					for(int j=0; j<3; j++) 
					{
						pValues[i++] = obj.getDate();
					}
				}
				qry=qry+" group by insurance_valid_upto,c.contract_short_name,cr.contractor_name,doc,revised_doc,bg_valid_upto ) as a ";
				
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					qry=qry+"where 1=(case WHEN (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and LENGTH(bg_valid_upto)<=10 and insurance_valid_upto is null and doc is null) then 0 when (doc is null and bg_valid_upto is null and insurance_valid_upto is null) then 0 when (STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"') then 0  WHEN (STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null and bg_valid_upto is null) then 0  WHEN (STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and doc is null and bg_valid_upto is null) then 0 WHEN (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null and doc is null) then 0 when (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null) then 0 when (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and doc is null) then 0 when (STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and bg_valid_upto is null) then 0 else 1 end)";
				}
				
				qry = qry + " GROUP BY contractor_name,contract_short_name";
				
				List<Contract> insuranceList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				objsList.put(hodObj.getHod_designation(), insuranceList);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public Map<String,List<Contract>> getContractsDocBGInsuranceForAutoEmailReport(Contract obj) throws Exception {
		Map<String,List<Contract>> objsList = new LinkedHashMap<String, List<Contract>>();
		try {
			
			String hodQry ="select u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name "
					+"from contract c " + 
					"left join bank_guarantee bg on bg.contract_id_fk = c.contract_id " +
					"left join insurance i on i.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null and (i.released_fk <> 'Yes' or i.released_fk is null) and c.contract_status_fk in ('In Progress') ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && obj.getDepartment_fk().equals("'Elec','S&T'")) {
				hodQry = hodQry + " and u.department_fk in (?,?)";
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())){
				hodQry = hodQry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
				hodQry = hodQry + " and (";
				for(int i1=0; i1<3; i1++) 
				{
					if(i1==0)
					{
						hodQry = hodQry + " c.doc<= ? ";
					}
					if(i1==1)
					{
						hodQry = hodQry + " or bg.valid_upto <= ? ";
					}
					if(i1==2)
					{
						hodQry = hodQry + " or i.valid_upto <= ? ";
					}					
					arrSize++;
				}
				hodQry = hodQry + " ) ";
			}
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && obj.getDepartment_fk().equals("'Elec','S&T'")) {
				pValues[i++] = "Elec";
				pValues[i++] = "S&T";
			}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) 
			{
				for(int j=0; j<3; j++) 
				{
					pValues[i++] = obj.getDate();
				}
			}
			
			List<Contract> hodList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			for (Contract hodObj : hodList) {		
				
				var conCatBGQry="(select GROUP_CONCAT(DISTINCT DATE_FORMAT(valid_upto,'%d-%b-%y') order by valid_upto asc SEPARATOR '\n' ) from bank_guarantee bg where bg.contract_id_fk = c.contract_id  and bg_type_fk is not null and release_date is null )";
				
				var conCatQry="(select GROUP_CONCAT(DISTINCT DATE_FORMAT(i1.valid_upto,'%d-%b-%y') order by valid_upto asc SEPARATOR '\n' )  from insurance i1 where i1.contract_id_fk = c.contract_id  and (released_fk is null or released_fk<>'Yes') )";
				
				var conCatDocQry="case when (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) is not null then (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) else DATE_FORMAT(doc,'%d-%b-%y') end ";
				

				String qry ="select contract_short_name,contractor_name,GROUP_CONCAT(DISTINCT doc SEPARATOR '\n' ) as doc,GROUP_CONCAT(DISTINCT bg_valid_upto SEPARATOR '\n' ) as bg_valid_upto,GROUP_CONCAT(DISTINCT insurance_valid_upto SEPARATOR '\n' ) as insurance_valid_upto,GROUP_CONCAT(DISTINCT ContractAlertRemarks SEPARATOR '\n' ) as ContractAlertRemarks from (select distinct "+conCatQry+" AS insurance_valid_upto,"
						+ "c.contract_short_name,cr.contractor_name," + 
						conCatDocQry+" AS doc,"
						+conCatBGQry+" AS bg_valid_upto, "
						
						+"GROUP_CONCAT(distinct CONCAT(replace(replace((coalesce((select CONCAT('DOC-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Contract Period' and contract_id = c.contract_id and alert_value ="

					+ "(case when (cr1.action = 'Yes' and cr1.revised_doc is not null) then (CONCAT('Date of Completion : ',DATE_FORMAT(cr1.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end )),'')),'DOC-NO Data',''),'DOC-.',''),"+			
						
					"replace((coalesce((select distinct CONCAT('\nBG-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
+ "AND created_date = (select max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Bank Guarantee' and contract_id = c.contract_id and alert_value = (case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))),'')),'\nBG-NO Data','')," +
					
					"replace((coalesce((select CONCAT('Insurance-',coalesce(remarks,'NO Data')) from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
+ "AND created_date = (select max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id  and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
					+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))),'')),'Insurance-NO Data',''))) AS ContractAlertRemarks "
					
					
						
						
						+"from contract c " + 
						"left join bank_guarantee bg on bg.contract_id_fk = c.contract_id " +
						"left join insurance i on i.contract_id_fk = c.contract_id " +
						"LEFT JOIN contract_revision cr1 on cr1.contract_id_fk = c.contract_id and cr1.action = 'Yes' and cr1.revised_doc is not null "+
						"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
						"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join user u on c.hod_user_id_fk = u.user_id "+
						"left join user us on c.dy_hod_user_id_fk = us.user_id "
						+"left join department dt on c.department_fk = dt.department "
						+"where contract_id is not null and c.contract_status_fk in ('In Progress') ";
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && obj.getDepartment_fk().equals("'Elec','S&T'")) {
					qry = qry + " and u.department_fk in (?,?)";
					arrSize++;
					arrSize++;
				}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())){
					qry = qry + " and u.department_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " and (";
					for(int i1=0; i1<3; i1++) 
					{
						if(i1==0)
						{
							qry = qry + " c.doc<= ? ";
						}
						if(i1==1)
						{
							qry = qry + " or bg.valid_upto <= ? ";
						}
						if(i1==2)
						{
							qry = qry + " or i.valid_upto <= ? ";
						}					
						arrSize++;
					}
					qry = qry + " )  ";
				}
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && obj.getDepartment_fk().equals("'Elec','S&T'")) {
					pValues[i++] = "Elec";
					pValues[i++] = "S&T";
				}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
							pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) 
				{
					for(int j=0; j<3; j++) 
					{
						pValues[i++] = obj.getDate();
					}
				}
				qry=qry+" group by insurance_valid_upto,c.contract_short_name,cr.contractor_name,doc,revised_doc,bg_valid_upto ) as a ";
				
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					qry=qry+"where 1=(case WHEN (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and LENGTH(bg_valid_upto)<=10 and insurance_valid_upto is null and doc is null) then 0 when (doc is null and bg_valid_upto is null and insurance_valid_upto is null) then 0 when (STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"') then 0  WHEN (STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null and bg_valid_upto is null) then 0  WHEN (STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and doc is null and bg_valid_upto is null) then 0 WHEN (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null and doc is null) then 0 when (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and insurance_valid_upto is null) then 0 when (STR_TO_DATE(bg_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and doc is null) then 0 when (STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"' and STR_TO_DATE(doc,'%d-%M-%Y')>'"+obj.getDate()+"' and bg_valid_upto is null) then 0 else 1 end)";
				}
				
				qry = qry + " GROUP BY contractor_name,contract_short_name";
				
				List<Contract> insuranceList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				objsList.put(hodObj.getHod_designation(), insuranceList);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public Map<String,List<Contract>> getContractsDocReport(Contract obj) throws Exception {
		Map<String,List<Contract>> objsList = new LinkedHashMap<String, List<Contract>>();
		try {
			
			String hodQry ="select u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name "
					+"from insurance i " + 
					"left join contract c on i.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null and (i.released_fk <> 'Yes' or i.released_fk is null) ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) { hodQry
			 * = hodQry + " and i.valid_upto <= ?"; arrSize++; }
			 */
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
			 * pValues[i++] = obj.getDate(); }
			 */
			
			List<Contract> hodList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			for (Contract hodObj : hodList) 
			{
				
				var conCatQry="case when (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) is not null then (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id  limit 1) else DATE_FORMAT(doc,'%d-%b-%y') end";
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					conCatQry="case when (case when (select revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) is not null then (select  revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id  limit 1) else doc end)>'"+obj.getDate()+"' then 'NO DOC' else (case when (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) is not null then (select DATE_FORMAT(MAX(revised_doc),'%d-%b-%y') AS revised_doc from contract_revision where revised_doc is not null and action = 'Yes' and contract_id_fk = contract_id  limit 1) else DATE_FORMAT(doc,'%d-%b-%y') end) end";
				}
								
				
				String qry ="select contract_short_name,contractor_name,GROUP_CONCAT(DISTINCT loa_date SEPARATOR '\n' ) as loa_date,GROUP_CONCAT(DISTINCT doc SEPARATOR '\n' ) as doc,GROUP_CONCAT(DISTINCT ContractAlertRemarks SEPARATOR '\n' ) as ContractAlertRemarks from (select distinct "
						+ "w.work_id,w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
						"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%b-%Y') AS date_of_start,"
						+conCatQry+ " AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%b-%y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%b-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%b-%Y') AS actual_completion_date,c.remarks,"
						+"DATE_FORMAT(contract_closure_date,'%d-%b-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%b-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%b-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%b-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%b-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
						+"DATE_FORMAT(retention_money_release,'%d-%b-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%b-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%b-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required, "
						+"(select remarks from alerts where alert_status='Active' and alert_type_fk = 'Contract Period' and contract_id = c.contract_id and alert_value ="

					+ "(case when (cr1.action = 'Yes' and cr1.revised_doc is not null) then (CONCAT('Date of Completion : ',DATE_FORMAT(cr1.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end )) as ContractAlertRemarks "						
						
						+"from contract c " + 
						"LEFT JOIN contract_revision cr1 on cr1.contract_id_fk = c.contract_id and cr1.action = 'Yes' and cr1.revised_doc is not null "+
						"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
						"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join user u on c.hod_user_id_fk = u.user_id "+
						"left join user us on c.dy_hod_user_id_fk = us.user_id "
						+"left join department dt on c.department_fk = dt.department "
						+"where contract_id is not null ";	
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " and c.doc <= ?";
					arrSize++;
				}
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					pValues[i++] = obj.getDate();
				}
				
				qry = qry + " ) as a ";
				
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					qry = qry +" where 1=(case when loa_date is not null and doc='NO DOC' then 0 else 1 end) ";
				}
				
				qry = qry + " GROUP BY contractor_name,contract_short_name";

				List<Contract> insuranceList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				objsList.put(hodObj.getHod_designation(), insuranceList);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Map<String,List<Contract>> getContractsInsuranceForReport(Contract obj) throws Exception {
		Map<String,List<Contract>> objsList = new LinkedHashMap<String, List<Contract>>();
		try {
			
			String hodQry ="select u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name "
					+"from insurance i " + 
					"left join contract c on i.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null and (i.released_fk <> 'Yes' or i.released_fk is null) ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				hodQry = hodQry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				hodQry = hodQry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i1 =0; i1< (length-1); i1++) {
						hodQry = hodQry + ",?";
						arrSize++;
					}
				}
				
				hodQry = hodQry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				hodQry = hodQry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) { hodQry
			 * = hodQry + " and i.valid_upto <= ?"; arrSize++; }
			 */
			
			hodQry = hodQry + " GROUP BY c.hod_user_id_fk";
			hodQry = hodQry + " ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			/*
			 * if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
			 * pValues[i++] = obj.getDate(); }
			 */
			
			List<Contract> hodList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			for (Contract hodObj : hodList) {			
				String qry ="select contract_short_name,contractor_name,GROUP_CONCAT(DISTINCT insurance_number SEPARATOR '\n<space>' ) as insurance_number,GROUP_CONCAT(DISTINCT insurance_value SEPARATOR '\n<space>' ) as insurance_value,GROUP_CONCAT(DISTINCT insurance_valid_upto SEPARATOR '\n<space>' ) as insurance_valid_upto,GROUP_CONCAT(DISTINCT ContractAlertRemarks SEPARATOR '\n' ) as ContractAlertRemarks from(select insurance_id,i.contract_id_fk,insurance_type_fk,issuing_agency,agency_address,insurance_number,TRUNCATE(cast((IFNULL(insurance_value,0)*IFNULL(insurance_value_units,0)/100000) as CHAR),2) as insurance_value,DATE_FORMAT(valid_upto,'%d-%b-%y') AS insurance_valid_upto,i.remarks as insurence_remark,revision,released_fk,"
						+ "w.work_id,w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
						"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%b-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%b-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%b-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%b-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%b-%Y') AS actual_completion_date,c.remarks,"
						+"DATE_FORMAT(contract_closure_date,'%d-%b-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%b-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%b-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%b-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%b-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
						+"DATE_FORMAT(retention_money_release,'%d-%b-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%b-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%b-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required, "
						+"(select remarks from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
						+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
						+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
						+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) "
+ "AND created_date = (select max(created_date) from alerts where alert_status='Active' and alert_type_fk = 'Insurance' and contract_id = c.contract_id and alert_value = (case when (i.insurance_type_fk is not null and i.insurance_number is not null) then CONCAT(i.insurance_type_fk,' ',i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
						+ "when (i.insurance_type_fk is null and i.insurance_number is not null) then CONCAT(i.insurance_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) "
						+ "when (i.insurance_type_fk is not null and i.insurance_number is null) then CONCAT(i.insurance_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
						+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ))) as ContractAlertRemarks "						
						
						+"from insurance i " + 
						"left join contract c on i.contract_id_fk = c.contract_id " +
						"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
						"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join user u on c.hod_user_id_fk = u.user_id "+
						"left join user us on c.dy_hod_user_id_fk = us.user_id "
						+"left join department dt on c.department_fk = dt.department "
						+"where contract_id is not null and (i.released_fk <> 'Yes' or i.released_fk is null) ";
				
				arrSize = 0;			
	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					qry = qry + " and c.contract_id = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ?";
					arrSize++;
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					qry = qry + " and c.contract_status_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					qry = qry + " and i.valid_upto <= ?";
					arrSize++;
				}
				pValues = new Object[arrSize];
				i = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
					pValues[i++] = obj.getContract_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(hodObj.getHod_designation())) {
					pValues[i++] = hodObj.getHod_designation();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
					pValues[i++] = obj.getContract_status_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDate())) {
					pValues[i++] = obj.getDate();
				}
				qry = qry + " ) as a ";
				
				if(obj.getDate()!=null && obj.getDate()!="")
				{
					qry = qry +" where 1=(case when (STR_TO_DATE(insurance_valid_upto,'%d-%M-%Y')>'"+obj.getDate()+"') then 0 else 1 end) ";
				}
				
				qry = qry + " GROUP BY contractor_name,contract_short_name";
				
				List<Contract> insuranceList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				objsList.put(hodObj.getHod_designation(), insuranceList);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	

	@Override
	public Contract getContractDetailsForReport(Contract obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Contract contract = null;
		try{
			con = dataSource.getConnection();
			String contract_updateQry = "select w.work_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk  " + 
									",scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,DATE_FORMAT(target_doc,'%d-%m-%Y') AS target_doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
									+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
									+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,contract_status_fk,c.status,bg_required,insurance_required,department_name,"
									+ "u.designation as hod_designation,us.designation as dy_hod_designation " + 
									"from contract c " + 
									"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
									"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
									"left join project p on w.project_id_fk = p.project_id " + 
									"left join user u on c.hod_user_id_fk = u.user_id "+
									"left join user us on c.dy_hod_user_id_fk = us.user_id "
									+"left join department dt on c.department_fk = dt.department "
									+ "where contract_id = ?" ;
			stmt = con.prepareStatement(contract_updateQry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				contract = new Contract();
				contract.setWork_name(resultSet.getString("work_name"));
				contract.setWork_id_fk(resultSet.getString("work_id_fk"));
				contract.setDesignation(resultSet.getString("designation"));
				contract.setUser_name(resultSet.getString("user_name"));
				contract.setContract_id_code(resultSet.getString("contract_id_code"));
				contract.setProject_id_fk(resultSet.getString("project_id_fk"));
				contract.setProject_name(resultSet.getString("project_name"));
				contract.setContract_id(resultSet.getString("contract_id"));
				contract.setContract_type_fk(resultSet.getString("contract_type_fk"));
				contract.setContract_name(resultSet.getString("contract_name"));
				contract.setContract_short_name(resultSet.getString("contract_short_name"));
				contract.setContractor_id_fk(resultSet.getString("contractor_id_fk"));
				contract.setContractor_name(resultSet.getString("contractor_name"));
				contract.setDepartment_fk(resultSet.getString("department_fk"));
				contract.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				contract.setDy_hod_user_id_fk(resultSet.getString("dy_hod_user_id_fk"));
				contract.setScope_of_contract(resultSet.getString("scope_of_contract"));
				contract.setDoc(resultSet.getString("doc"));
				contract.setAwarded_cost(resultSet.getString("awarded_cost"));
				contract.setLoa_letter_number(resultSet.getString("loa_letter_number"));
				contract.setLoa_date(resultSet.getString("loa_date"));
				contract.setCa_no(resultSet.getString("ca_no"));
				contract.setCa_date(resultSet.getString("ca_date"));
				contract.setActual_completion_date(resultSet.getString("actual_completion_date"));
				contract.setCompleted_cost(resultSet.getString("completed_cost"));
				contract.setEstimated_cost(resultSet.getString("estimated_cost"));
				contract.setDate_of_start(resultSet.getString("date_of_start"));
				contract.setContract_closure_date(resultSet.getString("contract_closure_date"));
				contract.setCompletion_certificate_release(resultSet.getString("completion_certificate_release"));
				contract.setFinal_takeover(resultSet.getString("final_takeover"));
				contract.setFinal_bill_release(resultSet.getString("final_bill_release"));
				contract.setDefect_liability_period(resultSet.getString("defect_liability_period"));
				contract.setRetention_money_release(resultSet.getString("retention_money_release"));
				contract.setPbg_release(resultSet.getString("pbg_release"));
				contract.setContract_status_fk(resultSet.getString("contract_status_fk"));
				contract.setBg_required(resultSet.getString("bg_required"));
				contract.setInsurance_required(resultSet.getString("insurance_required"));
				contract.setRemarks(resultSet.getString("remarks"));
				contract.setDepartment_name(resultSet.getString("department_name"));
				contract.setHod_designation(resultSet.getString("hod_designation"));
				contract.setDy_hod_designation(resultSet.getString("dy_hod_designation"));
				contract.setStatus(resultSet.getString("status"));
				
				contract.setContract_revision(getContract_revision(contract.getContract_id(),con));	
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}		
		return contract;
	}
	
	private List<Contract> getContract_revision(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> contract_revision = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT revision_number,cast(revised_amount as CHAR) as revised_amount ,DATE_FORMAT(revised_doc,'%d-%m-%Y') AS revised_doc"
					+ ",action as revision_status,remarks from contract_revision where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setRevision_number(resultSet.getString("revision_number"));
				obj.setRevised_amount(resultSet.getString("revised_amount"));
				obj.setRevised_doc(resultSet.getString("revised_doc"));
				obj.setRevision_status(resultSet.getString("revision_status"));
				obj.setRemarks(resultSet.getString("remarks"));
				contract_revision.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return contract_revision;
	}
	
	@Override
	public Contract getProgressDetailsAsOnDate(Contract obj) throws Exception {
		Contract pObj = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry ="select contract_status_fk,"
					+ "(select cast(IFNULL(sum(gross_work_done),0) as CHAR) from expenditure where contract_id_fk = contract_id) as payment_made, "
					+ "(select cast(IFNULL(revised_amount,0) as CHAR) from contract_revision where revised_amount is not null and action = 'Yes' and contract_id_fk = contract_id limit 1) as revised_amount,"
					+ "cast(awarded_cost as CHAR) as awarded_cost,"
					+ "(SELECT TRUNCATE(sum(contract_per)*100,2) FROM activities_scurve where contract_id = contract_id and category COLLATE utf8mb4_unicode_ci = 'Actual') as actual_physical_progress,"
					+ "DATE_FORMAT(date_of_start,'%d-%b-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%b-%Y') AS doc "
					+ "from contract c "
					+ "where c.contract_id = ?";
	
			
			Object[] pValues = new Object[]{obj.getContract_id()};
			
			pObj = jdbcTemplate.queryForObject( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
			if(!StringUtils.isEmpty(pObj)) {
				double physical_progress = 0;
				String p_progress = pObj.getActual_physical_progress();
				if(!StringUtils.isEmpty(p_progress)) {
					physical_progress = Double.parseDouble(p_progress);
				}
				
				double revised_amount = 0;
				double awarded_cost = 0;
				String r_amount = pObj.getRevised_amount();
				String a_cost = pObj.getAwarded_cost();
				if(!StringUtils.isEmpty(r_amount)) {
					revised_amount = Double.parseDouble(r_amount);
				}
				if(!StringUtils.isEmpty(a_cost)) {
					awarded_cost = Double.parseDouble(a_cost);
				}
				String p_made = pObj.getPayment_made();
				double payment_made = 0;
				if(!StringUtils.isEmpty(p_made)) {
					payment_made = Double.parseDouble(p_made);
				}
				double financial_progress = 0;
				if(payment_made != 0) {
					if(revised_amount != 0) {
						financial_progress = (payment_made*100)/revised_amount;
					}else {
						financial_progress = (payment_made*100)/awarded_cost;
					}					
				}
				String contract_status = pObj.getContract_status_fk();
				if(!StringUtils.isEmpty(contract_status) && contract_status.equals("Completed")) {
					physical_progress = 100;
					financial_progress = 100;
				}
				pObj.setActual_physical_progress(numberFormatter.format(physical_progress) + " %");
				pObj.setActual_financial_progress(numberFormatter.format(financial_progress) + " %");
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return pObj;
	}

	@Override
	public List<Contract> getMilestoneDetailsForReport(Contract obj) throws Exception {
		Connection con  = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> milestones = new ArrayList<Contract>();
		Contract mObj = null;
		try {
			con = dataSource.getConnection();
			String qry ="SELECT contract_milestones_id,milestone_id,milestone_name,DATE_FORMAT(milestone_date,'%d-%m-%Y') AS milestone_date,DATE_FORMAT(actual_date,'%d-%m-%Y') AS actual_date, revision"
					+ ",remarks from contract_milestones where contract_id_fk = ? and status = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, obj.getContract_id());
			stmt.setString(2, CommonConstants.ACTIVE);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				mObj = new Contract();
				mObj.setContract_milestones_id(resultSet.getString("contract_milestones_id"));
				mObj.setMilestone_id(resultSet.getString("milestone_id"));
				mObj.setMilestone_name(resultSet.getString("milestone_name"));
				mObj.setMilestone_date(resultSet.getString("milestone_date"));
				mObj.setActual_date(resultSet.getString("actual_date"));
				mObj.setRevision(resultSet.getString("revision"));
				mObj.setRemarks(resultSet.getString("remarks"));
				milestones.add(mObj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return milestones;
	}

	@Override
	public List<Contract> getBGDetailsForReport(Contract obj) throws Exception {
		Connection con  = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract bObj = null;
		try {
			con = dataSource.getConnection();
			String qry ="SELECT code,bg_type_fk,issuing_bank, bg_number,cast(bg_value as CHAR) as bg_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS valid_upto"
					+ ",DATE_FORMAT(bg_date,'%d-%m-%Y') AS bg_date,DATE_FORMAT(release_date,'%d-%m-%Y') AS release_date"
					+ " from bank_guarantee where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				bObj = new Contract();
				
				bObj.setCode(resultSet.getString("code"));
				bObj.setBg_type_fk(resultSet.getString("bg_type_fk"));
				bObj.setIssuing_bank(resultSet.getString("issuing_bank"));
				bObj.setBg_number(resultSet.getString("bg_number"));
				bObj.setBg_value(resultSet.getString("bg_value"));
				bObj.setBg_valid_upto(resultSet.getString("valid_upto"));
				bObj.setBg_date(resultSet.getString("bg_date"));
				bObj.setRelease_date(resultSet.getString("release_date"));
				objsList.add(bObj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}
	
	

	@Override
	public List<Contract> getInsuranceDetailsForReport(Contract obj) throws Exception {
		Connection con  = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract iObj = null;
		try {
			con = dataSource.getConnection();
			String qry ="SELECT insurance_type_fk,issuing_agency,agency_address,insurance_number,cast(insurance_value as CHAR) as insurance_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS valid_upto"
					+ ",remarks,revision,released_fk as insurance_status from insurance where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				iObj = new Contract();
				iObj.setInsurance_type_fk(resultSet.getString("insurance_type_fk"));
				iObj.setIssuing_agency(resultSet.getString("issuing_agency"));
				iObj.setAgency_address(resultSet.getString("agency_address"));
				iObj.setInsurance_number(resultSet.getString("insurance_number"));
				iObj.setInsurance_value(resultSet.getString("insurance_value"));
				iObj.setInsurance_valid_upto(resultSet.getString("valid_upto"));
				iObj.setRemarks(resultSet.getString("remarks"));
				iObj.setRevision(resultSet.getString("revision"));
				iObj.setInsurance_status(resultSet.getString("insurance_status"));
				objsList.add(iObj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}

	@Override
	public Contract getContractClosureDetails(Contract obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Contract contract = null;
		try{
			con = dataSource.getConnection();
			String contract_updateQry = "select w.work_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk  " + 
									",scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
									+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
									+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,contract_status_fk,bg_required,insurance_required " + 
									"from contract c " + 
									"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
									"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
									"left join project p on w.project_id_fk = p.project_id " + 
									"left join user u on c.hod_user_id_fk = u.user_id "+
									"left join user us on c.dy_hod_user_id_fk = us.user_id "
									+"left join department dt on c.department_fk = dt.department "
									+ "where contract_id = ?" ;
			stmt = con.prepareStatement(contract_updateQry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				contract = new Contract();
				contract.setWork_name(resultSet.getString("work_name"));
				contract.setWork_id_fk(resultSet.getString("work_id_fk"));
				contract.setDesignation(resultSet.getString("designation"));
				contract.setUser_name(resultSet.getString("user_name"));
				contract.setContract_id_code(resultSet.getString("contract_id_code"));
				contract.setProject_id_fk(resultSet.getString("project_id_fk"));
				contract.setProject_name(resultSet.getString("project_name"));
				contract.setContract_id(resultSet.getString("contract_id"));
				contract.setContract_type_fk(resultSet.getString("contract_type_fk"));
				contract.setContract_name(resultSet.getString("contract_name"));
				contract.setContract_short_name(resultSet.getString("contract_short_name"));
				contract.setContractor_id_fk(resultSet.getString("contractor_id_fk"));
				contract.setContractor_name(resultSet.getString("contractor_name"));
				contract.setDepartment_fk(resultSet.getString("department_fk"));
				contract.setHod_user_id_fk(resultSet.getString("hod_user_id_fk"));
				contract.setDy_hod_user_id_fk(resultSet.getString("dy_hod_user_id_fk"));
				contract.setScope_of_contract(resultSet.getString("scope_of_contract"));
				contract.setDoc(resultSet.getString("doc"));
				contract.setAwarded_cost(resultSet.getString("awarded_cost"));
				contract.setLoa_letter_number(resultSet.getString("loa_letter_number"));
				contract.setLoa_date(resultSet.getString("loa_date"));
				contract.setCa_no(resultSet.getString("ca_no"));
				contract.setCa_date(resultSet.getString("ca_date"));
				contract.setActual_completion_date(resultSet.getString("actual_completion_date"));
				contract.setCompleted_cost(resultSet.getString("completed_cost"));
				contract.setEstimated_cost(resultSet.getString("estimated_cost"));
				contract.setDate_of_start(resultSet.getString("date_of_start"));
				contract.setContract_closure_date(resultSet.getString("contract_closure_date"));
				contract.setCompletion_certificate_release(resultSet.getString("completion_certificate_release"));
				contract.setFinal_takeover(resultSet.getString("final_takeover"));
				contract.setFinal_bill_release(resultSet.getString("final_bill_release"));
				contract.setDefect_liability_period(resultSet.getString("defect_liability_period"));
				contract.setRetention_money_release(resultSet.getString("retention_money_release"));
				contract.setPbg_release(resultSet.getString("pbg_release"));
				contract.setContract_status_fk(resultSet.getString("contract_status_fk"));
				contract.setBg_required(resultSet.getString("bg_required"));
				contract.setInsurance_required(resultSet.getString("insurance_required"));
				contract.setRemarks(resultSet.getString("remarks"));
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}		
		return contract;
	}

	@Override
	public Contract getContractorDetails(Contract obj) throws Exception {
		Contract cObj = null;
		try {
			String qry ="SELECT ctr.contractor_id, ctr.contractor_name, ctr.contractor_specilization_fk, ctr.address, ctr.primary_contact_name, "
					+ "ctr.phone_number, ctr.email_id, ctr.pan_number, ctr.gst_number, ctr.bank_name, ctr.account_number, ctr.ifsc_code, ctr.remarks "
					+ "FROM contract c "
					+ "LEFT JOIN contractor ctr ON contractor_id_fk = contractor_id "
					+ "WHERE contract_id = ?";
			
			Object[] pValues = new Object[]{obj.getContract_id()};
			
			cObj = jdbcTemplate.queryForObject( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return cObj;
	}

	@Override
	public List<Contract> getKeyPersonnelForReport(Contract obj) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		try{
			con = dataSource.getConnection();
			String qry ="SELECT name,cast(mobile_no as CHAR) as mobile_no,email_id,designation from contract_key_personnel where contract_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, obj.getContract_id());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setName(resultSet.getString("name"));
				obj.setMobile_no(resultSet.getString("mobile_no"));
				obj.setEmail_id(resultSet.getString("email_id"));
				obj.setDesignation(resultSet.getString("designation"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, resultSet);
		}
		return objsList;
	}

	@Override
	public String getEmailIdsOfDepartments(String management) throws Exception {
		String email_ids = null;
		try {
			String qry = "";
			if(!StringUtils.isEmpty(management) && management.equals("CMD&DF")) {
				qry = "select group_concat(email_id) from user where designation in('CMD','DIR Finance') and EMAIL_ID is not null";
			}else if(!StringUtils.isEmpty(management) && management.equals("DP&CE")) {
				qry = "select group_concat(email_id) from user where designation in('CE','DIR Project') and EMAIL_ID is not null";
			}else if(!StringUtils.isEmpty(management) && management.equals("DT")) {
				qry = "select group_concat(email_id) from user where designation in('DIR Technical') and EMAIL_ID is not null";
			}
			if(!StringUtils.isEmpty(qry)) {
				email_ids = jdbcTemplate.queryForObject( qry, String.class);	
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return email_ids;
	}

	@Override
	public List<Contract> getStatsuListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select status from contract c "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where status IS NOT NULL and status <> ''";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())){
				qry = qry + " and u.designation in (?";
				int length = obj.getHod_designations().length;
				if(length > 1) {
					for(int i =0; i< (length-1); i++) {
						qry = qry + ",?";
						arrSize++;
					}
				}
				qry = qry + " ) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				qry = qry + " and c.contract_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ?";
				arrSize++;
			}
			qry = qry + " group by status ORDER BY FIELD(status,'Open','Yet to be Awarded','Closed') ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designations())) {
				int length = obj.getHod_designations().length;
				if(length >= 1) {
					for(int j =0; j<= (length-1); j++) {
						pValues[i++] = obj.getHod_designations()[j];
					}
				}	
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_status_fk())) {
				pValues[i++] = obj.getContract_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
}


