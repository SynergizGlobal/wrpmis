package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ManualsDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Manuals;
import com.synergizglobal.pmis.model.Training;

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

	@Override
	public Manuals getFoldersDataList(Manuals obj) throws Exception {
		List<Manuals> objsList = null;
		Manuals mObj = null;
		try {
			String qry ="select manual_folders as manual_folder_fk from manual_folders ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Manuals>(Manuals.class));	
			if(!StringUtils.isEmpty(objsList) ) {
				
				String qry1 ="select manual_folders as manual_folder_fk ,m.manual_folder_fk,m.manual_name,m.attachment from pmis_dev.manual_folders mf " + 
						"left join pmis_dev.manuals m on mf.manual_folders = m.manual_folder_fk " ;
				objsList = jdbcTemplate.query( qry1, new BeanPropertyRowMapper<Manuals>(Manuals.class));	
				obj.setManualsList(objsList); 
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return obj;
	}

	
}
