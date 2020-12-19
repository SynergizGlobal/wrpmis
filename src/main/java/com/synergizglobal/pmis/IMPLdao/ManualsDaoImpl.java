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

import com.synergizglobal.pmis.Idao.ManualsDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Manuals;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Work;

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
			
			String qryDetails ="select manual_folders as manual_folder_fk ,m.manual_folder_fk,m.manual_name,m.attachment from pmis_dev.manual_folders mf " + 
					"left join pmis_dev.manuals m on mf.manual_folders = m.manual_folder_fk where manual_folder_fk = ?" ;
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Manuals>(Manuals.class));	
			for (Manuals manual : objsList) {
				List<Manuals> manualInfo = jdbcTemplate.query( qryDetails, new Object[] {manual.getManual_folder_fk()}, new BeanPropertyRowMapper<Manuals>(Manuals.class));
				manual.setManualsList(manualInfo);
			}
			
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addManuals(Manuals obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO manuals"
					+ "( manual_name, manual_folder_fk, attachment)"
					+ "VALUES"
					+ "(:manual_name,:manual_folder_fk,:attachment)";
			if(!StringUtils.isEmpty(obj.getManual_name()) && !StringUtils.isEmpty(obj.getManual_folder_fk()) && !StringUtils.isEmpty(obj.getAttachment())) {
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
				if(count > 0) {
					flag = true;
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	
}
