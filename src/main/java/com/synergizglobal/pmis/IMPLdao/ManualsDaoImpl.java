package com.synergizglobal.pmis.IMPLdao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.ManualsDao;

@Repository
public class ManualsDaoImpl implements ManualsDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
}
