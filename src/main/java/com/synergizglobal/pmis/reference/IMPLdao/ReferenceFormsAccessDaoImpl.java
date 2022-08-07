package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.constants.CommonConstants;
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
			
			String qry ="select form_id as reference_forms_id, form_name as name, web_form_url as form_url, module_name_fk as module_fk "
					+ "from form f "
					+ "LEFT JOIN module m ON module_name_fk = module_name "
					+ "WHERE form_id = parent_form_id_sr_fk AND url_type = ? AND m.soft_delete_status_fk = ? and web_form_url is not null GROUP BY form_id,module_name_fk,form_name,web_form_url ";
			
			objsList = jdbcTemplate.query( qry,new Object[]{"Reference Forms",CommonConstants.ACTIVE}, new BeanPropertyRowMapper<ReferenceForms>(ReferenceForms.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ReferenceForms> getReferencePagesList(ReferenceForms obj) throws Exception {
		List<ReferenceForms> objsList = null;
		try {
			
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

}
