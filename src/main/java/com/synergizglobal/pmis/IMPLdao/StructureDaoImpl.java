package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

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
import com.synergizglobal.pmis.Idao.StructureDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.User;

@Repository
public class StructureDaoImpl implements StructureDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<Structure> getProjectsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT w.project_id_fk,p.project_name " + "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}

			qry = qry + " GROUP BY w.project_id_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getWorksListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.work_id_fk,w.work_short_name " + "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}

			qry = qry + " GROUP BY s.work_id_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getContractsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.contract_id_fk,c.contract_short_name " + "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			qry = qry + " GROUP BY s.contract_id_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getDepartmentsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.department_fk,d.department_name " + "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY s.department_fk ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Structure obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {

			String qry = "select count(DISTINCT(s.work_id_fk)) as total_records " + "from structure s "
					+ "left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ "where structure_id is not null and s.status <> 'Inactive' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(searchParameter)) {
				qry = qry
						+ " and (w.project_id_fk like ? or p.project_name like ? or s.work_id_fk like ? or w.work_short_name like ? "
						+ " or structure_type_fk like ? or structure like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}

			if (!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
			}
			totalRecords = jdbcTemplate.queryForObject(qry, pValues, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.structure_id,s.status,s.structure,w.work_name,w.work_short_name,w.project_id_fk,p.project_name,s.work_id_fk,"
					+ "GROUP_CONCAT(CONCAT(structure_type_fk, ' - ', count) SEPARATOR ',') as structure_type_fk "
					+ "FROM ( SELECT structure_id,status,structure, work_id_fk, structure_type_fk, COUNT(structure_type_fk) AS count FROM structure "
					+ "JOIN work ON work.work_id = structure.work_id_fk where status = 'Active' GROUP BY work_id_fk, structure_type_fk) s "
					+ "left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci   "
					+ "left join project p on w.project_id_fk = p.project_id  "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(searchParameter)) {
				qry = qry
						+ " and (w.project_id_fk like ? or p.project_name like ? or s.work_id_fk like ? or w.work_short_name like ?  "
						+ "or structure_type_fk like ? or structure like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if (!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + "GROUP BY s.work_id_fk ORDER BY structure_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}

			if (!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
				pValues[i++] = "%" + searchParameter + "%";
			}
			if (!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getProjectsListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getWorkListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w " + "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
					+ "where work_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}

			qry = qry + " order by work_id asc";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getContractListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select contract_id as contract_id_fk,contract_name,contract_short_name,c.work_id_fk,w.work_short_name,w.project_id_fk,project_name "
					+ "from `contract` c " + "LEFT JOIN `work` w ON c.work_id_fk = work_id "
					+ "LEFT JOIN `project` p ON w.project_id_fk = project_id " + "where contract_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}

			qry = qry + " order by contract_id asc";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructuresListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select structure_type from structure_type";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getDepartmentsListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select department as department_fk,department_name,contract_id_code from department";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Structure getStructuresListDetails(Structure obj) throws Exception {
		Structure structure = null;
		try {
			String qry = "select structure_id, s.work_id_fk, structure_type_fk, structure,w.work_name,p.project_name,w.work_short_name,w.project_id_fk, "
					+ "s.work_status_fk, s.target_date, s.estimated_cost, s.estimated_cost_units, s.construction_start_date, s.revised_completion, s.remarks "
					+ "from structure s " + "left join work w on s.work_id_fk = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id where structure_id is not null";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			structure = (Structure) jdbcTemplate.queryForObject(qry, pValues,
					new BeanPropertyRowMapper<Structure>(Structure.class));
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getStructure_id())) {
				List<Structure> objsList = null;
				String qryDetails = "select structure_id, structure_type_fk,structure_name,structure "
						+ " from structure s  where s.work_id_fk = ? and s.status <> 'Inactive' group by structure_type_fk";

				objsList = jdbcTemplate.query(qryDetails, new Object[] { structure.getWork_id_fk() },
						new BeanPropertyRowMapper<Structure>(Structure.class));
				structure.setStructureList(objsList);
				
				//Inactive List //
				
				List<Structure> objsListI = null;
				String qryDetailsI = "select structure_id, structure_type_fk,structure_name,structure "
						+ " from structure s  where s.work_id_fk = ? and s.status = 'Inactive' group by structure_type_fk";

				objsListI = jdbcTemplate.query(qryDetailsI, new Object[] { structure.getWork_id_fk() },
						new BeanPropertyRowMapper<Structure>(Structure.class));
				structure.setStructureListInactive(objsListI);
				if (!StringUtils.isEmpty(objsListI)) {
					for (Structure list : structure.getStructureListInactive()) {
						String qryI = "select structure_id,structure_name, structure  from structure s where s.work_id_fk = ? and s.structure_type_fk = ? and s.status = 'Inactive' ";
						List<Structure> objListI = jdbcTemplate.query(qryI,
								new Object[] { structure.getWork_id_fk(), list.getStructure_type_fk() },
								new BeanPropertyRowMapper<Structure>(Structure.class));

						list.setStructureSubListInactive(objListI);
					}
				}
				if (!StringUtils.isEmpty(objsList)) {
					for (Structure list : structure.getStructureList()) {

						String qry2 = "select structure_id,structure_name, structure  from structure s where s.work_id_fk = ? and s.structure_type_fk = ? and s.status <> 'Inactive' ";
						List<Structure> objList1 = jdbcTemplate.query(qry2,
								new Object[] { structure.getWork_id_fk(), list.getStructure_type_fk() },
								new BeanPropertyRowMapper<Structure>(Structure.class));

						list.setStructureSubList(objList1);
					}
						/*if(!StringUtils.isEmpty(objList1)) {
							for(Structure subdetails : list.getStructureSubList()) {
								String subdetailsQry ="select structure_id, structure,s.work_status_fk,s.structure_name,cast(s.latitude as CHAR) as latitude,cast(s.longitude as CHAR) as longitude, DATE_FORMAT(s.target_date,'%d-%m-%Y') AS target_date, s.estimated_cost, s.estimated_cost_units, DATE_FORMAT(s.construction_start_date,'%d-%m-%Y') AS construction_start_date, DATE_FORMAT(s.revised_completion,'%d-%m-%Y') AS revised_completion, s.remarks"
										+ " from structure s where  structure_id = ? ";
								List<Structure> subdetailsList = jdbcTemplate.query( subdetailsQry,new Object[] {subdetails.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
								subdetails.setStructureSubList2(subdetailsList);
							}
						}
						if(!StringUtils.isEmpty(objList1)) {
							for(Structure executives : list.getStructureSubList()) {
								String executivesQry ="select DISTINCT structure_id_fk as structure_id,contract_id_fk,contract_short_name from structure_contract_responsible_people sp left join contract c on c.contract_id = sp.contract_id_fk where  structure_id_fk = ? ";
								List<Structure> executivesList = jdbcTemplate.query( executivesQry,new Object[] {executives.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
								executives.setExecutivesList(executivesList);
								for(Structure peopleList : executives.getExecutivesList()) {
									String peopleQry ="select id, structure_id_fk, contract_id_fk,designation,user_name, responsible_people_id_fk from structure_contract_responsible_people sp left join user u on u.user_id=sp.responsible_people_id_fk where contract_id_fk = ? and  structure_id_fk = ? ";
									List<Structure> peopleLists = jdbcTemplate.query( peopleQry,new Object[] {peopleList.getContract_id_fk(),peopleList.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
									peopleList.setResponsiblePeopleLists(peopleLists);
								}
							}
						}
						if(!StringUtils.isEmpty(objList1)) {
							for(Structure executives : list.getStructureSubList()) {
								String executivesQry ="select id, structure_id_fk, contract_id_fk,designation,user_name, responsible_people_id_fk from structure_contract_responsible_people sp left join user u on u.user_id=sp.responsible_people_id_fk where  structure_id_fk = ? ";
								List<Structure> executivesList = jdbcTemplate.query( executivesQry,new Object[] {executives.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
								executives.setResponsiblePeopleLists(executivesList);
							}
						}
						if(!StringUtils.isEmpty(objList1)) {
							for(Structure details : list.getStructureSubList()) {
								String detailsQry ="select id, structure_id_fk, structure_detail, value as structure_value from structure_details sd where  structure_id_fk = ? ";
								List<Structure> detailsList = jdbcTemplate.query( detailsQry,new Object[] {details.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
								details.setStructureDetailsList(detailsList);
							}
						}
						if(!StringUtils.isEmpty(objList1)) {
							for(Structure documents : list.getStructureSubList()) {
								String documentsQry ="select id, structure_id_fk, name, attachment,structure_file_type_fk,created_date from structure_documents d where  structure_id_fk = ? ";
								List<Structure> documentsList = jdbcTemplate.query( documentsQry,new Object[] {documents.getStructure_id()}, new BeanPropertyRowMapper<Structure>(Structure.class));
								documents.setDocumentsList(documentsList);
							}
						}*/
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return structure;
	}

	@Override
	public boolean addStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement executivesInsertStmt = null;
		PreparedStatement detailsInsertStmt = null;
		PreparedStatement documentsStmt = null;
		int j = 0, dCount = 0, fCount = 0;
		;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = { 0 };
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			/*String insert_qry = "INSERT into  structure ( work_id_fk, structure_type_fk, "
					+ "structure,work_status_fk,structure_name,latitude,longitude,target_date,estimated_cost,estimated_cost_units,construction_start_date,revised_completion,remarks) "
					+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";*/

			String insert_qry = "INSERT into  structure ( work_id_fk, structure_type_fk, "
					+ "structure,structure_name,status) " + "VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry, Statement.RETURN_GENERATED_KEYS);
			int arraySize = 0;

			if (!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
				obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
				if (arraySize < obj.getStructures().length) {
					arraySize = obj.getStructures().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getStructure_names()) && obj.getStructure_names().length > 0) {
				obj.setStructure_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_names()));
				if (arraySize < obj.getStructure_names().length) {
					arraySize = obj.getStructure_names().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getLatitudes()) && obj.getLatitudes().length > 0) {
				obj.setLatitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLatitudes()));
				if (arraySize < obj.getLatitudes().length) {
					arraySize = obj.getLatitudes().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getLongitudes()) && obj.getLongitudes().length > 0) {
				obj.setLongitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLongitudes()));
				if (arraySize < obj.getLongitudes().length) {
					arraySize = obj.getLongitudes().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getWork_status_fks()) && obj.getWork_status_fks().length > 0) {
				obj.setWork_status_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getWork_status_fks()));
				if (arraySize < obj.getWork_status_fks().length) {
					arraySize = obj.getWork_status_fks().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getTarget_dates()) && obj.getTarget_dates().length > 0) {
				obj.setTarget_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getTarget_dates()));
				if (arraySize < obj.getTarget_dates().length) {
					arraySize = obj.getTarget_dates().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getEstimated_costs()) && obj.getEstimated_costs().length > 0) {
				obj.setEstimated_costs(CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_costs()));
				if (arraySize < obj.getEstimated_costs().length) {
					arraySize = obj.getEstimated_costs().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getEstimated_cost_unitss()) && obj.getEstimated_cost_unitss().length > 0) {
				obj.setEstimated_cost_unitss(
						CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_cost_unitss()));
				if (arraySize < obj.getEstimated_cost_unitss().length) {
					arraySize = obj.getEstimated_cost_unitss().length;
				}
			}

			if (!StringUtils.isEmpty(obj.getConstruction_start_dates())
					&& obj.getConstruction_start_dates().length > 0) {
				obj.setConstruction_start_dates(
						CommonMethods.replaceEmptyByNullInSringArray(obj.getConstruction_start_dates()));
				if (arraySize < obj.getConstruction_start_dates().length) {
					arraySize = obj.getConstruction_start_dates().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getRevised_completions()) && obj.getRevised_completions().length > 0) {
				obj.setRevised_completions(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevised_completions()));
				if (arraySize < obj.getRevised_completions().length) {
					arraySize = obj.getRevised_completions().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
				obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
				if (arraySize < obj.getRemarkss().length) {
					arraySize = obj.getRemarkss().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0
					&& !StringUtils.isEmpty(obj.getWork_id_fk()) && obj.getStructures().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					int p = 1;
					if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
							&& !StringUtils.isEmpty(obj.getStructures()[i])) {
						insertStmt.setString(p++, (obj.getWork_id_fk()));
						insertStmt.setString(p++,
								(obj.getStructure_type_fks().length > 0) ? obj.getStructure_type_fks()[i] : null);
						insertStmt.setString(p++, (obj.getStructures().length > 0) ? obj.getStructures()[i] : null);
						// insertStmt.setString(p++,(obj.getWork_status_fks().length > 0)?obj.getWork_status_fks()[i]:null);
						insertStmt.setString(p++,
								(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
						insertStmt.setString(p++, (CommonConstants.ACTIVE));
						//insertStmt.setString(p++,(obj.getLatitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLatitudes()[i]:null);
						// insertStmt.setString(p++,(obj.getLongitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLongitudes()[i]:null);
						// insertStmt.setString(p++,DateParser.parse((obj.getTarget_dates().length > 0)?obj.getTarget_dates()[i]:null));
						//insertStmt.setString(p++,(obj.getEstimated_costs().length > 0)?obj.getEstimated_costs()[i]:null);
						// insertStmt.setString(p++,(obj.getEstimated_costs().length > 0 && obj.getEstimated_cost_unitss().length > 0
						//	&& !StringUtils.isEmpty(obj.getEstimated_costs()[i]) && !StringUtils.isEmpty(obj.getEstimated_cost_unitss()[i]))?obj.getEstimated_cost_unitss()[i]:null);
						//insertStmt.setString(p++,DateParser.parse((obj.getConstruction_start_dates().length > 0)?obj.getConstruction_start_dates()[i]:null));
						//insertStmt.setString(p++,DateParser.parse((obj.getRevised_completions().length > 0)?obj.getRevised_completions()[i]:null));
						//insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
						insertStmt.addBatch();

						insertCount = insertStmt.executeBatch();
						rs = insertStmt.getGeneratedKeys();
						if (rs.next()) {
							String structure_id = rs.getString(1);
							obj.setStructure_id(structure_id);
						}
						/* if(insertCount.length > 0) {
							String executivesinsert_qry = "INSERT into  structure_contract_responsible_people ( structure_id_fk, contract_id_fk, responsible_people_id_fk) "
									+"VALUES (?,?,?)";
							executivesInsertStmt = con.prepareStatement(executivesinsert_qry);
							int executivesArrSize = 0;
							if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getContracts_id_fk().length > 0) {
								obj.setContracts_id_fk(CommonMethods.replaceEmptyByNullInSringArray(obj.getContracts_id_fk()));
								if(executivesArrSize < obj.getContracts_id_fk().length) {
									executivesArrSize = obj.getContracts_id_fk().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()) && obj.getResponsible_people_id_fks().length > 0) {
								obj.setResponsible_people_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_people_id_fks()));
								if(executivesArrSize < obj.getResponsible_people_id_fks().length) {
									executivesArrSize = obj.getResponsible_people_id_fks().length;
								}
							}
							if(arraySize == 1) {
								String joined = String.join(",", obj.getContracts_id_fk());
								String[] strArray = new String[] {joined};
								obj.setContracts_id_fk(strArray);
							}
							String[] firstArray = obj.getContracts_id_fk();
						
							if(!StringUtils.isEmpty(firstArray) && firstArray.length > 0 && !StringUtils.isEmpty(obj.getContracts_id_fk()[i])) {
							//firstArray =Arrays.stream(firstArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new); 
							    	List<String> contracts = null;
							    	if(firstArray[i].contains(",")) {
							    		contracts = new ArrayList<String>(Arrays.asList(firstArray[i].split(",")));
							    	}else {
							    		contracts = new ArrayList<String>(Arrays.asList(firstArray[i]));
							    	}
							    	if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getResponsible_people_id_fks().length > 0 ) {
							    		for(String cObj : contracts) {
							    			if(arraySize == 1 && !(firstArray[i].contains(","))) {
									    		String joined = String.join(",", obj.getResponsible_people_id_fks());
									    		String[] strArray = new String[] {joined};
									    		obj.setResponsible_people_id_fks(strArray);
									    	}
							    			if( !StringUtils.isEmpty(obj.getResponsible_people_id_fks()[j])) {
							    				List<String> executives = null;
									    		if(obj.getResponsible_people_id_fks()[j].contains(",")) {
									    			executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[j].split(",")));
										    	}else {
										    		executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[j]));
										    	}
								    			for(String eObj : executives) {
								    				if(!eObj.equals("null")) {
								    					int n = 1;
									    				if(!StringUtils.isEmpty(cObj) &&  !StringUtils.isEmpty(eObj)){
										    				executivesInsertStmt.setString(n++,(obj.getStructure_id()));
										    				executivesInsertStmt.setString(n++,(cObj.length() > 0)? cObj:null);
										    				executivesInsertStmt.setString(n++,(eObj.length() > 0)? eObj:null);
										    				executivesInsertStmt.addBatch();
									    				}
								    				}
								    			}
								    			executivesInsertStmt.executeBatch();
								    			j++;
							    			}else {
							    				j++;
							    			}
									    	
							    		}
							    	}
						}else {
								j++;
							}
							
							String structure_details_qry = "INSERT into  structure_details ( structure_id_fk, structure_detail, value) "
									+"VALUES (?,?,?)";
							detailsInsertStmt = con.prepareStatement(structure_details_qry);
							int detailsArrSize = 0;
							if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
								obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
								if(detailsArrSize < obj.getStructure_details().length) {
									detailsArrSize = obj.getStructure_details().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
								obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
								if(detailsArrSize < obj.getStructure_values().length) {
									detailsArrSize = obj.getStructure_values().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0 
									&& !StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0 ) {
								if(arraySize == 1) {
								String joined = String.join("", obj.getStructure_detailss());
								joined = joined.replaceAll("_",",_,");
								String[] strArray = new String[] {joined};
								obj.setStructure_detailss(strArray);
							}
								String[] detailsArray = obj.getStructure_detailss();
								if(!StringUtils.isEmpty(detailsArray) && detailsArray.length > 0 &&!StringUtils.isEmpty(detailsArray[i])) {
								//detailsArray =Arrays.stream(detailsArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new); 
								List<String> details = null;
							    	if(detailsArray[i].contains(",_,")) {
							    		details = new ArrayList<String>(Arrays.asList(detailsArray[i].split(",_,")));
							    	}else {
							    		details = new ArrayList<String>(Arrays.asList(detailsArray[i]));
							    	}
								for (String dObj : details) {
									    int param = 1;
									    if(!StringUtils.isEmpty(dObj) && !StringUtils.isEmpty(obj.getStructure_values()[dCount])){
									    	detailsInsertStmt.setString(param++,(obj.getStructure_id()));
									    	detailsInsertStmt.setString(param++,(dObj.length() > 0)? dObj:null);
									    	detailsInsertStmt.setString(param++,(obj.getStructure_values().length > 0)? obj.getStructure_values()[dCount]:null);
									    	detailsInsertStmt.addBatch();
									    }
									    detailsInsertStmt.executeBatch();
									    dCount++;
								}
								
							}else {
								 dCount++;
							}
							}
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
							LocalDateTime now = LocalDateTime.now();
							obj.setCreated_date(dtf.format(now));
							String document_insert_qry = "INSERT into  structure_documents ( structure_id_fk, attachment,structure_file_type_fk,name,created_date)"
									+ " VALUES (?,?,?,?,CURDATE())";
							documentsStmt = con.prepareStatement(document_insert_qry);
							int docArrSize = 0;
							
							if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
								obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
								if (docArrSize < obj.getStructure_file_types().length) {
									docArrSize = obj.getStructure_file_types().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructureDocumentNames()) && obj.getStructureDocumentNames().length > 0) {
								obj.setStructureDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDocumentNames()));
								if (docArrSize < obj.getStructureDocumentNames().length) {
									docArrSize = obj.getStructureDocumentNames().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructureFileNames()) && obj.getStructureFileNames().length > 0) {
								obj.setStructureFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureFileNames()));
								if (docArrSize < obj.getStructureFileNames().length) {
									docArrSize = obj.getStructureFileNames().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructure_file_ids()) && obj.getStructure_file_ids().length > 0) {
								obj.setStructure_file_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_ids()));
								if (docArrSize < obj.getStructure_file_ids().length) {
									docArrSize = obj.getStructure_file_ids().length;
								}
							}
							//structureFiless
								List<String> documents = null;
								if(!StringUtils.isEmpty(obj.getStructureFiless()) && obj.getStructureFiless().length > 0 && !StringUtils.isEmpty(obj.getStructureFiless()[i])) {
									if(arraySize == 1) {
							    		String joined = String.join("", obj.getStructureFiless());
							    		joined = joined.replaceAll("_",",_,");
							    		String[] strArray = new String[] {joined};
							    		obj.setStructureFiless(strArray);
							    	}
							    	if(obj.getStructureFiless()[i].contains(",_,")) {
							    		documents = new ArrayList<String>(Arrays.asList(obj.getStructureFiless()[i].split(",_,")));
							    	}else {
							    		documents = new ArrayList<String>(Arrays.asList(obj.getStructureFiless()[i]));
							    	}
									for (String fObj : documents) {
										int q=1;
											String fileName = null;
											if( !StringUtils.isEmpty(obj.getStructureFiles()[fCount])) {
												MultipartFile multipartFile = obj.getStructureFiles()[fCount];
												if ((null != multipartFile && !multipartFile.isEmpty())) {
													String saveDirectory = CommonConstants2.STRUCTURE_FILE_SAVING_PATH ;
													fileName = multipartFile.getOriginalFilename();
													if (null != multipartFile && !multipartFile.isEmpty()) {
														FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
													}
												}
											}if(!StringUtils.isEmpty(obj.getStructureFileNames()[fCount])) {
												fileName = obj.getStructureFileNames()[fCount];
											}
											if(!StringUtils.isEmpty(obj.getStructureFileNames()[fCount]) && (!StringUtils.isEmpty(obj.getStructureFiles()[fCount]))
													&& (!StringUtils.isEmpty(obj.getStructure_file_types()[fCount])) && (!StringUtils.isEmpty(obj.getStructureDocumentNames()[fCount]))) {
												documentsStmt.setString(q++,(obj.getStructure_id()));
												documentsStmt.setString(q++,(fileName));
												documentsStmt.setString(q++,(obj.getStructure_file_types()[fCount]));
												documentsStmt.setString(q++,(obj.getStructureDocumentNames()[fCount]));
												documentsStmt.addBatch();
												documentsStmt.executeBatch();
												fCount++;
											}else {
												fCount++;
											}
									}
								}else {
									fCount++;
							}
							}*/

					}
				}
				if (insertCount.length > 0) {
					flag = true;
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Works");
					formHistory.setForm_name("Add Structure");
					formHistory.setForm_action_type("Add");
					formHistory.setForm_details("New Structure for "+obj.getWork_id_fk() + " Created");
					formHistory.setWork_id_fk(obj.getWork_id_fk());
					//formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/
				}
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
			if (executivesInsertStmt != null) {
				executivesInsertStmt.close();
			}
			if (detailsInsertStmt != null) {
				detailsInsertStmt.close();
			}
			if (documentsStmt != null) {
				documentsStmt.close();
			}
		}
		return flag;
	}

	@Override
	public boolean updateStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement stmt = null;
		PreparedStatement executivesInsertStmt = null;
		PreparedStatement detailsInsertStmt = null;
		PreparedStatement documentsStmt = null;
		PreparedStatement updateStmt = null;
		int j = 0, dCount = 0, fCount = 0;
		;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = { 0 };
		int[] updateCount = { 0 };
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			con.setAutoCommit(false);
			/*	String updateQry = " update structure set "
						+ "structure = ?,work_status_fk = ?,structure_name = ?,latitude = ?,longitude = ?,target_date = ?,estimated_cost = ?,estimated_cost_units = ?,"
						+ "construction_start_date = ?,revised_completion = ?,remarks = ? where structure_id = ?";
				updateStmt = con.prepareStatement(updateQry);
				
				String insert_qry = "INSERT into  structure ( work_id_fk, structure_type_fk, "
						+ "structure,work_status_fk,structure_name,latitude,longitude,target_date,estimated_cost,estimated_cost_units,construction_start_date,"
						+ "revised_completion,remarks) "
						+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";*/

			String updateQry = " update structure set "
					+ "structure = ?,structure_name = ?,status = ?  where structure_id = ?";
			updateStmt = con.prepareStatement(updateQry);

			String insert_qry = "INSERT into structure ( work_id_fk, structure_type_fk, "
					+ "structure,structure_name,status) " + "VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry, Statement.RETURN_GENERATED_KEYS);
			int arraySize = 0;

			if (!StringUtils.isEmpty(obj.getStructure_names()) && obj.getStructure_names().length > 0) {
				obj.setStructure_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_names()));
				if (arraySize < obj.getStructure_names().length) {
					arraySize = obj.getStructure_names().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getLatitudes()) && obj.getLatitudes().length > 0) {
				obj.setLatitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLatitudes()));
				if (arraySize < obj.getLatitudes().length) {
					arraySize = obj.getLatitudes().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getLongitudes()) && obj.getLongitudes().length > 0) {
				obj.setLongitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLongitudes()));
				if (arraySize < obj.getLongitudes().length) {
					arraySize = obj.getLongitudes().length;
				}
			}

			if (!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
				obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
				if (arraySize < obj.getStructures().length) {
					arraySize = obj.getStructures().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getWork_status_fks()) && obj.getWork_status_fks().length > 0) {
				obj.setWork_status_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getWork_status_fks()));
				if (arraySize < obj.getWork_status_fks().length) {
					arraySize = obj.getWork_status_fks().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getTarget_dates()) && obj.getTarget_dates().length > 0) {
				obj.setTarget_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getTarget_dates()));
				if (arraySize < obj.getTarget_dates().length) {
					arraySize = obj.getTarget_dates().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getEstimated_costs()) && obj.getEstimated_costs().length > 0) {
				obj.setEstimated_costs(CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_costs()));
				if (arraySize < obj.getEstimated_costs().length) {
					arraySize = obj.getEstimated_costs().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getEstimated_cost_unitss()) && obj.getEstimated_cost_unitss().length > 0) {
				obj.setEstimated_cost_unitss(
						CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_cost_unitss()));
				if (arraySize < obj.getEstimated_cost_unitss().length) {
					arraySize = obj.getEstimated_cost_unitss().length;
				}
			}

			if (!StringUtils.isEmpty(obj.getConstruction_start_dates())
					&& obj.getConstruction_start_dates().length > 0) {
				obj.setConstruction_start_dates(
						CommonMethods.replaceEmptyByNullInSringArray(obj.getConstruction_start_dates()));
				if (arraySize < obj.getConstruction_start_dates().length) {
					arraySize = obj.getConstruction_start_dates().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getRevised_completions()) && obj.getRevised_completions().length > 0) {
				obj.setRevised_completions(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevised_completions()));
				if (arraySize < obj.getRevised_completions().length) {
					arraySize = obj.getRevised_completions().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
				obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
				if (arraySize < obj.getRemarkss().length) {
					arraySize = obj.getRemarkss().length;
				}
			}
			if (!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0
					&& !StringUtils.isEmpty(obj.getWork_id_fk()) && obj.getStructures().length > 0) {
				//List<Structure> structure_ids = getStructureIdsWithWork(obj.getWork_id_fk());
				/*		for(Structure structureIds : structure_ids){
							String id = structureIds.getStructure_id();
							String executivesInactiveQry = "DELETE from structure_contract_responsible_people  where structure_id_fk = ?";		 
							stmt = con.prepareStatement(executivesInactiveQry);
							stmt.setString(1,id);
							stmt.executeUpdate(); 
							if(stmt != null){stmt.close();}
							
							String detailsInactiveQry = "DELETE from structure_details  where structure_id_fk = ?";		 
							stmt = con.prepareStatement(detailsInactiveQry);
							stmt.setString(1,id);
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}
							
							String DocumentsInactiveQry = "DELETE from structure_documents  where structure_id_fk = ?";		 
							stmt = con.prepareStatement(DocumentsInactiveQry);
							stmt.setString(1,id);
							stmt.executeUpdate();
							if(stmt != null){stmt.close();}
						}*/
				String inactiveQry = "UPDATE structure set status = ?  where work_id_fk = ?";
				stmt = con.prepareStatement(inactiveQry);
				stmt.setString(1, CommonConstants.INACTIVE);
				stmt.setString(2, obj.getWork_id_fk());
				stmt.executeUpdate();
				if (stmt != null) {
					stmt.close();
				}

				for (int i = 0; i < arraySize; i++) {
					if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
							&& !StringUtils.isEmpty(obj.getStructures()[i])) {
						String sId = obj.getStructure_ids()[i];
						if (!StringUtils.isEmpty(sId)) {
							obj.setStructure_id(sId);
							int k = 1;
							if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
									&& !StringUtils.isEmpty(obj.getStructures()[i])) {
								updateStmt.setString(k++,
										(obj.getStructures().length > 0) ? obj.getStructures()[i] : null);
								// updateStmt.setString(k++,(obj.getWork_status_fks().length > 0)?obj.getWork_status_fks()[i]:null);
								updateStmt.setString(k++,
										(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
								updateStmt.setString(k++, (CommonConstants.ACTIVE));
								//updateStmt.setString(k++,(obj.getLatitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLatitudes()[i]:null);
								// updateStmt.setString(k++,(obj.getLongitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLongitudes()[i]:null);
								// updateStmt.setString(k++,DateParser.parse((obj.getTarget_dates().length > 0)?obj.getTarget_dates()[i]:null));
								// updateStmt.setString(k++,(obj.getEstimated_costs().length > 0)?obj.getEstimated_costs()[i]:null);
								// updateStmt.setString(k++,(obj.getEstimated_costs().length > 0 && obj.getEstimated_cost_unitss().length > 0
								//	&& !StringUtils.isEmpty(obj.getEstimated_costs()[i]) && !StringUtils.isEmpty(obj.getEstimated_cost_unitss()[i]))?obj.getEstimated_cost_unitss()[i]:null);
								// updateStmt.setString(k++,DateParser.parse((obj.getConstruction_start_dates().length > 0)?obj.getConstruction_start_dates()[i]:null));
								// updateStmt.setString(k++,DateParser.parse((obj.getRevised_completions().length > 0)?obj.getRevised_completions()[i]:null));
								// updateStmt.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
								updateStmt.setString(k++, (sId));
								updateStmt.addBatch();
							}
						} else {
							int p = 1;
							if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
									&& !StringUtils.isEmpty(obj.getStructures()[i])) {
								insertStmt.setString(p++, (obj.getWork_id_fk()));
								insertStmt.setString(p++,
										(obj.getStructure_type_fks().length > 0) ? obj.getStructure_type_fks()[i]
												: null);
								insertStmt.setString(p++,
										(obj.getStructures().length > 0) ? obj.getStructures()[i] : null);
								// insertStmt.setString(p++,(obj.getWork_status_fks().length > 0)?obj.getWork_status_fks()[i]:null);
								insertStmt.setString(p++,
										(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
								insertStmt.setString(p++, (CommonConstants.ACTIVE));
								// insertStmt.setString(p++,(obj.getLatitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLatitudes()[i]:null);
								//insertStmt.setString(p++,(obj.getLongitudes().length > 0 && !StringUtils.isEmpty(obj.getLongitudes()[i]))?obj.getLongitudes()[i]:null);
								//insertStmt.setString(p++,DateParser.parse((obj.getTarget_dates().length > 0)?obj.getTarget_dates()[i]:null));
								//insertStmt.setString(p++,(obj.getEstimated_costs().length > 0)?obj.getEstimated_costs()[i]:null);
								//insertStmt.setString(p++,(obj.getEstimated_costs().length > 0 && obj.getEstimated_cost_unitss().length > 0
								//&& !StringUtils.isEmpty(obj.getEstimated_costs()[i]) && !StringUtils.isEmpty(obj.getEstimated_cost_unitss()[i]))?obj.getEstimated_cost_unitss()[i]:null);
								// insertStmt.setString(p++,DateParser.parse((obj.getConstruction_start_dates().length > 0)?obj.getConstruction_start_dates()[i]:null));
								//insertStmt.setString(p++,DateParser.parse((obj.getRevised_completions().length > 0)?obj.getRevised_completions()[i]:null));
								//insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
								insertStmt.addBatch();
							}
						}

						insertCount = insertStmt.executeBatch();
						updateCount = updateStmt.executeBatch();
						rs = insertStmt.getGeneratedKeys();
						if (rs.next()) {
							String structure_id = rs.getString(1);
							obj.setStructure_id(structure_id);
						}
						/* if(insertCount.length > 0 || updateCount.length > 0) {
							String executivesinsert_qry = "INSERT into  structure_contract_responsible_people ( structure_id_fk, contract_id_fk, responsible_people_id_fk) "
									+"VALUES (?,?,?)";
							executivesInsertStmt = con.prepareStatement(executivesinsert_qry);
							int executivesArrSize = 0;
							if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getContracts_id_fk().length > 0) {
								obj.setContracts_id_fk(CommonMethods.replaceEmptyByNullInSringArray(obj.getContracts_id_fk()));
								if(executivesArrSize < obj.getContracts_id_fk().length) {
									executivesArrSize = obj.getContracts_id_fk().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getResponsible_people_id_fks()) && obj.getResponsible_people_id_fks().length > 0) {
								obj.setResponsible_people_id_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getResponsible_people_id_fks()));
								if(executivesArrSize < obj.getResponsible_people_id_fks().length) {
									executivesArrSize = obj.getResponsible_people_id_fks().length;
								}
							}
							if(arraySize == 1) {
								String joined = String.join(",", obj.getContracts_id_fk());
								String[] strArray = new String[] {joined};
								obj.setContracts_id_fk(strArray);
							}
							String[] firstArray = obj.getContracts_id_fk();
						
							if(!StringUtils.isEmpty(firstArray) && firstArray.length > 0 && !StringUtils.isEmpty(obj.getContracts_id_fk()[i])) {
						    	//firstArray =Arrays.stream(firstArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new); 
							    	List<String> contracts = null;
							    	if(firstArray[i].contains(",")) {
							    		contracts = new ArrayList<String>(Arrays.asList(firstArray[i].split(",")));
							    	}else {
							    		contracts = new ArrayList<String>(Arrays.asList(firstArray[i]));
							    	}
							    	if(!StringUtils.isEmpty(obj.getContracts_id_fk()) && obj.getResponsible_people_id_fks().length > 0 ) {
							    		for(String cObj : contracts) {
							    			if(arraySize == 1 && !(firstArray[i].contains(","))) {
									    		String joined = String.join(",", obj.getResponsible_people_id_fks());
									    		String[] strArray = new String[] {joined};
									    		obj.setResponsible_people_id_fks(strArray);
									    	}
							    			if( !StringUtils.isEmpty(obj.getResponsible_people_id_fks()[j])) {
							    				List<String> executives = null;
									    		if(obj.getResponsible_people_id_fks()[j].contains(",")) {
									    			executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[j].split(",")));
										    	}else {
										    		executives = new ArrayList<String>(Arrays.asList(obj.getResponsible_people_id_fks()[j]));
										    	}
								    			for(String eObj : executives) {
								    				if(!eObj.equals("null")) {
								    					int n = 1;
									    				if(!StringUtils.isEmpty(cObj) &&  !StringUtils.isEmpty(eObj)){
										    				executivesInsertStmt.setString(n++,(obj.getStructure_id()));
										    				executivesInsertStmt.setString(n++,(cObj.length() > 0)? cObj:null);
										    				executivesInsertStmt.setString(n++,(eObj.length() > 0)? eObj:null);
										    				executivesInsertStmt.addBatch();
									    				}
								    				}
								    			}
								    			executivesInsertStmt.executeBatch();
								    			j++;
							    			}else {
							    				j++;
							    			}
									    	
							    		}
							    	}
						    }else {
								j++;
							}
							
							String structure_details_qry = "INSERT into  structure_details ( structure_id_fk, structure_detail, value) "
									+"VALUES (?,?,?)";
							detailsInsertStmt = con.prepareStatement(structure_details_qry);
							int detailsArrSize = 0;
							if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0) {
								obj.setStructure_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_details()));
								if(detailsArrSize < obj.getStructure_details().length) {
									detailsArrSize = obj.getStructure_details().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0) {
								obj.setStructure_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_values()));
								if(detailsArrSize < obj.getStructure_values().length) {
									detailsArrSize = obj.getStructure_values().length;
								}
							}
							if(!StringUtils.isEmpty(obj.getStructure_details()) && obj.getStructure_details().length > 0 
									&& !StringUtils.isEmpty(obj.getStructure_values()) && obj.getStructure_values().length > 0 ) {
								if(arraySize == 1) {
						    		String joined = String.join("", obj.getStructure_detailss());
						    		joined = joined.replaceAll("_",",_,");
						    		String[] strArray = new String[] {joined};
						    		obj.setStructure_detailss(strArray);
						    	}
								String[] detailsArray = obj.getStructure_detailss();
								if(!StringUtils.isEmpty(detailsArray) && detailsArray.length > 0 &&!StringUtils.isEmpty(detailsArray[i])) {
						    		//detailsArray =Arrays.stream(detailsArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new); 
						    		List<String> details = null;
							    	if(detailsArray[i].contains(",_,")) {
							    		details = new ArrayList<String>(Arrays.asList(detailsArray[i].split(",_,")));
							    	}else {
							    		details = new ArrayList<String>(Arrays.asList(detailsArray[i]));
							    	}
						    		for (String dObj : details) {
									    int param = 1;
									    if(!StringUtils.isEmpty(dObj) && !StringUtils.isEmpty(obj.getStructure_values()[dCount])){
									    	detailsInsertStmt.setString(param++,(obj.getStructure_id()));
									    	detailsInsertStmt.setString(param++,(dObj.length() > 0)? dObj:null);
									    	detailsInsertStmt.setString(param++,(obj.getStructure_values().length > 0)? obj.getStructure_values()[dCount]:null);
									    	detailsInsertStmt.addBatch();
									    }
									    detailsInsertStmt.executeBatch();
									    dCount++;
						    		}
						    		
						    	}else {
						    		 dCount++;
						    	}
							}
							
							String document_insert_qry = "INSERT into  structure_documents ( structure_id_fk, attachment,structure_file_type_fk,name,created_date)"
									+ " VALUES (?,?,?,?,CURDATE())";
							documentsStmt = con.prepareStatement(document_insert_qry);
							int docArrSize = 0;
							
							if (!StringUtils.isEmpty(obj.getStructure_file_types()) && obj.getStructure_file_types().length > 0) {
								obj.setStructure_file_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_types()));
								if (docArrSize < obj.getStructure_file_types().length) {
									docArrSize = obj.getStructure_file_types().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructureDocumentNames()) && obj.getStructureDocumentNames().length > 0) {
								obj.setStructureDocumentNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureDocumentNames()));
								if (docArrSize < obj.getStructureDocumentNames().length) {
									docArrSize = obj.getStructureDocumentNames().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructureFileNames()) && obj.getStructureFileNames().length > 0) {
								obj.setStructureFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructureFileNames()));
								if (docArrSize < obj.getStructureFileNames().length) {
									docArrSize = obj.getStructureFileNames().length;
								}
							}
							if (!StringUtils.isEmpty(obj.getStructure_file_ids()) && obj.getStructure_file_ids().length > 0) {
								obj.setStructure_file_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_file_ids()));
								if (docArrSize < obj.getStructure_file_ids().length) {
									docArrSize = obj.getStructure_file_ids().length;
								}
							}
							//structureFiless
								List<String> documents = null;
								if(!StringUtils.isEmpty(obj.getStructureFiless()) && obj.getStructureFiless().length > 0 && !StringUtils.isEmpty(obj.getStructureFiless()[i])) {
									if(arraySize == 1) {
							    		String joined = String.join("", obj.getStructureFiless());
							    		joined = joined.replaceAll("_",",_,");
							    		String[] strArray = new String[] {joined};
							    		obj.setStructureFiless(strArray);
							    	}
							    	if(obj.getStructureFiless()[i].contains(",_,")) {
							    		documents = new ArrayList<String>(Arrays.asList(obj.getStructureFiless()[i].split(",_,")));
							    	}else {
							    		documents = new ArrayList<String>(Arrays.asList(obj.getStructureFiless()[i]));
							    	}
									for (String fObj : documents) {
										int q=1;
											String fileName = null;
											if( !StringUtils.isEmpty(obj.getStructureFiles()[fCount])) {
												MultipartFile multipartFile = obj.getStructureFiles()[fCount];
												if ((null != multipartFile && !multipartFile.isEmpty())) {
													String saveDirectory = CommonConstants2.STRUCTURE_FILE_SAVING_PATH ;
													fileName = multipartFile.getOriginalFilename();
													if (null != multipartFile && !multipartFile.isEmpty()) {
														FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
						 							}
												}
											}if(!StringUtils.isEmpty(obj.getStructureFileNames()[fCount])) {
												fileName = obj.getStructureFileNames()[fCount];
											}
											if(!StringUtils.isEmpty(obj.getStructureFileNames()[fCount]) && (!StringUtils.isEmpty(obj.getStructureFiles()[fCount]))
													&& (!StringUtils.isEmpty(obj.getStructure_file_types()[fCount])) && (!StringUtils.isEmpty(obj.getStructureDocumentNames()[fCount]))) {
												documentsStmt.setString(q++,(obj.getStructure_id()));
												documentsStmt.setString(q++,(fileName));
												documentsStmt.setString(q++,(obj.getStructure_file_types()[fCount]));
												documentsStmt.setString(q++,(obj.getStructureDocumentNames()[fCount]));
												documentsStmt.addBatch();
												documentsStmt.executeBatch();
												fCount++;
											}else {
												fCount++;
											}
									}
								}else {
									fCount++;
						    	}
							}*/

					}
				}
				int result = updateCount.length + insertCount.length;
				if (result > 0) {
					flag = true;
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
					formHistory.setModule_name_fk("Works");
					formHistory.setForm_name("Update Structure");
					formHistory.setForm_action_type("Update");
					formHistory.setForm_details("Structure for "+obj.getWork_id_fk() + " Updated");
					formHistory.setWork_id_fk(obj.getWork_id_fk());
					//formHistory.setContract(obj.getContract_id_fk());
					
					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/
				}
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
			if (executivesInsertStmt != null) {
				executivesInsertStmt.close();
			}
			if (detailsInsertStmt != null) {
				detailsInsertStmt.close();
			}
			if (documentsStmt != null) {
				documentsStmt.close();
			}
		}
		return flag;
	}

	private List<Structure> getStructureIdsWithWork(String work_id_fk) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT structure_id from structure where work_id_fk <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(work_id_fk)) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(work_id_fk)) {
				pValues[i++] = work_id_fk;
			}
			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean saveStructureDataUploadFile(Structure obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String structure_data_id = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String qry = "INSERT INTO structure_data"
					+ "(uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on) " + "VALUES "
					+ "( :uploaded_file, :status, :remarks, :uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = template.update(qry, paramSource, keyHolder);
			if (count > 0) {
				structure_data_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setId(structure_data_id);
				flag = true;

				MultipartFile file = obj.getStructureFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0) {
					String saveDirectory = CommonConstants.STRUCTURE_UPLOADED_FILE_SAVING_PATH;
					String fileName = structure_data_id + "_" + file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);

					obj.setUploaded_file(fileName);
					String updateQry = "UPDATE structure_data set uploaded_file= :uploaded_file where id= :id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);
					template.update(updateQry, paramSource1);
				}
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public int uploadStructures(List<Structure> structuresList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			String insert_qry = "INSERT into  structure ( work_id_fk, contract_id_fk, department_fk, structure_type_fk, "
					+ "structure) " + "VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry);
			for (Structure obj : structuresList) {
				if (!StringUtils.isEmpty(obj.getWork_id_fk()) && !StringUtils.isEmpty(obj.getStructure_type_fk())
						&& !StringUtils.isEmpty(obj.getStructure())) {
					int p = 1;
					String department = getDepartment(obj.getDepartment_fk(), con);
					insertStmt.setString(p++, (obj.getWork_id_fk()));
					insertStmt.setString(p++, (obj.getContract_id_fk()));
					insertStmt.setString(p++, (department));
					insertStmt.setString(p++, (obj.getStructure_type_fk()));
					insertStmt.setString(p++, (obj.getStructure()));
					insertStmt.addBatch();
					count++;
				}
				insertStmt.executeBatch();
			}

			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return count;
	}

	private String getDepartment(String department_name, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String department = null;
		try {
			String riskIdQry = "select department from department where department_name = ?";
			stmt = con.prepareStatement(riskIdQry);
			stmt.setString(1, department_name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				department = rs.getString("department");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return department;
	}

	@Override
	public List<Structure> getStructureUploadsList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT id, uploaded_file, sd.status, sd.remarks, uploaded_by_user_id_fk, DATE_FORMAT(uploaded_on,'%d-%b-%Y') as uploaded_on "
					+ "from structure_data sd " + "LEFT JOIN user u ON sd.uploaded_by_user_id_fk = u.user_id "
					+ "where id is not null";

			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));

		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructureExportList(Structure structure) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.structure_id,s.structure,w.work_name,w.work_short_name,dt.department_name,w.project_id_fk,p.project_name,s.work_id_fk,c.contract_name,"
					+ "c.contract_short_name, structure_type_fk  " + "FROM structure s "
					+ "left join contract c on s.contract_id_fk = c.contract_id    "
					+ "left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci   "
					+ "left join project p on w.project_id_fk = p.project_id  "
					+ "left join department dt on s.department_fk = dt.department " + "where structure_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			qry = qry + " GROUP BY s.structure_id ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getProject_id_fk())) {
				pValues[i++] = structure.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getWork_id_fk())) {
				pValues[i++] = structure.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getContract_id_fk())) {
				pValues[i++] = structure.getContract_id_fk();
			}

			objsList = jdbcTemplate.query(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getResponsiblePeopleListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation,department_fk FROM user u where user_name not like '%user%' and pmis_key_fk not like '%SGS%' and department_fk in('Engg','Elec','S&T')";
			qry = qry + " ORDER BY FIELD(user_type_fk,'HOD','DYHOD','Officers ( Jr./Sr. Scale )','Others'),"
					+ "FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','AGM Civil',"
					+ " 'DyCPM Civil','DyCPM III','DyCPM V','DyCE EE','DyCE Badlapur','DyCPM Pune','DyCE Proj','DyCEE I','DyCEE Projects','DyCEE PSI','DyCSTE I','DyCSTE IT','DyCSTE Projects','XEN Consultant',"
					+ " 'AEN Adhoc','AEN Project','AEN P-Way','AEN','Sr Manager Signal','Manager Signal','Manager Civil','Manager OHE','Manager GS','Manager Finance','Planning Manager',"
					+ " 'Manager Project','Manager','SSE','SSE Project','SSE Works','SSE Drg','SSE BR','SSE P-Way','SSE OHE','SPE','PE','JE','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T')";

			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getWorkStatusListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT DISTINCT(work_status_fk) from work where work_status_fk <> ''";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getUnitsListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT id, unit, value from money_unit";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getFileTypeForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT structure_file_type from structure_file_type";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean deleteStructure(Structure obj) throws Exception {
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		ResultSet resultSet = null;
		try{
				String concat="";
				if(obj.getStructure_ids().length>1)
				{
					concat="[";
				}
				for (int i = 0; i < obj.getStructure_ids().length; i++)
				{
					concat=concat+'"'+obj.getStructure_ids()[i]+'"'+",";
				}
				concat=concat.substring(0, concat.length() - 1);
				if(obj.getStructure_ids().length>1)
				{
					concat=concat+"]";
				}				
				
				connection = dataSource.getConnection();	
				
				if(obj.getStructure_ids().length>1)
				{
					statement = connection.prepareCall("{call deleteMultipleStructure(?,?)}");

				}	
				else
				{
					statement = connection.prepareCall("{call deleteStructure(?,?)}");
				}
				statement.setString(1, concat);
				statement.setString(2, obj.getStructure_type_fk());

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

	@Override
	public List<Structure> getStructureCount(Structure obj) throws Exception {
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		ResultSet resultSet = null;
		List<Structure> objsList = new ArrayList<Structure>();
		try{
				connection = dataSource.getConnection();
				String concat="";
				for (int i = 0; i < obj.getStructure_ids().length; i++)
				{
						concat=concat+obj.getStructure_ids()[i]+",";
				}
				concat=concat.substring(0, concat.length() - 1);				
				statement = connection.prepareCall("{call getStructureCount(?,?)}");
				statement.setString(1, concat);
				statement.setString(2, obj.getStructure_type_fk());
				resultSet =statement.executeQuery();
				
				while(resultSet.next()) 
				{
					Structure str = new Structure();
					str.setName(resultSet.getString("name"));
					str.setStructure_count(resultSet.getString("structure_count"));
					objsList.add(str);
				}
				
			} catch (Exception e) {
				throw new Exception(e);
			}
			return objsList;
	}

	@Override
	public List<Structure> getStructureTypeCount(Structure obj) throws Exception {
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		ResultSet resultSet = null;
		List<Structure> objsList = new ArrayList<Structure>();
		try{
				connection = dataSource.getConnection();
				String concat="";
				if(obj.getStructure_ids().length>0)
				{
					concat="[";
				}				
				for (int i = 0; i < obj.getStructure_ids().length; i++)
				{
						concat=concat+'"'+obj.getStructure_ids()[i]+'"'+",";
				}
				concat=concat.substring(0, concat.length() - 1);
				if(obj.getStructure_ids().length>0)
				{
					concat=concat+"]";
				}					
				statement = connection.prepareCall("{call getStructureTypeCount(?,?)}");
				statement.setString(1, concat);
				statement.setString(2, obj.getStructure_type_fk());
				resultSet =statement.executeQuery();
				
				while(resultSet.next()) 
				{
					Structure str = new Structure();
					str.setName(resultSet.getString("name"));
					str.setStructure_count(resultSet.getString("structure_count"));
					objsList.add(str);
				}
				
			} catch (Exception e) {
				throw new Exception(e);
			}
			return objsList;
	}

}
