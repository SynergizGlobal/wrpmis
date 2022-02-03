package com.synergizglobal.pmis.reference.IMPLdao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.RRLocationDao;
import com.synergizglobal.pmis.reference.model.Risk;

@Repository
public class RRLocationDaoImpl implements RRLocationDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public Risk getRRLocationDetails(Risk obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRRLocation(Risk obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRRLocation(Risk obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRRLocation(Risk obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
