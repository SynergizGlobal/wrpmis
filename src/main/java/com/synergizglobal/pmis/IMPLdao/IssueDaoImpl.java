package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.synergizglobal.pmis.Idao.IssueDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;

@Repository
public class IssueDaoImpl implements IssueDao {
	public static Logger logger = Logger.getLogger(IssueDaoImpl.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<Issue> getIssuesList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,title,FORMAT(date,'dd-MMM-yy') AS date,location,reported_by,responsible_person,c.department_fk,"
					+ "priority_fk,category_fk,status_fk,corrective_measure,FORMAT(resolved_date,'dd-MMM-yy') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,i.zonal_railway_fk,r.railway_name,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,railway_name,FORMAT(assigned_date,'dd-MMM-yy') AS assigned_date,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,other_organization,FORMAT(i.created_date,'dd-MMM-yy') AS created_date,FORMAT(escalation_date,'dd-MMM-yy') AS escalation_date,"
					+ "other_org_resposible_person_name,other_org_resposible_person_designation,description,i.modified_by,FORMAT(i.modified_date,'dd-MM-yyyy') as modified_date " 
					+ "from issue i "
					+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk  = r.railway_id "
					+ "where issue_id is not null ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
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

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
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

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesStatusList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select status from issue_status";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesPriorityList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select priority from issue_priority";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesCategoryList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "\r\n" + 
					"select f.issue_category_fk as category,issues_related_to from issue_contarct_category f\r\n" + 
					"\r\n" + 
					"left join issue_category_title t on t.issue_category_fk=f.issue_category_fk  ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getContract_type_fk())) {
				qry = qry + "where contract_category_fk = ? ";
				arraSize++;
			}
			qry = qry + "group by f.issue_category_fk,issues_related_to order by f.issue_category_fk ";
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getContract_type_fk())) {
				pValues[i++] = obj.getContract_type_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList; 
	} 

	@Override
	public List<Issue> getIssueTitlesList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select short_description from issue_category_title ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + "where issue_category_fk = ? ";
				arraSize++;
			}
			qry = qry + "group by short_description order by short_description ";
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getDepartmentList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select department as department_fk,department_name from department where department <> 'MGMT'";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getRailwayList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT railway_id,railway_name from railway WHERE railway_id <> 'Con' and railway_id='MRVC' ORDER BY case when railway_id='MRVC' then 1" + 
					"when railway_id='CR' then 2 when railway_id='WR' then 3 when railway_id='Others' then 4 end asc";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getProjectsListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "";
			
			if (CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					|| CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = "select distinct project_id_fk,project_name "
						+ "from contract c "
						+ "LEFT JOIN work w ON work_id_fk = w.work_id "
						+ "LEFT JOIN project p ON w.project_id_fk = p.project_id "
						+ "where contract_status_fk IN('In Progress','Not Started') ";
			} else {
				qry = "SELECT distinct project_id_fk,project_name "
						+ "FROM contract c "
						+ "LEFT JOIN work w ON work_id_fk = w.work_id "
						+ "LEFT JOIN project p ON w.project_id_fk = p.project_id "
						+ "where contract_status_fk IN('In Progress','Not Started') ";
			}
			int arrSize = 0;

			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + "AND (hod_user_id_fk = ? or dy_hod_user_id_fk = ? " 
						+ "or contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from structure_contract_responsible_people where responsible_people_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from fob_contract_responsible_people where fob_id_fk in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?)) "
						+ ")"; 
						
				arrSize = arrSize + 5;
			}

			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			qry = qry + " order by w.project_id_fk asc";

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getWorkListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = new ArrayList<Issue>();
		try {
			String qry = "";
			
			if (CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					|| CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = "select distinct work_id as work_id_fk ,work_name,work_short_name "
						+ "from contract c "
						+ "LEFT JOIN work w ON work_id_fk = w.work_id "
						+ "where contract_status_fk IN('In Progress','Not Started') ";
			} else {
				qry = "SELECT distinct work_id as work_id_fk ,work_name,work_short_name "
						+ "FROM contract c "
						+ "LEFT JOIN work w ON work_id_fk = w.work_id "
						+ "where contract_status_fk IN('In Progress','Not Started') ";
			}
			
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			

			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " AND (hod_user_id_fk = ? or dy_hod_user_id_fk = ? " 
						+ "or contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from structure_contract_responsible_people where responsible_people_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from fob_contract_responsible_people where fob_id_fk in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?)) "
						+ ") "; 
						
				arrSize = arrSize + 5;
			}

			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			qry = qry + " order by w.work_id asc";

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getContractsListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "";
			if (CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					|| CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = "select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk,"
						+ "hod_user_id_fk,dy_hod_user_id_fk,contract_type_fk " 
						+ "from contract c "
						+ "where contract_status_fk IN('In Progress','Not Started','Not Awarded') ";
			} else {
				qry = "SELECT contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk,"
						+ "hod_user_id_fk,dy_hod_user_id_fk,contract_type_fk "
						+ "FROM contract c "
						+ "where contract_status_fk IN('In Progress','Not Started','Not Awarded') ";
			}
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ? ";
				arrSize++;
			}

			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " AND (hod_user_id_fk = ? or dy_hod_user_id_fk = ? " 
						+ "or contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from structure_contract_responsible_people where responsible_people_id_fk = ?) " 
						+ "or contract_id in(select contract_id_fk from fob_contract_responsible_people where fob_id_fk in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?)) "
						+ ")"; 
						
				arrSize = arrSize + 5;
			}

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			qry = qry + " group by contract_id,contract_name,contract_short_name,work_id_fk,hod_user_id_fk,dy_hod_user_id_fk,contract_type_fk order by contract_id asc ";
			
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addIssue(Issue obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String issue_id = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String qry = "INSERT INTO issue"
					+ "(contract_id_fk,title,date,location,reported_by,responsible_person,"
					+ "priority_fk,category_fk,status_fk,assigned_date,corrective_measure,resolved_date,escalated_to,remarks,"
					+ "zonal_railway_fk,other_organization,other_org_resposible_person_name,other_org_resposible_person_designation,escalation_date,created_by_user_id_fk,created_date,description,la_id,structure,component) "
					+ "VALUES "
					+ "(:contract_id_fk,:title,:date,:location,:reported_by,:responsible_person,:"
					+ "priority_fk,:category_fk,:status_fk,:assigned_date,:corrective_measure,:resolved_date,:escalated_to,:remarks,"
					+ ":zonal_railway_fk,:other_organization,:other_org_resposible_person_name,:other_org_resposible_person_designation,:escalation_date,:created_by_user_id_fk,CURRENT_TIMESTAMP,:description,:la_id,:structure,:component)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = template.update(qry, paramSource, keyHolder);
			if (count > 0) {
				issue_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setIssue_id(issue_id);
				flag = true;

				String fileQry = "INSERT INTO issue_files (file_name,issue_id_fk,issue_file_type_fk,created_date)VALUES(:file_name,:issue_id,:issue_file_type_fk,CURRENT_TIMESTAMP)";

				int arraySize = 0;
				if (!StringUtils.isEmpty(obj.getIssueFileNames()) && obj.getIssueFileNames().length > 0) {
					obj.setIssueFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssueFileNames()));
					if (arraySize < obj.getIssueFileNames().length) {
						arraySize = obj.getIssueFileNames().length;
					}
				}

				if (!StringUtils.isEmpty(obj.getIssue_file_types()) && obj.getIssue_file_types().length > 0) {
					obj.setIssue_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssue_file_types()));
					if (arraySize < obj.getIssue_file_types().length) {
						arraySize = obj.getIssue_file_types().length;
					}
				}
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = obj.getIssueFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())) {
						String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + issue_id
								+ File.separator;
						String fileName = obj.getIssueFileNames()[i];
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Issue fileObj = new Issue();
						fileObj.setFile_name(fileName);
						fileObj.setIssue_file_type_fk(obj.getIssue_file_types()[i]);
						fileObj.setIssue_id(issue_id);
						paramSource = new BeanPropertySqlParameterSource(fileObj);
						template.update(fileQry, paramSource);
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Issues");
				formHistory.setForm_name("Add Issue");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("Added issue: "+obj.getTitle());
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				
				history_flag = addIssueInHistory(obj,template);

				String issue_status = obj.getStatus_fk();
				String reported_by_email_id = obj.getReported_by_email_id();
				sendEmailWithIssueStatusAlert(issue_id, issue_status, reported_by_email_id, obj.getExisting_status_fk(), null,
						null,"0");

			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	private boolean addIssueInHistory(Issue obj, NamedParameterJdbcTemplate template) throws Exception {
		boolean flag = false;
		try {
			
			if(!StringUtils.isEmpty(obj.getCorrective_measure()))
					{
						if(!obj.getCorrective_measure().equals(obj.getComment()) && StringUtils.isEmpty(obj.getExisting_status_fk())) {			
							obj.setComment(obj.getCorrective_measure());
						}
					}
			if(!StringUtils.isEmpty(obj.getRemarks_new()) && "Escalated".equalsIgnoreCase(obj.getExisting_status_fk())) {
				obj.setComment(obj.getRemarks_new());
			}
			if(!StringUtils.isEmpty(obj.getEscalated_to())) {
				obj.setAssigned_person_user_id_fk(obj.getEscalated_to());
			}else if(!StringUtils.isEmpty(obj.getResponsible_person())) {
				obj.setAssigned_person_user_id_fk(obj.getResponsible_person());
			}else{
				obj.setAssigned_person_user_id_fk(obj.getDy_hod_user_id_fk());
			}
			if(!StringUtils.isEmpty(obj.getStatus_fk()) 
					&& obj.getStatus_fk().equals(obj.getExisting_status_fk()) ) {
				obj.setStatus_fk("Updated");
			}
			/*if(!"Closed".equals(obj.getStatus_fk()) && !StringUtils.isEmpty(obj.getResponsible_person())
					&& obj.getResponsible_person().equals(obj.getExisting_responsible_person()) ) {
				obj.setStatus_fk("Updated");
			}*/
			if("Closed".equals(obj.getStatus_fk()) ) {
				//obj.setStatus_fk(obj.getStatus_fk());
				obj.setAssigned_person_user_id_fk(null);
			}
			String qry = "INSERT INTO issue_history(issue_id_fk,issue_status_fk,assigned_person_user_id_fk,comment,created_by) "
					+ "VALUES "
					+ "(:issue_id,:status_fk,:assigned_person_user_id_fk,:comment,:created_by_user_id_fk)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = template.update(qry, paramSource);
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public Issue getIssue(Issue obj) throws Exception {
		Issue iObj = null;
		try {
			String qry = "select issue_id,contract_id_fk,title,FORMAT(date,'dd-MM-yyyy') AS date,location,reported_by,responsible_person,c.department_fk,"
					+ "priority_fk,category_fk,status_fk,corrective_measure,FORMAT(resolved_date,'dd-MM-yyyy') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,c.contract_short_name,project_id_fk,project_name,i.zonal_railway_fk,r.railway_name,other_organization,"
					+ "FORMAT(escalation_date,'dd-MM-yyyy') AS escalation_date,FORMAT(assigned_date,'dd-MM-yyyy') AS assigned_date, "
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,i.status_fk as existing_status_fk,other_org_resposible_person_name,other_org_resposible_person_designation,description,structure,component  "
					+ "from issue i " + "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk  = r.railway_id "
					+ "where issue_id = ? ";
			int arrSize = 1;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				qry = qry + " and priority_fk = ?";
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

			Object[] pValues = new Object[arrSize];

			int i = 0;
			pValues[i++] = obj.getIssue_id();
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				pValues[i++] = obj.getPriority_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}

			iObj = (Issue) jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));

			if (!StringUtils.isEmpty(iObj)) {
				String filesQry = "select file_name,issue_id_fk as issue_id,id as issue_file_id,issue_file_type_fk from issue_files where issue_id_fk = ? ";
				List<Issue> objsList = jdbcTemplate.query(filesQry, new Object[] { obj.getIssue_id() },
						new BeanPropertyRowMapper<Issue>(Issue.class));
				if (!StringUtils.isEmpty(objsList)) {
					iObj.setIssueFilesList(objsList);
				}

				String fileNamesQry = "select STRING_AGG(file_name,',') as attachments from issue_files where issue_id_fk = ? ";
				Issue fileNames = jdbcTemplate.queryForObject(fileNamesQry, new Object[] { obj.getIssue_id() },
						new BeanPropertyRowMapper<Issue>(Issue.class));
				if (!StringUtils.isEmpty(fileNames)) {
					iObj.setAttachments(fileNames.getAttachments());
				}

				if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
					if (obj.getUser_id().equals(iObj.getResponsible_person())
							|| obj.getUser_id().equals(iObj.getEscalated_to())
							|| obj.getUser_id().equals(iObj.getHod_user_id_fk())
							|| obj.getUser_id().equals(iObj.getDy_hod_user_id_fk())
							|| obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN)) {
						iObj.setReadonlyForm(false);
					} else {
						iObj.setReadonlyForm(true);
					}

				}

				if (!StringUtils.isEmpty(obj.getMessage_id())) {
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
					String msgUpdateqry = "UPDATE messages SET read_time=CURRENT_TIMESTAMP where message_id = :message_id";

					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
					template.update(msgUpdateqry, paramSource);
				}

			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return iObj;
	}

	@Override
	public boolean updateIssue(Issue obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String priority = obj.getPriority_fk();
			String status_fk = obj.getStatus_fk();
			obj.setStatus_fk("");obj.setPriority_fk("");
			Issue issue = getIssue(obj);
			obj.setStatus_fk(status_fk);obj.setPriority_fk(priority);
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String qry = "UPDATE issue SET "
					+ "title=:title,date=:date,location=:location,reported_by=:reported_by,responsible_person=:responsible_person,"
					+ "priority_fk=:priority_fk,category_fk=:category_fk,status_fk=:status_fk,corrective_measure=:corrective_measure,resolved_date=:resolved_date,escalated_to=:escalated_to,"
					+ "remarks=:remarks,zonal_railway_fk=:zonal_railway_fk,"
					+ "other_organization=:other_organization,other_org_resposible_person_name=:other_org_resposible_person_name,other_org_resposible_person_designation=:other_org_resposible_person_designation,"
					+ "escalation_date=:escalation_date,assigned_date=:assigned_date,description=:description,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP,structure=:structure,component=:component " + "where issue_id = :issue_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = template.update(qry, paramSource);
			if (count > 0) {
				flag = true;				
				
				int arraySize = 0;
				if (!StringUtils.isEmpty(obj.getIssueFileNames()) && obj.getIssueFileNames().length > 0) {
					obj.setIssueFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssueFileNames()));
					if (arraySize < obj.getIssueFileNames().length) {
						arraySize = obj.getIssueFileNames().length;
					}
				}

				if (!StringUtils.isEmpty(obj.getIssue_file_types()) && obj.getIssue_file_types().length > 0) {
					obj.setIssue_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssue_file_types()));
					if (arraySize < obj.getIssue_file_types().length) {
						arraySize = obj.getIssue_file_types().length;
					}
				}
				
				if (!StringUtils.isEmpty(obj.getIssue_file_ids()) && obj.getIssue_file_ids().length > 0) {
					obj.setIssue_file_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssue_file_ids()));
					if (arraySize < obj.getIssue_file_ids().length) {
						arraySize = obj.getIssue_file_ids().length;
					}
				}
				
				String placeholders = "";
				String issue_file_ids = "";
				for (int i = 0; i < arraySize; i++) {
					if(!StringUtils.isEmpty(obj.getIssue_file_ids()) && obj.getIssue_file_ids().length > 0 && !StringUtils.isEmpty(obj.getIssue_file_ids()[i])) {
						placeholders = placeholders + "?,";
						issue_file_ids = issue_file_ids + obj.getIssue_file_ids()[i] + ",";
					}
				}
				
				if (!StringUtils.isEmpty(placeholders)) {
					placeholders = org.apache.commons.lang3.StringUtils.chop(placeholders);					
					issue_file_ids = org.apache.commons.lang3.StringUtils.chop(issue_file_ids);

					String deleteFilesQry = "delete from issue_files where id not in("+issue_file_ids+") and issue_id_fk = :issue_id";
					Issue fileObj = new Issue();
					fileObj.setIssue_id(obj.getIssue_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					template.update(deleteFilesQry, paramSource);
				}else {
					String deleteFilesQry = "delete from issue_files where  issue_id_fk = :issue_id";
					Issue fileObj = new Issue();
					fileObj.setIssue_id(obj.getIssue_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);
					template.update(deleteFilesQry, paramSource);
				}

				String insertFileQry = "INSERT INTO issue_files (file_name,issue_id_fk,issue_file_type_fk,created_date)VALUES(:file_name,:issue_id,:issue_file_type_fk,CURRENT_TIMESTAMP)";
				String updateFileQry = "UPDATE issue_files set file_name=:file_name,issue_id_fk=:issue_id,issue_file_type_fk=:issue_file_type_fk WHERE id=:issue_file_id";
				boolean newFileAdded = false;
				for (int i = 0; i < arraySize; i++) {
					MultipartFile multipartFile = obj.getIssueFiles()[i];
					if ((null != multipartFile && !multipartFile.isEmpty())
							|| !StringUtils.isEmpty(obj.getIssueFileNames()[i])) {
						long fileLength = multipartFile.getSize();
						if(fileLength > 0) {newFileAdded = true;}
						String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + obj.getIssue_id()
								+ File.separator;
						String fileName = obj.getIssueFileNames()[i];
						String issue_file_id = null;
						if(!StringUtils.isEmpty(obj.getIssue_file_ids()) && obj.getIssue_file_ids().length > 0) {
							 issue_file_id = obj.getIssue_file_ids()[i];
						}
						if (null != multipartFile && !multipartFile.isEmpty()) {
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						}
						Issue fileObj = new Issue();
						fileObj.setFile_name(fileName);
						fileObj.setIssue_file_type_fk(obj.getIssue_file_types()[i]);
						fileObj.setIssue_file_id(issue_file_id);
						fileObj.setIssue_id(obj.getIssue_id());
						paramSource = new BeanPropertySqlParameterSource(fileObj);
						if(!StringUtils.isEmpty(issue_file_id)) {
							template.update(updateFileQry, paramSource);
						}else {
							template.update(insertFileQry, paramSource);
						}
					}
				}
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Issues");
				formHistory.setForm_name("Update Issue");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Updated issue: "+obj.getTitle());
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				int count_old = 0,count_new = 0;
				String issue_id = obj.getIssue_id();
				String issue_status = obj.getStatus_fk();
				String existing_status_fk = obj.getExisting_status_fk();
				String reported_by_email_id = obj.getReported_by_email_id();
				String existing_responsible_person = obj.getExisting_responsible_person();
				String existing_escalated_to = obj.getExisting_escalated_to();
				if(!StringUtils.isEmpty(issue)){
					issue.setDate(DateParser.parse(issue.getDate()));
					issue.setResolved_date(DateParser.parse(issue.getResolved_date()));
					issue.setEscalation_date(DateParser.parse(issue.getEscalation_date()));
					issue.setAssigned_date(DateParser.parse(issue.getAssigned_date()));
					count_old = issue.getIssueFilesList().size();
					count_new = obj.getIssueFileNames().length;
					
					if(((!StringUtils.isEmpty(obj.getDescription())) && !obj.getDescription().equals(issue.getDescription())) 
							|| ((!StringUtils.isEmpty(obj.getPriority_fk()) && !obj.getPriority_fk().equals(issue.getPriority_fk()))) 
							|| ((!StringUtils.isEmpty(obj.getCorrective_measure()) && !obj.getCorrective_measure().equals(issue.getCorrective_measure())))
							 || ((!StringUtils.isEmpty(obj.getZonal_railway_fk()) && !obj.getZonal_railway_fk().equals(issue.getZonal_railway_fk()))) 
							 || ((!StringUtils.isEmpty(obj.getOther_organization()) && !obj.getOther_organization().equals(issue.getOther_organization())))
							 || ((!StringUtils.isEmpty(obj.getOther_org_resposible_person_name()) && !obj.getOther_org_resposible_person_name().equals(issue.getOther_org_resposible_person_name()))) 
							 || ((!StringUtils.isEmpty(obj.getOther_org_resposible_person_designation()) && !obj.getOther_org_resposible_person_designation().equals(issue.getOther_org_resposible_person_designation())))
							 || ((!StringUtils.isEmpty(obj.getStatus_fk()) && !obj.getStatus_fk().equals(issue.getStatus_fk()))) 
							 || ((!StringUtils.isEmpty(obj.getDate()) && !obj.getDate().equals(issue.getDate())))
							 || ((!StringUtils.isEmpty(obj.getAssigned_date()) && !obj.getAssigned_date().equals(issue.getAssigned_date())))
							 || ((!StringUtils.isEmpty(obj.getEscalation_date()) && !obj.getEscalation_date().equals(issue.getEscalation_date())))
							 || ((!StringUtils.isEmpty(obj.getEscalated_to()) && !obj.getEscalated_to().equals(issue.getEscalated_to())))
							 || ((!StringUtils.isEmpty(obj.getResponsible_person()) && !obj.getResponsible_person().equals(issue.getResponsible_person())))
							 ||  newFileAdded == true) {
						history_flag = addIssueInHistory(obj,template);
						sendEmailWithIssueStatusAlert(issue_id, issue_status, reported_by_email_id, existing_status_fk,
								existing_responsible_person, existing_escalated_to,"1");
					}
				}
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}
	
	public void sendEmailWithIssueStatusAlert1(String issue_id, String issue_status,String issueDetailsUrl, String today_date, String current_year) {
	    try {
			String emailsQry = "select i.issue_id,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,w.work_name,c.contract_name,i.category_fk,i.priority_fk,i.title,i.location,i.corrective_measure,i.remarks,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "u2.email_id as responsible_person_email_id,u3.email_id as escalated_to_email_id,"
					+ "u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,u5.user_name as dyhod_name,"
					+ "i.responsible_person as responsible_person_user_id,i.escalated_to as escalated_to_user_id,"
					+ "c.hod_user_id_fk as contract_hod_user_id,c.dy_hod_user_id_fk as contract_dyhod_user_id,"
					+ "u1.email_id as created_by_email_id,i.created_by_user_id_fk,other_org_resposible_person_name,other_org_resposible_person_designation,(select  distinct top 1 email_id from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%AEN%') as aen_email,\r\n" + 
					"\r\n" + 
					"(select  distinct top 1 email_id from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_email,\r\n" + 
					"	\r\n" + 
					"(select  distinct top 1 email_id from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%SSE%') as sse_email,\r\n" + 
					"(select  distinct top 1 user_name from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%AEN%') as aen_name,\r\n" + 
					"\r\n" + 
					"(select  distinct top 1 user_name from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_name,\r\n" + 
					"	\r\n" + 
					"(select  distinct top 1 user_name from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%SSE%') as sse_name  "
					+ "from issue i " + "left outer join [user] u1 on i.created_by_user_id_fk = u1.user_id "
					+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u4 on c.hod_user_id_fk = u4.user_id "
					+ "left outer join [user] u5 on c.dy_hod_user_id_fk = u5.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "where issue_id = ? ";
			
			String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


			Object[] pValues = new Object[] { issue_id };

			Issue iObj = (Issue) jdbcTemplate.queryForObject(emailsQry, pValues,
		            new BeanPropertyRowMapper<Issue>(Issue.class));
		    
		    String emailSubject = "Occurrence of Issue in " + iObj.getContract_short_name() + ", raised on " + iObj.getDate();

		    // Determine email recipients
		    StringBuilder mailTo = new StringBuilder();
		    StringBuilder mailCC = new StringBuilder();
		    
		    // Determine the recipient name (PE, AEN/AM or DyHOD if any name is missing)
		    String recipientName = iObj.getPe_name() + "," + iObj.getSse_name() + " and " + iObj.getAen_name();
		    
		    if (iObj.getPe_name() == null || iObj.getPe_name().isEmpty() ||
		        iObj.getSse_name() == null || iObj.getSse_name().isEmpty() ||
		        iObj.getAen_name() == null || iObj.getAen_name().isEmpty()) {
		        recipientName = iObj.getDyhod_name();  // Use Dyhod_name if any of the previous names are missing
		        mailTo.append(iObj.getContract_dyhod_email_id()).append(",");
		    }

		    // Add PE, AEN/AM, SSE to 'To'
		    if (iObj.getPe_email() != null && !iObj.getPe_email().isEmpty()) {
		        mailTo.append(iObj.getPe_email()).append(",");
		    }
		    if (iObj.getAen_mail() != null && !iObj.getAen_mail().isEmpty()) {
		        mailTo.append(iObj.getAen_mail()).append(",");
		    }
		    if (iObj.getSse_email() != null && !iObj.getSse_email().isEmpty()) {
		        mailTo.append(iObj.getSse_email()).append(",");
		    }

		    // Add DyHOD and Contractor to 'CC'
		    if (iObj.getContract_dyhod_email_id() != null && !iObj.getContract_dyhod_email_id().isEmpty()) {
		        mailCC.append(iObj.getContract_dyhod_email_id()).append(",");
		    }
		    if (iObj.getContractor_email() != null && !iObj.getContractor_email().isEmpty()) {
		        mailCC.append(iObj.getContractor_email()).append(",");
		    }

		    // Remove trailing commas
		    if (mailTo.length() > 0) {
		        mailTo.setLength(mailTo.length() - 1);
		    }
		    if (mailCC.length() > 0) {
		        mailCC.setLength(mailCC.length() - 1);
		    }

		    String mailBody = "Dear " + recipientName+ ",\n\n" +
		    	    "We would like to inform you that a new issue has been added to the MRVC-PMIS portal by " + iObj.getContractor_name() + ". Below are the details of the issue:\n\n" +
		    	    "Work Details: " + iObj.getWork_name() + "\n" +
		    	    "Contract Details: " + iObj.getContract_short_name() + "\n" +
		    	    "Issue Raised On: " + iObj.getDate() + "\n" +
		    	    "Reported By: " + iObj.getReported_by() + "\n" +
		    	    "Issue Description: " + iObj.getDescription() + "\n" +
		    	    "Category: " + iObj.getCategory_fk() + "; Priority: " + iObj.getPriority_fk() + "\n" +
		    	    "Organization Responsible for Issue: " + iObj.getZonal_railway_fk() + "\n" +
		    	    "Target Date of Resolution: " + iObj.getDate() + "\n\n" +
		    	    "The concerned team is expected to attend to the issue at the earliest and take the necessary actions.\n\n" +
		    	    "<a href='http://203.153.40.44:90/pmis_qa/check-issue/" + iObj.getIssue_id() + "'>Click Here</a> for more details on the issue.\n\n" +
		    	    "Thank you for your attention to this matter.\n\n" +
		    	    "Regards,\nMRVC-PMIS Team.\n" +
		    	    "[Company Logo]\n";

		    // Create and configure Mail object
		    Mail mail = new Mail();
		    mail.setMailTo(mailTo.toString());
		    mail.setMailCc(mailCC.toString());
		    mail.setMailSubject(emailSubject);
		    mail.setMailContent(mailBody);
		    mail.setMailSubject("Occurrence of Issue in " + iObj.getContract_short_name() + ", raised on " + iObj.getDate());

		    // Log email details
		    logger.info("Preparing email for issue ID: " + iObj.getIssue_id());
		    logger.info("Mail To: " + mailTo);
		    logger.info("Mail CC: " + mailCC);
		    logger.info("Email Subject: " + emailSubject);
		    logger.info("Email Body: \n" + mailBody);

		    // Send email
		    if (mailTo.length() > 0) {
		        EMailSender emailSender = new EMailSender();
		        emailSender.sendEmailWithIssueStatusAlert(mail, iObj, today_date, current_year);
		        logger.info("Email sent successfully for issue ID: " + iObj.getIssue_id());
		    } else {
		        logger.warn("No recipients found for the email. Skipping email for issue ID: " + iObj.getIssue_id());
		    }

		} catch (Exception e) {
		    logger.error("Failed to send email for issue ID: " + issue_id, e);
		}
	    
	}


	public void sendEmailWithIssueStatusAlert(String issue_id, String issue_status, String reported_by_email_id,
			String existing_status_fk, String existing_responsible_person, String existing_escalated_to,String action)
			throws Exception {

		try {

			String emailsQry = "select i.issue_id,zonal_railway_fk,description,date,contractor_name,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,w.work_name,c.contract_name,i.category_fk,i.priority_fk,i.title,i.location,i.corrective_measure,i.remarks,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "u2.email_id as responsible_person_email_id,u3.email_id as escalated_to_email_id,"
					+ "u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,u5.user_name as dyhod_name,u4.user_name as hod_name, "
					+ "i.responsible_person as responsible_person_user_id,i.escalated_to as escalated_to_user_id,"
					+ "c.hod_user_id_fk as contract_hod_user_id,c.dy_hod_user_id_fk as contract_dyhod_user_id,"
					+ "u1.email_id as created_by_email_id,i.created_by_user_id_fk,other_org_resposible_person_name,other_org_resposible_person_designation,(select email_id from issue i\r\n" + 
					"\r\n" + 
					"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
					"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
					"on a.executive_user_id_fk = b.user_id\r\n" + 
					"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
					"\r\n" + 
					"where i.contract_id_fk = c.contract_id  and issue_id="+issue_id+"  and designation in('XEN')) as aen_email,\r\n" + 
					"\r\n" + 
					"(select  distinct top 1 email_id from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_email,\r\n" + 
					"	\r\n" + 
					"\r\n" + 
					"(select email_id from issue i\r\n" + 
					"\r\n" + 
					"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
					"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
					"on a.executive_user_id_fk = b.user_id\r\n" + 
					"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
					"\r\n" + 
					"where i.contract_id_fk = c.contract_id  and issue_id="+issue_id+"  and designation in('SSE')) as sse_email,\r\n" + 
					"(select user_name from issue i\r\n" + 
					"\r\n" + 
					"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
					"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
					"on a.executive_user_id_fk = b.user_id\r\n" + 
					"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
					"\r\n" + 
					"where i.contract_id_fk = c.contract_id  and issue_id="+issue_id+"  and designation in('XEN')) as aen_name,\r\n" + 
					"\r\n" + 
					"(select  distinct top 1 user_name from contract_executive c1\r\n" + 
					"\r\n" + 
					"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
					"\r\n" + 
					"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_name,\r\n" + 
					"	\r\n" + 
					"\r\n" + 
					"(select user_name from issue i\r\n" + 
					"\r\n" + 
					"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
					"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
					"on a.executive_user_id_fk = b.user_id\r\n" + 
					"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
					"\r\n" + 
					"where i.contract_id_fk = c.contract_id  and issue_id="+issue_id+"  and designation in('SSE')) as sse_name,c1.email_id as contractor_email  "
					+ "from issue i " + "left outer join [user] u1 on i.created_by_user_id_fk = u1.user_id "
					+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u4 on c.hod_user_id_fk = u4.user_id "
					+ "left outer join [user] u5 on c.dy_hod_user_id_fk = u5.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ " left outer join contractor c1 on c1.contractor_id = c.contractor_id_fk "
					+ "where issue_id = ? ";

			Object[] pValues = new Object[] { issue_id };

			Issue iObj = (Issue) jdbcTemplate.queryForObject(emailsQry, pValues,
					new BeanPropertyRowMapper<Issue>(Issue.class));
			if (!StringUtils.isEmpty(iObj)) {

				/*********************************************************************************************/
				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

				String issueMessageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
						+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";

				String isuue_status = null;
				if (!StringUtils.isEmpty(iObj.getStatus_fk())) {
					isuue_status = iObj.getStatus_fk().toLowerCase();
				}
				String message1 = "A new issue against " + iObj.getContract_id_fk() + " has been " + isuue_status
						+ " to you";

				String message2 = "An issue against " + iObj.getContract_id_fk() + " has been " + isuue_status;

				String message3 = "An issue against " + iObj.getContract_id_fk() + " has been ";

				if ("Assigned".equals(iObj.getStatus_fk()) && !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
						&& !iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
					message3 = message3 + isuue_status;
				} else if ("Escalated".equals(iObj.getStatus_fk())
						&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
						&& !iObj.getEscalated_to_user_id().equals(existing_escalated_to)) {
					message3 = message3 + isuue_status;
				} else {
					message3 = message3 + "updated";
				}

				String hod_user_id = "", dy_hod_user_id = "", responsible_person_user_id = "",
						escalated_to_user_id = "", created_by_user_id = "";
				if ("Raised".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					created_by_user_id = iObj.getCreated_by_user_id_fk();
				} else if ("Assigned".equals(iObj.getStatus_fk())) {
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
				} else if ("Escalated".equals(iObj.getStatus_fk())) {
					escalated_to_user_id = iObj.getEscalated_to_user_id();
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
				} else if ("Closed".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					escalated_to_user_id = iObj.getEscalated_to_user_id();
					created_by_user_id = iObj.getCreated_by_user_id_fk();
				}
				String redirect_url = null;
				if ("Closed".equals(iObj.getStatus_fk())) {
					redirect_url = "/get-issue/" + iObj.getIssue_id();
				} else {
					redirect_url = "/get-issue/" + iObj.getIssue_id();
				}
				String message_type = "Issue";

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Raised".equals(iObj.getStatus_fk())
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if (!StringUtils.isEmpty(dy_hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id) && !(hod_user_id.equals(created_by_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(created_by_user_id) && !(created_by_user_id.equals(dy_hod_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(created_by_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				}
				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk())
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if (!StringUtils.isEmpty(hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(responsible_person_user_id) && !(responsible_person_user_id.equals(dy_hod_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				} else if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk())
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
						&& iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				} else if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk())
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
						&& !iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				}

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk())
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if (!StringUtils.isEmpty(escalated_to_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id) && !(hod_user_id.equals(escalated_to_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				} else if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk())
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getEscalated_to_user_id())
						&& iObj.getEscalated_to_user_id().equals(existing_escalated_to)) {
					if (!StringUtils.isEmpty(escalated_to_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id) && !(hod_user_id.equals(escalated_to_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				} else if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk())
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getEscalated_to_user_id())
						&& !iObj.getEscalated_to_user_id().equals(existing_escalated_to)) {
					if (!StringUtils.isEmpty(escalated_to_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(hod_user_id) && !(hod_user_id.equals(escalated_to_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				}

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && "Closed".equals(iObj.getStatus_fk())
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if (!StringUtils.isEmpty(hod_user_id) && !(hod_user_id.equals(escalated_to_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(dy_hod_user_id) && !(dy_hod_user_id.equals(responsible_person_user_id))) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(responsible_person_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(escalated_to_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if (!StringUtils.isEmpty(created_by_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(created_by_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				}

				/*********************************************************************************************/
				String mailTo = "";
				String mailCC = "";
				String mailBodyHeader = "";

				if ("Raised".equals(iObj.getStatus_fk())) {
					if (!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						//mailTo = mailTo + iObj.getContract_dyhod_email_id() + ",";
					}
					if (!StringUtils.isEmpty(reported_by_email_id)) {
						//mailCC = mailCC + reported_by_email_id + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						//mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
					}
				} else if ("Assigned".equals(iObj.getStatus_fk())) {
					if (!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						//mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						//mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						//mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
					}
				} else if ("Escalated".equals(iObj.getStatus_fk())) {
					if (!StringUtils.isEmpty(iObj.getEscalated_to_email_id())) {
						//mailTo = mailTo + iObj.getEscalated_to_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						//mailCC = mailCC + iObj.getResponsible_person_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						//mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						//mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
					}
				} else if ("Closed".equals(iObj.getStatus_fk())) {
					if (!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						//mailTo = mailTo + iObj.getContract_hod_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						//mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getEscalated_to_email_id())) {
						//mailCC = mailCC + iObj.getEscalated_to_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						//mailCC = mailCC + iObj.getResponsible_person_email_id() + ",";
					}
					if (!StringUtils.isEmpty(iObj.getCreated_by_email_id())) {
						//mailCC = mailCC + iObj.getCreated_by_email_id() + ",";
					}
				}

				if (!StringUtils.isEmpty(mailTo)) {
					//mailTo = org.apache.commons.lang3.StringUtils.chop(mailTo);
				}

				if (!StringUtils.isEmpty(mailCC)) {
					//mailCC = org.apache.commons.lang3.StringUtils.chop(mailCC);
				}

				

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && !iObj.getStatus_fk().equals(existing_status_fk)
						&& !iObj.getStatus_fk().equals("Closed")) {
					mailBodyHeader = mailBodyHeader + "A new ";
				} else {
					mailBodyHeader = mailBodyHeader + "An ";
				}
				mailBodyHeader = mailBodyHeader + "issue against ";

				if (!StringUtils.isEmpty(iObj.getContract_id_fk())) {
					mailBodyHeader = mailBodyHeader + iObj.getContract_id_fk();
				}
				mailBodyHeader = mailBodyHeader + " has been ";

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && !StringUtils.isEmpty(existing_status_fk)
						&& iObj.getStatus_fk().equals(existing_status_fk)) {
					if ("Assigned".equals(iObj.getStatus_fk())
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& !iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
						mailBodyHeader = mailBodyHeader + isuue_status;
					} else if ("Escalated".equals(iObj.getStatus_fk())
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& !iObj.getEscalated_to_user_id().equals(existing_escalated_to)) {
						mailBodyHeader = mailBodyHeader + isuue_status;
					} else {
						mailBodyHeader = mailBodyHeader + "updated ";
					}
				} else {
					mailBodyHeader = mailBodyHeader + isuue_status;
					if ("Assigned".equals(iObj.getStatus_fk()) || "Escalated".equals(iObj.getStatus_fk())) {
						mailBodyHeader = mailBodyHeader + " to you ";
					}
				}
				
				
				
			    String recipientName = iObj.getPe_name() + "," + iObj.getSse_name() + " and " + iObj.getAen_name();
			    
			    if (iObj.getPe_name() == null || iObj.getPe_name().isEmpty() ||
			        iObj.getSse_name() == null || iObj.getSse_name().isEmpty() ||
			        iObj.getAen_name() == null || iObj.getAen_name().isEmpty()) {
			        recipientName = iObj.getDyhod_name();  // Use Dyhod_name if any of the previous names are missing
			        mailTo=mailTo+iObj.getContract_dyhod_email_id()+",";
			    }

			    // Add PE, AEN/AM, SSE to 'To'
			    if (iObj.getPe_email() != null && !iObj.getPe_email().isEmpty()) {
			    	mailTo=mailTo+iObj.getPe_email()+",";
			    }
			    if (iObj.getAen_mail() != null && !iObj.getAen_mail().isEmpty()) {
			    	mailTo=mailTo+iObj.getAen_mail()+",";
			    }
			    if (iObj.getSse_email() != null && !iObj.getSse_email().isEmpty()) {
			    	mailTo=mailTo+iObj.getSse_email()+",";
			    }

			    // Add DyHOD and Contractor to 'CC'
			    if (iObj.getContract_dyhod_email_id() != null && !iObj.getContract_dyhod_email_id().isEmpty()) {
			        mailCC=mailCC+iObj.getContract_dyhod_email_id()+",";
			    }
			    if (iObj.getContractor_email() != null && !iObj.getContractor_email().isEmpty()) {
			    	mailCC=mailCC+iObj.getContractor_email()+",";
			    }			
			    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			    iObj.setCurdate(currentDate);
				
			    
				String header="Dear "+recipientName+",\r\n" + 
						"\r\n" + 
						"We would like to inform you that a new issue has been added to the MRVC-PMIS portal by "+iObj.getContractor_name()+". Below are the details of the issue:\r\n" + 
						"";
				
				if ("Raised".equals(iObj.getStatus_fk())) {
					iObj.setMail_body_header(header);
					iObj.setHod_name(recipientName);
				}


				String emailSubject = "PMIS Issue Notification - Issue ";

				if (!StringUtils.isEmpty(iObj.getStatus_fk()) && !StringUtils.isEmpty(existing_status_fk)
						&& iObj.getStatus_fk().equals(existing_status_fk)) {
					if ("Assigned".equals(iObj.getStatus_fk())
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& !iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
						emailSubject = emailSubject + iObj.getStatus_fk();
					} else if ("Escalated".equals(iObj.getStatus_fk())
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& !iObj.getEscalated_to_user_id().equals(existing_escalated_to)) {
						emailSubject = emailSubject + iObj.getStatus_fk();
					} else if ("Closed".equals(iObj.getStatus_fk())) {
						emailSubject = emailSubject + iObj.getStatus_fk();
					} else {
						emailSubject = emailSubject + "Updated";
					}
				} else {
					emailSubject = emailSubject + issue_status;
				}
				
				
				if ("Raised".equals(iObj.getStatus_fk()) && "1".equals(action)) 
				{
					emailSubject = "PMIS Issue Notification - Issue has Re-Opened";
					iObj.setAction("Re-Opened");
				}
				else
				{
					iObj.setAction("added");
				}

				Mail mail = new Mail();
				mail.setMailTo(mailTo);
				mail.setMailCc(mailCC);
				mail.setMailBcc(CommonConstants.BCC_MAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("IssueStatusAlert.vm");

				SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
				String today_date = monthFormat.format(new Date()).toUpperCase();

				SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
				String current_year = yearFormat.format(new Date()).toUpperCase();

				if (!StringUtils.isEmpty(mailTo)) {
					EMailSender emailSender = new EMailSender();
					logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + mailTo + ": Start ");
					logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + mailCC + ": Start ");
					emailSender.sendEmailWithIssueStatusAlert(mail, iObj, today_date, current_year);
					logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + mailTo + ": end ");
					logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + mailCC + ": end ");
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@Override
	public boolean deleteIssue(Issue obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Issue> getContractsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name " + "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}

			qry = qry + " GROUP BY contract_id_fk,c.contract_id,contract_name,contract_short_name";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getDepartmentsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT c.department_fk,department,department_name " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.department_fk is not null and c.department_fk <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}

			qry = qry + " GROUP BY c.department_fk,department,department_name";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getCategoryListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT category_fk " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ " where category_fk is not null and category_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry + " GROUP BY category_fk ";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getStatusListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT status_fk " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where status_fk is not null and status_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry + " GROUP BY status_fk ";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getWorksListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,w.work_short_name " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where work_id_fk is not null and work_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry + " GROUP BY work_id,w.work_short_name ";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getResponsiblePersonsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT responsible_person,u.user_name " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on i.responsible_person = u.designation "
					+ "where responsible_person is not null and responsible_person <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry + " GROUP BY responsible_person,u.user_name ";

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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getHODListFilterInIssue(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT hod_user_id_fk,u.designation " + "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry
						+ " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ? "
						+ "or contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ?) "
						+ "or location in(select fob_id_fk from fob_contract_responsible_people where responsible_people_id_fk = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			qry = qry + " group by u.designation,hod_user_id_fk ORDER BY case when u.designation='ED Civil' then 1 " + 
					"   when u.designation='CPM I' then 2 " + 
					"   when u.designation='CPM II' then 3" + 
					"   when u.designation='CPM III' then 4 " + 
					"   when u.designation='CPM V' then 5" + 
					"   when u.designation='CE' then 6 " + 
					"   when u.designation='ED S&T' then 7 " + 
					"   when u.designation='CSTE' then 8" + 
					"   when u.designation='GM Electrical' then 9" + 
					"   when u.designation='CEE Project I' then 10" + 
					"   when u.designation='CEE Project II' then 11" + 
					"   when u.designation='ED Finance & Planning' then 12" + 
					"   when u.designation='AGM Civil' then 13" + 
					"   when u.designation='DyCPM Civil' then 14" + 
					"   when u.designation='DyCPM III' then 15" + 
					"   when u.designation='DyCPM V' then 16" + 
					"   when u.designation='DyCE EE' then 17" + 
					"   when u.designation='DyCE Badlapur' then 18" + 
					"   when u.designation='DyCPM Pune' then 19" + 
					"   when u.designation='DyCE Proj' then 20" + 
					"   when u.designation='DyCEE I' then 21" + 
					"   when u.designation='DyCEE Projects' then 22" + 
					"   when u.designation='DyCEE PSI' then 23" + 
					"   when u.designation='DyCSTE I' then 24" + 
					"   when u.designation='DyCSTE IT' then 25" + 
					"   when u.designation='DyCSTE Projects' then 26" + 
					"   when u.designation='XEN Consultant' then 27" + 
					"   when u.designation='AEN Adhoc' then 28" + 
					"   when u.designation='AEN Project' then 29" + 
					"   when u.designation='AEN P-Way' then 30" + 
					"   when u.designation='AEN' then 31" + 
					"   when u.designation='Sr Manager Signal' then 32 " + 
					"   when u.designation='Manager Signal' then 33" + 
					"   when u.designation='Manager Civil' then 34 " + 
					"   when u.designation='Manager OHE' then 35" + 
					"   when u.designation='Manager GS' then 36" + 
					"   when u.designation='Manager Finance' then 37" + 
					"   when u.designation='Planning Manager' then 38" + 
					"   when u.designation='Manager Project' then 39" + 
					"   when u.designation='Manager' then 40 " + 
					"   when u.designation='SSE' then 41" + 
					"   when u.designation='SSE Project' then 42" + 
					"   when u.designation='SSE Works' then 43" + 
					"   when u.designation='SSE Drg' then 44" + 
					"   when u.designation='SSE BR' then 45" + 
					"   when u.designation='SSE P-Way' then 46" + 
					"   when u.designation='SSE OHE' then 47" + 
					"   when u.designation='SPE' then 48" + 
					"   when u.designation='PE' then 49" + 
					"   when u.designation='JE' then 50" + 
					"   when u.designation='Demo-HOD-Elec' then 51" + 
					"   when u.designation='Demo-HOD-Engg' then 52" + 
					"   when u.designation='Demo-HOD-S&T' then 53" + 
					"" + 
					"   end asc" ;


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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}

			if (!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getReportedByList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as reported_by_user_id,designation as reported_by_designation " + "FROM [user] "
					+ "where user_type_fk = ? group by user_id,designation order by designation";

			Object[] pValues = new Object[] { CommonConstants.USER_TYPE_HOD };

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getResponsiblePersonList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as responsible_person_user_id,designation as responsible_person_designation "
					+ "FROM [user] " + "where user_type_fk = ? ";
			int arrSize = 1;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_name())) {
				qry = qry + "and department_fk = (select department from department where department_name = ?)";
				arrSize++;
			}
			qry = qry + "group by user_id,designation order by designation";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE_DYHOD;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_name())) {
				pValues[i++] = obj.getDepartment_name();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getEscalatedToList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as escalated_to_user_id,designation as escalated_to_designation "
					+ "FROM [user] "
					+ "where (user_type_fk = ? or user_type_fk = ?) group by user_id,designation order by designation";

			Object[] pValues = new Object[] { CommonConstants.USER_TYPE_HOD, CommonConstants.USER_TYPE_MANAGEMENT };

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean readIssueMessage(String message_id) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String msgUpdateqry = "UPDATE messages SET read_time = CURRENT_TIMESTAMP where message_id = :message_id";
			
			Issue obj = new Issue();
			obj.setMessage_id(message_id);
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = template.update(msgUpdateqry, paramSource);
			if (count > 0) {
				flag = true;
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<Issue> getOtherOrganizationsList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT issue_other_organization as other_organization from issue_other_organization";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssueFileTypes() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT issue_file_type from issue_file_type";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getPendingIssues(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,title,FORMAT(date,'dd-MMM-yy') AS date,location,reported_by,responsible_person,c.department_fk,"
					+ "priority_fk,category_fk,status_fk,corrective_measure,FORMAT(resolved_date,'dd-MMM-yy') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,i.zonal_railway_fk,r.railway_name,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,railway_name,FORMAT(assigned_date,'dd-MMM-yy') AS assigned_date,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,other_organization,FORMAT(i.created_date,'dd-MMM-yy') AS created_date,FORMAT(escalation_date,'dd-MMM-yy') AS escalation_date,"
					+ "other_org_resposible_person_name,other_org_resposible_person_designation,description,i.modified_by,FORMAT(i.modified_date,'dd-MM-yyyy') as modified_date,(select count(*) from issue i 					left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "					left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "					LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "					left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "					LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "					LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "					LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "					LEFT OUTER JOIN railway r ON i.zonal_railway_fk  = r.railway_id "
					+ "					where issue_id is not null ) as total_issues " 
					+ "from issue i "
					+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "left outer join [user] u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk  = r.railway_id "
					+ "where issue_id is not null and status_fk<>'Closed' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}


			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getStructures(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select distinct structure from activities_view ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "where contract_id = ? ";
				arraSize++;
			}
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getComponents(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select distinct component from activities_view ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "where contract_id = ? ";
				arraSize++;
			}
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getStructureListForIssue(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select distinct structure from p6_activities a " + 
					"left join structure s on s.structure_id=a.structure_id_fk  ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "where a.contract_id_fk = ? ";
				arraSize++;
			}
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getComponentListForIssue(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select distinct component from p6_activities a " + 
					"left join structure s on s.structure_id=a.structure_id_fk  where 0=0 ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + "and a.contract_id_fk = ? ";
				arraSize++;
			}
			if (!StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + "and structure = ? ";
				arraSize++;
			}
			
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}

			if (!StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Issue> getUnresolvedIssues() {
    	List<Issue> issuesList = null;
		String qry = "select i.issue_id,zonal_railway_fk,description,date,contractor_name,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,w.work_name,c.contract_name,i.category_fk,i.priority_fk,i.title,i.location,i.corrective_measure,i.remarks,u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,u2.email_id as responsible_person_email_id,u3.email_id as escalated_to_email_id,u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,u5.user_name as dyhod_name,u4.user_name as hod_name,i.responsible_person as responsible_person_user_id,i.escalated_to as escalated_to_user_id,c.hod_user_id_fk as contract_hod_user_id,c.dy_hod_user_id_fk as contract_dyhod_user_id,u1.email_id as created_by_email_id,i.created_by_user_id_fk,other_org_resposible_person_name,other_org_resposible_person_designation,(select top 1 email_id from issue i\r\n" + 
				"\r\n" + 
				"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
				"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
				"on a.executive_user_id_fk = b.user_id\r\n" + 
				"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
				"\r\n" + 
				"where i.contract_id_fk = c.contract_id  and issue_id=i.issue_id  and designation in('XEN')) as aen_email,\r\n" + 
				"\r\n" + 
				"(select  distinct top 1 email_id from contract_executive c1\r\n" + 
				"\r\n" + 
				"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
				"\r\n" + 
				"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_email,\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"(select top 1 email_id from issue i\r\n" + 
				"\r\n" + 
				"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
				"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
				"on a.executive_user_id_fk = b.user_id\r\n" + 
				"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
				"\r\n" + 
				"where i.contract_id_fk = c.contract_id  and issue_id=i.issue_id  and designation in('SSE')) as sse_email,\r\n" + 
				"(select top 1 user_name from issue i\r\n" + 
				"\r\n" + 
				"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
				"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
				"on a.executive_user_id_fk = b.user_id\r\n" + 
				"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
				"\r\n" + 
				"where i.contract_id_fk = c.contract_id  and issue_id=i.issue_id  and designation in('XEN')) as aen_name,\r\n" + 
				"\r\n" + 
				"(select  distinct top 1 user_name from contract_executive c1\r\n" + 
				"\r\n" + 
				"left join [user] u on u.user_id=c1.executive_user_id_fk\r\n" + 
				"\r\n" + 
				"where user_id is not null and contract_id_fk=c.contract_id and designation like '%Project Engineer%') as pe_name,\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"(select top 1 user_name from issue i\r\n" + 
				"\r\n" + 
				"inner join (select contract_id_fk, department_id_fk,executive_user_id_fk,user_name,\r\n" + 
				"designation, department_fk,user_type_fk,user_role_name_fk,email_id from contract_executive a left join [user] b\r\n" + 
				"on a.executive_user_id_fk = b.user_id\r\n" + 
				"where department_fk not in('Fin','Plan')) m on m.contract_id_fk=i.contract_id_fk\r\n" + 
				"\r\n" + 
				"where i.contract_id_fk = c.contract_id  and issue_id=i.issue_id  and designation in('SSE')) as sse_name,\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"(select top 1 email_id as dt_email from [user] where designation like '%Director Technical%') as dt_email,\r\n" + 
				"\r\n" + 
				"(select top 1 email_id as dp_email from [user] where designation like '%DIR Project%') as dp_email,\r\n" + 
				"\r\n" + 
				"(select top 1 email_id as cmd_email from [user] where designation like '%CMD%') as cmd_email,\r\n" + 
				"\r\n" + 
				"(select top 1 user_name as dt_name from [user] where designation like '%Director Technical%') as dt_name,\r\n" + 
				"\r\n" + 
				"(select top 1 user_name as dp_name from [user] where designation like '%DIR Project%') as dp_name,\r\n" + 
				"(select top 1 user_name as cmd_name from [user] where designation like '%CMD%') as cmd_name,i.created_date,c1.email_id as contractor_email  \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"from issue i left outer join [user] u1 on i.created_by_user_id_fk = u1.user_id left outer join [user] u2 on i.responsible_person = u2.user_id left outer join [user] u3 on i.escalated_to = u3.user_id LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id left outer join [user] u4 on c.hod_user_id_fk = u4.user_id left outer join [user] u5 on c.dy_hod_user_id_fk = u5.user_id LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id  left outer join contractor c1 on c1.contractor_id = c.contractor_id_fk WHERE status_fk = 'Raised' and date is not null and i.created_date>='2024-12-24'";   	
    	
        issuesList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<>(Issue.class));
        return issuesList;
	}
	private String getEmailSubject(int daysPending, Issue issue) {
	    switch (daysPending) {
	        case 7:
	            return "Reminder-1: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 7 days of reporting";
	        case 14:
	            return "Reminder-2: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 14 days of reporting";
	        case 21:
	            return "Reminder-3: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 21 days of reporting";
	        case 28:
	            return "Reminder-4: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 28 days of reporting";
	        default:
	            return "Reminder: Notification of Unresolved Issue in " + issue.getContract_short_name();
	    }
	}

	private String getEmailBody(int daysPending, Issue issue) {
	    String workDetails = issue.getWork_name();
	    String contractDetails = issue.getContract_name();
	    String reportedBy = issue.getReported_by();
	    String description = issue.getDescription();
	    String category = issue.getCategory();
	    String priority = issue.getPriority();
	    String responsibleOrg = issue.getZonal_railway_fk();
	    String actionTaken = issue.getCorrective_measure();
	    String targetDate = issue.getDate();

	    String bodyTemplate = 
	        "Respected %s,\n\n" +
	        "This is a reminder that the following issue, reported on %s, has not been resolved till date.\n\n" +
	        "Work Details: %s\n" +
	        "Contract Details: %s\n" +
	        "Reported By: %s\n" +
	        "Issue Description: %s\n" +
	        "Category: %s; Priority: %s\n" +
	        "Organization Responsible for Issue: %s\n" +
	        "Action Taken: %s\n" +
	        "Target Date of Resolution: %s\n\n\n\\n" +
	        "We kindly request your immediate attention to this matter to ensure timely resolution.\n" +
	        "Click Here for more details on the issue.\n\n" +
	        "Thank you for your prompt action.\n\n" +
	        "Regards,\n" +
	        "MRVC-PMIS Team.\n" +
	        "[Company Logo]\n";

	    String recipientName = getRecipientName(daysPending, issue);
	    String reportingDate = issue.getDate().toString();  // Assuming issue.getReportingDate() returns a LocalDate

	    return String.format(bodyTemplate, recipientName, reportingDate, workDetails, contractDetails, reportedBy, description,
	            category, priority, responsibleOrg, actionTaken, targetDate);
	}


	private String getRecipientEmail(int daysPending, Issue issue) {
	    switch (daysPending) {
	        case 7:
	            return issue.getContract_dyhod_email_id();
	        case 14:
	            return issue.getContract_hod_email_id();
	        case 21:
	            return issue.getDp_email() + ", " + issue.getDt_email();  
	        case 28:
	            return issue.getCmd_email();
	        default:
	            return issue.getContract_dyhod_email_id();
	    }
	}

	private String getCCEmail(int daysPending, Issue issue) {
	    switch (daysPending) {
	        case 7:
	            return issue.getContract_hod_email_id() + ", " + issue.getContractor_email();
	        case 14:
	            return issue.getDp_email() + ", " + issue.getDt_email() + ", " + issue.getContractor_email();
	        case 21:
	            return issue.getCmd_email() + ", " + issue.getContractor_email();
	        case 28:
	            return issue.getContractor_email();
	        default:
	            return "";
	    }
	}

	private String getRecipientName(int daysPending, Issue issue) {
	    switch (daysPending) {
	        case 7:
	            return issue.getDyhod_name();
	        case 14:
	            return issue.getHod_name();
	        case 21:
	            return issue.getDp_name() + " and " + issue.getDt_name();  
	        case 28:
	            return issue.getCmd_name();
	        default:
	            return issue.getDyhod_name();
	    }
	}
	@Override
	public boolean sendReminderEmail(Issue issue, int daysPending) {
	    try {
	        // Determine the email details based on the daysPending
	        String subject = getEmailSubject(daysPending, issue);
	        String body = getEmailBody(daysPending, issue);
	        String to = getRecipientEmail(daysPending, issue);
	        String cc = getCCEmail(daysPending, issue);
	        
			String header="Dear "+getRecipientName(daysPending, issue)+",\r\n" + 
					"\r\n" + 
					"We would like to inform you that a new issue has been added to the MRVC-PMIS portal by "+issue.getContractor_name()+". Below are the details of the issue:\r\n" + 
					"";
			
			if ("Raised".equals(issue.getStatus_fk())) {
				issue.setMail_body_header(header);
				issue.setHod_name(getRecipientName(daysPending, issue));
				issue.setCurdate(issue.getCreated_date());
			}	        
	        
			Mail mail = new Mail();
			mail.setMailTo(to);
			mail.setMailCc(cc);
			mail.setMailBcc(CommonConstants.BCC_MAIL);
			mail.setMailSubject(subject);
			mail.setTemplateName("IssueStatusAlert.vm");

			SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
			String today_date = monthFormat.format(new Date()).toUpperCase();

			SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
			String current_year = yearFormat.format(new Date()).toUpperCase();

			if (!StringUtils.isEmpty(to)) {
				EMailSender emailSender = new EMailSender();
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + to + ": Start ");
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + cc + ": Start ");
				emailSender.sendEmailWithIssueStatusAlert(mail, issue, today_date, current_year);
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + to + ": end ");
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + cc + ": end ");
			}	        
	        return true;
	    } catch (Exception e) {
	        logger.error("Failed to send reminder email for Issue ID: {}, Days Pending: {}");
	        return false;
	    }
	}
	
	@Override
	public List<Issue> getActionTakens(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select comment,created_by,created_date,u.user_name from issue_history i left join [user] u on u.user_id=i.created_by where 0=0  ";
			int arraSize = 0;
			if (!StringUtils.isEmpty(obj.getIssue_id())) {
				qry = qry + "and issue_id_fk = ? ";
				arraSize++;
			}
		
			Object[] pValues = new Object[arraSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj.getIssue_id())) {
				pValues[i++] = obj.getIssue_id();
			}
			
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}	

}
