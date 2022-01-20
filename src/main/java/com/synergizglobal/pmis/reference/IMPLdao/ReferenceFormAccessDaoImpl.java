package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.reference.Idao.ReferenceFormAccessDao;
import com.synergizglobal.pmis.reference.model.ReferenceForms;
@Repository
public class ReferenceFormAccessDaoImpl implements ReferenceFormAccessDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<ReferenceForms> getReferenceForm(ReferenceForms obj) throws Exception {
		List<ReferenceForms> objsList = null;
		try {
			//String qry ="select reference_forms_id, name, form_url, module_fk from reference_forms Group by module_fk";
			String qry ="select form_id as reference_forms_id, form_name as name, web_form_url as form_url, module_name_fk as module_fk "
					+ "from form "
					+ "WHERE form_id = parent_form_id_sr_fk AND url_type = ?"
					+ "GROUP BY module_name_fk";

			objsList = jdbcTemplate.query( qry,new Object[]{"Reference Forms"}, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ReferenceForms> getReferenceFormssList(ReferenceForms obj) throws Exception {
		List<ReferenceForms> objsList = null;
		try {
			/*String qry = "SELECT form_id as reference_forms_id, form_name as name, web_form_url as form_url, module_name_fk as module_fk from form  " + 
					"where web_form_url is not null and web_form_url <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				qry = qry + " and module_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				pValues[i++] = obj.getModule_fk();
			}*/
			
			String qry = "SELECT form_id as reference_forms_id, form_name as name, web_form_url as form_url, module_name_fk as module_fk "
					+ "from form "
					+ "WHERE form_id = parent_form_id_sr_fk AND url_type = ? AND web_form_url is not null AND web_form_url <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				qry = qry + " and module_name_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "Reference Forms";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				pValues[i++] = obj.getModule_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ReferenceForms> getModuleNmae() throws Exception {
		List<ReferenceForms> objsList = null;
		try {
			String qry ="SELECT module_name FROM module";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public ReferenceForms getReferenceFormDetails(ReferenceForms obj) throws Exception {
		ReferenceForms referenceForm = null;
		try {
			/*String qry = "SELECT reference_forms_id, name, form_url, module_fk from reference_forms where reference_forms_id is not null";			
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				qry = qry + " and reference_forms_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				pValues[i++] = obj.getReference_forms_id();
			}*/
			
			String qry = "SELECT form_id as reference_forms_id, form_name as name, web_form_url as form_url, module_name_fk as module_fk "
					+ "FROM form "
					+ "where form_id = parent_form_id_sr_fk AND url_type = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				qry = qry + " and form_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = "Reference Forms";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				pValues[i++] = obj.getReference_forms_id();
			}
			referenceForm = (ReferenceForms)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
				
		}catch(Exception e) {
			throw new Exception(e);
		}
		return referenceForm;
	}

	@Override
	public boolean addReferenceForm(ReferenceForms obj) throws Exception {
		boolean flag = false;
		String form_id = null;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
			/*String insertQry = "INSERT INTO reference_forms"
					+ "(name, form_url, module_fk)"
					+ "VALUES"
					+ "(:name, :form_url, :module_fk)";*/
			
			obj.setUrl_type("Reference Forms");
			obj.setSoft_delete_status_fk(CommonConstants.ACTIVE);
			
			String insertQry = "INSERT INTO form"
					+ "(form_name, web_form_url, module_name_fk,url_type,soft_delete_status_fk)"
					+ "VALUES"
					+ "(:name, :form_url, :module_fk,:url_type,:soft_delete_status_fk)";
			
			/*BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);	*/		
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = namedParamJdbcTemplate.update(insertQry, paramSource, keyHolder);
			if (count > 0) {
				form_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setReference_forms_id(form_id);
				flag = true;
				
				String updateQry = "UPDATE form set "
						+ "parent_form_id_sr_fk = :reference_forms_id "
						+ "where form_id= :reference_forms_id";
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				namedParamJdbcTemplate.update(updateQry, paramSource);
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateReferenceForm(ReferenceForms obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			/*String updateQry = "UPDATE reference_forms set "
					+ "name= :name, form_url= :form_url, module_fk= :module_fk "
					+ "where reference_forms_id= :reference_forms_id";*/
			
			String updateQry = "UPDATE form set "
					+ "form_name= :name, web_form_url= :form_url, module_name_fk= :module_fk "
					+ "where form_id= :reference_forms_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
}
