package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.reference.Idao.ReferenceFormsAccessDao;
import com.synergizglobal.pmis.reference.model.ReferenceForms;

@Repository
public class ReferenceFormsAccessDaoImpl implements ReferenceFormsAccessDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<ReferenceForms> getReferenceForms() throws Exception {
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
	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception {
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

}
