package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.FOBDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FOB;

@Repository
public class FOBDaoImpl implements FOBDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<FOB> getFOBList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,"
					+ "contract_name,c.work_id_fk,work_name,module_name_fk,month,status_as_on_month,w.project_id_fk,p.project_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())) {
				pValues[i++] = obj.getWork_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addFOB(FOB obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			
			String qry = "INSERT INTO fob"
					+ "(fob_id,fob_name,contract_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,commissioning_date,"
					+ "estimated_cost,last_sanctioned_cost,completion_cost,work_status_fk,latitude,longitude,remarks,attachment) "
					+ "VALUES "
					+ "(:fob_id,fob_name,:contract_id_fk,:date_of_approval,:target_date,:construction_start_date,:actual_completion_date,:commissioning_date,:" 
					+ "estimated_cost,:last_sanctioned_cost,:completion_cost,:work_status_fk,:latitude,:longitude,:remarks,:attachment)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag) {
				if(flag && !StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
				}
				if(flag && !StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
				}
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
		            new BatchPreparedStatementSetter() {			                 
		                @Override
		                public void setValues(PreparedStatement ps, int i) throws SQLException {	
		                	int k = 1;
							ps.setString(1, obj.getFob_id());
							ps.setString(k++, fobDetailNames.length > 0 ?fobDetailNames[i]:null);
							ps.setString(k++, fobDetailValues.length > 0 ?fobDetailValues[i]:null);
		                }
		                @Override  
		                public int getBatchSize() {		                	
		                	int arraySize = 0;
		    				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
		    					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
		    					if(arraySize < obj.getFob_detail_names().length) {
		    						arraySize = obj.getFob_detail_names().length;
		    					}
		    				}
		    				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
		    					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
		    					if(arraySize < obj.getFob_detail_values().length) {
		    						arraySize = obj.getFob_detail_values().length;
		    					}
		    				}
		                    return arraySize;
		                }
		            });
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public FOB getFOB(FOB obj) throws Exception {
		FOB fobj = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,cast(f.estimated_cost as CHAR) as estimated_cost,cast(f.last_sanctioned_cost as CHAR) as last_sanctioned_cost,cast(f.completion_cost as CHAR) as completion_cost,work_status_fk,cast(f.latitude as CHAR) as latitude,cast(f.longitude as CHAR) as longitude,f.remarks,f.attachment,"
					+ "contract_name,c.work_id_fk,work_name,module_name_fk,month,status_as_on_month,w.project_id_fk,p.project_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk = p.project_id "					
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk = ws.work_status_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				qry = qry + " and fob_id = ?";
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFob_id())) {
				pValues[i++] = obj.getFob_id();
			}
			
			fobj = (FOB)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
			
			if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
				List<FOB> objsList = null;
				String qryFOBDetail = "select detail_name,value from fob_detail f where fob_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryFOBDetail, new Object[] {fobj.getFob_id()}, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobDetails(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return fobj;
	}

	@Override
	public boolean updateFOB(FOB obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE fob set "
					+ "fob_name = :fob_name,contract_id_fk = :contract_id_fk,date_of_approval = :date_of_approval,target_date = :target_date,construction_start_date = :construction_start_date,actual_completion_date = :actual_completion_date,commissioning_date = :commissioning_date,"
					+"estimated_cost = :estimated_cost,last_sanctioned_cost = :last_sanctioned_cost,completion_cost = :completion_cost,work_status_fk = :work_status_fk,latitude = :latitude,longitude = :longitude,remarks = :remarks,attachment=:attachment "
					+ "where fob_id = :fob_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag) {
				
				String deleteQry = "DELETE from fob_detail where fob_id_fk = :fob_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);				
				
				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
				}
				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
				}
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail, new BatchPreparedStatementSetter() { 
					                @Override
					                public void setValues(PreparedStatement ps, int i) throws SQLException {
					                	int k = 1;
					                	ps.setString(k++, obj.getFob_id());
										ps.setString(k++,fobDetailNames.length > 0?fobDetailNames[i]:null);
										ps.setString(k++,fobDetailValues.length > 0?fobDetailValues[i]:null);
										
					                }
					                @Override  
					                public int getBatchSize() {
					                	int arraySize = 0;
					    				if(!StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
					    					obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
					    					if(arraySize < obj.getFob_detail_names().length) {
					    						arraySize = obj.getFob_detail_names().length;
					    					}
					    				}
					    				if(!StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
					    					obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
					    					if(arraySize < obj.getFob_detail_values().length) {
					    						arraySize = obj.getFob_detail_values().length;
					    					}
					    				}
					                    return arraySize;
					                }
					           });
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteFOB(FOB obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FOB> getWorkStatusList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT work_status_fk from fob where work_status_fk is not null and work_status_fk <> ''  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY work_status_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<FOB> getContractsList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_name,c.contract_short_name from fob f " + 
					"LEFT JOIN contract c on f.contract_id_fk = c.contract_id "+
					"where contract_id_fk is not null ";
			int arrSize = 0;
		
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				qry = qry + " and work_status_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY contract_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_status_fk())){
				pValues[i++] = obj.getWork_status_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())){
				pValues[i++] = obj.getContract_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FOB>(FOB.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
