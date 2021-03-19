package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
			String qry ="select reference_forms_id, name, form_url, module_fk from reference_forms Group by module_fk";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<ReferenceForms> getReferenceFormssList(ReferenceForms obj) throws Exception {
		List<ReferenceForms> objsList = null;
		try {
			String qry = "SELECT reference_forms_id, name, form_url, module_fk from reference_forms  " + 
					"where form_url is not null and form_url <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				qry = qry + " and module_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getModule_fk())) {
				pValues[i++] = obj.getModule_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public ReferenceForms getReferenceFormDetails(ReferenceForms obj) throws Exception {
		ReferenceForms referenceForm = null;
		try {
			String qry = "SELECT reference_forms_id, name, form_url, module_fk from reference_forms where reference_forms_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				qry = qry + " and reference_forms_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReference_forms_id())) {
				pValues[i++] = obj.getReference_forms_id();
			}
			referenceForm = (ReferenceForms)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
				
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return referenceForm;
	}

	@Override
	public boolean addReferenceForm(ReferenceForms obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
			String insertQry = "INSERT INTO reference_forms"
					+ "(name, form_url, module_fk)"
					+ "VALUES"
					+ "(:name, :form_url, :module_fk)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateReferenceForm(ReferenceForms obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE reference_forms set "
					+ "name= :name, form_url= :form_url, module_fk= :module_fk "
					+ "where reference_forms_id= :reference_forms_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}
}
