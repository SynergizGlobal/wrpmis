package com.synergizglobal.pmis.reference.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.reference.Idao.RiskDeleteDao;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Repository
public class RiskDeleteDaoImpl implements RiskDeleteDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<TrainingType> getRisksList() throws Exception {
		List<TrainingType> objsList = null;
		try {
			String qry ="SELECT sub_work,date, count(*) as count" + 
					" FROM pmis.risk left join risk_revision on risk_id_pk= risk_id_pk_fk group by sub_work, date";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TrainingType>(TrainingType.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean deleteRisk(TrainingType obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
