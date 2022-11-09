package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.RRreportDao;
import com.synergizglobal.pmis.model.RandRMain;

@Repository
public class RRreportDaoImpl implements RRreportDao{
	public static Logger logger = Logger.getLogger(IssueDaoImpl.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<RandRMain> getWorksFilterListInRRReport(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.work_id,w.work_short_name from rr r "
					+ "left join work w on r.work_id = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where r.work_id is not null and r.work_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY r.work_id,w.work_short_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getLocationListInRRReport(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT location_name from rr r "
					+ "left join work w on r.work_id = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where location_name is not null and location_name <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and w.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY location_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<RandRMain> getTypeOfStructuresFilterListInRRReport(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT type_of_use from rr r "
					+ "left join work w on r.work_id = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where type_of_use is not null and type_of_use <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and w.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY type_of_use";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<RandRMain> getProjectsFilterListInRRReport(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT project_id,project_name from rr r "
					+ "left join work w on r.work_id = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where r.work_id is not null and r.work_id <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and r.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY project_id,project_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public RandRMain getRandRMainData(RandRMain obj) throws Exception {
		List<RandRMain> objsList = null;
		try {
			String qry = "SELECT r.work_id,work_short_name,location_name ,type_of_use,count(identification_no)structure_id,count(physical_verification)physical_verification,count(encroachment_removal)encroachment_removal," + 
					"(case when boundary_wall_status='Completed' then 1 else 0 end) as boundary_wall_doc " + 
					",count(handed_over_to_execution)handed_over_to_execution " + 
					"from rr r "
					+ "left join work w on r.work_id = w.work_id "
					+ "left join project p on w.project_id_fk = p.project_id "
					+ " where location_name is not null and location_name <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				qry = qry + " and type_of_use = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + " and w.work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				qry = qry + " and location_name = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY r.work_id,work_short_name,location_name,type_of_use,boundary_wall_status order by work_id,location_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
				pValues[i++] = obj.getType_of_use();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
				pValues[i++] = obj.getLocation_name();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
			obj.setReport1List(objsList);
			if(objsList.size() > 0) {
					
				String qry2 = "SELECT distinct rr_id, location_name,work_short_name,r.work_id ,sub_location_name,type_of_use," + 
						"FORMAT(physical_verification,'dd-MM-yyyy') as physical_verification," + 
						"FORMAT(encroachment_removal,'dd-MM-yyyy') as encroachment_removal," + 
						"FORMAT(boundary_wall_doc,'dd-MM-yyyy') as boundary_wall_doc" + 
						",FORMAT(handed_over_to_execution,'dd-MM-yyyy') as handed_over_to_execution,verification_by,FORMAT(letter_to_mmrda,'dd-MM-yyyy') as letter_to_mmrda,FORMAT(alternate_housing_allotment,'dd-MM-yyyy') as alternate_housing_allotment,boundary_wall_status,name_of_the_owner,carpet_area,estimation_amount,identification_no " + 
						"from rr r "
						+ "left join work w on r.work_id = w.work_id "
						+ "left join project p on w.project_id_fk = p.project_id "
						+ " where location_name is not null and location_name <> '' ";
				
				int arrSize1 = 0;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
					qry2 = qry2 + " and type_of_use = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
					qry2 = qry2 + " and w.work_id = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					qry2 = qry2 + " and w.project_id_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
					qry2 = qry2 + " and location_name = ?";
					arrSize1++;
				}
				Object[] pValues1 = new Object[arrSize1];
				int j = 0;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getType_of_use())) {
					pValues1[j++] = obj.getType_of_use();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
					pValues1[j++] = obj.getWork_id();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					pValues1[j++] = obj.getProject_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLocation_name())) {
					pValues1[j++] = obj.getLocation_name();
				}
				List<RandRMain>  objsList1 = jdbcTemplate.query( qry2,pValues1, new BeanPropertyRowMapper<RandRMain>(RandRMain.class));
				obj.setReport2List(objsList1);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return obj;
	}

	
}
