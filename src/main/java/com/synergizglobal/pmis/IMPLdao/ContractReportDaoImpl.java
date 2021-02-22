package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ContractReportDao;
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
					+ "where hod_user_id_fk IS NOT NULL and hod_user_id_fk <> ''";

			int arrSize = 0;
			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
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
			
			qry = qry + " group by u.designation order by u.designation ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
				pValues[i++] = obj.getHod_designation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getWorksListInContractReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select work_id_fk,work_id,work_name,work_short_name "
					+ "from contract c "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u ON hod_user_id_fk = user_id "
					+ "where c.work_id_fk IS NOT NULL and c.work_id_fk <> ''";

			int arrSize = 0;
			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
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
			qry = qry + " group by c.work_id_fk order by c.work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
				pValues[i++] = obj.getHod_designation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
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
			qry = qry + " group by c.contractor_id_fk order by c.contractor_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
				pValues[i++] = obj.getHod_designation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractsListForReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
					"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
					+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%m-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required, "
					+ "(select revision_number from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_number," 
					+ "(select revision_date from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_date," 
					+ "(select revised_amount from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revised_amount," 
					+ "(select revised_doc from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revised_doc," 
					+ "(select remarks from contract_revision where contract_revision_id = (select max(contract_revision_id) from contract_revision where contract_id_fk = contract_id)) as  revision_remarks " 
					+"from contract c " + 
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
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
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_designation())) {
				pValues[i++] = obj.getHod_designation();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractsBankGuaranteeForReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select bank_guarantee_id,bg.contract_id_fk,bg_type_fk,issuing_bank,bg_number,bg_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS bg_valid_upto,code,DATE_FORMAT(bg_date,'%d-%m-%Y') AS bg_date,DATE_FORMAT(release_date,'%d-%m-%Y') AS release_date,"
					+ "w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
					"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
					+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
					+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%m-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required " + 
					"from bank_guarantee bg " +
					"left join contract c on bg.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ? ";
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
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Contract> getContractsInsuranceForReport(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select insurance_id,i.contract_id_fk,insurance_type_fk,issuing_agency,agency_address,insurance_number,insurance_value,DATE_FORMAT(valid_upto,'%d-%m-%Y') AS insurance_valid_upto,i.remarks as insurence_remark,revision,released_fk,"
					+ "w.work_name,w.work_short_name,dt.department_name,dt.contract_id_code,w.project_id_fk,p.project_name,u.designation as hod_designation,us.designation as dy_hod_designation,u.user_name,c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_short_name,contractor_id_fk,cr.contractor_name,c.department_fk,c.hod_user_id_fk,c.dy_hod_user_id_fk,tally_head," + 
					"scope_of_contract,cast(estimated_cost as CHAR) as estimated_cost,DATE_FORMAT(date_of_start,'%d-%m-%Y') AS date_of_start,DATE_FORMAT(doc,'%d-%m-%Y') AS doc,cast(awarded_cost as CHAR) as awarded_cost,loa_letter_number,DATE_FORMAT(loa_date,'%d-%m-%Y') AS loa_date,ca_no,DATE_FORMAT(ca_date,'%d-%m-%Y') AS ca_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,c.remarks,"
					+"DATE_FORMAT(contract_closure_date,'%d-%m-%Y') AS contract_closure_date,DATE_FORMAT(completion_certificate_release,'%d-%m-%Y') AS completion_certificate_release,DATE_FORMAT(final_takeover,'%d-%m-%Y') AS final_takeover,DATE_FORMAT(final_bill_release,'%d-%m-%Y') AS final_bill_release,DATE_FORMAT(defect_liability_period,'%d-%m-%Y') AS defect_liability_period,cast(completed_cost as CHAR) as completed_cost,"
					+"DATE_FORMAT(retention_money_release,'%d-%m-%Y') AS retention_money_release,DATE_FORMAT(pbg_release,'%d-%m-%Y') AS pbg_release,DATE_FORMAT(contract_closure,'%d-%m-%Y') AS contract_closure ,contract_status_fk,bg_required,insurance_required "
					+"from insurance i " + 
					"left join contract c on i.contract_id_fk = c.contract_id " +
					"left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join user u on c.hod_user_id_fk = u.user_id "+
					"left join user us on c.dy_hod_user_id_fk = us.user_id "
					+"left join department dt on c.department_fk = dt.department "
					+"where contract_id is not null ";
			
			int arrSize = 0;			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ? ";
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
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}	
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
}
