package com.synergizglobal.pmis.IMPLdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.FOBDao;
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
			String qry = "select fob_id,fob_name,contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,estimated_cost,completion_cost,work_status_fk,latitude,longitude,weight,f.remark,"
					+ "contract_name,work_id_fk,work_name,module_name_fk,month,status_as_on_month "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk COLLATE utf8mb4_unicode_ci = ws.work_status_id "
					+ "LEFT OUTER JOIN work w ON ws.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "where fob_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
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
					+ "(contract_id_fk,activity_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk," 
					+"priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks) "
					+ "VALUES "
					+ "(:contract_id_fk,:activity_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:department_fk,:" 
					+ "priority_fk,:category_fk,:status_fk,:corrective_measure,:resolved_date,:escalated_to,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getFobDetails()) && obj.getFobDetails().size() > 0) {
				
				List<FOB> fobDetails = obj.getFobDetails();
				
				String qryFOBDetail = "INSERT INTO fob_deatil fob_id_fk,detail_name,value) VALUES  (:fob_id_fk,:detail_name,:title,:value)";		
				
				List<Map<String, Object>> batchValues = new ArrayList<>(fobDetails.size());
				for (FOB fob : fobDetails) {
				    batchValues.add(
				            new MapSqlParameterSource("fob_id_fk", fob.getFob_id())
				                    .addValue("detail_name", fob.getFob_detail_name())
				                    .addValue("value", fob.getFob_detail_value())
				                    .getValues());
				}

				int[] updateCounts = namedParamJdbcTemplate.batchUpdate(qryFOBDetail, batchValues.toArray(new Map[fobDetails.size()]));
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
			String qry = "select fob_id,fob_name,contract_id_fk,DATE_FORMAT(date_of_approval,'%d-%m-%Y') AS date_of_approval,DATE_FORMAT(target_date,'%d-%m-%Y') AS target_date,"
					+ "DATE_FORMAT(construction_start_date,'%d-%m-%Y') AS construction_start_date,DATE_FORMAT(actual_completion_date,'%d-%m-%Y') AS actual_completion_date,"
					+ "DATE_FORMAT(commissioning_date,'%d-%m-%Y') AS commissioning_date,estimated_cost,completion_cost,work_status_fk,latitude,longitude,weight,f.remark,"
					+ "contract_name,work_id_fk,work_name,module_name_fk,month,status_as_on_month "
					+ "from fob f "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work_status ws ON f.work_status_fk COLLATE utf8mb4_unicode_ci = ws.work_status_id "
					+ "LEFT OUTER JOIN work w ON ws.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
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
				String qryFOBDetail = "select detail_name as fob_detail_name,value as fob_detail_value "
						+ "from fob_detail f "
						+ "where fob_id_fk is not null " ;
				arrSize = 0;
				if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
					qry = qry + " and fob_id_fk = ?";
					arrSize++;
				}	
				
				pValues = new Object[arrSize];
				
				i = 0;
				if(!StringUtils.isEmpty(fobj) && !StringUtils.isEmpty(fobj.getFob_id())) {
					pValues[i++] = fobj.getFob_id();
				}
				
				objsList = jdbcTemplate.query(qryFOBDetail, pValues, new BeanPropertyRowMapper<FOB>(FOB.class));	
				
				fobj.setFobDetails(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return fobj;
	}

	@Override
	public boolean updateFOB(FOB obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFOB(FOB obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
