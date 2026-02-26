package com.synergizglobal.wrpmis.IMPLdao;

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

import com.synergizglobal.wrpmis.common.CommonMethods;
import com.synergizglobal.wrpmis.common.DBConnectionHandler;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.constants.CommonConstants2;
import com.synergizglobal.wrpmis.Idao.FormsHistoryDao;
import com.synergizglobal.wrpmis.Idao.StructureDao;
import com.synergizglobal.wrpmis.model.Budget;
import com.synergizglobal.wrpmis.model.FOB;
import com.synergizglobal.wrpmis.model.FormHistory;
import com.synergizglobal.wrpmis.model.StripChart;
import com.synergizglobal.wrpmis.model.Structure;
import com.synergizglobal.wrpmis.model.User;

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
			String qry = "SELECT s.project_id_fk,p.project_name " + "from structure s "
					+ "LEFT JOIN project p on s.project_id_fk = p.project_id "
					+ "where structure_id is not null and s.status <> 'Inactive' and project_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and s.project_id_fk = ?";
				arrSize++;
			}


			qry = qry + " GROUP BY s.project_id_fk,p.project_name ";

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
	public List<Structure> getWorksListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT * from structure s "
					+ "where structure_id is not null and s.status <> 'Inactive' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and c.project_id_fk = ?";
				arrSize++;
			}

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
	public List<Structure> getContractsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.contract_id_fk,c.contract_short_name " + "from structure s "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null and s.status <> 'Inactive' and contract_id_fk is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and c.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			qry = qry + " GROUP BY s.contract_id_fk,c.contract_short_name ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null and s.status <> 'Inactive' and s.department_fk is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and c.project_id_fk = ?";
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
			qry = qry + " GROUP BY s.department_fk,d.department_name ";

			Object[] pValues = new Object[arrSize];

			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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

			String qry = "select count(DISTINCT(s.project_id_fk)) as total_records " + "from structure s "
					+ "left join project p on s.project_id_fk = p.project_id "
					+ "where structure_id is not null and s.status <> 'Inactive' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and s.project_id_fk = ?";
				arrSize++;
			}


			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and s.contract_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(searchParameter)) {
				qry = qry
						+ " and (s.project_id_fk like ? or p.project_name like ?  "
						+ " or structure_type_fk like ? or structure like ?)";
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}

			if (!StringUtils.isEmpty(searchParameter)) {
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
			String qry = "					SELECT distinct s.status,s.project_id_fk,p.project_name,structure_type_fk = STUFF((\r\n"
					+ "          select distinct  ',' + CONCAT(structure_type_fk, ' - ', count(structure_type_fk))\r\n"
					+ "FROM structure sd  where status = 'Active' and sd.project_id_fk=p.project_id GROUP BY  structure_type_fk\r\n"
					+ "           \r\n"
					+ "          FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, ''),(select max(structure_id) as structure_id from structure s1 where s1.project_id_fk=p.project_id ) as structure_id\r\n"
					+ "					\r\n" + "					  FROM structure s\r\n"
					+ "					left join project p on s.project_id_fk = p.project_id  \r\n"
					+ "					\r\n" + "					where status = 'Active' ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and s.project_id_fk = ?";
				arrSize++;
			}


			if (!StringUtils.isEmpty(searchParameter)) {
				qry = qry
						+ " and (s.project_id_fk like ? or p.project_name like ?  "
						+ "or structure_type_fk like ? or structure like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if (!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry
						+ " GROUP BY structure_type_fk,structure_id,status,project_id_fk,project_name,p.project_id ORDER BY structure_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}

			if (!StringUtils.isEmpty(searchParameter)) {
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
			String qry = "select project_id as project_id_fk,project_name from project order by project_id asc";
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<Structure>(Structure.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getContractListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select contract_id as contract_id_fk,contract_name,contract_short_name,c.project_id_fk,project_name "
					+ "from contract c "
					+ "LEFT JOIN project p ON c.project_id_fk = project_id " + "where contract_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}

			qry = qry + " order by contract_id asc";

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
			String qry = "select structure_id, s.project_id_fk, structure_type_fk, structure,p.project_name, "
					+ "s.work_status_fk, s.target_date, s.estimated_cost, s.estimated_cost_units, s.construction_start_date, s.revised_completion, s.remarks "
					+ "from structure s "
					+ "left join project p on s.project_id_fk = p.project_id where structure_id is not null";
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
				String qryDetails = "select distinct structure_type_fk "
						+ " from structure s  where s.project_id_fk = ? and s.status <> 'Inactive' ";

				objsList = jdbcTemplate.query(qryDetails, new Object[] { structure.getProject_id_fk() },
						new BeanPropertyRowMapper<Structure>(Structure.class));
				structure.setStructureList(objsList);

				List<Structure> objsListI = null;
				String qryDetailsI = "select distinct structure_id, structure_type_fk,structure_name,structure "
						+ " from structure s  where s.project_id_fk = ? and s.status = 'Inactive' ";

				objsListI = jdbcTemplate.query(qryDetailsI, new Object[] { structure.getProject_id_fk() },
						new BeanPropertyRowMapper<Structure>(Structure.class));
				structure.setStructureListInactive(objsListI);
				if (!StringUtils.isEmpty(objsListI)) {
					for (Structure list : structure.getStructureListInactive()) {
						String qryI = "select distinct structure_id,structure_name, structure  from structure s where s.project_id_fk = ? and s.structure_type_fk = ? and s.status = 'Inactive' ";
						List<Structure> objListI = jdbcTemplate.query(qryI,
								new Object[] { structure.getProject_id_fk(), list.getStructure_type_fk() },
								new BeanPropertyRowMapper<Structure>(Structure.class));

						list.setStructureSubListInactive(objListI);
					}
				}
				if (!StringUtils.isEmpty(objsList)) {
					for (Structure list : structure.getStructureList()) {

						String qry2 = "select distinct structure_id,structure_name, structure  from structure s where s.project_id_fk = ? and s.structure_type_fk = ? and s.status <> 'Inactive' ";
						List<Structure> objList1 = jdbcTemplate.query(qry2,
								new Object[] { structure.getProject_id_fk(), list.getStructure_type_fk() },
								new BeanPropertyRowMapper<Structure>(Structure.class));

						list.setStructureSubList(objList1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return structure;
	}

//	@Override
//	public boolean addStructure(Structure obj) throws Exception {
//		Connection con = null;
//		PreparedStatement insertStmt = null;
//		PreparedStatement executivesInsertStmt = null;
//		PreparedStatement detailsInsertStmt = null;
//		PreparedStatement documentsStmt = null;
//		int j = 0, dCount = 0, fCount = 0;
//		;
//		boolean flag = false;
//		ResultSet rs = null;
//		int[] insertCount = { 0 };
//		try {
//			con = dataSource.getConnection();
//			con.setAutoCommit(false);
//			/*String insert_qry = "INSERT into  structure ( project_id_fk, structure_type_fk, "
//					+ "structure,work_status_fk,structure_name,latitude,longitude,target_date,estimated_cost,estimated_cost_units,construction_start_date,revised_completion,remarks) "
//					+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
//
//			String insert_qry = "INSERT into  structure ( project_id_fk, structure_type_fk, "
//					+ "structure,structure_name,status) " + "VALUES (?,?,?,?,?)";
//			insertStmt = con.prepareStatement(insert_qry, Statement.RETURN_GENERATED_KEYS);
//			int arraySize = 0;
//
//			if (!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
//				obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
//				if (arraySize < obj.getStructures().length) {
//					arraySize = obj.getStructures().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getStructure_names()) && obj.getStructure_names().length > 0) {
//				obj.setStructure_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_names()));
//				if (arraySize < obj.getStructure_names().length) {
//					arraySize = obj.getStructure_names().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getLatitudes()) && obj.getLatitudes().length > 0) {
//				obj.setLatitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLatitudes()));
//				if (arraySize < obj.getLatitudes().length) {
//					arraySize = obj.getLatitudes().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getLongitudes()) && obj.getLongitudes().length > 0) {
//				obj.setLongitudes(CommonMethods.replaceEmptyByNullInSringArray(obj.getLongitudes()));
//				if (arraySize < obj.getLongitudes().length) {
//					arraySize = obj.getLongitudes().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getWork_status_fks()) && obj.getWork_status_fks().length > 0) {
//				obj.setWork_status_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getWork_status_fks()));
//				if (arraySize < obj.getWork_status_fks().length) {
//					arraySize = obj.getWork_status_fks().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getTarget_dates()) && obj.getTarget_dates().length > 0) {
//				obj.setTarget_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getTarget_dates()));
//				if (arraySize < obj.getTarget_dates().length) {
//					arraySize = obj.getTarget_dates().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getEstimated_costs()) && obj.getEstimated_costs().length > 0) {
//				obj.setEstimated_costs(CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_costs()));
//				if (arraySize < obj.getEstimated_costs().length) {
//					arraySize = obj.getEstimated_costs().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getEstimated_cost_unitss()) && obj.getEstimated_cost_unitss().length > 0) {
//				obj.setEstimated_cost_unitss(
//						CommonMethods.replaceEmptyByNullInSringArray(obj.getEstimated_cost_unitss()));
//				if (arraySize < obj.getEstimated_cost_unitss().length) {
//					arraySize = obj.getEstimated_cost_unitss().length;
//				}
//			}
//
//			if (!StringUtils.isEmpty(obj.getConstruction_start_dates())
//					&& obj.getConstruction_start_dates().length > 0) {
//				obj.setConstruction_start_dates(
//						CommonMethods.replaceEmptyByNullInSringArray(obj.getConstruction_start_dates()));
//				if (arraySize < obj.getConstruction_start_dates().length) {
//					arraySize = obj.getConstruction_start_dates().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getRevised_completions()) && obj.getRevised_completions().length > 0) {
//				obj.setRevised_completions(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevised_completions()));
//				if (arraySize < obj.getRevised_completions().length) {
//					arraySize = obj.getRevised_completions().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
//				obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
//				if (arraySize < obj.getRemarkss().length) {
//					arraySize = obj.getRemarkss().length;
//				}
//			}
//			if (!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0
//					&& !StringUtils.isEmpty(obj.getProject_id_fk()) && obj.getStructures().length > 0) {
//				for (int i = 0; i < arraySize; i++) {
//					int p = 1;
//					if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
//							&& !StringUtils.isEmpty(obj.getStructures()[i])) {
//						insertStmt.setString(p++, (obj.getProject_id_fk()));
//						insertStmt.setString(p++,
//								(obj.getStructure_type_fks().length > 0) ? obj.getStructure_type_fks()[i] : null);
//						insertStmt.setString(p++, (obj.getStructures().length > 0) ? obj.getStructures()[i] : null);
//						insertStmt.setString(p++,
//								(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
//						insertStmt.setString(p++, (CommonConstants.ACTIVE));
//
//						insertStmt.executeUpdate();
//
//						rs = insertStmt.getGeneratedKeys();
//						if (rs.next()) {
//							String structure_id = rs.getString(1);
//							obj.setStructure_id(structure_id);
//						}
//					
//
//					}
//				}
//				if (insertCount.length > 0) {
//					flag = true;
//					FormHistory formHistory = new FormHistory();
//					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
//					formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
//					formHistory.setModule_name_fk("Works");
//					formHistory.setForm_name("Add Structure");
//					formHistory.setForm_action_type("Add");
//					formHistory.setForm_details("New Structure for "+obj.getProject_id_fk() + " Created");
//					formHistory.setproject_id_fk(obj.getProject_id_fk());
//					//formHistory.setContract(obj.getContract_id_fk());
//					
//					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
//					/********************************************************************************/
//				}
//			}
//			con.commit();
//		} catch (Exception e) {
//			con.rollback();
//			e.printStackTrace();
//			throw new Exception(e);
//		} finally {
//			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
//			if (executivesInsertStmt != null) {
//				executivesInsertStmt.close();
//			}
//			if (detailsInsertStmt != null) {
//				detailsInsertStmt.close();
//			}
//			if (documentsStmt != null) {
//				documentsStmt.close();
//			}
//		}
//		return flag;
//	}


	@SuppressWarnings("resource")
	@Override
	public String addStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement checkDuplicateStmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			String checkDuplicateQuery = "SELECT COUNT(*) FROM structure WHERE structure = ? AND structure_name = ? and structure_type_fk=?";
			checkDuplicateStmt = con.prepareStatement(checkDuplicateQuery);

			String insert_qry = "INSERT INTO structure (project_id_fk, structure_type_fk, structure, structure_name, status) VALUES (?, ?, ?, ?, ?)";
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
					&& !StringUtils.isEmpty(obj.getProject_id_fk()) && obj.getStructures().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					int p = 1;
					if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
							&& !StringUtils.isEmpty(obj.getStructures()[i])) {

						// Check for duplicate
						checkDuplicateStmt.setString(1, obj.getStructures()[i]);
						checkDuplicateStmt.setString(2, obj.getStructure_names()[i]);
						checkDuplicateStmt.setString(3, obj.getStructure_type_fks()[i]);
						rs = checkDuplicateStmt.executeQuery();
						if (rs.next() && rs.getInt(1) > 0) {
							return "duplicate"; // Return "duplicate" if a duplicate is found
						}

						insertStmt.setString(p++, obj.getProject_id_fk());
						insertStmt.setString(p++,
								obj.getStructure_type_fks().length > 0 ? obj.getStructure_type_fks()[i] : null);
						insertStmt.setString(p++, obj.getStructures().length > 0 ? obj.getStructures()[i] : null);
						insertStmt.setString(p++,
								obj.getStructure_names().length > 0 ? obj.getStructure_names()[i] : null);
						insertStmt.setString(p++, CommonConstants.ACTIVE);

						insertStmt.executeUpdate();

						rs = insertStmt.getGeneratedKeys();
						if (rs.next()) {
							String structure_id = rs.getString(1);
							obj.setStructure_id(structure_id);
						}
					}
				}

				flag = true;
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation() + " - " + obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Add Structure");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Structure for " + obj.getProject_id_fk() + " Created");
				formHistory.setProject_id_fk(obj.getProject_id_fk());

				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage()); // Throw the duplicate error message or any other exception message
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
			if (checkDuplicateStmt != null) {
				checkDuplicateStmt.close();
			}
		}
		return flag ? "success" : "error";
	}
	
	@Override
	public String updateStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement stmt = null;
		PreparedStatement executivesInsertStmt = null;
		ResultSet rs = null;
		PreparedStatement checkDuplicateStmt = null;
		PreparedStatement detailsInsertStmt = null;
		PreparedStatement documentsStmt = null;
		PreparedStatement updateStmt = null;
		int j = 0, dCount = 0, fCount = 0;
		
		boolean flag = false;
		int[] insertCount = { 0 };
		int[] updateCount = { 0 };
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			con.setAutoCommit(false);

			String checkDuplicateQuery = "SELECT COUNT(*) FROM structure WHERE structure = ? AND structure_name = ? and structure_type_fk=?";
			checkDuplicateStmt = con.prepareStatement(checkDuplicateQuery);

			String updateQry = " update structure set "
					+ "structure = ?,structure_name = ?,structure_type_fk=?,status = ?  where structure_id = ?";
			updateStmt = con.prepareStatement(updateQry);

			String insert_qry = "INSERT into structure ( project_id_fk, structure_type_fk, "
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
					&& !StringUtils.isEmpty(obj.getProject_id_fk()) && obj.getStructures().length > 0) {

				String inactiveQry = "UPDATE structure set status = ?  where project_id_fk = ?";
				stmt = con.prepareStatement(inactiveQry);
				stmt.setString(1, CommonConstants.INACTIVE);
				stmt.setString(2, obj.getProject_id_fk());
				stmt.executeUpdate();
				if (stmt != null) {
					stmt.close();
				}
				int loopTimes = 0;
				int loopTimes1 = 0;
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
								updateStmt.setString(k++,
										(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
								updateStmt.setString(k++,
										(obj.getStructure_type_fks().length > 0) ? obj.getStructure_type_fks()[i] : null);								
								updateStmt.setString(k++, (CommonConstants.ACTIVE));
								updateStmt.setString(k++, (sId));
								// updateStmt.addBatch();
								updateStmt.executeUpdate();
								
								loopTimes1++;
							}
						} else {
							int p = 1;
							if (!StringUtils.isEmpty(obj.getStructure_type_fks()[i])
									&& !StringUtils.isEmpty(obj.getStructures()[i])) {
								// Check for duplicate
								checkDuplicateStmt.setString(1, obj.getStructures()[i]);
								checkDuplicateStmt.setString(2, obj.getStructure_names()[i]);
								checkDuplicateStmt.setString(3, obj.getStructure_type_fks()[i]);
								rs = checkDuplicateStmt.executeQuery();
								if (rs.next() && rs.getInt(1) > 0) {
									return "duplicate"; // Return "duplicate" if a duplicate is found
								}

								insertStmt.setString(p++, (obj.getProject_id_fk()));
								insertStmt.setString(p++,
										(obj.getStructure_type_fks().length > 0) ? obj.getStructure_type_fks()[i]
												: null);
								insertStmt.setString(p++,
										(obj.getStructures().length > 0) ? obj.getStructures()[i] : null);
								insertStmt.setString(p++,
										(obj.getStructure_names().length > 0) ? obj.getStructure_names()[i] : null);
								insertStmt.setString(p++, (CommonConstants.ACTIVE));

								// insertStmt.addBatch();
								insertStmt.executeUpdate();
								if (rs.next()) {
									String structure_id = rs.getString(1);
									obj.setStructure_id(structure_id);
								}
								loopTimes++;
							}
						}

						if (loopTimes > 0) {
							
						}
					}
				}
				int result = loopTimes1 + loopTimes;
				if (result > 0) {
					flag = true;
					FormHistory formHistory = new FormHistory();
					formHistory.setCreated_by_user_id_fk(obj.getUser_id());
					formHistory.setUser(obj.getDesignation() + " - " + obj.getUser_name());
					formHistory.setModule_name_fk("Works");
					formHistory.setForm_name("Update Structure");
					formHistory.setForm_action_type("Update");
					formHistory.setForm_details("Structure for " + obj.getProject_id_fk() + " Updated");
					formHistory.setProject_id_fk(obj.getProject_id_fk());
					// formHistory.setContract(obj.getContract_id_fk());

					boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
					/********************************************************************************/
				}
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
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
		return flag ? "success" : "error";	
		}

	@Override
	public boolean checkForDuplicateStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean duplicateExists = false;

		try {
			con = dataSource.getConnection();
			String checkDuplicateQuery = "SELECT COUNT(*) FROM structure WHERE structure = ? AND structure_name = ? and structure_type_fk=?";
			pstmt = con.prepareStatement(checkDuplicateQuery);
			pstmt.setString(1, obj.getStructure());
			pstmt.setString(2, obj.getStructure_name());
			pstmt.setString(3, obj.getStructure_type_fk());
			rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				duplicateExists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(con, pstmt, rs);
		}

		return duplicateExists;
	}

	private List<Structure> getStructureIdsWithWork(String project_id_fk) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT structure_id from structure where project_id_fk <> '' ";
			int arrSize = 0;
			if (!StringUtils.isEmpty(project_id_fk)) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(project_id_fk)) {
				pValues[i++] = project_id_fk;
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

			String insert_qry = "INSERT into  structure ( project_id_fk, contract_id_fk, department_fk, structure_type_fk, "
					+ "structure) " + "VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry);
			for (Structure obj : structuresList) {
				if (!StringUtils.isEmpty(obj.getProject_id_fk()) && !StringUtils.isEmpty(obj.getStructure_type_fk())
						&& !StringUtils.isEmpty(obj.getStructure())) {
					int p = 1;
					String department = getDepartment(obj.getDepartment_fk(), con);
					insertStmt.setString(p++, (obj.getProject_id_fk()));
					insertStmt.setString(p++, (obj.getContract_id_fk()));
					insertStmt.setString(p++, (department));
					insertStmt.setString(p++, (obj.getStructure_type_fk()));
					insertStmt.setString(p++, (obj.getStructure()));
					// insertStmt.addBatch();
					insertStmt.executeUpdate();
					count++;
				}
				// insertStmt.executeBatch();
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
			String qry = "SELECT id, uploaded_file, sd.status, sd.remarks, uploaded_by_user_id_fk, FORMAT(uploaded_on,'dd-MMM-yy') as uploaded_on "
					+ "from structure_data sd " + "left join [user] u ON sd.uploaded_by_user_id_fk = u.user_id "
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
			String qry = "SELECT s.structure_id,s.structure,w.work_name,w.work_short_name,dt.department_name,w.project_id_fk,p.project_name,s.project_id_fk,c.contract_name,"
					+ "c.contract_short_name, structure_type_fk  " + "FROM structure s "
					+ "left join contract c on s.contract_id_fk = c.contract_id    "
					+ "left join project p on s.project_id_fk = p.project_id  "
					+ "left join department dt on s.department_fk = dt.department " + "where structure_id is not null ";

			int arrSize = 0;
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getProject_id_fk())) {
				qry = qry + " and s.project_id_fk = ?";
				arrSize++;
			}

			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getProject_id_fk())) {
				qry = qry + " and s.project_id_fk = ?";
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
			if (!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getProject_id_fk())) {
				pValues[i++] = structure.getProject_id_fk();
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
			String qry = "SELECT user_id,user_name,designation,department_fk FROM [user] u where user_name not like '%user%' and pmis_key_fk not like '%SGS%' and department_fk in('Engg','Elec','S&T')\r\n"
					+ "					ORDER BY \r\n" + "					case when user_type_fk='HOD' then 1\r\n"
					+ "					when user_type_fk='DYHOD' then 2\r\n"
					+ "					when user_type_fk='Officers ( Jr./Sr. Scale )' then 3\r\n"
					+ "					when user_type_fk='Others' then 4\r\n" + "					end asc ,\r\n"
					+ "case when u.designation='ED Civil' then 1 \r\n" + "   when u.designation='CPM I' then 2 \r\n"
					+ "   when u.designation='CPM II' then 3\r\n" + "   when u.designation='CPM III' then 4 \r\n"
					+ "   when u.designation='CPM V' then 5\r\n" + "   when u.designation='CE' then 6 \r\n"
					+ "   when u.designation='ED S&T' then 7 \r\n" + "   when u.designation='CSTE' then 8\r\n"
					+ "   when u.designation='GM Electrical' then 9\r\n"
					+ "   when u.designation='CEE Project I' then 10\r\n"
					+ "   when u.designation='CEE Project II' then 11\r\n"
					+ "   when u.designation='ED Finance & Planning' then 12\r\n"
					+ "   when u.designation='AGM Civil' then 13\r\n"
					+ "   when u.designation='DyCPM Civil' then 14\r\n"
					+ "   when u.designation='DyCPM III' then 15\r\n" + "   when u.designation='DyCPM V' then 16\r\n"
					+ "   when u.designation='DyCE EE' then 17\r\n"
					+ "   when u.designation='DyCE Badlapur' then 18\r\n"
					+ "   when u.designation='DyCPM Pune' then 19\r\n" + "   when u.designation='DyCE Proj' then 20\r\n"
					+ "   when u.designation='DyCEE I' then 21\r\n"
					+ "   when u.designation='DyCEE Projects' then 22\r\n"
					+ "   when u.designation='DyCEE PSI' then 23\r\n" + "   when u.designation='DyCSTE I' then 24\r\n"
					+ "   when u.designation='DyCSTE IT' then 25\r\n"
					+ "   when u.designation='DyCSTE Projects' then 26\r\n"
					+ "   when u.designation='XEN Consultant' then 27\r\n"
					+ "   when u.designation='AEN Adhoc' then 28\r\n"
					+ "   when u.designation='AEN Project' then 29\r\n"
					+ "   when u.designation='AEN P-Way' then 30\r\n" + "   when u.designation='AEN' then 31\r\n"
					+ "   when u.designation='Sr Manager Signal' then 32 \r\n"
					+ "   when u.designation='Manager Signal' then 33\r\n"
					+ "   when u.designation='Manager Civil' then 34 \r\n"
					+ "   when u.designation='Manager OHE' then 35\r\n"
					+ "   when u.designation='Manager GS' then 36\r\n"
					+ "   when u.designation='Manager Finance' then 37\r\n"
					+ "   when u.designation='Planning Manager' then 38\r\n"
					+ "   when u.designation='Manager Project' then 39\r\n"
					+ "   when u.designation='Manager' then 40 \r\n" + "   when u.designation='SSE' then 41\r\n"
					+ "   when u.designation='SSE Project' then 42\r\n"
					+ "   when u.designation='SSE Works' then 43\r\n" + "   when u.designation='SSE Drg' then 44\r\n"
					+ "   when u.designation='SSE BR' then 45\r\n" + "   when u.designation='SSE P-Way' then 46\r\n"
					+ "   when u.designation='SSE OHE' then 47\r\n" + "   when u.designation='SPE' then 48\r\n"
					+ "   when u.designation='PE' then 49\r\n" + "   when u.designation='JE' then 50\r\n"
					+ "   when u.designation='Demo-HOD-Elec' then 51\r\n"
					+ "   when u.designation='Demo-HOD-Engg' then 52\r\n"
					+ "   when u.designation='Demo-HOD-S&T' then 53\r\n" + "\r\n" + "   end asc";

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
		try {
			String concat = "";
			if (obj.getStructure_ids().length > 1) {
				concat = "[";
			}
			for (int i = 0; i < obj.getStructure_ids().length; i++) {
				concat = concat + '"' + obj.getStructure_ids()[i] + '"' + ",";
			}
			concat = concat.substring(0, concat.length() - 1);
			if (obj.getStructure_ids().length > 1) {
				concat = concat + "]";
			}

			connection = dataSource.getConnection();

			if (obj.getStructure_ids().length > 1) {
				statement = connection.prepareCall("{exec deleteMultipleStructure(?,?)}");

			} else {
				statement = connection.prepareCall("{exec deleteStructure(?,?)}");
			}
			statement.setString(1, concat);
			statement.setString(2, obj.getStructure_type_fk());
			statement.setString(3, obj.getProject_id_fk());

			boolean hadResults = statement.execute();
			if (hadResults) {
				return true;
			}
		} catch (Exception e) {
		} finally {
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
		try {
			connection = dataSource.getConnection();
			String concat = "";
			for (int i = 0; i < obj.getStructure_ids().length; i++) {
				concat = concat + obj.getStructure_ids()[i] + ",";
			}
			concat = concat.substring(0, concat.length() - 1);
			statement = connection.prepareCall("{execgetStructureCount(?,?)}");
			statement.setString(1, obj.getProject_id_fk());
			statement.setString(2, concat);
			statement.setString(3, obj.getStructure_type_fk());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
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
		try {
			connection = dataSource.getConnection();
			String concat = "";
			if (obj.getStructure_ids().length > 0) {
				concat = "[";
			}
			for (int i = 0; i < obj.getStructure_ids().length; i++) {
				concat = concat + '"' + obj.getStructure_ids()[i] + '"' + ",";
			}
			concat = concat.substring(0, concat.length() - 1);
			if (obj.getStructure_ids().length > 0) {
				concat = concat + "]";
			}
			statement = connection.prepareCall("{exec getStructureTypeCount(?,?)}");
			statement.setString(1, obj.getProject_id_fk());
			statement.setString(2, concat);
			statement.setString(3, obj.getStructure_type_fk());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
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
	public List<Structure> getWorkListForStructureForm(Structure obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
