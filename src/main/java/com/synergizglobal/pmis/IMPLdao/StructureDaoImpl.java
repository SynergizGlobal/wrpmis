package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StructureDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Structure;
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

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY structure_id ";

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

			if (!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY structure_id ";

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
	public List<Structure> getContractsListFilter(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry = "SELECT s.contract_id_fk,c.contrat_short_name "
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
			qry = qry + " GROUP BY structure_id ";

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
			qry = qry + " GROUP BY structure_id ";

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

}
