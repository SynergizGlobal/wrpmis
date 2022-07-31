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
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.Idao.ContractResourceDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractResource;
import com.synergizglobal.pmis.model.FormHistory;
@Repository
public class ContractResourceDaoImpl implements ContractResourceDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		List<ContractResource> objsList1 = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from contract c "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = project_id where contract_id is not null  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
			}
			qry = qry + " group by project_id  order by project_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				objsList1 = getExecutivesList(obj);	
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				if(objsList1.size() > 0) {
					for (ContractResource con : objsList1) {
				        boolean found=false;
				        for (ContractResource con1 : objsList) {
				            if ((con.getProject_id_fk().equals(con1.getProject_id_fk()))) {
				                found=true;
				                break;
				            }
				        }
				        if(!found){
				        	objsList.add(con);
				        }
				    }
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = new ArrayList<ContractResource>();
		List<ContractResource> objsList1 = null;
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
					+ "from contract c "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? )";
				arrSize++;
				arrSize++;
			}
			qry = qry + " group by work_id order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				objsList1 = getExecutivesList(obj);	
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				if(objsList1.size() > 0) {
					for (ContractResource con : objsList1) {
				        boolean found=false;
				        for (ContractResource con1 : objsList) {
				            if ((con.getWork_id_fk().equals(con1.getWork_id_fk()))) {
				                found=true;
				                break;
				            }
				        }
				        if(!found){
				        	objsList.add(con);
				        }
				    }
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		List<ContractResource> objsList1 = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? )";
				arrSize++;
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				objsList1 = getExecutivesList(obj);	
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				if(objsList1.size() > 0) {
					for (ContractResource con : objsList1) {
				        boolean found=false;
				        for (ContractResource con1 : objsList) {
				            if ((con.getContract_id_fk().equals(con1.getContract_id_fk()))) {
				                found=true;
				                break;
				            }
				        }
				        if(!found){
				        	objsList.add(con);
				        }
				    }
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	private List<ContractResource> getExecutivesList(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="SELECT id, w.work_name,w.work_short_name,contract_id_fk,w.project_id_fk,p.project_name,c.hod_user_id_fk as hod_user_id,"
					+ "c.work_id_fk,contract_type_fk,c.contract_id,c.contract_name,c.contract_status_fk,c.dy_hod_user_id_fk as dy_hod_user_id,"
					+ "c.contract_short_name,contractor_id_fk,cr.contractor_name,c.hod_user_id_fk,c.dy_hod_user_id_fk, department_id_fk as department_fk,"
					+ " executive_user_id_fk FROM contract_executive ce "
					+ "LEFT JOIN contract c on ce.contract_id_fk = c.contract_id "+
					"left join work w on c.work_id_fk = w.work_id  " + 
					"left join contractor cr on c.contractor_id_fk = cr.contractor_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "+
					"left join department hoddt on u.department_fk = hoddt.department "
					+"left join department dt on ce.department_id_fk = dt.department "+
					"LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id where contract_id is not null ";
			
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id()) && !obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN)) {
				qry = qry + " and  executive_user_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " group by contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id()) && !obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN)) {
				pValues[i++] = obj.getUser_id();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
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
			if(!StringUtils.isEmpty(obj.getUnits()) && obj.getUnits().length > 0) {
				obj.setUnits(CommonMethods.replaceEmptyByNullInSringArray(obj.getUnits()));
			}			
			String[] resourceTypes = obj.getResource_types();
			String[] resourceNames = obj.getResource_names();
			String[] resourceQuntity = obj.getQuantitys();
			String[] resourceUnits = obj.getUnits();

			String insertQry = "INSERT INTO contract_resource"
					+ "(contract_id_fk, date, resource_type, resource_name,unit, quantity,created_by_user_id)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?)";
			
			int[] counts = jdbcTemplate.batchUpdate(insertQry,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
		                	ps.setString(k++, obj.getContract_id_fk());
		                	ps.setString(k++, obj.getDate());
							ps.setString(k++, resourceTypes.length > 0 ?resourceTypes[i]:null);
							ps.setString(k++, resourceNames.length > 0 ?resourceNames[i]:null);
							ps.setString(k++, resourceUnits.length > 0 ?resourceUnits[i]:null);
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
		    				if(!StringUtils.isEmpty(obj.getUnits()) && obj.getUnits().length > 0) {
		    					obj.setUnits(CommonMethods.replaceEmptyByNullInSringArray(obj.getUnits()));
		    					if(arraySize < obj.getUnits().length) {
		    						arraySize = obj.getUnits().length;
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
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Execution &  Monitoring");
				formHistory.setForm_name("Contract Resources");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Contract Resources created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public List<ContractResource> getResourceTypeListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select resource_type from resource_type ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<ContractResource> getUnitsListForContractResourceForm() throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select distinct unit from unit ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public ContractResource getContarctResourceReportData(ContractResource obj) throws Exception {

		return obj;
	}

	@Override
	public List<ContractResource> getSubResourceTypeListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select resource_type_fk as resource_type,sub_resource_type from sub_resource_type where sub_resource_type is not null and sub_resource_type <> ''  ";
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResource_type())) {
				qry = qry + " and resource_type_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResource_type())) {
				pValues[i++] = obj.getResource_type();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
		}catch(Exception e){ 
			e.printStackTrace();
		throw new Exception(e);
		}
		return objsList;
	}
}
