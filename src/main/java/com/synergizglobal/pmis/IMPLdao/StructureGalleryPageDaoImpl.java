package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.StructureGalleryPageDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Structure;
import com.synergizglobal.pmis.model.Structure;
@Repository
public class StructureGalleryPageDaoImpl implements StructureGalleryPageDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<Structure> getGalleryList(Structure obj) throws Exception {
		List<Structure> objsList = null;
		try {
		
			String qry ="select id, structure_id_fk, name, sd.attachment,w.work_short_name,w.work_name, structure_file_type_fk, DATE_FORMAT(sd.created_date,'%d-%m-%Y') AS created_date " 
					+ " from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "where sd.attachment is not null and structure_file_type_fk = 'Site photograph' ";
			
			int arrSize = 0;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY id order by sd.created_date desc";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
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
			String qry ="select date_format(created_date,'%b-%y') as created_date,w.work_short_name, DATE_FORMAT(created_date,'%Y-%m')as valueDate  " 
					+ " from structure_documents sd "
					+ "LEFT JOIN structure s on sd.structure_id_fk = s.structure_id "
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "where sd.attachment is not null and structure_file_type_fk = 'Site photograph' ";
			
			int arrSize = 0;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY DATE_FORMAT(created_date,'%Y-%m') order by sd.created_date desc";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
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
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "where sd.attachment is not null and structure_file_type_fk = 'Site photograph' ";
			
			int arrSize = 0;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			qry = qry +" GROUP BY structure_type_fk";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
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
					+ "LEFT JOIN work w on s.work_id_fk = w.work_id "
					+ "where sd.attachment is not null and structure_file_type_fk = 'Site photograph' ";
			
			int arrSize = 0;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			qry = qry +" GROUP BY structure";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCreated_date())) {
				pValues[i++] = obj.getCreated_date();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Structure getWorkShortName(Structure obj) throws Exception {
		Structure work_short_name = null;
		try {
			String qry ="select work_short_name from work w  ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " where work_id = ?";
				arrSize++;
			}
			qry = qry +" GROUP BY work_id";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			work_short_name = (Structure)jdbcTemplate.queryForObject( qry,pValues, new BeanPropertyRowMapper<Structure>(Structure.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return work_short_name;
	}
	
}
