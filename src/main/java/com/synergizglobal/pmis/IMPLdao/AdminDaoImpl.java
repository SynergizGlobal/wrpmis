package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AdminDao;
import com.synergizglobal.pmis.model.Admin;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Admin> getAdminList() throws Exception {
		List<Admin> objsList = null;
		try {
			String qry ="SELECT admin_form_id, form_name, url, priority, soft_delete_status_fk FROM admin_form";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Admin>(Admin.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public Admin getAdminDetails(Admin obj) throws Exception {
		Admin admin = null;
		try {
			String qry = "SELECT admin_form_id, form_name, url, priority, soft_delete_status_fk from admin_form where admin_form_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAdmin_form_id())) {
				qry = qry + " and admin_form_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAdmin_form_id())) {
				pValues[i++] = obj.getAdmin_form_id();
			}
			admin = (Admin)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Admin>(Admin.class));	
				
		}catch(Exception e) {
			throw new Exception(e);
		}
		return admin;
	}

	@Override
	public boolean addAdmin(Admin admin) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO admin_form"
					+ "(form_name, url, priority, soft_delete_status_fk)"
					+ "VALUES"
					+ "(:form_name, :url,:priority, :soft_delete_status_fk)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(admin);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateAdmin(Admin admin) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE admin_form set "
					+ "form_name= :form_name,url= :url,priority= :priority,soft_delete_status_fk= :soft_delete_status_fk "
					+ "where admin_form_id= :admin_form_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(admin);		 
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

	@Override
	public List<Admin> getStatus(Admin obj) throws Exception {
		List<Admin> objsList = null;
		try {
			String qry ="select soft_delete_status as soft_delete_status_fk from soft_delete_status ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Admin>(Admin.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

}
