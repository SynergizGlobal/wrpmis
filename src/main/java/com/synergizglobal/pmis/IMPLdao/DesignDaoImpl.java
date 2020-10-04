package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DesignDao;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.User;

@Repository
public class DesignDaoImpl implements DesignDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Design> design(Design obj)throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select design_id,d.contract_id_fk,d.structure_type_fk,d.drawing_type_fk,d.department_id_fk,d.hod,d.dy_hod,d.structure_type_fk,d.contractor_drawing_no,"
					+ "d.mrvc_drawing_no,d.division_drawing_no,d.drawing_title,d.hq_drawing_no " + 
					"from design d "
					+ "LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					
					+ "LEFT JOIN user u on d.hod = u.user_id where design_id is not null"
					;
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
		 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
	}
		return objsList;
	}
	
	@Override
	public List<Design> structureList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select structure_type as structure_type_fk from structure_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Design> drawingTypeList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select drawing_type as drawing_type_fk from drawing_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Design> getDepartmentList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select department as department_id_fk,department_name from department";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
