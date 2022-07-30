package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.FOBGalleryDao;
import com.synergizglobal.pmis.model.FOBGallery;

@Repository
public class FOBGalleryDaoImpl implements FOBGalleryDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception {
		List<FOBGallery> objsList = null;
		try {
			String qry = "select fob_id_fk,attachment,FORMAT(created_date,'dd-MMM-yy') as created_date from fob_files where fob_id_fk = ? " ;
			
			objsList = jdbcTemplate.query( qry, new Object[] {fob_id}, new BeanPropertyRowMapper<FOBGallery>(FOBGallery.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

}
