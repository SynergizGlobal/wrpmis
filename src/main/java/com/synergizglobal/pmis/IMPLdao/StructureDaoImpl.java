package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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

import com.synergizglobal.pmis.Idao.StructureDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Structure;

@Repository
public class StructureDaoImpl implements StructureDao{
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public List<Structure> getProjectsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT w.project_id_fk,p.project_name "
					+ "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null ";

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

			
			qry = qry + " GROUP BY w.project_id_fk ";

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
	public List<Structure> getWorksListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.work_id_fk,w.work_short_name "
					+ "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null ";

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

			
			qry = qry + " GROUP BY s.work_id_fk ";

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
	public List<Structure> getContractsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.contract_id_fk,c.contract_short_name "
					+ "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null ";

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
			String qry = "SELECT s.department_fk,d.department_name "
					+ "from structure s "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON s.department_fk = d.department "
					+ "where structure_id is not null ";

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
			
			String qry ="select count(DISTINCT(s.work_id_fk)) as total_records " + 
					"from structure s " + 
					"left join contract c on s.contract_id_fk = c.contract_id  " + 
					"left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci " + 
					"left join project p on w.project_id_fk = p.project_id " 
					+"left join department dt on s.department_fk = dt.department "
					+"where structure_id is not null ";
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

			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (w.project_id_fk like ? or p.project_name like ? or s.work_id_fk like ? or w.work_short_name like ? or s.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or dt.department_name like ? or structure_type_fk like ? or structure like ?)";
				arrSize++;
				arrSize++;
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
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
	public List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="SELECT s.structure_id,s.structure,w.work_name,w.work_short_name,dt.department_name,w.project_id_fk,s.department_fk,p.project_name,s.work_id_fk,s.contract_id_fk,c.contract_name," + 
					"c.contract_short_name, GROUP_CONCAT(CONCAT(structure_type_fk, ' - ', count) SEPARATOR ',') as structure_type_fk " + 
					"FROM ( SELECT structure_id,structure,department_fk,contract_id_fk, work_id_fk, structure_type_fk, COUNT(structure_type_fk) AS count FROM structure " + 
					"JOIN work ON work.work_id = structure.work_id_fk GROUP BY work_id_fk, structure_type_fk) s " + 
					"left join contract c on s.contract_id_fk = c.contract_id    " + 
					"left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci   " + 
					"left join project p on w.project_id_fk = p.project_id  " + 
					"left join department dt on s.department_fk = dt.department " + 
					"where structure_id is not null " ;
			
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
			/*
						if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
							qry = qry + " and s.department_fk = ?";
							arrSize++;
						}*/
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (w.project_id_fk like ? or p.project_name like ? or s.work_id_fk like ? or w.work_short_name like ? or s.contract_id_fk like ?"
						+ " or c.contract_short_name like ? or dt.department_name like ? or structure_type_fk like ? or structure like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
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
			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			/*if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}*/

			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
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
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		
		}catch(Exception e){ 
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
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getWorkListForStructureForm(Structure obj) throws Exception {
		List<Structure> objsList = null;
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
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getContractListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select contract_id as contract_id_fk,contract_name,contract_short_name,c.work_id_fk,w.work_short_name,w.project_id_fk,project_name "
					+ "from `contract` c "
					+ "LEFT JOIN `work` w ON c.work_id_fk = work_id "
					+ "LEFT JOIN `project` p ON w.project_id_fk = project_id "
					+ "where contract_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}
			
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructuresListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select structure_type from structure_type";	
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getDepartmentsListForStructureFrom(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "select department as department_fk,department_name,contract_id_code from department";	
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Structure getStructuresListDetails(Structure obj) throws Exception {
		Structure structure = null;
		try {
			String qry = "select structure_id, s.work_id_fk, contract_id_fk,c.contract_short_name, s.department_fk,department_name, structure_type_fk, structure,w.work_name,p.project_name,w.work_short_name,w.project_id_fk from structure s " + 
					"left join contract c on s.contract_id_fk = contract_id " + 
					"left join work w on s.work_id_fk = w.work_id " 
					+"left join department dt on s.department_fk = dt.department "+
					"left join project p on w.project_id_fk = p.project_id where structure_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				qry = qry + " and structure_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_id())) {
				pValues[i++] = obj.getStructure_id();
			}
			structure = (Structure)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Structure>(Structure.class));	
			if(!StringUtils.isEmpty(structure) && !StringUtils.isEmpty(structure.getStructure_id())) {
				List<Structure> objsList = null;
				String qryDetails = "select structure_id, structure_type_fk,structure"
						+ " from structure s  where s.work_id_fk = ? group by structure_type_fk";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {structure.getWork_id_fk()}, new BeanPropertyRowMapper<Structure>(Structure.class));	
				structure.setStructureList(objsList);
				if(!StringUtils.isEmpty(objsList)) {
					for(Structure list : structure.getStructureList()) {
						
						String qry2 ="select structure_id, structure  from structure s where s.work_id_fk = ? and s.structure_type_fk = ? ";
						List<Structure> objList1 = jdbcTemplate.query( qry2,new Object[] {structure.getWork_id_fk(),list.getStructure_type_fk()}, new BeanPropertyRowMapper<Structure>(Structure.class));

						list.setStructureSubList(objList1);
					}
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return structure;
	}

	@Override
	public boolean addStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = {};
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insert_qry = "INSERT into  structure ( work_id_fk, contract_id_fk, department_fk, structure_type_fk, "
					+ "structure) "
					+"VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry,Statement.RETURN_GENERATED_KEYS); 
			int arraySize = 0; 
			if(!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0) {
				obj.setStructure_type_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_type_fks()));
				if(arraySize < obj.getStructure_type_fks().length) {
					arraySize = obj.getStructure_type_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
				obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
				if(arraySize < obj.getStructures().length) {
					arraySize = obj.getStructures().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0 && !StringUtils.isEmpty(obj.getWork_id_fk()) && obj.getStructures().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( obj.getStructure_type_fks().length > 0 ){
				    	
			    	    insertStmt.setString(p++,(obj.getWork_id_fk()));
					    insertStmt.setString(p++,(obj.getContract_id_fk()));
					    insertStmt.setString(p++,(obj.getDepartment_fk()));
					    insertStmt.setString(p++,(obj.getStructure_type_fks().length > 0)?obj.getStructure_type_fks()[i]:null);
					    insertStmt.setString(p++,(obj.getStructures().length > 0)?obj.getStructures()[i]:null);
					    insertStmt.addBatch();
				    }
				    insertCount = insertStmt.executeBatch();
			}
			if(insertCount.length > 0) {
					flag = true;
			}
		}
		
		   con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return flag;
	}

	@Override
	public boolean updateStructure(Structure obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = {};
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			con.setAutoCommit(false);
			String inactiveQry = "DELETE from structure  where work_id_fk = ?";		 
			stmt = con.prepareStatement(inactiveQry);
			stmt.setString(1,obj.getWork_id_fk());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			String insert_qry = "INSERT into structure ( work_id_fk, contract_id_fk, department_fk, structure_type_fk, "
					+ "structure) "
					+"VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry); 
			int arraySize = 0; 
			if(!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0) {
				obj.setStructure_type_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructure_type_fks()));
				if(arraySize < obj.getStructure_type_fks().length) {
					arraySize = obj.getStructure_type_fks().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
				obj.setStructures(CommonMethods.replaceEmptyByNullInSringArray(obj.getStructures()));
				if(arraySize < obj.getStructures().length) {
					arraySize = obj.getStructures().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getStructure_type_fks()) && obj.getStructure_type_fks().length > 0 && !StringUtils.isEmpty(obj.getStructures()) && obj.getStructures().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( obj.getStructure_type_fks().length > 0 && !StringUtils.isEmpty(obj.getStructure_type_fks()[i]) && !StringUtils.isEmpty(obj.getStructures()[i])) {
					    insertStmt.setString(p++,(obj.getWork_id_fk()));
					    insertStmt.setString(p++,(obj.getContract_id_fk()));
					    insertStmt.setString(p++,(obj.getDepartment_fk()));
					    insertStmt.setString(p++,(obj.getStructure_type_fks().length > 0)?obj.getStructure_type_fks()[i]:null);
					    insertStmt.setString(p++,(obj.getStructures().length > 0)?obj.getStructures()[i]:null);
					    insertStmt.addBatch();
				    }
				    insertCount = insertStmt.executeBatch();
			}
			if(insertCount.length > 0) {
				flag = true;
		    }
			}
			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt1, null);
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return flag;
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
					+ "(uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on) "
					+ "VALUES "
					+ "( :uploaded_file, :status, :remarks, :uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				structure_data_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setId(structure_data_id);
				flag = true;
				
				MultipartFile file = obj.getStructureFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants.STRUCTURE_UPLOADED_FILE_SAVING_PATH ;
					String fileName = structure_data_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					obj.setUploaded_file(fileName);
					String updateQry = "UPDATE structure_data set uploaded_file= :uploaded_file where id= :id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
					template.update(updateQry, paramSource1);
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
	public int uploadStructures(List<Structure> structuresList) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			String insert_qry = "INSERT into  structure ( work_id_fk, contract_id_fk, department_fk, structure_type_fk, "
					+ "structure) "
					+"VALUES (?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry); 
			for (Structure obj : structuresList) {
			   if(!StringUtils.isEmpty(obj.getWork_id_fk()) && !StringUtils.isEmpty(obj.getStructure_type_fk()) && !StringUtils.isEmpty(obj.getStructure())){
					int p = 1;
			    	String department = getDepartment(obj.getDepartment_fk(),con);
				    insertStmt.setString(p++,(obj.getWork_id_fk()));
				    insertStmt.setString(p++,(obj.getContract_id_fk()));
				    insertStmt.setString(p++,(department));
				    insertStmt.setString(p++,(obj.getStructure_type_fk()));
				    insertStmt.setString(p++,(obj.getStructure()));
				    insertStmt.addBatch();
				    count++;
				}
			    insertStmt.executeBatch();
			  }
		   
		   con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return count;
	}

	private String getDepartment(String department_name, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String department = null;
		try{
			String riskIdQry = "select department from department where department_name = ?";
			stmt = con.prepareStatement(riskIdQry);
			stmt.setString(1, department_name);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				department = rs.getString("department");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return department;
	}

	@Override
	public List<Structure> getStructureUploadsList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT id, uploaded_file, sd.status, sd.remarks, uploaded_by_user_id_fk, DATE_FORMAT(uploaded_on,'%d-%b-%Y') as uploaded_on "
					+ "from structure_data sd " 
					+ "LEFT JOIN user u ON sd.uploaded_by_user_id_fk = u.user_id "
					+ "where id is not null";
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructureExportList(Structure structure) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="SELECT s.structure_id,s.structure,w.work_name,w.work_short_name,dt.department_name,w.project_id_fk,s.department_fk,p.project_name,s.work_id_fk,s.contract_id_fk,c.contract_name," + 
					"c.contract_short_name, structure_type_fk  " + 
					"FROM structure s " + 
					"left join contract c on s.contract_id_fk = c.contract_id    " + 
					"left join work w on s.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci   " + 
					"left join project p on w.project_id_fk = p.project_id  " + 
					"left join department dt on s.department_fk = dt.department " + 
					"where structure_id is not null " ;
			
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

			
			qry = qry +" GROUP BY s.structure_id ";
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
			

			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));
		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

}
