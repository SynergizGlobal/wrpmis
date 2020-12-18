package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.ManualsDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Manuals;

@Repository
public class ManualsDaoImpl implements ManualsDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Manuals> getFoldersList() throws Exception {
		List<Manuals> objsList = null;
		try {
			String qry ="select manual_folders as manual_folder_fk from manual_folders ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Manuals>(Manuals.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	
}
