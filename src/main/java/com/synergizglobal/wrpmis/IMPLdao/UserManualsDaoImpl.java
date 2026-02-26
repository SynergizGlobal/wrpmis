package com.synergizglobal.wrpmis.IMPLdao;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants2;
import com.synergizglobal.wrpmis.Idao.UserManualsDao;
import com.synergizglobal.wrpmis.model.UserManuals;

@Repository
public class UserManualsDaoImpl implements UserManualsDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<UserManuals> getUserManuals(UserManuals obj) throws Exception {
		List<UserManuals>  objsList = null;
		try {
			String qry ="SELECT manual_id,title,file_name,FORMAT(created_date,'dd-MMM-yy') as created_date,created_by,status,priority FROM pmis_manuals "
					+ "where manual_id is not null ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				qry = qry + " and status = ?";
				arrSize++;
			}
			qry = qry + " order by priority asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus())) {
				pValues[i++] = obj.getStatus();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<UserManuals>(UserManuals.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<UserManuals> getStatusFilterListInUserManuals(UserManuals obj) throws Exception {
		List<UserManuals>  objsList = null;
		try {
			String qry ="SELECT soft_delete_status as status FROM soft_delete_status ";
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<UserManuals>(UserManuals.class));
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean addUserManual(UserManuals obj) throws Exception {
		boolean flag = false;
		String manual_id = null;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO pmis_manuals "
					+ "(title,file_name,created_by,status,priority)"
					+ "VALUES"
					+ "(:title,:file_name,:created_by,:status,:priority)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = namedParamJdbcTemplate.update(insertQry, paramSource,keyHolder);			
			if(count > 0) {
				flag = true;
				manual_id = String.valueOf(keyHolder.getKey().intValue());
				
				MultipartFile file = obj.getAttachment();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.PMIS_MANUAL_SAVING_PATH + manual_id + File.separator ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					obj.setFile_name(fileName);
					obj.setManual_id(manual_id);
					String updateQry = "UPDATE pmis_manuals set file_name= :file_name where manual_id= :manual_id ";
					paramSource = new BeanPropertySqlParameterSource(obj);		
					namedParamJdbcTemplate.update(updateQry, paramSource);
				}
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean updateUserManual(UserManuals obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE pmis_manuals SET "
					+ "title=:title,status=:status,priority=:priority WHERE manual_id=:manual_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
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
	public boolean deleteUserManual(UserManuals obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "UPDATE pmis_manuals SET "
					+ "status=:status WHERE manual_id=:manual_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
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

}
