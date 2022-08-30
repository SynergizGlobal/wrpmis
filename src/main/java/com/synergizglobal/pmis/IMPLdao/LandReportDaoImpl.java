package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.LandReportDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.LandAcquisition;
@Repository
public class LandReportDaoImpl implements LandReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<LandAcquisition> getProjectsFilterListInLandReport(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT project_id,project_name from la_land_identification la " +
					"left join land_executives le on la.work_id_fk = le.work_id_fk  "+
					"left join work w on la.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"where la.work_id_fk is not null and la.work_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and la.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				qry = qry + " and la_sub_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}			
			
			qry = qry + " GROUP BY project_id,project_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				pValues[i++] = obj.getLa_sub_category_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getTypeOfLandListInLandReport(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT category_fk from la_land_identification la " + 
					"left join work w on la.work_id_fk = w.work_id " +
					"left join land_executives le on la.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id " + 
					"where category_fk is not null and category_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and la.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				qry = qry + " and la_sub_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}			
			
			qry = qry + " GROUP BY category_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				pValues[i++] = obj.getLa_sub_category_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getSubCategoryOfLandFilterListInLandReport(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT la_sub_category_fk,la_sub_category from la_land_identification la " + 
					"left join work w on la.work_id_fk = w.work_id " +
					"left join land_executives le on la.work_id_fk = le.work_id_fk  "+
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join la_sub_category sc on la.la_sub_category_fk = sc.id " + 
					"where la_sub_category is not null and la_sub_category <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and la.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				qry = qry + " and la_sub_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}				
			
			qry = qry + " GROUP BY la_sub_category_fk,la_sub_category";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				pValues[i++] = obj.getLa_sub_category_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<LandAcquisition> getWorksFilterListInLandReport(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "SELECT la.work_id_fk,w.work_short_name from la_land_identification la " + 
					"left join land_executives le on la.work_id_fk = le.work_id_fk  "+
					"left join work w on la.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"where la.work_id_fk is not null and la.work_id_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and la.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				qry = qry + " and la_sub_category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and le.executive_user_id_fk = ? ";
				arrSize++;
			}			
			
			qry = qry + " GROUP BY la.work_id_fk,w.work_short_name";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				pValues[i++] = obj.getLa_sub_category_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public LandAcquisition getLandAcquisitionData(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		try {
			String qry = "select work_id_fk,work_short_name,category_fk ,la_sub_category,sum(area_to_be_acquired) as area_to_be_acquired,sum(area_acquired) as area_acquired,sum(balance_area) as balance_area,issue_id from(SELECT distinct la.work_id_fk,work_short_name,category_fk ,la_sub_category,CAST(sum(ISNULL(area_to_be_acquired,0))AS DECIMAL(10,2))\r\n" + 
					"					area_to_be_acquired,CAST(sum(ISNULL(area_acquired,0))AS DECIMAL(10,2))area_acquired,\r\n" + 
					"					(CAST(sum(ISNULL(area_to_be_acquired,0)) - sum(ISNULL(area_acquired,0))AS DECIMAL(10,2))) as balance_area,(select string_agg(value,',') as issue_id from(\r\n" + 
					"					SELECT distinct value  FROM STRING_SPLIT((select string_agg(title,',') from issue where la_id=la.la_id), ',') where value!='') as issue_id) as issue_id\r\n" + 
					"					from la_land_identification la \r\n" + 
					"					left join work w on la.work_id_fk = w.work_id \r\n" + 
					"					left join project p on w.project_id_fk = p.project_id  \r\n" + 
					"					left join la_sub_category sc on la.la_sub_category_fk = sc.id  \r\n" + 
					"					 where category_fk is not null and category_fk <> '' ";
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and la.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				qry = qry + " and la_sub_category_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY work_id_fk,work_short_name,category_fk,la_sub_category_fk,la_sub_category,la.la_id) as a " + 
					"					 group by  work_id_fk,work_short_name,category_fk,la_sub_category,issue_id order by work_id_fk,category_fk";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
				pValues[i++] = obj.getLa_sub_category_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			obj.setReport1List(objsList);
			if(objsList.size() > 0) {
					
				String qry2 = "SELECT distinct la_id,category_fk,la.work_id_fk,work_short_name,survey_number,la_sub_category,village,village_id," + 
						"CAST(ISNULL(area_to_be_acquired,0)AS DECIMAL(10,2))area_to_be_acquired," + 
						"CAST(ISNULL(area_acquired,0)AS DECIMAL(10,2))area_acquired," + 
						"(CAST(ISNULL(area_to_be_acquired,0) - ISNULL(area_acquired,0)AS DECIMAL(10,2))) as balance_area " + 
						",la_land_status_fk,(select string_agg(value,',') as issue_id from(\r\n" + 
						"SELECT distinct value  FROM STRING_SPLIT((select string_agg(title,',') from issue where la_id=la.la_id), ',') where value!='') as issue_id) as issue_id " + 
						"from la_land_identification la "+
						"left join work w on la.work_id_fk = w.work_id " + 
						"left join project p on w.project_id_fk = p.project_id " + 
						"left join la_sub_category sc on la.la_sub_category_fk = sc.id " 
						+ " where category_fk is not null and category_fk <> '' ";
				
				int arrSize1 = 0;
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
					qry2 = qry2 + " and category_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry2 = qry2 + " and la.work_id_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					qry2 = qry2 + " and w.project_id_fk = ?";
					arrSize1++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
					qry2 = qry2 + " and la_sub_category_fk = ?";
					arrSize1++;
				}
				//qry = qry + " GROUP BY la.la_id ";
				
				Object[] pValues1 = new Object[arrSize1];
				int j = 0;
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
					pValues1[j++] = obj.getCategory_fk();
				}	
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues1[j++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
					pValues1[j++] = obj.getProject_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getLa_sub_category_fk())) {
					pValues1[j++] = obj.getLa_sub_category_fk();
				}
				List<LandAcquisition>  objsList1 = jdbcTemplate.query( qry2,pValues1, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
				obj.setReport2List(objsList1);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return obj;
	}
	
	
	
}
