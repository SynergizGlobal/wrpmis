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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.FOBDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.model.FOB;

@Repository
public class FOBDaoImpl implements FOBDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<FOB> getFOBList(FOB obj) throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select fob_id,fob_name,f.contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(f.actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,f.estimated_cost,f.completion_cost,work_status_fk,latitude,longitude,f.remarks,"
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
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);		
			
			String qry = "INSERT INTO fob"
					+ "(fob_id,fob_name,contract_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,commissioning_date,"
					+ "estimated_cost,completion_cost,work_status_fk,latitude,longitude,remarks) "
					+ "VALUES "
					+ "(:fob_id,fob_name,:contract_id_fk,:date_of_approval,:target_date,:construction_start_date,:actual_completion_date,:commissioning_date,:" 
					+ "estimated_cost,:completion_cost,:work_status_fk,:latitude,:longitude,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
				obj.setFob_detail_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_names()));
			}
			if(flag && !StringUtils.isEmpty(obj.getFob_detail_values()) && obj.getFob_detail_values().length > 0) {
				obj.setFob_detail_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getFob_detail_values()));
			}
			
			if(flag && !StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
			            new BatchPreparedStatementSetter() {			                 
			                @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {	
			                	int k = 1;
								ps.setString(1, obj.getFob_id());
								ps.setString(k++,fobDetailNames[i]);
								ps.setString(k++, fobDetailValues[i]);
			                }
			                @Override  
			                public int getBatchSize() {
			                    return obj.getFob_detail_names().length;
			                }
			            });
				
			}
		}catch(Exception e){ 
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
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,f.estimated_cost,f.completion_cost,work_status_fk,latitude,longitude,f.remarks,"
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
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE fob set "
					+ "fob_name = :fob_name,contract_id_fk = :contract_id_fk,date_of_approval = :date_of_approval,target_date = :target_date,construction_start_date = :construction_start_date,actual_completion_date = :actual_completion_date,commissioning_date = :commissioning_date,"
					+"estimated_cost = :estimated_cost,completion_cost = :completion_cost,work_status_fk = :work_status_fk,latitude = :latitude,longitude = :longitude,remarks = :remarks "
					+ "where fob_id = :fob_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getFob_detail_names()) && obj.getFob_detail_names().length > 0) {
				
				String deleteQry = "DELETE from fob_detail where fob_id_fk = :fob_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);				
				
				String[] fobDetailNames = obj.getFob_detail_names();
				String[] fobDetailValues = obj.getFob_detail_values();
				
				String qryFOBDetail = "INSERT INTO fob_detail (fob_id_fk,detail_name,value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail, new BatchPreparedStatementSetter() { 
					                @Override
					                public void setValues(PreparedStatement ps, int i) throws SQLException {
					                	int k = 1;
					                	ps.setString(k++, obj.getFob_id());
										if(!StringUtils.isEmpty(fobDetailNames) && fobDetailNames.length > 0) {
											 ps.setString(k++,fobDetailNames[i]);
										}else {
											 ps.setString(k++, null);
										}	
										if(!StringUtils.isEmpty(fobDetailValues) && fobDetailValues.length > 0) {
											 ps.setString(k++, fobDetailValues[i]);
										}else {
											 ps.setString(k++, null);
										}	
					                    
					                }
					                @Override  
					                public int getBatchSize() {
					                    return obj.getFob_detail_names().length;
					                }
					           });
				
			}
		}catch(Exception e){ 
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
	public List<FOB> contractListFromFOB() throws Exception {
		List<FOB> objsList = null;
		try {
			String qry = "select f.contract_id_fk,contract_id,contract_name "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON f.contract_id_fk = c.contract_id ";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FOB>(FOB.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
