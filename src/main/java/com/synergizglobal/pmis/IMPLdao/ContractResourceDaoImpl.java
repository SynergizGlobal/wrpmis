package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.Idao.ContractResourceDao;

import com.synergizglobal.pmis.model.ContractResource;
@Repository
public class ContractResourceDaoImpl implements ContractResourceDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = new ArrayList<ContractResource>();
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getStructuresListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select fob_id as Structure_fk from fob "
					+ "where fob_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by fob_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addResource(ContractResource obj) throws Exception {
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getResource_types()) && obj.getResource_types().length > 0) {
				obj.setResource_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getResource_types()));
			}
			if(!StringUtils.isEmpty(obj.getResource_names()) && obj.getResource_names().length > 0) {
				obj.setResource_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getResource_names()));
			}
			if(!StringUtils.isEmpty(obj.getQuantitys()) && obj.getQuantitys().length > 0) {
				obj.setQuantitys(CommonMethods.replaceEmptyByNullInSringArray(obj.getQuantitys()));
			}
			String[] resourceTypes = obj.getResource_types();
			String[] resourceNames = obj.getResource_names();
			String[] resourceQuntity = obj.getQuantitys();

			String insertQry = "INSERT INTO contract_resource"
					+ "(contract_id_fk, structure_fk, date, resource_type, resource_name, quantity,created_by_user_id)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?)";
			
			int[] counts = jdbcTemplate.batchUpdate(insertQry,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
		                	ps.setString(k++, obj.getContract_id_fk());
		                	ps.setString(k++, obj.getStructure_fk());
		                	ps.setString(k++, obj.getDate());
							ps.setString(k++, resourceTypes.length > 0 ?resourceTypes[i]:null);
							ps.setString(k++, resourceNames.length > 0 ?resourceNames[i]:null);
							ps.setString(k++, resourceQuntity.length > 0 ?resourceQuntity[i]:null);
							ps.setString(k++, obj.getCreated_by_user_id());
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                	int arraySize = 0;
		    				if(!StringUtils.isEmpty(obj.getResource_types()) && obj.getResource_types().length > 0) {
		    					obj.setResource_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getResource_types()));
		    					if(arraySize < obj.getResource_types().length) {
		    						arraySize = obj.getResource_types().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getResource_names()) && obj.getResource_names().length > 0) {
		    					obj.setResource_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getResource_names()));
		    					if(arraySize < obj.getResource_names().length) {
		    						arraySize = obj.getResource_names().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getQuantitys()) && obj.getQuantitys().length > 0) {
		    					obj.setQuantitys(CommonMethods.replaceEmptyByNullInSringArray(obj.getQuantitys()));
		    					if(arraySize < obj.getQuantitys().length) {
		    						arraySize = obj.getQuantitys().length;
		    					}
		    				}
		                    return arraySize;
		                }
		            });
			if(counts.length > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
}
