package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.WbsTreeDao;
import com.synergizglobal.pmis.model.WbsTree;
import com.synergizglobal.pmis.model.WbsTree;

@Repository
public class WbsTreeDaoImpl implements WbsTreeDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Override
	public List<WbsTree> getLevel4List(WbsTree obj) throws Exception {
		List<WbsTree> objsList = null;
		String levelNo = "4";
		if(!StringUtils.isEmpty(obj.getLevelNo())) {
			levelNo = obj.getLevelNo();
		}
		try {
			String qry ="select wbs_4_name,wbs_3_name,wbs_2_name,wbs_1_name,"
					+ "CAST(sum(ifnull((((completed)/(scope))*100) * (weightage),0)) AS DECIMAL(10,2)) as actual,"
					+ "CAST(ifnull(sum(weightage),0) AS DECIMAL(10,2)) as weightage,"
					+ " CASE  WHEN baseline_finish <= CURDATE() THEN 100 WHEN baseline_start > CURDATE() "
					+ " THEN 0 ELSE CAST(sum(( ((datediff(now(),baseline_start)+1)/(datediff(baseline_finish , baseline_start)+1))*100 ) * (weightage)) AS DECIMAL(10,2)) END as 'planned' " + 
					" from p6_view pv "
					+ "left join activities a on (a.activity_name = pv.activity_name and a.structure = pv.wbs_3_name " + 
					"and a.contract_id_fk = pv.contract_id and a.structure_type_fk = pv.wbs_4_name and a.component = pv.wbs_2_name and a.component_id = pv.wbs_1_name " + 
					") where wbs_4_name is not null and wbs_4_name <> '' and baseline_finish > CURDATE() and baseline_start < CURDATE() ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + "and contract_id like CONCAT ('%', ?, '%')";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + "and contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_4_name())) {
				qry = qry + "and wbs_4_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_3_name())) {
				qry = qry + "and wbs_3_name = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_2_name())) {
				qry = qry + "and wbs_2_name = ? ";
				arrSize++;
			}
			qry = qry +" GROUP BY wbs_"+levelNo+"_name";

			Object[] pValues = new Object[arrSize];
			int i = 0;
	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_4_name())) {
				pValues[i++] = obj.getWbs_4_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_3_name())) {
				pValues[i++] = obj.getWbs_3_name();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWbs_2_name())) {
				pValues[i++] = obj.getWbs_2_name();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<WbsTree>(WbsTree.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<WbsTree> getContractsList(WbsTree obj) throws Exception {
		List<WbsTree> objsList = null;
		try {
			String qry ="select pv.contract_id,contract_short_name from contract c "
					+ "left join p6_view pv on pv.contract_id = c.contract_id where pv.contract_id is not null and pv.contract_id <> '' ";
			
			int arrSize = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				qry = qry + "and work_id_fk = ? ";
				arrSize++;
			}
			qry = qry +" GROUP BY pv.contract_id";
			Object[] pValues = new Object[arrSize];
			int i = 0;
	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id())) {
				pValues[i++] = obj.getWork_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<WbsTree>(WbsTree.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
}
