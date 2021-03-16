package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.NotificationTypeDao;
import com.synergizglobal.pmis.reference.model.Risk;
@Repository
public class NotificationTypeDaoImpl implements NotificationTypeDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Risk> getNotificationTypeList() throws Exception {
		List<Risk> objsList = null;
		try {
			String qry ="select notification_type, notification_type_icon from notification_type ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Risk>(Risk.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addNotificationType(Risk obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO notification_type"
					+ "( notification_type, notification_type_icon) VALUES (:notification_type, :notification_type_icon)";
			
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
}




