package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.TemplateUploadDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Repository
public class TemplateUploadDaoImpl implements TemplateUploadDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getTemplatesList() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="select id, template_name, attachment, uploaded_on, uploaded_by, status from upload_templates where status = ?";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean uploadTemplate(TrainingType obj) throws Exception {
		boolean flag = false;
		int count = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			String  updateReferenceTableQry = "UPDATE upload_templates SET status = ? where template_name = ?";		
			stmt = con.prepareStatement(updateReferenceTableQry);
			stmt.setString(1,CommonConstants.INACTIVE);
			stmt.setString(2,obj.getTemplate_name());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}	
			obj.setStatus(CommonConstants.ACTIVE);
			String insertQry = "INSERT INTO upload_templates"
					+ "( template_name,attachment,uploaded_on,uploaded_by,status) VALUES (:template_name, :attachment, :uploaded_on, :uploaded_by, :status)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			 count = namedParamJdbcTemplate.update(insertQry, paramSource);			
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
	public boolean deleteTemplate(TrainingType obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM upload_templates where id= :id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public List<TrainingType> getTemplateHistoryList(TrainingType obj) throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry = "SELECT id, template_name, attachment, uploaded_on, uploaded_by, status from upload_templates  " ;
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTemplate_name())) {
				qry = qry + " where template_name = ? ";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getTemplate_name())) {
				pValues[i++] = obj.getTemplate_name();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
	
}
