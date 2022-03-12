package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.GalleryPageDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.Structure;
@Repository
public class GalleryPageDaoImpl implements GalleryPageDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Structure> getGalleryList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
		
			String qry ="select id, structure_id_fk, name, attachment, structure_file_type_fk, DATE_FORMAT(sd.created_date,'%d-%m-%Y') AS created_date, sd.status " 
					+ " from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "where attachment is not null and sd.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				qry = qry + " and DATE_FORMAT(sd.created_date,'%Y-%m') = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY id";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getMonthList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="select date_format(created_date,'%b-%y') as created_date, DATE_FORMAT(created_date,'%Y-%m')as valueDate  " 
					+ " from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "where attachment is not null and sd.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				qry = qry + " and DATE_FORMAT(sd.created_date,'%Y-%m') = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY DATE_FORMAT(created_date,'%Y-%m')";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructuresList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="select structure_type_fk from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "where attachment is not null and sd.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				qry = qry + " and DATE_FORMAT(sd.created_date,'%Y-%m') = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY structure_type_fk";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Structure> getStructureIdList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
			String qry ="select structure from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "where attachment is not null and sd.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				qry = qry + " and DATE_FORMAT(sd.created_date,'%Y-%m') = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and structure = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY structure";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
}
