package com.synergizglobal.pmis.IMPLdao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ContractResourceReportDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractResource;
import com.synergizglobal.pmis.model.User;
@Repository
public class ContractResourceReportDaoImpl implements ContractResourceReportDao{


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<ContractResource> getProjectsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `contract_resource` cr "
					+ "LEFT JOIN contract c on cr.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN `work` w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN `project` p ON w.project_id_fk = project_id where contract_id is not null  ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + "and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by project_id  order by project_id asc";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getWorkListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = new ArrayList<ContractResource>();
		try {
			String qry = "select c.work_id_fk,work_name,work_short_name,project_id_fk,project_name "
					+ "from `contract_resource` cr "
					+ "LEFT JOIN contract c on cr.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN `work` w ON c.work_id_fk = w.work_id "
					+ "LEFT OUTER JOIN `project` p ON w.project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by work_id order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
		
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}


	
	@Override
	public List<ContractResource> getHODsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select contract_id_fk,c.work_id_fk,hod_user_id_fk,user_name,designation "
					+ "from contract_resource cr "
					+ "LEFT JOIN contract c on cr.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where designation is not null and designation <>'' and user_type_fk = ?";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + "  group by user_id ORDER BY FIELD(designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T'),designation" ;


			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<ContractResource> getContractsListForContractResourceForm(ContractResource obj) throws Exception {
		List<ContractResource> objsList = null;
		try {
			String qry ="select  contract_id_fk,contract_name,contract_short_name,work_id_fk ,hod_user_id_fk "
					+ "from contract_resource cr "
					+ "LEFT JOIN contract c on cr.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id "
					+ "where contract_id is not null ";
			
			int arrSize = 0;	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " group by contract_id  order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public ContractResource getContarctResourceReportData(ContractResource obj) throws Exception {
		try {
			String qry = "SELECT resource_id, contract_id_fk, date,contract_name,contract_short_name,work_name,work_short_name, resource_type, resource_name, quantity, created_by_user_id, created_date,unit FROM `contract_resource` cr "
					+ " LEFT JOIN contract c on cr.contract_id_fk = c.contract_id "
					+ " LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on w.project_id_fk = p.project_id ";
			int arrSize = 2;
			qry = qry + "WHERE (date BETWEEN ? AND ? ) ";
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry + "group by contract_id_fk order by date asc";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = obj.getFrom_date();
			pValues[i++] = obj.getTo_date();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			
			List<ContractResource> contractList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
			obj.setContarctsList(contractList);
				
		    /*String datesQry = " select DATE_ADD(?, INTERVAL (@i:=@i+1)-1 DAY)  as `date` from ACTIVITIES,(SELECT @i:=0) gen_sub ";
			
			datesQry = datesQry + "where DATE_ADD(?,INTERVAL @i DAY) BETWEEN ? AND ?";*/
			
			String datesQry ="SELECT ADDDATE(?, INTERVAL @i:=@i+1 DAY) AS date FROM (SELECT a.a FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c) a JOIN (SELECT @i := -1) r1 WHERE @i < DATEDIFF(?, ?)";			
			
			
			Object[] dValues = new Object[3];
			int j = 0;
			dValues[j++] = obj.getFrom_date();
			dValues[j++] = obj.getTo_date();
			dValues[j++] = obj.getFrom_date();
			
			List<ContractResource> datesList = jdbcTemplate.query( datesQry,dValues, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
			obj.setDatesList(datesList);
			
			for (ContractResource dataList : contractList) {
				
				String dataQry = "SELECT resource_name,resource_type,sum(quantity) As quantity,unit,format((sum(quantity)/(SELECT  DATEDIFF( ?, ? )+1  AS days FROM contract_resource limit 1)),2)+0 as average  "
						+ " FROM `contract_resource` ";
			
				dataQry = dataQry + "WHERE (date BETWEEN ? AND ? ) ";
				int arrSz = 4;
				if(!StringUtils.isEmpty(dataList) && !StringUtils.isEmpty(dataList.getContract_id_fk())) {
					dataQry = dataQry + " and contract_id_fk = ? ";
					arrSz++;
				}
				dataQry = dataQry + "group by resource_name order by date,resource_type asc ";
				Object[] pValues1 = new Object[arrSz];
				int k = 0;
				
				pValues1[k++] = obj.getTo_date();
				pValues1[k++] = obj.getFrom_date();
				pValues1[k++] = obj.getFrom_date();
				pValues1[k++] = obj.getTo_date();
				if(!StringUtils.isEmpty(dataList) && !StringUtils.isEmpty(dataList.getContract_id_fk())) {
					pValues1[k++] = dataList.getContract_id_fk();
				}
				List<ContractResource> reportList = jdbcTemplate.query( dataQry, pValues1, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
				dataList.setDataList(reportList);
				List<ContractResource> quantityReportList = new ArrayList<ContractResource>();
				for (ContractResource quantityList : reportList) {
					String quantityQry =  "Select contract_id_fk,resource_name,resource_type,sum(quantity) As quantity, date,unit from contract_resource  ";
					
					quantityQry = quantityQry + " WHERE (date BETWEEN ? AND ? )  ";
					int arrCount = 2;
					if(!StringUtils.isEmpty(quantityList) && !StringUtils.isEmpty(dataList.getContract_id_fk())) {
						quantityQry = quantityQry + " and contract_id_fk = ?  ";
						arrCount++;
					}
					if(!StringUtils.isEmpty(quantityList) && !StringUtils.isEmpty(quantityList.getResource_name())) {
						quantityQry = quantityQry + " and resource_name = ?  ";
						arrCount++;
					}
					quantityQry = quantityQry + "Group By date,resource_name  order by date ";
					Object[] qValues1 = new Object[arrCount];
					int z = 0;
					
					qValues1[z++] = obj.getFrom_date();
					qValues1[z++] = obj.getTo_date();
					if(!StringUtils.isEmpty(quantityList) && !StringUtils.isEmpty(dataList.getContract_id_fk())) {
						qValues1[z++] = dataList.getContract_id_fk();
					}
					if(!StringUtils.isEmpty(quantityList) && !StringUtils.isEmpty(quantityList.getResource_name())) {
						qValues1[z++] = quantityList.getResource_name();
					}
					quantityReportList = jdbcTemplate.query( quantityQry, qValues1, new BeanPropertyRowMapper<ContractResource>(ContractResource.class));
					if(datesList.size() > 0) {
						for (ContractResource con : datesList) {
					        boolean found=false;
					        for (ContractResource con1 : quantityReportList) {
					            if ((con.getDate().equals(con1.getDate()))) {
					                found=true;
					                break;
					            }
					        }
					        if(!found){
					        	quantityReportList.add(con);
					        }
					    }
					}
					Collections.sort(quantityReportList, new Comparator<ContractResource>() {
			            public int compare(ContractResource p1, ContractResource p2) {
			                return String.valueOf(p1.getDate()).compareTo(p2.getDate());
			            }
			        });
					quantityList.setQuantityList(quantityReportList);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return obj;
	}
}
